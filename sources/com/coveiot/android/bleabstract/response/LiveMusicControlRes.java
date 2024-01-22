package com.coveiot.android.bleabstract.response;

import java.io.Serializable;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class LiveMusicControlRes implements Serializable {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public MusicControlState f3642a;

    public LiveMusicControlRes(@Nullable MusicControlState musicControlState) {
        this.f3642a = musicControlState;
    }

    @Nullable
    public final MusicControlState getState() {
        return this.f3642a;
    }

    public final void setState(@Nullable MusicControlState musicControlState) {
        this.f3642a = musicControlState;
    }
}
