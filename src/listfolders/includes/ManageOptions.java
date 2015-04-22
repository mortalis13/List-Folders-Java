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
  
  public void addOption(){
    String name, value;
    name=dialog.tfName.getText();
    value=fun.encodeJSON(fun.getFieldsMap(window));
    db.updateOption(name, value);
    listOptions();
  }
  
  public void listOptions(){
    ArrayList<String> list;
    String[] array;
    
    list=db.listOptions();
    array=list.toArray(new String[0]);
    
    DefaultComboBoxModel model=new DefaultComboBoxModel(array);
    dialog.cbList.setModel(model);
  }
  
}
