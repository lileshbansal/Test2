import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import utilityPackage.Utility;

public class AddSalesPageTest {
  private WebDriver driver;
  private String baseUrl;
  
  @BeforeTest
  public void driverSetUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\lilesh bansal\\Desktop\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		baseUrl = "https://testfrontend.gscmaven.com";
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.get(baseUrl);
  }
  
  @Test
  public void testAddPage_TC_02() {

	    try {
	    	AddSalesPage sales = new AddSalesPage(driver);
	    	Utility u = new Utility(driver);
	    	u.login();
	    	sales.navigateToSaleOrderPage();
	    	sales.addOrder();
	    } 
	    catch(Exception e){
	    	e.printStackTrace();
	    }
  }
  
  @AfterTest
  public void driverClose() {
    driver.quit();
  }

}
