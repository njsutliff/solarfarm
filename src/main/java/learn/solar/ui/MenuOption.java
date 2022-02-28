package learn.solar.ui;

public enum MenuOption {
    EXIT("Exit"),
    FIND_BY_SECTION("Find by Section"),
    ADD_PANEL("Add Panel"),
    UPDATE_PANEL("Update Panel"),
    REMOVE_PANEL("Remove Panel");

    MenuOption(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    private final String title;
}
