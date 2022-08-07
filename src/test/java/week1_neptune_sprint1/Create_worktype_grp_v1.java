package week1_neptune_sprint1;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import week1_neptune.browser_cls;

public class Create_worktype_grp_v1 {

	public static WebDriver dr;

	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub

		// browser_cls cls = new browser_cls("chrome", "https://login.salesforce.com");
		browser_cls cls1 = new browser_cls();
		WebDriver dr = cls1.open_browser("chrome", "https://login.salesforce.com");
		JavascriptExecutor js = (JavascriptExecutor) dr;
		logintoworkforce(dr);
		navigate_worktypegroup(dr);
		create_new_worktypegrp(dr,js);
	}

	

	public static void logintoworkforce(WebDriver dr) {
		WebElement username = dr.findElement(By.id("username"));
		WebElement password = dr.findElement(By.id("password"));
		WebElement login = dr.findElement(By.id("Login"));

		username.sendKeys("hari.radhakrishnan@qeagle.com");
		password.sendKeys("India$321");
		login.click();		
	}
	
	public static void navigate_worktypegroup(WebDriver dr) throws Throwable {
		// TODO Auto-generated method stub
		dr.findElement(By.xpath("//*[@class='slds-icon-waffle']")).click();
		Thread.sleep(5000);
		dr.findElement(By.xpath("//button[text()='View All']")).click();
	}

	public static void create_new_worktypegrp(WebDriver dr, JavascriptExecutor js) {
		WebElement WTGmenu = dr.findElement(By.xpath("//p[text()='Work Type Groups']"));
		js.executeScript("arguments[0].scrollIntoView();", WTGmenu);
		WTGmenu.click();

		WebElement WTGplus = dr.findElement(By.xpath("//a[contains(@title,'Work Type Groups')]/following-sibling::one-app-nav-bar-item-dropdown/div/one-app-nav-bar-menu-button/a"));
		js.executeScript("arguments[0].click()", WTGplus);

		WebElement new_WTG = dr.findElement(By.xpath("//a[contains(@title,'Work Type Groups')]/following-sibling::one-app-nav-bar-item-dropdown/div/one-app-nav-bar-menu-button/div/div//a"));
		js.executeScript("arguments[0].click()", new_WTG);

		WebElement new_WTG_name = dr.findElement(By.xpath("//div[@class='uiInput uiInputText uiInput--default uiInput--input']/input"));
		String WTG_name = "leaf1";
		new_WTG_name.sendKeys(WTG_name);

		WebElement new_WTG_save = dr.findElement(By.xpath("//*[@class='button-container-inner slds-float_right']/button[3]"));
		new_WTG_save.click();

		WebElement new_name = dr.findElement(By.xpath("//span[text()='Work Type Group Name']/parent::div/following-sibling::div/span/span"));
		String grp_name = new_name.getText();
		System.out.println(grp_name);
		
		if (grp_name.equals(WTG_name)) {
			System.out.println("Successfully created a worktype group");
		}else {
			System.out.println("Name Mismatch");
		}
		
	}
}
