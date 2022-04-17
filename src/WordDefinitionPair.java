public class WordDefinitionPair {
	private String word;
	private String definition;

	WordDefinitionPair(String word, String def) {
		this.word = word;
		this.definition = def;
	}

	String getWord() {
		return word;
	}

	String getDefinition() {
		return definition;
	}

	void setWord(String word) {
		this.word = word;
	}

	void setDefinition(String def) {
		this.definition = def;
	}

	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else if (obj == null || this.getClass() != obj.getClass()) {
			return false;
		}

		WordDefinitionPair other = (WordDefinitionPair) obj;
		return this.word.equals(other.getWord()) && this.definition.equals(other.getDefinition());
	}

	public String toString() {
		return this.word + ", " + this.definition;
	}
}
