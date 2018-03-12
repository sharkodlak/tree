package sharkodlak.tree;

import java.util.*;

public abstract class MutableTreeNode<T> implements TreeNode<T> {
	private T data;
	private List<TreeNode<T>> children;
	private TreeNode<T> parent;

	public MutableTreeNode(T data) {
		this.data = data;
	}

	@Override
	public TreeNode<T> add(TreeNode<T> child) {
		getChildrenInitialized().add(child);
		child.setParent(this);
		return this;
	}

	@Override
	public TreeNode<T> add(TreeNode<T> child, int childIndex) {
		getChildrenInitialized().add(childIndex, child);
		child.setParent(this);
		return this;
	}

	@Override
	public TreeNode<T> get(int childIndex) {
		return getChildrenInitialized().get(childIndex);
	}

	@Override
	public TreeNode<T> getAncestor(int index) {
		if (index < 0) {
			throw new IllegalArgumentException(String.format("Index can't be negative number, %d given.", index));
		}
		TreeNode<T> ancestor = this;
		while (index-- > 0 && ancestor != null) {
			ancestor = ancestor.getParent();
		}
		return index == -1 ? ancestor : null;
	}

	@Override
	public List<TreeNode<T>> getChildren() {
		return Collections.unmodifiableList(getChildrenInitialized());
	}

	private List<TreeNode<T>> getChildrenInitialized() {
		if (children == null) {
			synchronized(this) {
				if (children == null) {
					children = getList();
				}
			}
		}
		return children;
	}

	@Override
	public T getData() {
		return data;
	}

	@Override
	public int getLevel() {
		int level = 0;
		TreeNode<T> node = this;
		while ((node = node.getParent()) != null) {
			++level;
		}
		return level;
	}

	abstract protected List<TreeNode<T>> getList();

	public static class ArrayList<T> extends MutableTreeNode<T> {
		public ArrayList(T data) {
			super(data);
		}

		protected List<TreeNode<T>> getList() {
			return new java.util.ArrayList<TreeNode<T>>();
		}
	}

	public static class Vector<T> extends MutableTreeNode<T> {
		public Vector(T data) {
			super(data);
		}

		protected List<TreeNode<T>> getList() {
			return new java.util.Vector<TreeNode<T>>();
		}
	}

	@Override
	public int getNumberOfChildren() {
		return getChildrenInitialized().size();
	}

	@Override
	public TreeNode<T> getParent() {
		return parent;
	}

	@Override
	public TreeNode<T> getRoot() {
		TreeNode<T> root, node = this;
		do {
			root = node;
			node = node.getParent();
		} while (node != null);
		return root;
	}

	@Override
	public TreeNode<T> getSharedAncestor(TreeNode<T> node) {
		if (node == null || node == this) {
			return this;
		}
		return getSharedAncestorForUneqalLevelNodes(node);
	}

	private TreeNode<T> getSharedAncestorForUneqalLevelNodes(TreeNode<T> node) {
		TreeNode<T> thisBranchNode = this, otherBranchNode = node;
		int delta = this.getLevel() - node.getLevel();
		if (delta > 0) {
			thisBranchNode = thisBranchNode.getAncestor(delta);
		} else {
			otherBranchNode = otherBranchNode.getAncestor(-delta);
		}
		return getSharedAncestorForSameLevelNodes(thisBranchNode, otherBranchNode);
	}

	private TreeNode<T> getSharedAncestorForSameLevelNodes(TreeNode<T> thisBranchNode, TreeNode<T> otherBranchNode) {
		do {
			if (thisBranchNode == otherBranchNode) {
				return thisBranchNode;
			}
			thisBranchNode = thisBranchNode.getParent();
			otherBranchNode = otherBranchNode.getParent();
		} while (thisBranchNode != null && otherBranchNode != null);
		return null;
	}

	@Override
	public boolean hasAncestor(TreeNode<T> node) {
		if (node == null) {
			return false;
		}
		TreeNode<T> ancestor = this;
		do {
			if (ancestor == node) {
				return true;
			}
			ancestor = ancestor.getParent();
		} while (ancestor != null);
		return false;
	}

	@Override
	public boolean hasChild(TreeNode<T> child) {
		return getChildrenInitialized().contains(child);
	}

	@Override
	public boolean isLeaf() {
		return children == null || children.isEmpty();
	}

	@Override
	public TreeNode<T> remove(int childIndex) {
		TreeNode<T> child = getChildrenInitialized().remove(childIndex);
		child.removeParent();
		return child;
	}

	@Override
	public boolean remove(TreeNode<T> child) {
		boolean removed = removeWithoutParentCheck(child);
		if (removed) {
			child.removeParent();
		}
		return removed;
	}

	private boolean removeWithoutParentCheck(TreeNode<T> child) {
		return getChildrenInitialized().remove(child);
	}

	@Override
	public TreeNode<T> removeParent() {
		return setParent(null);
	}

	@Override
	public TreeNode<T> replace(TreeNode<T> newNode) {
		if (parent != null) {
			synchronized(parent) {
				if (parent != null) {
					parent.set(newNode, parent.getChildren().indexOf(this));
				}
			}
		}
		for (TreeNode<T> child : getChildrenInitialized()) {
			child.setParent(newNode);
		}
		return newNode;
	}

	@Override
	public TreeNode<T> set(TreeNode<T> child, int childIndex) {
		getChildrenInitialized().set(childIndex, child);
		child.setParent(this);
		return this;
	}

	@Override
	public TreeNode<T> setData(T data) {
		this.data = data;
		return this;
	}

	@Override
	public TreeNode<T> setParent(TreeNode<T> newParent) {
		if (newParent != null && !newParent.hasChild(this)) {
			synchronized(newParent) {
				if (!newParent.hasChild(this)) {
					newParent.add(this);
				}
			}
		}
		return setParentWithoutChildCheck(newParent);
	}

	private TreeNode<T> setParentWithoutChildCheck(TreeNode<T> newParent) {
		if (parent != null) {
			parent.remove(this);
		}
		parent = newParent;
		return this;
	}
}
