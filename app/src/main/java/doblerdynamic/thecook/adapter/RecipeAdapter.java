package doblerdynamic.thecook.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import doblerdynamic.thecook.model.Recipe;

public class RecipeAdapter {
//
//} extends RecyclerView.Adapter<RecipeAdapter.MoviesViewHolder> {
//
//    public static final String IMAGE_URL = "http://image.tmdb.org/t/p/w185/";
//    public static final String POSTER_URL = "http://image.tmdb.org/t/p/w500/";
//
//    private final MovieDbAdapterOnClickHandler mClickHandler;
//    private List<Recipe> mDataset;
//
//    public RecipeAdapter(MovieDbAdapterOnClickHandler clickHandler) {
//        mClickHandler = clickHandler;
//    }
//
//    public void setMovieData(List<Recipe> movieData) {
//        mDataset = movieData;
//        notifyDataSetChanged();
//    }
//
//    // Create new views (invoked by the layout manager)
//    @Override
//    public MoviesViewHolder onCreateViewHolder(ViewGroup parent,
//                                                             int viewType) {
//        // create a new view
//        View v = (View) LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.activity_list_recipe, parent, false);
//
//        MoviesViewHolder vh = new MoviesViewHolder(v);
//        return vh;
//    }
//
//    // Replace the contents of a view (invoked by the layout manager)
//    @Override
//    public void onBindViewHolder(MoviesViewHolder holder, int position) {
//
//        String movieForThisDay = null;
//        String grade = null;
//
////        movieForThisDay = mDataset.get(position).getTitle();
////        grade = mDataset.get(position).getVoteAverage().toString();
////        String urlImage = mDataset.get(position).getPosterPath();
////        Context context = holder.mIvPoster.getContext();
////        Picasso.get().load(IMAGE_URL + urlImage)
////                .placeholder(R.drawable.no_img) .into(holder.mIvPoster);
////
////        holder.mTvTitle.setText(movieForThisDay);
////        holder.mTvGrade.setText(grade);
//
//    }
//
//    // Return the size of your dataset (invoked by the layout manager)
//    @Override
//    public int getItemCount() {
//        if (null == mDataset) return 0;
//        return mDataset.size();
//    }
//
//    public interface MovieDbAdapterOnClickHandler {
//        void onClick(Recipe movieForDay);
//    }
//
//    // you provide access to all the views for a data item in a view holder
//    public class MoviesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//        // each data item is just a string in this case
//        private TextView mTvTitle;
//        private TextView mTvGrade;
//        private ImageView mIvPoster;
//
//
//        public MoviesViewHolder(View v) {
//            super(v);
////            mTvTitle = (TextView) v.findViewById(R.id.tv_list_movie_data);
////            mTvGrade = (TextView) v.findViewById(R.id.tv_list_grade);
////            mIvPoster = (ImageView) v.findViewById(R.id.iv_list_poster);
//            v.setOnClickListener(this);
//        }
//
//        @Override
//        public void onClick(View v) {
//
//            int adapterPosition = getAdapterPosition();
//            Recipe selectedMovie = null;
//
//            selectedMovie = mDataset.get(adapterPosition);
//
//            mClickHandler.onClick(selectedMovie);
//        }
//
//    }

}