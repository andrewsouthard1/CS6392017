package net.andrewsouthard.imageviewer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Andrew Southard Images - Dogs");
        setContentView(R.layout.activity_main);
    }
}
