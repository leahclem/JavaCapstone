package s21_Meerkat_Project;

import java.sql.SQLException;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
/**
 * 
 * This class controls all functionality surround Auctions.
 * As far as storing history, backlogg, and even processing 
 * bid queries and updating the current winner and other pieces of data.
 */
public class Bids {
	// fields
	/**
	 * The current bid displayed to users
	 */
	private double currentBid;
	/**
	 * The highest bid of the current winner,
	 * there cannot be a new winner till someone
	 * bids higher than this number
	 */
	private double maxBid;
	/**
	 * The bare minimum someone is allowed to bid
	 * above the current bid.
	 */
	private double increment;
	/**
	 * The ending date and time of this Auction
	 */
	private LocalDateTime endBy;
	/**
	 * The starting date and time of this Auction
	 */
	private LocalDateTime startBy;
	/**
	 * The current winner of this Auction
	 */
	private User winner; // user object user name
	/**
	 * The cute puppy to find a home at the end of this Auction
	 */
	private Puppies pup; // puppy name is primary key
	/**
	 * The status of the Auction to determine if the Auction is over
	 */
	private boolean active;
	/**
	 * Determines if the winner has paid for the puppy,
	 * this can only change when the Auction
	 */
	private boolean paidFor = false;// will be used for checkpoint 6
	/**
	 * The queue used to contain the backlogged data,
	 * this data can only be retrieved by an admin.
	 */
	private Queue<String> backlogg;// this is where backlogg is stored for checkpoint 2
	/**
	 * The queue used to contain the Auction history,
	 * can be viewed by an admin.
	 */
	private Queue<String> bidHistory;
	/**
	 * The base empty constructor for an Auction
	 */
	public Bids() {

	}
	/**
	 * Constructor used to load in Database auctions,
	 * that are already in progress
	 * @param pup - the puppy finding a new home
	 * @param end - the end date of the Auction
	 * @param start - the start date of the Auction
	 * @param currentBid - the current bid on the Auction
	 * @param maxBid - the current high bid on the Auction
	 * @param winner - the current winner on the Auction
	 * @param active - the current status of the Auction
	 * @param paidFor - determines if the puppy has been paid for yet
	 */
	public Bids(Puppies pup, LocalDateTime end, LocalDateTime start, double currentBid, double maxBid,
			User winner, boolean active, boolean paidFor)// will be to bring in bids already made before
	{
		this.pup = pup;
		this.endBy = end;
		this.startBy = start;
		this.maxBid = maxBid;
		this.currentBid = currentBid;
		if (pup.getPrice() < 1000) {
			increment = 10;
		} else {
			increment = 50;
		}
		this.winner = winner;
		this.active = active;
		this.paidFor = paidFor;
		// temp till save fix
		this.backlogg = new Queue<>();
		this.bidHistory = new Queue<>();
	}
	/**
	 * The regular Auction constructor,
	 * used for creating a new Auction
	 * @param pup - the puppy finding a new home 
	 * @param end - the end date of the Auction
	 */
	public Bids(Puppies pup, LocalDateTime end) {// initial creation of an auction/bid
		this.pup = pup;
		currentBid = pup.getPrice();
		maxBid = 0;
		if (pup.getPrice() < 1000) {
			increment = 10;
		} else {
			increment = 50;
		}
		startBy = LocalDateTime.now().withSecond(0).withNano(0);
		endBy = end;

		winner = new User("no one", "apple", 'C'); // make sure this is fixed see checkBid method *)$
		active = true;

		this.backlogg = new Queue<>();
		this.bidHistory = new Queue<>();

		try {// Update the db, to add a new bid
			SQLMethods.checkConnect();
			SQLMethods.stmt.executeUpdate("CALL addBid(\'no one\'," + currentBid + " , 0, \'" + pup.getName() + "\', \'"
					+ dateToString(startBy) + "\', \'" + dateToString(endBy) + "\')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Update failed.");
		}
	}
	/**
	 * Function used to print out all Auction data
	 * @return - the data of the Auction in string format
	 */
	public String toString() {
		String win = " ";
		if (this.winner.getUserName().equalsIgnoreCase("no one")) {
			win = "no one";
		} else {
			win = this.winner.getUserName().toString();
		}
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		StringBuilder sb = new StringBuilder();
		sb.append(this.pup.getName() + " is ");
		if (active == false) {
			sb.append("not on auction. The winner was " + win + ". The auction ended on " + endBy
					+ " and the final price was " + nf.format(getCurrentBid()) + "." + " Paid for: " + paidFor);
		} else {
			sb.append("on auction for " + nf.format(currentBid) + ". The current winner of the auction is " + win
					+ ". This auction started on " + startBy + " and ends on " + endBy + ".");
		}
		String bidString = sb.toString();
		return bidString;
	}
	/**
	 * Method that was used in sending data to a flat file
	 * @return - data sent in a flat file format
	 */
	public String toStringF() {
		return this.getPup().getName() + "|" + dateToString(startBy) + "|" + dateToString(endBy) + "|" + currentBid
				+ "|" + maxBid + "|" + increment + "|" + winner.getUserName() + "|" + active + "|" + paidFor + "|"
				+ backlogg.size() + "|" + bidHistory.size();
	}
	/**
	 * Converts a given time to a string format
	 * used in prepared a date to be sent a database or flat file
	 * @param ldt - the date to be converted
	 * @return - the converted date
	 */
	public String dateToString(LocalDateTime ldt) {
		String year, month, day, hour, min;
		String date = ldt.toString();
		year = date.substring(0, 4);
		month = date.substring(5, 7);
		day = date.substring(8, 10);
		hour = date.substring(11, 13);
		min = date.substring(14, 16);
		return year + month + day + hour + min;
	}
	/**
	 * The function adds Auction history for the first time
	 * to an Auction
	 * @param u - the user who bidded
	 * @param newBid - The current leading bid
	 */
	// use for the first bidder
	private void newBHQueue(User u, double newBid) {
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		String bh = (pup.getName() + "'s Bid History");
		try {// Update the db and arraylist, to set update bidhist
			SQLMethods.checkConnect();
			SQLMethods.stmt.executeUpdate("CALL addHistory(\'" + pup.getName() + "\', \'" + bh + "\')");
			bidHistory.enqueue(bh);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Update failed.");
		}
		bh = ("Bidder \t\tResult \t\t\tWinner\t\tBid\t\t\tCurrent Price \t\tMax willing to pay");
		try {// Update the db and arraylist, to set update bidhist
			SQLMethods.checkConnect();
			SQLMethods.stmt.executeUpdate("CALL addHistory(\'" + pup.getName() + "\', \'" + bh + "\')");
			bidHistory.enqueue(bh);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Update failed.");
		}
		bh = (u.getUserName() + "\t\tFirst bid\t\t" + u.getUserName() + "\t\t" + nf.format(newBid) + "\t\t"
				+ nf.format(currentBid) + "\t\t\t" + nf.format(maxBid));
		try {// Update the db and arraylist, to set update bidhist
			SQLMethods.checkConnect();
			SQLMethods.stmt.executeUpdate("CALL addHistory(\'" + pup.getName() + "\', \'" + bh + "\')");
			bidHistory.enqueue(bh);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Update failed.");
		}
	}
	/**
	 * Adds to Auction bid history, whenever someone contributes
	 * to a bid
	 * @param u - the user who contributed
	 * @param newBid - the amount bidded
	 */
	// use if the same customer increases their bid, if they are already winning
	private void updateBHQueue(User u, double newBid) {
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		String bh = (u.getUserName() + "\t\tUpdated bid \t\t" + winner.getUserName() + "\t\t" + nf.format(newBid)
				+ "\t\t" + nf.format(currentBid) + "\t\t\t" + nf.format(maxBid));
		try {// Update the db and arraylist, to set update bidhist
			SQLMethods.checkConnect();
			SQLMethods.stmt.executeUpdate("CALL addHistory(\'" + pup.getName() + "\', \'" + bh + "\')");
			bidHistory.enqueue(bh);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Update failed.");
		}
	}
	/**
	 * Adds to Auction bid history, whenever there is a new winner
	 * @param u - the new winner 
	 * @param newBid - the new max bid
	 */
	// use if a new user wins
	private void newWinBHQueue(User u, double newBid) {
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		String bh = (u.getUserName() + "\t\tNew winner \t\t" + winner.getUserName() + "\t\t" + nf.format(newBid)
				+ "\t\t" + nf.format(currentBid) + "\t\t\t" + nf.format(maxBid));
		try {// Update the db and arraylist, to set update bidhist
			SQLMethods.checkConnect();
			SQLMethods.stmt.executeUpdate("CALL addHistory(\'" + pup.getName() + "\', \'" + bh + "\')");
			bidHistory.enqueue(bh);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Update failed.");
		}
	}
	/**
	 * Adds to Auction bid history, when someone doesn't 
	 * bid enough money
	 * @param u - the user who did not bid enough
	 * @param newBid - the amount they bidded
	 */
	// use if there is a new bid, but it isn't enough to win
	private void notEnoughBHQueue(User u, double newBid) {
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		String bh = (u.getUserName() + "\t\tRejected (too low) \t" + winner.getUserName() + "\t\t" + nf.format(newBid)
				+ "\t\t" + nf.format(currentBid) + "\t\t\t" + nf.format(maxBid));
		try {// Update the db and arraylist, to set update bidhist
			SQLMethods.checkConnect();
			SQLMethods.stmt.executeUpdate("CALL addHistory(\'" + pup.getName() + "\', \'" + bh + "\')");
			bidHistory.enqueue(bh);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Update failed.");
		}
	}
	/**
	 * Handles data from a bidder
	 * @param cust - the user who bidded 
	 * @param newBid - the amount the bidded
	 */
	public void checkBid(User cust, double newBid) {

		if (winner.getUserName().equalsIgnoreCase("no one")) { // do this if the bid is the first bid
			winner = cust;
			maxBid = newBid;
			newBHQueue(cust, newBid);

		} else if (winner == cust) { // do this if the customer is upping their bid on the same dog
			if (newBid > maxBid) { // if the bid is more than the current max bid
				maxBid = newBid;
				updateBHQueue(cust, newBid);
			} else if (newBid <= maxBid) { // if the bid is less than or equals to the max bid
				notEnoughBHQueue(cust, newBid);
			}

		} else { // if neither of the above situations apply then do this:
			if (newBid <= maxBid) { // if the newBid is less than or equals to maxBid
				currentBid = newBid;
				notEnoughBHQueue(cust, newBid);
			} else if (newBid > maxBid) { // if the newBid is greater than maxBid
				winner = cust;
				currentBid = maxBid + increment;
				maxBid = newBid;
				newWinBHQueue(cust, newBid);
			}
		}
	}
	/**
	 * Stores backlog data into the Auction
	 * @param cust - the user who bidded
	 * @param newBid - the amount they bidded
	 */
	public void storeBid(User cust, double newBid) {
		// Variable Declaration
		String savedBid = cust.getUserName() + " " + newBid;
		backlogg.enqueue(savedBid);
		try {// Update the db and arraylist, to add a backlogg
			SQLMethods.checkConnect();
			SQLMethods.stmt.executeUpdate("CALL addBackLog(\'" + pup.getName() + "\', \'" + savedBid + "\')");
			backlogg.enqueue(savedBid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Update failed.");
		}
	}
	/**
	 * Getter for the current bid
	 * @return - the current price on a pup
	 */
	public double getCurrentBid() {
		return currentBid;
	}
	/**
	 * Setter for the current bid
	 * @param currentBid - the value to be changed to
	 */
	public void setCurrentBid(double currentBid) {
		this.currentBid = currentBid;
	}
	/**
	 * Gets the highest bid in the Auction 
	 * @return - the highest bid belonging to the current winner
	 */
	public double getMaxBid() {
		return maxBid;
	}
	/**
	 * Sets the max bid of the winner 
	 * @param maxBid - the highest bid that belongs to the winner
	 */
	public void setMaxBid(double maxBid) {
		this.maxBid = maxBid;
	}
	/**
	 * Gets the increment of the Auction
	 * @return - the base increments above the current
	 * bid that a customer must bid
	 */
	public double getIncrement() {
		return increment;
	}
	/**
	 * Sets the increment of the Auction
	 * @param increment - the value it will be changed to
	 */
	public void setIncrement(double increment) {
		this.increment = increment;
	}
	/**
	 * Gets the end date of the Auction
	 * @return - the time the Auction ends
	 */
	public LocalDateTime getEndBy() {
		return endBy;
	}
	/**
	 * Sets the time the Auction ends
	 * @param endBy - the time the auction 
	 */
	public void setEndBy(LocalDateTime endBy) {
		this.endBy = endBy;
	}
	/**
	 * Gets the value of the start date
	 * @return - the time the Auction started
	 */
	public LocalDateTime getStartBy() {
		return startBy;
	}
	/**
	 * Sets the start date of the Auction
	 * @param startBy - the value of the start date
	 */
	public void setStartBy(LocalDateTime startBy) {
		this.startBy = startBy;
	}
	/**
	 * Gets the current winner
	 * @return - the one currently winning the Auction
	 */
	public User getWinner() {
		return winner;
	}
	/**
	 * Setter, allowing the change in current winner 
	 * @param winner - the won winning the Auction
	 */
	public void setWinner(User winner) {
		this.winner = winner;
	}
	/**
	 * Getter of the puppy
	 * @return - the puppy to find a new home
	 */
	public Puppies getPup() {
		return pup;
	}
	/**
	 * Setter for the puppy of the Auction
	 * @param pup - the new pup on the Auction
	 */
	public void setPup(Puppies pup) {
		this.pup = pup;
	}
	/**
	 * Getter for the status of the Auction
	 * @return - the status of the Auction
	 */
	public boolean isActive() {
		return active;
	}
	/**
	 * The status of Auction being open
	 * @param active - status of the Auction
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
	/**
	 * Getter for the paid for status variable
	 * @return - the status of the puppy being paid for
	 */
	public boolean isPaidFor() {
		return paidFor;
	}
	/**
	 * Sette for the paid for status variable
	 * @param paidFor - the value for it to be changed to
	 */
	public void setPaidFor(boolean paidFor) {
		this.paidFor = paidFor;
	}
	/**
	 * Getter for the backlogged data
	 * @return - the backlogged data
	 */
	public Queue<String> getBacklogg() {
		return backlogg;
	}
	/**
	 * Setter for the backlogged data
	 * @param backlogg - the value for it to be changed too
	 */
	public void setBacklogg(Queue<String> backlogg) {
		this.backlogg = backlogg;
	}
	/**
	 * Getter for the Auction History
	 * @return - the Auction history
	 */
	public Queue<String> getBidHistory() {
		return bidHistory;
	}

	public void setBidHistory(Queue<String> bidHistory) {
		this.bidHistory = bidHistory;
	}

}
