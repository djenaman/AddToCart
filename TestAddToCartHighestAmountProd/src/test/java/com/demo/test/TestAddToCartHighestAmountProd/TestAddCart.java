package com.demo.test.TestAddToCartHighestAmountProd;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestAddCart {
	private WebDriver driver;

	@BeforeClass
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.saucedemo.com/");
		driver.manage().window().maximize();
	}

	@Test
	public void addHighestAmountProduct() {
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();
		List<WebElement> allPriceTags = driver.findElements(By.xpath("//div[@class='inventory_item_price']"));
		double highestPrice = 0;
		int targetElementIndex = 0;
		for (int i = 1; i <= allPriceTags.size(); i++) {
			double currentItemPrice = Double.parseDouble(allPriceTags.get(i - 1).getText().split("\\$")[1]);
			if (currentItemPrice > highestPrice) {
				highestPrice = currentItemPrice;
				targetElementIndex = i;
			}
		}
		driver.findElement(By.xpath("(//div[@class='inventory_item_price']/following-sibling::button)[" + targetElementIndex + "]")).click();
		driver.findElement(By.id("shopping_cart_container")).click();
	}
}