package com.google.android.gms.common.moduleinstall.internal;

import androidx.annotation.Nullable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.common.moduleinstall.InstallStatusListener;
import com.google.android.gms.common.moduleinstall.ModuleInstallResponse;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes6.dex */
public final class g extends zaa {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AtomicReference f8360a;
    public final /* synthetic */ TaskCompletionSource b;
    public final /* synthetic */ InstallStatusListener c;
    public final /* synthetic */ zay d;

    public g(zay zayVar, AtomicReference atomicReference, TaskCompletionSource taskCompletionSource, InstallStatusListener installStatusListener) {
        this.d = zayVar;
        this.f8360a = atomicReference;
        this.b = taskCompletionSource;
        this.c = installStatusListener;
    }

    @Override // com.google.android.gms.common.moduleinstall.internal.zaa, com.google.android.gms.common.moduleinstall.internal.zae
    public final void zad(Status status, @Nullable ModuleInstallResponse moduleInstallResponse) {
        if (moduleInstallResponse != null) {
            this.f8360a.set(moduleInstallResponse);
        }
        TaskUtil.trySetResultOrApiException(status, null, this.b);
        if (!status.isSuccess() || (moduleInstallResponse != null && moduleInstallResponse.zaa())) {
            this.d.doUnregisterEventListener(ListenerHolders.createListenerKey(this.c, InstallStatusListener.class.getSimpleName()), 27306);
        }
    }
}
