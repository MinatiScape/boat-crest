package com.coveiot.android.leonardo.dashboard.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class InsightsStepsDataModel {

    /* renamed from: a  reason: collision with root package name */
    public boolean f4788a;
    public int b;
    public double c;
    public boolean d;
    @NotNull
    public String e;
    @NotNull
    public String f;
    @NotNull
    public String g;
    @NotNull
    public String h;

    public InsightsStepsDataModel(boolean z, int i, double d, boolean z2, @NotNull String header, @NotNull String subValue, @NotNull String subValueInfo, @NotNull String info) {
        Intrinsics.checkNotNullParameter(header, "header");
        Intrinsics.checkNotNullParameter(subValue, "subValue");
        Intrinsics.checkNotNullParameter(subValueInfo, "subValueInfo");
        Intrinsics.checkNotNullParameter(info, "info");
        this.f4788a = z;
        this.b = i;
        this.c = d;
        this.d = z2;
        this.e = header;
        this.f = subValue;
        this.g = subValueInfo;
        this.h = info;
    }

    public final boolean component1() {
        return this.f4788a;
    }

    public final int component2() {
        return this.b;
    }

    public final double component3() {
        return this.c;
    }

    public final boolean component4() {
        return this.d;
    }

    @NotNull
    public final String component5() {
        return this.e;
    }

    @NotNull
    public final String component6() {
        return this.f;
    }

    @NotNull
    public final String component7() {
        return this.g;
    }

    @NotNull
    public final String component8() {
        return this.h;
    }

    @NotNull
    public final InsightsStepsDataModel copy(boolean z, int i, double d, boolean z2, @NotNull String header, @NotNull String subValue, @NotNull String subValueInfo, @NotNull String info) {
        Intrinsics.checkNotNullParameter(header, "header");
        Intrinsics.checkNotNullParameter(subValue, "subValue");
        Intrinsics.checkNotNullParameter(subValueInfo, "subValueInfo");
        Intrinsics.checkNotNullParameter(info, "info");
        return new InsightsStepsDataModel(z, i, d, z2, header, subValue, subValueInfo, info);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof InsightsStepsDataModel) {
            InsightsStepsDataModel insightsStepsDataModel = (InsightsStepsDataModel) obj;
            return this.f4788a == insightsStepsDataModel.f4788a && this.b == insightsStepsDataModel.b && Double.compare(this.c, insightsStepsDataModel.c) == 0 && this.d == insightsStepsDataModel.d && Intrinsics.areEqual(this.e, insightsStepsDataModel.e) && Intrinsics.areEqual(this.f, insightsStepsDataModel.f) && Intrinsics.areEqual(this.g, insightsStepsDataModel.g) && Intrinsics.areEqual(this.h, insightsStepsDataModel.h);
        }
        return false;
    }

    @NotNull
    public final String getHeader() {
        return this.e;
    }

    @NotNull
    public final String getInfo() {
        return this.h;
    }

    public final double getMainValue() {
        return this.c;
    }

    public final int getSelectedType() {
        return this.b;
    }

    @NotNull
    public final String getSubValue() {
        return this.f;
    }

    @NotNull
    public final String getSubValueInfo() {
        return this.g;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v16 */
    /* JADX WARN: Type inference failed for: r0v17 */
    public int hashCode() {
        boolean z = this.f4788a;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        int hashCode = ((((r0 * 31) + Integer.hashCode(this.b)) * 31) + Double.hashCode(this.c)) * 31;
        boolean z2 = this.d;
        return ((((((((hashCode + (z2 ? 1 : z2 ? 1 : 0)) * 31) + this.e.hashCode()) * 31) + this.f.hashCode()) * 31) + this.g.hashCode()) * 31) + this.h.hashCode();
    }

    public final boolean isStepData() {
        return this.f4788a;
    }

    public final boolean isValueDecreased() {
        return this.d;
    }

    public final void setHeader(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.e = str;
    }

    public final void setInfo(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.h = str;
    }

    public final void setMainValue(double d) {
        this.c = d;
    }

    public final void setSelectedType(int i) {
        this.b = i;
    }

    public final void setStepData(boolean z) {
        this.f4788a = z;
    }

    public final void setSubValue(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.f = str;
    }

    public final void setSubValueInfo(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.g = str;
    }

    public final void setValueDecreased(boolean z) {
        this.d = z;
    }

    @NotNull
    public String toString() {
        return "InsightsStepsDataModel(isStepData=" + this.f4788a + ", selectedType=" + this.b + ", mainValue=" + this.c + ", isValueDecreased=" + this.d + ", header=" + this.e + ", subValue=" + this.f + ", subValueInfo=" + this.g + ", info=" + this.h + HexStringBuilder.COMMENT_END_CHAR;
    }
}
