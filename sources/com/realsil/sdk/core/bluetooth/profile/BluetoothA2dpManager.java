package com.realsil.sdk.core.bluetooth.profile;

import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.realsil.sdk.core.bluetooth.impl.BluetoothProfileImpl;
import com.realsil.sdk.core.bluetooth.utils.BluetoothHelper;
import com.realsil.sdk.core.logger.ZLogger;
import java.util.List;
/* loaded from: classes12.dex */
public final class BluetoothA2dpManager extends PublicProfileManager<BluetoothA2dp> {
    public static BluetoothA2dpManager f;
    public BtBroadcastReceiver g;
    public BluetoothProfile.ServiceListener h;

    /* loaded from: classes12.dex */
    public class BtBroadcastReceiver extends BroadcastReceiver {
        public BtBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
            action.hashCode();
            if (action.equals("android.bluetooth.a2dp.profile.action.PLAYING_STATE_CHANGED")) {
                int intExtra = intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
                int intExtra2 = intent.getIntExtra("android.bluetooth.profile.extra.PREVIOUS_STATE", -1);
                if (intExtra == 10) {
                    ZLogger.d("A2DP_PLAYING_STATE: STATE_PLAYING");
                } else if (intExtra == 11) {
                    ZLogger.d("A2DP_PLAYING_STATE: STATE_NOT_PLAYING");
                } else {
                    ZLogger.d("A2DP_PLAYING_STATE: " + intExtra2 + " > " + intExtra);
                }
            } else if (action.equals("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED")) {
                int intExtra3 = intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
                int intExtra4 = intent.getIntExtra("android.bluetooth.profile.extra.PREVIOUS_STATE", -1);
                if (intExtra3 == 2) {
                    ZLogger.d("A2DP_CONNECTION_STATE: STATE_CONNECTED");
                } else if (intExtra3 == 0) {
                    ZLogger.d("A2DP_CONNECTION_STATE: STATE_DISCONNECTED");
                } else if (intExtra3 == 1) {
                    ZLogger.d("A2DP_CONNECTION_STATE: STATE_CONNECTING");
                } else {
                    ZLogger.d("A2DP_CONNECTION_STATE: " + intExtra4 + " > " + intExtra3);
                }
                List<ProfileManagerCallback> list = BluetoothA2dpManager.this.d;
                if (list != null) {
                    for (ProfileManagerCallback profileManagerCallback : list) {
                        profileManagerCallback.onConnectionStateChanged(bluetoothDevice, intExtra3);
                    }
                }
            }
        }
    }

    public BluetoothA2dpManager(Context context) {
        super(context);
        this.g = null;
        BluetoothProfile.ServiceListener serviceListener = new BluetoothProfile.ServiceListener() { // from class: com.realsil.sdk.core.bluetooth.profile.BluetoothA2dpManager.1
            @Override // android.bluetooth.BluetoothProfile.ServiceListener
            public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
                String parseProfile = BluetoothHelper.parseProfile(i);
                ZLogger.i(parseProfile + " profile");
                if (i == 2) {
                    BluetoothA2dpManager.this.setBluetoothProfile((BluetoothA2dp) bluetoothProfile);
                }
            }

            @Override // android.bluetooth.BluetoothProfile.ServiceListener
            public void onServiceDisconnected(int i) {
                String parseProfile = BluetoothHelper.parseProfile(i);
                ZLogger.i(parseProfile + " profile");
                if (i == 2) {
                    BluetoothA2dpManager.this.setBluetoothProfile(null);
                }
            }
        };
        this.h = serviceListener;
        BluetoothAdapter bluetoothAdapter = this.b;
        if (bluetoothAdapter != null) {
            bluetoothAdapter.getProfileProxy(this.c, serviceListener, 2);
        }
        this.g = new BtBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.a2dp.profile.action.PLAYING_STATE_CHANGED");
        this.c.registerReceiver(this.g, intentFilter);
    }

    public static BluetoothA2dpManager getInstance() {
        return f;
    }

    public static void initial(Context context) {
        if (f == null) {
            synchronized (BluetoothA2dpManager.class) {
                if (f == null) {
                    f = new BluetoothA2dpManager(context);
                }
            }
        }
    }

    public boolean a2dpSrcConnect(byte[] bArr) {
        return a2dpSrcConnect(BluetoothHelper.convertMac(bArr));
    }

    public boolean a2dpSrcDisconnect(byte[] bArr) {
        return a2dpSrcDisconnect(BluetoothHelper.convertMac(bArr));
    }

    public void close() {
        ZLogger.d("close()");
        Context context = this.c;
        if (context != null) {
            try {
                context.unregisterReceiver(this.g);
            } catch (Exception e) {
                ZLogger.e(e.toString());
            }
        }
    }

    public boolean a2dpSrcConnect(String str) {
        BluetoothAdapter bluetoothAdapter = this.b;
        if (bluetoothAdapter != null && bluetoothAdapter.isEnabled()) {
            BluetoothDevice remoteDevice = this.b.getRemoteDevice(str);
            BP bp = this.e;
            if (bp == 0) {
                ZLogger.w("A2DP not initialized");
                return false;
            } else if (((BluetoothA2dp) bp).getConnectionState(remoteDevice) == 2) {
                ZLogger.w("a2dp already connected");
                return true;
            } else {
                ZLogger.d("a2dpSrcConnect: " + str);
                BluetoothProfileImpl.setPriority(this.e, remoteDevice, 100);
                return BluetoothProfileImpl.connectProfile(this.e, remoteDevice);
            }
        }
        ZLogger.w("BT not enabled");
        return false;
    }

    public boolean a2dpSrcDisconnect(String str) {
        BluetoothAdapter bluetoothAdapter = this.b;
        if (bluetoothAdapter != null && bluetoothAdapter.isEnabled()) {
            BluetoothDevice remoteDevice = this.b.getRemoteDevice(str);
            if (remoteDevice == null) {
                ZLogger.w("device is null");
                return false;
            }
            BP bp = this.e;
            if (bp == 0) {
                ZLogger.w("A2DP not initialized");
                return false;
            } else if (((BluetoothA2dp) bp).getConnectionState(remoteDevice) != 2) {
                ZLogger.w("A2DP already disconnected");
                return false;
            } else {
                ZLogger.d("a2dpSrcDisconnect" + str);
                BluetoothProfileImpl.setPriority(this.e, remoteDevice, 100);
                return BluetoothProfileImpl.disconnect(this.e, remoteDevice);
            }
        }
        ZLogger.w("BT not enabled");
        return false;
    }
}
