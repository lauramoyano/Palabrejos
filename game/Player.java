/*
 * Programación interactiva
 * Autores: Laura Moyano (2023906 - laura.moyano@correounivalle.edu.co)
	David Velasco (2024831 - velasco.david@correounivalle.edu.co)
	Laura Moyano (2023906 - laura.moyano@correounivalle.edu.co)
	David Velasco (2024831 - velasco.david@correounivalle.edu.co)
 * miniProyecto 3 : Palabrejos
 */
package game;

import java.io.Serializable;

public class Player implements Serializable{
	/**
	 * 
	 */
	private String name;
	private int hits;
	private int level;
	
	public Player(String name) {
		this.name = name;
		level = 1;
		
	}
	
	public void endPlayer(int hits, int level) {
		this.hits = hits;
		this.level = level;
	}

	public String getName() {
		return name;
	}
	
	public int getHits() {
		return hits;
	}

	public int getLevel() {
		return level;
	}
}
