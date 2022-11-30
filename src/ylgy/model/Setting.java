package ylgy.model;

import ylgy.util.IntReaderUtil;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * @author SwordFlame
 */
public class Setting {
    private int floorHeight;
    private int[] layerX;
    private int[] layerY;

    public Setting() throws FileNotFoundException {
//        System.out.println(file.canRead());
        Scanner reader = new Scanner(new File(".///settings//configuration.txt"));
        reader.nextLine();
        IntReaderUtil readfloorHeight = new IntReaderUtil(reader.nextLine());
        this.floorHeight = readfloorHeight.read();
        layerX = new int[floorHeight];
        layerY = new int[floorHeight];
        for(int i = 0; i < floorHeight; ++i){
            IntReaderUtil readxy = new IntReaderUtil(reader.nextLine());
            readxy.read();
            layerX[i] = readxy.read();
            layerY[i] = readxy.read();
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
