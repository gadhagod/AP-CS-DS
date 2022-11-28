import java.util.Stack;

public class StackSort {
    public static Stack<Integer> sort(Stack<Integer> input)
    {
        Stack<Integer> output = new Stack<Integer>();
        while (!input.isEmpty())
        {
            Integer current = input.pop();
            if (output.isEmpty() || current.intValue() <= output.peek().intValue())
            {
                output.push(current);
            }
            else
            {
                while (!output.isEmpty() && (output.peek().intValue() <= current.intValue()))
                {
                    input.push(output.pop());
                }
                output.push(current);
            }
        }
        return output;
    }
}
