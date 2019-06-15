package org.curiousworks.lesson9;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Character implements Comparable<Character> {

	private String characterName;
	private String characterAllegiance;
	private String messagesPath;
	private List<Message> messages;


	public Character(String characterName, String characterAllegiance, String messagesPath) {

		this.characterName = characterName;
		this.characterAllegiance = characterAllegiance;
		this.messagesPath = messagesPath;
		this.messages = loadMessagesFromMetaData(messagesPath);
	}

	public static List<String> loadMessagesFile(String filePath) {
		List<String> lines = FileHelper.loadMessages(filePath);
		lines.remove(0);
		return lines;
	}

	public static Character getCharacterByName(List<Character> characters, String name) {
		for (Character c : characters) {
			if (c.getCharacterName().equals(name))
				return c;
		}
		return null;
	}

	public String getAllMessagesString() {
		String result = "";

		for (Message message : messages) {
			result += message.getReceiver() + " - " + message.getMessage() + "\n";
		}
		return result;
	}

	public int getMessageCount() {
		return messages.size();
	}

	public static List<Character> readAllCharacters() {
		List<Character> allCharacters = new ArrayList<Character>();
		String data = FileHelper.loadMetaData();
		List<String> lines = Arrays.asList(data.split(System.getProperty("line.separator")));
		for (int i = 1; i < lines.size(); i++) {
			String line = lines.get(i);
			String[] charData = line.split(", ");
			Character character = new Character(charData[0].trim(), charData[1].trim(), charData[2].trim());
			allCharacters.add(character);
		}
		return allCharacters;
	}

	public static List<Message> loadMessagesFromMetaData(String messagesPath) {
		List<Message> result = new ArrayList<Message>();

		List<String> lines = loadMessagesFile(messagesPath);

		for (String line : lines) {
			String[] messageLine = line.split(", ",2);
			if (messageLine.length > 1) {
				Message message = new Message(messageLine[0], messageLine[1]);
				result.add(message);
			}
		}
		return result;
	}

	@Override
	public String toString() {
		return getCharacterName() + " - " + getCharacterAllegiance();
	}

	public static List<String> readDataForCharacterFromFile(String name) throws FileNotFoundException, IOException {
		String[] dataForCharacter = new String[3];
		String data = FileHelper.loadMetaData();
		String[] lines = data.split(System.getProperty("line.separator"));
		for (int i = 0; i < lines.length; i++) {
			String[] helpArray = lines[i].split(", ");
			if (helpArray[0].equals(name)) {
				dataForCharacter = helpArray;
			}
		}
		return Arrays.asList(dataForCharacter);
	}

	public static String[] getEmojiFromFeeling(Disposition.Feeling feeling) {
		String[] emojiArray = {};

		if (feeling.equals(Disposition.Feeling.happy)) {
			emojiArray = Disposition.happyEmoji;
		}
		if (feeling.equals(Disposition.Feeling.sad)) {
			emojiArray = Disposition.sadEmoji;
		}
		if (feeling.equals(Disposition.Feeling.love)) {
			emojiArray = Disposition.loveEmoji;
		}
		return emojiArray;
	}

	public boolean isMoreHappyThanSad() {
		int happiness = measureDisposition(Disposition.Feeling.happy);
		int sadness = measureDisposition(Disposition.Feeling.sad);
		return happiness > sadness;
	}

	public int measureDisposition(Disposition.Feeling feeling) {
		int counter = 0;

		String[] emojiArray = getEmojiFromFeeling(feeling);

		for (Message message : messages) {
			for (String emoji : emojiArray) {
				if (message.getMessage().contains(emoji)) {
					counter++;
				}
			}
		}
		return counter;
	}


	public static Character theMostIntenseDisposition(Disposition.Feeling disposition)
			throws FileNotFoundException, IOException {
		if (disposition.equals(Disposition.Feeling.love))
			return null;
		List<Character> allCharacters = readAllCharacters();
		Character characterDisposition = allCharacters.get(0);
		int disp = allCharacters.get(0).measureDisposition(disposition);
		for (int i = 1; i < allCharacters.size(); i++) {
			if (disp < allCharacters.get(i).measureDisposition(disposition)) {
				disp = allCharacters.get(i).measureDisposition(disposition);
				characterDisposition = allCharacters.get(i);
			}
		}
		return characterDisposition;
	}

	@Override
	public int compareTo(Character o) {
		int i;
		if (this.measureDisposition(Disposition.Feeling.love) == o.measureDisposition(Disposition.Feeling.love))
			i = 0;
		else if (this.measureDisposition(Disposition.Feeling.love) > o.measureDisposition(Disposition.Feeling.love))
			i = 1;
		else
			i = -1;
		return i;
	}

	public String getCharacterName() {
		return characterName;
	}

	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}

	public String getCharacterAllegiance() {
		return characterAllegiance;
	}

	public void setCharacterAllegiance(String characterAllegiance) {
		this.characterAllegiance = characterAllegiance;
	}


	public String getMessagesPath() {
		return messagesPath;
	}

	public void setMessagesPath(String messagesPath) {
		this.messagesPath = messagesPath;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
}
