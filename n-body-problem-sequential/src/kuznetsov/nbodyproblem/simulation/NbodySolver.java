package kuznetsov.nbodyproblem.simulation;

import kuznetsov.nbodyproblem.body.Body;
import kuznetsov.nbodyproblem.point.Point;
import kuznetsov.nbodyproblem.simulation.exceptions.BodiesNumOutOfBoundsException;
import kuznetsov.nbodyproblem.simulation.exceptions.DeltaTimeOutOfBoundsException;
import kuznetsov.nbodyproblem.simulation.exceptions.ErrorDistanceOutOfBoundsException;

import static kuznetsov.nbodyproblem.simulation.NbodySolvers.MAX_BODIES_NUM;
import static kuznetsov.nbodyproblem.simulation.NbodySolvers.MAX_DELTA_TIME;
import static kuznetsov.nbodyproblem.simulation.NbodySolvers.MAX_ERROR_DISTANCE;
import static kuznetsov.nbodyproblem.simulation.NbodySolvers.MIN_BODIES_NUM;
import static kuznetsov.nbodyproblem.simulation.NbodySolvers.MIN_DELTA_TIME;
import static kuznetsov.nbodyproblem.simulation.NbodySolvers.MIN_ERROR_DISTANCE;
import static kuznetsov.nbodyproblem.simulation.NbodySolvers.direction;
import static kuznetsov.nbodyproblem.simulation.NbodySolvers.distance;
import static kuznetsov.nbodyproblem.simulation.NbodySolvers.dp;
import static kuznetsov.nbodyproblem.simulation.NbodySolvers.dv;
import static kuznetsov.nbodyproblem.simulation.NbodySolvers.magnitude;

public class NbodySolver {

    private final Body[] b;
    private final int dt;
    private final double errorDistance;

    public NbodySolver(Point[] bodiesCoords, double bodyMass, int deltaTime, double errorDistance) {

        if (bodiesCoords.length < MIN_BODIES_NUM || bodiesCoords.length > MAX_BODIES_NUM) {
            throw new BodiesNumOutOfBoundsException();
        }

        if (deltaTime < MIN_DELTA_TIME || deltaTime > MAX_DELTA_TIME) {
            throw new DeltaTimeOutOfBoundsException();
        }

        if (errorDistance < MIN_ERROR_DISTANCE || errorDistance > MAX_ERROR_DISTANCE) {
            throw new ErrorDistanceOutOfBoundsException();
        }

        b = new Body[bodiesCoords.length];
        for (int i = 0; i < b.length; i++) {
            b[i] = new Body(bodiesCoords[i], bodyMass);
        }

        dt = deltaTime;
        this.errorDistance = errorDistance;
    }

    public NbodySolver(Body[] b, int deltaTime, double errorDistance) {

        if (b.length < MIN_BODIES_NUM || b.length > MAX_BODIES_NUM) {
            throw new BodiesNumOutOfBoundsException();
        }

        if (deltaTime < MIN_DELTA_TIME || deltaTime > MAX_DELTA_TIME) {
            throw new DeltaTimeOutOfBoundsException();
        }

        if (errorDistance < MIN_ERROR_DISTANCE || errorDistance > MAX_ERROR_DISTANCE) {
            throw new ErrorDistanceOutOfBoundsException();
        }

        this.b = b.clone();

        dt = deltaTime;
        this.errorDistance = errorDistance;
    }

    public int n() {
        return b.length;
    }

    public int dt() {
        return dt;
    }

    public int bodyX(int index) {
        return (int) b[index].getPoint().x();
    }

    public int bodyY(int index) {
        return (int) b[index].getPoint().y();
    }

    public void recalcBodiesCoords() {
        recalcBodiesForces();
        moveNBodies();
    }

    private void recalcBodiesForces() {
        double distance;
        double magnitude;
        Point direction;

        final int n = b.length;
        for (int k = 0; k < n - 1; k++) {
            for (int l = k + 1; l < n; l++) {
                distance = distance(b[k], b[l]);
                magnitude = (distance < errorDistance) ? 0.0 : magnitude(b[k], b[l], distance);
                direction = direction(b[k], b[l]);

                b[k].setF(
                        b[k].getF().x() + magnitude * direction.x() / distance,
                        b[k].getF().y() + magnitude * direction.y() / distance
                );

                b[l].setF(
                        b[l].getF().x() - magnitude * direction.x() / distance,
                        b[l].getF().y() - magnitude * direction.y() / distance
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
                    body.getV().x() + dv.x(),
                    body.getV().y() + dv.y()
            );

            body.setPoint(
                    body.getPoint().x() + dp.x(),
                    body.getPoint().y() + dp.y()
            );

            body.setF(0.0, 0.0);
        }
    }
}
