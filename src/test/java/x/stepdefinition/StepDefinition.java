package x.stepdefinition;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;

import com.utilities.CommonMethods;
import com.utilities.Constants;
import com.utilities.LoadProperty;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.io.IOException;
import java.util.Set;

public class StepDefinition {
	public static WebDriver driver;

	@Before
	public void loadFile() {
		// to initiate load file
		LoadProperty.LoadProperties();
		if (driver == null) {
			launchBrowser();
		}
	}

	public void launchBrowser() {
		// to open required browser
		switch (Constants.browser) {
		case "chrome":
			System.setProperty(Constants.chrome, Constants.chrome_driver_location);
			driver = new ChromeDriver();
			break;
		case "edge":
			System.setProperty(Constants.edge, Constants.edge_driver_location);
			driver = new EdgeDriver();
			break;

		default:
			System.setProperty(Constants.chrome, Constants.chrome_driver_location);
			driver = new ChromeDriver();
			break;
		}

	}
	@After
	public void closeBrowser() {
		// to close browser
		driver.quit();

	}

	@Given("user opens x on thier browser")
	public void user_opens_x_on_thier_browser() {
		driver.get(Constants.APP_Url);
		driver.manage().window().maximize();
	}

	@When("user clicks on new account creation option")
	public void user_clicks_on_new_account_creation_option() {
		CommonMethods.implicitWait(10);
		WebElement createAccount = driver.findElement(
				By.xpath("//*[@id=\"react-root\"]/div/div/div[2]/main/div/div/div[1]/div[1]/div/div[3]/a/div"));
		createAccount.click();

		// switch to new window
		String curID = driver.getWindowHandle();
		System.out.println("Before Switch :" + curID);
		Set<String> All_ID = driver.getWindowHandles();
		try {
			for (String ID : All_ID) {
				if (curID != ID) {
					driver.switchTo().window(ID);
					System.out.println("After Switch :" + ID);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	@Then("user fillup the account details")
	public void user_fillup_the_account_details() throws IOException, InterruptedException {

		FileInputStream file = new FileInputStream(Constants.testData_path);
		XSSFWorkbook wb = new XSSFWorkbook(file);
		XSSFSheet sheet = wb.getSheetAt(0);
		XSSFRow row = sheet.getRow(1);
		int size = row.getLastCellNum();

		XSSFCell cell = row.getCell(size - 1);
		DataFormatter dft = new DataFormatter();
		String data = dft.formatCellValue(cell);
		String dataArr[] = data.split(",");

		// username
		WebElement username = driver.findElement(By.xpath(
				"//*[@id=\"layers\"]/div[2]/div/div/div/div/div/div[2]/div[2]/div/div/div[2]/div[2]/div[1]/div/div[2]/div[1]/label/div/div[2]/div/input"));
		username.clear();
		username.sendKeys(dataArr[0]);

		// use email Id
		driver.findElement(By.xpath("//*[text()='Use email instead']")).click();

		// email id
		WebElement email = driver.findElement(By.xpath(
				"//*[@id=\"layers\"]/div[2]/div/div/div/div/div/div[2]/div[2]/div/div/div[2]/div[2]/div[1]/div/div[2]/div[2]/label/div/div[2]/div/input"));
		email.clear();
		email.sendKeys(dataArr[1]);

		// month
		WebElement month = driver.findElement(By.xpath("//*[@id='SELECTOR_1']"));
		Select s = new Select(month);
		s.selectByVisibleText(dataArr[2]);
		// date
		WebElement date = driver.findElement(By.xpath("//*[@id='SELECTOR_2']"));
		Select s1 = new Select(date);
		s1.selectByVisibleText(dataArr[3]);

		// year
		WebElement year = driver.findElement(By.xpath("//*[@id='SELECTOR_3']"));
		Select s2 = new Select(year);
		s2.selectByVisibleText(dataArr[4]);
		Thread.sleep(1000);

		// next page
		WebElement next = driver.findElement(By.xpath(
				"//*[@id=\"layers\"]/div[2]/div/div/div/div/div/div[2]/div[2]/div/div/div[2]/div[2]/div[2]/div/div/div/div/div"));
		// CommonMethods.explicitWait(next, 5);
		next.click();

		// frame one
		WebElement frameOne = driver.findElement(By.xpath("//*[@id='arkoseFrame']"));
		CommonMethods.explicitWait(frameOne, 10);
		driver.switchTo().frame(frameOne);

		// frame two
		WebElement frameTwo = driver.findElement(By.xpath("//*[@id=\"arkose\"]/div/iframe"));
		CommonMethods.explicitWait(frameTwo, 10);
		driver.switchTo().frame(frameTwo);

		// frame three
		WebElement frameThree = driver.findElement(By.xpath("//*[@id=\"game-core-frame\"]"));
		CommonMethods.explicitWait(frameThree, 10);
		driver.switchTo().frame(frameThree);

		// authenticate
		WebElement authenticate = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/button"));
		CommonMethods.explicitWait(authenticate, 10);
		authenticate.click();

		wb.close();
	}

	@Then("user clicks on create account icon")
	public void user_clicks_on_create_account_icon() throws InterruptedException {

//		WebElement submit = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div/button"));
//		CommonMethods.explicitWait(submit, 5);
		Thread.sleep(5000);
		CommonMethods.screenSnap("Account Created");
	}

}
