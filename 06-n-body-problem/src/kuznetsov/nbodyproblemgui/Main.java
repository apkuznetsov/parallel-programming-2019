package kuznetsov.nbodyproblemgui;

import kuznetsov.nobodyproblem.BodiesParameters;
import kuznetsov.nobodyproblem.BodiesSimulation;
import kuznetsov.physics.BodyGenerator;
import kuznetsov.physics.BodyGeneratorRandom;
import kuznetsov.simulationgui.SimulationFrame;

public class Main {

    public static final String TITLE = "n-body problem solver";

    public static void main(String[] args) {
        BodiesParameters params = ParamsParser.parse("settings.xml");
        BodyGenerator generator = new BodyGeneratorRandom(params.n(), params.m(), params.width(), params.height());
        BodiesSimulation simulation = new BodiesSimulation(generator, params.dt(), params.eps());

        SimulationFrame frame = new SimulationFrame(
                TITLE, params.width(), params.height(),
                params.durationMillis(), simulation
        );
        frame.setVisible(true);
    }

}
