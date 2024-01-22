package com.coveiot.android.dashboard2.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.Serializable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class ServerDataPullConfigModel implements Serializable {
    @Nullable
    private String fromDate;
    @Nullable
    private String versionTag;

    public ServerDataPullConfigModel() {
        this(null, null, 3, null);
    }

    public ServerDataPullConfigModel(@Nullable String str, @Nullable String str2) {
        this.fromDate = str;
        this.versionTag = str2;
    }

    public static /* synthetic */ ServerDataPullConfigModel copy$default(ServerDataPullConfigModel serverDataPullConfigModel, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = serverDataPullConfigModel.fromDate;
        }
        if ((i & 2) != 0) {
            str2 = serverDataPullConfigModel.versionTag;
        }
        return serverDataPullConfigModel.copy(str, str2);
    }

    @Nullable
    public final String component1() {
        return this.fromDate;
    }

    @Nullable
    public final String component2() {
        return this.versionTag;
    }

    @NotNull
    public final ServerDataPullConfigModel copy(@Nullable String str, @Nullable String str2) {
        return new ServerDataPullConfigModel(str, str2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ServerDataPullConfigModel) {
            ServerDataPullConfigModel serverDataPullConfigModel = (ServerDataPullConfigModel) obj;
            return Intrinsics.areEqual(this.fromDate, serverDataPullConfigModel.fromDate) && Intrinsics.areEqual(this.versionTag, serverDataPullConfigModel.versionTag);
        }
        return false;
    }

    @Nullable
    public final String getFromDate() {
        return this.fromDate;
    }

    @Nullable
    public final String getVersionTag() {
        return this.versionTag;
    }

    public int hashCode() {
        String str = this.fromDate;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.versionTag;
        return hashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public final void setFromDate(@Nullable String str) {
        this.fromDate = str;
    }

    public final void setVersionTag(@Nullable String str) {
        this.versionTag = str;
    }

    @NotNull
    public String toString() {
        return "ServerDataPullConfigModel(fromDate=" + this.fromDate + ", versionTag=" + this.versionTag + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ ServerDataPullConfigModel(String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2);
    }
}
