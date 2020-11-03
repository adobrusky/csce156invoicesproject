package com.bc;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class InvoiceList<T> implements Iterable<T> {
	private static final int SIZE = 5;
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
		this.arr = Arrays.copyOf(this.arr, this.arr.length+SIZE);
	}
	
	public T get(int index)
	{
		if (index<0 || index>this.size)
		{
			throw new ArrayIndexOutOfBoundsException("Enter a correct index!!");
		}
		
		return this.arr[index];
	}
	
	public void add(T item)
	{
		this.addAt(item, this.size);
	}
	
	public void addToStart(T item)
	{
		this.addAt(item, 0);
	}
	
	public void addAt(T item, int index)
	{
		if (index<0 || index>this.size)
		{
			throw new ArrayIndexOutOfBoundsException("Enter a correct index!!");
		}
		
		if(this.arr.length == this.size)
		{
			this.increaseSize();
		}
		
		for(int i=this.size; i>index;i--)
		{
			arr[i] = arr[i-1];
		}
		this.arr[index] = item;
		this.size++;
		
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<this.size; i++)
		{
			sb.append(this.arr[i] + ", ");
		}
		return sb.toString();
	}
	
	class InvoiceIterator implements Iterator<T> {
        int current = 0;  // the current element we are looking at

        // return whether or not there are more elements in the array that
        // have not been iterated over.
        public boolean hasNext() {
            if (current < InvoiceList.this.arr.length) {
                return true;
            } else {
                return false;
            }
        }

        // return the next element of the iteration and move the current
        // index to the element after that.
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
