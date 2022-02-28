package learn.solar.ui;

import learn.solar.data.PanelFileRepository;
import learn.solar.data.PanelRepository;
import learn.solar.domain.PanelResult;
import learn.solar.models.Material;
import learn.solar.models.Panel;

import java.io.FilterOutputStream;
import java.util.List;
import java.util.Scanner;

public class View {
    private final Scanner console = new Scanner(System.in);

    public  MenuOption viewMenu(){
        MenuOption[] values =  MenuOption.values();
        printHeader("solarfarm menu: ");
        for(int i = 0; i < values.length; i++){
            System.out.printf("%s. %s%n", i, values[i].getTitle());
        }
        int index = readInt("Select [ 0 - 4 ] ",0, 4);
        return  values[index];

    }
    public void printHeader(String message){
        System.out.println( );
        System.out.println(message);
        System.out.println("=".repeat(message.length()));
    }
    public void printResult(PanelResult result){
        System.out.printf("%s",result.isSuccess(), result.getMessages());
    }

    public  void viewPanels(List<Panel> list){
        if(list.size() == 0){
            System.out.println("No Panels found");
        }
        for(Panel p : list){
            System.out.printf("%s%s %n %s%n %s%n %s%n %s%n %s %n",
                    "[Panel ID: " + p.getId(),
                    " Section: " +p.getSection() + "]",
                    "Row: " + p.getRow(),
                    "Column: " +p.getColumn(),
                    "Installation Year: " +p.getInstallationYear(),
                    "Material: " +p.getMaterial(),
                    "Tracking?: " +p.isTracking());

        }
    }
    public String readSection(){
        System.out.println("Enter a section: ");
        return readString("");
    }
    public Panel makePanel() {
        printHeader("create a new Panel now: ");
        boolean done = false;
        Panel p;

            System.out.println("Enter a panel ID#:  ");
            int Id = readInt("");
            System.out.println(Id);

            String section = readSection();

            int row = readInt("enter a row");
            int col = readInt("enter a column");
            int year = readInt("Enter an installation year");
            Material[] values = Material.values();
            printHeader("pick a material for the solar panel: ");
            for (int i = 0; i < values.length; i++) {
                System.out.printf("%s. %s%n", i, values[i]);
            }
            int index = readInt("Select [ 0 - 4 ] ", 0, 4);
            Material material = values[index];
            boolean b = Boolean.parseBoolean(readString("Is it tracking? true or false: "));

            p = new Panel(Id, section, row, col, year, material, b);
        System.out.println(" Success! New panel created. ");
        return p;
    }


    private String readString(String message) {
        System.out.println(message);
        return console.nextLine();
    }

    private String readRequiredString(String stringToRead) {
        String result = null;
        do {
            result = readString(stringToRead).trim();
            if (result.length() == 0) {
                System.out.println("Empty string.");
            }
        } while (result.length() == 0);
        return result;
    }

    private int readInt(String stringToParseToInt) {
        String value = null;
        int result = 0;
        boolean valid = false;
        do {
            try {
                value = readRequiredString(stringToParseToInt);
                result = Integer.parseInt(value);
                valid = true;
            } catch (NumberFormatException n) {
                n.printStackTrace();
            }
        } while(!valid);
        return result;
    }

    private int readInt(String stringToParseToInt, int min, int max) {

        int result = 0;
        do{
            result = readInt(stringToParseToInt);
            if (result < min || result > max) {
                System.out.printf("Value must be between %s and %s.%n", min, max);
            }
        }while (result < min || result>max);
        return result;
    }


}
