
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;

import utilityPackage.Utility;

public class AddSalesPage {
	
	Select s;
	WebDriver driver;
	Utility u;
	
	@FindBy(xpath = "(//div[text()='Real Company']/ancestor::*[2]/following-sibling::*//span[text()='Sale Order']//ancestor::*[3]/preceding-sibling::*/a)[1]")
	WebElement dropwdownOrders;
	
	@FindBy(xpath = "//div[text()='Real Company']/ancestor::*[2]/following-sibling::*//span[text()='Sale Order']")
	WebElement saleOrder;
	
	@FindBy(xpath = "//h4[text()='Sale Order']")
	WebElement txtsaleOrder;
	
	@FindBy(id = "SOAddNew")
	WebElement btnAddOrder;
	
	@FindBy(id = "SOAddRef1")
	WebElement txtRefNo;
	
	@FindBy(id = "SOAddChannel")
	WebElement dropdownChannel;
	
	@FindBy(id = "SOAddCMore")
	WebElement searchCustomer;
	
	
	
	@FindBy(id = "SOAddSA")
	WebElement dropwdownShippingAddress;
	
	@FindBy(id = "SOAddSMore")
	WebElement searchAddProduct;
	
	@FindBy(xpath = "//span[@id='SOAddSAdd']")
	WebElement btnAddProduct;
	
	
	@FindBy(id = "SOAddBASame")
	WebElement checkboxSameAddres;
	
	@FindBy(id = "SOAddQty")
	WebElement txtProductQuantity;
	
	@FindBy(xpath = "//md-datepicker[@id='SOAddSCDate']//input")
	WebElement saleschanneldate;
	
	@FindBy(id = "SOAddSubmit")
	WebElement btnAddOrderubmit;
	
	
	public AddSalesPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver,this);
		u = new Utility(driver);
	}
	
	
	
	public void navigateToSaleOrderPage() {
		try {
			
			u.waitForElement(dropwdownOrders);
			dropwdownOrders.click();
			u.waitForElement(saleOrder);
			saleOrder.click();
			u.waitForElement(txtsaleOrder);
			String txtheader = txtsaleOrder.getText();
	    	Assert.assertEquals(txtheader, "Sale Order");
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public void addOrder() {
		
		try {
			ArrayList<String> arr = Utility.getExcelData("Sheet1","TC02");
			String newrefno = arr.get(0);	
			btnAddOrder.click();
			u.waitForElement(txtRefNo);
			txtRefNo.sendKeys(newrefno);
			Reporter.log(newrefno + " ref no.  is provided");
			
			String channel = arr.get(1);
			s = new Select(dropdownChannel);
	    	s.selectByVisibleText(channel);
	    	Reporter.log(channel + " channel is provided");
	    	
	    	searchCustomer.click();
	    	String customer = arr.get(2);
	    	By element = By.xpath("//table[@id= 'productTable']/tbody/tr/td[text()='"+customer+"']//following-sibling::*//button");
			u.waitForElement(element);
			WebElement selectCustomerData = driver.findElement(element);
	    	u.waitForElement(selectCustomerData);
	    	selectCustomerData.click();
	    	Reporter.log(customer + " Customer is selected");
	    	
	    	u.waitForElement(dropwdownShippingAddress);
	    	s= new Select(dropwdownShippingAddress);
	    	String address = arr.get(3);
	    	s.selectByValue(address);
	    	searchAddProduct.click();
	    	
	    	
	    	String data = arr.get(4);
	    	By element1 = By.xpath("//table[@id= 'productTable']/tbody/tr/td[text()='"+data+"']//following-sibling::*//button");
	    	u.waitForElement(element1);
	    	WebElement selectAddProductData= driver.findElement(element1);
	    	selectAddProductData.click();
	    	Reporter.log(data + " Product is provided");
	    	
	    	
	    	String quantity = arr.get(5);
	    	u.waitForElement(txtProductQuantity);
	    	txtProductQuantity.sendKeys(quantity);
	    	Reporter.log(quantity + " Quantity is provided");
	    	
	    	u.waitForElement(checkboxSameAddres);
	    	checkboxSameAddres.click();
	    	Reporter.log("Clicked on same address checkbox");
	    	
	    	u.waitForElement(btnAddProduct);
	    	btnAddProduct.click();
	    	Reporter.log("Clicked on Add product button");
	    	
	    	btnAddOrderubmit.click();
	    	Reporter.log("Clicked on Submit button");
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
