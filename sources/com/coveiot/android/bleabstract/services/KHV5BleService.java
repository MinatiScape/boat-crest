package com.coveiot.android.bleabstract.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import com.clevertap.android.sdk.Constants;
import com.coveiot.android.bleabstract.BleApiUtils;
import com.coveiot.android.bleabstract.NotificationInfo;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.api.JStyleUUID;
import com.coveiot.android.bleabstract.models.Clove1790BleState;
import com.coveiot.android.bleabstract.preferences.PreferenceManager1790;
import com.coveiot.android.bleabstract.response.ConnectionError;
import com.coveiot.android.bleabstract.utils.jstyleUtils.JStyleConstants;
import com.coveiot.android.jstylesdk.JstyleResponseListener;
import com.coveiot.android.jstylesdk.api.JstyleActivityModeHistoryReq;
import com.coveiot.android.jstylesdk.api.JstyleBaseReq;
import com.coveiot.android.jstylesdk.api.JstyleBaseRes;
import com.coveiot.android.jstylesdk.api.JstyleOnceHRReq;
import com.coveiot.android.jstylesdk.api.JstyleSleepReq;
import com.coveiot.android.jstylesdk.api.JstyleWalkReq;
import com.coveiot.android.jstylesdk.error.JstyleError;
import com.coveiot.android.jstylesdk.error.JstyleErrorType;
import com.coveiot.android.jstylesdk.model.JstyleLiveResponse;
import com.coveiot.sdk.ble.eventbus.BleEventBusManager;
import com.coveiot.sdk.ble.events.ConnectionType;
import com.coveiot.sdk.ble.model.BleDeviceInfo;
import com.coveiot.sdk.ble.model.CommandObject;
import com.coveiot.sdk.ble.utils.BleUtils;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import com.jstyle.blesdk.Util.BleSDK;
import com.jstyle.blesdk.callback.DataListener;
import com.jstyle.blesdk.cmdenum.SendCmdState;
import com.jstyle.blesdk.model.DeviceBean;
import com.jstyle.blesdk.model.MyDeviceTime;
import com.jstyle.blesdk1860.constant.BleConst;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
/* loaded from: classes2.dex */
public class KHV5BleService extends Service implements DataListener {
    public static final String D = KHV5BleService.class.getSimpleName();
    public ConnectionError A;
    public JstyleBaseReq C;
    public BluetoothManager b;
    public Clove1790BleState.BleState bluetoothConnectionStatus;
    public BluetoothAdapter c;
    public BluetoothGatt d;
    public String e;
    public ConnectionType f;
    public Handler i;
    public Handler j;
    public Handler k;
    public BluetoothDevice mBluetoothDevice;
    public Handler r;
    public BluetoothGattCharacteristic readWriteCharacteristic;
    public Handler s;

    /* renamed from: a  reason: collision with root package name */
    public final IBinder f3812a = new BtServiceBinder();
    public Handler g = new Handler();
    public final Handler h = new Handler(Looper.getMainLooper());
    public int l = 0;
    public LinkedList<CommandObject> m = new LinkedList<>();
    public int n = 0;
    public int o = 50;
    public List<Map<String, String>> p = new ArrayList();
    public boolean q = false;
    public int t = 128;
    public Queue<byte[]> u = new LinkedList();
    public final Runnable v = new Runnable() { // from class: com.coveiot.android.bleabstract.services.KHV5BleService.1
        @Override // java.lang.Runnable
        public void run() {
            if (KHV5BleService.this.bluetoothConnectionStatus == Clove1790BleState.BleState.SCANNING) {
                LogHelper.d(KHV5BleService.D, "restart initiated", ModuleNames.BLEABSTRACT.getModuleName());
                BleApiManager.getInstance(KHV5BleService.this).getBleApi().restartService();
            }
        }
    };
    public final Runnable w = new Runnable() { // from class: com.coveiot.android.bleabstract.services.KHV5BleService.5
        @Override // java.lang.Runnable
        public void run() {
            LinkedList<CommandObject> linkedList = KHV5BleService.this.m;
            if (linkedList != null && linkedList.size() > 0) {
                KHV5BleService.this.m.clear();
            }
            BleDeviceInfo.clearInstance();
            KHV5BleService kHV5BleService = KHV5BleService.this;
            kHV5BleService.getClass();
            Calendar calendar = Calendar.getInstance();
            int i = calendar.get(1);
            int i2 = calendar.get(5);
            int i3 = calendar.get(11);
            int i4 = calendar.get(12);
            int i5 = calendar.get(13);
            MyDeviceTime myDeviceTime = new MyDeviceTime();
            myDeviceTime.setYear(i);
            myDeviceTime.setMonth(calendar.get(2) + 1);
            myDeviceTime.setDay(i2);
            myDeviceTime.setHour(i3);
            myDeviceTime.setMinute(i4);
            myDeviceTime.setSecond(i5);
            kHV5BleService.writeValue(BleSDK.SetDeviceTime(myDeviceTime));
            KHV5BleService.this.l = 0;
        }
    };
    @RequiresApi(21)
    public ScanCallback x = new ScanCallback() { // from class: com.coveiot.android.bleabstract.services.KHV5BleService.6
        @Override // android.bluetooth.le.ScanCallback
        public void onScanFailed(int i) {
            super.onScanFailed(i);
            LogHelper.e("onScanFailed", "Error code " + i, ModuleNames.BLEABSTRACT.getModuleName());
            if (i != 1) {
                if (i == 2) {
                    BleApiManager.getInstance(KHV5BleService.this).getBleApi().restartService();
                    return;
                }
                KHV5BleService kHV5BleService = KHV5BleService.this;
                Clove1790BleState.BleState bleState = Clove1790BleState.BleState.DISCONNECTED;
                String str = KHV5BleService.D;
                kHV5BleService.a(bleState, true);
            }
        }

        @Override // android.bluetooth.le.ScanCallback
        public void onScanResult(int i, ScanResult scanResult) {
            super.onScanResult(i, scanResult);
            try {
                if (Build.VERSION.SDK_INT < 21 || !scanResult.getDevice().getAddress().equalsIgnoreCase(KHV5BleService.this.e)) {
                    return;
                }
                String str = KHV5BleService.D;
                ModuleNames moduleNames = ModuleNames.BLEABSTRACT;
                LogHelper.d(str, "Scan stopped, device found", moduleNames.getModuleName());
                KHV5BleService.this.stopScan();
                KHV5BleService.this.a(Clove1790BleState.BleState.CONNECTING, true);
                Handler handler = KHV5BleService.this.i;
                if (handler != null) {
                    handler.removeCallbacks(null);
                }
                LogHelper.d(str, "connect to device after scan", moduleNames.getModuleName());
                KHV5BleService.this.a(scanResult.getDevice());
            } catch (Exception e) {
                LogHelper.e(KHV5BleService.D, e.toString(), ModuleNames.BLEABSTRACT.getModuleName());
            }
        }
    };
    public final BroadcastReceiver y = new BroadcastReceiver() { // from class: com.coveiot.android.bleabstract.services.KHV5BleService.7
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("android.bluetooth.adapter.action.STATE_CHANGED")) {
                int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE);
                if (intExtra == 3 || intExtra == 10) {
                    KHV5BleService kHV5BleService = KHV5BleService.this;
                    Clove1790BleState.BleState bleState = Clove1790BleState.BleState.DISCONNECTED;
                    String str = KHV5BleService.D;
                    kHV5BleService.a(bleState, true);
                    KHV5BleService.this.stopScan();
                    KHV5BleService.this.closeGattConnection();
                    KHV5BleService.this.clearAllServiceParameters();
                } else if (intExtra != 12) {
                } else {
                    KHV5BleService.this.stopScan();
                    KHV5BleService.this.clearAllServiceParameters();
                    KHV5BleService.this.initServiceParams();
                    if (PreferenceManager1790.getInstance(KHV5BleService.this.getApplicationContext()).isBandUnpaired().booleanValue()) {
                        return;
                    }
                    KHV5BleService kHV5BleService2 = KHV5BleService.this;
                    kHV5BleService2.e = PreferenceManager1790.getInstance(kHV5BleService2.getApplicationContext()).getConnectedDeviceMacAddress();
                    KHV5BleService kHV5BleService3 = KHV5BleService.this;
                    kHV5BleService3.f = BleUtils.getConnectionType(kHV5BleService3.getApplicationContext());
                    if (KHV5BleService.this.c.isEnabled()) {
                        KHV5BleService.this.closeGattConnection();
                        KHV5BleService.this.a(Clove1790BleState.BleState.CONNECTING, true);
                        KHV5BleService.this.reconnectToConfiguredDevice();
                    }
                }
            } else if (action.equalsIgnoreCase("action_stop_service")) {
                try {
                    KHV5BleService.this.closeGattConnection();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                KHV5BleService kHV5BleService4 = KHV5BleService.this;
                Clove1790BleState.BleState bleState2 = Clove1790BleState.BleState.DISCONNECTED;
                String str2 = KHV5BleService.D;
                kHV5BleService4.a(bleState2, true);
                KHV5BleService.this.clearAllServiceParameters();
            }
        }
    };
    public long z = -1;
    public BluetoothGattCallback B = new BluetoothGattCallback() { // from class: com.coveiot.android.bleabstract.services.KHV5BleService.8
        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            super.onCharacteristicChanged(bluetoothGatt, bluetoothGattCharacteristic);
            if (KHV5BleService.this.d == null) {
                return;
            }
            String str = KHV5BleService.D;
            LogHelper.i(str, "onCharacteristicChanged: " + BleApiUtils.byte2Hex(bluetoothGattCharacteristic.getValue()), ModuleNames.BLEABSTRACT.getModuleName());
            KHV5BleService kHV5BleService = KHV5BleService.this;
            kHV5BleService.getClass();
            BleSDK.DataParsingWithData(bluetoothGattCharacteristic.getValue(), kHV5BleService);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            super.onCharacteristicRead(bluetoothGatt, bluetoothGattCharacteristic, i);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            super.onCharacteristicWrite(bluetoothGatt, bluetoothGattCharacteristic, i);
            if (i == 0) {
                KHV5BleService.this.nextQueue();
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onConnectionStateChange(final BluetoothGatt bluetoothGatt, int i, int i2) {
            KHV5BleService kHV5BleService = KHV5BleService.this;
            kHV5BleService.f = BleUtils.getConnectionType(kHV5BleService.getApplicationContext());
            KHV5BleService.this.z = System.currentTimeMillis();
            if (i == 0) {
                if (i2 == 2) {
                    LogHelper.d(KHV5BleService.D, "onConnectionStateChange: Connected", ModuleNames.BLEABSTRACT.getModuleName());
                    KHV5BleService kHV5BleService2 = KHV5BleService.this;
                    kHV5BleService2.A = null;
                    kHV5BleService2.s.postDelayed(new Runnable() { // from class: com.coveiot.android.bleabstract.services.KHV5BleService.8.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(KHV5BleService.this, "android.permission.BLUETOOTH_CONNECT") == 0) {
                                bluetoothGatt.discoverServices();
                            }
                        }
                    }, 1000L);
                    return;
                } else if (i2 == 0) {
                    KHV5BleService.this.A = new ConnectionError(i, System.currentTimeMillis());
                    String str = KHV5BleService.D;
                    ModuleNames moduleNames = ModuleNames.BLEABSTRACT;
                    LogHelper.d(str, "onConnectionStateChange: Disconnected", moduleNames.getModuleName());
                    LinkedList<CommandObject> linkedList = KHV5BleService.this.m;
                    if (linkedList != null && linkedList.size() > 0) {
                        KHV5BleService.this.m.clear();
                    }
                    KHV5BleService.this.a(Clove1790BleState.BleState.DISCONNECTED, true);
                    KHV5BleService kHV5BleService3 = KHV5BleService.this;
                    if (kHV5BleService3.f == ConnectionType.RECONNECT_ON_DISCONNECT) {
                        LogHelper.d(str, "Initiating reconnect", moduleNames.getModuleName());
                        KHV5BleService.this.reconnectToConfiguredDevice();
                    } else {
                        kHV5BleService3.closeGattConnection();
                    }
                    KHV5BleService.this.u.clear();
                    return;
                } else {
                    return;
                }
            }
            KHV5BleService.this.A = new ConnectionError(i, System.currentTimeMillis());
            String str2 = KHV5BleService.D;
            ModuleNames moduleNames2 = ModuleNames.BLEABSTRACT;
            LogHelper.e(str2, "onConnectionStateChange Error GATT.  Error id : " + i, moduleNames2.getModuleName());
            if (i == 133) {
                if (KHV5BleService.this.f == ConnectionType.RECONNECT_ON_DISCONNECT) {
                    LogHelper.d(str2, "ConnectionType is ++ " + KHV5BleService.this.f, moduleNames2.getModuleName());
                    KHV5BleService.this.l = 0;
                    LogHelper.d(str2, "133 error appeared 1 time  disconnect event and scan device", moduleNames2.getModuleName());
                    KHV5BleService.this.a(Clove1790BleState.BleState.DISCONNECTED, true);
                    KHV5BleService.this.reconnectToConfiguredDevice();
                    return;
                }
                LogHelper.d(str2, "ConnectionType is ++ " + KHV5BleService.this.f, moduleNames2.getModuleName());
                KHV5BleService kHV5BleService4 = KHV5BleService.this;
                kHV5BleService4.l = 0;
                kHV5BleService4.a(Clove1790BleState.BleState.DISCONNECTED, true);
                return;
            }
            LogHelper.d(str2, " onConnectionStateChange error code " + i, moduleNames2.getModuleName());
            KHV5BleService kHV5BleService5 = KHV5BleService.this;
            if (kHV5BleService5.f == ConnectionType.RECONNECT_ON_DISCONNECT) {
                int i3 = kHV5BleService5.l + 1;
                kHV5BleService5.l = i3;
                if (i3 <= 2) {
                    kHV5BleService5.a(Clove1790BleState.BleState.DISCONNECTED, true);
                    KHV5BleService.this.reconnectToConfiguredDevice();
                    return;
                }
                LogHelper.d(str2, "257 or 8 error appeared 1 time  disconnect event and scan device", moduleNames2.getModuleName());
                KHV5BleService.this.a(Clove1790BleState.BleState.DISCONNECTED, true);
                KHV5BleService.this.closeGattConnection();
                if (i == 257) {
                    BleApiManager.getInstance(KHV5BleService.this).getBleApi().restartService();
                    return;
                }
                return;
            }
            LogHelper.d(str2, "connectype is ++ " + KHV5BleService.this.f, moduleNames2.getModuleName());
            KHV5BleService kHV5BleService6 = KHV5BleService.this;
            kHV5BleService6.l = 0;
            kHV5BleService6.closeGattConnection();
            KHV5BleService.this.a(Clove1790BleState.BleState.DISCONNECTED, true);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorRead(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            super.onDescriptorRead(bluetoothGatt, bluetoothGattDescriptor, i);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            super.onDescriptorWrite(bluetoothGatt, bluetoothGattDescriptor, i);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onMtuChanged(BluetoothGatt bluetoothGatt, int i, int i2) {
            super.onMtuChanged(bluetoothGatt, i, i2);
            String str = KHV5BleService.D;
            LogHelper.d(str, "onMtuChanged mtu " + i + " status " + i2, ModuleNames.BLEABSTRACT.getModuleName());
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onPhyRead(BluetoothGatt bluetoothGatt, int i, int i2, int i3) {
            super.onPhyRead(bluetoothGatt, i, i2, i3);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onPhyUpdate(BluetoothGatt bluetoothGatt, int i, int i2, int i3) {
            super.onPhyUpdate(bluetoothGatt, i, i2, i3);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onReadRemoteRssi(BluetoothGatt bluetoothGatt, int i, int i2) {
            super.onReadRemoteRssi(bluetoothGatt, i, i2);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onReliableWriteCompleted(BluetoothGatt bluetoothGatt, int i) {
            super.onReliableWriteCompleted(bluetoothGatt, i);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onServicesDiscovered(final BluetoothGatt bluetoothGatt, int i) {
            super.onServicesDiscovered(bluetoothGatt, i);
            String str = KHV5BleService.D;
            ModuleNames moduleNames = ModuleNames.BLEABSTRACT;
            LogHelper.e(str, i + "", moduleNames.getModuleName());
            if (i == 0) {
                LogHelper.d(str, "service discovered method", moduleNames.getModuleName());
                KHV5BleService kHV5BleService = KHV5BleService.this;
                kHV5BleService.d = bluetoothGatt;
                LogHelper.e(str, "initialize ble services", moduleNames.getModuleName());
                if (bluetoothGatt.getServices() != null && bluetoothGatt.getServices().size() != 0) {
                    kHV5BleService.readWriteCharacteristic = null;
                    kHV5BleService.setCharacteristicNotification(true);
                    if (kHV5BleService.readWriteCharacteristic == null) {
                        LogHelper.d(str, " read  or write characteristic is null", moduleNames.getModuleName());
                        kHV5BleService.stopScan();
                        kHV5BleService.closeGattConnection();
                        kHV5BleService.a(Clove1790BleState.BleState.DISCONNECTED, true);
                        kHV5BleService.reconnectToConfiguredDevice();
                    } else {
                        kHV5BleService.a(Clove1790BleState.BleState.CONNECTED, true);
                        kHV5BleService.g.removeCallbacks(null);
                        kHV5BleService.g.postDelayed(kHV5BleService.w, Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS);
                    }
                } else {
                    LogHelper.d(str, " gatt service is null or empty", moduleNames.getModuleName());
                    kHV5BleService.stopScan();
                    kHV5BleService.closeGattConnection();
                    kHV5BleService.a(Clove1790BleState.BleState.DISCONNECTED, true);
                    kHV5BleService.reconnectToConfiguredDevice();
                }
                KHV5BleService.this.r.post(new Runnable() { // from class: com.coveiot.android.bleabstract.services.KHV5BleService.8.2
                    @Override // java.lang.Runnable
                    public void run() {
                        if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(KHV5BleService.this, "android.permission.BLUETOOTH_CONNECT") == 0) {
                            bluetoothGatt.requestMtu(512);
                        }
                    }
                });
                return;
            }
            BleApiManager.getInstance(KHV5BleService.this).getBleApi().restartService();
        }
    };

    /* loaded from: classes2.dex */
    public class BtServiceBinder extends Binder {
        public BtServiceBinder() {
        }

        public KHV5BleService getService() {
            return KHV5BleService.this;
        }
    }

    public final void a(final BluetoothDevice bluetoothDevice) {
        a(Clove1790BleState.BleState.CONNECTING, true);
        this.h.removeCallbacksAndMessages(null);
        this.mBluetoothDevice = bluetoothDevice;
        if (this.d != null) {
            if (Build.VERSION.SDK_INT >= 31 && ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH_CONNECT") != 0) {
                return;
            }
            this.d.disconnect();
            this.d.close();
            this.d = null;
        }
        this.g.postDelayed(new Runnable() { // from class: com.coveiot.android.bleabstract.services.KHV5BleService.2
            @Override // java.lang.Runnable
            public void run() {
                if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(KHV5BleService.this, "android.permission.BLUETOOTH_CONNECT") == 0) {
                    KHV5BleService kHV5BleService = KHV5BleService.this;
                    kHV5BleService.d = bluetoothDevice.connectGatt(kHV5BleService.getApplicationContext(), false, KHV5BleService.this.B);
                }
            }
        }, 1000L);
    }

    public final void b() {
        BluetoothAdapter bluetoothAdapter = this.c;
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
            return;
        }
        reconnectToConfiguredDevice();
    }

    public void clearAllServiceParameters() {
        closeGattConnection();
        Handler handler = this.j;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        Handler handler2 = this.i;
        if (handler2 != null) {
            handler2.removeCallbacksAndMessages(null);
        }
        Handler handler3 = this.g;
        if (handler3 != null) {
            handler3.removeCallbacksAndMessages(null);
        }
        Handler handler4 = this.k;
        if (handler4 != null) {
            handler4.removeCallbacksAndMessages(null);
        }
        Handler handler5 = this.r;
        if (handler5 != null) {
            handler5.removeCallbacksAndMessages(null);
        }
        Handler handler6 = this.s;
        if (handler6 != null) {
            handler6.removeCallbacksAndMessages(null);
        }
        a(Clove1790BleState.BleState.DISCONNECTED, false);
        this.g = null;
        this.i = null;
        this.j = null;
        this.k = null;
    }

    public void clearQueue() {
        LinkedList<CommandObject> linkedList = this.m;
        if (linkedList != null && linkedList.size() > 0) {
            this.m.clear();
        }
        Queue<byte[]> queue = this.u;
        if (queue == null || queue.size() <= 0) {
            return;
        }
        this.u.clear();
    }

    public void clearResponseList() {
        List<Map<String, String>> list = this.p;
        if (list == null || list.size() <= 0) {
            return;
        }
        this.p.clear();
    }

    public void closeGattConnection() {
        if (this.d == null) {
            return;
        }
        String str = D;
        LogHelper.i(str, "Closing GATT Connection " + this.d, ModuleNames.BLEABSTRACT.getModuleName());
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH_CONNECT") == 0) {
            this.d.disconnect();
            this.d.close();
            this.d = null;
        }
    }

    public void connect(String str) {
        BleDeviceInfo.clearInstance();
        PreferenceManager1790.getInstance(getApplicationContext()).saveConnectedDeviceMAcAddress(str);
        PreferenceManager1790.getInstance(getApplicationContext()).saveConnectionType(ConnectionType.RECONNECT_ON_DISCONNECT.name());
        String str2 = D;
        LogHelper.d(str2, "connection type ++ " + BleUtils.getConnectionType(this), ModuleNames.BLEABSTRACT.getModuleName());
        b();
    }

    public void dataCallback(DeviceBean deviceBean, SendCmdState sendCmdState) {
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public void dataCallback(Map<String, Object> map) {
        char c;
        char c2;
        String str;
        String str2 = (String) map.get("DataType");
        boolean booleanValue = ((Boolean) map.get("End")).booleanValue();
        JstyleBaseRes jstyleBaseRes = new JstyleBaseRes();
        String str3 = D;
        ModuleNames moduleNames = ModuleNames.BLEABSTRACT;
        LogHelper.i(str3, "dataCallback: " + str2 + " -- " + booleanValue, moduleNames.getModuleName());
        if (this.C != null) {
            LogHelper.d("v5 khCurrentCommand", " dataCallback -- " + this.C.getDataType(), moduleNames.getModuleName());
        }
        str2.hashCode();
        switch (str2.hashCode()) {
            case -1726342286:
                if (str2.equals("GetActivityModeData")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1200941421:
                if (str2.equals("ECGDATA")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -1158558318:
                if (str2.equals("SetAlarmClockWithAllClock")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -787314209:
                if (str2.equals("SetSedentaryReminder")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -639880425:
                if (str2.equals("RealTimeStep")) {
                    c2 = 4;
                    c = c2;
                    break;
                }
                c = 65535;
                break;
            case -13544106:
                if (str2.equals("ECGQuality")) {
                    c2 = 5;
                    c = c2;
                    break;
                }
                c = 65535;
                break;
            case 115609795:
                if (str2.equals("GetDeviceBatteryLevel")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 210920480:
                if (str2.equals("GetDetailActivityData")) {
                    c2 = 7;
                    c = c2;
                    break;
                }
                c = 65535;
                break;
            case 343843441:
                if (str2.equals("PPGDATA")) {
                    c2 = '\b';
                    c = c2;
                    break;
                }
                c = 65535;
                break;
            case 494645178:
                if (str2.equals("GetDetailSleepData")) {
                    c2 = '\t';
                    c = c2;
                    break;
                }
                c = 65535;
                break;
            case 769120590:
                if (str2.equals("GetStaticHR")) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case 1567484428:
                if (str2.equals("GetDeviceVersion")) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            case 1676528870:
                if (str2.equals(BleConst.ECGResult)) {
                    c = '\f';
                    break;
                }
                c = 65535;
                break;
            case 2049043094:
                if (str2.equals("FindMobilePhoneMode")) {
                    c2 = '\r';
                    c = c2;
                    break;
                }
                c = 65535;
                break;
            case 2056519717:
                if (str2.equals("SetDeviceTime")) {
                    c2 = 14;
                    c = c2;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                JstyleBaseReq jstyleBaseReq = this.C;
                if (jstyleBaseReq == null || !jstyleBaseReq.getDataType().equalsIgnoreCase(str2)) {
                    return;
                }
                this.p.addAll((List) map.get("Data"));
                this.n++;
                if (booleanValue) {
                    this.n = 0;
                    jstyleBaseRes.setBaseReq(this.C);
                    jstyleBaseRes.obj = this.p;
                    this.C.getResponseListener().onResponse(jstyleBaseRes);
                    this.q = false;
                }
                if (this.n == this.o) {
                    this.n = 0;
                    if (booleanValue) {
                        this.n = 0;
                        jstyleBaseRes.setBaseReq(this.C);
                        jstyleBaseRes.obj = this.p;
                        this.C.getResponseListener().onResponse(jstyleBaseRes);
                        this.q = false;
                        return;
                    }
                    this.q = true;
                    JstyleActivityModeHistoryReq jstyleActivityModeHistoryReq = new JstyleActivityModeHistoryReq();
                    jstyleActivityModeHistoryReq.setDataType("GetActivityModeData");
                    jstyleActivityModeHistoryReq.setMode(JStyleConstants.Constants.getMODE_CONTINUE());
                    jstyleActivityModeHistoryReq.setReqId(this.C.getReqId());
                    sendRequest(jstyleActivityModeHistoryReq, this.C.getResponseListener());
                    return;
                }
                return;
            case 1:
                LogHelper.d(str3, "NEW_ECGDATA", moduleNames.getModuleName());
                Map<String, byte[]> dataList = getDataList(map);
                JstyleLiveResponse jstyleLiveResponse = new JstyleLiveResponse();
                jstyleLiveResponse.setDataType("ECGDATA");
                jstyleLiveResponse.setObj(dataList);
                BleEventBusManager.getInstance().getEventBus().post(jstyleLiveResponse);
                return;
            case 2:
                LogHelper.d(str3, "SetAlarmClockWithAllClock", moduleNames.getModuleName());
                JstyleBaseReq jstyleBaseReq2 = this.C;
                if (jstyleBaseReq2 == null || !jstyleBaseReq2.getDataType().equalsIgnoreCase(str2)) {
                    return;
                }
                jstyleBaseRes.setBaseReq(this.C);
                this.C.getResponseListener().onResponse(jstyleBaseRes);
                return;
            case 3:
                LogHelper.d(str3, "SetSedentaryReminder", moduleNames.getModuleName());
                JstyleBaseReq jstyleBaseReq3 = this.C;
                if (jstyleBaseReq3 == null || !jstyleBaseReq3.getDataType().equalsIgnoreCase(str2)) {
                    return;
                }
                jstyleBaseRes.setBaseReq(this.C);
                this.C.getResponseListener().onResponse(jstyleBaseRes);
                return;
            case 4:
                LogHelper.d(str3, "RealTimeStep", moduleNames.getModuleName());
                Map<String, String> data = getData(map);
                JstyleLiveResponse jstyleLiveResponse2 = new JstyleLiveResponse();
                jstyleLiveResponse2.setDataType("RealTimeStep");
                jstyleLiveResponse2.setObj(data);
                BleEventBusManager.getInstance().getEventBus().post(jstyleLiveResponse2);
                return;
            case 5:
                LogHelper.d(str3, "NEW_ECGDATA", moduleNames.getModuleName());
                Map<String, String> data2 = getData(map);
                JstyleLiveResponse jstyleLiveResponse3 = new JstyleLiveResponse();
                jstyleLiveResponse3.setDataType("ECGQuality");
                jstyleLiveResponse3.setObj(data2);
                BleEventBusManager.getInstance().getEventBus().post(jstyleLiveResponse3);
                return;
            case 6:
            case 11:
                JstyleBaseReq jstyleBaseReq4 = this.C;
                if (jstyleBaseReq4 == null || !jstyleBaseReq4.getDataType().equalsIgnoreCase(str2)) {
                    return;
                }
                Map<String, String> data3 = getData(map);
                jstyleBaseRes.setBaseReq(this.C);
                jstyleBaseRes.obj = data3;
                this.C.getResponseListener().onResponse(jstyleBaseRes);
                return;
            case 7:
                JstyleBaseReq jstyleBaseReq5 = this.C;
                if (jstyleBaseReq5 == null || !jstyleBaseReq5.getDataType().equalsIgnoreCase(str2)) {
                    return;
                }
                this.p.addAll((List) map.get("Data"));
                this.n++;
                if (booleanValue) {
                    this.n = 0;
                    jstyleBaseRes.setBaseReq(this.C);
                    jstyleBaseRes.obj = this.p;
                    this.C.getResponseListener().onResponse(jstyleBaseRes);
                    this.q = false;
                }
                if (this.n == this.o) {
                    this.n = 0;
                    if (booleanValue) {
                        jstyleBaseRes.setBaseReq(this.C);
                        this.n = 0;
                        jstyleBaseRes.obj = this.p;
                        this.C.getResponseListener().onResponse(jstyleBaseRes);
                        this.q = false;
                        return;
                    }
                    this.q = true;
                    JstyleWalkReq jstyleWalkReq = new JstyleWalkReq();
                    jstyleWalkReq.setDataType("GetDetailActivityData");
                    jstyleWalkReq.setMode(JStyleConstants.Constants.getMODE_CONTINUE());
                    jstyleWalkReq.setReqId(this.C.getReqId());
                    sendRequest(jstyleWalkReq, this.C.getResponseListener());
                    return;
                }
                return;
            case '\b':
                LogHelper.d(str3, "NEW_PPGDATA", moduleNames.getModuleName());
                Map<String, byte[]> dataList2 = getDataList(map);
                JstyleLiveResponse jstyleLiveResponse4 = new JstyleLiveResponse();
                jstyleLiveResponse4.setDataType("PPGDATA");
                jstyleLiveResponse4.setObj(dataList2);
                BleEventBusManager.getInstance().getEventBus().post(jstyleLiveResponse4);
                return;
            case '\t':
                JstyleBaseReq jstyleBaseReq6 = this.C;
                if (jstyleBaseReq6 == null || !jstyleBaseReq6.getDataType().equalsIgnoreCase(str2)) {
                    return;
                }
                this.p.addAll((List) map.get("Data"));
                this.n++;
                if (booleanValue) {
                    this.n = 0;
                    jstyleBaseRes.setBaseReq(this.C);
                    jstyleBaseRes.obj = this.p;
                    this.C.getResponseListener().onResponse(jstyleBaseRes);
                    this.q = false;
                }
                if (this.n == this.o) {
                    this.n = 0;
                    if (booleanValue) {
                        this.n = 0;
                        jstyleBaseRes.setBaseReq(this.C);
                        jstyleBaseRes.obj = this.p;
                        this.C.getResponseListener().onResponse(jstyleBaseRes);
                        this.q = false;
                        return;
                    }
                    this.q = true;
                    JstyleSleepReq jstyleSleepReq = new JstyleSleepReq();
                    jstyleSleepReq.setDataType("GetDetailSleepData");
                    jstyleSleepReq.setReqId(this.C.getReqId());
                    jstyleSleepReq.setMode(JStyleConstants.Constants.getMODE_CONTINUE());
                    sendRequest(jstyleSleepReq, this.C.getResponseListener());
                    return;
                }
                return;
            case '\n':
                JstyleBaseReq jstyleBaseReq7 = this.C;
                if (jstyleBaseReq7 == null || !jstyleBaseReq7.getDataType().equalsIgnoreCase(str2)) {
                    return;
                }
                this.p.addAll((List) map.get("Data"));
                this.n++;
                if (booleanValue) {
                    this.n = 0;
                    jstyleBaseRes.setBaseReq(this.C);
                    jstyleBaseRes.obj = this.p;
                    this.C.getResponseListener().onResponse(jstyleBaseRes);
                    this.q = false;
                }
                if (this.n == this.o) {
                    this.n = 0;
                    if (booleanValue) {
                        this.n = 0;
                        jstyleBaseRes.setBaseReq(this.C);
                        jstyleBaseRes.obj = this.p;
                        this.C.getResponseListener().onResponse(jstyleBaseRes);
                        this.q = false;
                        return;
                    }
                    this.q = true;
                    JstyleOnceHRReq jstyleOnceHRReq = new JstyleOnceHRReq();
                    jstyleOnceHRReq.setDataType("GetStaticHR");
                    jstyleOnceHRReq.setMode(JStyleConstants.Constants.getMODE_CONTINUE());
                    jstyleOnceHRReq.setReqId(this.C.getReqId());
                    sendRequest(jstyleOnceHRReq, this.C.getResponseListener());
                    return;
                }
                return;
            case '\f':
                JstyleBaseReq jstyleBaseReq8 = this.C;
                if (jstyleBaseReq8 == null || !jstyleBaseReq8.getDataType().equalsIgnoreCase(BleConst.ENTERECG)) {
                    return;
                }
                LogHelper.d(str3, BleConst.ECGResult, moduleNames.getModuleName());
                Map<String, String> data4 = getData(map);
                String str4 = data4.get(DeviceKey.ECGResultValue);
                if (kotlin.text.m.isBlank(str4)) {
                    return;
                }
                LogHelper.d(str3, "resultValue : " + str4, moduleNames.getModuleName());
                if (Integer.parseInt(str4) == 12) {
                    LogHelper.d(str3, "ECGResult : 12", moduleNames.getModuleName());
                    this.C.getResponseListener().onFailure(new JstyleError(JstyleErrorType.LEAD_CONNECTION_FAILED, "LEAD CONNECTION FAILED"));
                    return;
                } else if (Integer.parseInt(str4) == 255 || Integer.parseInt(str4) == 1 || Integer.parseInt(str4) == 11) {
                    return;
                } else {
                    if (Integer.parseInt(str4) == 3) {
                        jstyleBaseRes.setBaseReq(this.C);
                        jstyleBaseRes.obj = data4;
                        this.C.getResponseListener().onResponse(jstyleBaseRes);
                        return;
                    } else if (Integer.parseInt(str4) == 2) {
                        this.C.getResponseListener().onFailure(new JstyleError(JstyleErrorType.BAND_INSTRUCTION_NOT_FOLLOWED, "MEASUREMENT TIME OUT"));
                        return;
                    } else if (Integer.parseInt(str4) == 6) {
                        this.C.getResponseListener().onFailure(new JstyleError(JstyleErrorType.BAND_INSTRUCTION_NOT_FOLLOWED, "USER TURNED OFF MANUALLY"));
                        return;
                    } else if (Integer.parseInt(str4) == 10) {
                        this.C.getResponseListener().onFailure(new JstyleError(JstyleErrorType.BAND_INSTRUCTION_NOT_FOLLOWED, "WEAK ECG SIGNAL"));
                        return;
                    } else {
                        this.C.getResponseListener().onFailure(new JstyleError(JstyleErrorType.BAND_INSTRUCTION_NOT_FOLLOWED, "BAND INSTRUCTION NOT FOLLOWED"));
                        return;
                    }
                }
            case '\r':
                LogHelper.d(str3, "FindMobilePhoneMode", moduleNames.getModuleName());
                if (str2.equalsIgnoreCase("FindMobilePhoneMode")) {
                    JstyleLiveResponse jstyleLiveResponse5 = new JstyleLiveResponse();
                    jstyleLiveResponse5.setDataType("FindMobilePhoneMode");
                    BleEventBusManager.getInstance().getEventBus().post(jstyleLiveResponse5);
                    return;
                }
                return;
            case 14:
                Map<String, String> data5 = getData(map);
                if (data5 == null || (str = data5.get(DeviceKey.KPhoneDataLength)) == null) {
                    return;
                }
                this.t = Integer.parseInt(str);
                LogHelper.d(str3, "KPhoneDataLength == " + this.t, moduleNames.getModuleName());
                return;
            default:
                return;
        }
    }

    public void dataCallback(Map<String, String> map, SendCmdState sendCmdState) {
    }

    public void disconnectAndForget() {
        Handler handler = this.k;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        clearQueue();
        stopScan();
        closeGattConnection();
        clearAllServiceParameters();
        unregisterReceivers();
        PreferenceManager1790.getInstance(getApplicationContext()).saveConnectedDeviceMAcAddress("");
        PreferenceManager1790.getInstance(getApplicationContext()).saveConnectionType(ConnectionType.DONT_CONNECT_ON_DISCONNECT.name());
        stopForeground(true);
        stopSelf();
    }

    public void disconnectAndRetainMacAddress() {
        Handler handler = this.k;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        clearQueue();
        stopScan();
        closeGattConnection();
        clearAllServiceParameters();
        unregisterReceivers();
        PreferenceManager1790.getInstance(getApplicationContext()).saveConnectionType(ConnectionType.DONT_CONNECT_ON_DISCONNECT.name());
        stopForeground(true);
        stopSelf();
    }

    public ConnectionError getConnectionError() {
        return this.A;
    }

    public long getConnectionStageChangeTimeStamp() {
        return this.z;
    }

    public Clove1790BleState.BleState getConnectionState() {
        return this.bluetoothConnectionStatus;
    }

    public Map<String, String> getData(Map<String, Object> map) {
        return (Map) map.get("Data");
    }

    public Map<String, byte[]> getDataList(Map<String, Object> map) {
        return (Map) map.get("Data");
    }

    public void initBluetoothDevice() {
        b();
    }

    public void initServiceParams() {
        if (this.j == null) {
            this.j = new Handler();
        }
        if (this.i == null) {
            this.i = new Handler();
        }
        if (this.g == null) {
            this.g = new Handler();
        }
        if (this.k == null) {
            this.k = new Handler();
        }
        if (this.r == null) {
            this.r = new Handler();
        }
        if (this.s == null) {
            this.s = new Handler();
        }
        a(Clove1790BleState.BleState.DISCONNECTED, false);
    }

    public void nextQueue() {
        Queue<byte[]> queue = this.u;
        writeValue(queue != null ? queue.poll() : null);
    }

    public void offerData(byte[] bArr) {
        offerValue(bArr);
    }

    public void offerValue(byte[] bArr) {
        this.u.offer(bArr);
    }

    @Override // android.app.Service
    @Nullable
    public IBinder onBind(Intent intent) {
        return this.f3812a;
    }

    @Override // android.app.Service
    public void onCreate() {
        a();
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x006c, code lost:
        if (r2 == null) goto L7;
     */
    @Override // android.app.Service
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int onStartCommand(android.content.Intent r2, int r3, int r4) {
        /*
            r1 = this;
            r3 = 3
            if (r2 != 0) goto L1f
            android.content.Intent r2 = new android.content.Intent     // Catch: java.lang.Exception -> L18
            java.lang.Class<com.coveiot.android.bleabstract.services.KHV5BleService> r4 = com.coveiot.android.bleabstract.services.KHV5BleService.class
            r2.<init>(r1, r4)     // Catch: java.lang.Exception -> L18
            int r4 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Exception -> L18
            r0 = 26
            if (r4 < r0) goto L14
            r1.startForegroundService(r2)     // Catch: java.lang.Exception -> L18
            goto L17
        L14:
            r1.startService(r2)     // Catch: java.lang.Exception -> L18
        L17:
            return r3
        L18:
            r2 = move-exception
            r2.printStackTrace()
            com.coveiot.android.bleabstract.BleApiUtils.checkExceptionAndShowNotification(r2, r1)
        L1f:
            r1.a()
            android.content.Context r2 = r1.getApplicationContext()
            com.coveiot.android.bleabstract.preferences.PreferenceManager1790 r2 = com.coveiot.android.bleabstract.preferences.PreferenceManager1790.getInstance(r2)
            java.lang.String r2 = r2.getConnectedDeviceMacAddress()
            r1.e = r2
            android.content.Context r2 = r1.getApplicationContext()
            com.coveiot.sdk.ble.events.ConnectionType r2 = com.coveiot.sdk.ble.utils.BleUtils.getConnectionType(r2)
            r1.f = r2
            java.lang.String r2 = com.coveiot.android.bleabstract.services.KHV5BleService.D
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r0 = "Obtained device Address is "
            r4.append(r0)
            java.lang.String r0 = r1.e
            r4.append(r0)
            java.lang.String r4 = r4.toString()
            com.coveiot.sdk.ble.utils.ModuleNames r0 = com.coveiot.sdk.ble.utils.ModuleNames.BLEABSTRACT
            java.lang.String r0 = r0.getModuleName()
            com.coveiot.utils.utility.LogHelper.i(r2, r4, r0)
            r1.clearAllServiceParameters()
            r1.initServiceParams()
            android.bluetooth.BluetoothManager r2 = r1.b
            if (r2 != 0) goto L6f
            java.lang.String r2 = "bluetooth"
            java.lang.Object r2 = r1.getSystemService(r2)
            android.bluetooth.BluetoothManager r2 = (android.bluetooth.BluetoothManager) r2
            r1.b = r2
            if (r2 != 0) goto L6f
            goto Lac
        L6f:
            android.bluetooth.BluetoothManager r2 = r1.b
            android.bluetooth.BluetoothAdapter r2 = r2.getAdapter()
            r1.c = r2
            com.coveiot.sdk.ble.eventbus.BleEventBusManager r2 = com.coveiot.sdk.ble.eventbus.BleEventBusManager.getInstance()     // Catch: java.lang.Exception -> L83
            com.squareup.otto.Bus r2 = r2.getEventBus()     // Catch: java.lang.Exception -> L83
            r2.register(r1)     // Catch: java.lang.Exception -> L83
            goto L87
        L83:
            r2 = move-exception
            r2.printStackTrace()
        L87:
            android.content.IntentFilter r2 = new android.content.IntentFilter     // Catch: java.lang.Exception -> La8
            java.lang.String r4 = "android.bluetooth.adapter.action.STATE_CHANGED"
            r2.<init>(r4)     // Catch: java.lang.Exception -> La8
            android.content.Context r4 = r1.getApplicationContext()     // Catch: java.lang.Exception -> La8
            android.content.BroadcastReceiver r0 = r1.y     // Catch: java.lang.Exception -> La8
            r4.registerReceiver(r0, r2)     // Catch: java.lang.Exception -> La8
            android.content.IntentFilter r2 = new android.content.IntentFilter     // Catch: java.lang.Exception -> La8
            java.lang.String r4 = "action_stop_service"
            r2.<init>(r4)     // Catch: java.lang.Exception -> La8
            android.content.Context r4 = r1.getApplicationContext()     // Catch: java.lang.Exception -> La8
            android.content.BroadcastReceiver r0 = r1.y     // Catch: java.lang.Exception -> La8
            r4.registerReceiver(r0, r2)     // Catch: java.lang.Exception -> La8
            goto Lac
        La8:
            r2 = move-exception
            r2.printStackTrace()
        Lac:
            java.lang.String r2 = r1.e
            boolean r2 = com.coveiot.sdk.ble.utils.BleUtils.isEmpty(r2)
            if (r2 != 0) goto Lb7
            r1.b()
        Lb7:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.bleabstract.services.KHV5BleService.onStartCommand(android.content.Intent, int, int):int");
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    public void reconnectToConfiguredDevice() {
        closeGattConnection();
        this.e = PreferenceManager1790.getInstance(getApplicationContext()).getConnectedDeviceMacAddress();
        this.f = BleUtils.getConnectionType(getApplicationContext());
        if (this.c.isEnabled()) {
            if (!BleUtils.isEmpty(this.e) && BluetoothAdapter.checkBluetoothAddress(this.e)) {
                a(this.c.getRemoteDevice(this.e));
                return;
            }
            String str = D;
            LogHelper.e(str, "Trying to connect, address is empty or small case " + this.e, ModuleNames.BLEABSTRACT.getModuleName());
            return;
        }
        LogHelper.e(D, "Bluetooth is disabled", ModuleNames.BLEABSTRACT.getModuleName());
    }

    public void restartService() {
        Handler handler = this.k;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        clearQueue();
        closeGattConnection();
        clearAllServiceParameters();
        unregisterReceivers();
        stopScan();
        PreferenceManager1790.getInstance(getApplicationContext()).saveConnectionType(ConnectionType.RECONNECT_ON_DISCONNECT.name());
        stopForeground(true);
        stopSelf();
    }

    public void scanLeDevice() {
        if (!PreferenceManager1790.getInstance(getApplicationContext()).isBandUnpaired().booleanValue()) {
            this.j.removeCallbacksAndMessages(null);
            this.i.removeCallbacks(null);
            stopScan();
            this.j.postDelayed(new Runnable() { // from class: com.coveiot.android.bleabstract.services.KHV5BleService.4
                @Override // java.lang.Runnable
                @RequiresApi(api = 21)
                public void run() {
                    String str = KHV5BleService.D;
                    ModuleNames moduleNames = ModuleNames.BLEABSTRACT;
                    LogHelper.d(str, "Scanning...", moduleNames.getModuleName());
                    KHV5BleService.this.closeGattConnection();
                    try {
                        KHV5BleService kHV5BleService = KHV5BleService.this;
                        kHV5BleService.e = PreferenceManager1790.getInstance(kHV5BleService.getApplicationContext()).getConnectedDeviceMacAddress();
                        LogHelper.d(str, "BLE deviceAddress as " + KHV5BleService.this.e, moduleNames.getModuleName());
                        if (BleUtils.isEmpty(KHV5BleService.this.e)) {
                            return;
                        }
                        ScanFilter.Builder builder = new ScanFilter.Builder();
                        builder.setDeviceAddress(KHV5BleService.this.e);
                        new ArrayList().add(builder.build());
                        ScanSettings build = new ScanSettings.Builder().setScanMode(1).build();
                        KHV5BleService.this.a(Clove1790BleState.BleState.SCANNING, true);
                        KHV5BleService kHV5BleService2 = KHV5BleService.this;
                        kHV5BleService2.h.postDelayed(kHV5BleService2.v, 1800000L);
                        if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(KHV5BleService.this, "android.permission.BLUETOOTH_SCAN") == 0) {
                            KHV5BleService.this.c.getBluetoothLeScanner().startScan((List<ScanFilter>) null, build, KHV5BleService.this.x);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, 500L);
            return;
        }
        a(Clove1790BleState.BleState.DISCONNECTED, true);
    }

    public void sendCommandToBand(byte[] bArr) {
        writeValue(bArr);
    }

    public void sendRequest(JstyleBaseReq jstyleBaseReq, JstyleResponseListener jstyleResponseListener) {
        jstyleBaseReq.setResponseListener(jstyleResponseListener);
        this.C = jstyleBaseReq;
        LogHelper.d("v5 khCurrentCommand", " sendRequest-- " + this.C.getDataType(), ModuleNames.BLEABSTRACT.getModuleName());
        String dataType = jstyleBaseReq.getDataType();
        if (dataType != null) {
            char c = 65535;
            switch (dataType.hashCode()) {
                case -2041249468:
                    if (dataType.equals(JStyleConstants.EnterOTA)) {
                        c = 0;
                        break;
                    }
                    break;
                case -1811662981:
                    if (dataType.equals("SetAutomaticHRMonitoring")) {
                        c = 1;
                        break;
                    }
                    break;
                case -1726342286:
                    if (dataType.equals("GetActivityModeData")) {
                        c = 2;
                        break;
                    }
                    break;
                case -1323245135:
                    if (dataType.equals(BleConst.ENTERECG)) {
                        c = 3;
                        break;
                    }
                    break;
                case -1158558318:
                    if (dataType.equals("SetAlarmClockWithAllClock")) {
                        c = 4;
                        break;
                    }
                    break;
                case -787314209:
                    if (dataType.equals("SetSedentaryReminder")) {
                        c = 5;
                        break;
                    }
                    break;
                case -639880425:
                    if (dataType.equals("RealTimeStep")) {
                        c = 6;
                        break;
                    }
                    break;
                case -541958941:
                    if (dataType.equals("MotorVibrationWithTimes")) {
                        c = 7;
                        break;
                    }
                    break;
                case 115609795:
                    if (dataType.equals("GetDeviceBatteryLevel")) {
                        c = '\b';
                        break;
                    }
                    break;
                case 210920480:
                    if (dataType.equals("GetDetailActivityData")) {
                        c = '\t';
                        break;
                    }
                    break;
                case 494645178:
                    if (dataType.equals("GetDetailSleepData")) {
                        c = '\n';
                        break;
                    }
                    break;
                case 759553291:
                    if (dataType.equals(JStyleConstants.Notification)) {
                        c = 11;
                        break;
                    }
                    break;
                case 769120590:
                    if (dataType.equals("GetStaticHR")) {
                        c = '\f';
                        break;
                    }
                    break;
                case 790362721:
                    if (dataType.equals("SetStepGoal")) {
                        c = '\r';
                        break;
                    }
                    break;
                case 1281238128:
                    if (dataType.equals("SetPersonalInfo")) {
                        c = 14;
                        break;
                    }
                    break;
                case 1567484428:
                    if (dataType.equals("GetDeviceVersion")) {
                        c = 15;
                        break;
                    }
                    break;
                case 2056196614:
                    if (dataType.equals("SetDeviceInfo")) {
                        c = 16;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                case 1:
                case 5:
                case 6:
                case 7:
                case 11:
                case '\r':
                case 14:
                case 16:
                    sendCommandToBand(jstyleBaseReq.getCommandBytes());
                    JstyleBaseReq jstyleBaseReq2 = this.C;
                    if (jstyleBaseReq2 == null || !jstyleBaseReq2.getDataType().equalsIgnoreCase(dataType)) {
                        return;
                    }
                    JstyleBaseRes jstyleBaseRes = new JstyleBaseRes();
                    jstyleBaseRes.setBaseReq(this.C);
                    this.C.getResponseListener().onResponse(jstyleBaseRes);
                    return;
                case 2:
                case '\t':
                case '\n':
                case '\f':
                    if (!this.q) {
                        clearResponseList();
                    }
                    sendCommandToBand(jstyleBaseReq.getCommandBytes());
                    return;
                case 3:
                case '\b':
                case 15:
                    sendCommandToBand(jstyleBaseReq.getCommandBytes());
                    return;
                case 4:
                    byte[] commandBytes = jstyleBaseReq.getCommandBytes();
                    int length = commandBytes.length;
                    int i = this.t;
                    if (length > i) {
                        int i2 = (i / 39) * 39;
                        int length2 = commandBytes.length % i2 == 0 ? commandBytes.length / i2 : (commandBytes.length / i2) + 1;
                        int i3 = 0;
                        while (i3 < length2) {
                            int i4 = i3 + 1;
                            int length3 = i2 * i4 >= commandBytes.length ? commandBytes.length - (i2 * i3) : i2;
                            byte[] bArr = new byte[length3];
                            System.arraycopy(commandBytes, i3 * i2, bArr, 0, length3);
                            offerData(bArr);
                            i3 = i4;
                        }
                        offerData();
                        return;
                    }
                    sendCommandToBand(jstyleBaseReq.getCommandBytes());
                    return;
                default:
                    return;
            }
        }
    }

    public void setCharacteristicNotification(boolean z) {
        BluetoothGattService service;
        BluetoothGattCharacteristic characteristic;
        BluetoothGatt bluetoothGatt = this.d;
        if (bluetoothGatt == null || (service = bluetoothGatt.getService(JStyleUUID.SERVICE_DATA_UUID_1790_1939)) == null || (characteristic = service.getCharacteristic(JStyleUUID.NOTIFY_CHARACTERISTIC_UUID_1790_1939)) == null) {
            return;
        }
        this.readWriteCharacteristic = characteristic;
        if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH_CONNECT") == 0) {
            this.d.setCharacteristicNotification(characteristic, z);
            try {
                Thread.sleep(20L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            BluetoothGattDescriptor descriptor = characteristic.getDescriptor(JStyleUUID.NOTIFY_UUID_1790_1939);
            if (descriptor == null) {
                return;
            }
            descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
            BluetoothGatt bluetoothGatt2 = this.d;
            if (bluetoothGatt2 == null) {
                return;
            }
            bluetoothGatt2.writeDescriptor(descriptor);
        }
    }

    public void stopScan() {
        try {
            BluetoothAdapter bluetoothAdapter = this.c;
            if (bluetoothAdapter == null || bluetoothAdapter.getBluetoothLeScanner() == null) {
                return;
            }
            if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH_SCAN") == 0) {
                this.c.getBluetoothLeScanner().stopScan(this.x);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopService() {
        Handler handler = this.k;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        disconnectAndForget();
    }

    public void stopServiceRetainMacAddress() {
        Handler handler = this.k;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        disconnectAndRetainMacAddress();
    }

    public void unregisterReceivers() {
        if (this.y != null) {
            try {
                getApplicationContext().unregisterReceiver(this.y);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            try {
                BleEventBusManager.getInstance().getEventBus().unregister(this);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void writeValue(byte[] bArr) {
        BluetoothGattService service;
        BluetoothGattCharacteristic characteristic;
        BluetoothGatt bluetoothGatt = this.d;
        if (bluetoothGatt == null || bArr == null || (service = bluetoothGatt.getService(JStyleUUID.SERVICE_DATA_UUID_1790_1939)) == null || (characteristic = service.getCharacteristic(JStyleUUID.DATA_CHARACTERISTIC_UUID_1790_1939)) == null) {
            return;
        }
        byte b = bArr[0];
        characteristic.setValue(bArr);
        String str = D;
        LogHelper.i(str, "writeValue: " + BleApiUtils.byte2Hex(bArr), ModuleNames.BLEABSTRACT.getModuleName());
        if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH_CONNECT") == 0) {
            this.d.writeCharacteristic(characteristic);
        }
    }

    public void offerData() {
        nextQueue();
    }

    public final void a(final Clove1790BleState.BleState bleState, boolean z) {
        Handler handler;
        this.bluetoothConnectionStatus = bleState;
        if (!z || (handler = this.g) == null) {
            return;
        }
        handler.removeCallbacksAndMessages(null);
        this.g.post(new Runnable(this) { // from class: com.coveiot.android.bleabstract.services.KHV5BleService.3
            @Override // java.lang.Runnable
            public void run() {
                BleEventBusManager.getInstance().getEventBus().post(new Clove1790BleState(bleState));
            }
        });
    }

    public final void a() {
        Notification build;
        try {
            NotificationInfo data = BleApiUtils.getData();
            if (data.getAppColor() == 0) {
                BleApiManager.getInstance(this);
                data = BleApiUtils.getData();
                if (data.getAppColor() == 0) {
                    BleApiUtils.getMetadata(this);
                    data = BleApiUtils.getData();
                }
            }
            PendingIntent activity = PendingIntent.getActivity(this, 0, new Intent(data.getAppLauncherActivity()), 67108864);
            int i = Build.VERSION.SDK_INT;
            if (i >= 26) {
                NotificationChannel notificationChannel = new NotificationChannel("101", data.getAppDesc(), 2);
                notificationChannel.enableLights(false);
                ((NotificationManager) getSystemService("notification")).createNotificationChannel(notificationChannel);
                build = new Notification.Builder(this, "101").setContentTitle(data.getAppDesc()).setColor(ContextCompat.getColor(this, data.getAppColor())).setSmallIcon(data.getAppIcon()).setContentIntent(activity).build();
            } else if (i >= 21) {
                build = new Notification.Builder(this).setContentTitle(data.getAppDesc()).setSmallIcon(data.getAppIcon()).setColor(ContextCompat.getColor(this, data.getAppColor())).setContentIntent(activity).build();
            } else {
                Notification.Builder builder = new Notification.Builder(this);
                build = builder.setContentTitle(data.getAppName() + " is running").setSmallIcon(data.getAppIcon()).setContentIntent(activity).build();
            }
            startForeground(101, build);
        } catch (Exception e) {
            e.printStackTrace();
            BleApiUtils.checkExceptionAndShowNotification(e, this);
        }
    }
}
