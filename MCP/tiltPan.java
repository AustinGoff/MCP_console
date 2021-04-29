package MCP;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class tiltPan {

    public void tEngine(){
        int tilt=0,pan=0,endtilt=0,endpan=0;
        System.out.println("enter tilt position you would like to start from: if nothing is entered default will be 0 ");
        Scanner scanner = new Scanner(System.in);
        tilt = scanner.nextInt();
        System.out.println("enter tilt position you would like to end at: if nothing is entered default will be 0 ");
        endtilt = scanner.nextInt();
        System.out.println("enter pan position you would like to start from: if nothing is entered default will be 0 ");
        pan= scanner.nextInt();
        System.out.println("enter pan position you would like to end at: if nothing is entered default will be 0 ");
        endpan= scanner.nextInt();

        System.out.println("lastly enter length of time for transition to last in seconds: format 3 or 3.5");
        float run = scanner.nextInt();

        int count=0,temp=0,st=tilt,sp=pan;
        float moves, milmov;

        long startTime = System.nanoTime();

        temp= (int) Math.pow((tilt-endtilt),2);
        count= (int) Math.pow((pan-endpan),2);

        temp= (int) Math.sqrt(temp);
        count= (int) Math.sqrt(count);


        if(temp>count){
            count=temp;
        }
        temp=0;

        long endTime = System.nanoTime();
        long duration = (endTime-startTime);
        System.out.println("number of times looped "+count+" in "+duration+" nanoseconds");
        moves= count/run;
        System.out.println("moves needed per second "+moves);
        milmov=(run*1000)/count;
        milmov=Math.round(milmov);
        System.out.println("millisecond per move:  "+milmov);
        startTime = System.nanoTime();

        while (temp<count){

            if(pan<endpan){
                pan++;
            }
            if(pan>endpan){
                pan--;
            }
            if(tilt<endtilt){
                tilt++;
            }
            if(tilt>endtilt){
                tilt--;
            }
            try {
                Thread.sleep((long)milmov);
            }catch(InterruptedException ie){
                Thread.currentThread().interrupt();
            }
            //need to implement packet maker to send variable to instruments
            temp++;
            System.out.println("tilt position is now "+tilt+" pan position is now "+pan);
        }

        endTime = System.nanoTime();

        System.out.println("increments in loop2: "+temp+" in " + (endTime-startTime)/1000000000+ " sec");
        System.out.println("tilt position is now "+tilt+" pan position is now "+pan);
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
                        String t = String.valueOf(st);
                        String p = String.valueOf(sp);
                        myWriter.write("start_tilt_pos:"+t+"\n");
                        t = String.valueOf(endtilt);
                        myWriter.write("end_tilt_pos:"+t+"\n");
                        myWriter.write("start_pan_pos::"+p+"\n");
                        p = String.valueOf(endpan);
                        myWriter.write("end_pan_pos::"+p+"\n");
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
