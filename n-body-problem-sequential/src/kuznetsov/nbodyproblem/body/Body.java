package kuznetsov.nbodyproblem.body;

import kuznetsov.nbodyproblem.point.Point;
import kuznetsov.nbodyproblem.simulation.exceptions.BodyMassOutOfBoundsException;

import static kuznetsov.nbodyproblem.simulation.Bodies.MAX_BODY_MASS;
import static kuznetsov.nbodyproblem.simulation.Bodies.MIN_BODY_MASS;

public class Body {

    private final double m;
    private final Point p;
    private final Point v;
    private final Point f;

    public Body(Point p, double m) {
        if (m < MIN_BODY_MASS || m > MAX_BODY_MASS) {
            throw new BodyMassOutOfBoundsException();
        }

        this.m = m;
        this.p = new Point(p);
        this.v = new Point(0.0, 0.0);
        this.f = new Point(0.0, 0.0);
    }

    public Body(Point p, double m, Point v) {
        if (m < MIN_BODY_MASS || m > MAX_BODY_MASS) {
            throw new BodyMassOutOfBoundsException();
        }

        this.m = m;
        this.p = new Point(p);
        this.v = new Point(v);
        this.f = new Point(0.0, 0.0);
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
        p.set(x, y);
    }

    public void setV(double x, double y) {
        v.set(x, y);
    }

    public void setF(double x, double y) {
        f.set(x, y);
    }

    public double m() {
        return m;
    }
}
