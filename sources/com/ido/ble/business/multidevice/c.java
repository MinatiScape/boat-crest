package com.ido.ble.business.multidevice;

import android.os.Handler;
import android.os.Looper;
import com.ido.ble.bluetooth.connect.ConnectFailedReason;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.logs.LogTool;
/* loaded from: classes11.dex */
public class c {
    private static c g;

    /* renamed from: a  reason: collision with root package name */
    private String f12120a;
    private ICommonListener b;
    private boolean c = false;
    private Handler d = new Handler(Looper.getMainLooper());
    private com.ido.ble.business.multidevice.a e = new a();
    private com.ido.ble.business.multidevice.a f = new b();

    /* loaded from: classes11.dex */
    public class a extends com.ido.ble.business.multidevice.a {
        public a() {
        }

        @Override // com.ido.ble.business.multidevice.a, com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnectFailed(ConnectFailedReason connectFailedReason, String str) {
            c.this.f();
        }

        @Override // com.ido.ble.business.multidevice.a, com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnectSuccess(String str) {
            c.this.g();
        }

        @Override // com.ido.ble.business.multidevice.a, com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onInDfuMode(BLEDevice bLEDevice) {
            c.this.f();
        }
    }

    /* loaded from: classes11.dex */
    public class b extends com.ido.ble.business.multidevice.a {
        public b() {
        }

        @Override // com.ido.ble.business.multidevice.a, com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnectBreak(String str) {
            c.this.a();
        }
    }

    /* renamed from: com.ido.ble.business.multidevice.c$c  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public class RunnableC0579c implements Runnable {
        public RunnableC0579c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            c.this.f();
        }
    }

    /* loaded from: classes11.dex */
    public class d implements Runnable {
        public d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            String str = com.ido.ble.bluetooth.f.b.f12116a;
            LogTool.d(str, "[DeviceSwitchManager] autoconnect:" + c.this.f12120a);
            com.ido.ble.bluetooth.a.b(c.this.f12120a);
        }
    }

    private c() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "[DeviceSwitchManager] currentConnectionBreaked");
        com.ido.ble.callback.b.N().b(this.f);
        i();
    }

    public static c b() {
        if (g == null) {
            g = new c();
        }
        return g;
    }

    private void c() {
        this.b = null;
        this.c = false;
        this.d.removeCallbacksAndMessages(null);
    }

    private void d() {
        this.d.postDelayed(new RunnableC0579c(), 45000L);
    }

    private void e() {
        if (this.c) {
            return;
        }
        LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "[DeviceSwitchManager] startToSwitch");
        this.c = true;
        if (com.ido.ble.bluetooth.a.h()) {
            h();
        } else {
            com.ido.ble.bluetooth.a.b();
            i();
        }
        d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        com.ido.ble.callback.b.N().b(this.e);
        this.b.onFailed(this.f12120a);
        c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        com.ido.ble.callback.b.N().b(this.e);
        this.b.onSuccess(this.f12120a);
        c();
    }

    private void h() {
        com.ido.ble.callback.b.N().a(this.f);
        com.ido.ble.bluetooth.a.b();
    }

    private void i() {
        LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "[DeviceSwitchManager] toConnectNewDevice");
        com.ido.ble.callback.b.N().a(this.e);
        this.d.postDelayed(new d(), 5000L);
    }

    public void a(String str, ICommonListener iCommonListener) {
        this.f12120a = str;
        this.b = iCommonListener;
        e();
    }
}
