#!/bin/bash

# Register a new user
echo "Registering user..."
REG_RESPONSE=$(curl -v -X POST http://localhost:8080/api/auth/signup \
-H "Content-Type: application/json" \
-d '{
    "username": "testuser",
    "email": "test@example.com",
    "password": "password123",
    "fullName": "Test User"
}' 2>&1)

echo "\nRegistration response:"
echo "$REG_RESPONSE"

echo "\nLogging in..."
# Login to get JWT token
LOGIN_RESPONSE=$(curl -s -X POST http://localhost:8080/api/auth/login \
-H "Content-Type: application/json" \
-d '{
    "username": "testuser",
    "password": "password123"
}' 2>&1)

echo "\nLogin response:"
echo "$LOGIN_RESPONSE"

TOKEN=$(echo "$LOGIN_RESPONSE" | jq -r '.token')

echo "\nSending test message..."
# Send a test message
MESSAGE_RESPONSE=$(curl -v -X POST http://localhost:8080/api/messages \
-H "Authorization: Bearer $TOKEN" \
-H "Content-Type: application/json" \
-H "X-User-Id: 1" \
-d '{
    "receiverId": 1,
    "content": "Hello, this is a test message!"
}' 2>&1)

echo "\nMessage response:"
echo "$MESSAGE_RESPONSE"

echo "\nGetting message history..."
# Get message history
HISTORY_RESPONSE=$(curl -v -X GET http://localhost:8080/api/messages \
-H "Authorization: Bearer $TOKEN" \
-H "X-User-Id: 1" 2>&1)

echo "\nHistory response:"
echo "$HISTORY_RESPONSE"
