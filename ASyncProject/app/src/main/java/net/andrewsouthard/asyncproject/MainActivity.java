package net.andrewsouthard.asyncproject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    ImageView img;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        img = (ImageView) findViewById(R.id.imageView2);
        new asyncImage().execute();
    }


    public class asyncImage extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                URL url = new URL("pics2.city-data.com/city/maps/fr625.png");
                HttpURLConnection con = (HttpURLConnection)url.openConnection();
                if(con.getResponseCode() != 200){
                    throw new Exception("Failed to connect");
                }
                InputStream is = con.getInputStream();
                is.close();
                Bitmap bitmap = BitmapFactory.decodeStream(is);
            } catch (Exception e){
                Log.e("Image", "Failed to Load Image", e);
                Log.e("Error", e.getMessage());
            }

            return null;
        }

        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            img.setImageBitmap(bitmap);
        }
    }


}