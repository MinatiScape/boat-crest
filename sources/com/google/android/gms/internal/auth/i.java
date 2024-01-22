package com.google.android.gms.internal.auth;

import android.accounts.Account;
import androidx.annotation.Nullable;
import com.google.android.gms.auth.account.WorkAccountApi;
import com.google.android.gms.common.api.Status;
/* loaded from: classes6.dex */
public final class i implements WorkAccountApi.AddAccountResult {
    public static final Account j = new Account("DUMMY_NAME", "com.google");
    public final Status h;
    public final Account i;

    public i(Status status, @Nullable Account account) {
        this.h = status;
        this.i = account == null ? j : account;
    }

    @Override // com.google.android.gms.auth.account.WorkAccountApi.AddAccountResult
    public final Account getAccount() {
        return this.i;
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.h;
    }
}
