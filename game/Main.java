/*
 * Programación interactiva
 * Autores: Laura Moyano (2023906 - laura.moyano@correounivalle.edu.co)
	David Velasco (2024831 - velasco.david@correounivalle.edu.co)
	Laura Moyano (2023906 - laura.moyano@correounivalle.edu.co)
	David Velasco (2024831 - velasco.david@correounivalle.edu.co)
 * miniProyecto 3 : Palabrejos
 */
package game;

import java.awt.EventQueue;

public class Main {
	public static void main(String[] args) {
		 //GUI logica = new GUI();
		 EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				GUI logica = new GUI();
			}});
		 
		
	}
}
