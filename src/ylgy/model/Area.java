package ylgy.model;

import javax.swing.*;
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
    public void checkClr(){
        for (Layer layer : layers){
            if(!layer.isClr()){
                return ;
            }
        }
        JOptionPane.showMessageDialog(null, "Win!");
        System.exit(0);
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
                    return flag;
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
