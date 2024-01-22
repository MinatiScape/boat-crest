package com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils;

import androidx.lifecycle.Lifecycle;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@JvmName(name = "YouTubePlayerUtils")
/* loaded from: classes9.dex */
public final class YouTubePlayerUtils {
    public static final void loadOrCueVideo(@NotNull YouTubePlayer youTubePlayer, @NotNull Lifecycle lifecycle, @NotNull String videoId, float f) {
        Intrinsics.checkNotNullParameter(youTubePlayer, "<this>");
        Intrinsics.checkNotNullParameter(lifecycle, "lifecycle");
        Intrinsics.checkNotNullParameter(videoId, "videoId");
        loadOrCueVideo(youTubePlayer, lifecycle.getCurrentState() == Lifecycle.State.RESUMED, videoId, f);
    }

    public static final /* synthetic */ void loadOrCueVideo(YouTubePlayer youTubePlayer, boolean z, String videoId, float f) {
        Intrinsics.checkNotNullParameter(youTubePlayer, "<this>");
        Intrinsics.checkNotNullParameter(videoId, "videoId");
        if (z) {
            youTubePlayer.loadVideo(videoId, f);
        } else {
            youTubePlayer.cueVideo(videoId, f);
        }
    }
}
