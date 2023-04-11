public class Point {
    double x;
    double y;

    Point() {
        x = 0;
        y = 0;
    }

    Point(double X, double Y) {
        x = X;
        y = Y;
    }


    public double Distance(Point other) {


        return ((other.x - x) * (other.x - x) + (other.y - y) * (other.y - y));


    }

    @Override
    public String toString() {
        return x + " " + y;
    }
}





