import java.util.*;
/**
 * First Question: Recursive methods of selection and bubble sorts
 * Second Question: Checks if word is in the given L language
 * Third Question: Removes any spaces between digits
 *
 * @author Ali Fahd
 * @version March 23, 2020
 */
public class Assignment4
{
    //Question 1
    /**
     * 
     * Method will recursively sort an array using the selection sort
     * algorithm
     * 
     * @param <T>
     * @param theArray - array given that needs sorting
     * @param int n - size of array
     */
    public static <T extends Comparable<? super T>> void recursiveSelectionSort(T[] theArray, int n) {
        int largest = indexOfLargest(theArray, n);  //variable to hold index of largest value
        int last = n - 1;   //variable to hold last value
        //only one element in the array then leave method
        if(n == 1){
            return;//leave method to prevent an infinite recursive call
        }else if(largest != last){//if largest value is not at the end of the list then put it there
            T temp = theArray[largest]; //temporary variable to hold value
            theArray[largest] = theArray[last];//swap places
            theArray[last] = temp;
        }
        //recursive call without last element
        recursiveSelectionSort(theArray, last);
    }
    
    /**
     *  
     *  Method will recursively sort an array using the bubble sort
     * algorithm
     * 
     * @param <T>
     * @param theArray - array given that needs sorting
     * @param int n - size of array
     */
    public static <T extends Comparable<? super T>> void recursiveBubbleSort(T[] theArray, int n) {
        if(n == 1){
            return;//leave method to prevent an infinite recursive call
        }else{//pass through array swapping values if previous value is greater
            for(int i = 1; i < n; i++){
                if(theArray[i - 1].compareTo(theArray[i]) > 0){//prev value is greater than current one
                    //swap values
                    T temp = theArray[i - 1];
                    theArray[i - 1] = theArray[i];
                    theArray[i] = temp;
                }
            }
        }
        //recursive call without the last element for the next pass through
        recursiveBubbleSort(theArray, n - 1);
    }
            
    /**
     * 
     * Method will return the index of the largest element in an array
     * 
     * @param <T>
     * @param theArray - given array which will find value from
     * @param int size - the size of the array
     * @return int indexSoFar - index of largest value in the array
     */
    private static <T extends Comparable<? super T>> int indexOfLargest(T[] theArray, int size){
        int indexSoFar = 0;//default value for largest index
        //loop through array
        for(int currIndex = 1; currIndex < size; currIndex++){
            if(theArray[currIndex].compareTo(theArray[indexSoFar]) > 0){//if current value is greater than the previous value
                indexSoFar = currIndex; //update varaible to new position with largest value
            }
        }
        return indexSoFar;//return largest index
    }
    
    
    //Question 2
    /**
     * 
     * Method will determine if a given string is in the L language w$w'
     * (w' is the reverse of w)
     * 
     * @param String str - given string by user assuming it contains $
     * @return boolean - true if it is false if it isn't in language
     */
    public static boolean isInLanguage (String str){
        Stack s = new Stack();  //empty stack
        Queue q = new LinkedList(); //empty queue
        
        //if an emptry string is passed return false becuase no '$'
        if(str.isEmpty()){
            return false;
        }
        
        //loop thorugh the string until you get to the '$'
         while(str.charAt(0) != '$'){
            q.add(str.charAt(0));   //add first character of part 'w' to the queue
            str = str.substring(1); //get rid of first character in string
        }
        str = str.substring(1);//remove the '$'
        //loop through for part 'w''
        for (int i = 0; i < str.length(); i++){
            s.push(str.charAt(i));  //add each character to stack
        }
        
        //if the 'w' size doesn't match 'w'' size then it can't be in the language
        //ex abc$cbad --- w size 3 and w' size 4
        if(q.size() != s.size()){
            return false;
        }
        
        //loop through the entire q (same as looping through entire stack if they're the same size)
        while (!q.isEmpty()){
            if (!q.remove().equals(s.pop())){//keep removing elements after comparing
                return false;//word is not in language if the top of the stack doesn't equal the front of the list
            }
        }
        return true;//word is in language if front of queue always matched top of stack
    }
    
    
    //Question 3
    /**
     * 
     * Method takes a string and converts all the digits into a number
     * removes all spaces between digits essentially
     * 
     * @param String str - given string
     * @return int num - the resulting number from the string
     */
    public static int convertToNumber(String str) {
        Queue q = new LinkedList(); //emptry queue
        int num = 0;    //current number
        //loop through entire string
        for(char c : str.toCharArray()){
            if(Character.isDigit(c)){//check if the character is a digit
                q.add(c); //if it is add it to the queue
            }
        }
        //loop through the queue
        while(!q.isEmpty()){
            //mutliply the number at the front of the queue by ten and add it to the total held in num
            num *= 10;
            num += Integer.parseInt(q.remove().toString());//remove item from queue make it a string and then and int
        }
        return num;//reutrn the number wihtout any spaces
    }
}