package nbody;

public class NbodySolvers {

    public static final double G = 6.67e-11;

    public static final int MIN_BODIES_NUM = 2;
    public static final int MAX_BODIES_NUM = 16;
    
    public static final int MIN_DELTA_TIME = 16;
    public static final int MAX_DELTA_TIME = 128;

    public static final double MIN_MASS = 1e10;
    public static final double MAX_MASS = 9e10;

    public static double distance(Body b1, Body b2) {
        return Math.sqrt(
                Math.pow(b1.p().x() - b2.p().x(), 2) + Math.pow(b1.p().y() - b2.p().y(), 2)
        );
    }

    public static double magnitude(Body b1, Body b2, double b1b2distance) {
        return G * b1.m() * b2.m() / Math.pow(b1b2distance, 2);
    }

    public static Point direction(Body b1, Body b2) {
        return new Point(
                b2.p().x() - b1.p().x(),
                b2.p().y() - b1.p().y()
        );
    }

    public static Point dv(Body b, long dt) {
        return new Point(
                b.f().x() / b.m() * dt,
                b.f().y() / b.m() * dt
        );
    }

    public static Point dp(Body b, long dt, Point dv) {
        return new Point(
                (b.v().x() + dv.x() / 2) * dt,
                (b.v().y() + dv.y() / 2) * dt
        );
    }
}
