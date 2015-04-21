import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;

public class ScanDirectory {
  Functions fun;
  
  String path;

  String text;
  String markup;
  String json;

  ArrayList<String> textArray;
  ArrayList<String> markupArray;
  ArrayList<TreeNode> jsonArray;
  
  ArrayList<String> filterExt;
  ArrayList<String> excludeExt;
  ArrayList<String> filterDir;
  
  boolean doExportText;
  boolean doExportMarkup;
  boolean doExportTree;
  
  String exportName;

  String nl = "\n";
  String pad = "  ";
  String iconsPath="./lib/images/";
  
  String[] exts={
    "chm", "css", "djvu", "dll", "doc", 
    "exe", "html", "iso", "js", "msi", 
    "pdf", "php", "psd", "rar", "txt", 
    "xls", "xml", "xpi", "zip",
  };
  
  String[] imageExts={
    "png", "gif", "jpg", "jpeg", "tiff", "bmp",
  };

  String[] musicExts={
    "mp3", "wav", "ogg", "alac", "flac",
  };

  String[] videoExts={
    "mkv", "flv", "vob", "avi", "wmv",
    "mov", "mp4", "mpg", "mpeg", "3gp",
  };

  public ScanDirectory(ListFoldersMain window) {
    String filterExtText, excludeExtText, filterDirText;
    fun=new Functions();
    
    textArray = new ArrayList<String>();
    markupArray = new ArrayList<String>();
    
    HashMap fields=fun.getFieldsMap(window);
    
    path=(String)fields.get("path");
    path=formatPath(path);
    
    filterExtText=(String)fields.get("filterExt");
    excludeExtText=(String)fields.get("excludeExt");
    filterDirText=(String)fields.get("filterDir");
    
    doExportText=(boolean)fields.get("doExportText");
    doExportMarkup=(boolean)fields.get("doExportMarkup");
    doExportTree=(boolean)fields.get("doExportTree");
    
    exportName=(String)fields.get("exportName");
    
    
    filterExt=getFilters(filterExtText);
    excludeExt=getFilters(excludeExtText);
    filterDir=getFilters(filterDirText);
    
//    System.out.println(path);
//    System.out.println(filterExtText);
//    System.out.println(excludeExtText);
//    System.out.println(filterDirText);
//    
//    System.out.println(doExportText);
//    System.out.println(doExportMarkup);
//    System.out.println(doExportTree);
//    System.out.println(exportName);
  }

  public void processData() {
   jsonArray = fullScan(path, -1);

   if(textArray.size()==0){
     text="No Data!";
     return;
   }
   
   text = join(textArray);
   markup = join(markupArray);

//    exportText();
//    exportMarkup();
//    exportTree();
  }
  
  public ArrayList<TreeNode> fullScan(String dir, int level) {
    ArrayList<TreeNode> json, res;
    ArrayList<String> list;
    String[] data;
    String pad;
    File file;
    
    json = new ArrayList<TreeNode>();

    file = new File(dir);
    data = file.list();
    list = prepareData(data, dir);
    pad = getPadding(level);

    for (String value : list) {
      TreeNode node;
      String item = dir + '/' + value;
      file = new File(item);

      if (file.isDirectory() == true) {
        String currentDir = "[" + value + "]";

        textArray.add(pad + currentDir);
        markupArray.add(wrapDir(pad + currentDir));

        res = fullScan(item, level + 1);

        node = new DirNode(value, res);
        json.add(node);
      } else {
        String currentFile = value;

        textArray.add(pad + currentFile);
        markupArray.add(wrapFile(pad + currentFile));

        node = new FileNode(value, getIcon(value));
        json.add(node);
      }
    }

    return json;
  }

  // --------------------------------------------------- helpers ---------------------------------------------------
  
  public ArrayList<String> prepareData(String[] data, String dir) {
    ArrayList<String> folders = new ArrayList<String>(), 
    files = new ArrayList<String>(), list;

    for (String value : data) {
      String item = dir + '/' + value;
      File f = new File(item);

      if (f.isDirectory() == true) {
        folders.add(value);
      } else if (filterFile(value)) {
        files.add(value);
      }
    }

    list = getList(folders, files);
    return list;
  }

  public ArrayList<String> getList(ArrayList<String> folders, ArrayList<String> files) {
    ArrayList<String> list = new ArrayList<String>();
    Collections.sort(folders);
    Collections.sort(files);
    list.addAll(folders);
    list.addAll(files);
    return list;
  }

  public String getPadding(int level) {
    String resPad = "";
    for (int i = 0; i <= level; i++) {
      resPad += pad;
    }
    return resPad;
  }

  public String join(ArrayList<String> array) {
    String res = "";
    for (String val : array) {
      res += val + '\n';
    }
    return res;
  }
  
  private String replaceTemplate(String tmpl, String replacement, String text){
    text=text.replace(tmpl, replacement);
    return text;
  }
  
  private String readTemplate(String tmpl) {
    String doc = "", line = null;
    BufferedReader br = null;

    try {
      br = new BufferedReader(new FileReader("templates/tree.html"));
      while ((line = br.readLine()) != null) {
        doc += line+nl;
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (br != null)
          br.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    return doc;
  }

  private void writeFile(String filename, String text) {
    File file;
    PrintWriter writer;
    
    try {
      file = new File(filename);
      file.createNewFile();

      writer = new PrintWriter(filename);
      writer.print(text);
      writer.close();
    } catch (Exception e) {
      System.out.println("error-writing-file: " + e.getMessage());
    }
  }
  
  public String formatPath(String path) {
    path=path.replace('\\', '/');
    path=path.trim();
    
    int last=path.length()-1;
    if(path.substring(last)=="/")
      path=path.substring(0,last);
    
    return path;
  }
  
  private String getIcon(String file){
    String ext, icon, path, iconExt;
    boolean useDefault=true;
    
    ext="";
    icon="jstree-file";
    path=iconsPath;
    iconExt=".png";
    
    Pattern pat=Pattern.compile("\\.[\\w]+$");
    Matcher mat=pat.matcher(file);
    
    if(!mat.find()) return icon;
    
    ext=mat.group();
    ext=ext.substring(1);
    
    if(useDefault){
      for(String item : exts){
        if(item.equals(ext)){
          icon=path+item+iconExt;
          useDefault=false;
          break;
        }
      }
    }
    
    if(useDefault){
      for(String item : imageExts){
        if(item.equals(ext)){
          icon=path+"image"+iconExt;
          useDefault=false;
          break;
        }
      }
    }
    
    if(useDefault){
      for(String item : musicExts){
        if(item.equals(ext)){
          icon=path+"music"+iconExt;
          useDefault=false;
          break;
        }
      }
    }
    
    if(useDefault){
      for(String item : videoExts){
        if(item.equals(ext)){
          icon=path+"video"+iconExt;
          useDefault=false;
          break;
        }
      }
    }
    
    return icon;
  }
  
// --------------------------------------------------- filters ---------------------------------------------------
  
  public ArrayList<String> getFilters(String filter) {
    ArrayList<String> list=new ArrayList<String>();
    String[] elements;
    filter=filter.trim();
    
    if(filter.length()!=0){
      elements=filter.split("\n");
      Collections.addAll(list, elements);
      
      for(int i=0;i<list.size();i++){
        String item=list.get(i);
        list.set(i,item.trim());
      }
    }
    
    return list;
  }
  
  public boolean filterFile(String file) {
    if(excludeExt.size()!=0){
      for(String ext:excludeExt){
        if(match("\\."+ext+"$",file))
          return false;
        
//        Pattern pat=Pattern.compile("\\."+ext+"$");
//        if(pat.matcher(file).matches())
//          return false;
      }
      return true;
    }
    
    if(filterExt.size()==0) return true;
    for(String ext:filterExt){
      if(match("\\."+ext+"$",file))
        return true;
    }
    return false;
  }
  
  public boolean match(String regex, String text) {
    Pattern pat=Pattern.compile(regex);
    return pat.matcher(text).matches();
  }
  
  public boolean filterDirectory(String dir) {
    return true;
  }
  
  public String getFiltersText() {
    String filters="";
    return filters;
  }
  
// --------------------------------------------------- wrappers ---------------------------------------------------

  public String wrapDir(String dir) {
    return "<span class=\"directory\">" + dir + "</span>";
  }

  public String wrapFile(String file) {
    return "<span class=\"file\">" + file + "</span>";
  }

  public String wrapMarkup(String markup) {
    String res = "<pre>" + nl + markup + "</pre>";
    res = wrapDocument(res);
    return res;
  }

  public String wrapDocument(String markup) {
    return "<meta charset=\"utf-8\">" + nl + markup;
  }

  // --------------------------------------------------- exports ---------------------------------------------------

  private void exportText() {
    File file;
    String exportPath, fileName, ext;

    exportPath = "export/text/";
    ext=".txt";
    fileName = getExportName(ext);
    fileName = exportPath + fileName;
    
    writeFile(fileName,text);
  }
  
  private void exportMarkup() {
    File file;
    String exportPath, fileName, ext;

    exportPath = "export/markup/";
    ext=".html";
    fileName = getExportName(ext);
    fileName = exportPath + fileName;
    markup = wrapMarkup(markup);

    writeFile(fileName, markup);
  }
  
  private void exportTree() {
    String tmpl, doc, treeName, exportPath, jsonFolder, 
    jsonPath, exportDoc, exportJSON;
    String filters;
    String jsonFile, htmlFile;
    
    Gson gson = new Gson();
    String json = gson.toJson(jsonArray);

    treeName=getExportName(null);
    
    tmpl="templates/tree.html";
    exportPath="export/tree/";
    jsonFolder="json/";
    jsonPath=exportPath+jsonFolder;
    
    exportDoc=treeName+".html";
    exportJSON=treeName+".json";
    
    doc=readTemplate(tmpl);
    doc=replaceTemplate("_jsonPath_", jsonFolder+exportJSON, doc);
    doc=replaceTemplate("_Title_", "Directory: "+treeName, doc);
    doc=replaceTemplate("_FolderPath_", "Directory: "+path, doc);
    
//    filters=getFiltersText();
//    doc=replaceTemplate("_Filters_", "Filters: "+filters, doc);
    
    htmlFile=exportPath+exportDoc;
    jsonFile=jsonPath+exportJSON;
      
    writeFile(htmlFile, doc);
    writeFile(jsonFile, json);
  }
  
  private String getExportName(String ext){
    boolean useCurrentDir=true;
    String exportName, name;
    
    exportName="no-name";
    
    if(useCurrentDir){
      Pattern pat=Pattern.compile("/[^/]+$");
      Matcher mat=pat.matcher(path);
      
      if(mat.find()){
        exportName=mat.group();
        exportName=exportName.substring(1);
      }
    }
    
    name=exportName;
    if(ext!=null) name+=ext;
    
    return name;
  }

}
