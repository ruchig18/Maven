package maventest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class magento {
	@Test
	public void register(){
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		
		 driver.manage().window().maximize();
		 driver.manage().deleteAllCookies();
		 
		 driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);//giving time for buffering etc
		
		
		 driver.get("http://magento.com");//will get to a specific URL
		 driver.findElement(By.xpath("//*[@id=\"block-header\"]/ul/li[9]/a/span[1]/div")).click();
		 driver.findElement(By.id("register")).click();
		 driver.findElement(By.id("firstname")).sendKeys("ruchi");
		 driver.findElement(By.id("lastname")).sendKeys("gupta");
		 driver.findElement(By.id("email_address")).sendKeys("ruchig18@yahoo.com");
		 driver.findElement(By.id("self_defined_company")).sendKeys("pmrdigite");
		Select select =new Select(driver.findElement(By.xpath("//*[@id=\"company_type\"]")));//used for drop down
		select.selectByVisibleText("Is a merchant who sells online");
		Select select1 =new Select(driver.findElement(By.id("individual_role")));
		select1.selectByVisibleText("Technical/developer");
		//select1.selectByIndex(index);// can select usng index value
		//select1.selectByValue(arg0);//can select using value
		Select select2 =new Select(driver.findElement(By.id("country")));
		select2.selectByVisibleText("Canada");
		 //driver.findElement(By.id("email_address")).sendKeys("ruchig18@yahoo.com");
       driver.findElement(By.id("password")).sendKeys("welcomewelcome12@");
       driver.findElement(By.id("password-confirmation")).sendKeys("welcomewelcome12@");
       //to go on another frame inside main page
       driver.switchTo().frame(driver.findElement(By.xpath("//*[@id=\"recaptcha-f979c2ff515d921c34af9bd2aee8ef076b719d03\"]/div/div/iframe")));
       driver.findElement(By.id("recaptcha-anchor")).click();//click checkbox of captcha
       driver.switchTo().defaultContent();//again swich out of frame
       if (!driver.findElement(By.id("agree_terms")).isSelected())//to check if check box is not already selected, ! is used 
       driver.findElement(By.id("agree_terms")).click();
       //driver.quit();
	}
	
	@Test(dependsOnMethods= {"register"})
	public void login() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		// driver.close();//closes the initialised web browesr
		 driver.manage().window().maximize();
		 driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);//giving time for buffering etc
		
		 driver.get("http://magento.com");//will get to a specific URL
		 driver.findElement(By.xpath("//*[@id=\"block-header\"]/ul/li[9]/a/span[1]/div")).click();//to find the search bar by locator name and type selenium
         driver.findElement(By.id("email")).sendKeys("ruchig18@yahoo.com");
         driver.findElement(By.id("pass")).sendKeys("welcome");
         driver.findElement(By.id("send2")).click();
         Thread.sleep(5000);   //force sleeping required for reading the text
        // System.out.println(driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[1]/div[2]/div/div/div")).getText());
        String x= driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[1]/div[2]/div/div/div")).getText();
        System.out.println(x);
         
         //String text=driver.;
         if(x.equals("Invalid login or password."))
        	 System.out.println("test pass");
         else System.out.println("test fail");
         driver.quit();
	
	}

}



