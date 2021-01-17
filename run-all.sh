mvn clean verify

java -jar discovery-service/target/*.jar &
java -jar calculator-service/target/*.jar &
java -jar forex-service/target/*.jar &
java -jar market-service/target/*.jar &
java -jar gateway-service/target/*.jar &
