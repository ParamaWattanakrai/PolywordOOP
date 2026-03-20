public class Main {

    // Global instances for the demonstration
    private static Vocabulary globalVocab = new Vocabulary();
    private static User languageLearner = new User(1);
    private static Admin moderator = new Admin(100);
    private static Linguist scholar = new Linguist(200);

    public static void main(String[] args) {
        initializeSystem();
        runUserSearch();
        runAdminProposals();
        runLinguistApproval();
        runFinalVerification();
        runDatasetAndExport();
    }

    private static void initializeSystem() {
        System.out.println("=== INITIALIZING SYSTEM ===");
        // Seed initial word with a basic reference
        globalVocab.newWord(
            "Mellifluous", 
            "/məˈliflo͞oəs/", 
            "A sound that is sweet and smooth; pleasing to hear.", 
            "Latin 'mel' (honey) + 'fluere' (to flow)", 
            "General Dictionary"
        );
        languageLearner.signUp("polyglot_99", "nature123", "user@example.com");
    }

    private static void runUserSearch() {
        System.out.println("\n=== USE CASE 1: User Search ===");
        Word found = globalVocab.queryWord("Mellifluous");
        if (found != null) {
            System.out.println("User found word via search. Details:\n" + found.getInfo());
        }
    }

    private static void runAdminProposals() {
        System.out.println("\n=== USE CASE 2: Admin Proposals ===");
        
        // Proposal 1: New Word
        moderator.proposeNewWord(
            "Petrichor", 
            "/ˈpeˌtrīkôr/", 
            "A pleasant smell that frequently accompanies the first rain.", 
            "Greek 'petra' (stone) + 'ichor' (ethereal fluid)", 
            "Nature Journal 1964"
        );
        
        // Proposal 2: Edit existing word (ID 0)
        moderator.proposeEdit(
            0, 
            "Mellifluous", 
            "/məˈliflo͞oəs/", 
            "A sound that is sweet and smooth; pleasing to hear.", 
            "Latin 'mel' (honey) + 'fluere' (to flow)", 
            "Oxford English Dictionary (Updated)"
        );

        System.out.println("Searching for 'Petrichor' before approval: " + 
            (globalVocab.queryWord("Petrichor") != null ? "Found" : "Not Found"));
    }

    private static void runLinguistApproval() {
        System.out.println("\n=== USE CASE 3: Linguist Approval ===");
        System.out.println("Requests in queue: " + Admin.requestQueue.size());
        scholar.approveRequests(globalVocab);
    }

    private static void runFinalVerification() {
        System.out.println("\n=== USE CASE 4: Verification ===");
        Word melli = globalVocab.queryWord("Mellifluous");
        Word petri = globalVocab.queryWord("Petrichor");

        if (melli != null) System.out.println("Mellifluous Reference: " + melli.getReference());
        if (petri != null) System.out.println("Petrichor Status: Approved and Found (ID: " + petri.getId() + ")");
    }

    private static void runDatasetAndExport() {
        System.out.println("\n=== USE CASE 5: Dataset & Export ===");
        String setName = "BeautifulWords";
        languageLearner.createDataset(setName);
        
        Word w1 = globalVocab.queryWord("Mellifluous");
        Word w2 = globalVocab.queryWord("Petrichor");

        languageLearner.addWordToDataset(w1, setName);
        languageLearner.addWordToDataset(w2, setName);

        Dataset userSet = languageLearner.getDatasetByName(setName);
        if (userSet != null) {
            userSet.exportCSV();
            System.out.println("Exported " + userSet.getWordSet().size() + " words to '" + setName + ".csv'");
        }
    }
}