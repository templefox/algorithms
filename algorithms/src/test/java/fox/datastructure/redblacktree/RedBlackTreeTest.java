package fox.datastructure.redblacktree;

import static org.junit.Assert.*;

import java.awt.Robot;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;
import java.util.function.Consumer;

import org.junit.Test;

import fox.datastructure.redblacktree.RedBlackTree.Color;
import fox.datastructure.redblacktree.RedBlackTree.Node;

public class RedBlackTreeTest {
	@Test
	public void basicTest() {
		try {
			RedBlackTree<Integer, String> tree = new RedBlackTree<>();
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void leftRotateTest() {
		RedBlackTree<Integer, Integer> tree = new RedBlackTree<>();

		Node<Integer, Integer> root = new Node<>();
		Node<Integer, Integer> xnode = new Node<>();
		Node<Integer, Integer> ynode = new Node<>();
		Node<Integer, Integer> anode = new Node<>();
		Node<Integer, Integer> bnode = new Node<>();
		Node<Integer, Integer> cnode = new Node<>();

		tree.root = root;
		tree.root.setLeft(xnode);
		xnode.setLeft(anode);
		xnode.setRight(ynode);
		ynode.setLeft(bnode);
		ynode.setRight(cnode);

		tree.leftRotate(xnode);

		assertTrue(root.getLeft() == ynode 
				& ynode.getLeft() == xnode 
				& ynode.getRight() == cnode
				& xnode.getLeft() == anode 
				& xnode.getRight() == bnode
		);
	}
	
	@Test
	public void rightRotateTest(){
		RedBlackTree<Integer, Integer> tree = new RedBlackTree<>();

		Node<Integer, Integer> xnode = new Node<>();
		Node<Integer, Integer> ynode = new Node<>();
		Node<Integer, Integer> anode = new Node<>();
		Node<Integer, Integer> bnode = new Node<>();
		Node<Integer, Integer> cnode = new Node<>();

		tree.root = ynode;
		ynode.setLeft(xnode);
		xnode.setLeft(anode);
		xnode.setRight(bnode);
		ynode.setRight(cnode);
		
		tree.rightRotate(ynode);
		
		assertTrue(tree.root == xnode);
		
		assertTrue(xnode.getLeft() == anode
				& xnode.getRight() == ynode
				& ynode.getLeft() == bnode
				& ynode.getRight() == cnode
				);
	}
	
	@Test
	public void insertTest(){
		RedBlackTree<Integer, Integer> tree = new RedBlackTree<>();
		
		tree.insert(1, 1);
		
		assertEquals(tree.root.key, (Integer)1);
		assertEquals(RedBlackTree.Color.Black, tree.root.color);

		tree.insert(5, 1);
		tree.insert(19, 1);
		tree.insert(9, 1);
		tree.insert(10, 1);
		tree.insert(16, 1);
		tree.insert(7, 1);
		tree.insert(1, 1);
		tree.insert(16, 1);
		tree.insert(8, 1);
		tree.insert(14, 1);
		
		Random r = new Random();
		for(int i=0;i<100;i++){
			int xx = r.nextInt(20);
			tree.insert(xx,1);
			System.out.println(xx);
		}
		
		String x = tree.toTreeString();
		System.out.println(x);

		assertEquals(RedBlackTree.Color.Black, tree.root.color);
		
		foreachNode(tree.root, node -> {
			if(node.color == RedBlackTree.Color.Red){
				assertEquals(Color.Black, node.left.color);
				assertEquals(Color.Black, node.right.color);
			}
		});
		
	}
	
	private <K extends Comparable<K>,V> void foreachNode(Node<K, V> root, Consumer<Node<K, V>> f){
		if(RedBlackTree.IsNotNil(root)){
			f.accept(root);
			foreachNode(root.left, f);
			foreachNode(root.right, f);
		}
	}
}
