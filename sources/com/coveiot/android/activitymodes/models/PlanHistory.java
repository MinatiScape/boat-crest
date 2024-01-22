package com.coveiot.android.activitymodes.models;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.Serializable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class PlanHistory implements Serializable {
    @NotNull
    private String activationDate;
    @NotNull
    private String categoryChosen;
    @NotNull
    private String createdDate;
    @NotNull
    private String deactivationDate;
    @NotNull
    private String endDate;
    @NotNull
    private String lastModifiedDate;
    @Nullable
    private PlanTemplate planTemplate;
    private int progress;
    @NotNull
    private String progressStatus;
    @NotNull
    private String startAndEndDate;
    private int totalCalories;
    private int totalDistance;
    private int totalSteps;
    @NotNull
    private String userPlanId;

    public PlanHistory(@NotNull String userPlanId, @NotNull String activationDate, @NotNull String deactivationDate, @NotNull String endDate, @NotNull String startAndEndDate, @NotNull String categoryChosen, int i, @NotNull String progressStatus, int i2, int i3, int i4, @NotNull String createdDate, @NotNull String lastModifiedDate, @Nullable PlanTemplate planTemplate) {
        Intrinsics.checkNotNullParameter(userPlanId, "userPlanId");
        Intrinsics.checkNotNullParameter(activationDate, "activationDate");
        Intrinsics.checkNotNullParameter(deactivationDate, "deactivationDate");
        Intrinsics.checkNotNullParameter(endDate, "endDate");
        Intrinsics.checkNotNullParameter(startAndEndDate, "startAndEndDate");
        Intrinsics.checkNotNullParameter(categoryChosen, "categoryChosen");
        Intrinsics.checkNotNullParameter(progressStatus, "progressStatus");
        Intrinsics.checkNotNullParameter(createdDate, "createdDate");
        Intrinsics.checkNotNullParameter(lastModifiedDate, "lastModifiedDate");
        this.userPlanId = userPlanId;
        this.activationDate = activationDate;
        this.deactivationDate = deactivationDate;
        this.endDate = endDate;
        this.startAndEndDate = startAndEndDate;
        this.categoryChosen = categoryChosen;
        this.progress = i;
        this.progressStatus = progressStatus;
        this.totalCalories = i2;
        this.totalDistance = i3;
        this.totalSteps = i4;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.planTemplate = planTemplate;
    }

    @NotNull
    public final String component1() {
        return this.userPlanId;
    }

    public final int component10() {
        return this.totalDistance;
    }

    public final int component11() {
        return this.totalSteps;
    }

    @NotNull
    public final String component12() {
        return this.createdDate;
    }

    @NotNull
    public final String component13() {
        return this.lastModifiedDate;
    }

    @Nullable
    public final PlanTemplate component14() {
        return this.planTemplate;
    }

    @NotNull
    public final String component2() {
        return this.activationDate;
    }

    @NotNull
    public final String component3() {
        return this.deactivationDate;
    }

    @NotNull
    public final String component4() {
        return this.endDate;
    }

    @NotNull
    public final String component5() {
        return this.startAndEndDate;
    }

    @NotNull
    public final String component6() {
        return this.categoryChosen;
    }

    public final int component7() {
        return this.progress;
    }

    @NotNull
    public final String component8() {
        return this.progressStatus;
    }

    public final int component9() {
        return this.totalCalories;
    }

    @NotNull
    public final PlanHistory copy(@NotNull String userPlanId, @NotNull String activationDate, @NotNull String deactivationDate, @NotNull String endDate, @NotNull String startAndEndDate, @NotNull String categoryChosen, int i, @NotNull String progressStatus, int i2, int i3, int i4, @NotNull String createdDate, @NotNull String lastModifiedDate, @Nullable PlanTemplate planTemplate) {
        Intrinsics.checkNotNullParameter(userPlanId, "userPlanId");
        Intrinsics.checkNotNullParameter(activationDate, "activationDate");
        Intrinsics.checkNotNullParameter(deactivationDate, "deactivationDate");
        Intrinsics.checkNotNullParameter(endDate, "endDate");
        Intrinsics.checkNotNullParameter(startAndEndDate, "startAndEndDate");
        Intrinsics.checkNotNullParameter(categoryChosen, "categoryChosen");
        Intrinsics.checkNotNullParameter(progressStatus, "progressStatus");
        Intrinsics.checkNotNullParameter(createdDate, "createdDate");
        Intrinsics.checkNotNullParameter(lastModifiedDate, "lastModifiedDate");
        return new PlanHistory(userPlanId, activationDate, deactivationDate, endDate, startAndEndDate, categoryChosen, i, progressStatus, i2, i3, i4, createdDate, lastModifiedDate, planTemplate);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof PlanHistory) {
            PlanHistory planHistory = (PlanHistory) obj;
            return Intrinsics.areEqual(this.userPlanId, planHistory.userPlanId) && Intrinsics.areEqual(this.activationDate, planHistory.activationDate) && Intrinsics.areEqual(this.deactivationDate, planHistory.deactivationDate) && Intrinsics.areEqual(this.endDate, planHistory.endDate) && Intrinsics.areEqual(this.startAndEndDate, planHistory.startAndEndDate) && Intrinsics.areEqual(this.categoryChosen, planHistory.categoryChosen) && this.progress == planHistory.progress && Intrinsics.areEqual(this.progressStatus, planHistory.progressStatus) && this.totalCalories == planHistory.totalCalories && this.totalDistance == planHistory.totalDistance && this.totalSteps == planHistory.totalSteps && Intrinsics.areEqual(this.createdDate, planHistory.createdDate) && Intrinsics.areEqual(this.lastModifiedDate, planHistory.lastModifiedDate) && Intrinsics.areEqual(this.planTemplate, planHistory.planTemplate);
        }
        return false;
    }

    @NotNull
    public final String getActivationDate() {
        return this.activationDate;
    }

    @NotNull
    public final String getCategoryChosen() {
        return this.categoryChosen;
    }

    @NotNull
    public final String getCreatedDate() {
        return this.createdDate;
    }

    @NotNull
    public final String getDeactivationDate() {
        return this.deactivationDate;
    }

    @NotNull
    public final String getEndDate() {
        return this.endDate;
    }

    @NotNull
    public final String getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    @Nullable
    public final PlanTemplate getPlanTemplate() {
        return this.planTemplate;
    }

    public final int getProgress() {
        return this.progress;
    }

    @NotNull
    public final String getProgressStatus() {
        return this.progressStatus;
    }

    @NotNull
    public final String getStartAndEndDate() {
        return this.startAndEndDate;
    }

    public final int getTotalCalories() {
        return this.totalCalories;
    }

    public final int getTotalDistance() {
        return this.totalDistance;
    }

    public final int getTotalSteps() {
        return this.totalSteps;
    }

    @NotNull
    public final String getUserPlanId() {
        return this.userPlanId;
    }

    public int hashCode() {
        int hashCode = ((((((((((((((((((((((((this.userPlanId.hashCode() * 31) + this.activationDate.hashCode()) * 31) + this.deactivationDate.hashCode()) * 31) + this.endDate.hashCode()) * 31) + this.startAndEndDate.hashCode()) * 31) + this.categoryChosen.hashCode()) * 31) + Integer.hashCode(this.progress)) * 31) + this.progressStatus.hashCode()) * 31) + Integer.hashCode(this.totalCalories)) * 31) + Integer.hashCode(this.totalDistance)) * 31) + Integer.hashCode(this.totalSteps)) * 31) + this.createdDate.hashCode()) * 31) + this.lastModifiedDate.hashCode()) * 31;
        PlanTemplate planTemplate = this.planTemplate;
        return hashCode + (planTemplate == null ? 0 : planTemplate.hashCode());
    }

    public final void setActivationDate(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.activationDate = str;
    }

    public final void setCategoryChosen(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.categoryChosen = str;
    }

    public final void setCreatedDate(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.createdDate = str;
    }

    public final void setDeactivationDate(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.deactivationDate = str;
    }

    public final void setEndDate(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.endDate = str;
    }

    public final void setLastModifiedDate(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.lastModifiedDate = str;
    }

    public final void setPlanTemplate(@Nullable PlanTemplate planTemplate) {
        this.planTemplate = planTemplate;
    }

    public final void setProgress(int i) {
        this.progress = i;
    }

    public final void setProgressStatus(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.progressStatus = str;
    }

    public final void setStartAndEndDate(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.startAndEndDate = str;
    }

    public final void setTotalCalories(int i) {
        this.totalCalories = i;
    }

    public final void setTotalDistance(int i) {
        this.totalDistance = i;
    }

    public final void setTotalSteps(int i) {
        this.totalSteps = i;
    }

    public final void setUserPlanId(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.userPlanId = str;
    }

    @NotNull
    public String toString() {
        return "PlanHistory(userPlanId=" + this.userPlanId + ", activationDate=" + this.activationDate + ", deactivationDate=" + this.deactivationDate + ", endDate=" + this.endDate + ", startAndEndDate=" + this.startAndEndDate + ", categoryChosen=" + this.categoryChosen + ", progress=" + this.progress + ", progressStatus=" + this.progressStatus + ", totalCalories=" + this.totalCalories + ", totalDistance=" + this.totalDistance + ", totalSteps=" + this.totalSteps + ", createdDate=" + this.createdDate + ", lastModifiedDate=" + this.lastModifiedDate + ", planTemplate=" + this.planTemplate + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ PlanHistory(String str, String str2, String str3, String str4, String str5, String str6, int i, String str7, int i2, int i3, int i4, String str8, String str9, PlanTemplate planTemplate, int i5, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, str5, str6, i, str7, i2, i3, i4, str8, str9, (i5 & 8192) != 0 ? null : planTemplate);
    }
}
