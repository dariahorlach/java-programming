import java.util.HashMap;

public class Translate {
    private final HashMap<String, String> dictionary;

    public Translate() {
        dictionary = new HashMap<>();
    }

    public void addWord(String english, String ukrainian) {
        dictionary.put(english.toLowerCase(), ukrainian.toLowerCase());
    }

    public String translatePhrase(String phrase) {
        StringBuilder translated = new StringBuilder();
        String[] words = phrase.toLowerCase().split(" ");

        for (String word : words) {
            if (dictionary.containsKey(word)) {
                translated.append(dictionary.get(word)).append(" ");
            } else {
                System.out.println("Переклад не знайдено.");
            }
        }

        return translated.toString().trim();
    }
}