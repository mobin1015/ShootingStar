package ShootingStar;

import javax.swing.*;
import java.awt.*;

public class attack {
    Image image = new ImageIcon("src/images/player_attack.png").getImage();
    Image cimage = image.getScaledInstance(100,100,Image.SCALE_SMOOTH);
    int x, y;
    int width = cimage.getWidth(null);
    int height = cimage.getHeight(null);
    int attack = 5;

    public attack(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void fire() {
        this.x += 2;
    }
}