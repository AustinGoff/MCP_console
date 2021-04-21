package MCP;
import java.awt.*;
import java.util.Timer;

public class fader {
    public void engine(){
        int a=0, b=0, c=0, x=255, y=0, z=0, count=0;
        Color start = new Color(a, b, c);
        Color end = new Color(x, y, z);
        Timer run = new Timer();
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
            System.out.println(count);
        } while (!start.equals(end));
    }

}
