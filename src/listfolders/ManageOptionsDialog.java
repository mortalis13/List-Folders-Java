package listfolders;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import listfolders.includes.Database;
import listfolders.includes.Functions;
import listfolders.includes.ManageOptions;


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
  public JPanel pStatusBar;
  public JLabel lStatus;

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
        String name=tfName.getText();
        manOpt.addOption(name);
        cbList.setSelectedItem(name);
      }
    });
    
    cbList = new JComboBox();
    cbList.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String name=(String) cbList.getSelectedItem();
        manOpt.loadOption(name);
        
        int count=cbList.getItemCount();
        tfName.setText(name);
        lStatus.setText(count+" options loaded");
      }
    });
    
    bRemove = new JButton("Remove");
    bRemove.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        String name=(String) cbList.getSelectedItem();
        manOpt.removeOption(name);
      }
    });
    
    pStatusBar = new JPanel(){
      @Override
      public void paintComponent(Graphics g){
        fun.drawDashedBorder(g, this.getWidth());
      }
    };
    pStatusBar.setBorder(new EmptyBorder(5, 5, 5, 5));
    
    GroupLayout gl_panel = new GroupLayout(panel);
    gl_panel.setHorizontalGroup(
      gl_panel.createParallelGroup(Alignment.LEADING)
        .addGroup(gl_panel.createSequentialGroup()
          .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
            .addGroup(gl_panel.createSequentialGroup()
              .addGap(20)
              .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                .addComponent(tfName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(cbList, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
              .addPreferredGap(ComponentPlacement.RELATED)
              .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                .addComponent(bAdd)
                .addComponent(bRemove, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)))
            .addGroup(gl_panel.createSequentialGroup()
              .addContainerGap()
              .addComponent(pStatusBar, GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)))
          .addContainerGap())
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
          .addPreferredGap(ComponentPlacement.RELATED, 165, Short.MAX_VALUE)
          .addComponent(pStatusBar, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
    );
    gl_panel.linkSize(SwingConstants.VERTICAL, new Component[] {tfName, bAdd, cbList, bRemove});
    gl_panel.linkSize(SwingConstants.HORIZONTAL, new Component[] {tfName, cbList});
    gl_panel.linkSize(SwingConstants.HORIZONTAL, new Component[] {bAdd, bRemove});
    pStatusBar.setLayout(new BorderLayout(5, 0));
    
    lStatus = new JLabel("");
    lStatus.setOpaque(true);
    pStatusBar.add(lStatus);
    panel.setLayout(gl_panel);
  }
}
