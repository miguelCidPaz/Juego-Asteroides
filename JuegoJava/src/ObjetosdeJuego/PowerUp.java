package ObjetosdeJuego;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import Estados.GameState;
import Graficos.Assets;
import Graficos.Sonido;
import UI.Action;
import maths.Vector2D;

public class PowerUp extends ObjetosMoviles{
	
	private long duracion;
	private Action action;
	private Sonido powerUpPick;
	private BufferedImage typeTexture;
	
	public PowerUp(Vector2D posicion, BufferedImage texture, Action action, GameState gamestate) {
		super(posicion, new Vector2D(), 0, Assets.orb,gamestate);
		this.action = action;
		this.typeTexture = texture;
		duracion = 0;
		powerUpPick = new Sonido(Assets.powerup);
	}
	
	void executeAction() {
		action.doAction();
		powerUpPick.play();
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		angulo += 0.1;
		duracion += dt;
		if(duracion > Constantes.POWERUP_DURATION) {
			this.Destruir();
		}
		colisiones();
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		Graphics2D g2d = (Graphics2D) g;
		
		at = AffineTransform.getTranslateInstance(
				position.getX() + Assets.orb.getWidth()/2 - typeTexture.getWidth()/2,
				position.getY() + Assets.orb.getHeight()/2 - typeTexture.getHeight()/2);
		
		at.rotate(angulo,
				typeTexture.getWidth()/2,
				typeTexture.getHeight()/2);
		
		g.drawImage(Assets.orb, (int)position.getX(), (int)position.getY(), null);
		
		
		
		g2d.drawImage(typeTexture, at, null);
	}

}
