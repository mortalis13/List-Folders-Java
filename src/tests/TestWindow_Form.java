package tests;
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


public class TestWindow_Form {

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
          TestWindow_Form window = new TestWindow_Form();
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
  public TestWindow_Form() {
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    JLabel lblFindWhat = new JLabel("Find What:");
    
    textField = new JTextField();
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
    
    JButton btnCancel = new JButton("Cancel");
    GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
    groupLayout.setHorizontalGroup(
      groupLayout.createParallelGroup(Alignment.LEADING)
        .addGroup(groupLayout.createSequentialGroup()
          .addContainerGap()
          .addComponent(lblFindWhat)
          .addPreferredGap(ComponentPlacement.RELATED)
          .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
            .addGroup(groupLayout.createSequentialGroup()
              .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                .addComponent(chckbxWholeWords)
                .addComponent(chckbxMatchCase))
              .addPreferredGap(ComponentPlacement.RELATED)
              .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                .addComponent(chckbxWrapAround)
                .addComponent(chckbxSearchBackwards)))
            .addComponent(textField))
          .addPreferredGap(ComponentPlacement.RELATED)
          .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
            .addComponent(btnFind, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnCancel))
          .addContainerGap(41, Short.MAX_VALUE))
    );
    groupLayout.setVerticalGroup(
      groupLayout.createParallelGroup(Alignment.LEADING)
        .addGroup(groupLayout.createSequentialGroup()
          .addContainerGap()
          .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(groupLayout.createSequentialGroup()
              .addComponent(btnFind)
              .addPreferredGap(ComponentPlacement.RELATED)
              .addComponent(btnCancel))
            .addGroup(groupLayout.createSequentialGroup()
              .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                .addComponent(lblFindWhat)
                .addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
              .addPreferredGap(ComponentPlacement.UNRELATED)
              .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                .addGroup(groupLayout.createSequentialGroup()
                  .addComponent(chckbxMatchCase)
                  .addPreferredGap(ComponentPlacement.RELATED)
                  .addComponent(chckbxWholeWords))
                .addGroup(groupLayout.createSequentialGroup()
                  .addComponent(chckbxWrapAround)
                  .addPreferredGap(ComponentPlacement.RELATED)
                  .addComponent(chckbxSearchBackwards)))))
          .addContainerGap(32, Short.MAX_VALUE))
    );
    groupLayout.setAutoCreateGaps(true);
    groupLayout.setAutoCreateContainerGaps(true);
    frame.getContentPane().setLayout(groupLayout);
  }
}
