package codingDashboardKasir;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class componentDashboardBesar extends JPanel{
     @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics = (Graphics2D) g.create();
        Image img = new ImageIcon(getClass().getResource("/codingDashboardKasir/Desktop - 7 (4).png")).getImage();
        graphics.drawImage(img, 0, 0, getWidth(), getHeight(), null);
        graphics.dispose();
    }
}
