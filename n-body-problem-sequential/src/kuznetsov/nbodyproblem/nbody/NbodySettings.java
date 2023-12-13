package kuznetsov.nbodyproblem.nbody;

import kuznetsov.nbodyproblem.nbody.exceptions.BodyMassOutOfBoundsException;
import kuznetsov.nbodyproblem.nbody.exceptions.DeltaTimeOutOfBoundsException;
import kuznetsov.nbodyproblem.nbody.exceptions.ErrorDistanceOutOfBoundsException;

import static kuznetsov.nbodyproblem.nbody.NbodySolvers.MAX_DELTA_TIME;
import static kuznetsov.nbodyproblem.nbody.NbodySolvers.MAX_ERROR_DISTANCE;
import static kuznetsov.nbodyproblem.nbody.NbodySolvers.MIN_DELTA_TIME;
import static kuznetsov.nbodyproblem.nbody.NbodySolvers.MIN_ERROR_DISTANCE;

public class NbodySettings {

    public final double bodyMass;
    public final int deltaTime;
    public final double errorDistance;
    public final int threadsNum;

    public NbodySettings(
            double bodyMass,
            int deltaTime,
            double errorDistance,
            int threadsNum
    ) {

        if (bodyMass < Bodies.MIN_BODY_MASS || bodyMass > Bodies.MAX_BODY_MASS) {
            throw new BodyMassOutOfBoundsException();
        }

        if (deltaTime < MIN_DELTA_TIME || deltaTime > MAX_DELTA_TIME) {
            throw new DeltaTimeOutOfBoundsException();
        }

        if (errorDistance < MIN_ERROR_DISTANCE || errorDistance > MAX_ERROR_DISTANCE) {
            throw new ErrorDistanceOutOfBoundsException();
        }

        this.bodyMass = bodyMass;
        this.deltaTime = deltaTime;
        this.errorDistance = errorDistance;
        this.threadsNum = threadsNum;
    }

    public NbodySettings(
            int deltaTime,
            double errorDistance,
            int threadsNum
    ) {

        bodyMass = Bodies.DEFAULT_BODY_MASS;

        if (deltaTime < MIN_DELTA_TIME || deltaTime > MAX_DELTA_TIME) {
            throw new DeltaTimeOutOfBoundsException();
        }

        if (errorDistance < MIN_ERROR_DISTANCE || errorDistance > MAX_ERROR_DISTANCE) {
            throw new ErrorDistanceOutOfBoundsException();
        }

        this.deltaTime = deltaTime;
        this.errorDistance = errorDistance;
        this.threadsNum = threadsNum;
    }
}
