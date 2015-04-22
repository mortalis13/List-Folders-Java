import java.awt.Window;
import java.util.HashMap;

import javax.swing.JFrame;

import com.google.gson.Gson;


public class Functions {
  
  void loadFields(ListFoldersMain window){
    Database db=window.db;
    HashMap<String,Object> fields;
    
    String last=db.loadLastOptions();
    if(last.length()==0) return;
    
    fields=decodeJSON(last);
    assignFields(window,fields);
  }
  
  HashMap getFieldsMap(ListFoldersMain window){
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
  
  void assignFields(ListFoldersMain window, HashMap fields){
    window.tfPath.setText((String) fields.get("path"));
    window.taFilterExt.setText((String) fields.get("filterExt"));
    window.taExcludeExt.setText((String) fields.get("excludeExt"));
    window.taFilterDir.setText((String) fields.get("filterDir"));
    
    window.chExportText.setSelected((boolean) fields.get("doExportText"));
    window.chExportMarkup.setSelected((boolean) fields.get("doExportMarkup"));
    window.chExportTree.setSelected((boolean) fields.get("doExportTree"));
    window.tfExportName.setText((String) fields.get("exportName"));
  }
  
  String encodeJSON(Object collection){
    Gson gson=new Gson();
    return gson.toJson(collection);
  }
  
  HashMap decodeJSON(String json){
    HashMap fields;
    fields=new Gson().fromJson(json, HashMap.class);
    return fields;
  }
  
  void stickWindow(JFrame to, Window what){
    int x=to.getX(), 
        y=to.getY(),
        w=to.getWidth();
    
    if(what!=null)
      what.setLocation(x+w,y);
  }
  
}
