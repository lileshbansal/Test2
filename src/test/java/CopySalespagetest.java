import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import utilityPackage.Utility;

public class CopySalespagetest {
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
	  public void testCopyPage_TC_05() {

		    try {
		    	AddSalesPage sales = new AddSalesPage(driver);
		    	CopySalesPage copy = new CopySalesPage(driver);
		    	Utility u = new Utility(driver);
		    	u.login();
		    	sales.navigateToSaleOrderPage();
		    	copy.copyOrder();
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
