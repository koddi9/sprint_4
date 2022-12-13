import io.github.bonigarcia.wdm.WebDriverManager;
import model.RentModel;
import model.UserModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import page_object.PageMain;
import page_object.PageOrder;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderTest {

    WebDriver driver;
    UserModel user;
    RentModel rentData;
    PageMain.OrderButtonDirection direction;

    public OrderTest(UserModel user, RentModel rentData, PageMain.OrderButtonDirection direction) {
        this.user = user;
        this.rentData = rentData;
        this.direction = direction;
    }

    @Before
    public void init() {
        // Запускаем браузер
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();

        // Переходим на сайт
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Test
    public void positiveTest_TakeAnOrder() {
        PageMain pageMainObj = new PageMain(driver);

        pageMainObj.clickOrderButton(direction);

        PageOrder pageOrderObj = new PageOrder(driver);

        boolean result = pageOrderObj.takeAnOrder(user, rentData);

        // Если заказ успешно выполнен, result = true
        assertTrue(result);
    }

    // Тестовые данные
    @Parameterized.Parameters
    public static Object[][] getCredentials() {
        return new Object[][]{
                {
                        new UserModel("Андрей", "Михайлов", "Улица Пушкина", "Черкизовская", "+7911234567"),
                        new RentModel("01.01.2023", 1), PageMain.OrderButtonDirection.HEADER_BUTTON},
                {
                        new UserModel("Максим", "Иванов", "Дом Колотушкина", "Чистые пруды", "+7999123321"),
                        new RentModel("31.12.2022", 3), PageMain.OrderButtonDirection.LOWER_BUTTON},
                {
                        new UserModel("Кирилл", "Аврилов", "Улица Ленина", "Спортивная", "+7921111222"),
                        new RentModel("08.09.2023", 7), PageMain.OrderButtonDirection.HEADER_BUTTON}
        };
    }

    @After
    public void destructor() {
        driver.quit();
    }
}
