package Estados;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import Graficos.Animacion;
import Graficos.Assets;
import Graficos.Sonido;
import ObjetosdeJuego.Constantes;
import ObjetosdeJuego.Mensajes;
import ObjetosdeJuego.Meteoro;
import ObjetosdeJuego.ObjetosMoviles;
import ObjetosdeJuego.Player;
import ObjetosdeJuego.PowerUp;
import ObjetosdeJuego.Tama�o;
import ObjetosdeJuego.TiposPowerUps;
import ObjetosdeJuego.Ufo;
import UI.Action;
import io.JSONParser;
import io.ScoreData;
import maths.Vector2D;

public class GameState extends EstadoBase {
	public static final Vector2D PLAYER_POSITION = new Vector2D(Constantes.WIDTH/2-Assets.player.getWidth()/2,
			Constantes.HEIGHT/2-Assets.player.getHeight()/2);
	
	private Player player;
	private ArrayList <ObjetosMoviles> objetosmoviles = new ArrayList <ObjetosMoviles>();
	private ArrayList <Animacion> explosion = new ArrayList<Animacion>();
	private ArrayList <Mensajes>mensaje = new ArrayList<Mensajes>();
	
	private int vidas = 3;
	private int score = 0;
	private int meteoros;
	private int Oleada = 1;
	
	private Sonido musicadefondo;
	private long ufoSpawn, powerUpSpawn;
	private long gameovertime;
	private boolean gameover;
	
	public GameState() {
		player = new Player(PLAYER_POSITION, new Vector2D(),Constantes.PLAYER_MAX_VEL, Assets.player,this); 
		objetosmoviles.add(player);
		meteoros = 1;
		iniciarOleada();
		
		musicadefondo = new Sonido(Assets.backgroundMusic);
		musicadefondo.loop();
		musicadefondo.cambiarVolumen(-10.0f);
		
		powerUpSpawn = 0;
		ufoSpawn = 0;
		gameovertime = 0;
		
		gameover = false;
	}
	
	public void addScore(int value, Vector2D posicion) {
		Color c = Color.WHITE;
		String text = "+"+value+" puntuacion";
		if(player.doblePuntaje()) {
			c = Color.YELLOW;
			value = value *2;
			text = "+"+value+" puntuacionX2!";
		}
		
		score += value;
		mensaje.add(new Mensajes(posicion, true, text, c, false, Assets.fuentemediana));
		
	}
	
	public void dividirMeteoros(Meteoro meteorito) {
		
		Tama�o tama�o = meteorito.getTama�o();
		BufferedImage[]textures = tama�o.texture;
		Tama�o nuevotama�o = null;
		
		switch(tama�o) {
		case BIG:
			nuevotama�o = Tama�o.MED;
			break;
		case MED:
			nuevotama�o = Tama�o.SMALL;
			break;
		case SMALL:
			nuevotama�o = Tama�o.TINY;
			break;
		default:
			return;
		}
		
		for( int i = 0 ; i < tama�o.cantidad ; i++) {
			objetosmoviles.add(new Meteoro(
					meteorito.getPosition(),
					new Vector2D(0,1).setDireccion(Math.random()*Math.PI*2),
					Constantes.METEORO_INIT_VEL*Math.random() + 1,
					textures[(int)(Math.random()*textures.length)],
					this,
					nuevotama�o
					));
		}
		
	}
	
	private void iniciarOleada() {
		double x,y;
		
		mensaje.add(new Mensajes(new Vector2D(Constantes.WIDTH/2, Constantes.HEIGHT/2), true, "Oleada "+Oleada, Color.WHITE, true, Assets.fuentegrande));
		
		for(int i = 0 ; i < meteoros ; i++ ) {
			
			x = i % 2 == 0 ? Math.random()*Constantes.WIDTH : 0;
			y = i % 2 == 0 ? 0 : Math.random()*Constantes.HEIGHT;
			
			BufferedImage texture = Assets.grandes[(int)(Math.random()*Assets.grandes.length)];
			
			objetosmoviles.add(new Meteoro(
					new Vector2D(x,y),
					new Vector2D(0,1).setDireccion(Math.random()*Math.PI*2),
					Constantes.METEORO_INIT_VEL*Math.random() +1,
					texture,
					this,
					Tama�o.BIG
					));
			
		}
		meteoros++;
		Oleada++;
	}
	
	public void playExplosion(Vector2D position) {
		explosion.add(new Animacion(
				Assets.explosion,
				50,
				position.sustraer(new Vector2D(Assets.explosion[0].getWidth()/2, Assets.explosion[0].getHeight()/2))
				));
	}
	
	private void spawnUfo() {
		
		int rand = (int)(Math.random()*2);
		
		double x = rand == 0 ? (Math.random()*Constantes.WIDTH): Constantes.WIDTH;
		double y = rand == 0 ? Constantes.HEIGHT : (Math.random()*Constantes.HEIGHT);
		
		ArrayList<Vector2D>ruta = new ArrayList<Vector2D>();
		
		double posx, posy;
		
		posx = Math.random()*Constantes.WIDTH/2;
		posy = Math.random()*Constantes.HEIGHT/2;
		ruta.add(new Vector2D(posx, posy));
		
		posx = Math.random()*(Constantes.WIDTH/2)+Constantes.WIDTH/2;
		posy = Math.random()*Constantes.HEIGHT/2;
		ruta.add(new Vector2D(posy, posx));
		
		posx = Math.random()*Constantes.WIDTH/2;
		posy = Math.random()*(Constantes.HEIGHT/2)+Constantes.HEIGHT/2;
		ruta.add(new Vector2D(posx, posy));
		
		posx = Math.random()*(Constantes.WIDTH/2)+Constantes.WIDTH/2;
		posy = Math.random()*(Constantes.HEIGHT/2)+Constantes.HEIGHT/2;
		ruta.add(new Vector2D(posx, posy));
		
		objetosmoviles.add(new Ufo(
				new Vector2D(x, y),
				new Vector2D(),
				Constantes.UFO_MAX_VEL,
				Assets.UFO,
				ruta,
				this
				));
	}
	
	private void spawnPowerUps() {
		
		final int x = (int)((Constantes.WIDTH - Assets.orb.getWidth())*Math.random());
		final int y = (int)((Constantes.HEIGHT - Assets.orb.getHeight())*Math.random());
		
		int index = (int)(Math.random()*(TiposPowerUps.values().length));
		
		TiposPowerUps p = TiposPowerUps.values()[index];
		
		final String text = p.text;
		Action action = null;
		Vector2D position = new Vector2D(x, y);
		
		switch(p) {
		
		case VIDA:
			action = new Action() {
				@Override
				public void doAction() {
					vidas++;
					mensaje.add(new Mensajes(
							position,
							false,
							text,
							Color.GREEN,
							false,
							Assets.fuentemediana));
				}
			};
			break;
		case ESCUDO:
			action = new Action() {
				@Override
				public void doAction() {
					player.setEscudo();
					mensaje.add(new Mensajes(
							position,
							false,
							text,
							Color.DARK_GRAY,
							false,
							Assets.fuentemediana));
				}
			};
			break;
		case DISPARO_RAPIDO:
			action = new Action() {
				@Override
				public void doAction() {
					player.setDisparorapido();
					mensaje.add(new Mensajes(
							position,
							false,
							text,
							Color.BLUE,
							false,
							Assets.fuentemediana));
				}
			};
			break;
		case STACK_PUNTUACION:
			action = new Action() {
				@Override
				public void doAction() {
					score += 1000;
					mensaje.add(new Mensajes(
							position,
							false,
							text,
							Color.MAGENTA,
							false,
							Assets.fuentemediana));
				}
			};
			break;
		case DOBLE_LASER:
			action = new Action() {
				@Override
				public void doAction() {
					player.setDobleDisparo();
					mensaje.add(new Mensajes(
							position,
							false,
							text,
							Color.ORANGE,
							false,
							Assets.fuentemediana));
				}
			};
			break;
		default:
			break;
		}
		
		this.objetosmoviles.add(new PowerUp(
				position,
				p.texture,
				action,
				this));
		
	}
	
	public void update(float dt) {
		
		if(gameover) {
			gameovertime +=dt;
		}
		
		powerUpSpawn += dt;
		ufoSpawn += dt;
		
		for(int i = 0 ; i < objetosmoviles.size() ; i++) {
			ObjetosMoviles mo = objetosmoviles.get(i);
			mo.update(dt);
			if(mo.estaDestruido()) {
				objetosmoviles.remove(i);
				i--;
			}
		}
		
		for(int i = 0 ; i< explosion.size() ; i++) {
			Animacion anim = explosion.get(i);
			anim.update(dt);
			if(!anim.estacorriendo()) {
				explosion.remove(i);
			}
		}
		
		if(gameovertime > Constantes.GAME_OVER) {
			try {
				ArrayList<ScoreData>dataList = JSONParser.readFile();
				dataList.add(new ScoreData(score));
				JSONParser.WriteFile(dataList);
			}catch(IOException e){
				e.printStackTrace();
			}
			EstadoBase.changeState(new MenuState());
		}
		
		if(powerUpSpawn > Constantes.POWERUP_SPAWNTIME) {
			spawnPowerUps();
			powerUpSpawn = 0;
		}
		
		if(ufoSpawn > Constantes.UFO_SPAWNRATE) {
			spawnUfo();
			ufoSpawn = 0;
		}
		
		for(int i = 0 ; i< objetosmoviles.size() ; i++) {
			if(objetosmoviles.get(i) instanceof Meteoro)
				return;
		}
		
		iniciarOleada();
	}
	
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		
		for (int i  = 0 ; i < mensaje.size() ; i++) {
			mensaje.get(i).draw(g2d);
			if(mensaje.get(i).estadestruido()) {
				mensaje.remove(i);
			}
		}
		
		for(int i = 0 ; i < objetosmoviles.size() ; i++) {
			objetosmoviles.get(i).draw(g);
		}
		
		for(int i = 0 ; i < explosion.size() ; i++) {
			Animacion anim = explosion.get(i);
			g2d.drawImage(anim.getCurrentFrame(), (int)anim.getPosition().getX(), (int)anim.getPosition().getY(), null);
		}
		drawScore(g);
		drawLive(g);
		
	}
	
	private void drawScore(Graphics g) {
		Vector2D pos = new Vector2D(850, 25);
		String scoreToString = Integer.toString(score);
		for(int i = 0 ; i < scoreToString.length() ; i++) {
			g.drawImage(Assets.numeros[Integer.parseInt(scoreToString.substring(i,i+1))],
					(int)pos.getX(), (int)pos.getY(), null);
			pos.setX(pos.getX()+20);
		}
	}
	
	private void drawLive(Graphics g) {
		
		if(vidas < 1) {
			return;
		}
		
		Vector2D posicionvida = new Vector2D(25, 25);
		g.drawImage(Assets.vida, (int)posicionvida.getX(),(int)posicionvida.getY(), null);
		g.drawImage(Assets.numeros[10],(int)posicionvida.getX()+40, (int)posicionvida.getY()+5, null);
		String vidaToString = Integer.toString(vidas);
		Vector2D pos = new Vector2D(posicionvida.getX(), posicionvida.getY());
		for(int i = 0 ; i < vidaToString.length() ; i++) {
			int numero = Integer.parseInt(vidaToString.substring(i,i+1));
			if(numero <= 0) {
				break;
			}
			g.drawImage(Assets.numeros[numero], (int)pos.getX()+60, (int)pos.getY()+5, null);
			pos.setX(pos.getX()+20);
		
		}
	}

	public ArrayList<ObjetosMoviles> getObjetosmoviles() {
		return objetosmoviles;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public boolean restarvida(Vector2D position) {
		vidas -- ;
		
		Mensajes vidaPerdida = new Mensajes(
				position,
				false,
				"-1 VIDA",
				Color.RED,
				false,
				Assets.fuentemediana);
		mensaje.add(vidaPerdida);
		return vidas > 0;
		
		}
	
	public ArrayList <Mensajes> getMensaje(){
		return mensaje;
	}
	
	public void gameOver() {
		Mensajes gameOvermsg = new Mensajes(
				
				PLAYER_POSITION,
				true,
				"GAME OVER",
				Color.WHITE,
				true,
				Assets.fuentegrande);
		
		this.mensaje.add(gameOvermsg);
		gameover = true;
	}
		
}