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

