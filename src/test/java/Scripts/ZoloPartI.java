package Scripts;

import org.testng.annotations.Test;
import Generic.BrowserLauncher;
import Generic.ExcelReader;
import Generic.CaptureScreen;
import org.testng.annotations.BeforeTest;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
//import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;

public class ZoloPartI {

	WebDriver Driver;

	@BeforeTest
	public void beforeTest() {
		BrowserLauncher bl = new BrowserLauncher();
		Driver = bl.bLauncher("mozila", "http://52.201.90.154:9002/");
	}

	@Test(priority=1)
	public void tp1() throws Exception {

		// WebDriverWait wait = new WebDriverWait(Driver, 30);
		CaptureScreen cp = new CaptureScreen();

		/* Part 1 | Scenario : 1 */
		// Open "http://52.201.90.154:9002/" >>
		// a. Log into the website with provided credentials
		// b. Select search field >>
		// c. Type "Electronic" >>
		// d. Then select "Electronic City Phase II, Bengaluru, Karnataka, India" from
		// auto suggest option

		Driver.findElement(By.xpath(".//*[@id='navbar']/ul/li[3]/a")).click();

		Driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

		ExcelReader er = new ExcelReader(".//src/test/resources/dataLibrary/ExcelData.xlsx", 0);
		Driver.findElement(By.xpath(".//*[@id='signin']/div/div[2]/div[2]/form/div[1]/input"))
				.sendKeys((er.eReader(1, 0)).getRawValue());
		Driver.findElement(By.xpath(".//*[@id='signin']/div/div[2]/div[2]/form/div[2]/input"))
				.sendKeys((er.eReader(1, 1)).getRawValue());

		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='signin']/div/div[2]/div[2]/form/div[4]/div/input")));
		// Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Thread.sleep(5000);

		Driver.findElement(By.cssSelector(".btn-group-justified")).click();
		Thread.sleep(5000);

		Driver.findElement(By.xpath(".//*[@id='searchBar']")).sendKeys("Electronic");
		Thread.sleep(3000);

		Driver.findElement(By.xpath("html/body/ul/li[3]/div")).click();

		/* Part 1 | Scenario : 2 */
		// Verify the search result >>
		// a. Apply filters: ('Budget: 5000-8000','Sharing Preference: 2 Sharing','PG
		// Type: Men') >>
		// b. Select "Zolo Goodfellas for men" >>
		// c. Open property details >>
		// d. Verify the items on the page.

		Select dropdown1 = new Select(
				Driver.findElement(By.xpath(".//*[@id='content']/div/div[1]/div/form/div/div[1]/select")));
		dropdown1.selectByVisibleText("5000 - 8000");

		Select dropdown2 = new Select(
				Driver.findElement(By.xpath(".//*[@id='content']/div/div[1]/div/form/div/div[2]/select")));
		dropdown2.selectByVisibleText("2 Sharing");

		Select dropdown3 = new Select(Driver.findElement(By.xpath(".//*[@id='gender-drpdwn']")));
		dropdown3.selectByVisibleText("Men");
		Thread.sleep(2000);

		Driver.findElement(By.cssSelector(".btn.btn-primary.handleFilter")).click();
		Thread.sleep(2000);

		Driver.findElement(By.xpath(".//*[text()='Zolo Goodfellas for Men']")).click();

		/* Part 1 | Scenario : 3 */
		// Complete schedule a visit flow >>
		// a. Click on Schedule a Visit button >>
		// b. Select future date and time >>
		// c. Get confirmation of scheduled visit

		Driver.findElement(By.xpath(".//*[@ng-href='visits/new/0?centerId=583849856193f53870e51342']")).click();
		Driver.findElement(By.xpath(".//*[@name='visitDate']")).clear();
		Driver.findElement(By.xpath(".//*[@name='visitDate']")).sendKeys("2017-09-30");

		Select dropdown4 = new Select(Driver.findElement(By.xpath(".//*[@name='visitTime']")));
		dropdown4.selectByVisibleText("12 pm - 1 pm");

		Driver.findElement(By.xpath(".//*[text()='Schedule aVisit']")).click();
		Thread.sleep(1000);

		cp.capture(Driver, "Part I\\ScheduleVisit");
	}

	@AfterTest
	public void afterTest() {
		Driver.quit();
	}
}