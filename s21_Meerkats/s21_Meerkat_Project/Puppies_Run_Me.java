package s21_Meerkat_Project;

import java.util.ArrayList;

/**
 * Java class containing the main method for the Meerkats project. Program is a
 * puppy auction application. Able to run both Customer commands and Admin
 * commands based on log in credentials. Tracks puppy inventory, customer
 * information, customer bids, bid history, payments from customer, and admin
 * information.
 * 
 * @author GabrielWorsly, LeahClemens, WillMcCoy
 *
 */

public class Puppies_Run_Me {

	/**
	 * Main method of Meerkats project generates menu based on User credentials (not
	 * logged in, Admin or Customer),
	 *
	 */

	public static void main(String[] args) {
		// Variable Declaration
		// $db opens database connection and loads and
		User loggedIn = null;
		// InputOutputMethods io = new InputOutputMethods();
		MainMenu mm = new MainMenu();
		ArrayList<Bids> bid = new ArrayList<Bids>();
		ArrayList<Puppies> pupList = new ArrayList<Puppies>();
		ArrayList<User> users = new ArrayList<User>();
		// io.loadData(pupList, users, bid);// load in saved data
		AuctionHouse ah = new AuctionHouse(true, bid, pupList, users); // set open to true
		InputOutputMethods.DbRead(ah);// load in db info

		int choice = 0;
		while (choice >= 0) {
			if (loggedIn == null) {
				choice = mm.menu();// moved menu to main menu so I can overwrite it for each menu
				// moved the choices to the main menu to be overwritten by other menus
				loggedIn = mm.menuChoice(choice, ah);// in main menu, if no one is signed in null is returned
			} else {
				// set up a new Menu
				MainMenu cm;

				// create custom menu depending on user type
				if (loggedIn.getUserType() == 'A') {// if the user is an admin
					cm = new AdminMenu(loggedIn);
				} else if (loggedIn.getUserType() == 'C') {// if the user is a customer
					cm = new CustomerMenu(loggedIn);
				} else {// else its not signed in right so use the main menu
					cm = new MainMenu();
				}
				// get the user choice
				choice = cm.menu();
				// do the user choice
				loggedIn = cm.menuChoice(choice, ah);// removed io

			}
			ah.checkTime(); // this checks if the bids are over
		} // end while

	}// end of main method

}// end of class Run_Me
