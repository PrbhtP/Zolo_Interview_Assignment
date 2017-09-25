package Scripts;

import org.testng.annotations.Test;
import Generic.BrowserLauncher;
import Generic.CaptureScreen;
import Generic.ExcelReader;
import org.testng.annotations.BeforeTest;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;

public class ZoloPartII {

	WebDriver Driver;

	@BeforeTest
	public void beforeTest() {
		BrowserLauncher bl = new BrowserLauncher();
		Driver = bl.bLauncher("mozila", "http://52.201.90.154:9002/");
	}

	@Test(priority = 2)
	public void tp2() throws Exception {

		// WebDriverWait wait1 = new WebDriverWait(Driver, 30);
		CaptureScreen cp1 = new CaptureScreen();

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
		Thread.sleep(3000);
		/* Part 2 | Scenario : 1 */
		// Go to your shortlisted property >>
		// a. Click 'Request a Bed' >>
		// b. Select any future date from calendar >>
		// c. Select 2 sharing room >>
		// d. Proceed to join waitlist/confirmed booking

		Driver.findElement(By.xpath(".//*[@class='btn btn-blue btn-block'][@ng-click='bookPg(selectedPg.id)']"))
				.click();
		Thread.sleep(3000);

		Driver.findElement(By.xpath(".//*[@ng-model='booking.date']")).clear();
		Driver.findElement(By.xpath(".//*[@ng-model='booking.date']")).sendKeys("2017-09-30");

		Driver.findElement(By.xpath(".//*[text()='2 Sharing']")).click();
		cp1.capture(Driver, "Part II\\RequestBed");
		Thread.sleep(3000);
		Driver.findElement(By.xpath(".//*[text()='Proceed to Pay']")).click();

		/* Part 2 | Scenario : 2 */
		// On Payment page >>
		// a. Verify 'Name','Phone' >>
		// b. Check for the promo code option >>
		// c. Enter promo code and apply for code >>
		// d. Proceed to make payment >>
		// e. Choose payment and complete payment

		Thread.sleep(1000);
		cp1.capture(Driver, "Part II\\PaymentPage");

		Driver.findElement(
				By.xpath(".//*[@id='content']/div/div/div/div[6]/div/div/div/div[3]/div[7]/form/div[1]/label/input"))
				.click();

		Driver.findElement(
				By.xpath(".//*[@id='content']/div/div/div/div[6]/div/div/div/div[3]/div[7]/form/div[2]/input"))
				.sendKeys("ZOLO17");

		Driver.findElement(By.xpath(".//*[@id='content']/div/div/div/div[6]/div/div/div/div[3]/div[7]/form/div[3]"))
				.click();
		cp1.capture(Driver, "Part II\\InvalidPromo");

		Driver.findElement(By.xpath(".//*[text()='Make Payment']")).click();
		Thread.sleep(1000);

		cp1.capture(Driver, "Part II\\PaymentMethod");

		/* Part 2 | Scenario : 3 */
		// Verify that on success page Return home' link is available

		// *****NOTE : As we didn't complete the payment in the above section, so the
		// mentioned scenario can't be verified here.

		/* Part 2 | Scenario : 4 */
		// Go to Menu options >>
		// a. Select Booking >>
		// b. Verify the booking details

		// *****NOTE : As we didn't complete the payment in the above section, so the
		// below codes are not applicable

		/*
		 * Select dropdown4 = new Select(Driver.findElement(By.xpath(
		 * ".//*[@id='header']/div[3]/a[2]/div[1]/span[1]")));
		 * dropdown4.selectByVisibleText("Bookings"); cp1.capture(Driver,
		 * "BookingConfirm");
		 */
	}

	@AfterTest
	public void afterTest() {
		Driver.quit();
	}
}