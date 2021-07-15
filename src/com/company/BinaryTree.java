package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by davidnagar on 2/1/15.
 */
public class BinaryTree<E> {
    /** Root of the binary search tree. */
    protected Node root;

    /** Sentinel, replaces NIL in the textbook's code. */
    protected Node nil;

    private ArrayList<Node> arrayList;


    /**
     * Interface for when we visit a node during a walk.
     */
    public interface Visitor
    {
        /**
         * Perform some action upon visiting the node.
         *
         * @param handle Handle that identifies the node being visited.
         */
        public Object visit(Object handle);
    }

    /**
     * Inner class for a node of a binary search tree.  May be
     * extended in subclasses of <code>BinarySearchTree</code>.
     */
    protected class Node implements Comparable
    {
        protected int height;
        /** The data stored in the node. */
        protected Comparable data;

        /** The node's parent. */
        protected Node parent;

        /** The node's left child. */
        protected Node left;

        /** The node's right child. */
        protected Node right;

        /**
         * Initializes a node with the data and makes other pointers
         * nil.
         *
         * @param data Data to save in the node.
         */
        public Node(Comparable<? extends E> data)
        {
            this.data = data;
            parent = nil;
            left = nil;
            right = nil;
        }

        /**
         * Compares this node to another node.  The comparison is
         * based on the <code>data</code> instance variables of the
         * two nodes.
         *
         * @param o The other node.
         * @return A negative integer if this node is less than
         * <code>o</code>; 0 if this node equals <code>o</code>; a
         * positive integer if this node is greater than
         * <code>o</code>.
         * @throws ClassCastException if <code>o</code> is not a
         * <code>Node</code>.
         */
        // Compare this node to another node.
        public int compareTo(Object o)
        {
            return data.compareTo(((Node) o).data);
        }

        /**
         * Returns the <code>data</code> instance variable of this node
         * as a <code>String</code>.
         */
        public String toString()
        {
            if (this == nil)
                return "nil";
            else
                return data.toString();
        }

        /**
         * Returns a multiline <code>String</code> representation of
         * the subtree rooted at this node, representing the depth of
         * each node by two spaces per depth preceding the
         * <code>String</code> representation of the node.
         *
         * @param depth Depth of this node.
         */
        public String toString(int depth)
        {
            String result = "";

            if (left != nil)
                result += left.toString(depth + 1);

            for (int i = 0; i < depth; i++)
                result += "  ";

            result += toString() + "\n";

            if (right != nil)
                result += right.toString(depth + 1);

            return result;
        }

        public int heightRec() {
            if (this.data == null) {
                return 0;
            } else {
                return 1 + Math.max(this.left.height(), this.right.height());
            }
        }

        public int height()
        {
            return this == null ? -1 : this.height;
        }

        public int getBalance() {
            return this.left.height - this.right.height;
        }
    }

    /**
     * Sets the sentinel <code>nil</code> to a given node.
     *
     * @param node The node that <code>nil</code> is set to.
     */
    protected void setNil(Node node)
    {
        nil = node;
        nil.parent = nil;
        nil.left = nil;
        nil.right = nil;
    }

    /**
     * Creates a binary search tree with just a <code>nil</code>,
     * which is the root.
     */
    public BinaryTree()
    {
        setNil(new Node(null));
        root = nil;
        arrayList = new ArrayList<Node>();
    }

    /**
     * Returns <code>true</code> if the given node is the sentinel
     * <code>nil</code>, <code>false</code> otherwise.
     *
     * @param node The node that is being asked about.
     */
    public boolean isNil(Object node)
    {
        return node == nil;
    }

    /**
     * Traverses the tree in inorder applying a <code>Visitor</code>
     * to each node.
     *
     * @param visitor Object implementing <code>Visitor</code> whose
     * <code>visit</code> method is applied to each node in the tree.
     */
    public void inorderWalk(Visitor visitor)
    {
        inorderWalk(root, visitor);
    }

    /**
     * Performs an inorder walk of the the subtree rooted at a node,
     * applying a <code>Visitor</code> to each node in the subtree.
     *
     * @param x Root of the subtree.
     * @param visitor Object implementing <code>Visitor</code> whose
     * <code>visit</code> method is applied to each node in the
     * subtree.
     */
    protected void inorderWalk(Node x, Visitor visitor)
    {
        if (x != nil) {
            inorderWalk(x.left, visitor);
            System.out.print(visitor.visit(x) + " ");
            inorderWalk(x.right, visitor);
        }
    }

    protected ArrayList<Node> convertToArray(Node x)
    {
        if (x != nil) {
            convertToArray(x.left);
            arrayList.add(x);
            convertToArray(x.right);
        }
        return arrayList;
    }

    /**
     * Traverses the tree in preorder applying a <code>Visitor</code>
     * to each node.
     *
     * @param visitor Object implementing <code>Visitor</code> whose
     * <code>visit</code> method is applied to each node in the tree.
     */
    public void preorderWalk(Visitor visitor)
    {
        preorderWalk(root, visitor);
    }

    /**
     * Performs a preorder walk of the the subtree rooted at a node,
     * applying a <code>Visitor</code> to each node in the subtree.
     *
     * @param x Root of the subtree.
     * @param visitor Object implementing <code>Visitor</code> whose
     * <code>visit</code> method is applied to each node in the
     * subtree.
     */
    protected void preorderWalk(Node x, Visitor visitor)
    {
        if (x != nil) {
            System.out.print(visitor.visit(x) + " ");
            preorderWalk(x.left, visitor);
            preorderWalk(x.right, visitor);
        }
    }

    /**
     * Traverses the tree in postorder applying a <code>Visitor</code>
     * to each node.
     *
     * @param visitor Object implementing <code>Visitor</code> whose
     * <code>visit</code> method is applied to each node in the tree.
     */
    public void postorderWalk(Visitor visitor)
    {
        postorderWalk(root, visitor);
    }

    /**
     * Performs a postorder walk of the the subtree rooted at a node,
     * applying a <code>Visitor</code> to each node in the subtree.
     *
     * @param x Root of the subtree.
     * @param visitor Object implementing <code>Visitor</code> whose
     * <code>visit</code> method is applied to each node in the
     * subtree.
     */
    protected void postorderWalk(Node x, Visitor visitor)
    {
        if (x != nil) {
            postorderWalk(x.left, visitor);
            postorderWalk(x.right, visitor);
            System.out.print(visitor.visit(x) + " ");
        }
    }

    /**
     * Returns a multiline <code>String</code> representation of the
     * tree, representing the depth of each node by two spaces per
     * depth preceding the <code>String</code> representation of the
     * node.
     */
    public String toString()
    {
        return root.toString(0);
    }

    /**
     * Searches the tree for a node with a given key.  Works
     * recursively.
     *
     * @param k The key being searched for.
     * @return A reference to a <code>Node</code> object with key
     * <code>k</code> if such a node exists, or a reference to the
     * sentinel <code>nil</code> if no node has key <code>k</code>.
     * The <code>Node</code> class is opaque to methods outside this
     * class.
     */
    public Node search(Comparable k)
    {
        return search(root, k);
    }

    protected Node search(Node x, Comparable k)
    {
        Node result = nil;
        convertToArray(x);
       for (int i = 0; i < arrayList.size(); i++){
           if (k.compareTo(arrayList.get(i).data) == 0){
               result = arrayList.get(i);
           }
       }

        return result;
    }

    public Object insert(Comparable data)
    {
        Node z = new Node(data);
        treeInsert(z);

        return z;
    }

    private void treeInsert(Node z) {
        Node y = nil;
        Node x = root;
        Random random = new Random();
        boolean bool = random.nextBoolean();
        while (x != nil) {
            y = x;
            if (bool) {
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
            if (bool) {
                y.left = z;
            }else {
                y.right = z;
            }
        }
    }
    
    public Node successor(Node node)
    {
        return node;
    }

    public void delete(Object node) {
        Node z = (Node) node;
        Node y = nil;
        if (z.left != nil) {
            z.left.parent = z.parent.parent;
            y = z.left;
        }else if (z.right != nil){
            z.right.parent = z.parent.parent;
            y = z.right;
        }

        if (z.parent.left == z){
            z.parent.left = y;
        }else if (z.parent.right == z){
            z.parent.right = y;
        }
    }
}
