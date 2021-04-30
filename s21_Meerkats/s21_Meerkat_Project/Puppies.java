package s21_Meerkat_Project;

import java.text.NumberFormat;

/**
 * Puppies class, used to create a Puppies object A Puppies object contains:
 * name: a unique String containing the name of the puppy, this field is the
 * primary key for database references breed: a String describing the breed of
 * the puppy sex: a String containing either 'male' or 'female' pedigree: a
 * boolean value storing whether the puppy has a pedigree or not price: a double
 * containing the starting price of the auction hypo: a boolean value storing
 * whether the puppy is hypoallegenic or not
 *
 */
public class Puppies {
	/**
	 * name String: a unique String containing the name of the puppy, this field is
	 * the primary key for database references
	 */
	private String name; // primary key for DB implementation
	/**
	 * breed: a String describing the breed of the puppy
	 */
	private String breed;
	/**
	 * sex: a String containing either 'male' or 'female'
	 */
	private String sex;
	/**
	 * pedigree: a boolean value storing whether the puppy has a pedigree or not
	 */
	private boolean pedigree;
	/**
	 * price: a double containing the starting price of the auction
	 */
	private double price; // opening price
	/**
	 * hypo: a boolean value storing whether the puppy is hypoallegenic or not
	 */
	private boolean hypo;

	/**
	 * The empty Constructor
	 */
	public Puppies() {

	}

	/**
	 * A constructor using only the name String
	 * 
	 * @param name
	 *         
	 */
	public Puppies(String name) {
		super();
		this.name = name;
	}

	/**
	 * Full constructor using name, breed, sex, pedigree, price, and hypo.
	 * 
	 * @param name
	 * @param breed
	 * @param sex
	 * @param pedigree
	 * @param price
	 * @param hypo
	 */
	public Puppies(String name, String breed, String sex, boolean pedigree, double price, boolean hypo) {
		super();
		this.name = name;
		this.breed = breed;
		this.sex = sex;
		this.pedigree = pedigree;
		this.price = price;
		this.hypo = hypo;
	}

	/**
	 * toString method used to return a String containing all the fields of the
	 * Puppies object
	 * 
	 * @see java.lang.Object#toString()
	 * @return String
	 */
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		return "Named: " + name + " is a " + breed + ". Is " + sex + ". Is pedigreed: " + pedigree
				+ ". Opening priced at: " + nf.format(price) + ". Is hypo-allergenic: " + hypo + ".";// Is available:
																										// "+available+".";
																										// //note
																										// removed

	}

	/**
	 * toStringF method used to return a String with a '|' instead of spaces for I/O
	 * purposes
	 * 
	 * @return String
	 */
	public String toStringF() {
		return name + "|" + breed + "|" + sex + "|" + pedigree + "|" + price + "|" + hypo;// +"|"+available;//note
																							// remove available
	}

	/**
	 * getName method
	 * 
	 * @return  name
	 */
	public String getName() {
		return name;
	}

	/**
	 * setName method
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * getBreed method
	 * 
	 * @return breed
	 */
	public String getBreed() {
		return breed;
	}

	/**
	 * setBreed method
	 * 
	 * @param breed
	 */
	public void setBreed(String breed) {
		this.breed = breed;
	}

	/**
	 * getSex method
	 * 
	 * @return sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * setSex method
	 * 
	 * @param sex
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * isPedigree method
	 * 
	 * @return pedigree 
	 */
	public boolean isPedigree() {
		return pedigree;
	}

	/**
	 * setPedigree method
	 * 
	 * @param pedigree
	 */
	public void setPedigree(boolean pedigree) {
		this.pedigree = pedigree;
	}

	/**
	 * getPrice method
	 * 
	 * @return price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * setPrice method
	 * 
	 * @param price
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * isHypo method
	 * 
	 * @return hypo
	 */
	public boolean isHypo() {
		return hypo;
	}

	/**
	 * setHypo method
	 * 
	 * @param hypo
	 */
	public void setHypo(boolean hypo) {
		this.hypo = hypo;
	}

}
