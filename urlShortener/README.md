## urlShortener

### Endpoints
```bash
# 1) short url
curl -X POST -H "Content-Type: application/json" \
    -d '{"url":"https://www.python.org/"}' \
    http://localhost:8888/api/v1/short

curl -X POST -H "Content-Type: application/json" \
    -d '{"url":"https://docs.scala-lang.org/ja/tour/tour-of-scala.html"}' \
    http://localhost:8888/api/v1/short
```

### Ref
- https://github.com/bugthesystem/reducio
- https://medium.com/grasswire-engineering/a-url-shortener-service-in-45-lines-of-scala-f861d4b8e5e3