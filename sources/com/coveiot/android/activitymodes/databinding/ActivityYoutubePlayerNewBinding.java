package com.coveiot.android.activitymodes.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.activitymodes.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
/* loaded from: classes2.dex */
public abstract class ActivityYoutubePlayerNewBinding extends ViewDataBinding {
    @NonNull
    public final View toolBar;
    @NonNull
    public final FrameLayout youtubeFrameLayout;
    @NonNull
    public final YouTubePlayerView youtubePlayerView;

    public ActivityYoutubePlayerNewBinding(Object obj, View view, int i, View view2, FrameLayout frameLayout, YouTubePlayerView youTubePlayerView) {
        super(obj, view, i);
        this.toolBar = view2;
        this.youtubeFrameLayout = frameLayout;
        this.youtubePlayerView = youTubePlayerView;
    }

    public static ActivityYoutubePlayerNewBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityYoutubePlayerNewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityYoutubePlayerNewBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityYoutubePlayerNewBinding) ViewDataBinding.bind(obj, view, R.layout.activity_youtube_player_new);
    }

    @NonNull
    @Deprecated
    public static ActivityYoutubePlayerNewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityYoutubePlayerNewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_youtube_player_new, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityYoutubePlayerNewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityYoutubePlayerNewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityYoutubePlayerNewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_youtube_player_new, null, false, obj);
    }
}
