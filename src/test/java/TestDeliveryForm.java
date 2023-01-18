import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static java.time.Duration.ofMillis;

public class TestDeliveryForm {

    public static String generateDate(int change){
        return LocalDate.now().plusDays(change).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    void shouldSuccessfullyDeliveryForm() { // Simply test without options for set date
        open("http://localhost:9999");
        int daysAdd = 4;
        String planningDate = generateDate(daysAdd);
        $("[data-test-id = city] input").setValue("Самара");
        $("[placeholder='Дата встречи']").sendKeys(Keys.CONTROL + "A" + Keys.DELETE);
        $("[placeholder='Дата встречи']").setValue(planningDate);
        $("[name='name']").setValue("Иван Комаров");
        $("[name='phone']").setValue("+79128090324");
        $(".checkbox__box").click();
        $("[role = 'button'] .button__text").click();
        $(withText("Успешно!")).shouldBe(visible, ofMillis(15000));
        $(".notification__content")
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }
}

