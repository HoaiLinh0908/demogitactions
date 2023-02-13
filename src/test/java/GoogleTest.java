import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class GoogleTest {
    private final By searchBox = By.xpath("//input[@name='q' and @role='combobox']");
    String theBeatles = "The Beatles";

    public void doGoogleSearch(String keyword) {
        $(searchBox).clear();
        $(searchBox).setValue(keyword);
        $(searchBox).sendKeys(Keys.ENTER);
    }

    public boolean doesSearchBoxRemainKeyWord(String keyWord) {
        return $(searchBox).getValue().equals(keyWord);
    }

    @Test
    public void testGoogleSearchTheBeatles() {
        //Configure test
        Configuration.browserSize = null;
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--lang=en-GB");
        Configuration.browserCapabilities = options;

        //Run test
        open("https://www.google.com/");
        doGoogleSearch(theBeatles);
        Assert.assertTrue(doesSearchBoxRemainKeyWord(theBeatles));

        //Collapse test
        Selenide.closeWindow();
        Selenide.closeWebDriver();
    }
}
