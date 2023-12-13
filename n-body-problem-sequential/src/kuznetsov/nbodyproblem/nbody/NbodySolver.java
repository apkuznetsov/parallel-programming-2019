package kuznetsov.nbodyproblem.nbody;

import kuznetsov.nbodyproblem.nbody.exceptions.BodiesNumOutOfBoundsException;
import kuznetsov.nbodyproblem.nbody.exceptions.DeltaTimeOutOfBoundsException;
import kuznetsov.nbodyproblem.nbody.exceptions.ErrorDistanceOutOfBoundsException;

import static kuznetsov.nbodyproblem.nbody.NbodySolvers.MAX_BODIES_NUM;
import static kuznetsov.nbodyproblem.nbody.NbodySolvers.MAX_DELTA_TIME;
import static kuznetsov.nbodyproblem.nbody.NbodySolvers.MAX_ERROR_DISTANCE;
import static kuznetsov.nbodyproblem.nbody.NbodySolvers.MIN_BODIES_NUM;
import static kuznetsov.nbodyproblem.nbody.NbodySolvers.MIN_DELTA_TIME;
import static kuznetsov.nbodyproblem.nbody.NbodySolvers.MIN_ERROR_DISTANCE;
import static kuznetsov.nbodyproblem.nbody.NbodySolvers.direction;
import static kuznetsov.nbodyproblem.nbody.NbodySolvers.distance;
import static kuznetsov.nbodyproblem.nbody.NbodySolvers.dp;
import static kuznetsov.nbodyproblem.nbody.NbodySolvers.dv;
import static kuznetsov.nbodyproblem.nbody.NbodySolvers.magnitude;

public class NbodySolver {

    private final Body[] b;
    private final int dt;
    private final double errorDistance;

    public NbodySolver(Coords[] bodiesCoords, double bodyMass, int deltaTime, double errorDistance) {

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
        return (int) b[index].p().x();
    }

    public int bodyY(int index) {
        return (int) b[index].p().y();
    }

    public void recalcBodiesCoords() {
        recalcBodiesForces();
        moveNBodies();
    }

    private void recalcBodiesForces() {
        double distance;
        double magnitude;
        Coords direction;

        final int n = b.length;
        for (int k = 0; k < n - 1; k++) {
            for (int l = k + 1; l < n; l++) {
                distance = distance(b[k], b[l]);
                magnitude = (distance < errorDistance) ? 0.0 : magnitude(b[k], b[l], distance);
                direction = direction(b[k], b[l]);

                b[k].setF(
                        b[k].f().x() + magnitude * direction.x() / distance,
                        b[k].f().y() + magnitude * direction.y() / distance
                );

                b[l].setF(
                        b[l].f().x() - magnitude * direction.x() / distance,
                        b[l].f().y() - magnitude * direction.y() / distance
                );
            }
        }
    }

    private void moveNBodies() {
        Coords dv; // dv = f/m * dt
        Coords dp; // dp = (v + dv/2) * dt

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
