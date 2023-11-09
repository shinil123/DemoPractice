package Red;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.internal.WebElementToJsonConverter;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
public class Scenario1 
{
	@Test(enabled=false)
	public void Scenario1() 
	{
		try
		{
			WebDriver driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			driver.get("https://demo.actitime.com/login.do");
			driver.findElement(By.cssSelector("textField")).sendKeys("admin");
			
		}
		catch(Exception e)
		{
			String actual=e.getMessage();
			System.out.println(actual);
			String expect="no-such-element-exception";
			Assert.assertTrue(actual.contains(expect));
			System.out.println("PASS");
		
		}		
			//WebDriverException
		//NoSuchElementException: assertion
	}
	
	@Test(enabled=false)
	public void Scenario2()// use window handle and open multiple windows
	{
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://the-internet.herokuapp.com/");
		List<WebElement> ll=driver.findElements(By.xpath("//table"));
		int size=ll.size();
		Assert.assertEquals(0,size,"Test Script Failed due to no exception/ size is not zero");
		for(WebElement links:ll)//
		{
			System.out.println(links.getText());
		}
		System.out.println(ll.size());
		int expect=ll.size();
	}
	
	@Test
	public void Multitabs()throws Throwable
	{
		WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Open the websites in tabs
        driver.get("https://www.amazon.in");//4
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://www.flipkart.com");//2
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://www.facebook.com");//3
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://www.google.com");//1

        Set<String> handles = driver.getWindowHandles();
        Iterator<String> iterator = handles.iterator();

        // Close Google
        while (iterator.hasNext()) {
            String handle = iterator.next();
            driver.switchTo().window(handle);
            if (driver.getCurrentUrl().contains("google.com")) {
            	Thread.sleep(2000);
                driver.close();
                iterator.remove();
                break;
            }
        }

        // Close Flipkart
        iterator = handles.iterator();  // Reset the iterator
        while (iterator.hasNext()) {
            String handle = iterator.next();
            driver.switchTo().window(handle);
            if (driver.getCurrentUrl().contains("flipkart.com")) {
            	Thread.sleep(2000);
                driver.close();
                iterator.remove();
                break;
            }
        }

        // Close Facebook
        iterator = handles.iterator();  // Reset the iterator
        while (iterator.hasNext()) {
            String handle = iterator.next();
            driver.switchTo().window(handle);
            if (driver.getCurrentUrl().contains("facebook.com")) {
            	Thread.sleep(2000);
                driver.close();
                iterator.remove();
                break;
            }
        }

        // Close Amazon
        iterator = handles.iterator();  // Reset the iterator
        while (iterator.hasNext()) {
            String handle = iterator.next();
            driver.switchTo().window(handle);
            if (driver.getCurrentUrl().contains("amazon.in")) {
            	Thread.sleep(2000);
                driver.close();
                break;
            }
        }
	}
}
