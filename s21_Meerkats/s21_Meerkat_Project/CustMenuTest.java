package s21_Meerkat_Project;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustMenuTest {

	private ArrayList<Bids> curBid = new ArrayList<>();

 	@BeforeEach
	void setUp() throws Exception {
 		Puppies pupA = new Puppies("Jolly", "poodle", "male", true, 2000.0, false);
		Puppies pupB = new Puppies("Happy", "beagle", "male", true, 1000.0, false);
		Puppies pupC = new Puppies("Sugar", "German Shepherd", "female", true, 1500.0, false);
		
		curBid.add(new Bids(pupA, LocalDateTime.now().plusHours(2).withSecond(0).withNano(0)));
		curBid.add(new Bids(pupB, LocalDateTime.now().plusMinutes(0).withSecond(0).withNano(0)));
		curBid.add(new Bids(pupC, LocalDateTime.now().plusMinutes(5).withSecond(0).withNano(0)));
	}

	@AfterEach
	void tearDown() throws Exception {
		curBid.clear();
	}

	@Test
	void shouldUpdateMaxBid() {
		Bids pupBid = null;
		double maxBid = 2500.00;
		double winner = 0;
		double increment = 25.00;
		double currentBid = 0;
		
		for (int i = 0; i < curBid.size(); i++) {
			if (curBid.get(i).getPup().getName().equals("Jolly")) {
				pupBid = curBid.get(i);
				currentBid = curBid.get(i).getPup().getPrice();
			}
		}
		double newPrice = currentBid + increment;
		if (maxBid > (newPrice)) {
			pupBid.setMaxBid(maxBid);
			winner = pupBid.getMaxBid();
		}
		//The maxBid from the customer, if larger than the current bid + increment, should be the winner
		assertEquals(maxBid, winner);
		//The new price should not be the same as the maxBid. In auction the max bid is
		//amount willing to bid and not actual price of the item
		assertNotEquals(maxBid, newPrice);		
	}

	@Test
	void shouldBeNullIfSold() {
		Bids pupBid = null;
		
		for (int i = 0; i < curBid.size(); i++) {
			if (curBid.get(i).getPup().getName().equals("Happy")) {
				pupBid = curBid.get(i);
			}
		}
		//If the puppy auction has ended which for Happy it has, then Happy should be null or removed
		assertNull(pupBid);
	}
}
