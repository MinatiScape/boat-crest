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
import com.coveiot.android.bleabstract.models.Clove1810GBleState;
import com.coveiot.android.bleabstract.preferences.PreferenceManager1810G;
import com.coveiot.android.bleabstract.response.ConnectionError;
import com.coveiot.android.bleabstract.utils.jstyleUtils.JStyleConstants;
import com.coveiot.android.jstyle1810gsdk.JstyleResponseListener;
import com.coveiot.android.jstyle1810gsdk.api.JstyleActivityModeHistoryReq;
import com.coveiot.android.jstyle1810gsdk.api.JstyleBaseReq;
import com.coveiot.android.jstyle1810gsdk.api.JstyleBaseRes;
import com.coveiot.android.jstyle1810gsdk.api.JstyleOnceHRReq;
import com.coveiot.android.jstyle1810gsdk.api.JstyleSleepReq;
import com.coveiot.android.jstyle1810gsdk.api.JstyleTemperatureReq;
import com.coveiot.android.jstyle1810gsdk.api.JstyleWalkReq;
import com.coveiot.android.jstyle1810gsdk.error.JstyleError;
import com.coveiot.android.jstyle1810gsdk.error.JstyleErrorType;
import com.coveiot.android.jstyle1810gsdk.model.JstyleLiveResponse;
import com.coveiot.sdk.ble.eventbus.BleEventBusManager;
import com.coveiot.sdk.ble.events.ConnectionType;
import com.coveiot.sdk.ble.model.BleDeviceInfo;
import com.coveiot.sdk.ble.model.CommandObject;
import com.coveiot.sdk.ble.utils.BleUtils;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import com.jstyle.blesdk1860.constant.BleConst;
import com.jstyle.blesdk1860.constant.DeviceKey;
import com.jstyle.sdk1810g.Util.BleSDK;
import com.jstyle.sdk1810g.callback.DataListener;
import com.jstyle.sdk1810g.model.MyDeviceTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
/* loaded from: classes2.dex */
public class KH1810GBleService extends Service implements DataListener {
    public static final String D = KH1810GBleService.class.getSimpleName();
    public JstyleBaseReq C;
    public BluetoothManager b;
    public Clove1810GBleState.BleState bluetoothConnectionStatus;
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
    public ConnectionError z;

    /* renamed from: a  reason: collision with root package name */
    public final IBinder f3767a = new BtServiceBinder();
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
    public final Runnable v = new Runnable() { // from class: com.coveiot.android.bleabstract.services.KH1810GBleService.1
        @Override // java.lang.Runnable
        public void run() {
            if (KH1810GBleService.this.bluetoothConnectionStatus == Clove1810GBleState.BleState.SCANNING) {
                LogHelper.d(KH1810GBleService.D, "restart initiated", ModuleNames.BLEABSTRACT.getModuleName());
                BleApiManager.getInstance(KH1810GBleService.this).getBleApi().restartService();
            }
        }
    };
    public final Runnable w = new Runnable() { // from class: com.coveiot.android.bleabstract.services.KH1810GBleService.5
        @Override // java.lang.Runnable
        public void run() {
            LinkedList<CommandObject> linkedList = KH1810GBleService.this.m;
            if (linkedList != null && linkedList.size() > 0) {
                KH1810GBleService.this.m.clear();
            }
            BleDeviceInfo.clearInstance();
            KH1810GBleService kH1810GBleService = KH1810GBleService.this;
            kH1810GBleService.getClass();
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
            kH1810GBleService.writeValue(BleSDK.SetDeviceTime(myDeviceTime));
            KH1810GBleService.this.j.postDelayed(new Runnable() { // from class: com.coveiot.android.bleabstract.services.KH1810GBleService.5.1
                @Override // java.lang.Runnable
                public void run() {
                    KH1810GBleService kH1810GBleService2 = KH1810GBleService.this;
                    String str = KH1810GBleService.D;
                    kH1810GBleService2.getClass();
                    kH1810GBleService2.writeValue(BleSDK.enableAncs(false));
                }
            }, 500L);
            KH1810GBleService.this.l = 0;
        }
    };
    @RequiresApi(21)
    public ScanCallback x = new ScanCallback() { // from class: com.coveiot.android.bleabstract.services.KH1810GBleService.6
        @Override // android.bluetooth.le.ScanCallback
        public void onScanFailed(int i) {
            super.onScanFailed(i);
            LogHelper.e("onScanFailed", "Error code " + i, ModuleNames.BLEABSTRACT.getModuleName());
            if (i != 1) {
                if (i == 2) {
                    BleApiManager.getInstance(KH1810GBleService.this).getBleApi().restartService();
                    return;
                }
                KH1810GBleService kH1810GBleService = KH1810GBleService.this;
                Clove1810GBleState.BleState bleState = Clove1810GBleState.BleState.DISCONNECTED;
                String str = KH1810GBleService.D;
                kH1810GBleService.a(bleState, true);
            }
        }

        @Override // android.bluetooth.le.ScanCallback
        public void onScanResult(int i, ScanResult scanResult) {
            super.onScanResult(i, scanResult);
            try {
                if (Build.VERSION.SDK_INT < 21 || !scanResult.getDevice().getAddress().equalsIgnoreCase(KH1810GBleService.this.e)) {
                    return;
                }
                String str = KH1810GBleService.D;
                ModuleNames moduleNames = ModuleNames.BLEABSTRACT;
                LogHelper.d(str, "Scan stopped, device found", moduleNames.getModuleName());
                KH1810GBleService.this.stopScan();
                KH1810GBleService.this.a(Clove1810GBleState.BleState.CONNECTING, true);
                Handler handler = KH1810GBleService.this.i;
                if (handler != null) {
                    handler.removeCallbacks(null);
                }
                LogHelper.d(str, "connect to device after scan", moduleNames.getModuleName());
                KH1810GBleService.this.a(scanResult.getDevice());
            } catch (Exception e) {
                LogHelper.e(KH1810GBleService.D, e.toString(), ModuleNames.BLEABSTRACT.getModuleName());
            }
        }
    };
    public final BroadcastReceiver y = new BroadcastReceiver() { // from class: com.coveiot.android.bleabstract.services.KH1810GBleService.7
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("android.bluetooth.adapter.action.STATE_CHANGED")) {
                int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE);
                if (intExtra == 3 || intExtra == 10) {
                    LogHelper.d(KH1810GBleService.D, "Bluetooth state BluetoothAdapter.STATE_OFF", ModuleNames.BLEABSTRACT.getModuleName());
                    KH1810GBleService.this.a(Clove1810GBleState.BleState.DISCONNECTED, true);
                    KH1810GBleService.this.stopScan();
                    KH1810GBleService.this.closeGattConnection();
                    KH1810GBleService.this.clearAllServiceParameters();
                } else if (intExtra != 12) {
                } else {
                    KH1810GBleService.this.stopScan();
                    KH1810GBleService.this.clearAllServiceParameters();
                    KH1810GBleService.this.initServiceParams();
                    String str = KH1810GBleService.D;
                    ModuleNames moduleNames = ModuleNames.BLEABSTRACT;
                    LogHelper.d(str, "Bluetooth state BluetoothAdapter.STATE_ON", moduleNames.getModuleName());
                    if (!PreferenceManager1810G.getInstance(KH1810GBleService.this.getApplicationContext()).isBandUnpaired().booleanValue()) {
                        KH1810GBleService kH1810GBleService = KH1810GBleService.this;
                        kH1810GBleService.e = PreferenceManager1810G.getInstance(kH1810GBleService.getApplicationContext()).getConnectedDeviceMacAddress();
                        KH1810GBleService kH1810GBleService2 = KH1810GBleService.this;
                        kH1810GBleService2.f = BleUtils.getConnectionType(kH1810GBleService2.getApplicationContext());
                        if (KH1810GBleService.this.c.isEnabled()) {
                            KH1810GBleService.this.closeGattConnection();
                            KH1810GBleService.this.a(Clove1810GBleState.BleState.CONNECTING, true);
                            KH1810GBleService.this.reconnectToConfiguredDevice();
                            return;
                        }
                        return;
                    }
                    LogHelper.d(str, "BLUETOOTH TURNED ON buT IS_BAND_UNPAIRED IS false", moduleNames.getModuleName());
                }
            } else if (action.equalsIgnoreCase("action_stop_service")) {
                try {
                    KH1810GBleService.this.closeGattConnection();
                    LogHelper.d(KH1810GBleService.D, "ACTION_STOP_SERVICE", ModuleNames.BLEABSTRACT.getModuleName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                KH1810GBleService kH1810GBleService3 = KH1810GBleService.this;
                Clove1810GBleState.BleState bleState = Clove1810GBleState.BleState.DISCONNECTED;
                String str2 = KH1810GBleService.D;
                kH1810GBleService3.a(bleState, true);
                KH1810GBleService.this.clearAllServiceParameters();
            }
        }
    };
    public long A = -1;
    public BluetoothGattCallback B = new BluetoothGattCallback() { // from class: com.coveiot.android.bleabstract.services.KH1810GBleService.8
        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            super.onCharacteristicChanged(bluetoothGatt, bluetoothGattCharacteristic);
            if (KH1810GBleService.this.d == null) {
                return;
            }
            String str = KH1810GBleService.D;
            LogHelper.i(str, "onCharacteristicChanged: " + BleApiUtils.byte2Hex(bluetoothGattCharacteristic.getValue()), ModuleNames.BLEABSTRACT.getModuleName());
            KH1810GBleService kH1810GBleService = KH1810GBleService.this;
            kH1810GBleService.getClass();
            BleSDK.DataParsingWithData(bluetoothGattCharacteristic.getValue(), kH1810GBleService);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            super.onCharacteristicRead(bluetoothGatt, bluetoothGattCharacteristic, i);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            super.onCharacteristicWrite(bluetoothGatt, bluetoothGattCharacteristic, i);
            if (i == 0) {
                KH1810GBleService.this.nextQueue();
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onConnectionStateChange(final BluetoothGatt bluetoothGatt, int i, int i2) {
            KH1810GBleService kH1810GBleService = KH1810GBleService.this;
            kH1810GBleService.f = BleUtils.getConnectionType(kH1810GBleService.getApplicationContext());
            KH1810GBleService.this.A = System.currentTimeMillis();
            if (i == 0) {
                if (i2 == 2) {
                    LogHelper.d(KH1810GBleService.D, "onConnectionStateChange: Connected", ModuleNames.BLEABSTRACT.getModuleName());
                    KH1810GBleService kH1810GBleService2 = KH1810GBleService.this;
                    kH1810GBleService2.z = null;
                    kH1810GBleService2.s.postDelayed(new Runnable() { // from class: com.coveiot.android.bleabstract.services.KH1810GBleService.8.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(KH1810GBleService.this, "android.permission.BLUETOOTH_CONNECT") == 0) {
                                bluetoothGatt.discoverServices();
                            }
                        }
                    }, 1000L);
                    return;
                } else if (i2 == 0) {
                    KH1810GBleService.this.z = new ConnectionError(i, System.currentTimeMillis());
                    String str = KH1810GBleService.D;
                    ModuleNames moduleNames = ModuleNames.BLEABSTRACT;
                    LogHelper.d(str, "onConnectionStateChange: Disconnected", moduleNames.getModuleName());
                    LinkedList<CommandObject> linkedList = KH1810GBleService.this.m;
                    if (linkedList != null && linkedList.size() > 0) {
                        KH1810GBleService.this.m.clear();
                    }
                    KH1810GBleService.this.a(Clove1810GBleState.BleState.DISCONNECTED, true);
                    KH1810GBleService kH1810GBleService3 = KH1810GBleService.this;
                    if (kH1810GBleService3.f == ConnectionType.RECONNECT_ON_DISCONNECT) {
                        LogHelper.d(str, "Initiating reconnect", moduleNames.getModuleName());
                        KH1810GBleService.this.reconnectToConfiguredDevice();
                    } else {
                        kH1810GBleService3.closeGattConnection();
                    }
                    KH1810GBleService.this.u.clear();
                    return;
                } else {
                    return;
                }
            }
            KH1810GBleService.this.z = new ConnectionError(i, System.currentTimeMillis());
            String str2 = KH1810GBleService.D;
            ModuleNames moduleNames2 = ModuleNames.BLEABSTRACT;
            LogHelper.e(str2, "onConnectionStateChange Error GATT.  Error id : " + i, moduleNames2.getModuleName());
            if (i == 133) {
                if (KH1810GBleService.this.f == ConnectionType.RECONNECT_ON_DISCONNECT) {
                    LogHelper.d(str2, "ConnectionType is ++ " + KH1810GBleService.this.f, moduleNames2.getModuleName());
                    KH1810GBleService.this.l = 0;
                    LogHelper.d(str2, "133 error appeared 1 time  disconnect event and scan device", moduleNames2.getModuleName());
                    KH1810GBleService.this.a(Clove1810GBleState.BleState.DISCONNECTED, true);
                    KH1810GBleService.this.reconnectToConfiguredDevice();
                    return;
                }
                LogHelper.d(str2, "ConnectionType is ++ " + KH1810GBleService.this.f, moduleNames2.getModuleName());
                KH1810GBleService kH1810GBleService4 = KH1810GBleService.this;
                kH1810GBleService4.l = 0;
                kH1810GBleService4.a(Clove1810GBleState.BleState.DISCONNECTED, true);
                return;
            }
            LogHelper.d(str2, " onConnectionStateChange error code " + i, moduleNames2.getModuleName());
            KH1810GBleService kH1810GBleService5 = KH1810GBleService.this;
            if (kH1810GBleService5.f == ConnectionType.RECONNECT_ON_DISCONNECT) {
                int i3 = kH1810GBleService5.l + 1;
                kH1810GBleService5.l = i3;
                if (i3 <= 2) {
                    kH1810GBleService5.a(Clove1810GBleState.BleState.DISCONNECTED, true);
                    KH1810GBleService.this.reconnectToConfiguredDevice();
                    return;
                }
                LogHelper.d(str2, "257 or 8 error appeared 1 time  disconnect event and scan device", moduleNames2.getModuleName());
                KH1810GBleService.this.a(Clove1810GBleState.BleState.DISCONNECTED, true);
                KH1810GBleService.this.closeGattConnection();
                if (i == 257) {
                    BleApiManager.getInstance(KH1810GBleService.this).getBleApi().restartService();
                    return;
                }
                return;
            }
            LogHelper.d(str2, "connectype is ++ " + KH1810GBleService.this.f, moduleNames2.getModuleName());
            KH1810GBleService kH1810GBleService6 = KH1810GBleService.this;
            kH1810GBleService6.l = 0;
            kH1810GBleService6.closeGattConnection();
            KH1810GBleService.this.a(Clove1810GBleState.BleState.DISCONNECTED, true);
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
            String str = KH1810GBleService.D;
            LogHelper.d(str, "onMtuChangedmtu " + i + " status " + i2, ModuleNames.BLEABSTRACT.getModuleName());
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
            String str = KH1810GBleService.D;
            ModuleNames moduleNames = ModuleNames.BLEABSTRACT;
            LogHelper.e(str, i + "", moduleNames.getModuleName());
            if (i == 0) {
                LogHelper.d(str, "service discovered method", moduleNames.getModuleName());
                KH1810GBleService kH1810GBleService = KH1810GBleService.this;
                kH1810GBleService.d = bluetoothGatt;
                LogHelper.e(str, "initialize ble services", moduleNames.getModuleName());
                if (bluetoothGatt.getServices() != null && bluetoothGatt.getServices().size() != 0) {
                    kH1810GBleService.readWriteCharacteristic = null;
                    kH1810GBleService.setCharacteristicNotification(true);
                    if (kH1810GBleService.readWriteCharacteristic == null) {
                        LogHelper.d(str, " read  or write characteristic is null", moduleNames.getModuleName());
                        kH1810GBleService.stopScan();
                        kH1810GBleService.closeGattConnection();
                        kH1810GBleService.a(Clove1810GBleState.BleState.DISCONNECTED, true);
                        kH1810GBleService.reconnectToConfiguredDevice();
                    } else {
                        kH1810GBleService.a(Clove1810GBleState.BleState.CONNECTED, true);
                        kH1810GBleService.g.removeCallbacks(null);
                        kH1810GBleService.g.postDelayed(kH1810GBleService.w, Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS);
                    }
                } else {
                    LogHelper.d(str, " gatt service is null or empty", moduleNames.getModuleName());
                    kH1810GBleService.stopScan();
                    kH1810GBleService.closeGattConnection();
                    kH1810GBleService.a(Clove1810GBleState.BleState.DISCONNECTED, true);
                    kH1810GBleService.reconnectToConfiguredDevice();
                }
                KH1810GBleService.this.r.post(new Runnable() { // from class: com.coveiot.android.bleabstract.services.KH1810GBleService.8.2
                    @Override // java.lang.Runnable
                    public void run() {
                        if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(KH1810GBleService.this, "android.permission.BLUETOOTH_CONNECT") == 0) {
                            bluetoothGatt.requestMtu(512);
                        }
                    }
                });
                return;
            }
            BleApiManager.getInstance(KH1810GBleService.this).getBleApi().restartService();
        }
    };

    /* loaded from: classes2.dex */
    public class BtServiceBinder extends Binder {
        public BtServiceBinder() {
        }

        public KH1810GBleService getService() {
            return KH1810GBleService.this;
        }
    }

    public final void a(final BluetoothDevice bluetoothDevice) {
        a(Clove1810GBleState.BleState.CONNECTING, true);
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
        this.g.postDelayed(new Runnable() { // from class: com.coveiot.android.bleabstract.services.KH1810GBleService.2
            @Override // java.lang.Runnable
            public void run() {
                String str = KH1810GBleService.D;
                LogHelper.d(str, "connectGatt called " + bluetoothDevice.getAddress(), ModuleNames.BLEABSTRACT.getModuleName());
                KH1810GBleService kH1810GBleService = KH1810GBleService.this;
                if (kH1810GBleService.B != null) {
                    if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(kH1810GBleService, "android.permission.BLUETOOTH_CONNECT") == 0) {
                        KH1810GBleService kH1810GBleService2 = KH1810GBleService.this;
                        kH1810GBleService2.d = bluetoothDevice.connectGatt(kH1810GBleService2.getApplicationContext(), false, KH1810GBleService.this.B);
                    }
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
        a(Clove1810GBleState.BleState.DISCONNECTED, false);
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
        PreferenceManager1810G.getInstance(getApplicationContext()).saveConnectedDeviceMAcAddress(str);
        PreferenceManager1810G.getInstance(getApplicationContext()).saveConnectionType(ConnectionType.RECONNECT_ON_DISCONNECT.name());
        String str2 = D;
        LogHelper.d(str2, "connection type ++ " + BleUtils.getConnectionType(this), ModuleNames.BLEABSTRACT.getModuleName());
        b();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public void dataCallback(Map<String, Object> map) {
        String str;
        char c;
        char c2;
        String str2;
        String str3 = (String) map.get("DataType");
        boolean booleanValue = ((Boolean) map.get("End")).booleanValue();
        JstyleBaseRes jstyleBaseRes = new JstyleBaseRes();
        String str4 = D;
        ModuleNames moduleNames = ModuleNames.BLEABSTRACT;
        LogHelper.i(str4, "dataCallback: " + str3 + " -- " + booleanValue, moduleNames.getModuleName());
        if (this.C != null) {
            LogHelper.d("1810 khCurrentCommand", " dataCallback -- " + this.C.getDataType(), moduleNames.getModuleName());
        }
        str3.hashCode();
        switch (str3.hashCode()) {
            case -1980636204:
                str = "GetTempHistoryData";
                if (str3.equals(str)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1726342286:
                if (str3.equals("GetActivityModeData")) {
                    str = "GetTempHistoryData";
                    c = 1;
                    break;
                }
                str = "GetTempHistoryData";
                c = 65535;
                break;
            case -1200941421:
                if (str3.equals("ECGDATA")) {
                    c2 = 2;
                    c = c2;
                    str = "GetTempHistoryData";
                    break;
                }
                str = "GetTempHistoryData";
                c = 65535;
                break;
            case -1158558318:
                if (str3.equals("SetAlarmClockWithAllClock")) {
                    str = "GetTempHistoryData";
                    c = 3;
                    break;
                }
                str = "GetTempHistoryData";
                c = 65535;
                break;
            case -787314209:
                if (str3.equals("SetSedentaryReminder")) {
                    c2 = 4;
                    c = c2;
                    str = "GetTempHistoryData";
                    break;
                }
                str = "GetTempHistoryData";
                c = 65535;
                break;
            case -639880425:
                if (str3.equals("RealTimeStep")) {
                    c2 = 5;
                    c = c2;
                    str = "GetTempHistoryData";
                    break;
                }
                str = "GetTempHistoryData";
                c = 65535;
                break;
            case -13544106:
                if (str3.equals("ECGQuality")) {
                    c2 = 6;
                    c = c2;
                    str = "GetTempHistoryData";
                    break;
                }
                str = "GetTempHistoryData";
                c = 65535;
                break;
            case 115609795:
                if (str3.equals("GetDeviceBatteryLevel")) {
                    c2 = 7;
                    c = c2;
                    str = "GetTempHistoryData";
                    break;
                }
                str = "GetTempHistoryData";
                c = 65535;
                break;
            case 210920480:
                if (str3.equals("GetDetailActivityData")) {
                    c2 = '\b';
                    c = c2;
                    str = "GetTempHistoryData";
                    break;
                }
                str = "GetTempHistoryData";
                c = 65535;
                break;
            case 343843441:
                if (str3.equals("PPGDATA")) {
                    c2 = '\t';
                    c = c2;
                    str = "GetTempHistoryData";
                    break;
                }
                str = "GetTempHistoryData";
                c = 65535;
                break;
            case 494645178:
                if (str3.equals("GetDetailSleepData")) {
                    c2 = '\n';
                    c = c2;
                    str = "GetTempHistoryData";
                    break;
                }
                str = "GetTempHistoryData";
                c = 65535;
                break;
            case 769120590:
                if (str3.equals("GetStaticHR")) {
                    str = "GetTempHistoryData";
                    c = 11;
                    break;
                }
                str = "GetTempHistoryData";
                c = 65535;
                break;
            case 1567484428:
                if (str3.equals("GetDeviceVersion")) {
                    str = "GetTempHistoryData";
                    c = '\f';
                    break;
                }
                str = "GetTempHistoryData";
                c = 65535;
                break;
            case 1669104719:
                if (str3.equals("HeartBeatPackets")) {
                    c2 = '\r';
                    c = c2;
                    str = "GetTempHistoryData";
                    break;
                }
                str = "GetTempHistoryData";
                c = 65535;
                break;
            case 1676528870:
                if (str3.equals(BleConst.ECGResult)) {
                    c2 = 14;
                    c = c2;
                    str = "GetTempHistoryData";
                    break;
                }
                str = "GetTempHistoryData";
                c = 65535;
                break;
            case 2049043094:
                if (str3.equals("FindMobilePhoneMode")) {
                    c2 = 15;
                    c = c2;
                    str = "GetTempHistoryData";
                    break;
                }
                str = "GetTempHistoryData";
                c = 65535;
                break;
            case 2056519717:
                if (str3.equals("SetDeviceTime")) {
                    c2 = 16;
                    c = c2;
                    str = "GetTempHistoryData";
                    break;
                }
                str = "GetTempHistoryData";
                c = 65535;
                break;
            default:
                str = "GetTempHistoryData";
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                String str5 = str;
                JstyleBaseReq jstyleBaseReq = this.C;
                if (jstyleBaseReq == null || !jstyleBaseReq.getDataType().equalsIgnoreCase(str3)) {
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
                    JstyleTemperatureReq jstyleTemperatureReq = new JstyleTemperatureReq();
                    jstyleTemperatureReq.setDataType(str5);
                    jstyleTemperatureReq.setMode(JStyleConstants.Constants.getMODE_CONTINUE());
                    jstyleTemperatureReq.setReqId(this.C.getReqId());
                    sendRequest(jstyleTemperatureReq, this.C.getResponseListener());
                    return;
                }
                return;
            case 1:
                JstyleBaseReq jstyleBaseReq2 = this.C;
                if (jstyleBaseReq2 == null || !jstyleBaseReq2.getDataType().equalsIgnoreCase(str3)) {
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
            case 2:
                LogHelper.d(str4, "NEW_ECGDATA", moduleNames.getModuleName());
                Map<String, byte[]> dataList = getDataList(map);
                JstyleLiveResponse jstyleLiveResponse = new JstyleLiveResponse();
                jstyleLiveResponse.setDataType("ECGDATA");
                jstyleLiveResponse.setObj(dataList);
                BleEventBusManager.getInstance().getEventBus().post(jstyleLiveResponse);
                return;
            case 3:
                LogHelper.d(str4, "SetAlarmClockWithAllClock", moduleNames.getModuleName());
                JstyleBaseReq jstyleBaseReq3 = this.C;
                if (jstyleBaseReq3 == null || !jstyleBaseReq3.getDataType().equalsIgnoreCase(str3)) {
                    return;
                }
                jstyleBaseRes.setBaseReq(this.C);
                this.C.getResponseListener().onResponse(jstyleBaseRes);
                return;
            case 4:
                LogHelper.d(str4, "SetSedentaryReminder", moduleNames.getModuleName());
                JstyleBaseReq jstyleBaseReq4 = this.C;
                if (jstyleBaseReq4 == null || !jstyleBaseReq4.getDataType().equalsIgnoreCase(str3)) {
                    return;
                }
                jstyleBaseRes.setBaseReq(this.C);
                this.C.getResponseListener().onResponse(jstyleBaseRes);
                return;
            case 5:
                LogHelper.d(str4, "RealTimeStep", moduleNames.getModuleName());
                Map<String, String> data = getData(map);
                JstyleLiveResponse jstyleLiveResponse2 = new JstyleLiveResponse();
                jstyleLiveResponse2.setDataType("RealTimeStep");
                jstyleLiveResponse2.setObj(data);
                BleEventBusManager.getInstance().getEventBus().post(jstyleLiveResponse2);
                return;
            case 6:
                LogHelper.d(str4, "NEW_ECGDATA", moduleNames.getModuleName());
                Map<String, String> data2 = getData(map);
                JstyleLiveResponse jstyleLiveResponse3 = new JstyleLiveResponse();
                jstyleLiveResponse3.setDataType("ECGQuality");
                jstyleLiveResponse3.setObj(data2);
                BleEventBusManager.getInstance().getEventBus().post(jstyleLiveResponse3);
                return;
            case 7:
            case '\f':
                JstyleBaseReq jstyleBaseReq5 = this.C;
                if (jstyleBaseReq5 == null || !jstyleBaseReq5.getDataType().equalsIgnoreCase(str3)) {
                    return;
                }
                Map<String, String> data3 = getData(map);
                jstyleBaseRes.setBaseReq(this.C);
                jstyleBaseRes.obj = data3;
                this.C.getResponseListener().onResponse(jstyleBaseRes);
                return;
            case '\b':
                JstyleBaseReq jstyleBaseReq6 = this.C;
                if (jstyleBaseReq6 == null || !jstyleBaseReq6.getDataType().equalsIgnoreCase(str3)) {
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
            case '\t':
                LogHelper.d(str4, "PPGDATA", moduleNames.getModuleName());
                Map<String, String> data4 = getData(map);
                LogHelper.d("PPG Data 1810G", data4.get(DeviceKey.PPGValue), moduleNames.getModuleName());
                for (String str6 : data4.get(DeviceKey.PPGValue).split(Constants.SEPARATOR_COMMA)) {
                    JstyleLiveResponse jstyleLiveResponse4 = new JstyleLiveResponse();
                    jstyleLiveResponse4.setDataType("PPGDATA");
                    HashMap hashMap = new HashMap();
                    hashMap.put(DeviceKey.PPGValue, str6);
                    jstyleLiveResponse4.setObj(hashMap);
                    BleEventBusManager.getInstance().getEventBus().post(jstyleLiveResponse4);
                }
                return;
            case '\n':
                JstyleBaseReq jstyleBaseReq7 = this.C;
                if (jstyleBaseReq7 == null || !jstyleBaseReq7.getDataType().equalsIgnoreCase(str3)) {
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
            case 11:
                JstyleBaseReq jstyleBaseReq8 = this.C;
                if (jstyleBaseReq8 == null || !jstyleBaseReq8.getDataType().equalsIgnoreCase(str3)) {
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
            case '\r':
                LogHelper.d("HeartBeatPackets", map.toString(), moduleNames.getModuleName());
                JstyleLiveResponse jstyleLiveResponse5 = new JstyleLiveResponse();
                jstyleLiveResponse5.setDataType("HeartBeatPackets");
                BleEventBusManager.getInstance().getEventBus().post(jstyleLiveResponse5);
                LogHelper.d("Probe Feature", "HeartBeatPackets Received", moduleNames.getModuleName());
                return;
            case 14:
                JstyleBaseReq jstyleBaseReq9 = this.C;
                if (jstyleBaseReq9 == null || !jstyleBaseReq9.getDataType().equalsIgnoreCase(BleConst.ENTERECG)) {
                    return;
                }
                LogHelper.d(str4, BleConst.ECGResult, moduleNames.getModuleName());
                Map<String, String> data5 = getData(map);
                String str7 = data5.get(DeviceKey.ECGResultValue);
                if (kotlin.text.m.isBlank(str7)) {
                    return;
                }
                if (Integer.valueOf(str7).intValue() == 12) {
                    LogHelper.d(str4, "ECGResult : 12", moduleNames.getModuleName());
                    this.C.getResponseListener().onFailure(new JstyleError(JstyleErrorType.BAND_INSTRUCTION_NOT_FOLLOWED, "BAND INSTRUCTION NOT FOLLOWED"));
                    return;
                } else if (Integer.valueOf(str7).intValue() == 255 || Integer.valueOf(str7).intValue() == 1 || Integer.valueOf(str7).intValue() == 11) {
                    return;
                } else {
                    if (Integer.valueOf(str7).intValue() == 3) {
                        jstyleBaseRes.setBaseReq(this.C);
                        jstyleBaseRes.obj = data5;
                        this.C.getResponseListener().onResponse(jstyleBaseRes);
                        return;
                    }
                    this.C.getResponseListener().onFailure(new JstyleError(JstyleErrorType.BAND_INSTRUCTION_NOT_FOLLOWED, "BAND INSTRUCTION NOT FOLLOWED"));
                    return;
                }
            case 15:
                LogHelper.d(str4, "FindMobilePhoneMode", moduleNames.getModuleName());
                if (str3.equalsIgnoreCase("FindMobilePhoneMode")) {
                    JstyleLiveResponse jstyleLiveResponse6 = new JstyleLiveResponse();
                    jstyleLiveResponse6.setDataType("FindMobilePhoneMode");
                    BleEventBusManager.getInstance().getEventBus().post(jstyleLiveResponse6);
                    return;
                }
                return;
            case 16:
                Map<String, String> data6 = getData(map);
                if (data6 != null && (str2 = data6.get(DeviceKey.KPhoneDataLength)) != null) {
                    this.t = Integer.parseInt(str2);
                    LogHelper.d(str4, "KPhoneDataLength == " + this.t, moduleNames.getModuleName());
                    return;
                }
                break;
        }
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
        PreferenceManager1810G.getInstance(getApplicationContext()).saveConnectedDeviceMAcAddress("");
        PreferenceManager1810G.getInstance(getApplicationContext()).saveConnectionType(ConnectionType.DONT_CONNECT_ON_DISCONNECT.name());
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
        PreferenceManager1810G.getInstance(getApplicationContext()).saveConnectionType(ConnectionType.DONT_CONNECT_ON_DISCONNECT.name());
        stopForeground(true);
        stopSelf();
    }

    public ConnectionError getConnectionError() {
        return this.z;
    }

    public long getConnectionStageChangeTimeStamp() {
        return this.A;
    }

    public Clove1810GBleState.BleState getConnectionState() {
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
        a(Clove1810GBleState.BleState.DISCONNECTED, false);
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
        return this.f3767a;
    }

    @Override // android.app.Service
    public void onCreate() {
        a();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        LogHelper.d(D, "Service onDestroy", ModuleNames.BLEABSTRACT.getModuleName());
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
            java.lang.Class<com.coveiot.android.bleabstract.services.KH1810GBleService> r4 = com.coveiot.android.bleabstract.services.KH1810GBleService.class
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
            com.coveiot.android.bleabstract.preferences.PreferenceManager1810G r2 = com.coveiot.android.bleabstract.preferences.PreferenceManager1810G.getInstance(r2)
            java.lang.String r2 = r2.getConnectedDeviceMacAddress()
            r1.e = r2
            android.content.Context r2 = r1.getApplicationContext()
            com.coveiot.sdk.ble.events.ConnectionType r2 = com.coveiot.sdk.ble.utils.BleUtils.getConnectionType(r2)
            r1.f = r2
            java.lang.String r2 = com.coveiot.android.bleabstract.services.KH1810GBleService.D
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
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.bleabstract.services.KH1810GBleService.onStartCommand(android.content.Intent, int, int):int");
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    public void reconnectToConfiguredDevice() {
        String str = D;
        ModuleNames moduleNames = ModuleNames.BLEABSTRACT;
        LogHelper.d(str, "reconnectToConfiguredDevice", moduleNames.getModuleName());
        closeGattConnection();
        this.e = PreferenceManager1810G.getInstance(getApplicationContext()).getConnectedDeviceMacAddress();
        this.f = BleUtils.getConnectionType(getApplicationContext());
        if (this.c.isEnabled()) {
            if (!BleUtils.isEmpty(this.e) && BluetoothAdapter.checkBluetoothAddress(this.e)) {
                BluetoothDevice remoteDevice = this.c.getRemoteDevice(this.e);
                LogHelper.d(str, "Connecting to device " + remoteDevice.getAddress(), moduleNames.getModuleName());
                a(remoteDevice);
                return;
            }
            LogHelper.e(str, "Trying to connect, address is empty or small case " + this.e, moduleNames.getModuleName());
            return;
        }
        LogHelper.e(str, "Bluetooth is disabled", moduleNames.getModuleName());
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
        PreferenceManager1810G.getInstance(getApplicationContext()).saveConnectionType(ConnectionType.RECONNECT_ON_DISCONNECT.name());
        stopForeground(true);
        stopSelf();
        LogHelper.d(D, "Service restartService", ModuleNames.BLEABSTRACT.getModuleName());
    }

    public void scanLeDevice() {
        if (!PreferenceManager1810G.getInstance(getApplicationContext()).isBandUnpaired().booleanValue()) {
            this.j.removeCallbacksAndMessages(null);
            this.i.removeCallbacks(null);
            stopScan();
            this.j.postDelayed(new Runnable() { // from class: com.coveiot.android.bleabstract.services.KH1810GBleService.4
                @Override // java.lang.Runnable
                @RequiresApi(api = 21)
                public void run() {
                    String str = KH1810GBleService.D;
                    ModuleNames moduleNames = ModuleNames.BLEABSTRACT;
                    LogHelper.d(str, "Scanning...", moduleNames.getModuleName());
                    KH1810GBleService.this.closeGattConnection();
                    try {
                        KH1810GBleService kH1810GBleService = KH1810GBleService.this;
                        kH1810GBleService.e = PreferenceManager1810G.getInstance(kH1810GBleService.getApplicationContext()).getConnectedDeviceMacAddress();
                        LogHelper.d(str, "BLE deviceAddress as " + KH1810GBleService.this.e, moduleNames.getModuleName());
                        if (BleUtils.isEmpty(KH1810GBleService.this.e)) {
                            return;
                        }
                        ScanFilter.Builder builder = new ScanFilter.Builder();
                        builder.setDeviceAddress(KH1810GBleService.this.e);
                        new ArrayList().add(builder.build());
                        ScanSettings build = new ScanSettings.Builder().setScanMode(1).build();
                        KH1810GBleService.this.a(Clove1810GBleState.BleState.SCANNING, true);
                        KH1810GBleService kH1810GBleService2 = KH1810GBleService.this;
                        kH1810GBleService2.h.postDelayed(kH1810GBleService2.v, 1800000L);
                        if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(KH1810GBleService.this, "android.permission.BLUETOOTH_SCAN") == 0) {
                            KH1810GBleService.this.c.getBluetoothLeScanner().startScan((List<ScanFilter>) null, build, KH1810GBleService.this.x);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, 500L);
            return;
        }
        a(Clove1810GBleState.BleState.DISCONNECTED, true);
    }

    public void sendCommandToBand(byte[] bArr) {
        writeValue(bArr);
    }

    public void sendRequest(JstyleBaseReq jstyleBaseReq, JstyleResponseListener jstyleResponseListener) {
        jstyleBaseReq.setResponseListener(jstyleResponseListener);
        this.C = jstyleBaseReq;
        LogHelper.d("1810 khCurrentCommand", " sendRequest-- " + this.C.getDataType(), ModuleNames.BLEABSTRACT.getModuleName());
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
                case -1980636204:
                    if (dataType.equals("GetTempHistoryData")) {
                        c = 1;
                        break;
                    }
                    break;
                case -1811662981:
                    if (dataType.equals("SetAutomaticHRMonitoring")) {
                        c = 2;
                        break;
                    }
                    break;
                case -1726342286:
                    if (dataType.equals("GetActivityModeData")) {
                        c = 3;
                        break;
                    }
                    break;
                case -1323245135:
                    if (dataType.equals(BleConst.ENTERECG)) {
                        c = 4;
                        break;
                    }
                    break;
                case -1300503361:
                    if (dataType.equals("TemperatureCorrectionValue")) {
                        c = 5;
                        break;
                    }
                    break;
                case -1257575528:
                    if (dataType.equals(JStyleConstants.TempUnit)) {
                        c = 6;
                        break;
                    }
                    break;
                case -1158558318:
                    if (dataType.equals("SetAlarmClockWithAllClock")) {
                        c = 7;
                        break;
                    }
                    break;
                case -789877151:
                    if (dataType.equals("SetMotorVibrationWithTimes")) {
                        c = '\b';
                        break;
                    }
                    break;
                case -787314209:
                    if (dataType.equals("SetSedentaryReminder")) {
                        c = '\t';
                        break;
                    }
                    break;
                case -639880425:
                    if (dataType.equals("RealTimeStep")) {
                        c = '\n';
                        break;
                    }
                    break;
                case 115609795:
                    if (dataType.equals("GetDeviceBatteryLevel")) {
                        c = 11;
                        break;
                    }
                    break;
                case 210920480:
                    if (dataType.equals("GetDetailActivityData")) {
                        c = '\f';
                        break;
                    }
                    break;
                case 343843441:
                    if (dataType.equals("PPGDATA")) {
                        c = '\r';
                        break;
                    }
                    break;
                case 494645178:
                    if (dataType.equals("GetDetailSleepData")) {
                        c = 14;
                        break;
                    }
                    break;
                case 742458513:
                    if (dataType.equals("SetHeartBeatPackets")) {
                        c = 15;
                        break;
                    }
                    break;
                case 759553291:
                    if (dataType.equals(JStyleConstants.Notification)) {
                        c = 16;
                        break;
                    }
                    break;
                case 769120590:
                    if (dataType.equals("GetStaticHR")) {
                        c = 17;
                        break;
                    }
                    break;
                case 790362721:
                    if (dataType.equals("SetStepGoal")) {
                        c = 18;
                        break;
                    }
                    break;
                case 1281238128:
                    if (dataType.equals("SetPersonalInfo")) {
                        c = 19;
                        break;
                    }
                    break;
                case 1567484428:
                    if (dataType.equals("GetDeviceVersion")) {
                        c = 20;
                        break;
                    }
                    break;
                case 2056196614:
                    if (dataType.equals("SetDeviceInfo")) {
                        c = 21;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                case 2:
                case 5:
                case 6:
                case '\b':
                case '\t':
                case '\n':
                case '\r':
                case 15:
                case 16:
                case 18:
                case 19:
                case 21:
                    sendCommandToBand(jstyleBaseReq.getCommandBytes());
                    JstyleBaseReq jstyleBaseReq2 = this.C;
                    if (jstyleBaseReq2 == null || !jstyleBaseReq2.getDataType().equalsIgnoreCase(dataType)) {
                        return;
                    }
                    JstyleBaseRes jstyleBaseRes = new JstyleBaseRes();
                    jstyleBaseRes.setBaseReq(this.C);
                    this.C.getResponseListener().onResponse(jstyleBaseRes);
                    return;
                case 1:
                case 3:
                case '\f':
                case 14:
                case 17:
                    if (!this.q) {
                        clearResponseList();
                    }
                    sendCommandToBand(jstyleBaseReq.getCommandBytes());
                    return;
                case 4:
                case 11:
                case 20:
                    sendCommandToBand(jstyleBaseReq.getCommandBytes());
                    return;
                case 7:
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
        if (this.d != null) {
            if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH_CONNECT") == 0) {
                this.d.writeCharacteristic(characteristic);
            }
        }
    }

    public void offerData() {
        nextQueue();
    }

    public final void a(final Clove1810GBleState.BleState bleState, boolean z) {
        Handler handler;
        this.bluetoothConnectionStatus = bleState;
        if (!z || (handler = this.g) == null) {
            return;
        }
        handler.removeCallbacksAndMessages(null);
        this.g.post(new Runnable(this) { // from class: com.coveiot.android.bleabstract.services.KH1810GBleService.3
            @Override // java.lang.Runnable
            public void run() {
                BleEventBusManager.getInstance().getEventBus().post(new Clove1810GBleState(bleState));
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
                build = new Notification.Builder(this).setContentTitle(data.getAppDesc()).setSmallIcon(data.getAppIcon()).setContentIntent(activity).build();
            }
            startForeground(101, build);
        } catch (Exception e) {
            e.printStackTrace();
            BleApiUtils.checkExceptionAndShowNotification(e, this);
        }
    }
}
