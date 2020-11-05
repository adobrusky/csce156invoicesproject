package com.bc;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class InvoiceList<T> implements Iterable<T> {
	private static final int SIZE = 1;
	private T arr[];
	private int size;

	@SuppressWarnings("unchecked")
	public InvoiceList()
	{
		this.arr = (T[])new Object[SIZE];
		this.size = 0;
	}

	private void increaseSize()
	{
		this.arr = Arrays.copyOf(this.arr, this.arr.length + SIZE);
	}

	public int size() {
		return this.size;
	}

	public T get(int index)
	{
		if (index<0 || index>this.size)
		{
			throw new ArrayIndexOutOfBoundsException("Enter a correct index!!");
		}

		return this.arr[index];
	}

	private int findIndex(int first, int last, double total) 
	{ 
		if (last >= first) { 
			int mid = first + (last - first) / 2; 

			//If its first invoice being added
			if(mid == 0) {
				return 0;
			}

			//If the invoice needs to go at the end
			if ((double)((Invoice)this.arr[last - 2]).getTotal() > total) {
				return last - 1; 
			}


			//If invoice needs to be at the middle
			if ((double)((Invoice)this.arr[mid - 1]).getTotal() > total && (double)((Invoice)this.arr[mid + 1]).getTotal() < total) {
				return mid; 
			}

			//if the invoices total is less than the middle
			if ((double)((Invoice)this.arr[mid - 1]).getTotal() > total) {
				return findIndex(mid - 1, last, total); 


			}

			//if the invoices total is greater than the middle
			return findIndex(first, mid - 1, total);
		}
		return -1; 
	}

	public void add(T item)
	{

		if(this.arr.length == this.size)
		{
			this.increaseSize();
		}
		
		double total = ((Invoice)item).getTotal();
		int index = findIndex(0, this.arr.length, total);

		for(int i = this.size; i > index; i--) {
			arr[i] = arr[i-1];
		}

		this.arr[index] = item;
		this.size++;
	}

	public String toString()
	{
		String result = "";
		for(int i = 0; i < this.size; i++)
		{
			result += this.arr[i] + ", ";
		}
		return result;
	}

	class InvoiceIterator implements Iterator<T> {
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
