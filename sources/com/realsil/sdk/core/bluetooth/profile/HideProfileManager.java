package com.realsil.sdk.core.bluetooth.profile;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import com.realsil.sdk.core.logger.ZLogger;
import java.lang.String;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes12.dex */
public abstract class HideProfileManager<CN extends String> {

    /* renamed from: a  reason: collision with root package name */
    public BluetoothManager f13575a;
    public BluetoothAdapter b;
    public Context c;
    public List<ProfileManagerCallback> d;
    public CN e;
    public Class f;
    public BluetoothProfile g;

    public HideProfileManager(Context context) {
        this.c = context.getApplicationContext();
        initialize();
    }

    public void addProfileManagerCallback(ProfileManagerCallback profileManagerCallback) {
        if (this.d == null) {
            this.d = new CopyOnWriteArrayList();
        }
        if (this.d.contains(profileManagerCallback)) {
            return;
        }
        this.d.add(profileManagerCallback);
    }

    public CN getClassName() {
        return this.e;
    }

    public int getConnectionState(BluetoothDevice bluetoothDevice) {
        BluetoothAdapter bluetoothAdapter = this.b;
        if (bluetoothAdapter != null && bluetoothAdapter.isEnabled()) {
            Class cls = this.f;
            if (cls == null) {
                ZLogger.w("not supported > " + this.f.getName());
                return -1;
            }
            try {
                Method method = cls.getMethod("getConnectionState", BluetoothDevice.class);
                if (method != null) {
                    Integer num = (Integer) method.invoke(this.g, bluetoothDevice);
                    ZLogger.d("result: " + num);
                    return num.intValue();
                }
            } catch (Exception e) {
                ZLogger.e("An exception occured,  " + e.toString());
            }
            return -1;
        }
        ZLogger.w("BT not enabled");
        return -1;
    }

    public boolean initialize() {
        if (this.f13575a == null) {
            BluetoothManager bluetoothManager = (BluetoothManager) this.c.getSystemService("bluetooth");
            this.f13575a = bluetoothManager;
            if (bluetoothManager == null) {
                ZLogger.w("Unable to initialize BluetoothManager.");
                return false;
            }
        }
        if (this.b == null) {
            BluetoothAdapter adapter = this.f13575a.getAdapter();
            this.b = adapter;
            if (adapter == null) {
                ZLogger.w("Unable to obtain a BluetoothAdapter.");
                return false;
            }
            return true;
        }
        return true;
    }

    public boolean isProfileSupported() {
        return this.f != null;
    }

    public void removeProfileManagerCallback(ProfileManagerCallback profileManagerCallback) {
        List<ProfileManagerCallback> list = this.d;
        if (list != null) {
            list.remove(profileManagerCallback);
        }
    }

    public void setClassName(CN cn) {
        this.e = cn;
    }
}
