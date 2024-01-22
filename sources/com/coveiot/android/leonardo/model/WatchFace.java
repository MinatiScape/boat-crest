package com.coveiot.android.leonardo.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class WatchFace {

    /* renamed from: a  reason: collision with root package name */
    public final int f4863a;
    public final int b;
    @Nullable
    public final Integer c;
    @Nullable
    public final Integer d;

    public WatchFace(int i, int i2, @Nullable Integer num, @Nullable Integer num2) {
        this.f4863a = i;
        this.b = i2;
        this.c = num;
        this.d = num2;
    }

    public static /* synthetic */ WatchFace copy$default(WatchFace watchFace, int i, int i2, Integer num, Integer num2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = watchFace.f4863a;
        }
        if ((i3 & 2) != 0) {
            i2 = watchFace.b;
        }
        if ((i3 & 4) != 0) {
            num = watchFace.c;
        }
        if ((i3 & 8) != 0) {
            num2 = watchFace.d;
        }
        return watchFace.copy(i, i2, num, num2);
    }

    public final int component1() {
        return this.f4863a;
    }

    public final int component2() {
        return this.b;
    }

    @Nullable
    public final Integer component3() {
        return this.c;
    }

    @Nullable
    public final Integer component4() {
        return this.d;
    }

    @NotNull
    public final WatchFace copy(int i, int i2, @Nullable Integer num, @Nullable Integer num2) {
        return new WatchFace(i, i2, num, num2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof WatchFace) {
            WatchFace watchFace = (WatchFace) obj;
            return this.f4863a == watchFace.f4863a && this.b == watchFace.b && Intrinsics.areEqual(this.c, watchFace.c) && Intrinsics.areEqual(this.d, watchFace.d);
        }
        return false;
    }

    public final int getImageRes() {
        return this.f4863a;
    }

    @Nullable
    public final Integer getWatchFaceId() {
        return this.c;
    }

    @Nullable
    public final Integer getWatchFacePos() {
        return this.d;
    }

    public final int getWatchFaceRes() {
        return this.b;
    }

    public int hashCode() {
        int hashCode = ((Integer.hashCode(this.f4863a) * 31) + Integer.hashCode(this.b)) * 31;
        Integer num = this.c;
        int hashCode2 = (hashCode + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.d;
        return hashCode2 + (num2 != null ? num2.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        return "WatchFace(imageRes=" + this.f4863a + ", watchFaceRes=" + this.b + ", watchFaceId=" + this.c + ", watchFacePos=" + this.d + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ WatchFace(int i, int i2, Integer num, Integer num2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2, (i3 & 4) != 0 ? null : num, (i3 & 8) != 0 ? 0 : num2);
    }
}
