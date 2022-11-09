package ylgy.util;

import java.awt.*;

/**
 * @author SwordFlame
 */
public class ImageUtil {

    public  static Image get(String fileName){
        return  Toolkit.getDefaultToolkit().getImage("imgs\\"+fileName);
    }

}