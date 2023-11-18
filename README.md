# School project of school log

## Requirements:
  - Input data validation ✅
  - Method / Constructor overloading ✅
  - Use of encapsulation ✅
  - Use of abstraction ✅
  - Use of polymorphism ✅
  - Use of static and not static methods ✅
  - Use of collections ✅
  - Introduction of functionality of adding, deleting, finding and sorting objects ✅
  - Initializing at least 20 sample data on which operations will be performed ✅
  - Error handling (optional) ✅
    
### Additional features:
  - Use of JavaFX library
  - Use of MVC app architecture
  - Use of Apache Maven
  - MySQL database connectivity 
  - Use of Database Connection Pooling with Apache DBCP2 for improved performance
  - Use of utility classes
  - Use of helper classes
  - Use of DAO classes
  - The author gave his soul to deal with java documentation (and it's flaws (as a C# guy, it was hell))
  

## Jak uruchomić projekt
  - Projekt stworzony był w oparciu o Amazon Correto JDK w wersji 21.0.1
  - Przy uruchamianiu projektu należy użyć serwera lokalnego MySQL w wersji 8 (Laragon / XAMPP)
  - Należy się upewnić, że port to 3306, nazwa użytkownika "root", a hasło puste

  - Z repozytorium należy pobrać plik resources/db/school_log.sql i zaimportować go przy użyciu workbencha,
    bądź komendy do terminala: mysql -u root -p < school_log.sql

  - Do dowolnej lokalizacji należy przy pomocy terminala sklonować repozytorium oraz uruchomić
    w dowolnym IDE - gdy pojawi się powiadomienie o znalezieniu skryptów Mavena, należy je załadować

  - Aplikacja powinna być uruchamiana przez klasę App.

  Testowa baza danych zawiera:
  3 klasy,
  5 nauczycieli,
  3 uczniów,
  1 konto admina,
  50 lekcji

  Aby wypróbować widok admina należy zalogować się podanymi danymi:
  login: admin
  hasło: 12345678

  Aby wypróbować widok nauczyciela należy zalogować się (przykładowo) podanymi danymi:
  login: jankowalski@gmail.com
  hasło: password1

  Aby wypróbować widok ucznia należy zalogować się (przykładowo) podanymi danymi:
  login: john.doe@gmail.com
  hasło: password1
  

  

