package s21_Meerkat_Project;

/**
 * 
 * The Customer class extends User - used to create a Customer object. A customer object
 * contains a first name, last name, user ID, password and account type identifier 'C'.
 *
 */
public class Customer extends User {
	private String payPal;
	// private ArrayList<Bids> hiBids; // Thinking this was not used. Commenting out
	// to see after testing. Used line Bids 295 & 397.
	private String address;
	/**
	 * Empty Customer constructor
	 */
	Customer() {

	}
	/**
	 * Full Customer constructor - used to create a new Customer User containing parent class User
	 * fields: username, password, and user type identifier 
	 * @param userName - Customer user name
	 * @param password - Customer password
	 * @param address - Customer mailing address
	 */
	Customer(String userName, String password, String address) {
		super(userName, password, 'C');
		this.address = address;
		// this.hiBids = new ArrayList<Bids>();
	}
	/**
	 * Full Customer constructor - used to create a new Customer User containing parent class User
	 * fields: username, password, and user type identifier 
	 * Customer fields: address and paypal
	 * @param userName - Customer user name
	 * @param password - Customer password
	 * @param address - Customer mailing address
	 * @param paypal - Customer paypal address
	 */
	Customer(String userName, String password, String address, String payPal) {
		super(userName, password, 'C');
		this.address = address;
		this.payPal = payPal;
		// this.hiBids = new ArrayList<Bids>();
	}
	/**
	 * toStringF method - console output for Customer object
	 * @return String containing User and Customer fields 
	 */
	public String toStringF() {
		return getUserName().toString() + "|" + getPassword().toString() + "|" + address + "|" + payPal;

	}
	/**
	 * getPayPal method 
	 * @return payPal String
	 */
	public String getPayPal() {
		return payPal;
	}
	/**
	 * setPayPal method
	 * @param payPal String
	 */
	public void setPayPal(String payPal) {
		this.payPal = payPal;
	}

/*	public ArrayList<Bids> getHiBids() {
	return hiBids;
	 }
	
	 //high bids needs more implementation
	public void addHighBid(Bids added) {
	 this.hiBids.add(added);
	 }
	
	public void setHiBids(ArrayList<Bids> hiBids) {
	 this.hiBids = hiBids;
	}*/
	
	/**
	 * getAddress method
	 * @return address String
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * setAddress method 
	 * @param address String
	 */
	public void setAddress(String address) {
		this.address = address;
	}

}