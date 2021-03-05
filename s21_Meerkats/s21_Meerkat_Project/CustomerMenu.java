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
		System.out.println("3. Display active auctions: ");
		System.out.println("4. Display all my winning auctions:");
		System.out.println("5. Logout: ");
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

	public User menuChoice(int choice, AuctionHouse ah) {
		User loggedIn = this.curUser;
		// apple
		if (choice == 1) {
			printPups(ah.getAllPups());//print or search a puppy in the system
		} else if (choice == 2) {
			newBid(ah.getAllBids(), curUser);//bid on a active auction
		} else if (choice == 3) {
			ah.activeBids();//displays all bids currently active
		} else if(choice == 4) {
			checkWinning(ah);//display all winning auctions for the current user
		} else if (choice == 5) {//logs out current user
			loggedIn = null;
			System.out.println("Bye Customer " + curUser.getUserName());
		} else if (choice == 6) {//exits the program
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

			}
			if (counter == 0) {
				System.out.println("That puppy is not available. ");
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
					pupBid.checkBid(customer, maxBid);
				} // end if they place a bid

			} // end else if puppy is found

		} // ends if(curBid!= null)
		else
			System.out.println("The bid does not exist, ask admin to add one.");
	}
	
	
	public void checkWinning(AuctionHouse ah) {
		//Variable Declaration
		boolean winCheck = false;//stays false if the user is not winning in any auctions
		
		if (ah.getAllBids().size() != 0) {// if there are any active bids
			for (Bids b : ah.getAllBids()) {//for all the bids
				if (b.getWinner().getUserName().equalsIgnoreCase(curUser.getUserName()) ) {//if the winner has the same name
					System.out.println(b.getPup().toString() + "\n It ends on " + b.getEndBy().toString());
					winCheck = true;//the user is winning in an auction
				} // end of if
			} // end of for
			
			if(winCheck = false) {
				System.out.println("You are not currently winning in any auctions, display active auctions to see who is winning.");
			}
		} else {//if there are no auctions right now
			System.out.println("There are no auctions, have an admin set one up.");
		}
	}//end of method checkWinning
}
