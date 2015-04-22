package tests;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class TestDialog extends JDialog {

  private final JPanel contentPanel = new JPanel();
  private JLabel lStatus;
  public JPanel panel;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    try {
      TestDialog dialog = new TestDialog();
      dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
      dialog.setVisible(true);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  private void drawBorder(Graphics g){
    int x=this.getWidth();
    
    Graphics2D g2=(Graphics2D)g;
    float dash1[] = {1.0f};
    BasicStroke stroke = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1.0f, dash1, 0.0f);
    g2.setStroke(stroke);
    g2.setColor((Color) new Color(120, 120, 120));
    
    g2.draw(new Line2D.Double(0, 0, x, 0));
  }

  /**
   * Create the dialog.
   */
  public TestDialog() {
    setBounds(100, 100, 450, 300);
    getContentPane().setLayout(new BorderLayout());
    contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    getContentPane().add(contentPanel, BorderLayout.CENTER);
    
    panel = new JPanel(){
      @Override
      public void paintComponent(Graphics g){
        drawBorder(g);
      }
    };
    
    FlowLayout flowLayout = (FlowLayout) panel.getLayout();
    flowLayout.setAlignment(FlowLayout.LEFT);
    
    GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
    gl_contentPanel.setHorizontalGroup(
      gl_contentPanel.createParallelGroup(Alignment.LEADING)
        .addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
          .addContainerGap()
          .addComponent(panel, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
          .addContainerGap())
    );
    gl_contentPanel.setVerticalGroup(
      gl_contentPanel.createParallelGroup(Alignment.TRAILING)
        .addGroup(gl_contentPanel.createSequentialGroup()
          .addContainerGap(178, Short.MAX_VALUE)
          .addComponent(panel, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
          .addContainerGap())
    );
    
    lStatus=new JLabel();
    panel.add(lStatus);
    lStatus.setOpaque(true);
    lStatus.setText("New label1");
    contentPanel.setLayout(gl_contentPanel);
  }
}
