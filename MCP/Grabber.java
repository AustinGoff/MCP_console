package MCP;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Grabber {

    static String manuf, fixt = "";



    public static void information() throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the lighting fixture's manufacturer: ");
        manuf = input.next().toLowerCase();
        input.nextLine();

        System.out.print("Enter the lighting fixture's name: ");
        String temp = input.nextLine().toLowerCase();
        fixt = temp.replace(" ", "-");

        webSearch();
    }

    public static void webSearch() throws IOException {
        URL website = new URL("https://open-fixture-library.org/"+ manuf +"/" + fixt + ".ofl");

        HttpURLConnection conn =  (HttpURLConnection) website.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        int code = conn.getResponseCode();

        if(code == 200) {
            System.out.println("Lighting fixture found, downloading.");
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());
            FileOutputStream fos = new FileOutputStream("C:\\MCP\\LightFixtures\\"+manuf+"+"+fixt+".zip");
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);

            Scanner scan = new Scanner(System.in);
            System.out.print("Enter the number 1 to download another file, any other input to return to menu: ");
            String answer = scan.next();
            if(answer.equals("1")){
                //unzip();
                information();
            }else{
                //unzip();
                return;
            }
        }else{
            System.out.println("Lighting fixture not found, please check and re-enter information.");
            information();
        }
    }
    private static void unzip() {
        try(ZipFile file = new ZipFile("C:\\MCP\\LightFixtures\\"+manuf+"+"+fixt+".zip"))
        {
            FileSystem fileSystem = FileSystems.getDefault();
            Enumeration<? extends ZipEntry> entries = file.entries();

            String uncompressedDirectory = "C:\\MCP\\LightFixtures\\unzipped-"+manuf+"+"+fixt;
            //Files.createDirectory(fileSystem.getPath(uncompressedDirectory));

            while (entries.hasMoreElements())
            {
                ZipEntry entry = entries.nextElement();
                if (entry.isDirectory())
                {
                    Files.createDirectories(fileSystem.getPath(uncompressedDirectory + entry.getName()));
                }
                else
                {

                    InputStream is = file.getInputStream(entry);
                    BufferedInputStream bis = new BufferedInputStream(is);
                    String uncompressedFileName = uncompressedDirectory + entry.getName();
                    Path uncompressedFilePath = fileSystem.getPath(uncompressedFileName);
                    Files.createFile(uncompressedFilePath);
                    FileOutputStream fileOutput = new FileOutputStream(uncompressedFileName);
                    while (bis.available() > 0)
                    {
                        fileOutput.write(bis.read());
                    }
                    fileOutput.close();
                }
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
