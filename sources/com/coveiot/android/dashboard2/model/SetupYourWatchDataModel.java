package com.coveiot.android.dashboard2.model;

import android.graphics.drawable.Drawable;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class SetupYourWatchDataModel {
    @Nullable
    private Drawable backgroundDrawable;
    @Nullable
    private Drawable drawable;
    @NotNull
    private String headerTextCenter;
    @NotNull
    private String headerTextEnd;
    @NotNull
    private String infoText;
    @Nullable
    private Drawable infoTextRightDrawable;
    @NotNull
    private String option;

    public SetupYourWatchDataModel() {
        this(null, null, null, null, null, null, null, 127, null);
    }

    public SetupYourWatchDataModel(@NotNull String option, @NotNull String headerTextCenter, @NotNull String headerTextEnd, @NotNull String infoText, @Nullable Drawable drawable, @Nullable Drawable drawable2, @Nullable Drawable drawable3) {
        Intrinsics.checkNotNullParameter(option, "option");
        Intrinsics.checkNotNullParameter(headerTextCenter, "headerTextCenter");
        Intrinsics.checkNotNullParameter(headerTextEnd, "headerTextEnd");
        Intrinsics.checkNotNullParameter(infoText, "infoText");
        this.option = option;
        this.headerTextCenter = headerTextCenter;
        this.headerTextEnd = headerTextEnd;
        this.infoText = infoText;
        this.drawable = drawable;
        this.infoTextRightDrawable = drawable2;
        this.backgroundDrawable = drawable3;
    }

    public static /* synthetic */ SetupYourWatchDataModel copy$default(SetupYourWatchDataModel setupYourWatchDataModel, String str, String str2, String str3, String str4, Drawable drawable, Drawable drawable2, Drawable drawable3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = setupYourWatchDataModel.option;
        }
        if ((i & 2) != 0) {
            str2 = setupYourWatchDataModel.headerTextCenter;
        }
        String str5 = str2;
        if ((i & 4) != 0) {
            str3 = setupYourWatchDataModel.headerTextEnd;
        }
        String str6 = str3;
        if ((i & 8) != 0) {
            str4 = setupYourWatchDataModel.infoText;
        }
        String str7 = str4;
        if ((i & 16) != 0) {
            drawable = setupYourWatchDataModel.drawable;
        }
        Drawable drawable4 = drawable;
        if ((i & 32) != 0) {
            drawable2 = setupYourWatchDataModel.infoTextRightDrawable;
        }
        Drawable drawable5 = drawable2;
        if ((i & 64) != 0) {
            drawable3 = setupYourWatchDataModel.backgroundDrawable;
        }
        return setupYourWatchDataModel.copy(str, str5, str6, str7, drawable4, drawable5, drawable3);
    }

    @NotNull
    public final String component1() {
        return this.option;
    }

    @NotNull
    public final String component2() {
        return this.headerTextCenter;
    }

    @NotNull
    public final String component3() {
        return this.headerTextEnd;
    }

    @NotNull
    public final String component4() {
        return this.infoText;
    }

    @Nullable
    public final Drawable component5() {
        return this.drawable;
    }

    @Nullable
    public final Drawable component6() {
        return this.infoTextRightDrawable;
    }

    @Nullable
    public final Drawable component7() {
        return this.backgroundDrawable;
    }

    @NotNull
    public final SetupYourWatchDataModel copy(@NotNull String option, @NotNull String headerTextCenter, @NotNull String headerTextEnd, @NotNull String infoText, @Nullable Drawable drawable, @Nullable Drawable drawable2, @Nullable Drawable drawable3) {
        Intrinsics.checkNotNullParameter(option, "option");
        Intrinsics.checkNotNullParameter(headerTextCenter, "headerTextCenter");
        Intrinsics.checkNotNullParameter(headerTextEnd, "headerTextEnd");
        Intrinsics.checkNotNullParameter(infoText, "infoText");
        return new SetupYourWatchDataModel(option, headerTextCenter, headerTextEnd, infoText, drawable, drawable2, drawable3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SetupYourWatchDataModel) {
            SetupYourWatchDataModel setupYourWatchDataModel = (SetupYourWatchDataModel) obj;
            return Intrinsics.areEqual(this.option, setupYourWatchDataModel.option) && Intrinsics.areEqual(this.headerTextCenter, setupYourWatchDataModel.headerTextCenter) && Intrinsics.areEqual(this.headerTextEnd, setupYourWatchDataModel.headerTextEnd) && Intrinsics.areEqual(this.infoText, setupYourWatchDataModel.infoText) && Intrinsics.areEqual(this.drawable, setupYourWatchDataModel.drawable) && Intrinsics.areEqual(this.infoTextRightDrawable, setupYourWatchDataModel.infoTextRightDrawable) && Intrinsics.areEqual(this.backgroundDrawable, setupYourWatchDataModel.backgroundDrawable);
        }
        return false;
    }

    @Nullable
    public final Drawable getBackgroundDrawable() {
        return this.backgroundDrawable;
    }

    @Nullable
    public final Drawable getDrawable() {
        return this.drawable;
    }

    @NotNull
    public final String getHeaderTextCenter() {
        return this.headerTextCenter;
    }

    @NotNull
    public final String getHeaderTextEnd() {
        return this.headerTextEnd;
    }

    @NotNull
    public final String getInfoText() {
        return this.infoText;
    }

    @Nullable
    public final Drawable getInfoTextRightDrawable() {
        return this.infoTextRightDrawable;
    }

    @NotNull
    public final String getOption() {
        return this.option;
    }

    public int hashCode() {
        int hashCode = ((((((this.option.hashCode() * 31) + this.headerTextCenter.hashCode()) * 31) + this.headerTextEnd.hashCode()) * 31) + this.infoText.hashCode()) * 31;
        Drawable drawable = this.drawable;
        int hashCode2 = (hashCode + (drawable == null ? 0 : drawable.hashCode())) * 31;
        Drawable drawable2 = this.infoTextRightDrawable;
        int hashCode3 = (hashCode2 + (drawable2 == null ? 0 : drawable2.hashCode())) * 31;
        Drawable drawable3 = this.backgroundDrawable;
        return hashCode3 + (drawable3 != null ? drawable3.hashCode() : 0);
    }

    public final void setBackgroundDrawable(@Nullable Drawable drawable) {
        this.backgroundDrawable = drawable;
    }

    public final void setDrawable(@Nullable Drawable drawable) {
        this.drawable = drawable;
    }

    public final void setHeaderTextCenter(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.headerTextCenter = str;
    }

    public final void setHeaderTextEnd(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.headerTextEnd = str;
    }

    public final void setInfoText(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.infoText = str;
    }

    public final void setInfoTextRightDrawable(@Nullable Drawable drawable) {
        this.infoTextRightDrawable = drawable;
    }

    public final void setOption(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.option = str;
    }

    @NotNull
    public String toString() {
        return "SetupYourWatchDataModel(option=" + this.option + ", headerTextCenter=" + this.headerTextCenter + ", headerTextEnd=" + this.headerTextEnd + ", infoText=" + this.infoText + ", drawable=" + this.drawable + ", infoTextRightDrawable=" + this.infoTextRightDrawable + ", backgroundDrawable=" + this.backgroundDrawable + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ SetupYourWatchDataModel(String str, String str2, String str3, String str4, Drawable drawable, Drawable drawable2, Drawable drawable3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "WATCH_FACE_STUDIO" : str, (i & 2) != 0 ? "" : str2, (i & 4) != 0 ? "" : str3, (i & 8) == 0 ? str4 : "", (i & 16) != 0 ? null : drawable, (i & 32) != 0 ? null : drawable2, (i & 64) != 0 ? null : drawable3);
    }
}
