package com.realsil.sdk.dfu.utils;

import android.content.Context;
import android.hardware.usb.UsbDevice;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.core.usb.GlobalUsbGatt;
import com.realsil.sdk.core.usb.UsbGatt;
import com.realsil.sdk.core.usb.UsbGattCallback;
import com.realsil.sdk.core.usb.UsbGattCharacteristic;
import com.realsil.sdk.dfu.exception.ConnectionException;
import com.realsil.sdk.dfu.m.f;
import com.realsil.sdk.dfu.model.DfuConfig;
import com.realsil.sdk.dfu.model.OtaDeviceInfo;
import com.realsil.sdk.dfu.model.OtaModeInfo;
import com.realsil.sdk.dfu.utils.DfuAdapter;
import com.realsil.sdk.dfu.v.b;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
/* loaded from: classes12.dex */
public class UsbGattDfuAdapter extends com.realsil.sdk.dfu.v.e implements f {
    public static volatile UsbGattDfuAdapter B;
    public GlobalUsbGatt C;
    public UsbGatt D;
    public UsbGattCharacteristic E;
    public com.realsil.sdk.dfu.v.b F;
    public b.InterfaceC0730b G = new a();
    public Runnable H = new b();
    public Runnable I = new c();
    public Runnable J = new d();
    public Handler K = new Handler(Looper.getMainLooper());
    public UsbGattCallback L = new e();

    /* loaded from: classes12.dex */
    public class a implements b.InterfaceC0730b {
        public a() {
        }

        @Override // com.realsil.sdk.dfu.v.b.InterfaceC0730b
        public void a(int i) {
            if (i == 1) {
                if (UsbGattDfuAdapter.this.isPreparing()) {
                    UsbGattDfuAdapter.this.notifyStateChanged(527);
                } else {
                    ZLogger.d(String.format("ignore, is not in preparing state: 0x%04X", Integer.valueOf(UsbGattDfuAdapter.this.q)));
                }
            }
            if (i == 2) {
                if (UsbGattDfuAdapter.this.isPreparing()) {
                    UsbGattDfuAdapter.this.a(new ConnectionException(5));
                } else {
                    ZLogger.d(String.format("ignore, is not in preparing state: 0x%04X", Integer.valueOf(UsbGattDfuAdapter.this.q)));
                }
            }
        }
    }

    /* loaded from: classes12.dex */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            UsbGattDfuAdapter.this.a(15000L);
            try {
                Thread.sleep(800L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (UsbGattDfuAdapter.this.d()) {
                ZLogger.v("wait discover service ...");
                UsbGattDfuAdapter.this.a(30000L);
                if (UsbGattDfuAdapter.this.q == 537) {
                    ZLogger.w("discoverServices timeout");
                    UsbGattDfuAdapter.this.disconnect();
                }
            }
        }
    }

    /* loaded from: classes12.dex */
    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (UsbGattDfuAdapter.this.d()) {
                ZLogger.d("wait discover service commplete");
                synchronized (UsbGattDfuAdapter.this.o) {
                    try {
                        UsbGattDfuAdapter.this.o.wait(30000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        ZLogger.e(e.toString());
                    }
                }
                if (UsbGattDfuAdapter.this.q == 537) {
                    ZLogger.w("discoverServices timeout");
                    UsbGattDfuAdapter.this.disconnect();
                }
            }
        }
    }

    /* loaded from: classes12.dex */
    public class d implements Runnable {
        public d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (UsbGattDfuAdapter.this.q == 536) {
                new Thread(UsbGattDfuAdapter.this.I).start();
                return;
            }
            ZLogger.d("ignore state:" + UsbGattDfuAdapter.this.q);
        }
    }

    /* loaded from: classes12.dex */
    public class e extends UsbGattCallback {
        public e() {
        }

        public final void a() {
            if (UsbGattDfuAdapter.this.isPreparing()) {
                UsbGattDfuAdapter.this.notifyLock();
                UsbGattDfuAdapter.this.a(new ConnectionException(0));
                return;
            }
            UsbGattDfuAdapter.this.notifyStateChanged(4097);
        }

        public void onCharacteristicRead(UsbGatt usbGatt, UsbGattCharacteristic usbGattCharacteristic, int i) {
            super.onCharacteristicRead(usbGatt, usbGattCharacteristic, i);
            UUID uuid = usbGattCharacteristic.getUuid();
            usbGattCharacteristic.getValue();
            if (i == 0) {
                byte[] value = usbGattCharacteristic.getValue();
                if (f.d.equals(uuid)) {
                    ByteBuffer wrap = ByteBuffer.wrap(value);
                    wrap.order(ByteOrder.LITTLE_ENDIAN);
                    ZLogger.d(String.format("protocolType=0x%04X", Integer.valueOf(wrap.getShort(0))));
                    UsbGattDfuAdapter.this.F = new com.realsil.sdk.dfu.v.a(0);
                    UsbGattDfuAdapter.this.F.a(UsbGattDfuAdapter.this.z, UsbGattDfuAdapter.this.D, UsbGattDfuAdapter.this.G);
                    UsbGattDfuAdapter.this.F.e();
                    return;
                }
                return;
            }
            boolean z = UsbGattDfuAdapter.this.h;
            ZLogger.e(z, "Characteristic read error: " + i);
            if (f.d.equals(uuid)) {
                if (UsbGattDfuAdapter.this.isPreparing()) {
                    UsbGattDfuAdapter.this.a(new ConnectionException(5));
                    return;
                }
                return;
            }
            ZLogger.d("ignore exctption when read other info");
        }

        public void onConnectionStateChange(UsbGatt usbGatt, int i, int i2) {
            if (i != 0) {
                a();
            } else if (i2 != 2) {
                if (i2 == 0) {
                    UsbGattDfuAdapter.this.disconnect();
                    a();
                }
            } else {
                UsbGattDfuAdapter usbGattDfuAdapter = UsbGattDfuAdapter.this;
                usbGattDfuAdapter.D = usbGattDfuAdapter.C.getUsbGatt(UsbGattDfuAdapter.this.z);
                if (usbGatt != null) {
                    UsbGattDfuAdapter.this.e();
                } else {
                    a();
                }
            }
        }

        public void onServicesDiscovered(UsbGatt usbGatt, int i) {
            UsbGattDfuAdapter usbGattDfuAdapter = UsbGattDfuAdapter.this;
            int i2 = usbGattDfuAdapter.q;
            if (i2 == 1025) {
                ZLogger.d("ignore, when it is ota processing");
            } else if (i == 0) {
                if (i2 == 537) {
                    usbGattDfuAdapter.notifyStateChanged(DfuAdapter.STATE_READ_DEVICE_INFO);
                    UsbGattDfuAdapter.this.notifyLock();
                } else {
                    usbGattDfuAdapter.notifyStateChanged(DfuAdapter.STATE_READ_DEVICE_INFO);
                }
                UsbGattDfuAdapter.this.readDeviceInfo();
            } else {
                ZLogger.w("service discovery failed !!!");
                if (UsbGattDfuAdapter.this.isPreparing()) {
                    UsbGattDfuAdapter.this.a(new ConnectionException(1));
                }
            }
        }
    }

    public UsbGattDfuAdapter(Context context) {
        this.mContext = context;
        c();
    }

    public static UsbGattDfuAdapter getInstance(Context context) {
        if (B == null) {
            synchronized (UsbGattDfuAdapter.class) {
                if (B == null) {
                    B = new UsbGattDfuAdapter(context.getApplicationContext());
                }
            }
        }
        return B;
    }

    @Override // com.realsil.sdk.dfu.utils.DfuAdapter
    public boolean connectDevice(ConnectParams connectParams) {
        if (super.connectDevice(connectParams)) {
            if (this.l.getAddress() == null) {
                ZLogger.w("address is null");
                return false;
            }
            String str = this.z;
            if (str != null) {
                if (Build.VERSION.SDK_INT >= 19) {
                    if (!str.equals(this.l.getAddress())) {
                        this.C.unRegisterCallback(this.z, this.L);
                        this.C.close(this.z);
                    }
                } else if (!equals(str, this.l.getAddress())) {
                    this.C.unRegisterCallback(this.z, this.L);
                    this.C.close(this.z);
                }
            }
            this.y = getRemoteDevice(this.l.getAddress());
            this.z = this.l.getAddress();
            this.m = this.l.getReconnectTimes();
            boolean a2 = a(this.y);
            if (!a2) {
                notifyStateChanged(4098);
            }
            return a2;
        }
        return false;
    }

    @Override // com.realsil.sdk.dfu.v.e, com.realsil.sdk.dfu.utils.DfuAdapter
    public void destroy() {
        super.destroy();
        GlobalUsbGatt globalUsbGatt = this.C;
        if (globalUsbGatt != null) {
            globalUsbGatt.unRegisterCallback(this.z, this.L);
        }
        com.realsil.sdk.dfu.v.b bVar = this.F;
        if (bVar != null) {
            bVar.a();
        }
        B = null;
    }

    @Override // com.realsil.sdk.dfu.utils.DfuAdapter
    public void disconnect() {
        super.disconnect();
        String str = this.z;
        if (str == null) {
            ZLogger.d("no device registered");
            notifyStateChanged(4097);
        } else {
            GlobalUsbGatt globalUsbGatt = this.C;
            if (globalUsbGatt != null) {
                if (globalUsbGatt.isConnected(str)) {
                    if (this.C.isCallbackRegisted(this.z, this.L)) {
                        notifyStateChanged(4096);
                        this.C.close(this.z);
                    } else {
                        ZLogger.v(this.i, "no gatt callback registered");
                        notifyStateChanged(4097);
                    }
                } else {
                    ZLogger.v("already disconnected");
                    notifyStateChanged(4097);
                }
            } else {
                ZLogger.d("mGlobalGatt == null");
                notifyStateChanged(4097);
            }
        }
        this.D = null;
    }

    @Override // com.realsil.sdk.dfu.v.e
    public OtaDeviceInfo getOtaDeviceInfo() {
        com.realsil.sdk.dfu.v.b bVar = this.F;
        if (bVar != null) {
            return bVar.b();
        }
        return super.getOtaDeviceInfo();
    }

    @Override // com.realsil.sdk.dfu.utils.DfuAdapter
    public OtaModeInfo getPriorityWorkMode(int i) {
        com.realsil.sdk.dfu.v.b bVar = this.F;
        if (bVar != null) {
            return bVar.a(i);
        }
        return super.getPriorityWorkMode(i);
    }

    @Override // com.realsil.sdk.dfu.utils.DfuAdapter
    public List<OtaModeInfo> getSupportedModes() {
        com.realsil.sdk.dfu.v.b bVar = this.F;
        if (bVar != null) {
            return bVar.c();
        }
        return super.getSupportedModes();
    }

    public final void readDeviceInfo() {
        UsbGatt usbGatt = this.D;
        if (usbGatt == null) {
            notifyStateChanged(527);
            return;
        }
        List<UsbGattCharacteristic> characteristics = usbGatt.getCharacteristics();
        if (characteristics != null && characteristics.size() > 0) {
            for (UsbGattCharacteristic usbGattCharacteristic : characteristics) {
                ZLogger.v(String.format(Locale.US, "instanceId=%d(0x%02X), uuid=%s", Integer.valueOf(usbGattCharacteristic.getInstanceId()), Integer.valueOf(usbGattCharacteristic.getInstanceId()), usbGattCharacteristic.getUuid().toString()));
            }
        } else {
            ZLogger.d("no characteristic found");
        }
        notifyStateChanged(DfuAdapter.STATE_READ_PROTOCOL_TYPE);
        UsbGatt usbGatt2 = this.D;
        UUID uuid = f.d;
        UsbGattCharacteristic characteristic = usbGatt2.getCharacteristic(uuid);
        this.E = characteristic;
        if (characteristic == null) {
            ZLogger.d("CHARACTERISTIC_PROTOCOL_TYPE not found");
            com.realsil.sdk.dfu.v.a aVar = new com.realsil.sdk.dfu.v.a(0);
            this.F = aVar;
            aVar.a(this.z, this.D, this.G);
            this.F.e();
            return;
        }
        boolean z = this.h;
        ZLogger.v(z, "find CHARACTERISTIC_PROTOCOL_TYPE = " + uuid);
        a(this.E);
    }

    @Override // com.realsil.sdk.dfu.v.e
    public boolean startOtaProcedure(DfuConfig dfuConfig, boolean z) {
        if (super.startOtaProcedure(dfuConfig, z)) {
            notifyStateChanged(1025);
            GlobalUsbGatt globalUsbGatt = this.C;
            if (globalUsbGatt != null) {
                globalUsbGatt.unRegisterCallback(this.z, this.L);
            }
            com.realsil.sdk.dfu.v.b bVar = this.F;
            if (bVar != null) {
                bVar.a();
            }
            boolean a2 = this.k.a(dfuConfig);
            if (!a2) {
                notifyStateChanged(1026);
            }
            return a2;
        }
        return false;
    }

    public final void e() {
        if (this.q != 536) {
            notifyStateChanged(DfuAdapter.STATE_PENDDING_DISCOVERY_SERVICE);
            if (this.K != null) {
                ZLogger.d("delay to discover service for : 1600");
                this.K.removeCallbacks(this.J);
                boolean postDelayed = this.K.postDelayed(this.J, 1600L);
                boolean z = this.h;
                ZLogger.v(z, "postDelayed:" + postDelayed);
                return;
            }
            ZLogger.v(this.h, "mHandler == null");
        }
    }

    @Override // com.realsil.sdk.dfu.utils.DfuAdapter
    public boolean b() {
        if (!super.b()) {
            notifyStateChanged(4098);
            return false;
        }
        boolean a2 = a(this.y);
        if (!a2) {
            notifyStateChanged(4098);
        }
        return a2;
    }

    @Override // com.realsil.sdk.dfu.v.e
    public void c() {
        super.c();
        GlobalUsbGatt globalUsbGatt = GlobalUsbGatt.getInstance();
        this.C = globalUsbGatt;
        if (globalUsbGatt == null) {
            GlobalUsbGatt.initial(this.mContext);
            this.C = GlobalUsbGatt.getInstance();
        }
    }

    public final boolean d() {
        boolean z;
        if (this.q == 537) {
            ZLogger.w("discoverServices already started");
            return false;
        }
        notifyStateChanged(DfuAdapter.STATE_DISCOVERY_SERVICE);
        if (this.D != null) {
            ZLogger.v("discoverServices...");
            z = this.D.discoverServices();
        } else {
            ZLogger.w("mBtGatt == null");
            z = false;
        }
        if (z) {
            return true;
        }
        ZLogger.w("discoverServices failed");
        if (isPreparing()) {
            a(new ConnectionException(1));
        }
        return false;
    }

    public final boolean a(UsbDevice usbDevice) {
        notifyStateChanged(DfuAdapter.STATE_PREPARE_CONNECTING);
        return this.C.connect(usbDevice, this.mContext, this.L);
    }

    public static UsbGattDfuAdapter getInstance(Context context, DfuAdapter.DfuHelperCallback dfuHelperCallback) {
        if (B == null) {
            synchronized (UsbGattDfuAdapter.class) {
                if (B == null) {
                    B = new UsbGattDfuAdapter(context.getApplicationContext(), dfuHelperCallback);
                }
            }
        }
        return B;
    }

    public final boolean a(UsbGattCharacteristic usbGattCharacteristic) {
        if (this.D != null && usbGattCharacteristic != null) {
            boolean z = this.h;
            ZLogger.v(z, "readCharacteristic:" + usbGattCharacteristic.getUuid());
            return this.D.readCharacteristic(usbGattCharacteristic);
        }
        ZLogger.w("mBtGatt is null maybe disconnected just now");
        return false;
    }

    public UsbGattDfuAdapter(Context context, DfuAdapter.DfuHelperCallback dfuHelperCallback) {
        this.mContext = context;
        this.n = dfuHelperCallback;
        c();
    }
}
