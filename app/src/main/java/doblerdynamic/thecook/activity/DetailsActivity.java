package doblerdynamic.thecook.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import doblerdynamic.thecook.R;
import doblerdynamic.thecook.adapter.StepsAdapter;
import doblerdynamic.thecook.model.Recipe;
import doblerdynamic.thecook.viewModel.RecipeViewModel;

public class DetailsActivity extends AppCompatActivity implements StepsAdapter.StepAdapterOnClickHandler {


    public RecipeViewModel recipeViewModel;
    public List<Recipe> mRecipes;

    @BindView(R.id.rv_details_steps)
    RecyclerView mRvSteps;

    StepsAdapter mStepsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        int headIndex = getIntent().getIntExtra("headIndex", -1);
        recipeViewModel = ViewModelProviders.of(this).get(RecipeViewModel.class);

        IngredientsFragment ingredientsFragment = new IngredientsFragment();

        setupRecycleView(savedInstanceState);

        if (headIndex == -1) {
            ingredientsFragment.setIngredientsList(recipeViewModel.getOne(this).getIngredients());
            mStepsAdapter.setStepData(recipeViewModel.getOne(this).getSteps());
        } else {
            ingredientsFragment.setIngredientsList(recipeViewModel.getOne(this, headIndex).getIngredients());
            mStepsAdapter.setStepData(recipeViewModel.getOne(this, headIndex).getSteps());
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.ingredients_fragment, ingredientsFragment)
                .commit();

    }

    protected void setupRecycleView(Bundle savedInstanceState) {
        mRvSteps.setHasFixedSize(true);
        mRvSteps.setLayoutManager(new LinearLayoutManager(this));
        mStepsAdapter = new StepsAdapter(this);
        mRvSteps.setAdapter(mStepsAdapter);
    }

    @Override
    public void onClick(int stepPosition) {
        Intent intent = new Intent(this, StepsActivity.class);
        intent.putExtra("step", stepPosition -1);
        intent.putExtra("recipe", recipeViewModel.getOne(this).getId());
        startActivity(intent);
    }
}
