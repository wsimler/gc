package com.zxc.roomkotlin.tylo;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class ParseUrl_Post extends AsyncTask<String, Void, String> {

    String urlString;
    Callback callback;
    String body;
    String mMethod;

    public ParseUrl_Post(Callback callback, String url, String body) {
        this.urlString = url;
        this.callback = callback;
        this.body = body;
//        this.mMethod = method;
    }

    @Override
    protected String doInBackground(String... params) {
        try {

            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);


            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            wr.write(body.getBytes());
            wr.flush();
            wr.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder responseBuffer = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                responseBuffer.append(inputLine);
            }
            in.close();

            return responseBuffer.toString().trim();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String resultString) {
        super.onPostExecute(resultString);
        //  //Logi("Recived Data ",resultString);
        callback.result(resultString);
    }
}