/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CountSubstrings;

import java.util.*;
import java.io.*;
/**
 *
 * @author Ali Fahd
 * @date Feb. 14, 2020
 */
public class CountSubstrings {
    private String filename;
    private String pattern;
    private int ALcount;
    private int LLcount;
    private List<Character> patternChars;
    private List<Character> text;
    private List<String[]> words;
    
    public CountSubstrings() {
        
        process();
    }
    
    public void process(){
        Scanner scn = new Scanner(System.in);
        System.out.println("Please enter the path for the input file: ");
        String filename = scn.nextLine();
        System.out.println("Enter the pattern to look for: ");
        String pattern = scn.nextLine();
        this.filename = filename;
        this.pattern = pattern;
        ALcount = 0;
        LLcount = 0;
        words = new ArrayList<String[]>();
        BufferedReader reader = null;
        try
        {   
            // Declaring and initializing variables
            Reader file = new FileReader(filename);
            reader = new BufferedReader(file);
            String line;
            
            // Iterates through each line (not including title lines)
            while((line = reader.readLine()) != null)
            {
                words.add(line.split(" "));
            }
            // Closes reader
            reader.close();
        }    
        catch (IOException e){
            e.printStackTrace();
        }
        
        patternChars = strToCharAL(pattern);
        
        long startAL = System.currentTimeMillis();
        
        for(String[] wordList : words) {
            for(String word : wordList) {
                text = strToCharAL(word);
                if (findBrute(text, patternChars) != -1){
                    ALcount++;
                }
            }
        }
        
        long finishAL = System.currentTimeMillis();      
        long timeElapsedAL = finishAL - startAL;
        
        System.out.print("Using ArrayLists: " + ALcount + "matches, derived in " + timeElapsedAL + "milliseconds.");
        
        long startLL = System.currentTimeMillis();
        
        for(String[] wordList : words) {
            for(String word : wordList) {
                text = strToCharLL(word);
                if (findBrute(text, patternChars) != -1){
                    LLcount++;
                }
            }
        }

        long finishLL = System.currentTimeMillis();      
        long timeElapsedLL = finishLL - startLL;
        
        System.out.print("Using LinkedLists: " + LLcount + "matches, derived in " + timeElapsedLL + "milliseconds.");
        
    }
    
    private ArrayList<Character> strToCharAL(String word) {
        ArrayList<Character> list = new ArrayList<>();
        for(int i = 0; i < word.length(); i++){
            list.add(word.charAt(i));
        }
        return list;
    }
    private LinkedList<Character> strToCharLL(String word) {
        LinkedList<Character> list = new LinkedList<>();
        for(int i = 0; i < word.length(); i++){
            list.push(word.charAt(i));
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
            // try every starting index 						     // within text
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
