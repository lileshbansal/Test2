package utilityPackage;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utility {
	WebDriver driver;
	WebDriverWait w;
	String username;
	String password;
	 
	@FindBy(id = "loginEmail")
	WebElement txtlogin;
	
	@FindBy(id = "loginPassword")
	WebElement txtpassword;
	
	@FindBy(id = "loginSubmit")
	WebElement btnSubmit;
	
	public Utility(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	public void waitForElement(WebElement locator) {
		 w = new WebDriverWait(driver,40);
		 w.until(ExpectedConditions.visibilityOf(locator));
	}
	
	public void waitForElement(By locator) {
		 w = new WebDriverWait(driver,40);
		 w.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	
	public void login() {
		ArrayList<String> a = getExcelData("Sheet1","TC01");
		username = a.get(0);
		password = a.get(1);
		waitForElement(txtlogin);
		txtlogin.sendKeys(username);
		waitForElement(txtpassword);
		txtpassword.sendKeys(password);
		waitForElement(btnSubmit);
		btnSubmit.click();
	}
	
	public static ArrayList<String> getExcelData(String sheetName, String testId) {
		 HashMap<String, ArrayList<String>> m0 = new HashMap<String, ArrayList<String>>();
		 ArrayList<String> arr = null ;
		 int id =0;
			try {
				FileInputStream fs = new FileInputStream("C:\\Users\\lilesh bansal\\git\\Test1\\Maven_practice\\Testdata\\test_data\\Test_data.xlsx");
				XSSFWorkbook  wb = new XSSFWorkbook(fs);
				Sheet sh = wb.getSheet(sheetName);
				
				int totalNoOfRows = sh.getPhysicalNumberOfRows();
				
				for (int i= 1 ; i < totalNoOfRows; i=i+3) {
					arr = new ArrayList<String>();
					String value = sh.getRow(i).getCell(0).getStringCellValue();
					if(testId.equals(value)) {
					id = i;
					break;
					}
				}
				int totalNoOfCols = sh.getRow(id).getPhysicalNumberOfCells();
				for (int j= 1 ; j < totalNoOfCols; j++) {
					arr.add( sh.getRow(id).getCell(j).getStringCellValue());
				}
				String PlayerName = sh.getRow(id).getCell(0).getStringCellValue();
				m0.put(PlayerName, arr);
				}
				
			catch (Exception e) {
				e.printStackTrace();
			}
			return m0.get(testId);
	}
	
	
	 
}
