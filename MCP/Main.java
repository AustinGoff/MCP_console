package MCP;

import java.awt.*;
import java.io.IOException;
import java.io.File;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;
import java.net.URI;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class Main {
    public static final String TEXT_RESET = "\u001B[0m";
    public static final String TEXT_RED = "\u001B[31m";

    private static void lightTest(){
        try{
            URI webpage = new URI("http://10.100.0.104/00.html");
            java.awt.Desktop.getDesktop().browse(webpage);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    private static void updateLights() throws IOException, URISyntaxException {
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Enter number of lights to turn on:");
//        String number= scanner.next();

        String number = "9";
        final String LOGIN_FORM_URL = "http://10.100.0.104/00.html";
        //final String LOGIN_FORM_URL = "C:\\Users\\Owen Schweigert\\Downloads\\completedLogin";
        final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.128 Safari/537.36";

        Connection.Response loginFormResponse = Jsoup.connect(LOGIN_FORM_URL)
                .method(Connection.Method.GET)
                .userAgent(USER_AGENT)
                .execute();

        FormElement loginForm = (FormElement)loginFormResponse.parse().select("form#00").first();
        //System.out.println(loginForm);
        checkElement("name", loginForm);

        Element loginField = loginForm.select("[name$=I026]").first();
        checkElement("Login Field", loginField);
        loginField.val(number);
        System.out.println(loginField);

        Connection.Response loginActionResponse = loginForm.submit()
                .cookies(loginFormResponse.cookies())
                .data("Test Lights","op")
                .method(Connection.Method.POST)
                .userAgent(USER_AGENT)
                .execute();

        System.out.println(loginActionResponse.parse().html());

        //driver.findElement(By.name("signUp")).click();

    }

    public static void checkElement(String name, Element elem) {
        if (elem == null) {
            throw new RuntimeException("Unable to find " + name);
        }
    }

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
        //col.trim();
        System.out.println(col+" in RGB");
        System.out.println(" ");
        System.out.println("enter new command or exit to quit: ");
    }

    private static void menu(){
        System.out.println("current command options include:");
        System.out.println("openlighttest: open test on webpage");
        System.out.println("updatelights: update lights");
        System.out.println("menu: displays options");
        System.out.println("toHEX: turns RGB into Hex code");
        System.out.println("toRGB: turns hex into RGB color code");
        System.out.println("Fade: create a timed color transformation");
        System.out.println("Parse: download manufacturer details to zip file");
        System.out.println("Tiltpan: create a timed pan and tilt transformation");
        System.out.println("Dim: create a timed brightness transformation");
        System.out.println("ONOFF: turn instrument on or off");
        System.out.println("enter exit to quit: ");
    }

    public static void fadecall() {
        fader call = new fader();
        call.engine();
        System.out.println();
        menu();
    }

    public static void tiltp() {
        tiltPan call = new tiltPan();
        call.tEngine();
        System.out.println();
        menu();
    }
    public static void dim() {
        dimmer call = new dimmer();
        call.dEngine();
        System.out.println();
        menu();
    }
    public static void on() {
        //need to implement packet maker to send variable to instruments
        menu();
    }

    public static void grabber() {
        Grabber grab = new Grabber();
        try {
            grab.information();
        } catch (IOException e) {
            System.out.println(TEXT_RED+"grabber has failed!"+TEXT_RESET);
            e.printStackTrace();
        }
        System.out.println();
        menu();
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        File dir = new File("C:/MCP");
        File lf = new File("C:/MCP/LightFixtures");
        File sd = new File("C:/MCP/SaveData");
        if(!dir.isDirectory()||!lf.isDirectory()||!sd.isDirectory()){
            System.out.println("Creating folders for MCP console!");
           for(int x=0;x<3;x++){
               System.out.print(".");

               try {
                   Thread.sleep(500);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
            System.out.println();
            boolean bool = dir.mkdir();
            boolean bool2 = lf.mkdir();
            boolean bool3 = sd.mkdir();
            if(bool){
                System.out.println("Main folder created successfully!");
            }else{
                System.out.println(TEXT_RED+"MAIN FOLDER NOT CREATED!"+TEXT_RESET);
            }
            if(bool2){
                System.out.println("Light folder created successfully!");
            }else{
                System.out.println(TEXT_RED+"LIGHT FOLDER NOT CREATED!"+TEXT_RESET);
            }
            if(bool3){
                System.out.println("Save data folder created successfully!");
            }else{
                System.out.println(TEXT_RED+"SAVE FOLDER NOT CREATED!"+TEXT_RESET);
            }
        }
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
            case "LIGHTTEST":
                lightTest();
                break;
            case "UPDATELIGHTS":
                updateLights();
                break;
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
            case "TILTPAN":
                tiltp();
                break;
            case "DIM":
                dim();
                break;
            case "ONOFF":
                on();
                break;
            case "PARSE":
                grabber();
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
