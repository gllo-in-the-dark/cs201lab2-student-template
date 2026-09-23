import java.util.*;

public class SinglyLinkedList<E extends Comparable<E>> {
    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    private static class Node<E> {
        private E element;
        private Node<E> next;
    
        public Node(E e, Node<E> n){
            element = e;
            next = n;
        }
    
        public E getElement(){
            return element;
        }
    
        public Node<E> getNext(){
            return next;
        }
    
        public void setNext(Node<E> n){
            next = n;
        }
    }

    public SinglyLinkedList(){

    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public E first(){
        if (isEmpty()){
            return null;
        } 
        return head.getElement();
    }

    public E last(){
        if (isEmpty()){
            return null;
        }
        return tail.getElement();
    }

    public void addFirst(E e){
        head = new Node<>(e, head);

        if (isEmpty()){
            tail = head;
        }
        size++;
    }

    public void addLast(E e){
        Node<E> newest = new Node<>(e, null);
        if (isEmpty()){
            head = newest;
        } else {
            tail.setNext(newest);
        }
        tail = newest;
        size++;
    }

    public E removeFirst(){
        if (isEmpty()){
            return null;
        }

        E answer = head.getElement();
        head = head.getNext();
        size--;

        if (isEmpty()){
            tail = null;
        }
        return answer;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        Node<E> current = head;
        while (current != null) {
            sb.append(current.getElement());
            sb.append(" ");
            current = current.getNext();
        }
        return sb.toString();
    }

    // write your codes here
    public void swap(){
        // Do nothing if the list is empty or has only one node
        if (isEmpty() || size() == 1){
            return;
        }

        // Create an array of nodes
        Node<E>[] nodes = new Node[size];
        Node<E> current = head;
        for (int i = 0; i < size; i++){
            nodes[i] = current;
            current = current.getNext();
        }

        // Create a sorted array of nodes
        Node<E>[] sortedNodes = nodes.clone();
        Arrays.sort(sortedNodes, (a, b) -> a.getElement().compareTo(b.getElement()));

        // Create a map of nodes to swap
        Map<Node<E>, Node<E>> swapMap = new HashMap<>();
        for (int i = 0; i < (size+1)/2; i++) {
            Node<E> smaller = sortedNodes[i];
            Node<E> larger = sortedNodes[size - i - 1];
            swapMap.put(smaller, larger);
            swapMap.put(larger, smaller);
        }

        // Create an array of swapped nodes
        Node<E>[] swappedNodes = new Node[size];
        for (int i = 0; i < size; i++){
            swappedNodes[i] = swapMap.get(nodes[i]);
        }

        // Set the next pointers of the swapped nodes
        for (int i = 0; i < size-1; i++){
            swappedNodes[i].setNext(swappedNodes[i+1]);
        }
        swappedNodes[size-1].setNext(null);

        // Set the head and tail of the linked list
        head = swappedNodes[0];
        tail = swappedNodes[size-1];
    }
   
}

