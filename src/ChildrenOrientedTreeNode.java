package sharkodlak.tree;

import java.util.List;

public interface ChildrenOrientedTreeNode<T> extends DataWrapper<T> {
	/** Adds child to the instance.
	 */
	public ChildrenOrientedTreeNode<T> add(ChildrenOrientedTreeNode<T> child);

	/** Adds child to the instance at index, shift children index from that position.
	 */
	public ChildrenOrientedTreeNode<T> add(ChildrenOrientedTreeNode<T> child, int childIndex);

	/** Returns the child node at index.
	 */
	public ChildrenOrientedTreeNode<T> get(int childIndex);

	/** Returns the children of instance.
	 */
	public List<? extends ChildrenOrientedTreeNode<T>> getChildren();

	/** Returns the number of children TreeNodes instance contains.
	 */
	public int getNumberOfChildren();

	/** Returns true if instance has node as it's child.
	 */
	public boolean hasChild(ChildrenOrientedTreeNode<T> node);

	/** Returns true if the instance has no children.
	 */
	public boolean isLeaf();

	/** Removes child at index from the instance.
	 */
	public ChildrenOrientedTreeNode<T> remove(int childIndex);

	/** Removes child from the instance.
	 */
	public boolean remove(ChildrenOrientedTreeNode<T> child);

	/** Replace existing child specified by index with child.
	 */
	public ChildrenOrientedTreeNode<T> set(ChildrenOrientedTreeNode<T> child, int childIndex);
}
