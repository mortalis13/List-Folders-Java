import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Database {
  private Connection conn;
  private String options_table="options";
  private String config_table="config";
  
  Statement st;
  PreparedStatement prep;
  ResultSet rs;
  
  String sql;
  
  public Database(){
    if(conn==null)
      conn=createConnection();
  }
  
  private Connection createConnection(){
    Connection conn = null;
    String driver="com.mysql.jdbc.Driver";
    String url="jdbc:mysql://localhost/list_folders_java";
    
    try {
      Class.forName(driver);
      conn = DriverManager.getConnection(url,"root","");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return conn;
  }
  
  public void updateOption(String name, String value, String dbtable){
    String table=options_table;
    if(dbtable.length()!=0) table=dbtable;
    
    sql="select name from "+table+" where name=?";
    
    try {
      prep = conn.prepareStatement(sql);
      prep.setString(1, name);
      
      rs=prep.executeQuery();
      
      if(!rs.next()){
        addOption(name, value, table);
        return;
      }
      
      sql="update "+table+" set value=? where name=?";
      prep = conn.prepareStatement(sql);
      prep.setString(1, value);
      prep.setString(2, name);
      prep.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  
  public void addOption(String name, String value, String table) throws SQLException{
    sql="insert into "+table+" (name,value) values(?,?)";
    prep = conn.prepareStatement(sql);
    prep.setString(1, name);
    prep.setString(2, value);
    prep.execute();
  }
  
  public void updateConfig(String name, String value){
    updateOption(name,value,config_table);
  }
  
  String loadLastOptions(){
    String table=config_table;
    
    sql="select value from "+table+" where name='last'";
    
    try {
      prep = conn.prepareStatement(sql);
      rs=prep.executeQuery();
      
      if(!rs.next()){
        return "";
      }
      
      return rs.getString(1);
      
    } catch (SQLException e) {
      e.printStackTrace();
      return "";
    }
  }
  
}
