# Spring, MongoDB, Reactive example: CRUD Application


| Methods | Urls                          | Actions                                       |
|---------|-------------------------------|-----------------------------------------------|
| POST    | /api/tutorials                | create new Tutorial                           |
| GET     | /api/tutorials                | retrieve all Tutorials                        |
| GET     | /api/tutorials/:id            | retrieve a Tutorial by :id                    |
| PUT     | /api/tutorials/:id            | update a Tutorial by :id                      |
| DELETE  | /api/tutorials/:id            | delete a Tutorial by :id                      |
| DELETE  | /api/tutorials                | delete all Tutorials                          |
| GET     | /api/tutorials/published      | find all published Tutorials                  |
| GET     | /api/tutorials?title=[keyword]| find all Tutorials which title contains keyword|



curl

```bash
curl --location 'http://localhost:8080/api/tutorials' \
--header 'Content-Type: application/json' \
--data '{
    "title": "Learn Java 22 and MongoDB reactive with Vy -advanced.",
    "description": "4.5 hours course"
}'
```

Get all courses

```bash
curl --location 'http://localhost:8080/api/tutorials'
```

