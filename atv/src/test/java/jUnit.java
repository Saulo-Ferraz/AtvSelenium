import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;


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

    @Test
    public void testAcessaAbaHistoryEVerificaNavegacaoParaMainPage() {
        driver.get("https://bulbapedia.bulbagarden.net/wiki/Main_Page");

        WebElement elementoHistory = driver.findElement(By.id("ca-history"));
        elementoHistory.click();

        WebElement elementoLI = driver.findElement(By.id("n-mainpage"));
        String textoDoLI = elementoLI.getText();

        assertEquals("Main Page", textoDoLI);
    }

    @Test
    public void testValidaAbaDiscordComLinkCorreto() {
        driver.get("https://bulbapedia.bulbagarden.net/wiki/Main_Page");

        WebElement elementoDiscord = driver.findElement(By.cssSelector("ul.bg-global-nav-primary > li:nth-child(4)"));
        String textoDiscord = elementoDiscord.getText();

        WebElement elementoLinkDiscord = driver
                .findElement(By.cssSelector("ul.bg-global-nav-primary > li:nth-child(4) a"));
        String valorHref = elementoLinkDiscord.getAttribute("href");

        assertEquals("Discord", textoDiscord);
        assertTrue(valorHref.contains("https://discord.gg"));
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testBotaoLoginNaoPresente() {
        driver.get("https://bulbapedia.bulbagarden.net/wiki/Main_Page");

        assertFalse(driver.findElements(By.id("botao-login")).size() > 0,
                "O botão de login não deve estar presente na página");
    }

    @Test
    public void testRedirecionarAoSelecionarIdiomaEspanhol() {
        driver.get("https://bulbapedia.bulbagarden.net/wiki/Main_Page");
        WebElement redicionarParaEspanhol = driver.findElement(By.xpath("//td[contains(text(), 'Spanish') and .//a[@class='extiw']]"));
        WebElement elementoLinkEspanhol =  redicionarParaEspanhol.findElement(By.xpath(".//a[@class='extiw']"));
        String urlEspanhol = elementoLinkEspanhol.getAttribute("href");

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elementoLinkEspanhol);
        elementoLinkEspanhol.click();
        String tituloPaginaAtual = driver.getTitle();

        assertEquals(urlEspanhol, "https://www.wikidex.net/wiki/WikiDex");
        assertEquals(tituloPaginaAtual, "WikiDex, la enciclopedia Pokémon");
    }
}
