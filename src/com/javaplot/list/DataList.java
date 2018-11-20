package com.javaplot.list;

import com.javaplot.Drawable;

import java.util.Iterator;

/**
 * Implementation of a List containing only Nodes with objects
 * that extends Drawable interface.
 *
 * @param <T>Generics for the list. It must extends Drawable interface,
 *                    so that each data has for sure a value, a label and a color.
 * @see Drawable Drawable
 * @see Node Node
 */
public class DataList<T extends Drawable> implements Iterable<Node<T>> {

    private Node<T> head, iterIndex;
    private int size;

    /**
     * Constructor for the list. It creates an empty list.
     */
    public DataList() {
        this.head = null;
        this.iterIndex = null;
        this.size = 0;
    }

    /**
     * Getter for the size of the list.
     *
     * @return The size of the list as an int value.
     */
    public int size() {
        return size;
    }

    /**
     * Insert an element at the beginning of the list.
     *
     * @param data The element to insert. It ust implement Drawable interface.
     * @see Drawable Drawable
     */
    public void insert(T data) {
        try {
            insertAt(0, data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Insert an element at the specified position.
     *
     * @param position The position to insert the data.
     * @param data     The element to insert. It must implement Drawable interface.
     * @throws ListException
     * @see Drawable Drawable
     */
    public void insertAt(int position, T data) throws ListException {
        if (position < 0) throw new ListException("Lista vuota");
        if (position == 0) {
            head = new Node<>(data, head);
        } else {
            Node<T> n = head;
            while (position-- > 1) {
                n = n.getNext();
            }
            n.setNext(new Node<T>(data, n.getNext()));
        }
        size++;
    }

    /**
     * Remove the top element of the list.
     *
     * @throws ListException
     */
    public void removeHead() throws ListException {
        this.removeAt(0);
    }

    /**
     * Remove the element at the specified position.
     *
     * @param position The position to remove the data from.
     * @throws ListException
     */
    public void removeAt(int position) throws ListException {
        if (position < 0 || position >= size()) throw new ListException("Lista vuota");

        if (position == 0)
            head = head.getNext();
        else {
            Node<T> n = head;
            while (position-- > 1) {
                n = n.getNext();
            }
            n.setNext(n.getNext().getNext());
        }
        size--;
    }

    /**
     * Return the Node at the beginning of the list.
     *
     * @return The Node in first position.
     * @see Node Node
     */
    public Node<T> getHead() {
        return getAt(0);
    }

    /**
     * Return the Node at the specified position.
     *
     * @param position The position of the Node to return.
     * @return The Node at the specified position.
     * @see Node Node
     */
    public Node<T> getAt(int position) {
        if (position < 0 || position >= size()) return null;

        if (position == 0)
            return head;
        else {
            Node<T> n = head;
            while (position-- > 1) {
                n = n.getNext();
            }
            return n.getNext();
        }
    }

    /**
     * Return the Node with the max length of his label.
     *
     * @return The Node of the list.
     * @see Node Node
     * @throws ListException
     */
    public Node<T> getMaxNodeFromStringLength() throws ListException {
        if (head == null || size == 0) throw new ListException("Lista vuota");

        Node<T> max = head;

        for (int i = 1; i < size; i++) {
            Node<T> dato = getAt(i);
            if (max.getData().getLabel().length() < dato.getData().getLabel().length()) {
                max = dato;
            }
        }
        return max;
    }

    /**
     * Return the Node with the max value.
     *
     * @return The Node of the list.
     * @see Node Node
     * @throws ListException
     */
    public Node<T> getMaxNodeFromValue() throws ListException {
        if (head == null || size == 0) throw new ListException("Lista vuota");

        Node<T> max = head;

        for (int i = 1; i < size; i++) {
            Node<T> dato = getAt(i);
            if (max.getData().getValue().floatValue() < dato.getData().getValue().floatValue()) {
                max = dato;
            }
        }
        return max;
    }

    /**
     * Find the data in the list. Throws an exception if not found.
     *
     * @param data The data to find.
     * @return The position in the list of the data.
     * @throws ListException
     */
    public int find(T data) throws ListException {
        Node<T> n = head;
        int i = 0;
        while (!n.getData().equals(data)) {
            n = n.getNext();
            if (++i > size()) throw new ListException("Elemento non trovato");
        }
        return i;
    }

    /**
     * Return an Iterator of the list.
     *
     * @return The Iterator object
     */
    @Override
    public Iterator<Node<T>> iterator() {
        this.iterIndex = this.head;

        return new Iterator<Node<T>>() {
            @Override
            public boolean hasNext() {
                return iterIndex != null;
            }

            @Override
            public Node<T> next() {
                Node<T> elem = iterIndex;

                iterIndex = iterIndex.getNext();

                return elem;
            }
        };
    }

    /**
     * Implementation for the String representation of the DataList.
     *
     * @return A proper String that represents the DataList.
     */
    @Override
    public String toString() {
        StringBuilder ris = new StringBuilder();

        Iterator<Node<T>> iter = this.iterator();

        while(iter.hasNext()){
            ris.append(iter.next().toString());
            if(iter.hasNext())
                ris.append(", ");
        }

        return new String(ris);
    }
}
