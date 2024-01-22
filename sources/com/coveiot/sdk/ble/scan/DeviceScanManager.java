package com.coveiot.sdk.ble.scan;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanSettings;
import android.companion.AssociationRequest;
import android.companion.BluetoothLeDeviceFilter;
import android.companion.CompanionDeviceManager;
import android.content.Context;
import android.content.IntentSender;
import android.os.Build;
import android.os.Handler;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.coveiot.sdk.ble.R;
import com.coveiot.sdk.ble.scan.model.BleDevice;
import com.coveiot.sdk.ble.utils.BleUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
/* loaded from: classes9.dex */
public class DeviceScanManager {
    public static DeviceScanManager m;
    public Handler b;
    public BluetoothAdapter c;
    public boolean d;
    public String[] e;
    public ScanResult f;
    public BluetoothLeScanner g;
    public boolean h;
    public CompanionDeviceManager i;
    public Context j;

    /* renamed from: a  reason: collision with root package name */
    public List<BleDevice> f7578a = new ArrayList();
    @RequiresApi(21)
    public ScanCallback k = new c();
    public BluetoothAdapter.LeScanCallback l = new d();

    /* loaded from: classes9.dex */
    public class a implements Runnable {
        public final /* synthetic */ Context h;

        public a(Context context) {
            this.h = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                int i = Build.VERSION.SDK_INT;
                if (i < 21) {
                    DeviceScanManager.this.c.stopLeScan(DeviceScanManager.this.l);
                } else if (i >= 31 && ContextCompat.checkSelfPermission(this.h, "android.permission.BLUETOOTH_SCAN") != 0) {
                    return;
                } else {
                    DeviceScanManager.this.g.stopScan(DeviceScanManager.this.k);
                }
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            DeviceScanManager.this.d = false;
            DeviceScanManager.this.b();
        }
    }

    /* loaded from: classes9.dex */
    public class b implements Runnable {
        public final /* synthetic */ Context h;

        public b(Context context) {
            this.h = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                int i = Build.VERSION.SDK_INT;
                if (i < 21) {
                    DeviceScanManager.this.c.stopLeScan(DeviceScanManager.this.l);
                } else if (i >= 31 && ContextCompat.checkSelfPermission(this.h, "android.permission.BLUETOOTH_SCAN") != 0) {
                    return;
                } else {
                    DeviceScanManager.this.g.stopScan(DeviceScanManager.this.k);
                }
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            DeviceScanManager.this.b();
            DeviceScanManager.this.d = false;
        }
    }

    /* loaded from: classes9.dex */
    public class c extends ScanCallback {
        public c() {
        }

        @Override // android.bluetooth.le.ScanCallback
        public void onScanFailed(int i) {
            super.onScanFailed(i);
            if (DeviceScanManager.this.f != null) {
                DeviceScanManager.this.f.onScanFailed(i);
            }
        }

        @Override // android.bluetooth.le.ScanCallback
        public void onScanResult(int i, android.bluetooth.le.ScanResult scanResult) {
            super.onScanResult(i, scanResult);
            if (Build.VERSION.SDK_INT >= 21) {
                DeviceScanManager.this.l.onLeScan(scanResult.getDevice(), scanResult.getRssi(), scanResult.getScanRecord().getBytes());
            }
        }
    }

    /* loaded from: classes9.dex */
    public class d implements BluetoothAdapter.LeScanCallback {

        /* loaded from: classes9.dex */
        public class a implements Runnable {
            public final /* synthetic */ ArrayList h;

            public a(ArrayList arrayList) {
                this.h = arrayList;
            }

            @Override // java.lang.Runnable
            public void run() {
                DeviceScanManager.this.f.onDevicesFound(this.h, false);
            }
        }

        public d() {
        }

        @Override // android.bluetooth.BluetoothAdapter.LeScanCallback
        public void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
            BleDevice bleDevice = new BleDevice(bluetoothDevice, i);
            if ((ContextCompat.checkSelfPermission(DeviceScanManager.this.j, "android.permission.BLUETOOTH_CONNECT") == 0 || Build.VERSION.SDK_INT < 31) && DeviceScanManager.this.e(bluetoothDevice) && !DeviceScanManager.this.f7578a.contains(bleDevice)) {
                DeviceScanManager.this.f7578a.add(bleDevice);
                ArrayList arrayList = (ArrayList) ((ArrayList) DeviceScanManager.this.f7578a).clone();
                if (DeviceScanManager.this.h) {
                    return;
                }
                DeviceScanManager.this.b.post(new a(arrayList));
            }
        }
    }

    /* loaded from: classes9.dex */
    public class e extends CompanionDeviceManager.Callback {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ AssociationResult f7581a;
        public final /* synthetic */ Activity b;
        public final /* synthetic */ int c;

        public e(DeviceScanManager deviceScanManager, AssociationResult associationResult, Activity activity, int i) {
            this.f7581a = associationResult;
            this.b = activity;
            this.c = i;
        }

        @Override // android.companion.CompanionDeviceManager.Callback
        public void onDeviceFound(IntentSender intentSender) {
            this.f7581a.onAssociationSuccess(this.b.getString(R.string.device_found));
            try {
                ActivityCompat.startIntentSenderForResult(this.b, intentSender, this.c, null, 0, 0, 0, null);
            } catch (IntentSender.SendIntentException e) {
                e.printStackTrace();
            }
        }

        @Override // android.companion.CompanionDeviceManager.Callback
        public void onFailure(CharSequence charSequence) {
            this.f7581a.onAssociationFailed(charSequence);
        }
    }

    public DeviceScanManager(Context context) {
        this.j = context;
    }

    public static DeviceScanManager getInstance(Context context) {
        if (m == null) {
            m = new DeviceScanManager(context.getApplicationContext());
        }
        return m;
    }

    public final void b() {
        if (isScanningInProgress()) {
            this.f.onDevicesFound(this.f7578a, true);
            this.d = false;
        }
    }

    public final void c(Context context, String str, long j, ScanResult scanResult, boolean z) throws RuntimeException {
        if (isScanningInProgress()) {
            stopScan();
        }
        this.f7578a.clear();
        this.d = true;
        this.f = scanResult;
        this.c = ((BluetoothManager) context.getSystemService("bluetooth")).getAdapter();
        this.b = new Handler(context.getMainLooper());
        if (this.c.isEnabled()) {
            int i = Build.VERSION.SDK_INT;
            if (i >= 23 && ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_FINE_LOCATION") != 0 && ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_COARSE_LOCATION") != 0) {
                throw new RuntimeException("Enable Location permission for bluetooth scan");
            }
            if (i >= 21) {
                if (this.g == null) {
                    this.g = this.c.getBluetoothLeScanner();
                }
                this.g.flushPendingScanResults(this.k);
                try {
                    this.g.stopScan(this.k);
                } catch (IllegalStateException e2) {
                    e2.printStackTrace();
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            } else {
                this.c.stopLeScan(this.l);
            }
            this.b.postDelayed(new b(context), j);
            if (Build.VERSION.SDK_INT >= 21) {
                ScanSettings build = new ScanSettings.Builder().setScanMode(2).build();
                ScanFilter build2 = new ScanFilter.Builder().setDeviceAddress(str).build();
                ArrayList arrayList = new ArrayList();
                arrayList.add(build2);
                if (z) {
                    this.g.startScan((List<ScanFilter>) null, build, this.k);
                    return;
                } else {
                    this.g.startScan(arrayList, build, this.k);
                    return;
                }
            }
            this.c.startLeScan(this.l);
            return;
        }
        throw new RuntimeException("Enable Bluetooth before starting scan");
    }

    public final void d(Context context, String[] strArr, long j, ScanResult scanResult) throws RuntimeException {
        this.f7578a.clear();
        this.d = true;
        this.e = strArr;
        this.f = scanResult;
        this.c = ((BluetoothManager) context.getSystemService("bluetooth")).getAdapter();
        this.b = new Handler(context.getMainLooper());
        if (this.c.isEnabled()) {
            int i = Build.VERSION.SDK_INT;
            if (i >= 23 && ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_FINE_LOCATION") != 0 && ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_COARSE_LOCATION") != 0) {
                throw new RuntimeException("Enable Location permission for bluetooth scan");
            }
            if (i >= 21) {
                if (this.g == null) {
                    this.g = this.c.getBluetoothLeScanner();
                }
                this.g.flushPendingScanResults(this.k);
                try {
                    this.g.stopScan(this.k);
                } catch (IllegalStateException e2) {
                    e2.printStackTrace();
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            } else {
                this.c.stopLeScan(this.l);
            }
            this.b.postDelayed(new a(context), j);
            if (Build.VERSION.SDK_INT >= 21) {
                this.g.startScan((List<ScanFilter>) null, new ScanSettings.Builder().setScanMode(2).build(), this.k);
                return;
            }
            this.c.startLeScan(this.l);
            return;
        }
        throw new RuntimeException("Enable Bluetooth before starting scan");
    }

    @RequiresApi(api = 26)
    public void deassociateWithBle(Activity activity, String str) {
        CompanionDeviceManager companionDeviceManager = (CompanionDeviceManager) activity.getSystemService(CompanionDeviceManager.class);
        this.i = companionDeviceManager;
        if (companionDeviceManager.getAssociations() == null || !this.i.getAssociations().contains(str)) {
            return;
        }
        this.i.disassociate(str);
    }

    public final boolean e(BluetoothDevice bluetoothDevice) {
        if (ContextCompat.checkSelfPermission(this.j, "android.permission.BLUETOOTH_CONNECT") == 0 || Build.VERSION.SDK_INT < 31) {
            String name = bluetoothDevice.getName();
            try {
                String[] strArr = this.e;
                if (strArr != null) {
                    if (name == null) {
                        return false;
                    }
                    if (!BleUtils.doesExist(strArr, name.toLowerCase())) {
                        return false;
                    }
                }
                return true;
            } catch (Exception e2) {
                e2.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public boolean isScanningInProgress() {
        return this.d;
    }

    public void scanAllDevices(Context context, long j, boolean z, ScanResult scanResult) throws RuntimeException {
        this.h = z;
        d(context, null, j, scanResult);
    }

    public void scanDevicesWithFilter(Context context, String[] strArr, long j, boolean z, ScanResult scanResult) throws RuntimeException {
        this.h = z;
        d(context, strArr, j, scanResult);
    }

    @RequiresApi(api = 26)
    public void startAssociation(String[] strArr, Activity activity, int i, boolean z, AssociationResult associationResult) {
        this.i = (CompanionDeviceManager) activity.getSystemService(CompanionDeviceManager.class);
        AssociationRequest.Builder builder = new AssociationRequest.Builder();
        builder.setSingleDevice(z);
        if (strArr != null && strArr.length > 0) {
            for (String str : strArr) {
                builder.addDeviceFilter(new BluetoothLeDeviceFilter.Builder().setNamePattern(Pattern.compile(str, 2)).build());
            }
        }
        this.i.associate(builder.build(), new e(this, associationResult, activity, i), null);
    }

    public void startScanForMAcAddress(Context context, String str, long j, boolean z, ScanResult scanResult, boolean z2) throws RuntimeException {
        this.h = z;
        c(context, str, j, scanResult, z2);
    }

    public void stopScan() {
        try {
            int i = Build.VERSION.SDK_INT;
            if (i >= 21) {
                if (this.g != null) {
                    if (i >= 31 && ContextCompat.checkSelfPermission(this.j, "android.permission.BLUETOOTH_SCAN") != 0) {
                        return;
                    }
                    this.d = false;
                    this.g.stopScan(this.k);
                }
            } else {
                this.c.stopLeScan(this.l);
            }
        } catch (IllegalStateException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        this.d = false;
    }
}
