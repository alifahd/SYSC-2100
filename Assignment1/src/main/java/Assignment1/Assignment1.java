/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment1;

/**
 *
 * @author Ali Fahd
 * @date Jan 31, 2020
 */
public class Assignment1 {

    /**
     * Question 1 - Mr. Spock's Dilemma
     *
     * @param n number of planets in solar system
     * @param k number of planets that can be visited
     * @return the number of groups of k planets chosen from n
     */
    public static int c(int n, int k) {
        //base case if k is 0 or k is n, no time to visit any planets or time to visit all planets
        if ((k == 0)||(k == n)){
            //returns 1
            System.out.printf("c(%d,%d) = 1\n", n, k);
            return 1;
        }else if (k > n){//base case if k is more than n, more time to visit planets than planets in the solar system
            //returns 0
            System.out.printf("c(%d,%d) = 0\n", n, k);
            return 0;
        }else{
            System.out.printf("c(%d,%d) = c(%d,%d) + c(%d,%d)\n", n, k, n - 1, k - 1, n - 1, k);
            return (c(n - 1, k - 1) + c(n - 1, k));
        }
    }

    /**
     * Question 2 - Organizing a Parade
     *
     * @param n length of parade
     * @return number of acceptable parades of length n
     */
    public static int P(int n) {
        //base case of parade with length 1
        if (n == 1) {
            //return 2, 1 float or 1 band
            System.out.printf("P(%d) = 2\n", n);
            return 2;
        }else if (n == 2){//base case if length is 2
            //returns 3 for the possible combinations of float-float, bandfloat, and float-band
            System.out.printf("P(%d) = 3\n", n);
            return 3;
        }else{
            //recursive call
            System.out.printf("P(%d) = P(%d) + P(%d)\n", n, n - 1, n - 2);
            return P(n - 1) + P(n - 2);
        }
    }

    /**
     * Question 3 a
     *
     * @param c character to print
     * @param n number of times c will be printed
     */
    public static void writeLine(char c, int n){
        //base case for number of itmes printed is 0
        if (n == 0){
            //displays nothing
            System.out.println();
        }else{//prints character if there are more than 0 lines
            //print character
            System.out.print(c);
            //recurseive call to print the number of characters wanted
            writeLine(c, n - 1);
        }
    }

    /**
     * Question 3 b
     *
     * @param c character to print
     * @param n number of times c will print
     * @param m number of lines of n to print
     */
    public static void writeBlock(char c, int n, int m){
        // Only prints the line if there are more than 0 blocks
        if (m >= 1){
            //print a line of cahracters
            writeLine(c, n);
            //recursive call printing next line for as many lines desired
            writeBlock(c, n, m - 1);
        }
    }

    /**
     * Question 4 - Reverse Digits
     *
     * @param number integers that will be reversed
     */
    public static void reverseDigits(int number) {
        //prints last digit taken from number or if input is simply one digit long
        if (number < 10){
            System.out.print(number);
        } else{
            //last digit of the number 
            int last = number % 10;
            //recursive call that goes to base case to print last digit
            reverseDigits(last);
            //removes last digit from number
            number /= 10;
            //recursive call for part of number left
            reverseDigits(number);
        }
    }

    /**
     * Question 5 - Binary Search
     *
     * @param anArray which consists of string in an alphabetical order
     * @param first
     * @param last
     * @param value
     * @return
     */
    public static int myBinarySearch(String[] anArray, int first, int last, String value) {
        //index trackers in array for mathcing value
        int index;
        //value not in original array
        if (first > last) {
            index = -1;
        }else{//search array for value
            //vairable for middle point
            int mid = (first + last) / 2;
            //check if value is at the middle of the array
            if (value == anArray[mid]) {
                index = mid;    //value is at mid
            }else if ((value.compareTo(anArray[mid])) < 0){
                 //checker for value in first half of array
                 //recursive calls with index before middle becomes last
                index = myBinarySearch(anArray, first, mid - 1, value);
            }else{//Value will be in first half of the array
                //recursive calls with index after middle becomes first
                index = myBinarySearch(anArray, mid + 1, last, value);
            }
        }
        return index;
        //end binary search
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
           System.out.print(c(3,2)+"\n\n");
           System.out.print(P(4)+"\n\n");
           writeLine('*', 5);
           System.out.print("\n\n");
           writeBlock('*', 5, 3);
           System.out.print("\n\n");
           reverseDigits(123456789);
           String []myArray = {"abc","def", "ghi", "jkl", "mno", "pqr", "stu", "vwx", "yzm"};
           System.out.print("\n\nIndex of value is " + myBinarySearch(myArray,0,8,"def"));
    }
    
    
}
