package s21_Meerkat_Project;

public class Customer extends User{
	private int creditC; // credit card info
	private ArrayList<bid> hiBids; // customer's current high bid
	private String address;
	
	Customer(){
		
	}
	
	Customer(String userName, String password, String address){
		super(userName, password, "C");
		this.address = address;
		this.hibids = new ArrayList<double>();
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
