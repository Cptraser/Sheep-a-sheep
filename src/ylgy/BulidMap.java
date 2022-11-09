package ylgy;

import ylgy.model.Area;
import ylgy.model.Layer;

public class BulidMap {
    public static Area buildMap(){
        Area map = new Area();
        map.setFloorHeight(1);
        map.setX(100);
        map.setY(200);


        Layer layer1 = BuildLayer.buildLayer(6, 6);
        layer1.setParentLayer(null);
        map.getLayers().add(layer1);
        return map;
    }

}
