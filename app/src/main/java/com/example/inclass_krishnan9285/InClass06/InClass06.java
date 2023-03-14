package com.example.inclass_krishnan9285.InClass06;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inclass_krishnan9285.R;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class InClass06 extends AppCompatActivity {

    private OkHttpClient client;

    private HttpUrl url;
    private String baseUrl;
    private ListView listView;
    private TextView instructionsText;
    private Spinner countries, categories;
    private TextView countrySelect, categorySelect;
    private String countryText, categoryText;
    private Button getNewsButton;
    private ArrayAdapter<String> adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_class06);

        setTitle("Top Headlines Search");

        instructionsText = findViewById(R.id.instructionsText);
        countries = findViewById(R.id.countries);
        categories = findViewById(R.id.categories);
        countrySelect = findViewById(R.id.countrySelect);
        categorySelect = findViewById(R.id.categorySelect);
        getNewsButton = findViewById(R.id.getNewsButton);
        listView = findViewById(R.id.listView);

        this.client = new OkHttpClient();
        this.baseUrl = "https://newsapi.org/v2/top-headlines";

        getNewsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        getArticle();
                    }
                });

            }
        });

    }

    private void getArticle(){


        countryText = countries.getSelectedItem().toString();
        categoryText = categories.getSelectedItem().toString();

        if (categoryText.equals("null") && countryText.equals("null")) {
            Toast.makeText(getApplicationContext(), "Please enter a country, category, or both!",
                    Toast.LENGTH_LONG).show();
        } else {
            if (countryText.equals("null") && (!(categoryText.equals("null")))) {
                url = HttpUrl.parse(baseUrl).newBuilder()
                        .addQueryParameter("apiKey", "e099428020c245eb8a21d06878d69bce")
                        .addQueryParameter("category", categoryText)
                        .build();
            } else if (categoryText.equals("null") && (!(countryText.equals("null")))) {
                url = HttpUrl.parse(baseUrl).newBuilder()
                        .addQueryParameter("apiKey", "e099428020c245eb8a21d06878d69bce")
                        .addQueryParameter("country", countryText)
                        .build();
            } else if (!((countryText.equals("null")) && (categoryText.equals("null")))) {
                url = HttpUrl.parse(baseUrl).newBuilder()
                        .addQueryParameter("apiKey", "e099428020c245eb8a21d06878d69bce")
                        .addQueryParameter("country", countryText)
                        .addQueryParameter("category", categoryText)
                        .build();

            }

            Request request = new Request.Builder()
                    .url(url)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Please enter a country, category, or both!",
                            Toast.LENGTH_LONG).show();
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    if(response.isSuccessful()){
                        Gson gsonData = new Gson();
                        Articles articles =  gsonData.fromJson(response.body().charStream(), Articles.class);
                        ArrayList<String> articlesList = articles.getTitles();


                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                adapter =
                                        new ArrayAdapter<>(getApplicationContext(),
                                                android.R.layout.simple_list_item_1,
                                                android.R.id.text1, articlesList);
                                listView.setAdapter(adapter);

                            }
                        });



                    }else{
                        Toast.makeText(getApplicationContext(), "Please enter a country, category, or both!",
                                Toast.LENGTH_LONG).show();
                    }
                }
            });


        }


    }


}