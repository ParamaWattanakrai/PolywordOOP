
public class Linguist extends Admin {
    public Linguist(int id) {
        super(id);
    }

    public void approveRequests(Vocabulary vocabulary) {
        for (WordRequest req : requestQueue) {
            if (req.isNewWordRequest()) {
                // Logic for NEW word
                vocabulary.newWord(req.getWriting(), req.getIpa(), req.getDefinition(), req.getEtymology(), req.getReference());
                System.out.println("Approved NEW word: " + req.getWriting());
            } else {
                // Logic for EDITING existing word
                boolean success = vocabulary.editWord(req.getWordId(), req.getWriting(), req.getIpa(), req.getDefinition(), req.getEtymology(), req.getReference());
                if (success) System.out.println("Approved EDIT for ID: " + req.getWordId());
            }
        }
        requestQueue.clear();
    }
}