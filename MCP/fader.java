package MCP;
import java.awt.*;
import java.util.Scanner;
import java.util.Timer;

public class fader {

    public void engine(){
        System.out.println("enter RGB for beginning shade of color, format: 200 155 100");
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();

        System.out.println("next enter RGB for ending shade of color, format: 200 155 100");
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        int z = scanner.nextInt();

        System.out.println("lastly enter length of time for transition to last in seconds: format 3 or 3.5");
        float run = scanner.nextInt();

        int count=0,temp=0,a2=a,b2=b,c2=c;
        float moves, milmov;
        Color start = new Color(a, b, c);
        Color end = new Color(x, y, z);
        //long startTime = System.nanoTime();

        //loop to calculate moves needed to fade to color
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

        //long endTime = System.nanoTime();
        //long duration = (endTime-startTime);
        //System.out.println("number of times looped "+count+" in "+duration+" nanoseconds");
        moves= count/run;
        System.out.println("moves needed per second "+moves);
        milmov=(run*1000)/count;
        milmov=Math.round(milmov);
        System.out.println("millisecond per move:  "+milmov);
        start = new Color(a2, b2, c2);
        long startTime = System.nanoTime();
        //timed color changing loop
        while (temp<count){
            if (a2 > x) {
                a2--;
            } else if (a2 < x) {
                a2++;
            }
            if (b2 > y) {
                b2--;
            } else if (b2 < y) {
                b2++;
            }
            if (c2 > z) {
                c2--;
            } else if (c2 < z) {
                c2++;
            }
            try {
                Thread.sleep((long)milmov);
            }catch(InterruptedException ie){
                Thread.currentThread().interrupt();
            }
            start = new Color(a2,b2,c2);
            temp++;
            //System.out.println("start color is now: "+start);
        }

        long endTime = System.nanoTime();
        //System.out.println("start color is now: "+start);
        System.out.println("increments in loop2: "+temp+" in " + (endTime-startTime)/1000000000+ " sec");
        return;
    }

}
