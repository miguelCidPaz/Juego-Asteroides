package ObjetosdeJuego;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Constantes {
	
	//General properties
	public static final long GAME_OVER = 10000/2;
	public static final int LOADING_BAR_WIDTH = 500;
	public static final int LOADING_BAR_HEIGHT = 50;
	public static final String PUNTUACION = "PUNTUACION";
	public static final String MAYORES_PUNTUACIONES = "MAYORES PUNTUACIONES";
	
	//Frame properties
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 600;
	
	//Player properties
	public static final int FIRERATE = 200;
	public static final double DELTAANGLE = 0.1;
	public static final double ACC = 0.2;
	public static final double PLAYER_MAX_VEL = 7.0;
	public static final long TIEMPO_PARPADEO = 200;
	public static final long TIEMPO_REAPARICION = 3000;
	
	//Laser properties
	public static final double LASER_VEL = 15.0;
	
	//Meteoro properties
	public static final double METEORO_INIT_VEL = 2.0;
	public static final int METEORO_SCORE = 20;
	public static final double METEORO_MAX_VEL = 6.0;
	public static final int SHIELD_DISTANCE = 150;
	
	//Ufo properties
	public static final int NODE_RADIUS = 160;
	public static final double UFO_MASS = 60;
	public static final int UFO_MAX_VEL = 3;
	public static long UFO_FIRE_RATE = 1000;
	public static double UFO_ANGLE_RANGE = Math.PI/2;
	public static final int UFO_SCORE = 40;
	public static final long UFO_SPAWNRATE = 10000;
	
	//Rutas
	public static final Path relativo = Paths.get("puntuacionAsteroides.json");
	public static final Path absoluto = relativo.toAbsolutePath();
	public static final String rutaArchivo = String.valueOf(absoluto);
	
	//PowerUps
	public static final long POWERUP_DURATION = 10000;
	public static final long POWERUP_SPAWNTIME = 8000;
	public static final long DURACION_ESCUDO = 12000;
	public static final long PUNTUACION_DOBLE_TIEMPO = 10000;
	public static final long DISPARORAPIDO_TIEMPO =14000;
	public static final long DISPARO_DOBLE_TIEMPO = 12000;
	public static final int SCORE_STACK = 1000;
	
}
