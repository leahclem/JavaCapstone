package s21_Meerkat_Project;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminMenu extends MainMenu{
	
	private User curUser;
	
	AdminMenu(){}
	
	AdminMenu(User u){
		this.curUser = u;
	}
	
	public int menu() {
		int value = 0;
		Scanner scan = new Scanner(System.in);
		System.out.println(" Welcome to Puppy Heaven! Admin user " + curUser.getUserName());
		System.out.println("1. Search puppies: ");
		System.out.println("2. Logout: ");
		System.out.println("3. Add a new puppy for sale: ");  
		System.out.println("4. Display active bids: ");
		System.out.println("5. Process the backloged data: ");
		System.out.println("6. Exit: ");
		System.out.print("Choice: ");
		try {
			value = scan.nextInt();
		} catch (InputMismatchException e) {
			System.out.print("Input Error. ");
			scan.nextLine();
		}
		return value;
	}
	
	public User menuChoice(int choice, ArrayList<Puppies> pupList, ArrayList<User> users, AuctionHouse ah) {
		User loggedIn = this.curUser;
		
		if (choice == 1) {
			printPups(pupList);
		} else if (choice == 2) {
			loggedIn = null;
			System.out.println("Bye Admin " + curUser.getUserName());
		} else if (choice == 3) {
			pupList.add(addPup());
		} else if (choice == 4) {
			ah.activeBids();
		} else if (choice == 5) {
			;
		} else if (choice == 6) {
			System.out.println("Bye!!!!!");
			System.exit(0);
		}else {
			System.out.println("I don't understand, please enter a number from 1-5? ");
		}
		
		return loggedIn;
	}
	// Admin menu: 
		// List all ongoing auctions
		// Select an auction and check bidding history
		// list information about completed auctions
		// list summary of winning bids
		// add an activate a new auction 
		// return to main menu
	
}
