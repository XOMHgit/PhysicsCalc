import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import  java.util.random.RandomGenerator;


public class GamePanel extends JPanel {


    Graphics graphics;
    int size = 200;


    ArrayList<PhysicsObject> objList = new ArrayList();


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        graphics = g;

        for (PhysicsObject obj : objList) {
            if (obj.anchored ){
                g.fillPolygon(obj.polygon);
            }else {
                g.drawPolygon(obj.polygon);
            }
        }

    }


    public GamePanel() {
        initializePhysPol();
        setSize(new Dimension(300000, 300000));


        new Timer(15, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                for (PhysicsObject obj : objList) {
                    obj.Update();
                }
                for (PhysicsObject obj : objList) {
                    if (!obj.anchored ){
                        obj.polygon.translate((int) obj.movement.x, (int) obj.movement.y);
                    }
                }

                repaint();
            }
        }).start();

        new Timer(8000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                objList.clear();
                initializePhysPol();
                repaint();
            }
        }).start();


    }

    public PhysicsObject RandomPhysObj(){
        PhysicsObject newPhysObj;


        Point a = new Point(RandomGenerator.getDefault().nextInt(100 + size,900-size), RandomGenerator.getDefault().nextInt(100 + size,900-size));
        Point b = new Point(a.x + RandomGenerator.getDefault().nextInt(-size,size), a.y + RandomGenerator.getDefault().nextInt(-size,size));
        Point c = new Point(a.x + RandomGenerator.getDefault().nextInt(-size,size), a.y + RandomGenerator.getDefault().nextInt(-size,size));



        Polygon ABCpol = new Polygon(new Point[]{a, b, c});

        PhysicsObject PhysObj = new PhysicsObject(
                ABCpol,
                new Direction(RandomGenerator.getDefault().nextInt(-3,3), RandomGenerator.getDefault().nextInt(-3,3)),
                0,
                RandomGenerator.getDefault().nextInt(0,10) < 5,
                this, RandomGenerator.getDefault().nextInt(5,
                15)
        );




        return PhysObj;
    }
    public void initializePhysPol() {


        // make walls

        int xoffset = 50;
        int yoffset = 50;

        Point a = new Point(1+ xoffset, 850+ yoffset);
        Point b = new Point(1+ xoffset, 1050+ yoffset);
        Point c = new Point(1000+ xoffset, 900+ yoffset);

        Point d = new Point(1 + xoffset , 100 + yoffset);
        Point e = new Point(1050+ xoffset  , 50 + yoffset);
        Point f = new Point(-50+ xoffset  , -50 + yoffset );

        Point g = new Point(100+ xoffset, 100+ yoffset);
        Point j = new Point(50+ xoffset, 900+ yoffset);
        Point h = new Point(-100+ xoffset, 500+ yoffset);

        Point k = new Point(850+ xoffset, 50+ yoffset);
        Point l = new Point(900+ xoffset, 900+ yoffset);
        Point m = new Point(1000+ xoffset, 500+ yoffset);

        Polygon obj1TriList = new Polygon(new Point[]{a, b, c});
        Polygon obj2TriList = new Polygon(new Point[]{d, e, f});
        Polygon obj3TriList = new Polygon(new Point[]{g, h, j});
        Polygon obj4TriList = new Polygon(new Point[]{k, l, m});

        PhysicsObject physObj1 = new PhysicsObject(obj1TriList, new Direction(0, 0), 0, true, this, 10);
        PhysicsObject physObj2 = new PhysicsObject(obj2TriList, new Direction(0, 0), 0, true, this, 15);
        PhysicsObject physObj3 = new PhysicsObject(obj3TriList, new Direction(2, 0), 0, true, this, 10);
        PhysicsObject physObj4 = new PhysicsObject(obj4TriList, new Direction(2, 0), 0, true, this, 10);

        objList.add(physObj1);
        objList.add(physObj2);
        objList.add(physObj3);
        objList.add(physObj4);


        for (int i = 0; i < RandomGenerator.getDefault().nextInt(1,4);i++){
            objList.add(RandomPhysObj());
        }

    }
}




