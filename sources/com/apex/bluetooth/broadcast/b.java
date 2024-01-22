package com.apex.bluetooth.broadcast;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.content.Intent;
/* loaded from: classes.dex */
public class b implements BluetoothProfile.ServiceListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ BluetoothDevice f2165a;
    public final /* synthetic */ Context b;
    public final /* synthetic */ a c;

    public b(a aVar, BluetoothDevice bluetoothDevice, Context context) {
        this.c = aVar;
        this.f2165a = bluetoothDevice;
        this.b = context;
    }

    @Override // android.bluetooth.BluetoothProfile.ServiceListener
    public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
        if (i == 1) {
            this.c.c = (BluetoothHeadset) bluetoothProfile;
            Intent intent = new Intent("east.apex.bluetooth.headset.proxy");
            intent.putExtra("device", this.f2165a);
            this.b.sendBroadcast(intent);
        }
    }

    @Override // android.bluetooth.BluetoothProfile.ServiceListener
    public void onServiceDisconnected(int i) {
        if (i == 1) {
            this.c.c = null;
        }
    }
}
