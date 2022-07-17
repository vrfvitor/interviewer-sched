# Interview Scheduler
Interview calendar/scheduler backend api with Java.

## Endpoints `/availabilities`
- POST `/candidate/{id}`: registers availabilities for candidate
- POST `/interviewer/{id}`: registers availabilities for interviewer
```json
// sample payload for both POST endpoints
{ 
    "startTime": "08:00",
    "endTime": "10:00",
    "dates": [
        "2022-07-25",
        "2022-07-31",
        "2022-07-30"
    ]
}
```

- GET  `/candidate/{id}/interviewers`: get matching availabilities between candidate (id) and interviewers
  - PATH_PARAM `id`: id of the candidate
  - QUERY_PARAM `id` (list): ids of the interviewers of interest

```json
// sample response
// GET /api/availabilities/candidate/<candidateId>/interviewers?id=<idInterviewerOne>&id=<idInterviewerTwo>
[{
    "id": 1,
    "date": "2022-07-18",
    "startTime": "09:00:00",
    "participant": {
        "id": "<idInterviewerTwo>",
        "email": "interviewer.a@corp.com",
        "firstName": "Interviewer",
        "lastName": "Goodman",
        "interviewer": true
    }
}]
```


## 


## Project Specs
- db
  - postgresql

- api
  - JDK v11
  - maven v3.6
  - Java Doc
  - Spring: Web, DataJPA, Bean-Validation (custom one as well)
  - Tests: Junit 5, Zonky (Embedded Postgres DB), Hamcrest, RestAssured

## Quickstart
1. clone project
2. generate Docker image (could use ./build.sh)
3. run `docker-compose up`

Note: app is configured to load database on startup, see `schema.sql` and `data.sql` at the project resources
