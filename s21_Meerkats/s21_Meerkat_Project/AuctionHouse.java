package s21_Meerkat_Project;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * 
 * The AuctionHouse class contains all bids, users, and puppies. It is used to
 * keep track of the active bids, users' bids, bidding times, and close bids
 * once the time is up. This class can also be used to access closed bids and
 * their winners once the auction is over
 *
 */
public class AuctionHouse {
	/**
	 * boolean - indicates if the Auction House is open or closed.
	 */
	private boolean open; // this should be update based on a time stamp, implement later when we have
							// business hours
	/**
	 * ArrayList of Bid objects
	 */
	private ArrayList<Bids> allBids; // used for implementation of database
	/**
	 * ArrayList of Puppy objects
	 */
	private ArrayList<Puppies> allPups;
	/**
	 * ArrayList of User objects
	 */
	private ArrayList<User> allUsers;

	/**
	 * Empty AuctionHouse constructor
	 */
	public AuctionHouse() {

	}

	// for use until we have Bids
	public AuctionHouse(boolean open, ArrayList<Puppies> pup, ArrayList<User> user) {
		this.open = open;
		this.allPups = pup;
		this.allUsers = user;
	}

	/**
	 * Full AuctionHouse constructor - used to access status of the AuctionHouse
	 * plus all bids, puppies, and users.
	 * 
	 * @param open
	 *            - if the auction house is open or closed
	 * @param allBids
	 *            - all users' bids
	 * @param allPups
	 *            - all puppies
	 * @param allUsers
	 *            - all users
	 */
	public AuctionHouse(boolean open, ArrayList<Bids> allBids, ArrayList<Puppies> allPups, ArrayList<User> allUsers) {
		this.open = open;
		this.allBids = allBids;
		this.allPups = allPups;
		this.allUsers = allUsers;
	}

	/**
	 * checkTime method - checks if a bid's end time has been reached based on local
	 * date and time. Establishes the winner when the bid ends and updates the bids
	 * database.
	 */
	public void checkTime() {// will check if any bids are over
		LocalDateTime curTime = LocalDateTime.now();// The current time and date
		String win = " ";
		if (this.allBids != null) {// if there are any bids active
			for (Bids b : this.allBids) {
				if (b.getEndBy().isBefore(curTime) && b.isActive() == true) {// Checks if the current time is
																				// before the end date and the
																				// bid is active
					// print and call something in bids to end this bid
					if (b.getWinner().getUserName().equalsIgnoreCase("null")) {
						win = "no one";
					} else {
						win = b.getWinner().getUserName().toString();
					}
					System.out.println("The bid for " + b.getPup().getName() + " is over the winner is " + win);

					try {// Update the db and arraylist, to set the bid inactive
						SQLMethods.checkConnect();
						SQLMethods.stmt.executeUpdate("CALL updateActive(\'" + b.getPup().getName() + "\')");
						b.setActive(false);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("Update failed");
					}

				}

			} // end of for loop
		}
	}// end of checkTime

	public void activeBids() {// prints all active bids
		if (this.allBids.size() != 0) {// if there are any active bids
			for (Bids b : this.allBids) {
				if (b.isActive()) {
					System.out.println(b.toString());
				} // end of if
			} // end of for
		} else {
			System.out.println("There are no auctions yet, have an admin set one up. ");
		}

	}// end of method

	/**
	 * closedBids method - if a bid is past the ending date this method prints all
	 * of the closed bids.
	 */
	public void closedBids() { // prints all closed bids
		if (this.allBids.size() != 0) { // if there are any bids
			for (Bids b : this.allBids) {
				if (b.getEndBy().isBefore(LocalDateTime.now())) { // if bids that are past the endBy date
					System.out.println(b.toString());
				} // end if
			} // end for

		} else {
			System.out.println("There are no closed bids yet. ");
		}
	}

	/**
	 * addBid method - adds a bid
	 * 
	 * @param bid
	 *            Bids object
	 */
	public void addBid(Bids bid) {
		this.allBids.add(bid);
	}

	/**
	 * isOpen method - checks AuctionHosue open status
	 * 
	 * @return open boolean
	 */
	public boolean isOpen() {
		return open;
	}

	/**
	 * setOpen method - sets open boolean
	 * 
	 * @param open
	 *            boolean
	 */
	public void setOpen(boolean open) {
		this.open = open;
	}

	/**
	 * getAllBids method - accesses entire Bids ArrayList
	 * 
	 * @return allBids ArrayList of Bids objects
	 */
	public ArrayList<Bids> getAllBids() {
		return allBids;
	}

	/**
	 * setAllBids method - sets all bids in Bids ArrayList
	 * 
	 * @param allBids
	 *            ArrayList of Bids objects
	 */
	public void setAllBids(ArrayList<Bids> allBids) {
		this.allBids = allBids;
	}

	/**
	 * getAllPups method - accesses entire Puppies ArrayList
	 * 
	 * @return allPups ArrayList of Puppies objects
	 */
	public ArrayList<Puppies> getAllPups() {
		return allPups;
	}

	/**
	 * setAllPups method - updates entire Puppies ArrayList
	 * 
	 * @param allPups
	 *            ArrayList of Puppies objects
	 */
	public void setAllPups(ArrayList<Puppies> allPups) {
		this.allPups = allPups;
	}

	/**
	 * getAllUsers method - accesses entire User ArrayList
	 * 
	 * @return allUsers ArrayList of Users objects
	 */
	public ArrayList<User> getAllUsers() {
		return allUsers;
	}

	/**
	 * setAllUsers method - updates entire Users ArrayList
	 * 
	 * @param allUsers
	 *            ArrayList of Users objects
	 */
	public void setAllUsers(ArrayList<User> allUsers) {
		this.allUsers = allUsers;
	}

}
