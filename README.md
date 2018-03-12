# tree


# Children Oriented Tree

Add child to parent

	parent

	parentClone = parent.addChild(child); // Store parent clone, because it's new instance

	parentClone
		child

	Original parent will be garbage collected if there is no other reference to him.

More effective way to build tree is leaf to root traversal in case children oriented tree is used.

	child

	List<ChildrenOrientedTreeNode> parentChildren = new List(child);
	parent = new ChildrenOrientedTreeNode(parentChildren);

	parent
		child

	List<ChildrenOrientedTreeNode> grandParentChildren = new List(parent);
	grandParent = new ChildrenOrientedTreeNode(grandParentChildren);

	grandParent
		parent
			child

Add grandchild

	grandParent
		parent

	parentClone = parent.addChild(child); // Developer have to update all parents in reverse order all way to root
	grandParentClone = grandParent.set(parentClone, parent);

	grandParentClone
		parentClone
			child

	Original grandParent and parent will be garbage collected if there is no other reference to them.


# Parent Oriented Tree

Set parent

	child

	childClone = child.setParent(parent); // Store child clone because it's new instance due to immutability

	parent
		childClone

Create nodes effectively by root to leaf traversal

	grandParent

	parent = new ParentOrientedTreeNode(grandParent);

	grandParent
		parent

	child = new ParentOrientedTreeNode(parent);

	grandParent
		parent
			child

Set parent and grand parent

	child

	parentClone = parent.setParent(grandParent);
	childClone = child.setParent(parentClone);

	grandParent
		parentClone
			childClone


# Full Tree Node

Update node in between

	nodeA
		nodeB
			nodeD
				nodeH
					nodeP
					nodeQ
				nodeI
					nodeR
					nodeS
			nodeE
				nodeJ
					nodeT
					nodeU
				nodeK
					nodeV
					nodeW
		nodeC
			nodeF
				nodeL
					nodeX
					nodeY
				nodeM
					nodeZ
					node1
			nodeG
				nodeN
					node2
					node3
				nodeO
					node4
					node5

	nodeDInitialClone = nodeD.setData(newData);
	then update all children and their's children up to leaf nodes with new parents (L1 Clones)
	update parent and it's descendants (L2 Clones) and repeat for each level (L3 Clones)
	// every update in single tree node rebuilds whole tree

	nodeAL3Clones
		nodeBL2Clone
			nodeDInitialClone
				nodeHL1Clone
					nodePL1Clone
					nodeQL1Clone
				nodeIL1Clone
					nodeRL1Clone
					nodeSL1Clone
			nodeEL2Clone
				nodeJL2Clone
					nodeTL2Clone
					nodeUL2Clone
				nodeKL2Clone
					nodeVL2Clone
					nodeWL2Clone
		nodeCL3Clones
			nodeFL3Clones
				nodeLL3Clones
					nodeXL3Clones
					nodeYL3Clones
				nodeML3Clones
					nodeZL3Clones
					node1L3Clones
			nodeGL3Clones
				nodeNL3Clones
					node2L3Clones
					node3L3Clones
				nodeOL3Clones
					node4L3Clones
					node5L3Clones
