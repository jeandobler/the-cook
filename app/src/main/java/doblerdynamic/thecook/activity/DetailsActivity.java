package doblerdynamic.thecook.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import doblerdynamic.thecook.R;
import doblerdynamic.thecook.model.Recipe;
import doblerdynamic.thecook.viewModel.RecipeViewModel;

public class DetailsActivity extends AppCompatActivity {


    public RecipeViewModel recipeViewModel;
    public List<Recipe> mRecipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        int headIndex = getIntent().getIntExtra("headIndex", 0);
        recipeViewModel = ViewModelProviders.of(this).get(RecipeViewModel.class);
        mRecipes = recipeViewModel.loadJSONFromAsset(this);

        IngredientsFragment ingredientsFragment = new IngredientsFragment();


        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .add(R.id.ingredients_fragment, ingredientsFragment)
                .commit();

        ingredientsFragment.setIngredientsList(mRecipes.get(headIndex).getIngredients());


    }

}
