package week1_neptune;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class base_classes_salesforce {
	
	public void logintoworkforce(WebDriver dr,String user,String pword) {
		WebElement username = dr.findElement(By.id("username"));
		WebElement password = dr.findElement(By.id("password"));
		WebElement login = dr.findElement(By.id("Login"));
		username.sendKeys(user);
		password.sendKeys(pword);
		login.click();		
	}
	
	public void navigate_worktypegroup(WebDriver dr,JavascriptExecutor js) throws Throwable {
		dr.findElement(By.xpath("//*[@class='slds-icon-waffle']")).click();
		dr.findElement(By.xpath("//button[text()='View All']")).click();
		
		WebElement WTGmenu = dr.findElement(By.xpath("//p[text()='Work Type Groups']"));
		js.executeScript("arguments[0].scrollIntoView();", WTGmenu);
		WTGmenu.click();
	}
	
	public void create_new_worktypegrp(WebDriver dr, JavascriptExecutor js,String WTG_name) {
		

		WebElement WTGplus = dr.findElement(By.xpath("//a[contains(@title,'Work Type Groups')]/following-sibling::one-app-nav-bar-item-dropdown/div/one-app-nav-bar-menu-button/a"));
		js.executeScript("arguments[0].click()", WTGplus);

		WebElement new_WTG = dr.findElement(By.xpath("//a[contains(@title,'Work Type Groups')]/following-sibling::one-app-nav-bar-item-dropdown/div/one-app-nav-bar-menu-button/div/div//a"));
		js.executeScript("arguments[0].click()", new_WTG);

		WebElement new_WTG_name = dr.findElement(By.xpath("//div[@class='uiInput uiInputText uiInput--default uiInput--input']/input"));
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
