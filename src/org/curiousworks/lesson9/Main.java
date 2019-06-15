package org.curiousworks.lesson9;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		List<Character> characters = Character.readAllCharacters();
		
		System.out.println(Character.getCharacterByName(characters, "Daenerys Stormborn")
				.getAllMessagesString());
		
		for(Character c : characters) {
			
		JOptionPane.showMessageDialog(null, c.getCharacterName() + " - " + c.getMessageCount());
		}
		
		if(Character.getCharacterByName(characters, "Tyrion Lannister").isMoreHappyThanSad()) 
		  System.out.println("The Imp is more happy than sad.");
		else
			System.out.println("The Imp is more sad than happy.");
		
		System.out.println(Character.theMostIntenseDisposition(Disposition.Feeling.happy) + " has the most positive disposition.");
		System.out.println(Character.theMostIntenseDisposition(Disposition.Feeling.sad) + " has the most negative disposition.");
		
		if(Character.getCharacterByName(characters, "Daenerys Stormborn").compareTo(Character.getCharacterByName(characters, "Jon Snow")) == 0)
			System.out.println("Dany and Jon love eachother equaly.");
		else if(Character.getCharacterByName(characters, "Daenerys Stormborn").compareTo(Character.getCharacterByName(characters, "Jon Snow")) == 1)
			System.out.println("Dany loves Jon more than he loves her.");
		else
			System.out.println("Jon loves Dany more than she loves him.");
	
			
	
	}

}
