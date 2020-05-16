import java.util.ArrayList;

public class Word {
	private String word;
	private int freq;
	private ArrayList<String> neighbours;

	public Word(String word) {
		this.word = word;
		this.freq = 1;
		this.neighbours = new ArrayList<String>();
	}

	public String getWord() {
		return this.word;
	}
	
	public int getFrequency() {
		return this.freq;
	}
	
	public void incrementFrequency() {
		this.freq++;
	}

	// ignores the word if it is already in the list. Otherwise performs insertion
	// sort style of insertion, to the list
	public void addNeighbour(String word) {
		int size = this.neighbours.size();
		if (size == 0) {
			this.neighbours.add(word);
			return;
		}
		if (this.neighbours.contains(word))
			return;
		for (int l = 0; l < size; l++) {
			if (this.neighbours.get(l).compareTo(word) > 0) {
				this.neighbours.add(l, word);
				return;
			}
		}
		this.neighbours.add(word);
	}

	private String listNeighbours() {
		if (this.neighbours.size() == 0) {
			return "[]";
		}
		return this.neighbours.toString();
	}

	public String getWordDetails() {
		return this.getWord() + " " + this.getFrequency() + " " + this.listNeighbours();
	}
}
