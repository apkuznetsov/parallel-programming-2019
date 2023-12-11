package nbody;

import static nbody.Bodies.G;

public class NbodySolvers {

    public static final int MIN_BODIES_NUM = 1;
    public static final int MAX_BODIES_NUM = 1024;
    public static final int DEFAULT_BODIES_NUM = 4;

    public static final int MIN_DELTA_TIME = 16;
    public static final int MAX_DELTA_TIME = 128;
    public static final int DEFAULT_DELTA_TIME = MIN_DELTA_TIME;

    public static final double MIN_ERROR_DISTANCE = 1e2;
    public static final double MAX_ERROR_DISTANCE = 1e3;
    public static final double DEFAULT_ERROR_DISTANCE = MIN_ERROR_DISTANCE;

    private static final Coords optdMemAllocDirection = new Coords(0.0, 0.0);
    private static final Coords optdMemAllocDv = new Coords(0.0, 0.0);
    private static final Coords optdMemAllocDp = new Coords(0.0, 0.0);

    public static double distance(Body b1, Body b2) {
        return Math.sqrt(
                Math.pow(b1.p().x() - b2.p().x(), 2) + Math.pow(b1.p().y() - b2.p().y(), 2)
        );
    }

    public static double magnitude(Body b1, Body b2, double b1b2distance) {
        return G * b1.m() * b2.m() / Math.pow(b1b2distance, 2);
    }

    public static Coords direction(Body b1, Body b2) {
        optdMemAllocDirection.set(
                b2.p().x() - b1.p().x(),
                b2.p().y() - b1.p().y());

        return optdMemAllocDirection;
    }

    public static Coords dv(Body b, long dt) {
        optdMemAllocDv.set(
                b.f().x() / b.m() * dt,
                b.f().y() / b.m() * dt);

        return optdMemAllocDv;
    }

    public static Coords dp(Body b, long dt, Coords dv) {
        optdMemAllocDp.set(
                (b.v().x() + dv.x() / 2) * dt,
                (b.v().y() + dv.y() / 2) * dt
        );

        return optdMemAllocDp;
    }
}
