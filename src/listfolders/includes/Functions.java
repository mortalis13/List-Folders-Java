package listfolders.includes;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

import listfolders.ListFoldersMain;

import com.google.gson.Gson;


public class Functions {
  
  ListFoldersMain window;
  Database db;
  
  public HashMap<String, Shortcut> shortcuts;
  
// ----------------------------------------------- Shortcut actions -----------------------------------------------
  
  Action exitAction = new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
      window.frame.dispose();
    }
  };
  
  Action exitTreeViewer = new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
      window.treeViewerWindow.frame.dispose();
    }
  };
  
  Action closeManOpt = new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
      window.manOptDialog.dispose();
    }
  };
  
  Action scanAction = new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
      window.bScanDir.doClick();
    }
  };
  
  Action browseDir = new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
      window.bBrowse.doClick();
    }
  };
  
  Action browseTreeFile = new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
      window.treeViewerWindow.bBrowse.doClick();
    }
  };
  
  Action addOption = new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
      window.manOptDialog.bAdd.doClick();
    }
  };
  
// ----------------------------------------------- Constructor -----------------------------------------------
  
  public Functions(){
    window=ListFoldersMain.window;
    db=ListFoldersMain.db;
    
    shortcuts=new HashMap<String, Shortcut>();
    setShortcuts();
  }
  
// ----------------------------------------------- Functions -----------------------------------------------
  
  /*
   * Adds predefined shortcuts to the HashMap
   * which then used in the addShortcut() to assign shortcuts to window components
   */
  private void setShortcuts(){
    KeyStroke stroke;
    
    stroke=KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
    shortcuts.put("exit", new Shortcut(stroke, exitAction));
    shortcuts.put("exitTreeViewer", new Shortcut(stroke, exitTreeViewer));
    shortcuts.put("closeManOpt", new Shortcut(stroke, closeManOpt));
    
    stroke=KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK);
    shortcuts.put("scan", new Shortcut(stroke, scanAction));
    
    stroke=KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK);
    shortcuts.put("browseDir", new Shortcut(stroke, browseDir));
    shortcuts.put("browseTreeFile", new Shortcut(stroke, browseTreeFile));
    
    stroke=KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
    shortcuts.put("addOption", new Shortcut(stroke, addOption));
  }
  
  /*
   * Assigns shortcut to the component globally within the parent window
   * The command is taken from the setShortcuts()
   */
  public void addShortcut(JComponent comp, String comm){
    KeyStroke stroke;
    Action action;
    InputMap inputMap;
    
    stroke=shortcuts.get(comm).stroke;
    action=shortcuts.get(comm).action;
    
    inputMap = comp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
    inputMap.put(stroke, comm);
    comp.getActionMap().put(comm, action);
  }
  
  /*
   * Formats path, fixes backslashes, trims and removes last slash
   */
  public static String formatPath(String path) {
    path=path.replace('\\', '/');
    path=path.trim();
    
    int last=path.length()-1;
    if(path.substring(last).equals("/"))
      path=path.substring(0,last);
    
    return path;
  }
  
  /*
   * Loads field values from the 'options' table
   * and assigns each value to appropriate field on the form
   */
  public void loadFields(String fieldsList){
    HashMap<String,Object> fields;
    if(fieldsList.length()==0) return;
    fields=decodeJSON(fieldsList);
    assignFields(fields);
  }
  
  /*
   * Loads and assign the last options set
   * saved after previous application session
   * Redirects to more general method loadFields(String)
   */
  public void loadFields(){
    String last=db.loadLastOptions();
    if(last==null) return;
    loadFields(last);
  }
  
  /*
   * Gets HashMap of all form fields
   * which is used to serialize them to JSON string
   */
  public HashMap getFieldsMap(){
    HashMap<String,Object> map=new HashMap<String,Object>();
      
    String path, filterExt, excludeExt, filterDir, exportName;
    boolean doExportText, doExportMarkup, doExportTree;
    
    path=window.tfPath.getText();
    filterExt=window.taFilterExt.getText();
    excludeExt=window.taExcludeExt.getText();
    filterDir=window.taFilterDir.getText();
    
    doExportText=window.chExportText.isSelected();
    doExportMarkup=window.chExportMarkup.isSelected();
    doExportTree=window.chExportTree.isSelected();
    exportName=window.tfExportName.getText();
    
    map.put("path",path);
    map.put("filterExt",filterExt);
    map.put("excludeExt",excludeExt);
    map.put("filterDir",filterDir);
    map.put("doExportText",doExportText);
    map.put("doExportMarkup",doExportMarkup);
    map.put("doExportTree",doExportTree);
    map.put("exportName",exportName);
    
    return map;
  }
  
  /*
   * Assigns values from the HashMap to form fields
   */
  private void assignFields(HashMap fields){
    window.tfPath.setText((String) fields.get("path"));
    window.taFilterExt.setText((String) fields.get("filterExt"));
    window.taExcludeExt.setText((String) fields.get("excludeExt"));
    window.taFilterDir.setText((String) fields.get("filterDir"));
    
    window.chExportText.setSelected((boolean) fields.get("doExportText"));
    window.chExportMarkup.setSelected((boolean) fields.get("doExportMarkup"));
    window.chExportTree.setSelected((boolean) fields.get("doExportTree"));
    window.tfExportName.setText((String) fields.get("exportName"));
  }
  
  /*
   * Gets JSON string from an object (array, array list, hash map)
   */
  public String encodeJSON(Object collection){
    Gson gson=new Gson();
    return gson.toJson(collection);
  }
  
  /*
   * Gets HashMap from JSON string
   */
  public HashMap decodeJSON(String json){
    HashMap fields;
    fields=new Gson().fromJson(json, HashMap.class);
    return fields;
  }
  
  /*
   * Moves dialog window to the right boundary of the main window
   */
  public void stickWindow(JFrame to, Window what){
    int x=to.getX(), 
        y=to.getY(),
        w=to.getWidth();
    
    if(what!=null)
      what.setLocation(x+w,y);
  }
  
  /*
   * Sets small and large icons for the window
   */
  public static void setWindowIcon(JFrame frame){
    List<Image> icons = new ArrayList<Image>();
    icons.add(new ImageIcon("icons/icon16.png").getImage());
    icons.add(new ImageIcon("icons/icon32.png").getImage());
    frame.setIconImages(icons);
  }
  
  /*
   * Returns file name from the full path of the JSON file
   * the name is assigned to the root directory name
   */
  public static String getNameFromPath(String path){
    String name="";
    name=regexFind("/([^/]+)\\.[^/.]+$", path, 1);
    return name;
  }
  
  /*
   * Returns extension from the filename
   */
  public static String getExt(String file){
    String ext;
    ext=regexFind("\\.([^.]+)$", file, 1);
    ext=ext.toLowerCase();
    return ext;
  }
  
  /*
   * Returns the result of the string search using regex
   * The 'group' parameter corresponds to the regex group in parenthesis
   * If the whole result is needed group=0 should be passed
   */
  public static String regexFind(String pattern, String text, int group){
    String result="";
    
    Pattern pat=Pattern.compile(pattern);
    Matcher mat=pat.matcher(text);
    
    if(mat.find())
      result=mat.group(group);
    
    return result;
  }
  
}
