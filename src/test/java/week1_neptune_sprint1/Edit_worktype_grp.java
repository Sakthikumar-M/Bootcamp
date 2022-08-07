package week1_neptune_sprint1;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import week1_neptune.base_classes_salesforce;
import week1_neptune.browser_cls;

public class Edit_worktype_grp {

	public static void main(String[] args) throws Throwable {
		// Open salesforce webpage
		browser_cls cls = new browser_cls();
		WebDriver dr = cls.open_browser("chrome", "https://login.salesforce.com");

		// login to salesforce
		base_classes_salesforce bc = new base_classes_salesforce();
		String username = "hari.radhakrishnan@qeagle.com";
		String pword = "India$321";
		bc.logintoworkforce(dr, username, pword);

		// Navigate to work type group
		JavascriptExecutor js = (JavascriptExecutor) dr;
		bc.navigate_worktypegroup(dr,js);

	}

}
