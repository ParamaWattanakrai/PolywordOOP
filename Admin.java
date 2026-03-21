import java.util.ArrayList;
import java.util.List;

public class Admin extends User {
    protected static List<WordRequest> requestQueue = new ArrayList<>();

    public Admin(int id) {
        super(id);
    }

    public void proposeEditWord(int wordId, String writing, String ipa, String definition, String etymology, String reference) {
        requestQueue.add(new WordRequest(WordRequest.RequestType.EDIT, wordId, writing, ipa, definition, etymology, reference));
    }

    public void proposeNewWord(String writing, String ipa, String definition, String etymology, String reference) {
        requestQueue.add(new WordRequest(WordRequest.RequestType.NEW, -1, writing, ipa, definition, etymology, reference));
    }

    public void proposeDeleteWord(int wordId) {
        requestQueue.add(new WordRequest(WordRequest.RequestType.DELETE, wordId, null, null, null, null, null));
    }
}