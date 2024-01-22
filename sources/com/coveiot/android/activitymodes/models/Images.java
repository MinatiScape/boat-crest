package com.coveiot.android.activitymodes.models;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.Serializable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class Images implements Serializable {
    @Nullable
    private String background1;
    @Nullable
    private String thumbnail1;

    public Images() {
        this(null, null, 3, null);
    }

    public Images(@Nullable String str, @Nullable String str2) {
        this.background1 = str;
        this.thumbnail1 = str2;
    }

    public static /* synthetic */ Images copy$default(Images images, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = images.background1;
        }
        if ((i & 2) != 0) {
            str2 = images.thumbnail1;
        }
        return images.copy(str, str2);
    }

    @Nullable
    public final String component1() {
        return this.background1;
    }

    @Nullable
    public final String component2() {
        return this.thumbnail1;
    }

    @NotNull
    public final Images copy(@Nullable String str, @Nullable String str2) {
        return new Images(str, str2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Images) {
            Images images = (Images) obj;
            return Intrinsics.areEqual(this.background1, images.background1) && Intrinsics.areEqual(this.thumbnail1, images.thumbnail1);
        }
        return false;
    }

    @Nullable
    public final String getBackground1() {
        return this.background1;
    }

    @Nullable
    public final String getThumbnail1() {
        return this.thumbnail1;
    }

    public int hashCode() {
        String str = this.background1;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.thumbnail1;
        return hashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public final void setBackground1(@Nullable String str) {
        this.background1 = str;
    }

    public final void setThumbnail1(@Nullable String str) {
        this.thumbnail1 = str;
    }

    @NotNull
    public String toString() {
        return "Images(background1=" + this.background1 + ", thumbnail1=" + this.thumbnail1 + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ Images(String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2);
    }
}
