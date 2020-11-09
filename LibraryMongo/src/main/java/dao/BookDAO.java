package dao;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;

import model.Book;
import model.Client;
import utils.UtilsIO;

public class BookDAO {

private MongoDatabase database;

public void setDataSource(MongoDatabase database) {
	this.database = database;
}

//first parse JAVA object to DOCUMENT object
//book object and books objects within an array to DOCUMENT object
//insert book DOCUMENT object Mongo to Mongo database
public void saveBook(Book book) {

	MongoCollection<Document> booksCollection = this.getBooksCollection();

	List<Document> clientsMongo = new ArrayList<Document>();

	for (Client client: book.getClients()) {

Document clientMongo = new Document("_id", new ObjectId());
clientMongo.append("name", client.getName()).append("state", book.getState());
clientsMongo.add(clientMongo);
}

Document bookMongo = new Document("_id", new ObjectId());
bookMongo.append("name", book.getTitle()).append("clients", clientsMongo);

	 booksCollection.insertOne(bookMongo);
}

// find one document in mongoDB by string name of book
//works as if name book was an id ... not good but enough so far
public Document findOneDocument(String nameToFind) {

	MongoCollection<Document> booksCollection = this.getBooksCollection();

	Document bookFound = booksCollection.find(eq("name", nameToFind)).first();

	return bookFound;
}

// delete one document in mongoDB by string name of book
//works as if name book was an id ... not good but enough so far
public DeleteResult delete(String bookName) {

	MongoCollection<Document> booksCollection = this.getBooksCollection();
	DeleteResult deletedBook = booksCollection.deleteOne(eq("name", bookName));
	
	return deletedBook;
}

//we need all the update methods, overload to complain controller
//use findOneAndUpdate by book name
//as we are at DAO we send printing task to UTILSIO
//UPDATE name, surname, age
public void update(String bookNameToFind, String title, int year, int pages) {

	MongoCollection<Document> booksCollection = this.getBooksCollection();

	Document updateResult = booksCollection.findOneAndUpdate(eq("name", bookNameToFind), new Document("$set",
		new Document("title", title).append("year", year).append("pages", pages)));

	UtilsIO.printUpdateResult(updateResult, this, bookNameToFind);
}

//as we are at DAO we send printing task to UTILSIO
//UPDATE name
public void update(String bookNameToFind, String bookName) {
	MongoCollection<Document> booksCollection = this.getBooksCollection();

	Document updateResult = booksCollection.findOneAndUpdate(eq("name", bookNameToFind),
		new Document("$set", new Document("name", bookName)));

	UtilsIO.printUpdateResult(updateResult, this, bookNameToFind);
}

//as we are at DAO we send printing task to UTILSIO
//UPDATE name, surname
public void update(String bookNameToFind, String bookName, String bookSurname) {
	MongoCollection<Document> booksCollection = this.getBooksCollection();

	Document updateResult = booksCollection.findOneAndUpdate(eq("name", bookNameToFind),
		new Document("$set", new Document("name", bookName).append("surname", bookSurname)));

	UtilsIO.printUpdateResult(updateResult, this, bookNameToFind);
}

//as we are at DAO we send printing task to UTILSIO
//UPDATE surname, age
public void update(String bookNameToFind, String bookSurname, int bookAge) {
	MongoCollection<Document> booksCollection = this.getBooksCollection();

	Document updateResult = booksCollection.findOneAndUpdate(eq("name", bookNameToFind),
		new Document("$set", new Document("surname", bookSurname).append("age", bookAge)));

	UtilsIO.printUpdateResult(updateResult, this, bookNameToFind);
}

//as we are at DAO we send printing task to UTILSIO
//UPDATE age
public void update(String bookNameToFind, int bookAge) {
	MongoCollection<Document> booksCollection = this.getBooksCollection();

	Document updateResult = booksCollection.findOneAndUpdate(eq("name", bookNameToFind),
		new Document("$set", new Document("age", bookAge)));

	UtilsIO.printUpdateResult(updateResult, this, bookNameToFind);

}

//as we are at DAO we send printing task to UTILSIO
//UPDATE books
public void update(String bookName, List<Book> booksToAdd) {
	MongoCollection<Document> booksCollection = this.getBooksCollection();

	for (Book book : booksToAdd) {
		
		Document bookMongo = new Document("_id", new ObjectId());
	
	bookMongo.append("title", book.getTitle())
	.append("year", book.getYear()).append("pages", book.getPages());
	
	Document updateResult = booksCollection.findOneAndUpdate(eq("name", bookName),
			new Document("$push", new Document("books",bookMongo)));
		
		UtilsIO.printUpdateResult(updateResult, this, bookName);
	}
	
}

//get all books from collections books from database carried by bookDAO
//in order to return to controller to print all books
public MongoCollection<Document> showAll() {

	MongoCollection<Document> booksCollection = database.getCollection("books");
	
	return booksCollection;
}

//getter from database: collection books
public MongoCollection<Document> getBooksCollection() {

	MongoCollection<Document> booksCollection = database.getCollection("books");
		return booksCollection;

	}

}
/*public void saveClient(Client client) {

		MongoCollection<Document> clientsCollection = this.getClientsCollection();

		List<Document> booksMongo = new ArrayList<Document>();
		
		for (Book book : client.getBooks()) {
				Document bookMongo = new Document("_id", new ObjectId());
				bookMongo.append("title", book.getTitle()).append("year", book.getYear()).append("pages", book.getPages());
				booksMongo.add(bookMongo);
			}
		Document clientMongo = new Document("_id", new ObjectId());
		clientMongo.append("name", client.getName()).append("surname", client.getSurname())
				.append("dayBookOut", client.getDayBookOut()).append("dayBookIn", client.getDayBookIn()).append("books", booksMongo);

		 clientsCollection.insertOne(clientMongo);
	}
*/


/**import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

class Rextester {
  public static void main(String[] args) {
    int n = 101;
    CarRentingTuple[] tuples = new CarRentingTuple[n];
    for (int i = 0; i < n; ++i) {
      tuples[i] = new CarRentingTuple(i, i+4);
    }
    solve(tuples);
  }
  
    public static void solve(CarRentingTuple[] tuples){

        Arrays.sort(tuples, Comparator.comparingInt(o -> o.start));

        // Figure out which tuples have colliding priorities
        for(int i = 0; i < tuples.length; i++) {
            for(int j = i + 1; j < tuples.length && tuples[j].start <= tuples[i].end; j++) {
                tuples[i].set.add(tuples[j]);
                tuples[j].set.add(tuples[i]);
            }
        }

        // Keep removing tuple with most colliding priorities until there are no more colliding priorities
        int removed = 0;
        while(hasCollision(tuples)) {
            CarRentingTuple worst = tuples[0];
            for(CarRentingTuple t : tuples)
                if(t.set.size() > worst.set.size())
                    worst = t;

            for(CarRentingTuple t : worst.set)
                t.set.remove(worst);
            worst.set.clear();
            worst.start = -1;
            removed++;
        }

        // Construct new array without removed tuples
        CarRentingTuple[] result = new CarRentingTuple[tuples.length - removed];
        for(int i = 0, j = 0; j < tuples.length; j++)
            if(tuples[j].start != -1)
                result[i++] = tuples[j];

        for (CarRentingTuple t : result) {
          System.out.print( t.toString() );
        }
        System.out.println();
        System.out.println( "result.length: " + result.length );
        // Note.writenl(result);
    }

    private static boolean hasCollision(CarRentingTuple[] tuple){
        for(CarRentingTuple t : tuple)
            if(t.set.size() > 0)
                return true;
        return false;
    }

}

class CarRentingTuple {
    HashSet<CarRentingTuple> set = new HashSet<>();
    int start, end;
    CarRentingTuple(int a, int b) {
        start = Math.min(a, b);
        end = Math.max(a, b);
    }
    public String toString(){
        return "(" + start + ", " + end + ")";
    }
}
}*/