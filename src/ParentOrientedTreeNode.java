package sharkodlak.tree;

public interface ParentOrientedTreeNode<T> extends DataWrapper<T> {
	/** Returns ancestor from desired generation if exists.
	 */
	public ParentOrientedTreeNode<T> getAncestor(int generations);

	/** Returns number of ancestor's generations from root to this node.
	 */
	public int getLevel();

	/** Returns the parent TreeNode of the instance.
	 */
	public ParentOrientedTreeNode<T> getParent();

	/** Returns the root TreeNode of the instance's tree.
	 */
	public ParentOrientedTreeNode<T> getRoot();

	/** Find shared ancestor if exists and returns it's instance.
	 */
	public ParentOrientedTreeNode<T> getSharedAncestor(ParentOrientedTreeNode<T> node);

	/** Returns true if instance has node as one of it's ancestors.
	 */
	public boolean hasAncestor(ParentOrientedTreeNode<T> node);

	/** Removes the instance from it's parent.
	 */
	public ParentOrientedTreeNode<T> removeParent();

	/** Sets the parent of the current instance to newParent.
	 **/
	public ParentOrientedTreeNode<T> setParent(ParentOrientedTreeNode<T> newParent);
}
