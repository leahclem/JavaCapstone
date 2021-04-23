package s21_Meerkat_Project;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CustomerMenu extends MainMenu {

	private User curUser;
	private Customer curCust;

	CustomerMenu() {
	}

	CustomerMenu(User u) {
		this.curUser = u;
		this.curCust = (Customer) u;
	}

	public int menu() {
		int value = 0;
		Scanner scan = new Scanner(System.in);
		System.out.println(" Welcome to Puppy Heaven! Customer user " + curUser.getUserName());
		System.out.println("1. View puppies: ");
		System.out.println("2. Place bid on Puppy: ");//up_g update db puppy data with winner curbid
		System.out.println("3. Display active auctions: ");
		System.out.println("4. Display all my winning auctions:");
		System.out.println("5. Pay for puppy:");//up_l update db for pup paid bool
		System.out.println("6. Logout: ");
		System.out.println("7. Exit: ");
		System.out.print("Choice: ");
		try {
			value = scan.nextInt();
		} catch (InputMismatchException e) {
			System.out.print("Input Error. ");
			scan.nextLine();
		}
		return value;
	}

	public User menuChoice(int choice, AuctionHouse ah, InputOutputMethods io) {
		User loggedIn = this.curUser;
		// apple
		if (choice == 1) {
			printPups(ah.getAllPups());// print or search a puppy in the system
		} else if (choice == 2) {
			newBid(ah.getAllBids(), curUser);// bid on a active auction
		} else if (choice == 3) {
			ah.activeBids();// displays all bids currently active
		} else if (choice == 4) {
			checkWinning(ah);// display all winning auctions for the current user
		} else if (choice == 5) {
			payment(ah, curUser);
		} else if (choice == 6) {// logs out current user
			loggedIn = null;
			System.out.println("Bye Customer " + curUser.getUserName());
		} else if (choice == 7) {// exits the program
			io.outputData(ah.getAllPups(), ah.getAllUsers(), ah.getAllBids());
			System.out.println("Bye!!!!!");
			System.exit(0);
			;
		} else {
			System.out.println("I don't understand, please enter a number from 1-7? ");
		}

		return loggedIn;
	}

	public static void newBid(ArrayList<Bids> curBid, User customer) {
		boolean validNum = false;
		double maxBid = 0;
		Scanner scan = new Scanner(System.in);
		String name = "";
		Bids pupBid = null;
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		System.out.println("What is the name of the puppy you wish to find? ");

		name = scan.nextLine();

		int counter = 0;
		if (curBid != null) {
			for (int i = 0; i < curBid.size(); i++) {
				if (curBid.get(i).getPup().getName().equalsIgnoreCase(name) && curBid.get(i).isActive()) {
					pupBid = curBid.get(i);

					System.out.println(curBid.get(i).toString());

					counter++;
				}
			}
			if (counter == 0) {
				System.out.println("That puppy is not available. ");
			} else {
				System.out.println("Would you like to bid on " + name + "? (yes/no)");
				String placeBid = scan.nextLine();

				if (placeBid.equalsIgnoreCase("yes")) {
					// checkBid(name) and auction end date
					System.out.println("Puppy name: " + name + "\nTo win you must bid at least: "
							+ nf.format((pupBid.getCurrentBid() + pupBid.getIncrement()))
							+ "\nHow much do you wish to bid?");
					while (!validNum) {
						try {
							maxBid = scan.nextDouble(); // remove after issue is resolved and add try catch
							validNum = true;
						} catch (InputMismatchException e) {
							System.out.println("Please enter a valid number. ");
							scan.nextLine();
						}
					}
					
					// check if time is between nine to five pm
					if (LocalDateTime.now().getHour() >= 9 && LocalDateTime.now().getHour() <= 16) {
						pupBid.checkBid(customer, maxBid);
					} else {// if not between 9-5pm
						pupBid.storeBid(customer, maxBid);
						System.out.println("Data backlogged");
					}

				} // end if they place a bid

			} // end else if puppy is found

		} // ends if(curBid!= null)
		else
			System.out.println("The bid does not exist, ask admin to add one.");
	}

	public void checkWinning(AuctionHouse ah) {
		// Variable Declaration
		boolean winCheck = false;// stays false if the user is not winning in any auctions

		if (ah.getAllBids().size() != 0) {// if there are any active bids
			for (Bids b : ah.getAllBids()) {// for all the bids
				if (b.getWinner().getUserName().equalsIgnoreCase(curUser.getUserName())) {// if the winner has the same
																							// name
					System.out.println(b.toString());
					winCheck = true;// the user is winning in an auction
				} // end of if
			} // end of for

			if (winCheck = false) {
				System.out.println(
						"You are not currently winning in any auctions, display active auctions to see who is winning.");
			}
		} else {// if there are no auctions right now
			System.out.println("There are no auctions, have an admin set one up.");
		}
	}// end of method checkWinning

	public void payment(AuctionHouse ah, User curUser) {// FIXME
		Scanner scan = new Scanner(System.in);
		Bids buyPup = null;
		String pup;
		String confirm;
		int counter = 0;
		int counter2 = 0;
		System.out.println("The puppies you need to pay for are: ");
		for (Bids b : ah.getAllBids()) {
		//prints out puppies that are won and not yet paid for by this user
			if (b.isActive() == false && b.isPaidFor() == false 
					&& b.getWinner().getUserName().equalsIgnoreCase(curUser.getUserName())) {
				System.out.println(b.getPup());
				counter++;
			}
		}//end for
		if (counter != 0) {
			System.out.println("Which puppy would you like to pay for? ");
			pup = scan.nextLine();
			for (Bids b : ah.getAllBids()) {
				if (b.getPup().getName().equalsIgnoreCase(pup) && b.isPaidFor() == false) {
					buyPup = b;
					counter2++;
				}
			}//end for
			if (counter2 == 0) {
				System.out.println("Could not find a puppy with that name.");
				return;
			}

			System.out.println("Please confirm your payment method using " + this.curCust.getPayPal());
			System.out.println("Do we have your permission to bill this account? (yes/no)");
			confirm = scan.nextLine();
			if (confirm.equalsIgnoreCase("yes")) {
				buyPup.setPaidFor(true);
				System.out.println("Payment confirmed. Thank you for your business!");
			} else {
				System.out.println("If the puppy is not paid for soon it will be put back on the market.");
			}
		} else 
			System.out.println("You have no puppies to pay for.");
	}

}
