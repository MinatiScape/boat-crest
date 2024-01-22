package com.coveiot.android.theme.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class TipsModel {
    private int tipsImage;
    @NotNull
    private String tipsName;

    public TipsModel(int i, @NotNull String tipsName) {
        Intrinsics.checkNotNullParameter(tipsName, "tipsName");
        this.tipsImage = i;
        this.tipsName = tipsName;
    }

    public static /* synthetic */ TipsModel copy$default(TipsModel tipsModel, int i, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = tipsModel.tipsImage;
        }
        if ((i2 & 2) != 0) {
            str = tipsModel.tipsName;
        }
        return tipsModel.copy(i, str);
    }

    public final int component1() {
        return this.tipsImage;
    }

    @NotNull
    public final String component2() {
        return this.tipsName;
    }

    @NotNull
    public final TipsModel copy(int i, @NotNull String tipsName) {
        Intrinsics.checkNotNullParameter(tipsName, "tipsName");
        return new TipsModel(i, tipsName);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof TipsModel) {
            TipsModel tipsModel = (TipsModel) obj;
            return this.tipsImage == tipsModel.tipsImage && Intrinsics.areEqual(this.tipsName, tipsModel.tipsName);
        }
        return false;
    }

    public final int getTipsImage() {
        return this.tipsImage;
    }

    @NotNull
    public final String getTipsName() {
        return this.tipsName;
    }

    public int hashCode() {
        return (Integer.hashCode(this.tipsImage) * 31) + this.tipsName.hashCode();
    }

    public final void setTipsImage(int i) {
        this.tipsImage = i;
    }

    public final void setTipsName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.tipsName = str;
    }

    @NotNull
    public String toString() {
        return "TipsModel(tipsImage=" + this.tipsImage + ", tipsName=" + this.tipsName + HexStringBuilder.COMMENT_END_CHAR;
    }
}
