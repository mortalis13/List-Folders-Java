package listfolders.includes.tree;

/*
 * Parent for DirNode and FileNode
 */
public abstract class TreeNode {
  String text;
  
  public TreeNode(String text){
    this.text=text;
  }
}
