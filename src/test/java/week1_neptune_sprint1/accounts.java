package week1_neptune_sprint1;

import java.util.ArrayList;
import java.util.Collections;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import week1_neptune.base_classes_salesforce;
import week1_neptune.browser_cls;

public class accounts {

	public static void main(String[] args) throws Throwable {
		browser_cls cls = new browser_cls();
		WebDriver dr = cls.open_browser("chrome", "https://login.salesforce.com");

		// login to salesforce
		base_classes_salesforce bc = new base_classes_salesforce();
		bc.logintoworkforce(dr, "hari.radhakrishnan@qeagle.com", "India$321");

		// Navigate to account
		dr.findElement(By.xpath("//*[@class='slds-icon-waffle']")).click();
		dr.findElement(By.xpath("//input[contains(@id,'input')]")).sendKeys("accounts");
		dr.findElement(By.xpath("//div[contains(@class,'panel-content scrollable')]/descendant::b")).click();
		JavascriptExecutor js = (JavascriptExecutor) dr;
		ArrayList<WebElement> scroll_length = new ArrayList<WebElement>();
		ArrayList<String> before_sort = new ArrayList<String>();
		ArrayList<String> after_sort = new ArrayList<String>();
		String content;
		
		//scroll the table
		int j = pagescroll(dr, js, scroll_length);

		//store the values before the sort
		for (int n = 1; n <= j; n++) {
			content = dr.findElement(By.xpath("//table[contains(@class,'slds-table')]/tbody/tr[" + n + "]//th//a"))
					.getAttribute("title");
			before_sort.add(content);
		}
		//Refresh the page and click the sort option
		dr.navigate().refresh();
		Thread.sleep(5000);
		WebElement title = dr.findElement(By.xpath("//table[contains(@class,'slds-table forceRecord')]/descendant::a[1]"));
		Actions act = new Actions(dr);
		act.moveToElement(title).click().perform();
		
		//scroll the page 
		int k = pagescroll(dr, js, scroll_length);
		
		//store the sorted content in new arraylist
		for (int n = 1; n <= k; n++) {
			content = dr.findElement(By.xpath("//table[contains(@class,'slds-table')]/tbody/tr[" + n + "]//th//a"))
					.getAttribute("title");
			after_sort.add(content);
		}
		
		//sort the old unsorted data
		Collections.sort(before_sort);
		
		System.out.println("Before Sort");
		System.out.println("Before Sort count"+before_sort.size());
		System.out.println(before_sort);
		
		System.out.println("After Sort");
		System.out.println("After Sort count"+before_sort.size());
		System.out.println(after_sort);

	}

	public static int pagescroll(WebDriver dr, JavascriptExecutor js, ArrayList<WebElement> scroll_length)
			throws Throwable {
		int j = 1;
		for (int i = 0; i < j; i++) {
			i = j + 1;
			WebElement scroll = dr.findElement(By.xpath("//table[contains(@class,'slds-table')]/tbody/tr[last()]"));

			js.executeScript("arguments[0].scrollIntoView()", scroll);
			Thread.sleep(1000);
			scroll_length = (ArrayList<WebElement>) dr
					.findElements(By.xpath("//table[contains(@class,'slds-table')]/tbody/tr//th//a"));
			j = scroll_length.size();
			System.out.println(i + "----" + j);
			scroll_length.clear();
		}
		return j;
	}

}
