package s21_Meerkat_Project;

import java.util.ArrayList;
import java.util.Scanner;

public class Puppies_Run_Me {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static void loginInMenu(ArrayList<User> users) {
		//Variable Declaration
		String userName;
		String password;
		Scanner scan = new Scanner(System.in);
		boolean notSignedIn = true;
		
		//make option to create customer account
		
		//End when the user enter a valid sign in
		while(notSignedIn) {
			//get userName
			System.out.println("Input Username: ");
			userName = scan.nextLine();
			//get password
			System.out.println("Input password: ");
			password = scan.nextLine();
			//cycle through users
			for(User u: users) {
				if(u.getUserName.equals(userName) && ) {
					
				}
			}
			
			System.out.println("Invalid Username or Password. Try again.");
		}
		
	}
	//Default stuff, going to change, to read from a file
	public static void createDefaultAdmin(ArrayList<User> users) {
		users.add(new Admin("dwolff", "CutePups", "Diane", "Wolff", true));
		users.add(new Customer("jdoe", "idkTu", "31 Old Warren Rd."))
	}
	
}
