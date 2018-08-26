package ru.inbox.foreman.treeNode;

import java.util.Objects;

/**
 * Класс узла дерева
 *
 * @param <T> тип данных;
 * @author SergeyNF
 */
public class TreeNode<T extends Comparable<? super T>> {
    private T data;
    private TreeNode<T> left;
    private TreeNode<T> right;

    public TreeNode() {

    }

    public TreeNode(T data) {
        this.data = data;
    }

    public TreeNode(T data, TreeNode<T> left, TreeNode<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public TreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    public TreeNode<T> getRight() {
        return right;
    }

    public void setRight(TreeNode<T> right) {
        this.right = right;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        //noinspection unchecked
        TreeNode<T> t = (TreeNode<T>) obj;
        return data.equals(t.data) && left.equals(t.left) && right.equals(t.right);
    }
}
