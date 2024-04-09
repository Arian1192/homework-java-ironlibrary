# IronLibrary Application

## Models

#### Author Class
The Author class represents the details of an author associated with a book in the library.
Attributes:
  - authorId: Integer - Unique identifier for the author (generated automatically).
  - name: String - Name of the author.
  - email: String - Email address of the author.

#### Book Class
The Book class represents the details of a book in the library.
Attributes:
  - isbn: String - Unique identifier for the book.
  - title: String - Title of the book.
  - category: String - Category of the book.
  - quantity: Integer - Number of copies available in the library.
    
#### Issue Class
The Issue class represents the details of a book issued to a student from the library.
Attributes:
  - issueId: Integer - Unique identifier for the issue (generated automatically).
  - issueDate: LocalDateTime - Date and time when the book was issued.
  - returnDate: LocalDateTime - Date and time when the book is expected to be returned.
  - issueStudent: Student - Student who issued the book.
  - issueBook: Book - Book issued.

#### Student Class
The Student class represents the details of a student.
Attributes:
  - usn: String - Unique student number.
  - name: String - Student's name.


## Repositories

#### AuthorRepository
Custom methods:
  - findBookByAuthor(String authorName): Find a book by author's name.
  - findAllBooksWithAuthors(): Find all books with authors.
  - findByAuthorBook(Book book): Find an author by a specific book.
    
#### BookRepository
Custom methods:
  - findAllByCategory(String Category): Find all books by category.
  - findOneByTitle(String Title): Find a book by title.
  - findByIsbn(String isbn): Find a book by ISBN.
    
#### IssueRepository
Custom methods:
  - findAllBooksByUsn(String Usn): Find all books issued by a student number.
  - findAllBooksAndIssuesByUsn(String usn): Find all books and issues by a student number.
    
#### StudentRepository
Custom methods:
  - findByUsnAndName(String usn, String name): Find a student by number and name.
  - findByUsn(String usn): Find a student by number.


## Services

#### AuthorServiceImpl
Methods:
  - save(Author author): Saves an author.
  - findBookByAuthor(String authorName): Find a book by author's name.
  - findAllBooksWithAuthors(): Find all books with authors.
  - findByAuthorBook(Book book): Findsan author by a specific book.

#### BookServiceImpl
The BookServiceImpl class implements the service for book-related operations.
Methods:
  - save(Book book): Save a book.
  - findByIsbn(String isbn): Find a book by its ISBN.
  - findAllByCategory(String category): Find all books by category.
  - findOneByTitle(String title): Find a book by title.
  - findAllByTitle(String title): Find all books by title.

#### StudentServiceImpl
The class StudentServiceImpl implements the service for operations related to students in the application.
Methods:
  - findStudentByUsnAndName(String usn, String name): Finds a student by unique number and name.
  - save(String usn, String name): Saves a new student with the specified number and name.
  - findStudentByUsn(String usn): Finds a student by their unique number.

#### IssueServiceImpl
The class IssueServiceImpl implements the service for operations related to book lending in the application.
These methods allow performing specific operations related to book lending in the IronLibrary application.
Methods:
  - save(Student student, Book book): Saves a book lending for a student.
  - findAllBooksAndIssuesByUsn(String usn): Finds all books and lendings by student number.
  - findAllBooksIssuedByUsn(String usn): Finds all books lent by student number.
  - findIssueByIsbn(String isbn): Finds a lending by the ISBN number of a book.

#### MenuServiceImpl
The MenuServiceImpl class provides methods for managing the menu and related operations in the IronLibrary application.
Methods:
  - getNewBookInformation(): Get the information necessary to add a new book.
  - getNewIssueInformation(): Get the information necessary to perform a new book issuance.
  - addBook(List<String> bookAndAuthorInformation): Add a new book with the provided information.
  - searchBookByAuthor(String authorName): Find all books by author.
  - searchBookByCategory(String category): Find all books by category.
  - searchBookByTitle(String title): Find all books by title.
  - issueBookToStudent(List<String> issueData): Create a new issue.
  - searchBooksByUsn(String usn): Find all books that are issued by a student with specific usn.
  - searchBooksAlongAuthors(): Find all books and their author.
  - mainMenu(): Manage the menu of the application.

#### IAuthorService
AuthorService interface defines the contract for author-related operations in the IronLibrary application.

#### IBookService
BookService interface defines service operations related to books.

#### IStudentService
StudentService interface defines service operations related to students.

#### IIssueService
IssueService interface defines service operations related to book issuance.

#### IMenuService
MenuService interface defines service operations related to menu management in the application.


## Utils
These classes and methods provide a way to handle exceptions and format dates properly in the IronLibrary application.

#### DataOutput
The DataOutput class provides static methods for formatting and generating data outputs related to books, authors, and loans. 
These methods provide a visually appealing way to present information related to books, authors, and loans in the IronLibrary application.
Methods:
  - oneBookTable(Book book): Creates an ASCII table to display a single book with its information.
  - listBookTable(List<Book> books): Creates an ASCII table to display a list of books with their information.
  - listBookTableWithAuthor(List<Object[]> lstObjects): Creates an ASCII table to display a list of books along with their respective authors.
  - bookIssuedDate(Issue issue): Gets the expected return date of a loaned book.
  - listBookTableByUsn(List<Object[]> lstObjects, Student student): Creates an ASCII table to display a list of books loaned by a student with their return information.

#### DateFormatter
The DateFormatter class provides static methods for formatting dates and times in different formats.
Methods:
  - simpleDateFormat(LocalDateTime returnDate): Formats the date and time into a simple "yyyy-MM-dd HH:mm:ss" format.
  - largeDateFormat(LocalDateTime returnDate): Formats the date and time into a more detailed format with time zone and day and month abbreviations.

#### BookWithActiveIssueException
The BookWithActiveIssueException class is an exception used to represent the situation where a book has an active loan. 
This could occur when attempting to loan a book that is already on loan.

#### InvalidBookInformationException
The InvalidBookInformationException class is an exception used to represent the situation where incorrect or invalid information is provided when trying to perform operations with books.

#### NoBookFoundException
The NoBookFoundException class is an exception used to represent the situation where a book is not found in the application.

#### NoStudentFoundException
The NoStudentFoundException class is an exception used to represent the situation where a student is not found in the application.


### Validator
The Validator class provides various static methods for validating and securely obtaining user input, ensuring that the entered data complies with certain patterns and formats.
Methods:
  - checkUsnFormat(String usn): Verifies if the format of the student number (USN) is correct.
  - checkISBNFormat(String maybeISBN): Verifies if the format of the ISBN number is correct.
  - checkEmailFormat(String email): Verifies if the format of the email address is correct.
  - patternMatches(String inputString, String regexPattern): Checks if the regular expression pattern matches the given input.
  - validateInteger(String input): Validates if the input is an integer number.
  - notBlankValidatorBooks(String input): Verifies if the input is not blank.
  - validateStringGeneralFormat(String input): Verifies if the string complies with a general string format, containing only letters and spaces.
Additionally, the userInput method handles obtaining user input and validating it according to a specific format, providing feedback and suggestions to the user.
These methods and the Validator class as a whole offer a secure and consistent mechanism for validating user input in the IronLibrary application.

## Run
The IronLibraryApplication class is the main class of the IronLibrary application. 
It implements the CommandLineRunner interface of Spring Boot to execute additional actions before the application starts completely.

#### Attributes:
  - isMenuEnabled: A boolean attribute indicating whether the application menu is enabled.
  - menuService: An instance of the IMenuService service that will be used to interact with the application menu.

#### Methods:
- main(String[] args): The main method that starts the Spring Boot application.
- run(String... args): The method that executes at the start of the application. If the menu is enabled, it calls the mainMenu() method of the IMenuService service.

#### Annotations:
@SpringBootApplication: An annotation that combines several annotations, including @Configuration, to enable auto-configuration of the application and scan components, configurations, and services.

#### Usage:
The IronLibraryApplication class starts the Spring Boot application and controls whether the menu should be displayed at startup, based on the value of the isMenuEnabled attribute.

### Properties
The application.properties file contains the configuration of the IronLibrary application:

#### Application Properties:
  spring.application.name=ironLibrary: Defines the name of the Spring Boot application as "ironLibrary".
  spring.datasource.url: Specifies the URL of the MySQL database that the application connects to.
  spring.datasource.username: Provides the username for the database connection.
  spring.datasource.password: Provides the password for the database connection.

#### JPA Configuration:
  spring.jpa.properties.hibernate.hbm2ddl.auto=update: Configures the automatic database schema update strategy to the JPA entity model.
  spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver: Specifies the name of the MySQL database driver class.
  spring.jpa.properties.hibernate.dialect: Defines the Hibernate dialect to interact with the database.
  
#### Other Configurations:
  app.menu.enabled=true: Enables or disables the application menu.

## TEST

### Model tests
These unit tests validate the behavior and functionality of the model classes in the IronLibrary application.

#### AuthorTest
  - createAuthor(): Verifies the creation of an author and its association with a book.

#### BookTest
Various tests to ensure that the getters and setters of the Book class work correctly.
  - Tests to validate the default constructor and proper property initialization.
  - Tests to verify object equality and hash codes.
  - Tests to update the quantity, category, and title of a book.
  - Tests to validate the format of the ISBN and ensure that the title is not null.
  - 
#### IssueTest
  - createIssue(): Tests the creation of a book loan and verifies its properties, such as the date, student, and associated book.
  - 
#### StudentTest
  - createStudent(): Verifies the correct creation of a student and its initialization.


### Repository Tests
These unit tests validate that the repositories of the IronLibrary application interact correctly with the database layer and manage business logic effectively.

#### AuthorRepositoryTest
  - Tests to check the retrieval of a book by author.
  - Tests to ensure that an empty list is received if the author has no books.
  - Tests to retrieve all books with their authors.
  - Tests to verify that the associations between books and authors are correct.
  - 
#### BookRepositoryTest
  - Tests to ensure the retrieval of a book by its ISBN, checking that the expected book is retrieved correctly.
  - Tests to validate the quantity of books of a certain category.
  - Tests to retrieve a book by its title.
  - Tests to validate that no book is found for an unregistered ISBN.
  - 
#### IssueRepositoryTest
  - Tests to verify the retrieval of books loaned by student number.
  - Tests to confirm the absence of books loaned by a student who has not made loans.
  - Tests to retrieve all loaned books with their respective details.
  - Tests to ensure the deletion of a loan.
    
#### StudentRepositoryTest
  - Test to confirm that students are saved correctly in the database.
  - Test to check the retrieval of a student by their unique number and name.

    
### Services Tests
These unit tests validate that the services of the IronLibrary application meet their requirements and behave properly in different scenarios.

#### AuthorServiceImplTest
  - Test to validate that the behavior is the same using the repository directly and using the service, confirming that different results are obtained.
    
#### BookServiceImplTest
  - Test to validate that the behavior is the same using the repository directly and using the service, confirming that different results are obtained.
    
#### IssueServiceImplTest
Unit tests to verify if:
  - All books issued by a student number are returned correctly.
  - The expected behavior is obtained when lending a book to a student.
  - It behaves correctly when trying to lend a book to a student with unavailable quantity.
  - It behaves properly when searching for books around a student.
    
#### MenuServiceImplTest
Tests to validate that:
  - Books are added with valid and invalid information, verifying that the appropriate exception is thrown.
  - Book searches by author are performed, confirming that they are found and that the correct exception is thrown if they are not found.
  - Book searches by category are performed, ensuring they work correctly and that exceptions are thrown in incorrect cases.
  - Books are issued to students correctly and that the search for loaned books is correct.

### Utils Tests
These unit tests validate that the utilities of the IronLibrary application work correctly and comply with the defined format and validation rules.

#### DataOutputTest
  - Tests to validate the generation of ASCII tables for data visualization.
  - The tests verify that the expected table is obtained for a single book, a list of books, an empty list of books, a list of books with authors, the issuance of a book, and the return of a book by student number.

#### DateFormatterTest
  - Tests to validate the date formats used in the application. Simple and long date formats are verified.
    
#### ValidatorTest
Tests to validate:
  - The format of the ISBN.
  - The format of the email.
  - The format of the student number (USN).
    
#### ValidatorTestInteger
  - Tests to validate if input numbers are valid integers.
    
#### ValidatorTestStrings
Tests to validate:
  - The presence of non-empty strings.
  - The general format of strings, such as names.
  - It also verifies behavior with null or empty strings.
    
#### ValidatorUserInputTest
  - Tests to verify if user input complies with the defined validation rules.
    It uses a console input simulator to test the validation of an ISBN input. 

### Resources
Configuration of resources for IronLibrary tests:
  - spring.application.name=ironLibrary: Defines the name of the Spring Boot application. This name can be used in various parts of the application.
  - spring.datasource.url=jdbc:mysql://localhost:3306/library_test?createDatabaseIfNotExist=TRUE: Defines the URL of the MySQL database that the application will connect to. It also indicates that if the database does not exist, it will be created.
  - spring.datasource.username=root: Username of the MySQL database.
  - spring.datasource.password=root: Password of the MySQL database.
  - spring.jpa.properties.hibernate.hbm2ddl.auto=create-drop: Configures the creation and deletion of the database schema each time the application starts.
  - spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver: Class of the database driver for MySQL connection.
  - spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect: Indicates the dialect of the MySQL database that the application will use to map JPA queries to specific SQL queries of the database.
  - spring.jpa.show-sql=true: Enables the display of SQL statements generated by Hibernate in the console. Useful for debugging and understanding the queries sent to the database.
  - spring.jpa.open-in-view=true: Configures whether to enable open session in view. This property is discouraged as it may cause unexpected database queries.
  - app.menu.enabled=false: A specific property of the application that can enable or disable a menu in the application's user interface.
