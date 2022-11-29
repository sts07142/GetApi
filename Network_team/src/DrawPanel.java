import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

class DrawPanel extends JPanel {  //<----------�¿������� ��Ÿ���� ���׶�� ���� �Լ�
	String state = ""; //<--------------------- DB���� ���¸� �о state��� string ������ ����
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.RED); //���� default������ "off"�� �س��´� 
		if (state.equalsIgnoreCase("on") == true) {   //<------------ DB���� �о��� �� on ������ ���
			g.setColor(Color.GREEN);
		}
		g.fillOval(0, 0, 10, 10);
		
	}
}