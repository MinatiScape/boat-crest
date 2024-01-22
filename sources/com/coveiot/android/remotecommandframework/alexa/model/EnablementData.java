package com.coveiot.android.remotecommandframework.alexa.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class EnablementData {
    @Nullable
    private AccountLinkData accountLink;
    @Nullable
    private String skillEnabledFrom;
    @Nullable
    private String status;
    private long userDeviceId;

    public EnablementData() {
        this(null, null, null, 0L, 15, null);
    }

    public EnablementData(@Nullable AccountLinkData accountLinkData, @Nullable String str, @Nullable String str2, long j) {
        this.accountLink = accountLinkData;
        this.status = str;
        this.skillEnabledFrom = str2;
        this.userDeviceId = j;
    }

    public static /* synthetic */ EnablementData copy$default(EnablementData enablementData, AccountLinkData accountLinkData, String str, String str2, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            accountLinkData = enablementData.accountLink;
        }
        if ((i & 2) != 0) {
            str = enablementData.status;
        }
        String str3 = str;
        if ((i & 4) != 0) {
            str2 = enablementData.skillEnabledFrom;
        }
        String str4 = str2;
        if ((i & 8) != 0) {
            j = enablementData.userDeviceId;
        }
        return enablementData.copy(accountLinkData, str3, str4, j);
    }

    @Nullable
    public final AccountLinkData component1() {
        return this.accountLink;
    }

    @Nullable
    public final String component2() {
        return this.status;
    }

    @Nullable
    public final String component3() {
        return this.skillEnabledFrom;
    }

    public final long component4() {
        return this.userDeviceId;
    }

    @NotNull
    public final EnablementData copy(@Nullable AccountLinkData accountLinkData, @Nullable String str, @Nullable String str2, long j) {
        return new EnablementData(accountLinkData, str, str2, j);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof EnablementData) {
            EnablementData enablementData = (EnablementData) obj;
            return Intrinsics.areEqual(this.accountLink, enablementData.accountLink) && Intrinsics.areEqual(this.status, enablementData.status) && Intrinsics.areEqual(this.skillEnabledFrom, enablementData.skillEnabledFrom) && this.userDeviceId == enablementData.userDeviceId;
        }
        return false;
    }

    @Nullable
    public final AccountLinkData getAccountLink() {
        return this.accountLink;
    }

    @Nullable
    public final String getSkillEnabledFrom() {
        return this.skillEnabledFrom;
    }

    @Nullable
    public final String getStatus() {
        return this.status;
    }

    public final long getUserDeviceId() {
        return this.userDeviceId;
    }

    public int hashCode() {
        AccountLinkData accountLinkData = this.accountLink;
        int hashCode = (accountLinkData == null ? 0 : accountLinkData.hashCode()) * 31;
        String str = this.status;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.skillEnabledFrom;
        return ((hashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31) + Long.hashCode(this.userDeviceId);
    }

    public final void setAccountLink(@Nullable AccountLinkData accountLinkData) {
        this.accountLink = accountLinkData;
    }

    public final void setSkillEnabledFrom(@Nullable String str) {
        this.skillEnabledFrom = str;
    }

    public final void setStatus(@Nullable String str) {
        this.status = str;
    }

    public final void setUserDeviceId(long j) {
        this.userDeviceId = j;
    }

    @NotNull
    public String toString() {
        return "EnablementData(accountLink=" + this.accountLink + ", status=" + this.status + ", skillEnabledFrom=" + this.skillEnabledFrom + ", userDeviceId=" + this.userDeviceId + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ EnablementData(AccountLinkData accountLinkData, String str, String str2, long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : accountLinkData, (i & 2) != 0 ? null : str, (i & 4) == 0 ? str2 : null, (i & 8) != 0 ? 0L : j);
    }
}
