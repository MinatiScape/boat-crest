package com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views;

import android.os.Handler;
import android.os.Looper;
import android.webkit.WebView;
import com.clevertap.android.sdk.Constants;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstantsKt;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
@SourceDebugExtension({"SMAP\nWebViewYouTubePlayer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 WebViewYouTubePlayer.kt\ncom/pierfrancescosoffritti/androidyoutubeplayer/core/player/views/YouTubePlayerImpl\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,163:1\n1#2:164\n11335#3:165\n11670#3,3:166\n*S KotlinDebug\n*F\n+ 1 WebViewYouTubePlayer.kt\ncom/pierfrancescosoffritti/androidyoutubeplayer/core/player/views/YouTubePlayerImpl\n*L\n54#1:165\n54#1:166,3\n*E\n"})
/* loaded from: classes9.dex */
public final class b implements YouTubePlayer {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final WebView f13334a;
    @NotNull
    public final Handler b;
    @NotNull
    public final Set<YouTubePlayerListener> c;

    public b(@NotNull WebView webView) {
        Intrinsics.checkNotNullParameter(webView, "webView");
        this.f13334a = webView;
        this.b = new Handler(Looper.getMainLooper());
        this.c = new LinkedHashSet();
    }

    public static final void d(WebView this_invoke, String function, List stringArgs) {
        Intrinsics.checkNotNullParameter(this_invoke, "$this_invoke");
        Intrinsics.checkNotNullParameter(function, "$function");
        Intrinsics.checkNotNullParameter(stringArgs, "$stringArgs");
        this_invoke.loadUrl("javascript:" + function + HexStringBuilder.COMMENT_BEGIN_CHAR + CollectionsKt___CollectionsKt.joinToString$default(stringArgs, Constants.SEPARATOR_COMMA, null, null, 0, null, null, 62, null) + HexStringBuilder.COMMENT_END_CHAR);
    }

    @Override // com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
    public boolean addListener(@NotNull YouTubePlayerListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        return this.c.add(listener);
    }

    @NotNull
    public final Set<YouTubePlayerListener> b() {
        return this.c;
    }

    public final void c(final WebView webView, final String str, Object... objArr) {
        String obj;
        final ArrayList arrayList = new ArrayList(objArr.length);
        for (Object obj2 : objArr) {
            if (obj2 instanceof String) {
                StringBuilder sb = new StringBuilder();
                sb.append('\'');
                sb.append(obj2);
                sb.append('\'');
                obj = sb.toString();
            } else {
                obj = obj2.toString();
            }
            arrayList.add(obj);
        }
        this.b.post(new Runnable() { // from class: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.a
            @Override // java.lang.Runnable
            public final void run() {
                b.d(webView, str, arrayList);
            }
        });
    }

    @Override // com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
    public void cueVideo(@NotNull String videoId, float f) {
        Intrinsics.checkNotNullParameter(videoId, "videoId");
        c(this.f13334a, "cueVideo", videoId, Float.valueOf(f));
    }

    public final void e() {
        this.c.clear();
        this.b.removeCallbacksAndMessages(null);
    }

    @Override // com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
    public void loadVideo(@NotNull String videoId, float f) {
        Intrinsics.checkNotNullParameter(videoId, "videoId");
        c(this.f13334a, "loadVideo", videoId, Float.valueOf(f));
    }

    @Override // com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
    public void mute() {
        c(this.f13334a, "mute", new Object[0]);
    }

    @Override // com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
    public void pause() {
        c(this.f13334a, "pauseVideo", new Object[0]);
    }

    @Override // com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
    public void play() {
        c(this.f13334a, "playVideo", new Object[0]);
    }

    @Override // com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
    public boolean removeListener(@NotNull YouTubePlayerListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        return this.c.remove(listener);
    }

    @Override // com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
    public void seekTo(float f) {
        c(this.f13334a, "seekTo", Float.valueOf(f));
    }

    @Override // com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
    public void setPlaybackRate(@NotNull PlayerConstants.PlaybackRate playbackRate) {
        Intrinsics.checkNotNullParameter(playbackRate, "playbackRate");
        c(this.f13334a, "setPlaybackRate", Float.valueOf(PlayerConstantsKt.toFloat(playbackRate)));
    }

    @Override // com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
    public void setVolume(int i) {
        if (i >= 0 && i < 101) {
            c(this.f13334a, "setVolume", Integer.valueOf(i));
            return;
        }
        throw new IllegalArgumentException("Volume must be between 0 and 100".toString());
    }

    @Override // com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
    public void toggleFullscreen() {
        c(this.f13334a, "toggleFullscreen", new Object[0]);
    }

    @Override // com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
    public void unMute() {
        c(this.f13334a, "unMute", new Object[0]);
    }
}
