import java.util.*;
/**
 * Write a description of class tst here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class tst
{
    /**
     * Constructor for objects of class tst
     */
    public tst()
    {
        System.out.println(isInLanguage("abc$dcba"));
    }

    /**
     * 
     * Method will determine if given string is in the language w$w'
     * Where w' is the reverse of w
     * For example, if w = abc, then  w' = cba
     * 
     * @param str method assumes str contains $
     * @return 
     */
    public static boolean isInLanguage (String str){
        Stack s = new Stack();
        Queue q = new LinkedList();
        if(str.isEmpty()){
            return false;
        }
        
         while(str.charAt(0) != '$'){
            // Adding first value of str to queue and removing it from the string
            q.add(str.charAt(0));
            str = str.substring(1);
        }
        str = str.substring(1);
        for (int i = 0; i < str.length(); i++){
            s.push(str.charAt(i));
        }
        
        if(q.size() != s.size()){
            return false;
        }
        
        while (!q.isEmpty() && !s.isEmpty()){
            if (!q.remove().equals(s.pop())){
                return false;
            }
        }
        return true;
    }
}
