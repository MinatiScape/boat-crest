package com.google.android.gms.common.api.internal;

import android.os.Looper;
import androidx.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.Preconditions;
import java.lang.ref.WeakReference;
import java.util.concurrent.locks.Lock;
/* loaded from: classes6.dex */
public final class i implements BaseGmsClient.ConnectionProgressReportCallbacks {

    /* renamed from: a  reason: collision with root package name */
    public final WeakReference f8278a;
    public final Api b;
    public final boolean c;

    public i(zaaw zaawVar, Api api, boolean z) {
        this.f8278a = new WeakReference(zaawVar);
        this.b = api;
        this.c = z;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.ConnectionProgressReportCallbacks
    public final void onReportServiceBinding(@NonNull ConnectionResult connectionResult) {
        zabi zabiVar;
        Lock lock;
        Lock lock2;
        boolean g;
        boolean h;
        Lock lock3;
        zaaw zaawVar = (zaaw) this.f8278a.get();
        if (zaawVar == null) {
            return;
        }
        Looper myLooper = Looper.myLooper();
        zabiVar = zaawVar.f8298a;
        Preconditions.checkState(myLooper == zabiVar.n.getLooper(), "onReportServiceBinding must be called on the GoogleApiClient handler thread");
        lock = zaawVar.b;
        lock.lock();
        try {
            g = zaawVar.g(0);
            if (g) {
                if (!connectionResult.isSuccess()) {
                    zaawVar.e(connectionResult, this.b, this.c);
                }
                h = zaawVar.h();
                if (h) {
                    zaawVar.f();
                }
                lock3 = zaawVar.b;
            } else {
                lock3 = zaawVar.b;
            }
            lock3.unlock();
        } catch (Throwable th) {
            lock2 = zaawVar.b;
            lock2.unlock();
            throw th;
        }
    }
}
