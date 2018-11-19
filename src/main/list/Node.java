package javaplot.lista;


import javaplot.Drawable;

public class Node<T extends Drawable> {
    private T data;
    private Node<T> next = null;

    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public T getData() { return data; }

    public void setData(T data) {
        this.data = data;
    }

    public String getLabel() {
        return data.getLabel();
    }

    @Override
    public String toString(){
        return "["+this.data.getLabel()+ ": " + this.data.getValue()+"]";
    }
}
