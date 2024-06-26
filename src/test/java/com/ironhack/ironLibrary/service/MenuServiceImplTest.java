package com.ironhack.ironLibrary.service;

import com.ironhack.ironLibrary.model.Author;
import com.ironhack.ironLibrary.model.Book;
import com.ironhack.ironLibrary.model.Issue;
import com.ironhack.ironLibrary.model.Student;
import com.ironhack.ironLibrary.repository.AuthorRepository;
import com.ironhack.ironLibrary.repository.BookRepository;
import com.ironhack.ironLibrary.repository.IssueRepository;
import com.ironhack.ironLibrary.repository.StudentRepository;
import com.ironhack.ironLibrary.utils.InvalidBookInformationException;
import com.ironhack.ironLibrary.utils.NoBookFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MenuServiceImplTest {

    @Autowired
    private IBookService bookService;

    @Autowired
    private IAuthorService authorService;
    @Autowired
    private IIssueService issueService;

    @Autowired
    private IStudentService studentService;

    @Autowired
    private IMenuService menuService;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void testAddBookWithValidInformation() {
        List<String> validInformation = Arrays.asList(
                "978-84-415-4302-0",
                "The fenix project",
                "novel",
                "Gene Kim",
                "g.kim@gmail.com",
                "10"
        );
        assertDoesNotThrow(() -> menuService.addBook(validInformation));
    }

    @Test
    public void testAddBookWithInvalidInformation() {
        List<String> invalidInformation = Arrays.asList(
                "XXX-YYY-JJJ",
                "1 Book",
                "Fiction",
                "Superm@n",
                "my mail",
                "hey"
        );
        InvalidBookInformationException exception = assertThrows(InvalidBookInformationException.class,
                () -> menuService.addBook(invalidInformation));
        assertEquals("The provided information is invalid. Please check the format", exception.getMessage());
    }

    @Test
    public void testAddBookWithIncompleteInformation() {
        List<String> incompleteInformation = Arrays.asList(
                "978-84-415-4302-0",
                "The fenix project",
                "novel",
                "Gene Kim",
                "g.kim@gmail.com"
        );
        InvalidBookInformationException exception = assertThrows(InvalidBookInformationException.class,
                () -> menuService.addBook(incompleteInformation));
        assertEquals("The book and author information must be a six-element string list.", exception.getMessage());
    }

    @Test
    public void testAddBookWithExtraInformation() {
        List<String> incompleteInformation = Arrays.asList(
                "978-84-415-4302-0",
                "The fenix project",
                "novel",
                "Gene Kim",
                "g.kim@gmail.com",
                "10",
                "10"
        );
        InvalidBookInformationException exception = assertThrows(InvalidBookInformationException.class,
                () -> menuService.addBook(incompleteInformation));
        assertEquals("The book and author information must be a six-element string list.", exception.getMessage());
    }

    @Test
    public void testAddBookSaveAndFindByIsbn() {
        List<String> validInformation = Arrays.asList(
                "978-84-415-4302-0",
                "The fenix project",
                "novel",
                "Gene Kim",
                "g.kim@gmail.com",
                "10"
        );
        assertDoesNotThrow(() -> menuService.addBook(validInformation));
        Book savedBook = bookService.findByIsbn("978-84-415-4302-0").orElse(null);
        assertNotNull(savedBook);
        assertEquals("978-84-415-4302-0", savedBook.getIsbn());
        assertEquals("The fenix project", savedBook.getTitle());
        assertEquals("novel", savedBook.getCategory());
        assertEquals(10, savedBook.getQuantity());

        Author savedAuthor = authorService.findByAuthorBook(savedBook).get();
        assertNotNull(savedAuthor);
        assertEquals("Gene Kim", savedAuthor.getName());
        assertEquals("g.kim@gmail.com", savedAuthor.getEmail());
    }

    @Test
    void testSearchBookByAuthor() {
        List<String> validInformation = Arrays.asList(
                "978-84-415-4302-0",
                "The fenix project",
                "novel",
                "Gene Kim",
                "g.kim@gmail.com",
                "10"
        );
        assertDoesNotThrow(() -> menuService.addBook(validInformation));
        try{
           Book book = menuService.searchBookByAuthor("Gene Kim");
            assertEquals("The fenix project", book.getTitle());
            assertEquals("978-84-415-4302-0", book.getIsbn());
        }catch (NoBookFoundException e){
            fail("An NoBookFoundException exception was thrown when it was not expected.");
        }
    }

    @Test
    void testGetBookByAuthorGoesWrong(){
        String authorName = "Paco Wall";
        NoBookFoundException exception = assertThrows(NoBookFoundException.class, () -> menuService.searchBookByAuthor(authorName));
        assertEquals("No book found for author: " + authorName, exception.getMessage());
    }

    @Test
    void testGetBookByAuthorGoesWrongWhenAuthorNameIsAnEmptyString(){
        String authorName = "";
        InvalidBookInformationException exception = assertThrows(InvalidBookInformationException.class, () -> menuService.searchBookByAuthor(authorName));
        assertEquals("The provided information is invalid. Please check the format", exception.getMessage());
    }

    private static List<List<String>> getListOfBooksToSave() {
        List<String> book1 = Arrays.asList(
                "978-84-415-4302-0",
                "The fenix project",
                "novel",
                "Gene Kim",
                "g.kim@gmail.com",
                "10"
        );
        List<String> book2 = Arrays.asList(
                "978-84-415-4302-1",
                "The unicorn project",
                "novel",
                "Gene Kim",
                "g.kim@gmail.com",
                "10"
        );
        List<List<String>> listOfBooksToSave = new ArrayList<>();
        listOfBooksToSave.add(book1);
        listOfBooksToSave.add(book2);
        return listOfBooksToSave;
    }

    @Test
    void testSearchBookByCategory() {
        List<List<String>> listOfBooksToSave = getListOfBooksToSave();
        for(List<String> book: listOfBooksToSave){
            menuService.addBook(book);
        }
        try{
            List<Book> listOfBooks = menuService.searchBookByCategory("novel");
            assertEquals(2, listOfBooks.size());
            assertEquals("The fenix project", listOfBooks.get(0).getTitle());
        }catch(NoBookFoundException e){
            fail("An NoBookFoundException exception was thrown when it was not expected.");
        }
    }

    @Test
    void testSearchBookByCategoryNotFoundAnyBook(){
        String Category = "comedy";
        NoBookFoundException exception = assertThrows(NoBookFoundException.class, () -> menuService.searchBookByCategory(Category));
        assertEquals("No books found for this category: " + Category, exception.getMessage());
    }

    @Test
    void testSearchBookByCategoryGoesWrong(){
        String Category = "";
        InvalidBookInformationException exception = assertThrows(InvalidBookInformationException.class, () -> menuService.searchBookByCategory(Category));
        assertEquals("The provided information is invalid. Please check the format", exception.getMessage());
    }

    @Test
    void testIssueBookToStudentGoWrongWithAIsbnWrong(){
        List<String> issueData = Arrays.asList(
                "09003688801",
                "Pedro",
                "978-84-415-5302-1"
        );
        studentService.save(issueData.get(0), issueData.get(1));
        NoBookFoundException exception = assertThrows(NoBookFoundException.class,() -> menuService.issueBookToStudent(issueData));
        assertEquals("No books are found with that ISBN", exception.getMessage());
    }

    @Test
    void testIssueBookToStudentGoWrongWithAUsnWrong(){
        List<String> issueData = Arrays.asList(
                "3",
                "Pedro",
                "978-84-415-5302-1"
        );
        studentService.save(issueData.get(0), issueData.get(1));
        InvalidBookInformationException exception = assertThrows(InvalidBookInformationException.class,() -> menuService.issueBookToStudent(issueData));
        assertEquals("The provided information is invalid. Please check the format", exception.getMessage());
    }

    @Test
    void testIssueBookToStudentGoWrongWithUnavailableQuantity(){
        List<String> issueData = Arrays.asList(
                "09003688802",
                "Pedro",
                "978-84-415-4302-1"
        );
        List<String> bookData = Arrays.asList(
                "978-84-415-4302-1",
                "The fenix project",
                "novel",
                "Gene Kim",
                "g.kim@gmail.com",
                "0"
        );
        menuService.addBook(bookData);
        studentService.save(issueData.get(0), issueData.get(1));
        NoBookFoundException exception = assertThrows(NoBookFoundException.class,() -> menuService.issueBookToStudent(issueData));
        assertEquals("Quantity unavailable", exception.getMessage());
    }

    @Test
    void testIssueBookToStudent() throws NoBookFoundException {
        List<String> issueData = Arrays.asList(
                "09003688800",
                "Pedro",
                "978-84-415-4302-1"
        );
        List<String> bookData = Arrays.asList(
                "978-84-415-4302-1",
                "The fenix project",
                "novel",
                "Gene Kim",
                "g.kim@gmail.com",
                "10"
        );
        menuService.addBook(bookData);
        String usnStudent = issueData.get(0);
        String name = issueData.get(1);

        studentService.save(usnStudent,name);
        assertDoesNotThrow(() -> menuService.issueBookToStudent(issueData));
        Optional<List<Book>> optionalBookList = issueService.findAllBooksIssuedByUsn(usnStudent);
        if(optionalBookList.isPresent()){
            List<Book> bookList = optionalBookList.get();
            assertEquals(1, bookList.size());
            assertEquals(9, bookList.get(0).getQuantity());
        }
    }

    @Test
    void searchBooksAlongAuthors() {
        List<String> bookData = Arrays.asList(
                "978-84-415-4302-1",
                "The fenix project",
                "novel",
                "Gene Kim",
                "g.kim@gmail.com",
                "10"
        );
        List<String> bookData1 = Arrays.asList(
                "978-84-415-4303-1",
                "Harry potter y la piedra filosofal",
                "comedy",
                "JK ROWLING",
                "jk.rowling@gmail.com",
                "2"
        );
        menuService.addBook(bookData);
        menuService.addBook(bookData1);
        try{
            List<Object[]> listOffBooksAlongWithAuthor = menuService.searchBooksAlongAuthors();
            assertEquals(2, listOffBooksAlongWithAuthor.size());
            Object[] bookAlongWithAuthor = listOffBooksAlongWithAuthor.get(0);
            Author author = (Author) bookAlongWithAuthor[1];
            Book book = (Book) bookAlongWithAuthor[0];
            assertEquals("novel", book.getCategory());
            assertEquals("Gene Kim", author.getName());
            Object[] bookAlongWithAuthor2 = listOffBooksAlongWithAuthor.get(1);
            Author author1 = (Author) bookAlongWithAuthor2[1];
            Book book1 = (Book) bookAlongWithAuthor2[0];
            assertEquals("comedy", book1.getCategory());
            assertEquals("JK ROWLING", author1.getName());

        }catch(NoBookFoundException e){
            fail("An NoBookFoundException exception was thrown when it was not expected.");
        }
    }

    @Test
    void testSearchBooksByUsnProperFunction() throws Exception {
        List<String> bookData = Arrays.asList(
                "978-84-415-4302-1",
                "The fenix project",
                "novel",
                "Gene Kim",
                "g.kim@gmail.com",
                "10"
        );
        List<String> issueData = Arrays.asList(
            "09003688800",
            "Pedro",
            "978-84-415-4302-1"
        );
        menuService.addBook(bookData);
        String usnStudent = issueData.get(0);
        String name = issueData.get(1);
        studentService.save(usnStudent,name);

        try {
            menuService.issueBookToStudent(issueData);
            List<Object[]> objectList = menuService.searchBooksByUsn(usnStudent);
            assertEquals(1, objectList.size());

            Book book = (Book) objectList.get(0)[0];
            Issue issue = (Issue) objectList.get(0)[1];
            assertEquals("The fenix project", book.getTitle());
            assertEquals("Pedro", issue.getIssueStudent().getName());
        } catch (Exception e){
            fail();
        }
    }

    @Test
    void testSearchBooksByUsnWrongUsn() throws Exception {
        List<String> bookData = Arrays.asList(
                "978-84-415-4302-1",
                "The fenix project",
                "novel",
                "Gene Kim",
                "g.kim@gmail.com",
                "10"
        );
        List<String> issueData = Arrays.asList(
                "09003688800",
                "Pedro",
                "978-84-415-4302-1"
        );
        menuService.addBook(bookData);
        String usnStudent = issueData.get(0);
        String name = issueData.get(1);
        studentService.save(usnStudent,name);
        try {
            menuService.issueBookToStudent(issueData);
            assertThrows(Exception.class, () -> {
                menuService.searchBooksByUsn("00001");
            });
        } catch (Exception e){
            fail();
        }
    }

    @AfterEach
    void tearDown() {
        issueRepository.deleteAll();
        authorRepository.deleteAll();
        bookRepository.deleteAll();
        studentRepository.deleteAll();
    }
}