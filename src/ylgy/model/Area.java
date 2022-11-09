package ylgy.model;

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
    public Area(){}
    public int getX(){ return x; }
    public int getY(){ return y; }
    public int getFloorHeight(){ return floorHeight; }
    public List<Layer> getLayers(){ return layers; }
    public void setX(int x){ this.x = x; }
    public void setY(int y){ this.y = y; }
    public void setFloorHeight(int floorHeight){ this.floorHeight = floorHeight; }
    public void setLayers(List<Layer> layers){ this.layers = layers; }

    public void grayCheck(){
        List<Layer> list=this.getLayers();
        // 第2层  索引为1  的层开始判定
        for (int i = 1; i <list.size(); i++) {
            Layer layer =list.get(i);
            for (int j = 0; j < layer.getCapacity(); j++) {
                Cell cell = layer.getIndex(j);
                if(cell.getState()==2){
                    // 单元格当中有牌 才进行置灰判定
                    Brand brand = cell.getBrand();
                    // 和上层的所有牌进行 交集判定
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
