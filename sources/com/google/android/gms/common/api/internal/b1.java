package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import java.util.concurrent.locks.Lock;
/* loaded from: classes6.dex */
public final class b1 implements zabz {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ b f8270a;

    public /* synthetic */ b1(b bVar, zay zayVar) {
        this.f8270a = bVar;
    }

    @Override // com.google.android.gms.common.api.internal.zabz
    public final void zaa(@NonNull ConnectionResult connectionResult) {
        Lock lock;
        Lock lock2;
        lock = this.f8270a.m;
        lock.lock();
        try {
            this.f8270a.k = connectionResult;
            b.p(this.f8270a);
        } finally {
            lock2 = this.f8270a.m;
            lock2.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabz
    public final void zab(@Nullable Bundle bundle) {
        Lock lock;
        Lock lock2;
        lock = this.f8270a.m;
        lock.lock();
        try {
            this.f8270a.k = ConnectionResult.RESULT_SUCCESS;
            b.p(this.f8270a);
        } finally {
            lock2 = this.f8270a.m;
            lock2.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabz
    public final void zac(int i, boolean z) {
        Lock lock;
        Lock lock2;
        boolean z2;
        zabi zabiVar;
        Lock lock3;
        lock = this.f8270a.m;
        lock.lock();
        try {
            b bVar = this.f8270a;
            z2 = bVar.l;
            if (z2) {
                bVar.l = false;
                b.n(this.f8270a, i, z);
                lock3 = this.f8270a.m;
            } else {
                bVar.l = true;
                zabiVar = this.f8270a.d;
                zabiVar.onConnectionSuspended(i);
                lock3 = this.f8270a.m;
            }
            lock3.unlock();
        } catch (Throwable th) {
            lock2 = this.f8270a.m;
            lock2.unlock();
            throw th;
        }
    }
}
