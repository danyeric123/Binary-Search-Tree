package com.company;

/**
 * Created by davidnagar on 2/1/15.
 */
public class AVLTree<E> extends BinarySearchTree<E> {

    public AVLTree(){
        super();
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
            avlInsert(z);

            return z;
        }

        /**
         * Inserts a node into the tree.
         *
         * @param z The node to insert.
         */
        protected void avlInsert(Node z)
        {
            treeInsert(z);
            rebalance(z);
        }

    private void rebalance(Node n) {
        Node current = n;

        while (current != nil) {
            current.height = Math.max(current.left.height, current.right.height) + 1;
            int currentBalance = current.getBalance();

            if (currentBalance < -1) {
                if (current.right.getBalance() == -1) {
                    leftRotate(current);
                }
                else if (current.right.getBalance() == 1) {
                    rightRotate(current.right);
                    leftRotate(current);
                }
            }else if (currentBalance > 1) {
                if (current.left.getBalance() == 1) {
                    rightRotate(current);
                }
                else if (current.left.getBalance() == -1) {
                    leftRotate(current.left);
                    rightRotate(current);
                }
            }
            current = current.parent;
        }
    }

    private void rightRotate(Node x) {
        Node y = x.left;
        x.left = y.right;
        if (y.right != nil) {
            y.right.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == nil) {
            root = y;
        }
        else if (x == x.parent.right) {
            x.parent.right = y;
        }
        else {
            x.parent.left = y;
        }
        y.right = x;
        x.parent = y;
        y.height = Math.max( y.left.height(), y.right.height()) + 1;
        x.height = Math.max(x.right.height(), y.height ) + 1;
    }


    private void leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        if (y.left != nil) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == nil) {
            root = y;
        }
        else if (x == x.parent.left) {
            x.parent.left = y;
        }
        else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
        x.height = Math.max( x.left.height(), x.right.height()) + 1;
        y.height = Math.max( y.left.height(), x.height ) + 1;
    }

    public void delete(Object node){
        super.delete(node);
        rebalance((Node)node);
    }

    protected void setNil(Node node)
    {
        nil = node;
        nil.parent = nil;
        nil.left = nil;
        nil.right = nil;
    }
}
