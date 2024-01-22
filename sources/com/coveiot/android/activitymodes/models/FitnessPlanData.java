package com.coveiot.android.activitymodes.models;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.Serializable;
import java.util.ArrayList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class FitnessPlanData implements Serializable {
    private boolean activitiesMapped;
    @NotNull
    private String backgroundImage;
    @NotNull
    private String categoryChosen;
    private int completedActivities;
    private double completedDistance;
    private int maxProgress;
    private boolean planCompleted;
    @NotNull
    private String planTemplateId;
    private int progress;
    @NotNull
    private String title;
    private int totalActivities;
    private double totalCalories;
    private int totalDistance;
    private int totalSteps;
    @Nullable
    private ArrayList<WeekInfo> weekList;

    public FitnessPlanData(@NotNull String title, boolean z, int i, int i2, double d, int i3, int i4, double d2, int i5, int i6, @NotNull String categoryChosen, boolean z2, @NotNull String backgroundImage, @NotNull String planTemplateId, @Nullable ArrayList<WeekInfo> arrayList) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(categoryChosen, "categoryChosen");
        Intrinsics.checkNotNullParameter(backgroundImage, "backgroundImage");
        Intrinsics.checkNotNullParameter(planTemplateId, "planTemplateId");
        this.title = title;
        this.activitiesMapped = z;
        this.progress = i;
        this.maxProgress = i2;
        this.totalCalories = d;
        this.totalDistance = i3;
        this.totalActivities = i4;
        this.completedDistance = d2;
        this.completedActivities = i5;
        this.totalSteps = i6;
        this.categoryChosen = categoryChosen;
        this.planCompleted = z2;
        this.backgroundImage = backgroundImage;
        this.planTemplateId = planTemplateId;
        this.weekList = arrayList;
    }

    @NotNull
    public final String component1() {
        return this.title;
    }

    public final int component10() {
        return this.totalSteps;
    }

    @NotNull
    public final String component11() {
        return this.categoryChosen;
    }

    public final boolean component12() {
        return this.planCompleted;
    }

    @NotNull
    public final String component13() {
        return this.backgroundImage;
    }

    @NotNull
    public final String component14() {
        return this.planTemplateId;
    }

    @Nullable
    public final ArrayList<WeekInfo> component15() {
        return this.weekList;
    }

    public final boolean component2() {
        return this.activitiesMapped;
    }

    public final int component3() {
        return this.progress;
    }

    public final int component4() {
        return this.maxProgress;
    }

    public final double component5() {
        return this.totalCalories;
    }

    public final int component6() {
        return this.totalDistance;
    }

    public final int component7() {
        return this.totalActivities;
    }

    public final double component8() {
        return this.completedDistance;
    }

    public final int component9() {
        return this.completedActivities;
    }

    @NotNull
    public final FitnessPlanData copy(@NotNull String title, boolean z, int i, int i2, double d, int i3, int i4, double d2, int i5, int i6, @NotNull String categoryChosen, boolean z2, @NotNull String backgroundImage, @NotNull String planTemplateId, @Nullable ArrayList<WeekInfo> arrayList) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(categoryChosen, "categoryChosen");
        Intrinsics.checkNotNullParameter(backgroundImage, "backgroundImage");
        Intrinsics.checkNotNullParameter(planTemplateId, "planTemplateId");
        return new FitnessPlanData(title, z, i, i2, d, i3, i4, d2, i5, i6, categoryChosen, z2, backgroundImage, planTemplateId, arrayList);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof FitnessPlanData) {
            FitnessPlanData fitnessPlanData = (FitnessPlanData) obj;
            return Intrinsics.areEqual(this.title, fitnessPlanData.title) && this.activitiesMapped == fitnessPlanData.activitiesMapped && this.progress == fitnessPlanData.progress && this.maxProgress == fitnessPlanData.maxProgress && Double.compare(this.totalCalories, fitnessPlanData.totalCalories) == 0 && this.totalDistance == fitnessPlanData.totalDistance && this.totalActivities == fitnessPlanData.totalActivities && Double.compare(this.completedDistance, fitnessPlanData.completedDistance) == 0 && this.completedActivities == fitnessPlanData.completedActivities && this.totalSteps == fitnessPlanData.totalSteps && Intrinsics.areEqual(this.categoryChosen, fitnessPlanData.categoryChosen) && this.planCompleted == fitnessPlanData.planCompleted && Intrinsics.areEqual(this.backgroundImage, fitnessPlanData.backgroundImage) && Intrinsics.areEqual(this.planTemplateId, fitnessPlanData.planTemplateId) && Intrinsics.areEqual(this.weekList, fitnessPlanData.weekList);
        }
        return false;
    }

    public final boolean getActivitiesMapped() {
        return this.activitiesMapped;
    }

    @NotNull
    public final String getBackgroundImage() {
        return this.backgroundImage;
    }

    @NotNull
    public final String getCategoryChosen() {
        return this.categoryChosen;
    }

    public final int getCompletedActivities() {
        return this.completedActivities;
    }

    public final double getCompletedDistance() {
        return this.completedDistance;
    }

    public final int getMaxProgress() {
        return this.maxProgress;
    }

    public final boolean getPlanCompleted() {
        return this.planCompleted;
    }

    @NotNull
    public final String getPlanTemplateId() {
        return this.planTemplateId;
    }

    public final int getProgress() {
        return this.progress;
    }

    @NotNull
    public final String getTitle() {
        return this.title;
    }

    public final int getTotalActivities() {
        return this.totalActivities;
    }

    public final double getTotalCalories() {
        return this.totalCalories;
    }

    public final int getTotalDistance() {
        return this.totalDistance;
    }

    public final int getTotalSteps() {
        return this.totalSteps;
    }

    @Nullable
    public final ArrayList<WeekInfo> getWeekList() {
        return this.weekList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int hashCode = this.title.hashCode() * 31;
        boolean z = this.activitiesMapped;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        int hashCode2 = (((((((((((((((((((hashCode + i) * 31) + Integer.hashCode(this.progress)) * 31) + Integer.hashCode(this.maxProgress)) * 31) + Double.hashCode(this.totalCalories)) * 31) + Integer.hashCode(this.totalDistance)) * 31) + Integer.hashCode(this.totalActivities)) * 31) + Double.hashCode(this.completedDistance)) * 31) + Integer.hashCode(this.completedActivities)) * 31) + Integer.hashCode(this.totalSteps)) * 31) + this.categoryChosen.hashCode()) * 31;
        boolean z2 = this.planCompleted;
        int hashCode3 = (((((hashCode2 + (z2 ? 1 : z2 ? 1 : 0)) * 31) + this.backgroundImage.hashCode()) * 31) + this.planTemplateId.hashCode()) * 31;
        ArrayList<WeekInfo> arrayList = this.weekList;
        return hashCode3 + (arrayList == null ? 0 : arrayList.hashCode());
    }

    public final void setActivitiesMapped(boolean z) {
        this.activitiesMapped = z;
    }

    public final void setBackgroundImage(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.backgroundImage = str;
    }

    public final void setCategoryChosen(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.categoryChosen = str;
    }

    public final void setCompletedActivities(int i) {
        this.completedActivities = i;
    }

    public final void setCompletedDistance(double d) {
        this.completedDistance = d;
    }

    public final void setMaxProgress(int i) {
        this.maxProgress = i;
    }

    public final void setPlanCompleted(boolean z) {
        this.planCompleted = z;
    }

    public final void setPlanTemplateId(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.planTemplateId = str;
    }

    public final void setProgress(int i) {
        this.progress = i;
    }

    public final void setTitle(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.title = str;
    }

    public final void setTotalActivities(int i) {
        this.totalActivities = i;
    }

    public final void setTotalCalories(double d) {
        this.totalCalories = d;
    }

    public final void setTotalDistance(int i) {
        this.totalDistance = i;
    }

    public final void setTotalSteps(int i) {
        this.totalSteps = i;
    }

    public final void setWeekList(@Nullable ArrayList<WeekInfo> arrayList) {
        this.weekList = arrayList;
    }

    @NotNull
    public String toString() {
        return "FitnessPlanData(title=" + this.title + ", activitiesMapped=" + this.activitiesMapped + ", progress=" + this.progress + ", maxProgress=" + this.maxProgress + ", totalCalories=" + this.totalCalories + ", totalDistance=" + this.totalDistance + ", totalActivities=" + this.totalActivities + ", completedDistance=" + this.completedDistance + ", completedActivities=" + this.completedActivities + ", totalSteps=" + this.totalSteps + ", categoryChosen=" + this.categoryChosen + ", planCompleted=" + this.planCompleted + ", backgroundImage=" + this.backgroundImage + ", planTemplateId=" + this.planTemplateId + ", weekList=" + this.weekList + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ FitnessPlanData(String str, boolean z, int i, int i2, double d, int i3, int i4, double d2, int i5, int i6, String str2, boolean z2, String str3, String str4, ArrayList arrayList, int i7, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i7 & 2) != 0 ? false : z, i, i2, d, i3, i4, d2, i5, i6, str2, z2, str3, str4, (i7 & 16384) != 0 ? null : arrayList);
    }
}
