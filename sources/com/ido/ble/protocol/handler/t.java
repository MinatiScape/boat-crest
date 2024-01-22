package com.ido.ble.protocol.handler;

import android.os.Handler;
import android.os.Looper;
import com.ido.ble.logs.LogTool;
/* loaded from: classes11.dex */
public class t {

    /* renamed from: a  reason: collision with root package name */
    private static Handler f12311a = new Handler(Looper.getMainLooper());

    /* loaded from: classes11.dex */
    public static class a implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ int f12312a;
        public final /* synthetic */ int b;
        public final /* synthetic */ int c;
        public final /* synthetic */ int d;

        public a(int i, int i2, int i3, int i4) {
            this.f12312a = i;
            this.b = i2;
            this.c = i3;
            this.d = i4;
        }

        @Override // java.lang.Runnable
        public void run() {
            t.c(this.f12312a, this.b, this.c, this.d);
        }
    }

    /* loaded from: classes11.dex */
    public static class b implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ byte[] f12313a;
        public final /* synthetic */ int b;
        public final /* synthetic */ int c;

        public b(byte[] bArr, int i, int i2) {
            this.f12313a = bArr;
            this.b = i;
            this.c = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            t.c(this.f12313a, this.b, this.c);
        }
    }

    public static int a(byte[] bArr) {
        com.ido.ble.bluetooth.a.a(bArr);
        return 0;
    }

    public static int b(byte[] bArr) {
        com.ido.ble.bluetooth.d.c.a(bArr);
        return 0;
    }

    public static void b(int i, int i2, int i3, int i4) {
        f12311a.post(new a(i, i2, i3, i4));
    }

    public static void b(byte[] bArr, int i, int i2) {
        f12311a.post(new b(bArr, i, i2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(int i, int i2, int i3, int i4) {
        if (i == 9728) {
            s.a(i2);
        } else if (i2 == 403) {
            q.a(i2, i3, i4);
        } else if (j.a(i2)) {
            j.a(i2, i3, i4);
        } else if (l.a(i2)) {
            l.a(i2, i3, i4);
        } else if (r.a(i2)) {
            r.a(i2, i3, i4);
        } else if (c.a(i2)) {
            c.a(i2, i3, i4);
        } else if (v.b(i2)) {
            v.a(i2, i3, i4);
        } else if (p.a(i2)) {
            p.a(i2, i3, i4);
        } else if (com.ido.ble.protocol.handler.a.a(i2)) {
            com.ido.ble.protocol.handler.a.a(i2, i3, i4);
        } else if (e.a(i2)) {
            e.a(i2, i3, i4);
        } else if (SyncHandler.a(i2)) {
            SyncHandler.a(i2, i3, i4);
        } else if (com.ido.ble.protocol.handler.b.a(i2)) {
            com.ido.ble.protocol.handler.b.a(i2, i3, i4);
        } else if (d.a(i2)) {
            d.a(i2, i3, i4);
        } else if (q.a(i2)) {
            q.a(i2, i3, i4);
        } else if (com.ido.ble.h.d.c(i2)) {
            com.ido.ble.h.d.a(i2, i3, i4);
        } else if (o.a(i2)) {
            o.a(i2, i3, i4);
        } else if (SyncV3Handler.isSyncV3Type(i2)) {
            SyncV3Handler.handleIntResult(i2, i3, i4);
        } else if (m.a(i2)) {
            m.a(i2, i3, i4);
        } else if (g.a(i2)) {
            g.a(i2, i3, i4);
        } else if (com.ido.ble.watch.custom.c.a(i2)) {
            com.ido.ble.watch.custom.c.a(i2, i3, i4);
        } else if (x.a(i2)) {
            x.a(i2, i3, i4);
        } else if (f.a(i2)) {
            f.a(i2, i3, i4);
        } else if (w.a(i2)) {
            w.a(i2, i3, i4);
        } else if (i.a(i2)) {
            i.a(i2, i3, i4);
        } else if (n.a(i2)) {
            n.a(i2, i3, i4);
        } else {
            LogTool.b("SoLibCallBackDispatcher", "[handleCallBackSysEvt] need handle type = " + i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(byte[] bArr, int i, int i2) {
        if (j.a(i)) {
            j.a(i, bArr, i2);
        } else if (l.a(i)) {
            l.a(i, bArr, i2);
        } else if (r.a(i)) {
            r.a(i, bArr, i2);
        } else if (c.a(i)) {
            c.a(i, bArr, i2);
        } else if (v.b(i)) {
            v.a(i, bArr, i2);
        } else if (p.a(i)) {
            p.a(i, bArr, i2);
        } else if (com.ido.ble.protocol.handler.a.a(i)) {
            com.ido.ble.protocol.handler.a.a(i, bArr, i2);
        } else if (e.a(i)) {
            e.a(i, bArr, i2);
        } else if (SyncHandler.a(i)) {
            SyncHandler.a(i, bArr, i2);
        } else if (com.ido.ble.protocol.handler.b.a(i)) {
            com.ido.ble.protocol.handler.b.a(i, bArr, i2);
        } else if (d.a(i)) {
            d.a(i, bArr, i2);
        } else if (q.a(i)) {
            q.a(i, bArr, i2);
        } else if (com.ido.ble.h.d.c(i)) {
            com.ido.ble.h.d.a(i, bArr, i2);
        } else if (o.a(i)) {
            o.a(i, bArr, i2);
        } else if (SyncV3Handler.isSyncV3Type(i)) {
            SyncV3Handler.handleJsonResult(i, bArr, i2);
        } else if (m.a(i)) {
            m.a(i, bArr, i2);
        } else if (g.a(i)) {
            g.a(i, bArr, i2);
        } else if (com.ido.ble.watch.custom.c.a(i)) {
            com.ido.ble.watch.custom.c.a(i, bArr, i2);
        } else if (x.a(i)) {
            x.a(i, bArr, i2);
        } else if (f.a(i)) {
            f.a(i, bArr, i2);
        } else if (w.a(i)) {
            w.a(i, bArr, i2);
        } else if (i.a(i)) {
            i.a(i, bArr, i2);
        } else if (n.a(i)) {
            n.a(i, bArr, i2);
        } else {
            h.a(bArr, i, i2);
            LogTool.b("SoLibCallBackDispatcher", "[handleCallBackJsonData] need handle type = " + i);
        }
    }
}
