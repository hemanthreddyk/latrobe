package WordLink;

import java.io.IOException;
import java.util.Scanner;

public class WordLink {
	private static int difficultyLevel = 1;
	private static Scanner sc = new Scanner(System.in);
	private static Dictionary dict = new Dictionary();

	public static void main(String[] args) throws IOException {
		dict.initializeDictionary();
		while (true) {
			displayMenu();
		}
	}

	static void displayMenu() throws IOException {
		String input;
		System.out.println("\nWordLink\n\n" + "\tA set the difficulty level\n" + "\tB display the dictionary\n"
				+ "\tC insert a word to the dictionary\n" + "\tD play the game (1 player)\n" + "\tE exit\n"
				+ "\tF play the game (2 players)\n");
		System.out.print("Select a function from the menu: ");
		input = sc.nextLine();
		if (input.length() != 1)
			process('Z');
		else
			process(input.toUpperCase().charAt(0));
	}

	static void process(char choice) throws IOException {

		switch (choice) {
		case 'A':
			System.out.println("Set the difficulty level");
			System.out.print(
					"The current difficulty level is " + WordLink.difficultyLevel + ". Type the new level (1 or 2): ");
			char level = sc.nextLine().charAt(0);
			setDifficultyLevel(level);
			break;
		case 'B':
			dict.displayDictionary();
			break;
		case 'C':
			insertWordToDictionary();
			break;
		case 'D':
			playGame(1);
			break;
		case 'E':
			System.out.println("\nUpdating dictionary.txt ... Bye");
			dict.writeDictionaryToFile();
			System.exit(0);
		case 'F':
			playGame(2);
			break;
		default:
			System.out.println("\nInvalid input...");
		}
	}

	static void setDifficultyLevel(int difficultyLevel) {
		if (difficultyLevel == 49 || difficultyLevel == 50) {
			WordLink.difficultyLevel = difficultyLevel-48;
			System.out.println("The difficulty level has now been set as " +  WordLink.difficultyLevel + ".\n");
		} else {
			System.out.println("Invalid difficulty level entered");
		}
	}

	static void insertWordToDictionary() {
		System.out.println("Insert a word to the dictionary\n");
		System.out.print("Enter the word: ");
		String word = sc.nextLine().toLowerCase();
		System.out.print("Difficulty level: ");
		int level = sc.nextInt();
		sc.nextLine();

		if (level != 1 && level != 2) {
			System.out.println("\nInvalid difficulty level entered");
			return;
		}
		int resp = dict.insertWord(word, level);
		if (resp == 1) {
			System.out.println("\n\"" + word + "\" is inserted.");
		} else if (resp == 2) {
			System.out.println("\n\"" + word + "\" exists in the dictionary. Insertion aborted.");
		} else {
			System.out.println("\nInvalid word. Insertion aborted.");
		}
	}

	static void playGame(int numberOfPlayers) {
		int playerTurn = 1;
		String gameScenario = (numberOfPlayers == 1 ? "" : " - "), curWord = "";
		char startingLetter = 0;

		String greetings = (numberOfPlayers == 1 ? "\nPlay the game (Level " + difficultyLevel + ")\n"
				: "\nPlay the game (2 Players - Level " + difficultyLevel + ")\n");
		System.out.println(greetings);
		System.out.print(numberOfPlayers == 1 ? "Enter a word: " : "P1: Enter a word: ");

		while (true) {
			boolean is_valid_startLetter = true, is_word_already_used = false, is_word_in_dictionary = false;
			boolean isValid = true;

			if (numberOfPlayers == 1 && playerTurn == 2) { // CPU

				curWord = dict.getWord(startingLetter, gameScenario, difficultyLevel);
				if (curWord.equals("")) {
					System.out.println("Well done! You win.");
					return;
				}
				gameScenario += (curWord + " - ");
			} else { // human

				curWord = sc.nextLine().toLowerCase();
				if (curWord.equals("")) {
					System.out.println("Error!!! Blank input");
					return;
				}

				gameScenario += curWord.toLowerCase();

				if (isValid) {
					if (startingLetter != 0)
						is_valid_startLetter = (startingLetter == curWord.charAt(0));

					if (!is_valid_startLetter) {
						System.out.println("Starting letter doesn't match");
						isValid = false;
					}
				}

				if (isValid) {
					is_word_already_used = !(gameScenario.lastIndexOf(curWord) == gameScenario.indexOf(curWord));
					if (is_word_already_used) {
						System.out.print("\"" + curWord + "\" is already used.");
						isValid = false;
					}
				}

				if (isValid) {
					if (difficultyLevel == 2) {// dictionary search
						is_word_in_dictionary = dict.searchDict(curWord, difficultyLevel, false);
					} else {
						is_word_in_dictionary = dict.searchDict(curWord, difficultyLevel, true);
					}

					if (!is_word_in_dictionary) {
						if (difficultyLevel == 1)
							System.out.println("\"" + curWord + "\" isn't in the level 1 dictionary!");
						else
							System.out.print("\"" + curWord + "\" doesn't exist in the dictionary. ");
						isValid = false;
					}
				}

				if (isValid) {
					gameScenario += " - ";
				} else {
					if (numberOfPlayers == 2)
						System.out.println("\n--Player " + (playerTurn == 1 ? "two" : "one") + " wins!--");
					else
						System.out.println("You didn't win.");
					return;
				}
			}
			playerTurn = (playerTurn == 1 ? 2 : 1);
			startingLetter = curWord.charAt(curWord.length() - 1);
			if (numberOfPlayers == 1 && playerTurn == 1) {
				System.out.print(gameScenario);
			}
			if (numberOfPlayers == 2) {
				System.out.print("P" + (playerTurn == 1 ? "1:" : "2:") + gameScenario);
			}

		}
	}

}
