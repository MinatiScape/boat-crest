package androidx.room;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.IMultiInstanceInvalidationCallback;
import androidx.room.IMultiInstanceInvalidationService;
import androidx.room.InvalidationTracker;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    public final Context f1667a;
    public final String b;
    public int c;
    public final InvalidationTracker d;
    public final InvalidationTracker.Observer e;
    @Nullable
    public IMultiInstanceInvalidationService f;
    public final Executor g;
    public final IMultiInstanceInvalidationCallback h = new a();
    public final AtomicBoolean i = new AtomicBoolean(false);
    public final ServiceConnection j;
    public final Runnable k;
    public final Runnable l;
    public final Runnable m;

    /* loaded from: classes.dex */
    public class a extends IMultiInstanceInvalidationCallback.Stub {

        /* renamed from: androidx.room.b$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public class RunnableC0179a implements Runnable {
            public final /* synthetic */ String[] h;

            public RunnableC0179a(String[] strArr) {
                this.h = strArr;
            }

            @Override // java.lang.Runnable
            public void run() {
                b.this.d.notifyObserversByTableNames(this.h);
            }
        }

        public a() {
        }

        @Override // androidx.room.IMultiInstanceInvalidationCallback
        public void onInvalidation(String[] strArr) {
            b.this.g.execute(new RunnableC0179a(strArr));
        }
    }

    /* renamed from: androidx.room.b$b  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public class ServiceConnectionC0180b implements ServiceConnection {
        public ServiceConnectionC0180b() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            b.this.f = IMultiInstanceInvalidationService.Stub.asInterface(iBinder);
            b bVar = b.this;
            bVar.g.execute(bVar.k);
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            b bVar = b.this;
            bVar.g.execute(bVar.l);
            b.this.f = null;
        }
    }

    /* loaded from: classes.dex */
    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                b bVar = b.this;
                IMultiInstanceInvalidationService iMultiInstanceInvalidationService = bVar.f;
                if (iMultiInstanceInvalidationService != null) {
                    bVar.c = iMultiInstanceInvalidationService.registerCallback(bVar.h, bVar.b);
                    b bVar2 = b.this;
                    bVar2.d.addObserver(bVar2.e);
                }
            } catch (RemoteException e) {
                Log.w("ROOM", "Cannot register multi-instance invalidation callback", e);
            }
        }
    }

    /* loaded from: classes.dex */
    public class d implements Runnable {
        public d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            b bVar = b.this;
            bVar.d.removeObserver(bVar.e);
        }
    }

    /* loaded from: classes.dex */
    public class e implements Runnable {
        public e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            b bVar = b.this;
            bVar.d.removeObserver(bVar.e);
            try {
                b bVar2 = b.this;
                IMultiInstanceInvalidationService iMultiInstanceInvalidationService = bVar2.f;
                if (iMultiInstanceInvalidationService != null) {
                    iMultiInstanceInvalidationService.unregisterCallback(bVar2.h, bVar2.c);
                }
            } catch (RemoteException e) {
                Log.w("ROOM", "Cannot unregister multi-instance invalidation callback", e);
            }
            b bVar3 = b.this;
            bVar3.f1667a.unbindService(bVar3.j);
        }
    }

    /* loaded from: classes.dex */
    public class f extends InvalidationTracker.Observer {
        public f(String[] strArr) {
            super(strArr);
        }

        @Override // androidx.room.InvalidationTracker.Observer
        public boolean a() {
            return true;
        }

        @Override // androidx.room.InvalidationTracker.Observer
        public void onInvalidated(@NonNull Set<String> set) {
            if (b.this.i.get()) {
                return;
            }
            try {
                b bVar = b.this;
                IMultiInstanceInvalidationService iMultiInstanceInvalidationService = bVar.f;
                if (iMultiInstanceInvalidationService != null) {
                    iMultiInstanceInvalidationService.broadcastInvalidation(bVar.c, (String[]) set.toArray(new String[0]));
                }
            } catch (RemoteException e) {
                Log.w("ROOM", "Cannot broadcast invalidation", e);
            }
        }
    }

    public b(Context context, String str, InvalidationTracker invalidationTracker, Executor executor) {
        ServiceConnectionC0180b serviceConnectionC0180b = new ServiceConnectionC0180b();
        this.j = serviceConnectionC0180b;
        this.k = new c();
        this.l = new d();
        this.m = new e();
        Context applicationContext = context.getApplicationContext();
        this.f1667a = applicationContext;
        this.b = str;
        this.d = invalidationTracker;
        this.g = executor;
        this.e = new f((String[]) invalidationTracker.f1657a.keySet().toArray(new String[0]));
        applicationContext.bindService(new Intent(applicationContext, MultiInstanceInvalidationService.class), serviceConnectionC0180b, 1);
    }

    public void a() {
        if (this.i.compareAndSet(false, true)) {
            this.g.execute(this.m);
        }
    }
}
