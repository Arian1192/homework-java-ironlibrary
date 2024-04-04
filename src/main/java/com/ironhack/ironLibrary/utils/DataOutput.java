package com.ironhack.ironLibrary.utils;

import com.ironhack.ironLibrary.model.Author;
import com.ironhack.ironLibrary.model.Book;
import com.ironhack.ironLibrary.model.Issue;
import com.ironhack.ironLibrary.model.Student;
import de.vandermeer.asciithemes.TA_GridThemes;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import de.vandermeer.asciitable.AsciiTable;

import java.util.List;

public class DataOutput {

    private static AsciiTable createAsciiTable(){
        AsciiTable asciiTable = new AsciiTable();
        asciiTable.getContext().setGridTheme(TA_GridThemes.NONE);
        asciiTable.setTextAlignment(TextAlignment.LEFT);
        return asciiTable;
    }
    public static String oneBookTable(Book book){
        AsciiTable asciiTable = createAsciiTable();
        asciiTable.addRow("Book ISBN", "Book Title", "Category", "No of Books");
        asciiTable.addRow(book.getIsbn(), book.getTitle(), book.getCategory(), book.getQuantity());
        return asciiTable.render();
    }

    public static String listBookTable(List<Book> books){
        AsciiTable asciiTable = createAsciiTable();
        asciiTable.addRow("Book ISBN", "Book Title", "Category", "No of Books");
        for (Book book : books){
            asciiTable.addRow(book.getIsbn(), book.getTitle(), book.getCategory(), book.getQuantity());
        }
        return asciiTable.render();
    }

    public static String listBookTableWithAuthor(List<Object[]> lstObjects){
        AsciiTable asciiTable = createAsciiTable();
        asciiTable.addRow("Book ISBN", "Book Title", "Category", "No of Books",
                "Author name", "Author mail");
        for (Object[] obj : lstObjects){
            Book book = (Book) obj[0];
            Author author = (Author) obj[1];
            asciiTable.addRow(book.getIsbn(), book.getTitle(), book.getCategory(), book.getQuantity(),
                    author.getName(), author.getEmail());
        }
        return asciiTable.render();
    }

    public static String bookIssuedDate(Issue issue){
        return "Book issued. Return date : " + DateFormatter.largeDateFormat(issue.getReturnDate());
    }

    //TODO: check this method - check inputs?
    public static String listBookTableByUsn(List<Object[]> lstObjects){
        AsciiTable asciiTable = createAsciiTable();
        asciiTable.addRow("Book Title", "Student Name", "Return date");
        for (Object[] obj : lstObjects){
            Book book = (Book) obj[0];
            Student student = (Student) obj[1];
            Issue issue = (Issue) obj[2];
            asciiTable.addRow(book.getTitle(), student.getName(),
                    DateFormatter.simpleDateFormat(issue.getReturnDate()));
        }
        return asciiTable.render();
    }
}
