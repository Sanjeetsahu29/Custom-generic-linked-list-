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

    // ----- Removal Method -----------
    public T removeFirst(){
        if (isEmpty()) throw new RuntimeException("List is empty");
        T data = head.data;
        head = head.next;
        size--;
        if(isEmpty()) tail = null;
        return data;
    }

    public T removeLast(){
        if (isEmpty()) throw new RuntimeException("List is empty");
        if (size == 1) return removeFirst();
        /*
            T data = tail.data;
            tail = tail.next;
            size--;
            if(isEmpty()) head = null;
            return data;
        This code look fine, but here you lost tail reference because this is single linked list,
        after removing the tail by tail = tail.next we will the loose the reference of tail which should
        point to the previous next, so in singly linked list we can't just do tail = tail.previous
         */

        Node currentNode = head;
        while(currentNode.next!=tail) currentNode = currentNode.next;
        T data = tail.data;
        currentNode.next = null;
        tail = currentNode;
        size--;
        return data;
    }

    public T removeAt(int index){
        if(index<0 || index>=size) throw new IndexOutOfBoundsException("invalid Index");
        if(index == 0) return removeFirst();
        if(index == size-1) return  removeLast();
        Node currentNode = head;
        for (int i = 0; i < index-1; i++) {
            currentNode = currentNode.next;
        }
        T data = currentNode.next.data;
        currentNode.next = currentNode.next.next;
        size--;
        return data;
    }

    public boolean removeByData(T data){
        if(isEmpty()) return false;
        if(head.data.equals(data)){
            removeFirst();
            return true;
        }
        Node currentNode = head;
        while(currentNode.next!=null && !currentNode.next.data.equals(data)){
            currentNode = currentNode.next;
        }
        if(currentNode.next!=null){
            if(currentNode.next == tail){
                tail = currentNode;
            }
            currentNode.next = currentNode.next.next;
            size--;
            return true;
        }
        return false;
    }

    // --- RETRIEVAL & UTILITY METHODS ---
    // Gets element at index: O(n)
    public T get(int index){
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Invalid Index");
        Node currentNode = head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }
        return currentNode.data;
    }

    //Check if node with particular data exists in list or not: O(n)
    public boolean contains(T data){
        Node currentNode = head;
        while(currentNode!=null){
            if(currentNode.data.equals(data)) return true;
            currentNode = currentNode.next;
        }
        return false;
    }
    public int getSize(){
        return size;
    }

    public void clear(){
        head = null;
        tail = null;
        size = 0;
    }

}
