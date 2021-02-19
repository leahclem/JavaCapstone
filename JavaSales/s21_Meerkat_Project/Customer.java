package s21_Meerkat_Project;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Customer extends User{
	private int creditC; // credit card info
	private ArrayList<Bids> hiBids; // customer's current high bid
	private String address;
	
	Customer(){
		
	}
	
	Customer(String userName, String password, String address){
		super(userName, password, 'C');
		this.address = address;
		this.hiBids = new ArrayList<Bids>();
	}
	
	public static void newBid(ArrayList<Puppies> pupList, double maxBid, User customer) {
		Scanner scan = new Scanner(System.in);
		String name = "";
		String pupName = "";
		double pupPrice = 0;
		boolean payEnt = false;
		System.out.println("What is the name of the puppy you wish to find? ");

		boolean validNum = false;
		while (!validNum) {
			try {
				name = scan.nextLine();
				validNum = true;
			} catch (InputMismatchException e) {
				System.out.println("Please enter a valid dog name. ");
				scan.nextLine();
			}

		}
		int counter = 0;
		for (int i = 0; i < pupList.size(); i++) {
			pupName = (String) pupList.get(i).getName();
			if (pupName.equalsIgnoreCase(name)) {
				System.out.println(pupList.get(i).toString());
				pupPrice = (double) pupList.get(i).getPrice(); //keeps using the price of last dog added 200.00
				counter++;
			}
			pupName = name;
		}
		if (counter == 0) {
			System.out.println("We do not have that puppy. ");
		}

		System.out.println("Would you like to bid on " + name + "?(yes/no)");
		String placeBid = scan.nextLine();
		if (placeBid.equalsIgnoreCase("yes")) {
			System.out.println("What method of payment do you wish to use, Credit or Paypal? ");
			String payment = scan.nextLine();

			if (payment.equalsIgnoreCase("credit")) {
				System.out.println("Enter your 16 digit card number: ");
				String cardNum = scan.nextLine();
				System.out.println("Enter the CVV (number on the back): ");
				String cvv = scan.nextLine();
				System.out.println("Payment method saved");
				payEnt = true;

			} else {
				System.out.println("Enter your paypal email address: ");
				String paypl = scan.nextLine();
				System.out.println("Enter your paypal password: ");
				String payplPass = scan.nextLine();
				System.out.println("Payment method saved");
				payEnt = true;
			}
			//checkBid(name) and auction end date
			System.out.println("Puppy name: " + name + "\nOpening price: " + pupPrice 
					+ "\nHow much do you wish to bid?");
			maxBid = scan.nextDouble();
			while (maxBid <= pupPrice) {
				System.out.println("You will need to bid more than " + pupName + "'s opening price of " + pupPrice);
				maxBid = scan.nextDouble();
			}//for now comparing to opening price but need to compare to current max??
		}
	}

	// make-bid method here: ensure ending date of auction is referenced, get max bid from customer, and display global current bid, check if
	// bid is sufficient to become current bid, then update current global bid.
			// winning bids are auctions you are currently winning
			// available bids are auctions you are bidding in, but not currently winning
			// global bid is current bid viewable to all 
	
	// checkWinBid method
	
	// updateBid method
	
	// view current auctions method
}
