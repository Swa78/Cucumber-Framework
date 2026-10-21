 package hooks;

import java.io.File;

import basePage.BaseClass;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks extends BaseClass {


	@Before
	public void setup(Scenario scenario ) {
		
		System.out.println("Scenario start : " + scenario.getName());
		loadconfig();  //Open the URL
		launchBrowser();  //Open the browser
	
		}
	
	@Before ("@smoke")
	public void beforesmoke() {
		System.out.println("Running smoke test");
	}
	
	@Before ("@Regression")
	public void beforeregression() {
		System.out.println("Running regression test");
	}
	
	
	@After
	public void teardown(Scenario scenario) {
		
		if(scenario.isFailed()) {
			
			//=====================Screenshot ========================
			
			System.out.println("Test Failed" +scenario.getName() );
			
			String folderpath = "ExtentReport/Screenshots";

			String imgpath = capturescreen(scenario.getName(), folderpath);

			try {
			    byte[] fileContent = java.nio.file.Files.readAllBytes(
			        java.nio.file.Paths.get(imgpath)
			    );

			    scenario.attach(fileContent, "image/png", "failed Screenshot");

			    String relativePath = "screenshots/" + new File(imgpath).getName();

			    com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter
			        .addTestStepScreenCaptureFromPath(relativePath);

			} catch (Exception e) {
			    e.printStackTrace();
			}
			
			System.out.println("Test Failed : " +scenario.getName());
			}
		
		
		else {
			
			System.out.println("Test Passed :  " +scenario.getName());
		}
	
		closeBrowser();  // Close the browser
		System.out.println("=========Test Executed Successfully==========");
	}
}
