package activities;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class activity6 {
    public static void main(String[] args) throws InterruptedException {

        //install the driver for firefox
        WebDriverManager.chromedriver().setup();

        //initialisation of firefox driver
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "NULL");

        WebDriver driver = new ChromeDriver();

        //navigate to
        driver.get("https://demoqa.com/login");

        //maximising the page
        driver.manage().window().maximize();

        //interactions
        Thread.sleep(5000);

        //Select the menu item that says “My Account” and click it.
        driver.findElement(By.xpath("//a[text() = 'My Account']")).click();


        // Print the title of the page
        System.out.println("Home page title: " + driver.getTitle());


        //Find the “Login” button on the page and click it
        WebElement loginButton = driver.findElement(By.xpath("//a[text() = 'Login']"));
        System.out.println("Element present having text:" + loginButton.getText());
        System.out.println("Element present :" + loginButton.isDisplayed());
        loginButton.click();


        //closing the browser
        driver.quit(); //closes the window




    }
}
