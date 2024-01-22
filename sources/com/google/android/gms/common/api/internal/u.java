package com.google.android.gms.common.api.internal;

import android.content.Context;
import androidx.annotation.NonNull;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
/* loaded from: classes6.dex */
public final class u implements ResultCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ StatusPendingResult f8287a;
    public final /* synthetic */ boolean b;
    public final /* synthetic */ GoogleApiClient c;
    public final /* synthetic */ zabe d;

    public u(zabe zabeVar, StatusPendingResult statusPendingResult, boolean z, GoogleApiClient googleApiClient) {
        this.d = zabeVar;
        this.f8287a = statusPendingResult;
        this.b = z;
        this.c = googleApiClient;
    }

    @Override // com.google.android.gms.common.api.ResultCallback
    public final /* bridge */ /* synthetic */ void onResult(@NonNull Result result) {
        Context context;
        Status status = (Status) result;
        context = this.d.f;
        Storage.getInstance(context).zac();
        if (status.isSuccess() && this.d.isConnected()) {
            zabe zabeVar = this.d;
            zabeVar.disconnect();
            zabeVar.connect();
        }
        this.f8287a.setResult(status);
        if (this.b) {
            this.c.disconnect();
        }
    }
}
