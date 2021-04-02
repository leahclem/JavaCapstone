To follow along you can CTRL-F to // for a guided tour. 

// Our tour starts on the first menu as soon as you run the Puppies_Run_Me.java:

						(Checkpoint C update!!)
FIRST...You must save the SaveData.txt file to your computer before running the program. 
		This is to simulate a database with users, puppies, 1 running auction, and 1 closed auction.
SECOND...If you would like to see our stub methods just ctrl+f "stubs"

Here is the initial menu:

 Welcome to Puppy Heaven!
1. Search puppies: 
2. Sign in: 
3. Load sample data: 
4. Display active bids: 
5. Exit: 
Choice: 

Option 1: Working to search by name or price. Pre-loaded with several Puppies objects:
		pupList.add(new Puppies("Jolly", "poodle", "male", true, 2000.0, false, true));
		pupList.add(new Puppies("Happy", "beagle", "male", true, 1000.0, false, true));
		pupList.add(new Puppies("Sugar", "German Shepherd", "female", true, 1500.0, false, true));
		pupList.add(new Puppies("Valley", "Alsation", "female", false, 200, true, false));
		
		The primary key for the puppies is the name (e.g. Jolly, Happy, etc.).
		
Option 2: Sign in is working, we have several profiles created for Customers as well as Admins:
		users.add(new Admin("dwolff", "CutePups", "Diane", "Wolff", true));
		users.add(new Customer("jdoe", "apple", "31 Old Warren Rd.","schmoe@gmail.com"));
		users.add(new Admin("willzy", "apple", "Will", "McCoy", true));
		users.add(new Admin("assteroids", "123", "Leah", "Clemens", true));
		
		In the first case the is the UserName, password, First Name, Last name, boolean for use later. 

		The 2nd case is a customer: user name: jdoe, password: apple, address, email for PayPal
		
Option 3: Works, program loads sample data from SavedData.txt file and displays auctions that have ended. However, this option
			would not be in the end user program and is used for data (times) that are dynamic (e.g. end 5 minutes from program 
			start).

Option 4: Works, shows any active auctions.

Option 5: Works, ends program.

// Now you can select Option 2 to sign in. The following menu will be displayed:

1. Sign in
2. Create Customer Account

Option 1: This option works. To sign in as Admin use option 1 and your UserName "dwolff", and password "CutePups".

Option 2: This option works. This brings up a separate menu to set up a new Customer.

// Choose option 1 then it will query you for user name and password, then bring up the following menu (you are an Admin, so this is 
the Admin menu, later in the tutorial you can log in as a Customer and we will detail the Customer Menu):

Welcome dwolff
 Welcome to Puppy Heaven! Admin user dwolff
1. Search puppies: 
2. Logout: 
3. Add a new puppy for sale: 
4. Display active bids: 
5. Create new bid: 
6. Create new Admin: 
7. Exit: 
Choice: 

Option 1. Works. Is the same method as in the main menu to search by puppy name or price.

Option 2. Works. Logs the Admin out, returns to Main Menu.

Option 3. Works. Adds a new puppy to the ArrayList of Puppies objects. This prompts the Admin for all the fields for the new puppy object. 
			This can be tested by using option 1 to search and enter a large value like 100000 for price. It will list any puppies under 
			that value. **(Still an issue with being able to bid on puppy with a closed auction, will be fixed next meeting)

Option 4. Works. Will show any active bids.

Option 5. Works. Allows the Admin to create a new auction. This prompts the Admin for the details to search for a puppy and set up a 
			new auction for the puppy ending on a certain date and time (prompts Admin for date/time). This can be set up with the same date
			and perhaps an hour in the future to allow this to be tested when logged in as a Customer later. 

Option 6. Works. Allows the Admin to create a new Admin.

Option 7. Works. Exits program.


// To view the Customer Menu, select option 2 to log out. This brings us back to the main menu. Select 2 sign in: 
	Use Username: jdoe, PassWord: apple.

This brings up the Customer Menu:

Welcome jdoe
 Welcome to Puppy Heaven! Customer user jdoe
1. View puppies: 
2. Place bid on Puppy: 
3. Display active bids: 
4. Logout: 
5. Exit: 
Choice: 

Option 1: Works. Same method as in Main Menu.

Option 2: Works. Checks for an existing auction, must be created by Admin. If the auction was created by the Admin in the above menu then
			this option allows the Customer to place a bid. Updates the winner or sets the Customer as the winner if they are the first bidder.
				
Option 3: Works. Same method as in the Admin, displays all bids. Work in progress to have this display bids just for the customer themselves,
			instead of all the bids in general.

Option 4: Works. Logs the customer out and returns to main menu.

Option 5: Works. Exits program.

// Here ends the tutorial. 

Notes: Much of the data is not persistent yet. We have pre-loaded some puppies and Users for testing, but the Auctions/Bids are not 
saved when the program is closed. 