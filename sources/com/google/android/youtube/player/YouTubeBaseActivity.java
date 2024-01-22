package com.google.android.youtube.player;

import android.app.Activity;
import android.os.Bundle;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
/* loaded from: classes10.dex */
public class YouTubeBaseActivity extends Activity {
    public a h;
    public YouTubePlayerView i;
    public int j;
    public Bundle k;

    /* loaded from: classes10.dex */
    public final class a implements YouTubePlayerView.d {
        public a() {
        }

        public /* synthetic */ a(YouTubeBaseActivity youTubeBaseActivity, byte b) {
            this();
        }

        @Override // com.google.android.youtube.player.YouTubePlayerView.d
        public final void a(YouTubePlayerView youTubePlayerView) {
            if (YouTubeBaseActivity.this.i != null && YouTubeBaseActivity.this.i != youTubePlayerView) {
                YouTubeBaseActivity.this.i.m(true);
            }
            YouTubeBaseActivity.this.i = youTubePlayerView;
            if (YouTubeBaseActivity.this.j > 0) {
                youTubePlayerView.b();
            }
            if (YouTubeBaseActivity.this.j >= 2) {
                youTubePlayerView.j();
            }
        }

        @Override // com.google.android.youtube.player.YouTubePlayerView.d
        public final void b(YouTubePlayerView youTubePlayerView, String str, YouTubePlayer.OnInitializedListener onInitializedListener) {
            YouTubeBaseActivity youTubeBaseActivity = YouTubeBaseActivity.this;
            youTubePlayerView.c(youTubeBaseActivity, youTubePlayerView, str, onInitializedListener, youTubeBaseActivity.k);
            YouTubeBaseActivity.d(YouTubeBaseActivity.this);
        }
    }

    public static /* synthetic */ Bundle d(YouTubeBaseActivity youTubeBaseActivity) {
        youTubeBaseActivity.k = null;
        return null;
    }

    public final YouTubePlayerView.d b() {
        return this.h;
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.h = new a(this, (byte) 0);
        this.k = bundle != null ? bundle.getBundle("YouTubeBaseActivity.KEY_PLAYER_VIEW_STATE") : null;
    }

    @Override // android.app.Activity
    public void onDestroy() {
        YouTubePlayerView youTubePlayerView = this.i;
        if (youTubePlayerView != null) {
            youTubePlayerView.k(isFinishing());
        }
        super.onDestroy();
    }

    @Override // android.app.Activity
    public void onPause() {
        this.j = 1;
        YouTubePlayerView youTubePlayerView = this.i;
        if (youTubePlayerView != null) {
            youTubePlayerView.l();
        }
        super.onPause();
    }

    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        this.j = 2;
        YouTubePlayerView youTubePlayerView = this.i;
        if (youTubePlayerView != null) {
            youTubePlayerView.j();
        }
    }

    @Override // android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        YouTubePlayerView youTubePlayerView = this.i;
        bundle.putBundle("YouTubeBaseActivity.KEY_PLAYER_VIEW_STATE", youTubePlayerView != null ? youTubePlayerView.q() : this.k);
    }

    @Override // android.app.Activity
    public void onStart() {
        super.onStart();
        this.j = 1;
        YouTubePlayerView youTubePlayerView = this.i;
        if (youTubePlayerView != null) {
            youTubePlayerView.b();
        }
    }

    @Override // android.app.Activity
    public void onStop() {
        this.j = 0;
        YouTubePlayerView youTubePlayerView = this.i;
        if (youTubePlayerView != null) {
            youTubePlayerView.p();
        }
        super.onStop();
    }
}
