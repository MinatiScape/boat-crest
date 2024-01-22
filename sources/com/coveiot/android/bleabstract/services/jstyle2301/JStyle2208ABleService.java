package com.coveiot.android.bleabstract.services.jstyle2301;

import android.annotation.SuppressLint;
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
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.text.TextUtils;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import com.coveiot.android.bleabstract.BleApiUtils;
import com.coveiot.android.bleabstract.NotificationInfo;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.preferences.PreferenceManagerAbstract;
import com.coveiot.android.jstyle2208a.JStyleConstants;
import com.coveiot.android.jstyle2208a.Util.BleData;
import com.coveiot.android.jstyle2208a.Util.ResolveData;
import com.coveiot.android.jstyle2208a.Util.RxBus;
import com.coveiot.android.jstyle2208a.api.JStyleVActivityHistoryDataReq;
import com.coveiot.android.jstyle2208a.api.JStyleVBaseReq;
import com.coveiot.android.jstyle2208a.api.JStyleVHRDataReq;
import com.coveiot.android.jstyle2208a.api.JStyleVHRVDataReq;
import com.coveiot.android.jstyle2208a.api.JStyleVRealTimeWalkReq;
import com.coveiot.android.jstyle2208a.api.JStyleVSleepDataReq;
import com.coveiot.android.jstyle2208a.api.JStyleVSpo2DataReq;
import com.coveiot.android.jstyle2208a.api.JStyleVStepsDataReq;
import com.coveiot.android.jstyle2208a.api.JStyleVTemperatureDataReq;
import com.coveiot.android.jstyle2208a.api.JStyleVTodayFitnessValueReq;
import com.coveiot.android.jstyle2208a.api.JstyleBaseRes;
import com.coveiot.android.jstyle2208a.model.JstyleLiveResponse;
import com.coveiot.sdk.ble.CloveBleState;
import com.coveiot.sdk.ble.eventbus.BleEventBusManager;
import com.coveiot.sdk.ble.events.ConnectionType;
import com.coveiot.sdk.ble.utils.BlePreferenceManager;
import com.coveiot.sdk.ble.utils.BleUtils;
import com.coveiot.sdk.ble.utils.CommonPreference;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.jstyle.blesdk1860.constant.BleConst;
import com.jstyle.blesdk1860.constant.DeviceKey;
import com.jstyle.blesdk2208.Util.BleSDK;
import com.jstyle.blesdk2208.callback.BleConnectionListener;
import com.jstyle.blesdk2208.callback.DataListener2208;
import com.jstyle.blesdk2208.model.MyDeviceTime;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.UUID;
/* loaded from: classes2.dex */
public final class JStyle2208ABleService extends Service implements DataListener2208 {
    public static final String ACTION_DATA_AVAILABLE = "com.jstylelife.ble.service.ACTION_DATA_AVAILABLE";
    public static final String ACTION_GATT_CONNECTED = "com.jstylelife.ble.service.ACTION_GATT_CONNECTED";
    public static final String ACTION_GATT_DISCONNECTED = "com.jstylelife.ble.service.ACTION_GATT_DISCONNECTED";
    public static final String ACTION_GATT_onDescriptorWrite = "com.jstylelife.ble.service.onDescriptorWrite";
    public static BluetoothGattCharacteristic colorCharacteristic;
    public static final UUID v = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb");
    public static final UUID w = UUID.fromString("0000fff0-0000-1000-8000-00805f9b34fb");
    public static final UUID x = UUID.fromString("0000fff6-0000-1000-8000-00805f9b34fb");
    public static final UUID y = UUID.fromString("0000fff7-0000-1000-8000-00805f9b34fb");
    public static ArrayList<BluetoothGatt> z = new ArrayList<>();
    public BleConnectionListener bleConnectionListener;
    public BluetoothManager c;
    public BluetoothAdapter d;
    public BluetoothGatt e;
    public Handler f;
    public Disposable g;
    public ArrayList<Map<String, String>> h;
    public int i;
    public int j;
    public JStyleVBaseReq k;
    public CloveBleState.BleState l;
    public Handler m;
    public String n;
    public Context o;
    public boolean p;
    public boolean q;
    public BluetoothAdapter.LeScanCallback r;
    public BluetoothGattCallback s;
    public Queue<byte[]> t;
    public final BroadcastReceiver u;

    /* renamed from: a  reason: collision with root package name */
    public boolean f4022a = false;
    public boolean fastconnect = false;
    public HashMap<BluetoothDevice, BluetoothGatt> hasp = new HashMap<>();
    public final IBinder b = new LocalBinder();

    /* loaded from: classes2.dex */
    public class LocalBinder extends Binder {
        public LocalBinder() {
        }

        public JStyle2208ABleService getService() {
            return JStyle2208ABleService.this;
        }
    }

    public JStyle2208ABleService() {
        new HashMap();
        this.f = new Handler();
        this.h = new ArrayList<>();
        this.i = 0;
        this.j = 50;
        this.l = CloveBleState.BleState.DISCONNECTED;
        this.m = new Handler(Looper.getMainLooper());
        this.p = true;
        this.r = new BluetoothAdapter.LeScanCallback() { // from class: com.coveiot.android.bleabstract.services.jstyle2301.JStyle2208ABleService.2
            @Override // android.bluetooth.BluetoothAdapter.LeScanCallback
            public void onLeScan(final BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
                if (bluetoothDevice.getAddress().equals(JStyle2208ABleService.this.n)) {
                    String decodeDeviceName = ResolveData.decodeDeviceName(bluetoothDevice, bArr);
                    if (TextUtils.isEmpty(decodeDeviceName) || !decodeDeviceName.equals("DfuTarg")) {
                        JStyle2208ABleService jStyle2208ABleService = JStyle2208ABleService.this;
                        if (jStyle2208ABleService.e != null) {
                            return;
                        }
                        jStyle2208ABleService.f.post(new Runnable() { // from class: com.coveiot.android.bleabstract.services.jstyle2301.JStyle2208ABleService.2.1
                            @Override // java.lang.Runnable
                            public void run() {
                                JStyle2208ABleService jStyle2208ABleService2 = JStyle2208ABleService.this;
                                UUID uuid = JStyle2208ABleService.v;
                                jStyle2208ABleService2.a(false);
                                int i2 = Build.VERSION.SDK_INT;
                                if (i2 >= 23) {
                                    if (i2 >= 31 && ContextCompat.checkSelfPermission(JStyle2208ABleService.this, "android.permission.BLUETOOTH_CONNECT") != 0) {
                                        return;
                                    }
                                    JStyle2208ABleService jStyle2208ABleService3 = JStyle2208ABleService.this;
                                    jStyle2208ABleService3.e = bluetoothDevice.connectGatt(jStyle2208ABleService3.o, false, jStyle2208ABleService3.s, 2);
                                } else {
                                    JStyle2208ABleService jStyle2208ABleService4 = JStyle2208ABleService.this;
                                    jStyle2208ABleService4.e = bluetoothDevice.connectGatt(jStyle2208ABleService4.o, false, jStyle2208ABleService4.s);
                                }
                                if (JStyle2208ABleService.this.e == null) {
                                    System.out.println("gatt is null ");
                                }
                            }
                        });
                    }
                }
            }
        };
        this.s = new BluetoothGattCallback() { // from class: com.coveiot.android.bleabstract.services.jstyle2301.JStyle2208ABleService.4
            @Override // android.bluetooth.BluetoothGattCallback
            public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
                super.onCharacteristicChanged(bluetoothGatt, bluetoothGattCharacteristic);
                if (JStyle2208ABleService.this.e == null) {
                    return;
                }
                ResolveData.byte2Hex(bluetoothGattCharacteristic.getValue());
                JStyle2208ABleService jStyle2208ABleService = JStyle2208ABleService.this;
                bluetoothGatt.getDevice().getAddress();
                jStyle2208ABleService.getClass();
                byte[] value = bluetoothGattCharacteristic.getValue();
                BleData bleData = new BleData();
                bleData.setAction("com.jstylelife.ble.service.ACTION_DATA_AVAILABLE");
                bleData.setValue(value);
                RxBus.getInstance().post(bleData);
            }

            @Override // android.bluetooth.BluetoothGattCallback
            public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
                super.onCharacteristicRead(bluetoothGatt, bluetoothGattCharacteristic, i);
                if (i == 0) {
                    JStyle2208ABleService jStyle2208ABleService = JStyle2208ABleService.this;
                    bluetoothGatt.getDevice().getAddress();
                    UUID uuid = JStyle2208ABleService.v;
                    jStyle2208ABleService.getClass();
                    byte[] value = bluetoothGattCharacteristic.getValue();
                    BleData bleData = new BleData();
                    bleData.setAction("com.jstylelife.ble.service.ACTION_DATA_AVAILABLE");
                    bleData.setValue(value);
                    RxBus.getInstance().post(bleData);
                }
            }

            @Override // android.bluetooth.BluetoothGattCallback
            public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
                super.onCharacteristicWrite(bluetoothGatt, bluetoothGattCharacteristic, i);
                if (i == 0) {
                    JStyle2208ABleService.this.nextQueue();
                }
            }

            /*  JADX ERROR: JadxRuntimeException in pass: InlineMethods
                jadx.core.utils.exceptions.JadxRuntimeException: Failed to process method for inline: com.coveiot.android.bleabstract.services.jstyle2301.JStyle2208ABleService.b(com.coveiot.android.bleabstract.services.jstyle2301.JStyle2208ABleService, boolean):boolean
                	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:76)
                	at jadx.core.dex.visitors.InlineMethods.visit(InlineMethods.java:51)
                Caused by: java.lang.NullPointerException
                */
            @Override // android.bluetooth.BluetoothGattCallback
            public void onConnectionStateChange(android.bluetooth.BluetoothGatt r5, int r6, int r7) {
                /*
                    Method dump skipped, instructions count: 254
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.bleabstract.services.jstyle2301.JStyle2208ABleService.AnonymousClass4.onConnectionStateChange(android.bluetooth.BluetoothGatt, int, int):void");
            }

            /*  JADX ERROR: JadxRuntimeException in pass: InlineMethods
                jadx.core.utils.exceptions.JadxRuntimeException: Failed to process method for inline: com.coveiot.android.bleabstract.services.jstyle2301.JStyle2208ABleService.b(com.coveiot.android.bleabstract.services.jstyle2301.JStyle2208ABleService, boolean):boolean
                	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:76)
                	at jadx.core.dex.visitors.InlineMethods.visit(InlineMethods.java:51)
                Caused by: java.lang.NullPointerException
                */
            @Override // android.bluetooth.BluetoothGattCallback
            public void onDescriptorWrite(android.bluetooth.BluetoothGatt r1, android.bluetooth.BluetoothGattDescriptor r2, int r3) {
                /*
                    r0 = this;
                    super.onDescriptorWrite(r1, r2, r3)
                    if (r3 != 0) goto L3e
                    com.coveiot.android.bleabstract.services.jstyle2301.JStyle2208ABleService r1 = com.coveiot.android.bleabstract.services.jstyle2301.JStyle2208ABleService.this
                    r1.nextQueue()
                    com.coveiot.android.bleabstract.services.jstyle2301.JStyle2208ABleService r1 = com.coveiot.android.bleabstract.services.jstyle2301.JStyle2208ABleService.this
                    r2 = 1
                    com.coveiot.android.bleabstract.services.jstyle2301.JStyle2208ABleService.b(r1, r2)
                    com.coveiot.android.bleabstract.services.jstyle2301.JStyle2208ABleService r1 = com.coveiot.android.bleabstract.services.jstyle2301.JStyle2208ABleService.this
                    r1.getClass()
                    com.coveiot.android.jstyle2208a.Util.BleData r1 = new com.coveiot.android.jstyle2208a.Util.BleData
                    r1.<init>()
                    java.lang.String r3 = "com.jstylelife.ble.service.onDescriptorWrite"
                    r1.setAction(r3)
                    com.coveiot.android.jstyle2208a.Util.RxBus r3 = com.coveiot.android.jstyle2208a.Util.RxBus.getInstance()
                    r3.post(r1)
                    com.coveiot.android.bleabstract.services.jstyle2301.JStyle2208ABleService r1 = com.coveiot.android.bleabstract.services.jstyle2301.JStyle2208ABleService.this
                    com.jstyle.blesdk2208.callback.BleConnectionListener r1 = r1.bleConnectionListener
                    if (r1 == 0) goto L2f
                    r1.ConnectionSucceeded()
                L2f:
                    com.jstyle.blesdk2208.model.MyDeviceTime r1 = new com.jstyle.blesdk2208.model.MyDeviceTime
                    r1.<init>()
                    com.jstyle.blesdk2208.Util.BleSDK.SetDeviceTime(r1)
                    com.coveiot.android.bleabstract.services.jstyle2301.JStyle2208ABleService r1 = com.coveiot.android.bleabstract.services.jstyle2301.JStyle2208ABleService.this
                    com.coveiot.sdk.ble.CloveBleState$BleState r3 = com.coveiot.sdk.ble.CloveBleState.BleState.CONNECTED
                    r1.a(r3, r2)
                L3e:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.bleabstract.services.jstyle2301.JStyle2208ABleService.AnonymousClass4.onDescriptorWrite(android.bluetooth.BluetoothGatt, android.bluetooth.BluetoothGattDescriptor, int):void");
            }

            @Override // android.bluetooth.BluetoothGattCallback
            public void onMtuChanged(BluetoothGatt bluetoothGatt, int i, int i2) {
                super.onMtuChanged(bluetoothGatt, i, i2);
                if (i2 == 0) {
                    JStyle2208ABleService.this.setCharacteristicNotification(true);
                } else if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(JStyle2208ABleService.this, "android.permission.BLUETOOTH_CONNECT") == 0) {
                    bluetoothGatt.requestMtu(153);
                }
            }

            @Override // android.bluetooth.BluetoothGattCallback
            public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
                super.onServicesDiscovered(bluetoothGatt, i);
                if (i == 0) {
                    String address = bluetoothGatt.getDevice().getAddress();
                    if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(JStyle2208ABleService.this, "android.permission.BLUETOOTH_CONNECT") == 0) {
                        JStyle2208ABleService.this.d.getRemoteDevice(address).getName();
                        JStyle2208ABleService.this.setCharacteristicNotification(true);
                        JStyle2208ABleService.this.getClass();
                        JStyle2208ABleService.this.writeValue(BleSDK.SetDeviceTime(new MyDeviceTime()));
                        JStyle2208ABleService.this.a(CloveBleState.BleState.CONNECTED, true);
                    }
                }
            }
        };
        this.t = new LinkedList();
        this.u = new BroadcastReceiver() { // from class: com.coveiot.android.bleabstract.services.jstyle2301.JStyle2208ABleService.7
            @Override // android.content.BroadcastReceiver
            @RequiresApi(api = 23)
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (action.equals("android.bluetooth.adapter.action.STATE_CHANGED")) {
                    int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE);
                    if (intExtra == 3 || intExtra == 10) {
                        LogHelper.d("JStyle2208ABleService", "Bluetooth turned off", ModuleNames.BLEABSTRACT.getModuleName());
                        JStyle2208ABleService jStyle2208ABleService = JStyle2208ABleService.this;
                        CloveBleState.BleState bleState = CloveBleState.BleState.DISCONNECTED;
                        UUID uuid = JStyle2208ABleService.v;
                        jStyle2208ABleService.a(bleState, true);
                        JStyle2208ABleService.this.b();
                        JStyle2208ABleService.this.disconnect();
                        try {
                            if ((Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(JStyle2208ABleService.this, "android.permission.BLUETOOTH_SCAN") == 0) && BluetoothAdapter.getDefaultAdapter().isDiscovering()) {
                                BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (intExtra != 12) {
                    } else {
                        JStyle2208ABleService jStyle2208ABleService2 = JStyle2208ABleService.this;
                        UUID uuid2 = JStyle2208ABleService.v;
                        jStyle2208ABleService2.b();
                        if (((Boolean) BlePreferenceManager.getPreference(JStyle2208ABleService.this.getApplicationContext(), CommonPreference.IS_BAND_UNPAIRED, Boolean.FALSE)).booleanValue()) {
                            return;
                        }
                        JStyle2208ABleService jStyle2208ABleService3 = JStyle2208ABleService.this;
                        jStyle2208ABleService3.n = PreferenceManagerAbstract.getInstance(jStyle2208ABleService3.getApplicationContext()).getConnectedDeviceMacAddress();
                        JStyle2208ABleService jStyle2208ABleService4 = JStyle2208ABleService.this;
                        jStyle2208ABleService4.f4022a = BleUtils.getConnectionType(jStyle2208ABleService4, PreferenceManagerAbstract.getInstance(jStyle2208ABleService4.getApplicationContext()).getConnectionType()) == ConnectionType.RECONNECT_ON_DISCONNECT;
                        if (JStyle2208ABleService.this.d.isEnabled()) {
                            JStyle2208ABleService.this.disconnect();
                            try {
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                            if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(JStyle2208ABleService.this, "android.permission.BLUETOOTH_SCAN") == 0) {
                                BluetoothAdapter.getDefaultAdapter().startDiscovery();
                                ModuleNames moduleNames = ModuleNames.BLEABSTRACT;
                                LogHelper.d("JStyle2208ABleService", "Bluetooth turned on", moduleNames.getModuleName());
                                LogHelper.d("JStyle2208ABleService", "BT turned on calling reconnect to config devices", moduleNames.getModuleName());
                                JStyle2208ABleService jStyle2208ABleService5 = JStyle2208ABleService.this;
                                if (AppUtils.isEmpty(jStyle2208ABleService5.n) || AppUtils.isEmpty(PreferenceManagerAbstract.getInstance(jStyle2208ABleService5).getConnectedDeviceMacAddress())) {
                                    return;
                                }
                                jStyle2208ABleService5.initBluetoothDevice(jStyle2208ABleService5.n, jStyle2208ABleService5.getApplicationContext(), true, jStyle2208ABleService5.bleConnectionListener);
                            }
                        }
                    }
                } else if (action.equalsIgnoreCase("action_stop_service")) {
                    try {
                        JStyle2208ABleService jStyle2208ABleService6 = JStyle2208ABleService.this;
                        UUID uuid3 = JStyle2208ABleService.v;
                        jStyle2208ABleService6.disconnect();
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                    LogHelper.d("JStyle2208ABleService", "Action stop service recieved", ModuleNames.BLEABSTRACT.getModuleName());
                    JStyle2208ABleService jStyle2208ABleService7 = JStyle2208ABleService.this;
                    CloveBleState.BleState bleState2 = CloveBleState.BleState.DISCONNECTED;
                    UUID uuid4 = JStyle2208ABleService.v;
                    jStyle2208ABleService7.a(bleState2, true);
                }
            }
        };
    }

    /*  JADX ERROR: NullPointerException in pass: MarkMethodsForInline
        java.lang.NullPointerException
        */
    public static /* synthetic */ boolean b(com.coveiot.android.bleabstract.services.jstyle2301.JStyle2208ABleService r0, boolean r1) {
        /*
            r0.getClass()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.bleabstract.services.jstyle2301.JStyle2208ABleService.b(com.coveiot.android.bleabstract.services.jstyle2301.JStyle2208ABleService, boolean):boolean");
    }

    public static Map<String, String> getData(Map<String, Object> map) {
        return (Map) map.get(DeviceKey.Data);
    }

    public static String getDataType(Map<String, Object> map) {
        return map == null ? "" : (String) map.get(DeviceKey.DataType);
    }

    public void clearResponseData() {
        ArrayList<Map<String, String>> arrayList = this.h;
        if (arrayList != null) {
            arrayList.clear();
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public void dataCallback(Map<String, Object> map) {
        char c;
        JStyleVBaseReq jStyleVBaseReq;
        String dataType = getDataType(map);
        boolean end = getEnd(map);
        JstyleBaseRes jstyleBaseRes = new JstyleBaseRes();
        dataType.hashCode();
        int hashCode = dataType.hashCode();
        switch (hashCode) {
            case 49:
                if (dataType.equals("1")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 51:
                if (dataType.equals("3")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 57:
                if (dataType.equals(BleConst.GetDeviceBatteryLevel)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1568:
                if (dataType.equals(BleConst.GetDeviceVersion)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1574:
                if (dataType.equals(BleConst.GetAutomaticHRMonitoring)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1576:
                if (dataType.equals(BleConst.GetAlarmClock)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1598:
                if (dataType.equals(BleConst.SetAlarmClockWithAllClock)) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 1629:
                if (dataType.equals(BleConst.EnterActivityMode)) {
                    c = 15;
                    break;
                }
                c = 65535;
                break;
            case 1662:
                if (dataType.equals("42")) {
                    c = 16;
                    break;
                }
                c = 65535;
                break;
            case 1724:
                if (dataType.equals(BleConst.AGPS)) {
                    c = 17;
                    break;
                }
                c = 65535;
                break;
            case 1730:
                if (dataType.equals(BleConst.Send_app_indoor_outdoor)) {
                    c = 18;
                    break;
                }
                c = 65535;
                break;
            case 1786:
                if (dataType.equals("82")) {
                    c = 19;
                    break;
                }
                c = 65535;
                break;
            default:
                switch (hashCode) {
                    case 1600:
                        if (dataType.equals(BleConst.SetSedentaryReminder)) {
                            c = 7;
                            break;
                        }
                        c = 65535;
                        break;
                    case 1601:
                        if (dataType.equals(BleConst.RealTimeStep)) {
                            c = '\b';
                            break;
                        }
                        c = 65535;
                        break;
                    case 1602:
                        if (dataType.equals(BleConst.GetTotalActivityData)) {
                            c = '\t';
                            break;
                        }
                        c = 65535;
                        break;
                    case 1603:
                        if (dataType.equals(BleConst.GetDetailActivityData)) {
                            c = '\n';
                            break;
                        }
                        c = 65535;
                        break;
                    case 1604:
                        if (dataType.equals(BleConst.GetDetailSleepData)) {
                            c = 11;
                            break;
                        }
                        c = 65535;
                        break;
                    case 1605:
                        if (dataType.equals(BleConst.GetDynamicHR)) {
                            c = '\f';
                            break;
                        }
                        c = 65535;
                        break;
                    case 1606:
                        if (dataType.equals(BleConst.GetStaticHR)) {
                            c = '\r';
                            break;
                        }
                        c = 65535;
                        break;
                    case 1607:
                        if (dataType.equals(BleConst.GetActivityModeData)) {
                            c = 14;
                            break;
                        }
                        c = 65535;
                        break;
                    default:
                        c = 65535;
                        break;
                }
        }
        switch (c) {
            case 0:
                Map<String, String> data = getData(map);
                if (data != null) {
                    data.get(DeviceKey.KPhoneDataLength);
                    return;
                }
                return;
            case 1:
            case 4:
            case 5:
            case 6:
            case 7:
                JStyleVBaseReq jStyleVBaseReq2 = this.k;
                if (jStyleVBaseReq2 == null || !jStyleVBaseReq2.getCommandType().equalsIgnoreCase(dataType)) {
                    return;
                }
                jstyleBaseRes.setBaseReq(this.k);
                this.k.getResponseListener().onResponse(jstyleBaseRes);
                return;
            case 2:
            case 3:
                JStyleVBaseReq jStyleVBaseReq3 = this.k;
                if (jStyleVBaseReq3 == null || !jStyleVBaseReq3.getCommandType().equalsIgnoreCase(dataType)) {
                    return;
                }
                Map<String, String> data2 = getData(map);
                JStyleVBaseReq jStyleVBaseReq4 = this.k;
                if (jStyleVBaseReq4 != null) {
                    jstyleBaseRes.setBaseReq(jStyleVBaseReq4);
                    jstyleBaseRes.obj = data2;
                    this.k.getResponseListener().onResponse(jstyleBaseRes);
                    return;
                }
                return;
            case '\b':
                Map<String, String> data3 = getData(map);
                JstyleLiveResponse jstyleLiveResponse = new JstyleLiveResponse();
                jstyleLiveResponse.setDataType(BleConst.RealTimeStep);
                jstyleLiveResponse.setObj(data3);
                BleEventBusManager.getInstance().getEventBus().post(jstyleLiveResponse);
                return;
            case '\t':
                JStyleVBaseReq jStyleVBaseReq5 = this.k;
                if (jStyleVBaseReq5 == null || !jStyleVBaseReq5.getCommandType().equalsIgnoreCase(dataType)) {
                    return;
                }
                this.h.addAll((List) map.get(DeviceKey.Data));
                this.i++;
                if (end) {
                    this.i = 0;
                    JStyleVBaseReq jStyleVBaseReq6 = this.k;
                    if (jStyleVBaseReq6 != null) {
                        jstyleBaseRes.setBaseReq(jStyleVBaseReq6);
                        jstyleBaseRes.obj = this.h.clone();
                        this.k.getResponseListener().onResponse(jstyleBaseRes);
                    }
                    this.h.clear();
                }
                if (this.i == this.j) {
                    this.i = 0;
                    if (end) {
                        this.i = 0;
                        JStyleVBaseReq jStyleVBaseReq7 = this.k;
                        if (jStyleVBaseReq7 != null) {
                            jstyleBaseRes.setBaseReq(jStyleVBaseReq7);
                            jstyleBaseRes.obj = this.h.clone();
                            this.k.getResponseListener().onResponse(jstyleBaseRes);
                        }
                        this.h.clear();
                        return;
                    }
                    JStyleVTodayFitnessValueReq jStyleVTodayFitnessValueReq = new JStyleVTodayFitnessValueReq();
                    jStyleVTodayFitnessValueReq.setMode((byte) JStyleConstants.Constants.getMODE_CONTINUE());
                    jStyleVTodayFitnessValueReq.setReqId(this.k.getReqId());
                    jStyleVTodayFitnessValueReq.setResponseListener(this.k.getResponseListener());
                    writeRequest(jStyleVTodayFitnessValueReq);
                    return;
                }
                return;
            case '\n':
                this.h.addAll((List) map.get(DeviceKey.Data));
                this.i++;
                if (end) {
                    this.i = 0;
                    JStyleVBaseReq jStyleVBaseReq8 = this.k;
                    if (jStyleVBaseReq8 != null) {
                        jstyleBaseRes.setBaseReq(jStyleVBaseReq8);
                        jstyleBaseRes.obj = this.h.clone();
                        this.k.getResponseListener().onResponse(jstyleBaseRes);
                    }
                    this.h.clear();
                }
                if (this.i == this.j) {
                    this.i = 0;
                    if (end) {
                        this.i = 0;
                        JStyleVBaseReq jStyleVBaseReq9 = this.k;
                        if (jStyleVBaseReq9 != null) {
                            jstyleBaseRes.setBaseReq(jStyleVBaseReq9);
                            jstyleBaseRes.obj = this.h.clone();
                            this.k.getResponseListener().onResponse(jstyleBaseRes);
                        }
                        this.h.clear();
                        return;
                    }
                    JStyleVStepsDataReq jStyleVStepsDataReq = new JStyleVStepsDataReq();
                    jStyleVStepsDataReq.setMode((byte) JStyleConstants.Constants.getMODE_CONTINUE());
                    jStyleVStepsDataReq.setReqId(this.k.getReqId());
                    jStyleVStepsDataReq.setResponseListener(this.k.getResponseListener());
                    writeRequest(jStyleVStepsDataReq);
                    return;
                }
                return;
            case 11:
                JStyleVBaseReq jStyleVBaseReq10 = this.k;
                if (jStyleVBaseReq10 == null || !jStyleVBaseReq10.getCommandType().equalsIgnoreCase(dataType)) {
                    return;
                }
                this.h.addAll((List) map.get(DeviceKey.Data));
                this.i++;
                if (end) {
                    this.i = 0;
                    JStyleVBaseReq jStyleVBaseReq11 = this.k;
                    if (jStyleVBaseReq11 != null) {
                        jstyleBaseRes.setBaseReq(jStyleVBaseReq11);
                        jstyleBaseRes.obj = this.h.clone();
                        this.k.getResponseListener().onResponse(jstyleBaseRes);
                    }
                    this.h.clear();
                }
                if (this.i == this.j) {
                    this.i = 0;
                    if (end) {
                        this.i = 0;
                        JStyleVBaseReq jStyleVBaseReq12 = this.k;
                        if (jStyleVBaseReq12 != null) {
                            jstyleBaseRes.setBaseReq(jStyleVBaseReq12);
                            jstyleBaseRes.obj = this.h.clone();
                            this.k.getResponseListener().onResponse(jstyleBaseRes);
                        }
                        this.h.clear();
                        return;
                    }
                    JStyleVSleepDataReq jStyleVSleepDataReq = new JStyleVSleepDataReq();
                    jStyleVSleepDataReq.setMode((byte) JStyleConstants.Constants.getMODE_CONTINUE());
                    jStyleVSleepDataReq.setReqId(this.k.getReqId());
                    jStyleVSleepDataReq.setResponseListener(this.k.getResponseListener());
                    writeRequest(jStyleVSleepDataReq);
                    return;
                }
                return;
            case '\f':
                JStyleVBaseReq jStyleVBaseReq13 = this.k;
                if (jStyleVBaseReq13 == null || !jStyleVBaseReq13.getCommandType().equalsIgnoreCase(dataType)) {
                    return;
                }
                LogHelper.d("GetDynamicHR", "" + map.get(DeviceKey.Data));
                this.h.addAll((List) map.get(DeviceKey.Data));
                this.i = this.i + 1;
                if (end) {
                    this.i = 0;
                    jstyleBaseRes.setBaseReq(this.k);
                    jstyleBaseRes.obj = this.h.clone();
                    this.k.getResponseListener().onResponse(jstyleBaseRes);
                    this.h.clear();
                }
                if (this.i == this.j) {
                    this.i = 0;
                    if (end) {
                        this.i = 0;
                        jstyleBaseRes.setBaseReq(this.k);
                        jstyleBaseRes.obj = this.h.clone();
                        this.k.getResponseListener().onResponse(jstyleBaseRes);
                        this.h.clear();
                        return;
                    }
                    JStyleVHRDataReq jStyleVHRDataReq = new JStyleVHRDataReq();
                    jStyleVHRDataReq.setMode((byte) JStyleConstants.Constants.getMODE_CONTINUE());
                    jStyleVHRDataReq.setReqId(this.k.getReqId());
                    jStyleVHRDataReq.setResponseListener(this.k.getResponseListener());
                    writeRequest(jStyleVHRDataReq);
                    return;
                }
                return;
            case '\r':
                JStyleVBaseReq jStyleVBaseReq14 = this.k;
                if (jStyleVBaseReq14 == null || !jStyleVBaseReq14.getCommandType().equalsIgnoreCase(dataType)) {
                    return;
                }
                this.h.addAll((List) map.get(DeviceKey.Data));
                this.i++;
                if (end) {
                    this.i = 0;
                    JStyleVBaseReq jStyleVBaseReq15 = this.k;
                    if (jStyleVBaseReq15 != null) {
                        jstyleBaseRes.setBaseReq(jStyleVBaseReq15);
                        jstyleBaseRes.obj = this.h.clone();
                        this.k.getResponseListener().onResponse(jstyleBaseRes);
                    }
                    this.h.clear();
                }
                if (this.i == this.j) {
                    this.i = 0;
                    if (end) {
                        this.i = 0;
                        JStyleVBaseReq jStyleVBaseReq16 = this.k;
                        if (jStyleVBaseReq16 != null) {
                            jstyleBaseRes.setBaseReq(jStyleVBaseReq16);
                            jstyleBaseRes.obj = this.h.clone();
                            this.k.getResponseListener().onResponse(jstyleBaseRes);
                        }
                        this.h.clear();
                        return;
                    }
                    JStyleVHRDataReq jStyleVHRDataReq2 = new JStyleVHRDataReq();
                    jStyleVHRDataReq2.setMode((byte) JStyleConstants.Constants.getMODE_CONTINUE());
                    jStyleVHRDataReq2.setReqId(this.k.getReqId());
                    jStyleVHRDataReq2.setResponseListener(this.k.getResponseListener());
                    writeRequest(jStyleVHRDataReq2);
                    return;
                }
                return;
            case 14:
                JStyleVBaseReq jStyleVBaseReq17 = this.k;
                if (jStyleVBaseReq17 == null || !jStyleVBaseReq17.getCommandType().equalsIgnoreCase(dataType)) {
                    return;
                }
                this.h.addAll((List) map.get(DeviceKey.Data));
                this.i++;
                if (end) {
                    this.i = 0;
                    jstyleBaseRes.setBaseReq(this.k);
                    jstyleBaseRes.obj = this.h.clone();
                    this.k.getResponseListener().onResponse(jstyleBaseRes);
                    this.h.clear();
                }
                if (this.i == this.j) {
                    this.i = 0;
                    if (end) {
                        this.i = 0;
                        jstyleBaseRes.setBaseReq(this.k);
                        jstyleBaseRes.obj = this.h.clone();
                        this.k.getResponseListener().onResponse(jstyleBaseRes);
                        this.h.clear();
                        return;
                    }
                    JStyleVActivityHistoryDataReq jStyleVActivityHistoryDataReq = new JStyleVActivityHistoryDataReq();
                    jStyleVActivityHistoryDataReq.setMode((byte) JStyleConstants.Constants.getMODE_CONTINUE());
                    jStyleVActivityHistoryDataReq.setReqId(this.k.getReqId());
                    jStyleVActivityHistoryDataReq.setResponseListener(this.k.getResponseListener());
                    writeRequest(jStyleVActivityHistoryDataReq);
                    return;
                }
                return;
            case 15:
                if (Integer.parseInt(getData(map).get("enterActivityModeSuccess")) == 0 || (jStyleVBaseReq = this.k) == null || !jStyleVBaseReq.getCommandType().equalsIgnoreCase(dataType)) {
                    return;
                }
                jstyleBaseRes.setBaseReq(this.k);
                this.k.getResponseListener().onResponse(jstyleBaseRes);
                return;
            case 16:
                JStyleVBaseReq jStyleVBaseReq18 = this.k;
                if (jStyleVBaseReq18 == null || !jStyleVBaseReq18.getCommandType().equalsIgnoreCase(dataType)) {
                    return;
                }
                this.h.addAll((List) map.get(DeviceKey.Data));
                this.i++;
                if (end) {
                    this.i = 0;
                    JStyleVBaseReq jStyleVBaseReq19 = this.k;
                    if (jStyleVBaseReq19 != null) {
                        jstyleBaseRes.setBaseReq(jStyleVBaseReq19);
                        jstyleBaseRes.obj = this.h.clone();
                        this.k.getResponseListener().onResponse(jstyleBaseRes);
                    }
                    this.h.clear();
                }
                if (this.i == this.j) {
                    this.i = 0;
                    if (end) {
                        this.i = 0;
                        JStyleVBaseReq jStyleVBaseReq20 = this.k;
                        if (jStyleVBaseReq20 != null) {
                            jstyleBaseRes.setBaseReq(jStyleVBaseReq20);
                            jstyleBaseRes.obj = this.h.clone();
                            this.k.getResponseListener().onResponse(jstyleBaseRes);
                        }
                        this.h.clear();
                        return;
                    }
                    JStyleVHRVDataReq jStyleVHRVDataReq = new JStyleVHRVDataReq();
                    jStyleVHRVDataReq.setMode((byte) JStyleConstants.Constants.getMODE_CONTINUE());
                    jStyleVHRVDataReq.setReqId(this.k.getReqId());
                    jStyleVHRVDataReq.setResponseListener(this.k.getResponseListener());
                    writeRequest(jStyleVHRVDataReq);
                    return;
                }
                return;
            case 17:
                JStyleVBaseReq jStyleVBaseReq21 = this.k;
                if (jStyleVBaseReq21 == null || !jStyleVBaseReq21.getCommandType().equalsIgnoreCase(dataType)) {
                    return;
                }
                this.h.addAll((List) map.get(DeviceKey.Data));
                this.i++;
                if (end) {
                    this.i = 0;
                    JStyleVBaseReq jStyleVBaseReq22 = this.k;
                    if (jStyleVBaseReq22 != null) {
                        jstyleBaseRes.setBaseReq(jStyleVBaseReq22);
                        jstyleBaseRes.obj = this.h.clone();
                        this.k.getResponseListener().onResponse(jstyleBaseRes);
                    }
                    this.h.clear();
                }
                if (this.i == this.j) {
                    this.i = 0;
                    if (end) {
                        this.i = 0;
                        JStyleVBaseReq jStyleVBaseReq23 = this.k;
                        if (jStyleVBaseReq23 != null) {
                            jstyleBaseRes.setBaseReq(jStyleVBaseReq23);
                            jstyleBaseRes.obj = this.h.clone();
                            this.k.getResponseListener().onResponse(jstyleBaseRes);
                        }
                        this.h.clear();
                        return;
                    }
                    JStyleVTemperatureDataReq jStyleVTemperatureDataReq = new JStyleVTemperatureDataReq();
                    jStyleVTemperatureDataReq.setMode((byte) JStyleConstants.Constants.getMODE_CONTINUE());
                    jStyleVTemperatureDataReq.setReqId(this.k.getReqId());
                    jStyleVTemperatureDataReq.setResponseListener(this.k.getResponseListener());
                    writeRequest(jStyleVTemperatureDataReq);
                    return;
                }
                return;
            case 18:
                JStyleVBaseReq jStyleVBaseReq24 = this.k;
                if (jStyleVBaseReq24 == null || !jStyleVBaseReq24.getCommandType().equalsIgnoreCase(dataType)) {
                    return;
                }
                this.h.addAll((List) map.get(DeviceKey.Data));
                this.i++;
                if (end) {
                    this.i = 0;
                    JStyleVBaseReq jStyleVBaseReq25 = this.k;
                    if (jStyleVBaseReq25 != null) {
                        jstyleBaseRes.setBaseReq(jStyleVBaseReq25);
                        jstyleBaseRes.obj = this.h.clone();
                        this.k.getResponseListener().onResponse(jstyleBaseRes);
                    }
                    this.h.clear();
                }
                if (this.i == this.j) {
                    this.i = 0;
                    if (end) {
                        this.i = 0;
                        JStyleVBaseReq jStyleVBaseReq26 = this.k;
                        if (jStyleVBaseReq26 != null) {
                            jstyleBaseRes.setBaseReq(jStyleVBaseReq26);
                            jstyleBaseRes.obj = this.h.clone();
                            this.k.getResponseListener().onResponse(jstyleBaseRes);
                        }
                        this.h.clear();
                        return;
                    }
                    JStyleVSpo2DataReq jStyleVSpo2DataReq = new JStyleVSpo2DataReq();
                    jStyleVSpo2DataReq.setMode((byte) JStyleConstants.Constants.getMODE_CONTINUE());
                    jStyleVSpo2DataReq.setReqId(this.k.getReqId());
                    jStyleVSpo2DataReq.setResponseListener(this.k.getResponseListener());
                    writeRequest(jStyleVSpo2DataReq);
                    return;
                }
                return;
            case 19:
                Map<String, String> data4 = getData(map);
                JstyleLiveResponse jstyleLiveResponse2 = new JstyleLiveResponse();
                jstyleLiveResponse2.setDataType("82");
                jstyleLiveResponse2.setObj(data4);
                BleEventBusManager.getInstance().getEventBus().post(jstyleLiveResponse2);
                return;
            default:
                return;
        }
    }

    public void dataCallback(byte[] bArr) {
    }

    public void disconnect() {
        this.f4022a = false;
        BleData bleData = new BleData();
        bleData.setAction("com.jstylelife.ble.service.ACTION_GATT_DISCONNECTED");
        RxBus.getInstance().post(bleData);
        if (this.e != null) {
            if (Build.VERSION.SDK_INT >= 31 && ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH_CONNECT") != 0) {
                return;
            }
            this.e.disconnect();
            this.e.close();
            this.e = null;
        }
        this.l = CloveBleState.BleState.DISCONNECTED;
    }

    public void disconnectAndForget() {
        b();
        disconnect();
        PreferenceManagerAbstract.getInstance(getApplicationContext()).saveConnectedDeviceMAcAddress("");
        PreferenceManagerAbstract.getInstance(getApplicationContext()).saveConnectionType(ConnectionType.DONT_CONNECT_ON_DISCONNECT.name());
        stopForeground(true);
        stopSelf();
    }

    public void disconnectAndRetainMacAddress() {
        b();
        disconnect();
        PreferenceManagerAbstract.getInstance(getApplicationContext()).saveConnectionType(ConnectionType.DONT_CONNECT_ON_DISCONNECT.name());
        stopForeground(true);
        stopSelf();
    }

    public CloveBleState.BleState getConnectionState() {
        return this.l;
    }

    public boolean getEnd(Map<String, Object> map) {
        return ((Boolean) map.get(DeviceKey.End)).booleanValue();
    }

    public List<BluetoothGattService> getSupportedGattServices() {
        BluetoothGatt bluetoothGatt = this.e;
        if (bluetoothGatt == null) {
            return null;
        }
        return bluetoothGatt.getServices();
    }

    public void initBluetoothDevice(String str, Context context, boolean z2, BleConnectionListener bleConnectionListener) {
        if (bleConnectionListener != null) {
            this.bleConnectionListener = bleConnectionListener;
        }
        this.f4022a = z2;
        PreferenceManagerAbstract.getInstance(getApplicationContext()).saveConnectedDeviceMAcAddress(str);
        PreferenceManagerAbstract.getInstance(getApplicationContext()).saveConnectedDeviceMAcAddress(str);
        PreferenceManagerAbstract.getInstance(getApplicationContext()).saveConnectionType(ConnectionType.RECONNECT_ON_DISCONNECT.name());
        this.fastconnect = false;
        this.n = str;
        this.o = context;
        BluetoothDevice remoteDevice = this.d.getRemoteDevice(str);
        if (isConnected()) {
            return;
        }
        BluetoothGatt bluetoothGatt = this.e;
        if (bluetoothGatt != null) {
            refreshDeviceCache(bluetoothGatt);
            this.e = null;
        }
        if (Build.VERSION.SDK_INT >= 31) {
            if (ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH_CONNECT") != 0) {
                return;
            }
            this.e = remoteDevice.connectGatt(context, false, this.s, 2);
        } else {
            this.e = remoteDevice.connectGatt(context, false, this.s);
        }
        if (this.e == null) {
            PrintStream printStream = System.out;
            printStream.println(remoteDevice.getAddress() + "gatt is null");
        }
        BleConnectionListener bleConnectionListener2 = this.bleConnectionListener;
        if (bleConnectionListener2 != null) {
            bleConnectionListener2.Connecting();
        }
        a(CloveBleState.BleState.CONNECTING, true);
    }

    public boolean isConnected() {
        return this.l == CloveBleState.BleState.CONNECTED;
    }

    public void nextQueue() {
        Queue<byte[]> queue = this.t;
        byte[] poll = queue != null ? queue.poll() : null;
        if (poll != null) {
            writeValue(poll);
        }
    }

    public void offerValue(byte[] bArr) {
        this.t.offer(bArr);
    }

    /* JADX WARN: Code restructure failed: missing block: B:5:0x000e, code lost:
        if (r1 == null) goto L5;
     */
    @Override // android.app.Service
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.os.IBinder onBind(android.content.Intent r1) {
        /*
            r0 = this;
            android.bluetooth.BluetoothManager r1 = r0.c
            if (r1 != 0) goto L11
            java.lang.String r1 = "bluetooth"
            java.lang.Object r1 = r0.getSystemService(r1)
            android.bluetooth.BluetoothManager r1 = (android.bluetooth.BluetoothManager) r1
            r0.c = r1
            if (r1 != 0) goto L11
            goto L19
        L11:
            android.bluetooth.BluetoothManager r1 = r0.c
            android.bluetooth.BluetoothAdapter r1 = r1.getAdapter()
            r0.d = r1
        L19:
            android.os.IBinder r1 = r0.b
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.bleabstract.services.jstyle2301.JStyle2208ABleService.onBind(android.content.Intent):android.os.IBinder");
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        a();
        subscribe();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        unSubscribe(this.g);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent == null) {
            try {
                Intent intent2 = new Intent(this, JStyle2208ABleService.class);
                if (Build.VERSION.SDK_INT >= 26) {
                    startForegroundService(intent2);
                    return 3;
                }
                startService(intent2);
                return 3;
            } catch (Exception e) {
                e.printStackTrace();
                BleApiUtils.checkExceptionAndShowNotification(e, this);
            }
        }
        String connectedDeviceMacAddress = PreferenceManagerAbstract.getInstance(this).getConnectedDeviceMacAddress();
        if (!AppUtils.isEmpty(connectedDeviceMacAddress)) {
            if (BleUtils.getConnectionType(getApplicationContext()) == ConnectionType.RECONNECT_ON_DISCONNECT) {
                this.f4022a = true;
            }
            initBluetoothDevice(connectedDeviceMacAddress, this, this.f4022a, null);
        }
        a();
        return super.onStartCommand(intent, i, i2);
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    public void readRssi(BluetoothDevice bluetoothDevice) {
        if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH_CONNECT") == 0) {
            this.e.readRemoteRssi();
        }
    }

    public void readValue(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (this.e == null) {
            return;
        }
        if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH_CONNECT") == 0) {
            this.e.readCharacteristic(bluetoothGattCharacteristic);
        }
    }

    public boolean refreshDeviceCache(BluetoothGatt bluetoothGatt) {
        try {
            Method method = bluetoothGatt.getClass().getMethod("refresh", new Class[0]);
            if (method != null) {
                return ((Boolean) method.invoke(bluetoothGatt, new Object[0])).booleanValue();
            }
        } catch (Exception unused) {
        }
        return false;
    }

    public void restartService() {
        b();
        disconnect();
        PreferenceManagerAbstract.getInstance(getApplicationContext()).saveConnectionType(ConnectionType.RECONNECT_ON_DISCONNECT.name());
        stopForeground(true);
        stopSelf();
        LogHelper.d("JStyle2208ABleService", "Service restartService", ModuleNames.BLEABSTRACT.getModuleName());
    }

    public void setCharacteristicNotification(boolean z2) {
        BluetoothGattService service;
        BluetoothGattCharacteristic characteristic;
        BluetoothGatt bluetoothGatt = this.e;
        if (bluetoothGatt == null || (service = bluetoothGatt.getService(w)) == null || (characteristic = service.getCharacteristic(y)) == null) {
            return;
        }
        if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH_CONNECT") == 0) {
            this.e.setCharacteristicNotification(characteristic, z2);
            try {
                Thread.sleep(20L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            BluetoothGattDescriptor descriptor = characteristic.getDescriptor(v);
            if (descriptor == null) {
                return;
            }
            descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
            BluetoothGatt bluetoothGatt2 = this.e;
            if (bluetoothGatt2 == null) {
                return;
            }
            bluetoothGatt2.writeDescriptor(descriptor);
        }
    }

    @SuppressLint({"MissingPermission"})
    public void startScan(boolean z2) {
        if (this.d.isEnabled()) {
            if (this.p) {
                a(true);
            } else {
                initBluetoothDevice(this.n, this.o, this.f4022a, this.bleConnectionListener);
            }
            this.p = !this.p;
        }
    }

    public void stopService() {
        disconnectAndForget();
    }

    public void stopServiceRetainMacAddress() {
        disconnectAndRetainMacAddress();
    }

    public void subscribe() {
        this.g = RxBus.getInstance().toObservable(BleData.class).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<BleData>() { // from class: com.coveiot.android.bleabstract.services.jstyle2301.JStyle2208ABleService.3
            @Override // io.reactivex.functions.Consumer
            public void accept(BleData bleData) throws Exception {
                BleData bleData2 = bleData;
                String action = bleData2.getAction();
                LogHelper.d("JStyle2208ABleService", "Data call back");
                if (action.equals("com.jstylelife.ble.service.ACTION_DATA_AVAILABLE")) {
                    BleSDK.DataParsingWithData(bleData2.getValue(), JStyle2208ABleService.this);
                }
            }
        });
        getApplicationContext().registerReceiver(this.u, new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED"));
    }

    public void unSubscribe(Disposable disposable) {
        if (disposable == null || disposable.isDisposed()) {
            return;
        }
        disposable.dispose();
    }

    public void writeRequest(JStyleVBaseReq jStyleVBaseReq) {
        this.k = jStyleVBaseReq;
        LogHelper.d("JStyle2208ABleService", "Sending request: " + jStyleVBaseReq);
        writeValue(this.k.getCommandBytes());
        boolean z2 = jStyleVBaseReq instanceof JStyleVRealTimeWalkReq;
        if (z2 && this.k != null) {
            JstyleBaseRes jstyleBaseRes = new JstyleBaseRes();
            jstyleBaseRes.setBaseReq(this.k);
            this.k.getResponseListener().onResponse(jstyleBaseRes);
        }
        if (!z2 || this.k == null) {
            return;
        }
        JstyleBaseRes jstyleBaseRes2 = new JstyleBaseRes();
        jstyleBaseRes2.setBaseReq(this.k);
        this.k.getResponseListener().onResponse(jstyleBaseRes2);
    }

    public void writeValue(final byte[] bArr) {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.coveiot.android.bleabstract.services.jstyle2301.JStyle2208ABleService.5
            @Override // java.lang.Runnable
            public void run() {
                BluetoothGattService service;
                BluetoothGattCharacteristic characteristic;
                BluetoothGatt bluetoothGatt = JStyle2208ABleService.this.e;
                if (bluetoothGatt == null || bArr == null || (service = bluetoothGatt.getService(JStyle2208ABleService.w)) == null || (characteristic = service.getCharacteristic(JStyle2208ABleService.x)) == null) {
                    return;
                }
                if (bArr[0] == 71) {
                    JStyle2208ABleService.this.f4022a = false;
                }
                characteristic.setValue(bArr);
                ResolveData.byte2Hex(bArr);
                if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(JStyle2208ABleService.this, "android.permission.BLUETOOTH_CONNECT") == 0) {
                    JStyle2208ABleService.this.e.writeCharacteristic(characteristic);
                }
            }
        }, 500L);
    }

    @SuppressLint({"MissingPermission"})
    public final void a(boolean z2) {
        if (z2) {
            if (this.q) {
                return;
            }
            this.f.postDelayed(new Runnable() { // from class: com.coveiot.android.bleabstract.services.jstyle2301.JStyle2208ABleService.1
                @Override // java.lang.Runnable
                public void run() {
                    JStyle2208ABleService jStyle2208ABleService = JStyle2208ABleService.this;
                    jStyle2208ABleService.d.stopLeScan(jStyle2208ABleService.r);
                    JStyle2208ABleService.this.q = false;
                    JStyle2208ABleService.this.startScan(true);
                }
            }, 20000L);
            this.fastconnect = false;
            this.d.startLeScan(this.r);
        } else if (this.q) {
            this.d.stopLeScan(this.r);
            this.f.removeCallbacksAndMessages(null);
        }
        this.q = z2;
    }

    public final void b() {
        if (this.q) {
            if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH_SCAN") == 0) {
                this.d.stopLeScan(this.r);
                this.q = false;
            }
        }
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

    public void disconnect(String str) {
        ArrayList arrayList = new ArrayList();
        Iterator<BluetoothGatt> it = z.iterator();
        while (it.hasNext()) {
            BluetoothGatt next = it.next();
            if (next != null && next.getDevice().getAddress().equals(str)) {
                arrayList.add(next);
                if (Build.VERSION.SDK_INT >= 31 && ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH_CONNECT") != 0) {
                    return;
                }
                next.close();
            }
        }
        z.removeAll(arrayList);
    }

    public final void a(final CloveBleState.BleState bleState, boolean z2) {
        Handler handler;
        this.l = bleState;
        if (!z2 || (handler = this.m) == null) {
            return;
        }
        handler.removeCallbacksAndMessages(null);
        this.m.post(new Runnable(this) { // from class: com.coveiot.android.bleabstract.services.jstyle2301.JStyle2208ABleService.6
            @Override // java.lang.Runnable
            public void run() {
                BleEventBusManager.getInstance().getEventBus().post(new CloveBleState(bleState));
            }
        });
    }
}