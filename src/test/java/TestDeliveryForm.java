import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static java.time.Duration.ofMillis;

public class TestDeliveryForm {

    @BeforeEach

    @Test
    void shouldSuccessfullyDeliveryForm() { // Simply test without options for set date
        open("http://localhost:9999");
        $("[data-test-id = city] input").setValue("Самара");
        $("[placeholder='Дата встречи']").sendKeys(Keys.CONTROL + "A" + Keys.DELETE);
        $("[placeholder='Дата встречи']").setValue("20.01.2023");
        $("[name='name']").setValue("Иван Комаров");
        $("[name='phone']").setValue("+79128090324");
        $(".checkbox__box").click();
        $("[role = 'button'] .button__text").click();
        $(withText("Успешно!")).shouldBe(visible, ofMillis(15000));
    }
}

