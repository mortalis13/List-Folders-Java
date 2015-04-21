import java.util.ArrayList;


public class DirNode extends TreeNode{
  ArrayList<TreeNode> children;
  
  public DirNode(String text, ArrayList<TreeNode> children) {
    // this.text = text;
    super(text);
    this.children = children;
  }
}
