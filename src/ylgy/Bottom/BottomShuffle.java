package ylgy.Bottom;

import ylgy.BuildLayer;
import ylgy.Start;
import ylgy.model.*;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.List;

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
            }
        });
    }
    public void layerShuffle(Layer layer){
        Cell[][] cells = layer.getCells();
        Brand[] brands = new Brand[layer.getCapacity()];
        int tot = 0;
        for(int i = 0; i < cells.length; ++i){
            for(int j = 0; j < cells[i].length; ++j){
                Cell cell = cells[i][j];
                if(cell!=null && cell.getState()==2){
                    brands[tot++] = cell.getBrand();
                }
            }
        }
        BuildLayer.shuffle(brands, tot);
        tot = 0;
        for(int i = 0; i < cells.length; ++i){
            for(int j = 0; j < cells[i].length; ++j){
                Cell cell = cells[i][j];
                if(cell!=null && cell.getState()==2){
                    Brand brand = brands[tot++];
                    cell.setBrand( brand );
                    int brandx = j*50 + layer.getOffset();
                    int brandy = i*50 + layer.getOffset();
                    brand.setBounds(brandx, brandy, 50, 50);
                }
            }
        }
    }
}
