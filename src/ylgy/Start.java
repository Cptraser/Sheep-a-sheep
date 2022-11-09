package ylgy;

import ylgy.model.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.*;


/**
 * @author SwordFlame
 */
public class Start extends JFrame{
    private Brand bj = new Brand("背景草地");
    private Brand xc = new Brand("消除区域");

    public static Area map = BulidMap.buildMap();
    public Start() throws HeadlessException{
        init();
        createMap();
        xc.setBounds(0,575,450,800);
        this.getContentPane().add(xc);

        bj.setBounds(0,0,450,800);
        this.getContentPane().add(bj);

        refresh();
    }


    public void init(){//draw windows
        this.setVisible(true);
        int width = 450;
        int height = 800;
        this.setSize(width, height);
        this.setTitle("羊了个羊第一关");
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
                if(cell.getState()==2){
                    Brand brand = cell.getBrand();
                    int brandx = j*50 + layer.getOffset();
                    int brandy = i*50 + layer.getOffset();
                    brand.setBounds(brandx, brandy, 50, 50);
                    this.getContentPane().add(brand);
                }
            }
        }
    }

    public void refresh(){
        ExecutorService threadPool=new ThreadPoolExecutor(1,5,
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
                repaint();
            }
        };
        threadPool.execute(runnable);
    }



    public static void main(String[] args) {
        new Start();
    }
}