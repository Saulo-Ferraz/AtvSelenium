import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class jUnit {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/driver/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void testarSelecaoNoDropdown() {
        driver.get("https://bulbapedia.bulbagarden.net/wiki/Main_Page");

        WebElement dropdownElement = driver.findElement(By.className("bg-global-nav-primary-toggle"));

        Select dropdown = new Select(dropdownElement);

        dropdown.selectByIndex(0);

        WebElement selectedLink = driver.findElement(By.cssSelector(".bg-global-nav-secondary-item.header"));

        assertEquals("Social", selectedLink.getText());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
