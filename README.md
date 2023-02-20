# Banking System Application
This project is a banking system application that allows users to create different types of accounts, access their accounts, transfer money between their accounts and to other accounts, and send and receive money as third-party users. The application also includes fraud detection and the ability for admins to modify accounts and view account balances.

### Accounts
The system supports four types of accounts: StudentChecking, Checking, Savings, and CreditCard. Each account has a balance, a primary owner, and an optional secondary owner. In addition, each account type has specific requirements, such as a secret key, minimum balance, penalty fee, interest rate, monthly maintenance fee, and creation date. StudentChecking accounts do not have a monthly maintenance fee or minimum balance. Savings accounts do not have a monthly maintenance fee, but they do have an interest rate. CreditCard accounts have a credit limit and an interest rate.

### Users
The system has three types of users: Admins, AccountHolders, and ThirdParty. AccountHolders can access their own accounts using Basic Auth. Admins can create new accounts, modify account balances, and view account balances. ThirdParty users can receive and send money to other accounts.

### Technical Information
The backend of the application is developed using Java/Spring Boot and data is stored in MySQL database tables. The application includes GET, POST, PUT/PATCH, and DELETE routes, and uses Spring Security for authentication. The application also includes unit and integration tests, as well as robust error handling.

### Usage
To use the application, you can clone the repository and run it locally using a Java IDE like Eclipse or IntelliJ IDEA. You'll also need to set up a MySQL database and configure the application properties accordingly.

### Credits
This project was developed by Laura López Alonso and is licensed under the MIT License.