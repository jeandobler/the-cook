package doblerdynamic.thecook.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import doblerdynamic.thecook.R;
import doblerdynamic.thecook.model.Step;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepViewHolder> {

    private final StepAdapterOnClickHandler mClickHandler;

    private List<Step> mDataset;

    public StepsAdapter(StepAdapterOnClickHandler clickHandler) {
        mClickHandler = clickHandler;
    }

    public void setStepData(List<Step> movieData) {
        mDataset = movieData;
        notifyDataSetChanged();
    }

    @Override
    public StepViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_list_steps, parent, false);

        StepViewHolder vh = new StepViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(StepViewHolder holder, int position) {

        holder.mTvTitle.setText(mDataset.get(position).getShortDescription());
        int number = mDataset.get(position).getId() + 1;
        holder.mTvStep.setText(String.valueOf(number));
    }

    @Override
    public int getItemCount() {
        if (null == mDataset) return 0;
        return mDataset.size();
    }

    public interface StepAdapterOnClickHandler {
        void onClick(int recipePosition);
    }

    public class StepViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mTvTitle;
        TextView mTvStep;

        public StepViewHolder(View v) {
            super(v);
            mTvTitle = v.findViewById(R.id.tv_step_title);
            mTvStep = v.findViewById(R.id.tv_step_id);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();

            mClickHandler.onClick(adapterPosition);
        }

    }

}