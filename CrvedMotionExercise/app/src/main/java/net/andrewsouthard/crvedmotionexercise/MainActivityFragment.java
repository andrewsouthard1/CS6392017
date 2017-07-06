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
                float x = fragmentView.getWidth()-imgView.getBottom();
                float y = fragmentView.getHeight()-imgView.getBottom();
                Path path = new Path();

                if(!finalPosition){
                    path.moveTo(0, 0);
                    path.quadTo(x/10, y, x, y);
                    finalPosition = true;
                }else {
                    path.moveTo(x, y);
                    path.quadTo(x, y/10, 0, 0);
                    finalPosition = false;
                }

                ObjectAnimator mAnimator;
                mAnimator = ObjectAnimator.ofFloat(imgView, View.X, View.Y, path);
                mAnimator.setDuration(3000);
                mAnimator.start();
            }
        });
        return fragmentView;
    }
}
