## urlShortener

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


# for url in https://github.com/ https://java.com/ja/
#     do
#         curl -X POST -H "Content-Type: application/json" \
#         -d '{"url":"$url"}' \
#         http://localhost:8888/api/v1/short
#     done

# 2) list url
curl http://localhost:8888/api/v1/all_url

# 3) reverse hashcode
# get hashcode from step 1)
curl -X POST -H "Content-Type: application/json" \
    -d '{"code":"B@6a7b5ddc"}' \
    http://localhost:8888/api/v1/reverse
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
```

### Ref
- App
    - https://github.com/bugthesystem/reducio
    - https://medium.com/grasswire-engineering/a-url-shortener-service-in-45-lines-of-scala-f861d4b8e5e3
- System Design
    - https://www.youtube.com/watch?v=JQDHz72OA3c
- Algorithm
    - https://github.com/yennanliu/CS_basics/blob/master/leetcode_python/Design/encode-and-decode-tinyurl.py
- Redis client
    - https://github.com/etaty/rediscala
         https://github.com/bugthesystem/reducio/blob/master/build.sbt
