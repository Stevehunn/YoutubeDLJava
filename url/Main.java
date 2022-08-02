package url;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        
        String link = "https://www.youtube.com/watch?v=QH2-TGUlwu4";

        File out = new File("C:\\Users\\steve\\Desktop\\video-dl");

        new Thread(new Download(link, out)).start();
    }
    
}
