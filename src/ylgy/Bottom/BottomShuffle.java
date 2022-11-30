package ylgy.Bottom;

import ylgy.BuildLayer;
import ylgy.Start;
import ylgy.model.*;
import ylgy.util.ImageUtil;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * @author SwordFlame
 */

public class BottomShuffle extends JButton {
    private int x;
    private int y;
    public BottomShuffle(String label, int x, int y){
        this.setText(label);
        this.x = x;
        this.y = y;
        this.setEnabled(true);
        this.setVisible(true);
        this.setLocation(x, y);
        this.setSize(80, 40);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                BottomShuffle shuffle = (BottomShuffle) e.getSource();
                List<Layer> layers = Start.map.getLayers();
                for (Layer layer : layers){
                    layerShuffle(layer);
                }
                layerShuffle(Start.map.getexLayer());
                Start.map.grayCheck();
            }
        });
    }
    public void layerShuffle(Layer layer){
        Cell[][] cells = layer.getCells();
        Brand[] brands = new Brand[layer.getCapacity()];
        int tot = 0;
        for (Cell[] value : cells) {
            for (Cell cell : value) {
                if (cell != null && cell.getState() == 2) {
                    int rand = new Random().nextInt(BuildLayer.brandNames.length);
                    String brandName = BuildLayer.brandNames[rand];
                    cell.getBrand().setName(brandName);
                    cell.getBrand().setImage(ImageUtil.get(brandName + ".png"));
                    cell.getBrand().setGrayImage(ImageUtil.get(brandName + "_gray.png"));
                }
            }
        }
    }
}
