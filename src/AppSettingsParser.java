import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class AppSettingsParser {

    private static final String DURATION_SECONDS_TAG_NAME = "durationSeconds";

    private final String settingsXml;

    public AppSettingsParser() throws IOException {
        settingsXml = new String(Files.readAllBytes(Path.of("settings.xml")));
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
}
