package com.mappls.sdk.maps.location;

import android.os.Handler;
import android.os.Message;
import androidx.annotation.NonNull;
import java.lang.ref.WeakReference;
/* loaded from: classes11.dex */
public class v {

    /* renamed from: a  reason: collision with root package name */
    public boolean f12783a;
    public final OnLocationStaleListener b;
    public long e;
    public boolean d = true;
    @NonNull
    public final b c = new b();

    /* loaded from: classes11.dex */
    public static class b extends Handler {

        /* renamed from: a  reason: collision with root package name */
        public final WeakReference<v> f12784a;

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            v vVar = this.f12784a.get();
            if (vVar != null) {
                vVar.g(true);
            }
        }

        public b(v vVar) {
            this.f12784a = new WeakReference<>(vVar);
        }
    }

    public v(OnLocationStaleListener onLocationStaleListener, LocationComponentOptions locationComponentOptions) {
        this.b = onLocationStaleListener;
        this.f12783a = locationComponentOptions.enableStaleState();
        this.e = locationComponentOptions.staleStateTimeout();
    }

    public void b() {
        if (this.d) {
            return;
        }
        d();
    }

    public void c() {
        this.c.removeCallbacksAndMessages(null);
    }

    public final void d() {
        this.c.removeCallbacksAndMessages(null);
        this.c.sendEmptyMessageDelayed(1, this.e);
    }

    public void e(long j) {
        this.e = j;
        if (this.c.hasMessages(1)) {
            d();
        }
    }

    public void f(boolean z) {
        if (z) {
            g(this.d);
        } else if (this.f12783a) {
            c();
            this.b.onStaleStateChange(false);
        }
        this.f12783a = z;
    }

    public final void g(boolean z) {
        if (z != this.d) {
            this.d = z;
            if (this.f12783a) {
                this.b.onStaleStateChange(z);
            }
        }
    }

    public void h(boolean z) {
        g(z);
        if (z) {
            return;
        }
        d();
    }

    public void i() {
        g(false);
        d();
    }
}
