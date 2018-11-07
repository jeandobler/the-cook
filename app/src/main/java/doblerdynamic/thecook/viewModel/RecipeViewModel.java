package doblerdynamic.thecook.viewModel;

import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import doblerdynamic.thecook.model.Recipe;

public class RecipeViewModel extends ViewModel {

    public List<Recipe> loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("recipes.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");


            List<Recipe> a = new Gson().fromJson(json);
            return jsonTo
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


}
