package com.ironhack.ironLibrary.service;

import com.ironhack.ironLibrary.model.Author;
import com.ironhack.ironLibrary.model.Book;
import com.ironhack.ironLibrary.utils.InvalidBookInformationException;
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
}


