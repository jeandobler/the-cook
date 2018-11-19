package doblerdynamic.thecook.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import doblerdynamic.thecook.R;
import doblerdynamic.thecook.adapter.RecipeAdapter;
import doblerdynamic.thecook.model.Recipe;
import doblerdynamic.thecook.viewModel.RecipeViewModel;

public class MainActivity extends AppCompatActivity implements RecipeAdapter.RecipeAdapterOnClickHandler {


    @BindView(R.id.rc_main_recipe)
    RecyclerView mRvRecipe;

    RecipeViewModel recipeViewModel;
    RecipeAdapter mRecipeAdapter;
    List<Recipe> mRecipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        this.setupRecycleView(savedInstanceState);

        recipeViewModel = ViewModelProviders.of(this).get(RecipeViewModel.class);

        mRecipes = recipeViewModel.loadJSONFromAsset(this);
        mRecipeAdapter.setRecipeData(mRecipes);

    }

    protected void setupRecycleView(Bundle savedInstanceState) {
        mRvRecipe.setHasFixedSize(true);
        mRvRecipe.setLayoutManager(new LinearLayoutManager(this));
        mRecipeAdapter = new RecipeAdapter(this);
        mRvRecipe.setAdapter(mRecipeAdapter);
    }

    @Override
    public void onClick(int recipePosition) {

        final Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("headIndex", recipePosition);
        startActivity(intent);

    }
}
