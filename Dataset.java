import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Dataset {
    private User user;
    private String name;
    private Set<Word> wordSet;

    public Dataset(User user, String name) {
        this.user = user;
        this.name = name;
        wordSet = new HashSet<>();
    }

    public void addWord(Word word) {
        wordSet.add(word);
    }

    public void removeWord(Word word) {
        wordSet.remove(word);
    }

    public void exportCSV() {
        try (FileWriter writer = new FileWriter(name + ".csv")) {

            writer.write("id,writing,ipa,definition,etymology,reference\n");

            for (Word w : wordSet) {
                writer.write(
                    w.getId() + "," +
                    w.getWriting() + "," +
                    w.getIpa() + "," +
                    w.getDefinition() + "," +
                    w.getEtymology() + "," +
                    w.getReference() + "\n"
                );
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public Set<Word> getWordSet() {
        return wordSet;
    }
}
