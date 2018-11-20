package doblerdynamic.thecook.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import doblerdynamic.thecook.R;
import doblerdynamic.thecook.model.Recipe;
import doblerdynamic.thecook.model.Step;
import doblerdynamic.thecook.viewModel.RecipeViewModel;

public class StepsActivity extends AppCompatActivity {

    RecipeViewModel mRecipeViewModel;
    Recipe mRecipe;
    List<Step> mSteps;
    Step mStep;
    int stepPosition;

    @BindView(R.id.btn_next)
    Button mBtNext;
    @BindView(R.id.btn_back)
    Button mBtBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);
        ButterKnife.bind(this);


        mRecipeViewModel = ViewModelProviders.of(this).get(RecipeViewModel.class);

        if (getIntent().hasExtra("recipe")) {
            int recipePosition = getIntent().getIntExtra("recipe", 0);
            mRecipeViewModel.setRecipe(mRecipeViewModel.getOne(this, recipePosition));
        }

        mRecipe = mRecipeViewModel.getRecipe();
        mSteps = mRecipe.getSteps();

        if (getIntent().hasExtra("step")) {
            int stepPosition = getIntent().getIntExtra("step", 1);
            mRecipeViewModel.setStepPosition(stepPosition);
        }

        setFragments(-1);

        final Context mContext = this;
        mBtNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSteps.size() > stepPosition + 1) {
                    stepPosition += 1;
                } else {
                    stepPosition = 0;
                }
                ((StepsActivity) mContext).setFragments(stepPosition);
            }
        });
        mBtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSteps.size() !=0) {
                    stepPosition -= 1;
                } else {
                    stepPosition = mSteps.size()-1;
                }
                ((StepsActivity) mContext).setFragments(stepPosition);
            }
        });

    }

    void setFragments(int head) {

        if (head == -1) {
            mStep = mSteps.get(mRecipeViewModel.getStepPosition());
        } else {
            mStep = mSteps.get(head);
        }

        FragmentManager fragmentManager = getSupportFragmentManager();

        StepsDescriptionFragment stepsDescriptionFragment = new StepsDescriptionFragment();
        StepsVideoFragment stepsVideoFragment = new StepsVideoFragment();

//        stepsDescriptionFragment.setmDescription(mStep.getDescription());
        stepsDescriptionFragment.setRetainInstance(false);
        Bundle bundle = new Bundle();
        bundle.putString("description", mStep.getDescription());
        bundle.putString("videoUrl", mStep.getVideoURL());
        stepsDescriptionFragment.setArguments(bundle);
        stepsVideoFragment.setArguments(bundle);
        fragmentManager.beginTransaction()
                .replace(R.id.f_step_description, stepsDescriptionFragment)
                .replace(R.id.f_step_video, stepsVideoFragment)
                .commit();

    }


}
