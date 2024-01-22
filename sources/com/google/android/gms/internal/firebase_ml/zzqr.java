package com.google.android.gms.internal.firebase_ml;

import android.app.Application;
import android.content.Context;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import androidx.work.PeriodicWorkRequest;
import com.clevertap.android.sdk.Constants;
import com.google.android.gms.common.api.internal.BackgroundDetector;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.Dependency;
import com.google.firebase.ml.common.FirebaseMLException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
/* loaded from: classes7.dex */
public final class zzqr {
    public static final GmsLogger f = new GmsLogger("ModelResourceManager", "");
    public static final Component<?> zzbja = Component.builder(zzqr.class).add(Dependency.required(Context.class)).factory(v4.f8743a).build();

    /* renamed from: a  reason: collision with root package name */
    public final zzpx f8797a = zzpx.zzof();
    public final AtomicLong b;
    @GuardedBy("this")
    public final Set<zzqp> c;
    public final Set<zzqp> d;
    public final ConcurrentHashMap<zzqp, a> e;

    /* loaded from: classes7.dex */
    public class a implements Callable<Void> {
        public final zzqp h;
        public final String i;

        public a(zzqp zzqpVar, String str) {
            this.h = zzqpVar;
            this.i = str;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public final Void call() {
            String str = this.i;
            str.hashCode();
            if (str.equals("OPERATION_RELEASE")) {
                zzqp zzqpVar = this.h;
                zzqr.f.v("ModelResourceManager", "Releasing modelResource");
                zzqpVar.release();
                zzqr.this.d.remove(zzqpVar);
                return null;
            } else if (str.equals("OPERATION_LOAD")) {
                try {
                    zzqr.this.g(this.h);
                    return null;
                } catch (FirebaseMLException e) {
                    zzqr.f.e("ModelResourceManager", "Error preloading model resource", e);
                    return null;
                }
            } else {
                return null;
            }
        }

        public final boolean equals(Object obj) {
            if (obj instanceof a) {
                a aVar = (a) obj;
                return Objects.equal(this.h, aVar.h) && Objects.equal(this.i, aVar.i);
            }
            return false;
        }

        public final int hashCode() {
            return Objects.hashCode(this.h, this.i);
        }
    }

    public zzqr(Context context) {
        AtomicLong atomicLong = new AtomicLong(PeriodicWorkRequest.MIN_PERIODIC_FLEX_MILLIS);
        this.b = atomicLong;
        this.c = new HashSet();
        this.d = new HashSet();
        this.e = new ConcurrentHashMap<>();
        if (context instanceof Application) {
            BackgroundDetector.initialize((Application) context);
        } else {
            f.e("ModelResourceManager", "No valid Application available and auto-manage cannot work");
        }
        BackgroundDetector.getInstance().addListener(new BackgroundDetector.BackgroundStateChangeListener(this) { // from class: com.google.android.gms.internal.firebase_ml.u4

            /* renamed from: a  reason: collision with root package name */
            public final zzqr f8739a;

            {
                this.f8739a = this;
            }

            @Override // com.google.android.gms.common.api.internal.BackgroundDetector.BackgroundStateChangeListener
            public final void onBackgroundStateChanged(boolean z) {
                this.f8739a.b(z);
            }
        });
        if (BackgroundDetector.getInstance().readCurrentStateIfPossible(true)) {
            atomicLong.set(Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS);
        }
    }

    public static final /* synthetic */ zzqr e(ComponentContainer componentContainer) {
        return new zzqr((Context) componentContainer.get(Context.class));
    }

    public final /* synthetic */ void b(boolean z) {
        GmsLogger gmsLogger = f;
        StringBuilder sb = new StringBuilder(34);
        sb.append("Background state changed to: ");
        sb.append(z);
        gmsLogger.v("ModelResourceManager", sb.toString());
        this.b.set(z ? Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS : PeriodicWorkRequest.MIN_PERIODIC_FLEX_MILLIS);
        h();
    }

    public final synchronized void c(zzqp zzqpVar) {
        if (this.c.contains(zzqpVar)) {
            d(zzqpVar);
        }
    }

    @GuardedBy("this")
    public final void d(zzqp zzqpVar) {
        a f2 = f(zzqpVar);
        this.f8797a.zzb(f2);
        long j = this.b.get();
        GmsLogger gmsLogger = f;
        StringBuilder sb = new StringBuilder(62);
        sb.append("Rescheduling modelResource release after: ");
        sb.append(j);
        gmsLogger.v("ModelResourceManager", sb.toString());
        this.f8797a.zza(f2, j);
    }

    public final a f(zzqp zzqpVar) {
        this.e.putIfAbsent(zzqpVar, new a(zzqpVar, "OPERATION_RELEASE"));
        return this.e.get(zzqpVar);
    }

    @WorkerThread
    public final void g(zzqp zzqpVar) throws FirebaseMLException {
        if (this.d.contains(zzqpVar)) {
            return;
        }
        try {
            zzqpVar.zzol();
            this.d.add(zzqpVar);
        } catch (RuntimeException e) {
            throw new FirebaseMLException("The load task failed", 13, e);
        }
    }

    public final synchronized void h() {
        for (zzqp zzqpVar : this.c) {
            d(zzqpVar);
        }
    }

    public final synchronized void zza(@NonNull zzqp zzqpVar) {
        Preconditions.checkNotNull(zzqpVar, "Model source can not be null");
        GmsLogger gmsLogger = f;
        gmsLogger.d("ModelResourceManager", "Add auto-managed model resource");
        if (this.c.contains(zzqpVar)) {
            gmsLogger.i("ModelResourceManager", "The model resource is already registered.");
            return;
        }
        this.c.add(zzqpVar);
        if (zzqpVar != null) {
            this.f8797a.zza(new a(zzqpVar, "OPERATION_LOAD"));
            c(zzqpVar);
        }
    }

    public final synchronized void zzd(@Nullable zzqp zzqpVar) {
        if (zzqpVar == null) {
            return;
        }
        a f2 = f(zzqpVar);
        this.f8797a.zzb(f2);
        this.f8797a.zza(f2, 0L);
    }
}
