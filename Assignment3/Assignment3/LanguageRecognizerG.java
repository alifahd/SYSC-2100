
/**
 * Takes a word and tells the user if it is in the given G language or not.
 *
 * @author Ali Fahd
 * @version March 8, 2020
 */
public class LanguageRecognizerG
{
    private String word;    //variable that is users input

    /**
     * Constructor for objects of class LanguageRecognizerG
     * takes the user input and initalizes it to word variable.
     */
    public LanguageRecognizerG(String input)
    {
        word = input;   //word is now user input
        recursivePrintG();  //calls procedure
    }

    /**
     * Method checks the requirements of the word being in the G language
     *
     * @param input the word the user entered
     * @return Boolean True if word is in G language otherwise false
     */
    public boolean recursiveRecogG(String input){
        int size = input.length();  //keep track of the size of the word
        //check if it is an emptry string
        if (input.isEmpty()){
            return true;
        }
        // If the word in 1 character long, it will be true if that word is <E>
        if(size == 1){
            //requirements of <E>
            if(input.equals("&") || input.equals("#")){
                return true;
            }else{
                return false;
            }
        }else if(size >= 2){    //checks other lengths
            //checks if the first letter is <V> then it will only pass
            //the last letter to see if it is <E> otherwise there's not other
            //combinations where the word starts with <V> and is in the G language
            if((input.startsWith("W")) || (input.startsWith("A"))){
                return recursiveRecogG(input.substring(1,size - 1));
            }//otherwise checks if the last character is <V> in which case we need
            //to do a recursive call without the <V> to check the rest
            else if((input.endsWith("W")) || (input.endsWith("A"))){                
                System.out.println(input.substring(0,size - 2));
                return recursiveRecogG(input.substring(0, size - 2));
            }
        }else{//incorrect size
            return false;
        }
        return false;//nothing matches
    }
    
    /**
     * Method prints the result of the check of the word in relation to 
     * the G language
     */
    public void recursivePrintG() {
        if(recursiveRecogG(word)){//if in language output it is
            System.out.println("Word \"" + word + "\" is a word of the G language");
        }else{//if not in langauge output this
            System.out.println("Word \"" + word + "\" is NOT a word of the G language");
        }
    }
}
