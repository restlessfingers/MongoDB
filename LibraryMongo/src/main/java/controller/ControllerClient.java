package controller;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import dao.BookDAO;
import dao.ClientDAO;
//import model.Client;
import model.Book;
import model.Client;
import services.BookState;
import utils.UtilsIO;
import view.MenuOptions;

public class ControllerClient{

	public static ClientDAO clientDAO;
	public static Client client;
	public static Book book;
	
	//just init and connect to mongoDB with creation of clientDAO object
	//needed to carry database
	public static MongoDatabase init() {

		MongoClientURI connectionString = new MongoClientURI(
				"mongodb+srv://restlessfingers:nojodasmongodb@cluster0.flwhu.mongodb.net/test");
		MongoClient mongoClient = new MongoClient(connectionString);

		MongoDatabase database = mongoClient.getDatabase("city");

		clientDAO = ControllerClient.setSource(database);

		return database;

	}

	//clientDAO object set to carry database
	//set database source to clientDAO
	public static ClientDAO setSource(MongoDatabase database) {

		ClientDAO clientDAO = new ClientDAO();
		clientDAO.setDataSource(database);
		
		return clientDAO;

	}

	//add new client to mongoDB
	public static void addClient(Scanner reader) {

		String clientName = UtilsIO.askForName(reader);
		String clientSurname = UtilsIO.askForSurname(reader);
		int clientAge = UtilsIO.askAge(reader);

		List<Book> books = new ArrayList<Book>();
		
		//create new client JAVA object with string and integer parameters and
		//list books from ADDBOOK -see addBook method-
		Client newClient = new Client(clientName, clientSurname, clientAge, addBook(reader, books));

		//client JAVA object send to DAO in order to upload to mongoDB as a DOCUMENT, mongo OBJECT
		clientDAO.saveClient(newClient);
	}

	//add new books with a list to a existing client at mongoDB
	public static void addBookToClient(Scanner reader/*, boolean isBlocked*/){

		String clientName = UtilsIO.askForName(reader);
		String bookName = UtilsIO.askForName(reader);
		int dateBook = UtilsIO.askForDate(reader);
		
		//check if client is in the mongoDB
		Document clientFound = clientDAO.findOneDocument(clientName);
		
		boolean clientstate = client.isBlocked();
		char bookState = book.getState(null);
		
		//ClientDAO checkClient = clientDAO.update(clientName, bookName);
		
		//if client is in DB, then, create a list with
		//the books created from addBook -see ADDBOOK method-
		//boolean status = Client.isBlocked(clientFound);
		
		if ((clientFound != null)  && (clientstate = false) && (bookState = 'A') || (bookState = 'R')) {
			List<Book> books = new ArrayList<Book>();
			clientDAO.update(clientName, addBook(reader, books));
		} else
			System.out.println("file not found");

	}/**Date dayBookOut = new Date();
		Date dayBookIn = new Date();
		boolean isBlocked = false;
		
			Document bookMongo = new Document("_id", new ObjectId());
			bookMongo.append("title", book.getTitle()).append("days late", dayBookOut.compareTo(dayBookIn));
			booksMongo.add(bookMongo);
		*/

	//method used from ADDAUTHOR and ADDBOOKTOAUTHOR to get a list of new books
	//loop while the user needs to create new books in a list
	//and eventually return that list
	public static List<Book> addBook(Scanner reader, List<Book> books) {

		while (true) {

			String command = UtilsIO.ask(reader, "Add Book (type QUIT to exit, otherwise go ahead)?");
			MenuOptions commandEnum = MenuOptions.commandisValid(command);

			if (commandEnum.equals(MenuOptions.QUIT)) {
				break;
			}

			String bookTitle = UtilsIO.askForTitle(reader);
			int bookYear = UtilsIO.askForYear(reader);
			int bookPages = UtilsIO.askForPages(reader);

			books.add(new Book(bookTitle, bookYear, bookPages));
		}
		return books;
	}

	//delete a book, prior checking a existing client
	public static void delete(Scanner reader) {

		String clientName = UtilsIO.askForName(reader);
		DeleteResult deletedClient = clientDAO.delete(clientName);

		if (deletedClient != null)
			System.out.println("Delete succesful: " + deletedClient);
		else
			System.out.println("file not found");

	}

	//find an client and printing the data
	public static void findOne(Scanner reader) {

		String clientName = UtilsIO.askForName(reader);
		Document clientFound = clientDAO.findOneDocument(clientName);

		if (clientFound != null)
			System.out.println(clientFound.toJson());
		else
			System.out.println("file not found");
	}

	//update an client considering all the possible cases to update
	//if user leave a string out we do not update
	//if user puts negative number to age we do not update
	public static void update(Scanner reader) {

		String clientNameToFind = UtilsIO.askForName(reader);
		String clientName = UtilsIO.askForName(reader);
		String clientSurname = UtilsIO.askForSurname(reader);
		int clientAge = UtilsIO.askAge(reader);

		// name & surname & age
		if (!clientName.isEmpty() && !clientSurname.isEmpty() && clientAge > 0) {
			clientDAO.update(clientNameToFind, clientName, clientSurname, clientAge);
			// name & surname
		} else if (!clientName.isEmpty() && !clientSurname.isEmpty() && clientAge < 0) {
			clientDAO.update(clientNameToFind, clientName, clientSurname);
			// name
		} else if (!clientName.isEmpty() && clientSurname.isEmpty() && clientAge < 0) {
			clientDAO.update(clientNameToFind, clientName);
			// surname & age
		} else if (clientName.isEmpty() && !clientSurname.isEmpty() && clientAge > 0) {
			clientDAO.update(clientNameToFind, clientSurname, clientAge);
			// name & age
		} else if (!clientName.isEmpty() && clientSurname.isEmpty() && clientAge > 0) {
			clientDAO.update(clientNameToFind, clientName);
			clientDAO.update(clientNameToFind, clientAge);
			// age
		} else if (clientName.isEmpty() && clientSurname.isEmpty() && clientAge > 0) {
			clientDAO.update(clientNameToFind, clientAge);
		}

	}

	//getting all the clients and printing all them
	public static void showAll() {

		MongoCollection<Document> clientsCollection = clientDAO.showAll();

		for (Document clientIterator : clientsCollection.find()) {
			System.out.println(clientIterator.toJson());
		}
	}

	//close the database
	public static void close(MongoDatabase database) {
		// to-do close all connections
	}
}