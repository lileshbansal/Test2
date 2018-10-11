import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import utilityPackage.Utility;

public class CopySalesPage {
	
	public Select s;
	Utility u;
	WebDriver driver;

	

	@FindBy(id = "SOAddRef1")
	WebElement txtRefNo;
	
	@FindBy(id = "SOAddCMore")
	WebElement searchCustomer;

	
	
	@FindBy(id = "SOAddSA")
	WebElement dropwdownShippingAddress;

	@FindBy(id = "SOAddSubmit")
	WebElement btnAddOrderubmit;
	
	
	public CopySalesPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		u = new Utility(driver);
	}
	
	public void copyOrder() {
		try {
			ArrayList<String> arr = Utility.getExcelData("Sheet1","TC05");
			
			String refno = arr.get(0);	
			By element = By.xpath("//table/tbody/tr/td[text()='"+refno+"']//following-sibling::*//img[@title='Copy']");
			u.waitForElement(element);
			WebElement btnCopy = driver.findElement(element);
			u.waitForElement(btnCopy);
			btnCopy.click();
			Reporter.log("Clicked on "+refno);
			
			
			String newrefno = arr.get(1);
			u.waitForElement(txtRefNo);
			txtRefNo.sendKeys(newrefno);
			Reporter.log("New ref no is "+newrefno);
			
			
			u.waitForElement(searchCustomer);
			searchCustomer.click();
			
			String customer = arr.get(2);
			By element1 = By.xpath("//table[@id= 'productTable']/tbody/tr/td[text()='"+customer+"']//following-sibling::*//button");
			u.waitForElement(element1);
			WebElement selectCustomerData= driver.findElement(element1);
			selectCustomerData.click();
			Reporter.log(customer + " Customer is selected");
			
			//u.waitForElement(dropwdownShippingAddress);
			s = new Select(dropwdownShippingAddress);
			Thread.sleep(2000);
			String address = arr.get(3);
			s.selectByValue(address);
			
			u.waitForElement(btnAddOrderubmit);
			btnAddOrderubmit.click();
			Reporter.log("Clicked on Submit button");
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
