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

        // Seed initial Thai words
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

        // Seed initial Burmese words
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

        // Search for seeded Thai words
        Word found1 = globalVocab.queryWord("สวย");
        if (found1 != null) {
            System.out.println("User found Thai word 'สวย' via search. Details:\n" + found1.getInfo());
        }

        Word found2 = globalVocab.queryWord("ใจดี");
        if (found2 != null) {
            System.out.println("User found Thai word 'ใจดี' via search. Details:\n" + found2.getInfo());
        }

        // Search for seeded Burmese word
        Word found3 = globalVocab.queryWord("မြတ်နိုး");
        if (found3 != null) {
            System.out.println("User found Burmese word 'မြတ်နိုး' via search. Details:\n" + found3.getInfo());
        }
    }

    private static void runAdminProposals() {
        System.out.println("\n=== USE CASE 2: Admin Proposals ===");

        // Proposal 1: New Thai word
        admin.proposeNewWord(
            "ความรัก",
            "/kʰwaːm ráːk/",
            "Love; a deep feeling of affection and attachment toward someone or something.",
            "Thai compound: ความ (abstract noun prefix) + รัก (to love)",
            "Royal Institute Dictionary of Thailand"
        );

        // Proposal 2: New Burmese word
        admin.proposeNewWord(
            "သတ္တိ",
            "/θaʔtḭ/",
            "Courage; the quality of being brave in the face of difficulty or danger.",
            "Pali loanword: sattī (strength, power)",
            "Myanmar Language Commission Dictionary"
        );

        // Proposal 3: New Thai word
        admin.proposeNewWord(
            "ความฝัน",
            "/kʰwaːm fǎn/",
            "A dream; an aspiration or vision one hopes to achieve.",
            "Thai compound: ความ (abstract noun prefix) + ฝัน (to dream)",
            "Royal Institute Dictionary of Thailand"
        );

        // Proposal 4: Edit existing word 'สวย' (ID 0) with updated reference
        admin.proposeEdit(
            0,
            "สวย",
            "/suːaj/",
            "Beautiful; attractive in appearance. Often used to describe people, objects, or scenery.",
            "Native Thai root word",
            "Royal Institute Dictionary of Thailand (Updated Edition)"
        );

        System.out.println("Searching for 'ความรัก' before approval: " +
            (globalVocab.queryWord("ความรัก") != null ? "Found" : "Not Found"));
        System.out.println("Searching for 'သတ္တိ' before approval: " +
            (globalVocab.queryWord("သတ္တိ") != null ? "Found" : "Not Found"));
        System.out.println("Searching for 'ความฝัน' before approval: " +
            (globalVocab.queryWord("ความฝัน") != null ? "Found" : "Not Found"));
    }

    private static void runLinguistApproval() {
        System.out.println("\n=== USE CASE 3: Linguist Approval ===");
        System.out.println("Requests in queue: " + Admin.requestQueue.size());
        linguist.approveRequests(globalVocab);
    }

    private static void runFinalVerification() {
        System.out.println("\n=== USE CASE 4: Verification ===");

        Word suay      = globalVocab.queryWord("สวย");
        Word jaidee    = globalVocab.queryWord("ใจดี");
        Word songop    = globalVocab.queryWord("สงบ");
        Word kwamrak   = globalVocab.queryWord("ความรัก");
        Word kwamfan   = globalVocab.queryWord("ความฝัน");
        Word myatnoe   = globalVocab.queryWord("မြတ်နိုး");
        Word ngimshet  = globalVocab.queryWord("ငြိမ်သက်");
        Word thatti    = globalVocab.queryWord("သတ္တိ");

        if (suay     != null) System.out.println("สวย Reference: "      + suay.getReference());
        if (jaidee   != null) System.out.println("ใจดี Reference: "     + jaidee.getReference());
        if (songop   != null) System.out.println("สงบ Reference: "      + songop.getReference());
        if (kwamrak  != null) System.out.println("ความรัก Status: Approved and Found (ID: " + kwamrak.getId() + ")");
        if (kwamfan  != null) System.out.println("ความฝัน Status: Approved and Found (ID: " + kwamfan.getId() + ")");
        if (myatnoe  != null) System.out.println("မြတ်နိုး Reference: " + myatnoe.getReference());
        if (ngimshet != null) System.out.println("ငြိမ်သက် Reference: " + ngimshet.getReference());
        if (thatti   != null) System.out.println("သတ္တိ Status: Approved and Found (ID: " + thatti.getId() + ")");
    }

    private static void runDatasetAndExport() {
        System.out.println("\n=== USE CASE 5: Dataset & Export ===");
        String setName = "ThaiAndBurmeseWords";
        user.createDataset(setName);

        Word w1 = globalVocab.queryWord("สวย");
        Word w2 = globalVocab.queryWord("ใจดี");
        Word w3 = globalVocab.queryWord("สงบ");
        Word w4 = globalVocab.queryWord("ความรัก");
        Word w5 = globalVocab.queryWord("ความฝัน");
        Word w6 = globalVocab.queryWord("မြတ်နိုး");
        Word w7 = globalVocab.queryWord("ငြိမ်သက်");
        Word w8 = globalVocab.queryWord("သတ္တိ");

        user.addWordToDataset(w1, setName);
        user.addWordToDataset(w2, setName);
        user.addWordToDataset(w3, setName);
        user.addWordToDataset(w4, setName);
        user.addWordToDataset(w5, setName);
        user.addWordToDataset(w6, setName);
        user.addWordToDataset(w7, setName);
        user.addWordToDataset(w8, setName);

        Dataset userSet = user.getDatasetByName(setName);
        if (userSet != null) {
            userSet.exportCSV();
            System.out.println("Exported " + userSet.getWordSet().size() + " words to '" + setName + ".csv'");
        }
    }
}