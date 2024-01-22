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
import com.realsil.sdk.core.bluetooth.BluetoothProfileCallback;
import com.realsil.sdk.core.bluetooth.BluetoothProfileManager;
import com.realsil.sdk.core.bluetooth.GlobalGatt;
import com.realsil.sdk.core.bluetooth.impl.BluetoothGattImpl;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.dfu.exception.ConnectionException;
import com.realsil.sdk.dfu.exception.LoadFileException;
import com.realsil.sdk.dfu.image.FirmwareLoaderX;
import com.realsil.sdk.dfu.image.LoadParams;
import com.realsil.sdk.dfu.image.pack.SubFileInfo;
import com.realsil.sdk.dfu.m.a;
import com.realsil.sdk.dfu.m.f;
import com.realsil.sdk.dfu.model.BinInfo;
import com.realsil.sdk.dfu.model.DfuConfig;
import com.realsil.sdk.dfu.model.OtaDeviceInfo;
import com.realsil.sdk.dfu.model.OtaModeInfo;
import com.realsil.sdk.dfu.params.QcConfig;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
/* loaded from: classes12.dex */
public class HidDfuAdapter extends BluetoothDfuAdapter implements f {
    public static volatile HidDfuAdapter G;
    public GlobalGatt H;
    public BluetoothGatt I;
    public BluetoothGattService J;
    public BluetoothGattService K;
    public BluetoothGattCharacteristic L;
    public com.realsil.sdk.dfu.m.a M;
    public a.c N = new a();
    public Runnable O = new b();
    public Runnable P = new c();
    public Handler Q = new Handler(Looper.getMainLooper());
    public BluetoothGattCallback R = new d();

    /* loaded from: classes12.dex */
    public class a implements a.c {
        public a() {
        }

        @Override // com.realsil.sdk.dfu.m.a.c
        public void a(int i) {
            if (i == 1) {
                if (HidDfuAdapter.this.isPreparing()) {
                    HidDfuAdapter.this.notifyStateChanged(527);
                } else if (HidDfuAdapter.this.isBackConnecting()) {
                    if (HidDfuAdapter.this.a()) {
                        HidDfuAdapter.this.notifyStateChanged(DfuAdapter.STATE_BACKCONNECT_VALIDATE);
                        HidDfuAdapter hidDfuAdapter = HidDfuAdapter.this;
                        if (!hidDfuAdapter.validate(hidDfuAdapter.getOtaDeviceInfo())) {
                            HidDfuAdapter.this.notifyStateChanged(DfuAdapter.STATE_BACKCONNECT_VALIDATE_FAILED);
                        } else {
                            HidDfuAdapter.this.notifyStateChanged(DfuAdapter.STATE_BACKCONNECT_COMPLETED);
                        }
                    } else {
                        HidDfuAdapter.this.notifyStateChanged(DfuAdapter.STATE_BACKCONNECT_COMPLETED);
                    }
                } else {
                    HidDfuAdapter hidDfuAdapter2 = HidDfuAdapter.this;
                    ZLogger.v(hidDfuAdapter2.j, String.format("ignore, is not in preparing or backconnect state: 0x%04X", Integer.valueOf(hidDfuAdapter2.q)));
                }
            }
            if (i == 2) {
                if (HidDfuAdapter.this.isPreparing() || HidDfuAdapter.this.isBackConnecting()) {
                    HidDfuAdapter.this.a(new ConnectionException(5));
                } else {
                    ZLogger.d(String.format("ignore, is not in preparing state: 0x%04X", Integer.valueOf(HidDfuAdapter.this.q)));
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
            HidDfuAdapter hidDfuAdapter = HidDfuAdapter.this;
            hidDfuAdapter.B = hidDfuAdapter.getBondState(hidDfuAdapter.C);
            ZLogger.v(">> mBondState: " + HidDfuAdapter.this.B);
            HidDfuAdapter hidDfuAdapter2 = HidDfuAdapter.this;
            if (hidDfuAdapter2.B == 11) {
                hidDfuAdapter2.a(15000L);
                try {
                    Thread.sleep(800L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            HidDfuAdapter.this.e();
        }
    }

    /* loaded from: classes12.dex */
    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (!HidDfuAdapter.this.checkState(512, 24) && !HidDfuAdapter.this.checkState(2048, 24)) {
                if (HidDfuAdapter.this.q == 534) {
                    ZLogger.d("STATE_PROCESS_PAIRING_REQUEST: wait to discover service");
                    new Thread(HidDfuAdapter.this.O).start();
                    return;
                }
                ZLogger.d("ignore state:" + HidDfuAdapter.this.q);
                return;
            }
            new Thread(HidDfuAdapter.this.O).start();
        }
    }

    /* loaded from: classes12.dex */
    public class d extends BluetoothGattCallback {
        public d() {
        }

        public final void a() {
            if (HidDfuAdapter.this.isPreparing()) {
                HidDfuAdapter.this.notifyLock();
                HidDfuAdapter.this.a(new ConnectionException(0));
                return;
            }
            HidDfuAdapter.this.notifyStateChanged(4097);
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
                    HidDfuAdapter.this.b(wrap.getShort(0));
                    if (HidDfuAdapter.this.M != null) {
                        com.realsil.sdk.dfu.m.a aVar = HidDfuAdapter.this.M;
                        HidDfuAdapter hidDfuAdapter = HidDfuAdapter.this;
                        String str = hidDfuAdapter.C;
                        BluetoothGatt bluetoothGatt2 = hidDfuAdapter.I;
                        HidDfuAdapter hidDfuAdapter2 = HidDfuAdapter.this;
                        aVar.a(str, bluetoothGatt2, hidDfuAdapter2.J, hidDfuAdapter2.K);
                        return;
                    }
                    return;
                }
                return;
            }
            boolean z = HidDfuAdapter.this.h;
            ZLogger.d(z, "Characteristic read error: " + i);
            if (f.d.equals(uuid)) {
                if (HidDfuAdapter.this.isPreparing()) {
                    HidDfuAdapter.this.a(new ConnectionException(5));
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
                    HidDfuAdapter.this.disconnect();
                    a();
                }
            } else {
                HidDfuAdapter hidDfuAdapter = HidDfuAdapter.this;
                hidDfuAdapter.I = hidDfuAdapter.H.getBluetoothGatt(HidDfuAdapter.this.C);
                ConnectParams connectParams = HidDfuAdapter.this.l;
                if (connectParams != null && connectParams.isRefreshCache()) {
                    BluetoothGattImpl.refresh(bluetoothGatt);
                }
                if (bluetoothGatt != null) {
                    if (HidDfuAdapter.this.isPreparing()) {
                        HidDfuAdapter hidDfuAdapter2 = HidDfuAdapter.this;
                        if (hidDfuAdapter2.q != 536) {
                            hidDfuAdapter2.notifyStateChanged(DfuAdapter.STATE_PENDDING_DISCOVERY_SERVICE);
                            if (HidDfuAdapter.this.Q != null) {
                                ZLogger.v("delay to discover service for : 1600");
                                HidDfuAdapter.this.Q.removeCallbacks(HidDfuAdapter.this.P);
                                boolean postDelayed = HidDfuAdapter.this.Q.postDelayed(HidDfuAdapter.this.P, 1600L);
                                if (postDelayed) {
                                    return;
                                }
                                boolean z = HidDfuAdapter.this.h;
                                ZLogger.v(z, "postDelayed:" + postDelayed);
                                return;
                            }
                            ZLogger.v(HidDfuAdapter.this.j, "mHandler == null");
                            return;
                        }
                        return;
                    } else if (!HidDfuAdapter.this.isBackConnecting() || HidDfuAdapter.this.checkState(2048, 24)) {
                        return;
                    } else {
                        HidDfuAdapter.this.notifyStateChanged(2048, 24);
                        if (HidDfuAdapter.this.Q != null) {
                            ZLogger.v("delay to discover service for : 1600");
                            HidDfuAdapter.this.Q.removeCallbacks(HidDfuAdapter.this.P);
                            boolean postDelayed2 = HidDfuAdapter.this.Q.postDelayed(HidDfuAdapter.this.P, 1600L);
                            if (postDelayed2) {
                                return;
                            }
                            boolean z2 = HidDfuAdapter.this.h;
                            ZLogger.v(z2, "postDelayed:" + postDelayed2);
                            return;
                        }
                        ZLogger.v(HidDfuAdapter.this.j, "mHandler == null");
                        return;
                    }
                }
                a();
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            HidDfuAdapter hidDfuAdapter = HidDfuAdapter.this;
            if (hidDfuAdapter.q == 1025) {
                ZLogger.d("ignore, when it is ota processing");
            } else if (i == 0) {
                if (hidDfuAdapter.checkState(512, 25) || HidDfuAdapter.this.checkState(2048, 25)) {
                    HidDfuAdapter hidDfuAdapter2 = HidDfuAdapter.this;
                    hidDfuAdapter2.notifyStateChanged(hidDfuAdapter2.p, 27);
                    HidDfuAdapter.this.notifyLock();
                }
            } else {
                ZLogger.w("service discovery failed !!!");
                if (HidDfuAdapter.this.isPreparing()) {
                    HidDfuAdapter.this.a(new ConnectionException(1));
                }
            }
        }
    }

    /* loaded from: classes12.dex */
    public class e extends BluetoothProfileCallback {
        public e() {
        }

        @Override // com.realsil.sdk.core.bluetooth.BluetoothProfileCallback
        public void onHidStateChanged(BluetoothDevice bluetoothDevice, int i) {
            super.onHidStateChanged(bluetoothDevice, i);
            HidDfuAdapter hidDfuAdapter = HidDfuAdapter.this;
            int i2 = hidDfuAdapter.q;
            if (i2 == 2065) {
                BluetoothDevice bluetoothDevice2 = hidDfuAdapter.A;
                if (bluetoothDevice2 == null) {
                    ZLogger.v("device has already been clean");
                    HidDfuAdapter.this.notifyStateChanged(DfuAdapter.STATE_BACKCONNECT_FAILED);
                } else if (!bluetoothDevice2.equals(bluetoothDevice)) {
                    ZLogger.v(String.format("target device is %s, ignore device:%s", HidDfuAdapter.this.A.toString(), bluetoothDevice.toString()));
                } else if (i == 2) {
                    ZLogger.v("pending to back connect with previous device");
                    HidDfuAdapter hidDfuAdapter2 = HidDfuAdapter.this;
                    hidDfuAdapter2.connectDevice(hidDfuAdapter2.l);
                } else if (i == 0) {
                    ZLogger.v("proile disconnected");
                    HidDfuAdapter.this.notifyStateChanged(DfuAdapter.STATE_BACKCONNECT_FAILED);
                }
            } else if (i2 == 529) {
                BluetoothDevice bluetoothDevice3 = hidDfuAdapter.A;
                if (bluetoothDevice3 == null) {
                    ZLogger.v("device has already been clean");
                    HidDfuAdapter.this.notifyStateChanged(4098);
                } else if (!bluetoothDevice3.equals(bluetoothDevice)) {
                    ZLogger.v(String.format("target device is %s, ignore device:%s", HidDfuAdapter.this.A.toString(), bluetoothDevice.toString()));
                } else if (i == 0) {
                    ZLogger.v(HidDfuAdapter.this.h, "RCU Disconnected!");
                    HidDfuAdapter.this.a(new ConnectionException(0));
                } else if (i == 1) {
                    ZLogger.v(HidDfuAdapter.this.j, "RCU Connecting!");
                } else if (i != 2) {
                    if (i != 3) {
                        return;
                    }
                    ZLogger.v(HidDfuAdapter.this.j, " RCU Disconnecting!");
                } else {
                    ZLogger.v(HidDfuAdapter.this.h, "RCU Connected!");
                    HidDfuAdapter hidDfuAdapter3 = HidDfuAdapter.this;
                    hidDfuAdapter3.a(hidDfuAdapter3.C);
                }
            } else {
                ZLogger.v(hidDfuAdapter.j, String.format("isgnore hid state change, when state is 0x%04X", Integer.valueOf(hidDfuAdapter.p)));
            }
        }
    }

    public HidDfuAdapter(Context context) {
        this.mContext = context;
        c();
    }

    public static HidDfuAdapter getInstance(Context context) {
        if (G == null) {
            synchronized (HidDfuAdapter.class) {
                if (G == null) {
                    G = new HidDfuAdapter(context.getApplicationContext());
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

    @Override // com.realsil.sdk.dfu.utils.DfuAdapter
    public boolean connectBack() {
        if (!super.connectBack()) {
            ZLogger.v("connect back failed");
            return false;
        }
        BluetoothDevice bluetoothDevice = this.A;
        if (bluetoothDevice == null) {
            ZLogger.d("device has already been clean, no need to connect back");
            return false;
        }
        int bondState = bluetoothDevice.getBondState();
        this.B = bondState;
        if (bondState != 12) {
            ZLogger.d(this.h, "device is not bonded, maybe has something wrong");
            return false;
        } else if (this.y.getConnectionState(4, this.A) != 2) {
            ZLogger.v("wait hid profile auto connected");
            notifyStateChanged(2048, 17);
            return true;
        } else {
            this.p = 2048;
            ZLogger.d(this.h, "profile has already connected, pending to connect");
            return connectDevice(this.l);
        }
    }

    @Override // com.realsil.sdk.dfu.utils.BluetoothDfuAdapter, com.realsil.sdk.dfu.utils.DfuAdapter
    public boolean connectDevice(ConnectParams connectParams) {
        if (super.connectDevice(connectParams)) {
            String str = this.C;
            if (str != null) {
                if (Build.VERSION.SDK_INT >= 19) {
                    if (!str.equals(this.l.getAddress())) {
                        this.H.unRegisterCallback(this.C, this.R);
                        this.H.close(this.C);
                    }
                } else if (!equals(str, this.l.getAddress())) {
                    this.H.unRegisterCallback(this.C, this.R);
                    this.H.close(this.C);
                }
            }
            this.A = getRemoteDevice(this.l.getAddress());
            this.C = this.l.getAddress();
            if (this.p != 512) {
                this.p = 512;
            }
            boolean d2 = d();
            if (!d2) {
                notifyStateChanged(4098);
            }
            return d2;
        }
        return false;
    }

    @Override // com.realsil.sdk.dfu.utils.BluetoothDfuAdapter, com.realsil.sdk.dfu.utils.DfuAdapter
    public void destroy() {
        super.destroy();
        GlobalGatt globalGatt = this.H;
        if (globalGatt != null) {
            globalGatt.unRegisterCallback(this.C, this.R);
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
                    if (this.H.isCallbackRegisted(this.C, this.R)) {
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
                ZLogger.d("mGlobalGatt == null");
                notifyStateChanged(4097);
            }
        }
        this.I = null;
    }

    @Override // com.realsil.sdk.dfu.utils.BluetoothDfuAdapter
    public BluetoothProfileCallback getBluetoothProfileCallback() {
        return new e();
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

    public boolean isHogpConnect(BluetoothDevice bluetoothDevice) {
        return bluetoothDevice != null && BluetoothProfileManager.getInstance().getConnectionState(4, bluetoothDevice) == 2;
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
                if (checkState(512, 21)) {
                    if (this.A == null) {
                        ZLogger.v("device has already been clean");
                        notifyStateChanged(4098);
                        return;
                    }
                    notifyStateChanged(512, 20);
                    ZLogger.v(this.j, "createBond");
                    this.A.createBond();
                    return;
                }
                return;
            case 11:
                ZLogger.v(this.h, "BOND_BONDING");
                return;
            case 12:
                ZLogger.v(this.h, "BOND_BONDED");
                if (checkState(512, 20)) {
                    BluetoothDevice bluetoothDevice = this.A;
                    if (bluetoothDevice == null) {
                        ZLogger.v("device has already been clean");
                        notifyStateChanged(4098);
                        return;
                    } else if (!isHogpConnect(bluetoothDevice)) {
                        ZLogger.v("hid not connect");
                        a(this.A);
                        return;
                    } else {
                        ZLogger.v("hid already connected");
                        a(this.C);
                        return;
                    }
                }
                notifyLock();
                return;
            default:
                return;
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
        notifyStateChanged(this.p, 28);
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
                globalGatt.unRegisterCallback(this.C, this.R);
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
    public boolean validate(OtaDeviceInfo otaDeviceInfo) {
        List<SubFileInfo> list;
        if (super.validate(otaDeviceInfo)) {
            if (this.t.isBankEnabled()) {
                if (!otaDeviceInfo.isBankEnabled()) {
                    ZLogger.d("conflict: not support bank");
                    return false;
                } else if (this.t.getActiveBank() == otaDeviceInfo.getActiveBank()) {
                    ZLogger.d("conflict: active bank not changed");
                    return false;
                }
            } else if (this.u != null) {
                try {
                    BinInfo loadImageBinInfo = FirmwareLoaderX.loadImageBinInfo(new LoadParams.Builder().with(this.mContext).fileLocation(this.u.getFileLocation()).setFilePath(this.u.getFilePath()).setSectionSizeCheckEnabled(this.u.isSectionSizeCheckEnabled()).setIcCheckEnabled(this.u.isIcCheckEnabled()).setFileSuffix(this.u.getFileSuffix()).versionCheckEnabled(true).setOtaDeviceInfo(otaDeviceInfo).build());
                    if (loadImageBinInfo != null && loadImageBinInfo.status == 4096 && (list = loadImageBinInfo.supportSubFileInfos) != null && list.size() > 0) {
                        ZLogger.d("conflict: version not apply");
                        return false;
                    }
                } catch (LoadFileException e2) {
                    e2.printStackTrace();
                }
            }
            return true;
        }
        return false;
    }

    @Override // com.realsil.sdk.dfu.utils.DfuAdapter
    public boolean b() {
        if (!super.b()) {
            notifyStateChanged(4098);
            return false;
        }
        boolean d2 = d();
        if (!d2) {
            notifyStateChanged(4098);
        }
        return d2;
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
        boolean a2;
        int bondState = getBondState(this.C);
        this.B = bondState;
        ZLogger.v(this.h, String.format(Locale.US, ">> mBondState: %d", Integer.valueOf(bondState)));
        if (this.l.isHid()) {
            if (this.B != 12) {
                boolean z = this.h;
                ZLogger.d(z, "connect with not bond device, bond first, current state: " + this.B);
                notifyStateChanged(512, 20);
                a2 = this.A.createBond();
            } else if (isHogpConnect(this.A)) {
                ZLogger.d("hogp already connected");
                a2 = a(this.C);
            } else {
                a2 = a(this.A);
            }
            return !a2 ? a(this.C) : a2;
        }
        return a(this.C);
    }

    public final boolean e() {
        if (this.q == 537) {
            ZLogger.w("discoverServices already started");
            return false;
        } else if (this.I == null) {
            ZLogger.w("mBtGatt is null");
            return false;
        } else {
            notifyStateChanged(this.p | 25);
            ZLogger.v(this.j, "discoverServices...");
            if (!this.I.discoverServices()) {
                ZLogger.d("discoverServices failed");
                if (isPreparing()) {
                    a(new ConnectionException(1));
                }
                return false;
            }
            synchronized (this.o) {
                try {
                    ZLogger.v(this.j, "wait discover service complete");
                    this.o.wait(30000L);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                    ZLogger.e(e2.toString());
                }
            }
            if (this.q == 537) {
                ZLogger.d("discoverServices timeout");
                disconnect();
                return false;
            }
            readDeviceInfo();
            return true;
        }
    }

    public final boolean a(String str) {
        notifyStateChanged(this.p | 23);
        boolean z = this.j;
        ZLogger.v(z, "connect gatt: " + this.C);
        return this.H.connect(str, this.R);
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

    public final boolean a(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            return false;
        }
        notifyStateChanged(this.p | 17);
        return BluetoothProfileManager.getInstance().connectHid(bluetoothDevice);
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
