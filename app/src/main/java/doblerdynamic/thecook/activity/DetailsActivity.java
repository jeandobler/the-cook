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
    public int headIndex = -1;
    @BindView(R.id.rv_details_steps)
    RecyclerView mRvSteps;
    StepsAdapter mStepsAdapter;

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        if (getIntent().getIntExtra("headIndex", -1) != -1) {
            savedInstanceState.putInt("headIndex", getIntent().getIntExtra("headIndex", -1));
            headIndex = getIntent().getIntExtra("headIndex", -1);

        }
        Log.e("Save", String.valueOf(headIndex));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);


        if (savedInstanceState != null) {
            Log.e("Restore", String.valueOf(savedInstanceState.getInt("headIndex")));
            if (getIntent().getIntExtra("headIndex", -1) == -1) {
                headIndex = savedInstanceState.getInt("headIndex");
            } else {
                headIndex = getIntent().getIntExtra("headIndex", -1);
            }
        }
        recipeViewModel = ViewModelProviders.of(this).get(RecipeViewModel.class);

        IngredientsFragment ingredientsFragment = new IngredientsFragment();

        if (headIndex == -1) {
            headIndex = getIntent().getIntExtra("headIndex", -1);
            Log.e("INdexDetail", String.valueOf(headIndex));
        }
        setupRecycleView(savedInstanceState);

        ingredientsFragment.setIngredientsList(recipeViewModel.getOne(this, headIndex).getIngredients());
        mStepsAdapter.setStepData(recipeViewModel.getOne(this, headIndex).getSteps());

        setTitle(recipeViewModel.getOne(this).getName());

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
        intent.putExtra("step", stepPosition);
        intent.putExtra("recipe", recipeViewModel.getOne(this).getId() - 1);
        startActivity(intent);
    }
}
