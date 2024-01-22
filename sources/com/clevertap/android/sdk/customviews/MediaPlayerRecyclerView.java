package com.clevertap.android.sdk.customviews;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.clevertap.android.sdk.R;
import com.clevertap.android.sdk.inbox.CTInboxActivity;
import com.clevertap.android.sdk.inbox.CTInboxBaseMessageViewHolder;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.StyledPlayerView;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes2.dex */
public class MediaPlayerRecyclerView extends RecyclerView {
    public ExoPlayer S0;
    public Context T0;
    public CTInboxBaseMessageViewHolder U0;
    public StyledPlayerView V0;

    /* loaded from: classes2.dex */
    public class a extends RecyclerView.OnScrollListener {
        public a() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int i) {
            super.onScrollStateChanged(recyclerView, i);
            if (i == 0) {
                MediaPlayerRecyclerView.this.playVideo();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(@NonNull RecyclerView recyclerView, int i, int i2) {
            super.onScrolled(recyclerView, i, i2);
        }
    }

    /* loaded from: classes2.dex */
    public class b implements RecyclerView.OnChildAttachStateChangeListener {
        public b() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnChildAttachStateChangeListener
        public void onChildViewAttachedToWindow(@NonNull View view) {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnChildAttachStateChangeListener
        public void onChildViewDetachedFromWindow(@NonNull View view) {
            if (MediaPlayerRecyclerView.this.U0 == null || !MediaPlayerRecyclerView.this.U0.itemView.equals(view)) {
                return;
            }
            MediaPlayerRecyclerView.this.stop();
        }
    }

    /* loaded from: classes2.dex */
    public class c implements Player.Listener {
        public c(MediaPlayerRecyclerView mediaPlayerRecyclerView) {
        }
    }

    public MediaPlayerRecyclerView(Context context) {
        super(context);
        T0(context);
    }

    public final CTInboxBaseMessageViewHolder S0() {
        CTInboxBaseMessageViewHolder cTInboxBaseMessageViewHolder;
        int findFirstVisibleItemPosition = ((LinearLayoutManager) getLayoutManager()).findFirstVisibleItemPosition();
        int findLastVisibleItemPosition = ((LinearLayoutManager) getLayoutManager()).findLastVisibleItemPosition();
        CTInboxBaseMessageViewHolder cTInboxBaseMessageViewHolder2 = null;
        int i = 0;
        for (int i2 = findFirstVisibleItemPosition; i2 <= findLastVisibleItemPosition; i2++) {
            View childAt = getChildAt(i2 - findFirstVisibleItemPosition);
            if (childAt != null && (cTInboxBaseMessageViewHolder = (CTInboxBaseMessageViewHolder) childAt.getTag()) != null && cTInboxBaseMessageViewHolder.needsMediaPlayer()) {
                Rect rect = new Rect();
                int height = cTInboxBaseMessageViewHolder.itemView.getGlobalVisibleRect(rect) ? rect.height() : 0;
                if (height > i) {
                    cTInboxBaseMessageViewHolder2 = cTInboxBaseMessageViewHolder;
                    i = height;
                }
            }
        }
        return cTInboxBaseMessageViewHolder2;
    }

    public final void T0(Context context) {
        this.T0 = context.getApplicationContext();
        StyledPlayerView styledPlayerView = new StyledPlayerView(this.T0);
        this.V0 = styledPlayerView;
        styledPlayerView.setBackgroundColor(0);
        if (CTInboxActivity.orientation == 2) {
            this.V0.setResizeMode(3);
        } else {
            this.V0.setResizeMode(0);
        }
        this.V0.setUseArtwork(true);
        this.V0.setDefaultArtwork(ResourcesCompat.getDrawable(context.getResources(), R.drawable.ct_audio, null));
        ExoPlayer build = new ExoPlayer.Builder(context).setTrackSelector(new DefaultTrackSelector(this.T0, new AdaptiveTrackSelection.Factory())).build();
        this.S0 = build;
        build.setVolume(0.0f);
        this.V0.setUseController(true);
        this.V0.setControllerAutoShow(false);
        this.V0.setPlayer(this.S0);
        addOnScrollListener(new a());
        addOnChildAttachStateChangeListener(new b());
        this.S0.addListener(new c(this));
    }

    public final void U0() {
        ViewGroup viewGroup;
        int indexOfChild;
        StyledPlayerView styledPlayerView = this.V0;
        if (styledPlayerView == null || (viewGroup = (ViewGroup) styledPlayerView.getParent()) == null || (indexOfChild = viewGroup.indexOfChild(this.V0)) < 0) {
            return;
        }
        viewGroup.removeViewAt(indexOfChild);
        ExoPlayer exoPlayer = this.S0;
        if (exoPlayer != null) {
            exoPlayer.stop();
        }
        CTInboxBaseMessageViewHolder cTInboxBaseMessageViewHolder = this.U0;
        if (cTInboxBaseMessageViewHolder != null) {
            cTInboxBaseMessageViewHolder.playerRemoved();
            this.U0 = null;
        }
    }

    public void onPausePlayer() {
        ExoPlayer exoPlayer = this.S0;
        if (exoPlayer != null) {
            exoPlayer.setPlayWhenReady(false);
        }
    }

    public void onRestartPlayer() {
        if (this.V0 == null) {
            T0(this.T0);
            playVideo();
        }
    }

    public void playVideo() {
        if (this.V0 == null) {
            return;
        }
        CTInboxBaseMessageViewHolder S0 = S0();
        if (S0 == null) {
            stop();
            U0();
            return;
        }
        CTInboxBaseMessageViewHolder cTInboxBaseMessageViewHolder = this.U0;
        if (cTInboxBaseMessageViewHolder != null && cTInboxBaseMessageViewHolder.itemView.equals(S0.itemView)) {
            Rect rect = new Rect();
            int height = this.U0.itemView.getGlobalVisibleRect(rect) ? rect.height() : 0;
            ExoPlayer exoPlayer = this.S0;
            if (exoPlayer != null) {
                if (height >= 400) {
                    if (this.U0.shouldAutoPlay()) {
                        this.S0.setPlayWhenReady(true);
                        return;
                    }
                    return;
                }
                exoPlayer.setPlayWhenReady(false);
                return;
            }
            return;
        }
        U0();
        if (S0.addMediaPlayer(this.V0)) {
            this.U0 = S0;
        }
    }

    public void release() {
        ExoPlayer exoPlayer = this.S0;
        if (exoPlayer != null) {
            exoPlayer.stop();
            this.S0.release();
            this.S0 = null;
        }
        this.U0 = null;
        this.V0 = null;
    }

    public void removePlayer() {
        if (this.V0 != null) {
            U0();
            this.V0 = null;
        }
    }

    public void stop() {
        ExoPlayer exoPlayer = this.S0;
        if (exoPlayer != null) {
            exoPlayer.stop();
        }
        this.U0 = null;
    }

    public MediaPlayerRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        T0(context);
    }

    public MediaPlayerRecyclerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        T0(context);
    }
}
