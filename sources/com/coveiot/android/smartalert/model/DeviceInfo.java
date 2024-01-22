package com.coveiot.android.smartalert.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class DeviceInfo implements Serializable {
    @SerializedName("applicableModels")
    @Expose
    @Nullable
    private final String applicationModels;
    @SerializedName("displayShape")
    @Expose
    @Nullable
    private final String displayShape;
    @SerializedName("resolution")
    @Expose
    @Nullable
    private final String resolution;

    public DeviceInfo() {
        this(null, null, null, 7, null);
    }

    public DeviceInfo(@Nullable String str, @Nullable String str2, @Nullable String str3) {
        this.resolution = str;
        this.displayShape = str2;
        this.applicationModels = str3;
    }

    public static /* synthetic */ DeviceInfo copy$default(DeviceInfo deviceInfo, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = deviceInfo.resolution;
        }
        if ((i & 2) != 0) {
            str2 = deviceInfo.displayShape;
        }
        if ((i & 4) != 0) {
            str3 = deviceInfo.applicationModels;
        }
        return deviceInfo.copy(str, str2, str3);
    }

    @Nullable
    public final String component1() {
        return this.resolution;
    }

    @Nullable
    public final String component2() {
        return this.displayShape;
    }

    @Nullable
    public final String component3() {
        return this.applicationModels;
    }

    @NotNull
    public final DeviceInfo copy(@Nullable String str, @Nullable String str2, @Nullable String str3) {
        return new DeviceInfo(str, str2, str3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DeviceInfo) {
            DeviceInfo deviceInfo = (DeviceInfo) obj;
            return Intrinsics.areEqual(this.resolution, deviceInfo.resolution) && Intrinsics.areEqual(this.displayShape, deviceInfo.displayShape) && Intrinsics.areEqual(this.applicationModels, deviceInfo.applicationModels);
        }
        return false;
    }

    @Nullable
    public final String getApplicationModels() {
        return this.applicationModels;
    }

    @Nullable
    public final String getDisplayShape() {
        return this.displayShape;
    }

    @Nullable
    public final String getResolution() {
        return this.resolution;
    }

    public int hashCode() {
        String str = this.resolution;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.displayShape;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.applicationModels;
        return hashCode2 + (str3 != null ? str3.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        return "DeviceInfo(resolution=" + this.resolution + ", displayShape=" + this.displayShape + ", applicationModels=" + this.applicationModels + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ DeviceInfo(String str, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3);
    }
}
