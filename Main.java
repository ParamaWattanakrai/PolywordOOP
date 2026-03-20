public class Main {

    // Global instances for the demonstration
    private static Vocabulary globalVocab = new Vocabulary();
    private static User user = new User(1);
    private static Admin admin = new Admin(100);
    private static Linguist linguist = new Linguist(200);

    public static void main(String[] args) {
        initializeSystem();
        runUserAuthentication();
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
    }

    private static void runUserAuthentication() {
        System.out.println("\n=== USE CASE: User Authentication ===");
        
        // 1. Sign Up
        String username = "polyglot_99";
        String password = "securePassword123";
        user.signUp(username, password, "user@example.com");
        System.out.println("User registered successfully.");

        // 2. Sign In (Success)
        boolean loginSuccess = user.signIn(username, password);
        System.out.println("Login attempt (Correct credentials): " + (loginSuccess ? "Success" : "Failed"));

        // 3. Sign In (Failure)
        boolean loginFail = user.signIn(username, "wrongPassword");
        System.out.println("Login attempt (Wrong credentials): " + (loginFail ? "Success" : "Failed"));
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
        admin.proposeNewWord(
            "Petrichor", 
            "/ˈpeˌtrīkôr/", 
            "A pleasant smell that frequently accompanies the first rain.", 
            "Greek 'petra' (stone) + 'ichor' (ethereal fluid)", 
            "Nature Journal 1964"
        );
        
        // Proposal 2: Edit existing word (ID 0)
        admin.proposeEdit(
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
        linguist.approveRequests(globalVocab);
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
        user.createDataset(setName);
        
        Word w1 = globalVocab.queryWord("Mellifluous");
        Word w2 = globalVocab.queryWord("Petrichor");

        user.addWordToDataset(w1, setName);
        user.addWordToDataset(w2, setName);

        Dataset userSet = user.getDatasetByName(setName);
        if (userSet != null) {
            userSet.exportCSV();
            System.out.println("Exported " + userSet.getWordSet().size() + " words to '" + setName + ".csv'");
        }
    }
}