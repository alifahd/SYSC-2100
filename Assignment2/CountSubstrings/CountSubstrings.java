import java.util.*;
import java.io.*;
import java.nio.*;
/**
 *
 * @author Ali Fahd
 * @date Feb. 14, 2020
 */
public class CountSubstrings {
    private String filename;    //File to read from
    private String pattern;     //Pattern to identify in file
    private int ALcount;        //number of matches by array list
    private int LLcount;        //number of matches by linked list
    private List<Character> patternChars;   //variable to convert pattern from string to char
    private List<Character> text;       //variable to convert each word from string to char     
    private List<String[]> words;     //list of words in the file     
    
    /**
     *  Constructor for class CountSubstrings
     */
    public CountSubstrings() {
        Scanner scn = new Scanner(System.in);
        System.out.println("Please enter the path for the input file: ");
        filename = scn.nextLine();  //gets filename input
        System.out.println("Enter the pattern to look for: ");
        pattern = scn.nextLine();   //gets pattern input
        ALcount = 0;
        LLcount = 0;
        words = new ArrayList<String[]>();    //holds all words in an array list
        process();  //calls the process
    }
    
    /**
     *  Method process executes the assignemnt
     */
    public void process(){
        //loops through each line of the file and adds each word to an arraylist
        try { 
            Scanner sc = new Scanner(new File (filename)); 
            //splits up the line and adds it to words as an array 
            while (sc.hasNextLine()){
                words.add(sc.nextLine().split(" ")); 
            } 
            sc.close(); 
        } catch (IOException e){ 
            e.printStackTrace();
        }
   
        //converts the word we're looking for to characters in an array list
        patternChars = strToCharAL(pattern);
        
        //starts timer for array list
        long startAL = System.currentTimeMillis();
        
        for(String[] arrOfWords : words) {
            for(String word : arrOfWords){
                //converts each words in file to characters
                text = strToCharAL(word);
                //compares pattern and current word
                if (findBrute(text, patternChars) != -1){
                    ALcount++;
                }
            }
        }
        
        //stops timer and calculates the time that has passed
        long finishAL = System.currentTimeMillis();      
        long timeElapsedAL = finishAL - startAL;
        
        System.out.print("Using ArrayLists: " + ALcount + " matches, derived in " + timeElapsedAL + " milliseconds.");
        
        //converts the word we're looking for to characters in a linked list
        patternChars = strToCharLL(pattern);
        
        //starts timer for linked list
        long startLL = System.currentTimeMillis();
        
        for(String[] arrOfWords : words) {
            for(String word : arrOfWords){
                //converts each words in file to characters
                text = strToCharLL(word);
                //compares pattern and current word
                if (findBrute(text, patternChars) != -1){
                    LLcount++;
                }
            }
        }
        
        //stops timer and calculates the time that has passed
        long finishLL = System.currentTimeMillis();      
        long timeElapsedLL = finishLL - startLL;
        
        System.out.print("\nUsing LinkedLists: " + LLcount + " matches, derived in " + timeElapsedLL + " milliseconds.");
        
    }
    
    /**
     *  Method converts a given word to a an array list with a 
     *  letter at each index
     *  
     *  @param String word - the word that needs to be converted to an array list
     *  @return ArrayList list - the list of characters in the word given
     */
    private ArrayList<Character> strToCharAL(String word) {
        ArrayList<Character> list = new ArrayList<>();
        for(int i = 0; i < word.length(); i++){
            list.add(word.charAt(i));
        }
        return list;
    }
    
    /**
     *  Method converts a given word to a a linked list with a 
     *  letter at each index
     *  
     *  @param String word - the word that needs to be converted to a linked list
     *  @return LinkedList list - the list of characters in the word given
     */
    private LinkedList<Character> strToCharLL(String word) {
        LinkedList<Character> list = new LinkedList<>();
        for(int i = 0; i < word.length(); i++){
            list.add(word.charAt(i));
        }
        return list;
    }
    
    /*
     * Returns the lowest index at which substring pattern begins in text (or
     * else -1).
     */
    private static int findBrute(List<Character> text, List<Character> pattern) {
        int n = text.size();
        int m = pattern.size();
        for (int i = 0; i <= n - m; i++) { 
            // try every starting index                              // within text
            int k = 0; // k is index into pattern
            while (k < m && text.get(i + k) == pattern.get(k))
            // kth character of pattern matches
                k++;
            if (k == m) // if we reach the end of the pattern,
                return i; // substring text[i..i+m-1] is a match
        }
        return -1; // search failed
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
    }
       
}