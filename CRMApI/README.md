# CRM API

### Endpoints
```
# 1) get all customers
curl http://localhost:8888/api/v1/users

# 2) get customer with id
curl http://localhost:8888/api/v1/user/1

# 3) update customer
curl -X POST -H "Content-Type: application/json" \
    -d '{"id":"1", "name":"TERRY","gender":"m","age":17,"tel":"123","email":"terry@indeed.com"}' \
    http://localhost:8888/api/v1/update 

# 4) post test
curl -X POST -H "Content-Type: application/json" \
    -d '{"id":"123", "name":"iori"}' \
    http://localhost:8888/hi 
```