package sharkodlak.tree;

import java.util.*;

public class ImmutableTreeNode<T> implements TreeNode<T> {
	private final T data;
	private final List<TreeNode<T>> children;

	private static <T> TreeNode<T> getInstance(T data, List<TreeNode<T>> children) {
		return new ImmutableTreeNode<T>(data, children);
	}

	public ImmutableTreeNode(T data) {
		this(data, Collections.emptyList());
	}

	public ImmutableTreeNode(T data, List<TreeNode<T>> children) {
		this.data = data;
		this.children = ImmutableTreeNode.getChildrenClone(children);
	}

	public TreeNode<T> addChild(TreeNode<T> child) {
		List<TreeNode<T>> childrenClone = getChildrenClone();
		childrenClone.add(child);
		return getInstance(data, childrenClone);
	}

	public TreeNode<T> getChildAt(int childIndex) {
		return children.get(childIndex);
	}

	public List<TreeNode<T>> getChildren() {
		return Collections.unmodifiableList(children);
	}

	private List<TreeNode<T>> getChildrenClone() {
		return ImmutableTreeNode.<T>getChildrenClone(children);
	}

	private static <T> List<TreeNode<T>> getChildrenClone(List<TreeNode<T>> children) {
		List<TreeNode<T>> childrenClone = new ArrayList<>();
		Collections.copy(childrenClone, children);
		return childrenClone;
	}

	public T getData() {
		return data;
	}

	public int getNumberOfChildren() {
		return children == null ? 0 : children.size();
	}

	public boolean hasChild(TreeNode<T> child) {
		return children.contains(child);
	}

	public TreeNode<T> insert(TreeNode<T> child, int childIndex) {
		List<TreeNode<T>> childrenClone = getChildrenClone();
		childrenClone.add(childIndex, child);
		return getInstance(data, childrenClone);
	}

	public boolean isLeaf() {
		return children == null || children.isEmpty();
	}

	public TreeNode<T> remove(int childIndex) {
		List<TreeNode<T>> childrenClone = getChildrenClone();
		childrenClone.remove(childIndex);
		return getInstance(data, childrenClone);
	}

	public TreeNode<T> remove(TreeNode<T> child) {
		List<TreeNode<T>> childrenClone = getChildrenClone();
		childrenClone.remove(child);
		return getInstance(data, childrenClone);
	}
}
