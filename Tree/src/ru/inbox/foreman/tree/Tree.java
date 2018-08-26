package ru.inbox.foreman.tree;


import ru.inbox.foreman.treeNode.TreeNode;

import java.util.*;
import java.util.function.Consumer;

/**
 * Бинарное дерево.
 *
 * @param <T>
 */
public class Tree<T extends Comparable<? super T>> {
    private TreeNode<T> root;
    private Comparator<T> comparator;
    private int size;

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
        this.comparator = comparator;
        this.size = 0;
    }

    /**
     * Размер дерева
     *
     * @return целое число, количество элементов в дереве
     */
    public int getSize() {
        return size;
    }

    /**
     * добавление данных в узел
     *
     * @param data добавляемые данные
     */
    public void add(T data) {
        if (size == 0) {
            root = new TreeNode<>(data);
            size++;
            return;
        }

        for (TreeNode<T> currentNode = root; ; ) {

            if (compareNodes(data, currentNode.getData())) {
                if (currentNode.getLeft() == null) {
                    currentNode.setLeft(new TreeNode<>(data));
                    size++;
                    return;
                }
                currentNode = currentNode.getLeft();
            } else {
                if (currentNode.getRight() == null) {
                    currentNode.setRight(new TreeNode<>(data));
                    size++;
                    return;
                }
                currentNode = currentNode.getRight();
            }
        }
    }


    /**
     * Поиск узла дерева по данным
     *
     * @param data данные
     * @return если найден - узел, если не навйден, null
     */
    public TreeNode<T> findNode(T data) {
        if (size == 0) {
            throw new NoSuchElementException("No Such Element");
        }
        //noinspection unchecked
        return (findNodes(data))[0];
    }

    /**
     * Удажение узла по данными
     *
     * @param data данные, которые необходимо удалить из дерева
     * @return false - если узла с данными нет, true - если узел удален.
     */
    public boolean remove(T data) {
        if (size == 0) {
            throw new NoSuchElementException("No Such Element");
        }
        //noinspection unchecked
        TreeNode<T>[] removedAndParent = findNodes(data);
        TreeNode<T> currentNode = removedAndParent[0];
        //noinspection unchecked
        TreeNode<T> parent = removedAndParent[1];
        if (currentNode == null) {
            return false;
        }
        //удаление листа
        if (currentNode.getLeft() == null && currentNode.getRight() == null) {
            removeNode(parent, currentNode, null);
            size--;
            return true;
        }
        //удаление узла и одним сыном
        if (currentNode.getLeft() == null || currentNode.getRight() == null) {
            TreeNode<T> nextNode = currentNode.getLeft() == null ? currentNode.getRight() : currentNode.getLeft();
            removeNode(parent, currentNode, nextNode);
            size--;
            return true;
        }
        removeDefaultNode(currentNode);
        size--;
        return true;
    }

    /**
     * Проход дерева в глубину
     *
     * @param consumer действие с данными узла
     */
    public void visitInVertical(Consumer<T> consumer) {
        Stack<TreeNode<T>> stack = new Stack<>();
        stack.add(root);
        TreeNode<T> currentNode;
        while (!stack.empty()) {
            currentNode = stack.pop();
            consumer.accept(currentNode.getData());
            if (currentNode.getRight() != null) {
                stack.add(currentNode.getRight());
            }
            if (currentNode.getLeft() != null) {
                stack.add(currentNode.getLeft());
            }

        }
    }

    /**
     * Обход дерева в ширину
     *
     * @param consumer действие с данными узла
     */
    public void visitInHorizontal(Consumer<T> consumer) {
        Queue<TreeNode<T>> queue = new ArrayDeque<>();
        queue.add(root);
        TreeNode<T> currentNode;
        while (queue.size() != 0) {
            currentNode = queue.poll();
            consumer.accept(currentNode.getData());
            if (currentNode.getLeft() != null) {
                queue.add(currentNode.getLeft());
            }
            if (currentNode.getRight() != null) {
                queue.add(currentNode.getRight());
            }
        }
    }

    /**
     * Обход дерева в в глубину рекурсией
     *
     * @param consumer действие с данными узла
     */
    public void visitInVerticalRecurs(Consumer<T> consumer) {
        visitRecurs(consumer, root);
    }

    private void visitRecurs(Consumer<T> consumer, TreeNode<T> startNode) {
        consumer.accept(startNode.getData());
        if (startNode.getLeft() != null) {
            visitRecurs(consumer, startNode.getLeft());
        }
        if (startNode.getRight() != null) {
            visitRecurs(consumer, startNode.getRight());
        }
    }

    /**
     * удаление стандартного(полноценного узла)
     *
     * @param removedNode удаляемый узел
     */

    private void removeDefaultNode(TreeNode<T> removedNode) {
        TreeNode<T> lastLeftNode = removedNode.getRight();
        TreeNode<T> parentToLastLeftNode = removedNode;

        while (lastLeftNode.getLeft() != null) {
            parentToLastLeftNode = lastLeftNode;
            lastLeftNode = lastLeftNode.getLeft();
        }

        T lastLeftNodeData = lastLeftNode.getData();
        removeNode(parentToLastLeftNode, lastLeftNode, lastLeftNode.getRight());
        removedNode.setData(lastLeftNodeData);
    }

    /**
     * Удаление листа либо узла с одним сыном
     *
     * @param parent      родитель удалямого узла
     * @param removedNode удаляемый узел
     * @param nextNode    null если удаляемый узел - лист либо сын удаляемого узла
     */
    private void removeNode(TreeNode<T> parent, TreeNode<T> removedNode, TreeNode<T> nextNode) {
        if (parent.getLeft() == removedNode) {
            parent.setLeft(nextNode);
        } else {
            parent.setRight(nextNode);
        }
    }

    /**
     * Поиск узла и его родителя по данным
     *
     * @param data данные
     * @return массив из найденного узла и его родителя
     */
    private TreeNode[] findNodes(T data) {

        for (TreeNode<T> currentNode = root, parentNode = null; ; ) {
            if (currentNode.getData() == null && data == null) {
                return new TreeNode[]{currentNode, parentNode};
            }
            if (Objects.equals(data, currentNode.getData())) {
                return new TreeNode[]{currentNode, parentNode};
            }
            if (compareNodes(data, currentNode.getData())) {
                if (currentNode.getLeft() == null) {
                    return new TreeNode[]{null, parentNode};
                }
                parentNode = currentNode;
                currentNode = currentNode.getLeft();

            } else {
                if (currentNode.getRight() == null) {
                    return new TreeNode[]{null, parentNode};
                }
                parentNode = currentNode;
                currentNode = currentNode.getRight();
            }
        }
    }

    private boolean compareNodes(T data, T currentNodeData) {
        if (data == null && currentNodeData == null) {
            return true;
        }
        if (data == null) {
            return true;
        }
        if (currentNodeData == null) {
            return false;
        }
        return comparator.compare(data, currentNodeData) < 0;
    }
}
