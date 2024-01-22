package com.abupdate.iot_download_libs;

import android.app.Activity;
import android.os.Handler;
import com.abupdate.trace.Trace;
import java.util.List;
/* loaded from: classes.dex */
public class CallBackManager {
    public static CallBackManager mInstance;

    /* renamed from: a  reason: collision with root package name */
    public Handler f1877a;
    public IOnDownListener c;
    public Object b = new Object();
    public boolean d = true;

    /* loaded from: classes.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            CallBackManager.this.f1877a = new Handler();
            CallBackManager.this.e();
        }
    }

    /* loaded from: classes.dex */
    public class b implements Runnable {
        public final /* synthetic */ DownEntity h;
        public final /* synthetic */ int i;
        public final /* synthetic */ long j;
        public final /* synthetic */ long k;

        public b(DownEntity downEntity, int i, long j, long j2) {
            this.h = downEntity;
            this.i = i;
            this.j = j;
            this.k = j2;
        }

        @Override // java.lang.Runnable
        public void run() {
            CallBackManager.this.c.on_progress(this.h, this.i, this.j, this.k);
            CallBackManager.this.e();
        }
    }

    /* loaded from: classes.dex */
    public class c implements Runnable {
        public final /* synthetic */ DownEntity h;

        public c(DownEntity downEntity) {
            this.h = downEntity;
        }

        @Override // java.lang.Runnable
        public void run() {
            CallBackManager.this.c.on_failed(this.h);
            CallBackManager.this.e();
        }
    }

    /* loaded from: classes.dex */
    public class d implements Runnable {
        public final /* synthetic */ DownEntity h;

        public d(DownEntity downEntity) {
            this.h = downEntity;
        }

        @Override // java.lang.Runnable
        public void run() {
            CallBackManager.this.c.on_success(this.h);
            CallBackManager.this.e();
        }
    }

    /* loaded from: classes.dex */
    public class e implements Runnable {
        public final /* synthetic */ int h;
        public final /* synthetic */ long i;
        public final /* synthetic */ long j;

        public e(int i, long j, long j2) {
            this.h = i;
            this.i = j;
            this.j = j2;
        }

        @Override // java.lang.Runnable
        public void run() {
            CallBackManager.this.c.on_all_progress(this.h, this.i, this.j);
            CallBackManager.this.e();
        }
    }

    /* loaded from: classes.dex */
    public class f implements Runnable {
        public f() {
        }

        @Override // java.lang.Runnable
        public void run() {
            CallBackManager.this.c.on_manual_cancel();
            CallBackManager.this.e();
        }
    }

    /* loaded from: classes.dex */
    public class g implements Runnable {
        public final /* synthetic */ List h;
        public final /* synthetic */ List i;

        public g(List list, List list2) {
            this.h = list;
            this.i = list2;
        }

        @Override // java.lang.Runnable
        public void run() {
            CallBackManager.this.c.on_finished(this.h, this.i);
            CallBackManager.this.e();
        }
    }

    public static CallBackManager getInstance() {
        if (mInstance == null) {
            synchronized (CallBackManager.class) {
                if (mInstance == null) {
                    mInstance = new CallBackManager();
                }
            }
        }
        return mInstance;
    }

    public final void d() {
        synchronized (this.b) {
            if (this.d) {
                try {
                    this.b.wait(5000L);
                } catch (Exception e2) {
                    Trace.e("CallBackManager", e2);
                }
            }
        }
    }

    public final void e() {
        synchronized (this.b) {
            try {
                this.d = false;
                this.b.notify();
            } catch (Exception e2) {
                Trace.e("CallBackManager", e2);
            }
        }
    }

    public void on_all_progress(int i, long j, long j2) {
        Handler handler = this.f1877a;
        if (handler == null) {
            this.c.on_all_progress(i, j, j2);
            return;
        }
        this.d = true;
        handler.post(new e(i, j, j2));
        d();
    }

    public void on_failed(DownEntity downEntity) {
        Handler handler = this.f1877a;
        if (handler == null) {
            this.c.on_failed(downEntity);
            return;
        }
        this.d = true;
        handler.post(new c(downEntity));
        d();
    }

    public void on_finished(List<DownEntity> list, List<DownEntity> list2) {
        Handler handler = this.f1877a;
        if (handler == null) {
            this.c.on_finished(list, list2);
            return;
        }
        this.d = true;
        handler.post(new g(list, list2));
        d();
    }

    public void on_manual_cancel() {
        Handler handler = this.f1877a;
        if (handler == null) {
            this.c.on_manual_cancel();
            return;
        }
        this.d = true;
        handler.post(new f());
        d();
    }

    public void on_progress(DownEntity downEntity, int i, long j, long j2) {
        Handler handler = this.f1877a;
        if (handler == null) {
            this.c.on_progress(downEntity, i, j, j2);
            return;
        }
        this.d = true;
        handler.post(new b(downEntity, i, j, j2));
        d();
    }

    public void on_start() {
        this.c.on_start();
    }

    public void on_success(DownEntity downEntity) {
        Handler handler = this.f1877a;
        if (handler == null) {
            this.c.on_success(downEntity);
            return;
        }
        this.d = true;
        handler.post(new d(downEntity));
        d();
    }

    public void setCallbackOnUIThread(Activity activity) {
        this.d = true;
        activity.runOnUiThread(new a());
        d();
    }

    public void setCallbackOnUIThread(Handler handler) {
        this.f1877a = handler;
    }

    public void setListener(IOnDownListener iOnDownListener) {
        this.c = iOnDownListener;
    }
}
