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
import android.os.IBinder;
import android.os.Looper;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import com.coveiot.android.bleabstract.BleApiUtils;
import com.coveiot.android.bleabstract.NotificationInfo;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.response.ConnectionError;
import com.coveiot.android.spo2sdk.CloveBleStatePC60F;
import com.coveiot.android.spo2sdk.events.ConnectionTypeSpo2;
import com.coveiot.android.spo2sdk.utils.Spo2PrefConstants;
import com.coveiot.android.spo2sdk.utils.Spo2PreferenceManager;
import com.coveiot.android.spo2sdk.utils.Spo2Utils;
import com.coveiot.sdk.ble.api.BleUUID;
import com.coveiot.sdk.ble.eventbus.BleEventBusManager;
import com.coveiot.sdk.ble.utils.BleUtils;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;
/* loaded from: classes2.dex */
public class PC60FBleService extends Service {
    public static final String ACTION_STOP_SERVICE = "action_stop_service";
    public static final String EMPTY_STRING = "";
    public static final String t = PC60FBleService.class.getSimpleName();
    public BluetoothManager b;
    public CloveBleStatePC60F.BleState bluetoothConnectionStatus;
    public BluetoothAdapter c;
    public BluetoothGatt d;
    public String e;
    public ConnectionTypeSpo2 f;
    public Handler i;
    public Handler j;
    public Handler k;
    public Handler m;
    public BluetoothDevice mBluetoothDevice;
    public ConnectionError r;
    public BluetoothGattCharacteristic readWriteCharacteristic;

    /* renamed from: a  reason: collision with root package name */
    public final IBinder f3905a = new BtServiceBinder();
    public Handler g = new Handler();
    public final Handler h = new Handler(Looper.getMainLooper());
    public int l = 0;
    public final LinkedBlockingQueue<byte[]> n = new LinkedBlockingQueue<>();
    public long o = -1;
    @RequiresApi(21)
    public ScanCallback p = new ScanCallback() { // from class: com.coveiot.android.bleabstract.services.PC60FBleService.3
        @Override // android.bluetooth.le.ScanCallback
        public void onScanFailed(int i) {
            super.onScanFailed(i);
            LogHelper.e("onScanFailed", "Error code " + i, ModuleNames.BLEABSTRACT.getModuleName());
            if (i != 1) {
                if (i == 2) {
                    BleApiManager.getInstance(PC60FBleService.this).getBleApi().restartService();
                    return;
                }
                PC60FBleService pC60FBleService = PC60FBleService.this;
                CloveBleStatePC60F.BleState bleState = CloveBleStatePC60F.BleState.DISCONNECTED;
                String str = PC60FBleService.t;
                pC60FBleService.a(bleState, true);
            }
        }

        @Override // android.bluetooth.le.ScanCallback
        public void onScanResult(int i, ScanResult scanResult) {
            super.onScanResult(i, scanResult);
            try {
                if (Build.VERSION.SDK_INT < 21 || !scanResult.getDevice().getAddress().equalsIgnoreCase(PC60FBleService.this.e)) {
                    return;
                }
                String str = PC60FBleService.t;
                ModuleNames moduleNames = ModuleNames.BLEABSTRACT;
                LogHelper.d(str, "Scan stopped, device found", moduleNames.getModuleName());
                PC60FBleService.this.stopScan();
                PC60FBleService.this.a(CloveBleStatePC60F.BleState.CONNECTING, true);
                Handler handler = PC60FBleService.this.i;
                if (handler != null) {
                    handler.removeCallbacks(null);
                }
                LogHelper.d(str, "connect to device after scan", moduleNames.getModuleName());
                PC60FBleService.this.a(scanResult.getDevice());
            } catch (Exception e) {
                LogHelper.e(PC60FBleService.t, e.toString(), ModuleNames.BLEABSTRACT.getModuleName());
            }
        }
    };
    public final BroadcastReceiver q = new BroadcastReceiver() { // from class: com.coveiot.android.bleabstract.services.PC60FBleService.4
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("android.bluetooth.adapter.action.STATE_CHANGED")) {
                int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE);
                if (intExtra == 3 || intExtra == 10) {
                    PC60FBleService pC60FBleService = PC60FBleService.this;
                    CloveBleStatePC60F.BleState bleState = CloveBleStatePC60F.BleState.DISCONNECTED;
                    String str = PC60FBleService.t;
                    pC60FBleService.a(bleState, true);
                    PC60FBleService.this.stopScan();
                    PC60FBleService.this.closeGattConnection();
                    PC60FBleService.this.clearAllServiceParameters();
                }
            } else if (action.equalsIgnoreCase("action_stop_service")) {
                try {
                    PC60FBleService.this.closeGattConnection();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                PC60FBleService pC60FBleService2 = PC60FBleService.this;
                CloveBleStatePC60F.BleState bleState2 = CloveBleStatePC60F.BleState.DISCONNECTED;
                String str2 = PC60FBleService.t;
                pC60FBleService2.a(bleState2, true);
                PC60FBleService.this.clearAllServiceParameters();
            }
        }
    };
    public BluetoothGattCallback s = new BluetoothGattCallback() { // from class: com.coveiot.android.bleabstract.services.PC60FBleService.5
        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            super.onCharacteristicChanged(bluetoothGatt, bluetoothGattCharacteristic);
            PC60FBleService pC60FBleService = PC60FBleService.this;
            if (pC60FBleService.d == null) {
                return;
            }
            pC60FBleService.n.add(bluetoothGattCharacteristic.getValue());
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            super.onCharacteristicRead(bluetoothGatt, bluetoothGattCharacteristic, i);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            byte[] value;
            super.onCharacteristicWrite(bluetoothGatt, bluetoothGattCharacteristic, i);
            if (i != 0 || (value = bluetoothGattCharacteristic.getValue()) == null || value.length <= 0) {
                return;
            }
            if ((value[4] & 255) == 132) {
                LogHelper.i(PC60FBleService.t, "enable param of request-> send success", ModuleNames.BLEABSTRACT.getModuleName());
            } else if ((value[4] & 255) == 133) {
                LogHelper.d(PC60FBleService.t, "enable [ wave ] of request-> send success", ModuleNames.BLEABSTRACT.getModuleName());
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onConnectionStateChange(final BluetoothGatt bluetoothGatt, int i, int i2) {
            PC60FBleService.this.o = System.currentTimeMillis();
            PC60FBleService pC60FBleService = PC60FBleService.this;
            pC60FBleService.f = Spo2Utils.getConnectionType(pC60FBleService.getApplicationContext());
            if (i == 0) {
                if (i2 == 2) {
                    LogHelper.d(PC60FBleService.t, "onConnectionStateChange: Connected", ModuleNames.BLEABSTRACT.getModuleName());
                    PC60FBleService pC60FBleService2 = PC60FBleService.this;
                    pC60FBleService2.r = null;
                    pC60FBleService2.m.postDelayed(new Runnable() { // from class: com.coveiot.android.bleabstract.services.PC60FBleService.5.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(PC60FBleService.this, "android.permission.BLUETOOTH_CONNECT") == 0) {
                                bluetoothGatt.discoverServices();
                            }
                        }
                    }, 1000L);
                    return;
                } else if (i2 == 0) {
                    String str = PC60FBleService.t;
                    ModuleNames moduleNames = ModuleNames.BLEABSTRACT;
                    LogHelper.d(str, "onConnectionStateChange: Disconnected", moduleNames.getModuleName());
                    PC60FBleService.this.a(CloveBleStatePC60F.BleState.DISCONNECTED, true);
                    PC60FBleService pC60FBleService3 = PC60FBleService.this;
                    if (pC60FBleService3.f == ConnectionTypeSpo2.RECONNECT_ON_DISCONNECT) {
                        LogHelper.d(str, "Initiating reconnect", moduleNames.getModuleName());
                        PC60FBleService.this.reconnectToConfiguredDevice();
                    } else {
                        pC60FBleService3.closeGattConnection();
                    }
                    PC60FBleService.this.n.clear();
                    return;
                } else {
                    return;
                }
            }
            PC60FBleService.this.r = new ConnectionError(i, System.currentTimeMillis());
            String str2 = PC60FBleService.t;
            ModuleNames moduleNames2 = ModuleNames.BLEABSTRACT;
            LogHelper.e(str2, "onConnectionStateChange Error GATT.  Error id : " + i, moduleNames2.getModuleName());
            if (i == 133) {
                if (PC60FBleService.this.f == ConnectionTypeSpo2.RECONNECT_ON_DISCONNECT) {
                    LogHelper.d(str2, "ConnectionTypeSpo2 is ++ " + PC60FBleService.this.f, moduleNames2.getModuleName());
                    PC60FBleService.this.l = 0;
                    LogHelper.d(str2, "133 error appeared 1 time  disconnect event and scan device", moduleNames2.getModuleName());
                    PC60FBleService.this.a(CloveBleStatePC60F.BleState.DISCONNECTED, true);
                    return;
                }
                LogHelper.d(str2, "ConnectionTypeSpo2 is ++ " + PC60FBleService.this.f, moduleNames2.getModuleName());
                PC60FBleService pC60FBleService4 = PC60FBleService.this;
                pC60FBleService4.l = 0;
                pC60FBleService4.a(CloveBleStatePC60F.BleState.DISCONNECTED, true);
                return;
            }
            LogHelper.d(str2, " onConnectionStateChange error code " + i, moduleNames2.getModuleName());
            PC60FBleService pC60FBleService5 = PC60FBleService.this;
            if (pC60FBleService5.f == ConnectionTypeSpo2.RECONNECT_ON_DISCONNECT) {
                int i3 = pC60FBleService5.l + 1;
                pC60FBleService5.l = i3;
                if (i3 <= 2) {
                    pC60FBleService5.a(CloveBleStatePC60F.BleState.DISCONNECTED, true);
                    PC60FBleService.this.reconnectToConfiguredDevice();
                    return;
                }
                LogHelper.d(str2, "257 or 8 error appeared 1 time  disconnect event and scan device", moduleNames2.getModuleName());
                PC60FBleService.this.a(CloveBleStatePC60F.BleState.DISCONNECTED, true);
                PC60FBleService.this.closeGattConnection();
                if (i == 257) {
                    BleApiManager.getInstance(PC60FBleService.this).getBleApi().restartService();
                    return;
                }
                return;
            }
            LogHelper.d(str2, "connectype is ++ " + PC60FBleService.this.f, moduleNames2.getModuleName());
            PC60FBleService pC60FBleService6 = PC60FBleService.this;
            pC60FBleService6.l = 0;
            pC60FBleService6.closeGattConnection();
            PC60FBleService.this.a(CloveBleStatePC60F.BleState.DISCONNECTED, true);
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
            String str = PC60FBleService.t;
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
        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            super.onServicesDiscovered(bluetoothGatt, i);
            String str = PC60FBleService.t;
            ModuleNames moduleNames = ModuleNames.BLEABSTRACT;
            LogHelper.e(str, i + "", moduleNames.getModuleName());
            if (i == 0) {
                LogHelper.d(str, "service discovered method", moduleNames.getModuleName());
                PC60FBleService pC60FBleService = PC60FBleService.this;
                pC60FBleService.d = bluetoothGatt;
                LogHelper.e(str, "initialize ble services", moduleNames.getModuleName());
                if (bluetoothGatt.getServices() != null && bluetoothGatt.getServices().size() != 0) {
                    pC60FBleService.readWriteCharacteristic = null;
                    pC60FBleService.setCharacteristicNotification(true);
                    if (pC60FBleService.readWriteCharacteristic == null) {
                        LogHelper.d(str, " read  or write characteristic is null", moduleNames.getModuleName());
                        pC60FBleService.stopScan();
                        pC60FBleService.closeGattConnection();
                        pC60FBleService.a(CloveBleStatePC60F.BleState.DISCONNECTED, true);
                        pC60FBleService.reconnectToConfiguredDevice();
                        return;
                    }
                    pC60FBleService.a(CloveBleStatePC60F.BleState.CONNECTED, true);
                    pC60FBleService.g.removeCallbacks(null);
                    pC60FBleService.l = 0;
                    return;
                }
                LogHelper.d(str, " gatt service is null or empty", moduleNames.getModuleName());
                pC60FBleService.stopScan();
                pC60FBleService.closeGattConnection();
                pC60FBleService.a(CloveBleStatePC60F.BleState.DISCONNECTED, true);
                pC60FBleService.reconnectToConfiguredDevice();
                return;
            }
            BleApiManager.getInstance(PC60FBleService.this).getBleApi().restartService();
        }
    };

    /* loaded from: classes2.dex */
    public class BtServiceBinder extends Binder {
        public BtServiceBinder() {
        }

        public PC60FBleService getService() {
            return PC60FBleService.this;
        }
    }

    public final void a(final BluetoothDevice bluetoothDevice) {
        a(CloveBleStatePC60F.BleState.CONNECTING, true);
        this.h.removeCallbacksAndMessages(null);
        this.mBluetoothDevice = bluetoothDevice;
        if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH_CONNECT") == 0) {
            BluetoothGatt bluetoothGatt = this.d;
            if (bluetoothGatt != null) {
                bluetoothGatt.disconnect();
                this.d.close();
                this.d = null;
            }
            Handler handler = this.g;
            if (handler != null) {
                handler.postDelayed(new Runnable() { // from class: com.coveiot.android.bleabstract.services.PC60FBleService.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(PC60FBleService.this, "android.permission.BLUETOOTH_CONNECT") == 0) {
                            PC60FBleService pC60FBleService = PC60FBleService.this;
                            pC60FBleService.d = bluetoothDevice.connectGatt(pC60FBleService.getApplicationContext(), false, PC60FBleService.this.s);
                        }
                    }
                }, 1000L);
            }
        }
    }

    public int available() {
        LinkedBlockingQueue<byte[]> linkedBlockingQueue = this.n;
        if (linkedBlockingQueue != null) {
            return linkedBlockingQueue.size();
        }
        return 0;
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
        Handler handler5 = this.m;
        if (handler5 != null) {
            handler5.removeCallbacksAndMessages(null);
        }
        a(CloveBleStatePC60F.BleState.DISCONNECTED, false);
        this.g = null;
        this.i = null;
        this.j = null;
        this.k = null;
    }

    public void clearBuffer() {
        LinkedBlockingQueue<byte[]> linkedBlockingQueue = this.n;
        if (linkedBlockingQueue != null) {
            linkedBlockingQueue.clear();
        }
    }

    public void closeGattConnection() {
        if (this.d == null) {
            return;
        }
        String str = t;
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
        Spo2PreferenceManager.savePreference(getApplicationContext(), Spo2PrefConstants.BLE_DEVICE_ADDRESS.getName(), str);
        Spo2PreferenceManager.savePreference(getApplicationContext(), Spo2PrefConstants.BLE_CONNECTION_TYPE.getName(), ConnectionTypeSpo2.DONT_CONNECT_ON_DISCONNECT.name());
        String str2 = t;
        LogHelper.d(str2, "connection type ++ " + Spo2Utils.getConnectionType(this), ModuleNames.BLEABSTRACT.getModuleName());
        BluetoothAdapter bluetoothAdapter = this.c;
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
            return;
        }
        reconnectToConfiguredDevice();
    }

    public void disconnectAndForget() {
        Handler handler = this.k;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        clearBuffer();
        stopScan();
        closeGattConnection();
        clearAllServiceParameters();
        unregisterReceivers();
        Spo2PreferenceManager.savePreference(getApplicationContext(), Spo2PrefConstants.BLE_DEVICE_ADDRESS.getName(), "");
        Spo2PreferenceManager.savePreference(getApplicationContext(), Spo2PrefConstants.BLE_CONNECTION_TYPE.getName(), ConnectionTypeSpo2.DONT_CONNECT_ON_DISCONNECT.name());
        stopForeground(true);
        stopSelf();
    }

    public ConnectionError getConnectionError() {
        return this.r;
    }

    public long getConnectionStageChangeTimeStamp() {
        return this.o;
    }

    public CloveBleStatePC60F.BleState getConnectionState() {
        return this.bluetoothConnectionStatus;
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
        if (this.m == null) {
            this.m = new Handler();
        }
        a(CloveBleStatePC60F.BleState.DISCONNECTED, false);
    }

    @Override // android.app.Service
    @Nullable
    public IBinder onBind(Intent intent) {
        return this.f3905a;
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

    /* JADX WARN: Code restructure failed: missing block: B:14:0x006f, code lost:
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
            r3 = 2
            if (r2 != 0) goto L1f
            android.content.Intent r2 = new android.content.Intent     // Catch: java.lang.Exception -> L18
            java.lang.Class<com.coveiot.android.bleabstract.services.PC60FBleService> r4 = com.coveiot.android.bleabstract.services.PC60FBleService.class
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
            com.coveiot.android.spo2sdk.utils.Spo2PrefConstants r4 = com.coveiot.android.spo2sdk.utils.Spo2PrefConstants.BLE_DEVICE_ADDRESS
            java.lang.String r4 = r4.getName()
            java.lang.String r0 = ""
            java.lang.Object r2 = com.coveiot.android.spo2sdk.utils.Spo2PreferenceManager.getPreference(r2, r4, r0)
            java.lang.String r2 = (java.lang.String) r2
            r1.e = r2
            android.content.Context r2 = r1.getApplicationContext()
            com.coveiot.android.spo2sdk.events.ConnectionTypeSpo2 r2 = com.coveiot.android.spo2sdk.utils.Spo2Utils.getConnectionType(r2)
            r1.f = r2
            java.lang.String r2 = com.coveiot.android.bleabstract.services.PC60FBleService.t
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
            if (r2 != 0) goto L72
            java.lang.String r2 = "bluetooth"
            java.lang.Object r2 = r1.getSystemService(r2)
            android.bluetooth.BluetoothManager r2 = (android.bluetooth.BluetoothManager) r2
            r1.b = r2
            if (r2 != 0) goto L72
            goto Laf
        L72:
            android.bluetooth.BluetoothManager r2 = r1.b
            android.bluetooth.BluetoothAdapter r2 = r2.getAdapter()
            r1.c = r2
            com.coveiot.sdk.ble.eventbus.BleEventBusManager r2 = com.coveiot.sdk.ble.eventbus.BleEventBusManager.getInstance()     // Catch: java.lang.Exception -> L86
            com.squareup.otto.Bus r2 = r2.getEventBus()     // Catch: java.lang.Exception -> L86
            r2.register(r1)     // Catch: java.lang.Exception -> L86
            goto L8a
        L86:
            r2 = move-exception
            r2.printStackTrace()
        L8a:
            android.content.IntentFilter r2 = new android.content.IntentFilter     // Catch: java.lang.Exception -> Lab
            java.lang.String r4 = "android.bluetooth.adapter.action.STATE_CHANGED"
            r2.<init>(r4)     // Catch: java.lang.Exception -> Lab
            android.content.Context r4 = r1.getApplicationContext()     // Catch: java.lang.Exception -> Lab
            android.content.BroadcastReceiver r0 = r1.q     // Catch: java.lang.Exception -> Lab
            r4.registerReceiver(r0, r2)     // Catch: java.lang.Exception -> Lab
            android.content.IntentFilter r2 = new android.content.IntentFilter     // Catch: java.lang.Exception -> Lab
            java.lang.String r4 = "action_stop_service"
            r2.<init>(r4)     // Catch: java.lang.Exception -> Lab
            android.content.Context r4 = r1.getApplicationContext()     // Catch: java.lang.Exception -> Lab
            android.content.BroadcastReceiver r0 = r1.q     // Catch: java.lang.Exception -> Lab
            r4.registerReceiver(r0, r2)     // Catch: java.lang.Exception -> Lab
            goto Laf
        Lab:
            r2 = move-exception
            r2.printStackTrace()
        Laf:
            java.lang.String r2 = r1.e
            boolean r2 = com.coveiot.sdk.ble.utils.BleUtils.isEmpty(r2)
            if (r2 != 0) goto Lc5
            android.bluetooth.BluetoothAdapter r2 = r1.c
            if (r2 == 0) goto Lc5
            boolean r2 = r2.isEnabled()
            if (r2 != 0) goto Lc2
            goto Lc5
        Lc2:
            r1.reconnectToConfiguredDevice()
        Lc5:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.bleabstract.services.PC60FBleService.onStartCommand(android.content.Intent, int, int):int");
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    public int read(byte[] bArr) {
        byte[] poll;
        if (this.n.size() <= 0 || (poll = this.n.poll()) == null || poll.length <= 0) {
            return 0;
        }
        int length = poll.length < bArr.length ? poll.length : bArr.length;
        for (int i = 0; i < length; i++) {
            bArr[i] = poll[i];
        }
        return length;
    }

    public void reconnectToConfiguredDevice() {
        closeGattConnection();
        this.e = (String) Spo2PreferenceManager.getPreference(getApplicationContext(), Spo2PrefConstants.BLE_DEVICE_ADDRESS.getName(), "");
        this.f = Spo2Utils.getConnectionType(getApplicationContext());
        if (this.c.isEnabled()) {
            if (!BleUtils.isEmpty(this.e) && BluetoothAdapter.checkBluetoothAddress(this.e)) {
                a(this.c.getRemoteDevice(this.e));
                return;
            }
            String str = t;
            LogHelper.e(str, "Trying to connect, address is empty or small case " + this.e, ModuleNames.BLEABSTRACT.getModuleName());
            return;
        }
        LogHelper.e(t, "Bluetooth is disabled", ModuleNames.BLEABSTRACT.getModuleName());
    }

    public void restartService() {
        Handler handler = this.k;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        clearBuffer();
        closeGattConnection();
        clearAllServiceParameters();
        unregisterReceivers();
        stopScan();
        Spo2PreferenceManager.savePreference(getApplicationContext(), Spo2PrefConstants.BLE_CONNECTION_TYPE.getName(), ConnectionTypeSpo2.RECONNECT_ON_DISCONNECT.name());
        stopForeground(true);
        stopSelf();
    }

    public void setCharacteristicNotification(boolean z) {
        BluetoothGattService service;
        BluetoothGattCharacteristic characteristic;
        BluetoothGatt bluetoothGatt = this.d;
        if (bluetoothGatt == null || (service = bluetoothGatt.getService(UUID.fromString(BleUUID.UART_SERVICE_UUID))) == null || (characteristic = service.getCharacteristic(UUID.fromString(BleUUID.UART_READ_CHARATERISTICS_UUID))) == null) {
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
            BluetoothGattDescriptor descriptor = characteristic.getDescriptor(UUID.fromString("00002902-0000-1000-8000-00805f9b34fb"));
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
                this.c.getBluetoothLeScanner().stopScan(this.p);
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

    public void unregisterReceivers() {
        if (this.q != null) {
            try {
                getApplicationContext().unregisterReceiver(this.q);
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
        if (bluetoothGatt == null || bArr == null || (service = bluetoothGatt.getService(UUID.fromString(BleUUID.UART_SERVICE_UUID))) == null || (characteristic = service.getCharacteristic(UUID.fromString(BleUUID.UART_WRITE_CHARATERISTICS_UUID))) == null) {
            return;
        }
        characteristic.setValue(bArr);
        String str = t;
        LogHelper.i(str, "writeValue: " + BleApiUtils.byte2Hex(bArr), ModuleNames.BLEABSTRACT.getModuleName());
        int i = 0;
        while (bArr.length - i > 10) {
            byte[] bArr2 = new byte[10];
            System.arraycopy(bArr, i, bArr2, 0, 10);
            characteristic.setValue(bArr2);
            if (Build.VERSION.SDK_INT >= 31 && ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH_CONNECT") != 0) {
                return;
            }
            this.d.writeCharacteristic(characteristic);
            i += 10;
        }
        if (bArr.length - i != 0) {
            byte[] bArr3 = new byte[bArr.length - i];
            System.arraycopy(bArr, i, bArr3, 0, bArr.length - i);
            characteristic.setValue(bArr3);
            if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH_CONNECT") == 0) {
                this.d.writeCharacteristic(characteristic);
            }
        }
    }

    public final void a(final CloveBleStatePC60F.BleState bleState, boolean z) {
        Handler handler;
        String str = t;
        LogHelper.d(str, "updateConnectionState: " + bleState.toString() + " shouldBroadcast: " + z, ModuleNames.BLEABSTRACT.getModuleName());
        this.bluetoothConnectionStatus = bleState;
        if (!z || (handler = this.g) == null) {
            return;
        }
        handler.removeCallbacksAndMessages(null);
        this.g.post(new Runnable(this) { // from class: com.coveiot.android.bleabstract.services.PC60FBleService.2
            @Override // java.lang.Runnable
            public void run() {
                BleEventBusManager.getInstance().getEventBus().post(new CloveBleStatePC60F(bleState));
            }
        });
    }
}
