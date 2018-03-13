package sharkodlak.tree;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import sharkodlak.tree.*;

public class MutableTreeNodeTest {
	protected <T> TreeNode<T> getTreeNode() {
		return getTreeNode(null);
	}

	protected <T> TreeNode<T> getTreeNode(T data) {
		return new MutableTreeNode.ArrayList<T>(data);
	}

	@Test
	void testAdd() {
		TreeNode<Object> root = getTreeNode(),
			first = getTreeNode(),
			second = getTreeNode(),
			third = getTreeNode();
		root.add(first)
			.add(second)
			.add(third);
		List children = root.getChildren();
		assertEquals(0, children.indexOf(first));
		assertEquals(1, children.indexOf(second));
		assertEquals(2, children.indexOf(third));
	}

	@Test
	public void testAddInsert() {
		TreeNode<Object> root = getTreeNode(),
			first = getTreeNode(),
			second = getTreeNode(),
			third = getTreeNode();
		root.add(third)
			.add(first, 0)
			.add(second, 1);
		List children = root.getChildren();
		assertEquals(first, children.get(0));
		assertEquals(second, children.get(1));
		assertEquals(third, children.get(2));
	}

	@Test
	public void testGet() {
		TreeNode<Object> root = getTreeNode(),
			first = getTreeNode(),
			second = getTreeNode(),
			third = getTreeNode();
		root.add(first)
			.add(second)
			.add(third);
		assertEquals(first, root.get(0));
		assertEquals(second, root.get(1));
		assertEquals(third, root.get(2));
	}

	@Test
	public void testGetAncestor() {
		TreeNode<Object> root = getTreeNode(),
			firstLevel = getTreeNode(),
			secondLevel = getTreeNode(),
			thirdLevel = getTreeNode();
		root.add(firstLevel);
		firstLevel.add(secondLevel);
		secondLevel.add(thirdLevel);
		assertEquals(thirdLevel, thirdLevel.getAncestor(0));
		assertEquals(secondLevel, thirdLevel.getAncestor(1));
		assertEquals(firstLevel, thirdLevel.getAncestor(2));
		assertEquals(root, thirdLevel.getAncestor(3));
		assertNull(thirdLevel.getAncestor(4));
		assertThrows(IllegalArgumentException.class, ()->thirdLevel.getAncestor(-1));
	}

}
