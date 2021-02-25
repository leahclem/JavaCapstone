package s21_Meerkat_Project;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CustomerMenu extends MainMenu {

	private User curUser;

	CustomerMenu() {
	}

	CustomerMenu(User u) {
		this.curUser = u;
	}

	public int menu() {
		int value = 0;
		Scanner scan = new Scanner(System.in);
		System.out.println(" Welcome to Puppy Heaven! Customer user " + curUser.getUserName());
		System.out.println("1. View puppies: ");
		System.out.println("2. Place bid on Puppy: ");
		System.out.println("3. Display active bids: ");
		System.out.println("4. Logout: ");
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

	public User menuChoice(int choice, AuctionHouse ah) {
		User loggedIn = this.curUser;

		if (choice == 1) {
			printPups(ah.getAllPups());
		} else if (choice == 2) {
			newBid(ah.getAllBids(), curUser);
		} else if (choice == 3) {
			ah.activeBids();
		} else if (choice == 4) {
			loggedIn = null;
			System.out.println("Bye Customer " + curUser.getUserName());
		} else if (choice == 5) {
			System.out.println("Bye!!!!!");
			System.exit(0);
			;
		} else {
			System.out.println("I don't understand, please enter a number from 1-5? ");
		}

		return loggedIn;
	}
	
	public static void newBid(ArrayList<Bids> curBid, User customer) {
		double maxBid = 0;
		Scanner scan = new Scanner(System.in);
		String name = "";
		double pupPrice = 0;
		boolean payEnt = false;
		Bids pupBid = null;
		System.out.println("What is the name of the puppy you wish to find? ");

		name = scan.nextLine();

		int counter = 0;
		if (curBid != null) {
			for (int i = 0; i < curBid.size(); i++) {
				if (curBid.get(i).getPup().getName().equalsIgnoreCase(name)) {
					pupBid = curBid.get(i);

					System.out.println(curBid.get(i).toString());

					counter++;
				}
				// pupName = name;
			}
			if (counter == 0) {
				System.out.println("We do not have that puppy. ");
			} else {
				System.out.println("Would you like to bid on " + name + "?(yes/no)");
				String placeBid = scan.nextLine();
				if (placeBid.equalsIgnoreCase("yes")) {

					// checkBid(name) and auction end date
					System.out.println("Puppy name: " + name + "\nCurrent Bid ammount: "
							+ (pupBid.getCurrentBid() + pupBid.getIncrement()) + "\nHow much do you wish to bid?");

					do {
						maxBid = scan.nextDouble();
						if (maxBid <= (pupBid.getCurrentBid() + pupBid.getIncrement()))
							System.out.println("You will need to bid more than " + name + "'s current bid of "
									+ (pupBid.getCurrentBid() + pupBid.getIncrement()));

					} while (maxBid <= (pupBid.getCurrentBid() + pupBid.getIncrement()));
					// for now comparing to opening price but need to compare to current max??

				}

			}
			pupBid.checkBid(customer, maxBid);
		} // ends if(curBid!= null)
		else
			System.out.println("The bid does not exist, ask admin to add one.");
	}


		
//if payEnt = true call newBid method from Customer?
		
//searchPup return puppy to bid on with newBid in Customer class
	
}
