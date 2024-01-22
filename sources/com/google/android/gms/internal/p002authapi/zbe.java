package com.google.android.gms.internal.p002authapi;

import androidx.annotation.Nullable;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.CredentialRequestResult;
import com.google.android.gms.common.api.Status;
/* renamed from: com.google.android.gms.internal.auth-api.zbe  reason: invalid package */
/* loaded from: classes6.dex */
public final class zbe implements CredentialRequestResult {
    public final Status h;
    @Nullable
    public final Credential i;

    public zbe(Status status, @Nullable Credential credential) {
        this.h = status;
        this.i = credential;
    }

    @Override // com.google.android.gms.auth.api.credentials.CredentialRequestResult
    @Nullable
    public final Credential getCredential() {
        return this.i;
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.h;
    }
}
