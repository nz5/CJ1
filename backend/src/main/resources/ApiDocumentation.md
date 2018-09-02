**API documentation**



 Auth controller
 ==

    1. POST:/api/auth/signin  
 _End point for login of users_

   
 Request:
```json
{
  "email": "xxx",
  "password": "xxx"
}
```    
Response:
```json
{
"accessToken": "xxxxxxxxxxxxxxx",
"tokenType": "Bearer"
}
```


    2. POST:/api/auth/signin
_End point for registration/creation of new users_

 Request:
```json
{
  "firstName": "yyy",
  "lastName": "ddd",
  "email": "xxx",
  "password": "xxx",
  "gradYear": "2019"
}
```    
Response:
```json
{
"success": "boolean",
"message": "xxxx"
}
```


User controller
==
    1. GET:/v1/user?id=x
_End point for getting user by his id_ 

Response:
```json
{
  "id": 1,
  "firstName": "yyy",
  "lastName": "ddd",
  "email": "xxx",
  "registrationCode": "HAW20",
  "password": "xxx",
  "gradYear": "2019",
  "geoLocationOld": "xxx",
  "geoLocationNew": "xxx"
}
```

    2. Delete:/v1/user/{id}
_End point for deleting user by his id_. User will be set as disabled, so logical delete will happen.