package com.realsil.sdk.core.bluetooth.profile;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import com.realsil.sdk.core.logger.ZLogger;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes12.dex */
public abstract class PublicProfileManager<BP extends BluetoothProfile> {

    /* renamed from: a  reason: collision with root package name */
    public BluetoothManager f13576a;
    public BluetoothAdapter b;
    public Context c;
    public List<ProfileManagerCallback> d;
    public BP e;

    public PublicProfileManager(Context context) {
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

    public BP getBluetoothProfile() {
        return this.e;
    }

    public int getConnectionState(BluetoothDevice bluetoothDevice) {
        BluetoothAdapter bluetoothAdapter = this.b;
        if (bluetoothAdapter != null && bluetoothAdapter.isEnabled()) {
            if (getBluetoothProfile() == null) {
                ZLogger.w("not supported > " + this.e.getClass().getName());
                return -1;
            }
            return this.e.getConnectionState(bluetoothDevice);
        }
        ZLogger.w("BT not enabled");
        return -1;
    }

    public boolean initialize() {
        if (this.f13576a == null) {
            BluetoothManager bluetoothManager = (BluetoothManager) this.c.getSystemService("bluetooth");
            this.f13576a = bluetoothManager;
            if (bluetoothManager == null) {
                ZLogger.w("Unable to initialize BluetoothManager.");
                return false;
            }
        }
        if (this.b == null) {
            BluetoothAdapter adapter = this.f13576a.getAdapter();
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
        return getBluetoothProfile() != null;
    }

    public void removeProfileManagerCallback(ProfileManagerCallback profileManagerCallback) {
        List<ProfileManagerCallback> list = this.d;
        if (list != null) {
            list.remove(profileManagerCallback);
        }
    }

    public void setBluetoothProfile(BP bp) {
        this.e = bp;
    }
}
