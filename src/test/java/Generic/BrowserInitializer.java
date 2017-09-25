package Generic;

public interface BrowserInitializer {

	String Mozila_Key="webdriver.gecko.driver";
	String Mozila_Value=".//src/test/resources/browserDrivers/geckodriver.exe";
	
	String Chrome_Key="webdriver.chrome.driver";
	String Chrome_Value=".//src/test/resources/browserDrivers/chromedriver.exe";
	
	String IE_Key="webdriver.ie.driver";
	String IE_Value=".//src/test/resources/browserDrivers/IEDriverServer.exe";
}