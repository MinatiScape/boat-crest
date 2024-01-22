package com.jieli.jl_bt_ota.impl;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.ParcelUuid;
import android.os.Parcelable;
import android.text.TextUtils;
import com.jieli.jl_bt_ota.constant.BluetoothConstant;
import com.jieli.jl_bt_ota.util.BluetoothUtil;
import com.jieli.jl_bt_ota.util.CommonUtil;
import com.jieli.jl_bt_ota.util.JL_Log;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/* loaded from: classes11.dex */
public abstract class BluetoothBreProfiles extends BluetoothDiscovery {
    private BluetoothHeadset r;
    private BluetoothA2dp s;
    private BluetoothHandFreeReceiver t;
    private final BluetoothProfile.ServiceListener u;

    /* loaded from: classes11.dex */
    public class BluetoothHandFreeReceiver extends BroadcastReceiver {
        private BluetoothHandFreeReceiver() {
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            char c;
            if (intent != null) {
                String action = intent.getAction();
                BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                if (TextUtils.isEmpty(action) || bluetoothDevice == null) {
                    return;
                }
                Objects.requireNonNull(action);
                String str = action;
                switch (str.hashCode()) {
                    case -377527494:
                        if (str.equals("android.bluetooth.device.action.UUID")) {
                            c = 0;
                            break;
                        }
                        c = 65535;
                        break;
                    case 545516589:
                        if (str.equals("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED")) {
                            c = 1;
                            break;
                        }
                        c = 65535;
                        break;
                    case 1244161670:
                        if (str.equals("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED")) {
                            c = 2;
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
                        Parcelable[] parcelableArrayExtra = intent.getParcelableArrayExtra("android.bluetooth.device.extra.UUID");
                        if (parcelableArrayExtra == null) {
                            JL_Log.i(BluetoothBreProfiles.this.TAG, "onReceive: ACTION_UUID no uuids");
                            return;
                        }
                        ParcelUuid[] parcelUuidArr = new ParcelUuid[parcelableArrayExtra.length];
                        for (int i = 0; i < parcelableArrayExtra.length; i++) {
                            parcelUuidArr[i] = ParcelUuid.fromString(parcelableArrayExtra[i].toString());
                            JL_Log.i(BluetoothBreProfiles.this.TAG, "onReceive: ACTION_UUID " + parcelUuidArr[i].toString());
                        }
                        return;
                    case 1:
                        try {
                            int intExtra = intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
                            JL_Log.i(BluetoothBreProfiles.this.TAG, "onReceive: hfp ACTION_CONNECTION_STATE_CHANGED device : " + BluetoothBreProfiles.this.printBtDeviceInfo(bluetoothDevice) + ", state : " + intExtra);
                            BluetoothBreProfiles.this.onHfpStatus(bluetoothDevice, intExtra);
                            return;
                        } catch (Exception e) {
                            e.printStackTrace();
                            return;
                        }
                    case 2:
                        try {
                            int intExtra2 = intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
                            JL_Log.i(BluetoothBreProfiles.this.TAG, "onReceive: a2dp ACTION_CONNECTION_STATE_CHANGED device : " + BluetoothBreProfiles.this.printBtDeviceInfo(bluetoothDevice) + ", state : " + intExtra2);
                            BluetoothBreProfiles.this.onA2dpStatus(bluetoothDevice, intExtra2);
                            return;
                        } catch (Exception e2) {
                            e2.printStackTrace();
                            return;
                        }
                    default:
                        return;
                }
            }
        }
    }

    public BluetoothBreProfiles(Context context) {
        super(context);
        this.u = new BluetoothProfile.ServiceListener() { // from class: com.jieli.jl_bt_ota.impl.BluetoothBreProfiles.1
            @Override // android.bluetooth.BluetoothProfile.ServiceListener
            public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
                String str = BluetoothBreProfiles.this.TAG;
                JL_Log.i(str, "------------onServiceConnected--------profile=" + i);
                if (2 == i) {
                    BluetoothBreProfiles.this.s = (BluetoothA2dp) bluetoothProfile;
                } else if (1 == i) {
                    BluetoothBreProfiles.this.r = (BluetoothHeadset) bluetoothProfile;
                }
            }

            @Override // android.bluetooth.BluetoothProfile.ServiceListener
            public void onServiceDisconnected(int i) {
                JL_Log.i(BluetoothBreProfiles.this.TAG, "------------onServiceDisconnected--------");
                if (2 == i) {
                    BluetoothBreProfiles.this.s = null;
                } else if (1 == i) {
                    BluetoothBreProfiles.this.r = null;
                }
            }
        };
        a(context);
        a();
    }

    private void b() {
        Context context;
        BluetoothHandFreeReceiver bluetoothHandFreeReceiver = this.t;
        if (bluetoothHandFreeReceiver == null || (context = this.context) == null) {
            return;
        }
        context.unregisterReceiver(bluetoothHandFreeReceiver);
        this.t = null;
    }

    public boolean deviceHasA2dp(BluetoothDevice bluetoothDevice) {
        return BluetoothUtil.deviceHasProfile(this.context, bluetoothDevice, BluetoothConstant.UUID_A2DP);
    }

    public boolean deviceHasHfp(BluetoothDevice bluetoothDevice) {
        return BluetoothUtil.deviceHasProfile(this.context, bluetoothDevice, BluetoothConstant.UUID_HFP);
    }

    @SuppressLint({"MissingPermission"})
    public boolean disconnectByProfiles(BluetoothDevice bluetoothDevice) {
        boolean z = false;
        if (bluetoothDevice != null && CommonUtil.checkHasConnectPermission(this.context)) {
            String str = this.TAG;
            JL_Log.i(str, "-disconnectByProfiles- device : " + printBtDeviceInfo(bluetoothDevice));
            if (bluetoothDevice.getType() != 2) {
                int isConnectedByA2dp = isConnectedByA2dp(bluetoothDevice);
                if (isConnectedByA2dp == 2) {
                    z = disconnectFromA2dp(bluetoothDevice);
                    String str2 = this.TAG;
                    JL_Log.i(str2, "-disconnectByProfiles- disconnectFromA2dp ret : " + z);
                }
                int isConnectedByHfp = isConnectedByHfp(bluetoothDevice);
                if (isConnectedByHfp == 2) {
                    boolean disconnectFromHfp = disconnectFromHfp(bluetoothDevice);
                    String str3 = this.TAG;
                    JL_Log.i(str3, "-disconnectByProfiles- disconnectFromHfp ret : " + disconnectFromHfp);
                    z = disconnectFromHfp;
                }
                if (isConnectedByA2dp == 0 && isConnectedByHfp == 0) {
                    return true;
                }
                return z;
            }
            return false;
        }
        JL_Log.i(this.TAG, "-disconnectByProfiles- device is null ");
        return false;
    }

    public boolean disconnectFromA2dp(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            JL_Log.e(this.TAG, "-disconnectFromA2dp- device is null");
            return false;
        } else if (this.s == null) {
            JL_Log.e(this.TAG, "-disconnectFromA2dp- mBluetoothA2dp is null");
            return false;
        } else {
            int isConnectedByA2dp = isConnectedByA2dp(bluetoothDevice);
            if (isConnectedByA2dp == 0) {
                JL_Log.i(this.TAG, "-disconnectFromA2dp- A2dp is disconnected");
                return true;
            }
            boolean disconnectDeviceA2dp = isConnectedByA2dp == 2 ? BluetoothUtil.disconnectDeviceA2dp(this.context, this.s, bluetoothDevice) : false;
            String str = this.TAG;
            JL_Log.i(str, "-disconnectFromA2dp- ret : " + disconnectDeviceA2dp);
            return disconnectDeviceA2dp;
        }
    }

    public boolean disconnectFromHfp(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            JL_Log.e(this.TAG, "-disconnectFromHfp- device is null");
            return false;
        } else if (this.r == null) {
            JL_Log.e(this.TAG, "-disconnectFromHfp- mBluetoothHfp is null");
            return false;
        } else {
            int isConnectedByHfp = isConnectedByHfp(bluetoothDevice);
            if (isConnectedByHfp == 0) {
                JL_Log.i(this.TAG, "-disconnectFromHfp- hfp is disconnected");
                return true;
            }
            boolean disconnectDeviceHfp = isConnectedByHfp == 2 ? BluetoothUtil.disconnectDeviceHfp(this.context, this.r, bluetoothDevice) : false;
            String str = this.TAG;
            JL_Log.i(str, "-disconnectFromHfp- ret : " + disconnectDeviceHfp);
            return disconnectDeviceHfp;
        }
    }

    public BluetoothHeadset getBluetoothHfp() {
        return this.r;
    }

    @SuppressLint({"MissingPermission"})
    public List<BluetoothDevice> getDevicesConnectedByProfile() {
        if (CommonUtil.checkHasConnectPermission(this.context)) {
            BluetoothHeadset bluetoothHeadset = this.r;
            List<BluetoothDevice> connectedDevices = bluetoothHeadset != null ? bluetoothHeadset.getConnectedDevices() : null;
            ArrayList arrayList = connectedDevices != null ? new ArrayList(connectedDevices) : null;
            BluetoothA2dp bluetoothA2dp = this.s;
            if (bluetoothA2dp != null) {
                connectedDevices = bluetoothA2dp.getConnectedDevices();
            }
            if (connectedDevices != null) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.addAll(connectedDevices);
            }
            return arrayList;
        }
        return null;
    }

    public BluetoothA2dp getmBluetoothA2dp() {
        return this.s;
    }

    @SuppressLint({"MissingPermission"})
    public int isConnectedByA2dp(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice != null && CommonUtil.checkHasConnectPermission(this.context)) {
            BluetoothA2dp bluetoothA2dp = this.s;
            if (bluetoothA2dp == null) {
                JL_Log.e(this.TAG, "-isConnectedByA2dp- mBluetoothA2dp is null");
                return 4100;
            }
            List<BluetoothDevice> connectedDevices = bluetoothA2dp.getConnectedDevices();
            if (connectedDevices != null) {
                for (BluetoothDevice bluetoothDevice2 : connectedDevices) {
                    if (bluetoothDevice2.getAddress().equals(bluetoothDevice.getAddress())) {
                        JL_Log.i(this.TAG, "-isConnectedByA2dp- ret : true");
                        return 2;
                    }
                }
            } else {
                JL_Log.i(this.TAG, "-isConnectedByA2dp- connect list is null");
            }
            JL_Log.i(this.TAG, "-isConnectedByA2dp- ret : false");
            return this.s.getConnectionState(bluetoothDevice);
        }
        JL_Log.e(this.TAG, "-isConnectedByA2dp- device is null");
        return 0;
    }

    @SuppressLint({"MissingPermission"})
    public int isConnectedByHfp(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice != null && CommonUtil.checkHasConnectPermission(this.context)) {
            BluetoothHeadset bluetoothHeadset = this.r;
            if (bluetoothHeadset == null) {
                JL_Log.e(this.TAG, "-isConnectedByHfp- mBluetoothHfp is null");
                return 4100;
            }
            List<BluetoothDevice> connectedDevices = bluetoothHeadset.getConnectedDevices();
            if (connectedDevices != null) {
                for (BluetoothDevice bluetoothDevice2 : connectedDevices) {
                    if (bluetoothDevice2.getAddress().equals(bluetoothDevice.getAddress())) {
                        JL_Log.i(this.TAG, "-isConnectedByHfp- ret : true.");
                        return 2;
                    }
                }
            } else {
                JL_Log.i(this.TAG, "-isConnectedByHfp- no connect list");
            }
            int connectionState = this.r.getConnectionState(bluetoothDevice);
            String str = this.TAG;
            JL_Log.i(str, "-isConnectedByHfp- ret : " + connectionState);
            return connectionState;
        }
        JL_Log.e(this.TAG, "-isConnectedByHfp- device is null");
        return 0;
    }

    @SuppressLint({"MissingPermission"})
    public int isConnectedByProfile(BluetoothDevice bluetoothDevice) {
        if (!CommonUtil.checkHasConnectPermission(this.context)) {
            JL_Log.w(this.TAG, "-isConnectedByProfile- no connect permission.");
            return 0;
        } else if (bluetoothDevice == null) {
            JL_Log.e(this.TAG, "-isConnectedByProfile- device is null.");
            return 0;
        } else if (this.r != null && this.s != null) {
            if (bluetoothDevice.getType() == 2) {
                JL_Log.e(this.TAG, "device is Invalid.");
                return 0;
            }
            List<BluetoothDevice> connectedDevices = this.r.getConnectedDevices();
            if (connectedDevices != null) {
                for (BluetoothDevice bluetoothDevice2 : connectedDevices) {
                    if (bluetoothDevice2.getAddress().equals(bluetoothDevice.getAddress())) {
                        JL_Log.w(this.TAG, "device connect hfp.");
                        return 2;
                    }
                }
            }
            List<BluetoothDevice> connectedDevices2 = this.s.getConnectedDevices();
            if (connectedDevices2 != null) {
                for (BluetoothDevice bluetoothDevice3 : connectedDevices2) {
                    if (bluetoothDevice3.getAddress().equals(bluetoothDevice.getAddress())) {
                        JL_Log.w(this.TAG, "device connect a2dp.");
                        return 2;
                    }
                }
            }
            return 0;
        } else {
            JL_Log.e(this.TAG, "mBluetoothHfp or mBluetoothA2dp is null.");
            a(this.context);
            return 4100;
        }
    }

    @Override // com.jieli.jl_bt_ota.impl.BluetoothDiscovery, com.jieli.jl_bt_ota.impl.BluetoothBase, com.jieli.jl_bt_ota.interfaces.IUpgradeManager
    public void release() {
        BluetoothAdapter bluetoothAdapter;
        BluetoothAdapter bluetoothAdapter2;
        super.release();
        b();
        BluetoothA2dp bluetoothA2dp = this.s;
        if (bluetoothA2dp != null && (bluetoothAdapter2 = this.mBluetoothAdapter) != null) {
            bluetoothAdapter2.closeProfileProxy(2, bluetoothA2dp);
            this.s = null;
        }
        BluetoothHeadset bluetoothHeadset = this.r;
        if (bluetoothHeadset == null || (bluetoothAdapter = this.mBluetoothAdapter) == null) {
            return;
        }
        bluetoothAdapter.closeProfileProxy(1, bluetoothHeadset);
        this.r = null;
    }

    private boolean a(Context context) {
        boolean z;
        boolean z2 = false;
        if (context == null) {
            return false;
        }
        BluetoothAdapter bluetoothAdapter = this.mBluetoothAdapter;
        if (bluetoothAdapter == null) {
            JL_Log.e(this.TAG, "get bluetooth adapter is null.");
            return false;
        }
        if (this.s == null) {
            try {
                z2 = bluetoothAdapter.getProfileProxy(context, this.u, 2);
                if (!z2) {
                    JL_Log.e(this.TAG, "BluetoothBreProfiles: a2dp error.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (this.r != null) {
            return true;
        }
        try {
            z = this.mBluetoothAdapter.getProfileProxy(context, this.u, 1);
            if (z) {
                return z;
            }
            try {
                JL_Log.e(this.TAG, "BluetoothBreProfiles: hfp error");
                return z;
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                return z;
            }
        } catch (Exception e3) {
            e = e3;
            z = z2;
        }
    }

    public boolean disconnectFromA2dp(String str) {
        BluetoothDevice remoteDevice = BluetoothUtil.getRemoteDevice(str);
        return remoteDevice != null && disconnectFromA2dp(remoteDevice);
    }

    public boolean disconnectFromHfp(String str) {
        BluetoothDevice remoteDevice = BluetoothUtil.getRemoteDevice(str);
        return remoteDevice != null && disconnectFromHfp(remoteDevice);
    }

    private void a() {
        if (this.t != null || this.context == null) {
            return;
        }
        this.t = new BluetoothHandFreeReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.device.action.UUID");
        intentFilter.addAction("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.a2dp.profile.action.PLAYING_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED");
        this.context.registerReceiver(this.t, intentFilter);
    }
}
