package Estados;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import Graficos.Assets;
import Graficos.Loader;
import Graficos.Text;
import ObjetosdeJuego.Constantes;
import maths.Vector2D;

public class LoadingState extends EstadoBase {
	
	private Thread loadingThread;
	private Font fuente;
	
	public LoadingState(Thread loadingThread) {
		this.loadingThread = loadingThread;
		this.loadingThread.start();
		fuente = Loader.loadFont("/fuentes/future.ttf", 38);
	}

	@Override
	public void update(float dt) {
		if(Assets.loaded) {
			EstadoBase.changeState(new MenuState());
			try {
				loadingThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void draw(Graphics g) {
		GradientPaint gp = new GradientPaint(
				Constantes.WIDTH / 2 - Constantes.LOADING_BAR_WIDTH / 2 ,
				Constantes.HEIGHT / 2 - Constantes.LOADING_BAR_HEIGHT / 2,
				Color.WHITE,
				Constantes.WIDTH / 2 + Constantes.LOADING_BAR_WIDTH / 2,
				Constantes.HEIGHT / 2 + Constantes.LOADING_BAR_HEIGHT / 2,
				Color.BLUE
				);
		
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.setPaint(gp);
		
		float porcentaje = (Assets.count / Assets.MAX_COUNT);
		
		g2d.fillRect(Constantes.WIDTH / 2 - Constantes.LOADING_BAR_WIDTH / 2,
				Constantes.HEIGHT / 2 - Constantes.LOADING_BAR_HEIGHT / 2, 
				(int) (Constantes.LOADING_BAR_WIDTH * porcentaje), Constantes.LOADING_BAR_HEIGHT);
		
		g2d.drawRect(Constantes.WIDTH / 2 - Constantes.LOADING_BAR_WIDTH / 2,
				Constantes.HEIGHT / 2 - Constantes.LOADING_BAR_HEIGHT / 2, 
				Constantes.LOADING_BAR_WIDTH, Constantes.LOADING_BAR_HEIGHT);
		
		Text.drawText(g2d, "Asteroides", new Vector2D(Constantes.WIDTH / 2, Constantes.HEIGHT / 2 - 50), true, Color.WHITE, fuente);
		
		Text.drawText(g2d, "", new Vector2D(Constantes.WIDTH / 2, Constantes.HEIGHT / 2 - 50), true, Color.WHITE, fuente);
	}

}
