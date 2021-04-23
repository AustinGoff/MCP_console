package MCP;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;
public class Main {
    public static final String TEXT_RESET = "\u001B[0m";
    public static final String TEXT_RED = "\u001B[31m";

    private static void RGBtoHex(){
        System.out.println("enter RGB, format: 200 155 100");
        Scanner scanner = new Scanner(System.in);
        int r = scanner.nextInt();
        int g = scanner.nextInt();
        int b = scanner.nextInt();
        String hex = String.format("#%02X%02X%02X", r, g, b);
        System.out.println("("+r+","+g+","+b+") value = "+hex+" in HEX code.");
        System.out.println(" ");
        System.out.println("enter new command or exit to quit: ");
    }
    private static void HextoRGB(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter HEX, format: #FFCCEE");
        String hex= scanner.next().toUpperCase();
        System.out.print("hex code "+hex+" becomes ");
        Color color = Color.decode(hex);
        String col = String.valueOf(color);
        col = col.substring(col.indexOf("["));
        col.trim();
        System.out.println(col+" in RGB");
        System.out.println(" ");
        System.out.println("enter new command or exit to quit: ");
    }
    private static void menu(){
        System.out.println("current command options include:");
        System.out.println("menu: displays options");
        System.out.println("toHEX: turns RGB into Hex code");
        System.out.println("toRGB: turns hex into RGB color code");
        System.out.println("Fade: create a timed color transformation");
        System.out.println("enter exit to quit: ");
    }
    public static void fadecall() {
        fader call = new fader();
        call.engine();
        System.out.println();
        menu();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String command="";
        System.out.println("------------------------------");
        System.out.println("Moment Console Project command shell");
        System.out.println("Version 1.1");
        System.out.println("enter commands: ");
        System.out.println("-------------------------------");
        menu();

	while(!command.equals("EXIT")){
        command= scanner.next().toUpperCase();
        switch(command){
            case "TOHEX":
                RGBtoHex();
                break;
            case "TORGB":
                HextoRGB();
                break;
            case "MENU":
                menu();
                break;
            case "EXIT":
                System.out.println("terminating program");
                break;
            case "FADE":
                fadecall();
                break;
            default:
                System.out.println(TEXT_RED+"unknown command entered try again"+TEXT_RESET);

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                menu();
        }
    }

    }
}
