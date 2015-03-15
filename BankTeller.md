# Introduction #

BankTeller was completed by me as a project while training at the FDM Academy. This project was completed prior to learning any Java frameworks.


---

## Project Specifications ##
  * Allow user to create a current(checkings)/savings account.
  * For current accounts, a user can withdraw more than he/she has in the account, up to the overdraft limit.
  * Let the user be able to see all accounts currently in database.
  * Let the user to easily switch from database to database.
  * Provide three different forms of storage, internal code, flat file and online database.
  * Allow the user to close an account if and only if the account has a balance of exactly $0 with no overdraft.
  * Log errors, such as malformed number amounts, invalid withdrawals/deposits and invalid account closing.


---

## How to run the project ##
  1. Install Apache Tomcat onto your computer
  1. Get Eclipse IDE
  1. Download Apache Tomcat plugin for Eclipse
  1. Configure Tomcat on your Eclipse
  1. Right click project, Tomcat project -> Update context definition
  1. Run Tomcat
  1. Open your browser and type localhost:8080/BankTeller/webpages


---

## Configurations ##
### Change database type ###
The project is configured to store account information on an online database. To change the data storage type, open the resources/BankTellerDatabaseType.txt and remove/add the # symbol to the preferred storage type. If more than one database is selected, the last database selected will be used. If no database is selected, the program will throw an error.