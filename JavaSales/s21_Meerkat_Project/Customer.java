package s21_Meerkat_Project;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Customer extends User {
	private String payPal;
	private ArrayList<Bids> hiBids; // customer's current high bid
	private String address;

	Customer() {

	}

	Customer(String userName, String password, String address) {
		super(userName, password, 'C');
		this.address = address;
		this.hiBids = new ArrayList<Bids>();
	}

	Customer(String userName, String password, String address, String payPal) {
		super(userName, password, 'C');
		this.address = address;
		this.payPal = payPal;
		this.hiBids = new ArrayList<Bids>();
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
}

// make-bid method here: ensure ending date of auction is referenced, get max
// bid from customer, and display global current bid, check if
// bid is sufficient to become current bid, then update current global bid.
// winning bids are auctions you are currently winning
// available bids are auctions you are bidding in, but not currently winning
// global bid is current bid viewable to all

// checkWinBid method

// updateBid method

// view current auctions method
