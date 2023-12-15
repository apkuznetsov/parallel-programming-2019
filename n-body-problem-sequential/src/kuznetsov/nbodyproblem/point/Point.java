package kuznetsov.nbodyproblem.point;

public class Point {

    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point(Point point) {
        this.x = point.x;
        this.y = point.y;
    }

    public double x() {
        return x;
    }

    public double y() {
        return y;
    }

    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

}
