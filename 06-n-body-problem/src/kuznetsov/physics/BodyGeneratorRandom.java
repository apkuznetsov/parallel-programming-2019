package kuznetsov.physics;

import kuznetsov.point.Point;

import java.util.Random;

public class BodyGeneratorRandom implements BodyGenerator {

    private final int n;
    private final double m;
    private final int width;
    private final int height;

    private final Random random;
    private final int randomMin;
    private final int randomMax;

    public BodyGeneratorRandom(int n, double m, int width, int height) {
        this.n = n;
        this.m = m;
        this.width = width;
        this.height = height;

        this.random = new Random();
        this.randomMin = -100;
        this.randomMax = 100;
    }

    @Override
    public Body[] generateBodies() {
        Body[] bodies = new Body[n];

        for (int i = 0; i < bodies.length; i++) {
            int x = width / 2 + randomShift();
            int y = height / 2 + randomShift();
            bodies[i] = new Body(new Point(x, y), m);
        }

        return bodies;
    }

    private int randomShift() {
        int range = randomMax - randomMin + 1;
        return random.nextInt(range) + randomMin;
    }

}
