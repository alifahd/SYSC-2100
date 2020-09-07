/**
 * Class BSTDictionary
 *
 * @author Ali Fahd
 * @date April 7, 2020
 */
public class BSTDictionary<String, SortableString> implements Dictionary{
    private BSTNode root;
    /**
     * Constructor
     * 
     * set root node to null
     */
    public BSTDictionary(){
        this.root = null; //initialize root of tree
    }
    
    /**
     * method searches for matching key node and retrns element in node
     * 
     * @param key - of node
     * @return - element in node
     */
    public Object search(Sortable key)
    {
        BSTNode node = searchHelper(root, key);//holds node with requested key
        
        //return null if node is null
        if(node == null){
            return null;
        }else{//return element contained in node
            // Retrieving the element from the node
            return node.getElement();
        }    
    }
    
    /**
     * method recursively searches the dictionary until it finds 
     * node with the corresponding key
     * 
     * @param n - node
     * @param key
     * @return node with desired key
     */
    private BSTNode searchHelper(BSTNode n, Sortable key){
        //base case
        //return n if node is null or if it is the node we are looking for
        if((n == null) || (key.compareTo(n.getKey()) == 0)){
            return n;
        }else if (key.compareTo(n.getKey()) < 0) {//search left side of tree
            //recursively search tree
            return searchHelper(n.getLeft(), key);
        }else{//otherwise search right side
            //recursively search tree
            return searchHelper(n.getRight(), key);
        }
    }
    
    /**
     * 
     * method insertns node into tree with gieven key and element
     * 
     * @param key
     * @param - element of node     
    */
    public void insert(Sortable key, Object element) {
        root = insertHelper(root, key, element);    //calls insertHelper method for insertion
    }

    /**
     * 
     * method recursevily searches tree to find apprpriate postion to insert
     * 
     * @param n - node
     * @param key- of node
     * @param element - in node
     * @return n
     */
    private BSTNode insertHelper(BSTNode n, Sortable key, Object element){
        //base case
        //if empty tree create new node as the root of the tree
        if(n == null){
            n = new BSTNode(key, element, null, null);//root
        }else if(key.compareTo(n.getKey()) == 0){//if current node has the key
            n.setElement(element);//set new element
        }else if(key.compareTo(n.getKey()) < 0){//key is less than key of current node
            n.setLeft(insertHelper(n.getLeft(), key, element));//set left side to key and element
        }else{//key is greater than key of current node
            n.setRight(insertHelper(n.getRight(), key, element));//key and element inserted on right
        }
        return n;
    }
    
    /**
     * 
     * method calls helper function to remove node from tree
     * 
     * @param key 
     */
    @Override
    public void delete(Sortable key) {
        //calls deleterHelper function to delete node from tree
        root = deleteHelper(root, key);
    }

    /**
     * 
     * method recursevily selescts node to be deleted
     * 
     * @param n - node
     * @param key- of node
     * @return n
     */
    private BSTNode deleteHelper(BSTNode n, Sortable key){
        //if node is null return null
        if(n == null){
            return null;
        }else if(key.compareTo(n.getKey()) == 0){//if node has desired key
            n = remove(n);  //remove node
        }else if(key.compareTo(n.getKey()) < 0){//if key less than current key
            n.setLeft(deleteHelper(n.getLeft(),key));//check left side
        }else{//if key greater than current key
            n.setRight(deleteHelper(n.getRight(),key));//check right side
        }
        return n;//return tree with selected node deleted
    }
    
    /**
     * method actually removes node to be deleted
     * 
     * @param n - the node
     * @return 
     */
    private BSTNode remove(BSTNode n){
        BSTNode node = n;//holds current node
        
        if(n.getLeft() == null){//no nodes on left side
            return n.getRight();//return right node
        }else if(n.getRight() == null){//no nodes on right side
            return n.getLeft();//return left node
        }else{//delete current node
            //temporary node that contains the right node
            BSTNode temp = n.getRight();
            //previous node equal to the current node
            BSTNode prev = node;
            //update prev and temp nodes until end of branch
            while(temp.getLeft() != null){
                prev = temp;
                temp = temp.getLeft();
            }
            //set the node to the left of temp to the left of the current node
            temp.setLeft(node.getLeft());
            
            // If the prev node is not the current node then its left value will
            // become the right of temp
            if(prev != node){//previous node not current node
                prev.setLeft(temp.getRight());//left value will become the right of temp
            }else{
                prev.setRight(temp.getRight());//right of prev is right of temp
            }
            //right of temp will be right of current node
            temp.setRight(node.getRight());
            
            //return remaining tree without passed node
            return temp;
        }
    }
    
    /**
     * 
     * method calls helper function to print the tree
     * 
     */
    public void printTree() {
        printTreeHelper(root);//call tree helper function
        System.out.println();
    }

    /**
     * 
     *  method recursevily prints left side and right side of tree
     * 
     * @param n - node
     */
    private void printTreeHelper(BSTNode n){
        if(n == null){//end of branch
            return;//leave method
        }
        else{
            printTreeHelper(n.getLeft());//left side of tree
            System.out.print(n.getKey() + " ");//current node
            printTreeHelper(n.getRight());//right side of tree
        }
    }
    
    /**
     * 
     * method calls helper function to determine the depth (maximum height) 
     * of the tree
     * 
     * @return 
     */
    public int depth() {
        return depthHelper(root);//csll depth helper method
    }
    
    /**
     * 
     * method recursively determines depth of tree
     * comparing left and right side depths
     * 
     * @param n - node
     * @return depth number
     */
    private int depthHelper(BSTNode n){
        //if a leaf has been encountered, 
        if(n == null){
            return 0;
        }
        else{
            //traverse through the tree comparing both sides of the tree
            //to see which one has a greater height
            return 1 + Math.max(depthHelper(n.getLeft()), depthHelper(n.getRight()));
        }
    }
}
