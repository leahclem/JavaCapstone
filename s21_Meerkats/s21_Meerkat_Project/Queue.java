//implements the Deitel queue structure
package s21_Meerkat_Project;

import java.util.NoSuchElementException;

/**
 * Queue class used for linked list implementation with the List class
 *
 * @param <E>
 */
public class Queue<E> {
	/**
	 * queue class field containing generic List<E> as defined in List class
	 */
	private List<E> queueList;

	// constructor
	/**
	 * Constructor method using a List of generic objects <E>
	 */
	public Queue() {
		queueList = new List<E>("queue");
	}

	// add object to queue
	/**
	 * enqueue method to add an object to the queue (FIFO)
	 * 
	 * @param object
	 */
	public void enqueue(E object) {
		queueList.insertAtBack(object);
	}

	// remove object from queue
	/**
	 * dequeue method to remove the first element (FIFO), throws exception if no
	 * element
	 * 
	 * @return List object
	 * @throws NoSuchElementException
	 */
	public E dequeue() throws NoSuchElementException {
		return queueList.removeFromFront();
	}

	// determine if queue is empty
	/**
	 * isEmpty method, used to determine if the queue is empty
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return queueList.isEmpty();
	}

	// output queue contents
	/**
	 * print method that prints a list of the queued items
	 */
	public void print() {
		queueList.print();
	}

	/**
	 * size method to return an int of the size of the queued items
	 * 
	 * @return int size
	 */
	public int size() {
		return queueList.numInQueue();
	}
}

/**************************************************************************
 * (C) Copyright 1992-2018 by Deitel & Associates, Inc. and * Pearson Education,
 * Inc. All Rights Reserved. * * DISCLAIMER: The authors and publisher of this
 * book have used their * best efforts in preparing the book. These efforts
 * include the * development, research, and testing of the theories and programs
 * * to determine their effectiveness. The authors and publisher make * no
 * warranty of any kind, expressed or implied, with regard to these * programs
 * or to the documentation contained in these books. The authors * and publisher
 * shall not be liable in any event for incidental or * consequential damages in
 * connection with, or arising out of, the * furnishing, performance, or use of
 * these programs. *
 *************************************************************************/
