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

Built-in Users
```
user/password
pm/password
admin/password
```

Login URL
```
http://localhost:8080/login
```


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

Ready made Apisec syntax for Default/UserA/UserB/UserC & ROLES - enter these in project / settings / environment / master / "Add Bulk Authentications" 
```
Default||Token||Authorization: Bearer {{@Cmd | curl -s -X POST http://localhost:8080/login -H 'accept: application/json' -H 'content-type: application/json' -d '{"username": "user","password": "password"}' | jq --raw-output '.token'}}
UserA||Token||Authorization: Bearer {{@Cmd | curl -s -X POST http://localhost:8080/login -H 'accept: application/json' -H 'content-type: application/json' -d '{"username": "user","password": "password"}' | jq --raw-output '.token'}}
UserB||Token||Authorization: Bearer {{@Cmd | curl -s -X POST http://localhost:8080/login -H 'accept: application/json' -H 'content-type: application/json' -d '{"username": "pm","password": "password"}' | jq --raw-output '.token'}}
UserC||Token||Authorization: Bearer {{@Cmd | curl -s -X POST http://localhost:8080/login -H 'accept: application/json' -H 'content-type: application/json' -d '{"username": "admin","password": "password"}' | jq --raw-output '.token'}}
ROLE_USER||Token||Authorization: Bearer {{@Cmd | curl -s -X POST http://localhost:8080/login -H 'accept: application/json' -H 'content-type: application/json' -d '{"username": "user","password": "password"}' | jq --raw-output '.token'}}
ROLE_PM||Token||Authorization: Bearer {{@Cmd | curl -s -X POST http://localhost:8080/login -H 'accept: application/json' -H 'content-type: application/json' -d '{"username": "pm","password": "password"}' | jq --raw-output '.token'}}
ROLE_ADMIN||Token||Authorization: Bearer {{@Cmd | curl -s -X POST http://localhost:8080/login -H 'accept: application/json' -H 'content-type: application/json' -d '{"username": "admin","password": "password"}' | jq --raw-output '.token'}}
```

Another example of formatted test credentials usinig @CmdCache 
```
Default||Token||Authorization: Bearer {{@CmdCache | curl -s -X POST http://localhost:8080/login -H 'accept: application/json' -H 'content-type: application/json' -d '{"username": "user","password": "password"}' | jq --raw-output '.token'}}
UserA||Token||Authorization: Bearer {{@CmdCache | curl -s -X POST http://localhost:8080/login -H 'accept: application/json' -H 'content-type: application/json' -d '{"username": "user","password": "password"}' | jq --raw-output '.token'}}
UserB||Token||Authorization: Bearer {{@CmdCache | curl -s -X POST http://localhost:8080/login -H 'accept: application/json' -H 'content-type: application/json' -d '{"username": "pm","password": "password"}' | jq --raw-output '.token'}}
UserC||Token||Authorization: Bearer {{@CmdCache | curl -s -X POST http://localhost:8080/login -H 'accept: application/json' -H 'content-type: application/json' -d '{"username": "admin","password": "password"}' | jq --raw-output '.token'}}
ROLE_USER||Token||Authorization: Bearer {{@CmdCache | curl -s -X POST http://localhost:8080/login -H 'accept: application/json' -H 'content-type: application/json' -d '{"username": "user","password": "password"}' | jq --raw-output '.token'}}
ROLE_PM||Token||Authorization: Bearer {{@CmdCache | curl -s -X POST http://localhost:8080/login -H 'accept: application/json' -H 'content-type: application/json' -d '{"username": "pm","password": "password"}' | jq --raw-output '.token'}}
ROLE_ADMIN||Token||Authorization: Bearer {{@CmdCache | curl -s -X POST http://localhost:8080/login -H 'accept: application/json' -H 'content-type: application/json' -d '{"username": "admin","password": "password"}' | jq --raw-output '.token'}}
```

## How to register and onboard this app in Apisec.
- Register the NotesApp - Using the OpenAPI Specification URL e.g. http://notesapp.apisec.ai:8282/v3/api-docs. Note after registration the system will initiate Playbook creation process that can take  up to 1-3  mins
- Optional: Deploy Private Scanner - If you're running this App on localhost / non-public IP.
- Setup credentials: Go to Project/Settings/Environment/Master and delete 'Default,UserA,UserB,UserC,ROLE_USER,ROLE_PM,ROL_ADMIN. Click 'Add Bulk Authentications' enter text from 'Formatted Credentials' section below and hit Save.
- Run Category Unsecured Endpoints and review results
- Run Category Excessive Data & Injection and review results
- Run Category ABAC_1 and review results

- Customize & Run Category Sensitive Data. Go to Project/Categories search and select for 'Sensitive Data'. Replace assertions with below text and click "Save and Rewrite Playbooks" 
```
@Response.$..password != @Empty
```
- Customize & Run Category Resource Limit. Go to Project/Categories and search for 'Resource Limit'. Replace value in 'Comma separated query param' and click "Save and Rewrite Playbooks"
```
pageSize=101,page=1
```
- Run RBAC & Review - Review RBAC map, make changes and save

- Generate Penetration Testing Report

## Using cloud instance of NotesApp
Cloud NotesApp URL
```
http://notesapp.apisec.ai:8282/v3/api-docs
```
Formatted Credentials 
```
Default||Token||Authorization: Bearer {{@CmdCache | curl -s -X POST http://notesapp.apisec.ai:8282/login -H 'accept: application/json' -H 'content-type: application/json' -d '{"username": "user","password": "password"}' | jq --raw-output '.token'}}
UserA||Token||Authorization: Bearer {{@CmdCache | curl -s -X POST http://notesapp.apisec.ai:8282/login -H 'accept: application/json' -H 'content-type: application/json' -d '{"username": "user","password": "password"}' | jq --raw-output '.token'}}
UserB||Token||Authorization: Bearer {{@CmdCache | curl -s -X POST http://notesapp.apisec.ai:8282/login -H 'accept: application/json' -H 'content-type: application/json' -d '{"username": "pm","password": "password"}' | jq --raw-output '.token'}}
UserC||Token||Authorization: Bearer {{@CmdCache | curl -s -X POST http://notesapp.apisec.ai:8282/login -H 'accept: application/json' -H 'content-type: application/json' -d '{"username": "admin","password": "password"}' | jq --raw-output '.token'}}
ROLE_USER||Token||Authorization: Bearer {{@CmdCache | curl -s -X POST http://notesapp.apisec.ai:8282/login -H 'accept: application/json' -H 'content-type: application/json' -d '{"username": "user","password": "password"}' | jq --raw-output '.token'}}
ROLE_PM||Token||Authorization: Bearer {{@CmdCache | curl -s -X POST http://notesapp.apisec.ai:8282/login -H 'accept: application/json' -H 'content-type: application/json' -d '{"username": "pm","password": "password"}' | jq --raw-output '.token'}}
ROLE_ADMIN||Token||Authorization: Bearer {{@CmdCache | curl -s -X POST http://notesapp.apisec.ai:8282/login -H 'accept: application/json' -H 'content-type: application/json' -d '{"username": "admin","password": "password"}' | jq --raw-output '.token'}}
```

  
