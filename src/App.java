import nbody.NbodySolvers;
import nbodygui.Frame;

public class App {

    public static void main(String[] args) {
        Frame frame = new Frame(NbodySolvers.MIN_BODIES_NUM);
        frame.setVisible(true);
    }
}
