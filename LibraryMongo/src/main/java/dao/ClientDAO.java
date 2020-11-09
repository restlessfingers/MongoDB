package dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.MongoCollection;
//import model.Author;
import model.Book;
import model.Client;
import utils.UtilsIO;

import static com.mongodb.client.model.Filters.eq;


public class ClientDAO {

	private MongoDatabase database;
	private Date dayBookIn;
	private Date dayBookOut;
	private boolean isBlocked;

	public void setDataSource(MongoDatabase database) {
		this.database = database;
	}

	
	//first parse JAVA object to DOCUMENT object
	//client object and books objects within an array to DOCUMENT object
	//insert client DOCUMENT object Mongo to Mongo database
	public void saveClient(Client client) {

		MongoCollection<Document> clientsCollection = this.getClientsCollection();

		List<Document> booksMongo = new ArrayList<Document>();
		
		for (Book book : client.getBooks()) {
				Document bookMongo = new Document("_id", new ObjectId());
				bookMongo.append("title", book.getTitle()).append("year", book.getYear()).append("pages", book.getPages());
				booksMongo.add(bookMongo);
			}
		Document clientMongo = new Document("_id", new ObjectId());
		clientMongo.append("name", client.getName()).append("surname", client.getSurname()).append("books", booksMongo);

		 clientsCollection.insertOne(clientMongo);
	}

	// find one document in mongoDB by string name of client
	//works as if name client was an id ... not good but enough so far
	public Document findOneDocument(String nameToFind) {

		MongoCollection<Document> clientsCollection = this.getClientsCollection();

		Document clientFound = clientsCollection.find(eq("name", nameToFind)).first();

		return clientFound;
	}

	// delete one document in mongoDB by string name of client
	//works as if name client was an id ... not good but enough so far
	public DeleteResult delete(String clientName) {

		MongoCollection<Document> clientsCollection = this.getClientsCollection();
		DeleteResult deletedClient = clientsCollection.deleteOne(eq("name", clientName));
		
		return deletedClient;
	}

	//we need all the update methods, overload to complain controller
	//use findOneAndUpdate by client name
	//as we are at DAO we send printing task to UTILSIO
	//UPDATE name, surname, age
	public void update(String clientNameToFind, String clientName, String clientSurname, int clientAge) {

		MongoCollection<Document> clientsCollection = this.getClientsCollection();

		Document updateResult = clientsCollection.findOneAndUpdate(eq("name", clientNameToFind), new Document("$set",
				new Document("name", clientName).append("surname", clientSurname).append("age", clientAge)));

		UtilsIO.printUpdateResult(updateResult, this, clientNameToFind);
	}

	//as we are at DAO we send printing task to UTILSIO
	//UPDATE name
	public void update(String clientNameToFind, String clientName) {
		MongoCollection<Document> clientsCollection = this.getClientsCollection();

		Document updateResult = clientsCollection.findOneAndUpdate(eq("name", clientNameToFind),
				new Document("$set", new Document("name", clientName)));

		UtilsIO.printUpdateResult(updateResult, this, clientNameToFind);
	}

	//as we are at DAO we send printing task to UTILSIO
	//UPDATE name, surname
	public void update(String clientNameToFind, String clientName, String clientSurname) {
		MongoCollection<Document> clientsCollection = this.getClientsCollection();

		Document updateResult = clientsCollection.findOneAndUpdate(eq("name", clientNameToFind),
				new Document("$set", new Document("name", clientName).append("surname", clientSurname)));

		UtilsIO.printUpdateResult(updateResult, this, clientNameToFind);
	}

	//as we are at DAO we send printing task to UTILSIO
	//UPDATE surname, age
	public void update(String clientNameToFind, String clientSurname, int clientAge) {
		MongoCollection<Document> clientsCollection = this.getClientsCollection();

		Document updateResult = clientsCollection.findOneAndUpdate(eq("name", clientNameToFind),
				new Document("$set", new Document("surname", clientSurname).append("age", clientAge)));

		UtilsIO.printUpdateResult(updateResult, this, clientNameToFind);
	}

	//as we are at DAO we send printing task to UTILSIO
	//UPDATE age
	public void update(String clientNameToFind, int clientAge) {
		MongoCollection<Document> clientsCollection = this.getClientsCollection();

		Document updateResult = clientsCollection.findOneAndUpdate(eq("name", clientNameToFind),
				new Document("$set", new Document("age", clientAge)));

		UtilsIO.printUpdateResult(updateResult, this, clientNameToFind);

	}

	//as we are at DAO we send printing task to UTILSIO
	//UPDATE books
	public void update(String clientName, List<Book> booksToAdd) {
		MongoCollection<Document> clientsCollection = this.getClientsCollection();

		for (Book book : booksToAdd) {
			
			Document bookMongo = new Document("_id", new ObjectId());
			
			bookMongo.append("title", book.getTitle())
			.append("dayBookOut", book.getDayBookOut()).append("dayBookOut", book.getDayBookIn());
			
			Document updateResult = clientsCollection.findOneAndUpdate(eq("name", clientName),
					new Document("$push", new Document("books",bookMongo)));
			
			UtilsIO.printUpdateResult(updateResult, this, clientName);
		}
		
	}
	
	public void update(String clientName, List<Book> booksToAdd, boolean isBlocked) {
		MongoCollection<Document> clientsCollection = this.getClientsCollection();

		for (Book book : booksToAdd) {
			
			Document bookMongo = new Document("_id", new ObjectId());
			
			bookMongo.append("title", book.getTitle())
			.append("year", book.getYear()).append("pages", book.getPages());
			
			Document updateResult = clientsCollection.findOneAndUpdate(eq("name", clientName), 
					new Document("$push", new Document("books",bookMongo)));
			
			UtilsIO.printUpdateResult(updateResult, this, clientName);
		}
		
	}

	
	//get all clients from collections clients from database carried by clientDAO
	//in order to return to controller to print all clients
	public MongoCollection<Document> showAll() {

		MongoCollection<Document> clientsCollection = database.getCollection("clients");
		
		return clientsCollection;
	}

	//getter from database: collection clients
	public MongoCollection<Document> getClientsCollection() {

		MongoCollection<Document> clientsCollection = database.getCollection("clients");
		return clientsCollection;

	}


	public boolean isBlocked() {
		//dayBookOut.compareTo(dayBookIn)
		if ((this.dayBookOut).compareTo(dayBookIn) < 100) {
			return false;
		} 
	return true;
	}
	
}

/*cuando el cliente coja los libros verificar si esta bloqueado. calcular fecha actual - valor del metodo isblocked?

Book
crear los libros con el status F, free
cuando el cliente coja el libro status R, rented

Cliente
cuando el cliente tarde en devolver cambiar su estatus a blocked in clientDAO.

crear opcion reservar por usuario, cambiar status R, reserved

RETURING BOOK Is necesary?how create a method do put de name of client, name of book, and know that is rented for this client. Further use this register to decide if is Renting or returning book?*/