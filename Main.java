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
        runDeletionDemonstration(); // Added deletion demo
        runDatasetAndExport();
    }

    private static void initializeSystem() {
        System.out.println("=== INITIALIZING SYSTEM ===");

        globalVocab.newWord(
            "สวย",
            "/suːaj/",
            "Beautiful; attractive in appearance.",
            "Native Thai root word",
            "Royal Institute Dictionary of Thailand"
        );
        globalVocab.newWord(
            "ใจดี",
            "/tɕaj diː/",
            "Kind-hearted; generous and gentle in nature.",
            "Thai compound: ใจ (heart) + ดี (good)",
            "Royal Institute Dictionary of Thailand"
        );
        globalVocab.newWord(
            "สงบ",
            "/sa ŋòp/",
            "Calm; peaceful and undisturbed.",
            "Native Thai root word",
            "Royal Institute Dictionary of Thailand"
        );

        globalVocab.newWord(
            "မြတ်နိုး",
            "/mjaʔ nó/",
            "To cherish; to hold something dear with deep affection.",
            "Burmese compound: မြတ် (precious) + နိုး (to value)",
            "Myanmar Language Commission Dictionary"
        );
        globalVocab.newWord(
            "ငြိမ်သက်",
            "/ɲɪ̀ɪ̃ θɛʔ/",
            "Serene; perfectly still and at peace.",
            "Native Burmese root word",
            "Myanmar Language Commission Dictionary"
        );
    }

    private static void runUserAuthentication() {
        System.out.println("\n=== USE CASE: User Authentication ===");
        String username = "polyglot_99";
        String password = "securePassword123";
        user.signUp(username, password, "user@example.com");
        System.out.println("User registered successfully.");

        boolean loginSuccess = user.signIn(username, password);
        System.out.println("Login attempt (Correct credentials): " + (loginSuccess ? "Success" : "Failed"));
    }

    private static void runUserSearch() {
        System.out.println("\n=== USE CASE 1: User Search ===");
        Word found1 = globalVocab.queryWord("สวย");
        if (found1 != null) {
            System.out.println("User found Thai word 'สวย' via search. Details:\n" + found1.getInfo());
        }
    }

    private static void runAdminProposals() {
        System.out.println("\n=== USE CASE 2: Admin Proposals ===");

        admin.proposeNewWord(
            "ความรัก",
            "/kʰwaːm ráːk/",
            "Love; a deep feeling of affection and attachment.",
            "Thai compound: ความ + รัก",
            "Royal Institute Dictionary of Thailand"
        );

        admin.proposeEdit(
            0,
            "สวย",
            "/suːaj/",
            "Beautiful; attractive in appearance. Often used for scenery.",
            "Native Thai root word",
            "Royal Institute Dictionary of Thailand (Updated Edition)"
        );
    }

    private static void runLinguistApproval() {
        System.out.println("\n=== USE CASE 3: Linguist Approval ===");
        System.out.println("Requests in queue: " + Admin.requestQueue.size());
        linguist.approveRequests(globalVocab);
    }

    private static void runFinalVerification() {
        System.out.println("\n=== USE CASE 4: Verification ===");
        Word kwamrak = globalVocab.queryWord("ความรัก");
        if (kwamrak != null) System.out.println("ความรัก Status: Approved and Found (ID: " + kwamrak.getId() + ")");
    }

    private static void runDeletionDemonstration() {
        System.out.println("\n=== USE CASE: Deletion Demonstration ===");
        
        // Find a word to delete
        Word toDelete = globalVocab.queryWord("สงบ");
        if (toDelete != null) {
            int id = toDelete.getId();
            System.out.println("Admin proposing deletion for word: " + toDelete.getWriting() + " (ID: " + id + ")");
            
            // 1. Admin proposes deletion
            admin.proposeDelete(id);
            
            // 2. Linguist approves the deletion request
            System.out.println("Linguist reviewing delete request...");
            linguist.approveRequests(globalVocab);
            
            // 3. Verify it is gone
            Word check = globalVocab.queryWord("สงบ");
            System.out.println("Searching for 'สงบ' after deletion: " + (check == null ? "Not Found (Success)" : "Found (Failure)"));
        }
    }

    private static void runDatasetAndExport() {
        System.out.println("\n=== USE CASE 5: Dataset & Export ===");
        String setName = "ThaiAndBurmeseWords";
        user.createDataset(setName);

        Word w1 = globalVocab.queryWord("สวย");
        Word w2 = globalVocab.queryWord("ใจดี");
        
        if (w1 != null) user.addWordToDataset(w1, setName);
        if (w2 != null) user.addWordToDataset(w2, setName);

        Dataset userSet = user.getDatasetByName(setName);
        if (userSet != null) {
            userSet.exportCSV();
            System.out.println("Exported " + userSet.getWordSet().size() + " words to '" + setName + ".csv'");
        }
    }
}