package nbody;

import nbody.exceptions.BodiesNumOutOfBoundsException;
import nbody.exceptions.DeltaTimeOutOfBoundsException;
import nbody.exceptions.ErrorDistanceOutOfBoundsException;
import nbody.exceptions.ThreadsNumOutOfBoundsException;

import java.util.ArrayList;
import java.util.List;

import static nbody.NbodySolvers.*;

public class NbodySolver {

    private final Body[] b;
    private final int dt;
    private final double errorDistance;
    private final int threadsNum;

    public NbodySolver(Coords[] bodiesCoords, NbodySettings settings) {

        if (bodiesCoords.length < MIN_BODIES_NUM || bodiesCoords.length > MAX_BODIES_NUM) {
            throw new BodiesNumOutOfBoundsException();
        }

        b = new Body[bodiesCoords.length];
        for (int i = 0; i < b.length; i++) {
            b[i] = new Body(bodiesCoords[i], settings.bodyMass);
        }

        dt = settings.deltaTime;
        this.errorDistance = settings.errorDistance;
        this.threadsNum = settings.threadsNum;
    }

    public NbodySolver(Body[] b, int deltaTime, double errorDistance, int threadsNum) {

        if (b.length < MIN_BODIES_NUM || b.length > MAX_BODIES_NUM) {
            throw new BodiesNumOutOfBoundsException();
        }

        if (deltaTime < MIN_DELTA_TIME || deltaTime > MAX_DELTA_TIME) {
            throw new DeltaTimeOutOfBoundsException();
        }

        if (errorDistance < MIN_ERROR_DISTANCE || errorDistance > MAX_ERROR_DISTANCE) {
            throw new ErrorDistanceOutOfBoundsException();
        }

        if (threadsNum < MIN_THREADS_NUM || threadsNum > MAX_THREADS_NUM) {
            throw new ThreadsNumOutOfBoundsException();
        }

        this.b = b.clone();

        dt = deltaTime;
        this.errorDistance = errorDistance;
        this.threadsNum = threadsNum;
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

        // MOVE N BODIES
        int[][] ranges = Helpers.ranges(b.length, threadsNum);
        List<Thread> threads = new ArrayList<>(threadsNum);
        Thread currThread;
        for (int[] range : ranges) {
            currThread = new MoveNBodiesThread(range[0], range[1]);
            currThread.start();
            threads.add(currThread);
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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

    private class MoveNBodiesThread extends Thread {

        private final int leftIndex;
        private final int rightIndex;

        public MoveNBodiesThread(int rangeStart, int rangeEnd) {
            this.leftIndex = rangeStart - 1;
            this.rightIndex = rangeEnd - 1;
        }

        @Override
        public void run() {

            Coords dv; // dv = f/m * dt
            Coords dp; // dp = (v + dv/2) * dt

            for (int i = leftIndex; i <= rightIndex; i++) {
                dv = dv(b[i], dt);
                dp = dp(b[i], dt, dv);

                b[i].setV(
                        b[i].v().x() + dv.x(),
                        b[i].v().y() + dv.y()
                );

                b[i].setP(
                        b[i].p().x() + dp.x(),
                        b[i].p().y() + dp.y()
                );

                b[i].setF(0.0, 0.0);
            }
        }
    }
}
