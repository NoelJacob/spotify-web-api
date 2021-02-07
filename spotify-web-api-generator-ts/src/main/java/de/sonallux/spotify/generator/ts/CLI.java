package de.sonallux.spotify.generator.ts;

import de.sonallux.spotify.core.SpotifyWebApiUtils;
import de.sonallux.spotify.core.model.SpotifyWebApi;
import picocli.CommandLine;
import picocli.CommandLine.Option;

import java.io.IOException;
import java.nio.file.Path;

public class CLI implements Runnable {

    @Option(names = {"-f", "--file"}, required = true, description = "The web API documentation file to a generate a Java wrapper for")
    Path apiDocumentationFile;

    @Option(names = {"-o", "--output"}, required = true, description = "The file the TypeScript model definitions should be written to")
    Path outputFile;

    @Option(names = { "-h", "--help" }, usageHelp = true, description = "display a help message")
    boolean helpRequested = false;

    @Override
    public void run() {
        var spotifyWebApi = readSpotifyWebApi();

        try {
            new TSGenerator().generate(spotifyWebApi, outputFile);
        } catch (IOException e) {
            System.err.println("Failed to write generated files: " + e.getMessage());
            System.exit(1);
        }
    }

    private SpotifyWebApi readSpotifyWebApi() {
        try {
            return SpotifyWebApiUtils.load(apiDocumentationFile);
        } catch (IOException e) {
            System.err.println("Failed to read web API documentation file: " + e.getMessage());
            System.exit(1);
            return null;
        }
    }

    public static void main(String[] args) {
        var exitCode = new CommandLine(new CLI()).execute(args);
        System.exit(exitCode);
    }
}
