import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;


public class WinTest {

  private JFrame frame;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          WinTest window = new WinTest();
          window.frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the application.
   */
  public WinTest() {
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    frame = new JFrame();
    frame.setBounds(100, 100, 630, 481);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    JPanel panel = new JPanel();
    GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
    groupLayout.setHorizontalGroup(
      groupLayout.createParallelGroup(Alignment.LEADING)
        .addGroup(groupLayout.createSequentialGroup()
          .addComponent(panel, GroupLayout.PREFERRED_SIZE, 612, GroupLayout.PREFERRED_SIZE)
          .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    groupLayout.setVerticalGroup(
      groupLayout.createParallelGroup(Alignment.LEADING)
        .addComponent(panel, GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
    );
    
    JTextArea textArea = new JTextArea();
    textArea.setMargin(new Insets(20, 20, 20, 20));
    textArea.setLineWrap(true);
    GroupLayout gl_panel = new GroupLayout(panel);
    gl_panel.setAutoCreateGaps(true);
    gl_panel.setAutoCreateContainerGaps(true);
    gl_panel.setHorizontalGroup(
      gl_panel.createSequentialGroup()
        .addComponent(textArea, GroupLayout.PREFERRED_SIZE, 586, GroupLayout.PREFERRED_SIZE)
    );
    gl_panel.setVerticalGroup(
      gl_panel.createSequentialGroup()
        .addComponent(textArea, GroupLayout.PREFERRED_SIZE, 422, GroupLayout.PREFERRED_SIZE)
    );
    panel.setLayout(gl_panel);
    frame.getContentPane().setLayout(groupLayout);
  }

}
