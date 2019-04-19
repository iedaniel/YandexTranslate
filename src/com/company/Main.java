package com.company;

import javax.net.ssl.HttpsURLConnection;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String translate;
        while ((translate = sc.next()) != null) {
            String urlStr = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=trnsl.1.1.20190415T160049Z.5d2227f639755b4b.1d1b8e9ea22721740dc690b3ebefac4bf871810f";
            URL url = new URL(urlStr);
            HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
            dataOutputStream.writeBytes("text=" + URLEncoder.encode(translate, "UTF-8") + "&lang=ru");

            InputStream response = connection.getInputStream();
            String json = new java.util.Scanner(response).nextLine();
            int start = json.indexOf("[");
            int end = json.indexOf("]");
            String translated = json.substring(start + 2, end - 1);
            System.out.println(translated);
            connection.disconnect();
        }
    }
}
