package com.company;


/**
 * Implements the {@link Dictionary} interface as a binary search tree
 * from Chapter 12 of <i>Introduction to Algorithms</i>, Second
 * edition.  Objects inserted into a binary search tree must implement
 * the <code>Comparable</code> interface.
 *
 * <p>
 *
 * When extending this class, you must instantiate a new
 * <code>nil</code> of the proper type and override constructors along
 * with the other methods.  See RedBlackTree for an example.
 */

public class BinarySearchTree<E> extends BinaryTree<E> {
    /**
     * Creates a binary search tree with just a <code>nil</code>,
     * which is the root.
     */
    public BinarySearchTree()
    {
	super();
    }

    /**
     * Searches the subtree rooted at a given node for a node with a
     * given key.  Works recursively.
     *
     * @param x Root of the subtree.
     * @param k The key being searched for.
     * @return A reference to a <code>Node</code> object with key
     * <code>k</code> if such a node exists, or a reference to the
     * sentinel <code>nil</code> if no node has key <code>k</code>.
     * The <code>Node</code> class is opaque to methods outside this
     * class.
     */
    protected Node search(Node x, Comparable k)
    {
	int c;

	if (x == nil || (c = k.compareTo(x.data)) == 0)
	    return x;

	if (c < 0)
	    return search(x.left, k);
	else
	    return search(x.right, k);
    }

    /**
     * Searches the tree for a node with a given key.  Works
     * iteratively.
     *
     * @param k The key being searched for.
     * @return A reference to a <code>Node</code> object with key
     * <code>k</code> if such a node exists, or a reference to the
     * sentinel <code>nil</code> if no node has key <code>k</code>.
     * The <code>Node</code> class is opaque to methods outside this
     * class.
     */
    public Node iterativeSearch(Comparable<Comparable> k)
    {
	Node x = root;
	int c;

	while (x != nil && (c = k.compareTo(x.data)) != 0) {
	    if (c < 0)
		x = x.left;
	    else
		x = x.right;
	}

	return x;
    }

    /**
     * Returns the node with the minimum key in the tree.
     *
     * @return A <code>Node</code> object with the minimum key in the
     * tree, or the sentinel <code>nil</code> if the tree is empty.
     */
    public Node minimum()
    {
	return treeMinimum(root);
    }

    /**
     * Returns the node with the minimum key in the subtree rooted at
     * a node.
     *
     * @param x Root of the subtree.
     * @return A <code>Node</code> object with the minimum key in the
     * tree, or the sentinel <code>nil</code> if the tree is empty.
     */
    protected Node treeMinimum(Node x)
    {
	while (x.left != nil)
	    x = x.left;

	return x;
    }

    /**
     * Returns the node with the maximum key in the tree.
     *
     * @return A <code>Node</code> object with the maximum key in the
     * tree, or the sentinel <code>nil</code> if the tree is empty.
     */
    public Node maximum()
    {
	return treeMaximum(root);
    }

    /**
     * Returns the node with the maximum key in the subtree rooted at
     * a node.
     *
     * @param x Root of the subtree.
     * @return A <code>Node</code> object with the maximum key in the
     * tree, or the sentinel <code>nil</code> if the tree is empty.
     */
    protected Node treeMaximum(Node x)
    {
	while (x.right != nil)
	    x = x.right;

	return x;
    }

    /**
     * Returns the successor of a given node in an inorder walk of the
     * tree.
     *
     * @param node The node whose successor is returned.
     * @return If <code>node</code> has a successor, it is returned.
     * Otherwise, return the sentinel <code>nil</code>.
     */
    public Node successor(Node node)
    {
	Node x = node;

	if (x.right != nil)
	    return treeMinimum(x.right);

	Node y = x.parent;
	while (y != nil && x == y.right) {
	    x = y;
	    y = y.parent;
	}

	return y;
    }

    /**
     * Returns the predecessor of a given node in an inorder walk of
     * the tree.
     *
     * @param node The node whose predecessor is returned.
     * @return If <code>node</code> has a predecessor, it is returned.
     * Otherwise, return the sentinel <code>nil</code>.
     */
    public Node predecessor(Node node)
    {
	Node x = node;

	if (x.left != nil)
	    return treeMaximum(x.left);

	Node y = x.parent;
	while (y != nil && x == y.left) {
	    x = y;
	    y = y.parent;
	}

	return y;
    }

    /**
     * Inserts data into the tree, creating a new node for this data.
     *
     * @param data Data to be inserted into the tree.
     * @return A reference to the <code>Node</code> object created.
     * The <code>Node</code> class is opaque to methods outside this
     * class.
     */
    public Object insert(Comparable data)
    {
	Node z = new Node(data);
	treeInsert(z);

	return z;
    }

    /**
     * Inserts a node into the tree.
     *
     * @param z The node to insert.
     */
    protected void treeInsert(Node z)
    {
	    Node y = nil;
	    Node x = root;
        while (x != nil) {
            y = x;
            if (z.compareTo(x) <= 0) {
                x = x.left;
            }
            else {
                x = x.right;
            }
        }

        z.parent = y;
        if (y == nil) {
            root = z;        // the tree had been empty
        }else {
            if (z.compareTo(y) <= 0) {
                y.left = z;
            }else {
                y.right = z;
            }
	}
    }

    /**
     * Removes a node from the tree.
     *
     * @param node The node to be removed.
     * @throws DeleteSentinelException if there is an attempt to
     * delete the sentinel <code>nil</code>.
     * @throws ClassCastException if <code>node</code> does not
     * reference a <code>Node</code> object.
     */
    public void delete(Object node)
    {
	Node z = (Node) node;

	// Make sure that there is no attempt to delete the sentinel
	// nil.
	if (z == nil)
	    throw new DeleteSentinelException();

	Node x;			// Replaces z as the subtree's root

	if (z.left == nil)
	    x = z.right;
	else
	    if (z.right == nil)
		    x = z.left;
	    else {        // neither child is nil
            x = successor(z); // replace with next item
            delete(x);    // Free x from its current position
            // Splice out z and put x in its place by fixing links
            // with children.
            x.left = z.left;
            x.right = z.right;
            x.left.parent = x;
            x.right.parent = x;
        }

	// Fix links between the parent of the subtree and x.
	if (x != nil)
	    x.parent = z.parent;

	if (root == z)
	    root = x;
	else
	    if (z == z.parent.left)
		    z.parent.left = x;
	    else
		    z.parent.right = x;
    }

    /**
     * Returns the data stored in a node.
     *
     * @param node The node whose data is returned.
     * @throws ClassCastException if <code>node</code> does not
     * reference a <code>Node</code> object.
     */
}

// $Id: BinarySearchTree.java,v 1.1 2003/10/14 16:56:20 thc Exp $
// $Log: BinarySearchTree.java,v $
// Revision 1.1  2003/10/14 16:56:20  thc
// Initial revision.
//
