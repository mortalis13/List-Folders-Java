import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.Dimension;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.JRadioButton;
import java.awt.Insets;
import javax.swing.JCheckBox;


public class tmp {

  private JFrame frame;
  private JTextField textField;
  private JTextField textField_1;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          Temp window = new Temp();
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
  public Temp() {
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    frame = new JFrame();
    frame.setBounds(0, 0, 600, 700);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    JPanel pWrapper = new JPanel();
    GroupLayout gl = new GroupLayout(frame.getContentPane());
    gl.setHorizontalGroup(
      gl.createParallelGroup(Alignment.LEADING)
        .addComponent(pWrapper, GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
    );
    gl.setVerticalGroup(
      gl.createParallelGroup(Alignment.LEADING)
        .addComponent(pWrapper, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
    );
    
    textField = new JTextField();
    textField.setColumns(10);
    
    textField_1 = new JTextField();
    textField_1.setColumns(10);
    
    JLabel label_4 = new JLabel("Export Name");
    
    JPanel pOptions = new JPanel();
    
    JPanel pExports = new JPanel();
    
    JCheckBox radioButton = new JCheckBox("Export Text");
    radioButton.setMargin(new Insets(0, 0, 10, 0));
    radioButton.setIconTextGap(5);
    radioButton.setHorizontalAlignment(SwingConstants.LEFT);
    
    JCheckBox radioButton_1 = new JCheckBox("Export Markup");
    radioButton_1.setMargin(new Insets(0, 0, 10, 0));
    radioButton_1.setIconTextGap(5);
    radioButton_1.setHorizontalAlignment(SwingConstants.LEFT);
    
    JCheckBox radioButton_2 = new JCheckBox("Export Tree");
    radioButton_2.setMargin(new Insets(0, 0, 10, 0));
    radioButton_2.setIconTextGap(5);
    radioButton_2.setHorizontalAlignment(SwingConstants.LEFT);
    GroupLayout gl_pExports = new GroupLayout(pExports);
    gl_pExports.setHorizontalGroup(
      gl_pExports.createParallelGroup(Alignment.LEADING)
        .addGap(0, 123, Short.MAX_VALUE)
        .addGap(0, 123, Short.MAX_VALUE)
        .addGroup(gl_pExports.createSequentialGroup()
          .addGroup(gl_pExports.createParallelGroup(Alignment.LEADING)
            .addComponent(radioButton)
            .addComponent(radioButton_1)
            .addComponent(radioButton_2))
          .addContainerGap(30, Short.MAX_VALUE))
    );
    gl_pExports.setVerticalGroup(
      gl_pExports.createParallelGroup(Alignment.LEADING)
        .addGap(0, 162, Short.MAX_VALUE)
        .addGap(0, 162, Short.MAX_VALUE)
        .addGroup(gl_pExports.createSequentialGroup()
          .addGap(24)
          .addComponent(radioButton)
          .addPreferredGap(ComponentPlacement.RELATED)
          .addComponent(radioButton_1)
          .addPreferredGap(ComponentPlacement.RELATED)
          .addComponent(radioButton_2)
          .addContainerGap(51, Short.MAX_VALUE))
    );
    gl_pExports.linkSize(SwingConstants.HORIZONTAL, new Component[] {radioButton, radioButton_1, radioButton_2});
    pExports.setLayout(gl_pExports);
    
    JTextArea textArea = new JTextArea();
    
    JLabel label_1 = new JLabel("Filter Extensions");
    
    JTextArea textArea_1 = new JTextArea();
    
    JLabel label_2 = new JLabel("Exclude Extensions");
    
    JTextArea textArea_2 = new JTextArea();
    textArea_2.setPreferredSize(new Dimension(10, 16));
    textArea_2.setMinimumSize(new Dimension(10, 16));
    
    JLabel label_3 = new JLabel("Filter Directories");
    
    JLabel label = new JLabel("Export Options");
    GroupLayout gl_pOptions = new GroupLayout(pOptions);
    
    gl_pOptions.setHorizontalGroup(
      gl_pOptions.createParallelGroup(Alignment.LEADING)
        .addGroup(gl_pOptions.createSequentialGroup()
          .addGroup(gl_pOptions.createParallelGroup(Alignment.LEADING, false)
            .addComponent(textArea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
            .addComponent(label_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
          .addGap(42)
          .addGroup(gl_pOptions.createParallelGroup(Alignment.LEADING, false)
            .addComponent(textArea_1)
            .addComponent(label_2, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
          .addGap(28)
          .addGroup(gl_pOptions.createParallelGroup(Alignment.LEADING, false)
            .addComponent(textArea_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(label_3, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))
          .addPreferredGap(ComponentPlacement.UNRELATED)
          .addGroup(gl_pOptions.createParallelGroup(Alignment.LEADING)
            .addComponent(pExports, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
            .addComponent(label, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE))
          .addContainerGap(26, Short.MAX_VALUE))
    );

    gl_pOptions.setVerticalGroup(
      gl_pOptions.createParallelGroup(Alignment.LEADING)
        .addGroup(Alignment.TRAILING, gl_pOptions.createSequentialGroup()
          .addGap(1)
          .addGroup(gl_pOptions.createParallelGroup(Alignment.BASELINE)
            .addComponent(label_1)
            .addComponent(label_2)
            .addComponent(label_3)
            .addComponent(label))
          .addPreferredGap(ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
          .addGroup(gl_pOptions.createParallelGroup(Alignment.LEADING)
            .addGroup(Alignment.TRAILING, gl_pOptions.createSequentialGroup()
              .addComponent(pExports, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
              .addGap(11))
            .addGroup(Alignment.TRAILING, gl_pOptions.createParallelGroup(Alignment.BASELINE)
              .addComponent(textArea, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
              .addComponent(textArea_1, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
              .addComponent(textArea_2, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE))))
    );

    gl_pOptions.linkSize(SwingConstants.VERTICAL, new Component[] {textArea, textArea_1, textArea_2});
    gl_pOptions.linkSize(SwingConstants.HORIZONTAL, new Component[] {textArea, label_1, label_2, label_3});
    pOptions.setLayout(gl_pOptions);
    GroupLayout gl_pWrapper = new GroupLayout(pWrapper);
    gl_pWrapper.setAutoCreateGaps(true);
    gl_pWrapper.setAutoCreateContainerGaps(true);
    
    gl_pWrapper.setHorizontalGroup(
      gl_pWrapper.createParallelGroup(Alignment.LEADING)
        .addComponent(textField, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(pOptions, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(label_4, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
        .addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
    );
    
    gl_pWrapper.setVerticalGroup(
      gl_pWrapper.createSequentialGroup()
        .addContainerGap()
        .addComponent(textField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        .addGap(18)
        .addComponent(pOptions, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
        .addGap(18)
        .addComponent(label_4)
        .addGap(6)
        .addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
        .addContainerGap(364, Short.MAX_VALUE)
    );
    // gl_pWrapper.linkSize(SwingConstants.HORIZONTAL, new Component[] {pOptions, pWrapper});
    
    pWrapper.setLayout(gl_pWrapper);
    frame.getContentPane().setLayout(gl);
  }
}
