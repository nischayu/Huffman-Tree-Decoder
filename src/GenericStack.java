/* GenericStack.java
 *  @version May 3, 2020
 *  @author Nischay Uppal
 *  @date May 4, 2020
 *  @description: Custom implementation of a Generic Stack.
 * */

public class GenericStack<T>{
    //The generic stack is in the form of a generic singly linked list.
    GenericSinglyLinkedList<T> linkedList = new GenericSinglyLinkedList<>();

    /**
     * push
     * This method pushes an item T onto the stack.
     * @param item of type T which will be added to the stack
     */
    public void push(T item){
        linkedList.addBeginning(item);
    }

    /**
     * pop
     * This method removes and returns the top item on a stack
     * @return T item, the removed item.
     */
    public T pop() throws Exception {
        //Remove the first Node in the linkedList.
        T item = linkedList.removeBeginning();
        if(item == null){
            throw new Exception("Stack is empty!");
        } else {
            return item;
        }
    }
}
