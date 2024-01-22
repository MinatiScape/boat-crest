package com.apex.bluetooth.broadcast;

import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.content.Intent;
/* loaded from: classes.dex */
public class c implements BluetoothProfile.ServiceListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ BluetoothDevice f2166a;
    public final /* synthetic */ Context b;
    public final /* synthetic */ a c;

    public c(a aVar, BluetoothDevice bluetoothDevice, Context context) {
        this.c = aVar;
        this.f2166a = bluetoothDevice;
        this.b = context;
    }

    @Override // android.bluetooth.BluetoothProfile.ServiceListener
    public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
        if (2 == i) {
            this.c.d = (BluetoothA2dp) bluetoothProfile;
            Intent intent = new Intent("east.apex.bluetooth.a2dp.proxy");
            intent.putExtra("device", this.f2166a);
            this.b.sendBroadcast(intent);
        }
    }

    @Override // android.bluetooth.BluetoothProfile.ServiceListener
    public void onServiceDisconnected(int i) {
        if (i == 2) {
            this.c.d = null;
        }
    }
}
