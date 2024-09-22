import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Main {
	
	List<WebElement> players;
	WebDriver driver;
	
    public static void main(String[] args) {
    	System.out.println("set driver");
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Maurispi\\Documents\\GitHub\\GRONDOMASTER-DESKTOP\\LigaMasterr\\chromedriver.exe");
//		options = new ChromeOptions();
    	System.out.println("create driver");
    	WebDriverManager.chromedriver().setup();
    	WebDriver driver = new ChromeDriver();
    	System.out.println("get driver");
    	driver.manage().window().maximize();
        // Open the Sofifa page with player data
        driver.get("https://sofifa.com/players?keyword=vini&hl=es-ES");
    	System.out.println("get url");
        // Wait for the page to load (could be replaced by WebDriverWait for more control)
        try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  // wait 5 seconds for the page to load

        // Find the elements containing player names (inspect the page for proper selectors)
    	List<WebElement> players = driver.findElements(By.xpath("//td[@data-col='oa']"));
        System.out.println("get jugadores");
        // Print each player's name
            for (WebElement player : players) {
            	System.out.println(player.getText());
            }
 
            // Close the browser window
        driver.quit();
    }
}