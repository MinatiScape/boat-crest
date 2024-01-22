package com.google.android.gms.internal.auth;

import android.accounts.Account;
import com.google.android.gms.auth.account.WorkAccount;
import com.google.android.gms.auth.account.WorkAccountApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
/* loaded from: classes6.dex */
public final class zzal implements WorkAccountApi {

    /* renamed from: a  reason: collision with root package name */
    public static final Status f8550a = new Status(13);

    @Override // com.google.android.gms.auth.account.WorkAccountApi
    public final PendingResult<WorkAccountApi.AddAccountResult> addWorkAccount(GoogleApiClient googleApiClient, String str) {
        return googleApiClient.execute(new e(this, WorkAccount.API, googleApiClient, str));
    }

    @Override // com.google.android.gms.auth.account.WorkAccountApi
    public final PendingResult<Result> removeWorkAccount(GoogleApiClient googleApiClient, Account account) {
        return googleApiClient.execute(new g(this, WorkAccount.API, googleApiClient, account));
    }

    @Override // com.google.android.gms.auth.account.WorkAccountApi
    public final void setWorkAuthenticatorEnabled(GoogleApiClient googleApiClient, boolean z) {
        setWorkAuthenticatorEnabledWithResult(googleApiClient, z);
    }

    @Override // com.google.android.gms.auth.account.WorkAccountApi
    public final PendingResult<Result> setWorkAuthenticatorEnabledWithResult(GoogleApiClient googleApiClient, boolean z) {
        return googleApiClient.execute(new c(this, WorkAccount.API, googleApiClient, z));
    }
}
