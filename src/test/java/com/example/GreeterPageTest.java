package com.example;

import static org.testng.Assert.assertEquals;

import java.nio.file.Paths;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class GreeterPageTest extends BaseTest {

    private static final String PAGE_URL =
            Paths.get("src", "test", "resources", "site", "index.html")
                    .toAbsolutePath().toUri().toString();

    @BeforeMethod
    public void openPage() {
        driver.get(PAGE_URL);
    }

    @Test
    public void pageTitleIsCorrect() {
        assertEquals(driver.getTitle(), "Greeter Demo");
    }

    @DataProvider(name = "names")
    public Object[][] names() {
        return new Object[][] { {"Alice"}, {"Bob Smith"}, {"Carol"} };
    }

    @Test(dataProvider = "names")
    public void greetsByName(String name) {
        driver.findElement(By.id("name")).sendKeys(name);
        driver.findElement(By.id("greet")).click();
        assertEquals(driver.findElement(By.id("message")).getText(), "Hello, " + name + "!");
    }

    @Test
    public void emptyNameShowsError() {
        driver.findElement(By.id("greet")).click();
        assertEquals(driver.findElement(By.id("error")).getText(), "Please enter a name");
    }
}
