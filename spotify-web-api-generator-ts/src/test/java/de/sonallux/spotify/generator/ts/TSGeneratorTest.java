package de.sonallux.spotify.generator.ts;

import java.nio.file.Path;

public class TSGeneratorTest {
    private static final Path INPUT_FILE_PATH = Path.of("spotify-web-api-core/src/main/resources/spotify-web-api.yml");
    private static final Path OUTPUT_PATH = Path.of("spotify-web-api-generator-ts", "models.d.ts");

    public static void main(String[] args) {
        var cli = new CLI();
        cli.apiDocumentationFile = INPUT_FILE_PATH;
        cli.outputFile = OUTPUT_PATH;
        cli.run();
    }
}
