package com.coveiot.android.bleabstract.models;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class WatchFace {

    /* renamed from: a  reason: collision with root package name */
    public final int f3456a;
    @Nullable
    public String b;

    public WatchFace(int i) {
        this.f3456a = i;
    }

    public static /* synthetic */ WatchFace copy$default(WatchFace watchFace, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = watchFace.f3456a;
        }
        return watchFace.copy(i);
    }

    public final int component1() {
        return this.f3456a;
    }

    @NotNull
    public final WatchFace copy(int i) {
        return new WatchFace(i);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof WatchFace) && this.f3456a == ((WatchFace) obj).f3456a;
    }

    public final int getWatchFaceId() {
        return this.f3456a;
    }

    @Nullable
    public final String getWatchFaceName() {
        return this.b;
    }

    public int hashCode() {
        return Integer.hashCode(this.f3456a);
    }

    public final void setWatchFaceName(@Nullable String str) {
        this.b = str;
    }

    @NotNull
    public String toString() {
        return "WatchFace(watchFaceId=" + this.f3456a + HexStringBuilder.COMMENT_END_CHAR;
    }
}
