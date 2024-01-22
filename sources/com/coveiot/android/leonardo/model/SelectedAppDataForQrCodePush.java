package com.coveiot.android.leonardo.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class SelectedAppDataForQrCodePush implements Serializable {
    private int appIcon;
    @NotNull
    private String appName;
    private int appType;
    @Nullable
    private String qrCodeCardType;

    public SelectedAppDataForQrCodePush(int i, @NotNull String appName, @Nullable String str, int i2) {
        Intrinsics.checkNotNullParameter(appName, "appName");
        this.appIcon = i;
        this.appName = appName;
        this.qrCodeCardType = str;
        this.appType = i2;
    }

    public static /* synthetic */ SelectedAppDataForQrCodePush copy$default(SelectedAppDataForQrCodePush selectedAppDataForQrCodePush, int i, String str, String str2, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = selectedAppDataForQrCodePush.appIcon;
        }
        if ((i3 & 2) != 0) {
            str = selectedAppDataForQrCodePush.appName;
        }
        if ((i3 & 4) != 0) {
            str2 = selectedAppDataForQrCodePush.qrCodeCardType;
        }
        if ((i3 & 8) != 0) {
            i2 = selectedAppDataForQrCodePush.appType;
        }
        return selectedAppDataForQrCodePush.copy(i, str, str2, i2);
    }

    public final int component1() {
        return this.appIcon;
    }

    @NotNull
    public final String component2() {
        return this.appName;
    }

    @Nullable
    public final String component3() {
        return this.qrCodeCardType;
    }

    public final int component4() {
        return this.appType;
    }

    @NotNull
    public final SelectedAppDataForQrCodePush copy(int i, @NotNull String appName, @Nullable String str, int i2) {
        Intrinsics.checkNotNullParameter(appName, "appName");
        return new SelectedAppDataForQrCodePush(i, appName, str, i2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SelectedAppDataForQrCodePush) {
            SelectedAppDataForQrCodePush selectedAppDataForQrCodePush = (SelectedAppDataForQrCodePush) obj;
            return this.appIcon == selectedAppDataForQrCodePush.appIcon && Intrinsics.areEqual(this.appName, selectedAppDataForQrCodePush.appName) && Intrinsics.areEqual(this.qrCodeCardType, selectedAppDataForQrCodePush.qrCodeCardType) && this.appType == selectedAppDataForQrCodePush.appType;
        }
        return false;
    }

    public final int getAppIcon() {
        return this.appIcon;
    }

    @NotNull
    public final String getAppName() {
        return this.appName;
    }

    public final int getAppType() {
        return this.appType;
    }

    @Nullable
    public final String getQrCodeCardType() {
        return this.qrCodeCardType;
    }

    public int hashCode() {
        int hashCode = ((Integer.hashCode(this.appIcon) * 31) + this.appName.hashCode()) * 31;
        String str = this.qrCodeCardType;
        return ((hashCode + (str == null ? 0 : str.hashCode())) * 31) + Integer.hashCode(this.appType);
    }

    public final void setAppIcon(int i) {
        this.appIcon = i;
    }

    public final void setAppName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.appName = str;
    }

    public final void setAppType(int i) {
        this.appType = i;
    }

    public final void setQrCodeCardType(@Nullable String str) {
        this.qrCodeCardType = str;
    }

    @NotNull
    public String toString() {
        return "SelectedAppDataForQrCodePush(appIcon=" + this.appIcon + ", appName=" + this.appName + ", qrCodeCardType=" + this.qrCodeCardType + ", appType=" + this.appType + HexStringBuilder.COMMENT_END_CHAR;
    }
}
