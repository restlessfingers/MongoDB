package model;

import java.util.List;

import org.bson.Document;

import dao.ClientDAO;

public class Client extends Person{
		public boolean isBlocked() {
		return isBlocked;
	}

	public void setBlocked(boolean isBlocked) {
		this.isBlocked = isBlocked;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

		boolean isBlocked;
		//los siguientes int deben ir a  book
		/*private int dayBookOut;
		private int dayBookIn;
		
		public int getDayBookOut() {
			return dayBookOut;
		}

		public void setDayBookOut(int dayBookOut) {
			this.dayBookOut = dayBookOut;
		}

		public void setBooks(List<Book> books) {
			this.books = books;
		}

		public int getDayBookIn() {
			return dayBookIn;
		}

		public void setDayBookIn(int dayBookIn) {
			this.dayBookIn = dayBookIn;
		}*/

		private List<Book> books;

		public Client(String name, String surname, int age, List<Book> books) {
			super(name, surname, age);
			this.books = books;
			//this.dayBookOut = 0;
			//this.dayBookIn = 0;
			
		}

		public Client(String name, String surname, int age) {
			super(name, surname, age);
		}

		public List<Book> getBooks() {
			return books;
		}

		public void setBook(List<Book> books) {
			this.books = books;
		}
		
		public static boolean isBlocked(Book book /*Document clientFound*/) {
			//dayBookOut.compareTo(dayBookIn)
			if ((book.getDayBookIn()).compareTo(book.getDayBookOut()) < 100) {
				return false;
				//set isBlocked no
			} 
		return true;//set isBlocked yes
		}

}
