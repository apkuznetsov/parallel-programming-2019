package kuznetsov.nobodyproblem;

import kuznetsov.physics.Body;
import kuznetsov.point.Point;

import java.util.Random;

public class BodiesSimulations {

    public static Body[] generateBodies(BodiesParameters params) {
        Body[] bodies = new Body[params.n()];

        double m = params.m();
        int w = params.width() - 1000;
        int h = params.height() - 1000;
        Random random = new Random();

        for (int i = 0; i < bodies.length; i++) {
            bodies[i] = new Body(new Point(Math.abs(random.nextInt()) % w, Math.abs(random.nextInt()) % h), m);
        }
        bodies[0] = new Body(new Point(500, 500), bodies[0].m() * bodies[0].m());

        return bodies;
    }

}
