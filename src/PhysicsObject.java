import java.awt.*;
import java.util.ArrayList;




public class PhysicsObject {




    public Direction movement;


    public int rotation;
    public  boolean anchored;
    public Polygon polygon;
    public double mass;
    public  boolean colliding = false;



    GamePanel gamePanel;


    public PhysicsObject(Polygon pols,Direction Mvmnt, int r,boolean Anchored,GamePanel gp,double Mass) {
        polygon = pols;
        movement = Mvmnt;
        rotation = r;
        anchored = Anchored;
        gamePanel = gp;
        mass = Mass;
    }


    private void collideWith(PhysicsObject otherObject,Line otherSide){

        // calculate movement of both objects after
        if (otherObject.anchored){
            otherObject.movement = new Direction(0,0);
        }

        double MoveX = (otherObject.mass * otherObject.movement.x) + (mass * movement.x);
        double MoveY = (otherObject.mass * otherObject.movement.y) + (mass * movement.y);
        Direction TotalVector = new Direction(MoveX/1.1,MoveY/1.1);

        movement = new Direction(TotalVector.x/mass,TotalVector.y/mass);
        movement = movement.reflectOver(otherSide.getnormal()); //MAKE IT BOUNC

        if (!otherObject.anchored){
            otherObject.movement = new Direction(TotalVector.x/otherObject.mass,TotalVector.y/otherObject.mass);
        }
    }
    private void movementHandler() {
        ArrayList<PhysicsObject> objList = gamePanel.objList;


        // check if about to collide with anything
        colliding = false;
        collide:{


                for (PhysicsObject other:objList) {

                    if (other != this){

                        for (Point point : polygon.points) {


                            for (PhysicsObject object:objList) {


                                if (!(object == (this)) && object.polygon.contains(point.x+movement.x,point.y+movement.y)){
                                    // collide

                                    Point movingToPoint = new Point(point.x + movement.x,point.y + movement.y);


                                    Line nextPositionOfPoint = new Line(point,movingToPoint);


                                    Line collisionLine = new Line(other.polygon.points[0],other.polygon.points[0]);


                                    // find which side is closer( the one we will collide with) and store that line for calculations
                                    double closestIntersection = Integer.MAX_VALUE;
                                    for (int i = 0; i < other.polygon.points.length;i++){


                                        // get line from this point to last point on the other polygon
                                        Point nextPoint;
                                        if (i == 0){
                                            nextPoint = other.polygon.points[other.polygon.points.length-1];
                                        }else {
                                            nextPoint = other.polygon.points[i-1];
                                        }


                                        Line comparetoLine = new Line(other.polygon.points[i],nextPoint);
                                        Point Intersection = nextPositionOfPoint.intersect(comparetoLine);


                                        // save distance as lowest if point lowest
                                        if (point.Distance(Intersection)< closestIntersection){
                                            closestIntersection = point.Distance(Intersection);
                                            collisionLine = comparetoLine;
                                        }
                                    }

                                    collideWith(other,collisionLine);
                                    colliding = true;
                                    object.colliding = true;
                                    break collide;
                                }
                            }
                        }


                        //loop through all sides and see if they collide with all other sides
                        for (int i = 0; i < other.polygon.npoints;i++){

                            Point nextOtherPoint =  i==other.polygon.npoints-1 ? other.polygon.points[0] : other.polygon.points[i+1];
                            Line otherSide = new Line(other.polygon.points[i],nextOtherPoint);

                            for (int j = 0; j < polygon.npoints;j++){

                                Point nextPoint =  j==polygon.npoints-1 ? polygon.points[0] : polygon.points[j+1];
                                Line side = new Line(polygon.points[j],nextPoint);

                                Point Intersection = side.intersect(otherSide);

                                if (polygon.contains(Intersection.x,Intersection.y) && other.polygon.contains(Intersection.x,Intersection.y)){
                                    // collide
                                    collideWith(other,otherSide);
                                    colliding = true;
                                    other.colliding = true;
                                    break collide;
                                }

                            }
                        }

                    }
                }


        }



    }




    public void Update() {




        if (!anchored){
            movementHandler();
            //movement = new Direction(movement.x/1.0005, movement.y/1.0005);
        }else {
            movement = new Direction(0,0);
        }

    }

    @Override
    public String toString() {
        return "PhysicsObject{" +
                "movement=" + movement +
                ", rotation=" + rotation +
                ", anchored=" + anchored +
                ", polygon=" + polygon +
                ", mass=" + mass +
                ", colliding=" + colliding +
                ", gamePanel=" + gamePanel +
                '}';
    }
}





