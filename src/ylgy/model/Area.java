package ylgy.model;

import ylgy.UI.GameFrameUI;
import ylgy.UI.HistoryUI;
import ylgy.UI.PasswordwindowUI;

import javax.swing.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @author SwordFlame
 */
public class Area {
    private int x;
    private int y;
    private int floorHeight;
    private List<Layer> layers = new ArrayList<>();
    private Layer exlayer = null;
    public Area(){}
    public int getX(){ return x; }
    public int getY(){ return y; }
    public int getFloorHeight(){ return floorHeight; }
    public List<Layer> getLayers(){ return layers; }
    public void setexLayer(Layer exlayer){ this.exlayer = exlayer; }
    public Layer getexLayer(){ return exlayer; }
    public void setX(int x){ this.x = x; }
    public void setY(int y){ this.y = y; }
    public void setFloorHeight(int floorHeight){ this.floorHeight = floorHeight; }
    public void setLayers(List<Layer> layers){ this.layers = layers; }
    public void checkClr() {
        for (Layer layer : layers){
            if(!layer.isClr()){
                return ;
            }
        }
        if(!exlayer.isClr()){
            return ;
        }
        try {
            Socket socket = new Socket("localhost", 8088);
            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os);
            if (!PasswordwindowUI.ifip) {
                pw.write("addscorename\n");
                pw.write(PasswordwindowUI.gettxtusername()+'\n');
            } else {
                pw.write("addscoreip\n");
                pw.write(InetAddress.getLocalHost().getHostAddress()+'\n');
            }
            pw.write(Double.toString(GameFrameUI.score) +'\n');
            pw.flush();

            socket.shutdownOutput();

            os.close();
            pw.close();
            socket.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        JOptionPane.showMessageDialog(null, "Win! And score has been recorded.");
        GameFrameUI.gameself.dispose();
        try {
            new HistoryUI();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void grayCheck(){
        List<Layer> list=this.getLayers();
        for (int i = 1; i <list.size(); i++) {
            Layer layer =list.get(i);
            for (int j = 0; j < layer.getCapacity(); j++) {
                Cell cell = layer.getIndex(j);
                if(cell.getState()==2){
                    Brand brand = cell.getBrand();
                    boolean flag= this.brand2layer(brand,layer.getParentLayer());
                    brand.setGray(flag);
                }
            }
        }
    }
    private boolean brand2layer(Brand brand, Layer layer){
        for (int j = 0; j < layer.getCapacity(); ++j){
            Cell cell = layer.getIndex(j);
            if(cell.getState()==2){
                Brand temp = cell.getBrand();
                boolean flag = brand.getBounds().intersects(temp.getBounds());
                if(flag){
                    return true;
                }
            }
        }
        if(layer.getParentLayer()==null) {
            return false;
        }else{
            return brand2layer(brand, layer.getParentLayer());
        }
    }


}
