import java.util.ArrayList;


public class Lexicon {
	ArrayList<Word> list;
	
	public Lexicon() {
		list = new ArrayList<Word>();
	}

	public void insertWord(String word) {
		Word newWordObj = new Word(word);
		int lexiconSize = this.list.size();
		boolean hasWordInLexicon = false;

		// if lexiconSize is 0, adding the word directly
		if (lexiconSize == 0) {
			this.list.add(new Word(word));
			return;
		}

		for (int l = 0; l < lexiconSize; l++) {

			String curWord = this.list.get(l).getWord();
			if (curWord.equals(word)) {
				this.list.get(l).incrementFrequency();
				hasWordInLexicon = true;
			}
			boolean isNeighbour = Helper.areNeighbors(curWord, word);
			// if the both the words are neighbors,
			// then updating the neighbors list of both words
			if (isNeighbour) {
				this.list.get(l).addNeighbour(word);
				newWordObj.addNeighbour(curWord);
			}
			// inserting word to the lexicon in insertion sort style
			if (curWord.compareTo(word) > 0 && !hasWordInLexicon ) {
				this.list.add(l, newWordObj);
				hasWordInLexicon = true;
			}
		}
		if(!hasWordInLexicon) {
			this.list.add(newWordObj);
		}
	}
}
