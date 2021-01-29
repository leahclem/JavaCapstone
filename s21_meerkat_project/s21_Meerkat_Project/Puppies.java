package s21_Meerkat_Project;

public class Puppies extends Bids {
	private String name;
	private String breed;
	private String sex;
	private boolean pedigree;
	private double price;
	private boolean hypo;
	
	public Puppies() {
		
	}

	public Puppies(String name, String breed, String sex, boolean pedigree, double price, boolean hypo) {
		super();
		this.name = name;
		this.breed = breed;
		this.sex = sex;
		this.pedigree = pedigree;
		this.price = price;
		this.hypo=hypo;
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

	public boolean isShowDog() {
		return pedigree;
	}

	public void setPedigree(boolean showDog) {
		this.pedigree = pedigree;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
		
}
