package ObjetosdeJuego;

import java.awt.image.BufferedImage;

import Graficos.Assets;

public enum Tama�o {
	
	BIG(2, Assets.medianos), MED(2, Assets.peque�os), SMALL(2, Assets.enanos), TINY(0,null);
	
	public int cantidad;
	
	public BufferedImage[]texture;
	
	private Tama�o(int cantidad, BufferedImage[]texture) {
		this.cantidad = cantidad;
		this.texture = texture;
	}
	
}
