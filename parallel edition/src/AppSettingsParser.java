import nbody.Bodies;
import nbody.NbodySolvers;
import nbodygui.Frames;
import nbodygui.Panels;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class AppSettingsParser {

    private static final String WIDTH_TAG_NAME = "width";
    private static final String HEIGHT_TAG_NAME = "height";
    private static final String BODIES_NUM_TAG_NAME = "bodiesNum";
    private static final String BODY_MASS_TAG_NAME = "bodyMass";
    private static final String DELTA_TIME_TAG_NAME = "deltaTime";
    private static final String ERROR_DISTANCE_TAG_NAME = "errorDistance";
    private static final String DURATION_SECONDS_TAG_NAME = "durationSeconds";
    private static final String THREADS_NUM_TAG_NAME = "threadsNum";

    private final String settingsXml;

    public AppSettingsParser() throws IOException {
        settingsXml = new String(Files.readAllBytes(Path.of("settings.xml")));
    }

    public Integer width() {
        Integer width;
        try {
            width = Integer.parseInt(value(WIDTH_TAG_NAME));
        } catch (NumberFormatException e) {
            width = null;
        }
        return width;
    }

    public Integer height() {
        Integer height;
        try {
            height = Integer.parseInt(value(HEIGHT_TAG_NAME));
        } catch (NumberFormatException e) {
            height = null;
        }
        return height;
    }

    public Integer bodiesNum() {
        Integer bodiesNum;
        try {
            bodiesNum = Integer.parseInt(value(BODIES_NUM_TAG_NAME));
        } catch (NumberFormatException e) {
            bodiesNum = null;
        }
        return bodiesNum;
    }

    public Double bodyMass() {
        Double bodyMass;
        try {
            bodyMass = Double.parseDouble(value(BODY_MASS_TAG_NAME));
        } catch (NumberFormatException | NullPointerException e) {
            bodyMass = null;
        }
        return bodyMass;
    }

    public Integer deltaTime() {
        Integer deltaTime;
        try {
            deltaTime = Integer.parseInt(value(DELTA_TIME_TAG_NAME));
        } catch (NumberFormatException e) {
            deltaTime = null;
        }
        return deltaTime;
    }

    public Double errorDistance() {
        Double errorDistance;
        try {
            errorDistance = Double.parseDouble(value(ERROR_DISTANCE_TAG_NAME));
        } catch (NumberFormatException | NullPointerException e) {
            errorDistance = null;
        }
        return errorDistance;
    }

    public Integer durationMillis() {
        Integer durationMillis;
        try {
            durationMillis = Integer.parseInt(value(DURATION_SECONDS_TAG_NAME)) * 1000;
        } catch (NumberFormatException e) {
            durationMillis = null;
        }
        return durationMillis;
    }

    public Integer threadsNum() {
        Integer threadsNum;
        try {
            threadsNum = Integer.parseInt(value(THREADS_NUM_TAG_NAME));
        } catch (NumberFormatException e) {
            threadsNum = null;
        }
        return threadsNum;
    }

    private String value(String tagName) {
        String[] left = settingsXml.split("<" + tagName + ">");
        if (left.length < 2) {
            return null;
        }

        String[] leftAndRight = left[1].split("</" + tagName + ">");
        if (leftAndRight.length < 1) {
            return null;
        }

        return leftAndRight[0];
    }

    public AppSettings parseSettings() {
        final int parsedWidth = (width() == null) ? Frames.DEFAULT_WIDTH : width();
        final int parsedHeight = (height() == null) ? Frames.DEFAULT_HEIGHT : height();
        final int parsedBodiesNum = (bodiesNum() == null) ? NbodySolvers.DEFAULT_BODIES_NUM : bodiesNum();
        final double parsedBodyMass = (bodyMass() == null) ? Bodies.DEFAULT_BODY_MASS : bodyMass();
        final int parsedDeltaTime = (deltaTime() == null) ? NbodySolvers.DEFAULT_DELTA_TIME : deltaTime();
        final double parsedErrorDistance = (errorDistance() == null) ? NbodySolvers.DEFAULT_ERROR_DISTANCE : errorDistance();
        final int parsedDurationMillis = (durationMillis() == null) ? Panels.DEFAULT_DURATION_MILLIS : durationMillis();
        final int parsedThreadsNum = (threadsNum() == null) ? NbodySolvers.DEFAULT_THREADS_NUM : threadsNum();

        return new AppSettings(
                parsedWidth,
                parsedHeight,
                parsedBodiesNum,
                parsedBodyMass,
                parsedDeltaTime,
                parsedErrorDistance,
                parsedDurationMillis,
                parsedThreadsNum
        );
    }
}
