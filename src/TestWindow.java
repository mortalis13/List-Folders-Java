import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.Rectangle;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JCheckBox;
import javax.swing.ButtonGroup;
import javax.swing.SwingConstants;
import java.awt.Insets;
import java.awt.Component;


public class TestWindow {

  private JFrame frame;
  private JTextField textField;
  private JCheckBox chckbxWholeWords;
  private JCheckBox chckbxWrapAround;
  private JCheckBox chckbxSearchBackwards;
  private final ButtonGroup buttonGroup = new ButtonGroup();

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          TestWindow window = new TestWindow();
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
  public TestWindow() {
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    JLabel lblFindWhat = new JLabel("Find What:");
    lblFindWhat.setAlignmentY(Component.TOP_ALIGNMENT);
    
    textField = new JTextField();
    textField.setAlignmentY(Component.TOP_ALIGNMENT);
    textField.setColumns(10);
    
    JCheckBox chckbxMatchCase = new JCheckBox("Match Case");
    chckbxMatchCase.setMargin(new Insets(0, 0, 0, 0));
    chckbxMatchCase.setHorizontalAlignment(SwingConstants.LEFT);
    buttonGroup.add(chckbxMatchCase);
    
    chckbxWholeWords = new JCheckBox("Whole Words");
    chckbxWholeWords.setMargin(new Insets(0, 0, 0, 0));
    buttonGroup.add(chckbxWholeWords);
    
    chckbxWrapAround = new JCheckBox("Wrap Around");
    chckbxWrapAround.setMargin(new Insets(0, 0, 0, 0));
    buttonGroup.add(chckbxWrapAround);
    
    chckbxSearchBackwards = new JCheckBox("Search Backwards");
    chckbxSearchBackwards.setMargin(new Insets(0, 0, 0, 0));
    buttonGroup.add(chckbxSearchBackwards);
    
    JButton btnFind = new JButton("Find");
    btnFind.setAlignmentY(Component.TOP_ALIGNMENT);
    
    JButton btnCancel = new JButton("Cancel");
    GroupLayout gl = new GroupLayout(frame.getContentPane());
    gl.setHorizontalGroup(
      gl.createParallelGroup(Alignment.LEADING)
        .addGroup(gl.createSequentialGroup()
          .addContainerGap()
          .addComponent(lblFindWhat)
          .addPreferredGap(ComponentPlacement.RELATED)
          .addGroup(gl.createParallelGroup(Alignment.LEADING, false)
            .addGroup(gl.createSequentialGroup()
              .addGroup(gl.createParallelGroup(Alignment.LEADING)
                .addComponent(chckbxWholeWords)
                .addComponent(chckbxMatchCase))
              .addPreferredGap(ComponentPlacement.RELATED)
              .addGroup(gl.createParallelGroup(Alignment.LEADING)
                .addComponent(chckbxWrapAround)
                .addComponent(chckbxSearchBackwards)))
            .addComponent(textField))
          .addPreferredGap(ComponentPlacement.RELATED)
          .addGroup(gl.createParallelGroup(Alignment.TRAILING)
            .addComponent(btnFind)
            .addComponent(btnCancel))
          .addContainerGap())
    );
    gl.setVerticalGroup(
      gl.createParallelGroup(Alignment.LEADING)
        .addGroup(gl.createSequentialGroup()
          .addContainerGap()
          .addGroup(gl.createParallelGroup(Alignment.LEADING)
            .addComponent(btnFind)
            .addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
            .addComponent(lblFindWhat))
          .addPreferredGap(ComponentPlacement.RELATED)
          .addGroup(gl.createParallelGroup(Alignment.LEADING)
            .addGroup(gl.createParallelGroup(Alignment.TRAILING)
              .addGroup(gl.createSequentialGroup()
                .addComponent(chckbxMatchCase)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(chckbxWholeWords))
              .addGroup(gl.createSequentialGroup()
                .addComponent(chckbxWrapAround)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(chckbxSearchBackwards)))
            .addComponent(btnCancel))
          .addContainerGap(24, Short.MAX_VALUE))
    );
    gl.linkSize(SwingConstants.HORIZONTAL, new Component[] {btnFind, btnCancel});
    gl.setAutoCreateGaps(true);
    gl.setAutoCreateContainerGaps(true);
    frame.getContentPane().setLayout(gl);
  }
}
