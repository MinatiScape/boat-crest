package com.pierfrancescosoffritti.androidyoutubeplayer.core.player;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes9.dex */
public interface YouTubePlayer {
    boolean addListener(@NotNull YouTubePlayerListener youTubePlayerListener);

    void cueVideo(@NotNull String str, float f);

    void loadVideo(@NotNull String str, float f);

    void mute();

    void pause();

    void play();

    boolean removeListener(@NotNull YouTubePlayerListener youTubePlayerListener);

    void seekTo(float f);

    void setPlaybackRate(@NotNull PlayerConstants.PlaybackRate playbackRate);

    void setVolume(int i);

    void toggleFullscreen();

    void unMute();
}
