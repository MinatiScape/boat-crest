package com.realsil.sdk.dfu.v;

import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.core.usb.GlobalUsbGatt;
import com.realsil.sdk.core.usb.UsbGatt;
import com.realsil.sdk.core.usb.UsbGattCallback;
import com.realsil.sdk.core.usb.UsbGattCharacteristic;
import com.realsil.sdk.dfu.model.OtaDeviceInfo;
import com.realsil.sdk.dfu.model.OtaModeInfo;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes12.dex */
public abstract class b implements com.realsil.sdk.dfu.m.f {
    public int h;
    public UsbGatt i;
    public List<UsbGattCharacteristic> j;
    public OtaDeviceInfo k;
    public String m;
    public InterfaceC0730b n;
    public List<OtaModeInfo> l = new ArrayList();
    public final UsbGattCallback o = new a();
    public int p = 0;
    public Object q = new Object();

    /* loaded from: classes12.dex */
    public class a extends UsbGattCallback {
        public a() {
        }

        public void onCharacteristicChanged(UsbGatt usbGatt, UsbGattCharacteristic usbGattCharacteristic) {
            b.this.a(usbGatt, usbGattCharacteristic);
        }

        public void onCharacteristicRead(UsbGatt usbGatt, UsbGattCharacteristic usbGattCharacteristic, int i) {
            b.this.a(usbGatt, usbGattCharacteristic, i);
        }

        public void onConnectionStateChange(UsbGatt usbGatt, int i, int i2) {
            if (i == 0 && i2 == 0 && b.this.d()) {
                b.this.b(2);
            }
        }
    }

    /* renamed from: com.realsil.sdk.dfu.v.b$b  reason: collision with other inner class name */
    /* loaded from: classes12.dex */
    public interface InterfaceC0730b {
        void a(int i);
    }

    public void a(UsbGatt usbGatt, UsbGattCharacteristic usbGattCharacteristic) {
    }

    public void a(UsbGatt usbGatt, UsbGattCharacteristic usbGattCharacteristic, int i) {
    }

    public void a(String str, UsbGatt usbGatt, InterfaceC0730b interfaceC0730b) {
        this.m = str;
        this.i = usbGatt;
        this.n = interfaceC0730b;
        this.l = new ArrayList();
        this.j = new ArrayList();
        GlobalUsbGatt.getInstance().registerCallback(this.m, this.o);
    }

    public OtaDeviceInfo b() {
        if (this.k == null) {
            this.k = new OtaDeviceInfo(this.h, 2);
        }
        return this.k;
    }

    public List<OtaModeInfo> c() {
        return this.l;
    }

    public boolean d() {
        return (this.p & 256) == 256;
    }

    public abstract void e();

    public void f() {
        ZLogger.v(false, "triggleSyncLock");
        synchronized (this.q) {
            this.q.notifyAll();
        }
    }

    public void g() {
        ZLogger.v(false, "waitSyncLock");
        synchronized (this.q) {
            try {
                this.q.wait(30000L);
            } catch (InterruptedException e) {
                ZLogger.w("wait sync data interrupted: " + e.toString());
            }
        }
    }

    public void b(int i) {
        ZLogger.d(String.format("syndata: 0x%04X >> 0x%04X", Integer.valueOf(this.p), Integer.valueOf(i)));
        this.p = i;
        InterfaceC0730b interfaceC0730b = this.n;
        if (interfaceC0730b != null) {
            interfaceC0730b.a(i);
        } else {
            ZLogger.v(false, "no callback registered");
        }
    }

    public void a() {
        this.p = 0;
        GlobalUsbGatt.getInstance().unRegisterCallback(this.m, this.o);
    }

    public boolean a(UsbGattCharacteristic usbGattCharacteristic) {
        UsbGatt usbGatt = this.i;
        if (usbGatt == null) {
            ZLogger.w("mUsbGatt is null maybe disconnected just now");
            return false;
        }
        return usbGatt.readCharacteristic(usbGattCharacteristic);
    }

    public OtaModeInfo a(int i) {
        List<OtaModeInfo> list = this.l;
        if (list != null && list.size() > 0) {
            for (OtaModeInfo otaModeInfo : this.l) {
                if (otaModeInfo.getWorkmode() == i) {
                    return otaModeInfo;
                }
            }
            return this.l.get(0);
        }
        return new OtaModeInfo(0);
    }
}
