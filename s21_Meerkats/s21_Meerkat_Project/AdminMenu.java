package s21_Meerkat_Project;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class AdminMenu extends MainMenu {

	private User curUser;

	AdminMenu() {
	}

	AdminMenu(User u) {
		this.curUser = u;
	}

	public int menu() {
		int value = 0;
		Scanner scan = new Scanner(System.in);
		System.out.println(" Welcome to Puppy Heaven! Admin user " + curUser.getUserName());
		System.out.println("1. Process backlogged data for Auctions (9-5pm): ");
		System.out.println("2. Search puppies: ");
		System.out.println("3. Logout: ");
		System.out.println("4. Add a new puppy for sale: ");
		System.out.println("5. Display active auctions: ");
		System.out.println("6. Create new auction: ");
		System.out.println("7. Create new Admin: ");
		System.out.println("8. View closed auctions: ");
		System.out.println("9. View bid history queue");
		System.out.println("10. Exit: ");
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

		if (choice == 1) {
			loadbacklog(ah);// process backlogged data
		} else if (choice == 2) {
			listPups(ah);
		} else if (choice == 3) {
			loggedIn = null;
			System.out.println("Bye Admin " + curUser.getUserName());
		} else if (choice == 4) {
			ah.getAllPups().add(addPup(ah));
		} else if (choice == 5) {
			ah.activeBids();
		} else if (choice == 6) {
			createBid(ah);
		} else if (choice == 7) {
			createAdmin(ah.getAllUsers());
		} else if (choice == 8) {
			ah.closedBids();
		} else if (choice == 9) {
			checkAuctionHist(ah);
		} else if (choice == 10) {
			io.outputData(ah.getAllPups(), ah.getAllUsers(), ah.getAllBids());
			System.out.println("Bye!!!!!");
			System.exit(0);
		} else {
			System.out.println("I don't understand, please enter a number from 1-6? ");
		}

		return loggedIn;
	}

	public void createBid(AuctionHouse ah) {
		Scanner scan = new Scanner(System.in);
		// query admin for which puppy to create a bid for, using name as the primary
		// key
		boolean noBids = true;
		int counter = 0;
		System.out.println("Which puppy are we auctioning? (provide Puppie's name) ");
		String name = scan.nextLine();
		Puppies pup = null;
		// for loop beginning to find pup by name
		for (int i = 0; i < ah.getAllPups().size(); i++) {
			if (ah.getAllPups().get(i).getName().equalsIgnoreCase(name)) {
				pup = ah.getAllPups().get(i);
			}

		} // end for loop for pup name search

		// for loop to determine if there is already a Bid object for the puppy
		for (int j = 0; j < ah.getAllBids().size(); j++) {
			try {
				if (ah.getAllBids().get(j).getPup().getName().equalsIgnoreCase(name)) {
					// if the name equals an existing Bid object, then return false
					counter += 1;
				}

			} catch (IndexOutOfBoundsException iob) {
				counter = 0;
			}
		} // end loop to determine if there is an existing Bid for the puppy

		if (pup == null) {
			System.out.println("We did not find a puppy with that name. ");
			// this should pull us out of the method if no puppy is found
		} else if (counter == 1) {
			System.out.println("There is already an auction for this puppy. ");
		} else if (counter == 0) {
			LocalDateTime endDate = validDate();
			ah.addBid(new Bids(pup, endDate));
		} else {
			System.out.println("Something isn't right. ");
		}

	}

	public void checkAuctionHist(AuctionHouse ah) {
		Scanner scan = new Scanner(System.in);
		// option 9 view bidHistory
		int choice = 0;
		System.out.println("Do you want to: \n1. View all auctions \n2. Search by puppy name \nChoice: ");

		boolean valid = false;
		while (!valid) {
			try {
				choice = scan.nextInt();
				if (choice == 1) {
					for (int i = 0; i < ah.getAllBids().size(); i++) {
						if (ah.getAllBids().get(i).getBidHistory().isEmpty()) {
							System.out.println(
									ah.getAllBids().get(i).getPup().getName() + " has no bidding history yet. ");
						} else {
							ah.getAllBids().get(i).getBidHistory().print();

						}

					}
					valid = true;
				} else if (choice == 2) {
					System.out.println("What is the puppy's name?");
					String name = scan.nextLine();
					for (int j = 0; j < ah.getAllBids().size(); j++) {
						if (name.equalsIgnoreCase(ah.getAllBids().get(j).getPup().getName())) {
							ah.getAllBids().get(j).getBidHistory().print();
						} else if (ah.getAllBids().get(j).getBidHistory().isEmpty()) {
							System.out.println(
									ah.getAllBids().get(j).getPup().getName() + " has no bidding history yet. ");

						} else {
							System.out.println("I don't see a puppy with that name. ");
						}

					}
					valid = true;
				} else {
					System.out.println("Please enter either 1 or 2.");
					scan.nextLine();
					valid = false;
				}
			} catch (InputMismatchException e) {
				System.out.println("Please enter either 1 or 2. ");
				scan.nextLine();
			}

		}
	}

	public void listPups(AuctionHouse ah) {
		for (int i = 0; i < ah.getAllPups().size(); i++) {
			System.out.println(ah.getAllPups().get(i).toString());
		}

	}

	public void loadbacklog(AuctionHouse ah) {
		// load backlog data, and add to bidhistory
		// Variable Declaration
		ArrayList<Bids> auctions = ah.getAllBids();
		StringTokenizer stz;
		String data;
		String custName;
		Bids curAuc;
		User cust = null;
		double maxbid;

		// make it so this only works from 9-5pm
		if (LocalDateTime.now().getHour() >= 9 && LocalDateTime.now().getHour() <= 16) {
			// loop through all bids
			for (int i = 0; i < auctions.size(); i++) {
				// set the current Auction
				curAuc = auctions.get(i);
				// check for backlog data
				if (!curAuc.getBacklogg().isEmpty()) {// if the backlogg is not empty
					// loop through all the backlogg data
					try {// this will eventually break, im counting on it
						do {// pop every piece of backlogged data and apply it
							data = curAuc.getBacklogg().dequeue();
							// divide the data between cust and bid
							// instantiate the stringtokenizer
							stz = new StringTokenizer(data, " ");
							custName = stz.nextToken();
							maxbid = Double.parseDouble(stz.nextToken());
							// get the customer
							for (int j = 0; j < ah.getAllUsers().size(); j++) {// loop through all users
								if (ah.getAllUsers().get(j).getUserName().equalsIgnoreCase(custName)) {// if the
																										// usernames are
																										// the same
									cust = ah.getAllUsers().get(j);
								}
							} // end of customer loop
								// apply the backlog to the Auction
							curAuc.checkBid(cust, maxbid);
						} while (!curAuc.getBacklogg().isEmpty());
						System.out.println("Backlog for " + curAuc.getPup().getName() + " has been updated.");
					} catch (NoSuchElementException nse) {
						System.out.println("no more backlogged data for this auction");
					} // end of try catch no such element

				} // end of if there is backlog data
				else {
					if (curAuc.isActive()) {
						System.out.println("No backlog for auction: " + curAuc.getPup().getName());
					}
				}
			} // end of for loop through all auctions
		} // end of if between 9am - 4:59pm
		else {
			System.out.println("Error, try loading the backlog between 9-5pm, when we are open.");
		}
	}// end of loadbacklog

	public LocalDateTime validDate() {
		Scanner scan = new Scanner(System.in);

		int month = 0;
		int day = 0;
		int year = 0;
		int hour = 0;
		int min = 0;
		boolean validNum = false;

		System.out.println("What is the ending month (1-12)? (1 - January, 2 - February, etc...) ");
		while (!validNum) {
			try {
				month = scan.nextInt();
				if (month <= 12 && month >= 1) {
					validNum = true;
				} else {
					System.out.println("Please month a number between 1-12: ");
				}
			} catch (InputMismatchException e) {
				System.out.println("Input a month between 1-12: ");
				scan.nextLine();
			}

		}

		validNum = false;
		scan.nextLine();
		System.out.println("What is the ending year? ");
		while (!validNum) {
			try {
				year = scan.nextInt();
				if (year <= 2022 && year >= 2021) {
					validNum = true;
				} else {
					System.out.println("Please enter 2021 or 2022: ");
				}
			} catch (InputMismatchException e) {
				System.out.println("Please enter 2021 or 2022: ");
				scan.nextLine();
			}

		}

		int validDay = getNumberOfDays(month, year);

		validNum = false;
		scan.nextLine();
		// clean up for different dates in months... eg feb =28, etc. // done WAM
		System.out.println("What is the ending day (1-" + validDay + ")? ");
		while (!validNum) {
			try {
				day = scan.nextInt();
				if (day <= validDay && day >= 1) {
					validNum = true;
				} else {
					System.out.println("Please day a number between 1-" + validDay + ": ");
				}
			} catch (InputMismatchException e) {
				System.out.println("Input a day between 1-" + validDay + ": ");
				scan.nextLine();
			}

		}

		scan.nextLine();
		validNum = false;
		System.out.println("What is the ending hour? ");
		while (!validNum) {
			try {
				hour = scan.nextInt();
				if (hour <= 16 && hour >= 9) {
					validNum = true;
				} else {
					System.out.println("Please enter an hour between 9 and 16: ");
				}
			} catch (InputMismatchException e) {
				System.out.println("Please enter an hour between 9 and 16: ");
				scan.nextLine();
			}
		}

		scan.nextLine();
		validNum = false;
		System.out.println("What is the ending minute? ");
		while (!validNum) {
			try {
				min = scan.nextInt();
				if (min <= 59 && min >= 0) {
					validNum = true;
				} else {
					System.out.println("Please enter a minute between 0 and 59: ");
				}
			} catch (InputMismatchException e) {
				System.out.println("Please enter a minute between 0 and 59: ");
				scan.nextLine();
			}
		}

		LocalDateTime date = LocalDateTime.of(year, month, day, hour, min, 0, 0);

		return date;
	}

	public int getNumberOfDays(int month, int year) {
		// Local variable for the number of days,
		// initialized to 31.
		int days = 31;

		// Determine the number of days in the month.

		if (month == 9 || month == 4 || month == 6 || month == 11)
			days = 30;
		else if (month == 2) {
			// Determine if the year is a leap year.
			if ((year % 100 == 0 && year % 400 == 0) || (year % 100 != 0 && year % 4 == 0))
				days = 29; // If so, February has 29 days.
			else
				days = 28; // If not, February has 28 days.
		}

		// Return the number of days.
		return days;
	}

	public void createAdmin(ArrayList<User> users) {
		MainMenu mm = new MainMenu();
		// Ask for name and sign in information
		Scanner scan = new Scanner(System.in);
		String userName;
		String password;
		String fname;
		String lname;
		boolean base = false;

		System.out.print("Enter the first name of the new Admin: ");
		fname = scan.nextLine();
		mm.checkValid(fname); 
		
		System.out.print("Enter the last name of the new Admin: ");
		lname = scan.nextLine();
		mm.checkValid(lname);
		
		System.out.println("Enter the user name of the new Admin: ");
		userName = scan.nextLine();
		mm.checkValid(userName);
		//loop to check for duplicate username and also validate new entry for the word "null" or pipes
		for (int i =0; i< users.size(); i++) {
			if (users.get(i).getUserName().equals(userName)) {
				System.out.println("That username is already taken, try a different one. ");
				userName = scan.nextLine();
				mm.checkValid(userName);
			}
		}
		
		System.out.println("Finally enter the password of the new Admin: ");
		password = scan.nextLine();
		mm.checkValid(password);

		users.add(new Admin(userName, password, fname, lname, base));
		
		System.out.println("Created user: " + userName);
	}// end of create admin
	
}
