package sharkodlak.tree;

import java.util.List;

public interface TreeNode<T> extends DataWrapper<T> {
	/** Adds child to the instance.
	 */
	public TreeNode<T> add(TreeNode<T> child);

	/** Adds child to the instance at index, shift children index from that position.
	 */
	public TreeNode<T> add(TreeNode<T> child, int childIndex);

	/** Returns the child node at index.
	 */
	public TreeNode<T> get(int childIndex);

	/** Returns ancestor from desired generation if exists.
	 */
	public TreeNode<T> getAncestor(int generations);

	/** Returns the children of instance.
	 */
	public List<? extends TreeNode<T>> getChildren();

	/** Returns number of ancestor's generations from root to this node.
	 */
	public int getLevel();

	/** Returns the number of children TreeNodes instance contains.
	 */
	public int getNumberOfChildren();

	/** Returns the parent TreeNode of the instance.
	 */
	public TreeNode<T> getParent();

	/** Returns the root TreeNode of the instance's tree.
	 */
	public TreeNode<T> getRoot();

	/** Find shared ancestor if exists and returns it's instance.
	 */
	public TreeNode<T> getSharedAncestor(TreeNode<T> node);

	/** Returns true if instance has node as one of it's ancestors.
	 */
	public boolean hasAncestor(TreeNode<T> node);

	/** Returns true if instance has node as it's child.
	 */
	public boolean hasChild(TreeNode<T> node);

	/** Returns true if the instance has no children.
	 */
	public boolean isLeaf();

	/** Removes child at index from the instance.
	 */
	public TreeNode<T> remove(int childIndex);

	/** Removes child from the instance.
	 */
	public boolean remove(TreeNode<T> child);

	/** Removes the instance from it's parent.
	 */
	public TreeNode<T> removeParent();

	/** Syntactic sugar to replace existing node with new node.
	 */
	public TreeNode<T> replace(TreeNode<T> newNode);

	/** Replace existing child specified by index with child.
	 */
	public TreeNode<T> set(TreeNode<T> child, int childIndex);

	/** Sets the parent of the current instance to newParent.
	 **/
	public TreeNode<T> setParent(TreeNode<T> newParent);
}
