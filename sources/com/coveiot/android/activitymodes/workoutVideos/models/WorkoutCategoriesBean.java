package com.coveiot.android.activitymodes.workoutVideos.models;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class WorkoutCategoriesBean {
    @Nullable
    private final String categoryId;
    @NotNull
    private final String categoryName;

    public WorkoutCategoriesBean(@Nullable String str, @NotNull String categoryName) {
        Intrinsics.checkNotNullParameter(categoryName, "categoryName");
        this.categoryId = str;
        this.categoryName = categoryName;
    }

    public static /* synthetic */ WorkoutCategoriesBean copy$default(WorkoutCategoriesBean workoutCategoriesBean, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = workoutCategoriesBean.categoryId;
        }
        if ((i & 2) != 0) {
            str2 = workoutCategoriesBean.categoryName;
        }
        return workoutCategoriesBean.copy(str, str2);
    }

    @Nullable
    public final String component1() {
        return this.categoryId;
    }

    @NotNull
    public final String component2() {
        return this.categoryName;
    }

    @NotNull
    public final WorkoutCategoriesBean copy(@Nullable String str, @NotNull String categoryName) {
        Intrinsics.checkNotNullParameter(categoryName, "categoryName");
        return new WorkoutCategoriesBean(str, categoryName);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof WorkoutCategoriesBean) {
            WorkoutCategoriesBean workoutCategoriesBean = (WorkoutCategoriesBean) obj;
            return Intrinsics.areEqual(this.categoryId, workoutCategoriesBean.categoryId) && Intrinsics.areEqual(this.categoryName, workoutCategoriesBean.categoryName);
        }
        return false;
    }

    @Nullable
    public final String getCategoryId() {
        return this.categoryId;
    }

    @NotNull
    public final String getCategoryName() {
        return this.categoryName;
    }

    public int hashCode() {
        String str = this.categoryId;
        return ((str == null ? 0 : str.hashCode()) * 31) + this.categoryName.hashCode();
    }

    @NotNull
    public String toString() {
        return "WorkoutCategoriesBean(categoryId=" + this.categoryId + ", categoryName=" + this.categoryName + HexStringBuilder.COMMENT_END_CHAR;
    }
}
