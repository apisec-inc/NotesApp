# NotesApp


##   How to run
```
  ./gradlew clean build
  ./gradlew bootrun
```

## URL
```
  http://localhost:8080/
  http://localhost:8080/v3/api-docs
```

## How-to authenticate


As: User/PM/Admin
```
curl -s -X POST http://localhost:8080/login -H 'accept: application/json' -H 'content-type: application/json' -d '{"username": "user","password": "password"}'
curl -s -X POST http://localhost:8080/login -H 'accept: application/json' -H 'content-type: application/json' -d '{"username": "pm","password": "password"}'
curl -s -X POST http://localhost:8080/login -H 'accept: application/json' -H 'content-type: application/json' -d '{"username": "admin","password": "password"}'

```

This should return a token like this
```
{"token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNjEwNjMwMjc0LCJpYXQiOjE2MTA1OTQyNzR9.U1ZXPOWkTj2ZdjxAJN8whj0U6T85Fp6IlrqnRK4t-6A"}
```

Ready made Apisec syntax for UserA/UserB/UserC - enter these in project / settings / environment / master / UserA/UserB/UserC
```
Authorization: Bearer {{@Cmd | curl -s -X POST http://localhost:8080/login -H 'accept: application/json' -H 'content-type: application/json' -d '{"username": "user","password": "password"}' | jq --raw-output '.token'}}
Authorization: Bearer {{@Cmd | curl -s -X POST http://localhost:8080/login -H 'accept: application/json' -H 'content-type: application/json' -d '{"username": "pm","password": "password"}' | jq --raw-output '.token'}}
Authorization: Bearer {{@Cmd | curl -s -X POST http://localhost:8080/login -H 'accept: application/json' -H 'content-type: application/json' -d '{"username": "admin","password": "password"}' | jq --raw-output '.token'}}
```
Ready made Apisec syntax for ROLES - enter these in project / settings / environment / master / ROLE_USER/ROLE_PM/ROLE_ADMIN
```
Authorization: Bearer {{@Cmd | curl -s -X POST http://localhost:8080/login -H 'accept: application/json' -H 'content-type: application/json' -d '{"username": "user","password": "password"}' | jq --raw-output '.token'}}
Authorization: Bearer {{@Cmd | curl -s -X POST http://localhost:8080/login -H 'accept: application/json' -H 'content-type: application/json' -d '{"username": "pm","password": "password"}' | jq --raw-output '.token'}}
Authorization: Bearer {{@Cmd | curl -s -X POST http://localhost:8080/login -H 'accept: application/json' -H 'content-type: application/json' -d '{"username": "admin","password": "password"}' | jq --raw-output '.token'}}
```

## How to register and onboard this app in Apisec.
- Register NotesApp - Using the OpenAPI Specification file/url from https://localhost:8080/v3/api-docs
- Deploy Private Scanner - If you're running this App on localhost / non-public IP.
- Run Category Unsecured Endpoints 
- Setup Default, UserA/UserB/UserC, & ROLE_USER/ROLE_PM/ROLE_ADMIN in the project using the values from above
- Run Category Excessive Data 
- Customize assertion and run Category Sensitive Data - 
```
Replace assertions for "Sensitive Data" category with and click "Save and Rewrite Playbooks"
@Response.$..password != @Empty
```
- Customize assertion and run Resource Limit (DDoS)
```
Replace DDoS param value in DDoS category with and click "Save and Rewrite Playbooks"
pageSize=101,page=1
```
- Customize assertion and run ABAC_1
```
Replace assertions for "ABAC_1" category with and click "Save and Rewrite Playbooks"

Disallowed:@StatusCode == 401 OR @StatusCode == 403
Create:@StatusCode == 200
```
- Run RBAC & Review - Review RBAC map, make changes and save

- Generate Penetration Testing Report