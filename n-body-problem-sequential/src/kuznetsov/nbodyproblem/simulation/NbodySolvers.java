package kuznetsov.nbodyproblem.simulation;

import kuznetsov.nbodyproblem.body.Body;
import kuznetsov.nbodyproblem.point.Point;

import static kuznetsov.nbodyproblem.simulation.Bodies.G;

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

    private static final Point optdMemAllocDirection = new Point(0.0, 0.0);
    private static final Point optdMemAllocDv = new Point(0.0, 0.0);
    private static final Point optdMemAllocDp = new Point(0.0, 0.0);

    public static double distance(Body b1, Body b2) {
        return Math.sqrt(
                Math.pow(b1.getPoint().x() - b2.getPoint().x(), 2) + Math.pow(b1.getPoint().y() - b2.getPoint().y(), 2)
        );
    }

    public static double magnitude(Body b1, Body b2, double b1b2distance) {
        return G * b1.getM() * b2.getM() / Math.pow(b1b2distance, 2);
    }

    public static Point direction(Body b1, Body b2) {
        optdMemAllocDirection.set(
                b2.getPoint().x() - b1.getPoint().x(),
                b2.getPoint().y() - b1.getPoint().y());

        return optdMemAllocDirection;
    }

    public static Point dv(Body b, long dt) {
        optdMemAllocDv.set(
                b.getF().x() / b.getM() * dt,
                b.getF().y() / b.getM() * dt);

        return optdMemAllocDv;
    }

    public static Point dp(Body b, long dt, Point dv) {
        optdMemAllocDp.set(
                (b.getV().x() + dv.x() / 2) * dt,
                (b.getV().y() + dv.y() / 2) * dt
        );

        return optdMemAllocDp;
    }
}
