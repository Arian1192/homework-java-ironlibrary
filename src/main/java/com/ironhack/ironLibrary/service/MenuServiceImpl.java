package com.ironhack.ironLibrary.service;

import com.ironhack.ironLibrary.model.Author;
import com.ironhack.ironLibrary.model.Book;
import org.springframework.beans.factory.annotation.Autowired;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.ironhack.ironLibrary.utils.Validator;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl  implements IMenuService{

    @Autowired
    private IAuthorService authorService;
    @Autowired
    private IBookService bookService;

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

    /**
     * TODO Testing
     * @param bookAndAuthorInformation
     */

    public void  addBook(List<String> bookAndAuthorInformation) {

        String isbn = bookAndAuthorInformation.get(0);
        String title = bookAndAuthorInformation.get(1);
        String category = bookAndAuthorInformation.get(2);
        String authorName = bookAndAuthorInformation.get(3);
        String email = bookAndAuthorInformation.get(4);
        int quantity = Integer.parseInt(bookAndAuthorInformation.get(5));

        Book newBook = new Book(isbn, title, category, quantity);
        Author author = new Author();
        author.setName(authorName);
        author.setEmail(email);
        author.setAuthorBook(newBook);

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
}
