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
import com.coveiot.android.bleabstract.services.jstyle2301.JStyle2301ABleService;
import com.coveiot.android.jstyle2301a.api.JStyleVBaseReq;
import com.coveiot.sdk.ble.CloveBleState;
import com.jstyle.blesdk2301.callback.BleConnectionListener;
import com.jstyle.blesdk2301.callback.OnScanResults;
import com.jstyle.blesdk2301.model.Device;
import java.util.List;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public class JStyle2301ABleManager {
    public static JStyle2301ABleManager l;

    /* renamed from: a  reason: collision with root package name */
    public String f4032a;
    public JStyle2301ABleService b;
    public BleConnectionListener bleConnectionListener1;
    public Intent d;
    public BluetoothAdapter e;
    public Context f;
    public ServiceConnection c = new ServiceConnection() { // from class: com.coveiot.android.bleabstract.services.jstyle2301.JStyle2301ABleManager.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            JStyle2301ABleManager.this.b = ((JStyle2301ABleService.LocalBinder) iBinder).getService();
            if (TextUtils.isEmpty(JStyle2301ABleManager.this.f4032a)) {
                return;
            }
            JStyle2301ABleManager jStyle2301ABleManager = JStyle2301ABleManager.this;
            jStyle2301ABleManager.b.initBluetoothDevice(jStyle2301ABleManager.f4032a, jStyle2301ABleManager.f, jStyle2301ABleManager.NeedReconnect1, jStyle2301ABleManager.bleConnectionListener1);
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            JStyle2301ABleManager.this.b = null;
        }
    };
    public boolean NeedReconnect1 = true;
    public String[] g = null;
    public OnScanResults h = null;
    public boolean i = false;
    @RequiresApi(api = 21)
    public final ScanCallback j = new ScanCallback() { // from class: com.coveiot.android.bleabstract.services.jstyle2301.JStyle2301ABleManager.2
        @Override // android.bluetooth.le.ScanCallback
        public void onBatchScanResults(List<ScanResult> list) {
            super.onBatchScanResults(list);
        }

        @Override // android.bluetooth.le.ScanCallback
        public void onScanFailed(int i) {
            super.onScanFailed(i);
            JStyle2301ABleManager.this.h.Fail(i);
        }

        @Override // android.bluetooth.le.ScanCallback
        public void onScanResult(int i, ScanResult scanResult) {
            super.onScanResult(i, scanResult);
            if (ContextCompat.checkSelfPermission(JStyle2301ABleManager.this.f, "android.permission.BLUETOOTH_CONNECT") != 0) {
                return;
            }
            String name = scanResult.getDevice().getName();
            if (JStyle2301ABleManager.this.i && !TextUtils.isEmpty(name) && JStyle2301ABleManager.this.a(name.toLowerCase().trim())) {
                Device device = new Device();
                device.setBluetoothDevice(scanResult.getDevice());
                device.setIsconted(false);
                device.setPaired(false);
                device.setIsdfu(name.toLowerCase().contains("dfu"));
                if (ContextCompat.checkSelfPermission(JStyle2301ABleManager.this.f, "android.permission.BLUETOOTH_CONNECT") != 0) {
                    return;
                }
                device.setName(scanResult.getDevice().getName());
                device.setMac(scanResult.getDevice().getAddress());
                device.setRiss(scanResult.getRssi());
                JStyle2301ABleManager.this.h.Success(device);
            }
        }
    };
    public Handler k = new Handler();

    /* renamed from: com.coveiot.android.bleabstract.services.jstyle2301.JStyle2301ABleManager$4  reason: invalid class name */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class AnonymousClass4 {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f4036a;

        static {
            int[] iArr = new int[CloveBleState.BleState.values().length];
            f4036a = iArr;
            try {
                iArr[CloveBleState.BleState.CONNECTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f4036a[CloveBleState.BleState.CONNECTING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f4036a[CloveBleState.BleState.DISCONNECTED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f4036a[CloveBleState.BleState.DISCOVERING_SERVICES.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f4036a[CloveBleState.BleState.SCANNING.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public JStyle2301ABleManager(Context context) {
        this.f = context;
        if (this.d == null) {
            Intent intent = new Intent(context, JStyle2301ABleService.class);
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

    public static JStyle2301ABleManager getInstance() {
        return l;
    }

    public static void init(Context context) {
        if (l == null) {
            synchronized (JStyle2301ABleManager.class) {
                if (l == null) {
                    l = new JStyle2301ABleManager(context);
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
        if (ContextCompat.checkSelfPermission(this.f, "android.permission.BLUETOOTH_CONNECT") != 0) {
            return;
        }
        Set<BluetoothDevice> bondedDevices = this.e.getBondedDevices();
        if (bondedDevices.size() > 0) {
            for (BluetoothDevice bluetoothDevice : bondedDevices) {
                if (a(bluetoothDevice.getName().toLowerCase())) {
                    Device device = new Device();
                    device.setBluetoothDevice(bluetoothDevice);
                    device.setIsconted(false);
                    device.setPaired(true);
                    if (ContextCompat.checkSelfPermission(this.f, "android.permission.BLUETOOTH_CONNECT") != 0) {
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

    @TargetApi(21)
    public void StopDeviceScan() {
        this.i = false;
        BluetoothAdapter bluetoothAdapter = this.e;
        if (bluetoothAdapter == null || bluetoothAdapter.getBluetoothLeScanner() == null || ContextCompat.checkSelfPermission(this.f, "android.permission.BLUETOOTH_SCAN") != 0) {
            return;
        }
        this.e.getBluetoothLeScanner().stopScan(this.j);
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
        JStyle2301ABleService jStyle2301ABleService = this.b;
        if (jStyle2301ABleService == null) {
            this.f4032a = str;
        } else {
            jStyle2301ABleService.initBluetoothDevice(str, this.f, z, bleConnectionListener);
        }
    }

    public void disconnectDevice() {
        JStyle2301ABleService jStyle2301ABleService = this.b;
        if (jStyle2301ABleService == null) {
            return;
        }
        jStyle2301ABleService.disconnectAndForget();
    }

    public void enableNotifaction() {
        JStyle2301ABleService jStyle2301ABleService = this.b;
        if (jStyle2301ABleService == null) {
            return;
        }
        jStyle2301ABleService.setCharacteristicNotification(true);
    }

    @NotNull
    public ConnectionStatus getConnectionState() {
        JStyle2301ABleService jStyle2301ABleService = this.b;
        if (jStyle2301ABleService != null) {
            int i = AnonymousClass4.f4036a[jStyle2301ABleService.getConnectionState().ordinal()];
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
        return ContextCompat.checkSelfPermission(this.f, "android.permission.BLUETOOTH_CONNECT") != 0 ? "" : ((BluetoothManager) this.f.getSystemService("bluetooth")).getAdapter().getRemoteDevice(str).getName();
    }

    public boolean isBleEnable() {
        if (ContextCompat.checkSelfPermission(this.f, "android.permission.BLUETOOTH_CONNECT") != 0) {
            return false;
        }
        return this.e.enable();
    }

    public boolean isConnected() {
        JStyle2301ABleService jStyle2301ABleService = this.b;
        if (jStyle2301ABleService == null) {
            return false;
        }
        return jStyle2301ABleService.isConnected();
    }

    public void offerValue(byte[] bArr) {
        JStyle2301ABleService jStyle2301ABleService = this.b;
        if (jStyle2301ABleService == null) {
            return;
        }
        jStyle2301ABleService.offerValue(bArr);
    }

    public void restartService() {
        JStyle2301ABleService jStyle2301ABleService = this.b;
        if (jStyle2301ABleService != null) {
            jStyle2301ABleService.restartService();
        }
        this.k.postDelayed(new Runnable() { // from class: com.coveiot.android.bleabstract.services.jstyle2301.JStyle2301ABleManager.3
            @Override // java.lang.Runnable
            public void run() {
                JStyle2301ABleManager jStyle2301ABleManager = JStyle2301ABleManager.this;
                jStyle2301ABleManager.getClass();
                try {
                    Intent intent = new Intent(jStyle2301ABleManager.f, JStyle2301ABleService.class);
                    jStyle2301ABleManager.f.bindService(intent, jStyle2301ABleManager.c, 1);
                    if (Build.VERSION.SDK_INT >= 26) {
                        jStyle2301ABleManager.f.startForegroundService(intent);
                    } else {
                        jStyle2301ABleManager.f.startService(intent);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    BleApiUtils.checkExceptionAndShowNotification(e, jStyle2301ABleManager.f);
                }
            }
        }, Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS);
    }

    public void stopService() {
        JStyle2301ABleService jStyle2301ABleService = this.b;
        if (jStyle2301ABleService != null) {
            jStyle2301ABleService.stopService();
        }
    }

    public void stopServiceAndRetainMacAddress() {
        JStyle2301ABleService jStyle2301ABleService = this.b;
        if (jStyle2301ABleService != null) {
            jStyle2301ABleService.stopServiceRetainMacAddress();
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
        JStyle2301ABleService jStyle2301ABleService = this.b;
        if (jStyle2301ABleService == null) {
            return;
        }
        jStyle2301ABleService.nextQueue();
    }
}
