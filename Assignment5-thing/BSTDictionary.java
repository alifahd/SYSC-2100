/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nazifa Tanzim
 */
public class BSTDictionary<String, SortableString> implements Dictionary{
    private BSTNode root;
    
    // Constructor
    public BSTDictionary(){
        // Initializes root of the tree to null
        this.root = null;
    }
    
    /**
     * 
     * Uses helper function to determine the node that
     * contains the requested key
     * 
     * @param key
     * @return the element of the requested key
     */
    @Override
    public Object search(Sortable key) {
        // Declaring a variable that will hold the node 
        //that contains the requested key
        BSTNode node = searchHelper(root, key);
        
        // Returns null if the node is null
        if(node == null){
            return null;
        }
        // Otherwise it returns the element contained in the node
        else{
            // Retrieving the element from the node
            return node.getElement();
        }        
    }
    
    /**
     * 
     * Helper method to recursively search the dictionary
     * until it finds a node with the corresponding key
     * 
     * @param n
     * @param key
     * @return the node with the desired key
     */
    private BSTNode searchHelper(BSTNode n, Sortable key){
        // Returns n if it is null or if it is the node we are looking for
        if((n == null) || (key.compareTo(n.getKey()) == 0)){
            return n;
        }
        // Searches the left side of the tree if the key is
        // found to be in the left side of the tree
        else if (key.compareTo(n.getKey()) < 0) {
            // Recursively searches tree
            return searchHelper(n.getLeft(), key);
        }
        // Searches the right side of the tree if the key is
        // found to be in the righ side of the tree
        else{
            // Recursively searches tree
            return searchHelper(n.getRight(), key);
        }
    }
    
    /**
     * 
     * Calls a helper function to insert a new node containing a key and element
     * into the tree
     * 
     * @param key
     * @param element 
     */
    @Override
    public void insert(Sortable key, Object element) {
        // Calls insertHelper to insert the key and element in the node
        root = insertHelper(root, key, element);
    }

    /**
     * 
     * Helper function that recursively traverses the tree to find
     * the appropriate position to insert the new node
     * 
     * @param n
     * @param key
     * @param element
     * @return 
     */
    private BSTNode insertHelper(BSTNode n, Sortable key, Object element){
        // If the tree is empty, then a new node will be create to become
        // the root of the tree
        if(n == null){
            // Creating a new node
            n = new BSTNode(key, element, null, null);
        }
        // If the current node has the desired key, then it will update the 
        // element
        else if(key.compareTo(n.getKey()) == 0){
            // Setting the new element
            n.setElement(element);
        }
        // If the passed key is less than the key of the current node, then
        // the passed key and element will be inserted on the left
        else if(key.compareTo(n.getKey()) < 0){
            // Setting the left side of the node to the key and element
            n.setLeft(insertHelper(n.getLeft(), key, element));
        }
        // If the passed key is greater than the key of the current node, then
        // the passed key and element will be inserted on the right
        else{
            // Setting the right side of the node to the key and element
            n.setRight(insertHelper(n.getRight(), key, element));
        }
        // Returning the inserted node
        return n;
    }
    
    /**
     * 
     * Calls a helper function to remove a node from the tree
     * 
     * @param key 
     */
    @Override
    public void delete(Sortable key) {
        // Calls deleteHelper to delete the node with the passed key
        // from the tree
        root = deleteHelper(root, key);
    }

    /**
     * 
     * Helper function that recursively selects the node to be deleted
     * 
     * @param n
     * @param key
     * @return 
     */
    private BSTNode deleteHelper(BSTNode n, Sortable key){
        // If the node is null then return null
        if(n == null){
            return null;
        }
        // If the current node contains the key that we want to remove, then
        // it will be removed
        else if(key.compareTo(n.getKey()) == 0){
            // Calling helper function to remove the node
            n = remove(n);
        }
        // If the key to be removed is less than the current key, then search
        // the left side of the tree to find the node to be removed
        else if(key.compareTo(n.getKey()) < 0){
            n.setLeft(deleteHelper(n.getLeft(),key));
        }
        // Otherwise, check the right side for the node to be removed
        else{
            n.setRight(deleteHelper(n.getRight(),key));
        }
        // Returning the tree with the selected node deleted
        return n;
    }
    
    /**
     * 
     * Second helper function that actually deletes the selected node
     * 
     * @param n
     * @return 
     */
    private BSTNode remove(BSTNode n){
        // Holds the current node
        BSTNode node = n;
        
        // If there are no nodes on the left side then return the right node
        if(n.getLeft() == null){
            // Returning right node
            return n.getRight();
        }
        // If there are no nodes on the right side then return the left node
        else if(n.getRight() == null){
            // Returning left node
            return n.getLeft();
        }
        // Deleting surrent node
        else{
            // Setting a temporary node that contains the right node
            BSTNode temp = n.getRight();
            // Seeting the previous node equal to the current node
            BSTNode prev = node;
            
            // Continually updates the prev and temp nodes until 
            // the end of the branch is reached
            while(temp.getLeft() != null){
                prev = temp;
                temp = temp.getLeft();
            }
            // Setting the node to the left of temp to the left of the current node
            temp.setLeft(node.getLeft());
            
            // If the prev node is not the current node then its left value will
            // become the right of temp
            if(prev != node){
                prev.setLeft(temp.getRight());
            }
            // Otherwise the right of prev will become the right of temp
            else{
                prev.setRight(temp.getRight());
            }
            // The right of temp will also be the right of the current node
            temp.setRight(node.getRight());
            
            // Returning temp which now contains the remaining tree without the passed node
            return temp;
        }
    }
    
    /**
     * 
     * Calls helper function to help print the tree
     * 
     */
    @Override
    public void printTree() {
        // Calling print TreeHelper
        printTreeHelper(root);
        System.out.println();
    }

    /**
     * 
     * Helper function that will recursively print the left side of the tree
     * and then the right right side
     * 
     * @param n 
     */
    private void printTreeHelper(BSTNode n){
        // Returns if the current node is null, i.e. end of a branch
        if(n == null){
            return;
        }
        else{
            // Printing the left side of the the tree
            printTreeHelper(n.getLeft());
            // Printing the current node
            System.out.print(n.getKey() + " ");
            // Printing the right side of the tree
            printTreeHelper(n.getRight());
        }
        
    }
    
    /**
     * 
     * Uses helper function to determine the depth (maximum height) of the tree
     * 
     * @return 
     */
    @Override
    public int depth() {
        // Calling helper function to determine the depth of the tree recursively
        return depthHelper(root);
    }
    
    /**
     * 
     * Helper function that recursively determines the depth of the tree by
     * comparing the depths of the left and right sides
     * 
     * @param n
     * @return 
     */
    private int depthHelper(BSTNode n){
        // If a leaf has been encountered, 
        if(n == null){
            return 0;
        }
        else{
            // Traversing through the tree and comparing both sides of the tree
            // to see which one has a greater height
            return 1 + Math.max(depthHelper(n.getLeft()), depthHelper(n.getRight()));
        }
    }

}