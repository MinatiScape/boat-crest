package com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners;

import android.view.View;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes9.dex */
public interface FullscreenListener {
    void onEnterFullscreen(@NotNull View view, @NotNull Function0<Unit> function0);

    void onExitFullscreen();
}
