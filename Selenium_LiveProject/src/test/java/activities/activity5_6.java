package activities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class activity5_6 {
    WebDriver driver;
    String url, page_title, userName, password;

    public static  By navigationBar = By.cssSelector(".main-navigation");
    public static  By myAccountLink = By.xpath("//a[text() = 'My Account']");
    public static  By loginButton = By.xpath("//a[text() = 'Login']");
    public static  By userNameTextBox = By.id("user_login");
    public static  By passwordTextBox = By.id("user_pass");
    public static  By submitButton = By.id("wp-submit");
    public static  By LoggedInUsr = By.cssSelector("#wp-admin-bar-my-account span.display-name");
    public static  By profileCardImg = By.cssSelector(".ld-profile-card");

    @BeforeClass
    public void setup() {
        //install the driver for firefox
        WebDriverManager.firefoxdriver().setup();

        //initialisation of firefox driver
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "NULL");
        driver = new FirefoxDriver();
        url = "https://alchemy.hguy.co/lms";
        page_title = "My Account – Alchemy LMS";
        userName = "root";
        password = "pa$$w0rd";

    }

    @Test(priority=1)
    public void verifyNavigationToLandingPage() throws Exception {
        //navigate to
        driver.navigate().to(url);
        //maximising the page
        driver.manage().window().maximize();
        Thread.sleep(2000);
        WebElement navigationBarEle = driver.findElement(navigationBar);
        Assert.assertEquals(navigationBarEle.isDisplayed(), true);

        List<WebElement> lisOfMenusInNavBar = driver.findElements(By.cssSelector(".main-navigation a"));
        String [] expectedMenusInNavBar = new String[] {"Home", "All Courses", "About", "Contact", "My Account"};

        for(int i=0; i < expectedMenusInNavBar.length; i++) {
            String menu = lisOfMenusInNavBar.get(i).getText();
            Assert.assertEquals(menu, expectedMenusInNavBar[i]);
        }
    }

    @Test(priority=2)
    public void verifyMyAccountPage() throws Exception {
        //Select the menu item that says “My Account” and click it.
        WebElement myAccountLinkEle = driver.findElement(myAccountLink);
        myAccountLinkEle.click();
        Thread.sleep(3000);

        String pageTitle = driver.getTitle();
        Assert.assertEquals(pageTitle, page_title);

        // Print the title of the page
        System.out.println("Home page title: " + driver.getTitle());
    }

    @Test(priority=3)
    public void verifyLogin() throws Exception {
        //Find the “Login” button on the page and click it
        WebElement loginButtonEle = driver.findElement(loginButton);
        Assert.assertEquals(loginButtonEle.isDisplayed(), true);
        loginButtonEle.click();

        //Find the username field of the login form and enter the username into that field.
        driver.findElement(userNameTextBox).sendKeys(userName);

        //Find the password field of the login form and enter the password into that field
        driver.findElement(passwordTextBox).sendKeys(password);

        //Find the submit button and click it
        driver.findElement(submitButton).click();

        Thread.sleep(2000);

        WebElement loggedInUserName = driver.findElement(LoggedInUsr);
        String userDisplayed = loggedInUserName.getText();
        Assert.assertEquals(userDisplayed, userName);

        WebElement profileCard = driver.findElement(profileCardImg);
        Assert.assertEquals(profileCard.isDisplayed(), true);
    }

    @AfterClass
    public void tearDown() {
        //closing the browser
        driver.quit();
    }

}


