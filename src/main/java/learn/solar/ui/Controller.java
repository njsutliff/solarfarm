package learn.solar.ui;

import learn.solar.domain.PanelService;

public class Controller {

    private final PanelService service;
    private final View view;

    public Controller(PanelService service, View view) {
        this.service = service;
        this.view = view;
    }

    public void run() {
        System.out.println("Welcome to solarfarm!");
        MenuLoop();
    }

    public void MenuLoop() {
        MenuOption option;
        do {
            option = view.viewMenu();
            System.out.println(option.getTitle());
            switch (option) {
                case EXIT:
                    break;
                case ADD_PANEL:
                    break;
                case REMOVE_PANEL:
                    break;
                case UPDATE_PANEL:
                    break;
                case FIND_BY_SECTION:
                    break;
            }

        } while (option != MenuOption.EXIT);
    }
}
