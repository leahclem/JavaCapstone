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
		this.pup=pup;
		currentBid=pup.getPrice();
		maxBid=0;
		if(pup.getPrice()<1000) {
			increment = 10;
		}else{
			increment = 50;
		}
		startBy=java.util.Calendar.getInstance().getTime();
		endBy=end;
		winner=null;
	}
	
	// 
	

}
