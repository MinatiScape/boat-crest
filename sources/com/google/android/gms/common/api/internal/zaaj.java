package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import android.os.DeadObjectException;
import androidx.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Set;
/* loaded from: classes6.dex */
public final class zaaj implements zabf {

    /* renamed from: a  reason: collision with root package name */
    public final zabi f8297a;
    public boolean b = false;

    public zaaj(zabi zabiVar) {
        this.f8297a = zabiVar;
    }

    public final void b() {
        if (this.b) {
            this.b = false;
            this.f8297a.n.x.zab();
            zaj();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabf
    public final BaseImplementation.ApiMethodImpl zaa(BaseImplementation.ApiMethodImpl apiMethodImpl) {
        zab(apiMethodImpl);
        return apiMethodImpl;
    }

    @Override // com.google.android.gms.common.api.internal.zabf
    public final BaseImplementation.ApiMethodImpl zab(BaseImplementation.ApiMethodImpl apiMethodImpl) {
        try {
            this.f8297a.n.x.a(apiMethodImpl);
            zabe zabeVar = this.f8297a.n;
            Api.Client client = (Api.Client) zabeVar.o.get(apiMethodImpl.getClientKey());
            Preconditions.checkNotNull(client, "Appropriate Api was not requested.");
            if (!client.isConnected() && this.f8297a.g.containsKey(apiMethodImpl.getClientKey())) {
                apiMethodImpl.setFailedResult(new Status(17));
            } else {
                apiMethodImpl.run(client);
            }
        } catch (DeadObjectException unused) {
            this.f8297a.f(new f(this, this));
        }
        return apiMethodImpl;
    }

    @Override // com.google.android.gms.common.api.internal.zabf
    public final void zad() {
    }

    @Override // com.google.android.gms.common.api.internal.zabf
    public final void zae() {
        if (this.b) {
            this.b = false;
            this.f8297a.f(new g(this, this));
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabf
    public final void zag(@Nullable Bundle bundle) {
    }

    @Override // com.google.android.gms.common.api.internal.zabf
    public final void zah(ConnectionResult connectionResult, Api api, boolean z) {
    }

    @Override // com.google.android.gms.common.api.internal.zabf
    public final void zai(int i) {
        this.f8297a.e(null);
        this.f8297a.o.zac(i, this.b);
    }

    @Override // com.google.android.gms.common.api.internal.zabf
    public final boolean zaj() {
        if (this.b) {
            return false;
        }
        Set<zada> set = this.f8297a.n.w;
        if (set != null && !set.isEmpty()) {
            this.b = true;
            for (zada zadaVar : set) {
                zadaVar.h();
            }
            return false;
        }
        this.f8297a.e(null);
        return true;
    }
}
