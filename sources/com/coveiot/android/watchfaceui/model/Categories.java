package com.coveiot.android.watchfaceui.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class Categories {
    @Nullable
    private String categoryId;
    private boolean newCategory;
    @Nullable
    private String title;

    public Categories() {
        this(null, null, false, 7, null);
    }

    public Categories(@Nullable String str, @Nullable String str2, boolean z) {
        this.title = str;
        this.categoryId = str2;
        this.newCategory = z;
    }

    public static /* synthetic */ Categories copy$default(Categories categories, String str, String str2, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = categories.title;
        }
        if ((i & 2) != 0) {
            str2 = categories.categoryId;
        }
        if ((i & 4) != 0) {
            z = categories.newCategory;
        }
        return categories.copy(str, str2, z);
    }

    @Nullable
    public final String component1() {
        return this.title;
    }

    @Nullable
    public final String component2() {
        return this.categoryId;
    }

    public final boolean component3() {
        return this.newCategory;
    }

    @NotNull
    public final Categories copy(@Nullable String str, @Nullable String str2, boolean z) {
        return new Categories(str, str2, z);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Categories) {
            Categories categories = (Categories) obj;
            return Intrinsics.areEqual(this.title, categories.title) && Intrinsics.areEqual(this.categoryId, categories.categoryId) && this.newCategory == categories.newCategory;
        }
        return false;
    }

    @Nullable
    public final String getCategoryId() {
        return this.categoryId;
    }

    public final boolean getNewCategory() {
        return this.newCategory;
    }

    @Nullable
    public final String getTitle() {
        return this.title;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        String str = this.title;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.categoryId;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        boolean z = this.newCategory;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return hashCode2 + i;
    }

    public final void setCategoryId(@Nullable String str) {
        this.categoryId = str;
    }

    public final void setNewCategory(boolean z) {
        this.newCategory = z;
    }

    public final void setTitle(@Nullable String str) {
        this.title = str;
    }

    @NotNull
    public String toString() {
        return "Categories(title=" + this.title + ", categoryId=" + this.categoryId + ", newCategory=" + this.newCategory + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ Categories(String str, String str2, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? false : z);
    }
}
