package com.coveiot.android.bleabstract.services.jstyle2301;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import com.clevertap.android.sdk.Constants;
import com.coveiot.android.bleabstract.BleApiUtils;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.services.jstyle2301.JStyle2208ABleService;
import com.coveiot.android.jstyle2208a.api.JStyleVBaseReq;
import com.coveiot.sdk.ble.CloveBleState;
import com.jstyle.blesdk2208.callback.BleConnectionListener;
import com.jstyle.blesdk2208.callback.OnScanResults;
import com.jstyle.blesdk2208.model.Device;
import java.util.List;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public class JStyle2208ABleManager {
    public static JStyle2208ABleManager l;

    /* renamed from: a  reason: collision with root package name */
    public String f4017a;
    public JStyle2208ABleService b;
    public BleConnectionListener bleConnectionListener1;
    public Intent d;
    public BluetoothAdapter e;
    public Context f;
    public ServiceConnection c = new ServiceConnection() { // from class: com.coveiot.android.bleabstract.services.jstyle2301.JStyle2208ABleManager.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            JStyle2208ABleManager.this.b = ((JStyle2208ABleService.LocalBinder) iBinder).getService();
            if (TextUtils.isEmpty(JStyle2208ABleManager.this.f4017a)) {
                return;
            }
            JStyle2208ABleManager jStyle2208ABleManager = JStyle2208ABleManager.this;
            jStyle2208ABleManager.b.initBluetoothDevice(jStyle2208ABleManager.f4017a, jStyle2208ABleManager.f, jStyle2208ABleManager.NeedReconnect1, jStyle2208ABleManager.bleConnectionListener1);
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            JStyle2208ABleManager.this.b = null;
        }
    };
    public boolean NeedReconnect1 = true;
    public String[] g = null;
    public OnScanResults h = null;
    public boolean i = false;
    @RequiresApi(api = 21)
    public final ScanCallback j = new ScanCallback() { // from class: com.coveiot.android.bleabstract.services.jstyle2301.JStyle2208ABleManager.2
        @Override // android.bluetooth.le.ScanCallback
        public void onBatchScanResults(List<ScanResult> list) {
            super.onBatchScanResults(list);
        }

        @Override // android.bluetooth.le.ScanCallback
        public void onScanFailed(int i) {
            super.onScanFailed(i);
            JStyle2208ABleManager.this.h.Fail(i);
        }

        @Override // android.bluetooth.le.ScanCallback
        public void onScanResult(int i, ScanResult scanResult) {
            super.onScanResult(i, scanResult);
            int i2 = Build.VERSION.SDK_INT;
            if (i2 < 31 || ContextCompat.checkSelfPermission(JStyle2208ABleManager.this.f, "android.permission.BLUETOOTH_CONNECT") == 0) {
                String name = scanResult.getDevice().getName();
                if (JStyle2208ABleManager.this.i && !TextUtils.isEmpty(name) && JStyle2208ABleManager.this.a(name.toLowerCase().trim())) {
                    Device device = new Device();
                    device.setBluetoothDevice(scanResult.getDevice());
                    device.setIsconted(false);
                    device.setPaired(false);
                    device.setIsdfu(name.toLowerCase().contains("dfu"));
                    if (i2 < 31 || ContextCompat.checkSelfPermission(JStyle2208ABleManager.this.f, "android.permission.BLUETOOTH_CONNECT") == 0) {
                        device.setName(scanResult.getDevice().getName());
                        device.setMac(scanResult.getDevice().getAddress());
                        device.setRiss(scanResult.getRssi());
                        JStyle2208ABleManager.this.h.Success(device);
                    }
                }
            }
        }
    };
    public Handler k = new Handler();

    /* renamed from: com.coveiot.android.bleabstract.services.jstyle2301.JStyle2208ABleManager$4  reason: invalid class name */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class AnonymousClass4 {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f4021a;

        static {
            int[] iArr = new int[CloveBleState.BleState.values().length];
            f4021a = iArr;
            try {
                iArr[CloveBleState.BleState.CONNECTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f4021a[CloveBleState.BleState.CONNECTING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f4021a[CloveBleState.BleState.DISCONNECTED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f4021a[CloveBleState.BleState.DISCOVERING_SERVICES.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f4021a[CloveBleState.BleState.SCANNING.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public JStyle2208ABleManager(Context context) {
        this.f = context;
        if (this.d == null) {
            Intent intent = new Intent(context, JStyle2208ABleService.class);
            this.d = intent;
            context.bindService(intent, this.c, 1);
            if (Build.VERSION.SDK_INT >= 26) {
                context.startForegroundService(this.d);
            } else {
                context.startService(this.d);
            }
        }
        this.e = ((BluetoothManager) context.getSystemService("bluetooth")).getAdapter();
    }

    public static JStyle2208ABleManager getInstance() {
        return l;
    }

    public static void init(Context context) {
        if (l == null) {
            synchronized (JStyle2208ABleManager.class) {
                if (l == null) {
                    l = new JStyle2208ABleManager(context);
                }
            }
        }
    }

    @TargetApi(21)
    public void DeviceScanResults(@NonNull String[] strArr, @NonNull OnScanResults onScanResults) {
        if (this.i || !l.isBleEnable()) {
            return;
        }
        this.i = true;
        this.g = strArr;
        this.h = onScanResults;
        if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(this.f, "android.permission.BLUETOOTH_CONNECT") == 0) {
            Set<BluetoothDevice> bondedDevices = this.e.getBondedDevices();
            if (bondedDevices.size() > 0) {
                for (BluetoothDevice bluetoothDevice : bondedDevices) {
                    if (a(bluetoothDevice.getName().toLowerCase())) {
                        Device device = new Device();
                        device.setBluetoothDevice(bluetoothDevice);
                        device.setIsconted(false);
                        device.setPaired(true);
                        if (Build.VERSION.SDK_INT >= 31 && ContextCompat.checkSelfPermission(this.f, "android.permission.BLUETOOTH_CONNECT") != 0) {
                            return;
                        }
                        device.setIsdfu(bluetoothDevice.getName().toLowerCase().contains("dfu"));
                        device.setName(bluetoothDevice.getName());
                        device.setMac(bluetoothDevice.getAddress());
                        this.h.Success(device);
                    }
                }
            }
            this.e.getBluetoothLeScanner().startScan((List<ScanFilter>) null, new ScanSettings.Builder().setScanMode(2).build(), this.j);
        }
    }

    @TargetApi(21)
    public void StopDeviceScan() {
        this.i = false;
        BluetoothAdapter bluetoothAdapter = this.e;
        if (bluetoothAdapter == null || bluetoothAdapter.getBluetoothLeScanner() == null) {
            return;
        }
        if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(this.f, "android.permission.BLUETOOTH_SCAN") == 0) {
            this.e.getBluetoothLeScanner().stopScan(this.j);
        }
    }

    public final boolean a(@NonNull String str) {
        String[] strArr = this.g;
        if (strArr == null || strArr.length == 0) {
            return false;
        }
        for (String str2 : strArr) {
            if (str.toLowerCase().contains(str2.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public void connectDevice(String str, boolean z, BleConnectionListener bleConnectionListener) {
        this.NeedReconnect1 = z;
        this.bleConnectionListener1 = bleConnectionListener;
        if (!this.e.isEnabled() || TextUtils.isEmpty(str) || isConnected()) {
            return;
        }
        JStyle2208ABleService jStyle2208ABleService = this.b;
        if (jStyle2208ABleService == null) {
            this.f4017a = str;
        } else {
            jStyle2208ABleService.initBluetoothDevice(str, this.f, z, bleConnectionListener);
        }
    }

    public void disconnectDevice() {
        JStyle2208ABleService jStyle2208ABleService = this.b;
        if (jStyle2208ABleService == null) {
            return;
        }
        jStyle2208ABleService.disconnectAndForget();
    }

    public void enableNotifaction() {
        JStyle2208ABleService jStyle2208ABleService = this.b;
        if (jStyle2208ABleService == null) {
            return;
        }
        jStyle2208ABleService.setCharacteristicNotification(true);
    }

    @NotNull
    public ConnectionStatus getConnectionState() {
        JStyle2208ABleService jStyle2208ABleService = this.b;
        if (jStyle2208ABleService != null) {
            int i = AnonymousClass4.f4021a[jStyle2208ABleService.getConnectionState().ordinal()];
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            if (i != 5) {
                                return ConnectionStatus.DISCONNECTED;
                            }
                            return ConnectionStatus.SCANNING;
                        }
                        return ConnectionStatus.DISCOVERING_SERVICES;
                    }
                    return ConnectionStatus.DISCONNECTED;
                }
                return ConnectionStatus.CONNECTING;
            }
            return ConnectionStatus.CONNECTED;
        }
        return ConnectionStatus.DISCONNECTED;
    }

    @Nullable
    public String getDeviceName(@NotNull String str) {
        return (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(this.f, "android.permission.BLUETOOTH_CONNECT") == 0) ? ((BluetoothManager) this.f.getSystemService("bluetooth")).getAdapter().getRemoteDevice(str).getName() : "";
    }

    public boolean isBleEnable() {
        if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(this.f, "android.permission.BLUETOOTH_CONNECT") == 0) {
            return this.e.enable();
        }
        return false;
    }

    public boolean isConnected() {
        JStyle2208ABleService jStyle2208ABleService = this.b;
        if (jStyle2208ABleService == null) {
            return false;
        }
        return jStyle2208ABleService.isConnected();
    }

    public void offerValue(byte[] bArr) {
        JStyle2208ABleService jStyle2208ABleService = this.b;
        if (jStyle2208ABleService == null) {
            return;
        }
        jStyle2208ABleService.offerValue(bArr);
    }

    public void restartService() {
        JStyle2208ABleService jStyle2208ABleService = this.b;
        if (jStyle2208ABleService != null) {
            jStyle2208ABleService.restartService();
        }
        this.k.postDelayed(new Runnable() { // from class: com.coveiot.android.bleabstract.services.jstyle2301.JStyle2208ABleManager.3
            @Override // java.lang.Runnable
            public void run() {
                JStyle2208ABleManager jStyle2208ABleManager = JStyle2208ABleManager.this;
                jStyle2208ABleManager.getClass();
                try {
                    Intent intent = new Intent(jStyle2208ABleManager.f, JStyle2208ABleService.class);
                    jStyle2208ABleManager.f.bindService(intent, jStyle2208ABleManager.c, 1);
                    if (Build.VERSION.SDK_INT >= 26) {
                        jStyle2208ABleManager.f.startForegroundService(intent);
                    } else {
                        jStyle2208ABleManager.f.startService(intent);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    BleApiUtils.checkExceptionAndShowNotification(e, jStyle2208ABleManager.f);
                }
            }
        }, Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS);
    }

    public void stopService() {
        JStyle2208ABleService jStyle2208ABleService = this.b;
        if (jStyle2208ABleService != null) {
            jStyle2208ABleService.stopService();
        }
    }

    public void stopServiceAndRetainMacAddress() {
        JStyle2208ABleService jStyle2208ABleService = this.b;
        if (jStyle2208ABleService != null) {
            jStyle2208ABleService.stopServiceRetainMacAddress();
        }
    }

    public void unbindService() {
        ServiceConnection serviceConnection = this.c;
        if (serviceConnection != null) {
            this.f.unbindService(serviceConnection);
        }
    }

    public void writeRequest(JStyleVBaseReq jStyleVBaseReq) {
        if (this.b == null || l == null || !isConnected()) {
            return;
        }
        this.b.clearResponseData();
        this.b.writeRequest(jStyleVBaseReq);
    }

    public void writeValue(byte[] bArr) {
        if (this.b == null || l == null || !isConnected()) {
            return;
        }
        this.b.writeValue(bArr);
    }

    public void writeValue() {
        JStyle2208ABleService jStyle2208ABleService = this.b;
        if (jStyle2208ABleService == null) {
            return;
        }
        jStyle2208ABleService.nextQueue();
    }
}
