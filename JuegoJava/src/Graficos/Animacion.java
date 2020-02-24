package Graficos;

import java.awt.image.BufferedImage;

import maths.Vector2D;

public class Animacion {
	
	private BufferedImage[]frames;
	private int velocidad;
	private int index;
	private boolean corriendo;
	private Vector2D position;
	private long time;
	
	public Animacion(BufferedImage[]frames, int velocidad, Vector2D position) {
		this.frames = frames;
		this.velocidad = velocidad;
		this.position = position;
		index = 0;
		corriendo = true;
	}

	public void update(float dt) {
		
		time += dt;
		
		if(time>velocidad) {
			time = 0;
			index ++;
			if(index >= frames.length) {
				corriendo = false;
				index = 0;
			}
		}
		
	}
	
	public boolean estacorriendo() {
		return corriendo;
	}
	
	public Vector2D getPosition() {
		return position;
	}
	
	public BufferedImage getCurrentFrame() {
		return frames[index];
	}
	
	
}
