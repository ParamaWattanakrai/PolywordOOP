import java.util.ArrayList;
import java.util.List;

public class Admin extends User {
    protected static List<WordRequest> requestQueue = new ArrayList<>();

    public Admin(int id) {
        super(id);
    }

    // Propose a change to an existing word
    public void proposeEdit(int wordId, String writing, String ipa, String definition, String etymology, String reference) {
        requestQueue.add(new WordRequest(wordId, writing, ipa, definition, etymology, reference));
    }

    // Propose a brand new word
    public void proposeNewWord(String writing, String ipa, String definition, String etymology, String reference) {
        requestQueue.add(new WordRequest(-1, writing, ipa, definition, etymology, reference));
    }
}