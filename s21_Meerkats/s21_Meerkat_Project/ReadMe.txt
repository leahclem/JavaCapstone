Here is the initial menu:

 Welcome to Puppy Heaven!
1. Search puppies: 
2. Sign in: 
3. Load sample data: 
4. Display active bids: 
5. Exit: 
Choice: 

Option 1: Working to search by name or price. Preloaded with several Puppies objects:
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
		
Option 3: Does not work, yet.

Option 4: Works, shows any active auctions.

Option 5: Works, ends program.

Select Option 2 to sign in. The following menu will be displayed:

1. Sign in
2. Create Customer Account

Option 1: This option works. To sign in as Admin use option 1 and your UserName "dwolff", and password "CutePups".

Option 2: This option works. This brings up a separate menu to set up a new Customer.

Once you choose option 1 it will query you for user name and password, then bring up the following menu (you are an Admin, so this is 
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

1. Works. Is the same method as in the main menu to search by puppy name or price.

2. Works. Logs the Admin out.

3. Works. 	Adds a new puppy to the ArrayList of Puppies objects. This prompts the Admin for all the fields for the new puppy object. 
			This can be tested by using option 1 to search and enter a large value like 100000 for price. It will list any puppies under 
			that value.

4. Works. Will show any active bids.

5. Works. 	Allows the Admin to create a new auction. This prompts the Admin for the details to search for a puppy and set up a 
			new auction for the puppy. 

6. Works. Allows the Admin to create a new Admin.

7. Works. Exits program.


To view the Customer Menu, select option 2. This brings us back to the main menu. Select 2 sign in: Use Username: jdoe, PassWord: apple.

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

Option 2: Works. Checks for an existing auction, must be created by Admin. Then allows the user to place a bid. Updates the winner or sets t
				as winner if they are the first bidder.
				
Option 3: Works. Same method as in the Admin, displays all bids.

Option 4: Works. Logs the customer out and returns to main menu.

Option 5: Works. Exits program.

Here ends your tutorial.