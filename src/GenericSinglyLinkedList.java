/* GenericSinglyLinkedList.java
 *  @version May 2, 2020
 *  @author Nischay Uppal
 *  @date May 4, 2020
 *  @description: Custom implementation of a Generic Singly Linked List.
 * */
class GenericSinglyLinkedList<E>{
    private ListNode<E> head;

    /**
     * GenericsSinglyLinkedList
     * This is the constructor for an empty linked list in which the head points to null
     */
    public GenericSinglyLinkedList(){
        this.head = null;
}

    /**
     * addBeginning
     * This method adds a node to the beginning of the linked list.
     * @param item E, an item to add to the linked list.
     * @return true if the method is completed.
     */
    public boolean addBeginning(E item) {
        //If head is null, create a new node and assign head to it. The new node will contain the item and will point to null
        //because it is the only node in the linked list.
        if (head == null) {
            head = new ListNode<E>(item,null);
        } else {
            //If the head is not null, create a new node which will contain the item. this new node points to the first
            //node in the linked list. redirect the head to now point to the new node.
            ListNode<E> newNode = new ListNode<E>(item,head);
            head = newNode;
        }
        return true;
    }

    /**
     * removeBeginning
     * This method removes the first node in a singly linked list.
     * @return the item within the first node.
     */
    public E removeBeginning() {
        //If there are no nodes to remove, return null.
        if(head == null) {
            return null;
        } else {
            //set the firstNode to the pointer head, and point the head to the second node. return the item in the first node.
            ListNode<E> firstNode = head;
            head = firstNode.getNext();
            return firstNode.getItem();
        }
    }

    /* ListNode<>()
     *  @version May 2, 2020
     *  @author Nischay Uppal
     *  @date May 4, 2020
     *  @description: Custom implementation of a Node in a Generic Singly Linked List.
     * */
    private class ListNode<T>{
        private T item;
        private ListNode<T> next;

        /**
         * ListNode
         * This constructor initializes a ListNode. ListNodes differ from TreeNodes such that ListNodes are used in singly
         * linked lists whereas TreeNodes are used in binary trees.
         * @param item T, the value it holds
         * @param next ListNode<T>, is the next node which this points to.
         */
        public ListNode(T item, ListNode<T> next){
            this.item = item;
            this.next = next;
        }

        //getters and setters
        public ListNode<T> getNext(){
            return this.next;
        }
        public void setNext(ListNode<T> next){
            this.next = next;
        }
        public T getItem(){
            return this.item;
        }
        public void setItem(T item){
            this.item = item;
        }
    }
}



