package s21_Meerkat_Project;

import java.util.NoSuchElementException;

/**
 * Queue class used for linked list implementation with the List class
 *
 * @param Queue
 */
public class Queue<E> {
	/**
	 * queue class field containing generic List as defined in List class
	 */
	private List<E> queueList;

	// constructor
	/**
	 * Constructor method using a List of generic objects
	 */
	public Queue() {
		queueList = new List<E>("queue");
	}

	// add object to queue
	/**
	 * enqueue method to add an object to the queue (FIFO)
	 * 
	 * @param object - object
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
	 * @throws NoSuchElementException - if queue is empty
	 */
	public E dequeue() throws NoSuchElementException {
		return queueList.removeFromFront();
	}

	// determine if queue is empty
	/**
	 * isEmpty method, used to determine if the queue is empty, returns 'true' if
	 * empty, 'false' if not empty
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
	 * @return size
	 */
	public int size() {
		return queueList.numInQueue();
	}
}