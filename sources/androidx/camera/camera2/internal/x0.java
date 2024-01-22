package androidx.camera.camera2.internal;

import android.hardware.camera2.CameraDevice;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public class x0 {
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public final Executor f598a;
    public final Object b = new Object();
    @GuardedBy("mLock")
    public final Set<SynchronizedCaptureSession> c = new LinkedHashSet();
    @GuardedBy("mLock")
    public final Set<SynchronizedCaptureSession> d = new LinkedHashSet();
    @GuardedBy("mLock")
    public final Set<SynchronizedCaptureSession> e = new LinkedHashSet();
    public final CameraDevice.StateCallback f = new a();

    /* loaded from: classes.dex */
    public class a extends CameraDevice.StateCallback {
        public a() {
        }

        public final void b() {
            List<SynchronizedCaptureSession> g;
            synchronized (x0.this.b) {
                g = x0.this.g();
                x0.this.e.clear();
                x0.this.c.clear();
                x0.this.d.clear();
            }
            for (SynchronizedCaptureSession synchronizedCaptureSession : g) {
                synchronizedCaptureSession.c();
            }
        }

        public final void c() {
            final LinkedHashSet linkedHashSet = new LinkedHashSet();
            synchronized (x0.this.b) {
                linkedHashSet.addAll(x0.this.e);
                linkedHashSet.addAll(x0.this.c);
            }
            x0.this.f598a.execute(new Runnable() { // from class: androidx.camera.camera2.internal.w0
                @Override // java.lang.Runnable
                public final void run() {
                    x0.b(linkedHashSet);
                }
            });
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onClosed(@NonNull CameraDevice cameraDevice) {
            b();
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onDisconnected(@NonNull CameraDevice cameraDevice) {
            c();
            b();
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onError(@NonNull CameraDevice cameraDevice, int i) {
            c();
            b();
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onOpened(@NonNull CameraDevice cameraDevice) {
        }
    }

    public x0(@NonNull Executor executor) {
        this.f598a = executor;
    }

    public static void b(@NonNull Set<SynchronizedCaptureSession> set) {
        for (SynchronizedCaptureSession synchronizedCaptureSession : set) {
            synchronizedCaptureSession.b().o(synchronizedCaptureSession);
        }
    }

    public final void a(@NonNull SynchronizedCaptureSession synchronizedCaptureSession) {
        SynchronizedCaptureSession next;
        Iterator<SynchronizedCaptureSession> it = g().iterator();
        while (it.hasNext() && (next = it.next()) != synchronizedCaptureSession) {
            next.c();
        }
    }

    @NonNull
    public CameraDevice.StateCallback c() {
        return this.f;
    }

    @NonNull
    public List<SynchronizedCaptureSession> d() {
        ArrayList arrayList;
        synchronized (this.b) {
            arrayList = new ArrayList(this.c);
        }
        return arrayList;
    }

    @NonNull
    public List<SynchronizedCaptureSession> e() {
        ArrayList arrayList;
        synchronized (this.b) {
            arrayList = new ArrayList(this.d);
        }
        return arrayList;
    }

    @NonNull
    public List<SynchronizedCaptureSession> f() {
        ArrayList arrayList;
        synchronized (this.b) {
            arrayList = new ArrayList(this.e);
        }
        return arrayList;
    }

    @NonNull
    public List<SynchronizedCaptureSession> g() {
        ArrayList arrayList;
        synchronized (this.b) {
            arrayList = new ArrayList();
            arrayList.addAll(d());
            arrayList.addAll(f());
        }
        return arrayList;
    }

    public void h(@NonNull SynchronizedCaptureSession synchronizedCaptureSession) {
        synchronized (this.b) {
            this.c.remove(synchronizedCaptureSession);
            this.d.remove(synchronizedCaptureSession);
        }
    }

    public void i(@NonNull SynchronizedCaptureSession synchronizedCaptureSession) {
        synchronized (this.b) {
            this.d.add(synchronizedCaptureSession);
        }
    }

    public void j(@NonNull SynchronizedCaptureSession synchronizedCaptureSession) {
        a(synchronizedCaptureSession);
        synchronized (this.b) {
            this.e.remove(synchronizedCaptureSession);
        }
    }

    public void k(@NonNull SynchronizedCaptureSession synchronizedCaptureSession) {
        synchronized (this.b) {
            this.c.add(synchronizedCaptureSession);
            this.e.remove(synchronizedCaptureSession);
        }
        a(synchronizedCaptureSession);
    }

    public void l(@NonNull SynchronizedCaptureSession synchronizedCaptureSession) {
        synchronized (this.b) {
            this.e.add(synchronizedCaptureSession);
        }
    }
}
