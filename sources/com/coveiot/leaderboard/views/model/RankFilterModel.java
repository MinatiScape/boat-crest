package com.coveiot.leaderboard.views.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes9.dex */
public final class RankFilterModel {

    /* renamed from: a  reason: collision with root package name */
    public boolean f7268a;
    @NotNull
    public String b;

    public RankFilterModel(boolean z, @NotNull String filterName) {
        Intrinsics.checkNotNullParameter(filterName, "filterName");
        this.f7268a = z;
        this.b = filterName;
    }

    public static /* synthetic */ RankFilterModel copy$default(RankFilterModel rankFilterModel, boolean z, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            z = rankFilterModel.f7268a;
        }
        if ((i & 2) != 0) {
            str = rankFilterModel.b;
        }
        return rankFilterModel.copy(z, str);
    }

    public final boolean component1() {
        return this.f7268a;
    }

    @NotNull
    public final String component2() {
        return this.b;
    }

    @NotNull
    public final RankFilterModel copy(boolean z, @NotNull String filterName) {
        Intrinsics.checkNotNullParameter(filterName, "filterName");
        return new RankFilterModel(z, filterName);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof RankFilterModel) {
            RankFilterModel rankFilterModel = (RankFilterModel) obj;
            return this.f7268a == rankFilterModel.f7268a && Intrinsics.areEqual(this.b, rankFilterModel.b);
        }
        return false;
    }

    @NotNull
    public final String getFilterName() {
        return this.b;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5 */
    public int hashCode() {
        boolean z = this.f7268a;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        return (r0 * 31) + this.b.hashCode();
    }

    public final boolean isSelected() {
        return this.f7268a;
    }

    public final void setFilterName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.b = str;
    }

    public final void setSelected(boolean z) {
        this.f7268a = z;
    }

    @NotNull
    public String toString() {
        return "RankFilterModel(isSelected=" + this.f7268a + ", filterName=" + this.b + HexStringBuilder.COMMENT_END_CHAR;
    }
}
