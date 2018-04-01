package com.suraj.androidtraining.Activity;

import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.suraj.androidtraining.Api.ApiClient;
import com.suraj.androidtraining.Api.ApiInterface;
import com.suraj.androidtraining.Model.Data;
import com.suraj.androidtraining.Model.Result;
import com.suraj.androidtraining.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NetoworkDemoActivity extends AppCompatActivity implements View.OnClickListener {

    private ActionBar actionBar;
    private TextView tvResponse;
    private Button btnNetworkTest;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_netowork_demo);

        findViews();
        setActionBar();
    }

    private void findViews() {
        tvResponse = findViewById(R.id.tvResponse);
        btnNetworkTest = findViewById(R.id.btnNetworkTest);
        btnNetworkTest.setOnClickListener(this);
        imageView = findViewById(R.id.imageView);
    }

    private void setActionBar() {
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Network Demo");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnNetworkTest:
                AsyncTaskForNetwork asyncTaskForNetwork = new AsyncTaskForNetwork();
                asyncTaskForNetwork.execute();

                //getDataFromServerByRetrofit();
                break;
            default:
                break;
        }
    }

    private void getDataFromServerByRetrofit() {
        Retrofit retrofit = ApiClient.getClient();
        //creating the api interface
        ApiInterface api = retrofit.create(ApiInterface.class);

        Call<Result> call = api.getDataFromServer();

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Result result = response.body();
                Data data = result.getData();
                int id = data.getId();
                String firstName = data.getFirstName();
                String lastName = data.getLastName();
                String avatar = data.getAvatar();
                /*Picasso.with(NetoworkDemoActivity.this)
                        .load(avatar)
                        .into(imageView);*/
                Toast.makeText(NetoworkDemoActivity.this, "Response:"+
                        "\nid="+id+
                        "\nfirstName="+firstName+
                        "\nlastName="+lastName+
                        "\navtar="+avatar, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Toast.makeText(NetoworkDemoActivity.this, "Error while making http call!:", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class AsyncTaskForNetwork extends AsyncTask<Void,Void,String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... voids) {
            String result = makeGetRequest();
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            /*try {
                JSONObject jsonObject = new JSONObject(result);
                JSONObject data = jsonObject.getJSONObject("data");
                int id = data.getInt("id");
                String firstName = data.getString("first_name");
                String lastName = data.getString("last_name");
                String avatar = data.getString("avatar");
                // load image now
                Picasso.with(NetoworkDemoActivity.this)
                        .load(avatar)
                        .into(imageView);

                result = "Response:"+
                        "\nid="+id+
                        "\nfirstName="+firstName+
                        "\nlastName="+lastName+
                        "\navtar="+avatar;

            } catch (JSONException e) {
                e.printStackTrace();
            }*/


            //Toast.makeText(NetoworkDemoActivity.this, result, Toast.LENGTH_SHORT).show();
            tvResponse.setText(result);
        }
    }

    private String makeGetRequest() {
        String result = "";
        String GET_URL = "https://reqres.in/api/users/2";
        try {
            URL obj = new URL(GET_URL);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");

            //con.setRequestProperty("User-Agent", USER_AGENT);
            int responseCode = con.getResponseCode();
            System.out.println("GET Result Code :: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // print result
                result = response.toString();
                System.out.println(result);
            } else {
                System.out.println("GET request not worked");
            }
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
