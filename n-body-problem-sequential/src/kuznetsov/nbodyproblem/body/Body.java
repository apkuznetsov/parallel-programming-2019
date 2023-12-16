package kuznetsov.nbodyproblem.body;

import kuznetsov.nbodyproblem.point.Point;

public class Body {

    private final double m;
    private final Point point;
    private final Point v;
    private final Point f;

    public Body(Point point, double m) {
        this.m = m;
        this.point = new Point(point);
        this.v = new Point(0.0, 0.0);
        this.f = new Point(0.0, 0.0);
    }

    public double getM() {
        return m;
    }

    public Point getPoint() {
        return point;
    }

    public Point getV() {
        return v;
    }

    public Point getF() {
        return f;
    }

    public void setPoint(double x, double y) {
        point.set(x, y);
    }

    public void setV(double x, double y) {
        v.set(x, y);
    }

    public void setF(double x, double y) {
        f.set(x, y);
    }

}
