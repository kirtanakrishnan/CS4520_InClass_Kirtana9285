package com.example.inclass_krishnan9285.InClass05;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.inclass_krishnan9285.R;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/*
Kirtana Krishnan
Assignment #5
 */
public class InClass05 extends AppCompatActivity {

    private EditText searchKeyword;
    private Button goButton, nextButton, previousButton;
    private ImageView imageDisplay;
    private String baseUrlRetrieve;
    private HttpUrl url;
    private OkHttpClient client;
    private String searchKeywordText;
    private String[] imageList;
    private int indexNext = 1;
    private int indexPrevious;
    private ProgressBar progressBarImage;
    private TextView loadingText;
    final static String keyword = "keyword";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_class05);

        setTitle("Image Search");

        searchKeyword = findViewById(R.id.searchKeyword);
        goButton = findViewById(R.id.goButton);
        nextButton = findViewById(R.id.nextButton);
        nextButton.setEnabled(false);
        previousButton = findViewById(R.id.previousButton);
        previousButton.setEnabled(false);
        imageDisplay = findViewById(R.id.imageDisplay);
        progressBarImage = findViewById(R.id.progressBarImage);
        progressBarImage.setVisibility(View.INVISIBLE);
        loadingText = findViewById(R.id.loadingText);
        loadingText.setVisibility(View.INVISIBLE);

        this.client = new OkHttpClient();
        this.baseUrlRetrieve = "http://ec2-54-164-201-39.compute-1.amazonaws.com/apis/images/retrieve";




        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextButton.setEnabled(true);
                previousButton.setEnabled(true);
                progressBarImage.setVisibility(View.VISIBLE);
                String loading = "Loading...";
                loadingText.setText(loading);
                loadingText.setVisibility(View.VISIBLE);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        isInternetAvailable();
                    }
                });

                searchKeywordText = searchKeyword.getText().toString().trim();

                url = HttpUrl.parse(baseUrlRetrieve).newBuilder()
                        .addQueryParameter(keyword, searchKeywordText)
                        .build();

                Request requestGet = new Request.Builder()
                        .url(url)
                        .build();

                client.newCall(requestGet)
                        .enqueue(new Callback() {
                            @Override
                            public void onFailure(@NonNull Call call, @NonNull IOException e) {

                            }

                            @Override
                            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                if (response.isSuccessful()) {

                                    ResponseBody responseBody = response.body();
                                    String body = responseBody.string();
                                    imageList = body.split("\n");
                                    indexPrevious = imageList.length - 1;


                                    if (body == null || body == "") {
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(InClass05.this,
                                                        "No Images Found", Toast.LENGTH_LONG).show();
                                                nextButton.setEnabled(false);
                                                previousButton.setEnabled(false);
                                            }
                                        });

                                    }


                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {

                                            for (int i = 0; i < 1; i++) {
                                                Glide.with(InClass05.this)
                                                        .load(imageList[i])
                                                    //    .placeholder(R.id.progressBarImage)
                                                        .into(imageDisplay);
                                                progressBarImage.setVisibility(View.INVISIBLE);
                                                loadingText.setVisibility(View.INVISIBLE);


                                            }

                                        }
                                    });


                                } else {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            progressBarImage.setVisibility(View.INVISIBLE);
                                            loadingText.setVisibility(View.INVISIBLE);
                                            nextButton.setEnabled(false);
                                            previousButton.setEnabled(false);
                                            Toast.makeText(InClass05.this,
                                                            "Please enter a valid keyword!", Toast.LENGTH_LONG)
                                                    .show();
                                        }
                                    });
                                    throw new IOException("Unexpected code " + response);
                                }
                            }
                        });

            }
        });



       nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressBarImage.setVisibility(View.VISIBLE);
                String next = "Loading next...";
                loadingText.setText(next);
                loadingText.setVisibility(View.VISIBLE);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        isInternetAvailable();
                    }
                });
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        updateNextImage();
                        }

                    });
                }


        });

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBarImage.setVisibility(View.VISIBLE);
                String previous = "Loading previous...";
                loadingText.setText(previous);
                loadingText.setVisibility(View.VISIBLE);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        isInternetAvailable();
                    }
                });

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        updatePreviousImage();
                    }
                });
            }
        });

    }




    private void updateNextImage() {
        if (indexNext == imageList.length) {
            indexNext = 0;
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                    Glide.with(InClass05.this)
                            .load(imageList[indexNext])
                            .into(imageDisplay);
                progressBarImage.setVisibility(View.INVISIBLE);
                loadingText.setVisibility(View.INVISIBLE);
            }

        });
        indexNext++;

    }

    private void updatePreviousImage() {
        if (indexPrevious == -1) {
            indexPrevious = imageList.length - 1;
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Glide.with(InClass05.this)
                        .load(imageList[indexPrevious])
                        .into(imageDisplay);
                progressBarImage.setVisibility(View.INVISIBLE);
                loadingText.setVisibility(View.INVISIBLE);
            }

        });
        indexPrevious--;

    }



    private boolean isInternetAvailable() {
        InetAddress inetAddress = null;
        try {
            Future<InetAddress> future = Executors.newSingleThreadExecutor().submit(new Callable<InetAddress>() {
                @Override
                public InetAddress call() {
                    try {
                        return InetAddress.getByName("google.com");
                    } catch (UnknownHostException e) {
                        return null;
                    }
                }
            });
            inetAddress = future.get(1000, TimeUnit.MILLISECONDS);
            future.cancel(true);
        } catch (ExecutionException | TimeoutException | InterruptedException e) {

        }
        return inetAddress!=null && !inetAddress.equals("");
    }

}


