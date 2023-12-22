package kuznetsov.nbodyproblemapp;

import kuznetsov.nobodyproblem.BodiesParameters;
import kuznetsov.nobodyproblem.BodiesSimulation;
import kuznetsov.simulationgui.PanelSimulation;

public class Main {

    public static final String TITLE = "n-body problem solver";

    public static void main(String[] args) {
        BodiesParameters params = ParamsParser.parse("settings.xml");
        BodiesSimulation simulation = new BodiesSimulation(params);

        PanelSimulation panel = new PanelSimulation(simulation, params.durationMillis());
        Frame frame = new Frame(TITLE, params.width(), params.height(), panel);
        frame.setVisible(true);
    }

}
