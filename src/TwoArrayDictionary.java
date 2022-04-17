/* Make sure the instructions document is read carefully.
 * 
 * You are required to use the given `words` and `definitions` arrays to implement the methods.
 * See test_two_array_implementation_insert and test_two_array_implementation_remove 
 * in class TestArrayImplementations.
 * 
 * Requirements:
 * 		- You are not allowed to add new attributes to this class.
 * 		- You are not allowed to use any Java collection library (no import statements).
 * 
 * The idea is that both `words` and `definitions` are initialized as arrays of size `MAX_CAPACITY` with each slot storing null.
 * Entries (words and definitions) are added from left to right, whereas all free slots remain null.
 * When an entry is removed, all slots to its right are shifted to the left to close the gap.
 * 
 * For example, given a dictionary with four entries:
 * words:       {w1, w2, w3, w4, null, null, ...}
 * definitions: {d1, d2, d3, d4, null, null, ...}
 * Removing the entry for word `w2` has the resulting dictionary:
 * words:       {w1, w3, w4, null, null, null, ...}
 * definitions: {d1, d3, d4, null, null, null, ...}
 * 
 * You may consider studying this note on manipulating basic array:
 * https://www.eecs.yorku.ca/~jackie/teaching/lectures/2019/F/EECS2030/notes/EECS2030_F19_Notes_Tracing_PointCollectorTester.pdf
 */

public class TwoArrayDictionary implements Dictionary {

	/*
	 * Use these attributes only to implement the methods.
	 */
	int MAX_CAPACITY = 100;
	int count = 0; // number of entries in dictionary

	String[] words;
	String[] definitions;

	/*
	 * Your tasks: declare and implement methods from the Dictionary interface.
	 */

	TwoArrayDictionary() {
		words = new String[MAX_CAPACITY];
		definitions = new String[MAX_CAPACITY];

	}

	public int size() {
		return count;
	}

	public boolean isEmpty() {
		return count == 0;
	}

	public int indexOfWord(String word) {
		for (int i = 0; i < count; i++) {
			if (words[i].equals(word)) {
				return i;
			}
		}
		return -1;
	}

	public String getDefinition(String word) throws WordNotInDictionaryException {
		int index = indexOfWord(word);
		if (index >= 0) {
			return definitions[index];
		}

		throw new WordNotInDictionaryException("");
	}

	public void insertEntry(String word, String definition)
			throws WordAlreadyExistsInDictionaryException, DictionaryFullException {

		if (count == MAX_CAPACITY) {
			throw new DictionaryFullException("");
		} else if (indexOfWord(word) >= 0) {
			throw new WordAlreadyExistsInDictionaryException("");
		}

		else {
			this.words[count] = word;
			this.definitions[count] = definition;
			count++;

		}
	}

	public String removeWord(String word) throws WordNotInDictionaryException {
		if (indexOfWord(word) < 0) {
			throw new WordNotInDictionaryException("");
		}

		else {
			int i = indexOfWord(word);
			String def = this.definitions[i];
			for (int j = i; j < count - 1; j++) {
				words[j] = words[j + 1];
				definitions[j] = definitions[j + 1];
			}
			words[count - 1] = null;
			definitions[count - 1] = null;
			count--;
			return def;
		}
	}

	public String[] getWords() {
		String[] words2 = new String[count];
		for (int i = 0; i < count; i++) {
			words2[i] = words[i];
		}
		return words2;
	}

	public String[] getDefinitions() {
		String[] defs = new String[count];
		for (int i = 0; i < count; i++) {
			defs[i] = definitions[i];
		}
		return defs;
	}

	public WordDefinitionPair[] getEntries() {
		WordDefinitionPair[] wdp = new WordDefinitionPair[count];
		for (int i = 0; i < count; i++) {
			WordDefinitionPair pair = new WordDefinitionPair(words[i], definitions[i]);
			wdp[i] = pair;
		}
		return wdp;
	}
}
