package doblerdynamic.thecook.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import doblerdynamic.thecook.R;
import doblerdynamic.thecook.model.Ingredient;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder> {


    private List<Ingredient> mDataset;


    public IngredientAdapter() {

    }

    public void setIngredientData(List<Ingredient> movieData) {
        mDataset = movieData;
        notifyDataSetChanged();
    }

    @Override
    public IngredientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_list_ingredients, parent, false);


        IngredientViewHolder vh = new IngredientViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(IngredientViewHolder holder, int position) {

        Log.e("asdasdasd", mDataset.get(position).getIngredient().toString());
        holder.mTvTitle.setText(mDataset.get(position).getIngredient());


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        if (null == mDataset) return 0;
        return mDataset.size();
    }

    public interface IngredientAdapterOnClickHandler {
        void onClick(int adapterPosition);
    }

    public class IngredientViewHolder extends RecyclerView.ViewHolder {
        TextView mTvTitle;

        public IngredientViewHolder(View v) {
            super(v);
            mTvTitle = v.findViewById(R.id.tv_ingredients_title);
//            mIvPoster = v.findViewById(R.id.iv_recipe_poster);
//            mTvTitle = v.findViewById(R.id.tv_recipe_title);
        }


    }

}