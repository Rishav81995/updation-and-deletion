package com.example.pc26.myproject;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by pc26 on 2/15/2020.
 */

public class FetchData extends AsyncTask<Void, Void, Void> {
    HttpURLConnection connection;
    URL url;
    String data = "";
    String dataParsed = "";
    String singleParsed = "";
    Users users;
    BufferedReader bufferedReader = null;
    // public FetchData(Users users) {
    //     this.users = users;
    // }

    @Override
    protected Void doInBackground(Void... voids) {
/*
        Gson gson = new Gson();

        try {
            return  postJsonToServer("http://dummy.restapiexample.com/api/v1/create", gson.toJson(users).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
*/
        try {
            url = new URL("http://dummy.restapiexample.com/api/v1/employees");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            connection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            InputStream inputStream = connection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String line = "";
            while (line != null) {
                try {
                    line = bufferedReader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                data = data + line;
            }

           /* try {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(data);
                } catch (JSONException e) {
                    e.printStackTrace();
                }*/

               // JSONArray jn = jsonObject.getJSONArray("data");
        JSONArray jn = null;
        try {
            jn = new JSONArray(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < jn.length(); i++) {
            JSONObject jo = null;
            try {
                jo = jn.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                singleParsed = "id:" + jo.get("id") + "\n" +
                                "employee_name:" + jo.get("employee_name") + "\n" +
                                "employee_salary:" + jo.get("employee_salary") + "\n" +
                                "employee_age:" + jo.get("employee_age") + "\n" +
                                "profile_image:" + jo.get("profile_image") + "\n";
            } catch (JSONException e) {
                e.printStackTrace();
            }
            dataParsed = dataParsed + singleParsed;
                    Log.e("dd", "hello" + dataParsed);
                }



            Log.e("jj", "message" + data);
      /*  } catch (IOException e) {

            e.printStackTrace();

        }
*/
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
      /* Gson gson1 = new GsonBuilder()
                .setLenient()
                .create();*/
        //
        // Gson gson =new Gson();
       /* GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setLenient();
        Gson gson = gsonBuilder.create();*/

// and in you adapter set this instance
        //  GsonConverterFactory.create(gson);
        //try {
         //   Data data = gson.fromJson(data, Data.class);
            Log.e("jj", "hello" + data);
            ApiActivity.data.setText(this.dataParsed);


       // } catch (Exception e) {
        //    Log.e("jj", "Exception" + e.getMessage());

      //  }
    }

}

 /*   public String postJsonToServer(String StringUrl, String data) throws Exception {
        Log.e("kkk", " postJsonToServer " + data);
        HttpURLConnection connection;
        URL url = new URL(StringUrl);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
       *//* if (!TextUtils.isEmpty(MobiComUserPreference.getInstance(context).getDeviceKeyString())) {
            connection.setRequestProperty(DEVICE_KEY_HEADER, MobiComUserPreference.getInstance(context).getDeviceKeyString());
        }*//*
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.connect();

        byte[] dataBytes = data.getBytes("UTF-8");
        DataOutputStream os = new DataOutputStream(connection.getOutputStream());
        os.write(dataBytes);
        os.flush();
        os.close();
        BufferedReader br = null;
        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            InputStream inputStream = connection.getInputStream();
            br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        } else {
            Log.i("uuu", "Response code for post json is :" + connection.getResponseCode());
        }
        StringBuilder sb = new StringBuilder();
        try {
            String line;
            if (br != null) {
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.e("yyy", "Response: postJsonToServer " + sb.toString());
        return sb.toString();
    }

}
*/