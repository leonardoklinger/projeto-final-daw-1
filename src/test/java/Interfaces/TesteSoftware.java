package Interfaces;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class TesteSoftware {
    private WebDriver driver;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @After
    public void fecha(){
        driver.quit();
    }

    @Test
    public void login(){
        driver.get("http://localhost:8080/TrabalhoTomCat_war/");
        driver.findElement(By.linkText("Entrar")).click();
        driver.findElement(By.name("input-usuario")).click();
        driver.findElement(By.name("input-usuario")).sendKeys("admin");
        driver.findElement(By.name("input-senha")).click();
        driver.findElement(By.name("input-senha")).sendKeys("123");
        driver.findElement(By.cssSelector("input:nth-child(1)")).click();
    }

    @Test
    public void listaLeiloes() {
        driver.get("http://localhost:8080/TrabalhoTomCat_war/");
        driver.findElement(By.linkText("Entrar")).click();
        driver.findElement(By.name("input-usuario")).click();
        driver.findElement(By.name("input-usuario")).sendKeys("admin");
        driver.findElement(By.name("input-senha")).click();
        driver.findElement(By.name("input-senha")).sendKeys("123");
        driver.findElement(By.cssSelector("input:nth-child(1)")).click();
        driver.findElement(By.linkText("Leilões")).click();
    }

    @Test
    public void cadastroLeilao() {
        driver.get("http://localhost:8080/TrabalhoTomCat_war/");
        driver.findElement(By.linkText("Entrar")).click();
        driver.findElement(By.name("input-usuario")).click();
        driver.findElement(By.name("input-usuario")).sendKeys("admin");
        driver.findElement(By.name("input-senha")).click();
        driver.findElement(By.name("input-senha")).sendKeys("123");
        driver.findElement(By.cssSelector("input:nth-child(1)")).click();
        driver.findElement(By.linkText("Cadastro")).click();
        driver.findElement(By.name("input-name")).click();
        driver.findElement(By.name("input-name")).sendKeys("PS5");
        driver.findElement(By.name("input-valor")).sendKeys("1000");
        driver.findElement(By.name("input-data")).click();
        driver.findElement(By.name("input-data")).sendKeys("2021-09-01");
        driver.findElement(By.cssSelector(".button")).click();
    }

    @Test
    public void filtrar() {
        driver.get("http://localhost:8080/TrabalhoTomCat_war/entrada");
        driver.findElement(By.name("input-usuario")).click();
        driver.findElement(By.name("input-usuario")).sendKeys("admin");
        driver.findElement(By.name("input-senha")).click();
        driver.findElement(By.name("input-senha")).sendKeys("123");
        driver.findElement(By.cssSelector("input:nth-child(1)")).click();
        driver.findElement(By.cssSelector(".fa-box")).click();
        driver.findElement(By.name("StatusBox")).click();
        {
            WebElement dropdown = driver.findElement(By.name("StatusBox"));
            dropdown.findElement(By.xpath("//option[. = 'EXPIRADO']")).click();
        }
        driver.findElement(By.cssSelector(".button")).click();
    }

    @Test
    public void editarLeilao() {
        driver.get("http://localhost:8080/TrabalhoTomCat_war/");
        driver.findElement(By.cssSelector(".fa-sign-in-alt")).click();
        driver.findElement(By.name("input-usuario")).click();
        driver.findElement(By.name("input-usuario")).sendKeys("admin");
        driver.findElement(By.name("input-senha")).click();
        driver.findElement(By.name("input-senha")).sendKeys("123");
        driver.findElement(By.cssSelector("input:nth-child(1)")).click();
        driver.findElement(By.linkText("Cadastro")).click();
        driver.findElement(By.name("input-name")).click();
        driver.findElement(By.name("input-name")).sendKeys("PS5");
        driver.findElement(By.name("input-valor")).sendKeys("1000");
        driver.findElement(By.name("input-data")).click();
        driver.findElement(By.name("input-data")).click();
        driver.findElement(By.name("input-data")).sendKeys("2021-09-03");
        driver.findElement(By.cssSelector(".button")).click();
        driver.findElement(By.linkText("Leilões")).click();
        driver.findElement(By.id("editar")).click();
        {
            WebElement element = driver.findElement(By.cssSelector("html"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).clickAndHold().perform();
        }
        {
            WebElement element = driver.findElement(By.cssSelector("html"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        {
            WebElement element = driver.findElement(By.cssSelector("html"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).release().perform();
        }
        driver.findElement(By.cssSelector("html")).click();
        driver.findElement(By.name("status")).click();
        driver.findElement(By.name("status")).click();
        driver.findElement(By.name("input-name")).click();
        driver.findElement(By.cssSelector(".button")).click();
    }

    @Test
    public void excluirLeilao() {
        driver.get("http://localhost:8080/TrabalhoTomCat_war/");
        driver.findElement(By.linkText("Entrar")).click();
        driver.findElement(By.cssSelector("html")).click();
        driver.findElement(By.name("input-usuario")).click();
        driver.findElement(By.name("input-usuario")).sendKeys("admin");
        driver.findElement(By.name("input-senha")).click();
        driver.findElement(By.name("input-senha")).sendKeys("123");
        driver.findElement(By.name("input-name")).sendKeys("PC");
        driver.findElement(By.name("input-valor")).sendKeys("1000");
        driver.findElement(By.name("input-data")).click();
        driver.findElement(By.name("input-data")).click();
        driver.findElement(By.name("input-data")).sendKeys("2021-09-03");
        driver.findElement(By.cssSelector(".button")).click();
        driver.findElement(By.linkText("Leilões")).click();
        driver.findElement(By.cssSelector("tr:nth-child(4) #excluir")).click();
    }

    @Test
    public void darlance() {
        driver.get("http://localhost:8080/TrabalhoTomCat_war/");
        driver.findElement(By.linkText("Entrar")).click();
        driver.findElement(By.name("input-usuario")).click();
        driver.findElement(By.name("input-usuario")).sendKeys("admin");
        driver.findElement(By.name("input-senha")).click();
        driver.findElement(By.name("input-senha")).sendKeys("123");
        driver.findElement(By.cssSelector("input:nth-child(1)")).click();
        driver.findElement(By.name("input-name")).click();
        driver.findElement(By.name("input-name")).sendKeys("PS7");
        driver.findElement(By.name("input-valor")).sendKeys("1000");
        driver.findElement(By.name("input-data")).click();
        driver.findElement(By.name("input-data")).sendKeys("2021-09-02");
        driver.findElement(By.cssSelector(".button")).click();
        driver.findElement(By.linkText("Leilões")).click();
        driver.findElement(By.cssSelector("tr:nth-child(4) #editar")).click();
        driver.findElement(By.name("status")).click();
        {
            WebElement dropdown = driver.findElement(By.name("status"));
            dropdown.findElement(By.xpath("//option[. = 'ABERTO']")).click();
        }
        driver.findElement(By.cssSelector("html")).click();
        driver.findElement(By.cssSelector(".button")).click();
        driver.findElement(By.linkText("Lances")).click();
        driver.findElement(By.id("editar")).click();
    }

    @Test
    public void excluirLance() {
        driver.get("http://localhost:8080/TrabalhoTomCat_war/");
        driver.findElement(By.linkText("Entrar")).click();
        driver.findElement(By.name("input-usuario")).click();
        driver.findElement(By.name("input-usuario")).sendKeys("admin");
        driver.findElement(By.name("input-senha")).click();
        driver.findElement(By.name("input-senha")).sendKeys("123");
        driver.findElement(By.cssSelector("input:nth-child(1)")).click();
        driver.findElement(By.linkText("Lances")).click();
        driver.findElement(By.id("excluir")).click();
    }

    @Test
    public void logout() {
        driver.get("http://localhost:8080/TrabalhoTomCat_war/");
        driver.findElement(By.linkText("Entrar")).click();
        driver.findElement(By.name("input-usuario")).click();
        driver.findElement(By.name("input-usuario")).sendKeys("admin");
        driver.findElement(By.name("input-senha")).click();
        driver.findElement(By.name("input-senha")).sendKeys("123");
        driver.findElement(By.cssSelector("input:nth-child(1)")).click();
        driver.findElement(By.linkText("Logout")).click();
    }
    @Test
    public void register() {
        driver.get("http://localhost:8080/TrabalhoTomCat_war/");
        driver.findElement(By.linkText("Registrar")).click();
        driver.findElement(By.name("input-nome")).click();
        driver.findElement(By.cssSelector(".container")).click();
        driver.findElement(By.name("input-nome")).click();
        driver.findElement(By.name("input-nome")).sendKeys("TesteUser");
        driver.findElement(By.name("input-usuario")).sendKeys("TesteUser");
        driver.findElement(By.name("input-email")).sendKeys("TesteUser@gmail.com");
        driver.findElement(By.name("input-senha")).sendKeys("123");
        driver.findElement(By.cssSelector("input:nth-child(1)")).click();
    }
}