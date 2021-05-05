package s21_Meerkat_Project;

import java.util.NoSuchElementException;

/**
 * List class used to create list objects for use with Queue class
 *
 * @param <E>
 */
class ListNode<E> {
	// package access members; List can access these directly
	/**
	 * data field
	 */
	E data; // data for this node

	/**
	 * ListNode<E> references next node in list
	 */
	ListNode<E> nextNode; // reference to the next node in the list

	// constructor creates a ListNode that refers to object

	/**
	 * Constructor for List class
	 * 
	 * @param object
	 */
	ListNode(E object) {
		this(object, null);
	}

	// constructor creates ListNode that refers to the specified
	// object and to the next ListNode

	/**
	 * Constructor for List class
	 * 
	 * @param data
	 * @param nextNode
	 */
	ListNode(E object, ListNode<E> node) {
		data = object;
		nextNode = node;
	}

	/**
	 * getData method
	 * 
	 * @return data
	 */
	E getData() {
		return data;
	}

	/**
	 * getNext method, returns reference to next node in list
	 * 
	 * @return nextNode
	 */
	ListNode<E> getNext() {
		return nextNode;
	}
}

// class List definition

/**
 * Class List contains: firstNode lastNode name
 *
 * @param <E>
 */
public class List<E> {
	/**
	 * field firstNode containing generic element <E>
	 */
	private ListNode<E> firstNode;
	/**
	 * field lastNode containing generic element <E>
	 */
	private ListNode<E> lastNode;
	/**
	 * field name, contains String used for printing
	 */
	private String name;

	/**
	 * Constructor for List, using 'list' as name
	 */
	public List() {
		this("list");
	}

	/**
	 * Constructor for List, using listName (String) as name
	 * 
	 * @param listName
	 */
	public List(String listName) {
		name = listName;
		firstNode = lastNode = null;
	}

	/**
	 * insertAtFront method used to add an item to the front of the List
	 * 
	 * @param insertItem
	 * 
	 */
	public void insertAtFront(E insertItem) {
		if (isEmpty()) { // firstNode and lastNode refer to same object
			firstNode = lastNode = new ListNode<E>(insertItem);
		} else { // firstNode refers to new node
			firstNode = new ListNode<E>(insertItem, firstNode);
		}
	}

	/**
	 * insertAtBack method used to add an item to the back of the List
	 * 
	 * @param insertItem
	 * 
	 */
	public void insertAtBack(E insertItem) {
		if (isEmpty()) { // firstNode and lastNode refer to same object
			firstNode = lastNode = new ListNode<E>(insertItem);
		} else { // lastNode's nextNode refers to new node
			lastNode = lastNode.nextNode = new ListNode<E>(insertItem);
		}
	}

	/**
	 * removeFromFront method used to remove an item from the front of the List
	 * 
	 * @return removedItem
	 * @throws NoSuchElementException
	 */
	public E removeFromFront() throws NoSuchElementException {
		if (isEmpty()) { // throw exception if List is empty
			throw new NoSuchElementException(name + " is empty");
		}

		E removedItem = firstNode.data; // retrieve data being removed

		// update references firstNode and lastNode
		if (firstNode == lastNode) {
			firstNode = lastNode = null;
		} else {
			firstNode = firstNode.nextNode;
		}

		return removedItem; // return removed node data
	}

	/**
	 * removeFromBack method used to remove an item from the back of the List
	 * 
	 * @return removedItem
	 * @throws NoSuchElementException
	 */
	public E removeFromBack() throws NoSuchElementException {
		if (isEmpty()) { // throw exception if List is empty
			throw new NoSuchElementException(name + " is empty");
		}

		E removedItem = lastNode.data; // retrieve data being removed

		// update references firstNode and lastNode
		if (firstNode == lastNode) {
			firstNode = lastNode = null;
		} else { // locate new last node
			ListNode<E> current = firstNode;

			// loop while current node does not refer to lastNode
			while (current.nextNode != lastNode) {
				current = current.nextNode;
			}

			lastNode = current; // current is new lastNode
			current.nextNode = null;
		}

		return removedItem; // return removed node data
	}

	/**
	 * isEmpty method returns boolean 'true' if List is empty
	 * 
	 * @return boolean
	 */
	public boolean isEmpty() {
		return firstNode == null;
	}

	/**
	 * print method prints all elements in the list
	 */
	public void print() {
		if (isEmpty()) {
			System.out.printf("Empty %s%n", name);
			return;
		}

		System.out.printf("The %s is: \n ", name);
		ListNode<E> current = firstNode;

		// while not at end of list, output current node's data
		while (current != null) {
			System.out.printf("%s \n", current.data);
			current = current.nextNode;
		}

		System.out.println();
	}

	/**
	 * numInQueue method returns total elements in List (see size in Queue class)
	 * 
	 * @return num
	 */
	public int numInQueue() {
		int num = 0;
		if (isEmpty()) {
			return 0;
		}
		ListNode<E> current = firstNode;

		while (current != null) {
			num = num + 1;
			current = current.nextNode;
		}
		return num;
	}

}