public class Word {
    private int id;
    private String writing;
    private String ipa;
    private String definition;
    private String etymology;
    private String reference;

    public Word(int id, String writing, String ipa,
                String definition, String etymology, String reference) {
        this.id = id;
        this.writing = writing;
        this.ipa = ipa;
        this.definition = definition;
        this.etymology = etymology;
        this.reference = reference;
    }

    public String getInfo() {
        return "ID: " + id + "\n" +
               "Word: " + writing + "\n" +
               "IPA: " + ipa + "\n" +
               "Definition: " + definition + "\n" +
               "Etymology: " + etymology + "\n" +
               "Reference: " + reference;
    }

    public void setWriting(String writing) {
        this.writing = writing;
    }

    public void setIpa(String ipa) {
        this.ipa = ipa;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public void setEtymology(String etymology) {
        this.etymology = etymology;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public int getId() {
        return id;
    }

    public String getWriting() {
        return writing;
    }

    public String getIpa() {
        return ipa;
    }

    public String getDefinition() {
        return definition;
    }

    public String getEtymology() {
        return etymology;
    }

    public String getReference() {
        return reference;
    }
}
