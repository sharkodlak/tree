package sharkodlak.tree;

import java.util.List;

public class ImmutableParentOrientedTreeNode<T> implements Immutable, ParentOrientedTreeNode<T> {
	private final T data;
	private final ParentOrientedTreeNode<T> parent;

	public ImmutableParentOrientedTreeNode(T data, ParentOrientedTreeNode<T> parent) {
		this.data = data;
		this.parent = parent;
	}

	@Override
	public ParentOrientedTreeNode<T> getAncestor(int index) {
		if (index < 0) {
			throw new IllegalArgumentException(String.format("Index can't be negative number, %d given.", index));
		}
		ParentOrientedTreeNode<T> ancestor = this;
		while (index-- > 0 && ancestor != null) {
			ancestor = ancestor.getParent();
		}
		return index == -1 ? ancestor : null;
	}

	@Override
	public T getData() {
		return data;
	}

	@Override
	public int getLevel() {
		int level = 0;
		ParentOrientedTreeNode<T> node = this;
		while ((node = node.getParent()) != null) {
			++level;
		}
		return level;
	}

	@Override
	public ParentOrientedTreeNode<T> getParent() {
		return parent;
	}

	@Override
	public ParentOrientedTreeNode<T> getRoot() {
		ParentOrientedTreeNode<T> root, node = this;
		do {
			root = node;
			node = node.getParent();
		} while (node != null);
		return root;
	}

	@Override
	public ParentOrientedTreeNode<T> getSharedAncestor(ParentOrientedTreeNode<T> node) {
		if (node == null || node == this) {
			return node;
		}
		return getSharedAncestorForUneqalLevelNodes(node);
	}

	private ParentOrientedTreeNode<T> getSharedAncestorForUneqalLevelNodes(ParentOrientedTreeNode<T> node) {
		ParentOrientedTreeNode<T> thisBranchNode = this, otherBranchNode = node;
		int delta = this.getLevel() - node.getLevel();
		if (delta > 0) {
			thisBranchNode = this.getAncestor(delta);
		} else {
			otherBranchNode = node.getAncestor(-delta);
		}
		return getSharedAncestorForSameLevelNodes(thisBranchNode, otherBranchNode);
	}

	private ParentOrientedTreeNode<T> getSharedAncestorForSameLevelNodes(ParentOrientedTreeNode<T> thisBranchNode,
		ParentOrientedTreeNode<T> otherBranchNode
	) {
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
	public boolean hasAncestor(ParentOrientedTreeNode<T> node) {
		if (node == null) {
			return false;
		}
		ParentOrientedTreeNode<T> ancestor = this;
		do {
			if (ancestor == node) {
				return true;
			}
			ancestor = ancestor.getParent();
		} while (ancestor != null);
		return false;
	}

	@Override
	public ParentOrientedTreeNode<T> removeParent() {
		return setParent(null);
	}

	@Override
	public ParentOrientedTreeNode<T> setData(T data) {
		return new ImmutableParentOrientedTreeNode<T>(data, parent);
	}

	@Override
	public ParentOrientedTreeNode<T> setParent(ParentOrientedTreeNode<T> newParent) {
		return new ImmutableParentOrientedTreeNode<T>(getData(), newParent);
	}
}
