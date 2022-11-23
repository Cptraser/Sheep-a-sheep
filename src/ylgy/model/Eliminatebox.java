package ylgy.model;

import ylgy.Start;

import javax.swing.*;
import java.awt.event.MouseListener;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author SwordFlame
 */
public class Eliminatebox {
    private int step = 5;
    private static final List<Brand> SLOT = new ArrayList<>();

    public void deleteByBrandName(String name){
        Iterator<Brand> iterator = SLOT.iterator();
        while(iterator.hasNext()){
            Brand brand = iterator.next();
            if(brand.getName().equals(name)){
                brand.getParent().remove(brand);
                iterator.remove();
            }
        }
    }

    private void noMouseListener(Brand brand){
        MouseListener[] mouseListeners = brand.getMouseListeners();
        if (mouseListeners!=null){
            for (MouseListener mouseListener : mouseListeners){
                brand.removeMouseListener(mouseListener);
            }
        }
    }

    public List<Brand> getSlot(){
        return SLOT;
    }
    public void setSlot(){
        SLOT.sort(Comparator.comparing(Brand::getName));
        paint();
    }


    public void addSlot(Brand brand){
        SLOT.add(brand);
//        noMouseListener(brand);
        SLOT.sort(Comparator.comparing(Brand::getName));
        Map<String, List<Brand>> map = SLOT.stream().collect(Collectors.groupingBy(Brand::getName));
        Set<String> keys = map.keySet();

        for (String key : keys) {
            List<Brand> brands = map.get(key);
            if (brands.size() == 3) {
                deleteByBrandName(key);
                break;
            }
        }
        paint();
        over(brand);
    }

    public void paint(){
        for (int i = 0; i < SLOT.size(); i++){
            Brand brand = SLOT.get(i);
            int x = step + i * brand.getWidth() + 5/2 + 10;
            brand.setBounds(x, 600,brand.getWidth(),brand.getHeight());
        }
    }
    private void over(Brand brand){
        if (SLOT.size()>=7){
            JOptionPane.showMessageDialog(brand,"Game Over~");
            Object[] list = {"Recover", "Exit"};
            Object objResult = JOptionPane.showOptionDialog(null, "Choose:", "Work Hard More!",
                    JOptionPane.YES_NO_CANCEL_OPTION, 2, null, list, list[0]);
            if ((int)objResult == 1) {
                System.exit(0);
            }
            else{
                List<Brand> slot = Start.eliminatebox.getSlot();
                int lim = Math.min(slot.size(), 3);
                int i = 0;
                Iterator<Brand> iterator = slot.iterator();
                Cell[][] cells = Start.map.getexLayer().getCells();
                while(i<lim && iterator.hasNext()){
                    Brand tbrand = iterator.next();
                    int x = (Start.cnt % 9) * 50;
                    int y = (Start.cnt / 9) * 50 + 400;
//                    brand.setTX(x);
//                    brand.setTY(y);
                    tbrand.setBounds(x, y, 50, 50);
                    cells[Start.cnt/9][Start.cnt%9] = new Cell(tbrand);
                    tbrand.setCell(cells[Start.cnt/9][Start.cnt%9]);
                    iterator.remove();
                    ++i;
                    ++Start.cnt;
                }
                Start.map.getexLayer().setCells(cells);
//                Start.map.getexLayer().show();
                Start.eliminatebox.setSlot();
            }
        }
    }


}
