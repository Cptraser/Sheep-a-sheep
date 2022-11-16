package ylgy.model;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class BottomUpmove extends JButton{
    private int x;
    private int y;
    private static int cnt = 0;
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
                List<Brand> slot = eliminatebox.getSlot();
                int lim = 3;
                if (slot.size()<3){
                    lim = slot.size();
                }
                int i = 0;
                Iterator<Brand> iterator = slot.iterator();
                while(i<lim && iterator.hasNext()){
                    Brand brand = iterator.next();
                    int x = (cnt % 9) * brand.getWidth();
                    int y = (cnt / 9) * brand.getHeight() + 400;
                    brand.setBounds(x, y, brand.getWidth(), brand.getHeight());
                    brand.getCell().setState(2);
                    iterator.remove();
                    ++i;
                    ++cnt;
                }
                eliminatebox.setSlot();
            }
        });
    }

    Eliminatebox eliminatebox = new Eliminatebox();

    public void setX(int x){ this.x = x; }
    public void setY(int y){ this.y = y; }

}
