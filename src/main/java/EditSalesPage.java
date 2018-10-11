import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import utilityPackage.Utility;

public class EditSalesPage {
	Select s;
	Utility u;
	WebDriver driver;

	

	@FindBy(id = "SOAddCMore")
	WebElement searchCustomer;

	@FindBy(id = "SOAddSMore2")
	WebElement searchAddProduct;

	@FindBy(id = "SOAddUpdate")
	WebElement btnUpdate;

	@FindBy(id = "SOAddSA")
	WebElement dropwdownShippingAddress;

	@FindBy(id = "SOAddQty1")
	WebElement txtProductQuantity;
	
	@FindBy(id = "SOAddPrice1")
	WebElement txtProductPrice;
	
	@FindBy(xpath = "//span[@id='SOAddSAdd2']")
	WebElement btnAddProduct;

	@FindBy(xpath = "//span[contains(text(),'Do you want to continue?')]")
	WebElement txtConfirm;

	@FindBy(id = "SOCnfrmSubmit")
	WebElement btnSubmit;

	public EditSalesPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		u = new Utility(driver);
	}

	public void editOrder() {
		try {
			ArrayList<String> arr = Utility.getExcelData("Sheet1","TC03");
			
			String refno = arr.get(0);
			By element = By.xpath("//table/tbody/tr/td[text()='"+refno+"']//following-sibling::*//img[@title='Edit']");
			u.waitForElement(element);
			WebElement ref = driver.findElement(element);
			ref.click();
			
			u.waitForElement(searchCustomer);
			searchCustomer.click();
			
			String customerName = arr.get(1);
			By element1 = By.xpath("//table[@id= 'productTable']/tbody/tr/td[text()='"+customerName+"']//following-sibling::*//button");
			u.waitForElement(element1);
			WebElement selectCustomerData = driver.findElement(element1);
			selectCustomerData.click();
			Reporter.log(customerName + " Customer name is provided");
			
			
			String address = arr.get(2);
			s = new Select(dropwdownShippingAddress);
			Thread.sleep(2000);
			s.selectByValue(address);
			
			u.waitForElement(searchAddProduct);
			searchAddProduct.click();
			
			String skuName = arr.get(3);
			By element2 = By.xpath("//table[@id= 'productTable']/tbody/tr/td[text()='"+skuName+"']//following-sibling::*//button");
			u.waitForElement(element2);
			WebElement selectAddProductData = driver.findElement(element2);
			selectAddProductData.click();
			Reporter.log(skuName + " sku name is provided");
			
			String quantity = arr.get(4);
			u.waitForElement(txtProductQuantity);
			txtProductQuantity.sendKeys(quantity);
			Reporter.log(quantity + " quantity is provided");
			
			u.waitForElement(txtProductPrice);
			u.waitForElement(btnAddProduct);
				btnAddProduct.click();
				Reporter.log("Clicked on Add Product Button");
			
			
			u.waitForElement(btnUpdate);
			btnUpdate.click();
			Reporter.log("Clicked on Update Button");

			u.waitForElement(txtConfirm);
			if (txtConfirm.isDisplayed()) {
				btnSubmit.click();
				Reporter.log("Edit Sales Order is done");
			}

		}

		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
