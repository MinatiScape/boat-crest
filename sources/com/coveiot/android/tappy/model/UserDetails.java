package com.coveiot.android.tappy.model;

import java.io.Serializable;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class UserDetails implements Serializable {
    @Nullable
    private String accountStatus;
    private long globalUserID;

    @Nullable
    public final String getAccountStatus() {
        return this.accountStatus;
    }

    public final long getGlobalUserID() {
        return this.globalUserID;
    }

    public final void setAccountStatus(@Nullable String str) {
        this.accountStatus = str;
    }

    public final void setGlobalUserID(long j) {
        this.globalUserID = j;
    }
}
