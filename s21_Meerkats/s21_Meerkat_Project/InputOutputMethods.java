package s21_Meerkat_Project;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class InputOutputMethods {

	public InputOutputMethods() {

	}

	public BufferedReader openRead() {
		Frame f = new Frame();
		// decide from where to read the file
		FileDialog foBox = new FileDialog(f, "Pick location for reading your file", FileDialog.LOAD);
		System.out.println(
				"The dialog box will appear behind Eclipse.  " + "\n   Choose where you would like to read from.");
		foBox.setVisible(true);
		// get the absolute path to the file
		String foName = foBox.getFile();
		String dirPath = foBox.getDirectory();

		// create a file instance for the absolute path
		File inFile = new File(dirPath + foName);
		if (!inFile.exists()) {
			System.out.println("That file does not exist");
			System.exit(0);
		}
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(inFile));
		} catch (IOException e) {
			System.out.println("You threw an exception. ");
		}
		return in;

	}

	public PrintWriter openWrite() {
		Frame f = new Frame();
		// decide from where to read the file
		FileDialog foBox = new FileDialog(f, "Pick location for writing your file", FileDialog.SAVE);
		System.out.println("The dialog box will appear behind Eclipse.  "
				+ "\n   Choose where you would like to write your data.");
		foBox.setVisible(true);
		// get the absolute path to the file
		String foName = foBox.getFile();
		String dirPath = foBox.getDirectory();

		// create a file instance for the absolute path
		File outFile = new File(dirPath + foName);
		PrintWriter out = null;

		try {
			out = new PrintWriter(outFile);
		} catch (IOException e) {
			System.out.println("You threw an exception");
		}
		return out;
	}

	public void loadData(ArrayList<Puppies> pupList, ArrayList<User> users, ArrayList<Bids> auctions) {
		// Variable Declaration
		int pups = 0, custs = 0, admins = 0, bids = 0;

		BufferedReader bf = null;
		try {
			// Open the file.
			bf = openRead();

			// read in the first line
			String line = "";
			try {
				line = bf.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}

			String delim = "|";

			// for the header line
			StringTokenizer st = new StringTokenizer(line, delim);
			// for every item on the first line
			for (int i = 0; i < 4; i++) {
				switch (i) {
				case 0:
					admins = Integer.parseInt(st.nextToken());
					break;
				case 1:
					custs = Integer.parseInt(st.nextToken());
					break;
				case 2:
					pups = Integer.parseInt(st.nextToken());
					break;
				case 3:
					bids = Integer.parseInt(st.nextToken());
					break;
				}

			} // end of first line for loop

			bf = loadAdmins(users, bf, admins);

			bf = loadCustomers(users, bf, custs);

			bf = loadPuppies(pupList, bf, pups);

			bf = loadAuctions(auctions, bf, bids, users, pupList);

		} // end of reading in the data.

		// catch any other type of exception
		catch (Exception e) {
			System.out.println("Other weird things happened");
			e.printStackTrace();
		} finally {
			try {
				bf.close();
			} catch (Exception e) {
			}
		}
		return;

	}

	public LocalDateTime strToDate(String date) {
		// "202103121443" Year month day hour minutes and no second/millisecs
		int year, month, day, hour, min;

		year = Integer.parseInt(date.substring(0, 4));
		month = Integer.parseInt(date.substring(4, 6));
		day = Integer.parseInt(date.substring(6, 8));
		hour = Integer.parseInt(date.substring(8, 10));
		min = Integer.parseInt(date.substring(10, 12));
		//for testing
		//System.out.println("year: " + year + " month: " + month + " day: " + day + " hour: " + hour + " minutes: " + min);	
		LocalDateTime rtDate = LocalDateTime.of(year, month, day, hour, min, 0, 0);

		return rtDate;
	}

	public BufferedReader loadAdmins(ArrayList<User> users, BufferedReader bf, int admins) {
		// for all the admins loop through a line and collect all data
		// Variable Declaration
		String line = "", delim = "|";
		StringTokenizer st;
		String userName = null, password = null, fname = null, lname = null;
		boolean fAdmin = false;

		for (int i = 0; i < admins; i++) {
			// read in the next line
			try {
				line = bf.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			st = new StringTokenizer(line, delim);
			// gather all data from the admin line
			for (int j = 0; j < 5; j++) {
				// assign variables accordingly
				switch (j) {
				case 0:
					userName = st.nextToken();
					break;
				case 1:
					password = st.nextToken();
					break;
				case 2:
					fname = st.nextToken();
					break;
				case 3:
					lname = st.nextToken();
					break;
				case 4:
					fAdmin = Boolean.parseBoolean(st.nextToken());
					break;
				}// end of switch case

			} // end of for all data for an admin
			users.add(new Admin(userName, password, fname, lname, fAdmin));
		} // end of for admin lines loop

		return bf;
	}

	public BufferedReader loadCustomers(ArrayList<User> users, BufferedReader bf, int custs) {
		// Variable Declaration
		String line = "", delim = "|";
		StringTokenizer st;
		String userName = null, password = null, address = null, email = null;

		// for all the customers loop through a line and collect all data
		for (int i = 0; i < custs; i++) {
			// read in the next line
			try {
				line = bf.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			st = new StringTokenizer(line, delim);
			// gather all data from the admin line
			for (int j = 0; j < 4; j++) {
				// assign variables accordingly
				switch (j) {
				case 0:
					userName = st.nextToken();
					break;
				case 1:
					password = st.nextToken();
					break;
				case 2:
					address = st.nextToken();
					break;
				case 3:
					email = st.nextToken();
					break;
				}// end of switch case

			} // end of for all data for an customers
			users.add(new Customer(userName, password, address, email));
		} // end of for customers lines loop

		return bf;

	}

	public BufferedReader loadPuppies(ArrayList<Puppies> pupList, BufferedReader bf, int pups) {
		// Variable Declaration
		String line = "", delim = "|";
		StringTokenizer st;
		String pupname = null, breed = null, gender = null;
		double price = 0;
		boolean pedigree = false, hypo = false, sold = false;

		// for all the pups loop through a line and collect all data
		for (int i = 0; i < pups; i++) {
			// read in the next line
			try {
				line = bf.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			st = new StringTokenizer(line, delim);
			// gather all data from the puppy line
			for (int j = 0; j < 7; j++) {
				// assign variables accordingly
				switch (j) {
				case 0:
					pupname = st.nextToken();
					break;
				case 1:
					breed = st.nextToken();
					break;
				case 2:
					gender = st.nextToken();
					break;
				case 3:
					pedigree = Boolean.parseBoolean(st.nextToken());
					break;
				case 4:
					price = Double.parseDouble(st.nextToken());
					break;
				case 5:
					hypo = Boolean.parseBoolean(st.nextToken());
					break;
				case 6:
					sold = Boolean.parseBoolean(st.nextToken());
					break;
				}// end of switch case

			} // end of for all data for a puppy
			pupList.add(new Puppies(pupname, breed, gender, pedigree, price, hypo, sold));
		} // end of for puppies lines loop

		return bf;

	}// end of loadPuppies method

	public BufferedReader loadAuctions(ArrayList<Bids> auctions, BufferedReader bf, int bids, ArrayList<User> users,
			ArrayList<Puppies> pupList) {
		// Variable Declaration
		String line = "", delim = "|";
		StringTokenizer st;
		int count = 0;
		double price = 0, cbid = 0, mbid = 0, incr = 0;
		LocalDateTime startDate = null, endDate = null;
		boolean active = false;
		String pupname, username;
		Puppies pup = null;
		User winner = null;

		// for all the auctions loop through a line and collect all data
		for (int i = 0; i < bids; i++) {
			// read in the next line
			try {
				line = bf.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			st = new StringTokenizer(line, delim);
			// gather all data from the auction line
			for (int j = 0; j < 8; j++) {
				// assign variables accordingly
				switch (j) {
				case 0:
					pupname = st.nextToken();
					// find the puppy
					for (Puppies p : pupList) {
						if (pupname.equalsIgnoreCase(p.getName())) {
							pup = p;
						}
					} // end of for loop through all users
					//if no pup found null will be entered which should never happen
					break;
				case 1:
					startDate = strToDate(st.nextToken());
					break;
				case 2:
					endDate = strToDate(st.nextToken());
					break;
				case 3:
					cbid = Double.parseDouble(st.nextToken());
					break;
				case 4:
					mbid = Double.parseDouble(st.nextToken());
					break;
				case 5:
					incr = Double.parseDouble(st.nextToken());
					break;
				case 6:
					username = st.nextToken();
					// check if no winner
					count = 0;// reset count variable

					if (username.equalsIgnoreCase("null")) {
						winner = new User("no one", "apple", 'C');
					} else {// find the matching user
						for (User u : users) {
							if (username.equalsIgnoreCase(u.getUserName())) {
								count++;
								winner = u;
							}
						} // end of for loop through all users
							// check if there are any winners
						if (count == 0) {
							winner = new User("no one", "apple", 'C');// note can't login with this information
						}
					} // end of else to find current winner

					break;
				case 7:
					active = Boolean.parseBoolean(st.nextToken());
					break;
				}// end of switch case

			} // end of for all data for a puppy
			auctions.add(new Bids(pup, endDate, startDate, cbid, mbid, incr, winner, active));
		} // end of for auction lines loop

		return bf;

	}

	public void outputData(ArrayList<Puppies> puppy) {
		PrintWriter out = null;
		try {
			out = openWrite();
			for (int i = 0; i < puppy.size(); i++) {
				out.println(puppy.get(i).toStringF());
			}
		} finally {
			try {
				out.close();
			} catch (Exception e) {
			}
		}
	}
}
