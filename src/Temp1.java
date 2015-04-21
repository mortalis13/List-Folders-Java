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


public class Temp1 {

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
          Temp1 window = new Temp1();
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
  public Temp1() {
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
    
    JCheckBox chExportText = new JCheckBox("Export Text");
    chExportText.setMargin(new Insets(0, 0, 10, 0));
    chExportText.setIconTextGap(5);
    chExportText.setHorizontalAlignment(SwingConstants.LEFT);
    
    JCheckBox chExportMarkup = new JCheckBox("Export Markup");
    chExportMarkup.setMargin(new Insets(0, 0, 10, 0));
    chExportMarkup.setIconTextGap(5);
    chExportMarkup.setHorizontalAlignment(SwingConstants.LEFT);
    
    JCheckBox chExportTree = new JCheckBox("Export Tree");
    chExportTree.setMargin(new Insets(0, 0, 10, 0));
    chExportTree.setIconTextGap(5);
    chExportTree.setHorizontalAlignment(SwingConstants.LEFT);
    GroupLayout gl_pExports = new GroupLayout(pExports);
    
    gl_pExports.setHorizontalGroup(
      gl_pExports.createParallelGroup(Alignment.LEADING)
        .addComponent(chExportText)
        .addComponent(chExportMarkup)
        .addComponent(chExportTree)
    );
    
    gl_pExports.setVerticalGroup(
      gl_pExports.createSequentialGroup()
        .addComponent(chExportText)
        .addPreferredGap(ComponentPlacement.RELATED)
        .addComponent(chExportMarkup)
        .addPreferredGap(ComponentPlacement.RELATED)
        .addComponent(chExportTree)
    );
    
    gl_pExports.linkSize(SwingConstants.HORIZONTAL, new Component[] {chExportText, chExportMarkup, chExportTree});
    pExports.setLayout(gl_pExports);
    
    JTextArea taFilterExt = new JTextArea();
    
    JLabel lFilterExt = new JLabel("Filter Extensions");
    
    JTextArea taExcludeExt = new JTextArea();
    
    JLabel lExcludeExt = new JLabel("Exclude Extensions");
    
    JTextArea taFilterDir = new JTextArea();
    taFilterDir.setPreferredSize(new Dimension(10, 16));
    taFilterDir.setMinimumSize(new Dimension(10, 16));
    
    JLabel lFilterDir = new JLabel("Filter Directories");
    
    JLabel lExportOptions = new JLabel("Export Options");
    GroupLayout gl_pOptions = new GroupLayout(pOptions);
    
    gl_pOptions.setHorizontalGroup(
      gl_pOptions.createSequentialGroup()
        .addGroup(gl_pOptions.createParallelGroup(Alignment.LEADING, false)
          .addComponent(taFilterExt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
          .addComponent(lFilterExt, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addGap(15)
        .addGroup(gl_pOptions.createParallelGroup(Alignment.LEADING, false)
          .addComponent(taExcludeExt)
          .addComponent(lExcludeExt, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
        .addGap(15)
        .addGroup(gl_pOptions.createParallelGroup(Alignment.LEADING, false)
          .addComponent(taFilterDir, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(lFilterDir, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))
        .addGap(15)
        .addGroup(gl_pOptions.createParallelGroup(Alignment.LEADING)
          .addComponent(pExports, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
          .addComponent(lExportOptions, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE))
        .addContainerGap(26, Short.MAX_VALUE)
    );

    gl_pOptions.setVerticalGroup(
      gl_pOptions.createParallelGroup(Alignment.LEADING)
        .addGroup(Alignment.TRAILING, gl_pOptions.createSequentialGroup()
          .addComponent(lFilterExt)
          .addPreferredGap(ComponentPlacement.UNRELATED)
          .addComponent(taFilterExt, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE))
        .addGroup(Alignment.TRAILING, gl_pOptions.createSequentialGroup()
          .addComponent(lExcludeExt)
          .addPreferredGap(ComponentPlacement.UNRELATED)
          .addComponent(taExcludeExt, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE))
        .addGroup(Alignment.TRAILING, gl_pOptions.createSequentialGroup()
          .addComponent(lFilterDir)
          .addPreferredGap(ComponentPlacement.UNRELATED)
          .addComponent(taFilterDir, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE))
        .addGroup(Alignment.TRAILING, gl_pOptions.createSequentialGroup()
          .addComponent(lExportOptions)
          .addPreferredGap(ComponentPlacement.UNRELATED)
          .addComponent(pExports, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE))
    );
      
    gl_pOptions.linkSize(SwingConstants.VERTICAL, new Component[] {taFilterExt, taExcludeExt, taFilterDir});
    gl_pOptions.linkSize(SwingConstants.HORIZONTAL, new Component[] {taFilterExt, lFilterExt, lExcludeExt, lFilterDir});
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
