package nchunakova.qa.domain;

public enum TestEnumItem {

    FIRSTBUTTON ("За все время"),
    SECONDBUTTON ("За последний месяц"),
    THIRDBUTTON ("За последний год"),
    FOURTHBUTTON ("Выбрать период");

    public final String searchFilter;

    TestEnumItem(String searchFilter) {
        this.searchFilter = searchFilter;
    }
}
