package s21_Meerkat_Project;

import java.text.NumberFormat;

public class Puppies {
	private String name; // primary key for DB implementation
	private String breed;
	private String sex;
	private boolean pedigree; // who parents are if true, if false no need to capture parent's names
	private double price; // opening price
	private boolean hypo;
	
	public Puppies() {
		
	}

	public Puppies(String name) {
		super();
		this.name=name;
	}
	
	public Puppies(String name, String breed, String sex, boolean pedigree, double price, boolean hypo) {
		super();
		this.name = name;
		this.breed = breed;
		this.sex = sex;
		this.pedigree = pedigree;
		this.price = price;
		this.hypo = hypo;
	}
	
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		return "Named: " +name+" is a "+breed+". Is "+sex+". Is pedigreed: "+pedigree+". Opening priced at: "+nf.format(price)+
				". Is hypo-allergenic: "+hypo+".";// Is available: "+available+"."; //note removed
				
	}
	
	public String toStringF() {
		return name+"|"+breed+"|"+sex+"|"+pedigree+"|"+price+"|"+hypo;//+"|"+available;//note remove available
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public boolean isPedigree() {
		return pedigree;
	}

	public void setPedigree(boolean pedigree) {
		this.pedigree = pedigree;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isHypo() {
		return hypo;
	}

	public void setHypo(boolean hypo) {
		this.hypo = hypo;
	}
	
}
