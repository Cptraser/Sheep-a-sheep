package ylgy.model;

import javax.swing.*;

public class Bottom extends JButton{
    private int x;
    private int y;
    public Bottom(String label, int x, int y){
        this.setText(label);
        this.x = x;
        this.y = y;
        this.setEnabled(true);
        this.setVisible(true);
        this.setLocation(x, y);
        this.setSize(80, 40);
    }

    public void setX(int x){ this.x = x; }
    public void setY(int y){ this.y = y; }

}
