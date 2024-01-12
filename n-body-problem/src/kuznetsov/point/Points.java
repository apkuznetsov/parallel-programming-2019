package kuznetsov.point;

public class Points {

    public static double distance(Point p1, Point p2) {
        return Math.sqrt(
                Math.pow(p1.x() - p2.x(), 2) + Math.pow(p1.y() - p2.y(), 2)
        );
    }

    public static Point direction(Point p1, Point p2) {
        return new Point(
                p2.x() - p1.x(),
                p2.y() - p1.y()
        );
    }

}
