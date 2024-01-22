package com.ido.ble.g.a.c;

import com.ido.ble.bluetooth.connect.ConnectFailedReason;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.callback.ConnectCallBack;
import com.ido.ble.callback.ScanCallBack;
import java.util.ArrayList;
import java.util.Collections;
/* loaded from: classes11.dex */
public class e {
    private static e f;

    /* renamed from: a  reason: collision with root package name */
    private boolean f12286a = false;
    private boolean b = false;
    private ArrayList<BLEDevice> c = new ArrayList<>();
    private ScanCallBack.ICallBack d = new a();
    private ConnectCallBack.ICallBack e = new b();

    /* loaded from: classes11.dex */
    public class a implements ScanCallBack.ICallBack {
        public a() {
        }

        @Override // com.ido.ble.callback.ScanCallBack.ICallBack
        public void onFindDevice(BLEDevice bLEDevice) {
            e.this.c.add(bLEDevice);
            Collections.sort(e.this.c);
        }

        @Override // com.ido.ble.callback.ScanCallBack.ICallBack
        public void onScanFinished() {
            if (e.this.c.size() == 0) {
                d.e();
            }
            e.this.f12286a = true;
        }

        @Override // com.ido.ble.callback.ScanCallBack.ICallBack
        public void onStart() {
            e.this.c.clear();
            if (e.this.f12286a) {
                d.f();
            }
            e.this.f12286a = false;
            e.this.b = true;
        }
    }

    /* loaded from: classes11.dex */
    public class b implements ConnectCallBack.ICallBack {
        public b() {
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnectBreak(String str) {
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnectFailed(ConnectFailedReason connectFailedReason, String str) {
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnectStart(String str) {
            if (e.this.b) {
                d.g();
                e.this.b = false;
            }
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnectSuccess(String str) {
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnecting(String str) {
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onDeviceInNotBindStatus(String str) {
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onInDfuMode(BLEDevice bLEDevice) {
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onInitCompleted(String str) {
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onRetry(int i, String str) {
        }
    }

    private e() {
    }

    private void a() {
        com.ido.ble.callback.b.N().b(this.d);
        com.ido.ble.callback.b.N().b(this.e);
    }

    public static e b() {
        if (f == null) {
            f = new e();
        }
        return f;
    }

    private void c() {
        com.ido.ble.callback.b.N().a(this.d);
        com.ido.ble.callback.b.N().a(this.e);
    }

    public void a(boolean z) {
        if (z) {
            c();
        } else {
            a();
        }
    }
}
