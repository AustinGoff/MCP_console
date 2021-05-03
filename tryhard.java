import java.awt.*;
import java.util.Scanner;

public class tryhard {
    static class Main {
        public static void main(String[] args) {
            HextoRGB();
        }


        private static void HextoRGB(){
            int r=0,g=0,b=0;
            Scanner scanner = new Scanner(System.in);
            System.out.println("enter HEX, format: #FFCCEE");
            String hex= scanner.next().toUpperCase();
            System.out.print("hex code "+hex+" becomes ");
            Color color = Color.decode(hex);
            String col = String.valueOf(color);
            col = col.substring(col.indexOf("["));
            System.out.println(col+" in RGB");
            char[] ch = col.toCharArray();
            col="";
            int flag=0;
            for (char c : ch) {
                if(Character.compare(c,'r')==0) {
                    flag = 1;

                }
                if(Character.compare(c,'g')==0) {
                    flag = 2;
                    col="";
                }
                if(Character.compare(c,'b')==0) {
                    flag = 3;
                    col="";
                }
                    if(Character.compare(c, 'r')!=0 &&Character.compare(c, '=')!=0 && flag==1){
                        if(Character.compare(c, ',')!=0 ) {
                            col += String.valueOf(c);
                            r= Integer.valueOf(col);
                        }
                    }



                    if(Character.compare(c, 'g')!=0&&Character.compare(c, '=')!=0 && flag==2){
                        if(Character.compare(c, ',')!=0 ) {
                            col += String.valueOf(c);
                            g= Integer.valueOf(col);
                        }
                    }



                    if(Character.compare(c, 'b')!=0 &&Character.compare(c, '=')!=0 && flag==3){
                        if(Character.compare(c, ',')!=0 && Character.compare(c,']')!=0) {
                            col += String.valueOf(c);
                            b= Integer.valueOf(col);
                        }
                    }
                }

            System.out.println(" col: "+col);
            System.out.println("r is: "+r);
            System.out.println("g is: "+g);
            System.out.println("b is: "+b);
        }
    }
}
