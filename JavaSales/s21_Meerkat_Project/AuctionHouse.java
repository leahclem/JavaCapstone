package s21_Meerkat_Project;

import java.util.ArrayList;

public class AuctionHouse {
	private boolean open; // this should be update based on a time stamp, implement later when we have business hours

	private ArrayList<Bids> allBids; // used for implementation of database
	
	private ArrayList<Puppies> allPups;
	
	private ArrayList<User> allUsers;
	
	public AuctionHouse() {
		
	}
	
	// for use until we have Bids
	public AuctionHouse(boolean open, ArrayList<Puppies> pup, ArrayList<User> user) {
		this.open=open;
		this.allPups=pup;
		this.allUsers=user;
	}

	public AuctionHouse(boolean open, ArrayList<Bids> allBids, ArrayList<Puppies> allPups, ArrayList<User> allUsers) {
		this.open = open;
		this.allBids = allBids;
		this.allPups = allPups;
		this.allUsers = allUsers;
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
