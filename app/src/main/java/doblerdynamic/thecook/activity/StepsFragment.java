package doblerdynamic.thecook.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import doblerdynamic.thecook.R;

public class StepsFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        // Inflate the Android-Me fragment layout
        View rootView = inflater.inflate(R.layout.fragment_steps, container, false);

        // Get a reference to the ImageView in the fragment layout
//        final ImageView imageView = (ImageView) rootView.findViewById(R.id.body_part_image_view);


        // Return the rootView
        return rootView;
    }


}
