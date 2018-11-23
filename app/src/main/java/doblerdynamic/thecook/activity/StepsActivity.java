package doblerdynamic.thecook.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
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

    @BindView(R.id.btn_next)
    Button mBtNext;
    @BindView(R.id.btn_back)
    Button mBtBack;
    private RecipeViewModel mRecipeViewModel;
    private Recipe mRecipe;
    private List<Step> mSteps;
    private Step mStep;
    private int stepPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);
        ButterKnife.bind(this);


        mRecipeViewModel = ViewModelProviders.of(this).get(RecipeViewModel.class);

        int recipePosition = getIntent().getIntExtra(getString(R.string.recipeIndex), 0);
        mRecipeViewModel.setRecipe(mRecipeViewModel.getOne(this, recipePosition));

        mRecipe = mRecipeViewModel.getRecipe();
        mSteps = mRecipe.getSteps();

        setTitle(mRecipe.getName());
        int stepPosition = getIntent().getIntExtra(getString(R.string.stepIndex), 0);
        mRecipeViewModel.setStepPosition(stepPosition);

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

        Intent detailsIntent = new Intent(this, DetailsActivity.class);
        detailsIntent.putExtra(getString(R.string.recipeIndex), getIntent().getIntExtra(getString(R.string.recipeIndex), 0));
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

    private void setFragments() {

        mStep = mSteps.get(mRecipeViewModel.getStepPosition());
        FragmentManager fragmentManager = getSupportFragmentManager();

        StepsDescriptionFragment stepsDescriptionFragment = new StepsDescriptionFragment();
        StepsVideoFragment stepsVideoFragment = new StepsVideoFragment();

        stepsDescriptionFragment.setRetainInstance(false);
        Bundle bundle = new Bundle();
        bundle.putString(getString(R.string.step_description), mStep.getDescription());
        bundle.putString(getString(R.string.step_videoUrl), mStep.getVideoURL());
        stepsDescriptionFragment.setArguments(bundle);
        stepsVideoFragment.setArguments(bundle);
        fragmentManager.beginTransaction()
                .replace(R.id.f_step_description, stepsDescriptionFragment)
                .replace(R.id.f_step_video, stepsVideoFragment)
                .commit();

    }


}
