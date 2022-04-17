package nchunakova.qa.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class WebTest {

    @CsvSource(value = {
            "амурский лесной | Авторы Художник: А.С. Кунац. Скульптор: Е.И. Новикова. Чеканка: Санкт-Петербургский " +
                    "монетный двор (СПМД). Оформление гурта: 252 рифления.",
            "кота в мешке | Мисселинг, или Как не купить кота в мешке"
    },
            delimiter = '|'
    )

    @ParameterizedTest(name = "Поиск по слову {0}, ожидаем результат: {1}")
    void tableComplexTest(String testData, String expectedResult) {
//        Предусловия:
        Selenide.open("https://www.cbr.ru/");
//        Шаги
        $(".home-header_search_inp").setValue(testData).pressEnter();
//        Ожидаемый результат:
        $$(".subtitle")
                .find(Condition.text(expectedResult))
                .shouldBe(Condition.visible);
    }

    @AfterEach
    void close() {
        Selenide.closeWebDriver();
    }
}