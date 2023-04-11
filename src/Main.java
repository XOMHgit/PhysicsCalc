import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.random.RandomGenerator;


public class Main {


    static int width = 1000;
    static int height = 1000;


    public static void main(String[] args) {

        JFrame f = new JFrame();


        GamePanel gamePanel = new GamePanel();
        f.add(gamePanel);


        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(new Dimension(width, height));
        f.setLayout(null);
        f.setVisible(true);


        f.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);


            }
        });


    }
}





