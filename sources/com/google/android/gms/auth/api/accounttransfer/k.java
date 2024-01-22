package com.google.android.gms.auth.api.accounttransfer;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.internal.auth.zzap;
import com.google.android.gms.internal.auth.zzau;
import com.google.android.gms.tasks.TaskCompletionSource;
/* loaded from: classes6.dex */
public abstract class k extends TaskApiCall {
    public TaskCompletionSource d;

    public /* synthetic */ k(int i, zzk zzkVar) {
        super(null, false, i);
    }

    public abstract void a(zzau zzauVar) throws RemoteException;

    @Override // com.google.android.gms.common.api.internal.TaskApiCall
    public final /* bridge */ /* synthetic */ void doExecute(Api.AnyClient anyClient, TaskCompletionSource taskCompletionSource) throws RemoteException {
        this.d = taskCompletionSource;
        a((zzau) ((zzap) anyClient).getService());
    }
}
