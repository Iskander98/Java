package task_02;

import java.io.*;
import java.net.*;
import java.util.*;

class post {
    public static void main(String[] args) {
        System.out.println(post());
    }

    public static String post() {
        try {
            final URL url = new URL("http://jsonplaceholder.typicode.com/posts?_limit=10");
            final HttpURLConnection con = (HttpURLConnection) url.openConnection();
            final Map<String, String> parameters = new HashMap<>();
            parameters.put("title", "foo");
            parameters.put("body", "okey");
            parameters.put("userId", "1");

            con.setRequestMethod("PUT");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);
            final DataOutputStream out = new DataOutputStream(con.getOutputStream());
            out.writeBytes(getParamsString(parameters));
            out.flush();
            out.close();
            final BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            final StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            return content.toString();

        } catch (final Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }Scanner

    public static String getParamsString(final Map<String, String> params) {
        final StringBuilder result = new StringBuilder();

        params.forEach((name, value) -> {
            try {
                result.append(URLEncoder.encode(name, "UTF-8"));
                result.append('=');
                result.append(URLEncoder.encode(value, "UTF-8"));
                result.append('&');
            } catch (final UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        });

        final String resultString = result.toString();
        return !resultString.isEmpty()
                ? resultString.substring(0, resultString.length() - 1)
                : resultString;
    }

}

private static void sendRequest(String text) throws IOException {

        final URL url = new URL("http://localhost:9200/task2/_doc/" + id);
        id++;
        final HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setDoOutput(true);
        urlConnection.setRequestMethod("PUT");
        urlConnection.setRequestProperty("Content-Type", "application/json");

        // Writing the post data to the HTTP request body
        BufferedWriter httpRequestBodyWriter = new BufferedWriter(
                new OutputStreamWriter(urlConnection.getOutputStream()));
        httpRequestBodyWriter.write("{ \"text': \": \"" + text + "\" }");
        httpRequestBodyWriter.close();

        // Reading from the HTTP response body
        Scanner httpResponseScanner = new Scanner(urlConnection.getInputStream());
        while (httpResponseScanner.hasNextLine()) {
            System.out.printf(httpResponseScanner.nextLine());
        }
        httpResponseScanner.close();
    }