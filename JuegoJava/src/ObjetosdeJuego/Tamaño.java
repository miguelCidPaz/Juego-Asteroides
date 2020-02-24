package ObjetosdeJuego;

import java.awt.image.BufferedImage;

import Graficos.Assets;

public enum Tamaño {
	
	BIG(2, Assets.medianos), MED(2, Assets.pequeños), SMALL(2, Assets.enanos), TINY(0,null);
	
	public int cantidad;
	
	public BufferedImage[]texture;
	
	private Tamaño(int cantidad, BufferedImage[]texture) {
		this.cantidad = cantidad;
		this.texture = texture;
	}
	
}
