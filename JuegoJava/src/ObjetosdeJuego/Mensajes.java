package ObjetosdeJuego;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import Graficos.Text;
import maths.Vector2D;

public class Mensajes {
	
	private float alpha;
	private String text;
	private Vector2D posicion;
	private Color color;
	private boolean center;
	private boolean desvanecido;
	private Font fuente;
	private final float deltaAlpha = 0.01f;
	private boolean destruido;
	
	public Mensajes(Vector2D posicion, boolean desvanecido, String text, Color color, boolean center, Font fuente) {
		this.fuente = fuente;
		this.text = text;
		this.posicion = posicion;
		this.desvanecido = desvanecido;
		this.color = color;
		this.center = center;
		this.destruido = false;
		
		if(desvanecido) {
			alpha = 1;
		}else {
			alpha = 0;
		}
	}
	
	public void draw(Graphics2D g2d) {
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		Text.drawText(g2d, text, posicion, center, color, fuente);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		posicion.setY(posicion.getY()-1);
		
		if(desvanecido) {
			alpha -= deltaAlpha;
		}else {
			alpha += deltaAlpha;
		}
		
		if(desvanecido && alpha < 0) {
			destruido = true;
		}
		
		if(!desvanecido && alpha > 1) {
			desvanecido = true;
			alpha = 1;
		}
		
	}
	public boolean estadestruido() {return destruido;}
}
