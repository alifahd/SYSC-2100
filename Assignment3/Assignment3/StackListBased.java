import java.util.*;
/**
 * Creates a stack using linked list.
 *
 * @author Ali Fahd
 * @version March 8, 2020
 */
public class StackListBased
{
    private LinkedList items; //variable of type linked list will act as the stack

    /**
     * Constructor for objects of class StackListBased
     * sends program to method createStack()
     */
    public StackListBased()
    {
        createStack();
    }

    /**
     * method initializes the linked list
     */
    public void createStack(){
        items = new LinkedList();
    }
    
    /**
     * cleares the linked list and thus the "stack"
     */
    public void popAll(){
        items.clear();
    }
    
    /**
     * 
     * checks the size of the linked list
     * 
     * @return Boolean true if size is 0 otherwise false
     */
    public boolean isEmpty(){
        if(items.size() == 0){
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * 
     * Adds an object to the beginning of the linked list 
     * and returns the new list as a "stack"
     * @param Object o
     * @return Linked List
     */
    public LinkedList push(Object o){
        items.add(0,o);
        return items;
    }
    
    /**
     * 
     * Removes the first item of the linked list and returns it
     * 
     * @exception EmptyStackException if nothing in list 
     * @return Object temp the item at the beggining of the list (top of stack)
     */
    public Object pop(){
        if (isEmpty()){
            throw new EmptyStackException();
        }else{
            Object temp = items.get(0);
            items.remove(temp);
            return temp;
        }
    }
    
    /**
     * 
     * Returns the item at the front of the list (top of the stack)
     * 
     * @exception EmptyStackException if nothing in list
     * @return Object temp the item at the beggining of the list (top of stack)
     */
    public Object peek(){
        if (isEmpty()){
            throw new EmptyStackException();
        }else{
            return items.get(0);
        }
    }
}
