package nbody;

import nbody.exceptions.BodiesNumOutOfBoundsException;
import nbody.exceptions.DeltaTimeOutOfBoundsException;

import static nbody.NbodySolvers.*;

public class NbodySolver {

    private final Body[] b;
    private final int dt;

    public NbodySolver(Point[] bodiesCoords, final int DT) {

        if (bodiesCoords.length < MIN_N || bodiesCoords.length > MAX_N) {
            throw new BodiesNumOutOfBoundsException();
        }

        if (DT < MIN_DT || DT > MAX_DT) {
            throw new DeltaTimeOutOfBoundsException();
        }

        b = new Body[bodiesCoords.length];
        for (int i = 0; i < b.length; i++) {
            b[i] = new Body(bodiesCoords[i]);
        }

        dt = DT;
    }

    public int N() {
        return b.length;
    }

    public int DT() {
        return dt;
    }

    public int bodyX(int index) {
        return (int) b[index].p().x();
    }

    public int bodyY(int index) {
        return (int) b[index].p().y();
    }

    public void recalcNBodiesCoords() {
        calcNForces();
        moveNBodies();
    }

    private void calcNForces() {
        double distance;
        double magnitude;
        Point direction;

        final int N = b.length;
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                distance = distance(b[i], b[j]);
                magnitude = magnitude(b[i], b[j], distance);
                direction = direction(b[i], b[j]);

                b[i].setF(
                        b[i].f().x() + magnitude * direction.x() / distance,
                        b[i].f().y() + magnitude * direction.y() / distance
                );

                b[j].setF(
                        b[i].f().x() - magnitude * direction.x() / distance,
                        b[i].f().y() - magnitude * direction.y() / distance
                );
            }
        }
    }

    private void moveNBodies() {
        Point dv; // dv = f/m * dt
        Point dp; // dp = (v + dv/2) * dt

        for (Body body : b) {
            dv = dv(body, dt);
            dp = dp(body, dt, dv);

            body.setV(
                    body.v().x() + dv.x(),
                    body.v().y() + dv.y()
            );

            body.setP(
                    body.p().x() + dp.x(),
                    body.p().y() + dp.y()
            );

            body.setF(0.0, 0.0);
        }
    }
}
