package s21_Meerkat_Project;

import java.text.NumberFormat;
import java.time.LocalDateTime;

public class Bids {

	private double currentBid;

	private double maxBid;

	private double increment;

	private LocalDateTime endBy;

	private LocalDateTime startBy;

	private User winner;

	private Puppies pup;

	private boolean active;

	public Bids() {

	}

	public Bids(Puppies pup, String end, String start , double currentBid, double maxBid, double increment, User winner, boolean active)//will be to bring in bids already made before
	{
		//initialize
		LocalDateTime endBy = this.strToDate(end);
		LocalDateTime startBy = this.strToDate(start);
		
		this.pup = pup;
		this.endBy = endBy;
		this.startBy = startBy;
		this.maxBid = maxBid;
		this.currentBid = currentBid;
		this.increment = increment;
		this.winner = winner;
		this.active = active;
	}
	
	public Bids(Puppies pup, LocalDateTime end) {//initial creation of an auction/bid
		this.pup = pup;
		currentBid = pup.getPrice();
		maxBid = 0;
		if (pup.getPrice() < 1000) {
			increment = 10;
		} else {
			increment = 50;
		}
		startBy = LocalDateTime.now();
		endBy = end;

		winner = new User("no one", "apple", 'C'); // make sure this is fixed see checkBid method 
		active = true;
	}

	public LocalDateTime strToDate(String date) {
		//"202103121443" Year month day hour minutes and no second/millisecs
		int year, month, day, hour, min;
		
		year = Integer.parseInt(date.substring(0, 3));
		month = Integer.parseInt(date.substring(4, 5));
		day = Integer.parseInt(date.substring(6, 7));
		hour = Integer.parseInt(date.substring(8, 9));
		min = Integer.parseInt(date.substring(10, 11));
		
		LocalDateTime rtDate = LocalDateTime.of(year, month, day, hour, min, 0, 0);
		
		return rtDate; 
	}
	
	public String toString() { 
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		StringBuilder sb = new StringBuilder();
		sb.append(this.pup.getName() + " is ");
		if (active == false) {
			sb.append("not on auction. The winner was " + this.winner.getUserName() + ". The auction ended on " + endBy
					+ " and the final price was " + nf.format(getCurrentBid()) + ".");
		} else {
			sb.append("on auction for " + nf.format(currentBid) + ". The current winner of the auction is "
					+ this.winner.getUserName() + ". This auction started on " + startBy + " and ends on " + endBy
					+ ".");
		}
		String bidString = sb.toString();
		return bidString;
	}

	public void checkBid(User cust, double newBid) {
		if (winner == null) { // fix from constructor change
			winner = cust;
			maxBid = newBid;
		} else if (winner == cust) {
			maxBid = newBid;
		} else {
			if (newBid > currentBid + increment && newBid < maxBid + increment) {
				currentBid = currentBid + increment;
			} else if (newBid > maxBid + increment) {
				winner = cust;
				currentBid = maxBid + increment;
				maxBid = newBid;
			}
		}
	}

	public double getCurrentBid() {
		return currentBid;
	}

	public void setCurrentBid(double currentBid) {
		this.currentBid = currentBid;
	}

	public double getMaxBid() {
		return maxBid;
	}

	public void setMaxBid(double maxBid) {
		this.maxBid = maxBid;
	}

	public double getIncrement() {
		return increment;
	}

	public void setIncrement(double increment) {
		this.increment = increment;
	}

	public LocalDateTime getEndBy() {
		return endBy;
	}

	public void setEndBy(LocalDateTime endBy) {
		this.endBy = endBy;
	}

	public LocalDateTime getStartBy() {
		return startBy;
	}

	public void setStartBy(LocalDateTime startBy) {
		this.startBy = startBy;
	}

	public User getWinner() {
		return winner;
	}

	public void setWinner(User winner) {
		this.winner = winner;
	}

	public Puppies getPup() {
		return pup;
	}

	public void setPup(Puppies pup) {
		this.pup = pup;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
