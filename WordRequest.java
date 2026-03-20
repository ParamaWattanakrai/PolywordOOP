public class WordRequest {
    private int wordId; // -1 indicates a NEW word request
    private String writing, ipa, definition, etymology, reference;

    public WordRequest(int wordId, String writing, String ipa, String definition, String etymology, String reference) {
        this.wordId = wordId;
        this.writing = writing;
        this.ipa = ipa;
        this.definition = definition;
        this.etymology = etymology;
        this.reference = reference;
    }

    // Getters
    public int getWordId() { return wordId; }
    public boolean isNewWordRequest() { return wordId == -1; }
    public String getWriting() { return writing; }
    public String getIpa() { return ipa; }
    public String getDefinition() { return definition; }
    public String getEtymology() { return etymology; }
    public String getReference() { return reference; }
}