package MCP;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class dimmer {

    public void dEngine(){
        int sb=0,eb=0;
        System.out.println("enter brightness you would like to start from: ");
        System.out.println("variable cannot be greater then 100 or less then 0");
        do {
            Scanner scanner = new Scanner(System.in);
            sb = scanner.nextInt();

        }while(sb>=100 && sb<=0);

        Scanner scanner = new Scanner(System.in);;
        do {
            System.out.println("enter brightness you would like to end at:");
            System.out.println("variable cannot be greater then 100 or less then 0");
            eb = scanner.nextInt();
        }while(eb>=100 && eb<=0);
        System.out.println("lastly enter length of time for transition to last in seconds: format 3 or 3.5");
        float run = scanner.nextInt();

        int count=0,temp=0,sb2=sb;
        float moves, milmov;

        long startTime = System.nanoTime();

        count= (int) Math.pow((sb-eb),2);

        count= (int) Math.sqrt(count);

        long endTime = System.nanoTime();
        long duration = (endTime-startTime);
        moves= count/run;
        System.out.println("moves needed per second "+moves);
        milmov=(run*1000)/count;
        milmov=Math.round(milmov);
        System.out.println("millisecond per move:  "+milmov);
        startTime = System.nanoTime();

        while (temp<count){
            if(sb<eb){
                sb++;
            }else{
                sb--;
            }
            try {
                Thread.sleep((long)milmov);
            }catch(InterruptedException ie){
                Thread.currentThread().interrupt();
            }
            temp++;
            //need to implement packet maker to send variable to instruments
            System.out.println("brightness is now "+sb);
        }

        endTime = System.nanoTime();

        System.out.println("increments in loop2: "+temp+" in " + (endTime-startTime)/1000000000+ " sec");
        System.out.println("brightness is now "+sb);
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
                        String t = String.valueOf(sb2);
                        myWriter.write("start_brightness:"+t+"\n");
                        t = String.valueOf(eb);
                        myWriter.write("end_brightness:"+t+"\n");
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
