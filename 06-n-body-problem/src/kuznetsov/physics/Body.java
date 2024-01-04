package kuznetsov.physics;

import kuznetsov.point.Point;

public class Body {

    private final double m;
    private final Point p;
    private final Point v;
    private final Point f;

    public Body(Point p, double m) {
        this.m = m;
        this.p = new Point(p);
        this.v = new Point(0.0, 0.0);
        this.f = new Point(0.0, 0.0);
    }

    public double m() {
        return m;
    }

    public Point p() {
        return p;
    }

    public Point v() {
        return v;
    }

    public Point f() {
        return f;
    }

    public void setP(double x, double y) {
        p.setP(x, y);
    }

    public void setV(double x, double y) {
        v.setP(x, y);
    }

    public void setF(double x, double y) {
        f.setP(x, y);
    }

}
