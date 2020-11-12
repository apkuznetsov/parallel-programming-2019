package nbody;

import static nbody.NbodySolvers.MIN_MASS;

public class Body {

    private final double m;
    private final Point p;
    private final Point v;
    private final Point f;

    public Body(Point xyCoords) {
        m = MIN_MASS;
        p = xyCoords;
        v = new Point(0.0, 0.0);
        f = new Point(0.0, 0.0);
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
