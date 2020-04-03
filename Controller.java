package tpfinalcomplementPOO_2;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements KeyListener {
	
	private Board b;
	
	Controller(Board b) {
		this.b = b;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP && this.b.getValidMoves().contains("u")) { //UP
			this.b.moveElement("u");
		}
		
		if(e.getKeyCode() == KeyEvent.VK_DOWN && this.b.getValidMoves().contains("d")) { //DOWN
			this.b.moveElement("d");
		}
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT && this.b.getValidMoves().contains("l")) { //LEFT
			this.b.moveElement("l");
		}
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT && this.b.getValidMoves().contains("r")) { //LEFT
			this.b.moveElement("r");
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
