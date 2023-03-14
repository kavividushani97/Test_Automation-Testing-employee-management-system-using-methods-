package assesment;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

		public class User_Test {
			WebDriver driver;
			String url="http://localhost/employeeuser/admin/";
			public static void main(String[] args) {
				User_Test test=new User_Test();
				test.callBrowser();
				test.passingUsernameAndPassword("admin@gmail.com","admin@12345");
				test.adminLogin();
				test.clickUserAndURLValidation();
				test.searchUser("Ad");
				test.addNewUser("ram@gmail.com","ram@12345","Ram","Gopal");
				test.updateUser("User");
				test.deleteUser();
				test.logoutUser();
			}
			
			void callBrowser() {
				System.setProperty("webdriver.chorme.driver", "chromedriver.exe");
				driver=new ChromeDriver();
				driver.manage().window().maximize();
				driver.manage().deleteAllCookies();
				driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
				driver.get(url);
			}
			
			void passingUsernameAndPassword(String userName,String userPassword) {
				driver.findElement(By.xpath("/html/body/div/div/div/div/div/div/div[2]/form/div[1]/input")).sendKeys(userName);
				driver.findElement(By.xpath("/html/body/div/div/div/div/div/div/div[2]/form/div[2]/input")).sendKeys(userPassword);	
			}
			
			void adminLogin() {
				WebElement inputUserName = driver.findElement(By.name("username"));
				WebElement inputUserPassword = driver.findElement(By.name("password"));
						
				if(inputUserName.getAttribute("value").isEmpty()) {
					System.out.println("Username is Empty");
				}
				else if(inputUserPassword.getAttribute("value").isEmpty()) {
					System.out.println("Password is Empty");
				}
				else {
					driver.findElement(By.xpath("/html/body/div/div/div/div/div/div/div[2]/form/button[1]")).click();
				}
			}
			
			void clickUserAndURLValidation() {
				driver.findElement(By.xpath("/html/body/div[1]/div[4]/a")).click();
				String url = driver.getCurrentUrl();
				String expectedUrl="http://localhost/employeeuser/admin/users.php";
				if(url.equals(expectedUrl)) {
					System.out.println("URL Validation Success!");
				}
				else {
					System.out.println("URL Validation Failed!");
				}
			}
			
			void searchUser(String name) {
				driver.findElement(By.xpath("/html/body/div[2]/div[4]/div/div[2]/label/input")).sendKeys(name);
			}
			
			void addNewUser(String username, String password, String firstname, String lastname) {
				driver.findElement(By.xpath("//*[@id=\"new_user\"]")).click();
				driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS); 
				driver.findElement(By.xpath("//*[@id=\"user-frm\"]/div[1]/div[1]/input")).sendKeys(username);
				driver.findElement(By.xpath("//*[@id=\"user-frm\"]/div[1]/div[2]/input")).sendKeys(password);
				driver.findElement(By.xpath("//*[@id=\"user-frm\"]/div[1]/div[3]/input")).sendKeys(firstname);
				driver.findElement(By.xpath("//*[@id=\"user-frm\"]/div[1]/div[4]/input")).sendKeys(lastname);
				driver.findElement(By.xpath("/html/body/div[3]/div/div/form/div[2]/button")).click();
				driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS); 
				String s= driver.switchTo().alert().getText();
				System.out.println("User Saving Alert Message is : "+s);
				driver.switchTo().alert().accept();
			}
			
			void updateUser(String lastName) {
				searchUser("Gopal");
				driver.findElement(By.xpath("/html/body/div[2]/div[4]/div/table/tbody/tr/td[4]/center/button[1]")).click();
				driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS); 
				driver.findElement(By.xpath("//*[@id=\"user-frm\"]/div[1]/div[4]/input")).sendKeys(lastName);
				driver.findElement(By.xpath("/html/body/div[3]/div/div/form/div[2]/button")).click();
				driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
				String s= driver.switchTo().alert().getText();
				System.out.println("User Updating Alert Message is : "+s);
				driver.switchTo().alert().accept();
			}
			
			void deleteUser() {
				searchUser("ram");
				driver.findElement(By.xpath("/html/body/div[2]/div[4]/div/table/tbody/tr/td[4]/center/button[2]")).click();
				driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
				String s= driver.switchTo().alert().getText();
				System.out.println("User Deleting Alert Message is : : "+s);
				driver.switchTo().alert().accept();
			}
			
			
			void logoutUser() {
				driver.findElement(By.xpath("/html/body/nav/div/div[2]/a")).click();
			


	}

}
