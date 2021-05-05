package s21_Meerkat_Project;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * The purpose of this static class is to upload all data from the Database on
 * SQL to the array lists on Java. To set up an initial sync up with SQL.
 */
public class InputOutputMethods {

	// public InputOutputMethods() {
	//
	// }
	/**
	 * This is the only program that is static in which is called and overheads the
	 * process of loading the data from SQL.
	 * 
	 * @param ah
	 *            - Auction House which stores all needed data throughout the
	 *            program
	 */
	public static void DbRead(AuctionHouse ah) {
		// Variable Declaration
		ResultSet us = null, as = null, cs = null, ps = null, bs = null, bas = null, bis = null;
		Queue<User> users = new Queue<>();
		ArrayList<Admin> admins = new ArrayList<>();
		ArrayList<Customer> custs = new ArrayList<>();
		String ut = "SELECT * FROM theuser";
		String at = "SELECT * FROM admin";
		String ct = "SELECT * FROM customer";
		String pt = "SELECT * FROM puppies";
		String bt = "SELECT * FROM bids";
		String bat = "SELECT * FROM backlogg";
		String bit = "SELECT * FROM bidhistory";
		User curuser, u;
		char type, userType;
		String fname, lname, payPal, address, name, breed, gender, pname, uname, username, password, data;
		Admin a;
		Customer c;
		int p, h, ac;
		double price, currentBid, maxBid, increment;
		Puppies pup = null, curp = null;
		boolean ped, hypo, active, paidFor;
		LocalDateTime start, end;
		User winner = null;
		Bids bid, curb = null;

		// check and potentially create connection to the database
		try {
			SQLMethods.checkConnect();
			// Get all result sets from all necessary tables from DB
			us = SQLMethods.stmt.executeQuery(ut);
			// Assign users to a queue
			users = new Queue<>();
			// loop through all data in us
			try {
				while (us.next()) {// gather all user data while there is another user
					username = us.getString(1);
					password = us.getString(2);
					userType = us.getString(3).charAt(0);
					u = new User(username, password, userType);
					users.enqueue(u);
					// System.out.println(u.getUserName() + u.getPassword() + u.getUserType());
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// Get admin data from DB
			as = SQLMethods.stmt.executeQuery(at);
			// Process admin data
			admins = new ArrayList<>();
			// loop through all data in as
			try {
				while (as.next()) {// gather all user data while there is another user
					fname = as.getString(1);
					lname = as.getString(2);
					username = as.getString(3);
					a = new Admin(username, "", fname, lname);
					admins.add(a);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// Process customer data
			cs = SQLMethods.stmt.executeQuery(ct);
			custs = new ArrayList<>();
			// loop through all data in cs
			try {
				while (cs.next()) {// gather all user data while there is another user
					payPal = cs.getString(1);
					address = cs.getString(2);
					username = cs.getString(3);
					c = new Customer(username, "", address, payPal);
					custs.add(c);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// merge users and add to arraylists
			while (!users.isEmpty()) {
				curuser = users.dequeue();// get current user
				type = curuser.getUserType();// get their type

				if (type == 'A') {
					// find the admin
					for (int i = 0; i < admins.size(); i++) {
						if (admins.get(i).getUserName().equals(curuser.getUserName())) {
							ah.getAllUsers().add(new Admin(curuser.getUserName(), curuser.getPassword(),
									admins.get(i).getFname(), admins.get(i).getLname()));
							break;// exit the for loop for the user has been found and assigned
						}
					}
				} else {// type is 'C'
					for (int i = 0; i < custs.size(); i++) {
						if (custs.get(i).getUserName().equals(curuser.getUserName())) {
							ah.getAllUsers().add(new Customer(curuser.getUserName(), curuser.getPassword(),
									custs.get(i).getAddress(), custs.get(i).getPayPal()));
							break;// exit the for loop for the user has been found and assigned
						}
					} // end of for loop
				} // end of else statement
			} // end of user merging into auctionhouse loop
				// Get the puppy data from DB
			ps = SQLMethods.stmt.executeQuery(pt);
			// process puppy data
			// loop through all data in ps
			try {
				while (ps.next()) {// gather all user data while there is another user
					name = ps.getString(1);
					breed = ps.getString(2);
					gender = ps.getString(3);
					p = ps.getInt(4);
					price = ps.getDouble(5);
					h = ps.getInt(6);
					if (h == 0)
						hypo = false;
					else
						hypo = true;
					if (p == 0)
						ped = false;
					else
						ped = true;
					pup = new Puppies(name, breed, gender, ped, price, hypo);
					ah.getAllPups().add(pup);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // get the Bids data from DB
			bs = SQLMethods.stmt.executeQuery(bt);
			try {// process bid data
				while (bs.next()) {// gather all user data while there is another bid
					currentBid = bs.getDouble(1);
					maxBid = bs.getDouble(2);
					end = strToDate(bs.getString(3));
					start = strToDate(bs.getString(4));
					uname = bs.getString(5);
					pname = bs.getString(6);
					ac = bs.getInt(7);
					p = bs.getInt(8);
					if (ac == 0)
						active = false;
					else
						active = true;
					if (p == 0)
						paidFor = false;
					else
						paidFor = true;
					// find the winner and puppy
					if (uname.equals("null")) {// check if there is no winner
						winner = new User("null", "apple", 'C');
					} else {
						for (int i = 0; i < ah.getAllUsers().size(); i++) {// find the winner
							if (ah.getAllUsers().get(i).getUserName().equals(uname)) {
								winner = ah.getAllUsers().get(i);
							}
						} // end of if statement
					} // end of else statement
						// find the puppy
					for (int i = 0; i < ah.getAllPups().size(); i++) {// find the winner
						if (ah.getAllPups().get(i).getName().equals(pname)) {
							pup = ah.getAllPups().get(i);
						}
					} // end of if statement
					bid = new Bids(pup, end, start, currentBid, maxBid, winner, active, paidFor);
					ah.getAllBids().add(bid);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // backlog data from DB
			bas = SQLMethods.stmt.executeQuery(bat);
			try {// process backlog
				while (bas.next()) {// gather all user data while there is another user
					// locate the bid
					pname = bas.getString(1);
					data = bas.getString(2);
					// loop through all bids to find the one
					for (int i = 0; i < ah.getAllBids().size(); i++) {
						if (ah.getAllBids().get(i).getPup().getName().equalsIgnoreCase(pname)) {
							curb = ah.getAllBids().get(i);
							break;
						}
					} // end of for loop
					curb.getBacklogg().enqueue(data);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // Get BidHistory data from DB
			bis = SQLMethods.stmt.executeQuery(bit);
			try {// process bid history data
				while (bis.next()) {// gather all user data while there is another user
					// locate the bid
					pname = bis.getString(1);
					data = bis.getString(2);
					// loop through all bids to find the one
					for (int i = 0; i < ah.getAllBids().size(); i++) {
						if (ah.getAllBids().get(i).getPup().getName().equalsIgnoreCase(pname)) {
							curb = ah.getAllBids().get(i);
							break;
						}
					} // end of for loop
					curb.getBidHistory().enqueue(data);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DB Read failure.");
			System.exit(0);
		}
		return;
	}// end of method

	/**
	 * This function take in a string in a given format and return its LocalDateTime
	 * equivalent
	 * 
	 * @param date
	 *            - the string to be turned into a date
	 * @return - the LocalDateTime variable derived from the date param.
	 */
	private static LocalDateTime strToDate(String date) {
		// "202103121443" Year month day hour minutes and no second/millisecs
		int year, month, day, hour, min;

		year = Integer.parseInt(date.substring(0, 4));
		month = Integer.parseInt(date.substring(4, 6));
		day = Integer.parseInt(date.substring(6, 8));
		hour = Integer.parseInt(date.substring(8, 10));
		min = Integer.parseInt(date.substring(10, 12));
		// for testing
		// System.out.println("year: " + year + " month: " + month + " day: " + day +
		// hour: " + hour + " minutes: " + min);
		LocalDateTime rtDate = LocalDateTime.of(year, month, day, hour, min, 0, 0);

		return rtDate;
	}

	// I commented out all of the old methods for we may no longer need them,
	// however so Mrs.Wolff can see them I'm leaving them commented out

	// public BufferedReader openRead() {
	// Frame f = new Frame();
	// // decide from where to read the file
	// FileDialog foBox = new FileDialog(f, "Pick location for reading your file",
	// FileDialog.LOAD);
	// System.out.println(
	// "The dialog box will appear behind Eclipse. " + "\n Choose where you would
	// like to read from.");
	// foBox.setVisible(true);
	// // get the absolute path to the file
	// String foName = foBox.getFile();
	// String dirPath = foBox.getDirectory();
	//
	// // create a file instance for the absolute path
	// File inFile = new File(dirPath + foName);
	// if (!inFile.exists()) {
	// System.out.println("That file does not exist");
	// System.exit(0);
	// }
	// BufferedReader in = null;
	// try {
	// in = new BufferedReader(new FileReader(inFile));
	// } catch (IOException e) {
	// System.out.println("You threw an exception. ");
	// }
	// return in;
	//
	// }
	//
	// public PrintWriter openWrite() {
	// Frame f = new Frame();
	// // decide from where to read the file
	// FileDialog foBox = new FileDialog(f, "Pick location for writing your file",
	// FileDialog.SAVE);
	// System.out.println("The dialog box will appear behind Eclipse. "
	// + "\n Choose where you would like to write your data.");
	// foBox.setVisible(true);
	// // get the absolute path to the file
	// String foName = foBox.getFile();
	// String dirPath = foBox.getDirectory();
	//
	// // create a file instance for the absolute path
	// File outFile = new File(dirPath + foName);
	// PrintWriter out = null;
	//
	// try {
	// out = new PrintWriter(outFile);
	// } catch (IOException e) {
	// System.out.println("You threw an exception");
	// }
	// return out;
	// }
	//
	// public void loadData(ArrayList<Puppies> pupList, ArrayList<User> users,
	// ArrayList<Bids> auctions) {
	// // Variable Declaration
	// int pups = 0, custs = 0, admins = 0, bids = 0;
	//
	// BufferedReader bf = null;
	// try {
	// // Open the file.
	// bf = openRead();
	//
	// // read in the first line
	// String line = "";
	// try {
	// line = bf.readLine();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	//
	// String delim = "|";
	//
	// // for the header line
	// StringTokenizer st = new StringTokenizer(line, delim);
	// // for every item on the first line
	// for (int i = 0; i < 4; i++) {
	// switch (i) {
	// case 0:
	// admins = Integer.parseInt(st.nextToken());
	// break;
	// case 1:
	// custs = Integer.parseInt(st.nextToken());
	// break;
	// case 2:
	// pups = Integer.parseInt(st.nextToken());
	// break;
	// case 3:
	// bids = Integer.parseInt(st.nextToken());
	// break;
	// }
	//
	// } // end of first line for loop
	//
	// bf = loadAdmins(users, bf, admins);
	//
	// bf = loadCustomers(users, bf, custs);
	//
	// bf = loadPuppies(pupList, bf, pups);
	//
	// bf = loadAuctions(auctions, bf, bids, users, pupList);
	//
	// } // end of reading in the data.
	//
	// // catch any other type of exception
	// catch (Exception e) {
	// System.out.println("Other weird things happened");
	// e.printStackTrace();
	// } finally {
	// try {
	// bf.close();
	// } catch (Exception e) {
	// }
	// }
	// return;
	//
	// }
	//

	//
	// public BufferedReader loadAdmins(ArrayList<User> users, BufferedReader bf,
	// int admins) {
	// // for all the admins loop through a line and collect all data
	// // Variable Declaration
	// String line = "", delim = "|";
	// StringTokenizer st;
	// String userName = null, password = null, fname = null, lname = null;
	// boolean fAdmin = false;
	//
	// for (int i = 0; i < admins; i++) {
	// // read in the next line
	// try {
	// line = bf.readLine();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// st = new StringTokenizer(line, delim);
	// // gather all data from the admin line
	// for (int j = 0; j < 4; j++) {
	// // assign variables accordingly
	// switch (j) {
	// case 0:
	// userName = st.nextToken();
	// break;
	// case 1:
	// password = st.nextToken();
	// break;
	// case 2:
	// fname = st.nextToken();
	// break;
	// case 3:
	// lname = st.nextToken();
	// break;
	// }// end of switch case
	//
	// } // end of for all data for an admin
	// users.add(new Admin(userName, password, fname, lname));
	// } // end of for admin lines loop
	//
	// return bf;
	// }
	//
	// public BufferedReader loadCustomers(ArrayList<User> users, BufferedReader bf,
	// int custs) {
	// // Variable Declaration
	// String line = "", delim = "|";
	// StringTokenizer st;
	// String userName = null, password = null, address = null, email = null;
	//
	// // for all the customers loop through a line and collect all data
	// for (int i = 0; i < custs; i++) {
	// // read in the next line
	// try {
	// line = bf.readLine();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// st = new StringTokenizer(line, delim);
	// // gather all data from the admin line
	// for (int j = 0; j < 4; j++) {
	// // assign variables accordingly
	// switch (j) {
	// case 0:
	// userName = st.nextToken();
	// break;
	// case 1:
	// password = st.nextToken();
	// break;
	// case 2:
	// address = st.nextToken();
	// break;
	// case 3:
	// email = st.nextToken();
	// break;
	// }// end of switch case
	//
	// } // end of for all data for an customers
	// users.add(new Customer(userName, password, address, email));
	// } // end of for customers lines loop
	//
	// return bf;
	//
	// }
	//
	// public BufferedReader loadPuppies(ArrayList<Puppies> pupList, BufferedReader
	// bf, int pups) {
	// // Variable Declaration
	// String line = "", delim = "|";
	// StringTokenizer st;
	// String pupname = null, breed = null, gender = null;
	// double price = 0;
	// boolean pedigree = false, hypo = false;
	//
	// // for all the pups loop through a line and collect all data
	// for (int i = 0; i < pups; i++) {
	// // read in the next line
	// try {
	// line = bf.readLine();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// st = new StringTokenizer(line, delim);
	// // gather all data from the puppy line
	// for (int j = 0; j < 6; j++) {
	// // assign variables accordingly
	// switch (j) {
	// case 0:
	// pupname = st.nextToken();
	// break;
	// case 1:
	// breed = st.nextToken();
	// break;
	// case 2:
	// gender = st.nextToken();
	// break;
	// case 3:
	// pedigree = Boolean.parseBoolean(st.nextToken());
	// break;
	// case 4:
	// price = Double.parseDouble(st.nextToken());
	// break;
	// case 5:
	// hypo = Boolean.parseBoolean(st.nextToken());
	// break;
	// }// end of switch case
	//
	// } // end of for all data for a puppy
	// pupList.add(new Puppies(pupname, breed, gender, pedigree, price, hypo));
	// } // end of for puppies lines loop
	//
	// return bf;
	//
	// }// end of loadPuppies method
	//
	// public BufferedReader loadAuctions(ArrayList<Bids> auctions, BufferedReader
	// bf, int bids, ArrayList<User> users,
	// ArrayList<Puppies> pupList) {
	// // Variable Declaration
	// String line = "", delim = "|";
	// StringTokenizer st;
	// int count = 0, backlog = 0, bidHist = 0;
	// double price = 0, cbid = 0, mbid = 0, incr = 0;
	// LocalDateTime startDate = null, endDate = null;
	// boolean active = false, isPaidFor = false;
	// String pupname, username;
	// Puppies pup = null;
	// User winner = null;
	// Customer win = null;
	// Bids added = null;
	//
	// // for all the auctions loop through a line and collect all data
	// for (int i = 0; i < bids; i++) {
	// // read in the next line
	// try {
	// line = bf.readLine();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// st = new StringTokenizer(line, delim);
	// // gather all data from the auction line
	// for (int j = 0; j < 11; j++) {
	// // assign variables accordingly
	// switch (j) {
	// case 0:// pupname
	// pupname = st.nextToken();
	// // find the puppy
	// for (Puppies p : pupList) {
	// if (pupname.equalsIgnoreCase(p.getName())) {
	// pup = p;
	// }
	// } // end of for loop through all users
	// // if no pup found null will be entered which should never happen
	// break;
	// case 1:// startdate
	// startDate = strToDate(st.nextToken());
	// break;
	// case 2:// endDate
	// endDate = strToDate(st.nextToken());
	// break;
	// case 3:// current bid
	// cbid = Double.parseDouble(st.nextToken());
	// break;
	// case 4:// max bid
	// mbid = Double.parseDouble(st.nextToken());
	// break;
	// case 5:// increment
	// incr = Double.parseDouble(st.nextToken());
	// break;
	// case 6:// winner
	// username = st.nextToken();
	// // check if no winner
	// count = 0;// reset count variable
	//
	// if (username.equalsIgnoreCase("null")) {
	// winner = new User("null", "apple", 'C');
	// } else {// find the matching user
	// for (User u : users) {
	// if (username.equalsIgnoreCase(u.getUserName())) {
	// count++;
	// winner = u;
	// if (winner instanceof Customer)
	// win = (Customer) winner;
	// }
	// } // end of for loop through all users
	// // check if there are any winners
	// if (count == 0) {
	// winner = new User("null", "apple", 'C');// note can't login with this
	// information
	// }
	// } // end of else to find current winner
	//
	// break;
	// case 7:// is active
	// active = Boolean.parseBoolean(st.nextToken());
	// break;
	// case 8:// is paidfor
	// isPaidFor = Boolean.parseBoolean(st.nextToken());
	// break;
	// case 9:// bidhist
	// backlog = Integer.parseInt(st.nextToken());
	// break;
	// case 10:// backlog
	// bidHist = Integer.parseInt(st.nextToken());
	// break;
	// }// end of switch case
	//
	// } // end of for all data for a puppy
	// added = new Bids(pup, endDate, startDate, cbid, mbid, incr, winner, active,
	// isPaidFor);
	// // add the backlogg data
	// for (int j = 0; j < backlog; j++) {
	// try {// grab the next line of backlog
	// line = bf.readLine();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// // add backlog to queue
	// added.getBacklogg().enqueue(line);
	// }
	// // add the bidHistory data
	// for (int j = 0; j < bidHist; j++) {
	// try {// grab the next line of backlog
	// line = bf.readLine();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// // add bidHist to queue
	// added.getBidHistory().enqueue(line);
	// }
	// auctions.add(added);
	// // win.addHighBid(added);//adds high bid to customer object
	//
	// } // end of for auction lines loop
	//
	// return bf;
	//
	// }
	//
	// public void outputData(ArrayList<Puppies> pupList, ArrayList<User> users,
	// ArrayList<Bids> auctions) {
	// ArrayList<Customer> custList = new ArrayList<>();
	// ArrayList<Admin> adminList = new ArrayList<>();
	// int pupSize = pupList.size();
	// int auctSize = auctions.size();
	//
	// for (User u : users) {
	// if (u instanceof Customer) {
	// custList.add((Customer) u);
	// } else if (u instanceof Admin) {
	// adminList.add((Admin) u);
	// }
	// }
	//
	// PrintWriter out = null;
	// try {
	// out = openWrite();
	//
	// out.println(adminList.size() + "|" + custList.size() + "|" + pupSize + "|" +
	// auctSize);
	//
	// for (Admin a : adminList) {
	// out.println(a.toStringF());
	// }
	// for (Customer c : custList) {
	// out.println(c.toStringF());
	// }
	//
	// for (Puppies p : pupList) {
	// out.println(p.toStringF());
	// }
	//
	// for (Bids b : auctions) {
	// out.println(b.toStringF());
	// while (!b.getBacklogg().isEmpty()) {
	// out.println(b.getBacklogg().dequeue().toString());
	// }
	// while (!b.getBidHistory().isEmpty()) {
	// out.println(b.getBidHistory().dequeue().toString());
	// }
	// }
	//
	// } finally {
	// try {
	// out.close();
	// } catch (Exception e) {
	// }
	// }
	// } // end of outputData

} // end of IOMethods
