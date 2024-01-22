package com.coveiot.android.remotecommandframework.alexa.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class AccountLinkData {
    @Nullable
    private String status;

    public AccountLinkData() {
        this(null, 1, null);
    }

    public AccountLinkData(@Nullable String str) {
        this.status = str;
    }

    public static /* synthetic */ AccountLinkData copy$default(AccountLinkData accountLinkData, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = accountLinkData.status;
        }
        return accountLinkData.copy(str);
    }

    @Nullable
    public final String component1() {
        return this.status;
    }

    @NotNull
    public final AccountLinkData copy(@Nullable String str) {
        return new AccountLinkData(str);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof AccountLinkData) && Intrinsics.areEqual(this.status, ((AccountLinkData) obj).status);
    }

    @Nullable
    public final String getStatus() {
        return this.status;
    }

    public int hashCode() {
        String str = this.status;
        if (str == null) {
            return 0;
        }
        return str.hashCode();
    }

    public final void setStatus(@Nullable String str) {
        this.status = str;
    }

    @NotNull
    public String toString() {
        return "AccountLinkData(status=" + this.status + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ AccountLinkData(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str);
    }
}
