package ylgy.Bottom;

import ylgy.Start;
import ylgy.model.Layer;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class BottomRevoke extends JButton {
    private int x;
    private int y;
    public BottomRevoke(String label, int x, int y){
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
                BottomRevoke revoke = (BottomRevoke) e.getSource();

            }
        });
    }
}
