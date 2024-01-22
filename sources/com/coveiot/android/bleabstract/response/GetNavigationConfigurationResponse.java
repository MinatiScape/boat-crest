package com.coveiot.android.bleabstract.response;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class GetNavigationConfigurationResponse {

    /* renamed from: a  reason: collision with root package name */
    public boolean f3614a;
    public boolean b;
    public boolean c;

    public final boolean isAODEnabled() {
        return this.c;
    }

    public final boolean isAudioEnabled() {
        return this.f3614a;
    }

    public final boolean isHapticEnabled() {
        return this.b;
    }

    public final void setAODEnabled(boolean z) {
        this.c = z;
    }

    public final void setAudioEnabled(boolean z) {
        this.f3614a = z;
    }

    public final void setHapticEnabled(boolean z) {
        this.b = z;
    }

    @NotNull
    public String toString() {
        return "(isAudioEnabled=" + this.f3614a + ", isHapticEnabled=" + this.b + ", isAODEnabled=" + this.c + HexStringBuilder.COMMENT_END_CHAR;
    }
}
