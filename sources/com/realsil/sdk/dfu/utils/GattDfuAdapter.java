package com.realsil.sdk.dfu.utils;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import com.realsil.sdk.core.bluetooth.BluetoothProfileManager;
import com.realsil.sdk.core.bluetooth.GlobalGatt;
import com.realsil.sdk.core.bluetooth.impl.BluetoothDeviceImpl;
import com.realsil.sdk.core.bluetooth.impl.BluetoothGattImpl;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.dfu.exception.ConnectionException;
import com.realsil.sdk.dfu.m.a;
import com.realsil.sdk.dfu.m.f;
import com.realsil.sdk.dfu.model.DfuConfig;
import com.realsil.sdk.dfu.model.OtaDeviceInfo;
import com.realsil.sdk.dfu.model.OtaModeInfo;
import com.realsil.sdk.dfu.params.QcConfig;
import com.realsil.sdk.dfu.utils.DfuAdapter;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
/* loaded from: classes12.dex */
public class GattDfuAdapter extends BluetoothDfuAdapter implements f {
    public static volatile GattDfuAdapter G;
    public GlobalGatt H;
    public BluetoothGatt I;
    public BluetoothGattService J;
    public BluetoothGattService K;
    public BluetoothGattCharacteristic L;
    public com.realsil.sdk.dfu.m.a M;
    public a.c N = new a();
    public Runnable O = new b();
    public Runnable P = new c();
    public Runnable Q = new d();
    public Handler R = new Handler(Looper.getMainLooper());
    public BluetoothGattCallback S = new e();

    /* loaded from: classes12.dex */
    public class a implements a.c {
        public a() {
        }

        @Override // com.realsil.sdk.dfu.m.a.c
        public void a(int i) {
            if (!GattDfuAdapter.this.isPreparing()) {
                GattDfuAdapter gattDfuAdapter = GattDfuAdapter.this;
                ZLogger.v(gattDfuAdapter.j, String.format("ignore, is not in preparing state: 0x%04X", Integer.valueOf(gattDfuAdapter.q)));
            } else if (i != 1) {
                if (i == 2) {
                    GattDfuAdapter.this.a(new ConnectionException(5));
                }
            } else if (GattDfuAdapter.this.isBackConnecting()) {
                GattDfuAdapter.this.notifyStateChanged(DfuAdapter.STATE_BACKCONNECT_SYNC_DATA);
            } else {
                GattDfuAdapter.this.notifyStateChanged(527);
            }
        }
    }

    /* loaded from: classes12.dex */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            GattDfuAdapter.this.a(15000L);
            try {
                Thread.sleep(800L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            GattDfuAdapter.this.d();
        }
    }

    /* loaded from: classes12.dex */
    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            GattDfuAdapter.this.d();
        }
    }

    /* loaded from: classes12.dex */
    public class d implements Runnable {
        public d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            GattDfuAdapter gattDfuAdapter = GattDfuAdapter.this;
            if (gattDfuAdapter.q == 536) {
                gattDfuAdapter.B = gattDfuAdapter.getBondState(gattDfuAdapter.C);
                if (GattDfuAdapter.this.B == 11) {
                    ZLogger.v("BOND_BONDING: wait to discover service");
                    new Thread(GattDfuAdapter.this.O).start();
                    return;
                }
                ZLogger.v(">> mBondState: " + GattDfuAdapter.this.B);
                new Thread(GattDfuAdapter.this.P).start();
                return;
            }
            ZLogger.d("ignore state:" + GattDfuAdapter.this.q);
        }
    }

    /* loaded from: classes12.dex */
    public class e extends BluetoothGattCallback {
        public e() {
        }

        public final void a() {
            if (GattDfuAdapter.this.isPreparing()) {
                GattDfuAdapter.this.notifyLock();
                GattDfuAdapter.this.a(new ConnectionException(0));
                return;
            }
            GattDfuAdapter.this.notifyStateChanged(4097);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            super.onCharacteristicRead(bluetoothGatt, bluetoothGattCharacteristic, i);
            UUID uuid = bluetoothGattCharacteristic.getUuid();
            bluetoothGattCharacteristic.getValue();
            if (i == 0) {
                byte[] value = bluetoothGattCharacteristic.getValue();
                if (f.d.equals(uuid)) {
                    ByteBuffer wrap = ByteBuffer.wrap(value);
                    wrap.order(ByteOrder.LITTLE_ENDIAN);
                    GattDfuAdapter.this.b(wrap.getShort(0));
                    if (GattDfuAdapter.this.M != null) {
                        com.realsil.sdk.dfu.m.a aVar = GattDfuAdapter.this.M;
                        GattDfuAdapter gattDfuAdapter = GattDfuAdapter.this;
                        String str = gattDfuAdapter.C;
                        BluetoothGatt bluetoothGatt2 = gattDfuAdapter.I;
                        GattDfuAdapter gattDfuAdapter2 = GattDfuAdapter.this;
                        aVar.a(str, bluetoothGatt2, gattDfuAdapter2.J, gattDfuAdapter2.K);
                        return;
                    }
                    return;
                }
                return;
            }
            ZLogger.d(GattDfuAdapter.this.h, String.format("Characteristic read error:0x%04X ", Integer.valueOf(i)));
            if (f.d.equals(uuid)) {
                if (GattDfuAdapter.this.isPreparing()) {
                    GattDfuAdapter.this.a(new ConnectionException(5));
                    return;
                }
                return;
            }
            ZLogger.v("ignore exctption when read other info");
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
            if (i != 0) {
                a();
            } else if (i2 != 2) {
                if (i2 == 0) {
                    GattDfuAdapter.this.disconnect();
                    a();
                }
            } else {
                GattDfuAdapter gattDfuAdapter = GattDfuAdapter.this;
                gattDfuAdapter.I = gattDfuAdapter.H.getBluetoothGatt(GattDfuAdapter.this.C);
                ConnectParams connectParams = GattDfuAdapter.this.l;
                if (connectParams != null && connectParams.isRefreshCache()) {
                    BluetoothGattImpl.refresh(bluetoothGatt);
                }
                if (bluetoothGatt != null) {
                    GattDfuAdapter gattDfuAdapter2 = GattDfuAdapter.this;
                    if (gattDfuAdapter2.q != 536) {
                        gattDfuAdapter2.notifyStateChanged(DfuAdapter.STATE_PENDDING_DISCOVERY_SERVICE);
                        if (GattDfuAdapter.this.R != null) {
                            ZLogger.v("delay to discover service for : 1600");
                            GattDfuAdapter.this.R.removeCallbacks(GattDfuAdapter.this.Q);
                            boolean postDelayed = GattDfuAdapter.this.R.postDelayed(GattDfuAdapter.this.Q, 1600L);
                            if (postDelayed) {
                                return;
                            }
                            boolean z = GattDfuAdapter.this.h;
                            ZLogger.v(z, "postDelayed:" + postDelayed);
                            return;
                        }
                        ZLogger.v(GattDfuAdapter.this.h, "mHandler == null");
                        return;
                    }
                    return;
                }
                a();
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            GattDfuAdapter gattDfuAdapter = GattDfuAdapter.this;
            int i2 = gattDfuAdapter.q;
            if (i2 == 1025) {
                ZLogger.d("ignore, when it is ota processing");
            } else if (i != 0) {
                ZLogger.w("service discovery failed !!!");
                if (GattDfuAdapter.this.isPreparing()) {
                    GattDfuAdapter.this.a(new ConnectionException(1));
                }
            } else if (i2 == 537) {
                gattDfuAdapter.notifyStateChanged(DfuAdapter.STATE_READ_DEVICE_INFO);
                GattDfuAdapter.this.notifyLock();
            } else {
                gattDfuAdapter.notifyStateChanged(DfuAdapter.STATE_READ_DEVICE_INFO);
            }
        }
    }

    public GattDfuAdapter(Context context) {
        this.mContext = context;
        c();
    }

    public static GattDfuAdapter getInstance(Context context) {
        if (G == null) {
            synchronized (GattDfuAdapter.class) {
                if (G == null) {
                    G = new GattDfuAdapter(context.getApplicationContext());
                }
            }
        }
        return G;
    }

    public int checkBatteryLevel(int i, int i2) {
        int primaryBat = getOtaDeviceInfo().getPrimaryBat();
        if (getOtaDeviceInfo().icType <= 3 && i2 == 1) {
            primaryBat = (((primaryBat * 2) - 210) * 100) / 90;
        }
        if (primaryBat <= i) {
            return 269;
        }
        return (primaryBat <= 110 || primaryBat > 140) ? 0 : 269;
    }

    @Override // com.realsil.sdk.dfu.utils.BluetoothDfuAdapter, com.realsil.sdk.dfu.utils.DfuAdapter
    public boolean connectDevice(ConnectParams connectParams) {
        boolean a2;
        if (super.connectDevice(connectParams)) {
            String str = this.C;
            if (str != null) {
                if (Build.VERSION.SDK_INT >= 19) {
                    if (!str.equals(this.l.getAddress())) {
                        this.H.unRegisterCallback(this.C, this.S);
                        this.H.close(this.C);
                    }
                } else if (!equals(str, this.l.getAddress())) {
                    this.H.unRegisterCallback(this.C, this.S);
                    this.H.close(this.C);
                }
            }
            this.A = getRemoteDevice(this.l.getAddress());
            String address = this.l.getAddress();
            this.C = address;
            int bondState = getBondState(address);
            this.B = bondState;
            ZLogger.v(this.h, String.format(Locale.US, ">> mBondState: %d", Integer.valueOf(bondState)));
            if (this.l.isHid()) {
                a2 = b(this.C);
                if (!a2) {
                    a2 = a(this.C);
                }
            } else {
                a2 = a(this.C);
            }
            if (!a2) {
                notifyStateChanged(4098);
            }
            return a2;
        }
        return false;
    }

    @Override // com.realsil.sdk.dfu.utils.BluetoothDfuAdapter, com.realsil.sdk.dfu.utils.DfuAdapter
    public void destroy() {
        super.destroy();
        GlobalGatt globalGatt = this.H;
        if (globalGatt != null) {
            globalGatt.unRegisterCallback(this.C, this.S);
        }
        com.realsil.sdk.dfu.m.a aVar = this.M;
        if (aVar != null) {
            aVar.a();
        }
        G = null;
    }

    @Override // com.realsil.sdk.dfu.utils.DfuAdapter
    public void disconnect() {
        super.disconnect();
        String str = this.C;
        if (str == null) {
            ZLogger.d("no device registered");
            notifyStateChanged(4097);
        } else {
            GlobalGatt globalGatt = this.H;
            if (globalGatt != null) {
                if (globalGatt.isConnected(str)) {
                    if (this.H.isCallbackRegisted(this.C, this.S)) {
                        notifyStateChanged(4096);
                        this.H.close(this.C);
                    } else {
                        ZLogger.v(this.i, "no gatt callback registered");
                        notifyStateChanged(4097);
                    }
                } else {
                    ZLogger.v("already disconnected");
                    notifyStateChanged(4097);
                }
            } else {
                notifyStateChanged(4097);
            }
        }
        this.I = null;
    }

    @Override // com.realsil.sdk.dfu.utils.BluetoothDfuAdapter
    public OtaDeviceInfo getOtaDeviceInfo() {
        com.realsil.sdk.dfu.m.a aVar = this.M;
        if (aVar != null) {
            return aVar.b();
        }
        return super.getOtaDeviceInfo();
    }

    @Override // com.realsil.sdk.dfu.utils.DfuAdapter
    public OtaModeInfo getPriorityWorkMode(int i) {
        com.realsil.sdk.dfu.m.a aVar = this.M;
        if (aVar != null) {
            return aVar.a(i);
        }
        return super.getPriorityWorkMode(i);
    }

    @Override // com.realsil.sdk.dfu.utils.DfuAdapter
    public List<OtaModeInfo> getSupportedModes() {
        com.realsil.sdk.dfu.m.a aVar = this.M;
        if (aVar != null) {
            return aVar.c();
        }
        return super.getSupportedModes();
    }

    public boolean isHogpConnect(String str) {
        return isHogpConnect(getRemoteDevice(str));
    }

    @Override // com.realsil.sdk.dfu.utils.BluetoothDfuAdapter
    public void processBluetoothStateChanged(int i) {
        super.processBluetoothStateChanged(i);
        if (i != 10 || Build.VERSION.SDK_INT < 29) {
            return;
        }
        if (isPreparing()) {
            ZLogger.v("auto disconnect when bt off");
            disconnect();
            notifyLock();
            a(new ConnectionException(0));
            return;
        }
        notifyStateChanged(4097);
    }

    @Override // com.realsil.sdk.dfu.utils.BluetoothDfuAdapter
    public void processBondStateChanged(int i) {
        switch (i) {
            case 10:
                ZLogger.v(this.h, "BOND_NONE");
                if (this.q != 533 || this.A == null) {
                    return;
                }
                ZLogger.v(this.h, "createBond");
                this.A.createBond();
                return;
            case 11:
                ZLogger.v(this.h, "BOND_BONDING");
                return;
            case 12:
                ZLogger.v(this.h, "BOND_BONDED");
                if (this.q == 532) {
                    if (this.A != null) {
                        if (!isHogpConnect(this.C)) {
                            ZLogger.v(this.j, "hid not connect");
                            notifyStateChanged(529);
                            BluetoothProfileManager.getInstance().connectHid(this.A);
                            return;
                        }
                        ZLogger.v(this.j, "hid already connected");
                        a(this.C);
                        return;
                    }
                    return;
                }
                notifyLock();
                return;
            default:
                return;
        }
    }

    @Override // com.realsil.sdk.dfu.utils.BluetoothDfuAdapter
    public void processHidStateChanged(int i) {
        super.processHidStateChanged(i);
        if (i == 0) {
            ZLogger.v(this.h, " Braodcast: RCU Disconnected!");
            if (this.q == 529) {
                a(new ConnectionException(0));
            }
        } else if (i == 1) {
            ZLogger.v(this.h, "RCU Connecting!");
        } else if (i != 2) {
            if (i != 3) {
                return;
            }
            ZLogger.v(this.h, " Braodcast: RCU Disconnecting!");
        } else {
            ZLogger.v(this.h, "RCU Connected!");
            if (this.q == 529) {
                boolean z = this.h;
                ZLogger.v(z, "connect gatt: " + this.C);
                notifyStateChanged(DfuAdapter.STATE_PREPARE_CONNECTING);
                this.H.connect(this.C, this.S);
            }
        }
    }

    public void readDeviceInfo() {
        BluetoothGattService service;
        BluetoothGattService service2;
        BluetoothGatt bluetoothGatt = this.I;
        if (bluetoothGatt == null) {
            return;
        }
        ConnectParams connectParams = this.l;
        if (connectParams != null) {
            service = bluetoothGatt.getService(connectParams.getOtaServiceUuid());
            service2 = this.I.getService(this.l.getDfuServiceUuid());
        } else {
            service = bluetoothGatt.getService(f.c);
            service2 = this.I.getService(f.e);
        }
        this.J = service;
        this.K = service2;
        notifyStateChanged(DfuAdapter.STATE_READ_PROTOCOL_TYPE);
        if (service == null) {
            boolean z = this.h;
            ZLogger.d(z, "not find OTA_SERVICE = " + f.c);
            this.L = null;
        } else {
            boolean z2 = this.h;
            ZLogger.v(z2, "find OTA_SERVICE = " + f.c);
            this.L = service.getCharacteristic(f.d);
        }
        if (this.L != null) {
            boolean z3 = this.h;
            ZLogger.v(z3, "find CHARACTERISTIC_PROTOCOL_TYPE = " + f.d);
            a(this.L);
            return;
        }
        b(0);
        com.realsil.sdk.dfu.m.a aVar = this.M;
        if (aVar != null) {
            aVar.a(this.C, this.I, this.J, this.K);
        }
    }

    @Override // com.realsil.sdk.dfu.utils.BluetoothDfuAdapter
    public boolean startOtaProcedure(OtaDeviceInfo otaDeviceInfo, DfuConfig dfuConfig, QcConfig qcConfig, boolean z) {
        if (super.startOtaProcedure(otaDeviceInfo, dfuConfig, qcConfig, z)) {
            notifyStateChanged(1025);
            GlobalGatt globalGatt = this.H;
            if (globalGatt != null) {
                globalGatt.unRegisterCallback(this.C, this.S);
            }
            com.realsil.sdk.dfu.m.a aVar = this.M;
            if (aVar != null) {
                aVar.a();
            }
            boolean a2 = this.k.a(dfuConfig);
            if (!a2) {
                notifyStateChanged(1026);
            }
            return a2;
        }
        return false;
    }

    @Override // com.realsil.sdk.dfu.utils.DfuAdapter
    public boolean b() {
        boolean a2;
        if (!super.b()) {
            notifyStateChanged(4098);
            return false;
        }
        if (this.l.isHid()) {
            a2 = b(this.C);
            if (!a2) {
                a2 = a(this.C);
            }
        } else {
            a2 = a(this.C);
        }
        if (!a2) {
            notifyStateChanged(4098);
        }
        return a2;
    }

    @Override // com.realsil.sdk.dfu.utils.BluetoothDfuAdapter
    public void c() {
        super.c();
        GlobalGatt globalGatt = GlobalGatt.getInstance();
        this.H = globalGatt;
        if (globalGatt == null) {
            GlobalGatt.initial(this.mContext);
            this.H = GlobalGatt.getInstance();
        }
    }

    public final boolean d() {
        boolean z;
        if (this.q == 537) {
            ZLogger.w("discoverServices already started");
            return false;
        }
        notifyStateChanged(DfuAdapter.STATE_DISCOVERY_SERVICE);
        ZLogger.v(this.j, "discoverServices...");
        BluetoothGatt bluetoothGatt = this.I;
        if (bluetoothGatt != null) {
            z = bluetoothGatt.discoverServices();
        } else {
            ZLogger.d("mBtGatt is null");
            z = false;
        }
        if (!z) {
            ZLogger.d(this.i, "discoverServices failed");
            if (isPreparing()) {
                a(new ConnectionException(1));
            }
            return false;
        }
        synchronized (this.o) {
            try {
                ZLogger.v("wait discover service complete");
                this.o.wait(30000L);
            } catch (InterruptedException e2) {
                ZLogger.d(this.i, e2.toString());
            }
        }
        if (this.q == 537) {
            ZLogger.w("discoverServices timeout");
            disconnect();
            return false;
        }
        readDeviceInfo();
        return true;
    }

    public boolean isHogpConnect(BluetoothDevice bluetoothDevice) {
        return bluetoothDevice != null && BluetoothProfileManager.getInstance().getConnectionState(4, bluetoothDevice) == 2;
    }

    public final boolean a(String str) {
        notifyStateChanged(DfuAdapter.STATE_PREPARE_CONNECTING);
        return this.H.connect(str, this.S);
    }

    public final boolean a(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            return false;
        }
        if (!BluetoothProfileManager.getInstance().isProfileSupported(4)) {
            ZLogger.w("HID_HOST not supported");
            return false;
        }
        int bondState = bluetoothDevice.getBondState();
        if (bondState != 12) {
            boolean z = this.h;
            ZLogger.d(z, "connect with not bond device, bond first, current state: " + bondState);
            notifyStateChanged(512, 20);
            return bluetoothDevice.createBond();
        } else if (isHogpConnect(bluetoothDevice.getAddress())) {
            ZLogger.d("hogp already connected");
            return a(bluetoothDevice.getAddress());
        } else if (BluetoothDeviceImpl.removeBond(bluetoothDevice)) {
            ZLogger.d("remove bond first");
            notifyStateChanged(533);
            return false;
        } else {
            ZLogger.d("remove bond failed");
            notifyStateChanged(529);
            return BluetoothProfileManager.getInstance().connectHid(bluetoothDevice);
        }
    }

    public static GattDfuAdapter getInstance(Context context, DfuAdapter.DfuHelperCallback dfuHelperCallback) {
        if (G == null) {
            synchronized (GattDfuAdapter.class) {
                if (G == null) {
                    G = new GattDfuAdapter(context.getApplicationContext(), dfuHelperCallback);
                }
            }
        }
        return G;
    }

    public final boolean b(String str) {
        return a(getRemoteDevice(str));
    }

    public GattDfuAdapter(Context context, DfuAdapter.DfuHelperCallback dfuHelperCallback) {
        this.mContext = context;
        this.n = dfuHelperCallback;
        c();
    }

    public final void b(int i) {
        boolean z = true;
        ZLogger.v(String.format("protocolType=0x%04X", Integer.valueOf(i)));
        com.realsil.sdk.dfu.m.a aVar = this.M;
        if (aVar != null) {
            aVar.a();
        }
        if (i == 16) {
            this.M = new com.realsil.sdk.dfu.r.a();
        } else if (i == 20) {
            this.M = new com.realsil.sdk.dfu.p.a();
        } else if (i == 18) {
            ConnectParams connectParams = this.l;
            if (connectParams == null || !"BeeTgt02".equals(connectParams.getLocalName())) {
                z = false;
            }
            this.M = new com.realsil.sdk.dfu.n.a(i, z);
        } else if (i == 19) {
            ConnectParams connectParams2 = this.l;
            if (connectParams2 == null || !"BeeTgt02".equals(connectParams2.getLocalName())) {
                z = false;
            }
            this.M = new com.realsil.sdk.dfu.o.a(i, z);
        } else {
            ConnectParams connectParams3 = this.l;
            if (connectParams3 == null || !"BeeTgt02".equals(connectParams3.getLocalName())) {
                z = false;
            }
            this.M = new com.realsil.sdk.dfu.q.a(0, z);
        }
        this.M.a(this.l, this.C, this.I, this.J, this.K, this.N);
    }

    public final boolean a(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (this.I != null && bluetoothGattCharacteristic != null) {
            if (this.h) {
                ZLogger.v(String.format(Locale.US, "readCharacteristic:(%d) %s", Integer.valueOf(bluetoothGattCharacteristic.getInstanceId()), bluetoothGattCharacteristic.getUuid().toString()));
            }
            return this.I.readCharacteristic(bluetoothGattCharacteristic);
        }
        ZLogger.w("mBtGatt is null maybe disconnected just now");
        return false;
    }
}
