package Complete_Java.Structured_path.LinkedList;

public class CustomLinkedList<T> {
    private class Node{
        T data;
        Node next;
        Node(T data){
            this.data = data;
            this.next = null;
        }
    }
    private Node head;
    private Node tail;
    private int size;
    public CustomLinkedList(){
        this.head=null;
        this.tail=null;
        this.size=0;
    }

    // ----- ADDITION METHODS--------
    // Default add method behaves like standard collections (adds to end)
    public void add(T data){
        addLast(data);
    }

    public boolean isEmpty(){
        return size == 0;
    }

    // Adds to the beginning of the list: O(1)
    public void addFirst(T data){
        Node newNode = new Node(data);
        if(isEmpty()){
            head = tail = newNode;
        }else{
            newNode.next = head;
            head = newNode;
        }
        size++;
    }

    public void addLast(T data){
        Node newNode  = new Node(data);
        if(isEmpty()){
            head = tail = newNode;
        }else{
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public void addAt(int index, T data){
        if(index<0 || index>size) throw new IndexOutOfBoundsException("Invalid Index");
        if(index == 0){
            addFirst(data);
            return;
        }
        if(index == size){
            addLast(data);
            return;
        }
        Node newNode = new Node(data);
        Node currentNode = head;
        for (int i = 0; i < index-1; i++) {
            currentNode = currentNode.next;
        }
        newNode.next = currentNode.next;
        currentNode.next = newNode;
        size++;
    }

}
