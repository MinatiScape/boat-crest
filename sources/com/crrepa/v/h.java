package com.crrepa.v;

import android.text.TextUtils;
import com.crrepa.ble.CRPBleClient;
import com.crrepa.ble.conn.CRPBleDevice;
import com.crrepa.ble.conn.listener.CRPBleConnectionStateListener;
import com.crrepa.ble.conn.listener.CRPBleFirmwareUpgradeListener;
import com.crrepa.ble.scan.bean.CRPScanDevice;
import com.crrepa.ble.scan.callback.CRPScanCallback;
import com.crrepa.ble.trans.upgrade.bean.HSFirmwareInfo;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.List;
/* loaded from: classes9.dex */
public class h {

    /* renamed from: a  reason: collision with root package name */
    public String f7850a;
    public HSFirmwareInfo b;
    public CRPBleFirmwareUpgradeListener c;
    public CRPScanCallback d = new d(this);
    public CRPBleConnectionStateListener e = new c(this);
    public boolean f = false;
    public boolean g = false;
    public int h = 0;
    public CRPBleClient i = CRPBleClient.create(com.crrepa.i0.f.a());
    public CRPBleDevice j;
    public com.crrepa.v.b k;

    /* loaded from: classes9.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            h.this.q();
        }
    }

    /* loaded from: classes9.dex */
    public class b implements Runnable {
        public final /* synthetic */ String h;

        public b(String str) {
            this.h = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            h.this.g(this.h);
        }
    }

    /* loaded from: classes9.dex */
    public static class c implements CRPBleConnectionStateListener {

        /* renamed from: a  reason: collision with root package name */
        public WeakReference<h> f7851a;

        public c(h hVar) {
            this.f7851a = new WeakReference<>(hVar);
        }

        @Override // com.crrepa.ble.conn.listener.CRPBleConnectionStateListener
        public void onConnectionStateChange(int i) {
            com.crrepa.i0.c.c("hs onConnectionStateChange: " + i);
            h hVar = this.f7851a.get();
            if (hVar == null) {
                return;
            }
            if (i == 0) {
                hVar.n();
            } else if (i != 2) {
            } else {
                hVar.k();
            }
        }
    }

    /* loaded from: classes9.dex */
    public static class d implements CRPScanCallback {

        /* renamed from: a  reason: collision with root package name */
        public WeakReference<h> f7852a;

        public d(h hVar) {
            this.f7852a = new WeakReference<>(hVar);
        }

        @Override // com.crrepa.ble.scan.callback.CRPScanCallback
        public void onScanComplete(List<CRPScanDevice> list) {
            h hVar = this.f7852a.get();
            if (hVar == null) {
                return;
            }
            hVar.r();
        }

        @Override // com.crrepa.ble.scan.callback.CRPScanCallback
        public void onScanning(CRPScanDevice cRPScanDevice) {
            h hVar = this.f7852a.get();
            if (hVar == null) {
                return;
            }
            hVar.c(cRPScanDevice);
        }
    }

    public final HSFirmwareInfo a(File file, boolean z, boolean z2) {
        return new com.crrepa.w.a().a(file, z, z2);
    }

    public void a() {
        this.g = true;
        com.crrepa.v.b bVar = this.k;
        if (bVar != null) {
            bVar.b();
        }
        CRPBleDevice cRPBleDevice = this.j;
        if (cRPBleDevice != null) {
            cRPBleDevice.disconnect();
        }
    }

    public void a(CRPBleFirmwareUpgradeListener cRPBleFirmwareUpgradeListener) {
        this.c = cRPBleFirmwareUpgradeListener;
    }

    public final void b(long j) {
        com.crrepa.g.a.a(new a(), j);
    }

    public void b(File file, boolean z, boolean z2) {
        if (file == null || !file.exists()) {
            m("firmware file not exist");
            return;
        }
        HSFirmwareInfo a2 = a(file, z, z2);
        this.b = a2;
        if (a2 == null) {
            m("firmware file not exist");
            return;
        }
        this.g = false;
        b(3000L);
    }

    public final synchronized void c(CRPScanDevice cRPScanDevice) {
        if (cRPScanDevice == null) {
            return;
        }
        if (this.f) {
            String name = cRPScanDevice.getDevice().getName();
            String address = cRPScanDevice.getDevice().getAddress();
            com.crrepa.i0.c.c("address: " + address);
            if (TextUtils.equals(this.f7850a, address) || TextUtils.equals(name, "HS-OTA")) {
                h();
                j(address);
            }
        }
    }

    public void d(String str) {
        this.f7850a = str;
    }

    public void e() {
        s();
    }

    public final void g(String str) {
        if (this.g) {
            return;
        }
        CRPBleDevice bleDevice = this.i.getBleDevice(str);
        this.j = bleDevice;
        com.crrepa.v.b connectDfu = bleDevice.connectDfu();
        this.k = connectDfu;
        connectDfu.setConnectionStateListener(this.e);
    }

    public final void h() {
        this.f = false;
        this.i.cancelScan();
    }

    public final void j(String str) {
        com.crrepa.g.a.a(new b(str), 3000L);
    }

    public final void k() {
        HSFirmwareInfo hSFirmwareInfo = this.b;
        if (hSFirmwareInfo == null) {
            m("file decompression failed");
        } else {
            this.k.a(this.c, hSFirmwareInfo);
        }
    }

    public final void m(String str) {
        com.crrepa.i0.c.c(str);
        CRPBleFirmwareUpgradeListener cRPBleFirmwareUpgradeListener = this.c;
        if (cRPBleFirmwareUpgradeListener != null) {
            cRPBleFirmwareUpgradeListener.onError(23, str);
        }
    }

    public final void n() {
        if (this.k.a()) {
            return;
        }
        p();
    }

    public final void p() {
        if (this.h < 5) {
            b(3000L);
        } else {
            m("Not connected to the target band");
        }
        this.h++;
    }

    public final void q() {
        if (this.g) {
            return;
        }
        this.f = true;
        this.i.scanDevice(this.d, 20000L);
    }

    public final void r() {
        if (this.f) {
            p();
        }
    }

    public final void s() {
        if (this.b == null) {
            m("firmware file not exist");
            return;
        }
        this.g = false;
        b(3000L);
    }
}
