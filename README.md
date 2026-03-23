# Creating a Custom Generic Linked List 
Building a custom generic linked list from scratch is a fantastic exercise. It is essentially a rite of passage for understanding how Java handles object references, memory, and generics.
While Java provides a built-in java.util.LinkedList, creating our own gives us complete control and a deep understanding of what happens under the hood.
Before we look at the code, it helps to visualize what we are building: a chain of independent objects (nodes) scattered in memory, connected only by reference pointers.<br><hr>
## Here is a comprehensive implementation of a Singly Linked List in Java, utilizing Generics (<T>) so it can hold any object type.
```
public class CustomLinkedList<T> {
    private class Node{
        T data;
        Node next;
        Node(T data){
            this.data = data;
            this.next = null;
        }
    }
}
```
I have added a private inner Node class to represent each element of the linked list, where every node contains the actual data (T data) and a reference to the next node (Node next).

The important aspect here is that I have explicitly marked the Node class as private, which reflects a strong application of the Encapsulation (Information Hiding) principle.

This means the Node structure is completely hidden from the outside world and is strictly controlled within the CustomLinkedList class. The intent behind this is to ensure that no external code can directly interact with or manipulate the internal linkage (next pointers), which is critical for maintaining the integrity of the data structure.

By doing this, I am enforcing that all operations—such as insertion, deletion, or traversal—must go through well-defined public methods of the linked list, rather than allowing uncontrolled access to its internal representation.

In essence, this design ensures:
- The internal structure remains protected and consistent
- The implementation can evolve without affecting external consumers
- The class exposes behavior, not its internal mechanics

```
private Node head;
    private Node tail;
    private int size;
    public CustomLinkedList(){
        this.head=null;
        this.tail=null;
        this.size=0;
    }
```
I have introduced three core state variables—head, tail, and size—along with a constructor that initializes the linked list into a well-defined empty state.

- head represents the entry point of the list, i.e., the first node.
- tail represents the last node, enabling efficient O(1) insertions at the end.
- size maintains a real-time count of elements, avoiding the need for traversal when querying length.

All three fields are marked as private, which again reinforces the Encapsulation principle—the internal state of the list cannot be directly accessed or mutated from outside the class. This ensures that structural invariants (like correct head/tail linkage and accurate size tracking) are only modified through controlled operations.

```
    public void add(T data){
        addLast(data);
    }
    
    public boolean isEmpty(){
        return size == 0;
    }

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
```
I have added a set of insertion operations that define how elements enter the linked list, along with a utility method to check the list state. The design here closely mirrors standard collection behavior while maintaining strict control over structural integrity.

The add(T data) method acts as a semantic abstraction, delegating to addLast(data). This aligns with standard Java collections where add() typically appends to the end. The key idea is API consistency—users of this class get predictable behavior without needing to know internal details.

The isEmpty() method is a small but important addition. Instead of repeatedly checking size == 0 across methods, I’ve centralized that logic. This improves readability, maintainability, and intent clarity, and avoids duplication of condition logic.

In addFirst(T data), I am performing an insertion at the head in O(1) time:
- A new node is created
- If the list is empty, both head and tail point to the same node
- Otherwise, the new node links to the current head, and head is updated

In addLast(T data), I leverage the tail pointer for O(1) append:
- If empty → same handling as addFirst
- Otherwise → current tail.next points to new node, and tail is advanced

Across both methods, I increment size in a controlled manner, ensuring state consistency—the structural change (node addition) and metadata update (size) are tightly coupled.

```
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

```

I have added a positional insertion method addAt(int index, T data) to allow inserting an element at any arbitrary index, which introduces index-based access semantics on top of a sequential data structure.

The first important aspect is defensive boundary validation:
- I explicitly check index < 0 || index > size to prevent illegal memory access patterns.
- Allowing index == size is intentional, as it represents a valid append operation.

Then I handle edge cases explicitly:
- index == 0 → delegate to addFirst(data)
- index == size → delegate to addLast(data)

