package ylgy.util;

import ylgy.model.Area;
import ylgy.model.Cell;
import ylgy.model.Layer;

import java.util.Objects;

public class DifficultyUtil {
    public static String[] brandNames={
            "刷子","剪刀","叉子",
            "手套","水桶","火",
            "玉米","球","瓶子",
            "白菜","稻草","肉腿",
            "胡萝卜","苹果","苹果",
            "铃铛","青草"
    };
    public static double calculate(Area map){
        double res = 0;
        for(Layer layer : map.getLayers()){
            res += calculatemiu(layer);
        }
        res += calculatemiu(map.getexLayer());
        return res;
    }
    private static double calculatemiu(Layer layer){
        double res = 0;
        int[] cntbrand = new int[brandNames.length];
        for(int i = 0; i < brandNames.length; ++i){
            cntbrand[i] = 0;
        }
        Cell[][] cells = layer.getCells();
        for(Cell[] celli : cells){
            for(Cell cell : celli){
                if(cell!=null&&cell.getState()==2){
                    for(int i = 0; i < brandNames.length; ++i){
                        if(Objects.equals(brandNames[i], cell.getBrand().getName())){
                            cntbrand[i]++;
                        }
                    }
                }
            }
        }
        double miu = 0;
        for(int i = 0; i < brandNames.length; ++i){
            miu += cntbrand[i];
        }
        miu/= brandNames.length;
        for(int i = 0; i < brandNames.length; ++i){
            res += (miu-cntbrand[i]) * (miu-cntbrand[i]) / brandNames.length;
        }
        return res;
    }
    public static void paintDifficulty(double theta){
        System.out.println(theta);
    }

}
