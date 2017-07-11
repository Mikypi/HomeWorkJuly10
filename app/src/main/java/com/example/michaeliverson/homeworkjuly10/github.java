package com.example.michaeliverson.homeworkjuly10;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class github extends AppCompatActivity {

    // VARIABLES
    public static final String BASE_URL = "http://api.github.com";
    private static final String TAG = "First";
    private static Retrofit retrofit = null;
    private volatile ArrayList<GithubRepo> owners;
    private volatile Bitmap map;

    // UI
    private EditText etGit;
    private ImageView ivView;
    private TextView tvName;
    private TextView tvfulName;
    private TextView tvURL;
    private TextView tvGITURL;
    private MyReciever myRecieve = new MyReciever();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_github);

        this.etGit = (EditText) findViewById(R.id.etGitHub);
        this.ivView = (ImageView) findViewById(R.id.ivView);
        this.tvName = (TextView) findViewById(R.id.tvName);
        this.tvfulName = (TextView) findViewById(R.id.tvFullName);
        this.tvURL = (TextView) findViewById(R.id.tvURL);
        this.tvGITURL = (TextView) findViewById(R.id.tvGitURL);
    }

    public void onGit(View view) {

        if (this.etGit.getText().toString() != "") {
            String name = this.etGit.getText().toString();
            try {
                if (retrofit == null) {

                    retrofit = new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    GitHubService gitService = retrofit.create(GitHubService.class);
                    retrofit2.Call<List<GithubRepo>> callService = gitService.callProfle(name);
                    callService.enqueue(new Callback<List<GithubRepo>>() {
                        @Override
                        public void onResponse(Call<List<GithubRepo>> call, Response<List<GithubRepo>> response) {
                            owners = new ArrayList<GithubRepo>();
                            for (int i = 0; i <= response.body().size() - 1; i++) {
                                owners.add(response.body().get(i));
                            }
                            display(owners.get(0));
                        }

                        @Override
                        public void onFailure(Call<List<GithubRepo>> call, Throwable t) {
                            Log.d(TAG, "Broke Code");
                        }
                    });
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } else
            Toast.makeText(this, "Please Enter A Name", Toast.LENGTH_LONG).show();
    }


    private void display(GithubRepo OWNER) {
        try {
            final URL newurl = new URL(OWNER.getOwner().getAvatarUrl());
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        map = BitmapFactory.decodeStream(newurl.openConnection().getInputStream());

                        Intent intent=new Intent("PICTURE");
                        sendBroadcast(intent);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            this.tvName.setText(OWNER.getFullName());
            this.tvfulName.setText(OWNER.getFullName());
            this.tvGITURL.setText(OWNER.getGitUrl());
            this.tvURL.setText(OWNER.getUrl());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void onNext(View view)
    {
        DataWrapper wrap = new DataWrapper(this.owners);
        Intent intent = new Intent(github.this,ListOfRepositories.class);
        intent.putExtra("KEYDATARRAY",wrap);
        startActivity(intent);
    }

    public class MyReciever extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            ivView.setImageBitmap(map);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("PICTURE");
        registerReceiver(myRecieve, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(myRecieve);
    }
}