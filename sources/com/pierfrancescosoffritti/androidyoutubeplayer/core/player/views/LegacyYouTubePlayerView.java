package com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.LayoutRes;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.FullscreenListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerCallback;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.NetworkObserver;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.PlaybackResumer;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes9.dex */
public final class LegacyYouTubePlayerView extends SixteenByNineFrameLayout {
    @NotNull
    public final WebViewYouTubePlayer h;
    @NotNull
    public final NetworkObserver i;
    @NotNull
    public final PlaybackResumer j;
    public boolean k;
    @NotNull
    public Function0<Unit> l;
    @NotNull
    public final Set<YouTubePlayerCallback> m;
    public boolean n;

    /* loaded from: classes9.dex */
    public static final class a extends Lambda implements Function0<Unit> {
        public static final a INSTANCE = new a();

        public a() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2() {
        }
    }

    /* loaded from: classes9.dex */
    public static final class b extends Lambda implements Function0<Unit> {
        public final /* synthetic */ IFramePlayerOptions $playerOptions;
        public final /* synthetic */ YouTubePlayerListener $youTubePlayerListener;

        /* loaded from: classes9.dex */
        public static final class a extends Lambda implements Function1<YouTubePlayer, Unit> {
            public final /* synthetic */ YouTubePlayerListener $youTubePlayerListener;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(YouTubePlayerListener youTubePlayerListener) {
                super(1);
                this.$youTubePlayerListener = youTubePlayerListener;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(YouTubePlayer youTubePlayer) {
                invoke2(youTubePlayer);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull YouTubePlayer it) {
                Intrinsics.checkNotNullParameter(it, "it");
                it.addListener(this.$youTubePlayerListener);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(IFramePlayerOptions iFramePlayerOptions, YouTubePlayerListener youTubePlayerListener) {
            super(0);
            this.$playerOptions = iFramePlayerOptions;
            this.$youTubePlayerListener = youTubePlayerListener;
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2() {
            LegacyYouTubePlayerView.this.getWebViewYouTubePlayer$core_release().initialize$core_release(new a(this.$youTubePlayerListener), this.$playerOptions);
        }
    }

    public /* synthetic */ LegacyYouTubePlayerView(Context context, FullscreenListener fullscreenListener, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, fullscreenListener, (i2 & 4) != 0 ? null : attributeSet, (i2 & 8) != 0 ? 0 : i);
    }

    public final void enableBackgroundPlayback(boolean z) {
        this.h.setBackgroundPlaybackEnabled$core_release(z);
    }

    public final boolean getCanPlay$core_release() {
        return this.n;
    }

    @NotNull
    public final WebViewYouTubePlayer getWebViewYouTubePlayer$core_release() {
        return this.h;
    }

    public final void getYouTubePlayerWhenReady(@NotNull YouTubePlayerCallback youTubePlayerCallback) {
        Intrinsics.checkNotNullParameter(youTubePlayerCallback, "youTubePlayerCallback");
        if (this.k) {
            youTubePlayerCallback.onYouTubePlayer(this.h.getYoutubePlayer$core_release());
        } else {
            this.m.add(youTubePlayerCallback);
        }
    }

    @NotNull
    public final View inflateCustomPlayerUi(@LayoutRes int i) {
        removeViews(1, getChildCount() - 1);
        View inflate = View.inflate(getContext(), i, this);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(context, layoutId, this)");
        return inflate;
    }

    public final void initialize(@NotNull YouTubePlayerListener youTubePlayerListener, boolean z, @NotNull IFramePlayerOptions playerOptions) {
        Intrinsics.checkNotNullParameter(youTubePlayerListener, "youTubePlayerListener");
        Intrinsics.checkNotNullParameter(playerOptions, "playerOptions");
        if (!this.k) {
            if (z) {
                this.i.observeNetwork();
            }
            b bVar = new b(playerOptions, youTubePlayerListener);
            this.l = bVar;
            if (z) {
                return;
            }
            bVar.invoke();
            return;
        }
        throw new IllegalStateException("This YouTubePlayerView has already been initialized.");
    }

    public final boolean isEligibleForPlayback$core_release() {
        return this.n || this.h.isBackgroundPlaybackEnabled$core_release();
    }

    public final boolean isYouTubePlayerReady$core_release() {
        return this.k;
    }

    public final void onResume$core_release() {
        this.j.onLifecycleResume();
        this.n = true;
    }

    public final void onStop$core_release() {
        this.h.getYoutubePlayer$core_release().pause();
        this.j.onLifecycleStop();
        this.n = false;
    }

    public final void release() {
        this.i.destroy();
        removeView(this.h);
        this.h.removeAllViews();
        this.h.destroy();
    }

    public final void setCustomPlayerUi(@NotNull View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        removeViews(1, getChildCount() - 1);
        addView(view);
    }

    public final void setYouTubePlayerReady$core_release(boolean z) {
        this.k = z;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LegacyYouTubePlayerView(@NotNull Context context, @NotNull FullscreenListener listener, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(listener, "listener");
        WebViewYouTubePlayer webViewYouTubePlayer = new WebViewYouTubePlayer(context, listener, null, 0, 12, null);
        this.h = webViewYouTubePlayer;
        Context applicationContext = context.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "context.applicationContext");
        NetworkObserver networkObserver = new NetworkObserver(applicationContext);
        this.i = networkObserver;
        PlaybackResumer playbackResumer = new PlaybackResumer();
        this.j = playbackResumer;
        this.l = a.INSTANCE;
        this.m = new LinkedHashSet();
        this.n = true;
        addView(webViewYouTubePlayer, new FrameLayout.LayoutParams(-1, -1));
        webViewYouTubePlayer.addListener(playbackResumer);
        webViewYouTubePlayer.addListener(new AbstractYouTubePlayerListener() { // from class: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.LegacyYouTubePlayerView.1
            @Override // com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener, com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
            public void onStateChange(@NotNull YouTubePlayer youTubePlayer, @NotNull PlayerConstants.PlayerState state) {
                Intrinsics.checkNotNullParameter(youTubePlayer, "youTubePlayer");
                Intrinsics.checkNotNullParameter(state, "state");
                if (state != PlayerConstants.PlayerState.PLAYING || LegacyYouTubePlayerView.this.isEligibleForPlayback$core_release()) {
                    return;
                }
                youTubePlayer.pause();
            }
        });
        webViewYouTubePlayer.addListener(new AbstractYouTubePlayerListener() { // from class: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.LegacyYouTubePlayerView.2
            @Override // com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener, com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
            public void onReady(@NotNull YouTubePlayer youTubePlayer) {
                Intrinsics.checkNotNullParameter(youTubePlayer, "youTubePlayer");
                LegacyYouTubePlayerView.this.setYouTubePlayerReady$core_release(true);
                for (YouTubePlayerCallback youTubePlayerCallback : LegacyYouTubePlayerView.this.m) {
                    youTubePlayerCallback.onYouTubePlayer(youTubePlayer);
                }
                LegacyYouTubePlayerView.this.m.clear();
                youTubePlayer.removeListener(this);
            }
        });
        networkObserver.getListeners().add(new NetworkObserver.Listener() { // from class: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.LegacyYouTubePlayerView.3
            @Override // com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.NetworkObserver.Listener
            public void onNetworkAvailable() {
                if (!LegacyYouTubePlayerView.this.isYouTubePlayerReady$core_release()) {
                    LegacyYouTubePlayerView.this.l.invoke();
                } else {
                    LegacyYouTubePlayerView.this.j.resume(LegacyYouTubePlayerView.this.getWebViewYouTubePlayer$core_release().getYoutubePlayer$core_release());
                }
            }

            @Override // com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.NetworkObserver.Listener
            public void onNetworkUnavailable() {
            }
        });
    }

    public final void initialize(@NotNull YouTubePlayerListener youTubePlayerListener, boolean z) {
        Intrinsics.checkNotNullParameter(youTubePlayerListener, "youTubePlayerListener");
        initialize(youTubePlayerListener, z, IFramePlayerOptions.Companion.getDefault());
    }

    public final void initialize(@NotNull YouTubePlayerListener youTubePlayerListener) {
        Intrinsics.checkNotNullParameter(youTubePlayerListener, "youTubePlayerListener");
        initialize(youTubePlayerListener, true);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public LegacyYouTubePlayerView(@NotNull Context context) {
        this(context, FakeWebViewYouTubeListener.INSTANCE, null, 0);
        Intrinsics.checkNotNullParameter(context, "context");
    }
}
