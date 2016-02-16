package SortLinkedList;

import java.io.Serializable;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

public class SortLinkedList<T extends Comparable> implements Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -333129570257240981L;
	private Node<T> head;
	private Node<T> tail;
	private ArrayBlockingQueue<T> queue;
	private int size;
	
	
	public SortLinkedList(int size)
	{
		this.size = size;
		head = tail = null;
		queue = new ArrayBlockingQueue<T>(size);
	}
	public void push(T value)
	{
		if(queue.size() == size)
		{
			T headValue = queue.poll();
			pop(headValue);
		}
		queue.offer(value);
		if(head == null)
		{
			head = new Node<T>();
			head.setPreNode(null);
			tail = new Node<T>();
			tail.setNextNode(null);
			Node<T> newNode = new Node<T>();
			newNode.setValue(value);
			newNode.setNextNode(tail);
			newNode.setPreNode(head);
			tail.setPreNode(newNode);
			head.setNextNode(newNode);
		}
		else
		{
			Node<T> newNode = new Node<T>();
			newNode.setValue(value);
			Node<T> curNode = head.getNextNode();
			Node<T> nextNode = curNode.getNextNode();
			
			while(nextNode != tail)
			{
				if(curNode.getValue().compareTo(value) < 0
							&& nextNode.getValue().compareTo(value) > 0)
				{
					newNode.setNextNode(nextNode);
					newNode.setPreNode(curNode);
					curNode.setNextNode(newNode);
					nextNode.setPreNode(newNode);
					break;
				}
				
				curNode = nextNode;
				nextNode = nextNode.getNextNode();
			}
			if(curNode.getPreNode() == head )
			{
				if(curNode.getValue().compareTo(value) > 0)
				{
					newNode.setNextNode(curNode);
					newNode.setPreNode(head);
					head.setNextNode(newNode);
					curNode.setPreNode(newNode);
				}
			}
			if(nextNode == tail)
			{
				if(curNode.getValue().compareTo(value) < 0)
				{
					newNode.setNextNode(tail);
					newNode.setPreNode(curNode);
					tail.setPreNode(newNode);
					curNode.setNextNode(newNode);
				}
			}
		}
	}
	
	public void pop(T value)
	{
		Node<T> curNode = head.getNextNode();
		while(curNode != tail)
		{
			if(curNode.getValue().equals(value))
			{
				Node<T> nextNode = curNode.getNextNode();
				Node<T> preNode = curNode.getPreNode();
				preNode.setNextNode(nextNode);
				nextNode.setPreNode(preNode);
				break;
			}
			curNode = curNode.getNextNode();
		}
	}
	
	private Node getHead() {
		return head;
	}
	
	private void setHead(Node head) {
		this.head = head;
	}
	
	public int size()
	{
		return queue.size();
	}
	
	public T get(int index)
	{
		checkElementIndex(index);
		return node(index).getValue();
	}
	
	private void checkElementIndex(int index)
	{
		if(index <0 || index >= this.size())
		{
			throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
		}
	}
	
	private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+this.size();
    }
	
	private Node<T> node(int index)
	{
		checkElementIndex(index);
		Node<T> curNode = head.getNextNode();
		for(int i = 0; i < index; i++)
		{
			curNode = curNode.getNextNode();
		}
		return curNode;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SortLinkedList<Double> list = new SortLinkedList<Double>(4);
		list.push(3.0);
		list.push(6.0);
		list.push(7.0);
		list.push(8.0);
		list.push(9.0);
		list.push(10.0);
		list.push(11.0);
		list.push(12.0);
		list.push(13.0);
		list.push(14.0);
		for(int i = 0; i < list.size(); i++)
		{
			System.out.println(list.get(i));
		}
	}

}

class Node<T extends Comparable> implements Serializable{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6915392610347459089L;
	private T value;
	private Node<T> nextNode;
	private Node<T> preNode;
	
	public T getValue() {
		return value;
	}
	public Node<T> getNextNode() {
		return nextNode;
	}
	public void setValue(T value) {
		this.value = value;
	}
	public void setNextNode(Node<T> nextNode) {
		this.nextNode = nextNode;
	}
	public Node<T> getPreNode() {
		return preNode;
	}
	public void setPreNode(Node<T> preNode) {
		this.preNode = preNode;
	}
	@SuppressWarnings("unchecked")
	public int compareTo(Node<T> node)
	{
		return this.value.compareTo(node.value);
	}
	
}
