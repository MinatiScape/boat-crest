package com.coveiot.android.theme.model;

import android.graphics.drawable.Drawable;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class BindingDataModel1 {
    @Nullable
    private Drawable drawable;
    private boolean isVisible;
    @Nullable
    private String subTitle;
    @Nullable
    private String title;

    public BindingDataModel1() {
        this(false, null, null, null, 15, null);
    }

    public BindingDataModel1(boolean z, @Nullable String str, @Nullable String str2, @Nullable Drawable drawable) {
        this.isVisible = z;
        this.title = str;
        this.subTitle = str2;
        this.drawable = drawable;
    }

    public static /* synthetic */ BindingDataModel1 copy$default(BindingDataModel1 bindingDataModel1, boolean z, String str, String str2, Drawable drawable, int i, Object obj) {
        if ((i & 1) != 0) {
            z = bindingDataModel1.isVisible;
        }
        if ((i & 2) != 0) {
            str = bindingDataModel1.title;
        }
        if ((i & 4) != 0) {
            str2 = bindingDataModel1.subTitle;
        }
        if ((i & 8) != 0) {
            drawable = bindingDataModel1.drawable;
        }
        return bindingDataModel1.copy(z, str, str2, drawable);
    }

    public final boolean component1() {
        return this.isVisible;
    }

    @Nullable
    public final String component2() {
        return this.title;
    }

    @Nullable
    public final String component3() {
        return this.subTitle;
    }

    @Nullable
    public final Drawable component4() {
        return this.drawable;
    }

    @NotNull
    public final BindingDataModel1 copy(boolean z, @Nullable String str, @Nullable String str2, @Nullable Drawable drawable) {
        return new BindingDataModel1(z, str, str2, drawable);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BindingDataModel1) {
            BindingDataModel1 bindingDataModel1 = (BindingDataModel1) obj;
            return this.isVisible == bindingDataModel1.isVisible && Intrinsics.areEqual(this.title, bindingDataModel1.title) && Intrinsics.areEqual(this.subTitle, bindingDataModel1.subTitle) && Intrinsics.areEqual(this.drawable, bindingDataModel1.drawable);
        }
        return false;
    }

    @Nullable
    public final Drawable getDrawable() {
        return this.drawable;
    }

    @Nullable
    public final String getSubTitle() {
        return this.subTitle;
    }

    @Nullable
    public final String getTitle() {
        return this.title;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9 */
    public int hashCode() {
        boolean z = this.isVisible;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        int i = r0 * 31;
        String str = this.title;
        int hashCode = (i + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.subTitle;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        Drawable drawable = this.drawable;
        return hashCode2 + (drawable != null ? drawable.hashCode() : 0);
    }

    public final boolean isVisible() {
        return this.isVisible;
    }

    public final void setDrawable(@Nullable Drawable drawable) {
        this.drawable = drawable;
    }

    public final void setSubTitle(@Nullable String str) {
        this.subTitle = str;
    }

    public final void setTitle(@Nullable String str) {
        this.title = str;
    }

    public final void setVisible(boolean z) {
        this.isVisible = z;
    }

    @NotNull
    public String toString() {
        return "BindingDataModel1(isVisible=" + this.isVisible + ", title=" + this.title + ", subTitle=" + this.subTitle + ", drawable=" + this.drawable + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ BindingDataModel1(boolean z, String str, String str2, Drawable drawable, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? true : z, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? null : drawable);
    }
}
