package allureReports;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

@Listeners({AllureListener.class})
public class Tests extends BaseClass{

	public WebDriver driver;
	String PATH = System.getProperty("user.dir");
	
	@BeforeClass 
	public void setUp() {
		BaseClass bs= new BaseClass();
		driver = bs.initialize_driver();
		driver.get("https://demo.nopcommerce.com/");		
	}
	
	@BeforeMethod
	public void videoRecord() throws SecurityException, Exception {
		System.out.println(PATH);
	}
	
	@AfterMethod
	public void stopRecoding() throws Exception {

	}
	
	
	@Severity(SeverityLevel.MINOR)	
	@Test(priority=1, description="Verify Logo presence on Home Page")
	@Description("Verify Logo presence on Home Page........")
	@Epic("EP001")
	@Feature("Feature1: Logo")
	@Story("Story:Logo Presence")
	@Step("Verify logo Presence")
	public void logoPresence() throws Exception{
		MyScreenRecorder.startRecording(new Tests() {}.getClass().getEnclosingMethod().getName());
		Thread.sleep(2000);
		boolean dispStatus=driver.findElement(By.xpath("//div[@class='header-logo']//a//img")).isDisplayed();
		Assert.assertEquals(dispStatus, true);
		MyScreenRecorder.stopRecording();
	}
	
	
	@Severity(SeverityLevel.BLOCKER)	
	@Test(priority=2, description="Verify login")
	@Description("Verify login with Valid Credentials........")
	@Epic("EP001")
	@Feature("Feature2: Login")
	@Story("Story:Valid login")
	@Step("Verify login")
	public void loginTest() throws Exception{
		MyScreenRecorder.startRecording(new Tests() {}.getClass().getEnclosingMethod().getName());
		driver.findElement(By.linkText("Log in")).click();
		driver.findElement(By.id("Email")).sendKeys("abhi.abhi@gmail.com");
		driver.findElement(By.id("Password")).sendKeys("abhi1234");
		driver.findElement(By.xpath("//input[@class='button-1 login-button']")).click();
		Thread.sleep(3000);
		Assert.assertEquals(driver.getTitle(), "nopCommerce demo store");
		MyScreenRecorder.stopRecording();
	}
	
		
	@Severity(SeverityLevel.NORMAL)	
	@Test(priority=3, description="Verify user Registration")
	@Description("Verify user Registration........")
	@Epic("EP001")
	@Feature("Feature3: Registration")
	@Story("Story:User registration")
	
	public void registrationTest() throws Exception{
		throw new SkipException("Skipping this Test");
	}
	
	
	@Severity(SeverityLevel.TRIVIAL)	
	@Test(priority=4, description="Verify user Registration1")
	@Description("Verify user Registration1........")
	@Epic("EP001")
	@Feature("Feature3: Registration1")
	@Story("Story:User registration1")
	
	public void registration1Test() throws Exception{
		Assert.assertTrue(true);
	}
	
	
	@Severity(SeverityLevel.TRIVIAL)	
	@Test(priority=5, description="Verify user Registration11")
	@Description("Verify user Registration11........")
	@Epic("EP001")
	@Feature("Feature3: Registration11")
	@Story("Story:User registration11")
	
	public void registration11Test() throws Exception{
		Assert.assertTrue(true);
	}
	

	@AfterClass
	public void tearDown(){	
		driver.quit();
	}
	
	
	@BeforeSuite
	public void cleanResults() throws IOException {
		File dirAllureResult = new File(PATH + File.separator + "allure-results");
        if(dirAllureResult.exists()) {
        	//remove all files except history folder 
        	for (File file: dirAllureResult.listFiles()) {
        		if(!file.getName().equals("history")) {   //|| !file.getName().equals("environment.properties")
        			file.delete();
        		}
        	}
        }
        File dirResult = new File(PATH + File.separator + "Report");
        if(dirResult.exists()) {
        	//remove Report folder and its contents
        	Runtime.getRuntime().exec(String.format("rm -r %s", dirResult));
        }
        
        //Set environment
        AllureEnvironmentWriter.allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put("Browser", "Chrome")
                        .put("Browser.Version", "70.0.3538.77")
                        .put("URL", "https://demo.nopcommerce.com/")
                        .build(), System.getProperty("user.dir")
                        + "/allure-results/");
	}
	
	
	@AfterSuite
	public void reportGen() throws IOException, InterruptedException {
		File reportDir = new File(PATH + File.separator + "Report");
		reportDir.mkdir();				
		Runtime.getRuntime().exec(String.format("/usr/local/bin/allure generate -c -o %s", reportDir));
        File temp = new File(PATH + File.separator + "TempReport");
        if(temp.exists()) {
            Runtime.getRuntime().exec(String.format("rm -r %s", temp));
        }
        Thread.sleep(2000);
        temp.mkdir();
        Thread.sleep(2000);
        CopyData.copyDirectory(reportDir,temp);
		Thread.sleep(2000);
        Runtime.getRuntime().exec(String.format("cp -R %s %s", reportDir,temp));
		//remove all files/folders from history folder of allure_result
        File historyFolder = new File(PATH + File.separator + "allure-results/history");
        if(historyFolder.exists()) {
            Runtime.getRuntime().exec(String.format("rm -r %s", historyFolder));
            Thread.sleep(2000);
        }
        File hFolder = new File(PATH + File.separator + "Report/history/");
		Runtime.getRuntime().exec(String.format("cp -R %s %s", hFolder,historyFolder));		
	}	
}
