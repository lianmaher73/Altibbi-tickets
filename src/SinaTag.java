import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SinaTag {
	WebDriver driver = new ChromeDriver();
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	Actions actions = new Actions(driver);
	Random random = new Random();
	SoftAssert myAssert = new SoftAssert();
	String pageURL = "https://web.altibb.com/%D8%A7%D9%84%D8%A7%D8%AF%D9%88%D9%8A%D8%A9";

	@BeforeTest
	public void befor() {
		driver.get(pageURL);
		String whatsappWidget = "/html/body/a[1]/div[1]/div[2]";
		driver.findElement(By.xpath(whatsappWidget)).click();
		driver.manage().window().maximize();

	}

	@Test(priority = 1, invocationCount = 1)
	public void testScientificDrugs() throws InterruptedException {
		driver.get(pageURL);
		actions.moveToElement(driver.findElement(By.className("alphapet-list"))).perform();
		List<WebElement> alphapetList = driver.findElements(By.className("drug_item"));
//		System.out.println(alphapetList.size()); // 20
		int rand = random.nextInt(alphapetList.size());
		WebElement randDrug = alphapetList.get(rand).findElement(By.tagName("div"))
				.findElement(By.className("drug-en-name"));
//		System.out.println("******" + randDrug.getText());
		actions.moveToElement(randDrug).perform();
		randDrug.click();

		actions.moveToElement(driver.findElement(By.className("drug-info-sec-title"))).perform();
		Thread.sleep(200);

		for (int i = 0; i < 3; i++) {
			List<WebElement> Tags = driver.findElement(By.className("desktop-sina"))
					.findElements(By.className("sina-links-main-link"));
			String currentUrl = driver.getCurrentUrl();
			String drugName = driver.findElement(By.className("drug-name-ar")).getText();
//			System.out.println( drugName );

			if (!currentUrl.contains("web")) {
				System.out.println("The drug redirected to the live version ");
				Thread.sleep(500);
				break;
			}

			if (i == 0) {
				String firstTag = Tags.get(i).getText();
				System.out.println(firstTag);
//				System.out.println(drugName);
				myAssert.assertEquals(firstTag, "الاستخدامات", "A mistake in Tags section for ");
				Tags.get(i).click();
				Thread.sleep(500);
//				wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@dir='rlt']"))));
				String actualDrug = driver.findElement(By.xpath("//div[@dir='rlt']")).getText();
				System.out.println("actualDrug ->" + actualDrug);
				System.out.println("drugName -> " + drugName);
				myAssert.assertTrue(actualDrug.contains(drugName), "Sina message does't contain the drug name ");
				driver.navigate().back();

			}

			if (i == 1) {
				String SecondTag = Tags.get(i).getText();
				System.out.println(SecondTag);

				myAssert.assertEquals(SecondTag, "الجرعات وطرق الاستعمال", "a mistake in Tags section");
				Tags.get(i).click();
				Thread.sleep(500);
//				wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@dir='rlt']"))));
				String actualDrug = driver.findElement(By.xpath("//div[@dir='rlt']")).getText();
				System.out.println("actualDrug ->" + actualDrug);
				System.out.println("drugName -> " + drugName);
				myAssert.assertTrue(actualDrug.contains(drugName), "Sina message does't contain the drug name ");
				driver.navigate().back();
			}

			if (i == 2) {
				String thirdTag = Tags.get(i).getText();
				System.out.println(thirdTag);
				myAssert.assertEquals(thirdTag, "الأعراض الجانبية", "a mistake in Tags section");
				Tags.get(i).click();
				Thread.sleep(500);
//				wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@dir='rlt']"))));
				String actualDrug = driver.findElement(By.xpath("//div[@dir='rlt']")).getText();
				System.out.println("actualDrug ->" + actualDrug);
				System.out.println("drugName -> " + drugName);
				myAssert.assertTrue(actualDrug.contains(drugName), "Sina message does't contain the drug name ");
				driver.navigate().back();
			}

		}

		myAssert.assertAll();

	}

	@Test(priority = 2, invocationCount = 1)
	public void sinabotton() throws InterruptedException {
		String[] URL = {
				"https://web.altibb.com/%D8%A7%D9%84%D8%A7%D8%AF%D9%88%D9%8A%D8%A9/%D8%A7%D9%84%D8%A7%D8%B3%D9%85%D8%A7%D8%A1-%D8%A7%D9%84%D8%AA%D8%AC%D8%A7%D8%B1%D9%8A%D8%A9",
				"https://web.altibb.com/%D8%A7%D9%84%D8%A7%D8%AF%D9%88%D9%8A%D8%A9" };
		int rand1 = random.nextInt(URL.length);
		driver.get(URL[rand1]);
//		System.out.println(URL[rand1]);

		actions.moveToElement(driver.findElement(By.className("alphapet-list"))).perform();
		List<WebElement> alphapetList = driver.findElements(By.className("drug_item"));
		int rand = random.nextInt(alphapetList.size());
		Thread.sleep(200);
		WebElement randDrug = alphapetList.get(rand).findElement(By.tagName("div"))
				.findElement(By.className("drug-en-name"));
		String expectedDrug = alphapetList.get(rand).findElement(By.tagName("div"))
				.findElement(By.className("drug-ar-name")).getText();
		Thread.sleep(200);
		actions.moveToElement(randDrug).perform();
		randDrug.click();
		String currentUrl = driver.getCurrentUrl();
		if (!currentUrl.contains("web")) {
			System.out.println("The drug redirected to the live version ");
			Thread.sleep(500);

		}

		actions.moveToElement(driver.findElement(By.className("drug-info-sec-title"))).perform();
		Thread.sleep(200);
		WebElement sinaButton = driver.findElement(By.className("main-sina-drug-link"));
		sinaButton.click();
		Thread.sleep(1000);

		String actualDrug = driver.findElement(By.xpath("//div[@dir='rlt']")).getText();
		System.out.println("sinabotton");
		System.out.println(actualDrug);
		System.out.println(expectedDrug);
		myAssert.assertEquals(actualDrug, expectedDrug, "Sina message does't contain the drug name ");
		myAssert.assertAll();

	}

	@AfterTest
	public void after() {
		driver.quit();

	}

}
