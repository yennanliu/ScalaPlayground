# TaxiService
> A taxi service built via `Scala finatra` framework
- `MVC` model
    - model : prepare data schema
    - view  : parse data structure for user interface
    - controller : handles interaction between user interface and backend
- Functionality :
  - taxi booking
  - taxi status checks
  - taxi status reset
  - time processing

| API | Type | Purpose | Example cmd | Comment|
| ----- | -------- | ---- | ----- | ---- |
| `POST /api/book` | POST | book a free taxi |please check `API endpoints Details` below ||
| `POST /api/tick` | POST | move 1 time unit forward|`curl http://localhost:8080/api/tick` | |
| `POST /api/reset` | POST | reset taxi booking status|`curl -X POST http://localhost:8888/api/reset` | |
| `PUT /api/all` | PUT |show all taxi status |`curl http://localhost:8080/api/all` | |
| `PUT /admin` | PUT | api admin UI|`http://localhost:9990/admin` | |

## 1) File structure

<details>
<summary>File structure</summary>

```
├── README.md
├── build.sbt : build file
├── script : test py script
├── src    : main source file


src
├── main
│   └── scala
│       └── com
│           └── yen
│               └── TaxiService
│                   ├── common   : common funcs
│                   ├── controller : service controller handles REST request
│                   ├── model  : data model (case class)
│                   ├── service  : service handles taxi booking logic
│                   └── serviceApp.scala : main service app
└── test
    └── scala
        └── com
            └── yen
                └── TaxiService
                    ├── common : common funcs unit test
                    ├── model : model unit test
                    └── service : service unit test
```

</details>

## 2) Run

<details>
<summary>Run</summary>

```bash
#---------------------------
# method 1 : intellJ
#---------------------------
# build, and run via intellJ (via build.sbt)

#---------------------------
# method 2 : sbt
#---------------------------
sbt build
sbt run

#---------------------------
# method 3 : java cmd
#---------------------------
# compile
sbt assembly
# run
java -cp \
target/scala-2.11/TaxiService-assembly-1.0.jar \
com.yen.TaxiService.App

# tests
# unit test
sbt test

# functional test
# Run the test cases via below py script check whether your API works correctly
python3 script/basic_solution_checker.py
```

</details>

## 3) API endpoints Details

<details>
<summary>API endpoints Details</summary>

#### 3-1) `POST /api/book`
- Service offers nearest available car to the customer location and return the total time taken to travel from the current car location to customer location then to customer destination.

```bash
curl -X POST -H "Content-Type: application/json" \
    -d '{
          "source": {
            "x": 1,
            "y": 1
          },
          "destination": {
            "x": 2,
            "y": 2
          }
        }' \
http://localhost:8888/api/book
```

#### 3-2) `POST /api/tick`
- Service offers `/api/tick` REST endpoint, when called should advance your service time stamp by 1 time unit.

```bash
curl http://localhost:8080/api/tick
```

#### 3-3) `PUT /api/reset`
- Service offers `/api/reset` REST endpoint, when called will reset all cars data back to the initial state regardless of cars that are currently booked.

```bash
curl -X POST http://localhost:8888/api/reset
```

#### 3-4) `Other endpoints`

- http://localhost:8080/api/all  : list all cars status
- http://localhost:9990/admin : service admin UI

</details>

## 4) Dependency
- Java 1.8
- scala 2.11.8
- finatra 21.1.0

## 5) TODO
- Dockerize
- refactoring
- scalability
- auto generated API doc

## 6) Ref
- [finatra tests](https://twitter.github.io/finatra/user-guide/testing/index.html)
- [ref.md](./doc/ref.md)