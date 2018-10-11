import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

import utilityPackage.Utility;

public class searchFilter {
	WebDriver driver;
	Utility u;
	@FindBy(id = "SONo")
	WebElement txtOrderNo;

	@FindBy(id = "SORef")
	WebElement txtRefNo;
	
	@FindBy(id = "SOSearch")
	WebElement btnSearch;
	
	@FindBy(xpath = "//table//tbody/tr//td[@id = 'SONo0']")
	WebElement tableOrderNo;
	
	@FindBy(xpath = "//table//tbody/tr//td[@id = 'SORef0']")
	WebElement tableRefNo;
	
	public searchFilter(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		u = new Utility(driver);
	}
	
	
	public void search() {
		try {
			ArrayList<String> arr = Utility.getExcelData("Sheet1","TC04");
			System.out.println("arr is " + arr);
			String orderNo = arr.get(0);
			String refNo = arr.get(1);
			txtOrderNo.sendKeys(orderNo);
			txtRefNo.sendKeys(refNo);
			btnSearch.click();
			Thread.sleep(1000);
			Assert.assertEquals(orderNo, tableOrderNo.getText());
			Reporter.log("OrderNo"+orderNo+" is matched with the data provided in the excel");
			Assert.assertEquals(refNo, tableRefNo.getText());
			Reporter.log("RefNo"+refNo+" is matched with the data provided in the excel");
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
