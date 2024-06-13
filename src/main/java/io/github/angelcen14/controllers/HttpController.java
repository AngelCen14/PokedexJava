package io.github.angelcen14.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class HttpController {

    private static final String GET = "GET";

    public static String get(String urlString) {
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URI(urlString).toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(GET);

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                result.append(line);
            }
            bufferedReader.close();
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }

        return result.toString();
    }
}
