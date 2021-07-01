package com.CtCI.CtCILibrary;

// Source: https://github.com/careercup/CtCI-6th-Edition/blob/master/Java/CtCILibrary/CtCILibrary/TreeNode.java

/* One node of a binary tree. The data element stored is a single
 * character.
 */
public class BinaryTreeNode {
    public int data;
    public BinaryTreeNode left;
    public BinaryTreeNode right;
    public BinaryTreeNode parent;
    private int size = 0;

    public BinaryTreeNode(int d) {
        data = d;
        size = 1;
    }

    private void setLeftChild(BinaryTreeNode left) {
        this.left = left;
        if (left != null) {
            left.parent = this;
        }
    }

    private void setRightChild(BinaryTreeNode right) {
        this.right = right;
        if (right != null) {
            right.parent = this;
        }
    }

    public void insertInOrder(int d) {
        if (d <= data) {
            if (left == null) {
                setLeftChild(new BinaryTreeNode(d));
            } else {
                left.insertInOrder(d);
            }
        } else {
            if (right == null) {
                setRightChild(new BinaryTreeNode(d));
            } else {
                right.insertInOrder(d);
            }
        }
        size++;
    }

    public int size() {
        return size;
    }

    public boolean isBST() {
        if (left != null) {
            if (data < left.data || !left.isBST()) {
                return false;
            }
        }

        if (right != null) {
            if (data >= right.data || !right.isBST()) {
                return false;
            }
        }

        return true;
    }

    public int height() {
        int leftHeight = left != null ? left.height() : 0;
        int rightHeight = right != null ? right.height() : 0;
        return 1 + Math.max(leftHeight, rightHeight);
    }

    public BinaryTreeNode find(int d) {
        if (d == data) {
            return this;
        } else if (d <= data) {
            return left != null ? left.find(d) : null;
        } else if (d > data) {
            return right != null ? right.find(d) : null;
        }
        return null;
    }

    private static BinaryTreeNode createMinimalBST(int arr[], int start, int end){
        if (end < start) {
            return null;
        }
        int mid = (start + end) / 2;
        BinaryTreeNode n = new BinaryTreeNode(arr[mid]);
        n.setLeftChild(createMinimalBST(arr, start, mid - 1));
        n.setRightChild(createMinimalBST(arr, mid + 1, end));
        return n;
    }

    public static BinaryTreeNode createMinimalBST(int[] array) {
        return createMinimalBST(array, 0, array.length - 1);
    }

    public void print() {
        BTreePrinter.printNode(this);
    }
}
