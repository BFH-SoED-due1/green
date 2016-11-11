cd "`dirname "$0"`"
cd ..
open http://localhost:8080
mvn clean package site jetty:run-war
