package sharkodlak.tree;

import java.util.List;

public interface TreeNode<T> {
	/** Adds child to the instance.
	 */
	public TreeNode<T> addChild(TreeNode<T> child);

	/** Returns the child TreeNode at index.
	 */
	public TreeNode<T> getChildAt(int childIndex);

	/** Returns the children of instance.
	 */
	public List<TreeNode<T>> getChildren();

	/** Returns instance's boxed data.
	 */
	public T getData();

	/** Returns the number of children TreeNodes instance contains.
	 */
	public int getNumberOfChildren();

	/** Adds child to the instance at index.
	 */
	public TreeNode<T> insert(TreeNode<T> child, int childIndex);

	/** Returns true if the instance has no children.
	 */
	public boolean isLeaf();

	/** Returns true if instance has node as it's child.
	 */
	public boolean hasChild(TreeNode<T> node);

	/** Removes child at index from the instance.
	 */
	public TreeNode<T> remove(int childIndex);

	/** Removes child from the instance.
	 */
	public TreeNode<T> remove(TreeNode<T> child);
}
