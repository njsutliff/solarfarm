package learn.solar.ui;

import learn.solar.data.DataException;
import learn.solar.domain.PanelResult;
import learn.solar.domain.PanelService;
import learn.solar.models.Panel;

import java.util.List;

public class Controller {

    private final PanelService service;
    private final View view;

    public Controller(PanelService service, View view) {
        this.service = service;
        this.view = view;
    }

    public void run() throws DataException {
        System.out.println("Welcome to solarfarm!");
        try {
            MenuLoop();
        }catch (DataException e){
            view.printHeader("FATAL ERROR" + e);
        }
    }

    public void MenuLoop() throws DataException {
        MenuOption option;
        do {
            option = view.viewMenu();
            System.out.println(option.getTitle());
            switch (option) {
                case EXIT:
                    view.printHeader(MenuOption.EXIT.getTitle());
                    break;
                case ADD_PANEL:
                    addPanel();
                    break;
                case REMOVE_PANEL:
                    deletePanel();
                    break;
                case UPDATE_PANEL:
                    updatePanel();
                    break;
                case FIND_BY_SECTION:
                    viewBySection();
                    break;
            }

        } while (option != MenuOption.EXIT);
    }
    private void viewBySection() throws DataException {
        view.printHeader(MenuOption.FIND_BY_SECTION.getTitle());
        String s = view.readSection();
        List<Panel> results = service.findBySection(s);
        view.viewPanels(results);

    }
    private  void addPanel() throws DataException {
        view.printHeader(MenuOption.ADD_PANEL.getTitle());
        Panel p = view.makePanel();
        PanelResult result = service.add(p);
        System.out.println(result.getMessages());
    }
    private  void updatePanel() {
        view.printHeader(MenuOption.UPDATE_PANEL.getTitle());
    }
    private  void deletePanel() {
        view.printHeader(MenuOption.REMOVE_PANEL.getTitle());

    }


}
