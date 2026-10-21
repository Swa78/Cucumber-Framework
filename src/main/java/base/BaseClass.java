package base;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.nio.file.Files;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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
	}
	
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
