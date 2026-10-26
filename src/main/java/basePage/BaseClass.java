package basePage;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.nio.file.Files;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.PageFactory;

public class BaseClass {
	
	public static WebDriver driver;
	
	//public static String Base_url="https://www.saucedemo.com/";
	
	public static Properties prop;
		
	public void loadconfig() {
		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream("src/main/resources/config/config1.properties");
			prop.load(fis);
			fis.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	///////// Browser Launch /////////////////////
	
	public void launchBrowser() {

	    String browser_name = prop.getProperty("browser");

	    // true when run with -Dheadless=true (default false)
	    boolean headless = Boolean.parseBoolean(System.getProperty("headless", "true"));

	    if (browser_name.equalsIgnoreCase("chrome")) {

	        ChromeOptions options = new ChromeOptions();
	        if (headless) {
	            options.addArguments("--headless=new");
	            options.addArguments("--window-size=1920,1080");
	        }
	        options.addArguments("--remote-allow-origins=*");

	        driver = new ChromeDriver(options);   // pass options here
	    }
	    else if (browser_name.equalsIgnoreCase("firefox")) {

	        FirefoxOptions options = new FirefoxOptions();
	        if (headless) {
	            options.addArguments("-headless");
	            options.addArguments("--width=1920");
	            options.addArguments("--height=1080");
	        }

	        driver = new FirefoxDriver(options);
	    }
	    else {
	        throw new IllegalArgumentException("Browser not supported: " + browser_name);
	    }

	    driver.get(prop.getProperty("url"));

	    if (!headless) {
	        driver.manage().window().maximize();
	    }
	}
	
	
	
	
	
	
	/*public void launchBrowser() {
		
	     String browser_name= prop.getProperty("browser");
		
	     if(browser_name.equalsIgnoreCase("chrome")) {
	    	 driver = new ChromeDriver();
	     }
	     else if(browser_name.equalsIgnoreCase("firefox")) {
	    	 driver = new FirefoxDriver();
	     }
	     else {
	    	System.out.println("Browser not supported");
	     }
	     driver.get(prop.getProperty("url"));
	     driver.manage().window().maximize();
	}   */
	
	//==========================common Page factory Method==============================
	public void initpagefactory(Object page) {
		PageFactory.initElements(driver, page);
	}
	
	//==============Method For screenshot===========================================
	
	public String capturescreen(String testName, String folderpath) {
		
		try {
			
			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			
			String path =folderpath + "/ " +testName + ".png";
			
			File dest = new File(path);
			
			dest.getParentFile().mkdirs();
			
		
			Files.copy(src.toPath(), dest.toPath());
			return path;
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;              // for failure case
		}
		
	}
	
	
	//Close Browser
	public void closeBrowser() {
		
		if(driver != null) {
			driver.quit();
		}
		
	}
	
	

}
