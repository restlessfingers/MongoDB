package model;

import java.util.Date;
import java.util.List;

import services.BookState;

public class Book {

	private String title;
	private int year;
	private int pages;
	private List<Client> clients;
	private Date dayBookOut;
	private Date dayBookIn;
	char state;
	
	
	public char getState(BookState bookState) {
		return state;
	}

	public void setState(char state) {
		this.state = state;
	}

	public Date getDayBookOut() {
		return dayBookOut;
	}

	public void setDayBookOut(Date dayBookOut) {
		this.dayBookOut = dayBookOut;
	}

	public Date getDayBookIn() {
		return dayBookIn;
	}

	public void setDayBookIn(Date dayBookIn) {
		this.dayBookIn = dayBookIn;
	}
	
	public Book(String title, int year, int pages) {
		super();
		this.title = title;
		this.year = year;
		this.pages = pages;
	}
	
	public Book(String title, int year, int pages, char letter) {
		super();
		this.title = title;
		this.year = year;
		this.pages = pages;
		this.state = letter;
	}

	public Book(String title, List<Client> clients) {
		super();
		this.title = title;
		this.clients = clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	@Override
	public String toString() {
		return "Book [title=" + title + ", year=" + year + ", pages=" + pages + "]";
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}
	public List<Client> getClients() {
		return clients;
	}

	public void setClient(List<Client> clients) {
		this.clients = clients;
	}
	//public Date dayBookOut = new Date(2000, 11, 21); 

}
