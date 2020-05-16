package WordLink;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

 class Dictionary {
	private ListOfNodes[] data = new ListOfNodes[26];
	private Scanner sc = new Scanner(System.in);

	public int insertWord(String word, int level) {
		int idx = (int) word.charAt(0) - 97;
		if (idx < 0 || idx > 25)
			return 3;
		if (data[idx] == null) {
			data[idx] = new ListOfNodes();
		}
		if (data[idx].insertInOrder(word, level))
			return 1;
		else
			return 2;
	}

	public void displayDictionary() {
		int lineWordCount = 0, lineCount = 0;
		for (int level = 1; level <= 2; level++) {
			System.out.println("\nLevel " + level);
			for (int i = 0; i < 26; i++) {

				if (data[i] != null) {
					DictionaryNode temp = data[i].getHead();
					while (temp != null) {
						if (temp.getLevel() == level) {
							lineWordCount++;
							System.out.print(temp.word + " ");
							if (lineWordCount == 7) {
								lineWordCount = 0;
								lineCount++;
								System.out.println();
							}
							if (lineCount == 5) {
								lineCount = 0;
								read();
							}

						}
						temp = temp.getNext();
					}
				}
			}
			if (lineCount != 0) {
				if (lineWordCount != 0) {
					System.out.println();
				}
				lineCount = 0;
				lineWordCount = 0;

				read();
			}
		}
	}

	public void read() {
		System.out.println("\nPress any key to continue . . . ");
		sc.nextLine();
	}

	public void initializeDictionary() throws FileNotFoundException {
		int level = 1;
		Scanner f = new Scanner(new File("src/WordLink/dictionary.txt"));
		while (f.hasNextLine()) {
			String curLine = f.nextLine().trim();
			if (curLine.length() == 1) {
				level = Integer.parseInt(curLine);
			} else {
				String words[] = curLine.split(" ");
				for (String word : words) {
					this.insertWord(word, level);
				}
			}
		}
	}

	public void writeDictionaryToFile() throws IOException {
		FileWriter fw = new FileWriter("src/WordLink/dictionary.txt");
		for (int level = 1; level <= 2; level++) {
			fw.write(level + "\n");
			for (int i = 0; i < 26; i++) {
				if (this.data[i] != null) {
					DictionaryNode temp = this.data[i].getHead();
					while (temp != null) {
						if (temp.getLevel() == level) {
							fw.write(temp.word + " ");
						}
						temp = temp.getNext();
					}
				}
			}
			fw.write("\n");
		}
		fw.close();
	}

	public boolean searchDict(String word, int level, boolean useLevel) {
		int idx = (int) word.charAt(0) - 97;
		if (idx < 0 || idx > 25 || data[idx] == null)
			return false;

		return data[idx].search(word, level, useLevel);
	}

	public String getWord(char ch, String gameScenario, int level) {
		int idx = (int) ch - 97;
		if (idx < 0 || idx > 25 || data[idx] == null)
			return "";

		DictionaryNode temp = data[idx].getHead();
		while (temp != null) {
			if (level == 1 && level != temp.getLevel()) {
				temp = temp.getNext();
				continue;
			}
			boolean is_word_already_used = gameScenario.contains(temp.word);
			if (!is_word_already_used) {
				return temp.word;
			}
			temp = temp.getNext();
		}

		return "";
	}
}
