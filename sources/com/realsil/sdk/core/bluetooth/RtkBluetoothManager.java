package com.realsil.sdk.core.bluetooth;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import com.realsil.sdk.core.RtkCore;
import com.realsil.sdk.core.bluetooth.impl.BluetoothAdapterImpl;
import com.realsil.sdk.core.bluetooth.impl.BluetoothDeviceImpl;
import com.realsil.sdk.core.bluetooth.utils.BluetoothHelper;
import com.realsil.sdk.core.logger.ZLogger;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes12.dex */
public final class RtkBluetoothManager {
    public static final String EXTRA_REASON = "android.bluetooth.device.extra.REASON";
    public static final int INDICATOR_ACL = 2;
    public static final int INDICATOR_BOND = 4;
    public static final int INDICATOR_BT = 1;
    public static final int INDICATOR_FULL = 255;

    /* renamed from: a  reason: collision with root package name */
    public static RtkBluetoothManager f13559a;
    public boolean b;
    public boolean c;
    public Context d;
    public List<RtkBluetoothManagerCallback> e;
    public BluetoothManager f;
    public BluetoothAdapter g;
    public BluetoothBroadcastReceiver h = null;
    public Object i = new Object();
    public Object j = new Object();
    public boolean k = false;
    public Runnable l = new a();

    /* loaded from: classes12.dex */
    public class BluetoothBroadcastReceiver extends BroadcastReceiver {
        public BluetoothBroadcastReceiver() {
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // android.content.BroadcastReceiver
        @TargetApi(19)
        public void onReceive(Context context, Intent intent) {
            char c;
            String action = intent.getAction();
            action.hashCode();
            switch (action.hashCode()) {
                case -1530327060:
                    if (action.equals("android.bluetooth.adapter.action.STATE_CHANGED")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case -301431627:
                    if (action.equals("android.bluetooth.device.action.ACL_CONNECTED")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case -223687943:
                    if (action.equals("android.bluetooth.device.action.PAIRING_REQUEST")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 1546533238:
                    if (action.equals(BluetoothAdapterImpl.ACTION_BLE_ACL_CONNECTED)) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case 1652078734:
                    if (action.equals(BluetoothAdapterImpl.ACTION_BLE_ACL_DISCONNECTED)) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                case 1821585647:
                    if (action.equals("android.bluetooth.device.action.ACL_DISCONNECTED")) {
                        c = 5;
                        break;
                    }
                    c = 65535;
                    break;
                case 2116862345:
                    if (action.equals("android.bluetooth.device.action.BOND_STATE_CHANGED")) {
                        c = 6;
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
                    RtkBluetoothManager rtkBluetoothManager = RtkBluetoothManager.this;
                    rtkBluetoothManager.getClass();
                    String action2 = intent.getAction();
                    int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", -1);
                    ZLogger.d(String.format(Locale.US, "action=%s, state: %d->%d", action2, Integer.valueOf(intent.getIntExtra("android.bluetooth.adapter.extra.PREVIOUS_STATE", -1)), Integer.valueOf(intExtra)));
                    switch (intExtra) {
                        case 10:
                            if (rtkBluetoothManager.b) {
                                ZLogger.v("ACTION_STATE_CHANGED: STATE_OFF");
                            }
                            synchronized (rtkBluetoothManager.j) {
                                rtkBluetoothManager.j.notifyAll();
                            }
                            break;
                        case 11:
                            if (rtkBluetoothManager.b) {
                                ZLogger.v("ACTION_STATE_CHANGED: STATE_TURNING_ON");
                                break;
                            }
                            break;
                        case 12:
                            if (rtkBluetoothManager.b) {
                                ZLogger.v("ACTION_STATE_CHANGED: STATE_ON");
                            }
                            synchronized (rtkBluetoothManager.i) {
                                rtkBluetoothManager.i.notifyAll();
                            }
                            break;
                        case 13:
                            if (rtkBluetoothManager.b) {
                                ZLogger.v("ACTION_STATE_CHANGED: STATE_TURNING_OFF");
                                break;
                            }
                            break;
                        default:
                            if (rtkBluetoothManager.b) {
                                ZLogger.v("ACTION_STATE_CHANGED: " + intExtra);
                                break;
                            }
                            break;
                    }
                    List<RtkBluetoothManagerCallback> list = rtkBluetoothManager.e;
                    if (list != null) {
                        for (RtkBluetoothManagerCallback rtkBluetoothManagerCallback : list) {
                            rtkBluetoothManagerCallback.onBluetoothStateChaned(null, intExtra);
                        }
                        return;
                    }
                    return;
                case 1:
                    RtkBluetoothManager rtkBluetoothManager2 = RtkBluetoothManager.this;
                    rtkBluetoothManager2.getClass();
                    BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                    if (bluetoothDevice == null) {
                        return;
                    }
                    if (rtkBluetoothManager2.b) {
                        ZLogger.d(String.format(Locale.US, "%s: action=%s", BluetoothHelper.formatAddress(bluetoothDevice.getAddress(), true), intent.getAction()));
                    }
                    List<RtkBluetoothManagerCallback> list2 = rtkBluetoothManager2.e;
                    if (list2 != null) {
                        for (RtkBluetoothManagerCallback rtkBluetoothManagerCallback2 : list2) {
                            rtkBluetoothManagerCallback2.onAclConnectionStateChanged(bluetoothDevice, true);
                        }
                        return;
                    }
                    return;
                case 2:
                    RtkBluetoothManager rtkBluetoothManager3 = RtkBluetoothManager.this;
                    rtkBluetoothManager3.getClass();
                    BluetoothDevice bluetoothDevice2 = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                    if (bluetoothDevice2 == null) {
                        return;
                    }
                    int intExtra2 = intent.getIntExtra("android.bluetooth.device.extra.PAIRING_VARIANT", 0);
                    if (rtkBluetoothManager3.b) {
                        ZLogger.d(String.format(Locale.US, "%s: action=%s", BluetoothHelper.formatAddress(bluetoothDevice2.getAddress(), true), intent.getAction()));
                        ZLogger.v("android.bluetooth.device.extra.PAIRING_VARIANT>> " + BluetoothDeviceImpl.pairingVariantToString(intExtra2) + " (" + intExtra2 + ")");
                    }
                    List<RtkBluetoothManagerCallback> list3 = rtkBluetoothManager3.e;
                    if (list3 != null) {
                        for (RtkBluetoothManagerCallback rtkBluetoothManagerCallback3 : list3) {
                            rtkBluetoothManagerCallback3.onPairingRequestNotify(bluetoothDevice2, intExtra2);
                        }
                    }
                    if (bluetoothDevice2.getBondState() == 12) {
                        ZLogger.d("device already bonded: " + bluetoothDevice2.getAddress());
                        return;
                    }
                    return;
                case 3:
                    RtkBluetoothManager rtkBluetoothManager4 = RtkBluetoothManager.this;
                    rtkBluetoothManager4.getClass();
                    BluetoothDevice bluetoothDevice3 = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                    if (rtkBluetoothManager4.b) {
                        ZLogger.d(String.format(Locale.US, "%s: action=%s", BluetoothHelper.formatAddress(bluetoothDevice3.getAddress(), true), intent.getAction()));
                    }
                    List<RtkBluetoothManagerCallback> list4 = rtkBluetoothManager4.e;
                    if (list4 != null) {
                        for (RtkBluetoothManagerCallback rtkBluetoothManagerCallback4 : list4) {
                            rtkBluetoothManagerCallback4.onBleAclConnectionStateChanged(bluetoothDevice3, true);
                        }
                        return;
                    }
                    return;
                case 4:
                    RtkBluetoothManager rtkBluetoothManager5 = RtkBluetoothManager.this;
                    rtkBluetoothManager5.getClass();
                    BluetoothDevice bluetoothDevice4 = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                    if (bluetoothDevice4 == null) {
                        return;
                    }
                    if (rtkBluetoothManager5.b) {
                        ZLogger.d(String.format(Locale.US, "%s: action=%s", BluetoothHelper.formatAddress(bluetoothDevice4.getAddress(), true), intent.getAction()));
                    }
                    List<RtkBluetoothManagerCallback> list5 = rtkBluetoothManager5.e;
                    if (list5 != null) {
                        for (RtkBluetoothManagerCallback rtkBluetoothManagerCallback5 : list5) {
                            rtkBluetoothManagerCallback5.onBleAclConnectionStateChanged(bluetoothDevice4, false);
                        }
                        return;
                    }
                    return;
                case 5:
                    RtkBluetoothManager rtkBluetoothManager6 = RtkBluetoothManager.this;
                    rtkBluetoothManager6.getClass();
                    BluetoothDevice bluetoothDevice5 = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                    if (bluetoothDevice5 == null) {
                        return;
                    }
                    if (rtkBluetoothManager6.b) {
                        ZLogger.d(String.format(Locale.US, "%s: action=%s", BluetoothHelper.formatAddress(bluetoothDevice5.getAddress(), true), intent.getAction()));
                    }
                    List<RtkBluetoothManagerCallback> list6 = rtkBluetoothManager6.e;
                    if (list6 != null) {
                        for (RtkBluetoothManagerCallback rtkBluetoothManagerCallback6 : list6) {
                            rtkBluetoothManagerCallback6.onAclConnectionStateChanged(bluetoothDevice5, false);
                        }
                        return;
                    }
                    return;
                case 6:
                    RtkBluetoothManager rtkBluetoothManager7 = RtkBluetoothManager.this;
                    rtkBluetoothManager7.getClass();
                    BluetoothDevice bluetoothDevice6 = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                    int intExtra3 = intent.getIntExtra("android.bluetooth.device.extra.PREVIOUS_BOND_STATE", -1);
                    int intExtra4 = intent.getIntExtra("android.bluetooth.device.extra.BOND_STATE", Integer.MIN_VALUE);
                    int intExtra5 = intent.getIntExtra(RtkBluetoothManager.EXTRA_REASON, -1);
                    if (bluetoothDevice6 == null) {
                        return;
                    }
                    ZLogger.d(String.format(Locale.US, "%s: action=%s, bondState:%d->%d, reason=%d", BluetoothHelper.formatAddress(bluetoothDevice6.getAddress(), true), intent.getAction(), Integer.valueOf(intExtra3), Integer.valueOf(intExtra4), Integer.valueOf(intExtra5)));
                    List<RtkBluetoothManagerCallback> list7 = rtkBluetoothManager7.e;
                    if (list7 != null) {
                        for (RtkBluetoothManagerCallback rtkBluetoothManagerCallback7 : list7) {
                            rtkBluetoothManagerCallback7.onBondStateChanged(bluetoothDevice6, intExtra4);
                        }
                        return;
                    }
                    return;
                default:
                    ZLogger.d("action:" + action);
                    return;
            }
        }
    }

    /* loaded from: classes12.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ZLogger.d("scan delay time reached");
            RtkBluetoothManager.this.b();
        }
    }

    public RtkBluetoothManager(Context context) {
        this.b = false;
        this.c = false;
        this.d = context.getApplicationContext();
        this.b = RtkCore.DEBUG;
        this.c = RtkCore.VDBG;
        a();
    }

    public static RtkBluetoothManager getInstance() {
        return f13559a;
    }

    public static void initial(Context context) {
        if (f13559a == null) {
            synchronized (RtkBluetoothManager.class) {
                if (f13559a == null) {
                    f13559a = new RtkBluetoothManager(context);
                }
            }
        }
    }

    public final boolean a() {
        Context context = this.d;
        if (context == null) {
            ZLogger.w("not intialized");
            return false;
        }
        if (this.g == null) {
            if (Build.VERSION.SDK_INT >= 18) {
                BluetoothManager bluetoothManager = (BluetoothManager) context.getSystemService("bluetooth");
                this.f = bluetoothManager;
                if (bluetoothManager == null) {
                    ZLogger.w("Unable to initialize BluetoothManager.");
                    return false;
                }
                this.g = bluetoothManager.getAdapter();
            } else {
                this.g = BluetoothAdapter.getDefaultAdapter();
            }
            if (this.g == null) {
                ZLogger.w("Unable to obtain a BluetoothAdapter.");
                return false;
            }
        }
        if (this.g == null) {
            ZLogger.w("mBluetoothAdapter == null");
            return true;
        }
        this.h = new BluetoothBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        intentFilter.addAction(BluetoothAdapterImpl.ACTION_BLE_ACL_CONNECTED);
        intentFilter.addAction(BluetoothAdapterImpl.ACTION_BLE_ACL_DISCONNECTED);
        intentFilter.addAction("android.bluetooth.device.action.PAIRING_REQUEST");
        intentFilter.addAction("android.bluetooth.device.action.BOND_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.device.action.ACL_CONNECTED");
        intentFilter.addAction("android.bluetooth.device.action.ACL_DISCONNECTED");
        intentFilter.addAction("android.bluetooth.device.action.ACL_DISCONNECT_REQUESTED");
        this.d.registerReceiver(this.h, intentFilter);
        return true;
    }

    public void addManagerCallback(RtkBluetoothManagerCallback rtkBluetoothManagerCallback) {
        if (this.e == null) {
            this.e = new CopyOnWriteArrayList();
        }
        if (this.e.contains(rtkBluetoothManagerCallback)) {
            return;
        }
        this.e.add(rtkBluetoothManagerCallback);
    }

    public final boolean b() {
        BluetoothAdapter bluetoothAdapter = this.g;
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
            ZLogger.w("bluetooth is not supported or disabled");
        }
        if (this.g.isDiscovering()) {
            if (this.b) {
                ZLogger.d("stopInquiry()");
            }
            return this.g.cancelDiscovery();
        }
        return true;
    }

    public void close() {
        ZLogger.v("close()");
        Context context = this.d;
        if (context != null) {
            try {
                context.unregisterReceiver(this.h);
            } catch (Exception e) {
                ZLogger.e(e.toString());
            }
        }
    }

    public boolean createBond(byte[] bArr) {
        return createBond(BluetoothHelper.convertMac(bArr));
    }

    public boolean disableBT() {
        return disableBT(true);
    }

    public boolean enableBT() {
        return enableBT(true);
    }

    public boolean isBleEnabled() {
        BluetoothManager bluetoothManager = this.f;
        BluetoothAdapter adapter = bluetoothManager != null ? bluetoothManager.getAdapter() : null;
        return adapter != null && adapter.isEnabled();
    }

    public boolean isBleSupported() {
        return this.d.getPackageManager().hasSystemFeature("android.hardware.bluetooth_le");
    }

    @TargetApi(19)
    public boolean pair(byte[] bArr) {
        if (this.g == null) {
            return false;
        }
        byte[] bArr2 = {bArr[5], bArr[4], bArr[3], bArr[2], bArr[1], bArr[0]};
        if (this.b) {
            ZLogger.d("createBondMac=" + BluetoothHelper.convertMac(bArr));
        }
        BluetoothDevice remoteDevice = this.g.getRemoteDevice(bArr2);
        int bondState = remoteDevice.getBondState();
        if (this.b) {
            ZLogger.d("attempt to createBond, state=" + Integer.toString(bondState));
        }
        return remoteDevice.createBond();
    }

    public void removeManagerCallback(RtkBluetoothManagerCallback rtkBluetoothManagerCallback) {
        List<RtkBluetoothManagerCallback> list = this.e;
        if (list != null) {
            list.remove(rtkBluetoothManagerCallback);
        }
    }

    public boolean reset() {
        b();
        unBondAllDevices();
        BluetoothAdapter bluetoothAdapter = this.g;
        if (bluetoothAdapter == null) {
            ZLogger.w("BT is not initialized");
            return false;
        } else if (bluetoothAdapter.isEnabled()) {
            this.k = true;
            if (this.c) {
                ZLogger.v("isNeedAutoEnableBt=" + this.k);
            }
            disableBT();
            try {
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (this.g.isEnabled()) {
                if (this.b) {
                    ZLogger.d("BT already enabled");
                }
                return true;
            }
            boolean enableBT = enableBT();
            if (this.b) {
                ZLogger.d("enableBT: " + enableBT);
            }
            return enableBT;
        } else {
            return enableBT();
        }
    }

    public void setInterruptPairRequest(boolean z) {
    }

    public boolean setScanMode(int i, int i2) {
        if (i == 20 || i == 21) {
            ZLogger.d("SCAN_MODE_NONE or SCAN_MODE_CONNECTABLE");
            return BluetoothAdapterImpl.setScanMode(this.g, i, 0);
        } else if (i != 23) {
            return true;
        } else {
            ZLogger.d("SCAN_MODE_CONNECTABLE_DISCOVERABLE");
            return BluetoothAdapterImpl.setScanMode(this.g, i, i2);
        }
    }

    public boolean startDiscovery(int i) {
        return startDiscovery(i, null);
    }

    public boolean unBondAllDevices() {
        BluetoothAdapter bluetoothAdapter = this.g;
        if (bluetoothAdapter != null && bluetoothAdapter.isEnabled()) {
            Set<BluetoothDevice> bondedDevices = this.g.getBondedDevices();
            if (bondedDevices != null && bondedDevices.size() > 0) {
                for (BluetoothDevice bluetoothDevice : bondedDevices) {
                    int i = 0;
                    while (true) {
                        if (i >= 10) {
                            break;
                        }
                        int bondState = bluetoothDevice.getBondState();
                        if (bondState == 10) {
                            ZLogger.d(this.b, "already unbond: " + bluetoothDevice.getName());
                            break;
                        } else if (bondState == 11) {
                            boolean cancelBondProcess = BluetoothDeviceImpl.cancelBondProcess(bluetoothDevice);
                            if (this.b) {
                                ZLogger.v(String.format(Locale.US, "cancelBondProcess(%d): %s, ret=%b", Integer.valueOf(i), BluetoothHelper.formatAddress(bluetoothDevice.getAddress(), true), Boolean.valueOf(cancelBondProcess)));
                            }
                            if (cancelBondProcess) {
                                break;
                            }
                            i++;
                        } else {
                            if (bondState == 12) {
                                boolean removeBond = BluetoothDeviceImpl.removeBond(bluetoothDevice);
                                if (this.b) {
                                    ZLogger.v(String.format(Locale.US, "removeBond(%d): %s, ret=%b", Integer.valueOf(i), BluetoothHelper.formatAddress(bluetoothDevice.getAddress(), true), Boolean.valueOf(removeBond)));
                                }
                                if (removeBond) {
                                    break;
                                }
                            } else {
                                continue;
                            }
                            i++;
                        }
                    }
                }
                return true;
            }
            ZLogger.d("no bond device exist");
            return true;
        }
        ZLogger.w("bluetooth is not enabled");
        return false;
    }

    public boolean unBondDevice(byte[] bArr) {
        BluetoothAdapter bluetoothAdapter = this.g;
        if (bluetoothAdapter != null && bluetoothAdapter.isEnabled()) {
            String convertMac = BluetoothHelper.convertMac(bArr);
            Set<BluetoothDevice> bondedDevices = this.g.getBondedDevices();
            if (bondedDevices == null || bondedDevices.size() <= 0) {
                return true;
            }
            for (BluetoothDevice bluetoothDevice : bondedDevices) {
                if (convertMac.compareToIgnoreCase(bluetoothDevice.getAddress()) == 0) {
                    while (true) {
                        int bondState = bluetoothDevice.getBondState();
                        if (bondState == 10) {
                            return true;
                        }
                        if (bondState == 11) {
                            BluetoothDeviceImpl.cancelBondProcess(bluetoothDevice);
                        }
                        if (this.b) {
                            ZLogger.d("unBondDevice: " + convertMac);
                        }
                        boolean removeBond = BluetoothDeviceImpl.removeBond(bluetoothDevice);
                        ZLogger.d("removeBond finished:" + removeBond);
                    }
                }
            }
            return true;
        } else if (this.b) {
            ZLogger.d("bluetooth is not enabled");
            return false;
        } else {
            return false;
        }
    }

    public boolean createBond(String str) {
        BluetoothAdapter bluetoothAdapter = this.g;
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
            if (this.b) {
                ZLogger.d("bluetooth is not enabled");
            }
            return false;
        } else if (str == null) {
            if (this.b) {
                ZLogger.d("mac cannot be null");
            }
            return false;
        } else {
            if (this.b) {
                ZLogger.d("createBondMac=" + str);
            }
            BluetoothDevice remoteDevice = this.g.getRemoteDevice(str);
            int bondState = remoteDevice.getBondState();
            if (bondState == 12) {
                ZLogger.d("device already bonded: " + bondState);
                return true;
            }
            if (this.b) {
                ZLogger.d("attempt to createBond, state=" + Integer.toString(bondState));
            }
            return BluetoothDeviceImpl.createBond(remoteDevice);
        }
    }

    public boolean disableBT(boolean z) {
        BluetoothAdapter bluetoothAdapter = this.g;
        if (bluetoothAdapter == null) {
            ZLogger.w("BT is not initialized..!");
            return false;
        } else if (bluetoothAdapter.getState() == 10) {
            ZLogger.w("BT already OFF");
            return true;
        } else if (!this.g.disable()) {
            ZLogger.d("disable BT failed");
            return false;
        } else if (z) {
            synchronized (this.j) {
                try {
                    if (this.b) {
                        ZLogger.d("wait BT disable...");
                    }
                    this.j.wait(10000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    ZLogger.e(e.toString());
                }
            }
            if (this.g.isEnabled()) {
                ZLogger.w("BT disable failed");
                return false;
            }
            if (this.b) {
                ZLogger.d("BT disable success");
            }
            return true;
        } else {
            return true;
        }
    }

    public boolean enableBT(boolean z) {
        BluetoothAdapter bluetoothAdapter = this.g;
        if (bluetoothAdapter == null) {
            ZLogger.w("mBluetoothAdapter == null");
            return false;
        } else if (bluetoothAdapter.getState() == 12) {
            if (this.b) {
                ZLogger.d("BT already on");
            }
            return true;
        } else {
            this.k = false;
            if (this.c) {
                ZLogger.v("isNeedAutoEnableBt=" + this.k);
            }
            if (!this.g.enable()) {
                ZLogger.w("BT enable fail");
                return false;
            } else if (z) {
                synchronized (this.i) {
                    try {
                        if (this.b) {
                            ZLogger.v("wait BT enable...");
                        }
                        this.i.wait(10000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        ZLogger.e(e.toString());
                    }
                }
                if (this.g.isEnabled()) {
                    if (this.b) {
                        ZLogger.d("BT enable success");
                    }
                    return true;
                }
                ZLogger.d("BT enable fail");
                return false;
            } else {
                return true;
            }
        }
    }

    public boolean startDiscovery(int i, String str) {
        BluetoothAdapter bluetoothAdapter = this.g;
        if (bluetoothAdapter != null && bluetoothAdapter.isEnabled()) {
            if (this.g.isDiscovering()) {
                this.g.cancelDiscovery();
            }
            if (this.b) {
                ZLogger.d("address=" + str + " , timeout=" + i);
            }
            this.g.startDiscovery();
            return true;
        }
        ZLogger.w("bluetooth is not supported or disabled");
        return false;
    }
}
