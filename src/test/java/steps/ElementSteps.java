
package steps;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.en.*;
import java.time.Duration;
import io.qameta.allure.Step;
import java.io.File;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;

public class ElementSteps {
    WebDriver driver;

    @Given("I open the application")
    public void i_open_the_application() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://example.com");
    }

    @When("I enter {string} into the input with id {string}")
    public void i_enter_text(String text, String id) {
        driver.findElement(By.id(id)).sendKeys(text);
    }

    @When("I click the button with id {string}")
    public void i_click_button(String id) {
        driver.findElement(By.id(id)).click();
    }

    @When("I select {string} from dropdown with id {string}")
    public void i_select_dropdown(String option, String id) {
        WebElement dropdown = driver.findElement(By.id(id));
        dropdown.findElement(By.xpath(String.format(".//option[text()='%s']", option))).click();
    }

    @When("I check the checkbox with id {string}")
    public void i_check_checkbox(String id) {
        WebElement checkbox = driver.findElement(By.id(id));
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

    @When("I upload file {string} into input with id {string}")
    public void i_upload_file(String file, String id) {
        driver.findElement(By.id(id)).sendKeys(System.getProperty("user.dir") + "/" + file);
    }

    @Step("Verifico se o texto '{0}' está presente na página")
    @Then("I should see text {string} on the page")
    public void i_should_see_text(String text) {
        if (!driver.getPageSource().contains(text)) {
            throw new AssertionError("Text not found: " + text);
        }
        
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("target/screenshot.png"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }

    }
}
