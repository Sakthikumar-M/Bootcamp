package week1_neptune_sprint1;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import week1_neptune.base_classes_salesforce;
import week1_neptune.browser_cls;

public class classic_account_creation {

	public static void main(String[] args) {
		
		//Variables and xpaths
		String browser = "chrome";
		String URL = "https://login.salesforce.com";
		String username = "hari.radhakrishnan@qeagle.com";
		String passwrd = "India$321";
		String profile = "//ul[@class='slds-global-actions']/li[9]//span/button//img";
		String pro_menu = "//div[@class='oneUserProfileCard']";
		
		
		//Open Browser
		browser_cls cls = new browser_cls();
		WebDriver dr = cls.open_browser(browser,URL);

		//Login to salesforce
		base_classes_salesforce bc = new base_classes_salesforce();
		bc.logintoworkforce(dr,username,passwrd);
		
		//Select Profile
		JavascriptExecutor jr = (JavascriptExecutor)dr;
		jr.executeScript("arguments[0].click()", dr.findElement(By.xpath(profile)));
		boolean ch1 = dr.findElement(By.xpath(pro_menu)).isDisplayed();
		System.out.println(ch1);
		
	}

}
