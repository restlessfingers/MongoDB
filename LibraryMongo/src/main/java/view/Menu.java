package view;

import com.mongodb.client.MongoDatabase;
import controller.ControllerBook;
import controller.ControllerClient;
import utils.UtilsIO;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Menu {

	public Menu() {
	}

	public void loopIO() {

		
		Scanner reader = new Scanner(System.in);
		//create connection and DATABASE
		MongoDatabase database = ControllerBook.init();

		//just sleep for 2 seconds to print on console all connection log from MONGO
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		//print Main Menu and options
		PrintMenu printMenu = new PrintMenu();

		//main loop to IO data  with user
		while (true) {

			printMenu.showMenu();
			String command = UtilsIO.ask(reader, "Option?");
			
			//work with ENUM to choose options, and STRING<>ENUM 
			MenuOptions commandEnum = MenuOptions.commandisValid(command);

			//close database in case of quit
			if (commandEnum.equals(MenuOptions.QUIT)) {
				ControllerBook.close(database);
				break;

			} else if (commandEnum.equals(MenuOptions.UNKNOWN)) {
				System.out.println("Unknown command!");

			} else if (commandEnum.equals(MenuOptions.ADD)) {
				ControllerBook.addAuthor(reader);
				
			} else if (commandEnum.equals(MenuOptions.ADDBOOK)) {
				ControllerBook.addBookToAuthor(reader);
				
			} else if (commandEnum.equals(MenuOptions.SHOW)) {
				ControllerBook.showAll();

			} else if (commandEnum.equals(MenuOptions.DELETE)) {
				ControllerBook.delete(reader);

			} else if (commandEnum.equals(MenuOptions.FIND)) {
				ControllerBook.findOne(reader);

			} else if (commandEnum.equals(MenuOptions.UPDATE)) {
				ControllerBook.update(reader);
			}
			else if (commandEnum.equals(MenuOptions.ADDCLIENT)) {
				ControllerClient.addClient(reader);
			}
			else if (commandEnum.equals(MenuOptions.RENTBOOK)) {
				ControllerClient.update(reader);
			}

		}

	}

}