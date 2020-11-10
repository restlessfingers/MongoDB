package com.example.demo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
@Repository
	public class BookRepository {
	@Autowired
	private JdbcTemplate template;
	
	public List<Book> findAll() {
	
	return template.query("select * from books", new
	
	BookMapper());
	}
	
	public void deleteBook (Book book) {
	template.update("delete from books where title=?",book.getTitle());
	}
	public void insertBook(Book book) {
	
	template.update("insert into Books (title, ISBN, pages)	values (?,?,?)",book.getTitle(),book.getISBN(),book.getPages());
	}
}