import java.awt.*;
import java.util.Timer;

public class fade {
    public static void engine(){
        int a=0, b=0, c=0, x=255, y=0, z=0,count=0;
        float run=3, moves;

        Color start = new Color(a, b, c);
        Color end = new Color(x, y, z);
        long startTime = System.nanoTime();
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

        long endTime = System.nanoTime();
        long duration = (endTime-startTime);
        //duration = duration/1000000;
        System.out.println("number of times looped "+count+" in "+duration+" nanoseconds");
        moves= count/run;
        System.out.println("moves needed per second "+moves);

        a=0;
        while (a<count){
            startTime = System.nanoTime();
            for (int i=0;i<=moves;i++) {
                //System.out.println("timed loop: "+i);
            }
            try {
                Thread.sleep(1000);
            }catch(InterruptedException ie){
                Thread.currentThread().interrupt();
            }
            endTime = System.nanoTime();
            a+=moves;
            System.out.println("timed loop2: "+a+" in "+ (endTime-startTime)/1000000000+ " sec");
        }
        System.exit(0);
    }
    public static void main(String[] args){
        engine();
    }
}
