#!/bin/bash
echo "Building TypeScript project..."
npm run build

echo "Building Java project..."
cd backend
mvn clean install
cd ..