package sudokusolver.aed.collections;

import java.util.Iterator;
import sudokusolver.aed.utils.TemporalAnalysisUtils;


public class StackList<T> implements IStack<T>
{
	private static class Node<T>
	{
		private T item;
		private Node<T> next;

		Node(T item, Node<T> next)
		{
			this.item = item;
			this.next = next;
		}
	}


	private class StackListIterator implements Iterator<T>
	{
		private Node<T> iterator;

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
			throw new UnsupportedOperationException("Removal Operation not supported for Iterator.");
		}
	}


	private Node<T> list;

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
		Node<T> newNode = new Node<T>(item, list);
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


	public static void main(String[] args) {
		final int ITERATIONS_STACKLIST = 15;
		final int ITERATIONS_SHITTYSTACK = 8;

		System.out.println("-- Test StackList method push --");
		TemporalAnalysisUtils.runDoublingRatioTest(
			(Integer n) -> {
				StackList<Integer> stack = new StackList<Integer>();
				for (int i = 0; i < 100; i++) {
					stack.push(i);
				}
				return stack;
			},
			(StackList<Integer> stack) -> stack.push(1),
			ITERATIONS_STACKLIST
		);

		System.out.println("\n-- Test StackList method pop --");
		TemporalAnalysisUtils.runDoublingRatioTest(
			(Integer n) -> {
				StackList<Integer> stack = new StackList<Integer>();
				for (int i = 0; i < 100; i++) {
					stack.push(i);
				}
				return stack;
			},
			(StackList<Integer> stack) -> stack.pop(),
			ITERATIONS_STACKLIST
		);

		System.out.println("-- Test ShittyStack method push --");
		TemporalAnalysisUtils.runDoublingRatioTest(
			(Integer n) -> {
				ShittyStack<Integer> stack = new ShittyStack<Integer>();
				for (int i = 0; i < n; i++) {
					stack.push(i);
				}
				return stack;
			},
			(ShittyStack<Integer> stack) -> stack.push(1),
			ITERATIONS_SHITTYSTACK
		);

		System.out.println("\n-- Test ShittyStack method pop --");
		TemporalAnalysisUtils.runDoublingRatioTest(
			(Integer n) -> {
				ShittyStack<Integer> stack = new ShittyStack<Integer>();
				for (int i = 0; i < n; i++) {
					stack.push(i);
				}
				return stack;
			},
			(ShittyStack<Integer> stack) -> stack.pop(),
			ITERATIONS_SHITTYSTACK
		);
	}
}
