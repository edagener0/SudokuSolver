package sudokusolver.aed.collections;

import java.util.Iterator;
import sudokusolver.aed.utils.TemporalAnalysisUtils;

import java.util.function.Function;
import java.util.function.Consumer;

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
			throw new UnsupportedOperationException("I do not support this.");
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


	/* public static void main(String[] args)
	{
		int totalTrials = 50;

		int testSampleSize = 1000;

		double stackListTimeForN = testStack(new StackList<Integer>(), totalTrials, testSampleSize);
		double stackListTimeFor2N = testStack(new StackList<Integer>(), totalTrials, testSampleSize * 2);
		
		double shittyStackListForN = testStack(new ShittyStack<Integer>(), totalTrials, testSampleSize);
		double shittyStackListFor2N = testStack(new ShittyStack<Integer>(), totalTrials, testSampleSize * 2);

		double razaoStackList = stackListTimeFor2N / stackListTimeForN;
		double razaoShittyStackList = shittyStackListFor2N / shittyStackListForN;

		String stackListTime = String.format("StackList:\nTime for N: %f seconds\nTime for 2N: %f seconds\nRazão dobrada: %f\n", 
								stackListTimeForN, stackListTimeFor2N, razaoStackList);

		String shittyStackTime = String.format("ShittyStack:\nTime for N: %f seconds\nTime for 2N: %f seconds\nRazão dobrada: %f\n", 
								shittyStackListForN, shittyStackListFor2N, razaoShittyStackList);
		
		System.out.println(stackListTime + shittyStackTime);
	} */

	public static void main(String[] args) {
		int iterations = 15;

		System.out.println("-- Test StackList method push --");
		TemporalAnalysisUtils.runDoublingRatioTest(
			(Integer n) -> {
				StackList<Integer> stack = new StackList<Integer>();
				for (int i = 0; i < n; i++) {
					stack.push(i);
				}
				return stack;
			},
			(StackList<Integer> stack) -> stack.push(1),
			iterations
		);

		System.out.println("\n-- Test StackList method pop --");
		TemporalAnalysisUtils.runDoublingRatioTest(
			(Integer n) -> {
				StackList<Integer> stack = new StackList<Integer>();
				for (int i = 0; i < n; i++) {
					stack.push(i);
				}
				return stack;
			},
			(StackList<Integer> stack) -> stack.pop(),
			iterations
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
			iterations
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
			iterations
		);
	}
}
