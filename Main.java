public class Main {
    public static void main(String[] args) {
        Vocabulary vocabulary = new Vocabulary();
        User user = new User(101);
        Admin admin = new Admin(201);
        Linguist linguist = new Linguist(301);

        System.out.println("=== 1. & 2. Authentication ===");
        testAuthentication(user);

        System.out.println("\n=== Initializing Vocabulary ===");
        setupInitialVocab(vocabulary);

        System.out.println("\n=== 3. Query Word ===");
        testQuery(vocabulary);

        System.out.println("\n=== 4. & 5. Dataset Management ===");
        testDatasetManagement(user, vocabulary);

        System.out.println("\n=== 6. Export Dataset ===");
        testExport(user, vocabulary);

        System.out.println("\n=== 7., 8., & 9. Proposals ===");
        testProposals(admin);

        System.out.println("\n=== 10. & 11. Request Handling ===");
        testRequestHandling(linguist, vocabulary);
    }

    private static void testAuthentication(User user) {
        user.signUp("Parama", "pass123", "parama@uni.ac.th");
        boolean login = user.signIn("Parama", "pass123");
        System.out.println("Sign Up and Sign In successful: " + login);
    }

    private static void setupInitialVocab(Vocabulary v) {
        v.newWord("สวัสดี", "/sà.wàt.diː/", "Hello", "Thai greeting", "RID");
        v.newWord("ขอบคุณ", "/khɔ̀ːp.khun/", "Thank you", "Thai gratitude", "RID");
        v.newWord("សួស្តី", "/suəs.dəy/", "Hello", "Khmer greeting", "Khmer Dict");
        v.newWord("សូមអរគុណ", "/soom aa-kun/", "Thank you", "Khmer gratitude", "Khmer Dict");
        v.newWord("မင်္ဂလာပါ", "/mɪ̀ɴɡəlàbà/", "Hello", "Burmese greeting", "MLC");
        v.newWord("ကျေးဇူးတင်ပါတယ်", "/tɕè.zú.tɪ̀ɴ.bà.dè/", "Thank you", "Burmese gratitude", "MLC");
    }

    private static void testQuery(Vocabulary v) {
        Word w = v.queryWord("สวัสดี");
        if (w != null) {
            System.out.println("Query Result:");
            System.out.println(w.getInfo());
        }
    }

    private static void testDatasetManagement(User user, Vocabulary v) {
        user.createDataset("SEA_Phrases");
        
        // 4. addWordToDataset (Using objects from vocabulary)
        user.addWordToDataset(v.queryWord("สวัสดี"), "SEA_Phrases");
        user.addWordToDataset(v.queryWord("សួស្តី"), "SEA_Phrases");
        user.addWordToDataset(v.queryWord("မင်္ဂလာပါ"), "SEA_Phrases");
        user.addWordToDataset(v.queryWord("ขอบคุณ"), "SEA_Phrases");
        System.out.println("Added 4 existing words to dataset.");

        // 5. removeWordFromDataset
        user.removeWordFromDataset(v.queryWord("ขอบคุณ"), "SEA_Phrases");
        System.out.println("Removed 'ขอบคุณ' from dataset.");
    }

    private static void testExport(User user, Vocabulary v) {
        Dataset ds = user.getDatasetByName("SEA_Phrases");
        if (ds != null) {
            // Correctly adding more vocabs by registering them in Vocabulary first
            v.newWord("သူငယ်ချင်း", "/θəŋèdʑɪ́ɴ/", "Friend", "Burmese", "MLC");
            v.newWord("មិត្តភក្តိ", "/mɨt pʰeak/", "Friend", "Khmer", "Khmer Dict");
            
            // Adding the newly registered words to the dataset
            ds.addWord(v.queryWord("သူငယ်ချင်း"));
            ds.addWord(v.queryWord("មិត្តភក្តိ"));
            
            ds.exportCSV();
            System.out.println("Exported " + ds.getName() + ".csv with " + ds.getWordSet().size() + " words.");
        }
    }

    private static void testProposals(Admin admin) {
        // 7. proposeEditWord
        admin.proposeEdit(0, "สวัสดีครับ", "/sà.wàt.diː.khráp/", "Hello (Polite)", "Thai", "RID");
        
        // 8. proposeNewWord
        admin.proposeNewWord("စားပြီးပြီလား", "/sàː.pîː.pīː.láː/", "Have you eaten?", "Thai small talk", "General");
        
        // 9. proposeDeleteWord
        admin.proposeDelete(2);
        
        System.out.println("Current Pending Requests: " + Admin.requestQueue.size());
    }

    private static void testRequestHandling(Linguist linguist, Vocabulary v) {
        if (Admin.requestQueue.isEmpty()) return;

        // 10. ApproveRequest
        WordRequest reqToApprove = Admin.requestQueue.get(0);
        linguist.approveRequest(reqToApprove, v);
        System.out.println("Approved Request Type: " + reqToApprove.getType());

        // 11. rejectRequest
        if (!Admin.requestQueue.isEmpty()) {
            WordRequest reqToReject = Admin.requestQueue.get(0);
            linguist.rejectRequest(reqToReject);
            System.out.println("Rejected Request Type: " + reqToReject.getType());
        }
    }
}