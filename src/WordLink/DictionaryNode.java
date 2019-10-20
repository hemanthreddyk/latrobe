package WordLink;

 class DictionaryNode {
	protected String word;
	private int level;
	private DictionaryNode next;

	public DictionaryNode(String _word, int _level) {
		this.level = _level;
		this.word = _word;
		this.next = null;
	}
	
	public int getLevel() {
		return this.level;
	}
	
	public DictionaryNode getNext() {
		return this.next;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void setNext(DictionaryNode next) {
		this.next = next;
	}
}
