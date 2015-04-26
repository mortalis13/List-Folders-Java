package listfolders.includes;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.HashMap;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import listfolders.ListFoldersMain;

import com.google.gson.Gson;


public class Functions {
  
  ListFoldersMain window;
  Database db;
  
  public HashMap<String, Action> shortcuts;
  public HashMap<String, KeyStroke> actionStrokes;
  
  Action exitAction = new AbstractAction() {
    public void actionPerformed(ActionEvent actionEvent) {
      window.frame.dispose();
    }
  };
  
  Action exitTreeViewer = new AbstractAction() {
    public void actionPerformed(ActionEvent actionEvent) {
      window.treeViewerWindow.frame.dispose();
    }
  };
  
  Action closeManOpt = new AbstractAction() {
    public void actionPerformed(ActionEvent actionEvent) {
      window.manOptDialog.dispose();
    }
  };
  
  Action scanAction = new AbstractAction() {
    public void actionPerformed(ActionEvent actionEvent) {
      window.bScanDir.doClick();
    }
  };
  
  public Functions(){
    window=ListFoldersMain.window;
    db=ListFoldersMain.db;
    
    shortcuts=new HashMap<String, Action>();
    actionStrokes=new HashMap<String, KeyStroke>();
    
    setShortcuts();
  }
  
  private void setShortcuts(){
    KeyStroke stroke;
    String comm;
    
    stroke=KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0);
    comm="exit";
    shortcuts.put(comm, exitAction);
    actionStrokes.put(comm, stroke);
    
    comm="exitTreeViewer";
    shortcuts.put(comm, exitTreeViewer);
    actionStrokes.put(comm, stroke);
    
    comm="closeManOpt";
    shortcuts.put(comm, closeManOpt);
    actionStrokes.put(comm, stroke);
    
    stroke=KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK);
    comm="scan";
    shortcuts.put(comm, scanAction);
    actionStrokes.put(comm, stroke);
  }
  
  public void addShortcut(JComponent comp, String comm){
    KeyStroke stroke;
    Action action;
    InputMap inputMap;
    
    stroke=actionStrokes.get(comm);
    action=shortcuts.get(comm);
    
    inputMap = comp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
    inputMap.put(stroke, comm);
    comp.getActionMap().put(comm, action);
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
  
}
