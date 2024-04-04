package com.ironhack.ironLibrary.service;

import com.ironhack.ironLibrary.model.Author;
import com.ironhack.ironLibrary.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.sql.SQLOutput;
import java.util.Optional;
import java.util.Scanner;
import com.ironhack.ironLibrary.utils.Validator;

@Service
public class MenuServiceImpl implements IMenuService{

    private final IAuthorService authorService;
    private final IBookService bookService;

    @Autowired
    public MenuServiceImpl(IAuthorService authorService, IBookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    public void  addBook() {
        String isbn = userInput("Enter isbn: ",true, "checkISBNFormat",
                "The ISBN must follow the next format: 978-92-95055-02-5");
        String title = userInput("Enter title: ", false, "NULL", "NULL");
        String category = userInput("Enter category: ", true, "validateStringGeneralFormat",
                "The category only accepts letters");
        String authorName = userInput("Enter author name: ", true, "validateStringGeneralFormat",
                "The author name only accepts letters");
        String email = userInput("Enter author email: ", true, "checkEmailFormat",
                "The email must contains an @ and a . ");
        int quantity = Integer.parseInt(userInput("Enter number of books: ", true, "validateInteger",
                "The quantity only accepts numbers"));

        Book neeBook = new Book(isbn, title, category, quantity);
        Author author = new Author();
        author.setName(authorName);
        author.setEmail(email);
        author.setAuthorBook(neeBook);

        Optional<Book> optionalBook = bookService.findByIsbn(isbn);

        if(optionalBook.isPresent()){
            Book book = optionalBook.get();
            book.setCategory(category);
            book.setQuantity(quantity);
            book.setTitle(title);
            bookService.save(book);
        }else{
            authorService.save(author);
            bookService.save(neeBook);
        }

    }

    String userInput(String printText, boolean validTheNextInput, String methodName, String suggestedInputFormat){
        String userInputText;
        boolean result = false;
        do{
            System.out.print(printText);
            Scanner scanner = new Scanner(System.in);
            userInputText = scanner.nextLine();
            if(validTheNextInput){
                try{
                    switch(methodName){
                        case "checkISBNFormat":
                            result = Validator.checkISBNFormat(userInputText);
                            break;
                        case "validateStringGeneralFormat":
                            result = Validator.validateStringGeneralFormat(userInputText);
                            break;
                        case "checkEmailFormat":
                            result = Validator.checkEmailFormat(userInputText);
                            break;
                        case "validateInteger":
                            result = Validator.validateInteger(userInputText);
                            break;
                        case "notBlankValidatorBooks":
                            result = Validator.notBlankValidatorBooks(userInputText);
                            break;
                    }
                    if(!result){
                        System.out.println("Sorry, but the input format it's incorrect. Please try again: ");
                        System.out.println(suggestedInputFormat);
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                result = true;
            }
        }while(!result);
        return userInputText;
    };
}
