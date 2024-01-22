package com.google.android.youtube.player.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.youtube.player.YouTubeApiServiceUtil;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.internal.c;
import com.google.android.youtube.player.internal.i;
import com.google.android.youtube.player.internal.t;
import java.util.ArrayList;
/* loaded from: classes10.dex */
public abstract class r<T extends IInterface> implements t {

    /* renamed from: a  reason: collision with root package name */
    public final Context f10503a;
    public final Handler b;
    public T c;
    public ArrayList<t.a> d;
    public ArrayList<t.b> g;
    public ServiceConnection i;
    public final ArrayList<t.a> e = new ArrayList<>();
    public boolean f = false;
    public final ArrayList<b<?>> h = new ArrayList<>();
    public boolean j = false;

    /* loaded from: classes10.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f10504a;

        static {
            int[] iArr = new int[YouTubeInitializationResult.values().length];
            f10504a = iArr;
            try {
                iArr[YouTubeInitializationResult.SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    /* loaded from: classes10.dex */
    public abstract class b<TListener> {

        /* renamed from: a  reason: collision with root package name */
        public TListener f10505a;

        public b(r rVar, TListener tlistener) {
            this.f10505a = tlistener;
            synchronized (rVar.h) {
                rVar.h.add(this);
            }
        }

        public final void a() {
            TListener tlistener;
            synchronized (this) {
                tlistener = this.f10505a;
            }
            a(tlistener);
        }

        public abstract void a(TListener tlistener);

        public final void b() {
            synchronized (this) {
                this.f10505a = null;
            }
        }
    }

    /* loaded from: classes10.dex */
    public final class c extends b<Boolean> {
        public final YouTubeInitializationResult b;
        public final IBinder c;

        public c(String str, IBinder iBinder) {
            super(r.this, Boolean.TRUE);
            this.b = r.l(str);
            this.c = iBinder;
        }

        @Override // com.google.android.youtube.player.internal.r.b
        public final /* synthetic */ void a(Boolean bool) {
            if (bool != null) {
                if (a.f10504a[this.b.ordinal()] != 1) {
                    r.this.a(this.b);
                    return;
                }
                try {
                    if (r.this.b().equals(this.c.getInterfaceDescriptor())) {
                        r rVar = r.this;
                        rVar.c = rVar.a(this.c);
                        if (r.this.c != null) {
                            r.this.g();
                            return;
                        }
                    }
                } catch (RemoteException unused) {
                }
                r.this.k();
                r.this.a(YouTubeInitializationResult.INTERNAL_ERROR);
            }
        }
    }

    /* loaded from: classes10.dex */
    public final class d extends c.a {
        public d() {
        }

        @Override // com.google.android.youtube.player.internal.c
        public final void a(String str, IBinder iBinder) {
            r rVar = r.this;
            Handler handler = rVar.b;
            handler.sendMessage(handler.obtainMessage(1, new c(str, iBinder)));
        }
    }

    /* loaded from: classes10.dex */
    public final class e extends Handler {
        public e() {
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            int i = message.what;
            if (i == 3) {
                r.this.a((YouTubeInitializationResult) message.obj);
            } else if (i == 4) {
                synchronized (r.this.d) {
                    if (r.this.j && r.this.f() && r.this.d.contains(message.obj)) {
                        ((t.a) message.obj).a();
                    }
                }
            } else if (i != 2 || r.this.f()) {
                int i2 = message.what;
                if (i2 == 2 || i2 == 1) {
                    ((b) message.obj).a();
                }
            }
        }
    }

    /* loaded from: classes10.dex */
    public final class f implements ServiceConnection {
        public f() {
        }

        @Override // android.content.ServiceConnection
        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            r.this.b(iBinder);
        }

        @Override // android.content.ServiceConnection
        public final void onServiceDisconnected(ComponentName componentName) {
            r.this.c = null;
            r.this.h();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public r(Context context, t.a aVar, t.b bVar) {
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
            throw new IllegalStateException("Clients must be created on the UI thread.");
        }
        this.f10503a = (Context) ab.a(context);
        ArrayList<t.a> arrayList = new ArrayList<>();
        this.d = arrayList;
        arrayList.add(ab.a(aVar));
        ArrayList<t.b> arrayList2 = new ArrayList<>();
        this.g = arrayList2;
        arrayList2.add(ab.a(bVar));
        this.b = new e();
    }

    public static YouTubeInitializationResult l(String str) {
        try {
            return YouTubeInitializationResult.valueOf(str);
        } catch (IllegalArgumentException unused) {
            return YouTubeInitializationResult.UNKNOWN_ERROR;
        } catch (NullPointerException unused2) {
            return YouTubeInitializationResult.UNKNOWN_ERROR;
        }
    }

    public abstract T a(IBinder iBinder);

    public final void a(YouTubeInitializationResult youTubeInitializationResult) {
        this.b.removeMessages(4);
        synchronized (this.g) {
            ArrayList<t.b> arrayList = this.g;
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                if (!this.j) {
                    return;
                }
                if (this.g.contains(arrayList.get(i))) {
                    arrayList.get(i).a(youTubeInitializationResult);
                }
            }
        }
    }

    public abstract void a(i iVar, d dVar) throws RemoteException;

    public abstract String b();

    public final void b(IBinder iBinder) {
        try {
            a(i.a.a(iBinder), new d());
        } catch (RemoteException unused) {
            Log.w("YouTubeClient", "service died");
        }
    }

    public abstract String c();

    @Override // com.google.android.youtube.player.internal.t
    public void d() {
        h();
        this.j = false;
        synchronized (this.h) {
            int size = this.h.size();
            for (int i = 0; i < size; i++) {
                this.h.get(i).b();
            }
            this.h.clear();
        }
        k();
    }

    @Override // com.google.android.youtube.player.internal.t
    public final void e() {
        this.j = true;
        YouTubeInitializationResult isYouTubeApiServiceAvailable = YouTubeApiServiceUtil.isYouTubeApiServiceAvailable(this.f10503a);
        if (isYouTubeApiServiceAvailable != YouTubeInitializationResult.SUCCESS) {
            Handler handler = this.b;
            handler.sendMessage(handler.obtainMessage(3, isYouTubeApiServiceAvailable));
            return;
        }
        Intent intent = new Intent(c()).setPackage(z.a(this.f10503a));
        if (this.i != null) {
            Log.e("YouTubeClient", "Calling connect() while still connected, missing disconnect().");
            k();
        }
        f fVar = new f();
        this.i = fVar;
        if (this.f10503a.bindService(intent, fVar, 129)) {
            return;
        }
        Handler handler2 = this.b;
        handler2.sendMessage(handler2.obtainMessage(3, YouTubeInitializationResult.ERROR_CONNECTING_TO_SERVICE));
    }

    public final boolean f() {
        return this.c != null;
    }

    public final void g() {
        synchronized (this.d) {
            boolean z = true;
            ab.a(!this.f);
            this.b.removeMessages(4);
            this.f = true;
            if (this.e.size() != 0) {
                z = false;
            }
            ab.a(z);
            ArrayList<t.a> arrayList = this.d;
            int size = arrayList.size();
            for (int i = 0; i < size && this.j && f(); i++) {
                if (!this.e.contains(arrayList.get(i))) {
                    arrayList.get(i).a();
                }
            }
            this.e.clear();
            this.f = false;
        }
    }

    public final void h() {
        this.b.removeMessages(4);
        synchronized (this.d) {
            this.f = true;
            ArrayList<t.a> arrayList = this.d;
            int size = arrayList.size();
            for (int i = 0; i < size && this.j; i++) {
                if (this.d.contains(arrayList.get(i))) {
                    arrayList.get(i).b();
                }
            }
            this.f = false;
        }
    }

    public final void i() {
        if (!f()) {
            throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
        }
    }

    public final T j() {
        i();
        return this.c;
    }

    public final void k() {
        ServiceConnection serviceConnection = this.i;
        if (serviceConnection != null) {
            try {
                this.f10503a.unbindService(serviceConnection);
            } catch (IllegalArgumentException e2) {
                Log.w("YouTubeClient", "Unexpected error from unbindService()", e2);
            }
        }
        this.c = null;
        this.i = null;
    }
}
