package com.coveiot.android.leonardo.dashboard.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class InsightsStepsDetailsModel {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public Double f4789a;
    @Nullable
    public Integer b;
    @Nullable
    public String c;
    @Nullable
    public Double d;
    @Nullable
    public Double e;
    @Nullable
    public String f;
    @Nullable
    public Integer g;
    @Nullable
    public Double h;
    @Nullable
    public String i;
    @Nullable
    public String j;
    @Nullable
    public Boolean k;
    @Nullable
    public Boolean l;
    @Nullable
    public Boolean m;

    public InsightsStepsDetailsModel() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, 8191, null);
    }

    public InsightsStepsDetailsModel(@Nullable Double d, @Nullable Integer num, @Nullable String str, @Nullable Double d2, @Nullable Double d3, @Nullable String str2, @Nullable Integer num2, @Nullable Double d4, @Nullable String str3, @Nullable String str4, @Nullable Boolean bool, @Nullable Boolean bool2, @Nullable Boolean bool3) {
        this.f4789a = d;
        this.b = num;
        this.c = str;
        this.d = d2;
        this.e = d3;
        this.f = str2;
        this.g = num2;
        this.h = d4;
        this.i = str3;
        this.j = str4;
        this.k = bool;
        this.l = bool2;
        this.m = bool3;
    }

    @Nullable
    public final Double component1() {
        return this.f4789a;
    }

    @Nullable
    public final String component10() {
        return this.j;
    }

    @Nullable
    public final Boolean component11() {
        return this.k;
    }

    @Nullable
    public final Boolean component12() {
        return this.l;
    }

    @Nullable
    public final Boolean component13() {
        return this.m;
    }

    @Nullable
    public final Integer component2() {
        return this.b;
    }

    @Nullable
    public final String component3() {
        return this.c;
    }

    @Nullable
    public final Double component4() {
        return this.d;
    }

    @Nullable
    public final Double component5() {
        return this.e;
    }

    @Nullable
    public final String component6() {
        return this.f;
    }

    @Nullable
    public final Integer component7() {
        return this.g;
    }

    @Nullable
    public final Double component8() {
        return this.h;
    }

    @Nullable
    public final String component9() {
        return this.i;
    }

    @NotNull
    public final InsightsStepsDetailsModel copy(@Nullable Double d, @Nullable Integer num, @Nullable String str, @Nullable Double d2, @Nullable Double d3, @Nullable String str2, @Nullable Integer num2, @Nullable Double d4, @Nullable String str3, @Nullable String str4, @Nullable Boolean bool, @Nullable Boolean bool2, @Nullable Boolean bool3) {
        return new InsightsStepsDetailsModel(d, num, str, d2, d3, str2, num2, d4, str3, str4, bool, bool2, bool3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof InsightsStepsDetailsModel) {
            InsightsStepsDetailsModel insightsStepsDetailsModel = (InsightsStepsDetailsModel) obj;
            return Intrinsics.areEqual((Object) this.f4789a, (Object) insightsStepsDetailsModel.f4789a) && Intrinsics.areEqual(this.b, insightsStepsDetailsModel.b) && Intrinsics.areEqual(this.c, insightsStepsDetailsModel.c) && Intrinsics.areEqual((Object) this.d, (Object) insightsStepsDetailsModel.d) && Intrinsics.areEqual((Object) this.e, (Object) insightsStepsDetailsModel.e) && Intrinsics.areEqual(this.f, insightsStepsDetailsModel.f) && Intrinsics.areEqual(this.g, insightsStepsDetailsModel.g) && Intrinsics.areEqual((Object) this.h, (Object) insightsStepsDetailsModel.h) && Intrinsics.areEqual(this.i, insightsStepsDetailsModel.i) && Intrinsics.areEqual(this.j, insightsStepsDetailsModel.j) && Intrinsics.areEqual(this.k, insightsStepsDetailsModel.k) && Intrinsics.areEqual(this.l, insightsStepsDetailsModel.l) && Intrinsics.areEqual(this.m, insightsStepsDetailsModel.m);
        }
        return false;
    }

    @Nullable
    public final Double getCalories() {
        return this.e;
    }

    @Nullable
    public final String getCaloriesBurnt() {
        return this.f;
    }

    @Nullable
    public final Double getCaloriesPercentage() {
        return this.d;
    }

    @Nullable
    public final Double getDistancePercentage() {
        return this.f4789a;
    }

    @Nullable
    public final String getDistanceWalked() {
        return this.c;
    }

    @Nullable
    public final Integer getDistanceWalkedMeters() {
        return this.b;
    }

    @Nullable
    public final String getHours() {
        return this.j;
    }

    @Nullable
    public final Integer getSteps() {
        return this.g;
    }

    @Nullable
    public final Double getStepsPercentage() {
        return this.h;
    }

    @Nullable
    public final String getStepsWalked() {
        return this.i;
    }

    public int hashCode() {
        Double d = this.f4789a;
        int hashCode = (d == null ? 0 : d.hashCode()) * 31;
        Integer num = this.b;
        int hashCode2 = (hashCode + (num == null ? 0 : num.hashCode())) * 31;
        String str = this.c;
        int hashCode3 = (hashCode2 + (str == null ? 0 : str.hashCode())) * 31;
        Double d2 = this.d;
        int hashCode4 = (hashCode3 + (d2 == null ? 0 : d2.hashCode())) * 31;
        Double d3 = this.e;
        int hashCode5 = (hashCode4 + (d3 == null ? 0 : d3.hashCode())) * 31;
        String str2 = this.f;
        int hashCode6 = (hashCode5 + (str2 == null ? 0 : str2.hashCode())) * 31;
        Integer num2 = this.g;
        int hashCode7 = (hashCode6 + (num2 == null ? 0 : num2.hashCode())) * 31;
        Double d4 = this.h;
        int hashCode8 = (hashCode7 + (d4 == null ? 0 : d4.hashCode())) * 31;
        String str3 = this.i;
        int hashCode9 = (hashCode8 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.j;
        int hashCode10 = (hashCode9 + (str4 == null ? 0 : str4.hashCode())) * 31;
        Boolean bool = this.k;
        int hashCode11 = (hashCode10 + (bool == null ? 0 : bool.hashCode())) * 31;
        Boolean bool2 = this.l;
        int hashCode12 = (hashCode11 + (bool2 == null ? 0 : bool2.hashCode())) * 31;
        Boolean bool3 = this.m;
        return hashCode12 + (bool3 != null ? bool3.hashCode() : 0);
    }

    @Nullable
    public final Boolean isCaloriesDecreased() {
        return this.m;
    }

    @Nullable
    public final Boolean isDistanceDecreased() {
        return this.l;
    }

    @Nullable
    public final Boolean isStepsDecreased() {
        return this.k;
    }

    public final void setCalories(@Nullable Double d) {
        this.e = d;
    }

    public final void setCaloriesBurnt(@Nullable String str) {
        this.f = str;
    }

    public final void setCaloriesDecreased(@Nullable Boolean bool) {
        this.m = bool;
    }

    public final void setCaloriesPercentage(@Nullable Double d) {
        this.d = d;
    }

    public final void setDistanceDecreased(@Nullable Boolean bool) {
        this.l = bool;
    }

    public final void setDistancePercentage(@Nullable Double d) {
        this.f4789a = d;
    }

    public final void setDistanceWalked(@Nullable String str) {
        this.c = str;
    }

    public final void setDistanceWalkedMeters(@Nullable Integer num) {
        this.b = num;
    }

    public final void setHours(@Nullable String str) {
        this.j = str;
    }

    public final void setSteps(@Nullable Integer num) {
        this.g = num;
    }

    public final void setStepsDecreased(@Nullable Boolean bool) {
        this.k = bool;
    }

    public final void setStepsPercentage(@Nullable Double d) {
        this.h = d;
    }

    public final void setStepsWalked(@Nullable String str) {
        this.i = str;
    }

    @NotNull
    public String toString() {
        return "InsightsStepsDetailsModel(distancePercentage=" + this.f4789a + ", distanceWalkedMeters=" + this.b + ", distanceWalked=" + this.c + ", caloriesPercentage=" + this.d + ", calories=" + this.e + ", caloriesBurnt=" + this.f + ", steps=" + this.g + ", stepsPercentage=" + this.h + ", stepsWalked=" + this.i + ", hours=" + this.j + ", isStepsDecreased=" + this.k + ", isDistanceDecreased=" + this.l + ", isCaloriesDecreased=" + this.m + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ InsightsStepsDetailsModel(Double d, Integer num, String str, Double d2, Double d3, String str2, Integer num2, Double d4, String str3, String str4, Boolean bool, Boolean bool2, Boolean bool3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : d, (i & 2) != 0 ? null : num, (i & 4) != 0 ? null : str, (i & 8) != 0 ? null : d2, (i & 16) != 0 ? null : d3, (i & 32) != 0 ? null : str2, (i & 64) != 0 ? null : num2, (i & 128) != 0 ? null : d4, (i & 256) != 0 ? null : str3, (i & 512) != 0 ? null : str4, (i & 1024) != 0 ? null : bool, (i & 2048) != 0 ? null : bool2, (i & 4096) == 0 ? bool3 : null);
    }
}
