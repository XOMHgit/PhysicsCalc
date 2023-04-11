public class Polygon extends java.awt.Polygon {




    Point[] points ;




    Polygon(Point[] p){
        points = p;


        int[]  xpointslist = new int[(int)p.length];
        int[]  ypointslist = new int[(int)p.length];
        for (int i = 0; i < p.length;i++){


            xpointslist[i] = (int) p[i].x;
            ypointslist[i] = (int) p[i].y;


        }


        xpoints = xpointslist;
        ypoints = ypointslist;
        npoints = p.length;


    }


    @Override
    public void translate(int deltaX, int deltaY) {
        super.translate(deltaX, deltaY);
        for (Point point: points){


            point.x += deltaX;
            point.y += deltaY;


        }
    }
}





