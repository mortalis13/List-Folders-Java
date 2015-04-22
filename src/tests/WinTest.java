package tests;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import java.awt.Font;
import java.awt.Insets;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDialog;
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

import javax.swing.JScrollPane;
import javax.swing.JInternalFrame;

import listfolders.ManageOptionsDialog;


public class WinTest {

  private JFrame frame;
  public JScrollPane scrollPane;
  public JTextArea textArea_1;
  public JButton button_1;

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
    frame.setSize(new Dimension(498, 565));
    frame.addComponentListener(new ComponentAdapter() {
      @Override
      public void componentResized(ComponentEvent e) {
      }
    });
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    
    JButton button = new JButton("New button");
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JDialog d1=new JDialog();
        d1.setBounds(132, 132, 300, 200);
        d1.setTitle("(modeless dialog)");
        d1.setVisible(true);
      }
    });
    
    scrollPane = new JScrollPane();
    
    button_1 = new JButton("New button");
    button_1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JDialog d2=new JDialog(frame);
//        JDialog d2=new JDialog(frame, Dialog.ModalityType.DOCUMENT_MODAL);
        d2.setBounds(132, 132, 300, 200);
        d2.setTitle("(modal dialog)");
        d2.setVisible(true);
      }
    });
    
    GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
    groupLayout.setHorizontalGroup(
      groupLayout.createParallelGroup(Alignment.LEADING)
        .addGroup(groupLayout.createSequentialGroup()
          .addContainerGap()
          .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
            .addComponent(button)
            .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 460, GroupLayout.PREFERRED_SIZE)
            .addComponent(button_1))
          .addContainerGap(12, Short.MAX_VALUE))
    );
    groupLayout.setVerticalGroup(
      groupLayout.createParallelGroup(Alignment.LEADING)
        .addGroup(groupLayout.createSequentialGroup()
          .addGap(12)
          .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE)
          .addPreferredGap(ComponentPlacement.RELATED)
          .addComponent(button)
          .addPreferredGap(ComponentPlacement.UNRELATED)
          .addComponent(button_1)
          .addContainerGap(220, Short.MAX_VALUE))
    );
    
    textArea_1 = new JTextArea();
    scrollPane.setViewportView(textArea_1);
    frame.getContentPane().setLayout(groupLayout);
  }
}
