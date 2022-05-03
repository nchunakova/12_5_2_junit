package nchunakova.qa.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import nchunakova.qa.domain.TestEnumItem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import java.util.stream.Stream;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class WebTest {

    @Disabled
    @ParameterizedTest(name = "Проверка поиска на сайте центробанка по слову {0}")
    @ValueSource(strings = {
            "ПНД",
            "Санкции"
    })
    void yaSearchTest(String testData) {
        Selenide.open("https://www.cbr.ru/");
        $(".home-header_search_inp").setValue(testData).pressEnter();
        $$(".subtitle").find(Condition.text(testData)).shouldBe(Condition.visible);
    }

    @Disabled
    @ParameterizedTest(name = "Поиск по слову {0}, ожидаем результат: {1}")
    @CsvSource(value = {
            "амурский лесной | Авторы Художник: А.С. Кунац. Скульптор: Е.И. Новикова. Чеканка: Санкт-Петербургский " +
                    "монетный двор (СПМД). Оформление гурта: 252 рифления.",
            "кота в мешке | Мисселинг, или Как не купить кота в мешке"
    },
            delimiter = '|'
    )
    void tableComplexTest(String testData, String expectedResult) {
        Selenide.open("https://www.cbr.ru/");
        $(".home-header_search_inp").setValue(testData).pressEnter();
        $$(".subtitle").find(Condition.text(expectedResult)).shouldBe(Condition.visible);
    }

    @DisplayName("Смена фильтров для результата выдачи поиска через enum")
    @EnumSource(TestEnumItem.class)
    @ParameterizedTest()
    void yaSearchMenuTest(TestEnumItem testData) {
        Selenide.open("https://www.cbr.ru/");
        $(".home-header_search_inp").setValue("ПНД").pressEnter();
        $(withText("За все время")).click();
        $$(".filter-select_option").find(Condition.text(testData.searchFilter)).click();
        //System.out.println(TestEnumItem.FIRSTBUTTON.searchFilter);
    }

    static Stream<Arguments> stringProvider() {
        return Stream.of(
                Arguments.of("амурский лесной", "Авторы Художник: А.С. Кунац. Скульптор: Е.И. Новикова." +
                        " Чеканка: Санкт-Петербургский монетный двор (СПМД). Оформление гурта: 252 рифления."),
                Arguments.of("кота в мешке", "Мисселинг, или Как не купить кота в мешке")
        );
    }

    @ParameterizedTest
    @DisplayName("Простой пример MethodSource: ищем по тексту {0} подзаголовок {1} в результате поисковой выдачи сайта")
    @MethodSource("stringProvider")
    void testWithMultiArgMethodSource(String first, String second) {
        Selenide.open("https://www.cbr.ru/");
        $(".home-header_search_inp").setValue(first).pressEnter();
        $$(".subtitle").find(Condition.text(second)).shouldBe(Condition.visible);
    }

    @AfterEach
    void close() {
        Selenide.closeWebDriver();
    }
}
