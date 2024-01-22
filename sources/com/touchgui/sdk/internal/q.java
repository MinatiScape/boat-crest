package com.touchgui.sdk.internal;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.touchgui.sdk.TGLogger;
/* loaded from: classes12.dex */
public final class q {

    /* renamed from: a  reason: collision with root package name */
    public final Context f13815a;
    public final a0 b;
    public BluetoothManager e;
    public BluetoothAdapter f;
    public BluetoothGatt g;
    public String n;
    public j o;
    public boolean p;
    public long q;
    public o t;
    public boolean c = false;
    public boolean d = false;
    public BluetoothDevice h = null;
    public n i = null;
    public m j = null;
    public int k = 0;
    public final Object l = new Object();
    public int m = 10;
    public p r = null;
    public final Runnable s = new Runnable() { // from class: com.touchgui.sdk.internal.rc
        @Override // java.lang.Runnable
        public final void run() {
            q.this.f();
        }
    };
    public final Handler u = new Handler(Looper.getMainLooper());
    public final k v = new k(this);
    public final l w = new l(this);

    public q(a0 a0Var) {
        this.b = a0Var;
        this.f13815a = a0Var.b();
    }

    public static boolean a(q qVar) {
        boolean z;
        synchronized (qVar.l) {
            String str = qVar.n;
            z = false;
            if (str != null) {
                TGLogger.d(str, "connectState=" + qVar.k);
                int i = qVar.k;
                if (i == 1) {
                    if (qVar.p && System.currentTimeMillis() - qVar.q > 10000) {
                        qVar.h();
                        qVar.b(false);
                    }
                } else if (i == 2) {
                    z = true;
                } else {
                    qVar.h = qVar.f.getRemoteDevice(qVar.n);
                    qVar.a(1);
                    TGLogger.i(qVar.n, "reconnect device, address=" + qVar.n);
                    qVar.d(true);
                }
            }
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        TGLogger.d(this.n, "discoverServices 1");
        if (this.g != null) {
            TGLogger.d(this.n, "discoverServices 2");
            if (c8.a(this.f13815a)) {
                this.g.discoverServices();
            }
        }
    }

    public final void b() {
        a(true, false);
    }

    public final void c() {
        if (!c8.a(this.f13815a)) {
            TGLogger.d("connectDevice: no permission, " + this.n);
            a(0);
            return;
        }
        TGLogger.d("connectDevice, " + this.n);
        this.p = true;
        this.q = System.currentTimeMillis();
        BluetoothGatt bluetoothGatt = this.g;
        if (bluetoothGatt != null) {
            bluetoothGatt.disconnect();
            this.g.connect();
            return;
        }
        BluetoothDevice bluetoothDevice = this.h;
        if (bluetoothDevice != null) {
            if (Build.VERSION.SDK_INT >= 23) {
                this.g = bluetoothDevice.connectGatt(this.f13815a, false, this.w, 2);
            } else {
                this.g = bluetoothDevice.connectGatt(this.f13815a, false, this.w);
            }
        }
    }

    public final void d() {
        if (this.t != null) {
            TGLogger.d(this.n, "Turn off reconnect");
            o oVar = this.t;
            oVar.f13804a = false;
            oVar.d.u.removeCallbacks(oVar.c);
            this.t = null;
        }
    }

    public final boolean e() {
        synchronized (this.l) {
            if (this.c) {
                return true;
            }
            if (this.e == null) {
                this.e = (BluetoothManager) this.f13815a.getSystemService("bluetooth");
            }
            BluetoothManager bluetoothManager = this.e;
            if (bluetoothManager != null) {
                BluetoothAdapter adapter = bluetoothManager.getAdapter();
                this.f = adapter;
                this.m = adapter.getState();
            }
            TGLogger.d("register bluetooth receiver");
            this.f13815a.registerReceiver(this.v, new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED"));
            this.c = true;
            return true;
        }
    }

    public final void g() {
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            final String str = this.n;
            final int i = this.k;
            this.u.post(new Runnable() { // from class: com.touchgui.sdk.internal.sc
                @Override // java.lang.Runnable
                public final void run() {
                    q.this.a(str, i);
                }
            });
            return;
        }
        n nVar = this.i;
        if (nVar != null) {
            g gVar = (g) nVar;
            gVar.a(this.k, this.n);
        }
    }

    public final void h() {
        int i;
        if (this.g != null) {
            TGLogger.d(this.n, "BleService$disconnect");
            if (c8.a(this.f13815a)) {
                this.g.disconnect();
            }
        }
        if (TextUtils.isEmpty(this.n) || (i = this.k) == 3 || i == 0) {
            return;
        }
        a(3);
    }

    public final boolean i() {
        BluetoothGatt bluetoothGatt;
        if (c8.a(this.f13815a) && this.f != null && (bluetoothGatt = this.g) != null) {
            if (this.k == 2) {
                return bluetoothGatt.requestMtu(247);
            }
        }
        return false;
    }

    public final void b(boolean z) {
        int i;
        b(z, (this.n == null || (i = this.m) == 10 || i == 13) ? false : true);
    }

    public final void a() {
        if (this.g != null) {
            if (c8.a(this.f13815a)) {
                this.g.disconnect();
                this.g.close();
            }
            this.g = null;
        }
    }

    public final void a(boolean z, boolean z2) {
        synchronized (this.l) {
            j jVar = this.o;
            if (jVar != null && jVar.e.get()) {
                this.r.b = z;
                this.o.a(true).booleanValue();
            } else if (z2) {
                b(z, false);
            } else {
                b(z);
            }
        }
    }

    public final void b(boolean z, boolean z2) {
        this.p = false;
        if (TextUtils.isEmpty(this.n)) {
            return;
        }
        String str = this.n;
        TGLogger.i(str, "close, connectState=" + this.k + ", clearAddress=" + z + ", reconnect=" + z2);
        if (this.k != 0) {
            a(0);
        }
        if (z && this.g != null) {
            if (c8.a(this.f13815a)) {
                this.g.close();
            }
            this.g = null;
        }
        this.u.removeCallbacks(this.s);
        if (z) {
            this.n = null;
            this.h = null;
            d();
        } else if (z2) {
            if (this.d && this.t == null) {
                TGLogger.d(this.n, "Turn on reconnect");
                o oVar = new o(this);
                this.t = oVar;
                oVar.a();
            }
        } else {
            d();
        }
    }

    public final void c(final boolean z) {
        this.u.post(new Runnable() { // from class: com.touchgui.sdk.internal.tc
            @Override // java.lang.Runnable
            public final void run() {
                q.this.a(z);
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:48:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void d(boolean r6) {
        /*
            r5 = this;
            com.touchgui.sdk.internal.a0 r0 = r5.b
            int r0 = r0.d()
            java.lang.String r1 = r5.n
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "tryScanAndConnectDevice, deviceMode="
            r2.<init>(r3)
            r2.append(r0)
            java.lang.String r2 = r2.toString()
            com.touchgui.sdk.TGLogger.d(r1, r2)
            android.content.Context r1 = r5.f13815a
            boolean r1 = com.touchgui.sdk.internal.c8.a(r1)
            r2 = 1
            r3 = 0
            if (r1 != 0) goto L24
            goto L38
        L24:
            android.bluetooth.BluetoothAdapter r1 = r5.f
            java.util.Set r1 = r1.getBondedDevices()
            android.bluetooth.BluetoothDevice r4 = r5.h
            if (r4 == 0) goto L38
            if (r1 == 0) goto L38
            boolean r1 = r1.contains(r4)
            if (r1 == 0) goto L38
            r1 = r2
            goto L39
        L38:
            r1 = r3
        L39:
            if (r1 == 0) goto L3d
            goto Laf
        L3d:
            android.content.Context r1 = r5.f13815a
            boolean r1 = com.touchgui.sdk.internal.c8.b(r1)
            if (r1 == 0) goto Laf
            if (r6 == 0) goto Laf
            r6 = -1
            if (r0 == r6) goto L55
            com.touchgui.sdk.internal.a0 r6 = r5.b
            r0 = 34146592(0x2090920, float:1.0067789E-37)
            boolean r6 = r6.a(r0)
            if (r6 == 0) goto Laf
        L55:
            com.touchgui.sdk.internal.j r6 = r5.o
            if (r6 != 0) goto L7c
            com.touchgui.sdk.internal.j r6 = new com.touchgui.sdk.internal.j
            android.content.Context r0 = r5.f13815a
            r6.<init>(r0)
            r5.o = r6
            com.touchgui.sdk.internal.p r0 = new com.touchgui.sdk.internal.p
            r0.<init>(r5)
            r5.r = r0
            monitor-enter(r6)
            java.util.concurrent.CopyOnWriteArrayList r1 = r6.f     // Catch: java.lang.Throwable -> L79
            boolean r1 = r1.contains(r0)     // Catch: java.lang.Throwable -> L79
            if (r1 != 0) goto L77
            java.util.concurrent.CopyOnWriteArrayList r1 = r6.f     // Catch: java.lang.Throwable -> L79
            r1.add(r0)     // Catch: java.lang.Throwable -> L79
        L77:
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L79
            goto L7c
        L79:
            r0 = move-exception
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L79
            throw r0
        L7c:
            com.touchgui.sdk.internal.p r6 = r5.r
            r6.f13810a = r3
            r6.b = r3
            android.content.Context r6 = r5.f13815a
            java.lang.String r0 = "power"
            java.lang.Object r6 = r6.getSystemService(r0)
            android.os.PowerManager r6 = (android.os.PowerManager) r6
            boolean r6 = r6.isInteractive()
            com.touchgui.sdk.internal.j r0 = r5.o
            java.lang.String r1 = r5.n
            r4 = 20
            boolean r6 = r0.a(r1, r4, r6)
            if (r6 != 0) goto Lb2
            com.touchgui.sdk.internal.o r6 = r5.t
            if (r6 == 0) goto La5
            boolean r6 = r6.f13804a
            if (r6 == 0) goto La5
            goto La6
        La5:
            r2 = r3
        La6:
            if (r2 == 0) goto Lab
            r5.d()
        Lab:
            r5.b(r3)
            goto Lb2
        Laf:
            r5.c()
        Lb2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.touchgui.sdk.internal.q.d(boolean):void");
    }

    public final boolean b(String str, boolean z) {
        int i;
        if (str.equals(this.n)) {
            o oVar = this.t;
            if (oVar != null && oVar.f13804a) {
                TGLogger.d(this.n, "device is reconnecting: ".concat(str));
                return true;
            }
        }
        String str2 = this.n;
        TGLogger.d(str2, "address=" + str + ", current=" + this.n + ", state=" + this.k);
        if (str.equals(this.n) && ((i = this.k) == 1 || i == 2)) {
            String str3 = this.n;
            TGLogger.d(str3, "device is connecting or connected: " + this.k);
            d();
            return true;
        }
        h();
        b(true);
        BluetoothDevice remoteDevice = this.f.getRemoteDevice(str);
        this.h = remoteDevice;
        if (remoteDevice == null) {
            TGLogger.e(this.n, "The Bluetooth device is not found and cannot be connected");
            return false;
        }
        this.m = this.f.getState();
        String str4 = this.n;
        TGLogger.i(str4, "bluetoothState=" + this.m + ", autoConnect=" + this.d);
        if (this.m != 12) {
            if (this.d) {
                this.n = str;
            }
            return false;
        }
        this.n = str;
        TGLogger.i(str, "connect device, address=" + this.n);
        a(1);
        d(z);
        return true;
    }

    public final boolean a(String str, boolean z) {
        if (BluetoothAdapter.checkBluetoothAddress(str)) {
            synchronized (this.l) {
                if (this.f != null && str != null) {
                    return b(str, z);
                }
                TGLogger.e(this.n, "The Bluetooth adapter is not initialized to obtain the mac address is not specified");
                return false;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(String str, int i) {
        n nVar = this.i;
        if (nVar != null) {
            ((g) nVar).a(i, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        synchronized (this.l) {
            if (z) {
                a();
            }
            c();
            if (this.d && this.t == null) {
                TGLogger.d(this.n, "Turn on reconnect");
                o oVar = new o(this);
                this.t = oVar;
                oVar.a();
            }
        }
    }

    public final void a(int i) {
        if (this.k != i) {
            this.k = i;
        }
        g();
    }
}
