package com.google.firebase.installations;

import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.installations.local.PersistedInstallationEntry;
/* loaded from: classes10.dex */
public class h implements j {

    /* renamed from: a  reason: collision with root package name */
    public final Utils f11313a;
    public final TaskCompletionSource<InstallationTokenResult> b;

    public h(Utils utils, TaskCompletionSource<InstallationTokenResult> taskCompletionSource) {
        this.f11313a = utils;
        this.b = taskCompletionSource;
    }

    @Override // com.google.firebase.installations.j
    public boolean a(Exception exc) {
        this.b.trySetException(exc);
        return true;
    }

    @Override // com.google.firebase.installations.j
    public boolean b(PersistedInstallationEntry persistedInstallationEntry) {
        if (!persistedInstallationEntry.isRegistered() || this.f11313a.isAuthTokenExpired(persistedInstallationEntry)) {
            return false;
        }
        this.b.setResult(InstallationTokenResult.builder().setToken(persistedInstallationEntry.getAuthToken()).setTokenExpirationTimestamp(persistedInstallationEntry.getExpiresInSecs()).setTokenCreationTimestamp(persistedInstallationEntry.getTokenCreationEpochInSecs()).build());
        return true;
    }
}
