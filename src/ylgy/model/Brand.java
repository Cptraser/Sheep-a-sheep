package ylgy.model;

import ylgy.Start;
import ylgy.util.ImageUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Objects;
import java.util.UUID;
import java.util.*;
import javax.swing.*;

/**
 * @author SwordFlame
 */
public class Brand extends Component{
    private String name;
    private String id;
    private int x = 0;
    private int y = 0;
    private Cell cell;
    private Image image;
    private Image grayImage;
    private Boolean isGray = false;
    public Brand(){}
    public Brand(String name){
        this.name = name;
        this.image = ImageUtil.get(name + ".png");
        this.grayImage = ImageUtil.get(name + "_gray.png");
        this.id = UUID.randomUUID().toString();
        Brand self = this;
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Brand brand = (Brand) e.getSource();
                if(brand.getGray() || Objects.equals("消除区域", brand.getName()) || Objects.equals("背景草地", brand.getName())){
                    return ;
                } else {
                    System.out.println(brand.getName()+"被点击");
                    eliminatebox.addSlot(brand);
                    self.getCell().setState(1);
                    Start.map.checkClr();
                    self.getCell().setBrand(null);
                    self.setCell(null);
                    Start.map.grayCheck();

                }
            }
        });
    }

    Eliminatebox eliminatebox = new Eliminatebox();

    @Override
    public void paint(Graphics g){
        if(isGray){
            g.drawImage(this.getGrayImage(), x, y, null);
        } else{
            g.drawImage(this.getImage(), x, y, null);
        }
    }


    public Cell getCell() { return cell; }
    public void setCell(Cell cell) { this.cell = cell; }
    public Boolean getGray() { return isGray; }
    public void setGray(Boolean gray) {
        isGray = gray;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Image getImage() {
        return image;
    }
    public void setImage(Image image) {
        this.image = image;
    }
    public Image getGrayImage() {
        return grayImage;
    }
    public void setGrayImage(Image grayImage) {
        this.grayImage = grayImage;
    }
}
