package com.coveiot.android.leonardo.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class VolumeControlEvent {

    /* renamed from: a  reason: collision with root package name */
    public final int f4862a;
    public final int b;

    public VolumeControlEvent(int i, int i2) {
        this.f4862a = i;
        this.b = i2;
    }

    public static /* synthetic */ VolumeControlEvent copy$default(VolumeControlEvent volumeControlEvent, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = volumeControlEvent.f4862a;
        }
        if ((i3 & 2) != 0) {
            i2 = volumeControlEvent.b;
        }
        return volumeControlEvent.copy(i, i2);
    }

    public final int component1() {
        return this.f4862a;
    }

    public final int component2() {
        return this.b;
    }

    @NotNull
    public final VolumeControlEvent copy(int i, int i2) {
        return new VolumeControlEvent(i, i2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof VolumeControlEvent) {
            VolumeControlEvent volumeControlEvent = (VolumeControlEvent) obj;
            return this.f4862a == volumeControlEvent.f4862a && this.b == volumeControlEvent.b;
        }
        return false;
    }

    public final int getCurrentVolume() {
        return this.b;
    }

    public final int getMaxVolume() {
        return this.f4862a;
    }

    public int hashCode() {
        return (Integer.hashCode(this.f4862a) * 31) + Integer.hashCode(this.b);
    }

    @NotNull
    public String toString() {
        return "VolumeControlEvent(maxVolume=" + this.f4862a + ", currentVolume=" + this.b + HexStringBuilder.COMMENT_END_CHAR;
    }
}
