package MCP;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;


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

        int count=0,temp=0,a2,b2,c2;
        float moves, milmov;
        Color start = new Color(a, b, c);
        Color end = new Color(x, y, z);
        Color wstart = new Color(a, b, c);
        long startTime = System.nanoTime();
        //instead of commented out while loop we use quick math for faster calculation of steps
        a2= (int) Math.pow((a-x),2);
        b2= (int) Math.pow((b-y),2);
        c2= (int) Math.pow((c-z),2);


        a2= (int) Math.sqrt(a2);
        b2= (int) Math.sqrt(b2);
        c2= (int) Math.sqrt(c2);

//if else statement to find the max number of steps needed for varaibles
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
    /*
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

    */

        long endTime = System.nanoTime();
        long duration = (endTime-startTime);
        System.out.println("number of times looped "+count+" in "+duration+" nanoseconds");
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
            //need to implement packet maker to send start variable to instruments
            temp++;
            //System.out.println("start color is now: "+start);
        }

        endTime = System.nanoTime();
        System.out.println("start color is now: "+start);
        System.out.println("increments in loop2: "+temp+" in " + (endTime-startTime)/1000000000+ " sec");

        System.out.println("would you like to save this color transition? enter y to save anything else to exit");
        String command=scanner.next().toLowerCase();

            if (command.equals("y") ) {

                File dir = new File("C:\\MCP\\SaveData\\");
                System.out.println("enter file name:");
                String name =scanner.next();
                name= name.concat(".txt");
                File myObj = new File(dir,name);

                try {
                    if (myObj.createNewFile()) {
                        System.out.println("File created: " + myObj.getName());
                    } else {
                        System.out.println("File already exists.");
                    }
                    try {
                        FileWriter myWriter = new FileWriter(myObj);
                        String col = String.valueOf(wstart);
                        col = col.substring(col.indexOf("["));
                        myWriter.write("start_color:"+col+"\n");
                        col = String.valueOf(end);
                        col = col.substring(col.indexOf("["));
                        myWriter.write("end_color:"+col+"\n");
                        myWriter.write("run:"+run+"\n");
                        myWriter.close();
                        System.out.println("Successfully wrote to the file.");
                    } catch (IOException e) {
                        System.out.println("info not written to file:");
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    System.out.println("file creation failure");
                    e.printStackTrace();
                }

            }

        return;
    }

}
