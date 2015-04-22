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
  
  public void loadFields(String fieldsList){
    HashMap<String,Object> fields;
    if(fieldsList.length()==0) return;
    fields=decodeJSON(fieldsList);
    assignFields(fields);
  }
  
  public void loadFields(){
    String last=db.loadLastOptions();
    if(last==null) return;
    loadFields(last);
  }
  
  public HashMap getFieldsMap(ListFoldersMain window){
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
  
  public String encodeJSON(Object collection){
    Gson gson=new Gson();
    return gson.toJson(collection);
  }
  
  public HashMap decodeJSON(String json){
    HashMap fields;
    fields=new Gson().fromJson(json, HashMap.class);
    return fields;
  }
  
  public void stickWindow(JFrame to, Window what){
    int x=to.getX(), 
        y=to.getY(),
        w=to.getWidth();
    
    if(what!=null)
      what.setLocation(x+w,y);
  }
  
  public void drawDashedBorder(Graphics g, int length){
    Graphics2D g2=(Graphics2D)g;
    float dash1[] = {1.0f};
    BasicStroke stroke = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1.0f, dash1, 0.0f);
    g2.setStroke(stroke);
    g2.setColor((Color) new Color(120, 120, 120));
    
    g2.draw(new Line2D.Double(0, 0, length, 0));
  }
  
}
