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
import android.bluetooth.le.ScanResult;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import com.clevertap.android.sdk.Constants;
import com.coveiot.android.bleabstract.BleApiUtils;
import com.coveiot.android.bleabstract.NotificationInfo;
import com.coveiot.android.bleabstract.R;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.DeviceType;
import com.coveiot.android.bleabstract.response.BloodPressureMeasurement;
import com.coveiot.android.bleabstract.response.ConnectionError;
import com.coveiot.android.bpsdk.CloveOmronBpBleState;
import com.coveiot.android.bpsdk.events.ConnectionTypeOmronBP;
import com.coveiot.android.bpsdk.utils.OmronBpConstants;
import com.coveiot.android.bpsdk.utils.OmronBpPreferenceManager;
import com.coveiot.android.bpsdk.utils.OmronBpUtils;
import com.coveiot.sdk.ble.api.BleUUID;
import com.coveiot.sdk.ble.api.ResponseListener;
import com.coveiot.sdk.ble.api.error.Error;
import com.coveiot.sdk.ble.api.error.Type;
import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.eventbus.BleEventBusManager;
import com.coveiot.sdk.ble.events.ProcessNextItemEvent;
import com.coveiot.sdk.ble.events.ResponseEvent;
import com.coveiot.sdk.ble.events.ResponseType;
import com.coveiot.sdk.ble.model.CommandObject;
import com.coveiot.sdk.ble.parser.ProtocolParser;
import com.coveiot.sdk.ble.utils.BleUtils;
import com.coveiot.sdk.ble.utils.CommandNames;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.UUID;
/* loaded from: classes2.dex */
public class BpMeasurementService extends Service {
    public static final String ACTION_STOP_SERVICE = "action_stop_service";
    public static final String EMPTY_STRING = "";
    public static final String y = BpMeasurementService.class.getSimpleName();
    public BluetoothManager b;
    public BluetoothAdapter c;
    public CommandObject currentCommand;
    public CommandObject currentDeviceInfoCommand;
    public BluetoothGatt d;
    public String e;
    public ConnectionTypeOmronBP f;
    public Handler j;
    public Handler k;
    public Handler l;
    public BluetoothDevice mBluetoothDevice;
    public Handler n;
    public boolean p;
    public Handler q;
    public HandlerThread r;
    public BluetoothGattCharacteristic readWriteCharacteristic;
    public ConnectionError u;
    public volatile boolean w;

    /* renamed from: a  reason: collision with root package name */
    public final IBinder f3684a = new BtServiceBinder();
    public LinkedList<CommandObject> g = new LinkedList<>();
    public CloveOmronBpBleState.BleState bluetoothConnectionStatus = CloveOmronBpBleState.BleState.DISCONNECTED;
    public Handler h = new Handler();
    public final Handler i = new Handler(Looper.getMainLooper());
    public int m = 0;
    public long o = -1;
    @RequiresApi(21)
    public ScanCallback s = new ScanCallback() { // from class: com.coveiot.android.bleabstract.services.BpMeasurementService.3
        @Override // android.bluetooth.le.ScanCallback
        public void onScanFailed(int i) {
            super.onScanFailed(i);
            LogHelper.e("onScanFailed", "Error code " + i, ModuleNames.BLEABSTRACT.getModuleName());
            if (i != 1) {
                if (i == 2) {
                    BleApiManager.getInstance(BpMeasurementService.this).getBleApi().restartService();
                    return;
                }
                BpMeasurementService bpMeasurementService = BpMeasurementService.this;
                CloveOmronBpBleState.BleState bleState = CloveOmronBpBleState.BleState.DISCONNECTED;
                String str = BpMeasurementService.y;
                bpMeasurementService.a(bleState, true);
            }
        }

        @Override // android.bluetooth.le.ScanCallback
        public void onScanResult(int i, ScanResult scanResult) {
            super.onScanResult(i, scanResult);
            try {
                if (Build.VERSION.SDK_INT < 21 || !scanResult.getDevice().getAddress().equalsIgnoreCase(BpMeasurementService.this.e)) {
                    return;
                }
                String str = BpMeasurementService.y;
                ModuleNames moduleNames = ModuleNames.BLEABSTRACT;
                LogHelper.d(str, "Scan stopped, device found", moduleNames.getModuleName());
                BpMeasurementService.this.stopScan();
                BpMeasurementService.this.a(CloveOmronBpBleState.BleState.CONNECTING, true);
                Handler handler = BpMeasurementService.this.j;
                if (handler != null) {
                    handler.removeCallbacks(null);
                }
                LogHelper.d(str, "connect to device after scan", moduleNames.getModuleName());
                BpMeasurementService.this.a(scanResult.getDevice());
            } catch (Exception e) {
                LogHelper.e(BpMeasurementService.y, e.toString(), ModuleNames.BLEABSTRACT.getModuleName());
            }
        }
    };
    public final BroadcastReceiver t = new BroadcastReceiver() { // from class: com.coveiot.android.bleabstract.services.BpMeasurementService.4
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("android.bluetooth.adapter.action.STATE_CHANGED")) {
                int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE);
                if (intExtra == 3 || intExtra == 10) {
                    BpMeasurementService bpMeasurementService = BpMeasurementService.this;
                    CloveOmronBpBleState.BleState bleState = CloveOmronBpBleState.BleState.DISCONNECTED;
                    String str = BpMeasurementService.y;
                    bpMeasurementService.a(bleState, true);
                    BpMeasurementService.this.stopScan();
                    BpMeasurementService.this.closeGattConnection();
                    BpMeasurementService.this.clearAllServiceParameters();
                }
            } else if (action.equalsIgnoreCase("action_stop_service")) {
                try {
                    BpMeasurementService.this.closeGattConnection();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                BpMeasurementService bpMeasurementService2 = BpMeasurementService.this;
                CloveOmronBpBleState.BleState bleState2 = CloveOmronBpBleState.BleState.DISCONNECTED;
                String str2 = BpMeasurementService.y;
                bpMeasurementService2.a(bleState2, true);
                BpMeasurementService.this.clearAllServiceParameters();
            }
        }
    };
    public BluetoothGattCallback v = new BluetoothGattCallback() { // from class: com.coveiot.android.bleabstract.services.BpMeasurementService.5
        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            super.onCharacteristicChanged(bluetoothGatt, bluetoothGattCharacteristic);
            String str = BpMeasurementService.y;
            LogHelper.d(str, "onCharacteristic");
            BpMeasurementService bpMeasurementService = BpMeasurementService.this;
            if (bpMeasurementService.d == null) {
                LogHelper.e(str, bpMeasurementService.getString(R.string.gatt_null));
            } else if (bluetoothGattCharacteristic.getValue().length > 0) {
                LogHelper.d(str, BpMeasurementService.this.getString(R.string.oncharacterstic_chaned) + "2", bluetoothGattCharacteristic.getValue().toString());
                BpMeasurementService bpMeasurementService2 = BpMeasurementService.this;
                Handler handler = bpMeasurementService2.h;
                if (handler == null) {
                    LogHelper.e(str, bpMeasurementService2.getString(R.string.reconnect_handler_null));
                } else {
                    handler.post(new BPPostRunnable(bluetoothGattCharacteristic.getValue()));
                }
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicRead(BluetoothGatt bluetoothGatt, final BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            super.onCharacteristicRead(bluetoothGatt, bluetoothGattCharacteristic, i);
            BpMeasurementService.this.q.post(new Runnable() { // from class: com.coveiot.android.bleabstract.services.BpMeasurementService.5.3
                @Override // java.lang.Runnable
                public void run() {
                    ProtocolParser.getInstance().onReadFromService(BpMeasurementService.this.getApplicationContext(), bluetoothGattCharacteristic, BpMeasurementService.this.currentCommand);
                }
            });
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onConnectionStateChange(final BluetoothGatt bluetoothGatt, int i, int i2) {
            BpMeasurementService.this.o = System.currentTimeMillis();
            BpMeasurementService bpMeasurementService = BpMeasurementService.this;
            bpMeasurementService.f = OmronBpUtils.getConnectionType(bpMeasurementService.getApplicationContext());
            if (i == 0) {
                if (i2 == 2) {
                    LogHelper.d(BpMeasurementService.y, "onConnectionStateChange: Connected", ModuleNames.BLEABSTRACT.getModuleName());
                    BpMeasurementService bpMeasurementService2 = BpMeasurementService.this;
                    bpMeasurementService2.u = null;
                    bpMeasurementService2.n.postDelayed(new Runnable() { // from class: com.coveiot.android.bleabstract.services.BpMeasurementService.5.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(BpMeasurementService.this, "android.permission.BLUETOOTH_CONNECT") == 0) {
                                bluetoothGatt.discoverServices();
                            }
                        }
                    }, 1000L);
                    return;
                } else if (i2 == 0) {
                    String str = BpMeasurementService.y;
                    String string = BpMeasurementService.this.getString(R.string.disconnected_connection_state);
                    ModuleNames moduleNames = ModuleNames.BLEABSTRACT;
                    LogHelper.d(str, string, moduleNames.getModuleName());
                    BpMeasurementService.this.a(CloveOmronBpBleState.BleState.DISCONNECTED, true);
                    BpMeasurementService bpMeasurementService3 = BpMeasurementService.this;
                    if (bpMeasurementService3.f == ConnectionTypeOmronBP.RECONNECT_ON_DISCONNECT) {
                        LogHelper.d(str, bpMeasurementService3.getString(R.string.init_Reconnect), moduleNames.getModuleName());
                        BpMeasurementService.this.reconnectToConfiguredDevice();
                        return;
                    }
                    bpMeasurementService3.closeGattConnection();
                    return;
                } else {
                    return;
                }
            }
            BpMeasurementService.this.u = new ConnectionError(i, System.currentTimeMillis());
            String str2 = BpMeasurementService.y;
            ModuleNames moduleNames2 = ModuleNames.BLEABSTRACT;
            LogHelper.e(str2, "onConnectionStateChange Error GATT.  Error id : " + i, moduleNames2.getModuleName());
            if (i == 133) {
                if (BpMeasurementService.this.f == ConnectionTypeOmronBP.RECONNECT_ON_DISCONNECT) {
                    LogHelper.d(str2, "ConnectionTypeOmronBP is ++ " + BpMeasurementService.this.f, moduleNames2.getModuleName());
                    BpMeasurementService.this.m = 0;
                    LogHelper.d(str2, "133 error appeared 1 time  disconnect event and scan device", moduleNames2.getModuleName());
                    BpMeasurementService.this.a(CloveOmronBpBleState.BleState.DISCONNECTED, true);
                    return;
                }
                LogHelper.d(str2, "ConnectionTypeOmronBP is ++ " + BpMeasurementService.this.f, moduleNames2.getModuleName());
                BpMeasurementService bpMeasurementService4 = BpMeasurementService.this;
                bpMeasurementService4.m = 0;
                bpMeasurementService4.a(CloveOmronBpBleState.BleState.DISCONNECTED, true);
                return;
            }
            LogHelper.d(str2, " onConnectionStateChange error code " + i, moduleNames2.getModuleName());
            BpMeasurementService bpMeasurementService5 = BpMeasurementService.this;
            if (bpMeasurementService5.f == ConnectionTypeOmronBP.RECONNECT_ON_DISCONNECT) {
                int i3 = bpMeasurementService5.m + 1;
                bpMeasurementService5.m = i3;
                if (i3 <= 2) {
                    bpMeasurementService5.a(CloveOmronBpBleState.BleState.DISCONNECTED, true);
                    BpMeasurementService.this.reconnectToConfiguredDevice();
                    return;
                }
                LogHelper.d(str2, "257 or 8 error appeared 1 time  disconnect event and scan device", moduleNames2.getModuleName());
                BpMeasurementService.this.a(CloveOmronBpBleState.BleState.DISCONNECTED, true);
                BpMeasurementService.this.closeGattConnection();
                if (i == 257) {
                    BleApiManager.getInstance(BpMeasurementService.this).getSecondaryBleImpl(DeviceType.omronbp).restartService();
                    return;
                }
                return;
            }
            LogHelper.d(str2, "connectype is ++ " + BpMeasurementService.this.f, moduleNames2.getModuleName());
            BpMeasurementService bpMeasurementService6 = BpMeasurementService.this;
            bpMeasurementService6.m = 0;
            bpMeasurementService6.closeGattConnection();
            BpMeasurementService.this.a(CloveOmronBpBleState.BleState.DISCONNECTED, true);
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
        public void onPhyRead(BluetoothGatt bluetoothGatt, int i, int i2, int i3) {
            super.onPhyRead(bluetoothGatt, i, i2, i3);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onPhyUpdate(BluetoothGatt bluetoothGatt, int i, int i2, int i3) {
            super.onPhyUpdate(bluetoothGatt, i, i2, i3);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onServicesDiscovered(final BluetoothGatt bluetoothGatt, int i) {
            super.onServicesDiscovered(bluetoothGatt, i);
            String str = BpMeasurementService.y;
            ModuleNames moduleNames = ModuleNames.BLEABSTRACT;
            LogHelper.e(str, i + "", moduleNames.getModuleName());
            if (i == 0) {
                LogHelper.d(str, BpMeasurementService.this.getString(R.string.service_discovered_method), moduleNames.getModuleName());
                BpMeasurementService.this.k.postDelayed(new Runnable() { // from class: com.coveiot.android.bleabstract.services.BpMeasurementService.5.2
                    /* JADX WARN: Code restructure failed: missing block: B:38:0x004e, code lost:
                        continue;
                     */
                    @Override // java.lang.Runnable
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct add '--show-bad-code' argument
                    */
                    public void run() {
                        /*
                            Method dump skipped, instructions count: 303
                            To view this dump add '--comments-level debug' option
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.bleabstract.services.BpMeasurementService.AnonymousClass5.AnonymousClass2.run():void");
                    }
                }, Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS);
                return;
            }
            BleApiManager.getInstance(BpMeasurementService.this).getBleApi().restartService();
        }
    };
    public Runnable x = new Runnable() { // from class: com.coveiot.android.bleabstract.services.BpMeasurementService.6
        @Override // java.lang.Runnable
        public void run() {
            String str = BpMeasurementService.y;
            ModuleNames moduleNames = ModuleNames.BLEABSTRACT;
            LogHelper.d(str, "cmdHandler called", moduleNames.getModuleName());
            BpMeasurementService.this.l.removeCallbacksAndMessages(null);
            CommandObject commandObject = BpMeasurementService.this.currentCommand;
            if (commandObject != null && !commandObject.isCompleted()) {
                LogHelper.d(str, "Interrupt command " + BpMeasurementService.this.currentCommand, moduleNames.getModuleName());
                if (BpMeasurementService.this.currentCommand.getResponseListener() != null) {
                    BpMeasurementService.this.currentCommand.getResponseListener().onFailure(new Error(Type.COMMAND_TIMED_OUT, "Something went wrong"));
                    LogHelper.d(str, "Time out for ", Arrays.toString(BpMeasurementService.this.currentCommand.getCmdByte()));
                } else {
                    Bus eventBus = BleEventBusManager.getInstance().getEventBus();
                    Type type = Type.COMMAND_REQUEST_ERROR;
                    eventBus.post(new Error(type, "No Response from band for " + Arrays.toString(BpMeasurementService.this.currentCommand.getCmdByte())));
                }
                BpMeasurementService bpMeasurementService = BpMeasurementService.this;
                bpMeasurementService.currentCommand = null;
                bpMeasurementService.w = false;
                return;
            }
            BpMeasurementService bpMeasurementService2 = BpMeasurementService.this;
            LinkedList<CommandObject> linkedList = bpMeasurementService2.g;
            if (linkedList != null && linkedList.size() > 0) {
                CommandObject remove = bpMeasurementService2.g.remove();
                bpMeasurementService2.currentCommand = remove;
                if (remove == null) {
                    LogHelper.d(str, "current command is null process next item ++", moduleNames.getModuleName());
                    bpMeasurementService2.l.removeCallbacksAndMessages(null);
                    bpMeasurementService2.w = false;
                    bpMeasurementService2.l.post(bpMeasurementService2.x);
                } else if (remove.getCh() != null) {
                    BluetoothGattCharacteristic ch = bpMeasurementService2.currentCommand.getCh();
                    if (bpMeasurementService2.d != null && ch != null) {
                        LogHelper.e(str, "read from band service+++ " + ch.getUuid(), moduleNames.getModuleName());
                        if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(bpMeasurementService2, "android.permission.BLUETOOTH_CONNECT") == 0) {
                            bpMeasurementService2.d.readCharacteristic(ch);
                        }
                    }
                } else {
                    CommandObject commandObject2 = bpMeasurementService2.currentCommand;
                    if (commandObject2 != null && commandObject2.getCmdByte() != null) {
                        bpMeasurementService2.a(bpMeasurementService2.currentCommand.getCmdByte(), bpMeasurementService2.currentCommand.getCh());
                    } else if (bpMeasurementService2.currentCommand.getResponseListener() != null) {
                        bpMeasurementService2.currentCommand.getResponseListener().onFailure(new Error(Type.COMMAND_TIMED_OUT, "Something went wrong"));
                        LogHelper.d(str, "Time out for ", Arrays.toString(bpMeasurementService2.currentCommand.getCmdByte()));
                    } else {
                        Bus eventBus2 = BleEventBusManager.getInstance().getEventBus();
                        Type type2 = Type.COMMAND_REQUEST_ERROR;
                        eventBus2.post(new Error(type2, "No Response from band for " + Arrays.toString(bpMeasurementService2.currentCommand.getCmdByte())));
                    }
                }
            } else {
                bpMeasurementService2.currentCommand = null;
            }
            BpMeasurementService bpMeasurementService3 = BpMeasurementService.this;
            if (bpMeasurementService3.currentCommand != null) {
                bpMeasurementService3.l.removeCallbacksAndMessages(null);
                if (BpMeasurementService.this.g.size() != 0) {
                    BpMeasurementService.this.p = false;
                } else {
                    BpMeasurementService bpMeasurementService4 = BpMeasurementService.this;
                    if (!bpMeasurementService4.p) {
                        bpMeasurementService4.p = true;
                    }
                }
                BpMeasurementService.this.l.removeCallbacksAndMessages(null);
                BpMeasurementService.this.w = true;
                if (!BpMeasurementService.this.currentCommand.isMultipacket()) {
                    BpMeasurementService.this.w = false;
                    BpMeasurementService.this.l.removeCallbacksAndMessages(null);
                } else if (BpMeasurementService.this.currentCommand.getCmdName() == CommandNames.DEVICE_INFO) {
                    LogHelper.d(str, "cmdHandler called device info", moduleNames.getModuleName());
                    BpMeasurementService bpMeasurementService5 = BpMeasurementService.this;
                    bpMeasurementService5.l.postDelayed(bpMeasurementService5.x, 15000L);
                }
            }
        }
    };

    /* loaded from: classes2.dex */
    public class BPPostRunnable implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public byte[] f3694a;

        public BPPostRunnable(byte[] bArr) {
            this.f3694a = bArr;
        }

        @Override // java.lang.Runnable
        public void run() {
            BleEventBusManager.getInstance().getEventBus().post(new ResponseEvent(ResponseType.OMRON_BP_DATA, new BloodPressureMeasurement(this.f3694a)));
        }
    }

    /* loaded from: classes2.dex */
    public class BtServiceBinder extends Binder {
        public BtServiceBinder() {
        }

        public BpMeasurementService getService() {
            return BpMeasurementService.this;
        }
    }

    public void clearAllServiceParameters() {
        closeGattConnection();
        Handler handler = this.k;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        Handler handler2 = this.j;
        if (handler2 != null) {
            handler2.removeCallbacksAndMessages(null);
        }
        Handler handler3 = this.h;
        if (handler3 != null) {
            handler3.removeCallbacksAndMessages(null);
        }
        Handler handler4 = this.l;
        if (handler4 != null) {
            handler4.removeCallbacksAndMessages(null);
        }
        Handler handler5 = this.n;
        if (handler5 != null) {
            handler5.removeCallbacksAndMessages(null);
        }
        a(CloveOmronBpBleState.BleState.DISCONNECTED, false);
        this.h = null;
        this.j = null;
        this.k = null;
        this.l = null;
    }

    public void closeGattConnection() {
        if (this.d == null) {
            return;
        }
        String str = y;
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
        OmronBpPreferenceManager.savePreference(getApplicationContext(), OmronBpConstants.BLE_DEVICE_ADDRESS.getName(), str);
        OmronBpPreferenceManager.savePreference(getApplicationContext(), OmronBpConstants.BLE_CONNECTION_TYPE.getName(), ConnectionTypeOmronBP.DONT_CONNECT_ON_DISCONNECT.name());
        String str2 = y;
        LogHelper.d(str2, "connection type ++ " + OmronBpUtils.getConnectionType(this), ModuleNames.BLEABSTRACT.getModuleName());
        BluetoothAdapter bluetoothAdapter = this.c;
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
            return;
        }
        reconnectToConfiguredDevice();
    }

    public void disconnectAndForget() {
        Handler handler = this.l;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        stopScan();
        closeGattConnection();
        clearAllServiceParameters();
        unregisterReceivers();
        OmronBpPreferenceManager.savePreference(getApplicationContext(), OmronBpConstants.BLE_DEVICE_ADDRESS.getName(), "");
        OmronBpPreferenceManager.savePreference(getApplicationContext(), OmronBpConstants.BLE_CONNECTION_TYPE.getName(), ConnectionTypeOmronBP.DONT_CONNECT_ON_DISCONNECT.name());
        stopForeground(true);
        stopSelf();
    }

    public ConnectionError getConnectionError() {
        return this.u;
    }

    public long getConnectionStageChangeTimeStamp() {
        return this.o;
    }

    public CloveOmronBpBleState.BleState getConnectionState() {
        return this.bluetoothConnectionStatus;
    }

    public void initServiceParams() {
        if (this.k == null) {
            this.k = new Handler();
        }
        if (this.j == null) {
            this.j = new Handler();
        }
        if (this.h == null) {
            this.h = new Handler();
        }
        if (this.l == null) {
            this.l = new Handler();
        }
        if (this.n == null) {
            this.n = new Handler();
        }
        a(CloveOmronBpBleState.BleState.DISCONNECTED, false);
    }

    @Override // android.app.Service
    @Nullable
    public IBinder onBind(Intent intent) {
        return this.f3684a;
    }

    @Override // android.app.Service
    public void onCreate() {
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

    @Subscribe
    public void onResponseObtained(ProcessNextItemEvent processNextItemEvent) {
        LogHelper.d(y, "process next item after response is called ", ModuleNames.BLEABSTRACT.getModuleName());
        this.w = false;
        Handler handler = this.l;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.l.postDelayed(this.x, 10L);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x008c, code lost:
        if (r2 == null) goto L10;
     */
    @Override // android.app.Service
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int onStartCommand(android.content.Intent r2, int r3, int r4) {
        /*
            r1 = this;
            r3 = 2
            if (r2 != 0) goto L1f
            android.content.Intent r2 = new android.content.Intent     // Catch: java.lang.Exception -> L18
            java.lang.Class<com.coveiot.android.bleabstract.services.BpMeasurementService> r4 = com.coveiot.android.bleabstract.services.BpMeasurementService.class
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
            android.content.Context r2 = r1.getApplicationContext()
            com.coveiot.android.bpsdk.utils.OmronBpConstants r4 = com.coveiot.android.bpsdk.utils.OmronBpConstants.BLE_DEVICE_ADDRESS
            java.lang.String r4 = r4.getName()
            java.lang.String r0 = ""
            java.lang.Object r2 = com.coveiot.android.bpsdk.utils.OmronBpPreferenceManager.getPreference(r2, r4, r0)
            java.lang.String r2 = (java.lang.String) r2
            r1.e = r2
            android.content.Context r2 = r1.getApplicationContext()
            com.coveiot.android.bpsdk.events.ConnectionTypeOmronBP r2 = com.coveiot.android.bpsdk.utils.OmronBpUtils.getConnectionType(r2)
            r1.f = r2
            java.lang.String r2 = com.coveiot.android.bleabstract.services.BpMeasurementService.y
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
            android.os.Handler r2 = r1.q
            if (r2 != 0) goto L7e
            android.os.HandlerThread r2 = new android.os.HandlerThread
            java.lang.String r4 = "gatt_response"
            r2.<init>(r4)
            r1.r = r2
            r2.start()
            android.os.Handler r2 = new android.os.Handler
            android.os.HandlerThread r4 = r1.r
            android.os.Looper r4 = r4.getLooper()
            r2.<init>(r4)
            r1.q = r2
        L7e:
            android.bluetooth.BluetoothManager r2 = r1.b
            if (r2 != 0) goto L8f
            java.lang.String r2 = "bluetooth"
            java.lang.Object r2 = r1.getSystemService(r2)
            android.bluetooth.BluetoothManager r2 = (android.bluetooth.BluetoothManager) r2
            r1.b = r2
            if (r2 != 0) goto L8f
            goto Lcc
        L8f:
            android.bluetooth.BluetoothManager r2 = r1.b
            android.bluetooth.BluetoothAdapter r2 = r2.getAdapter()
            r1.c = r2
            com.coveiot.sdk.ble.eventbus.BleEventBusManager r2 = com.coveiot.sdk.ble.eventbus.BleEventBusManager.getInstance()     // Catch: java.lang.Exception -> La3
            com.squareup.otto.Bus r2 = r2.getEventBus()     // Catch: java.lang.Exception -> La3
            r2.register(r1)     // Catch: java.lang.Exception -> La3
            goto La7
        La3:
            r2 = move-exception
            r2.printStackTrace()
        La7:
            android.content.IntentFilter r2 = new android.content.IntentFilter     // Catch: java.lang.Exception -> Lc8
            java.lang.String r4 = "android.bluetooth.adapter.action.STATE_CHANGED"
            r2.<init>(r4)     // Catch: java.lang.Exception -> Lc8
            android.content.Context r4 = r1.getApplicationContext()     // Catch: java.lang.Exception -> Lc8
            android.content.BroadcastReceiver r0 = r1.t     // Catch: java.lang.Exception -> Lc8
            r4.registerReceiver(r0, r2)     // Catch: java.lang.Exception -> Lc8
            android.content.IntentFilter r2 = new android.content.IntentFilter     // Catch: java.lang.Exception -> Lc8
            java.lang.String r4 = "action_stop_service"
            r2.<init>(r4)     // Catch: java.lang.Exception -> Lc8
            android.content.Context r4 = r1.getApplicationContext()     // Catch: java.lang.Exception -> Lc8
            android.content.BroadcastReceiver r0 = r1.t     // Catch: java.lang.Exception -> Lc8
            r4.registerReceiver(r0, r2)     // Catch: java.lang.Exception -> Lc8
            goto Lcc
        Lc8:
            r2 = move-exception
            r2.printStackTrace()
        Lcc:
            java.lang.String r2 = r1.e
            boolean r2 = com.coveiot.sdk.ble.utils.BleUtils.isEmpty(r2)
            if (r2 != 0) goto Le2
            android.bluetooth.BluetoothAdapter r2 = r1.c
            if (r2 == 0) goto Le2
            boolean r2 = r2.isEnabled()
            if (r2 != 0) goto Ldf
            goto Le2
        Ldf:
            r1.reconnectToConfiguredDevice()
        Le2:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.bleabstract.services.BpMeasurementService.onStartCommand(android.content.Intent, int, int):int");
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    public void readBpDataFromDevice() {
        BluetoothGatt bluetoothGatt = this.d;
        this.d = bluetoothGatt;
        String str = y;
        ModuleNames moduleNames = ModuleNames.BLEABSTRACT;
        LogHelper.e(str, getString(R.string.init_ble_service) + "", moduleNames.getModuleName());
        if (bluetoothGatt.getServices() != null && bluetoothGatt.getServices().size() != 0) {
            LogHelper.e(str, getString(R.string.gatt_not_null_not_empty));
            for (BluetoothGattService bluetoothGattService : bluetoothGatt.getServices()) {
                String str2 = y;
                LogHelper.d(str2, getString(R.string.service_for_found));
                if (BleUUID.BP_UART_SERVICE_UUID.equalsIgnoreCase(bluetoothGattService.getUuid().toString())) {
                    LogHelper.d(str2, "Service found");
                    for (BluetoothGattCharacteristic bluetoothGattCharacteristic : bluetoothGattService.getCharacteristics()) {
                        if (bluetoothGattCharacteristic.getUuid().toString().equalsIgnoreCase(BleUUID.BP_UART_READ_CHARATERISTICS_UUID)) {
                            int i = Build.VERSION.SDK_INT;
                            if (i >= 31 && ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH_CONNECT") != 0) {
                                return;
                            }
                            bluetoothGatt.setCharacteristicNotification(bluetoothGattCharacteristic, true);
                            BluetoothGatt bluetoothGatt2 = this.d;
                            if (bluetoothGatt2 == null) {
                                LogHelper.d(y, getString(R.string.gatt_null));
                            } else {
                                BluetoothGattService service = bluetoothGatt2.getService(UUID.fromString(BleUUID.BP_UART_SERVICE_UUID));
                                String str3 = y;
                                LogHelper.d(str3, getString(R.string.service_uuid_written));
                                if (service == null) {
                                    LogHelper.d(str3, getString(R.string.service_null));
                                } else {
                                    BluetoothGattCharacteristic characteristic = service.getCharacteristic(UUID.fromString(BleUUID.BP_UART_READ_CHARATERISTICS_UUID));
                                    if (characteristic == null) {
                                        LogHelper.d(str3, getString(R.string.characterstic_null));
                                    } else {
                                        LogHelper.d(str3, getString(R.string.character_service_uuid));
                                        if (i < 31 || ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH_CONNECT") == 0) {
                                            this.d.setCharacteristicNotification(characteristic, true);
                                            try {
                                                Thread.sleep(20L);
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }
                                            BluetoothGattDescriptor descriptor = characteristic.getDescriptor(UUID.fromString("00002902-0000-1000-8000-00805f9b34fb"));
                                            String str4 = y;
                                            LogHelper.d(str4, getString(R.string.descriptor_uuid));
                                            if (descriptor == null) {
                                                LogHelper.d(str4, getString(R.string.descriptor_null));
                                            } else {
                                                descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
                                                descriptor.setValue(BluetoothGattDescriptor.ENABLE_INDICATION_VALUE);
                                                BluetoothGatt bluetoothGatt3 = this.d;
                                                if (bluetoothGatt3 == null) {
                                                    LogHelper.d(str4, getString(R.string.gatt_null_after_descriptor));
                                                } else {
                                                    bluetoothGatt3.writeDescriptor(descriptor);
                                                    LogHelper.d(str4, getString(R.string.descriptor_written_called));
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            LogHelper.d(y, getString(R.string.enable_char_noti_called));
                        }
                    }
                    continue;
                }
            }
            a(CloveOmronBpBleState.BleState.CONNECTED, true);
            this.h.removeCallbacks(null);
            this.m = 0;
            return;
        }
        LogHelper.d(str, getString(R.string.gatt_null_empty), moduleNames.getModuleName());
        stopScan();
        closeGattConnection();
        a(CloveOmronBpBleState.BleState.DISCONNECTED, true);
        reconnectToConfiguredDevice();
    }

    public void reconnectToConfiguredDevice() {
        closeGattConnection();
        this.e = (String) OmronBpPreferenceManager.getPreference(getApplicationContext(), OmronBpConstants.BLE_DEVICE_ADDRESS.getName(), "");
        this.f = OmronBpUtils.getConnectionType(getApplicationContext());
        if (this.c.isEnabled()) {
            if (!BleUtils.isEmpty(this.e) && BluetoothAdapter.checkBluetoothAddress(this.e)) {
                a(this.c.getRemoteDevice(this.e));
                return;
            }
            String str = y;
            LogHelper.e(str, "Trying to connect, address is empty or small case " + this.e, ModuleNames.BLEABSTRACT.getModuleName());
            return;
        }
        LogHelper.e(y, "Bluetooth is disabled", ModuleNames.BLEABSTRACT.getModuleName());
    }

    public void restartService() {
        Handler handler = this.l;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        closeGattConnection();
        clearAllServiceParameters();
        unregisterReceivers();
        stopScan();
        OmronBpPreferenceManager.savePreference(getApplicationContext(), OmronBpConstants.BLE_CONNECTION_TYPE.getName(), ConnectionTypeOmronBP.RECONNECT_ON_DISCONNECT.name());
        stopForeground(true);
        stopSelf();
    }

    public void sendRequest(BaseRequest baseRequest, ResponseListener responseListener) {
        baseRequest.setResponseListener(responseListener);
        if (baseRequest.getGattServiceToRead().equalsIgnoreCase(BleUUID.DEVICE_INFO_SERVICE_UUID)) {
            CommandObject commandObject = new CommandObject(this.d, a(baseRequest), false, CommandNames.DEVICE_INFO);
            commandObject.setBaseRequest(baseRequest);
            commandObject.setResponseListener(baseRequest.getResponseListener());
            this.g.add(commandObject);
        } else if (baseRequest.getGattServiceToRead().equalsIgnoreCase(BleUUID.BATTERY_SERVICE_UUID)) {
            CommandObject commandObject2 = new CommandObject(this.d, a(baseRequest), false, CommandNames.GET_BATTERY_LEVEL);
            commandObject2.setBaseRequest(baseRequest);
            commandObject2.setResponseListener(baseRequest.getResponseListener());
            this.g.add(commandObject2);
        }
        if (this.w) {
            return;
        }
        this.w = true;
        this.l.postDelayed(this.x, 10L);
    }

    public void stopScan() {
        try {
            BluetoothAdapter bluetoothAdapter = this.c;
            if (bluetoothAdapter == null || bluetoothAdapter.getBluetoothLeScanner() == null) {
                return;
            }
            if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH_SCAN") == 0) {
                this.c.getBluetoothLeScanner().stopScan(this.s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopService() {
        Handler handler = this.l;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        disconnectAndForget();
    }

    public void unregisterReceivers() {
        if (this.t != null) {
            try {
                getApplicationContext().unregisterReceiver(this.t);
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

    public final void a(final BluetoothDevice bluetoothDevice) {
        a(CloveOmronBpBleState.BleState.CONNECTING, true);
        this.i.removeCallbacksAndMessages(null);
        this.mBluetoothDevice = bluetoothDevice;
        if (this.d != null) {
            if (Build.VERSION.SDK_INT >= 31 && ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH_CONNECT") != 0) {
                return;
            }
            this.d.disconnect();
            this.d.close();
            this.d = null;
        }
        Handler handler = this.h;
        if (handler != null) {
            handler.postDelayed(new Runnable() { // from class: com.coveiot.android.bleabstract.services.BpMeasurementService.1
                @Override // java.lang.Runnable
                public void run() {
                    if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(BpMeasurementService.this, "android.permission.BLUETOOTH_CONNECT") == 0) {
                        BpMeasurementService bpMeasurementService = BpMeasurementService.this;
                        bpMeasurementService.d = bluetoothDevice.connectGatt(bpMeasurementService.getApplicationContext(), false, BpMeasurementService.this.v);
                    }
                }
            }, 1000L);
        }
    }

    public final void a(final CloveOmronBpBleState.BleState bleState, boolean z) {
        Handler handler;
        String str = y;
        LogHelper.d(str, "updateConnectionState: " + bleState.toString() + " shouldBroadcast: " + z, ModuleNames.BLEABSTRACT.getModuleName());
        this.bluetoothConnectionStatus = bleState;
        if (!z || (handler = this.h) == null) {
            return;
        }
        handler.removeCallbacksAndMessages(null);
        this.h.post(new Runnable(this) { // from class: com.coveiot.android.bleabstract.services.BpMeasurementService.2
            @Override // java.lang.Runnable
            public void run() {
                BleEventBusManager.getInstance().getEventBus().post(new CloveOmronBpBleState(bleState));
            }
        });
    }

    public final void a(byte[] bArr, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (this.d == null || bluetoothGattCharacteristic == null) {
            return;
        }
        bluetoothGattCharacteristic.setValue(bArr);
        if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH_CONNECT") == 0) {
            this.d.writeCharacteristic(bluetoothGattCharacteristic);
            LogHelper.d(y + " Reqst sent is ++ ", Arrays.toString(bArr), ModuleNames.BLEABSTRACT.getModuleName());
        }
    }

    public final BluetoothGattCharacteristic a(BaseRequest baseRequest) {
        BluetoothGattService service;
        if (this.d == null || baseRequest.getGattServiceToRead() == null || (service = this.d.getService(UUID.fromString(baseRequest.getGattServiceToRead()))) == null) {
            return null;
        }
        return service.getCharacteristic(UUID.fromString(baseRequest.getGattCharacteristicToRead()));
    }
}
