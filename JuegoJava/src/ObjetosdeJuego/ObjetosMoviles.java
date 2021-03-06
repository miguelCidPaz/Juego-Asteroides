package ObjetosdeJuego;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Estados.GameState;
import Graficos.Assets;
import Graficos.Sonido;
import maths.Vector2D;

public abstract class ObjetosMoviles extends GameObject{
	
	protected Vector2D velocidad;
	protected AffineTransform at;
	protected double angulo;
	protected double maxVel;
	protected int width;
	protected int height;
	protected GameState gamestate;
	protected boolean destruido;
	
	private Sonido explosion;

	public ObjetosMoviles(Vector2D position, Vector2D velocidad, double maxVel, BufferedImage texture, GameState gamestate) {
		super(position, texture);
		this.velocidad = velocidad;
		this.maxVel = maxVel;
		this.gamestate = gamestate;
		width = texture.getWidth();
		height = texture.getHeight();
		angulo = 0;
		explosion = new Sonido(Assets.sonidoexpl);
		destruido = false;
		// TODO Auto-generated constructor stub
	}
	
	protected void colisiones() {
		ArrayList<ObjetosMoviles> objetosmoviles = gamestate.getObjetosmoviles();
		
		for(int i = 0 ; i<objetosmoviles.size() ; i++ ) {
			ObjetosMoviles m = objetosmoviles.get(i);
			if(m.equals(this)) {
				continue;
			}
			double distancia = m.getcenter().sustraer(getcenter()).getMagnitud();
			
			if(distancia < m.width/2 + width/2 && objetosmoviles.contains(this) && !m.destruido && !destruido) {
				colisiondeobjetos(m, this);
			}
		}
	}
	
	private void colisiondeobjetos(ObjetosMoviles a, ObjetosMoviles b) {
		Player p = null;
		
		if(a instanceof Player) {
			p = (Player)a;
		}else if(b instanceof Player) {
			p = (Player)b;
		}
		
		if(p != null && p.isSpawning()) {
			return;
		}
		
		if(a instanceof Meteoro && b instanceof Meteoro) {
			return;
		}
		
		if(!(a instanceof PowerUp || b instanceof PowerUp)) {
			a.Destruir();
			b.Destruir();
			return;
		}
		
		if(p!=null) {
			if(a instanceof Player) {
				((PowerUp)b).executeAction();
				b.Destruir();
			}else if(b instanceof Player) {
				((PowerUp)a).executeAction();
				a.Destruir();
			}
		}
	}
	
	protected void Destruir() {
		destruido = true;
		if(!(this instanceof Laser)) {
			explosion.play();
		}
	}
	
	public boolean estaDestruido() {return destruido;}
	
	protected Vector2D getcenter() {
		return new Vector2D(position.getX()+width/2,position.getY()+height/2);
	}

}
