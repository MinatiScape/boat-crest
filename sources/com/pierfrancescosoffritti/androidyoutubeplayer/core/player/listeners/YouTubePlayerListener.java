package com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes9.dex */
public interface YouTubePlayerListener {
    void onApiChange(@NotNull YouTubePlayer youTubePlayer);

    void onCurrentSecond(@NotNull YouTubePlayer youTubePlayer, float f);

    void onError(@NotNull YouTubePlayer youTubePlayer, @NotNull PlayerConstants.PlayerError playerError);

    void onPlaybackQualityChange(@NotNull YouTubePlayer youTubePlayer, @NotNull PlayerConstants.PlaybackQuality playbackQuality);

    void onPlaybackRateChange(@NotNull YouTubePlayer youTubePlayer, @NotNull PlayerConstants.PlaybackRate playbackRate);

    void onReady(@NotNull YouTubePlayer youTubePlayer);

    void onStateChange(@NotNull YouTubePlayer youTubePlayer, @NotNull PlayerConstants.PlayerState playerState);

    void onVideoDuration(@NotNull YouTubePlayer youTubePlayer, float f);

    void onVideoId(@NotNull YouTubePlayer youTubePlayer, @NotNull String str);

    void onVideoLoadedFraction(@NotNull YouTubePlayer youTubePlayer, float f);
}
