public class OldMain {
    public static void main(String[] args) {
        // 1. Initialize the central Vocabulary and a User
        Vocabulary vocabulary = new Vocabulary();
        User admin = new User(1);
        
        // 2. User signs up
        admin.signUp("lexicon_enthusiast", "securePass123", "dev@example.com");
        System.out.println("User registered: " + admin.getDatasetByName("none")); // Just to check status

        // 3. Add sample words to the global vocabulary
        vocabulary.newWord(
            "Serendipity", 
            "/ˌserənˈdipədē/", 
            "The occurrence of events by chance in a happy or beneficial way", 
            "Persian 'Serendip'", 
            "Oxford English Dictionary"
        );

        vocabulary.newWord(
            "Ephemeral", 
            "/əˈfem(ə)rəl/", 
            "Lasting for a very short time", 
            "Greek 'ephēmeros'", 
            "Merriam-Webster"
        );

        vocabulary.newWord(
            "Petrichor", 
            "/ˈpeˌtrīkôr/", 
            "A pleasant smell that frequently accompanies the first rain after a long period of warm, dry weather", 
            "Greek 'petra' (stone) + 'ichor' (ethereal fluid)", 
            "Nature Journal (1964)"
        );

        // 4. User creates a specific Dataset
        String mySetName = "NatureWords";
        admin.createDataset(mySetName);
        System.out.println("Dataset '" + mySetName + "' created.");

        // 5. Query words from Vocabulary and add them to the User's Dataset
        Word word1 = vocabulary.queryWord("Petrichor");
        Word word2 = vocabulary.queryWord("short time"); // Testing partial match in definition

        if (word1 != null) {
            admin.addWordToDataset(word1, mySetName);
            System.out.println("Added to set: " + word1.getWriting());
        }

        if (word2 != null) {
            admin.addWordToDataset(word2, mySetName);
            System.out.println("Added to set: " + word2.getWriting());
        }

        // 6. Export the Dataset to CSV
        Dataset mySet = admin.getDatasetByName(mySetName);
        if (mySet != null) {
            System.out.println("Exporting " + mySet.getWordSet().size() + " words to " + mySetName + ".csv...");
            mySet.exportCSV();
            System.out.println("Export complete.");
        }

        // 7. Display Word Info for confirmation
        System.out.println("\n--- Word Details ---");
        for (Word w : mySet.getWordSet()) {
            System.out.println(w.getInfo());
            System.out.println("--------------------");
        }
    }
}