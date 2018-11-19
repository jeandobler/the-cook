package doblerdynamic.thecook.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);

        mRecipeViewModel = ViewModelProviders.of(this).get(RecipeViewModel.class);

        if (getIntent().hasExtra("recipe")) {
            mRecipe = getIntent().getParcelableExtra("recipe");
            mRecipeViewModel.setRecipe(mRecipe);
        }

        mRecipe = mRecipeViewModel.getRecipe();
        mSteps = mRecipe.getSteps();

        if (getIntent().hasExtra("step")) {
            int position = getIntent().getIntExtra("step", 1);
            mRecipeViewModel.setStepPosition(position);
        }

        mStep = mSteps.get(mRecipeViewModel.getStepPosition());


        FragmentManager fragmentManager = getSupportFragmentManager();

        StepsDescriptionFragment stepsDescriptionFragment = new StepsDescriptionFragment();
        StepsVideoFragment stepsVideoFragment = new StepsVideoFragment();

        fragmentManager.beginTransaction().add(R.id.f_step_description, stepsDescriptionFragment)
                .commit();

        fragmentManager.beginTransaction().add(R.id.f_step_video, stepsVideoFragment)
                .commit();


    }
}
