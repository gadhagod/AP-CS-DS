import java.util.Stack;

/**
 * Write a description of class StringUtil here.
 * 
 * @author Anu Datar
 * @version 10/27/2017
 */
public class StringUtil
{
/*
     * Gets the character at at index of a string
     * @param index     The index of the desired character
     * @param str       The string in which the character is to be extracted
     * @return          A string with one character
     */
    private static String getCharAt(int index, String str)
    {
        return str.substring(index, index + 1);
    }

    /**
     * Reverses a string
     * @param str       String to be reversed
     * @return          The reversed str
     */
    public static String reverseString(String str)
    {
        Stack<String> st = new Stack<String>();
        for (int i = 0; i < str.length(); i ++)
        {
            st.push(getCharAt(i, str));
        }
        String result = "";
        while (!st.isEmpty())
        {
            result += st.pop();
        }
        return result;
    }

    /**
     * Checks whether a string is palidrome
     * @param s     The string that's to test whether its a palindrome
     * @return      Whether s is a palindrome
     */
    public static boolean isPalindrome(String s)
    {
        return s.equals(reverseString(s));
    }

    /**
     * Tests whether reverse and isPalindrome works correctly
     * @param args      The command line arguments
     */
    public static void main(String[] args)
    {
        String test =  "racecar";
        String test2 = "notapalindrome";

        if ( !("".equalsIgnoreCase(reverseString(""))) )
            System.out.println("An empty string is palindrome");

        if ( !("a".equalsIgnoreCase(reverseString("a"))) )
            System.out.println("\"a\" is a palindrome");

        if ( !("aa".equalsIgnoreCase(reverseString("aa"))) )
            System.out.println("Error: \"aa\" is a palindrome");

        if (!test.equalsIgnoreCase(reverseString(test)))
            System.out.println("Error: " + test + " is a palindrome");
        else
            System.out.println("Success " + test + " matched " + reverseString(test));

        if (test2.equalsIgnoreCase(reverseString(test2)))
            System.out.println("Error: " + test2 + " is a palindrome");
    }
}
