package sharkodlak.tree;

import java.util.List;

public interface TreeNode<T> {
	/** Adds child to the instance.
	 */
	public TreeNode<T> addChild(TreeNode<T> child);

	/** Returns ancestor from desired generation if exists.
	 */
	public TreeNode<T> getAncestor(int generations);

	/** Returns the child TreeNode at index.
	 */
	public TreeNode<T> getChildAt(int childIndex);

	/** Returns the children of instance.
	 */
	public List<TreeNode<T>> getChildren();

	/** Returns instance's boxed data.
	 */
	public T getData();

	/** Returns number of ancestor's generations.
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

	/** Adds child to the instance at index.
	 */
	public TreeNode<T> insert(TreeNode<T> child, int childIndex);

	/** Returns true if the instance has no children.
	 */
	public boolean isLeaf();

	/** Returns true if instance has node as one of it's ancestors.
	 */
	public boolean hasAncestor(TreeNode<T> node);

	/** Returns true if instance has node as it's child.
	 */
	public boolean hasChild(TreeNode<T> node);

	/** Syntactic sugar to node.hasAncestor(this).
	 */
	public boolean hasDescendant(TreeNode<T> node);

	/** Removes child at index from the instance.
	 */
	public TreeNode<T> remove(int childIndex);

	/** Removes child from the instance.
	 */
	public boolean remove(TreeNode<T> child);

	/** Removes the instance from it's parent.
	 */
	public TreeNode<T> removeParent();

	/** Sets instance's data.
	 */
	public TreeNode<T> setData(T data);

	/** Sets the parent of the current instance to newParent.
	 **/
	public TreeNode<T> setParent(TreeNode<T> newParent);
}
