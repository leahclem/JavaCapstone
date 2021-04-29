package s21_Meerkat_Project;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Customer extends User {
	private String payPal;
	// private ArrayList<Bids> hiBids; // Thinking this was not used. Commenting out
	// to see after testing. Used line Bids 295 & 397.
	private String address;

	Customer() {

	}

	Customer(String userName, String password, String address) {
		super(userName, password, 'C');
		this.address = address;
		// this.hiBids = new ArrayList<Bids>();
	}

	Customer(String userName, String password, String address, String payPal) {
		super(userName, password, 'C');
		this.address = address;
		this.payPal = payPal;
		// this.hiBids = new ArrayList<Bids>();
	}

	public String toStringF() {
		return getUserName().toString() + "|" + getPassword().toString() + "|" + address + "|" + payPal;

	}

	public String getPayPal() {
		return payPal;
	}

	public void setPayPal(String payPal) {
		this.payPal = payPal;
	}

	// public ArrayList<Bids> getHiBids() {
	// return hiBids;
	// }
	//
	// //high bids needs more implementation
	// public void addHighBid(Bids added) {
	// this.hiBids.add(added);
	// }
	//
	//
	// public void setHiBids(ArrayList<Bids> hiBids) {
	// this.hiBids = hiBids;
	// }

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}