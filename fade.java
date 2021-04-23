import java.awt.*;
import java.util.Timer;

public class fade {
    public static void engine(){
        int a=255, b=255, c=255, x=17, y=255, z=50,count=0,temp=0,a2=a,b2=b,c2=c;
        float run=3, moves, milmov;

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
        //System.out.println("number of times looped "+count+" in "+duration+" nanoseconds");
        moves= count/run;
        System.out.println("moves needed per second "+moves);
        milmov=(run*1000)/count;
        milmov=Math.round(milmov);
        System.out.println("millisecond per move:  "+milmov);
        start = new Color(a2, b2, c2);
        startTime = System.nanoTime();
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
            System.out.println("start color is now: "+start);
        }

        endTime = System.nanoTime();
        System.out.println("timed loop2: "+temp+"in " + (endTime-startTime)/1000000000+ " sec");
        System.exit(0);
    }
    public static void main(String[] args){
        engine();
    }
}
