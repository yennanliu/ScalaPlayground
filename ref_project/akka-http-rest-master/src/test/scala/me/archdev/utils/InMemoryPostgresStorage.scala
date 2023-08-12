package me.archdev.utils

import de.flapdoodle.embed.process.runtime.Network._
import me.archdev.restapi.utils.db.{DatabaseConnector, DatabaseMigrationManager}
import ru.yandex.qatools.embed.postgresql.PostgresStarter
import ru.yandex.qatools.embed.postgresql.config.AbstractPostgresConfig.{Credentials, Net, Storage, Timeout}
import ru.yandex.qatools.embed.postgresql.config.PostgresConfig
import ru.yandex.qatools.embed.postgresql.distribution.Version

object InMemoryPostgresStorage {
  val dbHost = getLocalHost.getHostAddress
  val dbPort = 25535
  val dbName = "database-name"
  val dbUser = "user"
  val dbPassword = "password"
  val jdbcUrl = s"jdbc:postgresql://$dbHost:$dbPort/$dbName"

  val psqlConfig = new PostgresConfig(
    Version.Main.V10, new Net(dbHost, dbPort),
    new Storage(dbName), new Timeout(),
    new Credentials(dbUser, dbPassword)
  )
  val psqlInstance = PostgresStarter.getDefaultInstance
  val flywayService = new DatabaseMigrationManager(jdbcUrl, dbUser, dbPassword)

  val process = psqlInstance.prepare(psqlConfig).start()
  flywayService.dropDatabase()
  flywayService.migrateDatabaseSchema()

  val databaseConnector = new DatabaseConnector(
    InMemoryPostgresStorage.jdbcUrl,
    InMemoryPostgresStorage.dbUser,
    InMemoryPostgresStorage.dbPassword
  )
}
