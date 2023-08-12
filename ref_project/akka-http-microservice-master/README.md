# Akka HTTP microservice example

[![GitHub license](https://img.shields.io/badge/license-MIT-blue.svg)](https://raw.githubusercontent.com/theiterators/akka-http-microservice/master/COPYING)
![Build Status](https://github.com/theiterators/akka-http-microservice/actions/workflows/ci.yml/badge.svg)

**Due to recent [Akka's license change](https://www.lightbend.com/blog/why-we-are-changing-the-license-for-akka), a fork named [Pekko](https://github.com/apache/incubator-pekko) was created under the Apache Foundation. Starting a new project, you should consider using [Pekko HTTP](https://github.com/apache/incubator-pekko-http). There's a sibling open source template we've created named [pekko-http-microservice](https://github.com/theiterators/pekko-http-microservice).**

This project demonstrates the [Akka HTTP](https://doc.akka.io/docs/akka-http/current/?language=scala) library and Scala to write a simple REST (micro)service. The project shows the following tasks that are typical for most Akka HTTP-based projects:

* starting standalone HTTP server,
* handling file-based configuration,
* logging,
* routing,
* deconstructing requests,
* unmarshalling JSON entities to Scala's case classes,
* marshaling Scala's case classes to JSON responses,
* error handling,
* issuing requests to external services,
* testing with mocking of external services.

The service in the template provides two REST endpoints - one which gives GeoIP info for given IP and another for calculating geographical distance between given pair of IPs. The project uses the service [ip-api](http://ip-api.com/) which offers JSON IP and GeoIP REST API for free for non-commercial use.

If you want to read a more thorough explanation, check out [tutorial](https://github.com/theiterators/akka-http-microservice/blob/master/TUTORIAL.md).

[![Deploy to Heroku](https://www.herokucdn.com/deploy/button.png)](https://heroku.com/deploy)
[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.io/#https://github.com/theiterators/akka-http-microservice)
## Usage

Start services with sbt:

```
$ sbt
> ~reStart
```

With the service up, you can start sending HTTP requests:

```
$ curl http://localhost:9000/ip/8.8.8.8
{
  "city": "Mountain View",
  "query": "8.8.8.8",
  "country": "United States",
  "lon": -122.0881,
  "lat": 37.3845
}
```

```
$ curl -X POST -H 'Content-Type: application/json' http://localhost:9000/ip -d '{"ip1": "8.8.8.8", "ip2": "93.184.216.34"}'
{
  "distance": 4347.624347494718,
  "ip1Info": {
    "city": "Mountain View",
    "query": "8.8.8.8",
    "country": "United States",
    "lon": -122.0881,
    "lat": 37.3845
  },
  "ip2Info": {
    "city": "Norwell",
    "query": "93.184.216.34",
    "country": "United States",
    "lon": -70.8228,
    "lat": 42.1508
  }
}
```

### Testing

Execute tests using `test` command:

```
$ sbt
> test
```

## Author & license

If you have any questions regarding this project, contact:

Łukasz Sowa <lukasz@iteratorshq.com> from [Iterators](https://www.iteratorshq.com).

For licensing info see LICENSE file in project's root directory.
