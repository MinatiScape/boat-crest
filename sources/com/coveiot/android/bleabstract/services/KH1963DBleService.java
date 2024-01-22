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
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import com.alibaba.fastjson.parser.JSONLexer;
import com.clevertap.android.sdk.Constants;
import com.coveiot.android.bleabstract.BleApiUtils;
import com.coveiot.android.bleabstract.NotificationInfo;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.api.JStyleUUID;
import com.coveiot.android.bleabstract.models.Clove1963DBleState;
import com.coveiot.android.bleabstract.models.RawPPGData;
import com.coveiot.android.bleabstract.preferences.PreferenceManager1963D;
import com.coveiot.android.bleabstract.response.ConnectionError;
import com.coveiot.android.bleabstract.utils.jstyleUtils.JStyleConstants;
import com.coveiot.android.jstyle1963dsdk.JstyleResponseListener;
import com.coveiot.android.jstyle1963dsdk.api.JstyleActivityModeHistoryReq;
import com.coveiot.android.jstyle1963dsdk.api.JstyleBaseReq;
import com.coveiot.android.jstyle1963dsdk.api.JstyleBaseRes;
import com.coveiot.android.jstyle1963dsdk.api.JstyleHistoryHRReq;
import com.coveiot.android.jstyle1963dsdk.api.JstyleHrvReq;
import com.coveiot.android.jstyle1963dsdk.api.JstyleOnceHRReq;
import com.coveiot.android.jstyle1963dsdk.api.JstyleSleepReq;
import com.coveiot.android.jstyle1963dsdk.api.JstyleSpo2Req;
import com.coveiot.android.jstyle1963dsdk.api.JstyleTemperatureReq;
import com.coveiot.android.jstyle1963dsdk.api.JstyleWalkReq;
import com.coveiot.android.jstyle1963dsdk.model.JstyleLiveResponse;
import com.coveiot.sdk.ble.api.BleUUID;
import com.coveiot.sdk.ble.eventbus.BleEventBusManager;
import com.coveiot.sdk.ble.events.ConnectionType;
import com.coveiot.sdk.ble.model.BleDeviceInfo;
import com.coveiot.sdk.ble.model.CommandObject;
import com.coveiot.sdk.ble.utils.BleUtils;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import com.jstyle.blesdk1860.constant.BleConst;
import com.jstyle.blesdk1860.constant.DeviceKey;
import com.jstyle.blesdk1963.Util.BleSDK;
import com.jstyle.blesdk1963.callback.DataListener1963;
import com.jstyle.blesdk1963.model.MyDeviceTime;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
/* loaded from: classes2.dex */
public class KH1963DBleService extends Service implements DataListener1963 {
    public static final String Q = KH1963DBleService.class.getSimpleName();
    public ConnectionError B;
    public JstyleBaseReq E;
    public byte[] F;
    public HashMap<Integer, byte[]> G;
    public int[] H;
    public byte[] I;
    public int J;
    public int K;
    public int L;
    public int M;
    public boolean N;
    public int O;
    public BluetoothManager b;
    public Clove1963DBleState.BleState bluetoothConnectionStatus;
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
    public Handler v;
    public HandlerThread w;

    /* renamed from: a  reason: collision with root package name */
    public final IBinder f3797a = new BtServiceBinder();
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
    public final Runnable x = new Runnable() { // from class: com.coveiot.android.bleabstract.services.KH1963DBleService.1
        @Override // java.lang.Runnable
        public void run() {
            if (KH1963DBleService.this.bluetoothConnectionStatus == Clove1963DBleState.BleState.SCANNING) {
                LogHelper.d(KH1963DBleService.Q, "restart initiated", ModuleNames.BLEABSTRACT.getModuleName());
                BleApiManager.getInstance(KH1963DBleService.this).getBleApi().restartService();
            }
        }
    };
    public final Runnable y = new Runnable() { // from class: com.coveiot.android.bleabstract.services.KH1963DBleService.5
        @Override // java.lang.Runnable
        public void run() {
            LinkedList<CommandObject> linkedList = KH1963DBleService.this.m;
            if (linkedList != null && linkedList.size() > 0) {
                KH1963DBleService.this.m.clear();
            }
            BleDeviceInfo.clearInstance();
            KH1963DBleService kH1963DBleService = KH1963DBleService.this;
            kH1963DBleService.getClass();
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
            kH1963DBleService.writeValue(BleSDK.SetDeviceTime(myDeviceTime));
            KH1963DBleService.this.l = 0;
        }
    };
    @RequiresApi(21)
    public ScanCallback z = new ScanCallback() { // from class: com.coveiot.android.bleabstract.services.KH1963DBleService.6
        @Override // android.bluetooth.le.ScanCallback
        public void onScanFailed(int i) {
            super.onScanFailed(i);
            LogHelper.e("onScanFailed", "Error code " + i, ModuleNames.BLEABSTRACT.getModuleName());
            if (i != 1) {
                if (i == 2) {
                    BleApiManager.getInstance(KH1963DBleService.this).getBleApi().restartService();
                    return;
                }
                KH1963DBleService kH1963DBleService = KH1963DBleService.this;
                Clove1963DBleState.BleState bleState = Clove1963DBleState.BleState.DISCONNECTED;
                String str = KH1963DBleService.Q;
                kH1963DBleService.a(bleState, true);
            }
        }

        @Override // android.bluetooth.le.ScanCallback
        public void onScanResult(int i, ScanResult scanResult) {
            super.onScanResult(i, scanResult);
            try {
                if (Build.VERSION.SDK_INT < 21 || !scanResult.getDevice().getAddress().equalsIgnoreCase(KH1963DBleService.this.e)) {
                    return;
                }
                String str = KH1963DBleService.Q;
                ModuleNames moduleNames = ModuleNames.BLEABSTRACT;
                LogHelper.d(str, "Scan stopped, device found", moduleNames.getModuleName());
                KH1963DBleService.this.stopScan();
                KH1963DBleService.this.a(Clove1963DBleState.BleState.CONNECTING, true);
                Handler handler = KH1963DBleService.this.i;
                if (handler != null) {
                    handler.removeCallbacks(null);
                }
                LogHelper.d(str, "connect to device after scan", moduleNames.getModuleName());
                KH1963DBleService.this.a(scanResult.getDevice());
            } catch (Exception e) {
                LogHelper.e(KH1963DBleService.Q, e.toString(), ModuleNames.BLEABSTRACT.getModuleName());
            }
        }
    };
    public final BroadcastReceiver A = new BroadcastReceiver() { // from class: com.coveiot.android.bleabstract.services.KH1963DBleService.7
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("android.bluetooth.adapter.action.STATE_CHANGED")) {
                int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE);
                if (intExtra == 3 || intExtra == 10) {
                    LogHelper.d(KH1963DBleService.Q, "Bluetooth state BluetoothAdapter.STATE_OFF", ModuleNames.BLEABSTRACT.getModuleName());
                    KH1963DBleService.this.a(Clove1963DBleState.BleState.DISCONNECTED, true);
                    KH1963DBleService.this.stopScan();
                    KH1963DBleService.this.closeGattConnection();
                    KH1963DBleService.this.clearAllServiceParameters();
                } else if (intExtra != 12) {
                } else {
                    KH1963DBleService.this.stopScan();
                    KH1963DBleService.this.clearAllServiceParameters();
                    KH1963DBleService.this.initServiceParams();
                    String str = KH1963DBleService.Q;
                    ModuleNames moduleNames = ModuleNames.BLEABSTRACT;
                    LogHelper.d(str, "Bluetooth state BluetoothAdapter.STATE_ON", moduleNames.getModuleName());
                    if (!PreferenceManager1963D.getInstance(KH1963DBleService.this.getApplicationContext()).isBandUnpaired().booleanValue()) {
                        KH1963DBleService kH1963DBleService = KH1963DBleService.this;
                        kH1963DBleService.e = PreferenceManager1963D.getInstance(kH1963DBleService.getApplicationContext()).getConnectedDeviceMacAddress();
                        KH1963DBleService kH1963DBleService2 = KH1963DBleService.this;
                        kH1963DBleService2.f = BleUtils.getConnectionType(kH1963DBleService2.getApplicationContext());
                        if (KH1963DBleService.this.c.isEnabled()) {
                            KH1963DBleService.this.closeGattConnection();
                            KH1963DBleService.this.a(Clove1963DBleState.BleState.CONNECTING, true);
                            KH1963DBleService.this.reconnectToConfiguredDevice();
                            return;
                        }
                        return;
                    }
                    LogHelper.d(str, "BLUETOOTH TURNED ON buT IS_BAND_UNPAIRED IS false", moduleNames.getModuleName());
                }
            } else if (action.equalsIgnoreCase("action_stop_service")) {
                try {
                    KH1963DBleService.this.closeGattConnection();
                    LogHelper.d(KH1963DBleService.Q, "ACTION_STOP_SERVICE", ModuleNames.BLEABSTRACT.getModuleName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                KH1963DBleService kH1963DBleService3 = KH1963DBleService.this;
                Clove1963DBleState.BleState bleState = Clove1963DBleState.BleState.DISCONNECTED;
                String str2 = KH1963DBleService.Q;
                kH1963DBleService3.a(bleState, true);
                KH1963DBleService.this.clearAllServiceParameters();
            }
        }
    };
    public long C = -1;
    public BluetoothGattCallback D = new BluetoothGattCallback() { // from class: com.coveiot.android.bleabstract.services.KH1963DBleService.8
        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            super.onCharacteristicChanged(bluetoothGatt, bluetoothGattCharacteristic);
            if (KH1963DBleService.this.d == null) {
                return;
            }
            String str = KH1963DBleService.Q;
            LogHelper.i(str, "onCharacteristicChanged: " + BleApiUtils.byte2Hex(bluetoothGattCharacteristic.getValue()), ModuleNames.BLEABSTRACT.getModuleName());
            KH1963DBleService.a(KH1963DBleService.this, bluetoothGattCharacteristic.getValue());
            KH1963DBleService kH1963DBleService = KH1963DBleService.this;
            kH1963DBleService.getClass();
            BleSDK.DataParsingWithData(bluetoothGattCharacteristic.getValue(), kH1963DBleService);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            super.onCharacteristicRead(bluetoothGatt, bluetoothGattCharacteristic, i);
            if (i == 0) {
                KH1963DBleService.a(KH1963DBleService.this, bluetoothGattCharacteristic.getValue());
                return;
            }
            String str = KH1963DBleService.Q;
            LogHelper.e(str, "onCharacteristicRead false " + i + bluetoothGattCharacteristic.toString());
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            super.onCharacteristicWrite(bluetoothGatt, bluetoothGattCharacteristic, i);
            if (i == 0) {
                KH1963DBleService.this.nextQueue();
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onConnectionStateChange(final BluetoothGatt bluetoothGatt, int i, int i2) {
            KH1963DBleService kH1963DBleService = KH1963DBleService.this;
            kH1963DBleService.f = BleUtils.getConnectionType(kH1963DBleService.getApplicationContext());
            KH1963DBleService.this.C = System.currentTimeMillis();
            if (i == 0) {
                if (i2 == 2) {
                    LogHelper.d(KH1963DBleService.Q, "onConnectionStateChange: Connected", ModuleNames.BLEABSTRACT.getModuleName());
                    KH1963DBleService kH1963DBleService2 = KH1963DBleService.this;
                    kH1963DBleService2.B = null;
                    kH1963DBleService2.s.postDelayed(new Runnable() { // from class: com.coveiot.android.bleabstract.services.KH1963DBleService.8.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(KH1963DBleService.this, "android.permission.BLUETOOTH_CONNECT") == 0) {
                                bluetoothGatt.discoverServices();
                            }
                        }
                    }, 1000L);
                    return;
                } else if (i2 == 0) {
                    KH1963DBleService.this.B = new ConnectionError(i, System.currentTimeMillis());
                    String str = KH1963DBleService.Q;
                    ModuleNames moduleNames = ModuleNames.BLEABSTRACT;
                    LogHelper.d(str, "onConnectionStateChange: Disconnected", moduleNames.getModuleName());
                    LinkedList<CommandObject> linkedList = KH1963DBleService.this.m;
                    if (linkedList != null && linkedList.size() > 0) {
                        KH1963DBleService.this.m.clear();
                    }
                    KH1963DBleService.this.a(Clove1963DBleState.BleState.DISCONNECTED, true);
                    KH1963DBleService kH1963DBleService3 = KH1963DBleService.this;
                    if (kH1963DBleService3.f == ConnectionType.RECONNECT_ON_DISCONNECT) {
                        LogHelper.d(str, "Initiating reconnect", moduleNames.getModuleName());
                        KH1963DBleService.this.reconnectToConfiguredDevice();
                    } else {
                        kH1963DBleService3.closeGattConnection();
                    }
                    KH1963DBleService.this.u.clear();
                    return;
                } else {
                    return;
                }
            }
            KH1963DBleService.this.B = new ConnectionError(i, System.currentTimeMillis());
            String str2 = KH1963DBleService.Q;
            ModuleNames moduleNames2 = ModuleNames.BLEABSTRACT;
            LogHelper.e(str2, "onConnectionStateChange Error GATT.  Error id : " + i, moduleNames2.getModuleName());
            if (i == 133) {
                if (KH1963DBleService.this.f == ConnectionType.RECONNECT_ON_DISCONNECT) {
                    LogHelper.d(str2, "ConnectionType is ++ " + KH1963DBleService.this.f, moduleNames2.getModuleName());
                    KH1963DBleService.this.l = 0;
                    LogHelper.d(str2, "133 error appeared 1 time  disconnect event and scan device", moduleNames2.getModuleName());
                    KH1963DBleService.this.a(Clove1963DBleState.BleState.DISCONNECTED, true);
                    KH1963DBleService.this.reconnectToConfiguredDevice();
                    return;
                }
                LogHelper.d(str2, "ConnectionType is ++ " + KH1963DBleService.this.f, moduleNames2.getModuleName());
                KH1963DBleService kH1963DBleService4 = KH1963DBleService.this;
                kH1963DBleService4.l = 0;
                kH1963DBleService4.a(Clove1963DBleState.BleState.DISCONNECTED, true);
                return;
            }
            LogHelper.d(str2, " onConnectionStateChange error code " + i, moduleNames2.getModuleName());
            KH1963DBleService kH1963DBleService5 = KH1963DBleService.this;
            if (kH1963DBleService5.f == ConnectionType.RECONNECT_ON_DISCONNECT) {
                int i3 = kH1963DBleService5.l + 1;
                kH1963DBleService5.l = i3;
                if (i3 <= 2) {
                    kH1963DBleService5.a(Clove1963DBleState.BleState.DISCONNECTED, true);
                    KH1963DBleService.this.reconnectToConfiguredDevice();
                    return;
                }
                LogHelper.d(str2, "257 or 8 error appeared 1 time  disconnect event and scan device", moduleNames2.getModuleName());
                KH1963DBleService.this.a(Clove1963DBleState.BleState.DISCONNECTED, true);
                KH1963DBleService.this.closeGattConnection();
                if (i == 257) {
                    BleApiManager.getInstance(KH1963DBleService.this).getBleApi().restartService();
                    return;
                }
                return;
            }
            LogHelper.d(str2, "connectype is ++ " + KH1963DBleService.this.f, moduleNames2.getModuleName());
            KH1963DBleService kH1963DBleService6 = KH1963DBleService.this;
            kH1963DBleService6.l = 0;
            kH1963DBleService6.closeGattConnection();
            KH1963DBleService.this.a(Clove1963DBleState.BleState.DISCONNECTED, true);
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
            String str = KH1963DBleService.Q;
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
            String str = KH1963DBleService.Q;
            ModuleNames moduleNames = ModuleNames.BLEABSTRACT;
            LogHelper.e(str, i + "", moduleNames.getModuleName());
            if (i == 0) {
                LogHelper.d(str, "service discovered method", moduleNames.getModuleName());
                KH1963DBleService kH1963DBleService = KH1963DBleService.this;
                kH1963DBleService.d = bluetoothGatt;
                LogHelper.e(str, "initialize ble services", moduleNames.getModuleName());
                if (bluetoothGatt.getServices() != null && bluetoothGatt.getServices().size() != 0) {
                    kH1963DBleService.readWriteCharacteristic = null;
                    kH1963DBleService.setCharacteristicNotification(true);
                    if (kH1963DBleService.readWriteCharacteristic == null) {
                        LogHelper.d(str, " read  or write characteristic is null", moduleNames.getModuleName());
                        kH1963DBleService.stopScan();
                        kH1963DBleService.closeGattConnection();
                        kH1963DBleService.a(Clove1963DBleState.BleState.DISCONNECTED, true);
                        kH1963DBleService.reconnectToConfiguredDevice();
                    } else {
                        kH1963DBleService.a(Clove1963DBleState.BleState.CONNECTED, true);
                        kH1963DBleService.g.removeCallbacks(null);
                        kH1963DBleService.g.postDelayed(kH1963DBleService.y, Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS);
                    }
                } else {
                    LogHelper.d(str, " gatt service is null or empty", moduleNames.getModuleName());
                    kH1963DBleService.stopScan();
                    kH1963DBleService.closeGattConnection();
                    kH1963DBleService.a(Clove1963DBleState.BleState.DISCONNECTED, true);
                    kH1963DBleService.reconnectToConfiguredDevice();
                }
                KH1963DBleService.this.r.post(new Runnable() { // from class: com.coveiot.android.bleabstract.services.KH1963DBleService.8.2
                    @Override // java.lang.Runnable
                    public void run() {
                        if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(KH1963DBleService.this, "android.permission.BLUETOOTH_CONNECT") == 0) {
                            bluetoothGatt.requestMtu(512);
                        }
                    }
                });
                return;
            }
            BleApiManager.getInstance(KH1963DBleService.this).getBleApi().restartService();
        }
    };
    public int P = 200;

    /* loaded from: classes2.dex */
    public class BtServiceBinder extends Binder {
        public BtServiceBinder() {
        }

        public KH1963DBleService getService() {
            return KH1963DBleService.this;
        }
    }

    /* loaded from: classes2.dex */
    public class PPGPostRunnable implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public int f3810a;

        public PPGPostRunnable(KH1963DBleService kH1963DBleService, int i) {
            this.f3810a = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            RawPPGData rawPPGData = new RawPPGData(this.f3810a);
            JstyleLiveResponse jstyleLiveResponse = new JstyleLiveResponse();
            jstyleLiveResponse.setDataType(BleConst.SetMusicControl);
            jstyleLiveResponse.setObj(rawPPGData);
            BleEventBusManager.getInstance().getEventBus().post(jstyleLiveResponse);
        }
    }

    /* loaded from: classes2.dex */
    public class PPGRunnable implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public ArrayList<Map<String, Integer>> f3811a;

        public PPGRunnable(ArrayList<Map<String, Integer>> arrayList) {
            this.f3811a = arrayList;
        }

        @Override // java.lang.Runnable
        public void run() {
            Iterator<Map<String, Integer>> it = this.f3811a.iterator();
            while (it.hasNext()) {
                int intValue = Integer.valueOf(it.next().get(DeviceKey.arrayPpgRawData).intValue()).intValue();
                KH1963DBleService kH1963DBleService = KH1963DBleService.this;
                kH1963DBleService.g.post(new PPGPostRunnable(kH1963DBleService, intValue));
            }
        }
    }

    public static int CalcCRC16(byte[] bArr, int i) {
        int i2 = 65535;
        int i3 = 0;
        while (i > 0) {
            i--;
            int i4 = i3 + 1;
            i2 ^= bArr[i3] & 255;
            for (int i5 = 0; i5 < 8; i5++) {
                int i6 = i2 & 1;
                i2 >>= 1;
                if (i6 == 1) {
                    i2 ^= 40961;
                }
            }
            i3 = i4;
        }
        return i2;
    }

    public static void a(KH1963DBleService kH1963DBleService, byte[] bArr) {
        HashMap<Integer, byte[]> hashMap;
        kH1963DBleService.getClass();
        byte b = bArr[0];
        if (b == -106) {
            if (bArr[1] == 1) {
                kH1963DBleService.d();
            } else if (bArr[1] == 2) {
                kH1963DBleService.J = 0;
                kH1963DBleService.M = 0;
                kH1963DBleService.K = 0;
                kH1963DBleService.L = 0;
                if (bArr[2] == 0) {
                    LogHelper.d("Upload Status", "<><><>Upload Failed");
                } else if (bArr[2] == 1) {
                    LogHelper.d("Upload Status", "<><><>Upload Success");
                }
            } else if (bArr[1] == 3) {
            } else {
                byte b2 = bArr[1];
            }
        } else if (b == -105 && (hashMap = kH1963DBleService.G) != null) {
            if (bArr[1] == 1) {
                kH1963DBleService.N = false;
                int i = kH1963DBleService.J + 1;
                kH1963DBleService.J = i;
                kH1963DBleService.I = hashMap.get(Integer.valueOf(i));
                NumberFormat percentInstance = NumberFormat.getPercentInstance();
                percentInstance.setMaximumFractionDigits(0);
                String format = percentInstance.format(kH1963DBleService.L / kH1963DBleService.F.length);
                if (format.contains("%")) {
                    format = format.replace("%", "");
                }
                LogHelper.d("updateProgress", "<><><>" + format);
                JstyleLiveResponse jstyleLiveResponse = new JstyleLiveResponse();
                jstyleLiveResponse.setDataType(JStyleConstants.CUSTOM_WATCH_FACE_UPLOAD_PERCENTAGE);
                jstyleLiveResponse.setObj(format);
                BleEventBusManager.getInstance().getEventBus().post(jstyleLiveResponse);
                byte[] bArr2 = kH1963DBleService.I;
                if (bArr2 == null) {
                    kH1963DBleService.b();
                    return;
                }
                kH1963DBleService.O = CalcCRC16(bArr2, bArr2.length);
                kH1963DBleService.d();
            } else if (bArr[1] == 0) {
                kH1963DBleService.N = false;
                byte[] bArr3 = hashMap.get(Integer.valueOf(kH1963DBleService.J));
                kH1963DBleService.I = bArr3;
                kH1963DBleService.O = CalcCRC16(bArr3, bArr3.length);
                kH1963DBleService.L -= kH1963DBleService.I.length;
                kH1963DBleService.d();
            }
        }
    }

    public static short dataFromRGB(byte b, byte b2, byte b3) {
        return (short) (((short) ((((char) (b & 248)) + ((char) (b2 >> 5))) * 256)) + ((short) (((char) ((b2 << 3) & 224)) + ((char) (b3 >> 3)))));
    }

    public final void b() {
        byte[] bArr = this.F;
        int length = bArr.length;
        int CalcCRC16 = CalcCRC16(bArr, bArr.length);
        int CalcCRC162 = CalcCRC16(r2, 8);
        byte[] bArr2 = {BleUUID.CMD_ID_96, 2, (byte) (length & 255), (byte) ((length >> 8) & 255), (byte) ((length >> 16) & 255), (byte) ((length >> 24) & 255), (byte) (CalcCRC16 & 255), (byte) ((CalcCRC16 >> 8) & 255), (byte) (CalcCRC162 & 255), (byte) ((CalcCRC162 >> 8) & 255)};
        writeValue(bArr2);
    }

    public final void c() {
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
        a(Clove1963DBleState.BleState.DISCONNECTED, false);
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
        String str = Q;
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
        PreferenceManager1963D.getInstance(getApplicationContext()).saveConnectedDeviceMAcAddress(str);
        PreferenceManager1963D.getInstance(getApplicationContext()).saveConnectionType(ConnectionType.RECONNECT_ON_DISCONNECT.name());
        String str2 = Q;
        LogHelper.d(str2, "connection type ++ " + BleUtils.getConnectionType(this), ModuleNames.BLEABSTRACT.getModuleName());
        c();
    }

    public final void d() {
        int i;
        boolean z;
        byte[] bArr = this.F;
        if (bArr == null || bArr.length == 0 || (i = this.P) == 0 || (z = this.N)) {
            return;
        }
        byte[] bArr2 = this.I;
        if (bArr2 == null) {
            b();
            return;
        }
        int length = bArr2.length;
        int i2 = z ? i - 7 : i - 5;
        this.N = false;
        int i3 = this.M;
        int i4 = i3 + i2;
        if (i4 >= length) {
            i2 = length - i3;
            this.M = 0;
            this.N = true;
        } else {
            this.M = i4;
        }
        int i5 = this.L;
        if (i5 + i2 >= bArr.length) {
            i2 = bArr.length - i5;
        }
        int i6 = this.N ? i2 + 7 : i2 + 5;
        byte[] bArr3 = new byte[i6];
        bArr3[0] = BleUUID.CMD_ID_97;
        bArr3[1] = (byte) this.J;
        int i7 = this.K;
        this.K = i7 + 1;
        bArr3[2] = (byte) i7;
        System.arraycopy(bArr, i5, bArr3, 3, i2);
        if (this.N) {
            int i8 = this.O;
            bArr3[i6 - 4] = (byte) (i8 & 255);
            bArr3[i6 - 3] = (byte) ((i8 >> 8) & 255);
        }
        int i9 = i6 - 2;
        int CalcCRC16 = CalcCRC16(bArr3, i9);
        bArr3[i9] = (byte) (CalcCRC16 & 255);
        bArr3[i6 - 1] = (byte) ((CalcCRC16 >> 8) & 255);
        this.L += i2;
        offerValue(bArr3);
        if (this.N) {
            this.K = 0;
            int i10 = this.J;
            int i11 = this.O;
            int CalcCRC162 = CalcCRC16(r3, 5);
            byte[] bArr4 = {BleUUID.CMD_ID_97, (byte) i10, -1, (byte) (i11 & 255), (byte) ((i11 >> 8) & 255), (byte) (CalcCRC162 & 255), (byte) ((CalcCRC162 >> 8) & 255)};
            offerValue(bArr4);
            nextQueue();
            return;
        }
        d();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public void dataCallback(Map<String, Object> map) {
        char c;
        char c2;
        String str;
        String str2 = (String) map.get(DeviceKey.DataType);
        boolean booleanValue = ((Boolean) map.get(DeviceKey.End)).booleanValue();
        JstyleBaseRes jstyleBaseRes = new JstyleBaseRes();
        String str3 = Q;
        ModuleNames moduleNames = ModuleNames.BLEABSTRACT;
        LogHelper.i(str3, "dataCallback: " + str2 + " -- " + booleanValue, moduleNames.getModuleName());
        if (this.E != null) {
            LogHelper.d("1963 khCurrentCommand", " dataCallback -- " + this.E.getDataType(), moduleNames.getModuleName());
        }
        str2.hashCode();
        int hashCode = str2.hashCode();
        if (hashCode == 49) {
            if (str2.equals("1")) {
                c = 0;
                c2 = c;
            }
            c2 = 65535;
        } else if (hashCode == 52) {
            if (str2.equals(BleConst.GetDeviceInfo)) {
                c = 1;
                c2 = c;
            }
            c2 = 65535;
        } else if (hashCode == 57) {
            if (str2.equals(BleConst.GetDeviceBatteryLevel)) {
                c = 2;
                c2 = c;
            }
            c2 = 65535;
        } else if (hashCode == 1568) {
            if (str2.equals(BleConst.GetDeviceVersion)) {
                c = 3;
                c2 = c;
            }
            c2 = 65535;
        } else if (hashCode == 1576) {
            if (str2.equals(BleConst.GetAlarmClock)) {
                c = 4;
                c2 = c;
            }
            c2 = 65535;
        } else if (hashCode == 1630) {
            if (str2.equals(BleConst.DeviceSendDataToAPP)) {
                c = '\f';
                c2 = c;
            }
            c2 = 65535;
        } else if (hashCode == 1662) {
            if (str2.equals("42")) {
                c = '\r';
                c2 = c;
            }
            c2 = 65535;
        } else if (hashCode == 1696) {
            if (str2.equals("55")) {
                c = 14;
                c2 = c;
            }
            c2 = 65535;
        } else if (hashCode == 1700) {
            if (str2.equals(BleConst.Sendmsg)) {
                c = 15;
                c2 = c;
            }
            c2 = 65535;
        } else if (hashCode == 1726) {
            if (str2.equals(BleConst.SetMusicControl)) {
                c = 16;
                c2 = c;
            }
            c2 = 65535;
        } else if (hashCode == 1600) {
            if (str2.equals(BleConst.SetSedentaryReminder)) {
                c = 5;
                c2 = c;
            }
            c2 = 65535;
        } else if (hashCode != 1601) {
            switch (hashCode) {
                case 1603:
                    if (str2.equals(BleConst.GetDetailActivityData)) {
                        c = 7;
                        c2 = c;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 1604:
                    if (str2.equals(BleConst.GetDetailSleepData)) {
                        c = '\b';
                        c2 = c;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 1605:
                    if (str2.equals(BleConst.GetDynamicHR)) {
                        c = '\t';
                        c2 = c;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 1606:
                    if (str2.equals(BleConst.GetStaticHR)) {
                        c = '\n';
                        c2 = c;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 1607:
                    if (str2.equals(BleConst.GetActivityModeData)) {
                        c = 11;
                        c2 = c;
                        break;
                    }
                    c2 = 65535;
                    break;
                default:
                    c2 = 65535;
                    break;
            }
        } else {
            if (str2.equals(BleConst.RealTimeStep)) {
                c = 6;
                c2 = c;
            }
            c2 = 65535;
        }
        switch (c2) {
            case 0:
                Map<String, String> data = getData(map);
                if (data == null || (str = data.get(DeviceKey.KPhoneDataLength)) == null) {
                    return;
                }
                this.t = Integer.parseInt(str);
                LogHelper.d(str3, "KPhoneDataLength == " + this.t, moduleNames.getModuleName());
                return;
            case 1:
                JstyleBaseReq jstyleBaseReq = this.E;
                if (jstyleBaseReq != null) {
                    if (!jstyleBaseReq.getDataType().equalsIgnoreCase(str2) && !this.E.getDataType().equalsIgnoreCase(JStyleConstants.WATCH_FACE_POSITION)) {
                        LogHelper.d(str3, "GetDeviceInfo else");
                        Map<String, String> data2 = getData(map);
                        JstyleLiveResponse jstyleLiveResponse = new JstyleLiveResponse();
                        jstyleLiveResponse.setDataType(BleConst.GetDeviceInfo);
                        jstyleLiveResponse.setObj(data2);
                        BleEventBusManager.getInstance().getEventBus().post(jstyleLiveResponse);
                        return;
                    }
                    Map<String, String> data3 = getData(map);
                    jstyleBaseRes.setBaseReq(this.E);
                    jstyleBaseRes.obj = data3;
                    this.E.getResponseListener().onResponse(jstyleBaseRes);
                    return;
                }
                LogHelper.d(str3, "GetDeviceInfo khCurrentCommand else null");
                Map<String, String> data4 = getData(map);
                JstyleLiveResponse jstyleLiveResponse2 = new JstyleLiveResponse();
                jstyleLiveResponse2.setDataType(BleConst.GetDeviceInfo);
                jstyleLiveResponse2.setObj(data4);
                BleEventBusManager.getInstance().getEventBus().post(jstyleLiveResponse2);
                return;
            case 2:
            case 3:
                JstyleBaseReq jstyleBaseReq2 = this.E;
                if (jstyleBaseReq2 == null || !jstyleBaseReq2.getDataType().equalsIgnoreCase(str2)) {
                    return;
                }
                Map<String, String> data5 = getData(map);
                jstyleBaseRes.setBaseReq(this.E);
                jstyleBaseRes.obj = data5;
                this.E.getResponseListener().onResponse(jstyleBaseRes);
                return;
            case 4:
                LogHelper.d(str3, "SetAlarmClockWithAllClock", moduleNames.getModuleName());
                JstyleBaseReq jstyleBaseReq3 = this.E;
                if (jstyleBaseReq3 == null || !jstyleBaseReq3.getDataType().equalsIgnoreCase(str2)) {
                    return;
                }
                jstyleBaseRes.setBaseReq(this.E);
                this.E.getResponseListener().onResponse(jstyleBaseRes);
                return;
            case 5:
                LogHelper.d(str3, "SetSedentaryReminder", moduleNames.getModuleName());
                JstyleBaseReq jstyleBaseReq4 = this.E;
                if (jstyleBaseReq4 == null || !jstyleBaseReq4.getDataType().equalsIgnoreCase(str2)) {
                    return;
                }
                jstyleBaseRes.setBaseReq(this.E);
                this.E.getResponseListener().onResponse(jstyleBaseRes);
                return;
            case 6:
                LogHelper.d(str3, "RealTimeStep", moduleNames.getModuleName());
                Map<String, String> data6 = getData(map);
                JstyleLiveResponse jstyleLiveResponse3 = new JstyleLiveResponse();
                jstyleLiveResponse3.setDataType(BleConst.RealTimeStep);
                jstyleLiveResponse3.setObj(data6);
                BleEventBusManager.getInstance().getEventBus().post(jstyleLiveResponse3);
                return;
            case 7:
                JstyleBaseReq jstyleBaseReq5 = this.E;
                if (jstyleBaseReq5 != null) {
                    if (jstyleBaseReq5.getDataType().equalsIgnoreCase(str2) || this.E.getDataType().equalsIgnoreCase(JStyleConstants.SESSION_STEPS_DATA)) {
                        this.p.addAll((List) map.get(DeviceKey.Data));
                        this.n++;
                        if (booleanValue) {
                            this.n = 0;
                            jstyleBaseRes.setBaseReq(this.E);
                            jstyleBaseRes.obj = this.p;
                            this.E.getResponseListener().onResponse(jstyleBaseRes);
                            this.q = false;
                        }
                        if (this.n == this.o) {
                            this.n = 0;
                            if (booleanValue) {
                                jstyleBaseRes.setBaseReq(this.E);
                                this.n = 0;
                                jstyleBaseRes.obj = this.p;
                                this.E.getResponseListener().onResponse(jstyleBaseRes);
                                this.q = false;
                                return;
                            }
                            this.q = true;
                            JstyleWalkReq jstyleWalkReq = new JstyleWalkReq();
                            jstyleWalkReq.setDataType(this.E.getDataType());
                            jstyleWalkReq.setMode(JStyleConstants.Constants.getMODE_CONTINUE());
                            jstyleWalkReq.setReqId(this.E.getReqId());
                            sendRequest(jstyleWalkReq, this.E.getResponseListener());
                            return;
                        }
                        return;
                    }
                    return;
                }
                return;
            case '\b':
                JstyleBaseReq jstyleBaseReq6 = this.E;
                if (jstyleBaseReq6 == null || !jstyleBaseReq6.getDataType().equalsIgnoreCase(str2)) {
                    return;
                }
                this.p.addAll((List) map.get(DeviceKey.Data));
                this.n++;
                if (booleanValue) {
                    this.n = 0;
                    jstyleBaseRes.setBaseReq(this.E);
                    jstyleBaseRes.obj = this.p;
                    this.E.getResponseListener().onResponse(jstyleBaseRes);
                    this.q = false;
                }
                if (this.n == this.o) {
                    this.n = 0;
                    if (booleanValue) {
                        this.n = 0;
                        jstyleBaseRes.setBaseReq(this.E);
                        jstyleBaseRes.obj = this.p;
                        this.E.getResponseListener().onResponse(jstyleBaseRes);
                        this.q = false;
                        return;
                    }
                    this.q = true;
                    JstyleSleepReq jstyleSleepReq = new JstyleSleepReq();
                    jstyleSleepReq.setDataType(BleConst.GetDetailSleepData);
                    jstyleSleepReq.setReqId(this.E.getReqId());
                    jstyleSleepReq.setMode(JStyleConstants.Constants.getMODE_CONTINUE());
                    sendRequest(jstyleSleepReq, this.E.getResponseListener());
                    return;
                }
                return;
            case '\t':
                JstyleBaseReq jstyleBaseReq7 = this.E;
                if (jstyleBaseReq7 == null || !jstyleBaseReq7.getDataType().equalsIgnoreCase(str2)) {
                    return;
                }
                this.p.addAll((List) map.get(DeviceKey.Data));
                this.n++;
                if (booleanValue) {
                    this.n = 0;
                    jstyleBaseRes.setBaseReq(this.E);
                    jstyleBaseRes.obj = this.p;
                    this.E.getResponseListener().onResponse(jstyleBaseRes);
                    this.q = false;
                }
                if (this.n == this.o) {
                    this.n = 0;
                    if (booleanValue) {
                        this.n = 0;
                        jstyleBaseRes.setBaseReq(this.E);
                        jstyleBaseRes.obj = this.p;
                        this.E.getResponseListener().onResponse(jstyleBaseRes);
                        this.q = false;
                        return;
                    }
                    this.q = true;
                    JstyleHistoryHRReq jstyleHistoryHRReq = new JstyleHistoryHRReq();
                    jstyleHistoryHRReq.setDataType(BleConst.GetDynamicHR);
                    jstyleHistoryHRReq.setMode(JStyleConstants.Constants.getMODE_CONTINUE());
                    jstyleHistoryHRReq.setReqId(this.E.getReqId());
                    sendRequest(jstyleHistoryHRReq, this.E.getResponseListener());
                    return;
                }
                return;
            case '\n':
                JstyleBaseReq jstyleBaseReq8 = this.E;
                if (jstyleBaseReq8 == null || !jstyleBaseReq8.getDataType().equalsIgnoreCase(str2)) {
                    return;
                }
                this.p.addAll((List) map.get(DeviceKey.Data));
                this.n++;
                if (booleanValue) {
                    this.n = 0;
                    jstyleBaseRes.setBaseReq(this.E);
                    jstyleBaseRes.obj = this.p;
                    this.E.getResponseListener().onResponse(jstyleBaseRes);
                    this.q = false;
                }
                if (this.n == this.o) {
                    this.n = 0;
                    if (booleanValue) {
                        this.n = 0;
                        jstyleBaseRes.setBaseReq(this.E);
                        jstyleBaseRes.obj = this.p;
                        this.E.getResponseListener().onResponse(jstyleBaseRes);
                        this.q = false;
                        return;
                    }
                    this.q = true;
                    JstyleOnceHRReq jstyleOnceHRReq = new JstyleOnceHRReq();
                    jstyleOnceHRReq.setDataType(BleConst.GetStaticHR);
                    jstyleOnceHRReq.setMode(JStyleConstants.Constants.getMODE_CONTINUE());
                    jstyleOnceHRReq.setReqId(this.E.getReqId());
                    sendRequest(jstyleOnceHRReq, this.E.getResponseListener());
                    return;
                }
                return;
            case 11:
                JstyleBaseReq jstyleBaseReq9 = this.E;
                if (jstyleBaseReq9 == null || !jstyleBaseReq9.getDataType().equalsIgnoreCase(str2)) {
                    return;
                }
                this.p.addAll((List) map.get(DeviceKey.Data));
                this.n++;
                if (booleanValue) {
                    this.n = 0;
                    jstyleBaseRes.setBaseReq(this.E);
                    jstyleBaseRes.obj = this.p;
                    this.E.getResponseListener().onResponse(jstyleBaseRes);
                    this.q = false;
                }
                if (this.n == this.o) {
                    this.n = 0;
                    if (booleanValue) {
                        this.n = 0;
                        jstyleBaseRes.setBaseReq(this.E);
                        jstyleBaseRes.obj = this.p;
                        this.E.getResponseListener().onResponse(jstyleBaseRes);
                        this.q = false;
                        return;
                    }
                    this.q = true;
                    JstyleActivityModeHistoryReq jstyleActivityModeHistoryReq = new JstyleActivityModeHistoryReq();
                    jstyleActivityModeHistoryReq.setDataType(BleConst.GetActivityModeData);
                    jstyleActivityModeHistoryReq.setMode(JStyleConstants.Constants.getMODE_CONTINUE());
                    jstyleActivityModeHistoryReq.setReqId(this.E.getReqId());
                    sendRequest(jstyleActivityModeHistoryReq, this.E.getResponseListener());
                    return;
                }
                return;
            case '\f':
                LogHelper.d(str3, "DeviceSendDataToAPP", moduleNames.getModuleName());
                Map<String, String> data7 = getData(map);
                if (data7 != null) {
                    int parseInt = Integer.parseInt(data7.get("type"));
                    if (parseInt == 0) {
                        JstyleLiveResponse jstyleLiveResponse4 = new JstyleLiveResponse();
                        jstyleLiveResponse4.setDataType("RejectTelMode");
                        jstyleLiveResponse4.setObj(Boolean.TRUE);
                        BleEventBusManager.getInstance().getEventBus().post(jstyleLiveResponse4);
                        return;
                    } else if (parseInt != 8) {
                        return;
                    } else {
                        JstyleLiveResponse jstyleLiveResponse5 = new JstyleLiveResponse();
                        jstyleLiveResponse5.setDataType("FindMobilePhoneMode");
                        jstyleLiveResponse5.setObj(Boolean.TRUE);
                        BleEventBusManager.getInstance().getEventBus().post(jstyleLiveResponse5);
                        return;
                    }
                }
                return;
            case '\r':
                JstyleBaseReq jstyleBaseReq10 = this.E;
                if (jstyleBaseReq10 == null || !jstyleBaseReq10.getDataType().equalsIgnoreCase(str2)) {
                    return;
                }
                this.p.addAll((List) map.get(DeviceKey.Data));
                this.n++;
                if (booleanValue) {
                    this.n = 0;
                    jstyleBaseRes.setBaseReq(this.E);
                    jstyleBaseRes.obj = this.p;
                    this.E.getResponseListener().onResponse(jstyleBaseRes);
                    this.q = false;
                }
                if (this.n == this.o) {
                    this.n = 0;
                    if (booleanValue) {
                        jstyleBaseRes.setBaseReq(this.E);
                        this.n = 0;
                        jstyleBaseRes.obj = this.p;
                        this.E.getResponseListener().onResponse(jstyleBaseRes);
                        this.q = false;
                        return;
                    }
                    this.q = true;
                    JstyleHrvReq jstyleHrvReq = new JstyleHrvReq();
                    jstyleHrvReq.setDataType("42");
                    jstyleHrvReq.setMode(JStyleConstants.Constants.getMODE_CONTINUE());
                    jstyleHrvReq.setReqId(this.E.getReqId());
                    sendRequest(jstyleHrvReq, this.E.getResponseListener());
                    return;
                }
                return;
            case 14:
                JstyleBaseReq jstyleBaseReq11 = this.E;
                if (jstyleBaseReq11 == null || !jstyleBaseReq11.getDataType().equalsIgnoreCase(str2)) {
                    return;
                }
                this.p.addAll((List) map.get(DeviceKey.Data));
                this.n++;
                if (booleanValue) {
                    this.n = 0;
                    jstyleBaseRes.setBaseReq(this.E);
                    jstyleBaseRes.obj = this.p;
                    this.E.getResponseListener().onResponse(jstyleBaseRes);
                    this.q = false;
                }
                if (this.n == this.o) {
                    this.n = 0;
                    if (booleanValue) {
                        jstyleBaseRes.setBaseReq(this.E);
                        this.n = 0;
                        jstyleBaseRes.obj = this.p;
                        this.E.getResponseListener().onResponse(jstyleBaseRes);
                        this.q = false;
                        return;
                    }
                    this.q = true;
                    JstyleSpo2Req jstyleSpo2Req = new JstyleSpo2Req();
                    jstyleSpo2Req.setDataType("55");
                    jstyleSpo2Req.setMode(JStyleConstants.Constants.getMODE_CONTINUE());
                    jstyleSpo2Req.setReqId(this.E.getReqId());
                    sendRequest(jstyleSpo2Req, this.E.getResponseListener());
                    return;
                }
                return;
            case 15:
                JstyleBaseReq jstyleBaseReq12 = this.E;
                if (jstyleBaseReq12 == null || !jstyleBaseReq12.getDataType().equalsIgnoreCase(str2)) {
                    return;
                }
                this.p.addAll((List) map.get(DeviceKey.Data));
                this.n++;
                if (booleanValue) {
                    this.n = 0;
                    jstyleBaseRes.setBaseReq(this.E);
                    jstyleBaseRes.obj = this.p;
                    this.E.getResponseListener().onResponse(jstyleBaseRes);
                    this.q = false;
                }
                if (this.n == this.o) {
                    this.n = 0;
                    if (booleanValue) {
                        jstyleBaseRes.setBaseReq(this.E);
                        this.n = 0;
                        jstyleBaseRes.obj = this.p;
                        this.E.getResponseListener().onResponse(jstyleBaseRes);
                        this.q = false;
                        return;
                    }
                    this.q = true;
                    JstyleTemperatureReq jstyleTemperatureReq = new JstyleTemperatureReq();
                    jstyleTemperatureReq.setDataType(BleConst.Sendmsg);
                    jstyleTemperatureReq.setMode(JStyleConstants.Constants.getMODE_CONTINUE());
                    jstyleTemperatureReq.setReqId(this.E.getReqId());
                    sendRequest(jstyleTemperatureReq, this.E.getResponseListener());
                    return;
                }
                return;
            case 16:
                this.v.post(new PPGRunnable(getDataHashMapList(map)));
                return;
            default:
                return;
        }
    }

    public void dataCallback(byte[] bArr) {
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
        PreferenceManager1963D.getInstance(getApplicationContext()).saveConnectedDeviceMAcAddress("");
        PreferenceManager1963D.getInstance(getApplicationContext()).saveConnectionType(ConnectionType.DONT_CONNECT_ON_DISCONNECT.name());
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
        PreferenceManager1963D.getInstance(getApplicationContext()).saveConnectionType(ConnectionType.DONT_CONNECT_ON_DISCONNECT.name());
        stopForeground(true);
        stopSelf();
    }

    public ConnectionError getConnectionError() {
        return this.B;
    }

    public long getConnectionStageChangeTimeStamp() {
        return this.C;
    }

    public Clove1963DBleState.BleState getConnectionState() {
        return this.bluetoothConnectionStatus;
    }

    public Map<String, String> getData(Map<String, Object> map) {
        return (Map) map.get(DeviceKey.Data);
    }

    public ArrayList<Map<String, Integer>> getDataHashMapList(Map<String, Object> map) {
        return (ArrayList) map.get(DeviceKey.Data);
    }

    public Map<String, byte[]> getDataList(Map<String, Object> map) {
        return (Map) map.get(DeviceKey.Data);
    }

    public void initBluetoothDevice() {
        c();
    }

    public void initServiceParams() {
        if (this.j == null) {
            this.j = new Handler();
        }
        if (this.v == null) {
            HandlerThread handlerThread = new HandlerThread("gatt_response");
            this.w = handlerThread;
            handlerThread.start();
            this.v = new Handler(this.w.getLooper());
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
        a(Clove1963DBleState.BleState.DISCONNECTED, false);
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
        return this.f3797a;
    }

    @Override // android.app.Service
    public void onCreate() {
        a();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        LogHelper.d(Q, "Service onDestroy", ModuleNames.BLEABSTRACT.getModuleName());
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
            java.lang.Class<com.coveiot.android.bleabstract.services.KH1963DBleService> r4 = com.coveiot.android.bleabstract.services.KH1963DBleService.class
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
            com.coveiot.android.bleabstract.preferences.PreferenceManager1963D r2 = com.coveiot.android.bleabstract.preferences.PreferenceManager1963D.getInstance(r2)
            java.lang.String r2 = r2.getConnectedDeviceMacAddress()
            r1.e = r2
            android.content.Context r2 = r1.getApplicationContext()
            com.coveiot.sdk.ble.events.ConnectionType r2 = com.coveiot.sdk.ble.utils.BleUtils.getConnectionType(r2)
            r1.f = r2
            java.lang.String r2 = com.coveiot.android.bleabstract.services.KH1963DBleService.Q
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
            android.content.BroadcastReceiver r0 = r1.A     // Catch: java.lang.Exception -> La8
            r4.registerReceiver(r0, r2)     // Catch: java.lang.Exception -> La8
            android.content.IntentFilter r2 = new android.content.IntentFilter     // Catch: java.lang.Exception -> La8
            java.lang.String r4 = "action_stop_service"
            r2.<init>(r4)     // Catch: java.lang.Exception -> La8
            android.content.Context r4 = r1.getApplicationContext()     // Catch: java.lang.Exception -> La8
            android.content.BroadcastReceiver r0 = r1.A     // Catch: java.lang.Exception -> La8
            r4.registerReceiver(r0, r2)     // Catch: java.lang.Exception -> La8
            goto Lac
        La8:
            r2 = move-exception
            r2.printStackTrace()
        Lac:
            java.lang.String r2 = r1.e
            boolean r2 = com.coveiot.sdk.ble.utils.BleUtils.isEmpty(r2)
            if (r2 != 0) goto Lb7
            r1.c()
        Lb7:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.bleabstract.services.KH1963DBleService.onStartCommand(android.content.Intent, int, int):int");
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    public void reconnectToConfiguredDevice() {
        String str = Q;
        ModuleNames moduleNames = ModuleNames.BLEABSTRACT;
        LogHelper.d(str, "reconnectToConfiguredDevice", moduleNames.getModuleName());
        closeGattConnection();
        this.e = PreferenceManager1963D.getInstance(getApplicationContext()).getConnectedDeviceMacAddress();
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
        PreferenceManager1963D.getInstance(getApplicationContext()).saveConnectionType(ConnectionType.RECONNECT_ON_DISCONNECT.name());
        stopForeground(true);
        stopSelf();
        LogHelper.d(Q, "Service restartService", ModuleNames.BLEABSTRACT.getModuleName());
    }

    public void scanLeDevice() {
        if (!PreferenceManager1963D.getInstance(getApplicationContext()).isBandUnpaired().booleanValue()) {
            this.j.removeCallbacksAndMessages(null);
            this.i.removeCallbacks(null);
            stopScan();
            this.j.postDelayed(new Runnable() { // from class: com.coveiot.android.bleabstract.services.KH1963DBleService.4
                @Override // java.lang.Runnable
                @RequiresApi(api = 21)
                public void run() {
                    String str = KH1963DBleService.Q;
                    ModuleNames moduleNames = ModuleNames.BLEABSTRACT;
                    LogHelper.d(str, "Scanning...", moduleNames.getModuleName());
                    KH1963DBleService.this.closeGattConnection();
                    try {
                        KH1963DBleService kH1963DBleService = KH1963DBleService.this;
                        kH1963DBleService.e = PreferenceManager1963D.getInstance(kH1963DBleService.getApplicationContext()).getConnectedDeviceMacAddress();
                        LogHelper.d(str, "BLE deviceAddress as " + KH1963DBleService.this.e, moduleNames.getModuleName());
                        if (BleUtils.isEmpty(KH1963DBleService.this.e)) {
                            return;
                        }
                        ScanFilter.Builder builder = new ScanFilter.Builder();
                        builder.setDeviceAddress(KH1963DBleService.this.e);
                        new ArrayList().add(builder.build());
                        ScanSettings build = new ScanSettings.Builder().setScanMode(1).build();
                        KH1963DBleService.this.a(Clove1963DBleState.BleState.SCANNING, true);
                        KH1963DBleService kH1963DBleService2 = KH1963DBleService.this;
                        kH1963DBleService2.h.postDelayed(kH1963DBleService2.x, 1800000L);
                        if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(KH1963DBleService.this, "android.permission.BLUETOOTH_SCAN") == 0) {
                            KH1963DBleService.this.c.getBluetoothLeScanner().startScan((List<ScanFilter>) null, build, KH1963DBleService.this.z);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, 500L);
            return;
        }
        a(Clove1963DBleState.BleState.DISCONNECTED, true);
    }

    public void sendCommandToBand(byte[] bArr) {
        writeValue(bArr);
    }

    public void sendRequest(final JstyleBaseReq jstyleBaseReq, JstyleResponseListener jstyleResponseListener) {
        jstyleBaseReq.setResponseListener(jstyleResponseListener);
        this.E = jstyleBaseReq;
        LogHelper.d("1963 khCurrentCommand", " sendRequest-- " + this.E.getDataType(), ModuleNames.BLEABSTRACT.getModuleName());
        String dataType = jstyleBaseReq.getDataType();
        if (dataType != null) {
            char c = 65535;
            int hashCode = dataType.hashCode();
            if (hashCode != 56) {
                if (hashCode != 57) {
                    if (hashCode != 1600) {
                        if (hashCode != 1601) {
                            switch (hashCode) {
                                case -2059680389:
                                    if (dataType.equals(JStyleConstants.CUSTOM_WATCH_FACE)) {
                                        c = 0;
                                        break;
                                    }
                                    break;
                                case -2041249468:
                                    if (dataType.equals(JStyleConstants.EnterOTA)) {
                                        c = 1;
                                        break;
                                    }
                                    break;
                                case -1710014578:
                                    if (dataType.equals(JStyleConstants.DISTANCE_UNIT)) {
                                        c = 2;
                                        break;
                                    }
                                    break;
                                case -1257575528:
                                    if (dataType.equals(JStyleConstants.TempUnit)) {
                                        c = 3;
                                        break;
                                    }
                                    break;
                                case -1039006405:
                                    if (dataType.equals(JStyleConstants.WATCH_FACE_POSITION)) {
                                        c = 4;
                                        break;
                                    }
                                    break;
                                case -516402510:
                                    if (dataType.equals(JStyleConstants.HOUR_FORMAT)) {
                                        c = 5;
                                        break;
                                    }
                                    break;
                                case 1568:
                                    if (dataType.equals(BleConst.GetDeviceVersion)) {
                                        c = 11;
                                        break;
                                    }
                                    break;
                                case 1571:
                                    if (dataType.equals(BleConst.SetMotorVibrationWithTimes)) {
                                        c = '\f';
                                        break;
                                    }
                                    break;
                                case 1574:
                                    if (dataType.equals(BleConst.GetAutomaticHRMonitoring)) {
                                        c = '\r';
                                        break;
                                    }
                                    break;
                                case 1576:
                                    if (dataType.equals(BleConst.GetAlarmClock)) {
                                        c = 14;
                                        break;
                                    }
                                    break;
                                case 1662:
                                    if (dataType.equals("42")) {
                                        c = 22;
                                        break;
                                    }
                                    break;
                                case 1696:
                                    if (dataType.equals("55")) {
                                        c = 23;
                                        break;
                                    }
                                    break;
                                case 1700:
                                    if (dataType.equals(BleConst.Sendmsg)) {
                                        c = 24;
                                        break;
                                    }
                                    break;
                                case 1722:
                                    if (dataType.equals(BleConst.ReadHeartateSensorstatus)) {
                                        c = 25;
                                        break;
                                    }
                                    break;
                                case 1726:
                                    if (dataType.equals(BleConst.SetMusicControl)) {
                                        c = JSONLexer.EOI;
                                        break;
                                    }
                                    break;
                                case 67700:
                                    if (dataType.equals("DIY")) {
                                        c = 27;
                                        break;
                                    }
                                    break;
                                case 759553291:
                                    if (dataType.equals(JStyleConstants.Notification)) {
                                        c = 28;
                                        break;
                                    }
                                    break;
                                case 930854267:
                                    if (dataType.equals(JStyleConstants.LIFT_WRIST)) {
                                        c = 29;
                                        break;
                                    }
                                    break;
                                case 1309954891:
                                    if (dataType.equals(JStyleConstants.SESSION_STEPS_DATA)) {
                                        c = 30;
                                        break;
                                    }
                                    break;
                                default:
                                    switch (hashCode) {
                                        case 51:
                                            if (dataType.equals("3")) {
                                                c = 6;
                                                break;
                                            }
                                            break;
                                        case 52:
                                            if (dataType.equals(BleConst.GetDeviceInfo)) {
                                                c = 7;
                                                break;
                                            }
                                            break;
                                        case 53:
                                            if (dataType.equals(BleConst.SetDeviceInfo)) {
                                                c = '\b';
                                                break;
                                            }
                                            break;
                                        default:
                                            switch (hashCode) {
                                                case 1603:
                                                    if (dataType.equals(BleConst.GetDetailActivityData)) {
                                                        c = 17;
                                                        break;
                                                    }
                                                    break;
                                                case 1604:
                                                    if (dataType.equals(BleConst.GetDetailSleepData)) {
                                                        c = 18;
                                                        break;
                                                    }
                                                    break;
                                                case 1605:
                                                    if (dataType.equals(BleConst.GetDynamicHR)) {
                                                        c = 19;
                                                        break;
                                                    }
                                                    break;
                                                case 1606:
                                                    if (dataType.equals(BleConst.GetStaticHR)) {
                                                        c = 20;
                                                        break;
                                                    }
                                                    break;
                                                case 1607:
                                                    if (dataType.equals(BleConst.GetActivityModeData)) {
                                                        c = 21;
                                                        break;
                                                    }
                                                    break;
                                            }
                                    }
                            }
                        } else if (dataType.equals(BleConst.RealTimeStep)) {
                            c = 16;
                        }
                    } else if (dataType.equals(BleConst.SetSedentaryReminder)) {
                        c = 15;
                    }
                } else if (dataType.equals(BleConst.GetDeviceBatteryLevel)) {
                    c = '\n';
                }
            } else if (dataType.equals(BleConst.SetStepGoal)) {
                c = '\t';
            }
            switch (c) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 5:
                case 6:
                case '\b':
                case '\t':
                case '\f':
                case '\r':
                case 15:
                case 16:
                case 25:
                case 26:
                case 28:
                case 29:
                    sendCommandToBand(jstyleBaseReq.getCommandBytes());
                    JstyleBaseReq jstyleBaseReq2 = this.E;
                    if (jstyleBaseReq2 == null || !jstyleBaseReq2.getDataType().equalsIgnoreCase(dataType)) {
                        return;
                    }
                    JstyleBaseRes jstyleBaseRes = new JstyleBaseRes();
                    jstyleBaseRes.setBaseReq(this.E);
                    this.E.getResponseListener().onResponse(jstyleBaseRes);
                    return;
                case 4:
                case 7:
                case '\n':
                case 11:
                    sendCommandToBand(jstyleBaseReq.getCommandBytes());
                    return;
                case 14:
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
                case 17:
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 30:
                    if (!this.q) {
                        clearResponseList();
                    }
                    sendCommandToBand(jstyleBaseReq.getCommandBytes());
                    return;
                case 27:
                    sendCommandToBand(jstyleBaseReq.getCommandBytes());
                    JstyleBaseReq jstyleBaseReq3 = this.E;
                    if (jstyleBaseReq3 != null && jstyleBaseReq3.getDataType().equalsIgnoreCase(dataType)) {
                        JstyleBaseRes jstyleBaseRes2 = new JstyleBaseRes();
                        jstyleBaseRes2.setBaseReq(this.E);
                        this.E.getResponseListener().onResponse(jstyleBaseRes2);
                    }
                    this.j.postDelayed(new Runnable() { // from class: com.coveiot.android.bleabstract.services.KH1963DBleService.9
                        @Override // java.lang.Runnable
                        public void run() {
                            KH1963DBleService kH1963DBleService = KH1963DBleService.this;
                            Bitmap bitmap = jstyleBaseReq.getBitmap();
                            String str = KH1963DBleService.Q;
                            kH1963DBleService.getClass();
                            int height = bitmap.getHeight();
                            int width = bitmap.getWidth();
                            kH1963DBleService.F = new byte[115200];
                            int i5 = 0;
                            int i6 = 0;
                            for (int i7 = 0; i7 < height; i7++) {
                                for (int i8 = 0; i8 < width; i8++) {
                                    int pixel = bitmap.getPixel(i8, i7);
                                    short dataFromRGB = KH1963DBleService.dataFromRGB((byte) Color.red(pixel), (byte) Color.green(pixel), (byte) Color.blue(pixel));
                                    byte[] bArr2 = kH1963DBleService.F;
                                    int i9 = i6 * 2;
                                    bArr2[i9] = (byte) ((dataFromRGB >> 8) & 255);
                                    bArr2[i9 + 1] = (byte) (dataFromRGB & 255);
                                    i6++;
                                }
                            }
                            byte[] bArr3 = kH1963DBleService.F;
                            int length4 = bArr3.length % 4096;
                            int length5 = bArr3.length / 4096;
                            if (length4 != 0) {
                                length5++;
                            }
                            kH1963DBleService.H = new int[length5];
                            kH1963DBleService.G = new HashMap<>();
                            for (int i10 = 0; i10 < length5; i10++) {
                                int i11 = i10 * 4096;
                                int i12 = i11 + 4096;
                                byte[] bArr4 = kH1963DBleService.F;
                                int length6 = i12 >= bArr4.length ? bArr4.length - i11 : 4096;
                                byte[] bArr5 = new byte[length6];
                                System.arraycopy(bArr4, i11, bArr5, 0, length6);
                                kH1963DBleService.H[i10] = KH1963DBleService.CalcCRC16(bArr5, length6);
                                kH1963DBleService.G.put(Integer.valueOf(i10), bArr5);
                            }
                            byte[] bArr6 = kH1963DBleService.G.get(Integer.valueOf(kH1963DBleService.J));
                            kH1963DBleService.I = bArr6;
                            kH1963DBleService.O = KH1963DBleService.CalcCRC16(bArr6, bArr6.length);
                            byte[] bArr7 = kH1963DBleService.F;
                            int length7 = bArr7.length;
                            int CalcCRC16 = KH1963DBleService.CalcCRC16(bArr7, bArr7.length);
                            int length8 = (kH1963DBleService.H.length * 2) + 10;
                            byte[] bArr8 = new byte[length8];
                            bArr8[0] = BleUUID.CMD_ID_96;
                            bArr8[1] = 1;
                            bArr8[2] = (byte) (length7 & 255);
                            bArr8[3] = (byte) ((length7 >> 8) & 255);
                            bArr8[4] = (byte) ((length7 >> 16) & 255);
                            bArr8[5] = (byte) ((length7 >> 24) & 255);
                            bArr8[6] = (byte) (CalcCRC16 & 255);
                            bArr8[7] = (byte) ((CalcCRC16 >> 8) & 255);
                            while (true) {
                                int[] iArr = kH1963DBleService.H;
                                if (i5 < iArr.length) {
                                    int i13 = (i5 * 2) + 8;
                                    bArr8[i13] = (byte) (iArr[i5] & 255);
                                    bArr8[i13 + 1] = (byte) ((iArr[i5] >> 8) & 255);
                                    i5++;
                                } else {
                                    int i14 = length8 - 2;
                                    int CalcCRC162 = KH1963DBleService.CalcCRC16(bArr8, i14);
                                    bArr8[i14] = (byte) (CalcCRC162 & 255);
                                    bArr8[length8 - 1] = (byte) ((CalcCRC162 >> 8) & 255);
                                    kH1963DBleService.writeValue(bArr8);
                                    return;
                                }
                            }
                        }
                    }, Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS);
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
                this.c.getBluetoothLeScanner().stopScan(this.z);
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
        if (this.A != null) {
            try {
                getApplicationContext().unregisterReceiver(this.A);
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
        String str = Q;
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

    public final void a(final BluetoothDevice bluetoothDevice) {
        a(Clove1963DBleState.BleState.CONNECTING, true);
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
        this.g.postDelayed(new Runnable() { // from class: com.coveiot.android.bleabstract.services.KH1963DBleService.2
            @Override // java.lang.Runnable
            public void run() {
                String str = KH1963DBleService.Q;
                LogHelper.d(str, "connectGatt called " + bluetoothDevice.getAddress(), ModuleNames.BLEABSTRACT.getModuleName());
                KH1963DBleService kH1963DBleService = KH1963DBleService.this;
                if (kH1963DBleService.D != null) {
                    if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(kH1963DBleService, "android.permission.BLUETOOTH_CONNECT") == 0) {
                        KH1963DBleService kH1963DBleService2 = KH1963DBleService.this;
                        kH1963DBleService2.d = bluetoothDevice.connectGatt(kH1963DBleService2.getApplicationContext(), false, KH1963DBleService.this.D);
                    }
                }
            }
        }, 1000L);
    }

    public final void a(final Clove1963DBleState.BleState bleState, boolean z) {
        Handler handler;
        this.bluetoothConnectionStatus = bleState;
        if (!z || (handler = this.g) == null) {
            return;
        }
        handler.removeCallbacksAndMessages(null);
        this.g.post(new Runnable(this) { // from class: com.coveiot.android.bleabstract.services.KH1963DBleService.3
            @Override // java.lang.Runnable
            public void run() {
                BleEventBusManager.getInstance().getEventBus().post(new Clove1963DBleState(bleState));
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
