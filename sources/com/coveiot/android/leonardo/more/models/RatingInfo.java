package com.coveiot.android.leonardo.more.models;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class RatingInfo {

    /* renamed from: a  reason: collision with root package name */
    public final int f5135a;
    @NotNull
    public final String b;

    public RatingInfo(int i, @NotNull String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.f5135a = i;
        this.b = name;
    }

    public static /* synthetic */ RatingInfo copy$default(RatingInfo ratingInfo, int i, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = ratingInfo.f5135a;
        }
        if ((i2 & 2) != 0) {
            str = ratingInfo.b;
        }
        return ratingInfo.copy(i, str);
    }

    public final int component1() {
        return this.f5135a;
    }

    @NotNull
    public final String component2() {
        return this.b;
    }

    @NotNull
    public final RatingInfo copy(int i, @NotNull String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return new RatingInfo(i, name);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof RatingInfo) {
            RatingInfo ratingInfo = (RatingInfo) obj;
            return this.f5135a == ratingInfo.f5135a && Intrinsics.areEqual(this.b, ratingInfo.b);
        }
        return false;
    }

    @NotNull
    public final String getName() {
        return this.b;
    }

    public final int getRating() {
        return this.f5135a;
    }

    public int hashCode() {
        return (Integer.hashCode(this.f5135a) * 31) + this.b.hashCode();
    }

    @NotNull
    public String toString() {
        return "RatingInfo(rating=" + this.f5135a + ", name=" + this.b + HexStringBuilder.COMMENT_END_CHAR;
    }
}
