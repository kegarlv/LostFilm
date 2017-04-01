package com.kegarlv.lostfilm.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kegarlv.lostfilm.Model.Movie;
import com.kegarlv.lostfilm.R;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class AboutMovieActivity extends AppCompatActivity {
    private static final String TAG = "myTag";
    private ImageView imageView;
    private TextView textView;
    private FirebaseDatabase mFirebase;
    private Movie movie;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_movie);
        Intent intent = getIntent();

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String imageLink = "https:" + intent.getStringExtra("ImageLink");
        String name = intent.getStringExtra("Name");
        int begin = -1;
        int end = -1;
        for(int i=0; i< name.length(); i++) {
            if(name.charAt(i) == '(')
                begin = i;
            if(name.charAt(i) == ')') {
                end = i;
                break;
            }
        }


        name = name.substring(begin+1, end);
        getSupportActionBar().setTitle(name);

        mFirebase = FirebaseDatabase.getInstance();
        DatabaseReference mRef = mFirebase.getReference();

        mRef.child("Series").orderByKey().equalTo(name).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                 movie = dataSnapshot.getChildren().iterator().next().getValue(Movie.class);
                (new GetDescription()).execute(movie.getLink());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        imageView = (ImageView)findViewById(R.id.app_bar_image);
        textView = (TextView)findViewById(R.id.textViewAboutMovie);

        progressDialog = ProgressDialog.show(this,"Retrieving data", "Please wait",true);
        textView.setVisibility(View.GONE);


        Picasso.with(imageView.getContext())
                .load(imageLink)
                .into(imageView);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    class GetDescription extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            try {
                Document doc = Jsoup.connect(params[0]).get();
                Element body = doc.select("div.body[oncontextmenu]").get(0);
                //// TODO: 12.02.17  remove <a>

                return  body.html();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return "";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            textView.setText(Html.fromHtml(s));
            textView.setVisibility(View.VISIBLE);
            progressDialog.dismiss();
        }
    }
}
