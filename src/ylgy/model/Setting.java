package ylgy.model;

import ylgy.Main;
import ylgy.util.IntReaderUtil;

import javax.swing.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.*;

/**
 * @author SwordFlame
 */
public class Setting {
    private int floorHeight;
    private int[] layerX;
    private int[] layerY;

    public Setting() throws FileNotFoundException {
        try {
            Socket socket = new Socket("localhost", 8088);
            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os);
            pw.write("getsettings\n");
            pw.flush();

            socket.shutdownOutput();

            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            br.readLine();
            String h = br.readLine();
            IntReaderUtil readfloorHeight = new IntReaderUtil(h);
            this.floorHeight = readfloorHeight.read();
            layerX = new int[floorHeight];
            layerY = new int[floorHeight];
            for(int i = 0; i < floorHeight; ++i){
                IntReaderUtil readxy = new IntReaderUtil(br.readLine());
                readxy.read();
                layerX[i] = readxy.read();
                layerY[i] = readxy.read();
            }

            br.close();
            is.close();
            os.close();
            pw.close();
            socket.close();
        } catch (IOException ex){
            throw new RuntimeException(ex);
        }
    }

    public void setFloorHeight(int floorHeight){ this.floorHeight = floorHeight; }
    public int getFloorHeight(){ return floorHeight; }
    public void setlayerX(int[] layerX){ this.layerX = layerX; }
    public int[] getlayerX(){ return layerX; }
    public void setlayerY(int[] layerY){ this.layerY = layerY; }
    public int[] getlayerY(){ return layerY; }
    public void print(){
        System.out.println(floorHeight);
        System.out.println(Arrays.toString(layerX));
        System.out.println(Arrays.toString(layerY));
    }

}
