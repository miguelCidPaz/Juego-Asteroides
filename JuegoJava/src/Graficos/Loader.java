package Graficos;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Loader {
    public static BufferedImage ImageLoader(String ruta){
        try {
            return ImageIO.read(Loader.class.getResource(ruta));
        } catch (IOException ex) {
            System.out.println("No hay imagen");
            ex.printStackTrace(System.out);
        }
        return null;
    }
    
    public static Font loadFont(String ruta, int tama�o) {
    	try {
    		return Font.createFont(Font.TRUETYPE_FONT, Loader.class.getResourceAsStream(ruta)).deriveFont(Font.PLAIN, tama�o);
    	}catch(FontFormatException | IOException e) {
    		e.printStackTrace();
    		return null;
    	}
    }
    
    public static Clip loadSound(String ruta) {
    	try {
    		Clip clip = AudioSystem.getClip();
    		clip.open(AudioSystem.getAudioInputStream(Loader.class.getResource(ruta)));
    		return clip;
    	}catch(LineUnavailableException | IOException | UnsupportedAudioFileException e) {
    		e.printStackTrace();
    	}
    	return null;
    }
}