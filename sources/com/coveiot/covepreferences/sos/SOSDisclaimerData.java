package com.coveiot.covepreferences.sos;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class SOSDisclaimerData implements Serializable {
    @NotNull
    private final String appDisclaimerUrl;
    @NotNull
    private String version;

    public SOSDisclaimerData(@NotNull String version, @NotNull String appDisclaimerUrl) {
        Intrinsics.checkNotNullParameter(version, "version");
        Intrinsics.checkNotNullParameter(appDisclaimerUrl, "appDisclaimerUrl");
        this.version = version;
        this.appDisclaimerUrl = appDisclaimerUrl;
    }

    public static /* synthetic */ SOSDisclaimerData copy$default(SOSDisclaimerData sOSDisclaimerData, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = sOSDisclaimerData.version;
        }
        if ((i & 2) != 0) {
            str2 = sOSDisclaimerData.appDisclaimerUrl;
        }
        return sOSDisclaimerData.copy(str, str2);
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
    public final SOSDisclaimerData copy(@NotNull String version, @NotNull String appDisclaimerUrl) {
        Intrinsics.checkNotNullParameter(version, "version");
        Intrinsics.checkNotNullParameter(appDisclaimerUrl, "appDisclaimerUrl");
        return new SOSDisclaimerData(version, appDisclaimerUrl);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SOSDisclaimerData) {
            SOSDisclaimerData sOSDisclaimerData = (SOSDisclaimerData) obj;
            return Intrinsics.areEqual(this.version, sOSDisclaimerData.version) && Intrinsics.areEqual(this.appDisclaimerUrl, sOSDisclaimerData.appDisclaimerUrl);
        }
        return false;
    }

    @NotNull
    public final String getAppDisclaimerUrl() {
        return this.appDisclaimerUrl;
    }

    @NotNull
    public final String getVersion() {
        return this.version;
    }

    public int hashCode() {
        return (this.version.hashCode() * 31) + this.appDisclaimerUrl.hashCode();
    }

    public final void setVersion(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.version = str;
    }

    @NotNull
    public String toString() {
        return "SOSDisclaimerData(version=" + this.version + ", appDisclaimerUrl=" + this.appDisclaimerUrl + HexStringBuilder.COMMENT_END_CHAR;
    }
}
