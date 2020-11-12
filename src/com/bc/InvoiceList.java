/**
 * Authors: Austin Dobrusky, Mark Forgét
 * Date:11/11/20
 * Description: provides all of the methods for the InvoiceList data structure
 */
package com.bc;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class InvoiceList<T> implements Iterable<T> {
	private static final int SIZE = 1;
	private T arr[];
	private int size;

	
	@SuppressWarnings("unchecked")
	public InvoiceList() {
		this.arr = (T[])new Object[SIZE];
		this.size = 0;
	}

	private void increaseSize() {
		this.arr = Arrays.copyOf(this.arr, this.arr.length + SIZE);
	}
	
	private void decreaseSize() {
		this.arr = Arrays.copyOf(this.arr, this.arr.length - 1);
	}

	public int size() {
		return this.size;
	}

	public T get(int index) {
		if (index < 0 || index > this.size) {
			throw new ArrayIndexOutOfBoundsException("Enter a valid index");
		}

		return this.arr[index];
	}

	private int findIndex(int first, int last, T item) { 
		//uses a recursive binary search to find where an item should be inserted in the invoicelist
		
		if (last >= first) { 
			int mid = first + (last - first) / 2; 

			//If its first invoice being added
			if(mid == 0) {
				return 0;
			}

			//If the invoice needs to go at the end
			if (((Invoice)this.arr[last - 2]).compareTo((Invoice)item) > 0) {
				return last - 1; 
			}

			//If invoice needs to be at the middle
			if (((Invoice)this.arr[mid - 1]).compareTo((Invoice)item) > 0 && ((Invoice)this.arr[mid + 1]).compareTo((Invoice)item) < 0) {
				return mid; 
			}

			//if the invoices total is less than the middle
			if (((Invoice)this.arr[mid - 1]).compareTo((Invoice)item) > 0) {
				return findIndex(mid - 1, last, item); 
			}

			//if the invoices total is greater than the middle
			return findIndex(first, mid - 1, item);
		}
		
		return -1; 
	}

	public void add(T item) {
		//Adds an item to the invoicelist while maintaining a sort by total from highest to lowest

		if(this.arr.length == this.size) {
			this.increaseSize();
		}
		
		int index = findIndex(0, this.arr.length, item);

		for(int i = this.size; i > index; i--) {
			arr[i] = arr[i-1];
		}

		this.arr[index] = item;
		this.size++;
	}
	
	public void remove(int index) {
		//Removes an element from a given index
		
		if (index < 0 || index > this.size) {
			throw new ArrayIndexOutOfBoundsException("Enter a valid index");
		}
		
		for(int i = 0; i < this.size - 1; i++) {
			this.arr[i] = this.arr[i + 1];
		}

		this.decreaseSize();
		this.size--;

	}

	public String toString() {
		String result = "[";
		
		for(int i = 0; i < this.size; i++) {
			result += this.arr[i] + ", ";
		}
		
		if(this.size() > 0) {
			result = result.substring(0, result.length() - 2) + "]";
		} else {
			result += "]";
		}
		
		return result;
	}

	class InvoiceIterator implements Iterator<T> {
		//Implements the iterator for the invoicelist class
		
		int current = 0;

		public boolean hasNext() {
			if (current < InvoiceList.this.size()) {
				return true;
			} else {
				return false;
			}
		}

		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			return arr[current++];
		}
	}

	@Override
	public Iterator<T> iterator() {
		return new InvoiceIterator();
	}
}
