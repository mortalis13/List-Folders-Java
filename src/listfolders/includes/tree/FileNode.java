package listfolders.includes.tree;

public class FileNode extends TreeNode{
  String icon;
  
  public FileNode(String text, String icon) {
    super(text);
    this.icon=icon;
  }
}
