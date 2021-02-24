package s21_Meerkat_Project;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class AuctionHouse {
	private boolean open; // this should be update based on a time stamp, implement later when we have
							// business hours

	private ArrayList<Bids> allBids; // used for implementation of database

	private ArrayList<Puppies> allPups;

	private ArrayList<User> allUsers;

	public AuctionHouse() {

	}
	
	

	// for use until we have Bids
	public AuctionHouse(boolean open, ArrayList<Puppies> pup, ArrayList<User> user) {
		this.open = open;
		this.allPups = pup;
		this.allUsers = user;
	}
	
	public AuctionHouse(boolean open, ArrayList<Bids> allBids, ArrayList<Puppies> allPups, ArrayList<User> allUsers) {
		this.open = open;
		this.allBids = allBids;
		this.allPups = allPups;
		this.allUsers = allUsers;
	}

	public void checkTime() {// will check if any bids are over
		LocalDateTime curTime = LocalDateTime.now();// The current time and date
		if (this.allBids != null) {// if there are any bids active
			for (Bids b : this.allBids) {
				if (b.getEndBy().isBefore(curTime) && b.isActive() == true) {// Checks if the current time is
																						// before the end date and the
																						// bid is active
					// print and call something in bids to end this bid
					System.out.println("The bid for " + b.getPup().getName() + " is over the winner is "
							+ b.getWinner().getUserName());
					b.setActive(false);

				}

			} // end of for loop
		}
	}// end of checkTime

	public void activeBids() {// prints all active bids
		if (this.allBids != null) {// if there are any active bids
			for (Bids b : this.allBids) {
				if (b.isActive()) {
					System.out.println(b.getPup().toString() + "\n It ends on " + b.getEndBy().toString());// add a to
																											// string
																											// for the
																											// date
				} // end of if
			} // end of for
		} else {
			System.out.println("No bids are currently active, have an admin set one up");
		}

	}// end of method
	
	public void addBid(Bids bid) {
		this.allBids.add(bid);
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public ArrayList<Bids> getAllBids() {
		return allBids;
	}

	public void setAllBids(ArrayList<Bids> allBids) {
		this.allBids = allBids;
	}

	public ArrayList<Puppies> getAllPups() {
		return allPups;
	}

	public void setAllPups(ArrayList<Puppies> allPups) {
		this.allPups = allPups;
	}

	public ArrayList<User> getAllUsers() {
		return allUsers;
	}

	public void setAllUsers(ArrayList<User> allUsers) {
		this.allUsers = allUsers;
	}

}
