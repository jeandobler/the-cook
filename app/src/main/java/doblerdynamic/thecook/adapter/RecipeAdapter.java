package doblerdynamic.thecook.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import doblerdynamic.thecook.R;
import doblerdynamic.thecook.model.Recipe;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private final RecipeAdapterOnClickHandler mClickHandler;

    private List<Recipe> mDataset;


    public RecipeAdapter(RecipeAdapterOnClickHandler clickHandler) {
        mClickHandler = clickHandler;
    }

    public void setRecipeData(List<Recipe> movieData) {
        mDataset = movieData;
        notifyDataSetChanged();
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_list_recipe, parent, false);


        RecipeViewHolder vh = new RecipeViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {

        holder.mTvTitle.setText(mDataset.get(position).getName());

        if (mDataset.get(position).getImage().isEmpty()) {
            Picasso.get().load(R.drawable.no_img)
                    .placeholder(R.drawable.no_img)
                    .into(holder.mIvPoster);
        } else {
            Picasso.get().load(mDataset.get(position).getImage())
                    .placeholder(R.drawable.no_img)
                    .into(holder.mIvPoster);
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        if (null == mDataset) return 0;
        return mDataset.size();
    }

    public interface RecipeAdapterOnClickHandler {
        void onClick(int recipePosition);
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView mIvPoster;
        TextView mTvTitle;

        public RecipeViewHolder(View v) {
            super(v);
            mIvPoster = v.findViewById(R.id.iv_recipe_poster);
            mTvTitle = v.findViewById(R.id.tv_recipe_title);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();

            mClickHandler.onClick(adapterPosition);
        }

    }

}