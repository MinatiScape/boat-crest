package com.google.zxing.client.android.camera;

import android.hardware.Camera;
import android.os.AsyncTask;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.RejectedExecutionException;
/* loaded from: classes11.dex */
public final class a implements Camera.AutoFocusCallback {
    public static final String g = a.class.getSimpleName();
    public static final Collection<String> h;

    /* renamed from: a  reason: collision with root package name */
    public long f11779a = 5000;
    public boolean b;
    public boolean c;
    public final boolean d;
    public final Camera e;
    public AsyncTask<?, ?, ?> f;

    /* loaded from: classes11.dex */
    public final class b extends AsyncTask<Object, Object, Object> {
        public b() {
        }

        @Override // android.os.AsyncTask
        public Object doInBackground(Object... objArr) {
            try {
                Thread.sleep(a.this.f11779a);
            } catch (InterruptedException unused) {
            }
            a.this.e();
            return null;
        }
    }

    static {
        ArrayList arrayList = new ArrayList(2);
        h = arrayList;
        arrayList.add("auto");
        arrayList.add("macro");
    }

    public a(Camera camera) {
        this.e = camera;
        String focusMode = camera.getParameters().getFocusMode();
        boolean contains = h.contains(focusMode);
        this.d = contains;
        String str = g;
        Log.i(str, "Current focus mode '" + focusMode + "'; use auto focus? " + contains);
        e();
    }

    public final synchronized void b() {
        if (!this.b && this.f == null) {
            b bVar = new b();
            try {
                bVar.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[0]);
                this.f = bVar;
            } catch (RejectedExecutionException e) {
                Log.w(g, "Could not request auto focus", e);
            }
        }
    }

    public final synchronized void c() {
        AsyncTask<?, ?, ?> asyncTask = this.f;
        if (asyncTask != null) {
            if (asyncTask.getStatus() != AsyncTask.Status.FINISHED) {
                this.f.cancel(true);
            }
            this.f = null;
        }
    }

    public void d(long j) {
        if (j > 0) {
            this.f11779a = j;
            return;
        }
        throw new IllegalArgumentException("AutoFocusInterval must be greater than 0.");
    }

    public synchronized void e() {
        if (this.d) {
            this.f = null;
            if (!this.b && !this.c) {
                try {
                    this.e.autoFocus(this);
                    this.c = true;
                } catch (RuntimeException e) {
                    Log.w(g, "Unexpected exception while focusing", e);
                    b();
                }
            }
        }
    }

    public synchronized void f() {
        this.b = true;
        if (this.d) {
            c();
            try {
                this.e.cancelAutoFocus();
            } catch (RuntimeException e) {
                Log.w(g, "Unexpected exception while cancelling focusing", e);
            }
        }
    }

    @Override // android.hardware.Camera.AutoFocusCallback
    public synchronized void onAutoFocus(boolean z, Camera camera) {
        this.c = false;
        b();
    }
}
