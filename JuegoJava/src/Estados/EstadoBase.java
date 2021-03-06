package Estados;

import java.awt.Graphics;

public abstract class EstadoBase {
	
	private static EstadoBase currentState = null;
	
	public static EstadoBase getCurrentState() {return currentState;}
	
	public static void changeState(EstadoBase newState) {
		currentState = newState;
	}
	
	public abstract void update(float dt);
	public abstract void draw(Graphics g);
}
