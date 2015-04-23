package listfolders.includes;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Window;
import java.awt.geom.Line2D;
import java.util.HashMap;

import javax.swing.JFrame;

import listfolders.ListFoldersMain;

import com.google.gson.Gson;


public class Functions {
  
  ListFoldersMain window;
  Database db;
  
  public Functions(){
    window=ListFoldersMain.window;
    db=ListFoldersMain.db;
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
