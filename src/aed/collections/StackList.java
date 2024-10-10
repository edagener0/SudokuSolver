package aed.collections;

import java.util.Iterator;

public class StackList<T> implements IStack<T>
{
	private class Node
	{
		private T item;
		private Node next;

		Node(T item, Node next)
		{
			this.item = item;
			this.next = next;
		}
	}


	private class StackListIterator implements Iterator<T>
	{
		private Node iterator;

		public StackListIterator()
		{
			this.iterator = list;
		}

		public boolean hasNext()
		{
			return this.iterator != null;
		}

		public T next()
		{
			T result = this.iterator.item;
			this.iterator = this.iterator.next;
			return result;
		}

		public void remove()
		{
			throw new UnsupportedOperationException("I do not support this.");
		}
	}


	private Node list;

	private int size;

	public StackList()
	{
		list = null;
		this.size = 0;
	}


	// @Override
	public int size()
	{
		return this.size;
	}


	@Override
	//@SuppressWarnings("unchecked")
	public StackList<T> shallowCopy()
	{
		StackList<T> stackListShallowCopy = new StackList<T>();

		stackListShallowCopy.list = this.list;
		stackListShallowCopy.size = this.size;

		return stackListShallowCopy;
	}


	@Override
	public boolean isEmpty()
	{
		return this.size <= 0;
	}


	@Override
	public void push(T item)
	{
		Node newNode = new Node(item, list);
		this.list = newNode;
		this.size++;
	}


	@Override
	public T pop()
	{
		if (this.size == 0)
		{
			return null;
		}

		T item = this.list.item;
		this.list = this.list.next;
		this.size--;
		return item;
	}


	@Override
	public T peek()
	{
		if (this.size == 0)
		{
			return null;
		}

		return this.list.item;
	}


	@Override
	public Iterator<T> iterator()
	{
		return new StackListIterator();
	}


	private static double testStack(IStack<Integer> stack, int totalTrials, int testSampleSize)
	{
		double totalTime = 0;

		for (int i = 0; i < totalTrials; i++)
		{
			long startTime = System.nanoTime();

			for (int j = 0; j < testSampleSize; j++)
			{
				stack.push(j);
			}

			for (int j = 0; j < testSampleSize / 2; j++)
			{
				stack.pop();
			}

			for (int j = 0; j < testSampleSize / 2; j++)
			{
				stack.push(j);
			}

			for (int j = 0; j < testSampleSize; j++)
			{
				stack.pop();
			}

			long endTime = System.nanoTime();
			totalTime = totalTime + (endTime - startTime) / 1000000000.0;
		}

		return totalTime / totalTrials;
	}


	public static void main(String[] args)
	{
		int totalTrials = 50;

		int testSampleSize = 1000;

		double stackListTimeForN = testStack(new StackList<Integer>(), totalTrials, testSampleSize);
		double stackListTimeFor2N = testStack(new StackList<Integer>(), totalTrials, testSampleSize * 2);
		
		double shittyStackListForN = testStack(new ShittyStack<Integer>(), totalTrials, testSampleSize);
		double shittyStackListFor2N = testStack(new ShittyStack<Integer>(), totalTrials, testSampleSize * 2);

		double razaoStackList = stackListTimeFor2N / stackListTimeForN;
		double razaoShittyStackList = shittyStackListFor2N / shittyStackListForN;

		System.out.println("Razao Dobrada do ShittyStack: " + razaoShittyStackList + "\n" +  "Razao dobrada da StackList: " + razaoStackList);
	}
}
