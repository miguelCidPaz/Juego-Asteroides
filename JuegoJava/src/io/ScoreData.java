package io;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ScoreData {
	
	private String date;
	private int puntuacion;
	
	public ScoreData(int puntuacion) {
		this.puntuacion = puntuacion;
		
		Date today = new Date(System.currentTimeMillis());
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/YY");
		date = format.format(today);
		
	}
	
	public ScoreData() {
		
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate (String date) {
		this.date = date;
	}
	
	public int getPuntuacion() {
		return puntuacion;
	}
	
	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

}
