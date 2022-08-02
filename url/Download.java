package url;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Download implements Runnable{

    String link;
    File out;

    public Download(String link, File out){
        this.link = link;
        this.out = out;
    }

    @Override
    public void run() {
        try{
            URL url = new URL(link);
            HttpURLConnection http = (HttpURLConnection)url.openConnection();
            double fileSize = (double) http.getContentLength();
            BufferedInputStream in = new BufferedInputStream(http.getInputStream());
            FileOutputStream fos = new FileOutputStream(this.out);
            BufferedOutputStream bout = new BufferedOutputStream(fos , 1024);
            byte[] buffer = new byte [1024];
            double download = 0.00;
            int read =0;
            double percentDowloaded = 0.00;
            while((read = in.read(buffer, 0, 1024))>= 0){
                bout.write(buffer, 0,read);
                download += read;
                percentDowloaded = (download*100)/fileSize;
                String percent =String.format("%.4f", percentDowloaded);
                System.out.println("Downloaded " + percent + "% of a file.");
            }
            bout.close();
            in.close();
            System.out.println("Download finish");

        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }

}
