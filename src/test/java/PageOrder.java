import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageOrder {

    private final WebDriver driver;

    private final By firstNameField = By.xpath("/html/body/div/div/div[2]/div[2]/div[1]/input");
    private final By lastNameField = By.xpath("/html/body/div/div/div[2]/div[2]/div[2]/input");
    private final By addressField = By.xpath("/html/body/div/div/div[2]/div[2]/div[3]/input");
    private final By metroField = By.xpath("/html/body/div/div/div[2]/div[2]/div[4]/div/div/input");
    private final By numberField = By.xpath("/html/body/div/div/div[2]/div[2]/div[5]/input");

    private final By nextButton = By.xpath("/html/body/div/div/div[2]/div[3]/button");

    private final By dateField = By.xpath("/html/body/div/div/div[2]/div[2]/div[1]/div[1]/div/input");
    private final By periodSpan = By.xpath("/html/body/div/div/div[2]/div[2]/div[2]/div/div[2]/span");
    private final By dropMenu = By.xpath("/html/body/div/div/div[2]/div[2]/div[2]/div[2]");

    private final By orderButton = By.xpath("/html/body/div/div/div[2]/div[3]/button[2]");
    private final By confirmationButton = By.xpath("/html/body/div/div/div[2]/div[5]/div[2]/button[2]");
    private final By orderSuccessBlock = By.xpath("/html/body/div/div/div[2]/div[5]/div[1]");

    public PageOrder(WebDriver driver) {
        this.driver = driver;
    }

    public boolean takeAnOrder(UserModel user, RentModel rentData) {
        setUserOrderPageFields(user);

        driver.findElement(nextButton).click();

        waitUntilLocatedByClassName("Order_MixedDatePicker__3qiay");

        setRendDataOrderPageField(rentData);

        driver.findElement(orderButton).click();
        driver.findElement(confirmationButton).click();

        waitUntilLocatedByClassName("Order_Modal__YZ-d3");

        return driver.findElement(orderSuccessBlock).isDisplayed();
    }

    private void setRendDataOrderPageField(RentModel rentData) {
        driver.findElement(dateField).sendKeys(rentData.getDate());
        driver.findElement(periodSpan).click();
        String periodBlock = String.format("//*[@id=\"root\"]/div/div[2]/div[2]/div[2]/div[2]/div[%s]", rentData.getPeriod());
        driver.findElement(dropMenu).findElement(By.xpath(periodBlock)).click();
    }

    private void setUserOrderPageFields(UserModel user) {
        driver.findElement(firstNameField).sendKeys(user.getFirstName());
        driver.findElement(lastNameField).sendKeys(user.getLastName());
        driver.findElement(addressField).sendKeys(user.getAddress());
        driver.findElement(metroField).sendKeys(user.getMetro());
        String metroSelectBlock = String.format(".//div[@class='select-search__select']//*[text()='%s']", user.getMetro());
        driver.findElement(By.xpath(metroSelectBlock)).click();
        driver.findElement(numberField).sendKeys(user.getNumber());
    }

    private void waitUntilLocatedByClassName(String className) {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(By.className(className)));
    }

}
