package listfolders;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import listfolders.includes.Database;
import listfolders.includes.Functions;
import listfolders.includes.ManageOptions;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class ManageOptionsDialog extends JDialog {

  private JPanel contentPane;
  public JPanel panel;
  public JTextField tfName;
  public JButton bAdd;
  public JComboBox cbList;
  public JButton bRemove;
  
  Functions fun;
  Database db;
  ListFoldersMain window;
  ManageOptions manOpt;

  public ManageOptionsDialog(Window owner) {
    super(owner);
    
    window=ListFoldersMain.window;
    fun=ListFoldersMain.fun;
    db=ListFoldersMain.db;
    
    initialize();
  }
  
  private void initialize(){
    setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    contentPane = new JPanel();
    contentPane.setBorder(null);
    setContentPane(contentPane);
    contentPane.setLayout(new GridLayout(1, 0, 0, 0));
    
    panel = new JPanel();
    contentPane.add(panel);
    
    tfName = new JTextField();
    tfName.setFont(new Font("Tahoma", Font.PLAIN, 12));
    tfName.setMargin(new Insets(2, 5, 2, 2));
    tfName.setColumns(10);
    
    addWindowListener(new WindowAdapter() {
      @Override
      public void windowOpened(WindowEvent e) {
        manOpt=new ManageOptions();
        manOpt.listOptions();
      }
    });
    
    bAdd = new JButton("Add");
    bAdd.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) { 
        manOpt.addOption();
      }
    });
    
    cbList = new JComboBox();
    
    bRemove = new JButton("Remove");
    GroupLayout gl_panel = new GroupLayout(panel);
    gl_panel.setHorizontalGroup(
      gl_panel.createParallelGroup(Alignment.LEADING)
        .addGroup(gl_panel.createSequentialGroup()
          .addGap(20)
          .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
            .addComponent(tfName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
            .addComponent(cbList, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
          .addPreferredGap(ComponentPlacement.RELATED)
          .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
            .addComponent(bAdd)
            .addComponent(bRemove, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE))
          .addGap(20))
    );
    gl_panel.setVerticalGroup(
      gl_panel.createParallelGroup(Alignment.LEADING)
        .addGroup(gl_panel.createSequentialGroup()
          .addGap(20)
          .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
            .addComponent(tfName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
            .addComponent(bAdd))
          .addPreferredGap(ComponentPlacement.UNRELATED)
          .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
            .addComponent(cbList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
            .addComponent(bRemove, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
          .addGap(20))
    );
    gl_panel.linkSize(SwingConstants.VERTICAL, new Component[] {tfName, bAdd, cbList, bRemove});
    gl_panel.linkSize(SwingConstants.HORIZONTAL, new Component[] {bAdd, bRemove});
    gl_panel.linkSize(SwingConstants.HORIZONTAL, new Component[] {tfName, cbList});
    panel.setLayout(gl_panel);
  }
}
