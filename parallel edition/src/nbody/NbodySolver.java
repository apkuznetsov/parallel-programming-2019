package nbody;

import nbody.exceptions.BodiesNumOutOfBoundsException;

import java.util.concurrent.*;

import static nbody.NbodySolvers.*;

public class NbodySolver {

    private final Body[] b;
    private final int dt;
    private final double errorDistance;

    private final RecalcingCallable[] recalcingCallables;
    private final MovingCallable[] movingCallables;

    private final ExecutorService executor;
    private final Future[] recalcingFutures;
    private final Future[] movingFutures;

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

        final int[][] recalcingRanges = Helpers.ranges(0, b.length - 2, settings.threadsNum);
        final int[][] movingRanges = Helpers.ranges(1, b.length, settings.threadsNum);

        recalcingCallables = new RecalcingCallable[recalcingRanges.length];
        movingCallables = new MovingCallable[movingRanges.length];
        for (int i = 0; i < recalcingRanges.length; i++) {
            recalcingCallables[i] = new RecalcingCallable(recalcingRanges[i][0], recalcingRanges[i][1]);
            movingCallables[i] = new MovingCallable(movingRanges[i][0], movingRanges[i][1]);
        }

        executor = Executors.newFixedThreadPool(settings.threadsNum);
        recalcingFutures = new Future[settings.threadsNum];
        movingFutures = new Future[settings.threadsNum];
    }

    public NbodySolver(Body[] b, NbodySettings settings) {

        if (b.length < MIN_BODIES_NUM || b.length > MAX_BODIES_NUM) {
            throw new BodiesNumOutOfBoundsException();
        }

        this.b = b.clone();

        dt = settings.deltaTime;
        this.errorDistance = settings.errorDistance;

        final int[][] recalcingRanges = Helpers.ranges(0, b.length - 2, settings.threadsNum);
        final int[][] movingRanges = Helpers.ranges(1, b.length, settings.threadsNum);

        recalcingCallables = new RecalcingCallable[recalcingRanges.length];
        movingCallables = new MovingCallable[movingRanges.length];
        for (int i = 0; i < recalcingRanges.length; i++) {
            recalcingCallables[i] = new RecalcingCallable(recalcingRanges[i][0], recalcingRanges[i][1]);
            movingCallables[i] = new MovingCallable(movingRanges[i][0], movingRanges[i][1]);
        }

        executor = Executors.newFixedThreadPool(settings.threadsNum);
        recalcingFutures = new Future[settings.threadsNum];
        movingFutures = new Future[settings.threadsNum];
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
        for (int i = 0; i < recalcingCallables.length; i++) {
            recalcingFutures[i] = executor.submit(recalcingCallables[i]);
        }

        for (Future f : recalcingFutures) {
            try {
                f.get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void moveNBodies() {
        for (int i = 0; i < movingCallables.length; i++) {
            movingFutures[i] = executor.submit(movingCallables[i]);
        }

        for (Future f : movingFutures) {
            try {
                f.get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private class RecalcingCallable implements Callable<Void> {

        private final int leftIndex;
        private final int rightIndex;

        public RecalcingCallable(int leftIndex, int rightIndex) {
            this.leftIndex = leftIndex;
            this.rightIndex = rightIndex;
        }

        @Override
        public Void call() {
            double distance;
            double magnitude;
            Coords direction;

            for (int k = leftIndex; k <= rightIndex; k++) {
                for (int l = k + 1; l < b.length; l++) {
                    distance = distance(b[k], b[l]);
                    magnitude = (distance < errorDistance) ? 0.0 : magnitude(b[k], b[l], distance);
                    direction = direction(b[k], b[l]);

                    b[k].setF(
                            b[k].f().x() + magnitude * direction.x() / distance,
                            b[k].f().y() + magnitude * direction.y() / distance
                    );

                    synchronized (NbodySolver.this) {
                        b[l].setF(
                                b[l].f().x() - magnitude * direction.x() / distance,
                                b[l].f().y() - magnitude * direction.y() / distance
                        );
                    }
                }
            }

            return null;
        }
    }

    private class MovingCallable implements Callable<Void> {

        private final int leftIndex;
        private final int rightIndex;

        public MovingCallable(int rangeStart, int rangeEnd) {
            this.leftIndex = rangeStart - 1;
            this.rightIndex = rangeEnd - 1;
        }

        @Override
        public Void call() {

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

            return null;
        }
    }
}
