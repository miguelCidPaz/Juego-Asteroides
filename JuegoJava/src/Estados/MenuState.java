package Estados;

import java.awt.Graphics;
import java.util.ArrayList;

import Graficos.Assets;
import ObjetosdeJuego.Constantes;
import UI.Action;
import UI.Boton;

public class MenuState extends EstadoBase	 {
	
	private ArrayList<Boton>botones;
	
	public MenuState() {
		botones = new ArrayList<Boton>();
		
		botones.add(new Boton(
				Assets.greyBtn,
				Assets.blueBtn,
				Constantes.WIDTH/2-Assets.greyBtn.getWidth()/2,
				Constantes.HEIGHT/2-Assets.greyBtn.getHeight()*2,
				"PLAY",
				new Action() {
					@Override
					public void doAction() {
						EstadoBase.changeState(new GameState());
					}
				}
				
				));
		
		botones.add(new Boton(
				Assets.greyBtn,
				Assets.blueBtn,
				Constantes.WIDTH/2-Assets.greyBtn.getWidth()/2,
				Constantes.HEIGHT/2+Assets.greyBtn.getHeight()*2,
				"EXIT",
				new Action() {
					@Override
					public void doAction() {
						System.exit(0);
					}
				}
				
				));
		
		botones.add(new Boton(
				Assets.greyBtn,
				Assets.blueBtn,
				Constantes.WIDTH/2-Assets.greyBtn.getWidth()/2,
				Constantes.HEIGHT/2,
				"PUNTUACIONES",
				new Action() {
					@Override
					public void doAction() {
						EstadoBase.changeState(new ScoreState());
					}
				}));
		
	}
	
	@Override
	public void update(float dt) {
		for(Boton b: botones) {
			b.update();
		}
		
	}

	@Override
	public void draw(Graphics g) {
		for(Boton b:botones) {
			b.draw(g);
		}
		
	}

}
