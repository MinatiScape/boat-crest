package com.htsmart.wristband2.dfu;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Handler;
import androidx.annotation.Nullable;
import com.htsmart.wristband2.dfu.h;
import com.htsmart.wristband2.utils.WristbandLog;
/* loaded from: classes11.dex */
public class b implements h {

    /* renamed from: a  reason: collision with root package name */
    public h.a f11995a;
    public boolean b;
    public String c;
    public String d;
    public int e;
    public boolean h;
    public Handler g = new Handler();
    public BluetoothAdapter.LeScanCallback i = new C0565b();
    public BluetoothAdapter f = BluetoothAdapter.getDefaultAdapter();

    /* loaded from: classes11.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            b.this.b(1);
            b.this.h = false;
            b.this.f.stopLeScan(b.this.i);
        }
    }

    /* renamed from: com.htsmart.wristband2.dfu.b$b  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public class C0565b implements BluetoothAdapter.LeScanCallback {
        public C0565b() {
        }

        /* JADX WARN: Code restructure failed: missing block: B:11:0x0056, code lost:
            if (android.text.TextUtils.isEmpty(r3.f11996a.c) == false) goto L12;
         */
        /* JADX WARN: Code restructure failed: missing block: B:14:0x0064, code lost:
            if (android.text.TextUtils.isEmpty(r3.f11996a.d) == false) goto L12;
         */
        /* JADX WARN: Code restructure failed: missing block: B:19:0x007f, code lost:
            if (r4.getName().toUpperCase().equals("BEETGT") != false) goto L12;
         */
        @Override // android.bluetooth.BluetoothAdapter.LeScanCallback
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void onLeScan(android.bluetooth.BluetoothDevice r4, int r5, byte[] r6) {
            /*
                r3 = this;
                r5 = 2
                java.lang.Object[] r5 = new java.lang.Object[r5]
                java.lang.String r6 = r4.getName()
                r0 = 0
                r5[r0] = r6
                java.lang.String r6 = r4.getAddress()
                r1 = 1
                r5[r1] = r6
                java.lang.String r6 = "onLeScan device name=%s , address=%s"
                com.htsmart.wristband2.utils.WristbandLog.i(r6, r5)
                com.htsmart.wristband2.dfu.b r5 = com.htsmart.wristband2.dfu.b.this
                boolean r5 = com.htsmart.wristband2.dfu.b.g(r5)
                if (r5 != 0) goto L26
                java.lang.Object[] r4 = new java.lang.Object[r0]
                java.lang.String r5 = "is already stop the le scan, do not do anything"
                com.htsmart.wristband2.utils.WristbandLog.w(r5, r4)
                return
            L26:
                com.htsmart.wristband2.dfu.b r5 = com.htsmart.wristband2.dfu.b.this
                int r5 = com.htsmart.wristband2.dfu.b.l(r5)
                if (r5 == 0) goto L67
                java.lang.String r5 = r4.getName()
                com.htsmart.wristband2.dfu.b r6 = com.htsmart.wristband2.dfu.b.this
                java.lang.String r6 = com.htsmart.wristband2.dfu.b.m(r6)
                boolean r5 = android.text.TextUtils.equals(r5, r6)
                java.lang.String r6 = r4.getAddress()
                com.htsmart.wristband2.dfu.b r2 = com.htsmart.wristband2.dfu.b.this
                java.lang.String r2 = com.htsmart.wristband2.dfu.b.n(r2)
                boolean r6 = android.text.TextUtils.equals(r6, r2)
                if (r5 == 0) goto L58
                com.htsmart.wristband2.dfu.b r5 = com.htsmart.wristband2.dfu.b.this
                java.lang.String r5 = com.htsmart.wristband2.dfu.b.m(r5)
                boolean r5 = android.text.TextUtils.isEmpty(r5)
                if (r5 == 0) goto L83
            L58:
                if (r6 == 0) goto L82
                com.htsmart.wristband2.dfu.b r5 = com.htsmart.wristband2.dfu.b.this
                java.lang.String r5 = com.htsmart.wristband2.dfu.b.n(r5)
                boolean r5 = android.text.TextUtils.isEmpty(r5)
                if (r5 != 0) goto L82
                goto L83
            L67:
                java.lang.String r5 = r4.getName()
                boolean r5 = android.text.TextUtils.isEmpty(r5)
                if (r5 != 0) goto L82
                java.lang.String r5 = r4.getName()
                java.lang.String r5 = r5.toUpperCase()
                java.lang.String r6 = "BEETGT"
                boolean r5 = r5.equals(r6)
                if (r5 == 0) goto L82
                goto L83
            L82:
                r1 = r0
            L83:
                if (r1 == 0) goto L8f
                com.htsmart.wristband2.dfu.b r5 = com.htsmart.wristband2.dfu.b.this
                com.htsmart.wristband2.dfu.b.e(r5, r4)
                com.htsmart.wristband2.dfu.b r4 = com.htsmart.wristband2.dfu.b.this
                com.htsmart.wristband2.dfu.b.j(r4, r0)
            L8f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.htsmart.wristband2.dfu.b.C0565b.onLeScan(android.bluetooth.BluetoothDevice, int, byte[]):void");
        }
    }

    @Override // com.htsmart.wristband2.dfu.h
    public void a() {
        this.b = true;
        this.f11995a = null;
        f(false);
    }

    @Override // com.htsmart.wristband2.dfu.h
    public void a(h.a aVar) {
        this.f11995a = aVar;
    }

    @Override // com.htsmart.wristband2.dfu.h
    public void a(@Nullable String str, @Nullable String str2, int i) {
        WristbandLog.i("dfuMode=%d , deviceName=%s , deviceAddress=%s", Integer.valueOf(i), str, str2);
        this.b = false;
        this.c = str;
        this.d = str2;
        this.e = i;
        BluetoothAdapter bluetoothAdapter = this.f;
        if (bluetoothAdapter == null) {
            b(Integer.MAX_VALUE);
        } else if (i == 2 || i == 3) {
            c(bluetoothAdapter.getRemoteDevice(str2));
        } else {
            f(true);
        }
    }

    public final void b(int i) {
        if (this.b) {
            return;
        }
        this.b = true;
        h.a aVar = this.f11995a;
        if (aVar != null) {
            aVar.a(i);
        }
    }

    public final void c(BluetoothDevice bluetoothDevice) {
        if (this.b) {
            return;
        }
        this.b = true;
        h.a aVar = this.f11995a;
        if (aVar != null) {
            aVar.a(bluetoothDevice);
        }
    }

    @Override // com.htsmart.wristband2.dfu.h
    public void cancel() {
        this.b = true;
        f(false);
    }

    public final void f(boolean z) {
        if (z && !this.f.isEnabled()) {
            b(0);
        } else if (!z) {
            this.h = false;
            this.f.stopLeScan(this.i);
            this.g.removeCallbacksAndMessages(null);
        } else {
            this.g.removeCallbacksAndMessages(null);
            boolean startLeScan = this.f.startLeScan(this.i);
            this.h = startLeScan;
            if (startLeScan) {
                this.g.postDelayed(new a(), 120000L);
            } else {
                b(0);
            }
        }
    }
}
