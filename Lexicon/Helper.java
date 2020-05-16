import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Helper {
	public static boolean areNeighbors(String word1, String word2) {
		if (word1.length() != word2.length())
			return false;
		int diff = 0;
		for (int i = 0; i < word1.length(); i++) {
			if (word1.charAt(i) != word2.charAt(i)) {
				diff++;
			}
		}

		if (diff != 1)
			return false;

		return true;
	}

	public static void readFile(String filePath, ArrayList<String> list) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(filePath));

		while (sc.hasNextLine()) {
			String curLine = sc.nextLine().trim();
			String words[] = curLine.split(" ");
			for (String word : words) {
				String transformedWord = word.replaceAll("[!-+.^:,?\\'\\\"\\d\\s]", "").toLowerCase();
				if (transformedWord.length() == 0)
					continue;
				list.add(transformedWord);
			}
		}
	}

	public static void writeLexiconToFile(Lexicon lexicon, String filePath) throws IOException {
		FileWriter fw = new FileWriter(filePath);
		for (Word w : lexicon.list) {
			fw.write(w.getWordDetails() + "\n");
		}
		fw.close();
	}
	
	public static void writeResultToFile(String result, String filePath) throws IOException {
		FileWriter fw = new FileWriter(filePath);
		fw.write(result);
		fw.close();
	}

	public static String getMatchingWords(Lexicon lexicon, String pattern) {
		String transformedPattern = "^" + pattern.concat("$").replace("?", "[a-z]").replace("*", "[a-z]*");
		String result = "";
		for (Word word : lexicon.list) {
			if (word.getWord().matches(transformedPattern)) {
				result += word.getWord() + " " + word.getFrequency() + "\n";
			}
		}
		if (result.length() == 0) {
			result += "No words in the lexicon match the pattern";
		}
		return result;
	}
}
