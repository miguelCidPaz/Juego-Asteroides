package ObjetosdeJuego;

import java.awt.image.BufferedImage;

import Graficos.Assets;

public enum TiposPowerUps {
	
	ESCUDO("ESCUDO", Assets.escudo),
	VIDA ("+1 VIDA", Assets.vida),
	PUNTUACION_X2("PUNTUACION X2", Assets.doblepuntuacion),
	DISPARO_RAPIDO("DISPARO RAPIDO", Assets.disparoRapido),
	STACK_PUNTUACION("+1000 SCORE", Assets.estrella),
	DOBLE_LASER("DOBLE LASER", Assets.dobleDisparo);
	
	public String text;
	public BufferedImage texture;
	
	private TiposPowerUps(String text, BufferedImage texture) {
		this.text = text;
		this.texture = texture;
	}
}
