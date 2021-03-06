package ObjetosdeJuego;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import Estados.GameState;
import maths.Vector2D;

public class Meteoro extends ObjetosMoviles {
	
	private Tama�o tama�o;

	public Meteoro(Vector2D position, Vector2D velocidad, double maxVel, BufferedImage texture, GameState gamestate, Tama�o tama�o) {
		super(position, velocidad, maxVel, texture, gamestate);
		this.tama�o = tama�o;
		this.velocidad = velocidad.scale(maxVel);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		Vector2D playerPos = new Vector2D(gamestate.getPlayer().getcenter());
		int distanceToPlayer = (int)playerPos.sustraer(getcenter()).getMagnitud();
		
		if(distanceToPlayer < Constantes.SHIELD_DISTANCE /2 + width /2) {
			if(gamestate.getPlayer().escudoactivado()) {
				Vector2D fleeForce = fleeForce();
				velocidad = velocidad.add(fleeForce);
			}
		}
		
		if(velocidad.getMagnitud() >= this.maxVel) {
			Vector2D velocidadInversa = new Vector2D(-velocidad.getX(), -velocidad.getY());
			velocidad = velocidad.add(velocidadInversa.normalizar().scale(0.01f));
		}
		
		velocidad = velocidad.limit(Constantes.METEORO_MAX_VEL);
		
		position = position.add(velocidad);
		
		if(position.getX() > Constantes.WIDTH) {
			position.setX(0);
		}
		if(position.getY() > Constantes.HEIGHT) {
			position.setY(0);
		}
		
		if(position.getX() < 0) {
			position.setX(Constantes.WIDTH);
		}
		if(position.getY() < 0) {
			position.setY(Constantes.HEIGHT);
		}
		
		angulo += Constantes.DELTAANGLE/2;
		
	}
	
	private Vector2D fleeForce() {
		Vector2D desiredVelocity = gamestate.getPlayer().getcenter().sustraer(getcenter());
		desiredVelocity = (desiredVelocity.normalizar()).scale(Constantes.METEORO_MAX_VEL);
		Vector2D v = new Vector2D(velocidad);
		return v.sustraer(desiredVelocity);
	}
	
	@Override
	public void Destruir() {
		gamestate.dividirMeteoros(this);
		gamestate.playExplosion(position);
		gamestate.addScore(Constantes.METEORO_SCORE,position);
		super.Destruir();
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		Graphics2D g2d = (Graphics2D)g;
		at = AffineTransform.getTranslateInstance(position.getX(), position.getY());
		at.rotate(angulo, width/2, height/2);
		g2d.drawImage(texture, at, null);
	}
	
	public Tama�o getTama�o() {
		return tama�o;
	}

}
