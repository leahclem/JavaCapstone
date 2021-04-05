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
		// for testing
		// System.out.println("year: " + year + " month: " + month + " day: " + day + "
		// hour: " + hour + " minutes: " + min);
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
		int count = 0, backlog = 0, bidHist = 0;
		double price = 0, cbid = 0, mbid = 0, incr = 0;
		LocalDateTime startDate = null, endDate = null;
		boolean active = false, isPaidFor = false;
		String pupname, username;
		Puppies pup = null;
		User winner = null;
		Customer win = null;
		Bids added = null;
		

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
			for (int j = 0; j < 11; j++) {
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
						// if no pup found null will be entered which should never happen
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
						winner = new User("null", "apple", 'C');
					} else {// find the matching user
						for (User u : users) {
							if (username.equalsIgnoreCase(u.getUserName())) {
								count++;
								winner = u;
								if(winner instanceof Customer)
									win = (Customer) winner;
							}
						} // end of for loop through all users
							// check if there are any winners
						if (count == 0) {
							winner = new User("null", "apple", 'C');// note can't login with this information
						}
					} // end of else to find current winner

					break;
				case 7:
					active = Boolean.parseBoolean(st.nextToken());
					break;
				case 8:
					isPaidFor = Boolean.parseBoolean(st.nextToken());
					break;
				case 9:
					backlog = Integer.parseInt(st.nextToken());
					break;
				case 10:
					bidHist = Integer.parseInt(st.nextToken());
					break;
				}// end of switch case

			} // end of for all data for a puppy
			added = new Bids(pup, endDate, startDate, cbid, mbid, incr, winner, active, isPaidFor);
			//add the backlogg data
			for(int j = 0 ; j < backlog ; j++) {
				try {//grab the next line of backlog
					line = bf.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
				//add backlog to queue
				added.getBacklogg().enqueue(line);
			}
			//add the bidHistory data
			for(int j = 0 ; j < bidHist ; j++) {
				try {//grab the next line of backlog
					line = bf.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
				//add bidHist to queue
				added.getBidHistory().enqueue(line);
			}
			auctions.add(added);
			win.addHighBid(added);//adds high bid to customer object
			
		} // end of for auction lines loop

		return bf;

	}

	public void outputData(ArrayList<Puppies> pupList, ArrayList<User> users, ArrayList<Bids> auctions) {
		ArrayList<Customer> custList = new ArrayList<>();
		ArrayList<Admin> adminList = new ArrayList<>();
		int pupSize = pupList.size();
		int auctSize = auctions.size();
		
		for (User u : users) {
			if (u instanceof Customer) {
				custList.add((Customer) u); 
			} else if (u instanceof Admin) {
				adminList.add((Admin) u); 
			}
		}

		PrintWriter out = null;
		try {
			out = openWrite();

			out.println(adminList.size() + "|" + custList.size() + "|" + pupSize + "|" + auctSize);

			for (Admin a : adminList) {
				out.println(a.toStringF());
			}
			for (Customer c : custList) {
				out.println(c.toStringF());
			}

			for(Puppies p : pupList) {
				out.println(p.toStringF());
			}
			
			for(Bids b : auctions) {
				out.println(b.toStringF());
				while(!b.getBacklogg().isEmpty()) {
					out.println(b.getBacklogg().dequeue().toString());
				}
				while(!b.getBidHistory().isEmpty()) {
					out.println(b.getBidHistory().dequeue().toString());
				}
			}
					
		} finally {
			try {
				out.close();
			} catch (Exception e) {
			}
		}
	} // end of outputData

} // end of IOMethods
