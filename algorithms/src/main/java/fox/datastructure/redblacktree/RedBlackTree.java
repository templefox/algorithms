package fox.datastructure.redblacktree;

import java.util.LinkedList;
import java.util.Queue;

public class RedBlackTree<K extends Comparable<K>, V> {
	private static Node<Object, Object> nil;

	static {
		Node<Object, Object> sentinel = new Node<Object, Object>();
		sentinel.color = Color.Black;
		sentinel.setRight(sentinel);
		sentinel.setLeft(sentinel);
		nil = sentinel;

	}

	Node<K, V> root;

	public RedBlackTree() {
		root = (Node<K, V>) nil;
	}

	static enum Color {
		Red, Black
	}
	
	public void delete(Node<K, V> z){
		Node<K, V> y;
		Node<K, V> x;
		if (IsNotNil(z.getLeft())&&IsNotNil(z.getRight())) {
			y = successor(z);
		}else {
			y = z;
		}
		
		if(IsNotNil(y.getLeft())){
			x = y.getLeft();
		}else {
			x = y.getRight();
		}
		
		if(!IsNotNil(y.getParent())){
			root = x;
		}else{
			if(y.isLeftChild()){
				y.getParent().setLeft(x);
			}else {
				y.getParent().setRight(x);
			}
		}
		
		if(y!=z){
			z.key = y.key;
			z.value = y.value;
		}
		
		if(y.color == Color.Black){
			deleteFixup(x);
		}
	}
	
	private void deleteFixup(Node<K, V> x) {
		// TODO Auto-generated method stub
		
	}

	public V search(K key) {
		return search0(root,key).value;
	}
	
	public V minimum(){
		return minimum(root).value;
	}
	
	Node<K, V> minimum(Node<K, V> node){
		while (IsNotNil(node.left)) {
			node = node.left;
		}
		return node;
	}
	
	public V maximum(){
		Node<K, V> node = root;
		while (IsNotNil(node.right)) {
			node = node.right;
		}
		return node.value;
	}
	
	Node<K, V> successor(Node x){
		if(IsNotNil(x.right)){
			return minimum(x);
		}
		
		Node<K, V> y = x.getParent();
		while (IsNotNil(y)&&x.isRightChild()) {
			x = y;
			y = y.getParent();
		}
		return y;
	}
	
	private Node<K,V> search0(Node<K,V> node, K key){
		if(!IsNotNil(node)||key.equals(node.key)){
			return node;
		}
		
		if(key.compareTo(node.key)<0){
			return search0(node.left, key);
		}
		else {
			return search0(node.right, key);
		}
	}

	public void insert(K key, V value) {
		Node<K, V> y = (Node<K, V>) nil;

		Node<K, V> z = new Node<>();
		z.key = key;
		z.value = value;
		z.color = Color.Red;
		z.left = y;
		z.right = y;
		z.parent = y;

		Node<K, V> x = root;
		while (IsNotNil(x)) {
			y = x;
			if (z.key.compareTo(x.key) < 0) {
				x = x.getLeft();
			} else {
				x = x.getRight();
			}
		}

		if (IsNotNil(y)) {
			if (z.key.compareTo(y.key) < 0) {
				y.setLeft(z);
			} else {
				y.setRight(z);
			}
		} else {
			root = z;
		}
		insertFixup(z);
	}

	private void insertFixup(Node<K, V> z) {
		while (z.getParent().color == Color.Red) {
			Node<K, V> y = z.getParent().getNeighbor();
			if (y.color == Color.Red) {
				z.getParent().color = Color.Black;
				y.color = Color.Black;
				z.getParent().getParent().color = Color.Red;
				z = z.getParent().getParent();
				continue;
			}
			if (z.getParent().isLeftChild()) {
				if (z.isRightChild()) {
					z = z.getParent();
					leftRotate(z);
				} else if (z.isLeftChild()) {
					z.getParent().color = Color.Black;
					z.getParent().getParent().color = Color.Red;
					rightRotate(z.getParent().getParent());
				}
			}else {
				if (z.isLeftChild()) {
					z = z.getParent();
					rightRotate(z);
				} else if (z.isRightChild()) {
					z.getParent().color = Color.Black;
					z.getParent().getParent().color = Color.Red;
					leftRotate(z.getParent().getParent());
				}
			}
		}
		root.color = Color.Black;
	}

	/**
	 * From x x(A,y) y(B,C) To y y(x,C) x(A,B)
	 * 
	 * @param x
	 */
	void leftRotate(Node<K, V> x) {
		assert IsNotNil(x.getRight());
		Node<K, V> y = x.getRight();

		// B in position
		x.setRight(y.getLeft());

		// Y to root
		if (x == root) {
			root = y;
		} else if (x.isLeftChild()) {
			x.getParent().setLeft(y);
		} else if (x.isRightChild()) {
			x.getParent().setRight(y);
		}

		// x in position
		y.setLeft(x);
	}

	/**
	 * From y y(x,C) x(A,B) To x x(A,y) y(B,C)
	 * 
	 * @param x
	 */
	void rightRotate(Node<K, V> y) {
		assert IsNotNil(y.getLeft());
		Node<K, V> x = y.getLeft();

		// B in postion
		y.setLeft(x.getRight());

		// X to root
		if (y == root) {
			root = x;
		} else if (y.isRightChild()) {
			y.getParent().setRight(x);
		} else if (y.isLeftChild()) {
			y.getParent().setLeft(x);
		}

		// y in position
		x.setRight(y);
	}

	public String toTreeString() {
		Queue<Node<K, V>> q1 = new LinkedList<>();
		Queue<Node<K, V>> q2 = new LinkedList<>();
		StringBuilder sb = new StringBuilder();
		q1.add(root);

		while (!q1.isEmpty() || !q2.isEmpty()) {
			while (!q1.isEmpty()) {
				Node<K, V> node = q1.poll();
				if (IsNotNil(node)) {
					sb.append(node.key);
					sb.append(node.color.toString());
					q2.add(node.getLeft());
					q2.add(node.getRight());
				}
			}
			sb.append("\n");

			while (!q2.isEmpty()) {
				Node<K, V> node = q2.poll();
				if (IsNotNil(node)) {
					sb.append(node.key);
					sb.append(node.color.toString());
					q1.add(node.getLeft());
					q1.add(node.getRight());
				}
			}
			sb.append("\n");
		}

		return sb.toString();
	}

	static boolean IsNotNil(Node<?, ?> node) {
		return node != nil;
	}

	static class Node<K, V> {
		K key;
		V value;
		Node<K, V> left;
		Node<K, V> right;
		Node<K, V> parent;
		Color color;

		public void setLeft(Node<K, V> newLeft) {
			left = newLeft;
			newLeft.parent = this;
		}

		public void setRight(Node<K, V> newRight) {
			right = newRight;
			newRight.parent = this;
		}

		public K getKey() {
			return key;
		}

		public V getValue() {
			return value;
		}

		public Node<K, V> getLeft() {
			return left;
		}

		public Node<K, V> getRight() {
			return right;
		}

		public Node<K, V> getParent() {
			return parent;
		}

		public Color getColor() {
			return color;
		}

		public boolean isLeftChild() {
			return this == this.getParent().getLeft();
		}

		public boolean isRightChild() {
			return this == this.getParent().getRight();
		}

		public Node<K, V> getNeighbor() {
			return isLeftChild() ? this.getParent().getRight() : this.getParent().getLeft();
		}
	}
}
