package com.google.firebase.installations;

import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.installations.local.PersistedInstallationEntry;
/* loaded from: classes10.dex */
public class i implements j {

    /* renamed from: a  reason: collision with root package name */
    public final TaskCompletionSource<String> f11314a;

    public i(TaskCompletionSource<String> taskCompletionSource) {
        this.f11314a = taskCompletionSource;
    }

    @Override // com.google.firebase.installations.j
    public boolean a(Exception exc) {
        return false;
    }

    @Override // com.google.firebase.installations.j
    public boolean b(PersistedInstallationEntry persistedInstallationEntry) {
        if (persistedInstallationEntry.isUnregistered() || persistedInstallationEntry.isRegistered() || persistedInstallationEntry.isErrored()) {
            this.f11314a.trySetResult(persistedInstallationEntry.getFirebaseInstallationId());
            return true;
        }
        return false;
    }
}
