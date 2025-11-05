import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.*;

public class TagFrequency {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть URL: ");
        String url = scanner.nextLine();

        System.out.print("Введіть ім'я файлу для збереження результату: ");
        String fileName = scanner.nextLine();

        try {
            Document doc = Jsoup.connect(url).get();

            Map<String, Integer> tagCount = new HashMap<>();

            for (Element element : doc.getAllElements()) {
                String tag = element.tagName();
                tagCount.put(tag, tagCount.getOrDefault(tag, 0) + 1);
            }

            List<TagData> tagList = new ArrayList<>();
            for (Map.Entry<String, Integer> entry : tagCount.entrySet()) {
                tagList.add(new TagData(entry.getKey(), entry.getValue()));
            }

            FileManager<TagData> fm = new FileManager<>(fileName);
            fm.save(tagList);

            System.out.println("\nСортування тегів в лексикографічному порядку:");
            tagList.stream()
                    .sorted(Comparator.comparing(TagData::tag))
                    .forEach(System.out::println);


            System.out.println("\nСортування тегів за частотою:");
            tagList.stream()
                    .sorted(Comparator.comparingInt(TagData::count))
                    .forEach(System.out::println);
        } catch (IOException e) {
            System.out.println("Помилка при з'єднанні: " + e.getMessage());
        }
    }
}