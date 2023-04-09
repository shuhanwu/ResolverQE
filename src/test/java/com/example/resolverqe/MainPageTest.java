package com.example.resolverqe;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainPageTest {
    MainPage mainPage = new MainPage();
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp() {
        open(System.getProperty("user.dir") + "/QE-index.html");
    }

    /*
    Test 1
    Navigate to the home page
    Assert that both the email address and password inputs are present as well as the login button
    Enter in an email address and password combination into the respective fields
     */
    @Test
    public void Test1_SampleLogin() {
        assertEquals(true, mainPage.inputEmail.isDisplayed());
        mainPage.inputEmail.sendKeys("test@email.com");
        assertEquals(true, mainPage.inputPassword.isDisplayed());
        mainPage.inputPassword.sendKeys("password");
        assertEquals(true, mainPage.buttonLogin.isDisplayed());
        mainPage.buttonLogin.click();
    }

    /*
    Test 2
    Navigate to the home page
    In the test 2 div, assert that there are three values in the listgroup
    Assert that the second list item's value is set to "List Item 2"
    Assert that the second list item's badge value is 6
     */
    @Test
    public void Test2_GetListValue() {
        List<WebElement> list = mainPage.divListGroup.findElements(By.xpath("//li[contains(text(), 'List Item')]"));
        assertEquals(3, list.size());

        List<WebElement> badgeList = mainPage.divListGroup.findElements(By.xpath("//li[contains(text(), 'List Item')]/span"));
        assertEquals("6", badgeList.get(1).getText().trim());

        assertEquals("List Item 2", list.get(1).getText().replace(badgeList.get(1).getText(), "").trim());
    }

    /*
    Test 3
    Navigate to the home page
    In the test 3 div, assert that "Option 1" is the default selected value
    Select "Option 3" from the select list
     */
    @Test
    public void Test3_SelectOptions() {
        assertEquals("Option 1", mainPage.dropdownMenuButton.getText().trim());
        assertEquals("Option 1", mainPage.dropdownMenuButton.shouldHave(text("Option 1")).getText());

        mainPage.dropdownMenuButton.shouldBe(have(text("Option 1")));

        mainPage.dropdownMenuButton.click();
        mainPage.dropdownMenuButton.findElement(By.xpath("//a[contains(text(), 'Option 3')]")).click();
        assertEquals("Option 3", mainPage.dropdownMenuButton.getText().trim());
    }

    /*
    Test 4
    Navigate to the home page
    In the test 4 div, assert that the first button is enabled and that the second button is disabled
     */
    @Test
    public void Test4_ButtonEnabledDisabled() {
        assertEquals(true, mainPage.buttonEnabled.isEnabled());
        assertEquals(false, mainPage.buttonDisabled.isEnabled());
    }


    /*
    Test 5
    Navigate to the home page
    In the test 5 div, wait for a button to be displayed (note: the delay is random) and then click it
    Once you've clicked the button, assert that a success message is displayed
    Assert that the button is now disabled
 */
    @Test
    public void Test5_ButtonClickDisabled() {
        while (!mainPage.test5Button.isDisplayed()){
            new CountDownLatch(1000).countDown();
        }
        mainPage.test5Button.shouldBe(visible);
        mainPage.test5Button.click();
        mainPage.test5AlertSuccess.shouldBe(visible);
        mainPage.test5Button.shouldBe(disabled);
    }

    /*
    Test 6
    Navigate to the home page
    Write a method that allows you to find the value of any cell on the grid
    Use the method to find the value of the cell at coordinates 2, 2 (staring at 0 in the top left corner)
    Assert that the value of the cell is "Ventosanzap"
    */
    @Test
    public void Test6_VerifyCellValue() {
        assertEquals("Ventosanzap", FindCellValue(2, 2));
    }

    @Test
    public String FindCellValue(int row, int col) {
        return mainPage.test6Table.findElement(By.cssSelector(String.format("tr:nth-child(%d) > td:nth-child(%d)", row + 1, col + 1))).getText().trim();
    }

}