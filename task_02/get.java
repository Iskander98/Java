package task_02;

import java.io.*;
import java.net.*;

class temp {
    public static void main(String[] args) {
        System.out.println(name());
    }

    public static String name() {
        try {
            final URL url = new URL("http://localhost:9200/books_index/_search");
            final HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");
            con.setConnectTimeout(5000);
            con.setReadTimeout(10000);
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
}
