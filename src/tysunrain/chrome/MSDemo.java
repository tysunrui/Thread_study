package tysunrain.chrome;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

/**
 * @ClassName: MSDemo
 * @Description: TODO
 * @Author: Administrator
 * @Date: 2020/2/25 0025
 * @Version: 1.0
 **/
public class MSDemo {

    public static void main(String[] args) {
        //System.setProperty("webdriver.chrome.driver","C:\\Program Files\\chromedriver\\chromedriver.exe");
        System.setProperty("webdriver.chrome.driver","C:\\Program Files\\chromedriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://item.jd.com/100011521400.html");
        final WebElement webElement = driver.findElement(By.linkText("+"));
        Runnable runnable  = new Runnable() {
            @Override
            public void run() {
                while(true){
                    webElement.click();
                }
            }
        };
        for (int i = 0; i < 10; i++) {
            new Thread(runnable).start();
        }
    }
}
