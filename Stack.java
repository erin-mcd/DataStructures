public class Stack<E> {
    private Node<E> top;

    public boolean isEmpty(){
        return top == null;
    }

    public E peek(){
    return top.item;
    }

    public E pop(){
    E toReturn = top.item;
    top = top.next;
    return toReturn;
    }

    public E push(E item){
    Node<E> newTop = new Node<E>(item);
    newTop.next = top;
    top = newTop;
    return item;
    }

    public static class Node<E>{
        E item;
        Node<E> next;
        public Node(E item){
            this.item = item;
        }
    }
}
