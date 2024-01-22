package com.realsil.sdk.dfu.m;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import com.realsil.sdk.core.bluetooth.GlobalGatt;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.dfu.m.f;
import com.realsil.sdk.dfu.model.OtaDeviceInfo;
import com.realsil.sdk.dfu.model.OtaModeInfo;
import com.realsil.sdk.dfu.utils.ConnectParams;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
/* loaded from: classes12.dex */
public abstract class a implements f {
    public int h;
    public BluetoothGatt i;
    public BluetoothGattService j;
    public BluetoothGattService k;
    public BluetoothGattCharacteristic l;
    public BluetoothGattCharacteristic m;
    public List<BluetoothGattCharacteristic> n;
    public OtaDeviceInfo o;
    public String q;
    public c r;
    public ConnectParams s;
    public b u;
    public List<OtaModeInfo> p = new ArrayList();
    public int t = 1;
    public final BluetoothGattCallback v = new C0722a();
    public Object w = new Object();
    public boolean x = true;
    public int y = 0;
    public boolean z = false;
    public Object A = new Object();

    /* renamed from: com.realsil.sdk.dfu.m.a$a  reason: collision with other inner class name */
    /* loaded from: classes12.dex */
    public class C0722a extends BluetoothGattCallback {
        public C0722a() {
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            a.this.a(bluetoothGatt, bluetoothGattCharacteristic);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            a.this.a(bluetoothGatt, bluetoothGattCharacteristic, i);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
            if (i == 0 && i2 == 0 && a.this.d()) {
                a.this.b(2);
                a.this.h();
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            super.onDescriptorWrite(bluetoothGatt, bluetoothGattDescriptor, i);
            try {
                ZLogger.v("onDescriptorWrite: " + i);
                synchronized (a.this.w) {
                    a.this.x = true;
                    a.this.w.notifyAll();
                }
            } catch (Exception e) {
                e.printStackTrace();
                ZLogger.e(e.toString());
            }
        }
    }

    /* loaded from: classes12.dex */
    public class b extends Thread {
        public b() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            super.run();
            setName("AdapterXGBase-DeviceInfoThread");
            a.this.g();
        }

        public /* synthetic */ b(a aVar, C0722a c0722a) {
            this();
        }
    }

    /* loaded from: classes12.dex */
    public interface c {
        void a(int i);
    }

    public void a(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
    }

    public void a(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
    }

    public OtaDeviceInfo b() {
        if (this.o == null) {
            this.o = new OtaDeviceInfo(this.h, 2);
        }
        return this.o;
    }

    public List<OtaModeInfo> c() {
        return this.p;
    }

    public boolean d() {
        return (this.y & 256) == 256;
    }

    public final void e() {
        BluetoothGattService service = this.i.getService(f.a.f13619a);
        if (service == null) {
            ZLogger.v(true, "BATTERY_SERVICE not found");
            return;
        }
        UUID uuid = f.a.b;
        BluetoothGattCharacteristic characteristic = service.getCharacteristic(uuid);
        this.l = characteristic;
        if (characteristic == null) {
            ZLogger.v(true, "BAS_READ_CHARACTERITIC not found");
            return;
        }
        ZLogger.v("find BAS_READ_CHARACTERITIC: " + uuid.toString());
    }

    public final void f() {
        BluetoothGatt bluetoothGatt = this.i;
        UUID uuid = f.b.f13620a;
        BluetoothGattService service = bluetoothGatt.getService(uuid);
        if (service == null) {
            ZLogger.v("DEVICE_INFORMATION_SERVICE not found");
            return;
        }
        ZLogger.d("find DEVICE_INFORMATION_SERVICE: " + uuid.toString());
        UUID uuid2 = f.b.e;
        BluetoothGattCharacteristic characteristic = service.getCharacteristic(uuid2);
        this.m = characteristic;
        if (characteristic == null) {
            ZLogger.d("DIS_PNP_ID_CHARACTERISTIC not found");
            return;
        }
        ZLogger.d("find DIS_PNP_ID_CHARACTERISTIC: " + uuid2.toString());
    }

    public void g() {
    }

    public void h() {
        synchronized (this.A) {
            this.z = true;
            this.A.notifyAll();
        }
    }

    public void i() {
        synchronized (this.A) {
            try {
                this.A.wait(6000L);
            } catch (InterruptedException e) {
                ZLogger.w("wait sync data interrupted: " + e.toString());
            }
        }
    }

    public void a(ConnectParams connectParams, String str, BluetoothGatt bluetoothGatt, BluetoothGattService bluetoothGattService, BluetoothGattService bluetoothGattService2, c cVar) {
        this.s = connectParams;
        this.q = str;
        this.i = GlobalGatt.getInstance().getBluetoothGatt(str);
        this.j = bluetoothGattService;
        this.k = bluetoothGattService2;
        this.r = cVar;
        this.p = new ArrayList();
        this.n = new ArrayList();
        ConnectParams connectParams2 = this.s;
        if (connectParams2 != null) {
            this.t = connectParams2.getBatteryValueFormat();
        } else {
            this.t = 1;
        }
        e();
        f();
        GlobalGatt.getInstance().registerCallback(this.q, this.v);
    }

    public void b(int i) {
        ZLogger.d(String.format("syndata: 0x%04X >> 0x%04X", Integer.valueOf(this.y), Integer.valueOf(i)));
        this.y = i;
        c cVar = this.r;
        if (cVar != null) {
            cVar.a(i);
        } else {
            ZLogger.v(false, "no callback registered");
        }
    }

    public void a() {
        b bVar = this.u;
        if (bVar != null) {
            bVar.interrupt();
            this.u = null;
        }
        this.y = 0;
        GlobalGatt.getInstance().unRegisterCallback(this.q, this.v);
    }

    public void a(String str, BluetoothGatt bluetoothGatt, BluetoothGattService bluetoothGattService, BluetoothGattService bluetoothGattService2) {
        ZLogger.v("sync data ...");
        this.q = str;
        this.i = bluetoothGatt;
        this.j = bluetoothGattService;
        this.k = bluetoothGattService2;
        e();
        f();
        b bVar = new b(this, null);
        this.u = bVar;
        bVar.start();
    }

    public boolean a(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (this.i == null) {
            ZLogger.w("mBluetoothGatt is null maybe disconnected just now");
            return false;
        } else if (bluetoothGattCharacteristic == null) {
            ZLogger.w("characteristic can not be null");
            return false;
        } else {
            ZLogger.v(String.format(Locale.US, "readCharacteristic:(%d) %s", Integer.valueOf(bluetoothGattCharacteristic.getInstanceId()), bluetoothGattCharacteristic.getUuid().toString()));
            this.z = false;
            if (this.i.readCharacteristic(bluetoothGattCharacteristic)) {
                i();
                return this.y != 2;
            }
            ZLogger.w("readCharacteristic failed");
            return false;
        }
    }

    public OtaModeInfo a(int i) {
        List<OtaModeInfo> list = this.p;
        if (list != null && list.size() > 0) {
            for (OtaModeInfo otaModeInfo : this.p) {
                if (otaModeInfo.getWorkmode() == i) {
                    return otaModeInfo;
                }
            }
            return this.p.get(0);
        }
        return new OtaModeInfo(0);
    }

    public boolean a(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, boolean z) {
        int properties = bluetoothGattCharacteristic.getProperties();
        if ((properties & 16) == 0) {
            ZLogger.w("check properties failed: " + properties);
            this.x = false;
            return false;
        }
        ZLogger.v("setCharacteristicNotification() - uuid: " + bluetoothGattCharacteristic.getUuid() + " enabled: " + z);
        bluetoothGatt.setCharacteristicNotification(bluetoothGattCharacteristic, z);
        BluetoothGattDescriptor descriptor = bluetoothGattCharacteristic.getDescriptor(f.f13618a);
        if (descriptor != null) {
            boolean z2 = descriptor.getValue() != null && descriptor.getValue().length == 2 && descriptor.getValue()[0] > 0 && descriptor.getValue()[1] == 0;
            ZLogger.v(true, "current cccd state: " + z2);
            if (z && z2) {
                this.x = true;
                ZLogger.w("cccd already enabled");
                return true;
            } else if (!z && !z2) {
                ZLogger.w("cccd already disable");
                this.x = true;
                return true;
            } else {
                descriptor.setValue(z ? BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE : BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE);
                if (bluetoothGatt.writeDescriptor(descriptor)) {
                    synchronized (this.w) {
                        ZLogger.d("wait write Characteristic Notification 15000ms");
                        try {
                            this.x = false;
                            this.w.wait(30000L);
                        } catch (InterruptedException e) {
                            ZLogger.e("wait writeDescriptor interrupted: " + e.toString());
                        }
                    }
                    return this.x;
                }
            }
        }
        return false;
    }

    public void a(byte[] bArr) {
        short s;
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        if (this.t == 1) {
            if (bArr.length > 0) {
                s = wrap.get();
            }
            s = 0;
        } else if (bArr.length >= 2) {
            s = wrap.getShort();
        } else {
            if (bArr.length > 0) {
                s = wrap.get();
            }
            s = 0;
        }
        ZLogger.v(true, "current battery: " + ((int) s));
        b().setBatteryLevel(s);
    }
}
