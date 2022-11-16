package ylgy;

import ylgy.model.Area;
import ylgy.model.Layer;

public class BulidMap {
    public static Area buildMap(){
        Area map = new Area();
        map.setFloorHeight(3);
        map.setX(100);
        map.setY(200);
        Layer layer1 = BuildLayer.buildLayer(3, 6);
        Layer layer2 = BuildLayer.buildLayer(4, 6);
        Layer layer3 = BuildLayer.buildLayer(6, 7);
        layer1.setParentLayer(null);
        layer2.setParentLayer(layer1);
        layer3.setParentLayer(layer2);
        map.getLayers().add(layer1);
        map.getLayers().add(layer2);
        map.getLayers().add(layer3);
        return map;
    }

}
