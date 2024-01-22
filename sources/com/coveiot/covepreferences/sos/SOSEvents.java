package com.coveiot.covepreferences.sos;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class SOSEvents implements Serializable {
    @Nullable
    private String contactName;
    @Nullable
    private String contactNumber;
    @Nullable
    private Boolean isSavedToServer;
    @Nullable
    private String status;
    @Nullable
    private Long unixTimeStamp;

    public SOSEvents(@Nullable Long l, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable Boolean bool) {
        this.unixTimeStamp = l;
        this.status = str;
        this.contactName = str2;
        this.contactNumber = str3;
        this.isSavedToServer = bool;
    }

    public static /* synthetic */ SOSEvents copy$default(SOSEvents sOSEvents, Long l, String str, String str2, String str3, Boolean bool, int i, Object obj) {
        if ((i & 1) != 0) {
            l = sOSEvents.unixTimeStamp;
        }
        if ((i & 2) != 0) {
            str = sOSEvents.status;
        }
        String str4 = str;
        if ((i & 4) != 0) {
            str2 = sOSEvents.contactName;
        }
        String str5 = str2;
        if ((i & 8) != 0) {
            str3 = sOSEvents.contactNumber;
        }
        String str6 = str3;
        if ((i & 16) != 0) {
            bool = sOSEvents.isSavedToServer;
        }
        return sOSEvents.copy(l, str4, str5, str6, bool);
    }

    @Nullable
    public final Long component1() {
        return this.unixTimeStamp;
    }

    @Nullable
    public final String component2() {
        return this.status;
    }

    @Nullable
    public final String component3() {
        return this.contactName;
    }

    @Nullable
    public final String component4() {
        return this.contactNumber;
    }

    @Nullable
    public final Boolean component5() {
        return this.isSavedToServer;
    }

    @NotNull
    public final SOSEvents copy(@Nullable Long l, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable Boolean bool) {
        return new SOSEvents(l, str, str2, str3, bool);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SOSEvents) {
            SOSEvents sOSEvents = (SOSEvents) obj;
            return Intrinsics.areEqual(this.unixTimeStamp, sOSEvents.unixTimeStamp) && Intrinsics.areEqual(this.status, sOSEvents.status) && Intrinsics.areEqual(this.contactName, sOSEvents.contactName) && Intrinsics.areEqual(this.contactNumber, sOSEvents.contactNumber) && Intrinsics.areEqual(this.isSavedToServer, sOSEvents.isSavedToServer);
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

    @Nullable
    public final String getStatus() {
        return this.status;
    }

    @Nullable
    public final Long getUnixTimeStamp() {
        return this.unixTimeStamp;
    }

    public int hashCode() {
        Long l = this.unixTimeStamp;
        int hashCode = (l == null ? 0 : l.hashCode()) * 31;
        String str = this.status;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.contactName;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.contactNumber;
        int hashCode4 = (hashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        Boolean bool = this.isSavedToServer;
        return hashCode4 + (bool != null ? bool.hashCode() : 0);
    }

    @Nullable
    public final Boolean isSavedToServer() {
        return this.isSavedToServer;
    }

    public final void setContactName(@Nullable String str) {
        this.contactName = str;
    }

    public final void setContactNumber(@Nullable String str) {
        this.contactNumber = str;
    }

    public final void setSavedToServer(@Nullable Boolean bool) {
        this.isSavedToServer = bool;
    }

    public final void setStatus(@Nullable String str) {
        this.status = str;
    }

    public final void setUnixTimeStamp(@Nullable Long l) {
        this.unixTimeStamp = l;
    }

    @NotNull
    public String toString() {
        return "SOSEvents(unixTimeStamp=" + this.unixTimeStamp + ", status=" + this.status + ", contactName=" + this.contactName + ", contactNumber=" + this.contactNumber + ", isSavedToServer=" + this.isSavedToServer + HexStringBuilder.COMMENT_END_CHAR;
    }
}
