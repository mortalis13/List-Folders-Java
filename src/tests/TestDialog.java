package tests;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.geom.Line2D;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import tests.DashedBorder.DashedLineIcon;
import javax.swing.JProgressBar;
import javax.swing.LayoutStyle.ComponentPlacement;

class CustomBorder implements Border{ 
  
  public void paintBorder(Component c, Graphics g, int x, int y, int width, int height){
    drawBorder(c,g);
  }
  
  public Insets getBorderInsets(Component c){
    return new Insets(0,0,0,0);
  }
  
  public boolean isBorderOpaque(){
    return true;
  }
  
  private void drawBorder(Component c, Graphics g){
    int x = c.getWidth();
    float dash[] = {1.0f};
    Graphics2D g2;
    BasicStroke stroke;

    g2 = (Graphics2D)g;
    stroke = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1.0f, dash, 0.0f);
    g2.setStroke(stroke);
    g2.setColor((Color) new Color(120, 120, 120));

    g2.draw(new Line2D.Double(0, 0, x, 0));
  }
}

public class TestDialog extends JDialog {

  private final JPanel contentPanel = new JPanel();
  private JLabel lStatus;
  public JPanel panel;
  public JProgressBar progressBar;

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
    
    Icon icon = new DashedLineIcon(Color.BLACK, 1, 1, 1, 0);
    Border matte = BorderFactory.createMatteBorder(1, 0, 0, 0, icon);
//    lStatus.setBorder( matte );
    
//    System.out.println(matte.getBorderInsets(lStatus));
    
    Border dashed = BorderFactory.createDashedBorder(null, 5, 5);
    Border empty = BorderFactory.createEmptyBorder(1, 0, 0, 0);
//    Border empty = BorderFactory.createEmptyBorder(1, -1, -1, -1);
    Border compound = new CompoundBorder(empty, dashed);
    
    Border custom=new CustomBorder();
    
    panel = new JPanel();
//    panel.setBorder( compound );
//    panel.setBorder( matte );
    panel.setBorder( custom );
    
//    panel = new JPanel(){
//      @Override
//      public void paintComponent(Graphics g){
//        drawBorder(g);
//      }
//    };
    
    FlowLayout flowLayout = (FlowLayout) panel.getLayout();
    flowLayout.setAlignment(FlowLayout.LEFT);
    
    progressBar = new JProgressBar();
    
    GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
    gl_contentPanel.setHorizontalGroup(
      gl_contentPanel.createParallelGroup(Alignment.TRAILING)
        .addGroup(gl_contentPanel.createSequentialGroup()
          .addContainerGap()
          .addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
            .addComponent(progressBar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
            .addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE))
          .addContainerGap())
    );
    gl_contentPanel.setVerticalGroup(
      gl_contentPanel.createParallelGroup(Alignment.TRAILING)
        .addGroup(gl_contentPanel.createSequentialGroup()
          .addContainerGap()
          .addComponent(progressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
          .addPreferredGap(ComponentPlacement.RELATED, 153, Short.MAX_VALUE)
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
