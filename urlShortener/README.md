# UrlShortener
- A url shorten app via scala
- Fratures
    - long url -> short url
    - short url -> long url
    - show all shorten url

### Endpoints
- finatra health page : http://localhost:9990

```bash
# 1) short url
curl -X POST -H "Content-Type: application/json" \
    -d '{"url":"https://www.python.org/"}' \
    http://localhost:8888/api/v1/short

curl -X POST -H "Content-Type: application/json" \
    -d '{"url":"https://docs.scala-lang.org/ja/tour/tour-of-scala.html"}' \
    http://localhost:8888/api/v1/short

curl -X POST -H "Content-Type: application/json" \
    -d '{"url":"https://github.com/apache/spark"}' \
    http://localhost:8888/api/v1/short

# for url in https://github.com/ https://java.com/ja/
#     do
#         curl -X POST -H "Content-Type: application/json" \
#         -d '{"url":"$url"}' \
#         http://localhost:8888/api/v1/short
#     done

# 2) list url
curl http://localhost:8888/api/v1/all_url

# 2') list hashed url
curl http://localhost:8888/api/v1/all_hashed

# 3) reverse hashcode
# get hashcode from step 1)
# curl -X POST -H "Content-Type: application/json" \
#     -d '{"code":"B@6a7b5ddc"}' \
#     http://localhost:8888/api/v1/reverse
curl http://localhost:8888/api/v1/reverse/2a3db3bc


# 4) delete key
curl http://localhost:8888/api/v1/remove/<key>
#curl http://localhost:8888/api/v1/remove/https://www.python.org/
#curl http://localhost:8888/api/v1/remove/docs.scala-lang.orgjatourtour-of-scala.html

# 5) delete all keys
curl http://localhost:8888/api/v1/removeAll
```

### Commands
```bash
#-----------------------
# PART 1 : Redis
#-----------------------

# 1) Mac download redis
brew install redis

# 2)
# start 
brew services start redis
# restart
brew services restart redis
# stop
brew services stop redis

# start server
redis-server
# access redis via CLI
redis-cli

# test dedis
redis-cli ping

#-----------------------
# PART 2 : Redis CLI
#-----------------------

# 1) get all keys
# https://chartio.com/resources/tutorials/how-to-get-all-keys-in-redis/
127.0.0.1:6379> keys *
```

```bash
# generate api report
# via sbt
# https://docs.scala-lang.org/overviews/scaladoc/generate.html

(base) yen@macbookpro urlShortener % sbt
[info] Loading global plugins from /Users/yen/.sbt/1.0/plugins
[info] Loading settings for project urlshortener-build from assembly.sbt ...
[info] Loading project definition from /Users/yen/scalaPlayGround/urlShortener/project
[info] Loading settings for project urlshortener from build.sbt ...
[info] Set current project to urlShortener (in build file:/Users/yen/scalaPlayGround/urlShortener/)
[info] sbt server started at local:///Users/yen/.sbt/1.0/server/ec4252217fb3edafa1c3/sock
sbt:urlShortener> doc
[info] Main Scala API documentation to /Users/yen/scalaPlayGround/urlShortener/target/scala-2.11/api...
[warn] /Users/yen/scalaPlayGround/urlShortener/src/main/scala/com/yen/urlShortener/controller/Controller.scala:5:33: imported `Controller' is permanently hidden by definition of object Controller in package controller
[warn] import com.twitter.finatra.http.Controller
[warn]                                 ^
[warn] there were 5 feature warnings; re-run with -feature for details
model contains 52 documentable templates
[warn] two warnings found
[info] Main Scala API documentation successful.
[success] Total time: 6 s, completed Feb 17, 2022 12:29:14 PM
sbt:urlShortener>

# api doc: /scalaPlayGround/urlShortener/target/scala-2.11/api
```

### Ref
- App
    - https://github.com/bugthesystem/reducio
    - https://medium.com/grasswire-engineering/a-url-shortener-service-in-45-lines-of-scala-f861d4b8e5e3
- System Design
    - https://www.youtube.com/watch?v=JQDHz72OA3c
- Algorithm
    - https://github.com/yennanliu/CS_basics/blob/master/leetcode_python/Design/encode-and-decode-tinyurl.py
    - https://leetcode.com/discuss/interview-question/124658/Design-a-URL-Shortener-(-TinyURL-)-System/
- Redis client
    - https://github.com/etaty/rediscala
         https://github.com/bugthesystem/reducio/blob/master/build.sbt
    - https://lucianomolinari.com/2016/06/07/building-a-service-using-akka-http-and-redis-part-1-of-2/
    - https://lucianomolinari.com/2016/06/07/building-a-service-using-akka-http-and-redis-part-2-of-2/
