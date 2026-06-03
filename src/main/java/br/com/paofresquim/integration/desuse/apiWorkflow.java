package br.com.paofresquim.paofresquim.integration.desuse;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class apiWorkflow {

        public static void main(String[] args) throws Exception {

            URL url = new URL("https://geral-n8n.yzqq8i.easypanel.host/webhook/dante");

            HttpURLConnection conn =
                    (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            conn.setRequestProperty(
                    "Content-Type",
                    "application/json"
            );

            String json =
                    "{\"title\":\"Teste\",\"body\":\"API REST\",\"userId\":1}";

            OutputStream os = conn.getOutputStream();

            os.write(json.getBytes());
            os.flush();
            os.close();

            System.out.println(
                    "Código HTTP: " + conn.getResponseCode()
            );

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream())
            );

            String linha;

            while ((linha = br.readLine()) != null) {
                System.out.println(linha);
            }

            br.close();
            conn.disconnect();
        }
}
