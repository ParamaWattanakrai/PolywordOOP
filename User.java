import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private List<Dataset> datasetList;

    public User(int id) {
        datasetList = new ArrayList<Dataset>();
    }

    public void signUp(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public boolean signIn(String username, String password) {
        if (this.username.equals(username) && this.password.equals(password)) return true;
        return false;
    }

    public void createDataset(String datasetName) {
        if (getDatasetByName(datasetName) != null) return;
        datasetList.add(new Dataset(this, datasetName));
    }

    public void addWordToDataset(Word word, String datasetName) {
        Dataset dataset = getDatasetByName(datasetName);
        if (dataset == null) return;
        dataset.addWord(word);
    }

    public void removeWordFromDataset(Word word, String datasetName) {
        Dataset dataset = getDatasetByName(datasetName);
        if (dataset == null) return;
        dataset.removeWord(word);
    }

    public Dataset getDatasetByName(String datasetName) {
        for (Dataset dataset : datasetList) {
            if (dataset.getName().equals(datasetName)) return dataset;
        }
        return null;
    }
}