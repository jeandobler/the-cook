package doblerdynamic.thecook.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import doblerdynamic.thecook.R;
import doblerdynamic.thecook.viewModel.RecipeViewModel;

public class StepsVideoFragment extends Fragment {


    RecipeViewModel mRecipeViewModel;
    private String mVideoUrl;
    private SimpleExoPlayer mExoPlayer;
    private SimpleExoPlayerView mPlayerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_steps_video, container, false);
        mRecipeViewModel = ViewModelProviders.of(this).get(RecipeViewModel.class);

        if (savedInstanceState == null) {
            Bundle extras = getArguments();
            this.setVideoUrl(extras.getString("videoUrl"));
        }

        return rootView;
    }

    public void setVideoUrl(String url) {
        this.mVideoUrl = url;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPlayerView = (SimpleExoPlayerView) view.findViewById(R.id.playerView);

        initializePlayer(view.getContext());

    }

    private void initializePlayer(Context context) {

        if (this.mVideoUrl != null && !this.mVideoUrl.isEmpty()) {
            Uri mediaUri = Uri.parse(this.mVideoUrl);
            if (mExoPlayer == null) {
                // Create an instance of the ExoPlayer.
                TrackSelector trackSelector = new DefaultTrackSelector();
                LoadControl loadControl = new DefaultLoadControl();
                mExoPlayer = ExoPlayerFactory.newSimpleInstance(context, trackSelector, loadControl);
                mPlayerView.setPlayer(mExoPlayer);

                // Set the ExoPlayer.EventListener to this activity.
//            mExoPlayer.addListener(context);

                // Prepare the MediaSource.
                String userAgent = Util.getUserAgent(context, "ClassicalMusicQuiz");
                MediaSource mediaSource = new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(
                        context, userAgent), new DefaultExtractorsFactory(), null, null);
                mExoPlayer.prepare(mediaSource);
                mExoPlayer.setPlayWhenReady(true);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mExoPlayer != null) {
            mExoPlayer.stop();
        }

    }
}
