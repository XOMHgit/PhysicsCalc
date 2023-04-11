public class Direction {


    public double x;
    public double y;

    Direction() {
        x = 0;
        y = 0;
    }

    Direction(double X, double Y) {
        x = X;
        y = Y;
    }


    Direction reflectOver(Direction other) {
        // returns this vector reflected over the given vector


        double dotprod = (x * other.x) + y * other.y;


        double parallelX = ((dotprod)) * other.x;
        double parallelY = ((dotprod)) * other.y;
        double perpendicularX = x - parallelX;
        double perpendicularY = y - parallelY;


        double reflectedx = (perpendicularX - parallelX);
        double reflectedy = (perpendicularY - parallelY);


        return new Direction(reflectedx, reflectedy);
    }


}





