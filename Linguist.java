public class Linguist extends Admin {
    public Linguist(int id) {
        super(id);
    }

    public void rejectRequest(WordRequest request) {
        if (requestQueue.contains(request)) {
            request.setStatus(WordRequest.Status.REJECTED);
            requestQueue.remove(request);
            System.out.println("Rejected request for: " + request.getWriting());
        }
    }

    public void approveRequest(WordRequest request, Vocabulary vocabulary) {
        if (!requestQueue.contains(request)) return;

        boolean success = false;
        switch (request.getType()) {
            case NEW:
                vocabulary.newWord(
                    request.getWriting(), 
                    request.getIpa(), 
                    request.getDefinition(), 
                    request.getEtymology(), 
                    request.getReference()
                );
                success = true;
                break;
            case EDIT:
                success = vocabulary.editWord(
                    request.getWordId(), 
                    request.getWriting(), 
                    request.getIpa(), 
                    request.getDefinition(), 
                    request.getEtymology(), 
                    request.getReference()
                );
                break;
            case DELETE:
                success = vocabulary.deleteWord(request.getWordId());
                break;
        }

        if (success) {
            request.setStatus(WordRequest.Status.APPROVED);
            requestQueue.remove(request);
            System.out.println("Successfully approved and applied request: " + request.getType());
        } else {
            System.out.println("Failed to apply request. Target Word ID may not exist.");
        }
    }
}