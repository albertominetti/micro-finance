mvn clean verify

START /B java -jar discovery-service/target/*.jar
START /B java -jar calculator-service/target/*.jar
START /B java -jar forex-service/target/*.jar
START /B java -jar market-service/target/*.jar
START /B java -jar gateway-service/target/*.jar
