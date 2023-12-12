import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class SeleniumTest {
    
        @Before
	    public void setup(){
		System.setProperty("webdriver.chrome.driver", "src/driver/chromedriver");
		
	}

	@Test
    	public void test01(){
		System.setProperty("webdriver.chrome.driver", "src/driver/chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://bulbapedia.bulbagarden.net/wiki/Main_Page");
		
		String currentUrl = driver.getCurrentUrl();
		String expected = "https://bulbapedia.bulbagarden.net/wiki/Main_Page";
		
		assertEquals(expected, currentUrl);
		
		driver.quit();
    }

	@Test
	public void test02(){
		System.setProperty("webdriver.chrome.driver", "src/driver/chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://bulbapedia.bulbagarden.net/wiki/Main_Page");

		WebElement clickable = driver.findElement(By.id("ca-talk"));
        new Actions(driver)
                .click(clickable)
                .perform();
		Assertions.assertTrue(driver.getCurrentUrl().contains("https://bulbapedia.bulbagarden.net/wiki/Talk:Main_Page"));

		driver.quit();
	}

	@Test
	public void test03() {
		System.setProperty("webdriver.chrome.driver", "src/driver/chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://bulbapedia.bulbagarden.net/wiki/Main_Page");
		WebElement search = driver.findElement(By.id("searchInput"));
		search.sendKeys("Dragonite");
		search.submit();
		
		Assertions.assertTrue(driver.getCurrentUrl().contains("https://bulbapedia.bulbagarden.net/wiki/Dragonite_(Pok%C3%A9mon)"));

		driver.quit();
	}

	@Test
	public void test04() {
		System.setProperty("webdriver.chrome.driver", "src/driver/chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		Actions actions = new Actions(driver);
		driver.manage().window().maximize();
		driver.get("https://bulbapedia.bulbagarden.net/wiki/Main_Page");
		WebElement hover = driver.findElement(By.id("ca-lang"));
		actions.moveToElement(hover).perform();

		Assertions.assertTrue(hover.isDisplayed());

		driver.quit();
	}

	@Test	
	public void test05() {
		System.setProperty("webdriver.chrome.driver", "src/driver/chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://bulbapedia.bulbagarden.net/wiki/Main_Page");
		
		
		Assertions.assertFalse(driver.getCurrentUrl().contains("https://www.globo.com"));
		
		driver.quit();
	}
	@Test
    public void test06() {
		System.setProperty("webdriver.chrome.driver", "src/driver/chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
        driver.get("https://sitetestes.vercel.app/");

        Select dropdown = new Select(driver.findElement(By.id("$0")));

        // Seleciona a opção pelo índice (índice começa a partir de 0)
        dropdown.selectByIndex(2); // Este exemplo seleciona a terceira opção

		driver.quit();
    }

}
