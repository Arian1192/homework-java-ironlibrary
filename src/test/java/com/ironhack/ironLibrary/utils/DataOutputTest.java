package com.ironhack.ironLibrary.utils;

import com.ironhack.ironLibrary.model.Author;
import com.ironhack.ironLibrary.model.Book;
import com.ironhack.ironLibrary.model.Issue;
import com.ironhack.ironLibrary.model.Student;
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciithemes.TA_GridThemes;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataOutputTest {

    private static Book book1;
    private static  Book book2;
    private static List<Book> books;

    private static Author author1;
    private static Author author2;

    private static  Student student1;
    private static  Student student2;

    private static  Issue issue1;
    private static  Issue issue2;


    @BeforeAll
    static void setUp(){
        book1 = new Book("978-92-95-055-02-1", "First title", "romance", 3);
        book2 = new Book("978-92-95-055-02-2", "Second title", "terror", 5);
        books = new ArrayList<>();
        books.add(book1);
        books.add(book2);

        author1 = new Author("Author first", "author@email.com", book1);
        author2 = new Author("Author second", "author2@email.com", book2);

        student1 = new Student(1, "First student");
        student2 = new Student(2, "Second student");

        issue1 = new Issue(student1, book1);
        issue2 = new Issue(student2, book2);

    }


    @Test
    @DisplayName("Validate oneBookTable output")
    void oneBookTableTest(){
        AsciiTable asciiTable = new AsciiTable();
        asciiTable.getContext().setGridTheme(TA_GridThemes.NONE);
        asciiTable.setTextAlignment(TextAlignment.LEFT);
        asciiTable.addRow("Book ISBN", "Book Title", "Category", "No of Books");
        asciiTable.addRow(book1.getIsbn(), book1.getTitle(), book1.getCategory(), book1.getQuantity());

        assertEquals(asciiTable.render(), DataOutput.oneBookTable(book1));
    }

    @Test
    @DisplayName("Validate listBookTable output")
    void listBookTableTest(){
        AsciiTable asciiTable = new AsciiTable();
        asciiTable.getContext().setGridTheme(TA_GridThemes.NONE);
        asciiTable.setTextAlignment(TextAlignment.LEFT);
        asciiTable.addRow("Book ISBN", "Book Title", "Category", "No of Books");
        for (Book book : books){
            asciiTable.addRow(book.getIsbn(), book.getTitle(), book.getCategory(), book.getQuantity());
        }
        assertEquals(asciiTable.render(), DataOutput.listBookTable(books));
    }

    @Test
    @DisplayName("Validate listBookTable output with empty list")
    void listBookTableEmptyListTest(){
        AsciiTable asciiTable = new AsciiTable();
        asciiTable.getContext().setGridTheme(TA_GridThemes.NONE);
        asciiTable.setTextAlignment(TextAlignment.LEFT);
        List<Book> noneBooks = new ArrayList<>();
        asciiTable.addRow("Book ISBN", "Book Title", "Category", "No of Books");
        assertEquals(asciiTable.render(), DataOutput.listBookTable(noneBooks));
    }

    @Test
    @DisplayName("Validate listBookTableWithAuthor output")
    void listBookTableWithAuthorTest(){

        List<Object[]> lstObjects= new ArrayList<>();
        lstObjects.add(new Object[]{book1, author1});
        lstObjects.add(new Object[]{book2, author2});

        AsciiTable asciiTable = new AsciiTable();
        asciiTable.getContext().setGridTheme(TA_GridThemes.NONE);
        asciiTable.setTextAlignment(TextAlignment.LEFT);
        asciiTable.addRow("Book ISBN", "Book Title", "Category", "No of Books",
                "Author name", "Author mail");
        for (Object[] obj : lstObjects){
            Book book = (Book) obj[0];
            Author author = (Author) obj[1];
            asciiTable.addRow(book.getIsbn(), book.getTitle(), book.getCategory(), book.getQuantity(),
                    author.getName(), author.getEmail());
        }
        assertEquals(asciiTable.render(), DataOutput.listBookTableWithAuthor(lstObjects));
    }

    @Test
    @DisplayName("Validate bookIssuedDate output")
    void bookIssuedDateTest(){
        assertEquals("Book issued. Return date : " + DateFormatter.largeDateFormat(issue1.getReturnDate()),
                DataOutput.bookIssuedDate(issue1));
    }

    //TODO: test Validate listBookTableByUsn


}
