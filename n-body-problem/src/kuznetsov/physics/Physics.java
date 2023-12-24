package kuznetsov.physics;

import kuznetsov.point.Point;


public class Physics {

    public static final double G = 6.67e-11;

    public static double gravity(double m1, double m2, double r) {
        return G * m1 * m2 / Math.pow(r, 2);
    }

    public static Point dp(Body b, long dt, Point dv) {
        return new Point(
                (b.v().x() + dv.x() / 2) * dt,
                (b.v().y() + dv.y() / 2) * dt
        );
    }

    public static Point dv(Body b, long dt) {
        return new Point(
                b.f().x() / b.m() * dt,
                b.f().y() / b.m() * dt
        );
    }

}
