/**
 * Takes a mathematical expression and calculates it after converting it
 * to a postfix representation.
 *
 * @author Ali Fahd
 * @version March 8, 2020
 */
public class InfixCalculator
{
    private String input;   //users input expression in infix form
    private String postfix; //expression in prefix form
    /**
     * Constructor for objects of class InfixCalculator
     * trims the user input and intializes variables, calls procedure.
     */
    public InfixCalculator(String input)
    {
        this.input = input.trim();  //removes unnecessary spaces
        postfix = new String();     //initalizes    
        evaluateInFix();            //calls procedure
    }

    /**
     * evaluates the expression once it converts it to postfix using the
     * stack class created.
     */
    public void evaluateInFix()
    {
        //method to convert infix to postfix expression
        convertPostfix();
        //creating a new temporary stack to keep the order
        StackListBased temp1 = new StackListBased();
        String tempString = ""; //used for multi-digit numbers
        //looping through postfix string
        for (int j = 0; j < postfix.length(); j++){
            char ch = postfix.charAt(j);//set up the current character
            if (ch == ' '){//do nothing if its a space
            }else if(Character.isDigit(ch)){//if its a number
                if (Character.isDigit(postfix.charAt(j+1))){//if its multi-digit
                    //append the number to a string
                    tempString = tempString + ch;
                }else{//if its single digit or the last digit in a multi digit number
                    tempString += ch;   //append to the string
                    temp1.push(tempString); //put it on the stack
                    tempString = "";    //reset the string for new numbers
                }
            }else{//if its an operator
                //takes the top two numbers on the stack and makes them floats
                //so that we can do calculations with them
                float operand2 = Float.parseFloat(temp1.pop().toString());
                float operand1 = Float.parseFloat(temp1.pop().toString());
                switch(ch){
                    // Performs addition and throws it back on the stack
                    case('+'):
                        temp1.push(operand1 + operand2);
                        break;
                    //Perfoms subtraction
                    case('-'):
                        temp1.push(operand1 - operand2);
                        break;
                    // Performs multiplication
                    case('*'):
                        temp1.push(operand1 * operand2);
                        break;
                    // Performs division
                    case('/'):
                        temp1.push(operand1 / operand2);
                        break;
                }
            }
        }
        //prints the infix, postfix and result
        System.out.println("infix: " + input);
        System.out.println("postfix: " + getPostfix());
        System.out.println("result: " + Math.round(Float.parseFloat(temp1.pop().toString())));
    }
    
    /**
     * converts the infix expression to a postfix expression without brackets
     */
    public void convertPostfix()
    {
        //creates new temporary empty stack
        StackListBased temp = new StackListBased();
        //loop through the infix expression
        for (int i = 0; i < input.length(); i++){
            char ch = input.charAt(i);  //set up the character we are at
            if (ch == ' '){//do nothing if its space
            }else if(Character.isDigit(ch)){//if its a number
                if (i == input.length() -1){//last number
                    postfix = postfix + ch + " ";//add a space
                }else if (Character.isDigit(input.charAt(i+1))){//multi digit number
                    postfix = postfix + ch;//don't add as space
                }else{//signle digit or the last number in a multi digit number
                    postfix = postfix + ch + " ";//add a space
                }
            }else if(ch == '('){//open bracket
                temp.push(ch);//put it on the stack
            }else if(ch == ')'){//close bracket
                //loop until you reach the corresponding open bracket
                while(temp.peek() != (Object) '('){
                    //add the operator to the postfix expression
                    postfix = postfix + temp.pop() + " ";
                }
                temp.pop();//remove the corresponding open bracket
            }else{//its an operator
                // Check the order of the operations
                while((!temp.isEmpty()) && (temp.peek() != (Object) '(') && (precedence(ch) <= precedence((char) temp.peek()))){
                    postfix = postfix + temp.pop() + " ";
                }
                // Pushes the current operator onto stack
                temp.push(ch);
            }
        }
        //appends the remaining operators from temp onto the expression
        while (!temp.isEmpty()){
            postfix = postfix + temp.pop() + " ";
        }
        postfix = postfix.trim();//removes unnecessary spaces from the expression
    }
    
    /**
     * returns the postfix expression
     * 
     * @return String the postfix expression
     */
    public String getPostfix()
    {
        return postfix;
    }
    
    /**
     * returns a number depending on the operation given
     * 
     * @param opr the operator
     * @return int the 1 if its multiplication or division, 0 for add and sub
     */
    private int precedence(char opr){
        if(opr == '+' || opr == '-'){//if operator is + or - 
            return 0;   //return 0
        }else if (opr == '*' || opr == '/'){// If the operator is * or / 
            return 1;   //return 1
        }
        return -1;
    }
}
