import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class ListFoldersMain {

  JFrame frame;
  
  JTextArea taFilterExt;
  JTextArea taExcludeExt;
  JTextArea taFilterDir;
  JTextArea taOutput;
  
  JTextField tfPath;
  JTextField tfExportName;
  
  JCheckBox chExportText;
  JCheckBox chExportMarkup;
  JCheckBox chExportTree;
  
  public JScrollPane pFilterExtScroll;
  public JScrollPane pExcludeExtScroll;
  public JScrollPane pFilterDirScroll;
  public JScrollPane pOutputScroll;
  
  static ListFoldersMain window;
  
  static Database db;
  Functions fun;
  public JToggleButton btnManageOptions;
  
  ManageOptions manOpt;
  Dialog dialog;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());  
          window = new ListFoldersMain();
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
    db=new Database();
    fun=new Functions();
    
    frame = new JFrame();
    frame.addComponentListener(new ComponentAdapter() {
      @Override
      public void componentMoved(ComponentEvent e) {
        if(manOpt!=null && manOpt.isVisible())
          fun.stickWindow(frame, manOpt);
      }
    });
    frame.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
//        db.updateConfig("last","123");
      }
      @Override
      public void windowClosed(WindowEvent e) {
        String value=fun.encodeJSON(fun.getFieldsMap(window));
        db.updateConfig("last",value);
      }
      @Override
      public void windowOpened(WindowEvent e) {
        fun.loadFields(window);
      }
    });
    frame.setBounds(0, 0, 516, 600);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    
    KeyboardFocusManager kfm = KeyboardFocusManager.getCurrentKeyboardFocusManager();
    kfm.addKeyEventDispatcher( new KeyEventDispatcher() {
      
      @Override
      public boolean dispatchKeyEvent(KeyEvent e) {
//        KeyStroke key1 = KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.ALT_DOWN_MASK);
//        KeyStroke keyStroke = KeyStroke.getKeyStrokeForEvent(e);
        
        if(e.getID()==KeyEvent.KEY_PRESSED && e.getKeyCode()==27){
          SwingUtilities.invokeLater( new Runnable() {
            @Override
            public void run() {
              frame.dispose();
//              System.out.println("Esc");
//              System.exit(0);
            }
          } );
           
          return true;
        }
        return false;
      }
      
    });
    
    pFilterExtScroll = new JScrollPane();
    pExcludeExtScroll = new JScrollPane();
    pFilterDirScroll = new JScrollPane();
    pOutputScroll = new JScrollPane();
      
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
        String path;
        
        path = "C:/1-Roman/Documents/8-test/list-test";
        ScanDirectory scandir = new ScanDirectory(window);

        scandir.processData();
        String text = scandir.text;

        taOutput.setText(text);
      }
    });
    
    JPanel pWrapper = new JPanel();
    GroupLayout gl = new GroupLayout(frame.getContentPane());
    gl.setHorizontalGroup(
      gl.createParallelGroup(Alignment.LEADING)
        .addComponent(pWrapper, GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
    );
    gl.setVerticalGroup(
      gl.createParallelGroup(Alignment.LEADING)
        .addComponent(pWrapper, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
    );
    
    tfPath = new JTextField();
    tfPath.setText("C:/1-Roman/Documents/8-test/list-test");
    tfPath.setMargin(new Insets(2, 5, 2, 2));
    tfPath.setColumns(10);
    
    tfExportName = new JTextField();
    tfExportName.setMargin(new Insets(2, 5, 2, 2));
    tfExportName.setPreferredSize(new Dimension(150, 20));
    
    JLabel lExportName = new JLabel("Export Name");
    
    JPanel pOptions = new JPanel();
    
    JPanel pExports = new JPanel();
    
    chExportText = new JCheckBox("Export Text");
    chExportText.setMargin(new Insets(0, 0, 10, 0));
    chExportText.setIconTextGap(5);
    chExportText.setHorizontalAlignment(SwingConstants.LEFT);
    
    chExportMarkup = new JCheckBox("Export Markup");
    chExportMarkup.setMargin(new Insets(0, 0, 10, 0));
    chExportMarkup.setIconTextGap(5);
    chExportMarkup.setHorizontalAlignment(SwingConstants.LEFT);
    
    chExportTree = new JCheckBox("Export Tree");
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
    
    taFilterExt = new JTextArea();
    taFilterExt.setMargin(new Insets(5, 5, 5, 5));
    
    taFilterExt.setLineWrap(true);
    
    taFilterExt.setFont(new Font("Monospaced", Font.PLAIN, 12));
    
    JLabel lFilterExt = new JLabel("Filter Extensions");
    
    taExcludeExt = new JTextArea();
    taExcludeExt.setLineWrap(true);
    taExcludeExt.setMargin(new Insets(5, 5, 5, 5));
    taExcludeExt.setFont(new Font("Monospaced", Font.PLAIN, 12));
    
    JLabel lExcludeExt = new JLabel("Exclude Extensions");
    
    taFilterDir = new JTextArea();
    taFilterDir.setLineWrap(true);
    taFilterDir.setMargin(new Insets(5, 5, 5, 5));
    
    taFilterDir.setFont(new Font("Monospaced", Font.PLAIN, 12));
    
    JLabel lFilterDir = new JLabel("Filter Directories");
    
    JLabel lExportOptions = new JLabel("Export Options");
    GroupLayout gl_pOptions = new GroupLayout(pOptions);
    
    gl_pOptions.setHorizontalGroup(
      gl_pOptions.createSequentialGroup()
        .addGroup(gl_pOptions.createParallelGroup(Alignment.LEADING, false)
          .addComponent(pFilterExtScroll, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
          .addComponent(lFilterExt, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addGap(15)
        .addGroup(gl_pOptions.createParallelGroup(Alignment.LEADING, false)
          .addComponent(pExcludeExtScroll)
          .addComponent(lExcludeExt, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
        .addGap(15)
        .addGroup(gl_pOptions.createParallelGroup(Alignment.LEADING, false)
          .addComponent(pFilterDirScroll, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(lFilterDir, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))
        .addGap(15)
        .addGroup(gl_pOptions.createParallelGroup(Alignment.LEADING)
          .addComponent(pExports, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
          .addComponent(lExportOptions, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE))
    );

    gl_pOptions.setVerticalGroup(
      gl_pOptions.createParallelGroup(Alignment.LEADING)
        .addGroup(Alignment.TRAILING, gl_pOptions.createSequentialGroup()
          .addComponent(lFilterExt)
          .addPreferredGap(ComponentPlacement.UNRELATED)
          .addComponent(pFilterExtScroll, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE))
        .addGroup(Alignment.TRAILING, gl_pOptions.createSequentialGroup()
          .addComponent(lExcludeExt)
          .addPreferredGap(ComponentPlacement.UNRELATED)
          .addComponent(pExcludeExtScroll, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE))
        .addGroup(Alignment.TRAILING, gl_pOptions.createSequentialGroup()
          .addComponent(lFilterDir)
          .addPreferredGap(ComponentPlacement.UNRELATED)
          .addComponent(pFilterDirScroll, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE))
        .addGroup(Alignment.TRAILING, gl_pOptions.createSequentialGroup()
          .addComponent(lExportOptions)
          .addPreferredGap(ComponentPlacement.UNRELATED)
          .addComponent(pExports, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE))
    );
      
    pOptions.setLayout(gl_pOptions);
    
    taOutput = new JTextArea();
    taOutput.setLineWrap(true);
    taOutput.setMargin(new Insets(5, 5, 5, 5));
    taOutput.setFont(new Font("Monospaced", Font.PLAIN, 12));
    
    btnManageOptions = new JToggleButton("Manage Options");
    btnManageOptions.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED){
          manOpt = new ManageOptions();
          manOpt.pack();
          fun.stickWindow(frame, manOpt);
          manOpt.setVisible(true);
        }else{
          manOpt.setVisible(false);
        }
      }
    });
    
    GroupLayout gl_pWrapper = new GroupLayout(pWrapper);
    gl_pWrapper.setHorizontalGroup(
      gl_pWrapper.createParallelGroup(Alignment.LEADING)
        .addComponent(tfPath, 0, 480, Short.MAX_VALUE)
        .addComponent(pOptions, 0, 480, Short.MAX_VALUE)
        .addComponent(lExportName, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
        .addComponent(tfExportName, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
        .addGroup(gl_pWrapper.createSequentialGroup()
          .addComponent(bScanDir, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
          .addPreferredGap(ComponentPlacement.RELATED, 236, Short.MAX_VALUE)
          .addComponent(btnManageOptions))
        .addComponent(pOutputScroll, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    gl_pWrapper.setVerticalGroup(
      gl_pWrapper.createParallelGroup(Alignment.LEADING)
        .addGroup(gl_pWrapper.createSequentialGroup()
          .addComponent(tfPath, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
          .addGap(18)
          .addComponent(pOptions, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
          .addGap(18)
          .addComponent(lExportName)
          .addGap(6)
          .addComponent(tfExportName, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
          .addGap(18)
          .addGroup(gl_pWrapper.createParallelGroup(Alignment.BASELINE)
            .addComponent(bScanDir, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
            .addComponent(btnManageOptions))
          .addGap(18)
          .addComponent(pOutputScroll, GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
          .addContainerGap())
    );
    gl_pWrapper.linkSize(SwingConstants.VERTICAL, new Component[] {btnManageOptions, bScanDir});
    gl_pWrapper.setAutoCreateGaps(true);
    gl_pWrapper.setAutoCreateContainerGaps(true);
    
    pFilterExtScroll.setViewportView(taFilterExt);
    pExcludeExtScroll.setViewportView(taExcludeExt);
    pFilterDirScroll.setViewportView(taFilterDir);
    pOutputScroll.setViewportView(taOutput);
    
    pWrapper.setLayout(gl_pWrapper);
    frame.getContentPane().setLayout(gl);
  }
}
