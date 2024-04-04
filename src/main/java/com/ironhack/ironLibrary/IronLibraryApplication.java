package com.ironhack.ironLibrary;

import com.ironhack.ironLibrary.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class IronLibraryApplication implements CommandLineRunner {

	@Autowired
	IMenuService menuService;
	public static void main(String[] args) {
		SpringApplication.run(IronLibraryApplication.class, args
		);
	}

	@Override
	public void run(String... args) throws Exception {
		List<String> bookAndAuthorInformation = menuService.getNewBookInformation();
		menuService.addBook(bookAndAuthorInformation);
	}

}




