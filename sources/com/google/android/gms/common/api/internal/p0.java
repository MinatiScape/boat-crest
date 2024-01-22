package com.google.android.gms.common.api.internal;

import androidx.annotation.WorkerThread;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.internal.Preconditions;
import java.lang.ref.WeakReference;
/* loaded from: classes6.dex */
public final class p0 implements Runnable {
    public final /* synthetic */ Result h;
    public final /* synthetic */ zada i;

    public p0(zada zadaVar, Result result) {
        this.i = zadaVar;
        this.h = result;
    }

    @Override // java.lang.Runnable
    @WorkerThread
    public final void run() {
        WeakReference weakReference;
        q0 q0Var;
        q0 q0Var2;
        WeakReference weakReference2;
        GoogleApiClient googleApiClient;
        ResultTransform resultTransform;
        q0 q0Var3;
        q0 q0Var4;
        WeakReference weakReference3;
        try {
            try {
                ThreadLocal threadLocal = BasePendingResult.zaa;
                threadLocal.set(Boolean.TRUE);
                resultTransform = this.i.f8308a;
                PendingResult onSuccess = ((ResultTransform) Preconditions.checkNotNull(resultTransform)).onSuccess(this.h);
                zada zadaVar = this.i;
                q0Var3 = zadaVar.h;
                q0Var4 = zadaVar.h;
                q0Var3.sendMessage(q0Var4.obtainMessage(0, onSuccess));
                threadLocal.set(Boolean.FALSE);
                zada zadaVar2 = this.i;
                zada.m(this.h);
                weakReference3 = this.i.g;
                googleApiClient = (GoogleApiClient) weakReference3.get();
                if (googleApiClient == null) {
                    return;
                }
            } catch (RuntimeException e) {
                zada zadaVar3 = this.i;
                q0Var = zadaVar3.h;
                q0Var2 = zadaVar3.h;
                q0Var.sendMessage(q0Var2.obtainMessage(1, e));
                BasePendingResult.zaa.set(Boolean.FALSE);
                zada zadaVar4 = this.i;
                zada.m(this.h);
                weakReference2 = this.i.g;
                googleApiClient = (GoogleApiClient) weakReference2.get();
                if (googleApiClient == null) {
                    return;
                }
            }
            googleApiClient.zap(this.i);
        } catch (Throwable th) {
            BasePendingResult.zaa.set(Boolean.FALSE);
            zada zadaVar5 = this.i;
            zada.m(this.h);
            weakReference = this.i.g;
            GoogleApiClient googleApiClient2 = (GoogleApiClient) weakReference.get();
            if (googleApiClient2 != null) {
                googleApiClient2.zap(this.i);
            }
            throw th;
        }
    }
}
