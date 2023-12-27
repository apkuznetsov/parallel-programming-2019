package kuznetsov.nbodyproblemgui;

import kuznetsov.nobodyproblem.BodiesParameters;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static kuznetsov.nbodyproblemgui.ParamsParserDefaults.DEFAULT_BODIES_NUM;
import static kuznetsov.nbodyproblemgui.ParamsParserDefaults.DEFAULT_BODY_MASS;
import static kuznetsov.nbodyproblemgui.ParamsParserDefaults.DEFAULT_DELTA_TIME;
import static kuznetsov.nbodyproblemgui.ParamsParserDefaults.DEFAULT_DURATION_MILLIS;
import static kuznetsov.nbodyproblemgui.ParamsParserDefaults.DEFAULT_ERROR_DISTANCE;
import static kuznetsov.nbodyproblemgui.ParamsParserDefaults.DEFAULT_HEIGHT;
import static kuznetsov.nbodyproblemgui.ParamsParserDefaults.DEFAULT_THREADS_NUM;
import static kuznetsov.nbodyproblemgui.ParamsParserDefaults.DEFAULT_WIDTH;
import static kuznetsov.nbodyproblemgui.ParamsParserDefaults.KEY_BODIES_NUM;
import static kuznetsov.nbodyproblemgui.ParamsParserDefaults.KEY_BODY_MASS;
import static kuznetsov.nbodyproblemgui.ParamsParserDefaults.KEY_DELTA_TIME;
import static kuznetsov.nbodyproblemgui.ParamsParserDefaults.KEY_DURATION_SECONDS;
import static kuznetsov.nbodyproblemgui.ParamsParserDefaults.KEY_ERROR_DISTANCE;
import static kuznetsov.nbodyproblemgui.ParamsParserDefaults.KEY_HEIGHT;
import static kuznetsov.nbodyproblemgui.ParamsParserDefaults.KEY_THREADS_NUM;
import static kuznetsov.nbodyproblemgui.ParamsParserDefaults.KEY_WIDTH;

public class ParamsParser {

    public static BodiesParameters parse(String fileName) {
        if (fileName == null) {
            throw new NullPointerException();
        }

        try {
            String str = new String(
                    Files.readAllBytes(Path.of(fileName))
            );

            return new BodiesParameters(
                    n(str), m(str), dt(str), eps(str), width(str), height(str), durationMillis(str), threadsNum(str)
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String parseValue(String str, String key) {
        String[] left = str.split("<" + key + ">");
        if (left.length < 2) {
            return null;
        }

        String[] leftAndRight = left[1].split("</" + key + ">");
        if (leftAndRight.length < 1) {
            return null;
        }

        return leftAndRight[0];
    }

    private static int n(String str) {
        try {
            return Integer.parseInt(parseValue(str, KEY_BODIES_NUM));
        } catch (NumberFormatException ignored) {
        }

        return DEFAULT_BODIES_NUM;
    }

    private static double m(String str) {
        try {
            return Double.parseDouble(parseValue(str, KEY_BODY_MASS));
        } catch (NumberFormatException | NullPointerException ignored) {
        }

        return DEFAULT_BODY_MASS;
    }

    private static int dt(String str) {
        try {
            return Integer.parseInt(parseValue(str, KEY_DELTA_TIME));
        } catch (NumberFormatException ignored) {
        }

        return DEFAULT_DELTA_TIME;
    }

    private static double eps(String str) {
        try {
            return Double.parseDouble(parseValue(str, KEY_ERROR_DISTANCE));
        } catch (NumberFormatException | NullPointerException ignored) {
        }

        return DEFAULT_ERROR_DISTANCE;
    }

    private static int width(String str) {
        try {
            return Integer.parseInt(parseValue(str, KEY_WIDTH));
        } catch (NumberFormatException ignored) {
        }

        return DEFAULT_WIDTH;
    }

    private static int height(String str) {
        try {
            return Integer.parseInt(parseValue(str, KEY_HEIGHT));
        } catch (NumberFormatException ignored) {
        }

        return DEFAULT_HEIGHT;
    }

    private static Integer durationMillis(String str) {
        try {
            return Integer.parseInt(parseValue(str, KEY_DURATION_SECONDS)) * 1000;
        } catch (NumberFormatException ignored) {
        }

        return DEFAULT_DURATION_MILLIS;
    }

    private static int threadsNum(String str) {
        try {
            return Integer.parseInt(parseValue(str, KEY_THREADS_NUM));
        } catch (NumberFormatException ignored) {
        }

        return DEFAULT_THREADS_NUM;
    }

}
