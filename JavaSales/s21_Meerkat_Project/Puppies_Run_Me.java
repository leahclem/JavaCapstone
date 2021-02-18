package s21_Meerkat_Project;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Puppies_Run_Me {

	public static void main(String[] args) {
		//Variable Declaration
		User loggedIn = null;
		MainMenu mm = new MainMenu();
		
		ArrayList<Puppies> pupList = new ArrayList<Puppies>();
		//ArrayList<Puppies> pupList = mm.loadData();
		ArrayList<User> users = new ArrayList<User>();
		mm.createDefaultAdmin(users);
		// create default puppy objects similar to above
		mm.createDefaultPuppy(pupList);
		AuctionHouse ah = new AuctionHouse(true,pupList, users); // set open to true
		// ArrayList<Puppies> pupList = loadData();
		// printPups(pupList);
		// ArrayList<User> users = new ArrayList<User>();
		// User loggedIn;
		// createDefaultAdmin(users);
		// loggedIn = loginInMenu(users);
		// System.out.println(loggedIn.getUserName());
		// //outputData(pupList);
		// System.exit(0);

		int choice = 0;
		while (choice >= 0) {
			if(loggedIn == null) {
				choice = mm.menu();//moved menu to main menu so I can overwrite it for each menu
				//moved the choices to the main menu to be overwritten by other menus
				loggedIn = mm.menuChoice(choice, ah.getAllPups(), ah.getAllUsers());//in main menu, if no one is signed in null is returned
			} else {
				//set up a new Menu
				MainMenu cm;
				
				//create custom menu depending on user type
				if(loggedIn.getUserType() == 'A') {//if the user is an admin
					cm = new AdminMenu(loggedIn);
				} else if(loggedIn.getUserType() == 'C'){//if the user is a customer
					cm = new CustomerMenu(loggedIn);
				} else {//else its not signed in right so use the main menu
					cm = new MainMenu();
				}
				//get the user choice
				choice = cm.menu();
				//do the user choice
				loggedIn = cm.menuChoice(choice, ah.getAllPups(), ah.getAllUsers());
				
			}
		} // end while

	}

}
