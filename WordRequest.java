public class WordRequest {
    public enum RequestType { NEW, EDIT, DELETE }
    public enum Status { PENDING, APPROVED, REJECTED }

    private int wordId;
    private String writing, ipa, definition, etymology, reference;
    private RequestType type;
    private Status status;

    public WordRequest(RequestType type, int wordId, String writing, String ipa, String definition, String etymology, String reference) {
        this.type = type;
        this.wordId = wordId;
        this.writing = writing;
        this.ipa = ipa;
        this.definition = definition;
        this.etymology = etymology;
        this.reference = reference;
        this.status = Status.PENDING;
    }

    public RequestType getType() { return type; }
    public int getWordId() { return wordId; }
    public String getWriting() { return writing; }
    public String getIpa() { return ipa; }
    public String getDefinition() { return definition; }
    public String getEtymology() { return etymology; }
    public String getReference() { return reference; }
    public Status getStatus() { return status; }

    public void setStatus(Status status) { this.status = status; }
}