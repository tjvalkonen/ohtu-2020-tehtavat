package ohtu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import java.util.Random;

public class Tester {

    public static void main(String[] args) {
        // ChromeOptions options = new ChromeOptions();
        // options.setBinary("C://Program Files (x86)/Google/Chrome/Application");

        // WebDriver driver = new RemoteWebDriver("http://localhost:9515", DesiredCapabilities.chrome());
        // driver.get("http://www.google.com");

        WebDriver driver = new HtmlUnitDriver();
        driver.get("http://localhost:4567");
        
        // tulostetaan sivu konsoliin
        System.out.println(driver.getPageSource());

        // WebDriver driver = new ChromeDriver();
        // driver.get("http://localhost:4567");
        
        sleep(2);

        /*
        // Login
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();
        */

        // New user
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();

        sleep(2);


        // Toimiva pekka
        /* 
        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));
        */

        // Väärä pekka salasana
        /* 
        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("väärä");
        element = driver.findElement(By.name("login"));
        */

        // uusi käyttäjätunnus

        Random r = new Random();
    
        element = driver.findElement(By.name("username"));
        element.sendKeys("uusi"+r.nextInt(100000));

        element = driver.findElement(By.name("password"));
        element.sendKeys("salasana");

        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("salasana");

        element = driver.findElement(By.name("signup"));


        sleep(2);
        element.submit();

        sleep(3);

        // tulostetaan sivu konsoliin
        System.out.println(driver.getPageSource());
        
        // Uloskirjautuminen
        // sleep(2);
        element = driver.findElement(By.linkText("continue to application mainpage"));
        sleep(1);
        element.click();
        
        sleep(2);
        element = driver.findElement(By.linkText("logout"));
        element.click();

        System.out.println(driver.getPageSource());

        driver.quit();
    }
    
    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }
}
