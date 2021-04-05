import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main
{
    public static void main(String[] args)
    {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        WebDriver driver = new ChromeDriver();

        for (int i = 0; i < 100; i++) {
            String url="https://www.deckshop.pro/deck/detail/"+(1000+i);
            driver.get(url);
            String pageSource = driver.getPageSource();

            Document doc = Jsoup.parse(pageSource);
            String title = doc.title();
            System.out.println("Title "+title);
            doc.select("a.card_link").forEach(element -> {
                System.out.println(" "+element.attr("href"));
            });
        }
    }
}
