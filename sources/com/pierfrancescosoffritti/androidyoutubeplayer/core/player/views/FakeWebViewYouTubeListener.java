package com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views;

import android.view.View;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.FullscreenListener;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes9.dex */
public final class FakeWebViewYouTubeListener implements FullscreenListener {
    @NotNull
    public static final FakeWebViewYouTubeListener INSTANCE = new FakeWebViewYouTubeListener();

    @Override // com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.FullscreenListener
    public void onEnterFullscreen(@NotNull View fullscreenView, @NotNull Function0<Unit> exitFullscreen) {
        Intrinsics.checkNotNullParameter(fullscreenView, "fullscreenView");
        Intrinsics.checkNotNullParameter(exitFullscreen, "exitFullscreen");
    }

    @Override // com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.FullscreenListener
    public void onExitFullscreen() {
    }
}
