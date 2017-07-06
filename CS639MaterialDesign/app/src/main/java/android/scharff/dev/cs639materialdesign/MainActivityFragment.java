package android.scharff.dev.cs639materialdesign;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

/**
 * A placeholder fragment containing a simple view.
 * References: https://developer.android.com/training/material/animations.html
 */
public class MainActivityFragment extends Fragment {

    public static final String MAIN_ACTIVITY_FRAGMENT = "MainActivityFragment";

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_main, container, false);
        final ImageView imgView = (ImageView) v.findViewById(R.id.imageView);

        // Need a button as the view is not inflated yet (no return)
        Button btnRevealHide = (Button)v.findViewById(R.id.button);

        btnRevealHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imgView.getVisibility() == View.INVISIBLE) {
                    // Going from invisible to visible
                    Log.i(MAIN_ACTIVITY_FRAGMENT, "Invisible to Visible");
                    // Center of the circle
                    int cx = imgView.getWidth() / 2;
                    int cy = imgView.getHeight() / 2;
                    float finalRadius = (float) Math.hypot(cx, cy);
                    // Going from 0 to the radius to make the image appear
                    Animator anim = ViewAnimationUtils.createCircularReveal(imgView, cx, cy, 0, finalRadius);
                    imgView.setVisibility(View.VISIBLE); // need to make the image visible before starting the animation
                    anim.start();
                } else {
                    // Going from visible to invisible
                    Log.i(MAIN_ACTIVITY_FRAGMENT, "Visible to Invisible");
                    // Get the center of the circle
                    int cx = imgView.getWidth() / 2;
                    int cy = imgView.getHeight() / 2;
                    float initialRadius = (float) Math.hypot(cx, cy);
                    // Going from the radius to 0 to make the image disappear
                    Animator anim = ViewAnimationUtils.createCircularReveal(
                            imgView, cx, cy, initialRadius, 0);
                    // listener to make the image invisible only when the animation is done
                    anim.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            imgView.setVisibility(View.INVISIBLE);
                        }
                    });
                    anim.start();
                }
            }
        });
        return v;
    }
}
