package nbody;

import nbody.exceptions.MOutOfBoundsException;

import static nbody.Bodies.MAX_M;
import static nbody.Bodies.MIN_M;

public class Body {

    private final double m;
    private Point p;
    private Point v;
    private Point f;

    public Body() {
        p = new Point(0.0, 0.0);
        v = new Point(0.0, 0.0);
        f = new Point(0.0, 0.0);
        m = MIN_M;
    }

    public Body(double x, double y) {
        p = new Point(x, y);
        v = new Point(0.0, 0.0);
        f = new Point(0.0, 0.0);
        m = MIN_M;
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
        this.p = new Point(x, y);
    }

    public void setV(double x, double y) {
        this.v = new Point(x, y);
    }

    public void setF(double x, double y) {
        this.f = new Point(x, y);
    }

    public double m() {
        return m;
    }
}
