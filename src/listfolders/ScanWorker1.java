package listfolders;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import listfolders.includes.ScanDirectory;
import listfolders.includes.tree.TreeNode;

public class ScanWorker1 extends SwingWorker<ArrayList<TreeNode>, Void> {

  public ArrayList<TreeNode> doInBackground() {
    ScanDirectory scandir;
    ArrayList<TreeNode> jsonArray;

    scandir = ListFoldersMain.scandir;
    jsonArray = scandir.fullScan(scandir.path, -1);
    return jsonArray;
  }

}