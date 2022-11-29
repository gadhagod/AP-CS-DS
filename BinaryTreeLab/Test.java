import java.util.Iterator;

public class Test {
    public static void main(String[] args) {
        TreeNode x = TreeUtil.buildTree(FileUtil.loadFile("tree.txt"));
        TreeDisplay y = new TreeDisplay();
        y.displayTree(x);
    }
}
