package s21_Meerkat_Project;

import java.util.Date;

public class Bids {

	private double currentBid;

	private double maxBid;

	private double increment;

	private Date endBy;

	private Date startBy;

	private User winner;

	private Puppies pup;

	public Bids() {

	}

	public Bids(Puppies pup, Date end) {
		this.pup = pup;
		currentBid = pup.getPrice();
		maxBid = 0;
		if (pup.getPrice() < 1000) {
			increment = 10;
		} else {
			increment = 50;
		}
		startBy = java.util.Calendar.getInstance().getTime();
		endBy = end;
		winner = null;
	}

	public void checkBid(User cust, double newBid) {
		if (winner == null) {
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

	public Date getEndBy() {
		return endBy;
	}

	public void setEndBy(Date endBy) {
		this.endBy = endBy;
	}

	public Date getStartBy() {
		return startBy;
	}

	public void setStartBy(Date startBy) {
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

}
