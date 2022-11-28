import java.lang.AssertionError;
import java.util.LinkedList;

/**
 * Tests the NodeQueue object's functionality
 * @author Aarav Borthakur
 * @version November 15, 2022
 */
public class NodeQueueTester {
    public static void main(String[] args) {
        NodeQueue<String> q = new NodeQueue<String>();

        System.out.println("Adding elements");
        q.add("My");
        q.add("Name");
        q.add("Is");
        q.add("Aarav");

        assertEqual(q.toString(), "[My, Name, Is, Aarav]");
        System.out.println("Adding works!");

        System.out.println("Removing elements");
        q.remove();
        System.out.println(q);

        System.out.println("Peeking elements");
        System.out.println(q.peek());

        System.out.println("Removing all elements");
        q.remove();
        q.remove();
        q.remove();
        System.out.println(q);

        System.out.println("Checking if empty");
        System.out.println("Empty: " + q.isEmpty());
    }

    private static void assertEqual(String first, String second)
    {
        if (!first.equals(second))
        {
            throw new AssertionError(first + " != " + second);
        }
    }
}
