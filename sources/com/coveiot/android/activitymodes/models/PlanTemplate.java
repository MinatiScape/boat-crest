package com.coveiot.android.activitymodes.models;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.Serializable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class PlanTemplate implements Serializable {
    @NotNull
    private String categoryId;
    @NotNull
    private String categoryName;
    @NotNull
    private String fullTitle;
    @Nullable
    private Images images;
    @NotNull
    private String planTemplateId;
    @NotNull
    private String shortDesc;
    @NotNull
    private String shortTitle;
    private int sortIndex;
    @NotNull
    private String subTitle;

    public PlanTemplate(@NotNull String planTemplateId, int i, @NotNull String categoryId, @NotNull String shortTitle, @NotNull String fullTitle, @NotNull String subTitle, @NotNull String shortDesc, @Nullable Images images, @NotNull String categoryName) {
        Intrinsics.checkNotNullParameter(planTemplateId, "planTemplateId");
        Intrinsics.checkNotNullParameter(categoryId, "categoryId");
        Intrinsics.checkNotNullParameter(shortTitle, "shortTitle");
        Intrinsics.checkNotNullParameter(fullTitle, "fullTitle");
        Intrinsics.checkNotNullParameter(subTitle, "subTitle");
        Intrinsics.checkNotNullParameter(shortDesc, "shortDesc");
        Intrinsics.checkNotNullParameter(categoryName, "categoryName");
        this.planTemplateId = planTemplateId;
        this.sortIndex = i;
        this.categoryId = categoryId;
        this.shortTitle = shortTitle;
        this.fullTitle = fullTitle;
        this.subTitle = subTitle;
        this.shortDesc = shortDesc;
        this.images = images;
        this.categoryName = categoryName;
    }

    @NotNull
    public final String component1() {
        return this.planTemplateId;
    }

    public final int component2() {
        return this.sortIndex;
    }

    @NotNull
    public final String component3() {
        return this.categoryId;
    }

    @NotNull
    public final String component4() {
        return this.shortTitle;
    }

    @NotNull
    public final String component5() {
        return this.fullTitle;
    }

    @NotNull
    public final String component6() {
        return this.subTitle;
    }

    @NotNull
    public final String component7() {
        return this.shortDesc;
    }

    @Nullable
    public final Images component8() {
        return this.images;
    }

    @NotNull
    public final String component9() {
        return this.categoryName;
    }

    @NotNull
    public final PlanTemplate copy(@NotNull String planTemplateId, int i, @NotNull String categoryId, @NotNull String shortTitle, @NotNull String fullTitle, @NotNull String subTitle, @NotNull String shortDesc, @Nullable Images images, @NotNull String categoryName) {
        Intrinsics.checkNotNullParameter(planTemplateId, "planTemplateId");
        Intrinsics.checkNotNullParameter(categoryId, "categoryId");
        Intrinsics.checkNotNullParameter(shortTitle, "shortTitle");
        Intrinsics.checkNotNullParameter(fullTitle, "fullTitle");
        Intrinsics.checkNotNullParameter(subTitle, "subTitle");
        Intrinsics.checkNotNullParameter(shortDesc, "shortDesc");
        Intrinsics.checkNotNullParameter(categoryName, "categoryName");
        return new PlanTemplate(planTemplateId, i, categoryId, shortTitle, fullTitle, subTitle, shortDesc, images, categoryName);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof PlanTemplate) {
            PlanTemplate planTemplate = (PlanTemplate) obj;
            return Intrinsics.areEqual(this.planTemplateId, planTemplate.planTemplateId) && this.sortIndex == planTemplate.sortIndex && Intrinsics.areEqual(this.categoryId, planTemplate.categoryId) && Intrinsics.areEqual(this.shortTitle, planTemplate.shortTitle) && Intrinsics.areEqual(this.fullTitle, planTemplate.fullTitle) && Intrinsics.areEqual(this.subTitle, planTemplate.subTitle) && Intrinsics.areEqual(this.shortDesc, planTemplate.shortDesc) && Intrinsics.areEqual(this.images, planTemplate.images) && Intrinsics.areEqual(this.categoryName, planTemplate.categoryName);
        }
        return false;
    }

    @NotNull
    public final String getCategoryId() {
        return this.categoryId;
    }

    @NotNull
    public final String getCategoryName() {
        return this.categoryName;
    }

    @NotNull
    public final String getFullTitle() {
        return this.fullTitle;
    }

    @Nullable
    public final Images getImages() {
        return this.images;
    }

    @NotNull
    public final String getPlanTemplateId() {
        return this.planTemplateId;
    }

    @NotNull
    public final String getShortDesc() {
        return this.shortDesc;
    }

    @NotNull
    public final String getShortTitle() {
        return this.shortTitle;
    }

    public final int getSortIndex() {
        return this.sortIndex;
    }

    @NotNull
    public final String getSubTitle() {
        return this.subTitle;
    }

    public int hashCode() {
        int hashCode = ((((((((((((this.planTemplateId.hashCode() * 31) + Integer.hashCode(this.sortIndex)) * 31) + this.categoryId.hashCode()) * 31) + this.shortTitle.hashCode()) * 31) + this.fullTitle.hashCode()) * 31) + this.subTitle.hashCode()) * 31) + this.shortDesc.hashCode()) * 31;
        Images images = this.images;
        return ((hashCode + (images == null ? 0 : images.hashCode())) * 31) + this.categoryName.hashCode();
    }

    public final void setCategoryId(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.categoryId = str;
    }

    public final void setCategoryName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.categoryName = str;
    }

    public final void setFullTitle(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.fullTitle = str;
    }

    public final void setImages(@Nullable Images images) {
        this.images = images;
    }

    public final void setPlanTemplateId(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.planTemplateId = str;
    }

    public final void setShortDesc(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.shortDesc = str;
    }

    public final void setShortTitle(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.shortTitle = str;
    }

    public final void setSortIndex(int i) {
        this.sortIndex = i;
    }

    public final void setSubTitle(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.subTitle = str;
    }

    @NotNull
    public String toString() {
        return "PlanTemplate(planTemplateId=" + this.planTemplateId + ", sortIndex=" + this.sortIndex + ", categoryId=" + this.categoryId + ", shortTitle=" + this.shortTitle + ", fullTitle=" + this.fullTitle + ", subTitle=" + this.subTitle + ", shortDesc=" + this.shortDesc + ", images=" + this.images + ", categoryName=" + this.categoryName + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ PlanTemplate(String str, int i, String str2, String str3, String str4, String str5, String str6, Images images, String str7, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, i, str2, str3, str4, str5, str6, (i2 & 128) != 0 ? null : images, str7);
    }
}
