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
		size++;
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
		size--;
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


	// função main utilizada para testes, coloque os testes efetuados aqui
	public static void main(String[] args)
	{
		long start = System.nanoTime();
		StackList<Integer> stack = new StackList<Integer>();
		stack.push(3);
		stack.push(5);
		// StackList<Integer> stack2 = stack.shallowCopy();
		stack.push(6);
		stack.push(7);
		stack.pop();
		System.out.println(stack.peek());
		long end = System.nanoTime();
		System.out.println("Time Elapsed: " + (end - start) / 1000000000.0);
	}
}
