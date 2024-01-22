package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import java.util.concurrent.locks.Lock;
/* loaded from: classes6.dex */
public final class a1 implements zabz {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ b f8268a;

    public /* synthetic */ a1(b bVar, zaw zawVar) {
        this.f8268a = bVar;
    }

    @Override // com.google.android.gms.common.api.internal.zabz
    public final void zaa(@NonNull ConnectionResult connectionResult) {
        Lock lock;
        Lock lock2;
        lock = this.f8268a.m;
        lock.lock();
        try {
            this.f8268a.j = connectionResult;
            b.p(this.f8268a);
        } finally {
            lock2 = this.f8268a.m;
            lock2.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabz
    public final void zab(@Nullable Bundle bundle) {
        Lock lock;
        Lock lock2;
        lock = this.f8268a.m;
        lock.lock();
        try {
            b.o(this.f8268a, bundle);
            this.f8268a.j = ConnectionResult.RESULT_SUCCESS;
            b.p(this.f8268a);
        } finally {
            lock2 = this.f8268a.m;
            lock2.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabz
    public final void zac(int i, boolean z) {
        Lock lock;
        Lock lock2;
        boolean z2;
        Lock lock3;
        ConnectionResult connectionResult;
        ConnectionResult connectionResult2;
        zabi zabiVar;
        lock = this.f8268a.m;
        lock.lock();
        try {
            b bVar = this.f8268a;
            z2 = bVar.l;
            if (!z2) {
                connectionResult = bVar.k;
                if (connectionResult != null) {
                    connectionResult2 = bVar.k;
                    if (connectionResult2.isSuccess()) {
                        this.f8268a.l = true;
                        zabiVar = this.f8268a.e;
                        zabiVar.onConnectionSuspended(i);
                        lock3 = this.f8268a.m;
                        lock3.unlock();
                    }
                }
            }
            this.f8268a.l = false;
            b.n(this.f8268a, i, z);
            lock3 = this.f8268a.m;
            lock3.unlock();
        } catch (Throwable th) {
            lock2 = this.f8268a.m;
            lock2.unlock();
            throw th;
        }
    }
}
