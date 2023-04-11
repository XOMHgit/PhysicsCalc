public class Line {


    Point a;
    Point b;

    Line(Point A, Point B) {
        a = A;
        b = B;
    }


    Point intersect(Line other) {
        // get intersect at point using https://en.wikipedia.org/wiki/Line%E2%80%93line_intersection#Given_two_points_on_each_line of this line and given line
        double numeratorY = (a.x * b.y - a.y * b.x) * (other.a.y - other.b.y) - (a.y - b.y) * (other.a.x * other.b.y - other.a.y * other.b.x);
        double numeratorX = (a.x * b.y - a.y * b.x) * (other.a.x - other.b.x) - (a.x - b.x) * (other.a.x * other.b.y - other.a.y * other.b.x);
        double denominator = (a.x - b.x) * (other.a.y - other.b.y) - (a.y - b.y) * (other.a.x - other.b.x);


        double x = numeratorX / denominator;
        double y = numeratorY / denominator;


        return new Point(x, y);


    }

    double distanceFromPoint(Point p){

        //a1
        //b2
        //p3
        //double numerator = Math.abs((p.x * (a.y - b.y) + a.x * (b.y - p.y) + b.x * (p.y - a.y)) );

        double numerator = Math.abs(a.x*(b.y-p.y) + b.x*(p.y-a.y) + p.x*(a.y-b.y));
        double denominator = Math.sqrt((b.x-a.x)*(b.x-a.x) + (b.y-a.y)*(b.y-a.y));
        return numerator/denominator;

    }


    Direction getnormal() {
        // gets this lines normal( a vector facing 90 degrees from this line ) ((from a to b)) ((( of unit length)))
        //If we define dx = x2 - x1 and dy = y2 - y1, then the normals are (-dy, dx) and (dy, -dx)
        double directionX = b.x - a.x;
        double directionY = b.y - a.y;
        double length = Math.sqrt(directionX * directionX + directionY * directionY);
        directionX = (directionX / length);
        directionY = (directionY / length);


        return new Direction(-directionY, directionX);
    }


}





