package page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PageMain {

    private final WebDriver driver;
    private final By headerOrderButton = By.xpath("/html/body/div/div/div/div[1]/div[2]/button[1]");
    private final By lowerOrderButton = By.xpath("/html/body/div/div/div/div[4]/div[2]/div[5]/button");
    private final By questionsDivBlock = By.xpath("/html/body/div/div/div/div[5]/div[2]/div");

    public PageMain(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOrderButton(OrderButtonDirection dir) {
        By button = null;
        switch (dir) {
            case HEADER_BUTTON:
                button = headerOrderButton;
                break;
            case LOWER_BUTTON:
                button = lowerOrderButton;
                scrollToLowerButton();
                break;
        }

        if (button != null) {
            driver.findElement(button).click();
        }
    }

    // Проверка на наличие ответов у всех вопросов
    public boolean HaveTextInQuestionsBlock() {
        List<WebElement> questionsItems = getAllItems();
        for (int i = 0; i < questionsItems.size(); i++) {
            WebElement item = questionsItems.get(i);
            String text = getTextFromPanel(i, item);
            if (text.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    // Получение значения элемента <p> из соответствующего item из списка вопросов
    public String getTextFromPanel(int panelIndex) {
        return getTextFromPanel(panelIndex, getAllItems().get(panelIndex));
    }

    private String getTextFromPanel(int i, WebElement item) {
        // Выборка кнопки и скролл к элементу
        WebElement element = item.findElement(By.className("accordion__button"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        // Открытие блока ответа и получение текста
        element.click();
        String pSelector = String.format("//*[@id=\"accordion__panel-%s\"]/p", i);
        return driver.findElement(By.xpath(pSelector)).getText();
    }

    private List<WebElement> getAllItems() {
        return driver.findElement(questionsDivBlock).findElements(By.className("accordion__item"));
    }

    private void scrollToLowerButton() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(lowerOrderButton));
    }

    public enum OrderButtonDirection {
        HEADER_BUTTON, LOWER_BUTTON
    }
}
