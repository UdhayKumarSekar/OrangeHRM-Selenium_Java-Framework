package com.orangehrm.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import com.orangehrm.actiondriver.Actiondriver;
import com.orangehrm.utilities.ExtentManager;
import com.orangehrm.utilities.LoggerManagerOHRM;

public class BaseClass {

//	protected static WebDriver driver;
	protected static Properties prop;
	protected FileInputStream fis;

//	action driver class object for keeping singleton pattern
//	private static Actiondriver actiondriver;

	/*
	 * We use ThreadLocal<WebDriver> in Selenium automation frameworks to ensure
	 * thread safety when running tests in parallel.
	 */
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	private static ThreadLocal<Actiondriver> actiondriver = new ThreadLocal<>();
	public static final Logger logger = LoggerManagerOHRM.getLogger(BaseClass.class);

//	Object for SoftAssertions
	protected ThreadLocal<SoftAssert> softassert = ThreadLocal.withInitial(SoftAssert::new);

//	getter method for SoftAssertions
	public SoftAssert getSoftAssert() {
		return softassert.get();
	}

//	Method for reading the config file
	@BeforeSuite
	public void loadconfig() {

		prop = new Properties();

//		using FileInputStream read the config file
		try {
			fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/config.properties");
			prop.load(fis);
			logger.info(
					"********* config.properties File was recieved through FileInputStream and loaded successfully *********");

		} catch (IOException e) {
			System.out.println("File Not exist: " + e.getMessage());
		}

//	Calling the Extent Report Generating method
//		ExtentManager.getReport();		--> this method is Implemented from ITestListener class so we dont need here

	}

	@BeforeMethod
	public void Setup() throws IOException {

//		printing the name of the class
		System.out.println("WebDriver Setup for : " + this.getClass().getSimpleName());
		browserLaunch();
		applicationLaunch();
		staticwait(2);
		logger.info("****** WebDriver Initiated and Browser was maximized ******");
		logger.trace("*** This is Trace message ***");
		logger.error("*** This is error message ***");
		logger.debug("*** This is debug Message ***");
		logger.fatal("*** This is fatal Message ***");
		logger.warn("*** This is warn Message ***");

		/*
		 * // Initialize the action Driver only Once if(actiondriver==null) {
		 * actiondriver = new Actiondriver(driver);
		 * System.out.println("Action driver class instance is created"+
		 * Thread.currentThread().getId());
		 * logger.info("****** Action driver instance is created ******"); }
		 */

//		Initialize ActionDriver for the current Thread using set method
		actiondriver.set(new Actiondriver(getDriver()));
		logger.info("****** Action driver initialized for Thread ******" + Thread.currentThread().getId());
	}

	private void browserLaunch() {
		String br = prop.getProperty("browser");

		if (br.equalsIgnoreCase("chrome")) {
//			driver = new ChromeDriver();	#modified after used ThreadLocal as follows

//	Chrome options used for handling the browser customization
			ChromeOptions options = new ChromeOptions();

//		Run without launching Browser(headless mode)		
			options.addArguments("--headless");
//		Disable GPU for headless mode
			options.addArguments("--disable-gpu");
//		Set window size
			options.addArguments("--window-size=1920,1080");
//		Disable browser notifications	
			options.addArguments("--disable-notifications");
//		Required for some CI/CD  environments
			options.addArguments("--no-sandbox");
//		Resolves the issues in resources
			options.addArguments("--disable-dev-shm-usage");

			driver.set(new ChromeDriver());

//	register the driver for extent manager	
			ExtentManager.registerDriver(getDriver());

			logger.info("****** ChromeDriver Instance is created ******");

		} else if (br.equalsIgnoreCase("edge")) {
//			driver = new EdgeDriver();		#modified after used ThreadLocal as follows

			EdgeOptions options = new EdgeOptions();

//		Run without launching Browser(headless mode)		
			options.addArguments("--headless");
//		Disable GPU for headless mode
			options.addArguments("--disable-gpu");
//		Set window size
//			options.addArguments("--window-size=1920,1080");
			options.addArguments("--width=1920");
			options.addArguments("--height=1080");
//		Disable browser notifications	
			options.addArguments("--disable-notifications");
//		Required for some CI/CD  environments
			options.addArguments("--no-sandbox");
//		Resolves the issues in resources
			options.addArguments("--disable-dev-shm-usage");
			driver.set(new EdgeDriver());

//			register the driver for extent manager	
			ExtentManager.registerDriver(getDriver());
			logger.info("****** EdgeDriver Instance is created ******");

		} else if (br.equalsIgnoreCase("firefox")) {
//			driver = new FirefoxDriver();	#modified after used ThreadLocal as follows

			FirefoxOptions options = new FirefoxOptions();

//		Run without launching Browser(headless mode)		
			options.addArguments("--headless");
//		Disable GPU for headless mode
			options.addArguments("--disable-gpu");
//		Set window size
			options.addArguments("--window-size=1920,1080");
//		Disable browser notifications	
			options.addArguments("--disable-notifications");
//		Required for some CI/CD  environments
			options.addArguments("--no-sandbox");
//		Resolves the issues in resources
			options.addArguments("--disable-dev-shm-usage");

			driver.set(new FirefoxDriver());

//			register the driver for extent manager	
			ExtentManager.registerDriver(getDriver());
			logger.info("****** FirefoxDriver Instance is created ******");
			
		} else {

			throw new IllegalArgumentException("Invalid Browser Name : " + br);
//			System.out.println("Invalid Browser Name: "+ br);
		}

	}

	private void applicationLaunch() {

//		using Implicitly wait 
		int wait = Integer.parseInt(prop.getProperty("Iwait_time"));
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(wait));

//		After using ThreadLocal 
//		driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(wait));
//	also we can use as follows	
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(wait));

//		Maximizing the window
//		driver.manage().window().maximize();

//		After using ThreadLocal 
//		driver.get().manage().window().maximize();

//		Another type of using getDriver method
		getDriver().manage().window().maximize();

//		catching url from config file and navigate to the application through url

		try {
			String app_url = prop.getProperty("url");
//			driver.get(app_url);			//		After using ThreadLocal 
			getDriver().get(app_url);
		} catch (Exception e) {
			System.out.println("Failed to find and navigate to the url: " + e.getMessage());
		}
	}

	@AfterMethod
	public void tearDown() {

//		if (driver != null) {				//		After using ThreadLocal 
		if (getDriver() != null) {
			try {
//				driver.quit();				//		After using ThreadLocal 
				getDriver().quit();
			} catch (Exception e) {
				System.out.println("Unable to quit the browser: " + e.getMessage());
			}
		}

		logger.info("****** WebDriver was Closed ******");

//		driver = null;
//		actiondriver = null;
		driver.remove();
		actiondriver.remove();

//		ExtentManager.endTest();	--> This method implemented in ITestListener Class

	}

//	Getter Method for Property Class Object prop
	public static Properties getProp() {
		return prop;
	}

	/*
	 * // Getter Method created before singleton design pattern implemented //
	 * Driver getter method public WebDriver getDriver() { return driver; }
	 * 
	 */
//	Getter Method created after singleton design pattern implemented
//	Getter method for WebDriver
	public static WebDriver getDriver() {
		if (driver.get() == null) {
			System.out.println("WebDriver is not Initialized..");
			throw new IllegalStateException("WebDriver is not Initialized..");
		}

		return driver.get();
	}

//	Getter Method created after singleton design pattern implemented
//	Getter method for WebDriver
	public static Actiondriver getActionDriver() {
		if (actiondriver.get() == null) {
			System.out.println("WebDriver is not Initialized..");
			throw new IllegalStateException("WebDriver is not Initialized..");
		}

		return actiondriver.get();
	}

//	Driver setter method
	public void setDriver(ThreadLocal<WebDriver> driver) {
		this.driver = (ThreadLocal<WebDriver>) driver;
	}

//	static wait method for pause
	public void staticwait(int seconds) {
		LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(seconds));
	}
}
