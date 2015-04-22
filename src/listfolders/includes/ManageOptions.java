package listfolders.includes;

import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;

import listfolders.ListFoldersMain;
import listfolders.ManageOptionsDialog;

public class ManageOptions {
  
  ManageOptionsDialog dialog;
  Functions fun;
  Database db;
  ListFoldersMain window;
  
  public ManageOptions(){
    window=ListFoldersMain.window;
    dialog=ListFoldersMain.manOptDialog;
    fun=ListFoldersMain.fun;
    db=ListFoldersMain.db;
  }
  
  public void addOption(String name){
    String value;
    value=fun.encodeJSON(fun.getFieldsMap(window));
    db.updateOption(name, value);
    listOptions("(+1 new)");
  }
  
  public void removeOption(String name){
    String value;
    db.removeOption(name);
    listOptions("(1 removed)");
  }
  
  public void loadOption(String name){
    String value;
    value=db.getOption(name);
    if(value==null) return;
    fun.loadFields(value);
  }
  
  public void listOptions(String msg){
    ArrayList<String> list;
    String[] array;
    int count=0;
    
    list=db.listOptions();
    if(list==null) return;
    
    array=list.toArray(new String[0]);
    count=list.size();
    
    DefaultComboBoxModel model=new DefaultComboBoxModel(array);
    dialog.cbList.setModel(model);
    
    if(msg==null) msg="";
    else msg=" "+msg;
    
    dialog.lStatus.setText(count+" options loaded"+msg);
  }
  
  public void listOptions(){
    listOptions(null);
  }
  
}
