package ru.inbox.foreman.tree;

import ru.inbox.foreman.treeNode.TreeNode;

import java.util.*;

public class Tree<T extends Comparable<T>> {
    private TreeNode<T> root;
    private int size = 0;
    private Comparator<T> comparator;
    private int modCount = 0;

    /**
     * конструктор с компаратором по умолчанию
     */
    public Tree() {
        this(Comparator.naturalOrder());
    }

    /**
     * конструктор с переданными компаратором для заданных элементов
     *
     * @param comparator компаратор
     */
    public Tree(Comparator<T> comparator) {
        this.root = new TreeNode<>();
        this.comparator = comparator;
    }

    /**
     * добавление данных в узел
     *
     * @param data добавляемые данные
     */
    public void add(T data) {
        addData(data, this.comparator);
    }

  /*  public void add(T data, Comparator<T> comparator) {
        addData(data, comparator);
    }*/

    public int getSize() {
        return size;
    }

    public IteratorInDepth<T> iteratorInDepth() {
        return new IteratorInDepth<T>() {
        };
    }

    private void checkCollection(int expectedModCount) {
        if (expectedModCount != modCount) {
            throw new ConcurrentModificationException("Collection modified");
        }
    }


    private void addData(T data, Comparator<T> comparator) {
        for (TreeNode<T> currentItem = root; ; ) {
            if (currentItem.getData() == null) {
                currentItem.setData(data);
                size++;
                return;
            }
            if (comparator.compare(data, currentItem.getData()) < 0) {
                if (currentItem.getLeft() == null) {
                    currentItem.setLeft(new TreeNode<>(data));
                    size++;
                    return;
                }
                currentItem = currentItem.getLeft();
            } else {
                if (currentItem.getRight() == null) {
                    currentItem.setRight(new TreeNode<>(data));
                    size++;
                    return;
                }
                currentItem = currentItem.getRight();
            }
        }
    }

    public class IteratorInDepth<T extends Comparable<T>> implements Iterator<T> {
        private List<TreeNode<T>> queue = new LinkedList<>();
        int extendModCount = modCount;

        IteratorInDepth() {
            if (root != null) {
                queue.add((TreeNode<T>) root);
            }
        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }

        @Override
        public T next() {
            checkCollection(extendModCount);
            TreeNode<T> currentItem = queue.remove(queue.size() - 1);
            if (currentItem.getLeft() != null) {
                queue.add(currentItem.getLeft());
            }
            if (currentItem.getRight() != null) {
                queue.add(currentItem.getRight());
            }
            return currentItem.getData();
        }

        @Override
        public void remove() {
            ;
        }
    }


//    private class IteratorInDepth<T> {
//        public IteratorInDepth(TreeNode<T> root) {
//        }
//    }
}
