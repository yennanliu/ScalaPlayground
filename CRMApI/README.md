# CRM API
- A simple API for CRM (Customer relationship management) built via finatra framework 

### Endpoints
```bash

# 1) init customer db
curl -X POST -H "Content-Type: application/json"  \
http://localhost:8888/api/v1/init

# 2) get all customers
curl http://localhost:8888/api/v1/users

# 3) get customer with id
curl http://localhost:8888/api/v1/user/1

# 4) add new customer
curl -X POST -H "Content-Type: application/json" \
    -d '{"xx":"999"}' \
    http://localhost:8888/api/v1/add/999

curl http://localhost:8888/api/v1/users
curl http://localhost:8888/api/v1/user/999

# 5) update customer
curl -X POST -H "Content-Type: application/json" \
    -d '{"id":"1", "name":"TERRY","gender":"m","age":17,"tel":"123","email":"terry@indeed.com"}' \
    http://localhost:8888/api/v1/update 

# 6) delete customer with id
curl -X POST -H "Content-Type: application/json" http://localhost:8888/api/v1/delete/1

# 7) post test
curl -X POST -H "Content-Type: application/json" \
    -d '{"id":"123", "name":"iori"}' \
    http://localhost:8888/hi 
```

