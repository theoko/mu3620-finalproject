package finalproject.mu3620.random;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Wikipedia {
    public String getText() {
        try {
            Document doc = Jsoup.connect("https://en.wikipedia.org/wiki/JFugue").get();
            Elements desc = doc.select("div.mw-parser-output p");
            System.out.println(desc.get(0).text());
            return desc.get(0).text();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
