package com.coveiot.android.navigation.models;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class NavigationDisclaimerData implements Serializable {
    @NotNull
    private final String appDisclaimerUrl;
    @NotNull
    private String dvcText;
    @NotNull
    private String version;

    public NavigationDisclaimerData(@NotNull String version, @NotNull String appDisclaimerUrl, @NotNull String dvcText) {
        Intrinsics.checkNotNullParameter(version, "version");
        Intrinsics.checkNotNullParameter(appDisclaimerUrl, "appDisclaimerUrl");
        Intrinsics.checkNotNullParameter(dvcText, "dvcText");
        this.version = version;
        this.appDisclaimerUrl = appDisclaimerUrl;
        this.dvcText = dvcText;
    }

    public static /* synthetic */ NavigationDisclaimerData copy$default(NavigationDisclaimerData navigationDisclaimerData, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = navigationDisclaimerData.version;
        }
        if ((i & 2) != 0) {
            str2 = navigationDisclaimerData.appDisclaimerUrl;
        }
        if ((i & 4) != 0) {
            str3 = navigationDisclaimerData.dvcText;
        }
        return navigationDisclaimerData.copy(str, str2, str3);
    }

    @NotNull
    public final String component1() {
        return this.version;
    }

    @NotNull
    public final String component2() {
        return this.appDisclaimerUrl;
    }

    @NotNull
    public final String component3() {
        return this.dvcText;
    }

    @NotNull
    public final NavigationDisclaimerData copy(@NotNull String version, @NotNull String appDisclaimerUrl, @NotNull String dvcText) {
        Intrinsics.checkNotNullParameter(version, "version");
        Intrinsics.checkNotNullParameter(appDisclaimerUrl, "appDisclaimerUrl");
        Intrinsics.checkNotNullParameter(dvcText, "dvcText");
        return new NavigationDisclaimerData(version, appDisclaimerUrl, dvcText);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof NavigationDisclaimerData) {
            NavigationDisclaimerData navigationDisclaimerData = (NavigationDisclaimerData) obj;
            return Intrinsics.areEqual(this.version, navigationDisclaimerData.version) && Intrinsics.areEqual(this.appDisclaimerUrl, navigationDisclaimerData.appDisclaimerUrl) && Intrinsics.areEqual(this.dvcText, navigationDisclaimerData.dvcText);
        }
        return false;
    }

    @NotNull
    public final String getAppDisclaimerUrl() {
        return this.appDisclaimerUrl;
    }

    @NotNull
    public final String getDvcText() {
        return this.dvcText;
    }

    @NotNull
    public final String getVersion() {
        return this.version;
    }

    public int hashCode() {
        return (((this.version.hashCode() * 31) + this.appDisclaimerUrl.hashCode()) * 31) + this.dvcText.hashCode();
    }

    public final void setDvcText(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.dvcText = str;
    }

    public final void setVersion(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.version = str;
    }

    @NotNull
    public String toString() {
        return "NavigationDisclaimerData(version=" + this.version + ", appDisclaimerUrl=" + this.appDisclaimerUrl + ", dvcText=" + this.dvcText + HexStringBuilder.COMMENT_END_CHAR;
    }
}
