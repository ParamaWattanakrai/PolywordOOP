import java.util.HashMap;
import java.util.Map;

public class Vocabulary {
    private int nextId = 0;
    private Map<Integer, Word> wordHash;

    public Vocabulary() {
        wordHash = new HashMap<>();
    }

    public void newWord(String writing, String ipa, String definition,
                        String etymology, String reference) {
        wordHash.put(nextId, new Word(nextId, writing, ipa, definition, etymology, reference));
        nextId++;
    }

    public boolean deleteWord(int wordId) {
        if (wordHash.containsKey(wordId)) {
            wordHash.remove(wordId);
            return true;
        }
        return false;
    }

    public boolean editWord(int wordId, String writing, String ipa, 
                            String definition, String etymology, String reference) {
        Word word = wordHash.get(wordId);
        if (word != null) {
            word.setWriting(writing);
            word.setIpa(ipa);
            word.setDefinition(definition);
            word.setEtymology(etymology);
            word.setReference(reference);
            return true;
        }
        return false;
    }

    public Word queryWord(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return null;
        }
    
        String lowerKeyword = keyword.toLowerCase();
    
        for (Word word : wordHash.values()) {
            // Priority 1: Exact match on the writing (the word itself)
            if (word.getWriting().equalsIgnoreCase(lowerKeyword)) {
                return word;
            }
        }
    
        for (Word word : wordHash.values()) {
            // Priority 2: Partial match on the writing
            if (word.getWriting().toLowerCase().contains(lowerKeyword)) {
                return word;
            }
        }
    
        for (Word word : wordHash.values()) {
            // Priority 3: Partial match in the definition
            if (word.getDefinition().toLowerCase().contains(lowerKeyword)) {
                return word;
            }
        }
    
        return null;
    }
}
