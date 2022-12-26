package ylgy;

import ylgy.UI.GameFrameUI;
import ylgy.model.Area;
import ylgy.model.Layer;
import ylgy.model.Setting;

public class BulidMap {
    public static Area buildMap(){
        Area map = new Area();
        Setting setting = GameFrameUI.setting;
        map.setFloorHeight(setting.getFloorHeight());
        map.setX(100);
        map.setY(200);
        Layer[] layers = new Layer[setting.getFloorHeight()];
        for(int i = 0; i < setting.getFloorHeight(); ++i){
            layers[i] = BuildLayer.buildLayer(setting.getlayerX()[i], setting.getlayerY()[i]);
        }
        Layer exlayer = BuildLayer.buildexLayer();
        for(int i = 0; i < setting.getFloorHeight(); ++i){
            if(i!=0) {
                layers[i].setParentLayer(layers[i - 1]);
            } else {
                layers[i].setParentLayer(null);
            }
        }
        for(int i = 0; i < setting.getFloorHeight(); ++i){
            map.getLayers().add(layers[i]);
        }
        map.setexLayer(exlayer);
        return map;
    }

}
