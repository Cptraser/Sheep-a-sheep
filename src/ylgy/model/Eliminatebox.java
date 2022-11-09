package ylgy.model;

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

    public void addSlot(Brand brand){
        SLOT.add(brand);
        noMouseListener(brand);
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
        for (int i =0;i<SLOT.size();i++){
            Brand brand = SLOT.get(i);
            int x = step + i * brand.getWidth() + 5/2 + 10;
            brand.setBounds(x,600,50,50);
        }
    }
    private void over(Brand brand){
        if (SLOT.size()>=7){
            JOptionPane.showMessageDialog(brand,"over");
            System.exit(0);
        }
    }


}
