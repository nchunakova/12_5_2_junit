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
            "Alden | 12000",
            "Kierra | 2000"
    },
            delimiter = '|'
    )

    @ParameterizedTest(name = "Поиск по имени {0}, ожидаем результат: {1}")
    void tableComplexTest(String testData, String expectedResult) {
//        Предусловия:
        Selenide.open("https://demoqa.com/webtables");
//        Шаги
        $("#searchBox").setValue(testData);
        //$("#submit").click();
//        Ожидаемый результат:
        $$(".rt-td")
                .find(Condition.text(expectedResult))
                .shouldBe(Condition.visible);
    }

    @AfterEach
    void close() {
        Selenide.closeWebDriver();
    }
}
