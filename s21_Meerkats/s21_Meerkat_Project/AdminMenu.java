package s21_Meerkat_Project;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Scanner;

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
		System.out.println("1. Search puppies: ");
		System.out.println("2. Logout: ");
		System.out.println("3. Add a new puppy for sale: ");
		System.out.println("4. Display active auctions: ");
		System.out.println("5. Create new auction: ");
		System.out.println("6. Create new Admin: ");
		System.out.println("7. View closed auctions: ");
		System.out.println("8. Exit: ");
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
			printPups(ah.getAllPups());
		} else if (choice == 2) {
			loggedIn = null;
			System.out.println("Bye Admin " + curUser.getUserName());
		} else if (choice == 3) {
			ah.getAllPups().add(addPup());
		} else if (choice == 4) {
			ah.activeBids();
		} else if (choice == 5) {
			createBid(ah);
		} else if (choice == 6) {
			createAdmin(ah.getAllUsers());
		} else if (choice == 7) {
			ah.closedBids();
		} else if (choice == 8) {
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
				counter=0;
			}
		} // end loop to determine if there is an existing Bid for the puppy

		if (pup == null) {
			System.out.println("We did not find a puppy with that name. ");
			// this should pull us out of the method if no puppy is found
		} else if (counter==1) {
			System.out.println("There is already an auction for this puppy. ");
		} else if (counter==0) {
			LocalDateTime endDate = validDate();
			ah.addBid(new Bids(pup, endDate));
		} else {
			System.out.println("Something isn't right. ");
		}
		
	}

	public void checkAuctionHist(){
		//stub checkpoint 3, it will display the history of a selected auction
	}
	
	public void loadbacklog() {
		//stub checkpoint 2, it will load backlog data, and add to bidhistory
	}
	
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
				if (hour <= 23 && hour >= 0) {
					validNum = true;
				} else {
					System.out.println("Please enter an hour between 0 and 23: ");
				}
			} catch (InputMismatchException e) {
				System.out.println("Please enter an hour between 0 and 23: ");
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
		// Ask for name and sign in information
		Scanner scan = new Scanner(System.in);
		String userName;
		String password;
		String fname;
		String lname;
		boolean base = false;

		System.out.print("Enter the first name of the new Admin: ");
		fname = scan.nextLine();
		while (fname.contains("|") || fname.equals("null")) {
			System.out.println("Name cannot include the word 'null' or the pipe symbol '|'");
			fname = scan.nextLine();
		}
		System.out.print("Enter the last name of the new Admin: ");//FIX ME: Do we need two name values?
		lname = scan.nextLine();
		while (lname.contains("|") || lname.equals("null")) {
			System.out.println("Name cannot include the word 'null' or the pipe symbol '|'");
			lname = scan.nextLine();
		}
		System.out.println("Enter the user name of the new Admin: ");
		userName = scan.nextLine();
		while (userName.contains("|") || userName.equals("null")) {
			System.out.println("Username cannot include the word 'null' or the pipe symbol '|'");
			userName = scan.nextLine();
		}
		System.out.println("Finally enter the password of the new Admin: ");
		password = scan.nextLine();
		while (password.contains("|") || password.equals("null")) {
			System.out.println("Password cannot include the word 'null' or the pipe symbol '|'");
			password = scan.nextLine();
		}

		users.add(new Admin(userName, password, fname, lname, base));

		System.out.println("Created user: " + userName);
	}// end of create admin
		

}
