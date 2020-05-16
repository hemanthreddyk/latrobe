import java.io.IOException;
import java.util.ArrayList;

public class WordMatchTester {

	public static void main(String[] args) throws IOException {
		Lexicon lexicon = new Lexicon();
		ArrayList<String> al = new ArrayList<String>();
		String pattern = args[0].toLowerCase();
		// reading files and forming list of valid words
		Helper.readFile("sample1.txt", al);
		Helper.readFile("sample2.txt", al);

		//adding each word to the lexicon
		for (String word : al) {
			lexicon.insertWord(word);
		}

		Helper.writeLexiconToFile(lexicon, "sample3.txt");

		String matchingWords = Helper.getMatchingWords(lexicon, pattern);
		String result = "Pattern: " + pattern + "\n\n" + matchingWords;
		System.out.println(result);
		Helper.writeResultToFile(result, "result.txt");
	}

}
