package Day1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirstTestCase {

	public static void main(String[] args) {
			
		// 1. Launch the browser
		WebDriver driver = new ChromeDriver();
//		WebDriver driver =new EdgeDriver();
//		WebDriver driver = new FirefoxDriver();
		

		// 2. Open URl
		 driver.get("https://kalyanifoods.co/");
		 
		// 3. Validate the title should be "Kalyanifoods"
		 
		 String act_title = driver.getTitle();
		 
		 if(act_title.equalsIgnoreCase("Kalyani Foods – Ice Lollies, Lassi, Shakes & Milk Products in Badanapur | My App")) 
		 {
			 System.out.println("Test Passed");
		 }
		 else 
		 {
			 System.out.println("Test Failed");
		 }
		
		 // 4. Close the browser		 
		 
		 
//		 driver.quit();
		 
	}

}
