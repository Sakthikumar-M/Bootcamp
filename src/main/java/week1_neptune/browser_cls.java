package week1_neptune;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class browser_cls {
	public WebDriver dr = null;

	public WebDriver open_browser(String browser, String url) {

		switch (browser) {
		case "chrome":
			chrome(url);
			break;
		case "firefox":
			firefox(url);
			break;
		case "edge":
			edge(url);
			break;

		}
		
		dr.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));		
		return dr;

	}

	private void edge(String url) {
		// TODO Auto-generated method stub
		WebDriverManager.edgedriver().setup();
		EdgeOptions opt = new EdgeOptions();
		opt.addArguments("--disable-notifications");
		opt.addArguments("--start-maximized");
		dr = new EdgeDriver(opt);
		common_functions(url);
		
	}

	private void firefox(String url) {
		// TODO Auto-generated method stub
		WebDriverManager.firefoxdriver().setup();
		dr = new FirefoxDriver();
		common_functions(url);

	}

	public void chrome(String url) {
		WebDriverManager.chromedriver().setup();
		
		ChromeOptions opt = new ChromeOptions();
		opt.addArguments("--disable-notifications");
		opt.addArguments("--start-maximized");
		opt.addArguments("ignore-certificate-errors");
		dr = new ChromeDriver(opt);
		common_functions(url);
	}

	public void common_functions(String url) {
		dr.manage().window().maximize();
		dr.get(url);
	}
}
