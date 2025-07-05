Run Instructions

Run with Docker
```
docker-compose up --build
```
- NGINX: exposes port 9443 (HTTPS)

- HTTP on port 80 redirects to HTTPS

- Self-signed certificate is auto-generated 
- Manual setup of the Self-signed certificate:
```
mkdir -p ./nginx/ssl
cd ./nginx/ssl
```
- Create Private key and certificate with OpenSSL
```
openssl req -x509 -newkey rsa:4096 -nodes \
-keyout nginx-selfsigned.key \
-out nginx-selfsigned.crt \
-sha256 -days 365 \
-subj "/C=BG/ST=Sofia/L=Sofia/O=EmailGen/OU=Dev/CN=localhost"
```
nginx-selfsigned.key – Private key

nginx-selfsigned.crt – Public certificate

- Swagger UI: https://localhost:9443/swagger-ui

Run Locally
```
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```
| Role  | Username | Password |
|-------|----------|----------|
| Admin | admin    | admin123 |
| User  | user     | user123  |
|-------|----------|----------|
Example Usage

GET – Dynamic Email Generation
```
curl -u user:user123 "http://localhost:8080/api/email?expression=concat(input1.firstChars(1),input2.lastChars(3),\"@test.com\")&input1=Anna&input2=Petrova"
```
POST – Generate from Saved Template

```curl -X POST -u user:user123 http://localhost:8080/api/email \
  -H "Content-Type: application/json" \
  -d '{
    "templateId": "<UUID>",
    "inputs": {
      "input1": "Anna",
      "input2": "Petrova"
    }
  }'```

License

MIT
Vladimir Stratiev