package com.pierfrancescosoffritti.androidyoutubeplayer.core.player;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import androidx.annotation.RestrictTo;
import com.coveiot.android.fitnesschallenges.utils.FitnessChallengeConstants;
import com.jstyle.blesdk1860.constant.BleConst;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener;
import com.squareup.otto.Bus;
import java.util.Collection;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.m;
import org.jetbrains.annotations.NotNull;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
@SourceDebugExtension({"SMAP\nYouTubePlayerBridge.kt\nKotlin\n*S Kotlin\n*F\n+ 1 YouTubePlayerBridge.kt\ncom/pierfrancescosoffritti/androidyoutubeplayer/core/player/YouTubePlayerBridge\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,203:1\n1855#2,2:204\n1855#2,2:206\n1855#2,2:208\n1855#2,2:210\n1855#2,2:212\n1855#2,2:214\n1855#2,2:216\n1855#2,2:218\n1855#2,2:220\n1855#2,2:222\n*S KotlinDebug\n*F\n+ 1 YouTubePlayerBridge.kt\ncom/pierfrancescosoffritti/androidyoutubeplayer/core/player/YouTubePlayerBridge\n*L\n60#1:204,2\n68#1:206,2\n77#1:208,2\n86#1:210,2\n95#1:212,2\n101#1:214,2\n114#1:216,2\n129#1:218,2\n143#1:220,2\n149#1:222,2\n*E\n"})
/* loaded from: classes9.dex */
public final class YouTubePlayerBridge {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final YouTubePlayerBridgeCallbacks f13320a;
    @NotNull
    public final Handler b;

    /* loaded from: classes9.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* loaded from: classes9.dex */
    public interface YouTubePlayerBridgeCallbacks {
        @NotNull
        YouTubePlayer getInstance();

        @NotNull
        Collection<YouTubePlayerListener> getListeners();

        void onYouTubeIFrameAPIReady();
    }

    public YouTubePlayerBridge(@NotNull YouTubePlayerBridgeCallbacks youTubePlayerOwner) {
        Intrinsics.checkNotNullParameter(youTubePlayerOwner, "youTubePlayerOwner");
        this.f13320a = youTubePlayerOwner;
        this.b = new Handler(Looper.getMainLooper());
    }

    public static final void p(YouTubePlayerBridge this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        for (YouTubePlayerListener youTubePlayerListener : this$0.f13320a.getListeners()) {
            youTubePlayerListener.onApiChange(this$0.f13320a.getInstance());
        }
    }

    public static final void q(YouTubePlayerBridge this$0, PlayerConstants.PlayerError playerError) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(playerError, "$playerError");
        for (YouTubePlayerListener youTubePlayerListener : this$0.f13320a.getListeners()) {
            youTubePlayerListener.onError(this$0.f13320a.getInstance(), playerError);
        }
    }

    public static final void r(YouTubePlayerBridge this$0, PlayerConstants.PlaybackQuality playbackQuality) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(playbackQuality, "$playbackQuality");
        for (YouTubePlayerListener youTubePlayerListener : this$0.f13320a.getListeners()) {
            youTubePlayerListener.onPlaybackQualityChange(this$0.f13320a.getInstance(), playbackQuality);
        }
    }

    public static final void s(YouTubePlayerBridge this$0, PlayerConstants.PlaybackRate playbackRate) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(playbackRate, "$playbackRate");
        for (YouTubePlayerListener youTubePlayerListener : this$0.f13320a.getListeners()) {
            youTubePlayerListener.onPlaybackRateChange(this$0.f13320a.getInstance(), playbackRate);
        }
    }

    public static final void t(YouTubePlayerBridge this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        for (YouTubePlayerListener youTubePlayerListener : this$0.f13320a.getListeners()) {
            youTubePlayerListener.onReady(this$0.f13320a.getInstance());
        }
    }

    public static final void u(YouTubePlayerBridge this$0, PlayerConstants.PlayerState playerState) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(playerState, "$playerState");
        for (YouTubePlayerListener youTubePlayerListener : this$0.f13320a.getListeners()) {
            youTubePlayerListener.onStateChange(this$0.f13320a.getInstance(), playerState);
        }
    }

    public static final void v(YouTubePlayerBridge this$0, float f) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        for (YouTubePlayerListener youTubePlayerListener : this$0.f13320a.getListeners()) {
            youTubePlayerListener.onCurrentSecond(this$0.f13320a.getInstance(), f);
        }
    }

    public static final void w(YouTubePlayerBridge this$0, float f) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        for (YouTubePlayerListener youTubePlayerListener : this$0.f13320a.getListeners()) {
            youTubePlayerListener.onVideoDuration(this$0.f13320a.getInstance(), f);
        }
    }

    public static final void x(YouTubePlayerBridge this$0, String videoId) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(videoId, "$videoId");
        for (YouTubePlayerListener youTubePlayerListener : this$0.f13320a.getListeners()) {
            youTubePlayerListener.onVideoId(this$0.f13320a.getInstance(), videoId);
        }
    }

    public static final void y(YouTubePlayerBridge this$0, float f) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        for (YouTubePlayerListener youTubePlayerListener : this$0.f13320a.getListeners()) {
            youTubePlayerListener.onVideoLoadedFraction(this$0.f13320a.getInstance(), f);
        }
    }

    public static final void z(YouTubePlayerBridge this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.f13320a.onYouTubeIFrameAPIReady();
    }

    public final PlayerConstants.PlaybackQuality l(String str) {
        if (m.equals(str, "small", true)) {
            return PlayerConstants.PlaybackQuality.SMALL;
        }
        if (m.equals(str, "medium", true)) {
            return PlayerConstants.PlaybackQuality.MEDIUM;
        }
        if (m.equals(str, "large", true)) {
            return PlayerConstants.PlaybackQuality.LARGE;
        }
        if (m.equals(str, "hd720", true)) {
            return PlayerConstants.PlaybackQuality.HD720;
        }
        if (m.equals(str, "hd1080", true)) {
            return PlayerConstants.PlaybackQuality.HD1080;
        }
        if (m.equals(str, "highres", true)) {
            return PlayerConstants.PlaybackQuality.HIGH_RES;
        }
        return m.equals(str, Bus.DEFAULT_IDENTIFIER, true) ? PlayerConstants.PlaybackQuality.DEFAULT : PlayerConstants.PlaybackQuality.UNKNOWN;
    }

    public final PlayerConstants.PlaybackRate m(String str) {
        return m.equals(str, "0.25", true) ? PlayerConstants.PlaybackRate.RATE_0_25 : m.equals(str, "0.5", true) ? PlayerConstants.PlaybackRate.RATE_0_5 : m.equals(str, "1", true) ? PlayerConstants.PlaybackRate.RATE_1 : m.equals(str, "1.5", true) ? PlayerConstants.PlaybackRate.RATE_1_5 : m.equals(str, "2", true) ? PlayerConstants.PlaybackRate.RATE_2 : PlayerConstants.PlaybackRate.UNKNOWN;
    }

    public final PlayerConstants.PlayerError n(String str) {
        if (m.equals(str, "2", true)) {
            return PlayerConstants.PlayerError.INVALID_PARAMETER_IN_REQUEST;
        }
        if (m.equals(str, BleConst.SetDeviceInfo, true)) {
            return PlayerConstants.PlayerError.HTML_5_PLAYER;
        }
        if (m.equals(str, "100", true)) {
            return PlayerConstants.PlayerError.VIDEO_NOT_FOUND;
        }
        if (!m.equals(str, "101", true) && !m.equals(str, "150", true)) {
            return PlayerConstants.PlayerError.UNKNOWN;
        }
        return PlayerConstants.PlayerError.VIDEO_NOT_PLAYABLE_IN_EMBEDDED_PLAYER;
    }

    public final PlayerConstants.PlayerState o(String str) {
        return m.equals(str, "UNSTARTED", true) ? PlayerConstants.PlayerState.UNSTARTED : m.equals(str, FitnessChallengeConstants.ENDED, true) ? PlayerConstants.PlayerState.ENDED : m.equals(str, "PLAYING", true) ? PlayerConstants.PlayerState.PLAYING : m.equals(str, "PAUSED", true) ? PlayerConstants.PlayerState.PAUSED : m.equals(str, "BUFFERING", true) ? PlayerConstants.PlayerState.BUFFERING : m.equals(str, "CUED", true) ? PlayerConstants.PlayerState.VIDEO_CUED : PlayerConstants.PlayerState.UNKNOWN;
    }

    @JavascriptInterface
    public final boolean sendApiChange() {
        return this.b.post(new Runnable() { // from class: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.d
            @Override // java.lang.Runnable
            public final void run() {
                YouTubePlayerBridge.p(YouTubePlayerBridge.this);
            }
        });
    }

    @JavascriptInterface
    public final void sendError(@NotNull String error) {
        Intrinsics.checkNotNullParameter(error, "error");
        final PlayerConstants.PlayerError n = n(error);
        this.b.post(new Runnable() { // from class: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.j
            @Override // java.lang.Runnable
            public final void run() {
                YouTubePlayerBridge.q(YouTubePlayerBridge.this, n);
            }
        });
    }

    @JavascriptInterface
    public final void sendPlaybackQualityChange(@NotNull String quality) {
        Intrinsics.checkNotNullParameter(quality, "quality");
        final PlayerConstants.PlaybackQuality l = l(quality);
        this.b.post(new Runnable() { // from class: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.h
            @Override // java.lang.Runnable
            public final void run() {
                YouTubePlayerBridge.r(YouTubePlayerBridge.this, l);
            }
        });
    }

    @JavascriptInterface
    public final void sendPlaybackRateChange(@NotNull String rate) {
        Intrinsics.checkNotNullParameter(rate, "rate");
        final PlayerConstants.PlaybackRate m = m(rate);
        this.b.post(new Runnable() { // from class: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.i
            @Override // java.lang.Runnable
            public final void run() {
                YouTubePlayerBridge.s(YouTubePlayerBridge.this, m);
            }
        });
    }

    @JavascriptInterface
    public final boolean sendReady() {
        return this.b.post(new Runnable() { // from class: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.a
            @Override // java.lang.Runnable
            public final void run() {
                YouTubePlayerBridge.t(YouTubePlayerBridge.this);
            }
        });
    }

    @JavascriptInterface
    public final void sendStateChange(@NotNull String state) {
        Intrinsics.checkNotNullParameter(state, "state");
        final PlayerConstants.PlayerState o = o(state);
        this.b.post(new Runnable() { // from class: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.k
            @Override // java.lang.Runnable
            public final void run() {
                YouTubePlayerBridge.u(YouTubePlayerBridge.this, o);
            }
        });
    }

    @JavascriptInterface
    public final void sendVideoCurrentTime(@NotNull String seconds) {
        Intrinsics.checkNotNullParameter(seconds, "seconds");
        try {
            final float parseFloat = Float.parseFloat(seconds);
            this.b.post(new Runnable() { // from class: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.e
                @Override // java.lang.Runnable
                public final void run() {
                    YouTubePlayerBridge.v(YouTubePlayerBridge.this, parseFloat);
                }
            });
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    @JavascriptInterface
    public final void sendVideoDuration(@NotNull String seconds) {
        Intrinsics.checkNotNullParameter(seconds, "seconds");
        try {
            if (TextUtils.isEmpty(seconds)) {
                seconds = BleConst.GetDeviceTime;
            }
            final float parseFloat = Float.parseFloat(seconds);
            this.b.post(new Runnable() { // from class: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.f
                @Override // java.lang.Runnable
                public final void run() {
                    YouTubePlayerBridge.w(YouTubePlayerBridge.this, parseFloat);
                }
            });
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    @JavascriptInterface
    public final boolean sendVideoId(@NotNull final String videoId) {
        Intrinsics.checkNotNullParameter(videoId, "videoId");
        return this.b.post(new Runnable() { // from class: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.b
            @Override // java.lang.Runnable
            public final void run() {
                YouTubePlayerBridge.x(YouTubePlayerBridge.this, videoId);
            }
        });
    }

    @JavascriptInterface
    public final void sendVideoLoadedFraction(@NotNull String fraction) {
        Intrinsics.checkNotNullParameter(fraction, "fraction");
        try {
            final float parseFloat = Float.parseFloat(fraction);
            this.b.post(new Runnable() { // from class: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.g
                @Override // java.lang.Runnable
                public final void run() {
                    YouTubePlayerBridge.y(YouTubePlayerBridge.this, parseFloat);
                }
            });
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    @JavascriptInterface
    public final boolean sendYouTubeIFrameAPIReady() {
        return this.b.post(new Runnable() { // from class: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.c
            @Override // java.lang.Runnable
            public final void run() {
                YouTubePlayerBridge.z(YouTubePlayerBridge.this);
            }
        });
    }
}
