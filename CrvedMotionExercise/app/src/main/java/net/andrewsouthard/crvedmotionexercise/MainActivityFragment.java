package net.andrewsouthard.crvedmotionexercise;

import android.animation.ObjectAnimator;
import android.graphics.Path;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivityFragment extends Fragment {

    View fragmentView;
    Boolean finalPosition = false;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_main, container, false);
        final ImageView imgView = (ImageView) fragmentView.findViewById(R.id.imageView2);
        Button btnRevealHide = (Button)fragmentView.findViewById(R.id.button);
        btnRevealHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cx = fragmentView.getWidth()/2;
                int cy = fragmentView.getHeight()/2;
                Path path = new Path();

                if(!finalPosition){
                    path.moveTo(0, 0);
                    path.quadTo(cx/10, cy, cx, cy);
                    finalPosition = true;
                }else {
                    path.moveTo(cx, cy);
                    path.quadTo(cx, cy/10, 0, 0);
                    finalPosition = false;
                }

                ObjectAnimator anim;
                anim = ObjectAnimator.ofFloat(imgView, View.X, View.Y, path);
                anim.setDuration(3000);
                anim.start();
            }
        });
        return fragmentView;
    }
}
