package ylgy;

import ylgy.model.*;

import java.util.Random;

public class BuildLayer {
    public  static String[] brandNames={
            "刷子","剪刀","叉子",
            "手套","水桶","火",
            "玉米","球","瓶子",
            "白菜","稻草","肉腿",
            "胡萝卜","苹果","苹果",
            "铃铛","青草"
    };
    public static Layer buildLayer(int cellNumx, int cellNumy){
        Layer layer = new Layer(cellNumx, cellNumy);
        Cell[][] cells = layer.getCells();
        Brand[] brands = new Brand[layer.getCapacity()];
        for(int i = 0; i < brands.length; i = i + 3){
            int rand = new Random().nextInt(brandNames.length);
            String brandName = brandNames[rand];
            Brand brand1 = new Brand(brandName);
            Brand brand2 = new Brand(brandName);
            Brand brand3 = new Brand(brandName);
            brands[i] = brand1;
            brands[i + 1] = brand2;
            brands[i + 2] = brand3;
        }
        shuffle(brands);
        for(int i = 0; i < brandNames.length; ++i){
            Brand brand = brands[i];
            int rand = new Random().nextInt(brandNames.length);
            brands[i] = brands[rand];
            brands[rand] = brand;
        }
        int cnt = 0;
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                Brand brand =brands[cnt];
                Cell cell = new Cell(brand);
                brand.setCell(cell);
                cells[i][j]=cell;
                cnt++;
            }
        }
        layer.setSize(cnt);
        return layer;
    }
    private static void swap(Brand[] arr, int i, int j) {
        Brand tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * shuffle方法能将参数数组arr随机重排
     * shuffle的基本思路是什么呢？从后往前，逐个给每个数组位置重新赋值，值是从剩下的元素中随机挑选的。
     * swap(arr, i-1, rnd.nextInt(i));i-1表示当前要赋值的位置，rnd.nextInt（i）表示从剩下的元素中随机挑选。
     */
    public static void shuffle(Brand[] arr) {
        Random rnd = new Random();
        for (int i = arr.length; i > 1; i--) {
            swap(arr, i - 1, rnd.nextInt(i));
        }
    }

}
