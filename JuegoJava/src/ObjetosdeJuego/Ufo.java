package ObjetosdeJuego;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Estados.GameState;
import Graficos.Assets;
import Graficos.Sonido;
import maths.Vector2D;

public class Ufo extends ObjetosMoviles {
	
	private ArrayList<Vector2D> ruta;
	private Vector2D currentNode;
	private int index;
	private boolean encamino;
	private long fireRate;
	private Sonido sonidoDisparo;

	public Ufo(Vector2D position, Vector2D velocidad, double maxVel, BufferedImage texture, ArrayList<Vector2D> ruta, GameState gamestate) {
		super(position, velocidad, maxVel, texture, gamestate);
		// TODO Auto-generated constructor stub
		this.ruta = ruta;
		index = 0;
		encamino = true;
		fireRate = 0;
		sonidoDisparo = new Sonido(Assets.ufoShoot);
	}
	
	private Vector2D seguimientoderuta() {
		currentNode = ruta.get(index);
		double distanciadenodo = currentNode.sustraer(getcenter()).getMagnitud();
		if(distanciadenodo < Constantes.NODE_RADIUS) {
			index ++;
			if(index >= ruta.size()) {
				encamino = false;
			}
		}
		return seekForce(currentNode);
	}
	
	private Vector2D seekForce(Vector2D target) {
		Vector2D desiredVelocity = target.sustraer(getcenter());
		desiredVelocity = desiredVelocity.normalizar().scale(maxVel);
		return desiredVelocity.sustraer(velocidad);
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		fireRate += dt;
		
		Vector2D rutaenseguimiento;
		
		if(encamino) {
			rutaenseguimiento = seguimientoderuta();
		}else {
			rutaenseguimiento = new Vector2D();
		}
		
		rutaenseguimiento = rutaenseguimiento.scale(1/Constantes.UFO_MASS);
		velocidad = velocidad.add(rutaenseguimiento);
		velocidad = velocidad.limit(maxVel);
		position = position.add(velocidad);
		
		if(position.getX() > Constantes.WIDTH || position.getY() > Constantes.HEIGHT || position.getX() < 0 || position.getY() < 0) {
			Destruir();
		}
		
		//shoot
		if(fireRate > Constantes.UFO_FIRE_RATE) {
			Vector2D toPlayer = gamestate.getPlayer().getcenter().sustraer(getcenter());
			toPlayer = toPlayer.normalizar();
			double currentAngle = toPlayer.getAngulo();
			currentAngle += Math.random()*Constantes.UFO_ANGLE_RANGE - Constantes.UFO_ANGLE_RANGE/2;
			
			if(toPlayer.getX()<0) {
				currentAngle = -currentAngle + Math.PI;
			}
			toPlayer = toPlayer.setDireccion(currentAngle);
			Laser laser = new Laser(
					getcenter().add(toPlayer.scale(width)),
					toPlayer,
					Constantes.LASER_VEL,
					currentAngle + Math.PI/2,
					Assets.laserverde,
					gamestate
					);
			gamestate.getObjetosmoviles().add(0, laser);
			fireRate = 0;
			sonidoDisparo.play();
		}
		
		if(sonidoDisparo.getFramePosition() > 8500) {
			sonidoDisparo.stop();
		}
		
		angulo +=0.05;
		colisiones();
	}
	
	@Override
	public void Destruir() {
		gamestate.addScore(Constantes.UFO_SCORE,position);
		gamestate.playExplosion(position);
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

}
