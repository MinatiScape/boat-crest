package com.realsil.sdk.core.bluetooth.profile;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.realsil.sdk.core.bluetooth.impl.BluetoothClassImpl;
import com.realsil.sdk.core.bluetooth.impl.BluetoothInputDeviceImpl;
import com.realsil.sdk.core.logger.ZLogger;
import java.lang.reflect.Method;
import org.eclipse.paho.android.service.MqttServiceConstants;
/* loaded from: classes12.dex */
public final class BluetoothInputDeviceManager extends HideProfileManager {
    public static BluetoothInputDeviceManager h;
    public BluetoothDevice i;
    public boolean j;
    public RCUReconnectReceiver k;
    public HidConnectionCallback l;
    public BluetoothProfile.ServiceListener m;

    /* loaded from: classes12.dex */
    public static abstract class HidConnectionCallback {
        public void onConnectionStateChange(boolean z) {
        }
    }

    /* loaded from: classes12.dex */
    public class RCUReconnectReceiver extends BroadcastReceiver {
        public RCUReconnectReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            ZLogger.i("RCUReconnectReceiver " + action);
            if ("android.bluetooth.input.profile.action.CONNECTION_STATE_CHANGED".equals(action)) {
                int intExtra = intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
                if (intExtra == 0) {
                    ZLogger.i(" Braodcast: RCU Disconnected!");
                    HidConnectionCallback hidConnectionCallback = BluetoothInputDeviceManager.this.l;
                    if (hidConnectionCallback != null) {
                        hidConnectionCallback.onConnectionStateChange(false);
                    }
                    BluetoothInputDeviceManager bluetoothInputDeviceManager = BluetoothInputDeviceManager.this;
                    bluetoothInputDeviceManager.l = null;
                    bluetoothInputDeviceManager.i = null;
                } else if (intExtra == 1) {
                    ZLogger.i(" Braodcast: RCU Connecting!");
                } else if (intExtra != 2) {
                    if (intExtra != 3) {
                        return;
                    }
                    ZLogger.i(" Braodcast: RCU Disconnecting!");
                } else {
                    ZLogger.i(" Braodcast: RCU Connected!");
                    HidConnectionCallback hidConnectionCallback2 = BluetoothInputDeviceManager.this.l;
                    if (hidConnectionCallback2 != null) {
                        hidConnectionCallback2.onConnectionStateChange(true);
                    }
                    BluetoothInputDeviceManager bluetoothInputDeviceManager2 = BluetoothInputDeviceManager.this;
                    bluetoothInputDeviceManager2.l = null;
                    bluetoothInputDeviceManager2.i = null;
                }
            } else if ("android.bluetooth.device.action.BOND_STATE_CHANGED" == action) {
                switch (intent.getIntExtra("android.bluetooth.device.extra.BOND_STATE", Integer.MIN_VALUE)) {
                    case 10:
                        ZLogger.i(" Braodcast: RCU unpaired!");
                        BluetoothInputDeviceManager bluetoothInputDeviceManager3 = BluetoothInputDeviceManager.this;
                        if (bluetoothInputDeviceManager3.j) {
                            bluetoothInputDeviceManager3.j = false;
                            BluetoothInputDeviceManager.this.i.createBond();
                            return;
                        }
                        HidConnectionCallback hidConnectionCallback3 = bluetoothInputDeviceManager3.l;
                        if (hidConnectionCallback3 != null) {
                            hidConnectionCallback3.onConnectionStateChange(false);
                        }
                        BluetoothInputDeviceManager bluetoothInputDeviceManager4 = BluetoothInputDeviceManager.this;
                        bluetoothInputDeviceManager4.l = null;
                        bluetoothInputDeviceManager4.i = null;
                        return;
                    case 11:
                        ZLogger.i(" Braodcast: RCU BONDING!");
                        return;
                    case 12:
                        ZLogger.i(" Braodcast: RCU BONDED!");
                        BluetoothInputDeviceManager bluetoothInputDeviceManager5 = BluetoothInputDeviceManager.this;
                        BluetoothDevice bluetoothDevice = bluetoothInputDeviceManager5.i;
                        if (bluetoothDevice != null) {
                            bluetoothInputDeviceManager5.a(bluetoothDevice);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public BluetoothInputDeviceManager(Context context) {
        super(context);
        BluetoothProfile.ServiceListener serviceListener = new BluetoothProfile.ServiceListener() { // from class: com.realsil.sdk.core.bluetooth.profile.BluetoothInputDeviceManager.1
            @Override // android.bluetooth.BluetoothProfile.ServiceListener
            public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
                if (4 == i) {
                    try {
                        BluetoothInputDeviceManager.this.f = bluetoothProfile.getClass().asSubclass(Class.forName(BluetoothInputDeviceImpl.CLASS_NAME));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    BluetoothInputDeviceManager.this.g = bluetoothProfile;
                    ZLogger.i("get Bluetooth input device proxy");
                }
            }

            @Override // android.bluetooth.BluetoothProfile.ServiceListener
            public void onServiceDisconnected(int i) {
                if (4 == i) {
                    BluetoothInputDeviceManager bluetoothInputDeviceManager = BluetoothInputDeviceManager.this;
                    bluetoothInputDeviceManager.f = null;
                    bluetoothInputDeviceManager.g = null;
                    ZLogger.i("close Bluetooth input device proxy");
                }
            }
        };
        this.m = serviceListener;
        BluetoothAdapter bluetoothAdapter = this.b;
        if (bluetoothAdapter != null) {
            bluetoothAdapter.getProfileProxy(context, serviceListener, 4);
        }
        this.k = new RCUReconnectReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.input.profile.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.device.action.BOND_STATE_CHANGED");
        this.c.registerReceiver(this.k, intentFilter);
    }

    public static BluetoothInputDeviceManager getInstance() {
        return h;
    }

    public static void initial(Context context) {
        ZLogger.v("initial");
        h = new BluetoothInputDeviceManager(context);
    }

    public boolean checkProfileConnect() {
        if (this.f != null) {
            return true;
        }
        ZLogger.d("checkProfileConnect(): profile not connect");
        return false;
    }

    public void close() {
        if (this.f != null) {
            this.b.closeProfileProxy(4, this.g);
        }
        if (this.c != null && this.k != null) {
            try {
                ZLogger.d("unregisterReceiver");
                this.c.unregisterReceiver(this.k);
            } catch (Exception e) {
                ZLogger.e(e.toString());
            }
        }
        this.l = null;
    }

    public boolean connect(String str, HidConnectionCallback hidConnectionCallback) {
        return a(this.b.getRemoteDevice(str), hidConnectionCallback);
    }

    public int getConnectionState(String str) {
        return getConnectionState(this.b.getRemoteDevice(str));
    }

    public boolean isHidDevice(String str) {
        return isHidDevice(this.b.getRemoteDevice(str));
    }

    public boolean isHogpConnect(String str) {
        return getConnectionState(str) == 2;
    }

    public static boolean isHidDevice(BluetoothDevice bluetoothDevice) {
        return BluetoothClassImpl.isHidDevice(bluetoothDevice.getBluetoothClass());
    }

    public final boolean a(BluetoothDevice bluetoothDevice, HidConnectionCallback hidConnectionCallback) {
        ZLogger.d("connect()");
        this.l = hidConnectionCallback;
        this.i = bluetoothDevice;
        this.j = false;
        if (checkProfileConnect()) {
            if (bluetoothDevice.getBondState() != 12) {
                ZLogger.i("connect with not bond device, bond first, current state: " + bluetoothDevice.getBondState());
                bluetoothDevice.createBond();
                return false;
            }
            try {
                ZLogger.d("connect with bonded device, remove bond first.");
                Method method = bluetoothDevice.getClass().getMethod("removeBond", new Class[0]);
                if (method != null) {
                    boolean booleanValue = ((Boolean) method.invoke(bluetoothDevice, new Object[0])).booleanValue();
                    if (booleanValue) {
                        this.j = booleanValue;
                        ZLogger.d("removeBond(): result: " + booleanValue);
                        return booleanValue;
                    }
                    return a(bluetoothDevice);
                }
            } catch (Exception e) {
                ZLogger.e("removeBond(): An exception occured, e = " + e);
            }
            return a(bluetoothDevice);
        }
        return false;
    }

    public boolean isHogpConnect(BluetoothDevice bluetoothDevice) {
        return getConnectionState(bluetoothDevice) == 2;
    }

    public final boolean a(BluetoothDevice bluetoothDevice) {
        try {
            Method method = this.f.getMethod(MqttServiceConstants.CONNECT_ACTION, BluetoothDevice.class);
            if (method != null) {
                boolean booleanValue = ((Boolean) method.invoke(this.g, bluetoothDevice)).booleanValue();
                ZLogger.d("connect(): connect result: " + booleanValue);
                return booleanValue;
            }
        } catch (Exception e) {
            ZLogger.e("connect(): An exception occured while connect device, e = " + e);
        }
        return false;
    }
}
