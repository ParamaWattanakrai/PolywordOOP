public class Admin extends User {
    public Admin(int id) {
        super(id);
    }

    public void newWord(Vocabulary vocabulary, String writing, String ipa,
                        String definition, String etymology, String reference) {
        vocabulary.newWord(writing, ipa, definition, etymology, reference);
    }
}
