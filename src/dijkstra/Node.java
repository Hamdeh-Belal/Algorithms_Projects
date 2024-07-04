/**
 * 
 */
package dijkstra;
/**
 * 
 */

public class Node<T extends Comparable<T>> {
	private Node<T> next;
	private T data;

	public Node() {
		super();
	}

	public Node(T data) {
		super();
		setData(data);
	}

	@Override
	public String toString() {
//		return "Node [next=" + next + ", data=" + data + "]";
		return data.toString();
	}

	public Node<T> getNext() {
		return next;
	}

	public void setNext(Node<T> next) {
		this.next = next;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}

