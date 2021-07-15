package com.company;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
	// write your code here
        BinaryTree<String> binaryTree = new BinaryTree<String>();
        binaryTree.insert("hey");
        binaryTree.insert("igloo");
        Object eric = binaryTree.insert("eric");
        binaryTree.insert("i");
        binaryTree.insert("jar");
        binaryTree.insert("kagaroo");
        binaryTree.insert("lamar");
        binaryTree.insert("fan");
        Object apple = binaryTree.insert("apple");
        BinaryTree.Visitor visitor = new BinaryTree.Visitor() {
            @Override
            public Object visit(Object handle) {
                return handle.toString();
            }
        };
        println("Binary Tree: ");
        binaryTree.preorderWalk(visitor);
        println();
        binaryTree.inorderWalk(visitor);
        println();
        binaryTree.postorderWalk(visitor);
        println();
        System.out.println("I found " + binaryTree.search("apple"));
        System.out.println("I found " + binaryTree.search("eric"));
        binaryTree.delete(eric);
        binaryTree.inorderWalk(visitor);
        println("\nI KILLED ERIC!!!!");
        binaryTree.delete(apple);
        binaryTree.inorderWalk(visitor);
        println("\nSorry, I ate the apple");

        BinarySearchTree<String> binarySearchTree = new BinarySearchTree<String>();
        //randomlyInsert(binarySearchTree);
        binarySearchTree.insert("hey");
        binarySearchTree.insert("igloo");
        eric = binarySearchTree.insert("eric");
        binarySearchTree.insert("i");
        binarySearchTree.insert("jar");
        binarySearchTree.insert("kagaroo");
        binarySearchTree.insert("lamar");
        binarySearchTree.insert("fan");
        apple = binarySearchTree.insert("apple");

        println("\nBST: ");
        binarySearchTree.preorderWalk(visitor);
        println();
        binarySearchTree.inorderWalk(visitor);
        println();
        binarySearchTree.postorderWalk(visitor);
        println();
        System.out.println("I found " + binarySearchTree.search("apple"));
        System.out.println("I found " + binarySearchTree.search("eric"));
        binarySearchTree.delete(eric);
        binarySearchTree.inorderWalk(visitor);
        println("\nI KILLED ERIC!!!!");
        binarySearchTree.delete(apple);
        binarySearchTree.inorderWalk(visitor);
        println("\nSorry, I ate the apple");




        AVLTree<String> avlTree = new AVLTree<String>();
        avlTree.insert("hey");
        avlTree.insert("igloo");
        eric = avlTree.insert("eric");
        avlTree.insert("i");
        avlTree.insert("jar");
        avlTree.insert("kagaroo");
        avlTree.insert("lamar");
        avlTree.insert("fan");
        apple = avlTree.insert("apple");
        println("\nAVLTree:");
        avlTree.preorderWalk(visitor);
        println();
        avlTree.inorderWalk(visitor);
        println();
        avlTree.postorderWalk(visitor);
        println();
        println("The tree balance is " + avlTree.root.getBalance());
        println("I found " + avlTree.search("eric").toString());
        println("I found an " + avlTree.search("apple").toString());
       avlTree.delete(eric);
        avlTree.inorderWalk(visitor);
        println("\nI KILLED ERIC!!!!");
        avlTree.delete(apple);
        avlTree.inorderWalk(visitor);
        println("\nSorry, I ate the apple");
    }

    private static void println(String str) {
        System.out.println(str);
    }
    private static void println() {
        System.out.println();
    }

    public static <T extends BinarySearchTree<String>> void randomlyInsert(T binarySearchTree){
        Random random = new Random();
        String str;
        for (int i = 0; i < random.nextInt(50)+3; i++){
           str = "";
            while(str == ""){
                for (int j = 0; j < 4; j++){
                str = str + (char)(random.nextInt(25)+97);
            }
            }
            System.out.println(str);
            binarySearchTree.insert(str);
        }
    }
}
