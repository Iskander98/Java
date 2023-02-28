package task_02;

import java.io.*;
import java.net.*;

class post {
    private static int id = 1;

    public static void main(String[] args) {
        String command = "", text = "";
        boolean isE = false, isS = false;
        for (String s : args) {
            if (s.equals("-e")) {
                isE = true;
            } else if (s.equals("-s")) {
                isS = true;
            } else {
                if (isE) {
                    command = s;
                    isE = false;
                } else if (isS) {
                    text = s;
                    isS = false;
                }
            }
        }
        System.out.println(text);
        if (command.equals("add")) {
            System.out.println(post1(text));
        } else if (command.equals("search")) {
            System.out.println(name(text));
        } else {
            System.out.println("Произошла ошибка, проверьте правильность введения запроса");
        }
    }

    public static String post1(String text) {
        try {
            final URL url = new URL("http://localhost:9200/task2/_doc/" + id);
            id++;
            final HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("PUT");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);
            con.setConnectTimeout(5000);
            con.setReadTimeout(10000);

            BufferedWriter zapros = new BufferedWriter(new OutputStreamWriter(con.getOutputStream()));

            zapros.write("{ \"text : \": \"" + text + "\"}");
            zapros.close();

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

    public static String name(String text) {
        try {
            final URL url = new URL("http://localhost:9200/_search/");
            final HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty(text, "application/json");
            con.setConnectTimeout(5000);
            con.setReadTimeout(10000);

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
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
