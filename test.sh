#!/bin/bash
#
## Register a user
#echo "Registering user..."
#curl -X POST http://localhost:8080/auth/signup \
#-H "Content-Type: application/json" \
#-d '{
#        "username": "testuser",
#        "email": "test@example.com",
#        "fullName":"test",
#        "password": "password123"
#    }'
#echo "\n"
#
## Login to get JWT token
#echo "Logging in..."
TOKEN=$(curl -X POST http://localhost:8080/api/auth/login \
-H "Content-Type: application/json" \
-d '{
    "username": "testuser",
    "password": "password123"
}' | jq -r '.token')

echo "\nToken received: $TOKEN"
#echo "\n"
#
# Send a test message
echo "Sending test message..."
curl -X POST http://localhost:8080/api/messages \
-H "Authorization: Bearer $TOKEN" \
-H "Content-Type: application/json" \
-H "X-User-Id: 1" \
-d '{
    "content": "Hello, this is a test message!",
    "receiverId": 1
}'
echo "\n"

# Get message history
#echo "Getting message history..."
#curl -X GET http://localhost:8080/api/messages \
#-H "Authorization: Bearer $TOKEN"\
#-H "X-User-Id: 1" \
