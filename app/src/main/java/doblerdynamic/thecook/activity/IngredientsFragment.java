package doblerdynamic.thecook.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import doblerdynamic.thecook.R;
import doblerdynamic.thecook.adapter.IngredientAdapter;
import doblerdynamic.thecook.model.Ingredient;

public class IngredientsFragment extends Fragment {

    RecyclerView mRvIngredients;
    IngredientAdapter ingredientAdapter;
    List<Ingredient> mIngredientList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the Android-Me fragment layout
        View rootView = inflater.inflate(R.layout.fragment_ingredients, container, false);

        mRvIngredients = rootView.findViewById(R.id.rv_fragment_ingredients);

        mRvIngredients.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
        ingredientAdapter = new IngredientAdapter();
        mRvIngredients.setAdapter(ingredientAdapter);
        // Return the rootView

        return rootView;
    }


    public void setIngredientsList(List<Ingredient> ingredientsList) {
        Log.e("QUALVALOR", ingredientsList.toString());

        ingredientAdapter.setIngredientData(ingredientsList);
//        mIngredientList = ingredientsList;
    }


}
