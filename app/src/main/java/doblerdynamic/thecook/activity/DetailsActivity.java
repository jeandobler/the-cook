package doblerdynamic.thecook.activity;

import android.appwidget.AppWidgetManager;
import android.arch.lifecycle.ViewModelProviders;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import doblerdynamic.thecook.R;
import doblerdynamic.thecook.adapter.StepsAdapter;
import doblerdynamic.thecook.model.Recipe;
import doblerdynamic.thecook.viewModel.RecipeViewModel;
import doblerdynamic.thecook.widget.RecipeWidgetProvider;

public class DetailsActivity extends AppCompatActivity implements StepsAdapter.StepAdapterOnClickHandler {


    @BindView(R.id.rv_details_steps)
    RecyclerView mRvSteps;
    StepsAdapter mStepsAdapter;
    private RecipeViewModel recipeViewModel;
    private List<Recipe> mRecipes;
    private int headIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        recipeViewModel = ViewModelProviders.of(this).get(RecipeViewModel.class);

        IngredientsFragment ingredientsFragment = new IngredientsFragment();

        if (headIndex == -1) {
            headIndex = getIntent().getIntExtra(getString(R.string.recipeIndex), -1);
        }


        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, RecipeWidgetProvider.class));
//        RecipeWidgetProvider.onUpdateRecipe(this, headIndex);
        RecipeWidgetProvider.onUpdateRecipe(this, appWidgetManager, appWidgetIds, headIndex, recipeViewModel.getOne(this, headIndex).getIngredients());

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
        intent.putExtra(getString(R.string.stepIndex), stepPosition);
        intent.putExtra(getString(R.string.recipeIndex), recipeViewModel.getOne(this).getId() - 1);
        startActivity(intent);
    }
}
