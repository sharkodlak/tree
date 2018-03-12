package sharkodlak.tree;

public class ImmutableTreeNode<T> implements Immutable, TreeNode<T> {
	private final T data;
	private final ImmutableChildrenOrientedTreeNode<ImmutableTreeNode<T>> childrenOrientedNode;
	private final ImmutableParentOrientedTreeNode<ImmutableTreeNode<T>> parentOrientedNode;
}
