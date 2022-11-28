import java.util.Stack;

public class Html {
    public static boolean matchHtml(String html)
    {
        Stack<String> st = new Stack<String>();
    
        for (int i = 0; i < html.length(); i++)
        {
            if (html.substring(i, i + 1 ).equals("<"))
            {
                String tag = "";
                i++;
                while (!html.substring(i, i + 1).equals(">"))
                {
                    if (i >= html.length())
                    {
                        return false;
                    }
                    tag += html.substring(i, i + 1);
                    i++;
                }
                if (tag.indexOf("/") == -1)
                {
                    st.push(tag);
                }
                else
                {
                    if (!st.pop().equals(tag.substring(1)))
                    {
                        return false;
                    }
                }
            }
        }
        return st.isEmpty();
    }
}