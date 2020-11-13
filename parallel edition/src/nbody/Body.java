package nbody;

import nbody.exceptions.BodyMassOutOfBoundsException;

import static nbody.Bodies.MAX_BODY_MASS;
import static nbody.Bodies.MIN_BODY_MASS;

public class Body {

    private final double m;
    private final Coords p;
    private final Coords v;
    private final Coords f;

    public Body(Coords xyCoords, double mass) {
        if (mass < MIN_BODY_MASS || mass > MAX_BODY_MASS) {
            throw new BodyMassOutOfBoundsException();
        }

        m = mass;
        p = xyCoords.clone();
        v = new Coords(0.0, 0.0);
        f = new Coords(0.0, 0.0);
    }

    public Body(Coords xyCoords, double mass, Coords v) {
        if (mass < MIN_BODY_MASS || mass > MAX_BODY_MASS) {
            throw new BodyMassOutOfBoundsException();
        }

        m = mass;
        p = xyCoords.clone();
        this.v = v.clone();
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
