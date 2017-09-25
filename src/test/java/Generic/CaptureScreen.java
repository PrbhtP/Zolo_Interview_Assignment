package Generic;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class CaptureScreen {
	
	public String capture(WebDriver Driver, String scName) {
		try {
			TakesScreenshot ts = (TakesScreenshot)Driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			String dest = ".\\ScreenShots\\" + scName + ".png";
			File destination = new File(dest);
			FileUtils.copyFile(source, destination);
			return dest;
			
		} catch (Exception e) {
			return "No Screenshot found";
		}
	}
}
