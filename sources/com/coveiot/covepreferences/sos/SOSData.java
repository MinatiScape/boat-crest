package com.coveiot.covepreferences.sos;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class SOSData implements Serializable {
    @Nullable
    private String contactName;
    @Nullable
    private String contactNumber;
    @Nullable
    private Boolean isSOSEnabled;

    public SOSData(@Nullable Boolean bool, @Nullable String str, @Nullable String str2) {
        this.isSOSEnabled = bool;
        this.contactName = str;
        this.contactNumber = str2;
    }

    public static /* synthetic */ SOSData copy$default(SOSData sOSData, Boolean bool, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            bool = sOSData.isSOSEnabled;
        }
        if ((i & 2) != 0) {
            str = sOSData.contactName;
        }
        if ((i & 4) != 0) {
            str2 = sOSData.contactNumber;
        }
        return sOSData.copy(bool, str, str2);
    }

    @Nullable
    public final Boolean component1() {
        return this.isSOSEnabled;
    }

    @Nullable
    public final String component2() {
        return this.contactName;
    }

    @Nullable
    public final String component3() {
        return this.contactNumber;
    }

    @NotNull
    public final SOSData copy(@Nullable Boolean bool, @Nullable String str, @Nullable String str2) {
        return new SOSData(bool, str, str2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SOSData) {
            SOSData sOSData = (SOSData) obj;
            return Intrinsics.areEqual(this.isSOSEnabled, sOSData.isSOSEnabled) && Intrinsics.areEqual(this.contactName, sOSData.contactName) && Intrinsics.areEqual(this.contactNumber, sOSData.contactNumber);
        }
        return false;
    }

    @Nullable
    public final String getContactName() {
        return this.contactName;
    }

    @Nullable
    public final String getContactNumber() {
        return this.contactNumber;
    }

    public int hashCode() {
        Boolean bool = this.isSOSEnabled;
        int hashCode = (bool == null ? 0 : bool.hashCode()) * 31;
        String str = this.contactName;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.contactNumber;
        return hashCode2 + (str2 != null ? str2.hashCode() : 0);
    }

    @Nullable
    public final Boolean isSOSEnabled() {
        return this.isSOSEnabled;
    }

    public final void setContactName(@Nullable String str) {
        this.contactName = str;
    }

    public final void setContactNumber(@Nullable String str) {
        this.contactNumber = str;
    }

    public final void setSOSEnabled(@Nullable Boolean bool) {
        this.isSOSEnabled = bool;
    }

    @NotNull
    public String toString() {
        return "SOSData(isSOSEnabled=" + this.isSOSEnabled + ", contactName=" + this.contactName + ", contactNumber=" + this.contactNumber + HexStringBuilder.COMMENT_END_CHAR;
    }
}
