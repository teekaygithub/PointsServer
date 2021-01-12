README

SETUP

- Install JDK 15
	=> URL: https://www.oracle.com/java/technologies/javase/jdk15-archive-downloads.html
- Install Apache Maven
	=> Version 3.6.3
	=> URL: https://maven.apache.org/download.cgi

NOTE: This application has been tested on a Windows-x64 machine.


PROCEDURE

1. Open a shell prompt (e.g. Git-Bash)
2. Enter the following command to launch the application: 'mvn spring-boot:run'
3. To add transactions as shown in the exercise document, execute the 'example.sh'
	=> sh example.sh
4. To execute requests manually, refer to the following API endpoints:
	=> Add transactions: 									localhost:8080/api/add
		-- example JSON: {"name":"DANNON", "points":100, "date":"2020-10-31T10:00:00"}
	=> Deduct points:										localhost:8080/api/spend
		-- example: localhost:8080/api/spend?amount=100
	=> Get total points balance for each distinct payer: 	localhost:8080/api/balance
	=> Get every recorded transaction: 						localhost:8080/api/all