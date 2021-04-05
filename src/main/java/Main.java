import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashSet;

public class Main
{
    public static void main(String[] args)
    {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        WebDriver driver = new ChromeDriver();
        String root = "https://www.deckshop.pro";
        HashSet<String> deckLinks = new HashSet<>();
        for (int i = 0; i < 3; i++) {
            String url = "https://www.deckshop.pro/deck/detail/" + (1000 + i);
            driver.get(url);
            String pageSource = driver.getPageSource();

            Document doc = Jsoup.parse(pageSource);
            String title = doc.title();
            System.out.println(i + " Title " + title);
            doc.select("a.card_link").forEach(element -> {
                String linkDec = root + element.attr("href");
                System.out.println(" " + linkDec);
                deckLinks.add(linkDec);
            });
        }

        for (String deckLin : deckLinks) {
            driver.get(deckLin);
            String source = driver.getPageSource();
            Document doc = Jsoup.parse(source);
            doc.select("body > div:nth-child(6) > div.jumbotron.jumbotron-fluid.bg-dark.py-2.py-sm-3.py-md-4.py-lg-4.px-md-3.px-lg-0 > div > div > div.col-md-4 > table").forEach(element -> {
                element.select("tr").forEach(row->{
                    System.out.println(row.text());
                });

            });

        }
        driver.close();
    }
}
