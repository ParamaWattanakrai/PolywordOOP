public class Main {
    private static Vocabulary vocabulary = new Vocabulary();
    private static User user = new User(101);

    static {
        vocabulary.newWord("สวัสดี", "/sà.wàt.diː/", "Hello", "Thai greeting", "RID");
        vocabulary.newWord("ขอบคุณ", "/khɔ̀ːp.khun/", "Thank you", "Thai gratitude", "RID");
        vocabulary.newWord("សួស្តី", "/suəs.dəy/", "Hello", "Khmer greeting", "Khmer Dict");
        vocabulary.newWord("សូមអរគុណ", "/soom aa-kun/", "Thank you", "Khmer gratitude", "Khmer Dict");
        vocabulary.newWord("မင်္ဂလာပါ", "/mɪ̀ɴɡəlàbà/", "Hello", "Burmese greeting", "MLC");
        vocabulary.newWord("ကျေးဇူးတင်ပါတယ်", "/tɕè.zú.tɪ̀ɴ.bà.dè/", "Thank you", "Burmese gratitude", "MLC");
    }

    public static void main(String[] args) {
        Word w = user.queryWord(vocabulary, "สวัสดี");
        System.out.println(w.getInfo());
    }
}