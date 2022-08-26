package week1_neptune_sprint1;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import week1_neptune.base_classes_salesforce;
import week1_neptune.browser_cls;

public class Assessment_V1 {

	public static int x = 0, y = 0, i = 1, j = 1;
	public static String nm1,name;

	public static void main(String[] args) throws Throwable {
		// Property file declaration
		Properties p1 = new Properties();
		String path = System.getProperty("user.dir") + "\\src\\main\\resources\\salesforce1.properties";
		FileInputStream fp = new FileInputStream(path);
		p1.load(fp);

		// Variables and xpaths
		JavascriptExecutor js;

		// Open Browser
		browser_cls cls = new browser_cls();
		WebDriver dr = cls.open_browser(p1.getProperty("browser"), p1.getProperty("URL"));
		js = (JavascriptExecutor) dr;
		
		// Login to salesforce
		base_classes_salesforce bc = new base_classes_salesforce();
		bc.logintoworkforce(dr, p1.getProperty("username"), p1.getProperty("passwrd"));

		// Click on the toggle menu button from the left corner
		dr.findElement(By.xpath(p1.getProperty("toggle"))).click();

		// Click View All and click Sales from App Launcher
		dr.findElement(By.xpath(p1.getProperty("view_all"))).click();

		WebElement sales_link = dr.findElement(By.xpath(p1.getProperty("sales")));
		js.executeScript("arguments[0].scrollIntoView();", sales_link);
		sales_link.click();

		JavascriptExecutor js1 = (JavascriptExecutor) dr;
		Thread.sleep(8000);
		boolean n1 = dr.findElement(By.xpath(p1.getProperty("closed1"))).isDisplayed();
		System.out.println(n1);
		boolean n2 = dr.findElement(By.xpath(p1.getProperty("open1"))).isDisplayed();
		System.out.println(n2);
		if (n1) {
			String close_val = dr.findElement(By.xpath(p1.getProperty("closed1"))).getText();
			String val1 = close_val.replaceAll("\\D", "");
			x = Integer.parseInt(val1);
		}

		if (n2) {
			String open_val = dr.findElement(By.xpath(p1.getProperty("open1"))).getText();
			String val2 = open_val.replaceAll("\\D", "");
			y = Integer.parseInt(val2);
		}

		int goal = x + y;

		// Click Edit Goal
		dr.findElement(By.xpath(p1.getProperty("Editgoal"))).click();
		Thread.sleep(3000);
		boolean chk = dr.findElement(By.xpath(p1.getProperty("goal_field"))).isDisplayed();
		Thread.sleep(3000);
		dr.findElement(By.xpath(p1.getProperty("goal_field"))).click();
		dr.findElement(By.xpath(p1.getProperty("goal_field"))).clear();
		WebElement txt = dr.findElement(By.xpath(p1.getProperty("goal_field")));
		if (goal < 10000) {
			goal = 10000;
		}
		txt.sendKeys(Integer.toString(goal));
		dr.findElement(By.xpath(p1.getProperty("save1"))).click();

		System.out.println(x + "----" + y + "----" + goal + "------" + chk);

		// Step7: Navigate to Dashboard tab
		Thread.sleep(3000);
		js = (JavascriptExecutor) dr;
		js.executeScript("arguments[0].click();", dr.findElement(By.xpath(p1.getProperty("dashboard"))));

		// Step8: Click on New Dashboard
		Thread.sleep(3000);
		dr.findElement(By.xpath(p1.getProperty("new_dashboard"))).click();

		// Step9: Enter the Dashboard name as "YourName_Workout"
		WebElement frame1 = dr.findElement(By.xpath(p1.getProperty("iframe1")));
		dr.switchTo().frame(frame1);
		int x = random_gen();
		nm1 = "sk-" + x;
		dr.findElement(By.xpath(p1.getProperty("dash_name"))).sendKeys(nm1);

		// Step10: Enter Description as Testing and Click on Create
		Thread.sleep(3000);
		dr.findElement(By.id("dashboardDescriptionInput")).sendKeys("Testing");
		js.executeScript("arguments[0].click();", dr.findElement(By.xpath(p1.getProperty("dash_create_btn"))));

		// Step12. Click on Done
		Thread.sleep(3000);
		WebElement frame2 = dr.findElement(By.xpath(p1.getProperty("iframe2")));
		dr.switchTo().frame(frame2);
		js.executeScript("arguments[0].click();", dr.findElement(By.xpath(p1.getProperty("done_btn"))));
		dr.switchTo().defaultContent();

		// Step13. Click on Dashboard tab
		js.executeScript("arguments[0].click();", dr.findElement(By.xpath(p1.getProperty("dashboard"))));

		// Step14. Verify the Dashboard is Created
		
		Assessment_V1 as1 = new Assessment_V1();
		WebElement rows = dr.findElement(By.xpath(p1.getProperty("dboard_name_1") + i + p1.getProperty("dboard_name_2")));
		name = scroll_page(dr, p1, js,rows);
		System.out.println(name);
		js.executeScript("arguments[0].click();", rows);
		System.out.println("Dashboard is Created");

		// Step16. Click on Subscribe
		dr.switchTo().frame(dr.findElement(By.xpath(p1.getProperty("iframe3"))));
		Thread.sleep(3000);
		dr.findElement(By.xpath(p1.getProperty("sub_btn"))).click();
		dr.switchTo().defaultContent();

		// Step17. Select Frequency as "Daily"
		dr.findElement(By.xpath(p1.getProperty("daily"))).click();

		// Step18. Time as 10:00 AM
		Select s1 = new Select(dr.findElement(By.id("time")));
		s1.selectByVisibleText("10:00 AM");

		// Step19. Click on Save
		dr.findElement(By.xpath(p1.getProperty("save2"))).click();

		// Step20. Verify "You started Dashboard Subscription" message displayed or not
		String msg = null;
		Thread.sleep(3000);
		if (dr.findElement(By.xpath(p1.getProperty("msg"))).isDisplayed()) {
			msg = dr.findElement(By.xpath(p1.getProperty("msg"))).getText();
			if (msg.contains("You started a dashboard subscription")) {
				System.out.println("You started a dashboard subscription is displayed");
			} else {
				System.out.println("dashboard subscription Message not displayed");
			}
		}

		// Step21. Click on Dashboards tab
		Thread.sleep(3000);
		List<WebElement> l1 = dr.findElements(By.xpath("//table/tbody/tr/th"));
		js.executeScript("arguments[0].click();", dr.findElement(By.xpath(p1.getProperty("dashboard"))));

		// Step22 - Delete Record
		
		for (int i = 1; i < l1.size(); i++) {

			if (dr.findElement(By.xpath("//table/tbody/tr[" + i + "]/th/descendant::a")).getAttribute("title")
					.equals(nm1)) {
				dr.findElement(
						By.xpath("//table/tbody/tr[" + i + "]/td[6]//span//lightning-primitive-cell-actions//button"))
						.click();
				Thread.sleep(3000);
				dr.findElement(By.xpath("//span[text()='Delete']")).click();
				Thread.sleep(3000);
				dr.findElement(By.xpath("//button[@title='Delete']/span")).click();
			}

		}

		// Step23 - Confirm the Delete
		Thread.sleep(3000);
		if (dr.findElement(By.xpath("/html/body/div[6]/div/div/div")).isDisplayed()) {
			String del_msg = dr.findElement(By.xpath("/html/body/div[6]/div/div/div"))
					.getText();
			if(del_msg.contains("Dashboard was deleted"))
			System.out.println(nm1+"Dashboard was deleted successfully");
		} else {
			System.out.println("Delete message not displayed");
		}
		
		//Step24 - Verify the item is not available under Private Dashboard folder
		l1.clear();
		i=1;
		rows = dr.findElement(By.xpath(p1.getProperty("dboard_name_1") + i + p1.getProperty("dboard_name_2")));
		name = scroll_page(dr, p1, js,rows);
		System.out.println("----"+i+"-----"+name);
		if(name.equals(nm1)) {
			System.out.println(nm1+" Record is not deleted from dashboard list");
		} else {
			System.out.println(i--+"Records verified and "+nm1+"is not found in the Private Dashboard List");
		}
		
	}
	public static int random_gen() {

		int min = 1;
		int max = 400;
		double a = Math.random() * (max - min + 1) + min;
		return ((int) a);
	}

	public static String scroll_page(WebDriver dr, Properties p1, JavascriptExecutor js,WebElement rows) {
		// TODO Auto-generated method stub
		

		while (rows.isDisplayed()) {

			js.executeScript("arguments[0].scrollIntoView()",rows);
			name = rows.getText();
			
			if (name.equals(nm1)) {
				break;
			} 
			i++;
		}
		return name;


	}

}
