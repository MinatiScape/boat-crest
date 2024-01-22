package com.google.android.gms.auth.api.signin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
/* loaded from: classes6.dex */
public class GoogleSignInResult implements Result {
    public final Status h;
    @Nullable
    public final GoogleSignInAccount i;

    public GoogleSignInResult(@Nullable GoogleSignInAccount googleSignInAccount, @NonNull Status status) {
        this.i = googleSignInAccount;
        this.h = status;
    }

    @Nullable
    public GoogleSignInAccount getSignInAccount() {
        return this.i;
    }

    @Override // com.google.android.gms.common.api.Result
    @NonNull
    public Status getStatus() {
        return this.h;
    }

    public boolean isSuccess() {
        return this.h.isSuccess();
    }
}
