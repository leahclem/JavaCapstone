package s21_Meerkat_Project;

public class Admin extends User {
	private String fname;
	private String lname;
	
	Admin(){
		
	}
	
	Admin(String userName, String password, String fname, String lname){
		super(userName, password, 'A');
		this.fname = fname;
		this.lname = lname;
	}
	
	public String toStringF() {
		return getUserName().toString()+"|"+getPassword().toString()+"|"+fname+"|"+lname;//+"|"+baseAdmin; note removed base admin
	}
	
	public String getFname() {
		return this.fname;
	}
	
	public void setFname(String fname) {
		this.fname = fname;
	}
	
	public String getLname() {
		return this.lname;
	}
	
	public void setLname(String lname) {
		this.lname = lname;
	}	
}
