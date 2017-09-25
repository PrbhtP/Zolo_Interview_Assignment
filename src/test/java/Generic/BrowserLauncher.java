package Generic;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BrowserLauncher implements BrowserInitializer{

	WebDriver Driver;
	
	public WebDriver bLauncher(String bName, String tURL) {
		
		if(bName.equalsIgnoreCase("mozila")) {
			System.setProperty(Mozila_Key, Mozila_Value);
			Driver = new FirefoxDriver();
			Driver.navigate().to(tURL);
			//Driver.manage().window().maximize();
		}
		
		else if(bName.equalsIgnoreCase("chrome")) {
			System.setProperty(Chrome_Key, Chrome_Value);
			Driver = new ChromeDriver();
			Driver.navigate().to(tURL);
			Driver.manage().window().maximize();
		}
		
		else if(bName.equalsIgnoreCase("ie")) {
			System.setProperty(IE_Key, IE_Value);
			Driver = new InternetExplorerDriver();
			Driver.navigate().to(tURL);
			Driver.manage().window().maximize();
		}
		
		return Driver;	
	}
	
}
