package week1_neptune_sprint1;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import week1_neptune.base_classes_salesforce;
import week1_neptune.browser_cls;

public class Legal_Entity_Sort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Variables and xpaths
		String browser = "chrome";
		String URL = "https://login.salesforce.com";
		String username = "hari.radhakrishnan@qeagle.com";
		String passwrd = "India$321";
		String toggle = "//*[@class='slds-icon-waffle']";
		String view_all = "//button[text()='View All']";
		String Legal_Entities = "//p[text()='Legal Entities']";
		String sort_icon = "//table[contains(@class,'slds')]//tr/th[4]//a";
		ArrayList<String>li = new ArrayList<String>();

		// Open Browser
		browser_cls cls = new browser_cls();
		WebDriver dr = cls.open_browser(browser, URL);

		// Login to salesforce
		base_classes_salesforce bc = new base_classes_salesforce();
		bc.logintoworkforce(dr, username, passwrd);
		
		//Click on the toggle menu button from the left corner
		dr.findElement(By.xpath(toggle)).click();
		
		//Click View All and click Legal Entities from App Launcher
		dr.findElement(By.xpath(view_all)).click();
		JavascriptExecutor js = (JavascriptExecutor)dr;
		WebElement Lgl_Entity = dr.findElement(By.xpath(Legal_Entities));
		js.executeScript("arguments[0].scrollIntoView();", Lgl_Entity);
		Lgl_Entity.click();
		
		//Click the sort arrow in the Last Modified Date
		dr.findElement(By.xpath(sort_icon)).click();
		
		//Capture the dates into an array
		int i=1;
		//try {
		while(dr.findElement(By.xpath("//table[contains(@class,'slds-table')]//tr["+i+"]/td[1]")).isDisplayed()) {
			js.executeScript("arguments[0].scrollIntoView();",dr.findElement(By.xpath("//table[contains(@class,'slds-table')]//tr["+i+"]")));
			i++;
			System.out.println(i);
		}
		/*}catch(Exception e) {
			if(e.getMessage().contains("no such")) {
				System.out.println("scroll completed");
			}*/
		}

}
