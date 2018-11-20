package doblerdynamic.thecook.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;

import doblerdynamic.thecook.R;
import doblerdynamic.thecook.viewModel.RecipeViewModel;

public class StepsDescriptionFragment extends Fragment {

    RecipeViewModel mRecipeViewModel;
    private TextView mTvDescription;
    private String mDescription;


    public StepsDescriptionFragment() {
    }

    public void setmDescription(String mDescription) {
        try {
            byte[] data = mDescription.getBytes();
            String text = new String(data, "UTF-8");
            this.mDescription = new String(mDescription.getBytes("ISO-8859-1"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View rootView = getView() != null ? getView() :
                inflater.inflate(R.layout.fragment_steps_description, container, false);
        mRecipeViewModel = ViewModelProviders.of(this).get(RecipeViewModel.class);
        mTvDescription = rootView.findViewById(R.id.tv_fragment_step_description_text);
        if (savedInstanceState == null) {
            Bundle extras = getArguments();
            this.setmDescription(extras.getString("description"));
        }


        return rootView;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mTvDescription.setText(mDescription);
    }


}
