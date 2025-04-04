package com.finacplus.qa.base;

	import java.io.FileInputStream;
	import java.io.IOException;
	import java.util.Properties;
	import java.util.concurrent.TimeUnit;

	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.chrome.ChromeOptions;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.openqa.selenium.support.events.EventFiringWebDriver;

	import com.finacplus.qa.util.TestUtil;
	import com.finacplus.qa.util.WebEventListener;

	public class TestBase {

		public static WebDriver driver;
		public static Properties prop = new Properties();
		public static EventFiringWebDriver e_driver;
		public static WebEventListener eventListener;

		public TestBase() {
			try (FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/com/finacplus/qa/config/config.properties")) {
				prop.load(ip);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public static void initialization() {
			String browserName = prop.getProperty("browser");

			if ("chrome".equals(browserName)) {
				System.setProperty("webdriver.chrome.driver", "/Users/Selenium/PageObjectModel/src/main/resources/chromedriver");
				driver = new ChromeDriver();
			} else if ("FF".equals(browserName)) {
				System.setProperty("webdriver.gecko.driver", "/Users/Selenium/PageObjectModel/src/main/resources/geckodriver");
				driver = new FirefoxDriver();
			}
			ChromeOptions options = new ChromeOptions();
			options.addArguments("chrome.switches","--disable-extensions");
			options.addArguments("--disable-save-password");
			options.addArguments("disable-infobars");
//			options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
//			options.setExperimentalOption("useAutomationExtension", false);

			driver = new ChromeDriver(options);

			e_driver = new EventFiringWebDriver(driver);
			eventListener = new WebEventListener();
			e_driver.register(eventListener);
			driver = e_driver;

			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);

			driver.get(prop.getProperty("url"));
		}
	}