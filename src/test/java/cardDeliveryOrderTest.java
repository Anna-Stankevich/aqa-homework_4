import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import static com.codeborne.selenide.Selenide.*;

public class cardDeliveryOrderTest {

    String generateDate(int days){
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
    
    @BeforeEach
    void setUp() {
        open("http://localhost:9999/");
    }

    @AfterEach
    void tearDown() {
        clearBrowserCookies();
        closeWebDriver();
    }

    @Test
    void shouldTestForm(){
        $("[data-test-id='city'] input").setValue("Казань");
        String date = generateDate(4);
        SelenideElement data = $("[data-test-id='date'] input");
        data.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        data.setValue(date);
        $("[data-test-id='name'] input").setValue("Иван Петров");
        $("[data-test-id='phone'] input").setValue("+79000000000");
        $("[data-test-id='agreement']").click();
        $$("button").find(Condition.exactText("Забронировать")).click();
        $("[data-test-id='notification'] button").shouldBe(Condition.visible, Duration.ofSeconds(15));
    }
}
