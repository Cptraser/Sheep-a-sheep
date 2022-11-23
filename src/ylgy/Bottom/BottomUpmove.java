package ylgy.Bottom;

import ylgy.Start;
import ylgy.model.Brand;
import ylgy.model.Cell;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.List;

public class BottomUpmove extends JButton{
    private int x;
    private int y;
    public BottomUpmove(String label, int x, int y){
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
                BottomUpmove upmove = (BottomUpmove) e.getSource();
                List<Brand> slot = Start.eliminatebox.getSlot();
                int lim = Math.min(slot.size(), 3);
                int i = 0;
                Iterator<Brand> iterator = slot.iterator();
                Cell[][] cells = Start.map.getexLayer().getCells();
                while(i<lim && iterator.hasNext()){
                    Brand brand = iterator.next();
                    int x = (Start.cnt % 9) * 50;
                    int y = (Start.cnt / 9) * 50 + 400;
//                    brand.setTX(x);
//                    brand.setTY(y);
                    brand.setBounds(x, y, 50, 50);
                    cells[Start.cnt/9][Start.cnt%9] = new Cell(brand);
                    brand.setCell(cells[Start.cnt/9][Start.cnt%9]);
                    iterator.remove();
                    ++i;
                    ++Start.cnt;
                }
                Start.map.getexLayer().setCells(cells);
//                Start.map.getexLayer().show();
                Start.eliminatebox.setSlot();
            }
        });
    }

    public void setX(int x){ this.x = x; }
    public void setY(int y){ this.y = y; }

}
