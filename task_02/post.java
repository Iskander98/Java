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
    }

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
