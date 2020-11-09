package model;

import java.util.List;

public class Client extends Person{
		char state;
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

}
