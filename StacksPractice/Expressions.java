import java.lang.Integer;
import java.util.ListIterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Expressions evauluates mathematical expressions
 * 
 * @author Anu Datar, Aarav Borthakur
 * @version 11/07/2022
 */

public class Expressions
{
    /**
     * Converts an array of strings to a (Linked) list of strings (with spaces and empties removed)
     * @param arr       The array to be converted
     * @return          The new Linked List
     */
    private static List<String> arrToList(String[] arr)
    {
        LinkedList<String> list = new LinkedList<String>();
        for (int i = 0; i < arr.length; i++)
        {
            if (!arr[i].equals(" ") && !arr[i].equals(""))
            {
                list.add(arr[i]);
            }
        }
        return list;
    }

    /**
     * Gets a list of tokens in an expression
     * @param expr  The expression to tokenize
     * @return      The list of tokens
     */
    private static List<String> getTokens(String expr)
    {
        expr = expr.replace("}", " } ").replace("]", " ] ").replace("{", " { ").replace("[", " [ ");
        expr = expr.replace("(", " ( ").replace(")", " ) ").replace(")", " ) ").replace("  ", " ");
        return arrToList(expr.split(" "));
    }

    /**
     * Applies an operator to a pair of numbers
     * @param first     The right side number in the expression
     * @param second    The left side number in the expression
     * @param operator  The operator of the expression
     * @return          The value of the expression
     */
    private static int applyOperator(int first, int second, String operator)
    {
        if (operator.equals("+"))
        {
            return first + second;
        }
        if (operator.equals("-"))
        {
            return first - second;
        }
        if (operator.equals("*"))
        {
            return first * second;
        }
        if (operator.equals("/"))
        {
            return first / second;
        }
        return first % second;
    }

    /**
     * Checks whether an operator has high precedance in an expression
     * @return      Whether an operator has high precedance in an expression
     */
    private static boolean hasHighPrecedance(String operator)
    {
        return operator.equals("*") || operator.equals("/") || operator.equals("%");
    }

    /**
     * Checks whether an operator has low precedance in an expression
     * @return      Whether an operator has low precedance in an expression
     */
    private static boolean hasLowPrecedance(String operator)
    {
        return operator.equals("+") || operator.equals("-");
    }

    /**
     * Checks whether an operator has higher precedance in an expression than another
     * @param first     The first operator
     * @param second    The second operator
     * @return          Whether the first has a higher precedence over second
     */
    private static boolean hasHigherPrecedance(String first, String second)
    {
        return hasHighPrecedance(first) && hasLowPrecedance(second);
    }

    /**
     * Checks whether an operator has equal precedance in an expression as another
     * @param first     The first operator
     * @param second    The second operator
     * @return          Whether the first has an equal precedence over second
     */
    private static boolean hasEqualPrecedance(String first, String second)
    {
        return (hasHighPrecedance(first) && hasHighPrecedance(second)) || (hasLowPrecedance(first) && hasLowPrecedance(second)) ;
    }

    /**
     * Checks whether an operator has lower precedance in an expression than another
     * @param first     The first operator
     * @param second    The second operator
     * @return          Whether the first has a lower precedence over second
     */
    private static boolean hasLowerPrecedance(String first, String second)
    {
        return !hasEqualPrecedance(first, second) && !hasHigherPrecedance(first, second);
    }

    /**
     * Checks whether a String is an operator
     * @param character The character to test
     * @return          Whether a String is an operator
     */
    private static boolean isOperator(String character)
    {
        return character.equals("+") || character.equals("-") || character.equals("*") || character.equals("/") || character.equals("%");
    }

    /**
     * Checks whether a String is a space
     * @param character The character to test
     * @return          Whether a String is an space
     */
    private static boolean isSpace(String character)
    {
        return character.equals(" ");
    }

    /**
     * Checks whether a String is an operand
     * @param character The character to test
     * @return          Whether a String is an operand
     */
    private static boolean isOperand(String character)
    {
        return !isSpace(character) && !isOperator(character) && !isCloser(character) && !isOpener(character);
    }

    /**
     * Checks whether a String is an opener
     * @param character The character to test
     * @return          Whether a String is an opener
     */
    private static boolean isOpener(String character)
    {
        return character.equals("(") || character.equals("[") || character.equals("{");
    }

    /**
     * Checks whether a String is a closer
     * @param character The character to test
     * @return          Whether a String is a closer
     */
    private static boolean isCloser(String character)
    {
        return character.equals(")") || character.equals("]") || character.equals("}");
    }

    /**
     * Gets the corresponding closer to an opener
     * @param opener    The opener to get the closer of
     * @return          The closer
     */
    private static String getCloser(String opener){
        if (opener.equals("{"))
        {
            return "}";
        }
        if (opener.equals("["))
        {
            return "]";
        }
        return ")";
    }

    /**
     * Adds an operator to a stack or to the result 
     * @param operator      The operator to be tested
     * @param st            The stack which stores the operators and operands
     * @param result        The resulting expression
     */
    private static String testOperator(String operator, Stack<String> st, String result)
    {
        if (st.isEmpty() || isOpener(st.peek()))
        {
            st.push(operator);
        }
        else if (hasLowerPrecedance(operator, st.peek()))
        {
            result += st.pop() + " ";
            result = testOperator(operator, st, result);
            
        }
        else if (hasHigherPrecedance(operator, st.peek()))
        {
            st.push(operator);
        }
        else if (hasEqualPrecedance(operator, st.peek()))
        {
            result += st.pop() + " ";
            st.push(operator);
        }
        return result;
    }

    /*
     * Parenthesis matching : An expression is said to be balanced if
     * every opener has a corresponding closer, in the right order
     * {, [ or ( are the only types of brackets allowed
     * @param   expression containing operands operators 
     *          and any of the 3 supported brackets
     * @return  true is the parenthesis are balanced         
     *          false otherwise
     */
    public static boolean matchParenthesis(String expression)
    {
        List<String> chars = getTokens(expression);
        ListIterator<String> it = chars.listIterator();
        Stack<String> st = new Stack<String>();
        while (it.hasNext())
        {
            String current = (String) it.next();
            if (isOpener(current))
            {
                st.push(current);
            } 
            else if (isCloser(current))
            {
                if (st.isEmpty())
                {   
                    return false;
                }
                String last = st.pop();
                if (!current.equals(getCloser(last)))
                {
                    return false;
                }
            }
        }
        return st.isEmpty();
    }
    /**
     * Returns a string in postfix form 
     * if given an expression in infix form as a parameter
     * does this conversion using a Stack
     * @param expr valid expression in infix form
     * @return equivalent expression in postfix form
     */
    public static String infixToPostfix(String expr)
    {
        String result = "";
        List<String> chars = getTokens(expr);
        ListIterator<String> it = chars.listIterator();
        Stack<String> st = new Stack<String>();
        while (it.hasNext())
        {
            String current = (String) it.next();
            if (isOperand(current))
            {
                result += current + " ";
            }
            else if (isOperator(current))
            {
                result = testOperator(current, st, result);
            } 
            else if (isCloser(current))
            {
                String last = st.pop();
                while (!isOpener(last))
                {
                    result += last + " ";
                    last = st.pop();
                }
            } 
            else if (isOpener(current))
            {
                st.push(current);
            }
        }
        while (!st.isEmpty())
        {
            result += st.pop() + " ";
        }
        return result;
    }

    /**
     * Returns the value of an expression in postfix form
     * does this computation using a Stack
     * @param expr valid expression in postfix form
     * @return value of the expression
     * @precondition postfix expression  
     *                contains numbers and operators + - * / and %
     *                and that operands and operators are separated by spaces
     */
    public static double evalPostfix(String expr)
    {
        List<String> chars = getTokens(expr);
        ListIterator<String> it = chars.listIterator();
        Stack<Integer> st = new Stack<Integer>();

        while (it.hasNext())
        {
            String current = (String) it.next();
            if (isOperand(current))
            {
                st.push(Integer.parseInt(current));
            }
            else if (isOperator(current))
            {
                int second = st.pop();
                int first = st.pop();
                st.push(applyOperator(first, second, current));
            }
        }        

        return st.pop();
    }

    /**
     * Tester to check if infix to postfix and evaluate postfix work well
     * @param args      The command line arguments
     */
    public static void main(String[] args)
    {
        String exp = "2 + 3 * 4";
        test(exp, 14);
        
        exp = "8 * 12 / 2";
        test(exp, 48);

        
        exp = "5 % 2 + 3 * 2 - 4 / 2";
        test(exp, 5);   

        testBalanced("{ 2 + 3 } * ( 4 + 3 )", true);
        testBalanced("} 4 + 4 { * ( 4 + 3 )", false);
        testBalanced("[ [ [ ] ]", false);
        testBalanced("{ ( } )", false);
        testBalanced("( ( ( ) ) )", true);
    }

    /**
     * Checks if infixToPostfix works properly
     * @param expr      The expression to test against
     * @param expect    The expected result
     */
    public static void test(String expr, double expect)
    {
        String post = infixToPostfix(expr);      
        double val = evalPostfix(post);

        if (val == expect)
        {
            System.out.println("** Success! Great Job **");
        }
        else
        {
            System.out.print("** Oops! Something went wrong. ");
            System.out.println("Check your postfix and eval methods **");
        }
    }

    /**
     * Checks if matchParenthesis works properly
     * @param expr      The expression to test against
     * @param expect    The expected result
     */
    public static void testBalanced(String ex, boolean expected)
    {
        boolean act = matchParenthesis(ex);
        if (act == expected)
            System.out.println("** Success!: matchParenthesis(" + ex + ") returned " + act);
        else
        {
            System.out.print("** Oops! Something went wrong check : matchParen(" + ex + ")");
            System.out.println(" returned " + act + " but should have returned " + expected);
        }
    }
}
