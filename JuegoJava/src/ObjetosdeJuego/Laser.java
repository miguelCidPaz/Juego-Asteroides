package ObjetosdeJuego;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import Estados.GameState;
import maths.Vector2D;

public class Laser extends ObjetosMoviles {

	public Laser(Vector2D position, Vector2D velocidad, double maxVel, double angulo, BufferedImage texture, GameState gamestate) {
		super(position, velocidad, maxVel, texture, gamestate);
		// TODO Auto-generated constructor stub
		this.angulo = angulo;
		this.velocidad = velocidad.scale(maxVel);
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		position = position.add(velocidad);
		
		if(position.getX() < 0 || position.getX() > Constantes.WIDTH || position.getY() < 0 || position.getY() > Constantes.HEIGHT) {
			Destruir();
		}
		
		colisiones();
		
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		Graphics2D g2d = (Graphics2D)g;
		at = AffineTransform.getTranslateInstance(position.getX()-width/2, position.getY());
		at.rotate(angulo, width/2, 0);
		g2d.drawImage(texture, at, null);
	}
	
	@Override
	public Vector2D getcenter() {
		return new Vector2D(position.getX()+width/2,position.getY()+width/2);
	}

}
