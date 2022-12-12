import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

class DrawPanel extends JPanel {  //function to show online or not( use circle's color)
         String state = "";
         public DrawPanel(String state) {
            this.state=state;
         }
         @Override
         public void paint(Graphics g) {
            super.paint(g);
            g.setColor(Color.RED); //offline : red
            
            if (state.equals("1")) {   //online : green
               g.setColor(Color.GREEN);
            }
            g.fillOval(0, 0, 10, 10);
         }
         
         
      }
