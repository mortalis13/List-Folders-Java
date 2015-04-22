import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class ManageOptions extends JDialog {

  private JPanel contentPane;
  public JPanel panel;
  public JTextField tfName;
  public JButton bAdd;
  public JComboBox cbList;
  public JButton bRemove;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());  
          ManageOptions dialog = new ManageOptions();
          dialog.pack();
          dialog.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the dialog.
   */
  public ManageOptions() {
    setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
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
    
    bAdd = new JButton("Add");
    bAdd.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        ListFoldersMain window=ListFoldersMain.window;
        tfName.setText(window.tfPath.getText());
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
