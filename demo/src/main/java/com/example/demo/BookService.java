package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Book;

@Service
public class BookService {
@Autowired
BookRepository repository;

	public Iterable<Book> findAll() {
	return repository.findAll();
	}
	public void insertBook (Book book) {
	repository.insertBook(book);
	}
	public void deleteBook(Book book) {
	repository.deleteBook(book);
	}

}