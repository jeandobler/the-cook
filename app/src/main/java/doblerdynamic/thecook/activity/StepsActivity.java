package doblerdynamic.thecook.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
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

        setTitle(mRecipe.getName());
        if (getIntent().hasExtra("step")) {
            int stepPosition = getIntent().getIntExtra("step", 1);
            mRecipeViewModel.setStepPosition(stepPosition);
        }

        setFragments();

        final Context mContext = this;
        mBtNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSteps.size() > mRecipeViewModel.getStepPosition() + 1) {
                    mRecipeViewModel.setStepPosition(mRecipeViewModel.getStepPosition() + 1);
                } else {
                    mRecipeViewModel.setStepPosition(0);
                }
                ((StepsActivity) mContext).setFragments();
            }
        });
        mBtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRecipeViewModel.getStepPosition() != 0) {
                    mRecipeViewModel.setStepPosition(mRecipeViewModel.getStepPosition() - 1);
                } else {
                    mRecipeViewModel.setStepPosition(mSteps.size() - 1);
                }
                ((StepsActivity) mContext).setFragments();
            }
        });


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.backAction();
    }

    private void backAction() {
        Log.e("RecipeIndex", String.valueOf(getIntent().getIntExtra("recipe", -1)));

        Intent detailsIntent = new Intent(this, DetailsActivity.class);
        detailsIntent.putExtra("headIndex", getIntent().getIntExtra("recipe", -1));
        startActivity(detailsIntent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.backAction();
                break;
        }
        return true;
    }

    void setFragments() {

//        if (head == -1) {
        Log.i("STEPPOSITIONagment", String.valueOf(mRecipeViewModel.getStepPosition()));
        mStep = mSteps.get(mRecipeViewModel.getStepPosition());
//        } else {
//            mStep = mSteps.get(head);
//        }

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
