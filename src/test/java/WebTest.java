
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.Test;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class WebTest {
  private WebDriver driver;
  private String baseUrl;
  private StringBuffer verificationErrors = new StringBuffer();
  public Integer passCount=0;
  public Integer totalCount=23;
  public Select s;
  
  @BeforeTest
  public void driverSetUp() {
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\lilesh bansal\\Desktop\\chromedriver.exe");
	  driver = new ChromeDriver();
	  driver.manage().window().maximize();
    baseUrl = "http://apps.qa2qe.cognizant.e-box.co.in/SoftwareLicence";
    driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
  }
  
  @Test
  public void testMozilla() {

	  driver.get(baseUrl + "/index.html");
	  
	    try {
	    	WebElement a = driver.findElement(By.id("licenceName"));
	    	s = new Select(a);
	    	s.selectByValue("Mozilla Public License 2.0");
	    	driver.findElement(By.id("quantity")).sendKeys("5");
	       
	    } catch (Error e) {
	        verificationErrors.append("s\n");
	    } catch(Exception e){
	      	verificationErrors.append("Cannot provide the input please stick to the UI constraints1.\n");
	    }
	    String e = driver.findElement(By.id("result")).getText();
	    
	    Assert.assertEquals("The total purchased quantity of licence 123456789abc is 5 and the amount is Rs.25000", e);
  }
  
  @AfterTest
  public void driverClose() throws Exception {
		System.out.println("#"+Math.round((passCount/(float)totalCount)*100));
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
    	 Assert.fail(verificationErrorString);
    }
  }

}
