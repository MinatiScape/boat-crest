package com.ido.ble.bluetooth.d;

import android.bluetooth.BluetoothDevice;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.clevertap.android.sdk.Constants;
import com.ido.ble.bluetooth.d.d;
import com.ido.ble.logs.LogTool;
/* loaded from: classes11.dex */
public class a {
    private static a c = null;
    private static int d = 5;

    /* renamed from: a  reason: collision with root package name */
    private int f12099a = 0;
    private Handler b = new Handler(Looper.getMainLooper());

    /* renamed from: com.ido.ble.bluetooth.d.a$a  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public class C0577a implements d.b {
        public C0577a() {
        }

        @Override // com.ido.ble.bluetooth.d.d.b
        public void a(String str) {
            a.this.a(str);
        }
    }

    /* loaded from: classes11.dex */
    public class b implements e {
        public b() {
        }

        @Override // com.ido.ble.bluetooth.d.e
        public void a() {
            LogTool.b(com.ido.ble.logs.a.q, "[BTConnectPresenter] scanBtDevice. not find device");
            a.this.b();
        }

        @Override // com.ido.ble.bluetooth.d.e
        public void a(BluetoothDevice bluetoothDevice) {
            a.this.a(bluetoothDevice);
        }
    }

    /* loaded from: classes11.dex */
    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.c();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(BluetoothDevice bluetoothDevice) {
        LogTool.d(com.ido.ble.logs.a.q, "[BTConnectPresenter] scanBtDevice ok, connect " + bluetoothDevice.getName());
        bluetoothDevice.createBond();
        e();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        LogTool.d(com.ido.ble.logs.a.q, "[BTConnectPresenter] getBTMacAddressOK, mac is " + str);
        if (TextUtils.isEmpty(str)) {
            b();
        } else if (!com.ido.ble.bluetooth.f.e.c(str)) {
            b(str);
        } else {
            LogTool.d(com.ido.ble.logs.a.q, "[BTConnectPresenter] has paired.");
            e();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        LogTool.d(com.ido.ble.logs.a.q, "[BTConnectPresenter] failed.");
        int i = this.f12099a;
        if (i < d) {
            this.f12099a = i + 1;
            LogTool.d(com.ido.ble.logs.a.q, "[BTConnectPresenter] retry connect currentTryTimes:" + this.f12099a);
            this.b.postDelayed(new c(), Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS);
        }
    }

    private void b(String str) {
        LogTool.d(com.ido.ble.logs.a.q, "[BTConnectPresenter] scanBtDevice");
        com.ido.ble.bluetooth.d.b.b().a(str, new b());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        LogTool.d(com.ido.ble.logs.a.q, "[BTConnectPresenter] getBTMacAddress. ");
        d.b().a(new C0577a());
    }

    public static a d() {
        if (c == null) {
            c = new a();
        }
        return c;
    }

    private void e() {
        LogTool.d(com.ido.ble.logs.a.q, "[BTConnectPresenter] sucess.");
    }

    public void a() {
        this.f12099a = 0;
        LogTool.d(com.ido.ble.logs.a.q, "[BTConnectPresenter] connect. currentTryTimes:" + this.f12099a);
        c();
    }
}
