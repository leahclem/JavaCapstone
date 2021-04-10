To follow along you can CTRL-F to "//" for a guided tour
// Our tour starts on the first menu as soon as you run the Puppies_Run_Me.java:

						(Checkpoint D update!!)
FIRST:		You must save the SaveData.txt file to your computer before running the program. Then select that file when you run the program.
			If you choose to log out using one of the official menu EXITS, then it will prompt you to save an updated file, which you would
			load the next time you open the program for data persistence.
SECOND:		Choose option 3 from main menu for the dynamic bids that are preset to open and some that are set to close immediately for 
			your testing. Please note that option 3 is for testing purposes and would not be in the customer facing product.
THIRD:		We implemented a queue for backlog data and bid history. To test backlog after closing hours you need to manually change the time
			on your computer to simulate business hours and closed hours. These options work behind the scenes if you are a customer.
			To process the backlogged data you must sign in as an Admin during 9:00 to 17:00 and select option 1. To view the bid history 
			you must sign in as an Admin and select option 9.

// Here is the initial menu:

 Welcome to Puppy Heaven!
1. Search puppies: 
2. Sign in: 
3. Load sample data: (for testing/grading purposes: dynamic dates) 
4. Display active bids: 
5. Exit: 
Choice: 

Option 1: 	Working to search by name or price. Pre-loaded with several Puppies objects:
			Puppies pupA = new Puppies("Ginger", "poodle", "male", true, 2000.0, false, true);
			Puppies pupB = new Puppies("Pepper", "beagle", "male", true, 1000.0, false, true);
			Puppies pupC = new Puppies("Red", "German Shepherd", "female", true, 1500.0, false, true);
			Puppies pupD = new Puppies("Weston", "Alsation", "female", false, 200, true, true);
		
			The primary key for the puppies is the name (e.g. Jolly, Happy, etc.).
		
Option 2: 	Sign in is working, we have several profiles created for Customers as well as Admins:
			dwolff|CutePups|Diane|Wolff|true
			assteroids|123|Leah|Clemens|true
			willzy|apple|Will|McCoy|true
			jdoe|apple|31 Old Warren Rd.|schmoe@gmail.com
			dave|apple|7457 Grapetree Lane|dave@superdave.com
		
			The first three are Admins: e.g.: user name: dwolff, password: CutePups, First Name: Diane, Last name: Wolff, boolean for use later. 

			The last two are customers: e.g.: user name: jdoe, password: apple, address, email for PayPal
		
Option 3: 	Works, program loads sample data from SavedData.txt file and displays auctions that have ended. However, this option
			would not be in the end user program and is used for data (times) that are dynamic (e.g. end 5 minutes from program 
			start).

Option 4: 	Works, shows any active auctions.

Option 5: 	Works, ends program.

// Now you can select Option 2 to sign in. The following menu will be displayed:

1. Sign in
2. Create Customer Account

Option 1: 	This option works. To sign in as Admin use option 1 and your UserName "dwolff", and password "CutePups".

Option 2: 	This option works. This brings up a separate menu to set up a new Customer.

// Choose option 1 then it will query you for user name and password, then bring up the following menu (you are an Admin, so this is 
the Admin menu, later in the tutorial you can log in as a Customer and we will detail the Customer Menu):

Welcome dwolff
 Welcome to Puppy Heaven! Admin user dwolff
1. Process backlogged data for Auctions (9-5pm): 
2. Search puppies: 
3. Logout:
4. Add a new puppy for sale:
5. Display active auctions:
6. Create new auction:
7. Create new Admin: 
8. View closed auctions:
9. View bid history queue:
10. Exit:
Choice: 

Option 1. 	Works. Processes backlogged data for after hours auctions (5pm-9am). This function only works during
			business hours (9am-5pm). If you are testing outside of that time frame you can manually update your 
			computer's clock. 
			
Option 2. 	Works. Is the same method as in the main menu to search by puppy name or price.

Option 3. 	Works. Logs the Admin out, returns to Main Menu.

Option 4. 	Works. Adds a new puppy to the ArrayList of Puppies objects. This prompts the Admin for all the fields for the new puppy object. 
			This can be tested by using option 2 to search and enter a large value like 100000 for price. It will list any puppies under 
			that value.

Option 5. 	Works. Will show any active bids.

Option 6. 	Works. Allows the Admin to create a new auction. This prompts the Admin for the details to search for a puppy and set up a 
			new auction for the puppy ending on a certain date and time (prompts Admin for date/time). We have updated the hours allowed
			to set the close time for the auction to only be able to close during business hours (9:00-16:59). This prevents an auction
			being bid on during closed hours and backlogged, then closing without the backlog being processed.
			
Option 7. 	Works. Allows the Admin to create a new Admin.

Option 8. 	Works. Displays all auctions that have ended.

Option 9. 	Works. Search by puppy name or view all bid history. This provides an annotated list of bids as per the project description
			document.

Option 10. 	Works. Exits program.


// To view the Customer Menu, select option 2 to log out. This brings us back to the main menu. Select 2 sign in: 
	Use Username: jdoe, PassWord: apple.

This brings up the Customer Menu:

Welcome jdoe
 Welcome to Puppy Heaven! Customer user jdoe
1. View puppies: 
2. Place bid on Puppy: 
3. Display active bids: 
4. Display all my winning auctions:
5. Pay for puppy:
6. Logout: 
7. Exit: 
Choice: 

Option 1: 	Works. Same method as in Main Menu.

Option 2: 	Works. Checks for an existing auction, must be created by Admin. If the auction was created by the Admin in the above menu then
			this option allows the Customer to place a bid. Updates the winner or sets the Customer as the winner if they are the first bidder.
				
Option 3: 	Works. Same method as in the Admin, displays all bids. 

Option 4. 	Works. Displays all auctions the user is currently winning

Option 5. 	Works. Displays all auctions user has won and needs to pay for. They can confirm payment per puppy 

Option 6: 	Works. Logs the customer out and returns to main menu.

Option 7: 	Works. Exits program.

// Here ends the tutorial. 
