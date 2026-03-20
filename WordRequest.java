public class WordRequest {
    public enum RequestType { NEW, EDIT, DELETE }

    private int wordId;
    private String writing, ipa, definition, etymology, reference;
    private RequestType type;

    public WordRequest(RequestType type, int wordId, String writing, String ipa, String definition, String etymology, String reference) {
        this.type = type;
        this.wordId = wordId;
        this.writing = writing;
        this.ipa = ipa;
        this.definition = definition;
        this.etymology = etymology;
        this.reference = reference;
    }

    // Getters
    public RequestType getType() { return type; }
    public int getWordId() { return wordId; }
    public String getWriting() { return writing; }
    public String getIpa() { return ipa; }
    public String getDefinition() { return definition; }
    public String getEtymology() { return etymology; }
    public String getReference() { return reference; }
}