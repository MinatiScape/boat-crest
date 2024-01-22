package com.google.android.youtube.player;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.internal.ab;
/* loaded from: classes10.dex */
public class YouTubePlayerSupportFragment extends Fragment implements YouTubePlayer.Provider {
    public final a h = new a(this, (byte) 0);
    public Bundle i;
    public YouTubePlayerView j;
    public String k;
    public YouTubePlayer.OnInitializedListener l;
    public boolean m;

    /* loaded from: classes10.dex */
    public final class a implements YouTubePlayerView.d {
        public a() {
        }

        public /* synthetic */ a(YouTubePlayerSupportFragment youTubePlayerSupportFragment, byte b) {
            this();
        }

        @Override // com.google.android.youtube.player.YouTubePlayerView.d
        public final void a(YouTubePlayerView youTubePlayerView) {
        }

        @Override // com.google.android.youtube.player.YouTubePlayerView.d
        public final void b(YouTubePlayerView youTubePlayerView, String str, YouTubePlayer.OnInitializedListener onInitializedListener) {
            YouTubePlayerSupportFragment youTubePlayerSupportFragment = YouTubePlayerSupportFragment.this;
            youTubePlayerSupportFragment.initialize(str, youTubePlayerSupportFragment.l);
        }
    }

    public static YouTubePlayerSupportFragment newInstance() {
        return new YouTubePlayerSupportFragment();
    }

    public final void a() {
        YouTubePlayerView youTubePlayerView = this.j;
        if (youTubePlayerView == null || this.l == null) {
            return;
        }
        youTubePlayerView.h(this.m);
        this.j.c(getActivity(), this, this.k, this.l, this.i);
        this.i = null;
        this.l = null;
    }

    @Override // com.google.android.youtube.player.YouTubePlayer.Provider
    public void initialize(String str, YouTubePlayer.OnInitializedListener onInitializedListener) {
        this.k = ab.a(str, (Object) "Developer key cannot be null or empty");
        this.l = onInitializedListener;
        a();
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.i = bundle != null ? bundle.getBundle("YouTubePlayerSupportFragment.KEY_PLAYER_VIEW_STATE") : null;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.j = new YouTubePlayerView(getActivity(), null, 0, this.h);
        a();
        return this.j;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        if (this.j != null) {
            FragmentActivity activity = getActivity();
            this.j.k(activity == null || activity.isFinishing());
        }
        super.onDestroy();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        this.j.m(getActivity().isFinishing());
        this.j = null;
        super.onDestroyView();
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        this.j.l();
        super.onPause();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.j.j();
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        YouTubePlayerView youTubePlayerView = this.j;
        bundle.putBundle("YouTubePlayerSupportFragment.KEY_PLAYER_VIEW_STATE", youTubePlayerView != null ? youTubePlayerView.q() : this.i);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        this.j.b();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        this.j.p();
        super.onStop();
    }
}
