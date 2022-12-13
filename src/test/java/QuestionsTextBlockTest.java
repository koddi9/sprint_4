import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import page_object.PageMain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class QuestionsTextBlockTest {

    int questionNumber;
    String answer;

    public QuestionsTextBlockTest(int questionNumber, String answer) {
        this.questionNumber = questionNumber;
        this.answer = answer;
    }

    WebDriver driver;

    @Before
    public void init() {
        // Запускаем браузер
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();

        // Переходим на сайт
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Test
    public void positiveTest_AnswersAreNotEmpty() {
        PageMain pageMainObj = new PageMain(driver);

        boolean result = pageMainObj.HaveTextInQuestionsBlock();

        // Если все ответы на вопросы заданы, result = true
        assertTrue(result);
    }

    @Test
    public void positiveTest_AnswersAreEquals() {
        PageMain pageMainObj = new PageMain(driver);

        String result = pageMainObj.getTextFromPanel(questionNumber);

        assertEquals(answer, result);
    }

    // Тестовые данные
    @Parameterized.Parameters
    public static Object[][] getCredentials() {
        return new Object[][]{
                {0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."}
        };
    }

    @After
    public void destructor() {
        driver.quit();
    }
}
