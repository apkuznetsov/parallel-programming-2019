package kuznetsov.nbodyproblem;

import kuznetsov.nbodyproblem.body.Body;
import kuznetsov.nbodyproblem.gui.Frame;
import kuznetsov.nbodyproblem.gui.Frames;
import kuznetsov.nbodyproblem.gui.Panel;
import kuznetsov.nbodyproblem.gui.Panels;
import kuznetsov.nbodyproblem.point.Point;
import kuznetsov.nbodyproblem.simulation.Bodies;
import kuznetsov.nbodyproblem.simulation.NbodySolver;
import kuznetsov.nbodyproblem.simulation.NbodySolvers;

import java.awt.Dimension;
import java.io.IOException;
import java.util.Random;

public class App {

    public static void main(String[] args) throws IOException {

        final AppSettingsParser parser = new AppSettingsParser();
        final int parsedWidth = (parser.width() == null) ? Frames.DEFAULT_WIDTH : parser.width();
        final int parsedHeight = (parser.height() == null) ? Frames.DEFAULT_HEIGHT : parser.height();
        final int parsedBodiesNum = (parser.bodiesNum() == null) ? NbodySolvers.DEFAULT_BODIES_NUM : parser.bodiesNum();
        final double parsedBodyMass = (parser.bodyMass() == null) ? Bodies.DEFAULT_BODY_MASS : parser.bodyMass();
        final int parsedDeltaTime = (parser.deltaTime() == null) ? NbodySolvers.DEFAULT_DELTA_TIME : parser.deltaTime();
        final double parsedErrorDistance = (parser.errorDistance() == null) ? NbodySolvers.DEFAULT_ERROR_DISTANCE : parser.errorDistance();
        final int parsedDurationMillis = (parser.durationMillis() == null) ? Panels.DEFAULT_DURATION_MILLIS : parser.durationMillis();

        final Dimension coordsBounds = new Dimension(parsedWidth - 100, parsedHeight - 100);
        final Point[] randomPointArr = randomCoordsArr(parsedBodiesNum, coordsBounds);
        final NbodySolver solver = new NbodySolver(randomPointArr, parsedBodyMass, parsedDeltaTime, parsedErrorDistance);

        final Panel panel = new Panel(solver, parsedDurationMillis);

        final Frame frame = new Frame(parsedWidth, parsedHeight, panel);
        frame.setVisible(true);
    }

    public static Point[] randomCoordsArr(int num, Dimension coordsBounds) {

        final Point[] randomPointArr = new Point[num];

        int x, y;
        final Random random = new Random();

        for (int i = 0; i < randomPointArr.length; i++) {
            x = Math.abs(random.nextInt()) % coordsBounds.width;
            y = Math.abs(random.nextInt()) % coordsBounds.height;
            randomPointArr[i] = new Point(x, y);
        }

        return randomPointArr;
    }

    public static Body[] pseudoEarthAndMoon(double x, double y) {
        Body earth = new Body(new Point(x, y), 6e10);
        Body moon = new Body(new Point(earth.getPoint().x(), earth.getPoint().y() + 300), 6e4);
        return new Body[]{earth, moon};
    }
}
