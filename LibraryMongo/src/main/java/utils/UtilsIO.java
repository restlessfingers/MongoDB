package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import org.bson.Document;

import dao.AuthorDAO;
import dao.BookDAO;
import dao.ClientDAO;

public class UtilsIO {

	// static menu methods for IO
	public static String ask(Scanner reader, String option) {
		// Prompt
		System.out.println(option);

		// get the user answer
		option = reader.nextLine();

		return option;
	}

	public static int askAge(Scanner reader) {
		// Prompt
		System.out.println("Age?");

		while (true) {
			try {
				return Integer.parseInt(reader.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Sorry, you did not entered a number! Watch it! \nAge?");

			}
		}
	}

	public static String askForName(Scanner reader) {
		System.out.println("Name: ");
		return reader.nextLine();
	}

	public static String askForSurname(Scanner reader) {
		System.out.println("Surname: ");
		return reader.nextLine();
	}
	
	public static void printUpdateResult(Document updateResult, BookDAO bookDAO, String bookNameToFind) {
		if (updateResult != null) {
			System.out.println("Update succesful: " + updateResult);
			
			System.out.print("Updated to: ");
			Document bookFound = bookDAO.findOneDocument(bookNameToFind);

			if (bookFound != null)
				System.out.println(bookFound.toJson());
			else
				System.out.println("file not found: book found, saved and after that lost");

		} else
			System.out.println("file not found");
	}
	
	
	public static void printUpdateResult(Document updateResult, ClientDAO clientDAO, String clientNameToFind) {
		if (updateResult != null) {
			System.out.println("Update succesful: " + updateResult);
			
			System.out.print("Updated to: ");
			Document clientFound = clientDAO.findOneDocument(clientNameToFind);

			if (clientFound != null)
				System.out.println(clientFound.toJson());
			else
				System.out.println("file not found: author found, saved and after that lost");

		} else
			System.out.println("file not found");
	}
	
	public static void printUpdateResult(Document updateResult, AuthorDAO authorDAO, String authorNameToFind) {

		if (updateResult != null) {
			System.out.println("Update succesful: " + updateResult);
			
			System.out.print("Updated to: ");
			Document authorFound = authorDAO.findOneDocument(authorNameToFind);

			if (authorFound != null)
				System.out.println(authorFound.toJson());
			else
				System.out.println("file not found: author found, saved and after that lost");

		} else
			System.out.println("file not found");
	}

	public static String askForTitle(Scanner reader) {
		System.out.println("Title: ");
		return reader.nextLine();
	}

	public static int askForYear(Scanner reader) {
		// Prompt
		System.out.println("Year?");

		while (true) {
			try {
				return Integer.parseInt(reader.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Sorry, you did not entered a number! Watch it! \nYear?");

			}
		}
	}
	
	

	public static int askForPages(Scanner reader) {
		// Prompt
		System.out.println("Pages?");

		while (true) {
			try {
				return Integer.parseInt(reader.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Sorry, you did not entered a number! Watch it! \nPages?");

			}
		}
	}
	public static int askForDate(Scanner reader)  {
	    int n;
        ArrayList<String> al = new ArrayList<String>();
        //Scanner in = new Scanner(System.in);
        n = reader.nextInt();
        String da[] = new String[n];
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        Date date[] = new Date[n];
        reader.nextLine();
	        for (int i = 0; i < da.length; i++) {
	            da[i] = reader.nextLine();
	        }
	        for (int i = 0; i < da.length; i++) {
	
	            try {
	                date[i] = sdf.parse(da[i]);
	            } catch (ParseException e) {
	
	                e.printStackTrace();
	            }
        }
		return n;
	}
	/*public static void printBookState(Document updateResult, BookDAO bookDAO, String bookNameToFind, boolean state) {
	if (updateResult != null) {
		if (state = r) {
		System.out.println("Update succesful: " + updateResult);
		
		System.out.print("Updated to: ");
		Document bookFound = bookDAO.findOneDocument(bookNameToFind);

		if (bookFound != null)
			System.out.println(bookFound.toJson());
		else
			System.out.println("file not found: book found, saved and after that lost");
		}
	} else
		System.out.println("file not found");
	}*/
}