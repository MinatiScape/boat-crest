package com.ido.ble.bluetooth.connect;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.callback.ScanCallBack;
import com.ido.ble.logs.LogTool;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.eclipse.paho.client.mqttv3.MqttTopic;
/* loaded from: classes11.dex */
public class l extends com.ido.ble.bluetooth.connect.p.b {
    private static final long j = 30000;
    private static l k;
    private BluetoothManager b;
    private Handler f;
    private ExecutorService h;
    private boolean c = false;
    private boolean d = false;
    private String e = "";
    private Map<String, BLEDevice> g = new HashMap();
    private Runnable i = new b();

    /* loaded from: classes11.dex */
    public class a implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ byte[] f12075a;
        public final /* synthetic */ BluetoothDevice b;
        public final /* synthetic */ int c;

        public a(byte[] bArr, BluetoothDevice bluetoothDevice, int i) {
            this.f12075a = bArr;
            this.b = bluetoothDevice;
            this.c = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (com.ido.ble.bluetooth.e.a.a(this.f12075a)) {
                l.this.b(this.b, this.c, this.f12075a);
            } else {
                l.this.c(this.b, this.c, this.f12075a);
            }
        }
    }

    /* loaded from: classes11.dex */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            l.this.e();
            l.this.j();
        }
    }

    private l() {
        i();
    }

    private void a(BLEDevice bLEDevice) {
        String str = com.ido.ble.bluetooth.f.b.f12116a;
        LogTool.d(str, "[ScanManager] find device:" + bLEDevice.toString());
        ScanCallBack.a(bLEDevice);
    }

    private void a(boolean z, long j2) {
        LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "[ScanManager] startScanDevices()");
        String str = com.ido.ble.bluetooth.f.b.f12116a;
        LogTool.d(str, "[ScanManager] " + com.ido.ble.bluetooth.f.e.a());
        String b2 = com.ido.ble.common.m.b();
        if (!TextUtils.isEmpty(b2)) {
            String str2 = com.ido.ble.bluetooth.f.b.f12116a;
            LogTool.b(str2, "[ScanManager] printPhoneEnvInfo, " + b2);
        }
        if (!BluetoothAdapter.getDefaultAdapter().isEnabled()) {
            LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[ScanManager] bluetooth switch is closed, can not scan device.");
            ScanCallBack.a();
        } else if (!com.ido.ble.common.m.j()) {
            LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[ScanManager] hasPhoneBluetoothPermission false.");
            String str3 = com.ido.ble.bluetooth.f.b.f12116a;
            LogTool.b(str3, "[ScanManager] " + com.ido.ble.common.m.b());
            ScanCallBack.a();
        } else if (this.c) {
            LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[ScanManager] at state of scanning, ignore this action");
        } else {
            this.h = Executors.newSingleThreadExecutor();
            this.d = z;
            if (j2 < 0) {
                j2 = 30000;
            }
            Handler handler = this.f;
            if (handler != null) {
                handler.removeCallbacks(this.i);
            }
            h().postDelayed(this.i, j2);
            this.c = true;
            this.g.clear();
            a();
            ScanCallBack.b();
            f();
        }
    }

    private boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return TextUtils.isEmpty(this.e) || str.toLowerCase().contains(this.e.toLowerCase());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
        byte[] b2 = com.ido.ble.bluetooth.e.b.b(bArr);
        if (b2 == null || b2.length < 2) {
            return;
        }
        BLEDevice bLEDevice = new BLEDevice();
        String name = bluetoothDevice.getName();
        if (TextUtils.isEmpty(name)) {
            name = com.ido.ble.bluetooth.e.b.a(bArr);
            if (TextUtils.isEmpty(name)) {
                return;
            }
        }
        if (this.d) {
            if (!com.ido.ble.bluetooth.e.b.c(bArr)) {
                return;
            }
        } else if (!a(name)) {
            return;
        }
        String address = bluetoothDevice.getAddress();
        if (this.g.containsKey(address)) {
            return;
        }
        bLEDevice.mDeviceName = name;
        bLEDevice.mDeviceAddress = address;
        bLEDevice.mRssi = i;
        bLEDevice.mDeviceId = (b2[0] & 255) | ((b2[1] & 255) << 8);
        bLEDevice.mIsInDfuMode = true;
        this.g.put(address, bLEDevice);
        a(bLEDevice);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
        String name = bluetoothDevice.getName();
        byte[] b2 = com.ido.ble.bluetooth.e.b.b(bArr);
        int i2 = 0;
        int i3 = (b2 == null || b2.length <= 2) ? 0 : (b2[0] & 255) | ((b2[1] & 255) << 8);
        if (b2 != null && b2.length > 3) {
            i2 = b2[3] & 255;
        }
        if (TextUtils.isEmpty(name)) {
            name = com.ido.ble.bluetooth.e.b.a(bArr);
            if (TextUtils.isEmpty(name)) {
                return;
            }
        }
        if (this.d) {
            if (!com.ido.ble.bluetooth.e.b.d(bArr)) {
                return;
            }
        } else if (!a(name)) {
            return;
        }
        String address = bluetoothDevice.getAddress();
        if (this.g.containsKey(address)) {
            return;
        }
        BLEDevice bLEDevice = new BLEDevice();
        bLEDevice.mDeviceName = name;
        bLEDevice.mDeviceAddress = address;
        bLEDevice.mRssi = i;
        bLEDevice.mDeviceId = i3;
        bLEDevice.bootload_version = i2;
        if (b2 != null) {
            if (b2.length >= 10) {
                bLEDevice.type = b2[9] & 255;
            }
            BLEDevice.a aVar = new BLEDevice.a();
            bLEDevice.otaFactoryDeviceInfo = aVar;
            if (b2.length >= 14) {
                aVar.f12113a = b2[10] & 255;
                aVar.b = b2[11] & 255;
                aVar.c = b2[12] & 255;
                aVar.d = b2[13] & 255;
            }
            if (b2.length > 17) {
                aVar.f12113a = (b2[9] << 8) | b2[8];
                aVar.b = b2[10] | (b2[11] << 8);
                aVar.c = b2[12] | (b2[13] << 8);
                aVar.d = b2[14] | (b2[15] << 8);
                aVar.e = (b2[16] & 255) | (b2[17] << 8);
            }
        }
        this.g.put(address, bLEDevice);
        a(bLEDevice);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "[ScanManager] to get bond with phone list.");
        for (BluetoothDevice bluetoothDevice : BluetoothAdapter.getDefaultAdapter().getBondedDevices()) {
            if (bluetoothDevice != null && bluetoothDevice.getType() == 2) {
                BLEDevice bLEDevice = new BLEDevice();
                bLEDevice.mDeviceAddress = bluetoothDevice.getAddress();
                bLEDevice.mDeviceName = bluetoothDevice.getName();
                bLEDevice.mDeviceId = -111;
                bLEDevice.mRssi = -1;
                bLEDevice.type = -1;
                if (!this.g.containsKey(bLEDevice.mDeviceAddress)) {
                    this.g.put(bLEDevice.mDeviceAddress, bLEDevice);
                    a(bLEDevice);
                }
            }
        }
    }

    private void f() {
        List<BluetoothDevice> connectedDevices = this.b.getConnectedDevices(7);
        if (connectedDevices == null || connectedDevices.size() == 0) {
            return;
        }
        BLEDevice c = com.ido.ble.f.a.f.b.e().c();
        String str = "";
        for (BluetoothDevice bluetoothDevice : connectedDevices) {
            str = str + bluetoothDevice.getAddress() + MqttTopic.TOPIC_LEVEL_SEPARATOR + bluetoothDevice.getName();
            if (!this.g.containsKey(bluetoothDevice.getAddress())) {
                if (c == null || !c.mDeviceAddress.equals(bluetoothDevice.getAddress())) {
                    BLEDevice bLEDevice = new BLEDevice();
                    if (!TextUtils.isEmpty(bluetoothDevice.getName())) {
                        bLEDevice.mDeviceName = bluetoothDevice.getName();
                        bLEDevice.mDeviceAddress = bluetoothDevice.getAddress();
                        bLEDevice.mRssi = -1;
                        bLEDevice.mDeviceId = -111;
                        this.g.put(bluetoothDevice.getAddress(), bLEDevice);
                        a(bLEDevice);
                    }
                } else {
                    this.g.put(bluetoothDevice.getAddress(), c);
                    a(c);
                }
            }
        }
        LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "[ScanManager] gattConnectedList=" + str);
    }

    public static l g() {
        if (k == null) {
            k = new l();
        }
        return k;
    }

    private Handler h() {
        if (this.f == null) {
            this.f = new Handler(Looper.getMainLooper());
        }
        return this.f;
    }

    private void i() {
        this.b = (BluetoothManager) com.ido.ble.common.e.a().getSystemService("bluetooth");
        this.f = new Handler(Looper.getMainLooper());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "[ScanManager] this task of scan is finished");
        Handler handler = this.f;
        if (handler != null) {
            handler.removeCallbacks(this.i);
        }
        b();
        this.c = false;
        k();
    }

    private void k() {
        ScanCallBack.a();
    }

    public void a(long j2) {
        a(true, j2);
    }

    public void a(long j2, String str) {
        this.e = str;
        a(false, j2);
    }

    @Override // com.ido.ble.bluetooth.connect.p.a
    public void a(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
        this.h.execute(new a(bArr, bluetoothDevice, i));
    }

    public void d() {
        if (this.c) {
            LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "[ScanManager] stopScanDevices()");
            j();
            return;
        }
        Handler handler = this.f;
        if (handler != null) {
            handler.removeCallbacks(this.i);
        }
        b();
        LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "[ScanManager] stopScanDevices(), mIsScanning = false");
    }
}
