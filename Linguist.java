public class Linguist extends Admin {
    public Linguist(int id) {
        super(id);
    }

    public void approveRequests(Vocabulary vocabulary) {
        for (WordRequest req : requestQueue) {
            switch (req.getType()) {
                case NEW:
                    vocabulary.newWord(req.getWriting(), req.getIpa(), req.getDefinition(), req.getEtymology(), req.getReference());
                    System.out.println("Approved NEW word: " + req.getWriting());
                    break;
                case EDIT:
                    boolean editSuccess = vocabulary.editWord(req.getWordId(), req.getWriting(), req.getIpa(), req.getDefinition(), req.getEtymology(), req.getReference());
                    if (editSuccess) System.out.println("Approved EDIT for ID: " + req.getWordId());
                    break;
                case DELETE:
                    boolean deleteSuccess = vocabulary.deleteWord(req.getWordId());
                    if (deleteSuccess) System.out.println("Approved DELETE for ID: " + req.getWordId());
                    break;
            }
        }
        requestQueue.clear();
    }
}