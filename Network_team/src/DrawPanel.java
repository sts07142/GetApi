import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

class DrawPanel extends JPanel {  //<----------온오프라인 나타내는 동그라미 관련 함수
	String state = ""; //<--------------------- DB에서 상태를 읽어서 state라는 string 변수에 저장
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.RED); //상태 default값으로 "off"로 해놓는다 
		if (state.equalsIgnoreCase("on") == true) {   //<------------ DB에서 읽었을 때 on 상태일 경우
			g.setColor(Color.GREEN);
		}
		g.fillOval(0, 0, 10, 10);
		
	}
}