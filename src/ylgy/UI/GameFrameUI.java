package ylgy.UI;

import ylgy.Bottom.BottomRevoke;
import ylgy.Bottom.BottomShuffle;
import ylgy.Bottom.BottomUpmove;
import ylgy.BulidMap;
import ylgy.model.*;
import ylgy.util.DifficultyUtil;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.*;



/**
 * @author SwordFlame
 */
public class GameFrameUI extends JFrame{
    public static JFrame gameself;
    public static int cnt = 0;
    public static Eliminatebox eliminatebox = new Eliminatebox();
    public static Setting setting;
    public static double score = 0;

    public static Area map;
    public GameFrameUI() throws HeadlessException {
        try {
            setting = new Setting();
            setting.print();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        map = BulidMap.buildMap();
        init();
        createMap();

        BottomUpmove upmove = new BottomUpmove("上移", 30, 700);
        this.getContentPane().add(upmove);

        BottomRevoke revoke = new BottomRevoke("撤销", 180, 700);
        this.getContentPane().add(revoke);

        BottomShuffle shuffle = new BottomShuffle("打乱", 330, 700);
        this.getContentPane().add(shuffle);

        Brand xc = new Brand("消除区域");
        xc.setBounds(0,575,430,110);
        this.getContentPane().add(xc);

        Brand bj = new Brand("背景草地");
        bj.setBounds(0,0,450,800);
        this.getContentPane().add(bj);


        refresh();
    }


    public void init(){//draw windows
        this.setVisible(true);
        int width = 450;
        int height = 800;
        this.setSize(width, height);
        this.setTitle("羊了个羊3.0");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setBounds(0, 0, 450, 800);
        this.setLocationRelativeTo(null);
    }

    public void createMap(){
        List<Layer> list = map.getLayers();
        for (Layer layer : list) {
            createLayer(this.getContentPane(), layer);
        }
        map.grayCheck();
    }
    private void createLayer(Container container, Layer layer){
        Cell[][] cells = layer.getCells();
        for (int i = 0; i < cells.length; ++i){
            for (int j = 0; j < cells[i].length; ++j){
                Cell cell = cells[i][j];
                if(cell.getState() == 2){
                    Brand brand = cell.getBrand();
                    int brandx = j*50 + layer.getOffset();
                    int brandy = i*50 + layer.getOffset();
                    brand.setBounds(brandx, brandy, 50, 50);
                    container.add(brand);
                }
            }
        }
    }

    public void refresh(){
        JFrame self = this;
        ExecutorService threadPool=new ThreadPoolExecutor(2,5,
                1L,TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        
        Runnable runnable = () -> {
            while (true){
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                map.getexLayer().show();
                repaint();
            }
        };
        Runnable difficultyCalculate = () -> {
            while (true){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                DifficultyUtil.paintDifficulty(DifficultyUtil.calculate(map), self);
            }
        };
        threadPool.execute(runnable);
        threadPool.execute(difficultyCalculate);
    }
}
