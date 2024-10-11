package common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import driver.DriverManager;
import keywords.WebUI;

public class BaseTest {

//    public WebDriver driver;

//    //Khởi tạo browser
//    @BeforeMethod
//    public static void createDriver(){
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
//    }

//    @BeforeTest
    public static void setupBrowser(@Optional("chrome") String browserName) {
        WebDriver driver;
        switch (browserName.trim().toLowerCase()) {
            case "chrome":
                driver = initChromeDriver();
                break;
            case "firefox":
                driver = initFirefoxDriver();
                break;
            case "edge":
                driver = initEdgeDriver();
                break;
            default:
                System.out.println(browserName + " invalid. Launching default browser: Chrome");
                driver = initChromeDriver();
        }

        System.out.println("11144: " + driver);
        DriverManager.setDriver(driver);

    }

    public static WebDriver initChromeDriver() {
        WebDriver driver;
        System.out.println("Launching Chrome browser...");
        System.out.println("52: " + WebUI.notificationsBlock());
        driver = new ChromeDriver(WebUI.notificationsBlock());
        driver.manage().window().maximize();
        return driver;
    }

    private static WebDriver initFirefoxDriver() {
        WebDriver driver;
        System.out.println("Launching Firefox browser...");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        return driver;
    }

    private static WebDriver initEdgeDriver() {
        WebDriver driver;
        System.out.println("Launching Edge browser...");
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        return driver;
    }

    //Đóng browser
//    @AfterMethod
    public static void closeDriver() {
        sleep(10);
        System.out.println("Closing browser...");
        System.out.println("Debug 80: " + DriverManager.getDriver());
        DriverManager.quit();
    }

    public static void sleep(double seconds) {
        try {
            Thread.sleep((long) (1000 * seconds));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
