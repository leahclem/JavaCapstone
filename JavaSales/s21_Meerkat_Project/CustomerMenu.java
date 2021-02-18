package s21_Meerkat_Project;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CustomerMenu extends MainMenu{

	private User curUser;
	
	CustomerMenu(){}
	
	CustomerMenu(User u){
		this.curUser = u;
	}

	public int menu() {
		int value = 0;
		Scanner scan = new Scanner(System.in);
		System.out.println(" Welcome to Puppy Heaven! Customer user " + curUser.getUserName());
		System.out.println("1. Search puppies: ");
		System.out.println("2. Logout: ");
		System.out.println("3. Load sample data: ");  /// display random 5 dogs, Will
		System.out.println("4. Process the backloged data: ");
		System.out.println("5. Exit: ");
		System.out.print("Choice: ");
		try {
			value = scan.nextInt();
		} catch (InputMismatchException e) {
			System.out.print("Input Error. ");
			scan.nextLine();
		}
		return value;
	}
	
	public User menuChoice(int choice, ArrayList<Puppies> pupList, ArrayList<User> users) {
		User loggedIn = this.curUser;
		
		if (choice == 1) {
			printPups(pupList);
		} else if (choice == 2) {
			loggedIn = null;
			System.out.println("Bye Customer " + curUser.getUserName());
		} else if (choice == 3) {
			;
		} else if (choice == 4) {
			;
		} else if (choice == 5) {
			System.out.println("Bye!!!!!");
			System.exit(0);
			;
		} else {
			System.out.println("I don't understand, please enter a number from 1-5? ");
		}
		
		return loggedIn;
	}
}
