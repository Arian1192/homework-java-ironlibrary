package com.ironhack.ironLibrary.service;

import com.ironhack.ironLibrary.IronLibraryApplication;
import com.ironhack.ironLibrary.model.Author;
import com.ironhack.ironLibrary.model.Book;
import com.ironhack.ironLibrary.model.Issue;
import com.ironhack.ironLibrary.model.Student;
import com.ironhack.ironLibrary.utils.DataOutput;
import com.ironhack.ironLibrary.utils.InvalidBookInformationException;
import com.ironhack.ironLibrary.utils.NoBookFoundException;
import org.springframework.beans.factory.annotation.Autowired;


import java.sql.SQLOutput;
import java.util.*;

import com.ironhack.ironLibrary.utils.Validator;
import org.springframework.stereotype.Service;


@Service
public class MenuServiceImpl  implements IMenuService{

    @Autowired
    private IAuthorService authorService;
    @Autowired
    private IBookService bookService;

    @Autowired
    private IStudentService studentService;

    @Autowired
    private IIssueService issueService;


    /**
     * TODO Testing
     * @return
     */
    public List<String> getNewBookInformation(){

        List <String> bookAndAuthorDetails = new ArrayList<>();

        String isbn = Validator.userInput("Enter isbn: ",true, "checkISBNFormat",
                "The ISBN must follow the next format: 978-92-95055-02-5");
        bookAndAuthorDetails.add(isbn);
        String title = Validator.userInput("Enter title: ", false, "NULL", "NULL");
        bookAndAuthorDetails.add(title);
        String category = Validator.userInput("Enter category: ", true, "validateStringGeneralFormat",
                "The category only accepts letters");
        bookAndAuthorDetails.add(category);
        String authorName = Validator.userInput("Enter author name: ", true, "validateStringGeneralFormat",
                "The author name only accepts letters");
        bookAndAuthorDetails.add(authorName);
        String email = Validator.userInput("Enter author email: ", true, "checkEmailFormat",
                "The email must contains an @ and a . ");
        bookAndAuthorDetails.add(email);
        String quantity = Validator.userInput("Enter number of books: ", true, "validateInteger",
                "The quantity only accepts numbers");
        bookAndAuthorDetails.add(quantity);

        return bookAndAuthorDetails;
    }

    public List<String> getNewIssueInformation(){

        List <String> issueDetails = new ArrayList<>();
        String usn = Validator.userInput("Enter usn: ", true, "validateInteger",
                "The quantity only accepts numbers");
        issueDetails.add(usn);
        String studentName = Validator.userInput("Enter name: ", true, "validateStringGeneralFormat",
                "The author name only accepts letters");
        issueDetails.add(studentName);
        String isbn = Validator.userInput("Enter book ISBN: ",true, "checkISBNFormat",
                "The ISBN must follow the next format: 978-92-95055-02-5");
        issueDetails.add(isbn);
        return issueDetails;
    }

    public void  addBook(List<String> bookAndAuthorInformation) {

        if (bookAndAuthorInformation.size() != 6) {
            throw new InvalidBookInformationException(
                    "The book and author information must be a six-element string list.");
        }

        String isbn = bookAndAuthorInformation.get(0);
        String title = bookAndAuthorInformation.get(1);
        String category = bookAndAuthorInformation.get(2);
        String authorName = bookAndAuthorInformation.get(3);
        String email = bookAndAuthorInformation.get(4);
        String quantityStr = bookAndAuthorInformation.get(5);

        if (!Validator.checkISBNFormat(isbn) ||
                !Validator.validateStringGeneralFormat(category) ||
                !Validator.validateStringGeneralFormat(authorName) ||
                !Validator.checkEmailFormat(email) ||
                !Validator.validateInteger(quantityStr)) {
            throw new InvalidBookInformationException("The provided information is invalid. Please check the format");
        }

        int quantity = Integer.parseInt(quantityStr);

        Book newBook = new Book(isbn, title, category, quantity);
        Author author = new Author(authorName, email, newBook);

        Optional<Book> optionalBook = bookService.findByIsbn(isbn);

        if(optionalBook.isPresent()){
            Book book = optionalBook.get();
            book.setCategory(category);
            book.setQuantity(quantity);
            book.setTitle(title);
            bookService.save(book);
        }else{
            authorService.save(author);
            bookService.save(newBook);
        }
    }

    public Book searchBookByAuthor(String authorName) throws NoBookFoundException {
        if (!Validator.validateStringGeneralFormat(authorName)) {
            throw new InvalidBookInformationException("The provided information is invalid. Please check the format");
        }
        Optional<Book> optionalBook = authorService.findBookByAuthor(authorName);
        if (optionalBook.isPresent()) {
            return optionalBook.get();
        } else {
            throw new NoBookFoundException("No book found for author: " + authorName);
        }
    }

    public List<Book> searchBookByCategory(String category) throws NoBookFoundException {
        if(!Validator.validateStringGeneralFormat(category)) throw new InvalidBookInformationException("The provided information is invalid. Please check the format");
       Optional<List<Book>> optionalBookList = bookService.findAllByCategory(category);
       if(optionalBookList.isPresent() && !optionalBookList.get().isEmpty()){
           return optionalBookList.get();
       }else{
           throw new NoBookFoundException("No books found for this category: " + category);
       }
    }


    @Override
    public void issueBookToStudent(List<String> issueData) throws NoBookFoundException {
        String usn = issueData.get(0);
        String name = issueData.get(1);
        String isbn = issueData.get(2);

        if (!Validator.checkISBNFormat(isbn)
                || !Validator.validateStringGeneralFormat(name)
                || !Validator.checkUsnFormat(usn)
        ) {
            throw new InvalidBookInformationException("The provided information is invalid. Please check the format");
        }

        Optional<Student> optionalStudent = studentService.findStudentByUsnAndName(usn,name);
        if(optionalStudent.isEmpty()){
            studentService.save(usn, name);
        }else{
            Student student = optionalStudent.get();
            Book book = bookService.findByIsbn(isbn).orElseThrow(() -> new NoBookFoundException("No books are found with that ISBN"));
            if (book.getQuantity() < 1) {
                throw new NoBookFoundException("Quantity unavailable");
            } else {
                book.setQuantity(book.getQuantity() - 1);
                bookService.save(book);
                issueService.save(student, book);
                // Update book quantity

            }
        }
      }


    public List<Object[]> searchBooksByUsn(String usn) throws Exception {
        Optional<Student> optionalStudent = studentService.findStudentByUsn(usn);
        if(optionalStudent.isPresent()){
            Student student = optionalStudent.get();
            Optional<List<Object[]>> optionalObjects = issueService.findAllBooksAndIssuesByUsn(student.getUsn());
            if (optionalObjects.isPresent()){
                return optionalObjects.get();
            }else{
                throw new NoBookFoundException("No books found for this usn: " + usn);
            }
        }else{
            throw new Exception("No student found for this usn: " + usn);

        }
    }

    @Override
    public List<Object[]> searchBooksAlongAuthors() throws NoBookFoundException {
        return authorService.findAllBooksWithAuthors().orElseThrow(()-> new NoBookFoundException("No Books are found"));
    }

    @Override
    public void mainManu() throws Exception {
        System.out.println("Welcome to Iron Library book management system!!");
        System.out.println("Please access one of the following options");
        Scanner scanner = new Scanner(System.in);
        int userInput = 0;
        boolean errorHandling = false;
        printMenuOptions();

        do{
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number from 1 to 8.");
                scanner.next();
            }
            userInput = scanner.nextInt();

            if (userInput < 1 || userInput > 8) {
                System.out.println("Invalid input. Please enter a number from 1 to 8.");
            } else {
                errorHandling = executeMenuOption(userInput);
                if (userInput != 8) {
                    printMenuOptions();
                }
            }
        }while(userInput != 8 || !errorHandling);

    }

    private void printMenuOptions(){
        System.out.println("""
                Menu:
                1 - Add a book
                2 - Search book by title
                3 - Search book by category
                4 - Search book by Author
                5 - List all books along with author
                6 - Issue book to student
                7 - List books by usn
                8 - Exit
                only digits between 1-8 are available""");
    }

    private boolean executeMenuOption(int userInput)  {
        boolean isError = false;
        switch (userInput){
            case 1:
                List<String> bookAndAuthorInformation = getNewBookInformation();
                addBook(bookAndAuthorInformation);
                return isError;
            case 2:
                System.out.println("option 2");
                break;
            case 3:
                String category = Validator.userInput("Enter category: ", true, "validateStringGeneralFormat",
                        "The category only accepts letters");
                List<Book> books = null;
                try {
                    books = searchBookByCategory(category);
                    System.out.println(DataOutput.listBookTable(books));
                    return isError;
                } catch (NoBookFoundException e) {
                    return true;
                }
            case 4:
                String authorName = Validator.userInput("Enter author name: ", true, "validateStringGeneralFormat",
                        "The author name only accepts letters");
                Book book = null;
                try {
                    book = searchBookByAuthor(authorName);
                    System.out.println(DataOutput.oneBookTable(book));
                    return isError;
                } catch (NoBookFoundException e) {
                    return true;
                }
            case 5:
                List<Object[]> objects = null;
                try {
                    objects = searchBooksAlongAuthors();
                    System.out.println(DataOutput.listBookTableWithAuthor(objects));
                    return isError;
                } catch (NoBookFoundException e) {
                    return true;
                }
            case 6:
                List <String> issueData = getNewIssueInformation();
                try {
                    issueBookToStudent(issueData);
                    return isError;
                } catch (NoBookFoundException e) {
                    return true;
                }
            case 7:
                String usn = Validator.userInput("Enter usn: ", true, "validateInteger",
                        "The quantity only accepts numbers");
                try {
                    List <Object[]> info = searchBooksByUsn(usn);
                    Object[] firstElement = info.get(0);
                    Issue issue = (Issue) firstElement[1];
                    Student student = issue.getIssueStudent();
                    System.out.println(DataOutput.listBookTableByUsn(info, student));
                    return isError;
                } catch (Exception e) {
                    return true;
                }
            case 8:
                System.out.println("Thanks for use our application :)");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid input. Please enter a number from 1 to 8.");
        }
        return isError;
    }



}



