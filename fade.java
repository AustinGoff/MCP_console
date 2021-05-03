/*
the purpose of this code was to be a standalone testing pager for this feature, after completion of feature it was or will be implemented into the main shell for use.
*/
import java.awt.*;
import java.util.Timer;

public class fade {
    public static void engine(){
        int a=200, b=10, c=155, x=255, y=255, z=0,count=0,temp=0,a2,b2,c2;
        float run=3, moves, milmov;
        Color start = new Color(a, b, c);
        Color end = new Color(x, y, z);
        long startTime = System.nanoTime();

        a2= (int) Math.pow((a-x),2);
        b2= (int) Math.pow((b-y),2);
        c2= (int) Math.pow((c-z),2);


        a2= (int) Math.sqrt(a2);
        b2= (int) Math.sqrt(b2);
        c2= (int) Math.sqrt(c2);


        if(a2>b2){
            count=a2;
            if(a2>c2){
                count=a2;
            }else{
                count=c2;
            }
        }else{
            count=b2;
            if(b2>c2){
                count=b2;
            }else{
                count=c2;
            }
        }


    //loop to calculate moves needed to fade to color
        /*
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
                c++
            }
            start = new Color(a,b,c);
            count++;
            //System.out.println(count);
        } while (!start.equals(end));
        */
        long endTime = System.nanoTime();
        long duration = (endTime-startTime);
        System.out.println("initial count: "+count+" in "+duration+" nanoseconds");
        moves= count/run;
        System.out.println("moves needed per second "+moves);
        milmov=(run*1000)/count;
        milmov=Math.round(milmov);
        System.out.println("millisecond per move:  "+milmov);
        startTime = System.nanoTime();
        //timed color changing loop
        while (temp<count){
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
            try {
                Thread.sleep((long)milmov);
            }catch(InterruptedException ie){
                Thread.currentThread().interrupt();
            }
            start = new Color(a,b,c);
            temp++;
           // System.out.println("start color is now: "+start);
        }

        endTime = System.nanoTime();
        System.out.println("timed loop2 increments: "+temp+" in " + (endTime-startTime)/1000000000+ " sec");
        System.out.println("start color is now: "+start);
        System.exit(0);
    }
    public static void main(String[] args){
        engine();
    }
}
