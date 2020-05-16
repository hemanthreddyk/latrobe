package WordLink;

 class ListOfNodes {
	private DictionaryNode head = null;

	public DictionaryNode getHead() {
		return head;
	}

	public boolean insertInOrder(String word, int level) {
		if (search(word, level, false)) {
			return false;
		}
		DictionaryNode node = new DictionaryNode(word, level);
		if (head == null) {
			head = node;
		} else {
			DictionaryNode prev = null, temp = head;
			while (temp != null) {

				if (word.compareTo(temp.word) < 0) {
					if (prev == null) {
						node.setNext(head);
						head = node;
					} else {
						node.setNext(prev.getNext());
						prev.setNext(node);
					}
					return true;
				}

				prev = temp;
				temp = temp.getNext();
			}

			prev.setNext(node);
		}
		return true;

	}

	public boolean search(String word, int level, boolean useLevel) {
		DictionaryNode temp = head;
		while (temp != null) {
			if (useLevel && (temp.word.equals(word) && temp.getLevel() == level)) {
				return true;
			}
			if (!useLevel && temp.word.equals(word)) {
				return true;
			}
			temp = temp.getNext();
		}
		return false;
	}

	public void display() {
		DictionaryNode temp = head;
		System.out.println();
		while (temp != null) {
			System.out.print("(" + temp.word + "," + temp.getLevel() + ") ");
			temp = temp.getNext();
		}
		System.out.println("\n");
	}
}
