import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JList;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JRadioButton;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.Insets;
import java.awt.Dimension;

public class ListFoldersMain {

  private JFrame frame;
  private JTextArea taOutput;
  private JTextField tfPath;
  private JTextField tfExportName;
  private final ButtonGroup buttonGroup = new ButtonGroup();

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          ListFoldersMain window = new ListFoldersMain();
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
  public ListFoldersMain() {
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    frame = new JFrame();
    frame.setBounds(100, 100, 620, 691);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    taOutput = new JTextArea();
    taOutput.setFont(new Font("Monospaced", Font.PLAIN, 12));
    
    tfPath = new JTextField();
    tfPath.setColumns(10);
    
        JButton bScanDir = new JButton("Scan Directory");
        bScanDir.addKeyListener(new KeyAdapter() {
          @Override
          public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == 27) {
              System.exit(0);
            }
          }
        });
        bScanDir.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            String path = "C:/1-Roman/Documents/8-test/list-test";
            ScanDirectory scandir = new ScanDirectory();

            scandir.processData(path);
            String text = scandir.text;

            taOutput.setText(text);
          }
        });
    
    JPanel pControls = new JPanel();
    GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
    groupLayout.setHorizontalGroup(
      groupLayout.createParallelGroup(Alignment.TRAILING)
        .addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
          .addContainerGap()
          .addComponent(bScanDir, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
          .addContainerGap(475, Short.MAX_VALUE))
        .addGroup(groupLayout.createSequentialGroup()
          .addContainerGap()
          .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
            .addComponent(taOutput, Alignment.LEADING)
            .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
              .addComponent(tfPath, Alignment.LEADING)
              .addComponent(pControls, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 582, Short.MAX_VALUE)))
          .addContainerGap())
    );
    groupLayout.setVerticalGroup(
      groupLayout.createParallelGroup(Alignment.LEADING)
        .addGroup(groupLayout.createSequentialGroup()
          .addContainerGap()
          .addComponent(tfPath, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
          .addGap(18)
          .addComponent(pControls, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
          .addGap(18)
          .addComponent(bScanDir, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
          .addGap(18)
          .addComponent(taOutput, GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
          .addContainerGap())
    );
    groupLayout.setAutoCreateGaps(true);
    groupLayout.setAutoCreateContainerGaps(true);
    
    tfExportName = new JTextField();
    tfExportName.setColumns(10);
    
    JPanel pFilters = new JPanel();
    
    JTextArea taFilterExt = new JTextArea();
    
    JTextArea taExcludeExt = new JTextArea();
    
    JTextArea taFilterDir = new JTextArea();
    taFilterDir.setMinimumSize(new Dimension(10, 16));
    taFilterDir.setPreferredSize(new Dimension(10, 16));
    
    JPanel pExportOptions = new JPanel();
    
    JRadioButton rdbtnNewRadioButton = new JRadioButton("Export Text");
    rdbtnNewRadioButton.setMargin(new Insets(0, 0, 10, 0));
    rdbtnNewRadioButton.setIconTextGap(5);
    rdbtnNewRadioButton.setHorizontalAlignment(SwingConstants.LEFT);
    buttonGroup.add(rdbtnNewRadioButton);
    
    JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Export Markup");
    rdbtnNewRadioButton_1.setMargin(new Insets(0, 0, 10, 0));
    rdbtnNewRadioButton_1.setIconTextGap(5);
    rdbtnNewRadioButton_1.setHorizontalAlignment(SwingConstants.LEFT);
    buttonGroup.add(rdbtnNewRadioButton_1);
    
    JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("Export Tree");
    rdbtnNewRadioButton_2.setMargin(new Insets(0, 0, 10, 0));
    rdbtnNewRadioButton_2.setIconTextGap(5);
    rdbtnNewRadioButton_2.setHorizontalAlignment(SwingConstants.LEFT);
    buttonGroup.add(rdbtnNewRadioButton_2);
    
    JLabel lblExportName = new JLabel("Export Name");
    GroupLayout gl_pControls = new GroupLayout(pControls);
    gl_pControls.setHorizontalGroup(
      gl_pControls.createParallelGroup(Alignment.LEADING)
        .addGroup(gl_pControls.createSequentialGroup()
          .addGroup(gl_pControls.createParallelGroup(Alignment.LEADING)
            .addGroup(gl_pControls.createSequentialGroup()
              .addComponent(pFilters, GroupLayout.PREFERRED_SIZE, 448, GroupLayout.PREFERRED_SIZE)
              .addPreferredGap(ComponentPlacement.UNRELATED)
              .addComponent(pExportOptions, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE))
            .addComponent(tfExportName, GroupLayout.PREFERRED_SIZE, 253, GroupLayout.PREFERRED_SIZE)
            .addComponent(lblExportName, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))
          .addContainerGap(37, Short.MAX_VALUE))
    );
    gl_pControls.setVerticalGroup(
      gl_pControls.createParallelGroup(Alignment.TRAILING)
        .addGroup(gl_pControls.createSequentialGroup()
          .addGroup(gl_pControls.createParallelGroup(Alignment.TRAILING, false)
            .addComponent(pExportOptions, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
            .addComponent(pFilters, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
          .addPreferredGap(ComponentPlacement.RELATED)
          .addComponent(lblExportName)
          .addPreferredGap(ComponentPlacement.RELATED)
          .addComponent(tfExportName, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
    );
    
    JLabel lblExportOptions = new JLabel("Export Options");
    GroupLayout gl_pExportOptions = new GroupLayout(pExportOptions);
    gl_pExportOptions.setHorizontalGroup(
      gl_pExportOptions.createParallelGroup(Alignment.LEADING)
        .addGroup(gl_pExportOptions.createSequentialGroup()
          .addGroup(gl_pExportOptions.createParallelGroup(Alignment.LEADING)
            .addComponent(lblExportOptions)
            .addComponent(rdbtnNewRadioButton)
            .addComponent(rdbtnNewRadioButton_1)
            .addComponent(rdbtnNewRadioButton_2))
          .addContainerGap(30, Short.MAX_VALUE))
    );
    gl_pExportOptions.setVerticalGroup(
      gl_pExportOptions.createParallelGroup(Alignment.LEADING)
        .addGroup(gl_pExportOptions.createSequentialGroup()
          .addGap(1)
          .addComponent(lblExportOptions)
          .addPreferredGap(ComponentPlacement.UNRELATED)
          .addComponent(rdbtnNewRadioButton)
          .addPreferredGap(ComponentPlacement.RELATED)
          .addComponent(rdbtnNewRadioButton_1)
          .addPreferredGap(ComponentPlacement.RELATED)
          .addComponent(rdbtnNewRadioButton_2)
          .addContainerGap(73, Short.MAX_VALUE))
    );
    gl_pExportOptions.linkSize(SwingConstants.HORIZONTAL, new Component[] {rdbtnNewRadioButton, rdbtnNewRadioButton_1, rdbtnNewRadioButton_2});
    pExportOptions.setLayout(gl_pExportOptions);
    
    JLabel lblFilterExtensions = new JLabel("Filter Extensions");
    
    JLabel lblExcludeExtensions = new JLabel("Exclude Extensions");
    
    JLabel lblFilterDirectories = new JLabel("Filter Directories");
    GroupLayout gl_pFilters = new GroupLayout(pFilters);
    gl_pFilters.setHorizontalGroup(
      gl_pFilters.createParallelGroup(Alignment.LEADING)
        .addGroup(gl_pFilters.createSequentialGroup()
          .addGroup(gl_pFilters.createParallelGroup(Alignment.LEADING)
            .addComponent(lblFilterExtensions)
            .addComponent(taFilterExt, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE))
          .addPreferredGap(ComponentPlacement.UNRELATED)
          .addGroup(gl_pFilters.createParallelGroup(Alignment.LEADING)
            .addComponent(lblExcludeExtensions, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
            .addComponent(taExcludeExt, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE))
          .addPreferredGap(ComponentPlacement.UNRELATED)
          .addGroup(gl_pFilters.createParallelGroup(Alignment.LEADING)
            .addComponent(lblFilterDirectories, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
            .addComponent(taFilterDir, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE))
          .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    gl_pFilters.setVerticalGroup(
      gl_pFilters.createParallelGroup(Alignment.TRAILING)
        .addGroup(gl_pFilters.createSequentialGroup()
          .addGap(1)
          .addGroup(gl_pFilters.createParallelGroup(Alignment.BASELINE)
            .addComponent(lblFilterExtensions)
            .addComponent(lblExcludeExtensions)
            .addComponent(lblFilterDirectories))
          .addPreferredGap(ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
          .addGroup(gl_pFilters.createParallelGroup(Alignment.BASELINE)
            .addComponent(taFilterExt, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
            .addComponent(taExcludeExt, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
            .addComponent(taFilterDir, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)))
    );
    gl_pFilters.linkSize(SwingConstants.VERTICAL, new Component[] {taFilterExt, taExcludeExt, taFilterDir});
    gl_pFilters.linkSize(SwingConstants.HORIZONTAL, new Component[] {taFilterExt, taExcludeExt, taFilterDir});
    gl_pFilters.linkSize(SwingConstants.HORIZONTAL, new Component[] {lblFilterExtensions, lblExcludeExtensions, lblFilterDirectories});
    pFilters.setLayout(gl_pFilters);
    pControls.setLayout(gl_pControls);
    frame.getContentPane().setLayout(groupLayout);
  }
}
