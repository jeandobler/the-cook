package doblerdynamic.thecook.viewModel;

import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import doblerdynamic.thecook.model.Recipe;

public class RecipeViewModel extends ViewModel {

    private List<Recipe> recipes = new ArrayList<>();

    public List<Recipe> loadJSONFromAsset(Context context) {
        String json = null;

        if (!recipes.isEmpty()) {
            return recipes;
        }
        try {
            InputStream is = context.getAssets().open("recipes.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
            JSONArray jsonArray = new JSONArray(json);
            Gson gson = new Gson();
            for (int cont = 0; cont < jsonArray.length(); cont++) {
                Recipe recipe = gson.fromJson(jsonArray.get(cont).toString(), Recipe.class);
                recipes.add(recipe);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
            Log.e("Objecto", "Exception ");
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return recipes;
    }


}
