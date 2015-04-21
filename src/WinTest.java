import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JCheckBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Dimension;


public class WinTest {

  private JFrame frame;
  JPanel panel;
  public JCheckBox ch;

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
    panel = new JPanel();
    
    frame = new JFrame();
    frame.setSize(new Dimension(300, 500));
    frame.addComponentListener(new ComponentAdapter() {
      @Override
      public void componentResized(ComponentEvent e) {
        System.out.println("Panel size: "+panel.getWidth());
        System.out.println("Panel pref size: "+panel.getPreferredSize());
      }
    });
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    JButton button = new JButton("New button");
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println(ch.isSelected());
      }
    });
    
    ch = new JCheckBox("New check box");
    
    GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
    groupLayout.setHorizontalGroup(
      groupLayout.createParallelGroup(Alignment.LEADING)
        .addGroup(groupLayout.createSequentialGroup()
          .addContainerGap()
          .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
            .addComponent(ch)
            .addComponent(panel, 0, 498, Short.MAX_VALUE)
            .addComponent(button))
          .addContainerGap())
    );
    groupLayout.setVerticalGroup(
      groupLayout.createParallelGroup(Alignment.LEADING)
        .addGroup(groupLayout.createSequentialGroup()
          .addComponent(panel, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
          .addGap(18)
          .addComponent(ch)
          .addGap(18)
          .addComponent(button)
          .addContainerGap(211, Short.MAX_VALUE))
    );
    
    JTextArea textArea = new JTextArea();
    textArea.setMargin(new Insets(20, 20, 20, 20));
    textArea.setLineWrap(true);
    GroupLayout gl_panel = new GroupLayout(panel);
    gl_panel.setHorizontalGroup(
      gl_panel.createParallelGroup(Alignment.LEADING)
        .addGroup(gl_panel.createSequentialGroup()
          .addComponent(textArea, 0, 594, Short.MAX_VALUE)
          .addContainerGap())
    );
    gl_panel.setVerticalGroup(
      gl_panel.createParallelGroup(Alignment.LEADING)
        .addGroup(gl_panel.createSequentialGroup()
          .addContainerGap()
          .addComponent(textArea, 0, 420, Short.MAX_VALUE)
          .addContainerGap())
    );
    gl_panel.setAutoCreateGaps(true);
    gl_panel.setAutoCreateContainerGaps(true);
    panel.setLayout(gl_panel);
    frame.getContentPane().setLayout(groupLayout);
  }
}
