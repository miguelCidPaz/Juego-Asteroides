package Graficos;

import java.awt.Font;
import java.awt.image.BufferedImage;

import javax.sound.sampled.Clip;

/**
 *
 * @author migue
 */
public class Assets {
	public static boolean loaded = false;
	public static float count = 0;
	public static float MAX_COUNT = 46;
	
    //Naves
    public static BufferedImage player;
    public static BufferedImage UFO;
    public static BufferedImage doblepistolaplayer;
    
    //Efectos
    public static BufferedImage propulsion;
    public static BufferedImage[]explosion = new BufferedImage[9];
    
    //Lasers
    public static BufferedImage laserazul;
    public static BufferedImage laserrojo;
    public static BufferedImage laserverde;
    
    //Meteoros
    public static BufferedImage[]grandes = new BufferedImage[4];
    public static BufferedImage[]medianos = new BufferedImage[2];
    public static BufferedImage[]peque�os = new BufferedImage[2];
    public static BufferedImage[]enanos = new BufferedImage[2];
    
    //otros
    public static BufferedImage[]numeros = new BufferedImage[11];
    public static BufferedImage vida;
    public static Font fuentegrande;
    public static Font fuentemediana;
    
    //ui
    public static BufferedImage blueBtn;
    public static BufferedImage greyBtn;
    
    //sonidos
    public static Clip backgroundMusic, sonidoexpl, playerLoose, playerShoot, ufoShoot,powerup;
    
    //powerUps
    public static BufferedImage orb, doblepuntuacion, dobleDisparo, disparoRapido ,estrella, escudo;
	public static BufferedImage[] efectoEscudo = new BufferedImage[3];
    
    public static void init(){
    	
    	//naves
    	player = loadImage("/naves/player.png");
    	doblepistolaplayer = loadImage("/naves/doubleGunPlayer.png");
    	UFO = loadImage("/naves/ufo.png");
    	
    	//powerups
    	orb = loadImage("/powers/orb.png");
    	doblepuntuacion = loadImage("/powers/doubleScore.png");
    	dobleDisparo = loadImage("/powers/doubleGun.png");
    	disparoRapido = loadImage("/powers/fastFire.png");
    	escudo = loadImage("/powers/shield.png");
    	estrella = loadImage("/powers/star.png");
    	for(int i = 0 ; i< 3 ; i++) {
    		efectoEscudo[i]= loadImage("/efectos/shield"+(i+1)+".png");
    	}
        
        //Propulsion
    	propulsion = loadImage("/efectos/fire08.png");
        
        //Laser
    	laserazul = loadImage("/lasers/laserAzul.png");
    	laserrojo = loadImage("/lasers/laserRojo.png");
    	laserverde = loadImage("/lasers/laserVerde.png");
        
        //Vida
    	vida = loadImage("/otros/life.png");
        
        //fuente
    	fuentegrande = loadFont("/fuentes/future.ttf", 42);
    	fuentemediana = loadFont("/fuentes/future.ttf", 20);
        
        //sonidos
        backgroundMusic = loadSound("/sonidos/backgroundMusic.wav");
        sonidoexpl =loadSound("/sonidos/explosion.wav");
        playerLoose = loadSound("/sonidos/playerLoose.wav");
        playerShoot =loadSound("/sonidos/playerShoot.wav");
        ufoShoot = loadSound("/sonidos/ufoShoot.wav");
        powerup = loadSound("/sonidos/powerUp.wav");
        
        
        //ui
        greyBtn = loadImage("/UI/botonGris.png");
        blueBtn = loadImage("/UI/botonAzul.png");
        
        //Meteoros
        for(int i = 0 ; i < grandes.length ; i++ ) {
        	grandes[i] = loadImage("/meteoros/Grande"+(i+1)+".png");
        }
        for(int i = 0 ; i < medianos.length ; i++ ) {
        	medianos[i] = loadImage("/meteoros/Mediano"+(i+1)+".png");
        }
        for(int i = 0 ; i < peque�os.length ; i++ ) {
        	peque�os[i] = loadImage("/meteoros/Peque�o"+(i+1)+".png");
        }
        for(int i = 0 ; i < enanos.length ; i++ ) {
        	enanos[i] = loadImage("/meteoros/Enano"+(i+1)+".png");
        }
        
        //Explosion
        for(int i = 0 ; i <explosion.length ; i++) {
        	explosion[i] =loadImage("/efectos/"+(i)+".png");
        }
        
        //Numeros
        for(int i = 0 ; i < numeros.length ; i++) {
        	numeros[i] = loadImage("/numeros/"+(i)+".png");
        }
        
       loaded = true; 
       
    }
    
    
    
    public static BufferedImage loadImage(String ruta) {
    	count ++;
    	return Loader.ImageLoader(ruta);
    }
    
    public static Font loadFont(String ruta, int tama�o) {
    	count++;
    	return Loader.loadFont(ruta, tama�o);
    }
    
    public static Clip loadSound(String ruta) {
    	count ++;
    	return Loader.loadSound(ruta);
    }
    
    
    
}