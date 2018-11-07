package doblerdynamic.thecook.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import doblerdynamic.thecook.R;
import doblerdynamic.thecook.model.Recipe;
import doblerdynamic.thecook.viewModel.RecipeViewModel;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.message) TextView mTextMessage;

    RecipeViewModel recipeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        recipeViewModel = ViewModelProviders.of(this).get(RecipeViewModel.class);
        List<Recipe> vara = recipeViewModel.loadJSONFromAsset(this);
        mTextMessage.setText(vara.get(0).getName());


    }

}
