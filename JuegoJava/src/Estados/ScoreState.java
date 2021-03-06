package Estados;

import java.awt.Color;
import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

import Graficos.Assets;
import Graficos.Text;
import ObjetosdeJuego.Constantes;
import UI.Action;
import UI.Boton;
import io.JSONParser;
import io.ScoreData;
import maths.Vector2D;

public class ScoreState extends EstadoBase{
	
	private Boton returnBoton;
	private PriorityQueue<ScoreData>puntuacionesAltas;
	private Comparator<ScoreData>comparadorPuntuaciones;
	private ScoreData[]auxArray;
	
	public ScoreState() {
		returnBoton = new Boton(
				Assets.greyBtn,
				Assets.blueBtn,
				Assets.greyBtn.getHeight(),
				Constantes.HEIGHT - Assets.greyBtn.getHeight() * 2,
				"RETURN",
				new Action() {
					@Override
					public void doAction() {
						EstadoBase.changeState(new MenuState());
					}
				});
		
		comparadorPuntuaciones = new Comparator<ScoreData>() {
			@Override
			public int compare(ScoreData e1, ScoreData e2) {
				return e1.getPuntuacion() < e2.getPuntuacion() ? -1:e1.getPuntuacion() > e2.getPuntuacion() ? 1 : 0;
			}
		};
		
		puntuacionesAltas = new PriorityQueue<ScoreData>(10,comparadorPuntuaciones);
		
		try {
			ArrayList<ScoreData>dataList = JSONParser.readFile();
			
			for(ScoreData d: dataList) {
				puntuacionesAltas.add(d);
			}
			
			while(puntuacionesAltas.size() > 10) {
				puntuacionesAltas.poll();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		returnBoton.update();
		
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		returnBoton.draw(g);
		
		auxArray = puntuacionesAltas.toArray(new ScoreData[puntuacionesAltas.size()]);
		
		Arrays.sort(auxArray, comparadorPuntuaciones);
		
		Vector2D scorePos = new Vector2D(
				Constantes.WIDTH / 2 - 200,
				100);
		
		Vector2D datePos = new Vector2D(
				Constantes.WIDTH / 2 + 200,
				100);
		
		Text.drawText(g,"PUNTUACION", scorePos, true, Color.BLUE, Assets.fuentegrande);
		Text.drawText(g,"FECHA", datePos, true, Color.BLUE, Assets.fuentegrande);
		
		scorePos.setY(scorePos.getY()+40);
		datePos.setY(datePos.getY()+40);
		
		for(int i = auxArray.length - 1; i> -1; i--) {
			ScoreData d = auxArray[i];
			
			Text.drawText(g, Integer.toString(d.getPuntuacion()), scorePos, true, Color.WHITE, Assets.fuentemediana);
			Text.drawText(g, d.getDate(), datePos, true, Color.WHITE, Assets.fuentemediana);
			
			scorePos.setY(scorePos.getY()+40);
			datePos.setY(datePos.getY()+40);
		}
	}

}
