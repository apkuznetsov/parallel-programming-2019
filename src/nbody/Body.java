package nbody;

import static nbody.NbodySolvers.MIN_MASS;

public class Body {

    private final double m;
    private Point p;
    private Point v;
    private Point f;

    public Body(Point xyCoords) {
        p = xyCoords;
        v = new Point(0.0, 0.0);
        f = new Point(0.0, 0.0);
        m = MIN_MASS;
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
