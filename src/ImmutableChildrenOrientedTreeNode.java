package sharkodlak.tree;

import java.util.*;

public class ImmutableChildrenOrientedTreeNode<T> implements Immutable, ChildrenOrientedTreeNode<T> {
	private final T data;
	private final List<ChildrenOrientedTreeNode<T>> children;

	private static <T> ChildrenOrientedTreeNode<T> getInstance(T data, List<ChildrenOrientedTreeNode<T>> children) {
		return new ImmutableChildrenOrientedTreeNode<T>(data, children);
	}

	public ImmutableChildrenOrientedTreeNode(T data) {
		this(data, Collections.emptyList());
	}

	public ImmutableChildrenOrientedTreeNode(T data, List<ChildrenOrientedTreeNode<T>> children) {
		this.data = data;
		this.children = ImmutableChildrenOrientedTreeNode.getChildrenClone(children);
	}

	public ChildrenOrientedTreeNode<T> add(ChildrenOrientedTreeNode<T> child) {
		List<ChildrenOrientedTreeNode<T>> childrenClone = getChildrenClone();
		childrenClone.add(child);
		return getInstance(data, childrenClone);
	}

	public ChildrenOrientedTreeNode<T> add(ChildrenOrientedTreeNode<T> child, int childIndex) {
		List<ChildrenOrientedTreeNode<T>> childrenClone = getChildrenClone();
		childrenClone.add(childIndex, child);
		return getInstance(data, childrenClone);
	}

	public ChildrenOrientedTreeNode<T> get(int childIndex) {
		return children.get(childIndex);
	}

	public List<ChildrenOrientedTreeNode<T>> getChildren() {
		return Collections.unmodifiableList(children);
	}

	private List<ChildrenOrientedTreeNode<T>> getChildrenClone() {
		return ImmutableChildrenOrientedTreeNode.<T>getChildrenClone(children);
	}

	private static <T> List<ChildrenOrientedTreeNode<T>> getChildrenClone(List<ChildrenOrientedTreeNode<T>> children) {
		List<ChildrenOrientedTreeNode<T>> childrenClone = new ArrayList<>();
		Collections.copy(childrenClone, children);
		return childrenClone;
	}

	public T getData() {
		return data;
	}

	public int getNumberOfChildren() {
		return children.size();
	}

	public boolean hasChild(ChildrenOrientedTreeNode<T> child) {
		return children.contains(child);
	}

	public boolean isLeaf() {
		return children.isEmpty();
	}

	public ChildrenOrientedTreeNode<T> remove(int childIndex) {
		List<ChildrenOrientedTreeNode<T>> childrenClone = getChildrenClone();
		childrenClone.remove(childIndex);
		return getInstance(data, childrenClone);
	}

	public ChildrenOrientedTreeNode<T> remove(ChildrenOrientedTreeNode<T> child) {
		List<ChildrenOrientedTreeNode<T>> childrenClone = getChildrenClone();
		childrenClone.remove(child);
		return getInstance(data, childrenClone);
	}

	public ChildrenOrientedTreeNode<T> set(ChildrenOrientedTreeNode<T> child, int childIndex) {
		List<ChildrenOrientedTreeNode<T>> childrenClone = getChildrenClone();
		childrenClone.set(childIndex, child);
		return getInstance(data, childrenClone);
	}

	public ChildrenOrientedTreeNode<T> set(ChildrenOrientedTreeNode<T> child, ChildrenOrientedTreeNode<T> childToReplace) {
		return set(child, children.indexOf(childToReplace));
	}

	public ChildrenOrientedTreeNode<T> setData(T data) {
		return getInstance(data, children);
	}
}
