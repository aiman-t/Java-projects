/*
 * You are required to use the given `dict` array to implement the methods.
 * See test_one_array_implementation_insert and test_one_array_implementation_remove 
 * in class TestArrayImplementations.
 *
 * Requirements:
 * 		- You are not allowed to add new attributes to this class.
 * 		- You are not allowed to use any Java collection library (no import statements).
 * 
 * The idea is that `dict` is initialized as an array of size `MAX_CAPACITY` with each slot storing null.
 * Entries are added from left to right, whereas all free slots remain null.
 * When an entry is removed, all slots to its right are shifted to the left to close the gap.
 * 
 * For example, given a dictionary with four entries:
 * {(w1, d1), (w2, d2), (w3, d3), (w4, d4), null, null, ...} 
 * Removing the entry for word `w2` has the resulting dictionary:
 * {(w1, d1), (w3, d3), (w4, d4), null, null, null, ...}
 * 
 * You may consider studying this note on manipulating basic array:
 * https://www.eecs.yorku.ca/~jackie/teaching/lectures/2019/F/EECS2030/notes/EECS2030_F19_Notes_Tracing_PointCollectorTester.pdf
 */

public class OneArrayDictionary implements Dictionary {

	int MAX_CAPACITY = 100;
	int count = 0;
	WordDefinitionPair[] dict;

	/*
	 * Your tasks: declare and implement methods from the Dictionary interface.
	 */

	OneArrayDictionary() {
		dict = new WordDefinitionPair[MAX_CAPACITY];

	}

	public int size() {
		return count;
	}

	public boolean isEmpty() {
		return count == 0;
	}

	public int indexOfWord(String word) {
		for (int i = 0; i < count; i++) {
			if (dict[i].getWord().equals(word)) {
				return i;
			}
		}
		return -1;
	}

	public String getDefinition(String word) throws WordNotInDictionaryException {
		for (int i = 0; i < count; i++) {
			if (dict[i].getWord().equals(word)) {
				return dict[i].getDefinition();
			}
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
			dict[count] = new WordDefinitionPair(word, definition);
			count++;

		}
	}

	public String removeWord(String word) throws WordNotInDictionaryException {
		if (indexOfWord(word) < 0) {
			throw new WordNotInDictionaryException("");
		}

		else {
			int i = indexOfWord(word);
			String def = dict[i].getDefinition();
			for (int j = i; j < count - 1; j++) {
				dict[j] = dict[j + 1];
			}
			dict[count - 1] = null;
			count--;
			return def;
		}
	}

	public String[] getWords() {
		String[] words = new String[count];
		for (int i = 0; i < count; i++) {
			words[i] = dict[i].getWord();
		}
		return words;
	}

	public String[] getDefinitions() {
		String[] defs = new String[count];
		for (int i = 0; i < count; i++) {
			defs[i] = dict[i].getDefinition();
		}
		return defs;
	}

	public WordDefinitionPair[] getEntries() {
		WordDefinitionPair[] wdp = new WordDefinitionPair[count];
		for (int i = 0; i < count; i++) {
			wdp[i] = dict[i];
		}
		return wdp;
	}
}
