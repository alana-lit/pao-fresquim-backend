package br.com.paofresquim.paofresquim.integration.desuse;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class workflow {
    public static void main(String[] args) throws Exception {
        URL url = new URL("https://geral-n8n.yzqq8i.easypanel.host/webhook/dante");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/json");

        String json = "{\"nome\":\"Allysson\"}";

        OutputStream os = conn.getOutputStream();
        os.write(json.getBytes());
        os.flush();

        System.out.println(conn.getResponseCode());
    }

}
