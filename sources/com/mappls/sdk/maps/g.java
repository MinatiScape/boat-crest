package com.mappls.sdk.maps;

import android.os.Handler;
import android.os.Message;
import androidx.annotation.NonNull;
import com.mappls.sdk.maps.MapplsMap;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes11.dex */
public class g implements MapplsMap.OnCameraMoveStartedListener, MapplsMap.OnCameraMoveListener, MapplsMap.OnCameraMoveCanceledListener, MapplsMap.OnCameraIdleListener {
    public int j;
    public final a h = new a(this);
    public boolean i = true;
    public final CopyOnWriteArrayList<MapplsMap.OnCameraMoveStartedListener> k = new CopyOnWriteArrayList<>();
    public final CopyOnWriteArrayList<MapplsMap.OnCameraMoveCanceledListener> l = new CopyOnWriteArrayList<>();
    public final CopyOnWriteArrayList<MapplsMap.OnCameraMoveListener> m = new CopyOnWriteArrayList<>();
    public final CopyOnWriteArrayList<MapplsMap.OnCameraIdleListener> n = new CopyOnWriteArrayList<>();

    /* loaded from: classes11.dex */
    public static class a extends Handler {

        /* renamed from: a  reason: collision with root package name */
        public WeakReference<g> f12725a;

        public a(g gVar) {
            this.f12725a = new WeakReference<>(gVar);
        }

        public void a(int i) {
            g gVar = this.f12725a.get();
            if (gVar != null) {
                if (i == 0) {
                    boolean z = !gVar.i && (hasMessages(3) || hasMessages(2));
                    removeMessages(3);
                    removeMessages(2);
                    if (z) {
                        return;
                    }
                }
                Message message = new Message();
                message.what = i;
                sendMessage(message);
            }
        }

        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            g gVar = this.f12725a.get();
            if (gVar != null) {
                int i = message.what;
                if (i == 0) {
                    gVar.m();
                } else if (i == 1) {
                    gVar.k();
                } else if (i == 2) {
                    gVar.l();
                } else if (i != 3) {
                } else {
                    gVar.j();
                }
            }
        }
    }

    public void f(@NonNull MapplsMap.OnCameraIdleListener onCameraIdleListener) {
        this.n.add(onCameraIdleListener);
    }

    public void g(MapplsMap.OnCameraMoveCanceledListener onCameraMoveCanceledListener) {
        this.l.add(onCameraMoveCanceledListener);
    }

    public void h(MapplsMap.OnCameraMoveListener onCameraMoveListener) {
        this.m.add(onCameraMoveListener);
    }

    public void i(MapplsMap.OnCameraMoveStartedListener onCameraMoveStartedListener) {
        this.k.add(onCameraMoveStartedListener);
    }

    public final void j() {
        if (this.i) {
            return;
        }
        this.i = true;
        if (this.n.isEmpty()) {
            return;
        }
        Iterator<MapplsMap.OnCameraIdleListener> it = this.n.iterator();
        while (it.hasNext()) {
            it.next().onCameraIdle();
        }
    }

    public final void k() {
        if (this.m.isEmpty() || this.i) {
            return;
        }
        Iterator<MapplsMap.OnCameraMoveListener> it = this.m.iterator();
        while (it.hasNext()) {
            it.next().onCameraMove();
        }
    }

    public final void l() {
        if (this.l.isEmpty() || this.i) {
            return;
        }
        Iterator<MapplsMap.OnCameraMoveCanceledListener> it = this.l.iterator();
        while (it.hasNext()) {
            it.next().onCameraMoveCanceled();
        }
    }

    public final void m() {
        if (this.i) {
            this.i = false;
            if (this.k.isEmpty()) {
                return;
            }
            Iterator<MapplsMap.OnCameraMoveStartedListener> it = this.k.iterator();
            while (it.hasNext()) {
                it.next().onCameraMoveStarted(this.j);
            }
        }
    }

    public void n() {
        this.h.removeCallbacksAndMessages(null);
        this.k.clear();
        this.l.clear();
        this.m.clear();
        this.n.clear();
    }

    public void o(@NonNull MapplsMap.OnCameraIdleListener onCameraIdleListener) {
        if (this.n.contains(onCameraIdleListener)) {
            this.n.remove(onCameraIdleListener);
        }
    }

    @Override // com.mappls.sdk.maps.MapplsMap.OnCameraIdleListener
    public void onCameraIdle() {
        this.h.a(3);
    }

    @Override // com.mappls.sdk.maps.MapplsMap.OnCameraMoveListener
    public void onCameraMove() {
        this.h.a(1);
    }

    @Override // com.mappls.sdk.maps.MapplsMap.OnCameraMoveCanceledListener
    public void onCameraMoveCanceled() {
        this.h.a(2);
    }

    @Override // com.mappls.sdk.maps.MapplsMap.OnCameraMoveStartedListener
    public void onCameraMoveStarted(int i) {
        this.j = i;
        this.h.a(0);
    }

    public void p(MapplsMap.OnCameraMoveCanceledListener onCameraMoveCanceledListener) {
        if (this.l.contains(onCameraMoveCanceledListener)) {
            this.l.remove(onCameraMoveCanceledListener);
        }
    }

    public void q(MapplsMap.OnCameraMoveListener onCameraMoveListener) {
        if (this.m.contains(onCameraMoveListener)) {
            this.m.remove(onCameraMoveListener);
        }
    }

    public void r(MapplsMap.OnCameraMoveStartedListener onCameraMoveStartedListener) {
        if (this.k.contains(onCameraMoveStartedListener)) {
            this.k.remove(onCameraMoveStartedListener);
        }
    }
}
