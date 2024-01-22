package com.google.android.gms.internal.auth;

import android.os.RemoteException;
import com.google.android.gms.auth.api.proxy.ProxyResponse;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;
/* loaded from: classes6.dex */
public final class n extends zzbd {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ TaskCompletionSource f8532a;

    public n(zzbo zzboVar, TaskCompletionSource taskCompletionSource) {
        this.f8532a = taskCompletionSource;
    }

    @Override // com.google.android.gms.internal.auth.zzbd, com.google.android.gms.internal.auth.zzbg
    public final void zzb(ProxyResponse proxyResponse) throws RemoteException {
        TaskUtil.setResultOrApiException(new Status(proxyResponse.googlePlayServicesStatusCode), proxyResponse, this.f8532a);
    }
}
