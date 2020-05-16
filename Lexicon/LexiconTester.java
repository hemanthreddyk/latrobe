import java.io.IOException;
import java.util.ArrayList;

public class LexiconTester {

	public static void main(String[] args) throws IOException {
		Lexicon lexicon = new Lexicon();
		ArrayList<String> al = new ArrayList<String>();
		
		//reading files and forming list of valid words
		Helper.readFile("sample1.txt", al);
		Helper.readFile("sample2.txt", al);

		//adding each word to the lexicon
		for (String word : al) {
			lexicon.insertWord(word);
		}

		Helper.writeLexiconToFile(lexicon, "sample3.txt");
	}
}
