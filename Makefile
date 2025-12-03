.PHONY: run-payment run-product

run-payment:
	mvn -f payment/pom.xml spring-boot:run

run-product:
	mvn -f product/pom.xml spring-boot:run
