package nbody;

import static nbody.Bodies.MIN_MASS;

public class Body {

    private final double m;
    private final Coords p;
    private final Coords v;
    private final Coords f;

    public Body(Coords xyCoords) {
        m = MIN_MASS;
        p = xyCoords;
        v = new Coords(0.0, 0.0);
        f = new Coords(0.0, 0.0);
    }

    public Coords p() {
        return p;
    }

    public Coords v() {
        return v;
    }

    public Coords f() {
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
