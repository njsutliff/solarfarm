package learn.solar.ui;

import java.util.Scanner;

public class View {
    private final Scanner console = new Scanner(System.in);

    public  MenuOption viewMenu(){
        MenuOption[] values =  MenuOption.values();
        printHeader("solarfarm menu: ");
        for(int i = 0; i < values.length; i++){
            System.out.printf("%s. %s%n", i, values[i].getTitle());
        }
        int index = readInt("Select [ 0 - 4 ]",0, 4);
        return  values[index];

    }
    public void printHeader(String message){
        System.out.println( );
        System.out.println(message);
        System.out.println("=".repeat(message.length()));
    }


    private String readString(String stringToRead) {
        System.out.print(stringToRead);
        return console.nextLine();
    }

    private String readRequiredString(String stringToRead) {
        String result;
        do {
            result = stringToRead.trim();
            if (result.length() == 0) {
                System.out.println("Empty string.");
            }
        } while (!stringToRead.isBlank());
        return result;
    }

    private int readInt(String stringToParseToInt) {
        String input = null;
        int result = 0;
        try {
            input = readRequiredString(stringToParseToInt);
            result = Integer.parseInt(input);
        } catch (NumberFormatException n) {
            n.printStackTrace();
        }
        System.out.print(Integer.parseInt(stringToParseToInt));
        return result;
    }

    private int readInt(String stringToParseToInt, int min, int max) {
        String s = readString(stringToParseToInt);
        int result = 0;
        do{
            result = readInt(s);
            if (result < min || result > max) {
                System.out.printf("Value must be between %s and %s.%n", min, max);
            }
        }while (result < min || result>max);
        return 0;
    }

}
