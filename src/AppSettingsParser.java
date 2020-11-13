import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class AppSettingsParser {

    private final String settingsXml;

    public AppSettingsParser() throws IOException {
        settingsXml = new String(Files.readAllBytes(Path.of("settings.xml")));
    }

    public String value(String tagName) {
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
