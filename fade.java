import java.awt.*;
import java.util.Timer;

public class fade {
    public static void engine(){
        int a=0, b=0, c=0, x=255, y=0, z=0,count=0;
        float run=3, moves;
        Color start = new Color(a, b, c);
        Color end = new Color(x, y, z);
        Timer task = new Timer();

        do {
            if (a > x) {
                a--;
            } else if (a < x) {
                a++;
            }
            if (b > y) {
                b--;
            } else if (b < y) {
                b++;
            }
            if (c > z) {
                c--;
            } else if (c < z) {
                c++;
            }
            start = new Color(a,b,c);
            count++;
            //System.out.println(count);
        } while (!start.equals(end));
        //System.out.println("number of times looped "+count);
        moves= count/run;
        System.out.println("moves needed per second "+moves);
    }
    public static void main(String[] args){
        engine();
    }
}
