package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public static WebDriver driver;
	public Logger logger;
	public Properties p;
	public String hubURL = "http://localhost:4444/wd/hub";

	@BeforeClass(alwaysRun = true)
	@Parameters({ "os", "browser" })
	public void setup(String os, String br) throws IOException {

		FileReader file = new FileReader(System.getProperty("user.dir") + "//src//test//resources//config.properties");
		p = new Properties();
		p.load(file);
		logger = org.apache.logging.log4j.LogManager.getLogger(this.getClass());

		if (p.getProperty("execution_env").equalsIgnoreCase("remote")) {
			DesiredCapabilities capabilities = new DesiredCapabilities();

			switch (os.toLowerCase()) {
			case "windows":
				capabilities.setPlatform(Platform.WIN10);
				break;
			case "linux":
				capabilities.setPlatform(Platform.LINUX);
				break;
			case "mac":
				capabilities.setPlatform(Platform.MAC);
				break;
			default:
				System.out.println("invalid platform");
				return;
			}

//			if (os.equals("windows")) {
//				capabilities.setPlatform(Platform.WIN10);
//			} else if (os.equals("mac")) {
//				capabilities.setPlatform(Platform.MAC);
//			} else if (os.equals("linux")) {
//				capabilities.setPlatform(Platform.LINUX);
//			} else {
//				System.out.println("no amtching platform found");
//				return;
//			}

			// browser

			switch (br.toLowerCase()) {
			case "chrome":
				capabilities.setBrowserName("chrome");
				break;
			case "edge":
				capabilities.setBrowserName("MicrosoftEdge");
				break;
			case "firefox":
				capabilities.setBrowserName("firefox");
				break;
			default:
				System.out.println("no matching browser");
				return;
			}

			driver = new RemoteWebDriver(new URL(hubURL), capabilities);

		}
		if (p.getProperty("execution_env").equalsIgnoreCase("local")) {

			switch (br.toLowerCase()) {
			case "chrome":
				driver = new ChromeDriver();
				break;
			case "edge":
				driver = new EdgeDriver();
				break;
			case "firefox":
				driver = new FirefoxDriver();
				break;
			default:
				System.out.println("Invalid browser name..");
				return;
			}
		}

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get(p.getProperty("AppURL"));
		driver.manage().window().maximize();
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	// random number generator functions
	public static String randomeString() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}

	public static String randomeNumber() {
		String generatedString = RandomStringUtils.randomNumeric(10);
		return generatedString;
	}

	public static String randomAlphaNumeric() {
		String str = RandomStringUtils.randomAlphabetic(3);
		String num = RandomStringUtils.randomNumeric(3);

		return (str + "@" + num);
	}

	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

		String targetFilePath = System.getProperty("user.dir") + File.separator + "screenshots" + File.separator + tname
				+ "_" + timeStamp + ".png";
		File targetFile = new File(targetFilePath);

		sourceFile.renameTo(targetFile);
		
		
//		// Ensure screenshots directory exists
//		targetFile.getParentFile().mkdirs();
//
//		// Use Files.copy for reliable file operations
//		Files.copy(sourceFile.toPath(), Paths.get(targetFilePath));

		return targetFilePath;

	}

}
