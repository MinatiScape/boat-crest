package com.coveiot.android.activitymodes.database.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.mappls.sdk.navigation.util.SavingTrackHelper;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@Entity(tableName = "entity_preparation_plan")
/* loaded from: classes2.dex */
public final class EntityPreparationPlan {
    public String category;
    public String fulltitle;
    public String imageUrl;
    public List<String> images;
    public List<String> introText;
    @NonNull
    @PrimaryKey
    public String planId;
    public String shortDesc;
    public String shortTitle;
    public String startDate;
    public String subtitle;
    public String userPlanId;

    @NotNull
    public final String getCategory() {
        String str = this.category;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException(SavingTrackHelper.POINT_COL_CATEGORY);
        return null;
    }

    @NotNull
    public final String getFulltitle() {
        String str = this.fulltitle;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException("fulltitle");
        return null;
    }

    @NotNull
    public final String getImageUrl() {
        String str = this.imageUrl;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException("imageUrl");
        return null;
    }

    @NotNull
    public final List<String> getImages() {
        List<String> list = this.images;
        if (list != null) {
            return list;
        }
        Intrinsics.throwUninitializedPropertyAccessException("images");
        return null;
    }

    @NotNull
    public final List<String> getIntroText() {
        List<String> list = this.introText;
        if (list != null) {
            return list;
        }
        Intrinsics.throwUninitializedPropertyAccessException("introText");
        return null;
    }

    @NotNull
    public final String getPlanId() {
        String str = this.planId;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException("planId");
        return null;
    }

    @NotNull
    public final String getShortDesc() {
        String str = this.shortDesc;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException("shortDesc");
        return null;
    }

    @NotNull
    public final String getShortTitle() {
        String str = this.shortTitle;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException("shortTitle");
        return null;
    }

    @NotNull
    public final String getStartDate() {
        String str = this.startDate;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException("startDate");
        return null;
    }

    @NotNull
    public final String getSubtitle() {
        String str = this.subtitle;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException("subtitle");
        return null;
    }

    @NotNull
    public final String getUserPlanId() {
        String str = this.userPlanId;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException("userPlanId");
        return null;
    }

    public final void setCategory(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.category = str;
    }

    public final void setFulltitle(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.fulltitle = str;
    }

    public final void setImageUrl(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.imageUrl = str;
    }

    public final void setImages(@NotNull List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.images = list;
    }

    public final void setIntroText(@NotNull List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.introText = list;
    }

    public final void setPlanId(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.planId = str;
    }

    public final void setShortDesc(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.shortDesc = str;
    }

    public final void setShortTitle(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.shortTitle = str;
    }

    public final void setStartDate(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.startDate = str;
    }

    public final void setSubtitle(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.subtitle = str;
    }

    public final void setUserPlanId(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.userPlanId = str;
    }
}
