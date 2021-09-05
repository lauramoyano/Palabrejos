/*
 * Programación interactiva
 * Autores: Laura Moyano (2023906 - laura.moyano@correounivalle.edu.co)
	David Velasco (2024831 - velasco.david@correounivalle.edu.co)
	Laura Moyano (2023906 - laura.moyano@correounivalle.edu.co)
	David Velasco (2024831 - velasco.david@correounivalle.edu.co)
 * miniProyecto 3 : Palabrejos
 */
package game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Logic {
	File words = new File("src/game/words.txt"); 
	FileReader reader;
	BufferedReader bufferedReader;
	ArrayList<String> dictionary = new ArrayList<String>();
	ArrayList<String> dictionaryRound = new ArrayList<String>();
	ArrayList<String> dictionaryRound2 = new ArrayList<String>();
	int round;
	int countHits;
	String inputWord;
	
	public Logic(){
		this.countHits=0;
		this.round =1;
		this.txtToarray();
		this.addToArrayRound();
		this.copyArrayRound();
	}
	

	public ArrayList<String> getDictionaryRound2() {
		return dictionaryRound2;
	}


	public ArrayList<String> getDictionaryRound() {
		return dictionaryRound;
	}

	public void setInputWord(String inputWord) {
		this.inputWord = inputWord;
	}

	public void txtToarray() {
		try {
			this.reader = new FileReader(words);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		this.bufferedReader = new BufferedReader(reader);
		String line;
		try {
			while((line = bufferedReader.readLine()) != null) {
				dictionary.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	} 
	
	public String getInputWord() {
		return inputWord;
	}

	public void addToArrayRound() {
		Collections.shuffle(dictionary);

		if(round==1) {
			for(int i = 0 ; i < 8; i++) {	
				dictionaryRound.add(dictionary.get(i));
			}
		}
		
		if(round==2) {
			for(int i = 0 ; i < 12; i++) {
				dictionaryRound.add(dictionary.get(i));
			}
			
		}

		if(round==3) {
			for(int i = 0 ; i < 16; i++) {
				dictionaryRound.add(dictionary.get(i));
			}
		}
		
		if(round==4) {
			for(int i = 0 ; i < 20; i++) {
				dictionaryRound.add(dictionary.get(i));
			}
		}
		
		if(round==5) {
			for(int i = 0 ; i < 24; i++) {
				dictionaryRound.add(dictionary.get(i));
			}
		}
	}
	
	public void compareWords() {

		for(int i=0; i< dictionaryRound2.size(); i++) {
			
			if(dictionaryRound2.get(i).equalsIgnoreCase(inputWord)) {
				this.countHits++;
				this.inputWord= "";
				dictionaryRound2.remove(i);		
			}
		}
		
		this.inputWord = "";
		
	}
	
	public void copyArrayRound() {
		
		for(int i = 0; i < dictionaryRound.size(); i++) {
			String word = dictionaryRound.get(i);
			dictionaryRound2.add(word);
		}
		
	}
	
	public int getRound() {
		return round;
	}

	public int getCountHits() {
		return countHits;
	}
	
	//Si un jugador ya existe entonces debe establecerse la ronda en la que quedó
	public void setRound(int round) {
		this.round = round;
	}


	public boolean changeRound() {
		
		if(this.round == 1 && this.countHits >= 7) {
			
			clearArrayRound();		
			return true;
		}
		if(this.round == 2 && this.countHits >= 9) {
			
			clearArrayRound();
			return true;
		}		
		if(this.round == 3 && this.countHits >= 12) {
			
			clearArrayRound();
			return true;
		}
		if(this.round == 4 && this.countHits >= 15) {
			
			clearArrayRound();	
			return true;
		}
		if(this.round == 5 && this.countHits >= 18) {
			
			this.countHits = 0;
			this.copyArrayRound();
			return true;
		}
		else {
			
			return false;
		}
		
	} 
	
	
	public void clearArrayRound() {
		this.round++;
		this.countHits = 0;
		this.dictionaryRound.clear();
		this.dictionaryRound2.clear();
		this.addToArrayRound();
		this.copyArrayRound();
		
		
	}
	
}

