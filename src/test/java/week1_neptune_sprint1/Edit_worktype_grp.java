package week1_neptune_sprint1;

import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import week1_neptune.base_classes_salesforce;
import week1_neptune.browser_cls;

public class Edit_worktype_grp {

	public static void main(String[] args) throws Throwable {
		// Open salesforce webpage
		browser_cls cls = new browser_cls();
		WebDriver dr = cls.open_browser("chrome", "https://login.salesforce.com");

		// login to salesforce
		base_classes_salesforce bc = new base_classes_salesforce();
		bc.logintoworkforce(dr, "hari.radhakrishnan@qeagle.com", "India$321");

		// Navigate to work type group
		JavascriptExecutor js = (JavascriptExecutor) dr;
		bc.navigate_worktypegroup(dr, js);
		dr.findElement(By.xpath("//*[@id='input-120']")).sendKeys("leaf1");
		Thread.sleep(5000);
		
		//click on row one
		dr.findElement(By.xpath("//table[contains(@class,'slds-table')]/tbody/tr[1]")).click();
		
		//select the dropdown
		WebElement drop = dr.findElement(By.xpath("//table[contains(@class,'slds-table')]/tbody/tr[1]/td[5]/span/div"));
		//WebElement clk = (WebElement) new WebDriverWait(dr, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(drop));
		drop.click();
		Thread.sleep(3000);
		
		//Select the Edit option
		WebElement edit = dr.findElement(By.xpath("//div[contains(@class,'branding-actions actionMenu popup')]/div/ul/li[1]/a"));
		edit.click();
		//js.executeScript("arguments[0].click()", edit);
		
		WebElement edit_val = dr.findElement(By.xpath("//input[contains(@id,'126')]"));
		edit_val.clear();
		edit_val.sendKeys("leaf1 updated");
		
		WebElement save = dr.findElement(By.xpath("//div[contains(@class,'button-container-inner')]/button[3]/span"));
		save.click();
		 
	}

}
