package table;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class MainClass {
    public static void main(String[] args) {
        System.setProperty("webdriver.gecko.driver", "/home/barabas/Projects/testgroup/driver/geckodriver");
        WebDriver driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get("https://www.w3schools.com/html/html_tables.asp");

         WebElement tabElement = driver.findElement(By.xpath("//*[@id=\"customers\"]"));

         Table table = new Table(tabElement,driver);
        System.out.println("rows number is" + " " +  table.getRows().size());
        System.out.println(table.getValueFromCell(2,3));
        System.out.println(table.getValueFromCell(4,1));
        System.out.println(table.getValueFromCell(4,"Company"));
        System.out.println(table.getValueFromCell(1,"Country"));
        System.out.println(table.getValueFromCell(2,"Contact"));


        driver.quit();
    }

}
