# School project of school log

## Requirements:
  - Input data validation ✅
  - Method / Constructor overloading ⭕
  - Use of encapsulation ✅
  - Use of abstraction ✅
  - Use of polymorphism ✅
  - Use of static and not static methods ✅
  - Use of collections ⭕
  - Introduction of functionality of adding, deleting, finding and sorting objects ⭕
  - Initializing at least 20 sample data on which operations will be performed ⭕
  - Error handling (optional) ✅
    
### Additional features:
  - Use of JavaFX library
  - Use of MVC app architecture
  - Use of Apache Maven
  - MySQL database connectivity 
  - Use of Database Connection Pooling with Apache DBCP2 for improving performance
  - Use of utility classes

## Week 1
  - Connecting the app to MySQL Database
  - Creating simple user UI

    ### Design assumptions:
    - Creating a login welcome-screen<br>
          - > for teachers<br>
          - > for admin<br><br>
    - Teachers are logging in with the login and passwords provided by the admin<br>
          - > teachers view is the main view of the app (main purpose of the app)<br><br>
    - Admin can add and-or delete teachers - has control over the database<br>
          - > admin account is created ONLY by manual record creation in MySQL<br><br>

## Week 2
  - Creating complex DB schema for electronic school log
  - Creating a mechanism for seeding the DB with random data
  - Getting the database to it's final state on top of which later I'll add more functionalities
    in the app itself

    ### Design assumptions:
    - No design assumptions for this week as I'll use console to debug
