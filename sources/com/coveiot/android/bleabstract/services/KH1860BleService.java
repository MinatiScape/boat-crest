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
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.alibaba.fastjson.parser.JSONLexer;
import com.clevertap.android.sdk.Constants;
import com.coveiot.android.bleabstract.BleApiUtils;
import com.coveiot.android.bleabstract.JstyleResolveData;
import com.coveiot.android.bleabstract.NotificationInfo;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.api.JStyleUUID;
import com.coveiot.android.bleabstract.models.Clove1860BleState;
import com.coveiot.android.bleabstract.models.RawPPGData;
import com.coveiot.android.bleabstract.preferences.PreferenceManager1860;
import com.coveiot.android.bleabstract.response.ConnectionError;
import com.coveiot.android.bleabstract.response.LiveMusicControlRes;
import com.coveiot.android.bleabstract.response.MusicControlState;
import com.coveiot.android.bleabstract.utils.jstyleUtils.JStyleConstants;
import com.coveiot.android.bleabstract.utils.jstyleUtils.JStyleUtils;
import com.coveiot.android.jstyle1860Sdk.FileType;
import com.coveiot.android.jstyle1860Sdk.JstyleResponseListener;
import com.coveiot.android.jstyle1860Sdk.api.JstyleActivityModeHistoryReq;
import com.coveiot.android.jstyle1860Sdk.api.JstyleBaseReq;
import com.coveiot.android.jstyle1860Sdk.api.JstyleBaseRes;
import com.coveiot.android.jstyle1860Sdk.api.JstyleGPSDataReq;
import com.coveiot.android.jstyle1860Sdk.api.JstyleHistoryHRReq;
import com.coveiot.android.jstyle1860Sdk.api.JstyleHrvReq;
import com.coveiot.android.jstyle1860Sdk.api.JstyleOnceHRReq;
import com.coveiot.android.jstyle1860Sdk.api.JstyleSleepReq;
import com.coveiot.android.jstyle1860Sdk.api.JstyleSpo2Req;
import com.coveiot.android.jstyle1860Sdk.api.JstyleWalkReq;
import com.coveiot.android.jstyle1860Sdk.error.JstyleError;
import com.coveiot.android.jstyle1860Sdk.error.JstyleErrorType;
import com.coveiot.android.jstyle1860Sdk.model.JstyleLiveResponse;
import com.coveiot.sdk.ble.api.BleUUID;
import com.coveiot.sdk.ble.api.model.CameraState;
import com.coveiot.sdk.ble.api.response.CameraEventRes;
import com.coveiot.sdk.ble.eventbus.BleEventBusManager;
import com.coveiot.sdk.ble.events.ConnectionType;
import com.coveiot.sdk.ble.model.BleDeviceInfo;
import com.coveiot.sdk.ble.model.CommandObject;
import com.coveiot.sdk.ble.utils.BleUtils;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import com.jstyle.blesdk1860.Util.BleSDK;
import com.jstyle.blesdk1860.Util.ResolveUtil;
import com.jstyle.blesdk1860.callback.DataListener1860;
import com.jstyle.blesdk1860.constant.BleConst;
import com.jstyle.blesdk1860.constant.DeviceKey;
import com.jstyle.blesdk1860.model.MyDeviceTime;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import kotlin.text.Typography;
import org.apache.commons.codec.digest.DigestUtils;
/* loaded from: classes2.dex */
public class KH1860BleService extends Service implements DataListener1860 {
    public static final String T = KH1860BleService.class.getSimpleName();
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
    public int Q;
    public byte[] R;
    public byte[] S;
    public BluetoothManager b;
    public Clove1860BleState.BleState bluetoothConnectionStatus;
    public BluetoothAdapter c;
    public BluetoothGatt d;
    public String e;
    public ConnectionType f;
    public Handler i;
    public Handler j;
    public Handler k;
    public BluetoothDevice mBluetoothDevice;
    public BluetoothGattCharacteristic readWriteCharacteristic;
    public Handler s;
    public Handler t;
    public Handler v;
    public HandlerThread w;

    /* renamed from: a  reason: collision with root package name */
    public final IBinder f3780a = new BtServiceBinder();
    public Handler g = new Handler();
    public final Handler h = new Handler(Looper.getMainLooper());
    public int l = 0;
    public LinkedList<CommandObject> m = new LinkedList<>();
    public int n = 0;
    public int o = 50;
    public int p = 128;
    public List<Map<String, String>> q = new ArrayList();
    public boolean r = false;
    public Queue<byte[]> u = new LinkedList();
    public final Runnable x = new Runnable() { // from class: com.coveiot.android.bleabstract.services.KH1860BleService.1
        @Override // java.lang.Runnable
        public void run() {
            if (KH1860BleService.this.bluetoothConnectionStatus == Clove1860BleState.BleState.SCANNING) {
                LogHelper.d(KH1860BleService.T, "restart initiated", ModuleNames.BLEABSTRACT.getModuleName());
                BleApiManager.getInstance(KH1860BleService.this).getBleApi().restartService();
            }
        }
    };
    public final Runnable y = new Runnable() { // from class: com.coveiot.android.bleabstract.services.KH1860BleService.5
        @Override // java.lang.Runnable
        public void run() {
            LinkedList<CommandObject> linkedList = KH1860BleService.this.m;
            if (linkedList != null && linkedList.size() > 0) {
                KH1860BleService.this.m.clear();
            }
            BleDeviceInfo.clearInstance();
            KH1860BleService kH1860BleService = KH1860BleService.this;
            kH1860BleService.getClass();
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
            kH1860BleService.writeValue(BleSDK.SetDeviceTime(myDeviceTime));
            KH1860BleService.this.l = 0;
        }
    };
    @RequiresApi(21)
    public ScanCallback z = new ScanCallback() { // from class: com.coveiot.android.bleabstract.services.KH1860BleService.6
        @Override // android.bluetooth.le.ScanCallback
        public void onScanFailed(int i) {
            super.onScanFailed(i);
            LogHelper.e("onScanFailed", "Error code " + i, ModuleNames.BLEABSTRACT.getModuleName());
            if (i != 1) {
                if (i == 2) {
                    BleApiManager.getInstance(KH1860BleService.this).getBleApi().restartService();
                    return;
                }
                KH1860BleService kH1860BleService = KH1860BleService.this;
                Clove1860BleState.BleState bleState = Clove1860BleState.BleState.DISCONNECTED;
                String str = KH1860BleService.T;
                kH1860BleService.a(bleState, true);
            }
        }

        @Override // android.bluetooth.le.ScanCallback
        public void onScanResult(int i, ScanResult scanResult) {
            super.onScanResult(i, scanResult);
            try {
                if (Build.VERSION.SDK_INT < 21 || !scanResult.getDevice().getAddress().equalsIgnoreCase(KH1860BleService.this.e)) {
                    return;
                }
                String str = KH1860BleService.T;
                ModuleNames moduleNames = ModuleNames.BLEABSTRACT;
                LogHelper.d(str, "Scan stopped, device found", moduleNames.getModuleName());
                KH1860BleService.this.stopScan();
                KH1860BleService.this.a(Clove1860BleState.BleState.CONNECTING, true);
                Handler handler = KH1860BleService.this.i;
                if (handler != null) {
                    handler.removeCallbacks(null);
                }
                LogHelper.d(str, "connect to device after scan", moduleNames.getModuleName());
                KH1860BleService.this.a(scanResult.getDevice());
            } catch (Exception e) {
                LogHelper.e(KH1860BleService.T, e.toString(), ModuleNames.BLEABSTRACT.getModuleName());
            }
        }
    };
    public final BroadcastReceiver A = new BroadcastReceiver() { // from class: com.coveiot.android.bleabstract.services.KH1860BleService.7
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("android.bluetooth.adapter.action.STATE_CHANGED")) {
                int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE);
                if (intExtra == 3 || intExtra == 10) {
                    LogHelper.d(KH1860BleService.T, "Bluetooth state BluetoothAdapter.STATE_OFF", ModuleNames.BLEABSTRACT.getModuleName());
                    KH1860BleService kH1860BleService = KH1860BleService.this;
                    Clove1860BleState.BleState bleState = Clove1860BleState.BleState.DISCONNECTED;
                    kH1860BleService.a(bleState, true);
                    KH1860BleService.this.stopScan();
                    KH1860BleService.this.closeGattConnection();
                    KH1860BleService.this.clearAllServiceParameters();
                    BleEventBusManager.getInstance().getEventBus().post(new Clove1860BleState(bleState));
                } else if (intExtra != 12) {
                } else {
                    KH1860BleService.this.stopScan();
                    KH1860BleService.this.clearAllServiceParameters();
                    KH1860BleService.this.initServiceParams();
                    String str = KH1860BleService.T;
                    ModuleNames moduleNames = ModuleNames.BLEABSTRACT;
                    LogHelper.d(str, "Bluetooth state BluetoothAdapter.STATE_ON", moduleNames.getModuleName());
                    if (!PreferenceManager1860.getInstance(KH1860BleService.this.getApplicationContext()).isBandUnpaired().booleanValue()) {
                        KH1860BleService kH1860BleService2 = KH1860BleService.this;
                        kH1860BleService2.e = PreferenceManager1860.getInstance(kH1860BleService2.getApplicationContext()).getConnectedDeviceMacAddress();
                        KH1860BleService kH1860BleService3 = KH1860BleService.this;
                        kH1860BleService3.f = BleUtils.getConnectionType(kH1860BleService3.getApplicationContext());
                        if (KH1860BleService.this.c.isEnabled()) {
                            KH1860BleService.this.closeGattConnection();
                            KH1860BleService.this.a(Clove1860BleState.BleState.CONNECTING, true);
                            KH1860BleService.this.reconnectToConfiguredDevice();
                            return;
                        }
                        return;
                    }
                    LogHelper.d(str, "BLUETOOTH TURNED ON buT IS_BAND_UNPAIRED IS false", moduleNames.getModuleName());
                }
            } else if (action.equalsIgnoreCase("action_stop_service")) {
                try {
                    KH1860BleService.this.closeGattConnection();
                    LogHelper.d(KH1860BleService.T, "ACTION_STOP_SERVICE", ModuleNames.BLEABSTRACT.getModuleName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                KH1860BleService kH1860BleService4 = KH1860BleService.this;
                Clove1860BleState.BleState bleState2 = Clove1860BleState.BleState.DISCONNECTED;
                String str2 = KH1860BleService.T;
                kH1860BleService4.a(bleState2, true);
                KH1860BleService.this.clearAllServiceParameters();
            }
        }
    };
    public long C = -1;
    public BluetoothGattCallback D = new BluetoothGattCallback() { // from class: com.coveiot.android.bleabstract.services.KH1860BleService.8
        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            super.onCharacteristicChanged(bluetoothGatt, bluetoothGattCharacteristic);
            if (KH1860BleService.this.d == null) {
                return;
            }
            String str = KH1860BleService.T;
            LogHelper.i(str, "onCharacteristicChanged: " + BleApiUtils.byte2Hex(bluetoothGattCharacteristic.getValue()), ModuleNames.BLEABSTRACT.getModuleName());
            KH1860BleService.a(KH1860BleService.this, bluetoothGattCharacteristic.getValue());
            KH1860BleService kH1860BleService = KH1860BleService.this;
            kH1860BleService.getClass();
            BleSDK.DataParsingWithData(bluetoothGattCharacteristic.getValue(), kH1860BleService);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            super.onCharacteristicRead(bluetoothGatt, bluetoothGattCharacteristic, i);
            if (i == 0) {
                KH1860BleService.a(KH1860BleService.this, bluetoothGattCharacteristic.getValue());
                return;
            }
            String str = KH1860BleService.T;
            LogHelper.e(str, "onCharacteristicRead false " + i + bluetoothGattCharacteristic.toString());
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            super.onCharacteristicWrite(bluetoothGatt, bluetoothGattCharacteristic, i);
            if (i == 0) {
                KH1860BleService.this.nextQueue();
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onConnectionStateChange(final BluetoothGatt bluetoothGatt, int i, int i2) {
            KH1860BleService kH1860BleService = KH1860BleService.this;
            kH1860BleService.f = BleUtils.getConnectionType(kH1860BleService.getApplicationContext());
            KH1860BleService.this.C = System.currentTimeMillis();
            if (i == 0) {
                if (i2 == 2) {
                    LogHelper.d(KH1860BleService.T, "onConnectionStateChange: Connected", ModuleNames.BLEABSTRACT.getModuleName());
                    KH1860BleService kH1860BleService2 = KH1860BleService.this;
                    kH1860BleService2.B = null;
                    kH1860BleService2.t.postDelayed(new Runnable() { // from class: com.coveiot.android.bleabstract.services.KH1860BleService.8.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(KH1860BleService.this, "android.permission.BLUETOOTH_CONNECT") == 0) {
                                bluetoothGatt.discoverServices();
                            }
                        }
                    }, 1000L);
                    return;
                } else if (i2 == 0) {
                    KH1860BleService.this.B = new ConnectionError(i, System.currentTimeMillis());
                    String str = KH1860BleService.T;
                    ModuleNames moduleNames = ModuleNames.BLEABSTRACT;
                    LogHelper.d(str, "onConnectionStateChange: Disconnected", moduleNames.getModuleName());
                    LinkedList<CommandObject> linkedList = KH1860BleService.this.m;
                    if (linkedList != null && linkedList.size() > 0) {
                        KH1860BleService.this.m.clear();
                    }
                    KH1860BleService.this.a(Clove1860BleState.BleState.DISCONNECTED, true);
                    KH1860BleService kH1860BleService3 = KH1860BleService.this;
                    if (kH1860BleService3.f == ConnectionType.RECONNECT_ON_DISCONNECT) {
                        LogHelper.d(str, "Initiating reconnect", moduleNames.getModuleName());
                        KH1860BleService.this.reconnectToConfiguredDevice();
                    } else {
                        kH1860BleService3.closeGattConnection();
                    }
                    KH1860BleService.this.u.clear();
                    return;
                } else {
                    return;
                }
            }
            KH1860BleService.this.B = new ConnectionError(i, System.currentTimeMillis());
            String str2 = KH1860BleService.T;
            ModuleNames moduleNames2 = ModuleNames.BLEABSTRACT;
            LogHelper.e(str2, "onConnectionStateChange Error GATT.  Error id : " + i, moduleNames2.getModuleName());
            if (i == 133) {
                if (KH1860BleService.this.f == ConnectionType.RECONNECT_ON_DISCONNECT) {
                    LogHelper.d(str2, "ConnectionType is ++ " + KH1860BleService.this.f, moduleNames2.getModuleName());
                    KH1860BleService.this.l = 0;
                    LogHelper.d(str2, "133 error appeared 1 time  disconnect event and scan device", moduleNames2.getModuleName());
                    KH1860BleService.this.a(Clove1860BleState.BleState.DISCONNECTED, true);
                    KH1860BleService.this.reconnectToConfiguredDevice();
                    return;
                }
                LogHelper.d(str2, "ConnectionType is ++ " + KH1860BleService.this.f, moduleNames2.getModuleName());
                KH1860BleService kH1860BleService4 = KH1860BleService.this;
                kH1860BleService4.l = 0;
                kH1860BleService4.a(Clove1860BleState.BleState.DISCONNECTED, true);
                return;
            }
            LogHelper.d(str2, " onConnectionStateChange error code " + i, moduleNames2.getModuleName());
            KH1860BleService kH1860BleService5 = KH1860BleService.this;
            if (kH1860BleService5.f == ConnectionType.RECONNECT_ON_DISCONNECT) {
                int i3 = kH1860BleService5.l + 1;
                kH1860BleService5.l = i3;
                if (i3 <= 2) {
                    kH1860BleService5.a(Clove1860BleState.BleState.DISCONNECTED, true);
                    KH1860BleService.this.reconnectToConfiguredDevice();
                    return;
                }
                LogHelper.d(str2, "257 or 8 error appeared 1 time  disconnect event and scan device", moduleNames2.getModuleName());
                KH1860BleService.this.a(Clove1860BleState.BleState.DISCONNECTED, true);
                KH1860BleService.this.closeGattConnection();
                if (i == 257) {
                    BleApiManager.getInstance(KH1860BleService.this).getBleApi().restartService();
                    return;
                }
                return;
            }
            LogHelper.d(str2, "connectype is ++ " + KH1860BleService.this.f, moduleNames2.getModuleName());
            KH1860BleService kH1860BleService6 = KH1860BleService.this;
            kH1860BleService6.l = 0;
            kH1860BleService6.closeGattConnection();
            KH1860BleService.this.a(Clove1860BleState.BleState.DISCONNECTED, true);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorRead(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            super.onDescriptorRead(bluetoothGatt, bluetoothGattDescriptor, i);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            super.onDescriptorWrite(bluetoothGatt, bluetoothGattDescriptor, i);
            if (i == 0) {
                KH1860BleService.this.writeValue(BleSDK.GetDeviceTime());
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onMtuChanged(BluetoothGatt bluetoothGatt, int i, int i2) {
            super.onMtuChanged(bluetoothGatt, i, i2);
            String str = KH1860BleService.T;
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
            String str = KH1860BleService.T;
            ModuleNames moduleNames = ModuleNames.BLEABSTRACT;
            LogHelper.e(str, i + "", moduleNames.getModuleName());
            if (i == 0) {
                LogHelper.d(str, "service discovered method", moduleNames.getModuleName());
                KH1860BleService kH1860BleService = KH1860BleService.this;
                kH1860BleService.d = bluetoothGatt;
                LogHelper.e(str, "initialize ble services", moduleNames.getModuleName());
                if (bluetoothGatt.getServices() != null && bluetoothGatt.getServices().size() != 0) {
                    kH1860BleService.readWriteCharacteristic = null;
                    kH1860BleService.setCharacteristicNotification(true);
                    if (kH1860BleService.readWriteCharacteristic == null) {
                        LogHelper.d(str, " read  or write characteristic is null", moduleNames.getModuleName());
                        kH1860BleService.stopScan();
                        kH1860BleService.closeGattConnection();
                        kH1860BleService.a(Clove1860BleState.BleState.DISCONNECTED, true);
                        kH1860BleService.reconnectToConfiguredDevice();
                    } else {
                        kH1860BleService.a(Clove1860BleState.BleState.CONNECTED, true);
                        kH1860BleService.g.removeCallbacks(null);
                        kH1860BleService.g.postDelayed(kH1860BleService.y, Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS);
                    }
                } else {
                    LogHelper.d(str, " gatt service is null or empty", moduleNames.getModuleName());
                    kH1860BleService.stopScan();
                    kH1860BleService.closeGattConnection();
                    kH1860BleService.a(Clove1860BleState.BleState.DISCONNECTED, true);
                    kH1860BleService.reconnectToConfiguredDevice();
                }
                KH1860BleService.this.s.post(new Runnable() { // from class: com.coveiot.android.bleabstract.services.KH1860BleService.8.2
                    @Override // java.lang.Runnable
                    public void run() {
                        if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(KH1860BleService.this, "android.permission.BLUETOOTH_CONNECT") == 0) {
                            bluetoothGatt.requestMtu(512);
                        }
                    }
                });
                return;
            }
            BleApiManager.getInstance(KH1860BleService.this).getBleApi().restartService();
        }
    };
    public int P = 200;

    /* loaded from: classes2.dex */
    public class BtServiceBinder extends Binder {
        public BtServiceBinder() {
        }

        public KH1860BleService getService() {
            return KH1860BleService.this;
        }
    }

    /* loaded from: classes2.dex */
    public class PPGPostRunnable implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public int f3795a;
        public String b;

        public PPGPostRunnable(KH1860BleService kH1860BleService, int i, String str) {
            this.f3795a = i;
            this.b = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            RawPPGData rawPPGData = new RawPPGData(this.f3795a);
            JstyleLiveResponse jstyleLiveResponse = new JstyleLiveResponse();
            jstyleLiveResponse.setDataType(this.b);
            jstyleLiveResponse.setObj(rawPPGData);
            BleEventBusManager.getInstance().getEventBus().post(jstyleLiveResponse);
        }
    }

    /* loaded from: classes2.dex */
    public class PPGRunnable implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public ArrayList<Map<String, Integer>> f3796a;
        public String b;

        public PPGRunnable(ArrayList<Map<String, Integer>> arrayList, String str) {
            this.f3796a = arrayList;
            this.b = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            Iterator<Map<String, Integer>> it = this.f3796a.iterator();
            while (it.hasNext()) {
                Map<String, Integer> next = it.next();
                if (this.f3796a.size() > 1) {
                    int intValue = Integer.valueOf(next.get(DeviceKey.arrayPpgRawData).intValue()).intValue();
                    KH1860BleService kH1860BleService = KH1860BleService.this;
                    kH1860BleService.g.post(new PPGPostRunnable(kH1860BleService, intValue, this.b));
                }
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

    public static void a(KH1860BleService kH1860BleService, byte[] bArr) {
        kH1860BleService.getClass();
        byte b = bArr[0];
        if (b != 65) {
            switch (b) {
                case -110:
                    if (bArr[1] == 1) {
                        kH1860BleService.d(FileType.AGPS_UPDATE.name());
                        return;
                    } else if (bArr[1] == 2) {
                        kH1860BleService.J = 0;
                        kH1860BleService.M = 0;
                        kH1860BleService.K = 0;
                        kH1860BleService.L = 0;
                        if (bArr[2] == 0) {
                            LogHelper.d("Upload Status", "<><><>Upload Failed");
                            return;
                        } else if (bArr[2] == 1) {
                            LogHelper.d("Upload Status", "<><><>Upload Success");
                            return;
                        } else {
                            return;
                        }
                    } else if (bArr[1] == 3) {
                        kH1860BleService.J = 0;
                        kH1860BleService.M = 0;
                        kH1860BleService.K = 0;
                        kH1860BleService.L = 0;
                        LocalBroadcastManager.getInstance(kH1860BleService).sendBroadcast(new Intent(com.coveiot.android.bleabstract.Constants.AGPS_UPDATED_BROADCAST_INTENT));
                        PreferenceManager1860.getInstance(kH1860BleService.getApplicationContext()).saveAgpsFileLastUpdatedDate(JStyleUtils.getCurrentDate());
                        LogHelper.i(T, "resolveData: crc detection");
                        return;
                    } else if (bArr[1] == 16) {
                        LogHelper.i(T, "resolveData: time out");
                        return;
                    } else {
                        return;
                    }
                case -109:
                    HashMap<Integer, byte[]> hashMap = kH1860BleService.G;
                    if (hashMap == null) {
                        return;
                    }
                    if (bArr[1] == 1) {
                        kH1860BleService.N = false;
                        int i = kH1860BleService.J + 1;
                        kH1860BleService.J = i;
                        kH1860BleService.I = hashMap.get(Integer.valueOf(i));
                        kH1860BleService.d();
                        byte[] bArr2 = kH1860BleService.I;
                        if (bArr2 == null) {
                            kH1860BleService.a(FileType.AGPS_UPDATE.name());
                            return;
                        }
                        kH1860BleService.O = CalcCRC16(bArr2, bArr2.length);
                        kH1860BleService.d(FileType.AGPS_UPDATE.name());
                        return;
                    } else if (bArr[1] == 0) {
                        kH1860BleService.N = false;
                        byte[] bArr3 = hashMap.get(Integer.valueOf(kH1860BleService.J));
                        kH1860BleService.I = bArr3;
                        if (bArr3 == null) {
                            kH1860BleService.a(FileType.AGPS_UPDATE.name());
                            return;
                        }
                        kH1860BleService.O = CalcCRC16(bArr3, bArr3.length);
                        kH1860BleService.L -= kH1860BleService.I.length;
                        kH1860BleService.d(FileType.AGPS_UPDATE.name());
                        return;
                    } else {
                        return;
                    }
                case -108:
                    if (bArr[1] == 1) {
                        if (bArr[2] == 1) {
                            kH1860BleService.J = 0;
                            kH1860BleService.M = 0;
                            kH1860BleService.K = 0;
                            kH1860BleService.L = 0;
                            kH1860BleService.sendCommandToBand(kH1860BleService.E.getCommandBytes());
                            if (kH1860BleService.E != null) {
                                JstyleBaseRes jstyleBaseRes = new JstyleBaseRes();
                                jstyleBaseRes.setBaseReq(kH1860BleService.E);
                                kH1860BleService.E.getResponseListener().onResponse(jstyleBaseRes);
                            }
                            LogHelper.i(T, "resolveData: No need to upgrade");
                            return;
                        } else if (bArr[2] == 0) {
                            kH1860BleService.c();
                            return;
                        } else if (bArr[2] == 2) {
                            LogHelper.i(T, "resolveData: Need to upgrade the firmware file first");
                            return;
                        } else {
                            return;
                        }
                    } else if (bArr[1] == 2) {
                        if (bArr[2] == 0) {
                            LogHelper.i(T, "resolveData: upgrade unsuccessful");
                            return;
                        } else if (bArr[2] == 1) {
                            kH1860BleService.J = 0;
                            kH1860BleService.M = 0;
                            kH1860BleService.K = 0;
                            kH1860BleService.L = 0;
                            kH1860BleService.sendCommandToBand(kH1860BleService.E.getCommandBytes());
                            if (kH1860BleService.E != null) {
                                JstyleBaseRes jstyleBaseRes2 = new JstyleBaseRes();
                                jstyleBaseRes2.setBaseReq(kH1860BleService.E);
                                kH1860BleService.E.getResponseListener().onResponse(jstyleBaseRes2);
                            }
                            LogHelper.i(T, "resolveData: update successed");
                            return;
                        } else {
                            return;
                        }
                    } else if (bArr[1] == 3) {
                        LogHelper.i(T, "resolveData: crc detection");
                        return;
                    } else if (bArr[1] == 16) {
                        LogHelper.i(T, "resolveData: time out");
                        return;
                    } else if (bArr[1] == 112) {
                        kH1860BleService.writeValue(kH1860BleService.R);
                        return;
                    } else {
                        return;
                    }
                case -107:
                    if (bArr[1] == 1) {
                        kH1860BleService.N = false;
                        int i2 = kH1860BleService.J + 1;
                        kH1860BleService.J = i2;
                        byte[] bArr4 = kH1860BleService.G.get(Integer.valueOf(i2));
                        if (bArr4 != null) {
                            kH1860BleService.Q = JstyleResolveData.CalcCRC16(bArr4, bArr4.length);
                        }
                        kH1860BleService.I = bArr4;
                        kH1860BleService.d();
                        if (kH1860BleService.I == null) {
                            kH1860BleService.writeValue(kH1860BleService.S);
                            return;
                        } else {
                            kH1860BleService.c();
                            return;
                        }
                    } else if (bArr[1] == 0) {
                        LogHelper.i(T, "resolveData: resend" + kH1860BleService.J);
                        kH1860BleService.N = false;
                        byte[] bArr5 = kH1860BleService.G.get(Integer.valueOf(kH1860BleService.J));
                        kH1860BleService.I = bArr5;
                        if (bArr5 == null) {
                            return;
                        }
                        kH1860BleService.Q = JstyleResolveData.CalcCRC16(bArr5, bArr5.length);
                        kH1860BleService.L -= kH1860BleService.I.length;
                        kH1860BleService.c();
                        return;
                    } else {
                        return;
                    }
                case -106:
                    if (bArr[1] == 1) {
                        kH1860BleService.d(FileType.DIY.name());
                        return;
                    } else if (bArr[1] == 2) {
                        kH1860BleService.J = 0;
                        kH1860BleService.M = 0;
                        kH1860BleService.K = 0;
                        kH1860BleService.L = 0;
                        if (bArr[2] == 0) {
                            LogHelper.d("Upload Status", "<><><>Upload Failed");
                            return;
                        } else if (bArr[2] == 1) {
                            JstyleBaseReq jstyleBaseReq = kH1860BleService.E;
                            if (jstyleBaseReq != null) {
                                kH1860BleService.sendCommandToBand(jstyleBaseReq.getCommandBytes());
                                JstyleBaseRes jstyleBaseRes3 = new JstyleBaseRes();
                                jstyleBaseRes3.setBaseReq(kH1860BleService.E);
                                kH1860BleService.E.getResponseListener().onResponse(jstyleBaseRes3);
                            }
                            LogHelper.d("Upload Status", "<><><>Upload Success");
                            return;
                        } else {
                            return;
                        }
                    } else if (bArr[1] == 3) {
                        LogHelper.i(T, "resolveData: crc detection");
                        return;
                    } else if (bArr[1] == 16) {
                        LogHelper.i(T, "resolveData: time out");
                        return;
                    } else {
                        return;
                    }
                case -105:
                    HashMap<Integer, byte[]> hashMap2 = kH1860BleService.G;
                    if (hashMap2 == null) {
                        return;
                    }
                    if (bArr[1] == 1) {
                        kH1860BleService.N = false;
                        int i3 = kH1860BleService.J + 1;
                        kH1860BleService.J = i3;
                        kH1860BleService.I = hashMap2.get(Integer.valueOf(i3));
                        kH1860BleService.d();
                        byte[] bArr6 = kH1860BleService.I;
                        if (bArr6 == null) {
                            kH1860BleService.a(FileType.DIY.name());
                            return;
                        }
                        kH1860BleService.O = CalcCRC16(bArr6, bArr6.length);
                        kH1860BleService.d(FileType.DIY.name());
                        return;
                    } else if (bArr[1] == 0) {
                        kH1860BleService.N = false;
                        byte[] bArr7 = hashMap2.get(Integer.valueOf(kH1860BleService.J));
                        kH1860BleService.I = bArr7;
                        if (bArr7 == null) {
                            kH1860BleService.a(FileType.DIY.name());
                            return;
                        }
                        kH1860BleService.O = CalcCRC16(bArr7, bArr7.length);
                        kH1860BleService.L -= kH1860BleService.I.length;
                        kH1860BleService.d(FileType.DIY.name());
                        return;
                    } else {
                        return;
                    }
                default:
                    return;
            }
        }
        String gpsTime = getGpsTime(bArr);
        kH1860BleService.P = getValue(bArr[8], 0);
        LogHelper.d("gpsDate---maxLength", "<><><>" + gpsTime + "---" + kH1860BleService.P);
        JstyleLiveResponse jstyleLiveResponse = new JstyleLiveResponse();
        jstyleLiveResponse.setDataType(JStyleConstants.GET_GPS_TIME);
        jstyleLiveResponse.setObj(gpsTime);
        BleEventBusManager.getInstance().getEventBus().post(jstyleLiveResponse);
    }

    public static short dataFromRGB(byte b, byte b2, byte b3) {
        return (short) (((short) ((((char) (b & 248)) + ((char) (b2 >> 5))) * 256)) + ((short) (((char) ((b2 << 3) & 224)) + ((char) (b3 >> 3)))));
    }

    public static synchronized long getDateLong(String str) {
        long j;
        synchronized (KH1860BleService.class) {
            j = 0;
            try {
                j = new SimpleDateFormat("yy.MM.dd").parse(str).getTime();
            } catch (Exception unused) {
            }
        }
        return j;
    }

    public static String getGpsTime(byte[] bArr) {
        return ResolveUtil.bcd2String(bArr[9]) + "." + ResolveUtil.bcd2String(bArr[10]) + "." + ResolveUtil.bcd2String(bArr[11]);
    }

    public static int getValue(byte b, int i) {
        return (int) ((b & 255) * Math.pow(256.0d, i));
    }

    public final void b() {
        BluetoothAdapter bluetoothAdapter = this.c;
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
            return;
        }
        reconnectToConfiguredDevice();
    }

    public final byte c(String str) {
        if (str == FileType.AGPS_UPDATE.name()) {
            return (byte) -109;
        }
        if (str == FileType.DIY.name()) {
            return BleUUID.CMD_ID_97;
        }
        return (byte) 0;
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
        Handler handler5 = this.s;
        if (handler5 != null) {
            handler5.removeCallbacksAndMessages(null);
        }
        Handler handler6 = this.t;
        if (handler6 != null) {
            handler6.removeCallbacksAndMessages(null);
        }
        a(Clove1860BleState.BleState.DISCONNECTED, false);
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
        List<Map<String, String>> list = this.q;
        if (list == null || list.size() <= 0) {
            return;
        }
        this.q.clear();
    }

    public void closeGattConnection() {
        if (this.d == null) {
            return;
        }
        String str = T;
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
        PreferenceManager1860.getInstance(getApplicationContext()).saveConnectedDeviceMAcAddress(str);
        PreferenceManager1860.getInstance(getApplicationContext()).saveConnectionType(ConnectionType.RECONNECT_ON_DISCONNECT.name());
        String str2 = T;
        LogHelper.d(str2, "connection type ++ " + BleUtils.getConnectionType(this), ModuleNames.BLEABSTRACT.getModuleName());
        b();
    }

    public final void d(String str) {
        int i;
        boolean z;
        byte[] bArr = this.F;
        if (bArr == null || bArr.length == 0 || (i = this.P) == 0 || (z = this.N)) {
            return;
        }
        byte[] bArr2 = this.I;
        if (bArr2 == null) {
            a(str);
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
        bArr3[0] = c(str);
        bArr3[1] = (byte) this.J;
        int i7 = this.K;
        this.K = i7 + 1;
        bArr3[2] = (byte) i7;
        System.arraycopy(this.F, this.L, bArr3, 3, i2);
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
            byte[] bArr4 = {c(str), (byte) i10, -1, (byte) (i11 & 255), (byte) ((i11 >> 8) & 255), (byte) (CalcCRC162 & 255), (byte) ((CalcCRC162 >> 8) & 255)};
            offerValue(bArr4);
            nextQueue();
            return;
        }
        d(str);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Type inference failed for: r1v107 */
    /* JADX WARN: Type inference failed for: r1v108, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r1v111 */
    @Override // com.jstyle.blesdk1860.callback.DataListener1860
    public void dataCallback(Map<String, Object> map) {
        char c;
        char c2;
        String str;
        JstyleBaseRes jstyleBaseRes;
        ?? r1;
        String str2 = (String) map.get(DeviceKey.DataType);
        boolean booleanValue = ((Boolean) map.get(DeviceKey.End)).booleanValue();
        JstyleBaseRes jstyleBaseRes2 = new JstyleBaseRes();
        String str3 = T;
        ModuleNames moduleNames = ModuleNames.BLEABSTRACT;
        LogHelper.i(str3, "dataCallback: " + str2 + " -- " + booleanValue, moduleNames.getModuleName());
        if (this.E != null) {
            LogHelper.d("1860 khCurrentCommand", " dataCallback -- " + this.E.getDataType(), moduleNames.getModuleName());
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
        } else if (hashCode == 1598) {
            if (str2.equals(BleConst.SetAlarmClockWithAllClock)) {
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
        } else if (hashCode == 1723) {
            if (str2.equals(BleConst.BloodOxygen_PPG)) {
                c = 17;
                c2 = c;
            }
            c2 = 65535;
        } else if (hashCode == 1600) {
            if (str2.equals(BleConst.SetSedentaryReminder)) {
                c = 5;
                c2 = c;
            }
            c2 = 65535;
        } else if (hashCode == 1601) {
            if (str2.equals(BleConst.RealTimeStep)) {
                c = 6;
                c2 = c;
            }
            c2 = 65535;
        } else if (hashCode == 1661) {
            if (str2.equals(BleConst.Gps)) {
                c = '\r';
                c2 = c;
            }
            c2 = 65535;
        } else if (hashCode == 1662) {
            if (str2.equals("42")) {
                c = 14;
                c2 = c;
            }
            c2 = 65535;
        } else if (hashCode == 1697) {
            if (str2.equals(BleConst.Blood_oxygen)) {
                c = 15;
                c2 = c;
            }
            c2 = 65535;
        } else if (hashCode != 1698) {
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
            if (str2.equals(BleConst.Getppg)) {
                c = 16;
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
                this.p = Integer.parseInt(str);
                LogHelper.d(str3, "KPhoneDataLength == " + this.p, moduleNames.getModuleName());
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
                    jstyleBaseRes2.setBaseReq(this.E);
                    jstyleBaseRes2.obj = data3;
                    this.E.getResponseListener().onResponse(jstyleBaseRes2);
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
                jstyleBaseRes2.setBaseReq(this.E);
                jstyleBaseRes2.obj = data5;
                this.E.getResponseListener().onResponse(jstyleBaseRes2);
                return;
            case 4:
                LogHelper.d(str3, "SetAlarmClockWithAllClock", moduleNames.getModuleName());
                JstyleBaseReq jstyleBaseReq3 = this.E;
                if (jstyleBaseReq3 == null || !jstyleBaseReq3.getDataType().equalsIgnoreCase(str2)) {
                    return;
                }
                jstyleBaseRes2.setBaseReq(this.E);
                this.E.getResponseListener().onResponse(jstyleBaseRes2);
                return;
            case 5:
                LogHelper.d(str3, "SetSedentaryReminder", moduleNames.getModuleName());
                JstyleBaseReq jstyleBaseReq4 = this.E;
                if (jstyleBaseReq4 == null || !jstyleBaseReq4.getDataType().equalsIgnoreCase(str2)) {
                    return;
                }
                jstyleBaseRes2.setBaseReq(this.E);
                this.E.getResponseListener().onResponse(jstyleBaseRes2);
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
                        this.q.addAll((List) map.get(DeviceKey.Data));
                        this.n++;
                        if (booleanValue) {
                            this.n = 0;
                            jstyleBaseRes2.setBaseReq(this.E);
                            jstyleBaseRes2.obj = this.q;
                            this.E.getResponseListener().onResponse(jstyleBaseRes2);
                            this.r = false;
                        }
                        if (this.n == this.o) {
                            this.n = 0;
                            if (booleanValue) {
                                jstyleBaseRes2.setBaseReq(this.E);
                                this.n = 0;
                                jstyleBaseRes2.obj = this.q;
                                this.E.getResponseListener().onResponse(jstyleBaseRes2);
                                this.r = false;
                                return;
                            }
                            this.r = true;
                            JstyleBaseReq jstyleWalkReq = new JstyleWalkReq(Calendar.getInstance(), JStyleConstants.Constants.getMODE_CONTINUE());
                            jstyleWalkReq.setDataType(this.E.getDataType());
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
                this.q.addAll((List) map.get(DeviceKey.Data));
                this.n++;
                if (booleanValue) {
                    this.n = 0;
                    jstyleBaseRes2.setBaseReq(this.E);
                    jstyleBaseRes2.obj = this.q;
                    this.E.getResponseListener().onResponse(jstyleBaseRes2);
                    this.r = false;
                }
                if (this.n == this.o) {
                    this.n = 0;
                    if (booleanValue) {
                        this.n = 0;
                        jstyleBaseRes2.setBaseReq(this.E);
                        jstyleBaseRes2.obj = this.q;
                        this.E.getResponseListener().onResponse(jstyleBaseRes2);
                        this.r = false;
                        return;
                    }
                    this.r = true;
                    JstyleBaseReq jstyleSleepReq = new JstyleSleepReq(Calendar.getInstance(), JStyleConstants.Constants.getMODE_CONTINUE());
                    jstyleSleepReq.setDataType(BleConst.GetDetailSleepData);
                    jstyleSleepReq.setReqId(this.E.getReqId());
                    sendRequest(jstyleSleepReq, this.E.getResponseListener());
                    return;
                }
                return;
            case '\t':
                JstyleBaseReq jstyleBaseReq7 = this.E;
                if (jstyleBaseReq7 == null || !jstyleBaseReq7.getDataType().equalsIgnoreCase(str2)) {
                    return;
                }
                LogHelper.d("GetDynamicHR", "" + map.get(DeviceKey.Data));
                this.q.addAll((List) map.get(DeviceKey.Data));
                this.n = this.n + 1;
                if (booleanValue) {
                    this.n = 0;
                    jstyleBaseRes2.setBaseReq(this.E);
                    jstyleBaseRes2.obj = this.q;
                    this.E.getResponseListener().onResponse(jstyleBaseRes2);
                    this.r = false;
                }
                if (this.n == this.o) {
                    this.n = 0;
                    if (booleanValue) {
                        this.n = 0;
                        jstyleBaseRes2.setBaseReq(this.E);
                        jstyleBaseRes2.obj = this.q;
                        this.E.getResponseListener().onResponse(jstyleBaseRes2);
                        this.r = false;
                        return;
                    }
                    this.r = true;
                    JstyleBaseReq jstyleHistoryHRReq = new JstyleHistoryHRReq(Calendar.getInstance(), JStyleConstants.Constants.getMODE_CONTINUE());
                    jstyleHistoryHRReq.setDataType(BleConst.GetDynamicHR);
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
                this.q.addAll((List) map.get(DeviceKey.Data));
                this.n++;
                if (booleanValue) {
                    this.n = 0;
                    jstyleBaseRes2.setBaseReq(this.E);
                    jstyleBaseRes2.obj = this.q;
                    this.E.getResponseListener().onResponse(jstyleBaseRes2);
                    this.r = false;
                }
                if (this.n == this.o) {
                    this.n = 0;
                    if (booleanValue) {
                        this.n = 0;
                        jstyleBaseRes2.setBaseReq(this.E);
                        jstyleBaseRes2.obj = this.q;
                        this.E.getResponseListener().onResponse(jstyleBaseRes2);
                        this.r = false;
                        return;
                    }
                    this.r = true;
                    JstyleBaseReq jstyleOnceHRReq = new JstyleOnceHRReq(Calendar.getInstance(), JStyleConstants.Constants.getMODE_CONTINUE());
                    jstyleOnceHRReq.setDataType(BleConst.GetStaticHR);
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
                this.q.addAll((List) map.get(DeviceKey.Data));
                this.n++;
                if (booleanValue) {
                    this.n = 0;
                    jstyleBaseRes2.setBaseReq(this.E);
                    jstyleBaseRes2.obj = this.q;
                    this.E.getResponseListener().onResponse(jstyleBaseRes2);
                    this.r = false;
                }
                if (this.n == this.o) {
                    this.n = 0;
                    if (booleanValue) {
                        this.n = 0;
                        jstyleBaseRes2.setBaseReq(this.E);
                        jstyleBaseRes2.obj = this.q;
                        this.E.getResponseListener().onResponse(jstyleBaseRes2);
                        this.r = false;
                        return;
                    }
                    this.r = true;
                    JstyleBaseReq jstyleActivityModeHistoryReq = new JstyleActivityModeHistoryReq(Calendar.getInstance(), JStyleConstants.Constants.getMODE_CONTINUE());
                    jstyleActivityModeHistoryReq.setDataType(BleConst.GetActivityModeData);
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
                    LogHelper.d(str3, "DeviceSendDataToAPP- data2 -- " + data7.toString(), moduleNames.getModuleName());
                    JstyleLiveResponse jstyleLiveResponse4 = new JstyleLiveResponse();
                    new LiveMusicControlRes(MusicControlState.UNKNOWN);
                    Intent intent = new Intent(com.coveiot.android.bleabstract.Constants.MUSIC_CONTROL_BROADCAST_INTENT);
                    new CameraEventRes(CameraState.EXIT);
                    Intent intent2 = new Intent(com.coveiot.android.bleabstract.Constants.CAMERA_BROADCAST_INTENT);
                    switch (parseInt) {
                        case 1:
                            jstyleLiveResponse4.setDataType("RejectTelMode");
                            jstyleLiveResponse4.setObj(Boolean.TRUE);
                            BleEventBusManager.getInstance().getEventBus().post(jstyleLiveResponse4);
                            return;
                        case 2:
                        default:
                            return;
                        case 3:
                            intent2.putExtra(com.coveiot.android.bleabstract.Constants.CAMERA_BROADCAST_INTENT_EXTRA, new CameraEventRes(CameraState.ENTER));
                            LocalBroadcastManager.getInstance(this).sendBroadcast(intent2);
                            return;
                        case 4:
                            intent2.putExtra(com.coveiot.android.bleabstract.Constants.CAMERA_BROADCAST_INTENT_EXTRA, new CameraEventRes(CameraState.CAPTURE));
                            LocalBroadcastManager.getInstance(this).sendBroadcast(intent2);
                            return;
                        case 5:
                            intent.putExtra(com.coveiot.android.bleabstract.Constants.MUSIC_CONTROL_BROADCAST_INTENT_EXTRA, new LiveMusicControlRes(MusicControlState.PREVIOUS));
                            LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
                            return;
                        case 6:
                            intent.putExtra(com.coveiot.android.bleabstract.Constants.MUSIC_CONTROL_BROADCAST_INTENT_EXTRA, new LiveMusicControlRes(MusicControlState.TOGGLE));
                            LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
                            return;
                        case 7:
                            intent.putExtra(com.coveiot.android.bleabstract.Constants.MUSIC_CONTROL_BROADCAST_INTENT_EXTRA, new LiveMusicControlRes(MusicControlState.NEXT));
                            LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
                            return;
                        case 8:
                            intent.putExtra(com.coveiot.android.bleabstract.Constants.MUSIC_CONTROL_BROADCAST_INTENT_EXTRA, new LiveMusicControlRes(MusicControlState.VOLUME_UP));
                            LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
                            return;
                        case 9:
                            intent.putExtra(com.coveiot.android.bleabstract.Constants.MUSIC_CONTROL_BROADCAST_INTENT_EXTRA, new LiveMusicControlRes(MusicControlState.VOLUME_DOWN));
                            LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
                            return;
                        case 10:
                            jstyleLiveResponse4.setDataType("FindMobilePhoneMode");
                            jstyleLiveResponse4.setObj(Boolean.TRUE);
                            BleEventBusManager.getInstance().getEventBus().post(jstyleLiveResponse4);
                            return;
                        case 11:
                            jstyleLiveResponse4.setDataType("FindMobilePhoneMode");
                            jstyleLiveResponse4.setObj(Boolean.FALSE);
                            BleEventBusManager.getInstance().getEventBus().post(jstyleLiveResponse4);
                            return;
                    }
                }
                return;
            case '\r':
                JstyleBaseReq jstyleBaseReq10 = this.E;
                if (jstyleBaseReq10 == null || !jstyleBaseReq10.getDataType().equalsIgnoreCase(str2)) {
                    return;
                }
                LogHelper.d("Gps", "" + map.get(DeviceKey.Data));
                if (map.get(DeviceKey.Data) != null) {
                    this.q.addAll((List) map.get(DeviceKey.Data));
                }
                this.n++;
                if (booleanValue) {
                    this.n = 0;
                    jstyleBaseRes2.setBaseReq(this.E);
                    jstyleBaseRes2.obj = this.q;
                    this.E.getResponseListener().onResponse(jstyleBaseRes2);
                    this.r = false;
                }
                if (this.n == this.o) {
                    this.n = 0;
                    if (booleanValue) {
                        this.n = 0;
                        jstyleBaseRes2.setBaseReq(this.E);
                        jstyleBaseRes2.obj = this.q;
                        this.E.getResponseListener().onResponse(jstyleBaseRes2);
                        this.r = false;
                        return;
                    }
                    this.r = true;
                    JstyleGPSDataReq jstyleGPSDataReq = new JstyleGPSDataReq();
                    jstyleGPSDataReq.setDataType(BleConst.Gps);
                    jstyleGPSDataReq.setReqId(this.E.getReqId());
                    jstyleGPSDataReq.setMode(JStyleConstants.Constants.getMODE_CONTINUE());
                    sendRequest(jstyleGPSDataReq, this.E.getResponseListener());
                    return;
                }
                return;
            case 14:
                JstyleBaseReq jstyleBaseReq11 = this.E;
                if (jstyleBaseReq11 == null || !jstyleBaseReq11.getDataType().equalsIgnoreCase(str2)) {
                    return;
                }
                this.q.addAll((List) map.get(DeviceKey.Data));
                this.n++;
                if (booleanValue) {
                    this.n = 0;
                    jstyleBaseRes2.setBaseReq(this.E);
                    jstyleBaseRes2.obj = this.q;
                    this.E.getResponseListener().onResponse(jstyleBaseRes2);
                    this.r = false;
                }
                if (this.n == this.o) {
                    this.n = 0;
                    if (booleanValue) {
                        jstyleBaseRes2.setBaseReq(this.E);
                        this.n = 0;
                        jstyleBaseRes2.obj = this.q;
                        this.E.getResponseListener().onResponse(jstyleBaseRes2);
                        this.r = false;
                        return;
                    }
                    this.r = true;
                    JstyleBaseReq jstyleHrvReq = new JstyleHrvReq(Calendar.getInstance(), JStyleConstants.Constants.getMODE_CONTINUE());
                    jstyleHrvReq.setDataType("42");
                    jstyleHrvReq.setReqId(this.E.getReqId());
                    sendRequest(jstyleHrvReq, this.E.getResponseListener());
                    return;
                }
                return;
            case 15:
                JstyleBaseReq jstyleBaseReq12 = this.E;
                if (jstyleBaseReq12 == null || !jstyleBaseReq12.getDataType().equalsIgnoreCase(str2)) {
                    return;
                }
                this.q.addAll((List) map.get(DeviceKey.Data));
                this.n++;
                if (booleanValue) {
                    r1 = 0;
                    this.n = 0;
                    jstyleBaseRes = jstyleBaseRes2;
                    jstyleBaseRes.setBaseReq(this.E);
                    jstyleBaseRes.obj = this.q;
                    this.E.getResponseListener().onResponse(jstyleBaseRes);
                    this.r = false;
                } else {
                    jstyleBaseRes = jstyleBaseRes2;
                    r1 = 0;
                }
                if (this.n == this.o) {
                    this.n = r1;
                    if (booleanValue) {
                        jstyleBaseRes.setBaseReq(this.E);
                        this.n = r1;
                        jstyleBaseRes.obj = this.q;
                        this.E.getResponseListener().onResponse(jstyleBaseRes);
                        this.r = r1;
                        return;
                    }
                    this.r = true;
                    JstyleBaseReq jstyleSpo2Req = new JstyleSpo2Req(Calendar.getInstance(), JStyleConstants.Constants.getMODE_CONTINUE());
                    jstyleSpo2Req.setDataType(BleConst.Blood_oxygen);
                    jstyleSpo2Req.setReqId(this.E.getReqId());
                    sendRequest(jstyleSpo2Req, this.E.getResponseListener());
                    return;
                }
                return;
            case 16:
                this.v.post(new PPGRunnable(getDataHashMapList(map), BleConst.Getppg));
                return;
            case 17:
                this.v.post(new PPGRunnable(getDataHashMapList(map), BleConst.BloodOxygen_PPG));
                return;
            default:
                return;
        }
    }

    @Override // com.jstyle.blesdk1860.callback.DataListener1860
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
        PreferenceManager1860.getInstance(getApplicationContext()).saveConnectedDeviceMAcAddress("");
        PreferenceManager1860.getInstance(getApplicationContext()).saveConnectionType(ConnectionType.DONT_CONNECT_ON_DISCONNECT.name());
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
        PreferenceManager1860.getInstance(getApplicationContext()).saveConnectionType(ConnectionType.DONT_CONNECT_ON_DISCONNECT.name());
        stopForeground(true);
        stopSelf();
    }

    public ConnectionError getConnectionError() {
        return this.B;
    }

    public long getConnectionStageChangeTimeStamp() {
        return this.C;
    }

    public Clove1860BleState.BleState getConnectionState() {
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
        b();
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
        if (this.s == null) {
            this.s = new Handler();
        }
        if (this.t == null) {
            this.t = new Handler();
        }
        a(Clove1860BleState.BleState.DISCONNECTED, false);
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
        return this.f3780a;
    }

    @Override // android.app.Service
    public void onCreate() {
        a();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        LogHelper.d(T, "Service onDestroy", ModuleNames.BLEABSTRACT.getModuleName());
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
            java.lang.Class<com.coveiot.android.bleabstract.services.KH1860BleService> r4 = com.coveiot.android.bleabstract.services.KH1860BleService.class
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
            com.coveiot.android.bleabstract.preferences.PreferenceManager1860 r2 = com.coveiot.android.bleabstract.preferences.PreferenceManager1860.getInstance(r2)
            java.lang.String r2 = r2.getConnectedDeviceMacAddress()
            r1.e = r2
            android.content.Context r2 = r1.getApplicationContext()
            com.coveiot.sdk.ble.events.ConnectionType r2 = com.coveiot.sdk.ble.utils.BleUtils.getConnectionType(r2)
            r1.f = r2
            java.lang.String r2 = com.coveiot.android.bleabstract.services.KH1860BleService.T
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
            r1.b()
        Lb7:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.bleabstract.services.KH1860BleService.onStartCommand(android.content.Intent, int, int):int");
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    public void reconnectToConfiguredDevice() {
        String str = T;
        ModuleNames moduleNames = ModuleNames.BLEABSTRACT;
        LogHelper.d(str, "reconnectToConfiguredDevice", moduleNames.getModuleName());
        closeGattConnection();
        this.e = PreferenceManager1860.getInstance(getApplicationContext()).getConnectedDeviceMacAddress();
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
        PreferenceManager1860.getInstance(getApplicationContext()).saveConnectionType(ConnectionType.RECONNECT_ON_DISCONNECT.name());
        stopForeground(true);
        stopSelf();
        LogHelper.d(T, "Service restartService", ModuleNames.BLEABSTRACT.getModuleName());
    }

    public void scanLeDevice() {
        if (!PreferenceManager1860.getInstance(getApplicationContext()).isBandUnpaired().booleanValue()) {
            this.j.removeCallbacksAndMessages(null);
            this.i.removeCallbacks(null);
            stopScan();
            this.j.postDelayed(new Runnable() { // from class: com.coveiot.android.bleabstract.services.KH1860BleService.4
                @Override // java.lang.Runnable
                @RequiresApi(api = 21)
                public void run() {
                    String str = KH1860BleService.T;
                    ModuleNames moduleNames = ModuleNames.BLEABSTRACT;
                    LogHelper.d(str, "Scanning...", moduleNames.getModuleName());
                    KH1860BleService.this.closeGattConnection();
                    try {
                        KH1860BleService kH1860BleService = KH1860BleService.this;
                        kH1860BleService.e = PreferenceManager1860.getInstance(kH1860BleService.getApplicationContext()).getConnectedDeviceMacAddress();
                        LogHelper.d(str, "BLE deviceAddress as " + KH1860BleService.this.e, moduleNames.getModuleName());
                        if (BleUtils.isEmpty(KH1860BleService.this.e)) {
                            return;
                        }
                        ScanFilter.Builder builder = new ScanFilter.Builder();
                        builder.setDeviceAddress(KH1860BleService.this.e);
                        new ArrayList().add(builder.build());
                        ScanSettings build = new ScanSettings.Builder().setScanMode(1).build();
                        KH1860BleService.this.a(Clove1860BleState.BleState.SCANNING, true);
                        KH1860BleService kH1860BleService2 = KH1860BleService.this;
                        kH1860BleService2.h.postDelayed(kH1860BleService2.x, 1800000L);
                        if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(KH1860BleService.this, "android.permission.BLUETOOTH_SCAN") == 0) {
                            KH1860BleService.this.c.getBluetoothLeScanner().startScan((List<ScanFilter>) null, build, KH1860BleService.this.z);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, 500L);
            return;
        }
        a(Clove1860BleState.BleState.DISCONNECTED, true);
    }

    public void sendCommandToBand(byte[] bArr) {
        writeValue(bArr);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public void sendRequest(final JstyleBaseReq jstyleBaseReq, JstyleResponseListener jstyleResponseListener) {
        char c;
        FileInputStream fileInputStream;
        byte[] bArr;
        ByteArrayOutputStream byteArrayOutputStream;
        final byte[] byteArray;
        jstyleBaseReq.setResponseListener(jstyleResponseListener);
        this.E = jstyleBaseReq;
        LogHelper.d("1860 khCurrentCommand", " sendRequest-- " + this.E.getDataType(), ModuleNames.BLEABSTRACT.getModuleName());
        String dataType = jstyleBaseReq.getDataType();
        if (dataType != null) {
            int hashCode = dataType.hashCode();
            if (hashCode == 56) {
                if (dataType.equals(BleConst.SetStepGoal)) {
                    c = 11;
                }
                c = 65535;
            } else if (hashCode == 57) {
                if (dataType.equals(BleConst.GetDeviceBatteryLevel)) {
                    c = '\f';
                }
                c = 65535;
            } else if (hashCode == 1600) {
                if (dataType.equals(BleConst.SetSedentaryReminder)) {
                    c = 17;
                }
                c = 65535;
            } else if (hashCode == 1601) {
                if (dataType.equals(BleConst.RealTimeStep)) {
                    c = 18;
                }
                c = 65535;
            } else if (hashCode == 1661) {
                if (dataType.equals(BleConst.Gps)) {
                    c = 24;
                }
                c = 65535;
            } else if (hashCode == 1662) {
                if (dataType.equals("42")) {
                    c = 25;
                }
                c = 65535;
            } else if (hashCode == 1697) {
                if (dataType.equals(BleConst.Blood_oxygen)) {
                    c = JSONLexer.EOI;
                }
                c = 65535;
            } else if (hashCode != 1698) {
                switch (hashCode) {
                    case -2041249468:
                        if (dataType.equals(JStyleConstants.EnterOTA)) {
                            c = 0;
                            break;
                        }
                        c = 65535;
                        break;
                    case -1710828730:
                        if (dataType.equals(JStyleConstants.DELETE_ACTIVITY_DATA)) {
                            c = 1;
                            break;
                        }
                        c = 65535;
                        break;
                    case -1710014578:
                        if (dataType.equals(JStyleConstants.DISTANCE_UNIT)) {
                            c = 2;
                            break;
                        }
                        c = 65535;
                        break;
                    case -1268524623:
                        if (dataType.equals(JStyleConstants.CLOUD_WATCH_FACE_UPDATE)) {
                            c = 3;
                            break;
                        }
                        c = 65535;
                        break;
                    case -1257575528:
                        if (dataType.equals(JStyleConstants.TempUnit)) {
                            c = 4;
                            break;
                        }
                        c = 65535;
                        break;
                    case -1039006405:
                        if (dataType.equals(JStyleConstants.WATCH_FACE_POSITION)) {
                            c = 5;
                            break;
                        }
                        c = 65535;
                        break;
                    case -704320835:
                        if (dataType.equals(JStyleConstants.DEFAULT_WATCH_FACE_UPDATE)) {
                            c = 6;
                            break;
                        }
                        c = 65535;
                        break;
                    case -516402510:
                        if (dataType.equals(JStyleConstants.HOUR_FORMAT)) {
                            c = 7;
                            break;
                        }
                        c = 65535;
                        break;
                    case 1568:
                        if (dataType.equals(BleConst.GetDeviceVersion)) {
                            c = '\r';
                            break;
                        }
                        c = 65535;
                        break;
                    case 1571:
                        if (dataType.equals(BleConst.SetMotorVibrationWithTimes)) {
                            c = 14;
                            break;
                        }
                        c = 65535;
                        break;
                    case 1575:
                        if (dataType.equals(BleConst.SetAutomaticHRMonitoring)) {
                            c = 15;
                            break;
                        }
                        c = 65535;
                        break;
                    case 1598:
                        if (dataType.equals(BleConst.SetAlarmClockWithAllClock)) {
                            c = 16;
                            break;
                        }
                        c = 65535;
                        break;
                    case 1723:
                        if (dataType.equals(BleConst.BloodOxygen_PPG)) {
                            c = 28;
                            break;
                        }
                        c = 65535;
                        break;
                    case 67700:
                        if (dataType.equals("DIY")) {
                            c = 29;
                            break;
                        }
                        c = 65535;
                        break;
                    case 759553291:
                        if (dataType.equals(JStyleConstants.Notification)) {
                            c = 30;
                            break;
                        }
                        c = 65535;
                        break;
                    case 930854267:
                        if (dataType.equals(JStyleConstants.LIFT_WRIST)) {
                            c = 31;
                            break;
                        }
                        c = 65535;
                        break;
                    case 1131756115:
                        if (dataType.equals(JStyleConstants.DELETE_GPS_DATA)) {
                            c = ' ';
                            break;
                        }
                        c = 65535;
                        break;
                    case 1309954891:
                        if (dataType.equals(JStyleConstants.SESSION_STEPS_DATA)) {
                            c = '!';
                            break;
                        }
                        c = 65535;
                        break;
                    case 1450989724:
                        if (dataType.equals(JStyleConstants.AGPS_UPDATE_FILE)) {
                            c = Typography.quote;
                            break;
                        }
                        c = 65535;
                        break;
                    default:
                        switch (hashCode) {
                            case 51:
                                if (dataType.equals("3")) {
                                    c = '\b';
                                    break;
                                }
                                c = 65535;
                                break;
                            case 52:
                                if (dataType.equals(BleConst.GetDeviceInfo)) {
                                    c = '\t';
                                    break;
                                }
                                c = 65535;
                                break;
                            case 53:
                                if (dataType.equals(BleConst.SetDeviceInfo)) {
                                    c = '\n';
                                    break;
                                }
                                c = 65535;
                                break;
                            default:
                                switch (hashCode) {
                                    case 1603:
                                        if (dataType.equals(BleConst.GetDetailActivityData)) {
                                            c = 19;
                                            break;
                                        }
                                        c = 65535;
                                        break;
                                    case 1604:
                                        if (dataType.equals(BleConst.GetDetailSleepData)) {
                                            c = 20;
                                            break;
                                        }
                                        c = 65535;
                                        break;
                                    case 1605:
                                        if (dataType.equals(BleConst.GetDynamicHR)) {
                                            c = 21;
                                            break;
                                        }
                                        c = 65535;
                                        break;
                                    case 1606:
                                        if (dataType.equals(BleConst.GetStaticHR)) {
                                            c = 22;
                                            break;
                                        }
                                        c = 65535;
                                        break;
                                    case 1607:
                                        if (dataType.equals(BleConst.GetActivityModeData)) {
                                            c = 23;
                                            break;
                                        }
                                        c = 65535;
                                        break;
                                    default:
                                        c = 65535;
                                        break;
                                }
                        }
                }
            } else {
                if (dataType.equals(BleConst.Getppg)) {
                    c = 27;
                }
                c = 65535;
            }
            switch (c) {
                case 0:
                case 1:
                case 2:
                case 4:
                case 6:
                case 7:
                case '\b':
                case '\n':
                case 11:
                case 14:
                case 15:
                case 17:
                case 18:
                case 27:
                case 28:
                case 30:
                case 31:
                case ' ':
                    sendCommandToBand(jstyleBaseReq.getCommandBytes());
                    JstyleBaseReq jstyleBaseReq2 = this.E;
                    if (jstyleBaseReq2 == null || !jstyleBaseReq2.getDataType().equalsIgnoreCase(dataType)) {
                        return;
                    }
                    JstyleBaseRes jstyleBaseRes = new JstyleBaseRes();
                    jstyleBaseRes.setBaseReq(this.E);
                    this.E.getResponseListener().onResponse(jstyleBaseRes);
                    return;
                case 3:
                    File file = new File(jstyleBaseReq.getWatchFaceFilePath());
                    String str = T;
                    LogHelper.i(str, "initByteValue: " + file.length());
                    if (file.isFile()) {
                        try {
                            fileInputStream = new FileInputStream(file);
                            bArr = new byte[1024];
                            byteArrayOutputStream = new ByteArrayOutputStream();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        while (true) {
                            int read = fileInputStream.read(bArr);
                            if (read != -1) {
                                byteArrayOutputStream.write(bArr, 0, read);
                            } else {
                                byteArray = byteArrayOutputStream.toByteArray();
                                if (!file.exists() && byteArray != null) {
                                    this.j.postDelayed(new Runnable() { // from class: com.coveiot.android.bleabstract.services.KH1860BleService.11
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            KH1860BleService kH1860BleService = KH1860BleService.this;
                                            byte[] bArr2 = byteArray;
                                            String str2 = KH1860BleService.T;
                                            kH1860BleService.getClass();
                                            JstyleResolveData.cmdCheck = (byte) -108;
                                            byte[] bArr3 = new byte[16];
                                            int length = bArr2.length - 16;
                                            byte[] bArr4 = new byte[length];
                                            kH1860BleService.F = bArr4;
                                            System.arraycopy(bArr2, 0, bArr4, 0, length);
                                            System.arraycopy(bArr2, bArr2.length - 16, bArr3, 0, 16);
                                            byte[] md5 = DigestUtils.md5(kH1860BleService.F);
                                            LogHelper.i(str2, "initByteValue: " + JstyleResolveData.byte2Hex(md5) + " File md5 " + JstyleResolveData.byte2Hex(bArr3));
                                            if (JstyleResolveData.byte2Hex(md5).equals(JstyleResolveData.byte2Hex(bArr3))) {
                                                kH1860BleService.R = JstyleResolveData.checkUpdateFile(kH1860BleService.F, md5, 0);
                                                kH1860BleService.S = JstyleResolveData.endUpdateFile(kH1860BleService.F, md5, 0);
                                                byte[] bArr5 = kH1860BleService.F;
                                                int length2 = bArr5.length % 4096;
                                                int length3 = bArr5.length / 4096;
                                                if (length2 != 0) {
                                                    length3++;
                                                }
                                                kH1860BleService.G = new HashMap<>();
                                                LogHelper.i(str2, "initByteValue: " + length3);
                                                for (int i = 0; i < length3; i++) {
                                                    int i2 = i * 4096;
                                                    int i3 = i2 + 4096;
                                                    byte[] bArr6 = kH1860BleService.F;
                                                    int length4 = i3 > bArr6.length ? bArr6.length - i2 : 4096;
                                                    byte[] bArr7 = new byte[length4];
                                                    System.arraycopy(bArr6, i2, bArr7, 0, length4);
                                                    kH1860BleService.G.put(Integer.valueOf(i), bArr7);
                                                }
                                                byte[] bArr8 = kH1860BleService.G.get(Integer.valueOf(kH1860BleService.J));
                                                kH1860BleService.I = bArr8;
                                                kH1860BleService.Q = JstyleResolveData.CalcCRC16(bArr8, bArr8.length);
                                                kH1860BleService.writeValue(kH1860BleService.R);
                                            }
                                        }
                                    }, Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS);
                                    return;
                                } else {
                                    jstyleBaseReq.getResponseListener().onFailure(new JstyleError(JstyleErrorType.COMMAND_REQUEST_ERROR, "Watch face file info is missing"));
                                    return;
                                }
                            }
                        }
                    } else {
                        LogHelper.d(str, "file does not exist");
                    }
                    byteArray = null;
                    if (!file.exists()) {
                    }
                    jstyleBaseReq.getResponseListener().onFailure(new JstyleError(JstyleErrorType.COMMAND_REQUEST_ERROR, "Watch face file info is missing"));
                    return;
                case 5:
                case '\t':
                case '\f':
                case '\r':
                    sendCommandToBand(jstyleBaseReq.getCommandBytes());
                    return;
                case 16:
                    byte[] commandBytes = jstyleBaseReq.getCommandBytes();
                    int length = commandBytes.length;
                    int i = this.p;
                    if (length > i) {
                        int i2 = (i / 39) * 39;
                        int length2 = commandBytes.length % i2 == 0 ? commandBytes.length / i2 : (commandBytes.length / i2) + 1;
                        int i3 = 0;
                        while (i3 < length2) {
                            int i4 = i3 + 1;
                            int length3 = i2 * i4 >= commandBytes.length ? commandBytes.length - (i2 * i3) : i2;
                            byte[] bArr2 = new byte[length3];
                            System.arraycopy(commandBytes, i3 * i2, bArr2, 0, length3);
                            offerData(bArr2);
                            i3 = i4;
                        }
                        offerData();
                        return;
                    }
                    sendCommandToBand(jstyleBaseReq.getCommandBytes());
                    return;
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case '!':
                    if (!this.r) {
                        clearResponseList();
                    }
                    sendCommandToBand(jstyleBaseReq.getCommandBytes());
                    return;
                case 29:
                    this.j.postDelayed(new Runnable() { // from class: com.coveiot.android.bleabstract.services.KH1860BleService.9
                        @Override // java.lang.Runnable
                        public void run() {
                            KH1860BleService kH1860BleService = KH1860BleService.this;
                            Bitmap bitmap = jstyleBaseReq.getBitmap();
                            String name = FileType.DIY.name();
                            String str2 = KH1860BleService.T;
                            kH1860BleService.getClass();
                            int height = bitmap.getHeight();
                            int width = bitmap.getWidth();
                            kH1860BleService.F = new byte[97920];
                            int i5 = 0;
                            int i6 = 0;
                            for (int i7 = 0; i7 < height; i7++) {
                                for (int i8 = 0; i8 < width; i8++) {
                                    int pixel = bitmap.getPixel(i8, i7);
                                    short dataFromRGB = KH1860BleService.dataFromRGB((byte) Color.red(pixel), (byte) Color.green(pixel), (byte) Color.blue(pixel));
                                    byte[] bArr3 = kH1860BleService.F;
                                    int i9 = i6 * 2;
                                    bArr3[i9] = (byte) ((dataFromRGB >> 8) & 255);
                                    bArr3[i9 + 1] = (byte) (dataFromRGB & 255);
                                    i6++;
                                }
                            }
                            byte[] bArr4 = kH1860BleService.F;
                            int length4 = bArr4.length % 4096;
                            int length5 = bArr4.length / 4096;
                            if (length4 != 0) {
                                length5++;
                            }
                            kH1860BleService.H = new int[length5];
                            kH1860BleService.G = new HashMap<>();
                            for (int i10 = 0; i10 < length5; i10++) {
                                int i11 = i10 * 4096;
                                int i12 = i11 + 4096;
                                byte[] bArr5 = kH1860BleService.F;
                                int length6 = i12 >= bArr5.length ? bArr5.length - i11 : 4096;
                                byte[] bArr6 = new byte[length6];
                                System.arraycopy(bArr5, i11, bArr6, 0, length6);
                                kH1860BleService.H[i10] = KH1860BleService.CalcCRC16(bArr6, length6);
                                kH1860BleService.G.put(Integer.valueOf(i10), bArr6);
                            }
                            byte[] bArr7 = kH1860BleService.G.get(Integer.valueOf(kH1860BleService.J));
                            kH1860BleService.I = bArr7;
                            kH1860BleService.O = KH1860BleService.CalcCRC16(bArr7, bArr7.length);
                            byte[] bArr8 = kH1860BleService.F;
                            int length7 = bArr8.length;
                            int CalcCRC16 = KH1860BleService.CalcCRC16(bArr8, bArr8.length);
                            int length8 = (kH1860BleService.H.length * 2) + 10;
                            byte[] bArr9 = new byte[length8];
                            bArr9[0] = kH1860BleService.b(name);
                            bArr9[1] = 1;
                            bArr9[2] = (byte) (length7 & 255);
                            bArr9[3] = (byte) ((length7 >> 8) & 255);
                            bArr9[4] = (byte) ((length7 >> 16) & 255);
                            bArr9[5] = (byte) ((length7 >> 24) & 255);
                            bArr9[6] = (byte) (CalcCRC16 & 255);
                            bArr9[7] = (byte) ((CalcCRC16 >> 8) & 255);
                            while (true) {
                                int[] iArr = kH1860BleService.H;
                                if (i5 < iArr.length) {
                                    int i13 = (i5 * 2) + 8;
                                    bArr9[i13] = (byte) (iArr[i5] & 255);
                                    bArr9[i13 + 1] = (byte) ((iArr[i5] >> 8) & 255);
                                    i5++;
                                } else {
                                    int i14 = length8 - 2;
                                    int CalcCRC162 = KH1860BleService.CalcCRC16(bArr9, i14);
                                    bArr9[i14] = (byte) (CalcCRC162 & 255);
                                    bArr9[length8 - 1] = (byte) ((CalcCRC162 >> 8) & 255);
                                    kH1860BleService.writeValue(bArr9);
                                    return;
                                }
                            }
                        }
                    }, Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS);
                    return;
                case '\"':
                    this.J = 0;
                    this.M = 0;
                    this.K = 0;
                    this.L = 0;
                    this.N = false;
                    this.j.postDelayed(new Runnable() { // from class: com.coveiot.android.bleabstract.services.KH1860BleService.10
                        @Override // java.lang.Runnable
                        public void run() {
                            KH1860BleService.this.writeValue(JstyleResolveData.deleteAgpsFile());
                            KH1860BleService kH1860BleService = KH1860BleService.this;
                            String filePath = jstyleBaseReq.getFilePath();
                            String name = FileType.AGPS_UPDATE.name();
                            kH1860BleService.getClass();
                            String str2 = KH1860BleService.T;
                            LogHelper.i(str2, "downloadedPath: " + filePath);
                            File file2 = new File(filePath);
                            if (!file2.exists()) {
                                return;
                            }
                            byte[] a2 = kH1860BleService.a(file2);
                            kH1860BleService.F = a2;
                            int length4 = a2.length % 4096;
                            int length5 = a2.length / 4096;
                            if (length4 != 0) {
                                length5++;
                            }
                            kH1860BleService.H = new int[length5];
                            kH1860BleService.G = new HashMap<>();
                            LogHelper.i(str2, "initAgpsByteValue: " + length5);
                            int i5 = 0;
                            for (int i6 = 0; i6 < length5; i6++) {
                                int i7 = i6 * 4096;
                                int i8 = i7 + 4096;
                                byte[] bArr3 = kH1860BleService.F;
                                int length6 = i8 >= bArr3.length ? bArr3.length - i7 : 4096;
                                byte[] bArr4 = new byte[length6];
                                System.arraycopy(bArr3, i7, bArr4, 0, length6);
                                kH1860BleService.H[i6] = KH1860BleService.CalcCRC16(bArr4, length6);
                                kH1860BleService.G.put(Integer.valueOf(i6), bArr4);
                            }
                            byte[] bArr5 = kH1860BleService.G.get(Integer.valueOf(kH1860BleService.J));
                            kH1860BleService.I = bArr5;
                            kH1860BleService.O = KH1860BleService.CalcCRC16(bArr5, bArr5.length);
                            File file3 = new File(filePath);
                            if (!file3.exists()) {
                                return;
                            }
                            byte[] a3 = kH1860BleService.a(file3);
                            kH1860BleService.F = a3;
                            int length7 = a3.length;
                            int CalcCRC16 = KH1860BleService.CalcCRC16(a3, a3.length);
                            byte[] bArr6 = new byte[42];
                            bArr6[0] = kH1860BleService.b(name);
                            bArr6[1] = 1;
                            bArr6[2] = (byte) (length7 & 255);
                            bArr6[3] = (byte) ((length7 >> 8) & 255);
                            bArr6[4] = (byte) ((length7 >> 16) & 255);
                            bArr6[5] = (byte) ((length7 >> 24) & 255);
                            bArr6[6] = (byte) (CalcCRC16 & 255);
                            bArr6[7] = (byte) ((CalcCRC16 >> 8) & 255);
                            while (true) {
                                int[] iArr = kH1860BleService.H;
                                if (i5 < iArr.length) {
                                    int i9 = (i5 * 2) + 8;
                                    bArr6[i9] = (byte) (iArr[i5] & 255);
                                    bArr6[i9 + 1] = (byte) ((iArr[i5] >> 8) & 255);
                                    i5++;
                                } else {
                                    int CalcCRC162 = KH1860BleService.CalcCRC16(bArr6, 40);
                                    bArr6[40] = (byte) (CalcCRC162 & 255);
                                    bArr6[41] = (byte) ((CalcCRC162 >> 8) & 255);
                                    kH1860BleService.writeValue(bArr6);
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
        String str = T;
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

    public final byte b(String str) {
        if (str == FileType.AGPS_UPDATE.name()) {
            return (byte) -110;
        }
        if (str == FileType.DIY.name()) {
            return BleUUID.CMD_ID_96;
        }
        return (byte) 0;
    }

    public final void c() {
        boolean z;
        int i = this.P;
        if (i == 0 || (z = this.N)) {
            return;
        }
        byte[] bArr = this.I;
        if (bArr == null) {
            writeValue(this.S);
            return;
        }
        int length = bArr.length;
        int i2 = z ? i - 8 : i - 6;
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
        int i6 = i5 + i2;
        byte[] bArr2 = this.F;
        if (i6 >= bArr2.length) {
            i2 = bArr2.length - i5;
        }
        int i7 = this.N ? i2 + 8 : i2 + 6;
        byte[] bArr3 = new byte[i7];
        bArr3[0] = -107;
        int i8 = this.J;
        bArr3[1] = (byte) (i8 & 255);
        bArr3[2] = (byte) ((i8 >> 8) & 255);
        int i9 = this.K;
        this.K = i9 + 1;
        bArr3[3] = (byte) i9;
        System.arraycopy(bArr2, i5, bArr3, 4, i2);
        if (this.N) {
            int i10 = this.Q;
            bArr3[i7 - 4] = (byte) (i10 & 255);
            bArr3[i7 - 3] = (byte) ((i10 >> 8) & 255);
        }
        int i11 = i7 - 2;
        int CalcCRC16 = JstyleResolveData.CalcCRC16(bArr3, i11);
        bArr3[i11] = (byte) (CalcCRC16 & 255);
        bArr3[i7 - 1] = (byte) ((CalcCRC16 >> 8) & 255);
        this.L += i2;
        offerValue(bArr3);
        if (this.N) {
            this.K = 0;
            int i12 = this.J;
            int i13 = this.Q;
            byte[] md5 = DigestUtils.md5(this.G.get(Integer.valueOf(i12)));
            int length2 = md5.length;
            byte[] bArr4 = new byte[24];
            bArr4[0] = -107;
            bArr4[1] = (byte) (i12 & 255);
            bArr4[2] = (byte) ((i12 >> 8) & 255);
            bArr4[3] = -1;
            bArr4[4] = (byte) (i13 & 255);
            bArr4[5] = (byte) ((i13 >> 8) & 255);
            System.arraycopy(md5, 0, bArr4, 6, md5.length);
            int CalcCRC162 = JstyleResolveData.CalcCRC16(bArr4, 22);
            bArr4[22] = (byte) (CalcCRC162 & 255);
            bArr4[23] = (byte) ((CalcCRC162 >> 8) & 255);
            offerValue(bArr4);
            nextQueue();
            return;
        }
        c();
    }

    public final void d() {
        float length = this.L / this.F.length;
        NumberFormat percentInstance = NumberFormat.getPercentInstance();
        percentInstance.setMaximumFractionDigits(0);
        String format = percentInstance.format(length);
        if (format.contains("%")) {
            format = format.replace("%", "");
        }
        LogHelper.d("updateProgress", "<><><>" + format);
        JstyleLiveResponse jstyleLiveResponse = new JstyleLiveResponse();
        jstyleLiveResponse.setDataType(JStyleConstants.CUSTOM_WATCH_FACE_UPLOAD_PERCENTAGE);
        jstyleLiveResponse.setObj(format);
        BleEventBusManager.getInstance().getEventBus().post(jstyleLiveResponse);
    }

    public final void a(final BluetoothDevice bluetoothDevice) {
        a(Clove1860BleState.BleState.CONNECTING, true);
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
        this.g.postDelayed(new Runnable() { // from class: com.coveiot.android.bleabstract.services.KH1860BleService.2
            @Override // java.lang.Runnable
            public void run() {
                String str = KH1860BleService.T;
                LogHelper.d(str, "connectGatt called " + bluetoothDevice.getAddress(), ModuleNames.BLEABSTRACT.getModuleName());
                KH1860BleService kH1860BleService = KH1860BleService.this;
                if (kH1860BleService.D != null) {
                    if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(kH1860BleService, "android.permission.BLUETOOTH_CONNECT") == 0) {
                        KH1860BleService kH1860BleService2 = KH1860BleService.this;
                        kH1860BleService2.d = bluetoothDevice.connectGatt(kH1860BleService2.getApplicationContext(), false, KH1860BleService.this.D);
                    }
                }
            }
        }, 1000L);
    }

    public final void a(final Clove1860BleState.BleState bleState, boolean z) {
        Handler handler;
        this.bluetoothConnectionStatus = bleState;
        if (!z || (handler = this.g) == null) {
            return;
        }
        handler.removeCallbacksAndMessages(null);
        this.g.post(new Runnable(this) { // from class: com.coveiot.android.bleabstract.services.KH1860BleService.3
            @Override // java.lang.Runnable
            public void run() {
                BleEventBusManager.getInstance().getEventBus().post(new Clove1860BleState(bleState));
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

    public final void a(String str) {
        byte[] bArr = this.F;
        int length = bArr.length;
        int CalcCRC16 = CalcCRC16(bArr, bArr.length);
        int CalcCRC162 = CalcCRC16(r2, 8);
        byte[] bArr2 = {b(str), 2, (byte) (length & 255), (byte) ((length >> 8) & 255), (byte) ((length >> 16) & 255), (byte) ((length >> 24) & 255), (byte) (CalcCRC16 & 255), (byte) ((CalcCRC16 >> 8) & 255), (byte) (CalcCRC162 & 255), (byte) ((CalcCRC162 >> 8) & 255)};
        writeValue(bArr2);
    }

    public final byte[] a(File file) {
        if (file.isFile()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] bArr = new byte[1024];
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read != -1) {
                        byteArrayOutputStream.write(bArr, 0, read);
                    } else {
                        return byteArrayOutputStream.toByteArray();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            LogHelper.d("readFile ", "file does not exist！");
            return null;
        }
    }
}
