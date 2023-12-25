package kuznetsov.nobodyproblem;

import kuznetsov.physics.Body;
import kuznetsov.physics.Physics;
import kuznetsov.point.Point;
import kuznetsov.point.Points;
import kuznetsov.simulation.Simulation;

public class BodiesSimulation implements Simulation {

    private final int dt;
    private final Body[] bodies;
    private final double eps;

    public BodiesSimulation(BodiesParameters params) {
        this.dt = params.dt();
        this.bodies = BodiesSimulations.generateBodies(params);
        this.eps = params.eps();
    }

    public int dt() {
        return dt;
    }

    public int n() {
        return bodies.length;
    }

    public Point p(int index) {
        return bodies[index].p();
    }

    public void recompute() {
        computeForces();
        computeMoves();
    }

    private void computeForces() {
        int n = bodies.length;

        for (int i = 0; i < n - 1; i++) {
            Body curr = bodies[i];

            for (int j = i + 1; j < n; j++) {
                Body other = bodies[j];

                double distance = Points.distance(curr.p(), other.p());
                double magnitude = (distance < eps) ? 0.0 : Physics.gravity(curr.m(), other.m(), distance);
                Point direction = Points.direction(curr.p(), other.p());
                double dfx = magnitude * direction.x() / distance;
                double dfy = magnitude * direction.y() / distance;

                curr.setF(
                        curr.f().x() + dfx,
                        curr.f().y() + dfy
                );
                other.setF(
                        other.f().x() - dfx,
                        other.f().y() - dfy
                );
            }
        }
    }

    private void computeMoves() {
        for (Body body : bodies) {
            Point dv = Physics.dv(body, dt);
            Point dp = Physics.dp(body, dt, dv);

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
