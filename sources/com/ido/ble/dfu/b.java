package com.ido.ble.dfu;

import android.os.Handler;
import android.os.Looper;
import com.ido.ble.dfu.BleDFUState;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public class b {
    private static b c;

    /* renamed from: a  reason: collision with root package name */
    private List<BleDFUState.IListener> f12158a = new ArrayList();
    private Handler b = new Handler(Looper.getMainLooper());

    /* loaded from: classes11.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            for (BleDFUState.IListener iListener : b.this.g()) {
                iListener.onPrepare();
            }
        }
    }

    /* renamed from: com.ido.ble.dfu.b$b  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public class RunnableC0582b implements Runnable {
        public RunnableC0582b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            for (BleDFUState.IListener iListener : b.this.g()) {
                iListener.onDeviceInDFUMode();
            }
        }
    }

    /* loaded from: classes11.dex */
    public class c implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ int f12161a;

        public c(int i) {
            this.f12161a = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            for (BleDFUState.IListener iListener : b.this.g()) {
                iListener.onProgress(this.f12161a);
            }
        }
    }

    /* loaded from: classes11.dex */
    public class d implements Runnable {
        public d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            for (BleDFUState.IListener iListener : b.this.g()) {
                iListener.onSuccess();
            }
        }
    }

    /* loaded from: classes11.dex */
    public class e implements Runnable {
        public e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            for (BleDFUState.IListener iListener : b.this.g()) {
                iListener.onSuccessAndNeedToPromptUser();
            }
        }
    }

    /* loaded from: classes11.dex */
    public class f implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ BleDFUState.FailReason f12164a;

        public f(BleDFUState.FailReason failReason) {
            this.f12164a = failReason;
        }

        @Override // java.lang.Runnable
        public void run() {
            for (BleDFUState.IListener iListener : b.this.g()) {
                iListener.onFailed(this.f12164a);
            }
        }
    }

    /* loaded from: classes11.dex */
    public class g implements Runnable {
        public g() {
        }

        @Override // java.lang.Runnable
        public void run() {
            for (BleDFUState.IListener iListener : b.this.g()) {
                iListener.onCanceled();
            }
        }
    }

    /* loaded from: classes11.dex */
    public class h implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ int f12166a;

        public h(int i) {
            this.f12166a = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            for (BleDFUState.IListener iListener : b.this.g()) {
                iListener.onRetry(this.f12166a);
            }
        }
    }

    private b() {
    }

    public static b f() {
        if (c == null) {
            c = new b();
        }
        return c;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<BleDFUState.IListener> g() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.f12158a);
        return arrayList;
    }

    public void a() {
        a(new g());
    }

    public void a(int i) {
        a(new c(i));
    }

    public void a(BleDFUState.FailReason failReason) {
        a(new f(failReason));
    }

    public void a(BleDFUState.IListener iListener) {
        this.f12158a.add(iListener);
    }

    public void a(Runnable runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
        } else {
            this.b.post(runnable);
        }
    }

    public void b() {
        a(new RunnableC0582b());
    }

    public void b(int i) {
        a(new h(i));
    }

    public void b(BleDFUState.IListener iListener) {
        this.f12158a.remove(iListener);
    }

    public void c() {
        a(new a());
    }

    public void d() {
        a(new d());
    }

    public void e() {
        a(new e());
    }
}
